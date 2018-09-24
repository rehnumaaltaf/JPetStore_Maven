import { Component, OnInit, OnDestroy, Output, Input, ViewChild } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';
import { GLAccount } from './gl-account';
import { Router } from '@angular/router';
import { GlAccountService } from './service/gl-account.service';
import { FormGroup, FormControl } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { SelectItem } from '../../../shared/interface/selectItem';
import { DropdownModule } from 'primeng/primeng';
import { ButtonModule } from 'primeng/primeng';
import { ConfirmDialogModule, ConfirmationService } from 'primeng/primeng';
import { addGl } from '../../../shared/interface/router-links';
import { CanComponentDeactivate } from '../../../shared/guards/can-deactivate-guard';
import { ExcelExportData } from '@progress/kendo-angular-excel-export';
import { GridComponent, GridDataResult, DataStateChangeEvent, PageChangeEvent} from '@progress/kendo-angular-grid';
import { process, SortDescriptor, orderBy, GroupDescriptor, State } from '@progress/kendo-data-query';
import { viewGl } from '../../../shared/interface/router-links';
import { ModalDirective } from 'ngx-bootstrap/modal';



@Component({
  selector: 'app-gl-account',
  templateUrl: './gl-account.component.html',
  styleUrls: ['./gl-account.component.css']
})
export class GlAccountComponent implements OnInit, OnDestroy, CanComponentDeactivate {
  public mode: Boolean = false;
  public allowUnsort: Boolean = true;
  showHide: boolean;
  subscription: Subscription;
  isComplete: Boolean = false;
  selectedUOMrow: GLAccount;
  glData: GLAccount = new GLAccount();
  UomCodeDropDown: SelectItem[];
  public success;
  public isShowModal;
  public savedData;
   public gledit;
  public gl_Id;
  public status;
  public glCode;
  isDeletePopupModal: boolean;
  deactivateSuccessModal: boolean;
  public isdeleteSuccessModal: Boolean = false;
  isActionPerformed = false;
  isDeactivatePopupModal: Boolean = false;
  public glName;
  public isupdateModal;
  isUpdate: any;
  isActivated: any;
  isInActivated: any;
  @Output() loadingData: Boolean = true;
  public gldatabyid; // for edit GL
  public viewbyIdParam; // for edit GL
   gridView: GridDataResult;
   gridData: GridDataResult;
    sort: SortDescriptor[] = [];
  state: State = {
        skip: 0,
        take: 1000,
    };
  group: GroupDescriptor[] = [{ field: 'statusName' }];
 public allItemsChecked: Boolean = false;
delSuccess: any;
reactivateSuccessModal: any;
deleteStatus: String;
statusMsg: String;
@ViewChild('confirmdel') public confirmdel: ModalDirective;
@ViewChild('deleteSuccessModal') public deleteSuccessModal: ModalDirective;

  constructor(private route: ActivatedRoute, private router: Router, public glAccountService: GlAccountService) {
    this.showHide = true;
    this.allData = this.allData.bind(this);
    this.loadingGLList();
  }

  changeShowStatus() {
    this.glAccountService.editGlId = null; // for edit GL navigation to add
    this.router.navigate([addGl]);
  }

 // for edit GL
  /*editgl(glId: number) {

      this.glAccountService.editGlId = glId;
      this.router.navigate([addGl]);
    }*/

  ngOnInit() {

     this.gledit = {'glCode': '', 'glId': '', 'glName': '', 'glFullname': '',
      'glDesc': '', 'externalGlMappingName': '' , 'statusName': '', 'externalGlMappingCode' : ''};

    this.route.queryParams.subscribe(params => {
        // Defaults to 0 if no query param provided.
        // this.glCode = this.glAccountService;

        this.success = +params['success'];
        this.isUpdate = params['isUpdate'];
        this.isActivated = params['Activate'];
        this.delSuccess = params['delSuccess'];
        if (this.success === 1) {
          this.isShowModal = true;
          this.glName = params['glName'];
          setTimeout(() => { this.isShowModal = false; } , 3000);

        } else if (this.isUpdate) {
          this.isupdateModal = true;
          this.glCode = params['glcode'];
          setTimeout(() => { this.isupdateModal = false; } , 3000);
        }else if (this.isActivated) {
          this.isupdateModal = true;
          this.glCode = params['glcode'];
          if (params['status'] === 'Active') { this.status = 'Activated'
          }else { this.status = 'Deactivated'}
          setTimeout(() => { this.isupdateModal = false; } , 3000);
        } else if (this.delSuccess) {
           this.glName = params['glName'];
            if (params['status'] === 'Draft') {
             this.isdeleteSuccessModal = true;
            setTimeout(() => { this.deleteSuccessModal.hide(); } , 3000);
          } else if (params['status'] === 'InActive') {
             this.deactivateSuccessModal = true;
            setTimeout(() => { this.deactivateSuccessModal = false; } , 3000);
          } else {
             this.reactivateSuccessModal = true;
            setTimeout(() => { this.reactivateSuccessModal = false; } , 3000);
          }
        }
        // } else if (this.isActivated) {
        //   this.isupdateModal = true;
        //   this.glName = params['glname'];
        //   this.status = params['status'];
        //   setTimeout(() => { this.isupdateModal = false; } , 3000);
        // } else if (this.isInActivated) {
        //   this.isupdateModal = true;
        //   this.glName = params['glname'];
        //   this.status = params['status'];
        //   setTimeout(() => { this.isupdateModal = false; } , 3000);
//  }
});

      this.loadingGLList();
    //  this.deleteSuccessModal = false;
      this.isDeletePopupModal = false;
      this.isDeactivatePopupModal = false;
      this.deactivateSuccessModal = false;

  }

