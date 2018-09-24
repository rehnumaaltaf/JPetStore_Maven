import { Component, OnInit, OnDestroy, Output, Input } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';
import { UserRoleService } from './service/user-role.service';
import { UserRoleInterface } from './user-role.interface';
import { FormGroup ,  FormControl,  Validators,  FormBuilder, ReactiveFormsModule , FormsModule} from '@angular/forms';
import { UserRoleData } from './user-role-data';
import { ActivatedRoute } from '@angular/router';
import { Module1 } from '././moduleunit';
import { Module2 } from '././moduleunit';
import { Router } from '@angular/router';
import { adduserrole } from '../../shared/interface/router-links';
import { viewuserrole } from '../../shared/interface/router-links';
import { PlatformLocation, Location} from '@angular/common';
import { Module } from '././module';
import { ExcelExportData } from '@progress/kendo-angular-excel-export';
import { GridComponent, GridDataResult, DataStateChangeEvent, PageChangeEvent} from '@progress/kendo-angular-grid';
import { process, SortDescriptor, orderBy, GroupDescriptor, State } from '@progress/kendo-data-query';
import { subModule } from '././module';


@Component({
  selector: 'app-view-user-role',
  templateUrl: './view-user-role.component.html',
  styleUrls: ['./view-user-role.component.css']
})


export class ViewUserRoleComponent implements OnInit {
  userRoleDetails: UserRoleInterface[];
  subscription: Subscription;
  showHide: boolean;
  public iseditModal;
  public success;
  public isRoleAdded;
  
  platformlocation: PlatformLocation;
  isDeletePopupModal: boolean;
  isCannotDeletePopup: boolean;
  public del_id;
  gridView: GridDataResult;
  allowUnsort: Boolean = true;
  sort: SortDescriptor[] = [];
  state: State = {
        skip: 0,
        take: 1000
    };
  isNavbtwComponent : boolean; 
  group: GroupDescriptor[] = [{ field: 'statusName' }];
  public errorMessage;
  deleteSuccessModal: boolean;
  public savedData;
  public roleName;
  public roleDesc;
  public status;
  public statusmsg;
  isDraftStatus: boolean;
  isActiveStatus: boolean;
  isupdateModal: boolean;
  isShowModal :boolean;
  viewByIddelete: boolean = false;
userRoleDataList: UserRoleData = new UserRoleData();
  roleNameToShow: string;
  userRoleDataListToBeEdited: UserRoleData = new UserRoleData();
  isComplete: Boolean = false;
  @Output() loadingData: Boolean = true;
  gridData: GridDataResult;
   constructor(private route: ActivatedRoute, platformlocation: PlatformLocation, public userRoleService: UserRoleService, private router: Router) {
  //  this.showHide = true;
  this.getListingPageData();
  this.allData = this.allData.bind(this);
     platformlocation.onPopState(() => {
                this.isNavbtwComponent = true;               
                //this.router.navigate(['addUom'], { queryParams: {}});
                //this.location.go('addUom');
                //this.route.queryParams = [];
        });
    }

   ngOnInit() {
     //debugger;
     window.scrollTo(0, 0);
    this.roleNameToShow = this.userRoleService.roleNameToShow;
    this.route.queryParams.subscribe(params => {
        // Defaults to 0 if no query param provided.
        this.success = +params['success'];
        if (this.success === 1) {
          this.isRoleAdded = true;
          setTimeout(() => {this.isRoleAdded = false; }, 3000);
        } if (this.success === 9) {
          this.isupdateModal = true;
          setTimeout(() => {this.isupdateModal = false; }, 3000);
        } if (this.success === 8) {
          this.viewByIddelete = true;
           setTimeout(() => {this.viewByIddelete = false; }, 3000);
        }
       
      });
   }
   getPickListItems() {
     this.subscription = this.userRoleService.getFeaturePickListValue().subscribe(userRole => {
      this.userRoleService.featuresList = userRole.body.featureValues;
    },
      error => alert(error),
      () => console.log('Finished')
    );

}
viewById(event) {
    // alert("==eventparam==>"+JSON.stringify(event));
    this.userRoleService.viewbyIdParam = event;
    this.router.navigate([viewuserrole]);
  }
 pageChange(event: PageChangeEvent): void {
        this.state.skip = event.skip;
        this.getListingPageData();
    }

