import { Component, OnInit } from '@angular/core';
import { AccordionModule } from 'ngx-bootstrap';
@Component({
  selector: 'app-view-unit-limit',
  templateUrl: './view-unit-limit.component.html',
  styleUrls: ['./view-unit-limit.component.css']
})
export class ViewUnitLimitComponent implements OnInit {

   public status: any = {
    isFirstOpen: true,
    isFirstDisabled: false
  };
  
  constructor() { }

  ngOnInit() {
  }

}
