import { addCalendarSetup } from '../../../shared/interface/router-links';
import { Component, OnInit, OnDestroy, Output, ViewEncapsulation, Inject , ViewChild  } from '@angular/core';
import { PlatformLocation, Location} from '@angular/common';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Subscription } from 'rxjs/Subscription';
import { Router } from '@angular/router';
import { MasterSetupService } from '../../master-setup/service/master-setup.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { ExcelExportData } from '@progress/kendo-angular-excel-export';
import { GridComponent, GridDataResult, DataStateChangeEvent, PageChangeEvent} from '@progress/kendo-angular-grid';
import { process, SortDescriptor, orderBy, GroupDescriptor, State } from '@progress/kendo-data-query';
import { Observable } from 'rxjs/Rx';
import { CanComponentDeactivate } from '../../../shared/guards/can-deactivate-guard';
import { CalendarSetupInterface } from './calendar-setup-interface';
import { Link } from '../../../shared/interface/link';
import {CalendarSetupService} from './service/calendar-setup.service';
import { viewCalendarSetup } from '../../../shared/interface/router-links';
import { ModalDirective } from 'ngx-bootstrap/modal';
import { listingPageCalendarSetup } from '../../../shared/interface/router-links';

@Component({
  selector: 'app-calendar-setup',
  templateUrl: './calendar-setup.component.html',
  styleUrls: ['./calendar-setup.component.css']
})
export class CalendarSetupComponent implements OnInit {
  @ViewChild('successModal') public successModal: ModalDirective;
  @ViewChild('deletesuccessModal') public deletesuccessModal: ModalDirective;
  @ViewChild('updateModal') public updateModal: ModalDirective;
  
  isdeleteSuccessModal: boolean;
	isActionPerformed = false;
  private link: Link;
  group: GroupDescriptor[] = [{ field: 'statusName' }];
  subscription: Subscription;
  sort: SortDescriptor[] = [];
  isShowModal: boolean;
  private CalendarSetupdetails;
  data: CalendarSetupInterface[];
  isupdateModal: boolean;
  viewByIddelete: boolean = false;
  public success;
  public calendarSetupId;
  deactivateSuccessDialog: string;
  headerTermCode : string;
  headercalendarName : string;
  headercalendarTypeName : string;
  
 
deleteSuccessModal : boolean;
isDeactivatePopupModal: Boolean = false;
headerTermLine : string;
  sizes: any;
  state: State = {
        skip: 0,
        take: 1000
    };
    public del_id;
	
public CalenderForSuccess;
 delSuccess: number;
  isUpdate: any; 
public statusName;
public basetermName;
public statusMsg;
isDeletePopupModal: Boolean = false;
public isDraftStatus;
public isCannotDeletePopup;
public id;
public inactive;
public active;
public draft;
public status1;
public deletebyid;
 public calendarCode;
 public calendarName;
taxRateCodedelpop : string;
statusdelname : string;
 successToast: string;
 isActivated: Number;

 gridData: GridDataResult;

  public status: any = {
    isFirstOpen: true,
    isFirstDisabled: false
  };
    constructor(private route: ActivatedRoute, http: Http, private router: Router ,
              public CalendarsetupService: CalendarSetupService, public masterSetupService: MasterSetupService,
                            platformlocation: PlatformLocation, location: Location) {
 this.loadingCalendarSetupList();
  this.allData = this.allData.bind(this);
     platformlocation.onPopState(() => {
        });                          
  }

  ngOnInit() {
	  
	   window.scrollTo(0, 0); 
    this.calendarCode = this.CalendarsetupService.calendarCodeForPayment;
    this.route.queryParams.subscribe(params => {
        // Defaults to 0 if no query param provided.
         this.calendarName = this.CalendarsetupService.originCodeParam;
       this.success = Number(params['success']);
      this.isUpdate = params['isUpdate'];
      this.isActivated = Number(params['Activate']);
      this.delSuccess = Number(params['delSuccess']);
		// for 
		 // THIS BLOCK IS TOAST FROM SAVE AND SUBMIT FROM ADD PAGE

      if (this.success === 1) {
        this.isShowModal = true;
        setTimeout(() => { this.successModal.hide(); }, 3000);
        this.router.navigate([listingPageCalendarSetup]);

      } else if (this.isUpdate) {
       
        this.successToast = 'Success : Updated Calender Name ' + params['CalenderName'];
        this.isupdateModal = true;
        if (params['currentStatus'] === 'Active' || params['currentStatus'] === 'Draft') {
          this.successToast = 'Success : Activated ' + params['calendarName'];
        } else if (params['currentStatus'] === 'InActive') {
          this.successToast = 'Success : Deactivated ' + params['calendarName'];
        } // alert("inside update");
        setTimeout(() => { this.updateModal.hide(); }, 3000);
        this.router.navigate([listingPageCalendarSetup]);
      } else if (this.delSuccess === 5) {
         this.isupdateModal = true;
        this.successToast = 'successfully deleted calendar Name ' + params['calendarName'];
        setTimeout(() => { this.updateModal.hide(); }, 3000);
        this.router.navigate([listingPageCalendarSetup]);

      }

      // THIS BBLOCK IS TOAST FROM UPDATE ACTIVATE AND DEACTIVATE FROM EDIT PAGE

      if (this.isActivated === 1) {
       // alert('In active 1')
        this.calendarName = params['calendarName'];
        this.successToast = 'Success : Updated & Activated Calender Name ' + this.calendarName;
        this.isupdateModal = true;
        // alert("inside update");
        setTimeout(() => { this.updateModal.hide(); }, 3000);
        this.router.navigate([listingPageCalendarSetup]);
        if (params['status'] === 'Active') {
          this.status = 'Activated'
        } else { this.status = 'Deactivated' }
        setTimeout(() => { this.updateModal.hide(); }, 3000);
        this.router.navigate([listingPageCalendarSetup]);
      } else if (this.isActivated === 2) {
       // alert('In active 2')
        this.calendarName = params['calendarName'];
        this.successToast = 'Success : Updated & Deactivated Calender Name ' + this.calendarName;
        this.isupdateModal = true;
        // alert("inside update");
        setTimeout(() => { this.updateModal.hide(); }, 3000);
        this.router.navigate([listingPageCalendarSetup]);
      }
		//
		
      });
	  this.isdeleteSuccessModal = false;
	  
  }
  
