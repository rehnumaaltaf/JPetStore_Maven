import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-add-legal-entity-limit',
  templateUrl: './add-legal-entity-limit.component.html',
  styleUrls: ['./add-legal-entity-limit.component.css']
})
export class AddLegalEntityLimitComponent implements OnInit {
 public status: any = {
    isFirstOpen: true,
    isFirstDisabled: false
  };
  
  constructor() { }

  ngOnInit() {
  }

}