  loadingGLList() {
     this.subscription = this.glAccountService.getGLloadingJSON().subscribe(glDetails => {
     this.glAccountService.glDetails = glDetails.body;
      this.loadglDetails();
    },
      error => alert(error),
      () => console.log('Finished')
    );
  }

  loadglDetails(): void {

        this.gridView = {
            data: process(orderBy(this.glAccountService.glDetails, this.sort), this.state).data,
            total: this.glAccountService.glDetails.length
        };


       // alert("stringify in loaduom===>"+JSON.stringify(this.gridView));
    }

  public dataStateChange(state: DataStateChangeEvent): void {
         this.state = state;
        this.gridData = process(this.glAccountService.glDetails, this.state);
    }

     pageChange(event: PageChangeEvent): void {
        this.state.skip = event.skip;
        this.loadingGLList();
    }

    sortChange(sort: SortDescriptor[]): void {
        this.sort = sort;
        this.loadingGLList();
    }

    checkAllClicked($event) {
    console.log('checkAllClicked', $event);
     /*this.event.forEach(x => x.state = ev.target.checked)*/
  }

  checkorUnCheckAll(ele) {
     const checkboxes = document.getElementsByTagName('input');
     if (ele.target.checked) {
         for (let intval = 0; intval < checkboxes.length; intval++) {
             if (checkboxes[intval].type === 'checkbox') {
                 checkboxes[intval].checked = true;
             }
         }
     } else {
         for (let i = 0; i < checkboxes.length; i++) {
              if (checkboxes[i].type === 'checkbox') {
                 checkboxes[i].checked = false;
              }
         }
     }
 }


 viewById(event) {
    this.glAccountService.viewbyIdParam = event;
    this.router.navigate([viewGl]);
  }


  gl_delete(params) {
    console.log(JSON.stringify(params))
   this.gl_Id = params.glBasicDto.glId;
   this.glName = params.glBasicDto.glName;
     this.status = params.statusName;
    this.confirmdel.show();
      if ( params.statusName === 'Draft') {
        this.deleteStatus = 'delete';
      }else if (params.statusName === 'Active') {
        this.deleteStatus = 'deactivate';
      } else if (params.statusName === 'InActive') {
        this.deleteStatus = 'reactivate';
      }

 }

  conf_delete(id) {
    this.confirmdel.hide();
     const param = { 'glId' : this.gl_Id};
     this.subscription = this.glAccountService.deleteGlById(param).subscribe(deletedStatus => {
                            const  statusCode = this.getStatusCode(deletedStatus);
                        if (statusCode === 200) {
                           if (this.status === 'Draft') {
                               this.statusMsg = 'Deleted';
                           this.isdeleteSuccessModal = true;
                           setTimeout(() => {this.deleteSuccessModal.hide(); } , 3000);
                          } else if (this.status === 'Active') {
                             this.statusMsg = 'Dectivated';
                             this.isdeleteSuccessModal = true;
                             setTimeout(() => {this.deleteSuccessModal.hide(); }, 3000);
                           } else if (this.status === 'InActive') {
                             this.statusMsg = 'InActivated';
                             this.isdeleteSuccessModal = true;
                             setTimeout(() => {this.deleteSuccessModal.hide(); }, 3000);
                           }

                      }
                       this.loadingGLList();
       },
         error => alert(error),
         () => console.log('Finished')
    );
}

 public onHidden() {
   this.isdeleteSuccessModal = false;
 }

 getStatusCode(deletedStatus) {
  let statusCode: number;
   for (const obj in deletedStatus) {
                                if (deletedStatus.hasOwnProperty(obj)) {
                                for (const prop in deletedStatus[obj]) {
                                    if (deletedStatus[obj].hasOwnProperty(prop)) {
                                      if (prop === 'status') {
                                          statusCode = deletedStatus[obj][prop];
                                      }

                                    }
                                  }
                       }
                    }
  return statusCode;
}

closedeletepopup() {
   this.isDeletePopupModal = false;
    this.isDeactivatePopupModal = false;


}

canDeactivate(): boolean {
    return this.isActionPerformed;
  }



  onAddBtnClick() {
    this.showHide = !this.showHide;
  }

  ngOnDestroy() {
    // prevent memory leak when component destroyed
    this.subscription.unsubscribe();
  }

     allData(): ExcelExportData {
    const result: ExcelExportData = {
      data: process(this.glAccountService.glDetails, this.state).data,
      group: this.group
     };

    return result;
  }


}
