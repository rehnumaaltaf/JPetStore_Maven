import { Component, Input , OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Subscription } from 'rxjs/Subscription';
import { ProductMasterInterface } from '../product-master-interface';
import {ProductMasterService}  from '../product-master/../service/product-master.service';
import { Router , ActivatedRoute} from '@angular/router';
@Component({
    moduleId: module.id,
    selector: 'app-base-product-mapping',
    templateUrl: 'add-multiple-processing.component.html',
})
export class AddMultiBaseProductComponent implements OnInit {
     public indexPosition;
     public data0;
     public data1;
     public data2;
     public data3;
 public pageName;
 
 arbagency:any;
     subscription: Subscription;
 whentoAdd:boolean;
 whentoEdit:boolean;
 shortName:string;
 userRolemodulefilter=[];
 fullName:string;
@Input('group')
public baseProductMappingform: FormGroup;
@Input() title: Number;
@Input() financialCalendarModelArbb: ProductMasterInterface[];
 constructor(public productMasterService: ProductMasterService, private route: ActivatedRoute) {
   this.subscription = this.productMasterService.getdestinationFromJson().subscribe(destination => {
      
      this.arbagency=destination.body.arbitrationAgencyValues;
    },
      error => { throw error },
      () => console.log('Finished')
    );
    this.route.queryParams.subscribe(params => {
        this.pageName = +params['elem'];
      });
     // alert(this.pageName);
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
     console.log(JSON.stringify(this.financialCalendarModelArbb) , this.indexPosition );
       if (this.financialCalendarModelArbb != null) {
       if (this.financialCalendarModelArbb[this.indexPosition] != undefined) {
        this.data0 = this.financialCalendarModelArbb[this.indexPosition].mappingId;
        this.shortName = this.financialCalendarModelArbb[this.indexPosition].arbitrationAgencyId;
        this.fullName = this.financialCalendarModelArbb[this.indexPosition].arbitrationFullName;
       }
       }
    }
    /* onChange(event:Event){
       this.userRolemodulefilter = this.arbagency.filter(
          book => book.value === this.baseProductMappingform.value.arbitrationAgencyId);
        alert('filter' + JSON.stringify(this.userRolemodulefilter));
     
     }*/
}
