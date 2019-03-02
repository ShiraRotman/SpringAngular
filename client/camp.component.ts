import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { ElectionsService } from './elec.service';
@Component ({ templateUrl: './camp.component.html' })

export class CampComponent
{
    campaign=null; leaders=null; userVote=null; failed=false;
    constructor(private router: Router,private service: ElectionsService)
    {
        if (!service.authenticated()) router.navigateByUrl('/login');
        else if (service.selectedCamp==null) router.navigateByUrl('/campaigns');
        else
        {
            this.campaign=service.selectedCamp; service.selectedCamp=null;
            const campaignID=this.campaign.campaignID;
            service.sendGetRequest(`vote?campaign=${campaignID}`,data=>this.userVote=data[1]);
            service.sendGetRequest(`leaders?campaign=${campaignID}`,data=>
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

    vote()
    {
        //const voteParams=`?campaign=${this.campaign.campaignID}&foruser=${this.userVote}`;
        const voteParams={ campaign: this.campaign.campaignID, foruser: this.userVote };
        this.service.sendPostRequest('vote',voteParams,data=>this.failed=(data==null));
    }
}
