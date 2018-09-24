import { addCalendarSetup } from '../../../../shared/interface/router-links';
import { Component, Inject, OnInit, OnDestroy, Output, Input, ViewChild ,  ViewEncapsulation } from '@angular/core';
import { FormGroup, FormControl, Validators, FormArray, FormBuilder, ReactiveFormsModule, FormsModule } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Router } from '@angular/router';
import { CalendarSetupInterface } from '../calendar-setup-interface';
import { CalendarSetupService } from '../service/calendar-setup.service';
import { Subscription } from 'rxjs/Subscription';
import { ModalModule } from 'ngx-bootstrap';
import { ModalDirective } from 'ngx-bootstrap/modal';
import { Link } from '../../../../shared/interface/link';
import { listingPageCalendarSetup } from '../../../../shared/interface/router-links';

@Component({
  selector: 'app-view-calendar-setup',
  templateUrl: './view-calendar-setup.component.html',
  styleUrls: ['./view-calendar-setup.component.css'],
  encapsulation:  ViewEncapsulation.None
})
export class ViewCalendarSetupComponent implements OnInit {
	@ViewChild('successModal') public successModal: ModalDirective;
  @ViewChild('deletesuccessModal') public deletesuccessModal: ModalDirective;
  @ViewChild('statusChangePopupModal') public statusChangePopupModal: ModalDirective;
  @ViewChild('pagebackModal') public pagebackModal: ModalDirective;
 
 public calendarSetupViewByIdModel: CalendarSetupInterface;
 //CalendarSetupdetails : CalendarSetupInterface [];
CalendarSetupdetails : CalendarSetupInterface = new CalendarSetupInterface();
   public actvataionStatus: any;
  public isupdateModal;
  subscription: Subscription;
  public draft;
  public active;
  public inactive;
  // for accordian//
  public physicals;
  public options;
  public futures;
  public spread;
  public fx;

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
	public code;
	public name;
	public calendarTypeName;
	public exchangeName;
	public productName;
	public phyDlvryStrtDate;
	public trickerCode;
    public phyDlvryEndDate;
    public futursCalendarId;
    public futursCalendarName;
    public dlvryMonth;
    public firstTradeDate;
    public lastTradeDate;
    public firstNoticeDate;
    public lastNoticeDate;
    public firstDlvryDate;
    public lastDlvryDate;
    public finalStlmntDate;
    public spreadMonth1;
    public spreadMonth2;
    public tenorId;
    public tenorName;
    public span;
    public durationType;
	
	public calendarsetupdata;
 
  public status: any = {
    isFirstOpen: true,
    isFirstDisabled: false
  };
  constructor(private route: ActivatedRoute, http: Http, private router: Router,
    private calendarSetupService: CalendarSetupService) { }

  ngOnInit() { 
	  
	  window.scrollTo(0, 0);
    this.isDeletePopupModal = false;
    this.status_change = false;
    this.isDeactivatePopupModal = false;
    this.isStatusChangePopupModal = false;
    this.isCannotDeletePopup = false;
    this.deletebyid = false;
    this.editMode = true;

    this.calendarSetupViewByIdModel = this.calendarSetupService.viewbyIdParam;
	console.log("calendarSetupViewByIdModel"+this.calendarSetupViewByIdModel+"calendarSetupViewByIdModel");

  console.log("financialCalendarModel ==>"+JSON.stringify(this.calendarSetupViewByIdModel));
  console.log("financialCalendarModel ==>"+this.calendarSetupViewByIdModel.calendarSetupId);
	this.calendarSetupService.paramPassingId    = this.calendarSetupViewByIdModel.calendarSetupId;
	
	if (this.calendarSetupViewByIdModel.status === 'Draft') {
      this.draft = true;
      this.active = false;
      this.inactive = false;
    } else if (this.calendarSetupViewByIdModel.status === 'Active') {
      this.active = true;
      this.draft = false;
      this.inactive = false;
    } else if (this.calendarSetupViewByIdModel.status === 'InActive') {
      this.inactive = true;
      this.active = false;
      this.draft = false;
    }
	//
	
	this.subscription = this.calendarSetupService.getSelectedcalendarSetupByID ()
     .subscribe(addCalendarSetup => {
      this.calendarSetupService.CalendarSetupdetails = addCalendarSetup.body;
	  this.calendarsetupdata=this.calendarSetupService.CalendarSetupdetails
      this.code = this.calendarsetupdata.calendarCode;
      this.name = this.calendarsetupdata.calendarName;
      this.calendarTypeName = this.calendarsetupdata.calendarTypeName;
      this.exchangeName = this.calendarsetupdata.exchangeName;
      this.productName = this.calendarsetupdata.productName;
      this.trickerCode = this.calendarsetupdata.trickerCode;
      this.phyDlvryStrtDate = this.calendarsetupdata.phyDlvryStrtDate;
	  this.phyDlvryEndDate = this.calendarsetupdata.phyDlvryEndDate;
	  this.futursCalendarId = this.calendarsetupdata.futursCalendarId;
	  this.futursCalendarName = this.calendarsetupdata.futursCalendarName;	  
	  this.dlvryMonth = this.calendarsetupdata.dlvryMonth;
	  this.firstTradeDate = this.calendarsetupdata.firstTradeDate;
	  this.lastTradeDate = this.calendarsetupdata.lastTradeDate;
	  this.firstNoticeDate = this.calendarsetupdata.firstNoticeDate;
	  this.lastNoticeDate = this.calendarsetupdata.lastNoticeDate;
	  this.firstDlvryDate = this.calendarsetupdata.firstDlvryDate;	  
	  this.lastDlvryDate = this.calendarsetupdata.lastDlvryDate;
	  this.finalStlmntDate = this.calendarsetupdata.finalStlmntDate;
	  this.spreadMonth1 = this.calendarsetupdata.spreadMonth1;
	  this.spreadMonth2 = this.calendarsetupdata.spreadMonth2;
	  this.tenorId = this.calendarsetupdata.tenorId;
	  this.tenorName = this.calendarsetupdata.tenorName;
	  this.span = this.calendarsetupdata.span;
	 this.durationType = this.calendarsetupdata.durationType;
	  
 },
      error => alert(error),
      () => console.log('Finished')
    );
	//
	if (this.calendarSetupViewByIdModel.calendarTypeName === 'Physicals'){
			this.physicals=true;
			this.options=false;
			this.futures=false;
			this.spread=false;
			this.fx=false;
		
		} else if (this.calendarSetupViewByIdModel.calendarTypeName === 'Options') {
			this.physicals=false;
			this.options=true;
			this.futures=false;
			this.spread=false;
			this.fx=false;
			
		} else if (this.calendarSetupViewByIdModel.calendarTypeName === 'Futures') {
			this.physicals=false;
			this.options=false;
			this.futures=true;
			this.spread=false;
			this.fx=false;
			
		} else if (this.calendarSetupViewByIdModel.calendarTypeName === 'Spread') {
			this.physicals=false;
			this.options=false;
			this.futures=false;
			this.spread=true;
			this.fx=false;
			
		} else if (this.calendarSetupViewByIdModel.calendarTypeName === 'FX') {
			this.physicals=false;
			this.options=false;
			this.futures=false;
			this.spread=false;
			this.fx=true;
		
		}	
  }
  
