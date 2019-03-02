import { Injectable } from '@angular/core';
import { HttpClient,HttpHeaders } from '@angular/common/http';
import { environment } from './environment';

@Injectable({providedIn: 'root'})
export class ElectionsService
{
    authHeaders: HttpHeaders=new HttpHeaders({ }); selectedCamp=null;
    constructor(private http: HttpClient) { }
    invalidate() { this.authHeaders=new HttpHeaders({ }); }

    authenticated(): Boolean
    { return this.authHeaders.keys().length>0; }

    getFullAddress(path: string): string
    { return `http://localhost:${environment.serverport}/${path}`; }

    sendGetRequest(path: string,callback)
    {
        this.http.get(this.getFullAddress(path),{headers: this.authHeaders}).
                subscribe(callback);
    }

    sendPostRequest(path: string,body,callback)
    {
        const headers=this.authHeaders.set('Content-Type','application/json');
        this.http.post(this.getFullAddress(path),body,{headers: headers}).subscribe(callback);
    }
}
