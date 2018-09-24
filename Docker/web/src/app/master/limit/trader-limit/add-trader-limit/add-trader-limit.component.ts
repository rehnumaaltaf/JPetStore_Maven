import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-add-trader-limit',
  templateUrl: './add-trader-limit.component.html',
  styleUrls: ['./add-trader-limit.component.css']
})
export class AddTraderLimitComponent implements OnInit {
  public status: any = {
    isFirstOpen: true,
    isFirstDisabled: false
  };
  
  constructor() { }

  ngOnInit() {
  }

}
