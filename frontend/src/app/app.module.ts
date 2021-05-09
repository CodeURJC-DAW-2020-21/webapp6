import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
//import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
//import { NgxChartsModule } from '@swimlane/ngx-charts';

import { AppComponent } from './app.component';
// **** PRODUCTS ****
//import { PrincipalComponent } from './components/products/principal.component';
import { PrincipalModule } from './components/products/principal.module';
import { HeaderComponent } from './components/products/header/header.component';
import { FooterComponent } from './components/products/footer/footer.component';
import { ErrorComponent } from './components/products/error.component';
import { SingleProductComponent } from './components/products/single-product/single-product.component';
import { EditProductComponent } from './components/products/edit-product/edit-product.component';
// **** CATEGORY ****
import { CategoryComponent } from './components/category/category.component';
// **** USER ****
import { LoginComponent } from './components/user/login/login.component';
import { SignInComponent } from './components/user/sign-in/sign-in.component';
// **** PROFILE ****
//import { ProfileComponent } from './components/profile/profile.component';
import { ProfileModule } from './components/profile/profile.module';
// **** CART ****
import { CartComponent } from './components/cart/cart.component';
import { CardPaymentComponent } from './components/cart/cardPayment/cardPayment.component';

import { routing } from './app.routing';

@NgModule({
  declarations: [
    AppComponent, HeaderComponent, FooterComponent, ErrorComponent,// PrincipalComponent,
    SingleProductComponent, EditProductComponent,
    CategoryComponent,
    LoginComponent, SignInComponent,
    //ProfileComponent,
    CartComponent, CardPaymentComponent
  ],
  imports: [
    BrowserModule, routing, HttpClientModule, 
    FormsModule, ProfileModule, PrincipalModule
    //BrowserAnimationsModule, NgxChartsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
