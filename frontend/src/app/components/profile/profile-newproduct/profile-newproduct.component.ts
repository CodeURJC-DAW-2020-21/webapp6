import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-profile-newproduct',
  templateUrl: './profile-newproduct.component.html',
  styleUrls: ['./profile-newproduct.component.css']
})
export class ProfileNewproductComponent implements OnInit {

  constructor(private router:Router) { }

  ngOnInit() {
  }

}
