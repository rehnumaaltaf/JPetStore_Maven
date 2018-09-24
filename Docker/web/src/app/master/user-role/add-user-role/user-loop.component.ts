import { Component, Input , OnChanges } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Subscription } from 'rxjs/Subscription';
import { Router ,ActivatedRoute} from '@angular/router';
import {UserRoleService} from './../service/user-role.service';
import { DropDown} from '../dropdownlist'; 
import {ModuleDropDown} from '../moduleDropdown'; 
import {FeatureDropDown} from '../featureDropdownlist'; 
import { UserRoleData } from '../user-role-data';
@Component({
    moduleId: module.id,
    selector: 'app-user-mapping',
    templateUrl: 'user-loop.component.html',
})
export class UserLoopComponent implements OnChanges {
@Input('group')
 public userMappingform: FormGroup;
 
  @Input() title: Number;
  @Input() financialCalendarModel: UserRoleData [];
 public ToShowFeatureDrop;
moduleName :string;
userRolemodulefilter: DropDown[];
modalDataList: ModuleDropDown = new ModuleDropDown();
featureDataList: FeatureDropDown = new FeatureDropDown();
featureList: DropDown[];
moduleDropDown:DropDown[];
 public pageName;
 
objModeldrop: DropDown = new DropDown();
  public indexPosition;
 subscription: Subscription;
 whentoAdd:boolean;
 whentoEdit:boolean;
 constructor(public userRoleService: UserRoleService ,private route: ActivatedRoute) {
   //  alert("loop")
     //this.getPickListItems();  
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
     ngOnChanges(){
       this.indexPosition = this.title;
       if(this.financialCalendarModel != null) {
       if(this.financialCalendarModel[this.indexPosition] != undefined) {
       this.modalDataList = this.financialCalendarModel[this.indexPosition].module;
       this.featureDataList = this.financialCalendarModel[this.indexPosition].feature;
       console.log('this component is called'  + this.financialCalendarModel[this.indexPosition].module.id);
       this.defaultDAta();
       }
       }
    }
    getPickListItems() {
  // To get Module pick list value.
     this.subscription = this.userRoleService.getFeaturePickListValue().subscribe(userRole => {
      this.moduleDropDown = userRole.body.moduleValues;
          alert('d' +JSON.stringify(this.moduleDropDown))
          },
            error => {
      });
    }
     onChange(event:Event){
      /* alert(JSON.stringify(this.modalDataList));
      this.objModeldrop.value = this.userMappingform.value.module;
       console.log(this.objModeldrop);
       this.userRolemodulefilter = this.userRoleService.userRole12.filter(
          book => book.value === this.objModeldrop.value);
        alert('filter' + JSON.stringify(this.userRolemodulefilter));*/
        this.subscription = this.userRoleService.getSelectedFeatureBasedOnMod(this.userMappingform.value.module).subscribe(userRole => {
        this.featureList = userRole.body.featureValues;
        this.ToShowFeatureDrop = "true";
    },
      error => alert(error),
      () => console.log('Finished')
    );
     }
      onChangeduringAdd(event:Event){
      // alert(this.userMappingform.value.module.id);
      if (this.userMappingform.value.module != null) {
        this.subscription = this.userRoleService.getSelectedFeatureBasedOnMod(this.userMappingform.value.module.id).subscribe(userRole => {
        this.featureList  = userRole.body.featureValues;
        this.ToShowFeatureDrop = "true";
    },
      error => alert(error),
      () => console.log('Finished')
    );
      } else {
        alert('Please select a proper Module.');
      }
     }
       defaultDAta(){
       //alert(this.userMappingform.value.module.id);
        this.subscription = this.userRoleService.getSelectedFeatureBasedOnMod(this.financialCalendarModel[this.indexPosition].module.id).subscribe(userRole => {
        this.featureList = userRole.body.featureValues;
        this.ToShowFeatureDrop = "true";
    },
      error => alert(error),
      () => console.log('Finished')
    );
     }
}