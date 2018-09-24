import { Component, OnInit, ViewEncapsulation } from '@angular/core';

@Component({
  selector: 'app-product-grade',
  templateUrl: '././product-grade.component.html',
  styleUrls: ['././product-grade.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class ProductGradeComponent implements OnInit {

  constructor() { }
 public status: any = {
    isFirstOpen: true
   // isFirstDisabled: false
  };
  ngOnInit() {
  }

}
