import { Component, OnInit } from '@angular/core';
import { Router , ActivatedRoute} from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { FormGroup, FormControl , FormArray,   FormBuilder, ReactiveFormsModule , FormsModule, Validators} from '@angular/forms';
import { CalendarSetupInterface } from '../calendar-setup-interface';
import { CalendarSetupService } from '../service/calendar-setup.service';
import { listingPageCalendarSetup } from '../../../../shared/interface/router-links';
@Component({
  selector: 'app-add-calendar-setup',
  templateUrl: './add-calendar-setup.component.html',
  styleUrls: ['./add-calendar-setup.component.css']
})
export class AddCalendarSetupComponent implements OnInit {
  subscription: Subscription;
  calendartypeArray :any;
  //this is for page title 
  pageTitle: String;
  deactivateSuccessDialog: string;
  public currentStatus;
  public pageName;
  count:number;
  public errormodal: boolean;
  public isupdateModal:boolean;
  public isDeletePopupModal:boolean;


  //date set
   public CalendaRegDate: Date = new Date();
   public CalendaExpDate: Date = new Date();
 public min: Date = new Date();
 public max: Date = new Date(2999, 11, 31);

  //this is for hide an dshow of buttons
  whenDataNotTobeEdited :boolean;
  whenDataEdited:boolean;
  whenStatusInactive:boolean;
  whenStatusactive:boolean;
  //whenDataTobeEdited:boolean;
  isActionPerformed = false;

  // this for validation
req_calendarCode: boolean;
req_calendarName: boolean;
req_trickerCode: boolean;
ispagebackPopupModal: boolean;
req_phyDlvryStrtDate: boolean;
req_phyDlvryEndDate: boolean;
req_dlvryMonth: boolean;
req_lastTradeDate: boolean;
req_firstNoticeDate: boolean;
req_lastNoticeDate: boolean;
req_firstDlvryDate: boolean;
req_lastDlvryDate: boolean;
req_finalStlmntDate: boolean;
req_spreadMonth1: boolean;
req_spreadMonth2: boolean;
req_tenorName: boolean;
req_span: boolean;
req_futursCalendarName: boolean;
req_exchangeName: boolean;
req_productName: boolean;
req_calendarTypeName:boolean;
req_firstTradeDate:boolean;
req_durationType:boolean;

// for accordian
  physicals:boolean;
  options:boolean;
   futures:boolean;
  spread:boolean;
  fx:boolean;
  isDropdownDisabled:boolean;

  calendarDetailsList: CalendarSetupInterface = new CalendarSetupInterface();
  calendarDetailsvalues:any;
  public status: any = {
    isFirstOpen: true,
    isFirstDisabled: false
  };
  constructor( private router: Router, private route: ActivatedRoute,public calendarSetupService: CalendarSetupService) {
        this.whenDataNotTobeEdited = true;
    //this.whenDataTobeEdited = false;
   this.isDropdownDisabled= false;
  }

