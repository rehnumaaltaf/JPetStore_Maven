import { Component, OnInit } from '@angular/core';
import { AccordionModule } from 'ngx-bootstrap';
@Component({
  selector: 'app-view-unit-master',
  templateUrl: './view-unit-master.component.html',
  styleUrls: ['./view-unit-master.component.css']
})
export class ViewUnitMasterComponent implements OnInit {

  public status: any = {
    isFirstOpen: true,
    isFirstDisabled: false
  };
  constructor() { }

  ngOnInit() {
  }

}
