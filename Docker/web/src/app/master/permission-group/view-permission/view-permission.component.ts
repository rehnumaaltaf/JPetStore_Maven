import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import {PermissionGroupService} from './../service/permission-group.service';
import { PermissionGroup } from '../permission-group-interface';
import { FormGroup ,  FormControl,  Validators, FormArray,   FormBuilder, ReactiveFormsModule , FormsModule} from '@angular/forms';
import { viewpermissiongrp, permissiongroup } from '../../../shared/interface/router-links';
@Component({
  selector: 'app-view-permission',
  templateUrl: './view-permission.component.html',
  styleUrls: ['./view-permission.component.css']
})
export class ViewPermissionComponent implements OnInit {

permissionGroupDetails: PermissionGroup[];
permissionGroupData: PermissionGroup= new PermissionGroup();
permissionGroupInterface: PermissionGroup = new PermissionGroup();
isFirstDisabled: boolean;
 public status: any = {
    isFirstOpen: true,
    isFirstDisabled: false
  };
  public permissionGroup;
  public draft;
  public active;
  public inactive;
  subscription: Subscription;
  updateData: any;
  isEdit: boolean;
  editMode: boolean;
  public del_id;
  errorMessage: any;
  permissionGroupName: any;
  isDeletePopupModal: boolean;
  isDraftStatus: boolean;
  deleteSuccessModal: boolean;
  deletebyid: boolean;
  public deletedStatus;
  ispagebackPopupModal: boolean;
  isCannotDeletePopup: boolean;
  isUpdatedSuccessPopup: boolean;
  isShowModal: boolean;
  statusMsg: any;
  status_change: boolean;
  editPermissionId: Number;
  isActiveStatus:boolean;
  public permissionGroupId: number;

  constructor(private route: ActivatedRoute, http: Http, private router: Router, private permissionGroupService: PermissionGroupService) { }

  ngOnInit() {
    window.scrollTo(0, 0); 
     this.status_change = false;
      this.isCannotDeletePopup = false;
      this.editMode = true;
       this.deletebyid = false;
      this.permissionGroup = this.permissionGroupService.permissionGroup;
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
      }

 backtoPrev() {
         this.router.navigate([permissiongroup]);
}
close() {
    this.isCannotDeletePopup = false;
}

  enableEdit() {
    this.editMode = false;
    this.isEdit = true;
}

