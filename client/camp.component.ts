import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { ElectionsService } from './elec.service';
@Component ({ templateUrl: './camp.component.html' })

export class CampComponent
{
    campaign=new Object();
    constructor(private http: HttpClient,private service: ElectionsService)
    {
        http.get(service.getFullAddress('campaign'),{ headers: service.authHeaders }).
                subscribe(data=>this.campaign=data);
    }
}
