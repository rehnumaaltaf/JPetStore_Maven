import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-view-trader-limit',
  templateUrl: './view-trader-limit.component.html',
  styleUrls: ['./view-trader-limit.component.css']
})
export class ViewTraderLimitComponent implements OnInit {
  public status: any = {
    isFirstOpen: true,
    isFirstDisabled: false
  };

  constructor() { }

  ngOnInit() {
  }

}
