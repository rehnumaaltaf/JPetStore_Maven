import { Component, Inject, OnInit, OnDestroy, Output, Input, ViewChild } from '@angular/core';
import { FormGroup, FormControl, Validators, FormArray, FormBuilder, ReactiveFormsModule, FormsModule } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Router } from '@angular/router';
import { HolidayCalendar } from '../holiday-calendar-interface';
import { HolidayCalendarService } from '../service/holiday-calendar.service';
import { holidayCalendar } from '../../../../shared/interface/router-links';
import { Subscription } from 'rxjs/Subscription';
import { ModalModule } from 'ngx-bootstrap';
import { ModalDirective } from 'ngx-bootstrap/modal';
import { DialogModule } from 'primeng/primeng';
import { Link } from '../../../../shared/interface/link';
@Component({
  selector: 'app-view-holiday-calendar',
  templateUrl: './view-holiday-calendar.component.html',
  styleUrls: ['./view-holiday-calendar.component.css']
})
export class ViewHolidayCalendarComponent implements OnInit, OnDestroy {
  @ViewChild('successModal') public successModal: ModalDirective;
  @ViewChild('deletesuccessModal') public deletesuccessModal: ModalDirective;
  @ViewChild('statusChangePopupModal') public statusChangePopupModal: ModalDirective;

  public actvataionStatus: any;
  public data: any;
  public calendar_Code;
  public calendar_Name;
  public savedData;
  public holidayCalName;
  public holidayCalCode;
  public isupdateModal;
  isUpdate: any;
  isActivated: any;
  isInActivated: any;

  isNavbtwComponent: boolean;
  isActionPerformed = false;
  private link: Link;
  public isShowModal: boolean;
  public backdrop: boolean;
  public success;
  public holidayCal_Name;
  public holidayCal_Code;
  public holidayCal_Id;
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
  holidayCalendarNgModelBinding: HolidayCalendar = new HolidayCalendar();
  holidayCalendar: HolidayCalendar;
  @ViewChild('pagebackModal') public pagebackModal: ModalDirective;

  public draft;
  public active;
  public inactive;
  subscription: Subscription;
  updateData: any;
  isEdit: boolean;
  editMode: boolean;
  public gl_Id;
  errorMessage: any;
  glCode: any;
  status: any;
  statusParam: any;
  glName: any;
  deletebyid: boolean;
  public deletedStatus;
  ispagebackPopupModal: boolean;
  isCannotDeletePopup: boolean;
  isUpdatedSuccessPopup: boolean;
  statusMsg: any;
  status_change: boolean;
  isActiveStatus: boolean;
  isDraftStatus: boolean;
  public listToedit = [];
  public featureValList = [];
  public featureList1 = [];
  public moduleNameList = [];
  public listOfHolidayCalendaredit = [];
  public map1: Map<string, Object> = new Map<string, Object>();
  public statuses: any = {
    isFirstOpen: true,
    isFirstDisabled: false
  };
  constructor(private route: ActivatedRoute, http: Http, private router: Router,
    private holidayCalendarService: HolidayCalendarService) { }

