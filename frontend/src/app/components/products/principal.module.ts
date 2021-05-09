import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PrincipalComponent } from './principal.component';
import { ProductsAllComponent } from './products-all/products-all.component';
import { ProductsRatingComponent } from './products-rating/products-rating.component';
import { PrincipalRoutes } from './principal.routing';

@NgModule({
  imports: [
    CommonModule, PrincipalRoutes
  ],
  declarations: [
    PrincipalComponent,
    ProductsAllComponent,
    ProductsRatingComponent
  ]
})
export class PrincipalModule { }
