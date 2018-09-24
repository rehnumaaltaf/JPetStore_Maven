import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-view-legal-entity-limit',
  templateUrl: './view-legal-entity-limit.component.html',
  styleUrls: ['./view-legal-entity-limit.component.css']
})
export class ViewLegalEntityLimitComponent implements OnInit {
 public status: any = {
    isFirstOpen: true,
    isFirstDisabled: false
  };
  
  constructor() { }

  ngOnInit() {
  }

}
