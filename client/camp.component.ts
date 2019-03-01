import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

import { ElectionsService } from './elec.service';
@Component ({ templateUrl: './camp.component.html' })

export class CampComponent
{
    campaign=null; leaders=null; userVote=null;
    constructor(private http: HttpClient,private router: Router,private service: ElectionsService)
    {
        if (!service.authenticated()) router.navigateByUrl('/login');
        else if (service.selectedCamp==null) router.navigateByUrl('/campaigns');
        else
        {
            this.campaign=service.selectedCamp;
            service.selectedCamp=null;
        }
    }
}
