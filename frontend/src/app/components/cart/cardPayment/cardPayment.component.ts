import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute} from '@angular/router';

import { CartService } from '../../../services/cart.service';

@Component({
  selector: 'app-cardPayment',
  templateUrl: './cardPayment.component.html',
  styleUrls: ['./cardPayment.component.css']
  //styleUrls: ['../../../../assets/css/styleCard.css']
})
export class CardPaymentComponent implements OnInit {

  idRequest:number;

  constructor(private router:Router, private activatedRoute:ActivatedRoute, private cartService:CartService) { 
    this.idRequest = activatedRoute.snapshot.params['idRequest']
  }

  ngOnInit() {
  }

  pay(){
    this.cartService.paid(this.idRequest).subscribe(
      _ => this.router.navigate(['/new']),
      error => console.log(error)
    );
  }
}