  ngOnInit() {
    // this.pagebackModal.hide();
    window.scrollTo(0, 0);
    this.isDeletePopupModal = false;
    this.status_change = false;
    this.isDeactivatePopupModal = false;
    this.isStatusChangePopupModal = false;
    this.isCannotDeletePopup = false;
    this.editMode = true;
    this.deletebyid = false;

    this.holidayCalendarNgModelBinding = this.holidayCalendarService.viewbyIdParam;

    // this.holidayCalendarNgModelBinding.holidayListDto[0].holidayListDate
    // const viewDate = new Date(this.holidayCalendarNgModelBinding.holidayListDto[0].holidayListDate);
    // this.holidayCalendarNgModelBinding.holidayListDto[0].holidayListDate =
    // new Date(viewDate.getMonth() + '/' + viewDate.getDate() + '/' + '/' + viewDate.getFullYear())

    // alert(JSON.stringify(this.holidayCalendarNgModelBinding))
    //  alert("s"+this.glData.statusName)
    if (this.holidayCalendarNgModelBinding.statusName === 'Draft') {
      this.draft = true;
      this.active = false;
      this.inactive = false;
    } else if (this.holidayCalendarNgModelBinding.statusName === 'Active') {
      this.active = true;
      this.draft = false;
      this.inactive = false;
    } else if (this.holidayCalendarNgModelBinding.statusName === 'InActive') {
      this.inactive = true;
      this.active = false;
      this.draft = false;
    }

    this.isDeletePopupModal = false;
    this.isdeleteSuccessModal = false;
    this.isCannotDeletePopup = false;

    this.subscription = this.holidayCalendarService.getSelectedFeatues
      ('/holidaycalendarservice/holidaycalendar/', this.holidayCalendarNgModelBinding.holidayCalId).
      subscribe(addHolidayCalendar => {
        this.holidayCalendarService.viewHolidayCalendarObject = addHolidayCalendar.body;
        // alert('=holiday multiple==>' + JSON.stringify(addHolidayCalendar.body));
        // console.log(JSON.stringify(addHolidayCalendar.body.featureMap['feature']));
        for (let i = 0; i < this.holidayCalendarService.viewHolidayCalendarObject.holidayListDto.length; i++) {
          // tslint:disable-next-line:max-line-length
          //   alert(this.basePaymentService.viewbasePaymentdetails.baseNagotiationTerm[i].nagotiationCode);
          const featureVal = this.holidayCalendarService.viewHolidayCalendarObject.holidayListDto[i].holidayListName;
          // tslint:disable-next-line:max-line-length
          const moduleName = this.holidayCalendarService.viewHolidayCalendarObject.holidayListDto[i].holidayListDate;
          // tslint:disable-next-line:max-line-length
          this.featureList1.push(featureVal);
          console.log(JSON.stringify(this.featureList1));
          this.moduleNameList.push(moduleName);
        }
        /*  for (let i = 0; i < this.featureValList.length; i++) {
               this.featureList1[i] = this.featureValList[i].replace("\"","");
             }
             for (let j = 0; j < this.featureList1.length; j++) {
               this.featureList1[j] = this.featureList1[j].replace("\"","");
             }
          // tslint:disable-next-line:no-shadowed-variable
          for (let i = 0 ; i < this.moduleNameList.length; i++) {
               this.moduleNameList[i] = this.moduleNameList[i].replace("\"","");
             }
             for (let j = 0 ; j < this.featureList1.length; j++) {
               this.moduleNameList[j] = this.moduleNameList[j].replace("\"","");
             } */
      },
      error => //  alert(error),
      () => console.log('Finished')
      );






  }


  backtoPrev() {
    this.router.navigate([holidayCalendar]);
  }
  close() {
    this.isCannotDeletePopup = false;
  }

  enableEdit() {
    this.editMode = false;
    this.isEdit = true;
  }

  /* conf_status_change(event) {
      this.isShowModal = true;
     setTimeout(() => {this.isShowModal = false; }, 3000);
    this.router.navigate([holidayCalendar]);
  } */
  updatewithStatus(currentStatus: String) {
    // alert(statusName);
    // this.isStatusChangePopupModal = true;
    this.currentStatus = currentStatus;
    this.isDeletePopupModal = true;
    if (currentStatus === 'Active') {
      this.deactivateSuccessDialog = 'Please Confirm to Deactivate ' + this.holidayCalendarNgModelBinding.holidayCalName ;
    } else if (currentStatus === 'InActive') {
      this.deactivateSuccessDialog = 'Please Confirm to Activate '  + this.holidayCalendarNgModelBinding.holidayCalName ;
    } else if (currentStatus === 'Draft') {
      this.deactivateSuccessDialog = 'Please Confirm to Delete '  + this.holidayCalendarNgModelBinding.holidayCalName ;
    } else if (currentStatus === 'AcitvateDraft') {
      this.deactivateSuccessDialog = 'Please Confirm to Activate '  + this.holidayCalendarNgModelBinding.holidayCalName ;
    }

  }