  ngOnInit() {
        this.route.queryParams.subscribe(params => {
        this.pageName = +params['elem'];      
    });
   this.pageTitle = "Add Calendar Setup";
   if (this.pageName == 6) {
   this.isDropdownDisabled=true;
      this.pageTitle = 'Edit Calendar Setup';     
    //  this.buildUSERROLEForm();  
this.getCalenderRecordstoEdit();
//this.getCalendarNameandType();
    } else {
      //this.buildUSERROLEForm();
    } 
}
getCalenderRecordstoEdit(){
       this.calendarDetailsvalues = this.calendarSetupService.CalendarSetupdetails;
       console.log(JSON.stringify(this.calendarDetailsvalues));
             if(typeof this.calendarDetailsvalues == 'undefined'){
        this.router.navigate([listingPageCalendarSetup]);
      }
      else{
       this.calendarDetailsList.calendarCode=this.calendarDetailsvalues.calendarCode;       
       this.calendarDetailsList.calendarName=this.calendarDetailsvalues.calendarName;
       this.calendarDetailsList.calendarTypeId=this.calendarDetailsvalues.calendarTypeId;
       this.calendarDetailsList.calendarTypeName= this.calendarDetailsvalues.calendarTypeName;

    //    if (this.calendarDetailsList.calendarTypeName === 'Physicals'){
		// 	this.physicals=true;
		// 	this.options=false;
		// 	this.futures=false;
		// 	this.spread=false;
		// 	this.fx=false;
		
		// } else if (this.calendarDetailsList.calendarTypeName === 'Options') {
		// 	this.physicals=false;
		// 	this.options=true;
		// 	this.futures=false;
		// 	this.spread=false;
		// 	this.fx=false;
			
		// } else if (this.calendarDetailsList.calendarTypeName === 'Futures') {
		// 	this.physicals=false;
		// 	this.options=false;
		// 	this.futures=true;
		// 	this.spread=false;
		// 	this.fx=false;
			
		// } else if (this.calendarDetailsList.calendarTypeName === 'Spread') {
		// 	this.physicals=false;
		// 	this.options=false;
		// 	this.futures=false;
		// 	this.spread=true;
		// 	this.fx=false;
			
		// } else if (this.calendarDetailsList.calendarTypeName === 'FX') {
		// 	this.physicals=false;
		// 	this.options=false;
		// 	this.futures=false;
		// 	this.spread=false;
		// 	this.fx=true;
		
		// }	
       this.calendarDetailsList.trickerCode=this.calendarDetailsvalues.trickerCode;
       this.calendarDetailsList.spreadMonth1=this.calendarDetailsvalues.spreadMonth1;
       this.calendarDetailsList.spreadMonth2=this.calendarDetailsvalues.spreadMonth2;
       //this.calendarDetailsList.dlvryMonth=this.calendarDetailsvalues[0].dlvryMonth;
      //this.calendarDetailsList.firstDlvryDate=new Date(this.calendarDetailsvalues[0].firstDlvryDate);
      // this.calendarDetailsList.finalStlmntDate=new Date(this.calendarDetailsvalues[0].finalStlmntDate);
       //this is to display button
     // this.calendarDetailsList.status = this.calendarDetailsvalues[2].status;
      this.mychange();
      console.log("status"+this.calendarDetailsList.status);
      if (this.calendarDetailsList.status == 'Draft') {
        this.whenDataNotTobeEdited = false;
        this.whenDataEdited = true;
        this.whenStatusInactive = false;
        this.whenStatusactive = false;
      }else if (this.calendarDetailsList.status == 'Active') {
        this.whenStatusactive = true;
        this.whenStatusInactive = false;
        this.whenDataEdited = false;
        this.whenDataNotTobeEdited = false;
      }else if (this.calendarDetailsList.status == 'InActive') {
        this.whenStatusInactive = true;
        this.whenDataEdited = false;
        this.whenStatusactive = false;
        this.whenDataNotTobeEdited = false;
      }
      }
}

  // getCalendarNameandType(){
  //  this.calendartypeArray = [];
  //    this.subscription = this.calendarSetupService.getcalendartypeJson().subscribe(calendartyperesponse => {
  //      this.calendartypeArray = calendartyperesponse;
  //     //  this.calendarDetailsList.calendarTypeId=this.calendartypeArray[0].calendarTypeId;
  //      // this.calendarDetailsList.calendarTypeName=this.calendartypeArray[0].calendarTypeName;
  //    },
  //     error => alert(error),
  //     () => console.log('Finished')
  //     );
    
  //    }

  update(status){
  if (this.isValidForm()) {
    if (this.calendarDetailsList.calendarTypeName === 'Physicals') {
      console.log('in physical type');
    if (this.PhysicalsTypes()) {

    this.updateCalendarSetUp(status);
    }
    }
    else if (this.calendarDetailsList.calendarTypeName === 'Futures') {
    if ( this.FuturesTypes()) {
        this.updateCalendarSetUp(status);
    }
  }
    else if (this.calendarDetailsList.calendarTypeName === 'Options') {
     if (this.OptionsTypes()){
       this.updateCalendarSetUp(status);
     }
    }
    else if (this.calendarDetailsList.calendarTypeName === 'Spread') {
     if(this.SpreadTypes()){
       this.updateCalendarSetUp(status);
     }
         }
    else if  (this.calendarDetailsList.calendarTypeName === 'FX') {
         if(this.FXTypes()){
           this.updateCalendarSetUp(status);
         }
    }
}

  }


