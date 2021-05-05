import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgxChartsModule } from '@swimlane/ngx-charts';

import { ProfileComponent } from './profile.component';
import { ProfileProductsComponent } from './profile-products/profile-products.component';
import { ProfileNewproductComponent } from './profile-newproduct/profile-newproduct.component';
import { ProfileSalesComponent } from './profile-sales/profile-sales.component';
import { ProfileShoppingComponent } from './profile-shopping/profile-shopping.component';

import { ProfileRoutes } from './profile.routing';

@NgModule({
    declarations: [
        ProfileComponent,
        ProfileProductsComponent,
        ProfileNewproductComponent,
        ProfileSalesComponent,
        ProfileShoppingComponent
    ],
    imports: [
        CommonModule,
        ProfileRoutes,
        FormsModule,
        BrowserAnimationsModule,
        NgxChartsModule
    ]
})
export class ProfileModule { }