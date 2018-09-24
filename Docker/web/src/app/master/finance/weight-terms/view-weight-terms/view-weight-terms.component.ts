import { Component, Inject, OnInit, OnDestroy, Output, Input, ViewChild } from '@angular/core';
import { FormGroup, FormControl, Validators, FormArray, FormBuilder, ReactiveFormsModule, FormsModule } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Router } from '@angular/router';
import { Tolerance, Franchise, WeightTermModel } from '../weight-terms';
import { WeightTermsService } from '../service/weight-terms.service';
import { Subscription } from 'rxjs/Subscription';
import { ModalModule } from 'ngx-bootstrap';
import { ModalDirective } from 'ngx-bootstrap/modal';
import { Link } from '../../../../shared/interface/link';
import { listweight } from '../../../../shared/interface/router-links';
import { WeightTermsComponent } from '../../weight-terms/weight-terms.component';
@Component({
  selector: 'app-view-weight-terms',
  templateUrl: './view-weight-terms.component.html',
  styleUrls: ['./view-weight-terms.component.css']
})
export class ViewWeightTermsComponent implements OnInit {
 @ViewChild('successModal') public successModal: ModalDirective;
  @ViewChild('deletesuccessModal') public deletesuccessModal: ModalDirective;
  @ViewChild('statusChangePopupModal') public statusChangePopupModal: ModalDirective;
  @ViewChild('pagebackModal') public pagebackModal: ModalDirective;
  @ViewChild('editModal') public editModal: ModalDirective;
  weightTermViewByIdModel: WeightTermModel = new WeightTermModel();
  public actvataionStatus: any;
  public isupdateModal;
  subscription: Subscription;
  public draft;
  public active;
  public inactive;
  isUpdate: any;
  isActivated: any;
  isInActivated: any;
   isActionPerformed = false;
   public isShowModal: boolean;
  public backdrop: boolean;
  public success;
   @Output() loadingData: Boolean = true;
  isDeletePopupModal: Boolean = false;
  deactivateSuccessModal: boolean;
  isdeleteSuccessModal: boolean;
  isDeactivatePopupModal: Boolean = false;
  isStatusChangePopupModal: Boolean = false;
  deactivateSuccessDialog: string;
  multiple: Boolean = false;
  groups;
  delSuccess: any;
  public currentStatus;
  reactivateSuccessModal: any;
  deletebyid: boolean;
  public deletedStatus;
  ispagebackPopupModal: boolean;
  isCannotDeletePopup: boolean;
  isUpdatedSuccessPopup: boolean;
  statusMsg: any;
  status_change: boolean;
  isActiveStatus: boolean;
  isDraftStatus: boolean;
    editMode: boolean;
    isEdit: boolean;
  public statuses: any = {
    isFirstOpen: true,
    isFirstDisabled: false
  };

  // edit part
  weighttermmaterial: WeightTermModel[];
  weighttermdetail: WeightTermModel = new WeightTermModel();
  UpdateModal = false;
  openEdit: Boolean = false;
  constructor(private route: ActivatedRoute, http: Http, private router: Router,
    private weightTermsService: WeightTermsService) { }


  ngOnInit() {
    window.scrollTo(0, 0);
    this.isDeletePopupModal = false;
    this.status_change = false;
    this.isDeactivatePopupModal = false;
    this.isStatusChangePopupModal = false;
    this.isCannotDeletePopup = false;
    this.deletebyid = false;
    this.editMode = true;

    this.weightTermViewByIdModel = this.weightTermsService.viewbyIdParam;
     if (this.weightTermViewByIdModel.statusName === 'Draft') {
      this.draft = true;
      this.active = false;
      this.inactive = false;
    } else if (this.weightTermViewByIdModel.statusName === 'Active') {
      this.active = true;
      this.draft = false;
      this.inactive = false;
    } else if (this.weightTermViewByIdModel.statusName === 'InActive') {
      this.inactive = true;
      this.active = false;
      this.draft = false;
    }
  }

  updatewithStatus(currentStatus: String) {
    // alert(statusName);
    // this.isStatusChangePopupModal = true;
    this.currentStatus = currentStatus;
    this.isDeletePopupModal = true;
    if (currentStatus === 'Active') {
      this.deactivateSuccessDialog = 'Please Confirm to Deactivate ' + this.weightTermViewByIdModel.weightTermsName ;
    } else if (currentStatus === 'InActive') {
      this.deactivateSuccessDialog = 'Please Confirm to Activate '  + this.weightTermViewByIdModel.weightTermsName ;
    } else if (currentStatus === 'Draft') {
      this.deactivateSuccessDialog = 'Please Confirm to Delete '  + this.weightTermViewByIdModel.weightTermsName ;
    } else if (currentStatus === 'AcitvateDraft') {
      this.deactivateSuccessDialog = 'Please Confirm to Activate '  + this.weightTermViewByIdModel.weightTermsName ;
    }

  }