 updatewithStatus(currentStatus: String) {
    // this.isStatusChangePopupModal = true;
    this.currentStatus = currentStatus;
    this.isDeletePopupModal = true;
    if (currentStatus === 'Active') {
      this.deactivateSuccessDialog = 'Please Confirm to Deactivate ' + this.calendarSetupViewByIdModel.calendarName ;
    } else if (currentStatus === 'InActive') {
      this.deactivateSuccessDialog = 'Please Confirm to Activate '  + this.calendarSetupViewByIdModel.calendarName ;
    } else if (currentStatus === 'Draft') {
      this.deactivateSuccessDialog = 'Please Confirm to Delete '  + this.calendarSetupViewByIdModel.calendarName ;
    } else if (currentStatus === 'AcitvateDraft') {
      this.deactivateSuccessDialog = 'Please Confirm to Activate '  + this.calendarSetupViewByIdModel.calendarName ;
    }

  }
  
  
  
  conf_delete(params) {
	  alert("i am "+this.calendarSetupViewByIdModel.calendarSetupId);
    this.successModal.hide();
    if (this.currentStatus === 'Draft') {
		 
	  const param = this.calendarSetupViewByIdModel.calendarSetupId;
	  alert(param)
      this.subscription = this.calendarSetupService.deletecalendarSetupById
        (param)
        .subscribe(deletedStatus => {
          // this.successModal.hide();
          //  this.holidayCalName = params['holidayCalName'];
          const statusCode = this.getStatusCode(deletedStatus);
          this.router.navigate([listingPageCalendarSetup], {
            queryParams:
            { 'delSuccess': 5, calendarName: this.calendarSetupViewByIdModel.calendarName }
          });
        },
        error => { throw error });
    } else {
       this.perfromupdate();
    }
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
  
  perfromupdate() {
    this.calendarSetupViewByIdModel = this.calendarSetupService.viewbyIdParam
    if (this.calendarSetupViewByIdModel.status === 'Draft') {
      this.calendarSetupViewByIdModel.status = 'Active';
    } else if (this.calendarSetupViewByIdModel.status === 'Active'  ) {
      this.calendarSetupViewByIdModel.status = 'InActive';
    } else {
      this.calendarSetupViewByIdModel.status = 'Active';
    }
    this.calendarSetupViewByIdModel.calendarCode ="0";
    this.subscription = this.calendarSetupService.updateCalendarSetup(this.calendarSetupViewByIdModel.calendarSetupId).subscribe(savedData => {
      // this.glAccountService.glDetails.push(<GLAccount>savedData);
      this.router.navigate([listingPageCalendarSetup], {
        queryParams:
        { 'isUpdate': 1, calendarName: this.calendarSetupViewByIdModel.calendarName,
        currentStatus: this.calendarSetupViewByIdModel.status }
      });

    },
      error => alert(error),
      () => console.log('Finished')
    );
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
    this.router.navigate([listingPageCalendarSetup]);
  }
  close() {
    this.isCannotDeletePopup = false;
  }
 enableEdit(event){    
   this.calendarSetupService.paramPassingId=event.calendarSetupId;
    //this.subscription = this.CalendarsetupService.getSelectedcalendarSetupByID().subscribe(calenadrSetupResponse => {
      this.subscription = this.calendarSetupService.getListingPageFromJson().subscribe(calenadrSetupResponse => {
      this.calendarSetupService.CalendarSetupdetails = calenadrSetupResponse.body;
       this.router.navigate([addCalendarSetup], { queryParams: { 'elem': 6 } });
     },
       error => { throw error });
	   
    //  this.router.navigate([addCalendarSetup], { queryParams: { 'elem': 6 } });     
  }

}
