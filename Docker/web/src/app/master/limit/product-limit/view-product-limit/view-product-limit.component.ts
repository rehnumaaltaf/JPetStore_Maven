import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-view-product-limit',
  templateUrl: './view-product-limit.component.html',
  styleUrls: ['./view-product-limit.component.css']
})
export class ViewProductLimitComponent implements OnInit {
  public status: any = {
    isFirstOpen: true,
    isFirstDisabled: false
  };
  
  constructor() { }

  ngOnInit() {
  }

}
