import { Component , ViewChild, ContentChild,  OnInit, OnDestroy, Output } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';
import { UserRoleService } from '../service/user-role.service';
import { UserRoleInterface } from '../user-role.interface';
import { UserRoleData } from '../user-role-data';
import { Features } from '../user-role-data';
import { FormGroup, FormControl , FormArray,   FormBuilder, ReactiveFormsModule , FormsModule} from '@angular/forms';
import { Router , ActivatedRoute} from '@angular/router';
import { Module1 } from './../moduleunit';
import { Module2 } from './../moduleunit';
import { listuserrole } from '../../../shared/interface/router-links';
import { DropDown } from '../dropdownlist';
import { Module } from './../module';
import {UserLoopComponent} from './user-loop.component';
import { subModule } from './../module';
import { Suggestion } from '../suggestion';
import {FeatureDropDown} from '../featureDropdownlist';
import { ModalDirective } from 'ngx-bootstrap/modal';
@Component({
  selector: 'app-user-role',
  templateUrl: './user-role.component.html',
  styleUrls: ['./user-role.component.css']
})
export class UserRoleComponent implements OnInit, OnDestroy {
   @ViewChild('duplicate') public duplicate: ModalDirective;
  @ViewChild('filebrowse') public filebrowse: ModalDirective;
  @ViewChild('deletesuccessModal') public deletesuccessModal: ModalDirective;
  @ContentChild('temp') testEl: any;
public module: Module[];
userRoleDetails: UserRoleInterface[];
moduleDropDown: DropDown[];
featureList: Features[];
 public ToShowFeatureDrop;
userRoleDataList: UserRoleData = new UserRoleData();
userRoleDataValueToSave: UserRoleData = new UserRoleData();
showHide: boolean;
public featureValList = [];
errorMessage: string;
errorMessageForUser: string;
req_roleName: boolean;
public isShowFeature: Boolean;
req_feature: boolean;
reqmsg3: string;
reqmsg2: string;
reqmsg1: string;
ErrorModal = false;
req_roleDesc: boolean;
financialCalendarModel: UserRoleData [];
public whenToShowMore;
public isShowModal;
whenDataEdited: boolean;
whenStatusactive: boolean;
whenDataNotTobeEdited: boolean;
whenDataTobeEdited: boolean;
whenStatusInactive: boolean;
roleForm: FormGroup;
 public isupdateModal;
 isUpdate: boolean;
 isActivated: boolean;
isInActivated: boolean;
errormodal: boolean;
counter: number;
public isError;
subscription: Subscription;
formarraylength: number;
AddEditFinancialId: Number;
pageTitle: String;
public pageName;
public status: any =  {
isFirstOpen : true
// isFirstDisabled: false
}; 
constructor(private router: Router, private _routeParams: ActivatedRoute, private fb:  FormBuilder, private route: ActivatedRoute, public userRoleService: UserRoleService) {
    this.showHide = true;
    this.getPickListItems();
    this.whenDataNotTobeEdited = true;
    this.whenDataTobeEdited = false;
    this.module = this.userRoleService.getModules();
}

changeShowStatus() {
    this.showHide = !this.showHide;
}
ngOnInit(): any {
  window.scrollTo(0, 0);
  this.errormodal = false;
   this.AddEditFinancialId = +this.route.snapshot.params['id'];
   this.route.queryParams.subscribe(params => {
        // Defaults to 0 if no query param provided.
        this.pageName = +params['elem'];
        
      });
    this.pageTitle = 'Add User Role';
    if (this.pageName == 6) {
      this.pageTitle = 'Edit User Role';
      this.roleForm = this.fb.group({
      roleName: '' ,
      description: '',
      features: this.fb.array([]),
      modules: []
    });
      this.userRoleDataList = this.userRoleService.editFeatures;
      this.editListDetails();
     } else {
       this.buildUSERROLEForm();
       this.addPermissionMapping();
     }
    //this.formarraylength = 0;
       /* this.roleForm = this.fb.group({
                      roleName: '' ,
                      description: '',
                      features: this.fb.array([]),
                      modules: []
          });
        this.addPermissionMapping()*/
}
buildUSERROLEForm(): void {
   this.roleForm = this.fb.group({
      roleName: '' ,
      description: '',
      features: this.fb.array([]),
      modules: []
    });
  }
  getUserRoleArray(roleForm) {
      return roleForm.get('features').controls;
  }

ngOnDestroy() {
    // prevent memory leak when component destroyed
    this.subscription.unsubscribe();
}
editListDetails() {
  //alert(this.financialCalendarModel.roleName);
 // this.roleForm.controls['roleName'].setValue(this.financialCalendarModel.roleName);
 // this.roleForm.controls['description'].setValue(this.financialCalendarModel.roleDesc);
  if (this.userRoleDataList.roleStatusName === 'Draft') {
        this.whenDataNotTobeEdited = false;
        this.whenDataEdited = true;
        this.whenStatusInactive = false;
        this.whenStatusactive = false;
      }else if (this.userRoleDataList.roleStatusName === 'Active') {
        this.whenStatusactive = true;
        this.whenStatusInactive = false;
        this.whenDataEdited = false;
        this.whenDataNotTobeEdited = false;
      }else if (this.userRoleDataList.roleStatusName === 'InActive') {
        this.whenStatusInactive = true;
        this.whenDataEdited = false;
        this.whenStatusactive = false;
        this.whenDataNotTobeEdited = false;
      }
 // this.fillYears(this.financialCalendarModel.fiscalYear);
  console.log(this.userRoleService.editSelectedFeatures.length + '  check of length');
  this.financialCalendarModel = this.userRoleService.editSelectedFeatures;
  const sizerollemapping = Number( JSON.stringify(this.userRoleService.editSelectedFeatures.length));
 if (sizerollemapping >= 1 ) {
     for (let i = 0; i <  sizerollemapping ; i++) {
      // this.financialCalendarModel.tenorFinancialArray[i].monthName = '';
     //  this.financialcalForm.value.tenorFinancialArray = this.financialCalendarModel.tenorFinancialArray;
      // console.log(this.financialcalForm.value.tenorFinancialArray[0].startDate);
       this.addPermissionMapping();
       // this.financialcalForm.controls['tenorFinancialArray'].setValue(this.financialCalendarModel.tenorFinancialArray);
       // this.financialcalForm.value.tenorFinancialArray[i].monthName=
     }
 }
}
getPickListItems() {
  // To get Module pick list value.
     this.subscription = this.userRoleService.getFeaturePickListValue().subscribe(userRole => {
      this.userRoleService.userRole12 = userRole.body.moduleValues;
      this.moduleDropDown = userRole.body.moduleValues;
},
  error => {
      });
}
save() {
  // Before saving(draft mode) to database form validation.
  if (this.isValidForm()) {
    // if validation passed store data in database.
  this.userRoleDataValueToSave.roleId = '';
  this.userRoleDataValueToSave.roleName  = this.roleForm.value.roleName.trim();
  this.userRoleDataValueToSave.roleDesc  = this.roleForm.value.description.trim();
  this.userRoleDataValueToSave.features =  this.roleForm.value.features;
  this.subscription = this.userRoleService.saveRoleDetails(this.userRoleDataValueToSave).subscribe(userRole => {
      this.userRoleService.userRole12.push(userRole);
      this.isShowModal = 'true';
      this.router.navigate([listuserrole], { queryParams: { 'success': 1 } });
      
    },
      error => {
      this.errorMessageForUser = this.userRoleService.errorMessage;
      this.isError = 'true';
      });
  }
  }
  saveAndSubmitRoleDetails() {
  // Before saving(Active mode) to database form validation.
  if (this.isValidForm()) {
  // if validation passed store data in database.
  this.userRoleDataValueToSave.roleId = '';
  this.userRoleDataValueToSave.roleName  = this.roleForm.value.roleName.trim();
  this.userRoleDataValueToSave.roleDesc  = this.roleForm.value.description.trim();
  this.userRoleDataValueToSave.features =  this.roleForm.value.features;
  this.subscription = this.userRoleService.saveAndSubmitRoleDetails(this.userRoleDataValueToSave).subscribe(userRole => {
      this.userRoleService.userRole12.push(userRole);
      this.router.navigate([listuserrole], { queryParams: { 'success': 1 } });
    },
      error => {
      this.errorMessageForUser = this.userRoleService.errorMessage;
      this.isError = 'true';
      });
  }
  }
   isValidForm(): boolean {
     const sizerollemapping = Number( JSON.stringify(this.roleForm.value.features.length));
   // alert(JSON.stringify(this.roleForm.value.features));
   this.counter = 0;
   this.reqmsg1 = '';
   this.reqmsg2 = '';
   this.reqmsg3 = '';
   // alert(JSON.stringify(this.roleForm.value.features));
   // tslint:disable-next-line:max-line-length
   if ((this.userRoleDataList.roleName === '') || (this.userRoleDataList.roleName == null) || (this.userRoleDataList.roleName === undefined)) {
                this.req_roleName = true;
                this.counter++;
               // this.reqmsg1 = 'Enter Role Name';
               // this.errormodal=true;
  } else if (this.userRoleDataList.roleName.trim().length > 20) {
                this.req_roleName = true;
                this.counter++;
               // this.reqmsg1 = 'Role Name should be less than 50';
                // this.errormodal =  true;
               // this.errorMessageForUser = 'Role Name Is Mandatory';
               // this.isError = 'true';

    // tslint:disable-next-line:max-line-length
  } else {
    this.req_roleName = false;
  }
  if (this.userRoleDataList.roleDesc === '' || this.userRoleDataList.roleDesc == null || this.userRoleDataList.roleDesc === undefined) {
                this.req_roleDesc = true;
                this.counter++;
               // this.reqmsg2 = 'Enter Role Description';
               // this.errormodal=true;
               // this.errorMessageForUser = 'Role Name Is Mandatory';
               // this.isError = 'true';

           }else if ((this.userRoleDataList.roleDesc.trim().length > 250)) {

                this.req_roleDesc = true;
                this.counter++;
               // this.reqmsg2 = 'Role Description should be less than 2000';
               // this.errormodal=true;
               // this.errorMessageForUser = 'Role Name Is Mandatory';
               // this.isError = 'true';

           }else {
             this.req_roleDesc = false;
           } if ((this.roleForm.value.features.feature == null && this.roleForm.value.features[0].feature == null)) {
               // alert('Feature is Mandatory , Please Select a feature.');
               // this.errorMessageForUser = 'Feature is Mandatory , Please Select a feature.';
                // this.isError = 'true';
                this.req_feature = true;
            } 
            
            if ( sizerollemapping >= 1 ) {

            
             
           
            for (let i = 0; i <  sizerollemapping ; i++) {
                 for (let j = i + 1 ; j <  sizerollemapping ; j++) {
                    console.log('// check duplicate role mapping' );
                    if (this.roleForm.value.features[i].feature ===
                            this.roleForm.value.features[j].feature &&
                             this.roleForm.value.features[i].module ===
                             this.roleForm.value.features[j].module
                        ) {
                            console.log('// duplicate occured in role mapping')
                            this.reqmsg3 = ' Role Mapping must be Unique ' ;
                            alert(this.reqmsg3);
                            this.isError = true;
                            this.counter++;
                    }
              }
            }
       }
            
            
            
            
            if (this.counter !== 0) {




                this.showModal();
                setTimeout(() => {this.hideModal(); }, 3000);
                return false;
       } else {
               return true;
              }
  }
public showModal(): void {
    this.errormodal = true;
  }

