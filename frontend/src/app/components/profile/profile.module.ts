import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

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
        FormsModule
    ]
})
export class ProfileModule { }