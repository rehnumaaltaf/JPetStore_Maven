import { Component, NgModule, OnInit, OnDestroy, Output, Input , ViewChild, ViewEncapsulation } from '@angular/core';
import { PermissionGroup } from './permission-group-interface';
import {PermissionGroupService} from './service/permission-group.service';
import { CommonModule, PlatformLocation, Location} from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';
import { FormGroup ,  FormControl,  Validators,  FormBuilder, ReactiveFormsModule , FormsModule} from '@angular/forms';
import { Subscription } from 'rxjs/Subscription';
import { ActivatedRoute, Router } from '@angular/router';
import { addpermissiongroup } from '../../shared/interface/router-links';
import { MasterSetupService } from './../master-setup/service/master-setup.service';
import { ExcelExportData } from '@progress/kendo-angular-excel-export';
import { GridComponent, GridDataResult, DataStateChangeEvent, PageChangeEvent} from '@progress/kendo-angular-grid';
import { process, SortDescriptor, orderBy, GroupDescriptor, State } from '@progress/kendo-data-query';
import { viewpermission } from '../../shared/interface/router-links';
import { ModalDirective } from 'ngx-bootstrap/modal';
@Component({
  selector: 'app-permission-group',
  templateUrl: './permission-group.component.html',
  styleUrls: ['./permission-group.component.css'],
  encapsulation: ViewEncapsulation.None,

})

export class PermissionGroupComponent implements OnInit, OnDestroy {
  @ViewChild('successModal') public successModal: ModalDirective;
  permissionGroupDetails: PermissionGroup[];
  permissionGroup: PermissionGroup;
  subscription: Subscription;
  showHide: boolean;
  public isShowModal: boolean;
  public success: number;
  public permissionGroupName: String;
  public permissionGroupId: number;
  isComplete: Boolean = false;
  isDeletePopupModal: Boolean = false;
  isDeactivatePopupModal: Boolean = false;
  isActiveStatus: Boolean = false;
  isDraftStatus: Boolean = false;
  deleteSuccessModal: Boolean = false;
  isCancelUpdatePopupModal: Boolean = false;
  status: String;
  isReactivatePopupModal: boolean;
  errorMessageForPermission: String;
  isError: Boolean;
  @Output() loadingData: Boolean = true;
  group: GroupDescriptor[] = [{ field: 'statusName' }];
  multiple: Boolean = false;
  gridView: GridDataResult;
  isNavbtwComponent: boolean;
  allowUnsort: Boolean = true;
  addoreditpageflag: boolean;
  addoreditpagemsg: string;
  sort: SortDescriptor[] = [];
  statusmsg: string;
  state: State = {
        skip: 0,
        take: 1000
    };
  gridData: GridDataResult;
  constructor(private route: ActivatedRoute, private permissionGroupService: PermissionGroupService, private router: Router,
        private masterSetupService: MasterSetupService ,   platformlocation: PlatformLocation, location: Location) {
    this.showHide = true;
    this.success = 0;
    this.allData = this.allData.bind(this);
     platformlocation.onPopState(() => {
         this.isNavbtwComponent = true;

        });
  }

  ngOnInit() {
    window.scrollTo(0, 0);
    this.success = 0;
    this.loadingPermissionList();
    console.log('permission-group loading');
    this.route.queryParams.subscribe(params => {
        // Defaults to 0 if no query param provided.
        this.success = + params['success'];
        if (this.success === 1) {
           this.showModalshowmodel();
           this.statusmsg = ' Draft'
          setTimeout(() => {this.hideModalshowmodel(); } , 3000);
        }
        if (this.success === 2) {
           this.showModalshowmodel();
          this.statusmsg = ' Active'
          setTimeout(() => {this.hideModalshowmodel(); } , 3000);
        }
        if (this.success === 3) {
           this.showModalshowmodel();
           this.statusmsg = ' InActive'
          setTimeout(() => {this.hideModalshowmodel(); } , 3000);
        }




      });
   }

 public showModalshowmodel(): void {
    this.isShowModal = true;
  }

  public hideModalshowmodel(): void {
    this.successModal.hide();
  }
 
