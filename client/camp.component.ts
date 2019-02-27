import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from './environment';

@Component
({
    selector: 'app-root',
    templateUrl: './camp.component.html'
})

export class CampComponent
{
    campaign=new Object();
    constructor(private http: HttpClient)
    {
        http.get(`http://localhost:${environment.serverport}/campaign`).subscribe(
                data=>this.campaign=data);
    }
}
