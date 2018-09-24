import { Component, OnInit, ViewEncapsulation} from '@angular/core';
import { AccordionModule } from 'ngx-bootstrap';
@Component({
  selector: 'app-view-warehouse',
  templateUrl: './view-warehouse.component.html',
  styleUrls: ['./view-warehouse.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class ViewWarehouseComponent implements OnInit {

   public status: any = {
    isFirstOpen: true,
    isFirstDisabled: false
  };
  constructor() { }

  ngOnInit() {
  }

}
