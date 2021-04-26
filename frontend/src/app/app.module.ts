import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { PrincipalComponent } from './components/products/principal.component';
import { CategoryComponent } from './components/category/category.component';
import { routing } from './app.routing';

@NgModule({
  declarations: [
    AppComponent, PrincipalComponent, CategoryComponent
  ],
  imports: [
    BrowserModule, routing
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
