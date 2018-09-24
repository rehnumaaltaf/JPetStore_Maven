import { Component, OnInit,ViewEncapsulation } from '@angular/core';

@Component({
  selector: 'app-view-product-grade',
  templateUrl: './view-product-grade.component.html',
  styleUrls: ['./view-product-grade.component.css'],
  encapsulation: ViewEncapsulation.None, 
})
export class ViewProductGradeComponent implements OnInit {

  constructor() { }
  public status: any = {
    isFirstOpen: true
   // isFirstDisabled: false
  };
  ngOnInit() {
  }

}
