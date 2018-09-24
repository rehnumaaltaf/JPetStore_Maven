import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-view-crop-season',
  templateUrl: './view-crop-season.component.html',
  styleUrls: ['./view-crop-season.component.css']
})
export class ViewCropSeasonComponent implements OnInit {
 public status: any = {
    isFirstOpen: true,
    isFirstDisabled: false
  };
  constructor() { }

  ngOnInit() {
  }

}
