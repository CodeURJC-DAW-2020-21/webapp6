import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-profile-shopping',
  templateUrl: './profile-shopping.component.html',
  styleUrls: ['./profile-shopping.component.css']
})
export class ProfileShoppingComponent implements OnInit {

  constructor(private router:Router) { }

  ngOnInit() {
  }

}