  conf_delete(params) {

    // this.calendar_Name = this.holidayCalendarService.originCodeParam;
    //  alert("===holidayListDto1===>"+JSON.stringify(this.holidayCalendarService.originCodeParam));
    // alert("===holidayListDto2===>"+JSON.stringify(this.calendar_Name));
    // this.isDeactivatePopupModal = false;
    this.successModal.hide();

    if (this.currentStatus === 'Draft') {
     // alert(this.holidayCalendarNgModelBinding.holidayCalId);
      const param = { 'holidayCalId': this.holidayCalendarNgModelBinding.holidayCalId };
      this.subscription = this.holidayCalendarService.deleteHolidayCalendarById
        (param)
        .subscribe(deletedStatus => {
          // this.successModal.hide();
          //  this.holidayCalName = params['holidayCalName'];
          const statusCode = this.getStatusCode(deletedStatus);
          this.router.navigate([holidayCalendar], {
            queryParams:
            { 'delSuccess': 5, holidayCalName: this.holidayCalendarNgModelBinding.holidayCalName }
          });
        },
        error => { throw error });
    } else {
      this.perfromupdate();
    }



  }


  perfromupdate() {
    // alert(this.currentStatus)
    let actvataionStatus = 1;
    console.log(JSON.stringify(this.holidayCalendarService.viewbyIdParam));
    this.holidayCalendarNgModelBinding = this.holidayCalendarService.viewbyIdParam
    if (this.holidayCalendarNgModelBinding.statusName === 'Draft') {
      this.holidayCalendarNgModelBinding.statusName = 'Active';
    } else if (this.holidayCalendarNgModelBinding.statusName === 'Active'  ) {
      this.holidayCalendarNgModelBinding.statusName = 'InActive';
      actvataionStatus = 2;
    } else {// if (this.holidayCalendarNgModelBinding.statusName === 'InActive') {
      this.holidayCalendarNgModelBinding.statusName = 'Active';
    }
 // alert( JSON.stringify(this.holidayCalendarNgModelBinding))
    this.holidayCalendarNgModelBinding.deletedIds = [0];
    this.listToedit.push(this.holidayCalendarNgModelBinding)
    this.subscription = this.holidayCalendarService.updateHolidayCal(this.holidayCalendarNgModelBinding).subscribe(savedData => {
      // this.glAccountService.glDetails.push(<GLAccount>savedData);
      // this.router.navigate([holidayCalendar], {
      //   queryParams:
      //   { 'isUpdate': 1, holidayCalName: this.holidayCalendarNgModelBinding.holidayCalName,
      //   currentStatus: this.holidayCalendarNgModelBinding.statusName }
      // });

       this.router.navigate([holidayCalendar], {
          queryParams:
          { 'Activate': actvataionStatus, holidayCalName: this.holidayCalendarNgModelBinding.holidayCalName }
        });

    },
      error => alert(error),
      () => console.log('Finished')
    );

    // alert(' End inside update ');
  }

  onHiddenpopup() {
    // this.isStatusChangePopupModal = false;
    this.isDeletePopupModal = false;
    this.isDeactivatePopupModal = false;
    this.isupdateModal = false;
    // this.isDeactivatePopupModal = false;
    // this.successModal.hide();
  }

  conf_update(selection: boolean) {
    if (selection) {

      this.perfromupdate();

    } else {
      this.statusChangePopupModal.hide();
      this.successModal.hide();
      // this.isStatusChangePopupModal = false;
    }
  }


