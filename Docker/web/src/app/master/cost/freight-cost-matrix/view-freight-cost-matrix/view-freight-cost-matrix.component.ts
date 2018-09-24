import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-view-freight-cost-matrix',
  templateUrl: './view-freight-cost-matrix.component.html',
  styleUrls: ['./view-freight-cost-matrix.component.css']
})
export class ViewFreightCostMatrixComponent implements OnInit {

  public status: any = {
    isFirstOpen: true,
    isFirstDisabled: false
  };
  constructor() { }

  ngOnInit() {
  }

}
