import { Component, OnInit, OnDestroy, Output, ViewChild } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Subscription } from 'rxjs/Subscription';
import { Router } from '@angular/router';
import { ExcelExportData } from '@progress/kendo-angular-excel-export';
import { GridComponent, GridDataResult, DataStateChangeEvent, PageChangeEvent } from '@progress/kendo-angular-grid';
import { process, SortDescriptor, orderBy, GroupDescriptor, State } from '@progress/kendo-data-query';
import { Observable } from 'rxjs/Rx';
import { HolidayCalendarService } from './service/holiday-calendar.service';
import { ActivatedRoute } from '@angular/router';
import { addHolidayCalendar } from '../../../shared/interface/router-links';
import { viewHolidayCalendar } from '../../../shared/interface/router-links';
import { Link } from '../../../shared/interface/link';
import { ResponseData } from '../../../shared/interface/responseData';
import { FormGroup, FormControl } from '@angular/forms';
import { SelectItem } from '../../../shared/interface/selectItem';
import { DropdownModule } from 'primeng/primeng';
import { ButtonModule } from 'primeng/primeng';
import { ConfirmDialogModule, ConfirmationService } from 'primeng/primeng';
import { ModalModule, AccordionModule } from 'ngx-bootstrap';
import { ModalDirective } from 'ngx-bootstrap/modal';
import { master } from '../../../shared/interface/router-links';
import { holidayCalendar } from '../../../shared/interface/router-links';
import { MasterSetupService } from '../../master-setup/service/master-setup.service';
import { HolidayCalendar } from './holiday-calendar-interface';



@Component({
  selector: 'app-holiday-calendar',
  templateUrl: './holiday-calendar.component.html',
  styleUrls: ['./holiday-calendar.component.css']
})
export class HolidayCalendarComponent implements OnInit, OnDestroy {
  @ViewChild('successModal') public successModal: ModalDirective;
  @ViewChild('deletesuccessModal') public deletesuccessModal: ModalDirective;
  @ViewChild('updateModal') public updateModal: ModalDirective;
  //  @ViewChild ('holidayedit') public holidayedit: ModalDirective;

  public holidayCaldArray = [];
  subscription: Subscription;
  public data: any;
  public status;
  public calendar_Code;
  public calendar_Name;
  public savedData;
  public holidayCalName;
  public holidayCalCode;
  public isupdateModal;
  public isUpdate;
  isActivated: Number;
  isDeactivated: any;
  isInActivated: any;
  deactivateSuccessDialog: string;
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
  public isdeleteSuccessModal: Boolean = false;
  isDeactivatePopupModal: Boolean = false;
  successToast: string;
  isCannotDeletePopup: boolean;
  multiple: Boolean = false;
  gridView: GridDataResult;
  allowUnsort: Boolean = true;
  gridData: GridDataResult;
  group: GroupDescriptor[] = [];
  sort: SortDescriptor[] = [];
  public holidayDetails: HolidayCalendar = new HolidayCalendar();
  state: State = {
    skip: 0,
    take: 1000
  };

  allItemsChecked: Boolean = false;

  groups;
  delSuccess: number;
  constructor(private route: ActivatedRoute, http: Http, private router: Router,
    public holidayCalendarService: HolidayCalendarService
  ) { }

  changeShowStatus() {
    // this.showHide = !this.showHide;
    this.router.navigate([addHolidayCalendar]);
  }