 confirm() {
     this.status_change = true;
          if (this.permissionGroup.statusName === 'Active') {
              this.statusMsg = 'InActivate';
              this.permissionGroup.statusName =  'InActive';
              this.status = 'Active';
        } else if (this.permissionGroup.statusName === 'InActive') {
              this.statusMsg = 'Activate';
              this.permissionGroup.statusName =  'Active';
              this.status = 'InActive';
        } else if (this.permissionGroup.statusName === 'Draft') {
              this.statusMsg = 'Activate';
              this.permissionGroup.statusName =  'Active';
              this.status = 'Draft';
        }
   }

conf_status_change_draft() {
  const submitstatus = 'save';
  const addoreditpage = false; // add page is true , edit page is false
  this.permissionGroup.statusName = 'Active';
  const sizerollemapping = Number( JSON.stringify(this.permissionGroup.permissionGroupRoleMapping.length));
  for ( let _i = 0 ; _i < sizerollemapping ; _i++)  {
    this.permissionGroup.permissionGroupRoleMapping[_i].roleId = this.permissionGroup.permissionGroupRoleMapping[_i].role.id;
    this.permissionGroup.permissionGroupRoleMapping[_i].partyId = this.permissionGroup.permissionGroupRoleMapping[_i].party.id;
    this.permissionGroup.permissionGroupRoleMapping[_i].productId = this.permissionGroup.permissionGroupRoleMapping[_i].product.id;
    this.permissionGroup.permissionGroupRoleMapping[_i].unitId = this.permissionGroup.permissionGroupRoleMapping[_i].unit.id;
    this.permissionGroup.permissionGroupRoleMapping[_i].portfolioId = this.permissionGroup.permissionGroupRoleMapping[_i].portfolio.id;
   }
      this.subscription = this.permissionGroupService.savePermissionGroup(this.permissionGroup, submitstatus, addoreditpage).
        subscribe(updateData => {
              this.updateData = updateData;
              this.isShowModal = true;
              setTimeout(() => { this.isShowModal = false; }, 3000);
              this.router.navigate([permissiongroup]);
          }, error => {
              this.status_change = false;
             },
            () => console.log('Finished')

            );
}
conf_status_change_to_active() {
    const submitstatus = 'InActive';
    const addoreditpage = false; // add page is true , edit page is false
    this.permissionGroup.statusName = 'Active';

  const sizerollemapping = Number( JSON.stringify(this.permissionGroup.permissionGroupRoleMapping.length));
  for ( let _i = 0 ; _i < sizerollemapping ; _i++)  {
    this.permissionGroup.permissionGroupRoleMapping[_i].roleId = this.permissionGroup.permissionGroupRoleMapping[_i].role.id;
    this.permissionGroup.permissionGroupRoleMapping[_i].partyId = this.permissionGroup.permissionGroupRoleMapping[_i].party.id;
    this.permissionGroup.permissionGroupRoleMapping[_i].productId = this.permissionGroup.permissionGroupRoleMapping[_i].product.id;
    this.permissionGroup.permissionGroupRoleMapping[_i].unitId = this.permissionGroup.permissionGroupRoleMapping[_i].unit.id;
    this.permissionGroup.permissionGroupRoleMapping[_i].portfolioId = this.permissionGroup.permissionGroupRoleMapping[_i].portfolio.id;
   }
          this.subscription = this.permissionGroupService.savePermissionGroup(this.permissionGroup, submitstatus, addoreditpage).
          subscribe(updateData => {
              this.updateData = updateData;
              this.isShowModal = true;
              setTimeout(() => { this.isShowModal = false; }, 3000);
              this.router.navigate([permissiongroup]);
          }, error => {
              this.status_change = false;
             },
            () => console.log('Finished')

            );
}
conf_status_change_deactivate() {
  this.permissionGroup.statusName = 'InActive';
  const deactivestaus = 'InActive';
  const addoreditpage = false; // add page is true , edit page is false
    const sizerollemapping = Number( JSON.stringify(this.permissionGroup.permissionGroupRoleMapping.length));
  for ( let _i = 0 ; _i < sizerollemapping ; _i++)  {
    this.permissionGroup.permissionGroupRoleMapping[_i].roleId = this.permissionGroup.permissionGroupRoleMapping[_i].role.id;
    this.permissionGroup.permissionGroupRoleMapping[_i].partyId = this.permissionGroup.permissionGroupRoleMapping[_i].party.id;
    this.permissionGroup.permissionGroupRoleMapping[_i].productId = this.permissionGroup.permissionGroupRoleMapping[_i].product.id;
    this.permissionGroup.permissionGroupRoleMapping[_i].unitId = this.permissionGroup.permissionGroupRoleMapping[_i].unit.id;
    this.permissionGroup.permissionGroupRoleMapping[_i].portfolioId = this.permissionGroup.permissionGroupRoleMapping[_i].portfolio.id;
    }
          this.subscription = this.permissionGroupService.savePermissionGroup(this.permissionGroup, deactivestaus, addoreditpage).
            subscribe(updateData => {
              this.updateData = updateData;
              this.isShowModal = true;
              setTimeout(() => { this.isShowModal = false; }, 3000);
              this.router.navigate([permissiongroup]);
          }, error => {
                   this.status_change = false;
             },
            () => console.log('Finished')

            );
}

   delete() {
      this.deletebyid = true;
      this.statusMsg = 'Delete';
      this.status = 'Draft';
   }

 conf_delete(id) {
      const paramobj = {'permissionGroupId': id};
          this.subscription = this.permissionGroupService.deleteGroupById(this.permissionGroupId, this.status).subscribe(deletedStatus => {
          this.deletedStatus = deletedStatus;
          this.deletebyid = false;
          if (this.status === 'Draft') {
          this.isDraftStatus = true;
        }else if (this.status === 'Active') {
          this.isActiveStatus = true;
        }
          this.router.navigate([permissiongroup]);

 },
         error => {
            this.errorMessage = error;
            this.isDeletePopupModal = false;
            this.isCannotDeletePopup = true;
        },
         () => console.log('Finished')
    );
}

closedeletepopup() {
   this.isDeletePopupModal = false;
   this.isCannotDeletePopup = false;
 }
 permission_group_delete( id , name , status ) {
    this.permissionGroupId = id;
    this.permissionGroupService.permissionGroupName = name;
    this.status = status;
    if (this.status === 'Draft') {
      this.isDraftStatus = true;
    }else if (this.status === 'Active') {
      this.isActiveStatus = true;
    }
    this.isDeletePopupModal = true;

    return false;
  }

 }