  updateCalendarSetUp(status){
  this.calendarDetailsList.calendarCode=this.calendarDetailsList.calendarCode;
  this.calendarDetailsList.calendarName=this.calendarDetailsList.calendarName;
  this.calendarDetailsList.calendarTypeId=this.calendarDetailsList.calendarTypeId;
  this.calendarDetailsList.exchangeName=this.calendarDetailsList.exchangeName;
  this.calendarDetailsList.productName=this.calendarDetailsList.productName;
  if(status !== ''){
          this.calendarDetailsList.status = status;
  }      
 console.log(JSON.stringify(this.calendarDetailsList) +"<====>");
// this.subscription = this.calendarSetupService.updateCalendarSetupDetails(this.calendarDetailsList).subscribe(updateTaxRateDetail => {
// this.router.navigate([listingPageCalendarSetup], { queryParams: { 'success': 9 } });
// },
//       error => {     
//         this.ispagebackPopupModal = true;
//      },       
//      )  

  }
cancel(){
        this.router.navigate([listingPageCalendarSetup]);
  }

  //   updateStatus(currentStatus: String){
  
  //     this.currentStatus = currentStatus;
  //    //   alert("hello"+this.taxRateDetailsList.taxCodeUID);
  //   this.isDeletePopupModal = true;
  //   if (currentStatus === 'Active') {
  //     this.deactivateSuccessDialog = 'Please Confirm to Activate ' + this.calendarDetailsList.calendarName ;
  //   } else if (currentStatus === 'InActive') {
  //     this.deactivateSuccessDialog = 'Please Confirm to DeActivate '  + this.calendarDetailsList.calendarName ;
  //   } else if (currentStatus === 'Draft') {
  //     this.deactivateSuccessDialog = 'Please Confirm to Delete '  + this.calendarDetailsList.calendarName ;
  //   } else if (currentStatus === 'AcitvateDraft') {
  //     this.deactivateSuccessDialog = 'Please Confirm to Activate '  + this.calendarDetailsList.calendarName ;
  //   }
  // }

  mychange() {
  if (this.calendarDetailsList.calendarTypeName === 'Physicals') {
 this.physicals = true;
 this.options = false;
 this.futures = false;
 this.spread = false;
 this.fx = false;
    }
 if (this.calendarDetailsList.calendarTypeName === 'Futures') {
 this.physicals = false;
 this.options = false;
 this.futures = true;
 this.spread = false;
 this.fx = false;
    }
 if (this.calendarDetailsList.calendarTypeName === 'Options') {
 this.physicals = false;
 this.options = true;
 this.futures = false;
 this.spread = false;
 this.fx = false;
    }
 if (this.calendarDetailsList.calendarTypeName === 'Spread') {
 this.physicals = false;
 this.options = false;
 this.futures = false;
 this.spread = true;
 this.fx = false;
    }
 if (this.calendarDetailsList.calendarTypeName === 'FX') {
 this.physicals = false;
 this.options = false;
 this.futures = false;
 this.spread = false;
 this.fx = true;
    }

  }

  // Save Functionality for add calendar setup
saveCalendarSetup  (status) {
this.isActionPerformed = true;
if (this.isValidForm()) {
    if (this.calendarDetailsList.calendarTypeName === 'Physicals') {
      console.log('in physical type');
    if (this.PhysicalsTypes()) {

    this.getListOfCalendarSetUp();
    }
    }
    else if (this.calendarDetailsList.calendarTypeName === 'Futures') {
    if ( this.FuturesTypes()) {
        this.getListOfCalendarSetUp();
    }
  }
    else if (this.calendarDetailsList.calendarTypeName === 'Options') {
     if (this.OptionsTypes()){
       this.getListOfCalendarSetUp();
     }
    }
    else if (this.calendarDetailsList.calendarTypeName === 'Spread') {
     if(this.SpreadTypes()){
       this.getListOfCalendarSetUp();
     }
         }
    else if  (this.calendarDetailsList.calendarTypeName === 'FX') {
         if(this.FXTypes()){
           this.getListOfCalendarSetUp();
         }
    }
}

  }

