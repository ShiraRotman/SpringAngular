import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { RouterModule,Routes } from '@angular/router';

import { CampListComponent } from './list.component';
import { CampComponent } from './camp.component';
import { LoginComponent } from './login.component';
import { RouteComponent } from './route.component';

const routes: Routes=
[
    { path: '',pathMatch: 'full',redirectTo: 'login' },
    { path: 'login',component: LoginComponent },
    { path: 'campaigns',component: CampListComponent },
    { path: 'campaign',component: CampComponent }
]

@NgModule
({
    declarations: [CampListComponent,CampComponent,LoginComponent,RouteComponent],
    imports: [BrowserModule,FormsModule,HttpClientModule,RouterModule.forRoot(routes)],
    providers: [], bootstrap: [RouteComponent]
})
export class ElectionsModule { }