  changeShowStatus () {
   this.router.navigate([addCalendarSetup], { queryParams: { 'elem': 5 } });
  }
  
  allData(): ExcelExportData {
   console.log('ddd');
    const result: ExcelExportData = {
     // data: process(this.basePaymentService.userRole12, this.state).data,
      group: this.group
    };

    return result;
  }
  
   editcalendarSetup(event) {
     this.CalendarsetupService.paramPassingId=event.calendarSetupId;
     this.subscription = this.CalendarsetupService.getSelectedcalendarSetupByID().subscribe(calenadrSetupResponse => {
    // this.subscription = this.CalendarsetupService.getListingPageFromJson().subscribe(calenadrSetupResponse => {
      this.CalendarsetupService.CalendarSetupdetails = calenadrSetupResponse.body;
      console.log(this.CalendarsetupService.CalendarSetupdetails);
       this.router.navigate([addCalendarSetup], { queryParams: { 'elem': 6 } });
     },
       error => { throw error });
	   
   }
 
 viewById(event) {
    this.CalendarsetupService.viewbyIdParam = event;
	this.router.navigate([viewCalendarSetup]);
  }
  
  
  loadingCalendarSetupList() {
	  // this.isDeletePopupModal = false;
     this.subscription = this.CalendarsetupService.getListingPageFromJson().subscribe(addCalendarSetup => {
	 this.CalendarsetupService.CalendarSetupdetails = addCalendarSetup.body;
      //this.data = addCalendarSetup.body;
      /*var data1 = addCalendarSetup.view.column.split(",");
      if (data1[1] != null && data1[1] =='calendarCode') {
        this.headerTermCode = "Calender Code";
      }
      if (data1[0] != null && data1[0] =='calendarName') {
        this.headercalendarName = "Calendar Name";
      }
      if (data1[2] != null && data1[2] =='calendarTypeName') {
        this.headercalendarTypeName = "Calendar Type";
      }
     */
       this.headerTermCode = "Calender Code";
	   this.headercalendarName = "Calendar Name";
	   this.headercalendarTypeName = "Calendar Type";
    
     },
      error => alert(error),
      () => console.log('Finished')
      );
   
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
	 
	 
calendarName_delete(params) {
    this.CalenderForSuccess = params.calendarName;
    this.status = params.status;
    this.calendarSetupId = params.calendarSetupId;
  //  this.holidayCal_Code = params.originCode;
  //  this.holidayCal_Name = params.originName;
    this.isDeletePopupModal = true;
    if (this.status === 'Draft') {
      this.deactivateSuccessDialog = 'Please Confirm to Delete ' + params['calendarName'] ;
    } else if (this.status === 'Active') {
      this.deactivateSuccessDialog = 'Please Confirm to Deactivate ' + params['calendarName'] ;
    } else if (this.status === 'Inactive') {
      this.deactivateSuccessDialog = 'Please Confirm to Activate ' + params['calendarName'] ;
    }
 }
 
conf_delete(params) {
  console.log(JSON.stringify(params)+"params");
 // alert(event);
   const param = this.calendarSetupId;
   //alert(param)
   this.successModal.hide();
   debugger;
    this.subscription = this.CalendarsetupService.deletecalendarSetupById
      (param)
      .subscribe(deletedStatus => {
        
        // this.successModal.hide();
        const statusCode = this.getStatusCode(deletedStatus);
        if (this.status === 'Active') {
          // alert('activate')
          this.deactivateSuccessDialog = 'Success: Calendar Name Name ' + this.CalenderForSuccess +  ' Deactivated'  ;
        } else if (this.status === 'InActive') {
          // alert('reactivate')
          this.deactivateSuccessDialog = 'Success: Calendar Name Name ' + this.CalenderForSuccess +  ' Activated'  ;
        } else if (this.status === 'Draft') {
          this.deactivateSuccessDialog = 'Success: Calendar Name Name ' + this.CalenderForSuccess +  ' Deleted'  ;
          // this.deactivateSuccessDialog = 'successfully deleted calendar ';
        }
        this.isdeleteSuccessModal = true;
        setTimeout(() => { this.deletesuccessModal.hide(); }, 3000);
        this.loadingCalendarSetupList();
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
  closedeletepopup() {
		this.isDeletePopupModal = false;
		this.isCannotDeletePopup = false;
	}
 onHiddenpopup() {
		this.isDeletePopupModal = false;
		this.isDeactivatePopupModal = false;
		this.isupdateModal = false;
    // this.successModal.hide();
  }
	 

}
