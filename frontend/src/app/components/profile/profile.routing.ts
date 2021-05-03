import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ProfileComponent } from './profile.component';
import { ProfileProductsComponent } from './profile-products/profile-products.component';
import { ProfileNewproductComponent } from './profile-newproduct/profile-newproduct.component';
import { ProfileSalesComponent } from './profile-sales/profile-sales.component';
import { ProfileShoppingComponent } from './profile-shopping/profile-shopping.component';

const routes: Routes = [
  {path: 'new/profile', component: ProfileComponent,
  children: [
    {path: '', component: ProfileProductsComponent},
    {path: 'profile-newproduct', component: ProfileNewproductComponent},
    {path: 'profile-sales', component: ProfileSalesComponent},
    {path: 'profile-shopping', component: ProfileShoppingComponent}
  ]},
];

export const ProfileRoutes = RouterModule.forChild(routes);