  ngOnInit() {
    window.scrollTo(0, 0);
    this.holidaycalendarlist();
    this.isShowModal = false;

    this.route.queryParams.subscribe(params => {
      // Defaults to 0 if no query param provided.
      this.calendar_Name = this.holidayCalendarService.originCodeParam;
       this.success = Number(params['success']);
      this.isUpdate = Number(params['isUpdate']);
      this.isActivated = Number(params['Activate']);
      this.delSuccess = Number(params['delSuccess']);

      // THIS BLOCK IS TOAST FROM SAVE AND SUBMIT FROM ADD PAGE

      if (this.success === 1) {
        // this.holidayCalName = params['holidayCalName'];
        // setTimeout(() => { this.isShowModal = false; } , 3000);
        this.isShowModal = true;
        setTimeout(() => { this.successModal.hide(); }, 3000);
        this.router.navigate([holidayCalendar]);

      } else if (this.isUpdate) {
        // this.holidayCalName = params['holidayCalName'];
        this.successToast = 'Success: Holiday Calendar ' + params['holidayCalName'] +  ' Updated'
        this.isupdateModal = true;
        if (params['currentStatus'] === 'Active' || params['currentStatus'] === 'Draft') {
          this.successToast = 'Success: Holiday Calendar ' + params['holidayCalName'] +  ' Updated'  ;
        } else if (params['currentStatus'] === 'InActive') {
          this.successToast = 'Success: Holiday Calendar ' + params['holidayCalName'] +  ' Deactivated'  ;
        } // alert("inside update");
        setTimeout(() => { this.updateModal.hide(); }, 3000);
        this.router.navigate([holidayCalendar]);
      } else if (this.delSuccess === 5) {
         this.isupdateModal = true;
        this.successToast = 'Success: Holiday Calendar ' + params['holidayCalName'] +  ' Deleted'  ;
        setTimeout(() => { this.updateModal.hide(); }, 3000);
        this.router.navigate([holidayCalendar]);

      }

      // THIS BBLOCK IS TOAST FROM UPDATE ACTIVATE AND DEACTIVATE FROM EDIT PAGE

      if (this.isActivated === 1) {
       // alert('In active 1')
        this.holidayCalName = params['holidayCalName'];
        this.successToast = 'Success: Holiday Calendar ' + params['holidayCalName'] +  ' Activated'  ;
        this.isupdateModal = true;
        // alert("inside update");
        setTimeout(() => { this.updateModal.hide(); }, 3000);
        this.router.navigate([holidayCalendar]);
        if (params['status'] === 'Active') {
          this.status = 'Activated'
        } else { this.status = 'Deactivated' }
        setTimeout(() => { this.updateModal.hide(); }, 3000);
        this.router.navigate([holidayCalendar]);
      } else if (this.isActivated === 2) {
       // alert('In active 2')
        this.holidayCalName = params['holidayCalName'];
        this.successToast = 'Success: Holiday Calendar ' + params['holidayCalName'] +  ' Deactivated'  ;
        this.isupdateModal = true;
        // alert("inside update");
        setTimeout(() => { this.updateModal.hide(); }, 3000);
        this.router.navigate([holidayCalendar]);
      }

    });
   // this.isDeletePopupModal = false;
     // this.isdeleteSuccessModal = false; // if we uncomment then it shows error of hide undefined
    // this.isCannotDeletePopup = false;
    // this.isupdateModal = false;
     // this.isShowModal = false;
  }
  viewById(event) {
    // alert('==eventparam==>' + JSON.stringify(event));
    this.holidayCalendarService.viewbyIdParam = event;
    console.log('holiday componet', this.holidayCalendarService.viewbyIdParam);
    this.router.navigate([viewHolidayCalendar]);
  }

