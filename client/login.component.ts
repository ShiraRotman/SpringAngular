import { Component } from '@angular/core';
import { HttpClient,HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';

import { ElectionsService } from './elec.service';
@Component ({ templateUrl: './login.component.html' })

export class LoginComponent
{
    credentials={username:'',password:''};
    authFailed=false;

    constructor(private http: HttpClient,private router: Router,private service:
            ElectionsService) { }

    login()
    {
        const credentials=this.credentials;
        const headers=new HttpHeaders(credentials.username!=''?
        {
            Authorization: 'Basic ' + btoa(credentials.username + ':' +
                    credentials.password)
        }:{ });

        this.http.get(this.service.getFullAddress('user'),{headers: headers}).
                subscribe(response=>
        {
            this.authFailed=((response===null)||(!response.hasOwnProperty('name')));
            if (!this.authFailed)
            {
                this.service.authHeaders=headers;
                this.router.navigateByUrl('/campaigns');
            }
            else if (this.service.authenticated()) this.service.invalidate();
        });
    }
}