import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-profile-products',
  templateUrl: './profile-products.component.html',
  styleUrls: ['./profile-products.component.css']
})
export class ProfileProductsComponent implements OnInit {

  constructor(private router:Router) { }

  ngOnInit() {
  }

}