  holidaycalendarlist() {
    this.subscription = this.holidayCalendarService.getHolidayJSON().subscribe(holidaylist => {
      this.holidayCalendarService.holidaydetails = holidaylist.body;
      this.data = holidaylist.body;
    },
      error => alert(error),
      () => console.log('Finished')
    );
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
  dataStateChange(state: DataStateChangeEvent): void {
    this.state = state;
    this.gridData = process(this.holidayCalendarService.holidayCalendarSaveDetails, this.state);
  }


  pageChange(event: PageChangeEvent): void {
    this.holidaycalendarlist();
  }

  allData(): ExcelExportData {
    const result: ExcelExportData = {
      data: process(this.holidayCalendarService.holidayCalendarSaveDetails, this.state).data,
      group: this.group
    };

    return result;
  }

  errorHandler(errorMessage) {
    // this.isdeleteSuccessModal = false;
    // this.isCannotDeletePopup = true;
  }
  conf_delete(params) {
   this.successModal.hide();
   const param = { 'holidayCalId': this.holidayCal_Id };
    this.subscription = this.holidayCalendarService.deleteHolidayCalendarById
      (param)
      .subscribe(deletedStatus => {
        // this.successModal.hide();
        //  this.holidayCalName = params['holidayCalName'];
         this.isdeleteSuccessModal = true;
        const statusCode = this.getStatusCode(deletedStatus);
        if (this.status === 'Active') {
          // alert('activate')
          this.deactivateSuccessDialog = 'Success: Holiday Calendar ' + this.holidayCalName +  ' Deactivated'  ;
        } else if (this.status === 'InActive') {
          // alert('reactivate')
          this.deactivateSuccessDialog = 'Success: Holiday Calendar ' + this.holidayCalName +  ' Activated'  ;
        } else if (this.status === 'Draft') {
          this.deactivateSuccessDialog = 'Success: Holiday Calendar ' + this.holidayCalName +  ' Deleted'  ;
          // this.deactivateSuccessDialog = 'successfully deleted calendar ';
        }
        this.holidaycalendarlist();
        // tslint:disable-next-line:curly
        // tslint:disable-next-line:no-non-null-assertion
        if (this.status) {
        setTimeout(() => { this.deletesuccessModal.hide(); }, 3000);
        }
        // this.router.navigate([holidayCalendar], {
        //     queryParams:
        //     { 'delSuccess': 5, holidayCalName: this.holidayCalName }
        //   });
      },
      error => { throw error });
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

  holidayCalander_Delete(params) {
    console.log(JSON.stringify(params));
    // alert(JSON.stringify(params));
    // this.isDeactivatePopupModal = true;
    this.holidayCal_Id = params.holidayCalId;
    this.status = params.statusName;
    this.holidayCalName = params.holidayCalName;
   // alert(JSON.stringify(this.holidayCalName));
    this.isDeletePopupModal = true;
    if (this.status === 'Draft') {
      this.deactivateSuccessDialog = 'Please Confirm to Delete ' + params['holidayCalName'] ;
    } else if (this.status === 'Active') {
      this.deactivateSuccessDialog = 'Please Confirm to Deactivate ' + params['holidayCalName'] ;
    } else if (this.status === 'Inactive') {
      this.deactivateSuccessDialog = 'Please Confirm to Activate ' + params['holidayCalName'] ;
    }

  }

  holidayCalander_status_change(params) {
    this.holidayDetails = params;
    let actvataionStatus;
  //  alert(JSON.stringify( this.holidayDetails))
    if (this.holidayDetails.statusName === 'Active') {
      this.holidayDetails.statusName = 'InActive';
      actvataionStatus = 2;
     // alert(JSON.stringify('should show inactive ' + this.holidayDetails.statusName))
      //  params.statusName = 'Acive'
      } else if (this.holidayDetails.statusName === 'Draft') {
      actvataionStatus = 5;
      this.holidayDetails.statusName = 'Active';
     // alert(JSON.stringify('should show active ' + this.holidayDetails.statusName))
     } else if (this.holidayDetails.statusName === 'InActive') {
      this.holidayDetails.statusName = 'Active';
     // alert(JSON.stringify('should show active ' + this.holidayDetails.statusName))
    }
    this.holidayDetails.deletedIds = [0];
//    this.holidayCaldArray = new Array()
    this.holidayCaldArray.push(this.holidayDetails);
    // alert(JSON.stringify(this.holidayCaldArray));
    this.subscription = this.holidayCalendarService.updateHolidayCal(this.holidayDetails).subscribe(savedwithStatus => {
    // this.router.navigate([holidayCalendar], { queryParams: { 'success': 1 } });
    if (actvataionStatus === 2) {
    this.router.navigate([holidayCalendar], {
          queryParams:
          { 'Activate': actvataionStatus, holidayCalName: this.holidayDetails.holidayCalName }
        });
    } else if (actvataionStatus === 5) { this.router.navigate([holidayCalendar], {
          queryParams:
          { 'delSuccess': actvataionStatus, holidayCalName: this.holidayDetails.holidayCalName }
        });
       }
     },
      error => alert(error),
      () => console.log('Finished')
    );
  }

  onHiddenpopup() {
    this.isDeletePopupModal = false;
    this.isDeactivatePopupModal = false;
    this.isupdateModal = false;
    this.isShowModal = false;
    // this.isdeleteSuccessModal = false;
  }


  canDeactivate(): boolean {
    return this.isActionPerformed;
  }
ngOnDestroy() {
    this.subscription.unsubscribe();
  }

}

//  extra code ----

  //   onHidden() {
  //  this.deleteSuccessModal = false;

  // }

  //  closedeletepopup() {
  //    alert('123')
  //    this.successModal.hide();
  //     this.isDeactivatePopupModal = false;
  //     this.isDeletePopupModal = false;

  //  }

  // resetCancel($event) {
  //   this.updateModal.hide();
  //   this.isupdateModal = false;
  //   this.isDeactivatePopupModal = false;
  //   this.isDeletePopupModal = false;
  //   // this.successModal.hide();
  // }
  // onHiddenReset() {
  //   this.isupdateModal = false;
  //   // this.isDeletePopupModal = false;
  // }
  // public closesuccess(): void {
  //   this.successModal.hide();
  // }

