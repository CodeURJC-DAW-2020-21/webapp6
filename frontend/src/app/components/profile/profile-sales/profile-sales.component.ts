import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ProfileService } from '../../../services/profile.service';
import { LoginService } from '../../../services/login.service';

@Component({
  selector: 'app-profile-sales',
  templateUrl: './profile-sales.component.html',
  styleUrls: ['./profile-sales.component.css']
})
export class ProfileSalesComponent implements OnInit {

  sales: Object[];

  view: any[] = [1200, 370];
  legendTitle: string = 'Months';
  legendTitleMulti: string = '';
  legendPosition: string = 'below'; // ['right', 'below']
  legend: boolean = true;
  xAxis: boolean = true;
  yAxis: boolean = false;

  yAxisLabel: string = 'Products';
  xAxisLabel: string = 'Months';
  showXAxisLabel: boolean = true;
  showYAxisLabel: boolean = true;

  //maxXAxisTickLength: number = 30;
  //maxYAxisTickLength: number = 30;
  trimXAxisTicks: boolean = false;
  trimYAxisTicks: boolean = false;
  rotateXAxisTicks: boolean = false;

  animations: boolean = true; // animations on load
  showGridLines: boolean = true; // grid lines
  showDataLabel: boolean = true; // numbers on bars
  gradient: boolean = false;
  colorScheme = {
    domain: ['#704FC4', '#4B852C', '#B67A3D', '#5B6FC8', '#25706F']
  };
  schemeType: string = 'ordinal'; // 'ordinal' or 'linear'
  barPadding: number = 5
  tooltipDisabled: boolean = false;
  yScaleMax: number = 9000;
  roundEdges: boolean = false;

  constructor(private router: Router, private profileService: ProfileService) { }

  ngOnInit() {
    this.getSales()
  }

  getSales() {
    this.profileService.getSales().subscribe(
      sales => {
        this.getData(sales);
      },
      error => console.log(error)
    );
  }

  private getData(sales: Object[]) {
    this.sales = [
      {
        "name": "January",
        "value": sales["January"]
      },
      {
        "name": "February",
        "value": sales["February"]
      },
      {
        "name": "March",
        "value": sales["March"]
      },
      {
        "name": "April",
        "value": sales["April"]
      },
      {
        "name": "May",
        "value": sales["May"]
      },
      {
        "name": "June",
        "value": sales["June"]
      },
      {
        "name": "July",
        "value": sales["July"]
      },
      {
        "name": "August",
        "value": sales["August"]
      },
      {
        "name": "September",
        "value": sales["September"]
      },
      {
        "name": "October",
        "value": sales["October"]
      },
      {
        "name": "November",
        "value": sales["November"]
      },
      {
        "name": "December",
        "value": sales["December"]
      },
    ]
  }
}