  getListOfCalendarSetUp()
  {
 this.calendarDetailsList.calendarCode = this.calendarDetailsList.calendarCode;
      this.calendarDetailsList.calendarName = this.calendarDetailsList.calendarName;
      this.calendarDetailsList.trickerCode = this.calendarDetailsList.trickerCode;
      this.calendarDetailsList.phyDlvryStrtDate = this.calendarDetailsList.phyDlvryStrtDate;
      this.calendarDetailsList.phyDlvryEndDate = this.calendarDetailsList.phyDlvryEndDate;
      this.calendarDetailsList.dlvryMonth = this.calendarDetailsList.dlvryMonth;
       this.calendarDetailsList.lastTradeDate = this.calendarDetailsList.lastTradeDate;
       this.calendarDetailsList.firstNoticeDate = this.calendarDetailsList.firstNoticeDate;
       this.calendarDetailsList.lastNoticeDate = this.calendarDetailsList.lastNoticeDate;
       this.calendarDetailsList.firstDlvryDate = this.calendarDetailsList.firstDlvryDate;
       this.calendarDetailsList.lastDlvryDate = this.calendarDetailsList.lastDlvryDate;
       this.calendarDetailsList.finalStlmntDate = this.calendarDetailsList.finalStlmntDate;
       this.calendarDetailsList.spreadMonth1 = this.calendarDetailsList.spreadMonth1;
       this.calendarDetailsList.spreadMonth2 = this.calendarDetailsList.spreadMonth2;
        this.calendarDetailsList.tenorId = this.calendarDetailsList.tenorId;
      this.calendarDetailsList.span = this.calendarDetailsList.span;
      this.calendarDetailsList.durationType = this.calendarDetailsList.durationType;
      this.calendarDetailsList.status = status;
      console.log(JSON.stringify(this.calendarDetailsList) + "<====>" + status);
      this.subscription = this.calendarSetupService.addCalendarSetupDetails(this.calendarDetailsList).subscribe(addCalendarSetupDetail => {
      this.router.navigate([listingPageCalendarSetup], { queryParams: { 'success': 1 } }); 
      },
        error => {
          this.ispagebackPopupModal = true;      
        },
        () => console.log('Finished')
      );
  }
  isValidForm(): boolean {
this.count=0;      
           // validation for calendar code
      if (this.calendarDetailsList.calendarCode == null ) {
            this.req_calendarCode  = true;
          this.count++;
      } else if (this.calendarDetailsList.calendarCode != null) {
            if (this.calendarDetailsList.calendarCode.trim() === '') {
              this.req_calendarCode  = true;
              this.count++;
        } else {
                this.req_calendarCode = false;
        }
      }
      // validation for calendar name
      if (this.calendarDetailsList.calendarName == null ) {
            this.req_calendarName  = true;
          this.count++;
      } else if (this.calendarDetailsList.calendarName != null) {
            if (this.calendarDetailsList.calendarName.trim() === '') {
              this.req_calendarName  = true;
              this.count++;
        } else {
                this.req_calendarName = false;
        }
      }
       // validation for calendar type name
      if (this.calendarDetailsList.calendarTypeName == null ) {
            this.req_calendarTypeName  = true;
          this.count++;
      } else if (this.calendarDetailsList.calendarTypeName != null) {
            if (this.calendarDetailsList.calendarTypeName.trim() === '') {
              this.req_calendarTypeName  = true;
              this.count++;
        } else {
                this.req_calendarTypeName = false;
        }
      }
      
      if (this.count !== 0) {
         this.showModal();
                setTimeout(() => {this.onHidden();  }, 3000)
        return false;
    } else {
        return true;
    }

    }
     public showModal(): void {
    this.errormodal = true;
  }
   public onHidden(): void {
    this.errormodal = false;
  }
  PhysicalsTypes() {
    // validation for physical delivery start date
      if (this.calendarDetailsList.phyDlvryStrtDate == null ) {
        console.log(this.calendarDetailsList.phyDlvryStrtDate)
            this.req_phyDlvryStrtDate = true;
          this.count++;
      } else {
                this.req_phyDlvryStrtDate = false;
      }
      // //validation for physical delivery end date
      if (this.calendarDetailsList.phyDlvryEndDate == null ) {
        console.log(this.calendarDetailsList.phyDlvryStrtDate)
            this.req_phyDlvryEndDate = true;
          this.count++;
      } else{
                this.req_phyDlvryEndDate = false;
      }
      // validation for futures calendar
      if (this.calendarDetailsList.futursCalendarName == null ) {
            this.req_futursCalendarName  = true;
          this.count++;
      } else if (this.calendarDetailsList.futursCalendarName != null) {
            if (this.calendarDetailsList.futursCalendarName.trim() === '') {
              this.req_futursCalendarName = true;
              this.count++;
        } else {
                this.req_futursCalendarName = false;
        }
      }
        // validation for Futures calendar
      if (this.calendarDetailsList.futursCalendarName  == null ) {
            this.req_futursCalendarName  = true;
          this.count++;
      } else if (this.calendarDetailsList.futursCalendarName  != null) {
            if (this.calendarDetailsList.futursCalendarName .trim() === '') {
              this.req_futursCalendarName  = true;
              this.count++;
        } else {
                this.req_futursCalendarName = false;
        }
      }
      // validation for ticker code
      if (this.calendarDetailsList.trickerCode == null ) {
            this.req_trickerCode  = true;
          this.count++;
      } else if (this.calendarDetailsList.trickerCode != null) {
            if (this.calendarDetailsList.trickerCode.trim() === '') {
              this.req_trickerCode  = true;
              this.count++;
        } else {
                this.req_trickerCode = false;
        }
      }

      // validation for delivery month
    
      if (this.calendarDetailsList.dlvryMonth == null ||
         this.calendarDetailsList.dlvryMonth === '') {
            this.req_dlvryMonth  = true;
        
           this.count++;
        }
 else {
                this.req_dlvryMonth = false;
        }  
            
           if (this.count !== 0) {
         this.showModal();
                setTimeout(() => {this.onHidden();  }, 3000)
        return false;
    } else {
        return true;
    }
 
}

FuturesTypes(){
 // validation for delivery month
      if (this.calendarDetailsList.dlvryMonth == null ||
        this.calendarDetailsList.dlvryMonth === '') {
        this.req_dlvryMonth  =  true;
          this.count++;
        }
      else {
        this.req_dlvryMonth  =  false;
        }
       // validation for ticker code
      if (this.calendarDetailsList.trickerCode == null ) {
            this.req_trickerCode  = true;
          this.count++;
      } else if (this.calendarDetailsList.trickerCode != null) {
            if (this.calendarDetailsList.trickerCode.trim() === '') {
              this.req_trickerCode  = true;
              this.count++;
        } else {
                this.req_trickerCode = false;
        }
      }
      // validation for first notice date
      if (this.calendarDetailsList.firstNoticeDate  == null ) {
            this.req_firstNoticeDate  = true;
          this.count++;
      } else  {
                this.req_firstNoticeDate = false;
      }
     // validation for last notice date
      if (this.calendarDetailsList.lastNoticeDate  == null ) {
            this.req_lastNoticeDate  = true;
          this.count++;
      } else {
                this.req_lastNoticeDate = false;
      }
    // validation for first delivery date
      if (this.calendarDetailsList.firstDlvryDate  == null ) {
            this.req_firstDlvryDate  = true;
          this.count++;
      } else {
                this.req_firstDlvryDate = false;
      }
    //  validation for last delivery date
      if (this.calendarDetailsList.lastDlvryDate  == null ) {
            this.req_lastDlvryDate  = true;
          this.count++;
      } else {
                this.req_lastDlvryDate = false;
      }
     // validation for final settlement date
      if (this.calendarDetailsList.finalStlmntDate  == null ) {
            this.req_finalStlmntDate  = true;
          this.count++;
      } else  {
                this.req_finalStlmntDate = false;
      }
     //  validation for first trade date
      if (this.calendarDetailsList.firstTradeDate == null ) {
            this.req_firstTradeDate  = true;
          this.count++;
      } else {
                this.req_firstTradeDate = false;
      }
      // validation for last trade date
      if (this.calendarDetailsList.lastTradeDate  == null ) {
            this.req_lastTradeDate  = true;
          this.count++;
      } else {
                this.req_lastTradeDate = false;
      }
                 if (this.count !== 0) {
         this.showModal();
                setTimeout(() => {this.onHidden();  }, 3000)
        return false;
    } else {
        return true;
    }
}


