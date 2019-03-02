import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { ElectionsService } from './elec.service';
@Component ({ templateUrl: './camp.component.html' })

export class CampComponent
{
    campaign=null; leaders=null; userVote=null; failed=false; status=null;
    constructor(private router: Router,private service: ElectionsService)
    {
        if (!service.authenticated()) router.navigateByUrl('/login');
        else if (service.selectedCamp==null) router.navigateByUrl('/campaigns');
        else
        {
            this.campaign=service.selectedCamp; service.selectedCamp=null;
            const now=Date.now(),startDate=Date.parse(this.campaign.startDate);
            const endDate=Date.parse(this.campaign.endDate);
            if (startDate>now) this.status='The campaign has not yet started!';
            else if (endDate<now) this.status='The campaign has ended!';
            this.getVotesData();
        }
    }

    vote()
    {
        const voteParams={ campaign: this.campaign.campaignID, foruser: this.userVote };
        this.service.sendPostRequest('vote',voteParams,data=>
        {
            if (data!=null) { this.failed=false; this.getVotesData(); }
            else this.failed=true;
        });
    }

    private getVotesData()
    {
        const campaignID=this.campaign.campaignID;
        this.service.sendGetRequest(`vote?campaign=${campaignID}`,data=>this.userVote=data[1]);
        this.service.sendGetRequest(`leaders?campaign=${campaignID}`,data=>
        {
            this.leaders=data;
            for (let index=0;index<this.leaders.length;index++)
            {
                this.leaders[index]=
                {
                    place: index+1,
                    username: data[index][0],
                    votes: data[index][1]
                }
            }
        });
    }
}
