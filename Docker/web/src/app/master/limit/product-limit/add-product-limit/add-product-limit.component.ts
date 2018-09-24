import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-add-product-limit',
  templateUrl: './add-product-limit.component.html',
  styleUrls: ['./add-product-limit.component.css']
})
export class AddProductLimitComponent implements OnInit {
  public status: any = {
    isFirstOpen: true,
    isFirstDisabled: false
  };
  
  constructor() { }

  ngOnInit() {
  }

}
