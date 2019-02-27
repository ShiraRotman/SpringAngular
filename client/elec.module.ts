import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { CampComponent } from './camp.component';
@NgModule
({
    declarations: [CampComponent],
    imports: [BrowserModule,HttpClientModule],
    providers: [],
    bootstrap: [CampComponent]
})
export class ElectionsModule { }