    SpreadTypes(){
      // validation for ticker code
      if (this.calendarDetailsList.trickerCode == null ) {
            this.req_trickerCode  = true;
          this.count++;
      } else if (this.calendarDetailsList.trickerCode != null) {
            if (this.calendarDetailsList.trickerCode.trim() === '') {
              this.req_trickerCode  = true;
              this.count++;
        } else {
                this.req_trickerCode = false;
        }
      }
       // validation for spread month 2
        if (this.calendarDetailsList.spreadMonth2== null ||
        this.calendarDetailsList.spreadMonth2=== '') {
        this.req_spreadMonth2  =  true;
          this.count++;
        }
      else {
        this.req_spreadMonth2  =  false;
        }
        // validation for spread month1 
     if (this.calendarDetailsList.spreadMonth1== null ||
        this.calendarDetailsList.spreadMonth1=== '') {
        this.req_spreadMonth1  =  true;
          this.count++;
        }
      else {
        this.req_spreadMonth1  =  false;
        }
                 if (this.count !== 0) {
         this.showModal();
                setTimeout(() => {this.onHidden();  }, 3000)
        return false;
    } else {
        return true;
    }
    }

    FXTypes() {
       // validation for ticker code
      if (this.calendarDetailsList.trickerCode == null ) {
            this.req_trickerCode  = true;
          this.count++;
      } else if (this.calendarDetailsList.trickerCode != null) {
            if (this.calendarDetailsList.trickerCode.trim() === '') {
              this.req_trickerCode  = true;
              this.count++;
        } else {
                this.req_trickerCode = false;
        }
      }
      // validation for tenor id
      if (this.calendarDetailsList.tenorName  == null ) {
            this.req_tenorName  = true;
          this.count++;
      } else if (this.calendarDetailsList.tenorName  != null) {
            if (this.calendarDetailsList.tenorName.trim() === '') {
              this.req_tenorName  = true;
              this.count++;
        } else {
                this.req_tenorName = false;
        }
      }
       // validation for span
      if (this.calendarDetailsList.span  == null ) {
            this.req_span  = true;
          this.count++;
      } else if (this.calendarDetailsList.span  != null) {
            if (this.calendarDetailsList.span .trim() === '') {
              this.req_span  = true;
              this.count++;
        } else {
                this.req_span = false;
        }
      }
     // validation for duration type
      if (this.calendarDetailsList.durationType == null ) {
            this.req_durationType  = true;
          this.count++;
      } else if (this.calendarDetailsList.durationType != null) {
            if (this.calendarDetailsList.durationType.trim() === '') {
              this.req_durationType  = true;
              this.count++;
        } else {
                this.req_durationType = false;
        }
      }
         if (this.count !== 0) {
         this.showModal();
                setTimeout(() => {this.onHidden();  }, 3000)
        return false;
    } else {
        return true;
    }
    }

