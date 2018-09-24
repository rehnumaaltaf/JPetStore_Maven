import { Component, Input , OnInit } from '@angular/core';
import {  FormsModule,
       ReactiveFormsModule,
       FormGroup,
       FormControl, FormBuilder } from '@angular/forms';
import { Subscription } from 'rxjs/Subscription';
import { TaxRateNagotiationTerm } from '../tax-rate-interface';
import { TaxRateService} from '../../tax-rate/service/tax-rate.service';
import { Router , ActivatedRoute} from '@angular/router';
import { addDays, addWeeks, addMonths } from '@progress/kendo-date-math';
@Component({
    moduleId: module.id,
    selector: 'app-tax-rate-mapping',
    templateUrl: 'multi-add-tax-rate.html',
})
export class AddMultiTaxRateComponent implements OnInit {
     public indexPosition;
     public data0;
     public data1;
     public data2;
     public date1;
     public dataArr = [];
     req_taxRate:boolean;
     req_fromDate:boolean;
     public taxregdate: Date = new Date();
     public taxexpdate: Date = new Date();
     public min: Date = new Date();
     public max: Date = new Date(2999, 11, 31);
      public pageName;
      whentoAdd:boolean;
      whentoEdit:boolean;
@Input('group')

public TaxRateMappingform: FormGroup;

@Input() title: Number;
@Input() financialCalendarModel: TaxRateNagotiationTerm[];
@Input() reqEffectiveFromDate:boolean;
@Input() reqTaxRate:boolean;
  negoTermDetails:TaxRateNagotiationTerm= new TaxRateNagotiationTerm();
 constructor(public taxRatePaymentService:TaxRateService, private route: ActivatedRoute, private formBuilder:  FormBuilder) {
 
    this.route.queryParams.subscribe(params => {
        // Defaults to 0 if no query param provided.
        this.pageName = +params['elem'];
        
      });
      if(this.pageName == 6)  {
        this.whentoEdit = true;
        this.whentoAdd = false;
      }    else {
        this.whentoAdd = true;
        this.whentoEdit = false;
      }

     
     }
    
        
   ngOnInit() {
     this.indexPosition = this.title;
       if (this.financialCalendarModel != null) {
        if (this.financialCalendarModel[this.indexPosition] != undefined) {
          if(this.pageName == 6){
              this.data0 = new Date(this.financialCalendarModel[this.indexPosition].effectiveFrom);
              if( this.financialCalendarModel[this.indexPosition].expirationDate != null)
                this.data1 = new Date(this.financialCalendarModel[this.indexPosition].expirationDate);
              this.data2 = this.financialCalendarModel[this.indexPosition].taxRatePercentage;
          }
        }
       }
      if(this.financialCalendarModel != null){
      if(this.financialCalendarModel[this.indexPosition]!=undefined){
        if(this.financialCalendarModel[this.indexPosition].effectiveFrom==null)
          {
            this.req_fromDate=true;
          }

      }
      }
    }
     
}