 allData(): ExcelExportData {
   console.log('ddd');
    const result: ExcelExportData = {
      data: process(this.userRoleService.userRole12, this.state).data,
      group: this.group
    };

    return result;
  }
  

  redirectToAddUserRole() {
     this.router.navigate([adduserrole], { queryParams: { 'elem': 5 } });
   }
  OnDestroy() {
    // prevent memory leak when component destroyed
    this.subscription.unsubscribe();
  }

  deleteClick() {
   // alert("delete")
  }
editClick(role) {
   this.subscription = this.userRoleService.getSelectedFeatues(role.roleId).subscribe(userRole => {
    this.userRoleService.selectedFeatureToedit(userRole.body.featureMap);
    this.userRoleService.editUserRoleFeatures(role);
      this.router.navigate([adduserrole], { queryParams: { 'elem': 6 } });
    },
      error => alert(error),
      () => console.log('Finished')
    );
}
 close() {
    this.iseditModal = false;
  }

onRowSelect(event) {
 // alert(event.data);
}
sortChange(sort: SortDescriptor[]): void {
        this.sort = sort;
        this.getListingPageData();
    }
getListingPageData() {
     this.subscription = this.userRoleService.getListingPageValues().subscribe(userRole => {
     this.userRoleService.userRole12 = userRole.body;
     this.isDeletePopupModal = false;
     // alert(this.userRoleService.userRole12);
    },
      error => alert(error),
      () => console.log('Finished')
    );

}
public  dataStateChange(state: DataStateChangeEvent): void {
         this.state = state;
        this.gridData = process(this.userRoleService.userRole12, this.state);
    }
user_delete(event, roleName, roleDesc, roleStatus ) {
 // alert(roleStatus)
   this.del_id = event;
   this.roleName = roleName;
   this.userRoleService.roleNameToShow = roleName;
   this.roleDesc = roleDesc;
   this.status = roleStatus;
   this.isDeletePopupModal = true;
   if (this.status === 'Draft') {
      this.isDraftStatus = true;
    }else if (this.status === 'Active') {
      this.isActiveStatus = true;
    }
 }
 closedeletepopup() {
   this.isDeletePopupModal = false;
   this.isCannotDeletePopup = false;
 }
 conf_delete(id) {
     const param = {'id': this.del_id};
      this.roleNameToShow = this.userRoleService.roleNameToShow;
     const paramList = [];
     paramList.push(param);
     const paramobj = { 'userRoleId' : []};
     paramobj.userRoleId = paramList;
    this.subscription = this.userRoleService.deleteUserRole(this.del_id, this.status ).subscribe(deletedStatus => {
                           this.savedData = deletedStatus;
                           this.deleteSuccessModal = true;
                          if (this.status === 'Draft') {
                            this.isDraftStatus = true;
                          }else if (this.status === 'Active') {
                            this.isActiveStatus = true;
                          }
                           setTimeout(() => {this.deleteSuccessModal = false; }, 3000);
                          this.getListingPageData();

       },
         error => {
            this.errorMessage = error;
            this.isDeletePopupModal = false;
            this.isCannotDeletePopup = true;
        },
         () => console.log('Finished')
    );
}

 getSelectedPickListItems(selectedData) {

    // alert(JSON.stringify(selectedData));
   const roleId = selectedData.roleId;
  // alert("dole" + roleId)
     this.subscription = this.userRoleService.getSelectedFeatues(roleId).subscribe(userRole => {
      this.userRoleService.selectedFeature = userRole.body[0].feature.value;
      console.log(JSON.stringify(userRole.body[0].feature));
      this.userRoleService.editUserRoleFeatures(this.userRoleService.selectedFeature);
     this.userRoleService.editUserRole(<UserRoleData>selectedData);
      this.router.navigate(['/master/edituserrole']);
    },
      error => alert(error),
      () => console.log('Finished')
    );

}
checkAllClicked($event) {
    console.log('checkAllClicked', $event);
     /*this.event.forEach(x => x.state = ev.target.checked)*/
  }


}