    // validations for Options Types
    OptionsTypes() {
      // validation for ticker code
      if (this.calendarDetailsList.trickerCode == null ) {
            this.req_trickerCode  = true;
          this.count++;
      } else if (this.calendarDetailsList.trickerCode != null) {
            if (this.calendarDetailsList.trickerCode.trim() === '') {
              this.req_trickerCode  = true;
              this.count++;
        } else {
                this.req_trickerCode = false;
        }
      }
      // validation for delivery month
        if (this.calendarDetailsList.dlvryMonth == null ||
        this.calendarDetailsList.dlvryMonth === '') {
        this.req_dlvryMonth  =  true;
          this.count++;
        }
      else {
        this.req_dlvryMonth  =  false;
        }
       // validation for futures calendar
      if (this.calendarDetailsList.futursCalendarName == null ) {
            this.req_futursCalendarName  = true;
          this.count++;
      } else if (this.calendarDetailsList.futursCalendarName != null) {
            if (this.calendarDetailsList.futursCalendarName.trim() === '') {
              this.req_futursCalendarName = true;
              this.count++;
        } else {
                this.req_futursCalendarName = false;
        }
      }
         if (this.count !== 0) {
         this.showModal();
                setTimeout(() => {this.onHidden();  }, 3000)
        return false;
    } else {
        return true;
    }
    }
}