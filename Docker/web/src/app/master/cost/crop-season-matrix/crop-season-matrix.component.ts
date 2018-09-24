import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-crop-season-matrix',
  templateUrl: './crop-season-matrix.component.html',
  styleUrls: ['./crop-season-matrix.component.css']
})
export class CropSeasonMatrixComponent implements OnInit {
  public status: any = {
    isFirstOpen: true,
    isFirstDisabled: false
  };
  constructor() { }

  ngOnInit() {
  }

}