  public hideModal(): void {
    this.deletesuccessModal.hide();
  }

  public onHidden(): void {
    this.errormodal = false;
  }


  clear() {
   this.ngOnInit();
  }
   addPermissionMapping() {
        const control  = <FormArray>this.roleForm.controls['features'];
        const addrCtrl = this.initMapping();
        control.push(addrCtrl);
    }
    removePermissionMapping(i: number) {
            const control = <FormArray>this.roleForm.controls['features'];
            if (this.pageName == 6) {
            this.featureValList.push(this.financialCalendarModel[i].feature);
             this.financialCalendarModel.splice(i,1);
            control.removeAt(i);
            if (i==0) {
              this.addPermissionMapping();
            }
            } else {
               control.removeAt(i);
                if (i==0) {
                 this.addPermissionMapping();
            }
            }
    }
    
    initMapping() {
      console.log('inside init');
        return this.fb.group({
          feature: [],
          module: [],
          mappingId:[]
      });
    }
    getStyle() {
    if (this.req_roleName === true) {
      return '#d43c3c';
    } else {
      return '#cfdee7';
    }
  }
  getStyle1() {
    if (this.req_roleDesc === true) {
      return '#d43c3c';
    } else {
      return '#cfdee7';
    }
  }
click_more (index) {
  console.log(JSON.stringify(this.roleForm.value.features[index]));
       if (this.roleForm.value.features[index].feature != null) {
         if(this.roleForm.value.features[index].feature.id != null) {
         this.getPopUpPageData(this.roleForm.value.features[index].feature.id);
        } else {
           this.getPopUpPageData(this.roleForm.value.features[index].feature);
        }
       } else {
              this.errorMessageForUser = 'Please Select a Feature First' ;
              this.isError = 'true';
        }
}
 getPopUpPageData(id: any) {
     this.subscription = this.userRoleService.getPopUpValues(id).subscribe(userRole => {
     this.userRoleService.popUpList = userRole.body;
     this.isShowFeature = true;
    },
      error => alert(error),
      () => console.log('Finished')
    );

}
closePop() {
     this.isShowFeature = false;
}
clickToClosePop() {
  this.isError = false;
}

reset () {
  this.router.navigate(['/master/viewuserrole'])
}
updateUserRole(statusName) {
    if (this.isValidForm()) {
     if (this.userRoleDataList.roleStatusName === 'Draft') {
        this.userRoleDataValueToSave.statusName  = 'Active';
        if (statusName === 'Active'){
            this.isActivated = true;
        }
     }else if (this.userRoleDataList.roleStatusName === 'Active') {
         this.userRoleDataValueToSave.statusName  = 'InActive';
          if (statusName === 'InActive') {
            this.isInActivated = true;
        }
     }else if (this.userRoleDataList.roleStatusName = 'InActive') {
       //alert('12');
        this.userRoleDataValueToSave.statusName = 'Active';
        if (statusName === 'Active') {
            this.isActivated = true;
        }
     }
     
  this.userRoleDataValueToSave.roleId = '';
  this.userRoleDataValueToSave.roleName  = this.roleForm.value.roleName.trim();
  this.userRoleDataValueToSave.roleDesc  = this.roleForm.value.description.trim();
  this.userRoleDataValueToSave.featureMapping =  this.roleForm.value.features;
  this.userRoleDataValueToSave.mappingIdtobedelted = this.featureValList;
  console.log('Requestbody' + JSON.stringify(this.userRoleDataValueToSave))
     this.subscription = this.userRoleService.updateRole(this.userRoleDataValueToSave).subscribe(savedwithStatus => {
    this.isupdateModal = true;
    this.router.navigate([listuserrole], { queryParams: { 'success': 9 } });
      },
      error => alert(error),
      () => console.log('Finished')
    );

    }
  }
  update() {
    if (this.isValidForm()) {
  this.userRoleDataValueToSave.statusName  = 'none';
  this.userRoleDataValueToSave.roleId = '';
  this.userRoleDataValueToSave.roleName  = this.roleForm.value.roleName.trim();
  this.userRoleDataValueToSave.roleDesc  = this.roleForm.value.description.trim();
  this.userRoleDataValueToSave.featureMapping =  this.roleForm.value.features;
  this.userRoleDataValueToSave.mappingIdtobedelted = this.featureValList;
  console.log('only update' + JSON.stringify(this.userRoleDataValueToSave))
     this.subscription = this.userRoleService.updateRole(this.userRoleDataValueToSave).subscribe(savedwithStatus => {
    this.isupdateModal = true;
    this.router.navigate([listuserrole], { queryParams: { 'success': 9 } });
      },
      error => alert(error),
      () => console.log('Finished')
    );

    }
  }
  saveEdit() {
  if (this.isValidForm()) {
  this.userRoleDataValueToSave.statusName  = 'none';
  this.userRoleDataValueToSave.roleId = '';
  this.userRoleDataValueToSave.roleName  = this.roleForm.value.roleName.trim();
  this.userRoleDataValueToSave.roleDesc  = this.roleForm.value.description.trim();
  this.userRoleDataValueToSave.featureMapping =  this.roleForm.value.features;
  this.userRoleDataValueToSave.mappingIdtobedelted = this.featureValList;
   console.log('save' + JSON.stringify(this.userRoleDataValueToSave))
  this.subscription = this.userRoleService.updateRole(this.userRoleDataValueToSave).subscribe(userRole => {
      this.userRoleService.userRole12.push(userRole);
      this.router.navigate([listuserrole], { queryParams: { 'success': 9 } });
    },
      error => {
      this.errorMessageForUser = this.userRoleService.errorMessage;
      this.isError = 'true';
      });
  }
  }
  saveAndSubmitEdit() {
  if (this.isValidForm()) {
  this.userRoleDataValueToSave.statusName  = 'Active';
  this.userRoleDataValueToSave.roleId = '';
  this.userRoleDataValueToSave.roleName  = this.roleForm.value.roleName.trim();
  this.userRoleDataValueToSave.roleDesc  = this.roleForm.value.description.trim();
  this.userRoleDataValueToSave.featureMapping =  this.roleForm.value.features;
  this.userRoleDataValueToSave.mappingIdtobedelted = this.featureValList;
  console.log('submit'+JSON.stringify(this.userRoleDataValueToSave))
  this.subscription = this.userRoleService.updateRole(this.userRoleDataValueToSave).subscribe(userRole => {
      this.userRoleService.userRole12.push(userRole);
      this.router.navigate([listuserrole], { queryParams: { 'success': 9 } });
    },
      error => {
      this.errorMessageForUser = this.userRoleService.errorMessage;
      this.isError = 'true';
      });
  }
  }
}
