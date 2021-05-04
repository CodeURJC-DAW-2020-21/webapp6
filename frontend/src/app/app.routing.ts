import { RouterModule } from '@angular/router';

// **** PRODUCTS ****
import { PrincipalComponent } from './components/products/principal.component';
import { ErrorComponent } from './components/products/error.component';
import { SingleProductComponent } from './components/products/single-product/single-product.component';
import { EditProductComponent } from './components/products/edit-product/edit-product.component';
// **** CATEGORY ****
import { CategoryComponent } from './components/category/category.component';
// **** USER ****
import { LoginComponent } from './components/user/login/login.component';
import { SignInComponent } from './components/user/sign-in/sign-in.component';
// **** PROFILE ****
import { ProfileComponent } from './components/profile/profile.component';
// **** CART ****
import { CartComponent } from './components/cart/cart.component';
import { CardPaymentComponent } from './components/cart/cardPayment/cardPayment.component';

const appRoutes = [
    // **** PRODUCTS ****
    { path: 'new', component: PrincipalComponent },
    { path: 'new/single-product', component: SingleProductComponent },
    { path: 'new/edit-product', component: EditProductComponent },
    { path: 'new/error', component: ErrorComponent },
    // **** CATEGORY ****
    { path: 'new/category', component: CategoryComponent},
    // **** USER ****
    { path: 'new/login', component: LoginComponent},
    { path: 'new/sign_in', component: SignInComponent},
    // **** PROFILE ****
    { path: 'new/profile', component: ProfileComponent},
    // **** CART ****
    { path: 'new/cart', component: CartComponent},
    { path: 'new/cart/cardPayment', component: CardPaymentComponent},
    { path: '', redirectTo: 'new', pathMatch: 'full' }
  ]

export const routing = RouterModule.forRoot(appRoutes);