import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-profile-sales',
  templateUrl: './profile-sales.component.html',
  styleUrls: ['./profile-sales.component.css']
})
export class ProfileSalesComponent implements OnInit {

  constructor(private router:Router) { }

  ngOnInit() {
  }

}
