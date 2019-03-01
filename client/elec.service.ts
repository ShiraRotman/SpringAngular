import { Injectable } from '@angular/core';
import { HttpHeaders } from '@angular/common/http';
import { environment } from './environment';

@Injectable({providedIn: 'root'})
export class ElectionsService
{
    authHeaders: HttpHeaders=new HttpHeaders({ });
    invalidate() { this.authHeaders=new HttpHeaders({ }); }

    authenticated(): Boolean
    { return Object.getOwnPropertyNames(this.authHeaders).length>0; }

    getFullAddress(path: string): string
    { return `http://localhost:${environment.serverport}/${path}`; }
}