   conf_delete(params) {
    this.successModal.hide();
    if (this.currentStatus === 'Draft') {
     // alert(this.holidayCalendarNgModelBinding.holidayCalId);
      const param = { 'weightTermsId': this.weightTermViewByIdModel.weightTermsId };
      this.subscription = this.weightTermsService.deleteHolidayCalendarById
        (param)
        .subscribe(deletedStatus => {
          // this.successModal.hide();
          //  this.holidayCalName = params['holidayCalName'];
          const statusCode = this.getStatusCode(deletedStatus);
          this.router.navigate([listweight], {
            queryParams:
            { 'delSuccess': 5, weightTermsName: this.weightTermViewByIdModel.weightTermsName }
          });
        },
        error => { throw error });
    } else {
       this.perfromupdate();
    }
  }

  perfromupdate() {
    // alert(this.currentStatus)
    let actvataionStatus;
    console.log(JSON.stringify(this.weightTermsService.viewbyIdParam));
    this.weightTermViewByIdModel = this.weightTermsService.viewbyIdParam
    if (this.weightTermViewByIdModel.statusName === 'Draft') {
      actvataionStatus = 5;
      this.weightTermViewByIdModel.statusName = 'Active';
    } else if (this.weightTermViewByIdModel.statusName === 'Active'  ) {
      actvataionStatus = 2;
      this.weightTermViewByIdModel.statusName = 'InActive';
    } else {// if (this.holidayCalendarNgModelBinding.statusName === 'InActive') {
      this.weightTermViewByIdModel.statusName = 'Active';
    }
 // alert( JSON.stringify(this.holidayCalendarNgModelBinding))
   // this.holidayCalendarNgModelBinding.deletedIds = [0];
   // this.listToedit.push(this.holidayCalendarNgModelBinding)
    this.subscription = this.weightTermsService.updateWeightTerms(this.weightTermViewByIdModel).subscribe(savedData => {
      // this.glAccountService.glDetails.push(<GLAccount>savedData);
       if (actvataionStatus === 2) {
    this.router.navigate([listweight], {
          queryParams:
          { 'Activate': actvataionStatus, weightTermsName: this.weightTermViewByIdModel.weightTermsName }
        });
    } else if (actvataionStatus === 5) { this.router.navigate([listweight], {
          queryParams:
          { 'delSuccess': actvataionStatus, weightTermsName: this.weightTermViewByIdModel.weightTermsName }
        });
    } else {
      this.router.navigate([listweight], {
        queryParams:
        { 'isUpdate': 1, weightTermsName: this.weightTermViewByIdModel.weightTermsName,
        currentStatus: this.weightTermViewByIdModel.statusName }
      });
      }

    },
      error => // alert(error),
      () => console.log('Finished')
    );

    // alert(' End inside update ');
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

   onHiddenpopup() {
    // this.isStatusChangePopupModal = false;
    this.isDeletePopupModal = false;
    this.isDeactivatePopupModal = false;
    this.isupdateModal = false;
    // this.isDeactivatePopupModal = false;
    // this.successModal.hide();
  }
    onHidden() {
    this.isdeleteSuccessModal = false;
  }
   backtoPrev() {
    this.router.navigate([listweight]);
  }
  close() {
    this.isCannotDeletePopup = false;
  }
  enableEdit() {
    this.editMode = false;
    this.isEdit = true;
  }
   editRow() {
    // alert('inside edit  ' + this.weighttermmaterial + '    ' + index);
    this.openEdit = true;
    this.weighttermdetail = this.weightTermViewByIdModel;
   // alert('id is' + JSON.stringify(this.weighttermdetail));
  }

  onHideEditModal() {
      this.openEdit = false;

  }

  closeEditModal($event) {
    //  alert('on click');
    this.editModal.hide();
  //  alert('close popup');
   // this.loadingWeightTremList();
      // this.UpdateModal = true;
      this.router.navigate([listweight], {
        queryParams:
        { 'isUpdate': 1, weightTermsName: this.weightTermViewByIdModel.weightTermsName,
        currentStatus: this.weightTermViewByIdModel.statusName }
      });
      setTimeout(() => {
        this.UpdateModal = false;
      }, 3000);
   // }
  }
// ngOnDestroy() {
//     this.subscription.unsubscribe();
//   }


}
