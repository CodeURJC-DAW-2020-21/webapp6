import { Routes, RouterModule } from '@angular/router';

import { PrincipalComponent } from './principal.component';
import { ProductsAllComponent } from './products-all/products-all.component';
import { ProductsRatingComponent } from './products-rating/products-rating.component';

const routes: Routes = [
  {path: 'new', component: PrincipalComponent,
  children: [
    {path: '', component: ProductsAllComponent},
    {path: 'products-rating', component: ProductsRatingComponent}
  ]},
];

export const PrincipalRoutes = RouterModule.forChild(routes);
