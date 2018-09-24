import { Component, Inject, ViewChild, OnInit, OnDestroy, Output, Input , OnChanges } from '@angular/core';
import { AccordionModule, ModalModule  } from 'ngx-bootstrap';
import { SharedModule } from '../../../shared/shared.module';
import { ModalDirective } from 'ngx-bootstrap/modal';
import {PermissionGroupService} from './../service/permission-group.service';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';
import { FormGroup ,  FormControl,  Validators, FormArray,   FormBuilder, ReactiveFormsModule , FormsModule} from '@angular/forms';
import { Subscription } from 'rxjs/Subscription';
import {Unit } from './../unit';
import { PermissionGroup } from '../permission-group-interface';
import { PermissionGroupRoleMapping } from '../permission-group-interface';
import { Router , ActivatedRoute} from '@angular/router';
import { viewpermissiongrp, permissiongroup } from '../../../shared/interface/router-links';

@Component({
  selector: 'app-add-permission',
  templateUrl: './add-permission.component.html',
  styleUrls: ['./add-permission.component.css']
})
export class AddPermissionComponent implements OnInit,  OnDestroy   {
@ViewChild('deletesuccessModal') public deletesuccessModal: ModalDirective;
units: Unit[];
public pagetitle: string;
public reqmsg: string;
permissionGroupDetails: PermissionGroup[];
req_uomname: boolean;
permissionGroup: PermissionGroup= new PermissionGroup();
addpage: boolean;
editpage: boolean;
permissionGroupRoleMapping123: PermissionGroupRoleMapping[];
addoreditpage: boolean;
permissionGroupData: PermissionGroup= new PermissionGroup();
sumbitstatus: string;
public errormodal: boolean;
permissionGroupForm: FormGroup;

req_permisiongrpname: boolean;
req_permisiongrpowner: boolean;
req_permisionrole: boolean;
req_permisionrparty: boolean;
req_permisionunit: boolean;
req_permisionproduct: boolean;
req_permisionportfolio: boolean;
reqmsgowner: string;
reqmsgname: string;
isError: boolean;
sizerollemapping: any;
errorMessageForPermission: String;
checkdropdown: number;
editPermissionId: Number;
subscription: Subscription;
public roleFeatures;
public status: any = {
    isFirstOpen: true
   // isFirstDisabled: false
  };
  public draft;
  public active;
  public inactive;
  //requserrolemapping: string;
  reqrole: string;
  reqparty: string;
  reqproduct: string;
  requnit: string;
  reqportfolio: string;

@Output() loadingData: Boolean = true;
  constructor(private permissionGroupService: PermissionGroupService ,  private fb:  FormBuilder, private router: Router,
        private route: ActivatedRoute) {
     }

 ngOnInit(): any {
    window.scrollTo(0, 0); 
     this.req_permisiongrpname = false;
    this.req_permisiongrpowner = false;
     this.isError = false;
     this.getDropDown();
     this.editPermissionId = this.route.snapshot.params['id'];
     if (this.editPermissionId ) {
           this.initPermissionForm();
           this.pagetitle = 'Edit Permission Group';
           this.permissionGroupService.getPermissionGroupById(this.editPermissionId).
           subscribe(permissionGroupDetails => {
               this.editpage = true;
              this.addpage = false;
              this.permissionGroup = permissionGroupDetails.body;
              console.log('Edit page');
              this.editPermission();
                if (this.permissionGroup.statusName === 'Draft') {
                    this.draft = true;
                    this.active = false;
                    this.inactive = false;
                } else if (this.permissionGroup.statusName === 'Active' ) {
                    this.active = true;
                    this.draft = false;
                    this.inactive = false;
                } else if (this.permissionGroup.statusName === 'InActive') {
                    this.inactive = true;
                    this.active = false;
                    this.draft = false;
                }
       },
         error => { console.log(' Server Error');//throw error;
               }

    );
     }else {
        this.pagetitle = 'Add Permission Permission';
        this.editpage = false;
        this.addpage = true;
        this.initPermissionForm();
        this.addPermissionMapping();
     }
      }
    ngOnDestroy() {
//this.subscription.unsubscribe();
    }
    getPermissionList(permissiongGroupForm) {
    return permissiongGroupForm.get('permissionGroupRoleMapping').controls;
  }
   initPermissionForm(): void {
        this.permissionGroupForm = this.fb.group({
            'permissionGroupId': [] ,
            'permissionGroupName': [''],
            'permissionGroupDesc': [''],
            'permissionOwner': [''],
            'statusName': [this.permissionGroup.statusName],
            'updateStatusName': ['0'],
            'permissionGroupRoleMapping': this.fb.array([]),
            'deletedmappid': this.fb.array([
               ])
         });
   }
    getDropDown(): void {
       this.permissionGroupService.getPermissionDropDownJSON().subscribe(permissionGroupDetails => {
      this.permissionGroupService.roles =  permissionGroupDetails.body.roles;
      this.permissionGroupService.companies = permissionGroupDetails.body.parties;
      this.permissionGroupService.portfolios = permissionGroupDetails.body.portfolios;
      this.permissionGroupService.products = permissionGroupDetails.body.products;
      this.permissionGroupService.units = permissionGroupDetails.body.units;
      },
    error => {  console.log(' Server error');
        //throw error;
               }
      );
   }
    editPermission() {
        const sizerollemapping = Number( JSON.stringify(this.permissionGroup.permissionGroupRoleMapping.length));
        if (sizerollemapping >= 1 ) {
            for (let i = 0; i <  sizerollemapping ; i++) {
                    this.addPermissionMapping()
            }
        }

    }
    addPermission() {
        if (this.isValidForm()) {
                this.permissionGroupService.savePermissionGroup(this.permissionGroupForm.value,
                this.sumbitstatus, this.addoreditpage).subscribe(permissionGroupDetails => {
                this.router.navigate([viewpermissiongrp], { queryParams: { 'success': 1 } });
                if ( this.permissionGroupForm.value.statusName === 'Draft' ) {
                   this.router.navigate([viewpermissiongrp], { queryParams: { 'success': 1 } });
              } else  if ( this.permissionGroupForm.value.statusName === 'Active' ) {
                   this.router.navigate([viewpermissiongrp], { queryParams: { 'success': 2 } });
              }else  if ( this.permissionGroupForm.value.statusName === 'InActive' ) {
                   this.router.navigate([viewpermissiongrp], { queryParams: { 'success': 3 } });
              }



            },
            error => {
                    this.errorMessageForPermission = this.permissionGroupService.errorMessage;
                    this.isError = true;
                   // throw error;
            },
            () => console.log('Finished')
            )
        }
    }

