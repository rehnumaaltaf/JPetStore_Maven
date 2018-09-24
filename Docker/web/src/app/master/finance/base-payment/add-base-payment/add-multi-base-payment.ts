import { Component, Input , OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Subscription } from 'rxjs/Subscription';
import { BaseNagotiationTerm } from '../base-payment-interface';
import { BasePaymentService} from '../../base-payment/service/base-payment.service';
import { Router , ActivatedRoute} from '@angular/router';
@Component({
    moduleId: module.id,
    selector: 'app-base-payment-mapping',
    templateUrl: 'add-multi-base-payment.component.html',
})
export class AddMultiBasePaymentComponent implements OnInit {
     public indexPosition;
     public data0;
     public data1;
     public data2;
     public data3;
     editgradeId:number;
     
 public pageName;
 whentoAdd:boolean;
 whentoEdit:boolean;
@Input('group')
public basePaymentMappingform: FormGroup;
@Input() title: Number;
@Input() financialCalendarModel: BaseNagotiationTerm[];
 constructor(public basePaymentService: BasePaymentService, private route: ActivatedRoute) {
    this.editgradeId = this.route.snapshot.params['id'];
      if(this.editgradeId != null)  {
        this.whentoEdit = true;
        this.whentoAdd = false;
      }    else {
        this.whentoAdd = true;
        this.whentoEdit = false;
      }
     }
   ngOnInit() {
     this.indexPosition = this.title;
       //alert(this.indexPosition);
         // alert(JSON.stringify(this.financialCalendarModel));
       if (this.financialCalendarModel != null) {
       if (this.financialCalendarModel[this.indexPosition] != undefined) {
       this.data0 = this.financialCalendarModel[this.indexPosition].nagotiationTermId;
       this.data1 = this.financialCalendarModel[this.indexPosition].nagotiationCode;
       this.data2 = this.financialCalendarModel[this.indexPosition].nagotiationName;
       this.data3 = this.financialCalendarModel[this.indexPosition].printDescription;
       }
       }
    }
}
