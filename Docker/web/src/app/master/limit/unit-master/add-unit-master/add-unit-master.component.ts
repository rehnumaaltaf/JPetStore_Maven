import { Component, OnInit } from '@angular/core';
import { AccordionModule } from 'ngx-bootstrap';
@Component({
  selector: 'app-add-unit-master',
  templateUrl: './add-unit-master.component.html',
  styleUrls: ['./add-unit-master.component.css']
})
export class AddUnitMasterComponent implements OnInit {

  public status: any = {
    isFirstOpen: true,
    isFirstDisabled: false
  };
  constructor() { }

  ngOnInit() {
  }

}