  closedeletepopup() {
    // this.successModal.hide();
    // this.deletebyid = false;
    // this.status_change = false;
    this.isDeactivatePopupModal = false;
    this.isStatusChangePopupModal = false;
    this.isDeletePopupModal = false;
    // this.pagebackModal.hide();
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

 

  onHidden() {
    this.isdeleteSuccessModal = false;
  }
ngOnDestroy() {
    this.subscription.unsubscribe();
  }


  /* onSubmitBtnClick() {
  alert(this.holidayCalendarNgModelBinding.statusName);
     if (this.holidayCalendarNgModelBinding.statusName === 'Draft') {
       // this.dialogValue = 'Do you want Active the Financial Calendar ' + this.financialCal.fiscalYear;
    //   this.isConfirmPopupModal = true;
        this.subscription = this.holidayCalendarService.activeFinancialCalendar(this.holidayCalendarNgModelBinding.pkFinCalId)
        .subscribe(data => {
       //   this.successModal.hide();
          this.successDialog = 'You have successfully Activated financial calendar ' + this.holidayCalendarNgModelBinding.fiscalYear;
           this.isconfirmSuccessModal = true;
           setTimeout(() => { this.confirmSuccessModal.hide();
             this.router.navigate([holidayCalendar]);
       }, 1000);
        });
     }
    } */



}

// extra code

/* conf_status_change(event) {
 console.log(this.holidayCalendarNgModelBinding);
 alert('in confirm');
    this.subscription = this.holidayCalendarService.updateStatusInView
    (this.holidayCalendarNgModelBinding, this.holidayCalendarNgModelBinding.statusName).subscribe(updateData => {
          this.updateData = updateData;
          this.isShowModal = true;
          setTimeout(() => {this.isShowModal = false; }, 3000);
          this.router.navigate([holidayCalendar]);

     }, error => {

           this.isCannotDeletePopup = true;
         },
        () => console.log('Finished')

        );
}  */


// confirm() {
//     // alert(' inside confirm ');
//     this.status_change = true;

//     this.isDeactivatePopupModal = true;
//     // alert(' 2nd inside confirm ');
//     // this.isShowModal = true;
//     // alert('1');
//     if (this.holidayCalendarNgModelBinding.statusName === 'Active') {
//       //  alert(1);
//       this.statusMsg = 'Deactivate';
//       this.holidayCalendarNgModelBinding.statusName = 'InActive';
//       this.status = 'Active';
//       //  this.pagebackModal.show();
//     } else if (this.holidayCalendarNgModelBinding.statusName === 'InActive') {
//       //  alert(2);
//       this.statusMsg = 'Activate';
//       this.holidayCalendarNgModelBinding.statusName = 'Active';
//       this.status = 'InActive';
//       //   this.pagebackModal.show();
//     } else if (this.holidayCalendarNgModelBinding.statusName === 'Draft') {
//       // alert(3);
//       this.statusMsg = 'Activate';
//       this.holidayCalendarNgModelBinding.statusName = 'Active';
//       this.status = 'Draft';
//       //  this.pagebackModal.show();
//     }
//     // this.listOfHolidayCalendaredit.push(this.holidayCalendarNgModelBinding);
//     //   this.subscription = this.holidayCalendarService.updateHolidayCal(this.listOfHolidayCalendaredit).subscribe(savedwithStatus => {
//     //   this.router.navigate([holidayCalendar], { queryParams: { 'success': 1} });
//     //   },
//     //   error => alert(error),
//     //   () => console.log('Finished')
//     // );

//     this.subscription = this.holidayCalendarService.updateHolidayCal(this.holidayCalendarNgModelBinding).subscribe(savedwithStatus => {
//       this.router.navigate([holidayCalendar], { queryParams: { 'success': 1 } });
//     },
//       error => alert(error),
//       () => console.log('Finished')
//     );

//     // alert(' End inside confirm ');
//   }


  // activateDraft(currentStatus: String) {
  //   this.currentStatus = currentStatus;
  //   this.isDeletePopupModal = true;
  //   this.deactivateSuccessDialog = 'Are you sure you want to Activate '  + this.holidayCalendarNgModelBinding.holidayCalName + '?';

  // }

 // holidayCalander_Delete(params) {
  //   this.actvataionStatus = 1;
  //   console.log(this.holidayCalendarNgModelBinding);
  //   // alert(JSON.stringify(params));
  //   // this.isDeactivatePopupModal = true;
  //   this.holidayCal_Id = params.holidayCalId;
  //   this.status = params.statusName;
  //   this.holidayCal_Code = params.originCode;
  //   this.holidayCal_Name = params.originName;
  //   this.isDeletePopupModal = true;
  //   if (this.status === 'Draft') {
  //     this.deactivateSuccessDialog = 'Are you sure you want to delete' + params['holidayCalName'] + '?';
  //   } else if (this.status === 'Active') {
  //     this.actvataionStatus = 2;
  //     this.deactivateSuccessDialog = 'Are you sure you want to Activate' + params['holidayCalName'] + '?';
  //   } else if (this.status === 'Inactive') {
  //     this.deactivateSuccessDialog = 'Are you sure you want to Deactivate' + params['holidayCalName'] + '?';
  //   }

  // }