  public onHiddenshowmodel(): void {
    this.isShowModal = false;
  }
   ngOnDestroy() {
     this.subscription.unsubscribe();
   }

   loadingPermissionList() {
    this.permissionGroupName = this.permissionGroupService.permissionGroupName;
    this.addoreditpageflag = this.permissionGroupService.addoreditpageflag;
    console.log(' addoreditpageflag ' + this.addoreditpageflag);
    if (this.addoreditpageflag) {
        this.addoreditpagemsg = 'Added';
    } else {
        this.addoreditpagemsg = 'Updated';
    }

    this.subscription = this.permissionGroupService.getPermissionGroupViewList()
    .subscribe(permissionGroupDetails => {
    this.permissionGroupService.addPermission(<PermissionGroup[]>permissionGroupDetails.body);
    this.loadingData = false;
    /*
    this.masterSetupService.addChildObjects(this.masters.links);
       this.link = this.masterSetupService.getChildObject('View PermissionGroup.GET');
    */

    }, error => {
      console.log('Error Loading UOM Listing: ' + <any>error)
      this.loadingData = false;
      //throw error;
      // this.notificationService.addNotications(error);
    });
  }
   checkAllClicked($event) {
    console.log('checkAllClicked ', $event);
    /*this.event.forEach(x => x.state = ev.target.checked)*/
  }

   sortChange(sort: SortDescriptor[]): void {
        this.sort = sort;
        this.loadingPermissionList();
    }

  modalPopup(event) {
    // alert('popup');

  }

 protected dataStateChange(state: DataStateChangeEvent): void {
         this.state = state;
         this.gridData = process(this.permissionGroupService.permissionGroupDetails, this.state);
    }


   pageChange(event: PageChangeEvent): void {
        this.state.skip = event.skip;
        this.loadingPermissionList();
    }

  redirecttoadd() {
    this.permissionGroupService.permissionGroup = null;
     this.router.navigate([addpermissiongroup]);
   }
  OnDestroy() {
    // prevent memory leak when component destroyed
    this.subscription.unsubscribe();
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


  closedeletepopup()  {
   this.isDeletePopupModal = false;
     this.isDeactivatePopupModal = false;
   }

   conf_delete(event) {
     const param = {'id': this.permissionGroupId};
     const otherarray = [];
     otherarray.push(param);
     const paramobj = { 'permissionGroupIds' : [] };
      this.isDeletePopupModal = false;
     paramobj.permissionGroupIds = otherarray;
     this.subscription = this.permissionGroupService.deleteGroupById(this.permissionGroupId, this.status).subscribe(deletedStatus => {
          this.isDeletePopupModal = false;
          this.deleteSuccessModal = true;
          if (this.status === 'Draft') {
             this.isDraftStatus = true;
          }else if (this.status === 'Active') {
             this.isActiveStatus = true;
          }
          setTimeout(() => {this.deleteSuccessModal = false;  }, 3000);
          this.loadingPermissionList();

       },
         error => {
           this.errorMessageForPermission = error ;
            this.isDeletePopupModal = false;
             this.isError = true;
            // throw error;
        }

    );
}

clickToClosePop() {
    this.isError = false;
}
editClick(id) {
  this.subscription = this.permissionGroupService.getPermissionGroupById(id).subscribe(permissionGroupDetails => {
    this.permissionGroupService.permissionGroup = permissionGroupDetails.body;
         this.router.navigate(['/master/addpermissiongroup']);
       },
         error => {
           this.errorMessageForPermission = error ;
            this.isDeletePopupModal = false;
             this.isError = true;
            // throw error;
        }

    );
}

 viewById(id) {
    this.subscription = this.permissionGroupService.getPermissionGroupById(id).subscribe(permissionGroupDetails => {
    this.permissionGroupService.permissionGroup = permissionGroupDetails.body;
       this.router.navigate([viewpermission]);
       },
         error => {
           this.errorMessageForPermission = error ;
            this.isDeletePopupModal = false;
             this.isError = true;
           //  throw error;
        }

    );
   }

 allData(): ExcelExportData {
    const result: ExcelExportData = {
    data: process(this.permissionGroupService.permissionGroupDetails, this.state).data,
      group: this.group
    };

    return result;
  }
}
