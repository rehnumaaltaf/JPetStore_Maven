import { Component, OnInit } from '@angular/core';
import { AccordionModule } from 'ngx-bootstrap';
@Component({
  selector: 'app-add-unit-limit',
  templateUrl: './add-unit-limit.component.html',
  styleUrls: ['./add-unit-limit.component.css']
})
export class AddUnitLimitComponent implements OnInit {

   public status: any = {
    isFirstOpen: true,
    isFirstDisabled: false
  };
  
  constructor() { }

  ngOnInit() {
  }

}
