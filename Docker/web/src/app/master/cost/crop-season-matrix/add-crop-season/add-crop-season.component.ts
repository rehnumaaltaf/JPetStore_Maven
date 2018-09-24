import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-add-crop-season',
  templateUrl: './add-crop-season.component.html',
  styleUrls: ['./add-crop-season.component.css']
})
export class AddCropSeasonComponent implements OnInit {
  public status: any = {
    isFirstOpen: true,
    isFirstDisabled: false
  };
  constructor() { }

  ngOnInit() {
  }

}
