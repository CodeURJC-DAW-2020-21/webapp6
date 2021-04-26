import { RouterModule } from '@angular/router';

import { PrincipalComponent } from './components/products/principal.component';
import { CategoryComponent } from './components/category/category.component';
//import { BookListComponent } from './book-list.component';
//import { BookDetailComponent } from './book-detail.component';

const appRoutes = [
    { path: 'new', component: PrincipalComponent,  },
    { path: 'new/category', component: CategoryComponent},
    { path: '', redirectTo: 'new', pathMatch: 'full' }
  ]
/*
const appRoutes = [
  { path: 'book/:id', component: BookDetailComponent,  },
  { path: 'books', component: BookListComponent },
  { path: '', redirectTo: 'books', pathMatch: 'full' }^
]*/

export const routing = RouterModule.forRoot(appRoutes);