    addPermissionMapping() {

        const control = <FormArray>this.permissionGroupForm.controls['permissionGroupRoleMapping'];
        const addrCtrl =   this.fb.group({
            'mappingId': [],
            'roleId': [],
            'partyId': [],
            'productId': [],
            'unitId': [],
            'portfolioId': []});
        control.push(addrCtrl);
    }

    removePermissionMapping(i: number) {
        const control = <FormArray>this.permissionGroupForm.controls['permissionGroupRoleMapping'];
       
         if (this.permissionGroupForm.value.permissionGroupRoleMapping != null ) {
              if ( this.permissionGroupForm.value.permissionGroupRoleMapping[i].mappingId != null  &&
                    this.permissionGroupForm.value.permissionGroupRoleMapping[i].mappingId !== undefined ) {
                 this.permissionGroupForm.value.deletedmappid.push(this.permissionGroupForm.value.permissionGroupRoleMapping[i].mappingId);
                 this.permissionGroupForm.value.permissionGroupRoleMapping.splice(i, 1);
            }
         }
             control.removeAt(i);
        if (control.length === 0 ) {
             this.addPermissionMapping();
        }
    }

    isValidForm(): boolean {

         this.reqmsg = ''; this.checkdropdown = 0;
         const sizerollemapping = Number( JSON.stringify(this.permissionGroupForm.value.permissionGroupRoleMapping.length));
        if (this.permissionGroup.permissionGroupName  == null ) {
                    this.req_permisiongrpname = true;
                    this.checkdropdown++;
        }else if ( this.permissionGroup.permissionGroupName  != null  ) {
            if ( this.permissionGroup.permissionOwner === '' ) {
                this.req_permisiongrpname = true;
                this.checkdropdown++;
            } else if (this.permissionGroupForm.value.permissionGroupName.length > 50) {
                this.req_permisiongrpname = true;
                this.checkdropdown++;
            } else {
                this.req_permisiongrpname = false;
            }
        }

        if ( this.permissionGroup.permissionOwner  == null ) {
             this.req_permisiongrpowner = true;
            this.checkdropdown++;
        } else if ( this.permissionGroup.permissionOwner  != null) {
            if (this.permissionGroup.permissionOwner === '') {
                this.req_permisiongrpowner = true;
                this.checkdropdown++;
            } else if (this.permissionGroupForm.value.permissionOwner.length > 200) {
                this.req_permisiongrpowner = true;
                this.checkdropdown++;
            }

        }
        if ( this.permissionGroupForm.value.permissionGroupRoleMapping != null) {
            this.permissionGroupService.setRoleCode(this.permissionGroupForm.value.permissionGroupRoleMapping);

        }
        if ( sizerollemapping >= 1 ) {

            for (let i = 0; i <  sizerollemapping ; i++) {
                if (this.permissionGroupForm.value.permissionGroupRoleMapping[i] === null ) {

                       // this.requserrolemapping += 'Required';
                    this.checkdropdown++;
                }else if (this.permissionGroupForm.value.permissionGroupRoleMapping[i] !== null ) {
                    if ( this.permissionGroupForm.value.permissionGroupRoleMapping[i].roleId == null ) {
                        this.checkdropdown++;
                    }
                    if ( this.permissionGroupForm.value.permissionGroupRoleMapping[i].partyId == null) {
                       this.checkdropdown++;
                    }
                    if ( this.permissionGroupForm.value.permissionGroupRoleMapping[i].productId == null) {
                         this.checkdropdown++;
                    }
                    if ( this.permissionGroupForm.value.permissionGroupRoleMapping[i].unitId == null) {
                         this.checkdropdown++;
                    }
                    if ( this.permissionGroupForm.value.permissionGroupRoleMapping[i].portfolioId == null) {
                         this.checkdropdown++;
                    }

                }
            }
             this.permissionGroupData = this.permissionGroupForm.value;
            for (let i = 0; i <  sizerollemapping ; i++) {
                 for (let j = i + 1 ; j <  sizerollemapping ; j++) {
                    console.log('// check duplicate role mapping' );
                    if (this.permissionGroupData.permissionGroupRoleMapping[i].roleId ===
                            this.permissionGroupData.permissionGroupRoleMapping[j].roleId  &&
                             this.permissionGroupData.permissionGroupRoleMapping[i].productId ===
                             this.permissionGroupData.permissionGroupRoleMapping[j].productId &&
                             this.permissionGroupData.permissionGroupRoleMapping[i].unitId ===
                             this.permissionGroupData.permissionGroupRoleMapping[j].unitId &&
                              this.permissionGroupData.permissionGroupRoleMapping[i].portfolioId ===
                       this.permissionGroupData.permissionGroupRoleMapping[j].portfolioId &&
                       this.permissionGroupData.permissionGroupRoleMapping[i].partyId ===
                       this.permissionGroupData.permissionGroupRoleMapping[j].partyId
                        ) {
                            console.log('// duplicate occured in role mapping')
                            this.errorMessageForPermission = ' Role Mapping must be Unique ' ;
                            this.isError = true;
                            this.checkdropdown++;
                    }
              }
            }
       }
        if (this.checkdropdown !== 0) {
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

        getStyle() {
        if (this.req_permisiongrpname === true) {
           return '#d43c3c';
        } else {
        return '#cfdee7';
            }
    }
    getStyleowner() {
         if (this.req_permisiongrpowner === true) {
           return '#d43c3c';
        } else {
        return '#cfdee7';
            }

    }
    savedata() {

        if (this.addpage ) {
            this.addoreditpage = true;
        } else if (this.editpage) {
            this.addoreditpage = false;
        }
        this.sumbitstatus = 'draft';
        this.permissionGroupForm.value.updateStatusName = 'Draft';
        this.addPermission() ;

    }
    saveandsubmit() {
        if (this.addpage ) {
            this.addoreditpage = true;
        } else if (this.editpage) {
            this.addoreditpage = false;
        }
         this.sumbitstatus = 'save';
          this.permissionGroupForm.value.updateStatusName = 'Active';
          this.permissionGroupForm.value.statusName = 'Active';
        this.addPermission() ;
    }
    saveactive() {
        this.addoreditpage = false;
        this.permissionGroupForm.value.updateStatusName = 'Active';
        this.permissionGroupForm.value.statusName = 'none';
        this.sumbitstatus = 'Active';
        this.addPermission() ;
    }
    submitactivetodeactive() {
        this.addoreditpage = false;
        this.permissionGroupForm.value.updateStatusName = 'InActive';
        this.permissionGroupForm.value.statusName = 'InActive';
        this.sumbitstatus = 'InActive';
        this.addPermission() ;
    }
    savedeactive() {
        this.addoreditpage = false;
        this.permissionGroupForm.value.updateStatusName = 'InActive';
        this.permissionGroupForm.value.statusName = 'none';
        this.sumbitstatus = 'InActive';
        this.addPermission() ;
    }
    submitdeactivetoreactive() {
        this.addoreditpage = false;
        this.permissionGroupForm.value.updateStatusName = 'Active';
        this.permissionGroupForm.value.statusName = 'Active';
        this.sumbitstatus = 'Active';
        this.addPermission() ;
    }

    showroleinfo(permissiongroupid ) {
        if (this.permissionGroupForm.value.
            permissionGroupRoleMapping[permissiongroupid].roleId !== undefined) {
                this.permissionGroupService.getRoleFeatures(this.permissionGroupForm.value.
            permissionGroupRoleMapping[permissiongroupid].roleId).subscribe(roleDetails => {
            this.permissionGroupService.roleFeatures = roleDetails.body
            this.roleFeatures = true;
            },
            error => {
                    this.errorMessageForPermission = this.permissionGroupService.errorMessage;
                    this.isError = true;
                    // throw error;
            },
            () => console.log('Finished')
            )
        } else {
              this.errorMessageForPermission = 'Please Select a Role Mapping ' ;
                this.isError = true;
        }
    }
close() {
    this.roleFeatures = false;
  }
closeIsError() {
    this.isError = false;
}
clear() {
    this.ngOnInit();
}
backtolist() {
    this.router.navigate([permissiongroup]);
}
showroleinfoview(permissiongroupid ) {
    // alert("==eventparam==>"+JSON.stringify(event));
     this.permissionGroupService.redirectUserRoleView(permissiongroupid);
    this.router.navigate(['master/viewuserdetails']);
  }

}