import { Component, OnInit, OnDestroy,ViewEncapsulation, Output , ViewChild } from '@angular/core';
import { listingPageTaxRate } from '../../../../shared/interface/router-links';
import { TaxRateInterface } from '../tax-rate-interface';
import { TaxRateNagotiationTerm } from '../tax-rate-interface';
import { FormGroup, FormControl , FormArray,   FormBuilder, ReactiveFormsModule , FormsModule, Validators} from '@angular/forms';
import { Router , ActivatedRoute} from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { MasterSetupService } from '../../../master-setup/service/master-setup.service';
import { SelectItem } from '../../../../shared/interface/selectItem';
import { TaxRateService } from '../service/tax-rate.service';
import { CanComponentDeactivate } from '../../../../shared/guards/can-deactivate-guard';
import { AccordionModule , ModalDirective } from 'ngx-bootstrap';
import { AddMultiTaxRateComponent} from './multi-add-tax-rate.component';
//import {LocationService }  from '../../../geo-location/location/service/location.service';
import { ConfirmationService } from '../../../../shared/confirm-box/confirm.service';
import { MESSAGE_SUCCESS, MESSAGE_ERROR, ACTION_SAVE } from '../../../../shared/interface/common.constants';
import { ACTION_SUBMIT, ACTION_UPDATE, ACTION_ACTIVE, ACTION_DEACTIVE,ACTION_INACTIVE,ACTION_DRAFT } from '../../../../shared/interface/common.constants';
import { MessageModel } from '../../../../shared/message/message.model';
 @Component({
  selector: 'app-add-tax-rate',
  templateUrl: './add-tax-rate.component.html',
  styleUrls: ['./add-tax-rate.component.css'],
    encapsulation: ViewEncapsulation.None,
})
export class AddTaxRateComponent implements OnInit, OnDestroy, CanComponentDeactivate  {
  @ViewChild('successModal') public successModal: ModalDirective;
  @ViewChild('deletesuccessModal') public deletesuccessModal: ModalDirective;
  taxRateForm:FormGroup;
  public errormodal: boolean;
  public currentStatus;
  deactivateSuccessDialog: string;
  financialCalendarModel: TaxRateNagotiationTerm[];
  ispagebackPopupModal: boolean;
  taxBylineardio:string;
  sizeTaxRateMapping:number;
  isActionPerformed = false;
  public isupdateModal;
  req_taxCode: boolean;
  req_taxName:boolean;
  req_taxDescription:boolean;
  req_taxCountryID:boolean;
  req_govtTaxRef:boolean;
  req_taxByLine:boolean;
  req_fromDate:boolean;
  req_expiryDate:boolean;
  req_taxRate:boolean;
  taxCountryDropdownList:string;
  subscription: Subscription;
  pageTitle: String;
  public pageName;
   countryList: SelectItem [] = [] ;
  //countryList :any;
  count:number;
  isError: boolean;
  public isCannotDeletePopup;
  isDeactivatePopupModal: Boolean = false;
  isDeletePopupModal: Boolean = false;
  taxRateCheck : TaxRateInterface = new TaxRateInterface();
  taxRateDetailsList:TaxRateInterface= new TaxRateInterface();
  showPlusButton:boolean;
  showCrossButton:boolean;
  cntPlusButton:number;
  whenDataEdited: boolean;
  whenStatusactive: boolean;
  whenDataNotTobeEdited: boolean;
  whenDataTobeEdited: boolean;
  whenStatusInactive: boolean;
  flag:boolean;
  testResponse:any;
  cntAddedTaxDetails:number;
  messages: MessageModel;	
  public status: any = {
    isFirstOpen: true,
    isFirstDisabled: false
  };
  constructor(private router: Router, private route: ActivatedRoute,
    private formBuilder:  FormBuilder, public taxRateService: TaxRateService, private confirmationService: ConfirmationService) { 
    this.whenDataNotTobeEdited = true;
    this.whenDataTobeEdited = false;
    }

  ngOnInit() {
     this.isCannotDeletePopup = false;
     this.isDeletePopupModal = false;
	  this.isDeactivatePopupModal = false;
    this.taxBylineardio = 'N';
    this.errormodal = false;
    this.showPlusButton = true;
    this.showCrossButton = false;
    this.cntPlusButton = 1;
    this.cntAddedTaxDetails = 0;
    this.flag = true;
 this.getLocationCountryName();
    this.isError = false;   
    this.route.queryParams.subscribe(params => {
        // Defaults to 0 if no query param provided.
        this.pageName = +params['elem'];      
    });
   this.pageTitle = "Add Tax Rate";
   if (this.pageName == 6) {
      this.pageTitle = 'Edit Tax Rate ';
      this.buildUSERROLEForm();
     this.getTaxRateDetails();     
    } else {
      this.taxBylineardio = 'N';    
      this.buildUSERROLEForm();
      this.addPermissionMapping();
    } 
  
  }
  getLocationCountryName(){
   this.countryList = [];
     this.subscription = this.taxRateService.loactionTaxCountryname().subscribe(TaxCountryName => {
       this.countryList = TaxCountryName.body;
       console.log(this.countryList);
     },
      error =>  {throw (error)},
      () => console.log('Finished')
      );
  }
  getTaxRateDetails(){
      let editDetails:any;
      editDetails = this.taxRateService.taxRatePaymentdetails;
      if(typeof editDetails == 'undefined'){
        this.router.navigate([listingPageTaxRate]);
      }
      this.taxRateDetailsList.taxCodeUID = editDetails.taxCodeUID;
      this.taxRateDetailsList.taxCode = editDetails.taxCode;      
      this.taxRateDetailsList.taxName = editDetails.taxName;


      this.taxRateDetailsList.taxDescription= editDetails.taxDescription;
      this.taxBylineardio = editDetails.taxByLine;
      this.taxRateDetailsList.taxCountryID = editDetails.taxCountryID;
      this.taxRateDetailsList.govTaxRef= editDetails.govTaxRef;
      this.taxRateDetailsList.taxRateDetails = editDetails.taxRateDetails;
      this.taxRateDetailsList.status = editDetails.status.toUpperCase();
      if (this.taxRateDetailsList.status == ACTION_DRAFT) {
        this.whenDataNotTobeEdited = false;
        this.whenDataEdited = true;
        this.whenStatusInactive = false;
        this.whenStatusactive = false;
      }else if (this.taxRateDetailsList.status == ACTION_ACTIVE) {
        this.whenStatusactive = true;
        this.whenStatusInactive = false;
        this.whenDataEdited = false;
        this.whenDataNotTobeEdited = false;
      }else if (this.taxRateDetailsList.status == ACTION_INACTIVE) {
        this.whenStatusInactive = true;
        this.whenDataEdited = false;
        this.whenStatusactive = false;
        this.whenDataNotTobeEdited = false;
      }
  this.financialCalendarModel = this.taxRateDetailsList.taxRateDetails;
 if (this.taxRateDetailsList.taxRateDetails.length >= 1 ) {
     for (let i = 0; i <  this.taxRateDetailsList.taxRateDetails.length ; i++) {
      this.addPermissionMapping();
     }
 }     
    
  }
// for success toaster
  conf_delete() {
    
    if ((this.currentStatus).toUpperCase() === ACTION_DRAFT) {
	  const param = this.taxRateDetailsList.taxCodeUID;
      this.subscription = this.taxRateService.deletetaxRateById
        (param)
        .subscribe(deletedStatus => {
			this.taxRateService.messages = { severity: MESSAGE_SUCCESS, summary: 'Message.Common.Delete',
			placeHolder: ['Tax Rate Name', this.taxRateDetailsList.taxName] };
			
          //const statusCode = this.getStatusCode(deletedStatus);
          this.router.navigate([listingPageTaxRate], {
            queryParams:
            { 'delSuccess': 5, taxName: this.taxRateDetailsList.taxName }
          });
        },
        error => { throw error });
    } else {
       this.perfromupdate();
    }
  }

// for success toaster
  perfromupdate() {
    this.taxRateService.viewbyIdParam = this.taxRateDetailsList.taxCodeUID;
   
    this.subscription = this.taxRateService.updateTaxRates(this.taxRateDetailsList.taxCodeUID).subscribe(savedData => {
		 // for suceess toaster
	  if ((this.taxRateDetailsList.status).toUpperCase() === ACTION_ACTIVE) {					  
		this.taxRateService.messages = { severity: MESSAGE_SUCCESS, summary: 'Message.Common.Deactivate',
         placeHolder: ['Tax Rate Name', this.taxRateDetailsList.taxName] };
        } else if ((this.taxRateDetailsList.status).toUpperCase() === ACTION_INACTIVE) {			
         this.taxRateService.messages = { severity: MESSAGE_SUCCESS, summary: 'Message.Common.Reactivate',
         placeHolder: ['Tax Rate Name', this.taxRateDetailsList.taxName] };
        } else if ((this.taxRateDetailsList.status).toUpperCase() === ACTION_DRAFT) {
          this.taxRateService.messages = { severity: MESSAGE_SUCCESS, summary: 'Message.Common.Delete',
         placeHolder: ['Tax Rate Name', this.taxRateDetailsList.taxName] };
        }
	   //  
		
      this.router.navigate([listingPageTaxRate], {
        queryParams:
        { 'isUpdate': 1, taxName: this.taxRateDetailsList.taxName,
        currentStatus: this.taxRateDetailsList.status }
      });

    },
      error => {throw (error)},
      () => console.log('Finished')
    );
  }
  
  

  addPermissionMapping() {
        const control = <FormArray>this.taxRateForm.controls['taxRateDetails'];
        const addrCtrl =   this.formBuilder.group({
           'effectiveFrom':[],
            'expirationDate': [],
            'taxRatePercentage': [],
           });
      control.push(addrCtrl);
      this.cntPlusButton = control.length;
      if(control.length > 1){
        this.showPlusButton = false;
        this.showCrossButton = true;
      }else{
        this.showPlusButton = true;
        this.showCrossButton = false;
      }
      this.financialCalendarModel = this.taxRateDetailsList.taxRateDetails;
  }
 removePermissionMapping(i: number) {
      const control = <FormArray>this.taxRateForm.controls['taxRateDetails'];
        control.removeAt(i);
         this.cntPlusButton--;
        if(this.taxRateForm.value.taxRateDetails.length > 1){
          this.showCrossButton = true;
        }else{
          this.showCrossButton = false;
        }
    }
   buildUSERROLEForm(): void {
     this.taxRateForm = this.formBuilder.group({
      taxCode: '' , 
      taxName: '',
      taxDescription: '',
      taxCountryID: '',
      govTaxRef: '',
      taxByLine: '',
      taxRateDetails:this.formBuilder.array([])
    });

    }

  getTaxRateArray(taxRateForm) {
      return taxRateForm.get('taxRateDetails').controls;
  }

    checkAddedTaxDetailsCnt():number{
      this.cntAddedTaxDetails = 0;
      for (let i = 0; i <  this.taxRateForm.value.taxRateDetails.length ; i++) {
                  for (let j = i + 1 ; j <=  this.taxRateForm.value.taxRateDetails.length ; j++) {
                      if (this.taxRateForm.value.taxRateDetails[i].effectiveFrom !=
                              null  &&
                              this.taxRateForm.value.taxRateDetails[i].taxRatePercentage != null) {
                                this.cntAddedTaxDetails++;
                      }
            }
          }
          return this.cntAddedTaxDetails;
    }

 

  isValidForm(): boolean {
      this.count = 0;
      const sizeTaxRateMapping=Number(JSON.stringify(this.taxRateForm.value.taxRateDetails.length));

      //validation for tax code
      if (this.taxRateForm.value.taxCode == null ) {
            this.req_taxCode  = true;
          this.count++;
      } else if (this.taxRateForm.value.taxCode != null) {
            if (this.taxRateForm.value.taxCode.trim() === '') {
              this.req_taxCode  = true;
              this.count++;
        } else {
                this.req_taxCode = false;
        }
      }
//validation for tax Name
        if (this.taxRateForm.value.taxName == null ) {
            this.req_taxName  = true;
          this.count++;
      } else if (this.taxRateForm.value.taxName != null) {
            if (this.taxRateForm.value.taxName.trim() === '' ) {
              this.req_taxName = true;
              this.count++;
        } else {
                this.req_taxName = false;
        }
      }

//validation for tax govTaxRef
       if (this.taxRateForm.value.govTaxRef == null ) {
            this.req_govtTaxRef  = true;
          this.count++;
      } else if (this.taxRateForm.value.govTaxRef != null) {
            if (this.taxRateForm.value.govTaxRef.trim() === '' ) {
              this.req_govtTaxRef = true;
              this.count++;
        } else {
                this.req_govtTaxRef = false;
        }
      }
//validation for tax country name
       if (this.taxRateForm.value.taxCountryID == null ) {
            this.req_taxCountryID = true;
          this.count++;
      } else if (this.taxRateForm.value.taxCountryID != null) {
            if (this.taxRateForm.value.taxCountryID === '' ) {
              this.req_taxCountryID= true;
              this.count++;
        } else {
                this.req_taxCountryID = false;
        }
      }
// validation for tax by line
       if (this.taxRateForm.value.taxByLine == null ) {
       this.req_taxByLine  = true;
       this.count++;

       } else if (this.taxRateForm.value.taxByLine != null) {
          if (this.taxRateForm.value.taxByLine.trim() === '' ) {
            this.req_taxByLine = true;
            this.count++;
      } else {
              this.req_taxByLine = false;
      }
    }
    
     if(sizeTaxRateMapping >=1){
   for (let i = 0; i < sizeTaxRateMapping; i++) {
        if (this.taxRateForm.value.taxRateDetails[i] == null) {
          this.count++;
      } else if (this.taxRateForm.value.taxRateDetails[i] != null) {
       if (this.taxRateForm.value.taxRateDetails[i].taxRatePercentage== null ||
          this.taxRateForm.value.taxRateDetails[i].taxRatePercentage === '') { 
            
        this.req_taxRate = true;
            this.count++;
      }
       if (this.taxRateForm.value.taxRateDetails[i].effectiveFrom == null ||
         this.taxRateForm.value.taxRateDetails[i].effectiveFrom === '') {
         this.req_fromDate = true;
           this.count++;
        }
      }
      }
     }
   
 this.taxRateCheck = this.taxRateForm.value;

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

  public hideModal(): void {
  this.deletesuccessModal.hide();
  }
 
  public onHidden(): void {
    this.errormodal = false;
  }
  saveTaxRate(status){
     this.isActionPerformed = true;
    if(this.isValidForm()){
      this.taxRateDetailsList.taxCode= this.taxRateForm.value.taxCode;
      this.taxRateDetailsList.taxName = this.taxRateForm.value.taxName;
      this.taxRateDetailsList.taxDescription= this.taxRateForm.value.taxDescription;
      this.taxRateDetailsList.taxByLine= this.taxRateForm.value.taxByLine;
      this.taxRateDetailsList.taxCountryID =this.taxRateForm.value.taxCountryID;
      this.taxRateDetailsList.govTaxRef=this.taxRateForm.value.govTaxRef;
       this.taxRateDetailsList.taxRateDetails = this.taxRateForm.value.taxRateDetails;
       this.taxRateDetailsList.status = status;
       this.subscription = this.taxRateService.saveTaxRateDetails(this.taxRateDetailsList).subscribe(addTaxRateDetail => {
		   this.taxRateService.messages = { severity: MESSAGE_SUCCESS, summary: 'Message.Common.Save',
			placeHolder: ['Tax Rate Name', this.taxRateDetailsList.taxName] };
       this.testResponse=addTaxRateDetail.body;
     this.router.navigate([listingPageTaxRate], { queryParams: { 'success': 1 } });
      },
        error => {
          this.ispagebackPopupModal = true;

        }, 
        () => console.log('Finished')
      );
      
    
    }
   
  }

  update(status){
    this.isActionPerformed = true;
    if(this.isValidForm()){
      this.taxRateDetailsList.taxCodeUID= this.taxRateDetailsList.taxCodeUID;
      this.taxRateDetailsList.taxCode= this.taxRateForm.value.taxCode;
      this.taxRateDetailsList.taxName = this.taxRateForm.value.taxName;
      this.taxRateDetailsList.taxDescription= this.taxRateForm.value.taxDescription;
      this.taxRateDetailsList.taxByLine= this.taxRateForm.value.taxByLine;
      this.taxRateDetailsList.taxCountryID =this.taxRateForm.value.taxCountryID;
      this.taxRateDetailsList.govTaxRef=this.taxRateForm.value.govTaxRef;
       this.taxRateDetailsList.taxRateDetails = this.taxRateForm.value.taxRateDetails;
       if(status !== ''){
          this.taxRateDetailsList.status = status;
       }
      this.subscription = this.taxRateService.updateTaxRateDetails(this.taxRateDetailsList).subscribe(updateTaxRateDetail => {
		  this.taxRateService.messages = { severity: MESSAGE_SUCCESS, summary: 'Message.Common.Update',
			placeHolder: ['Tax Rate Name', this.taxRateDetailsList.taxName] };
       this.router.navigate([listingPageTaxRate], { queryParams: { 'success': 9 } });
       },
       error => {     
        this.ispagebackPopupModal = true;
     }, 
      
     )  
      
    }
  }
// for confirmatiom box
  updateStatus(currentStatus: String){
      this.currentStatus = currentStatus;
    this.isDeletePopupModal = true;
    /*if (currentStatus === 'Active') {
      this.deactivateSuccessDialog = 'Please Confirm to Activate ' + this.taxRateDetailsList.taxName ;
    } else if (currentStatus === 'InActive') {
      this.deactivateSuccessDialog = 'Please Confirm to DeActivate '  + this.taxRateDetailsList.taxName ;
    } else if (currentStatus === 'Draft') {
      this.deactivateSuccessDialog = 'Please Confirm to Delete '  + this.taxRateDetailsList.taxName ;
    } else if (currentStatus === 'AcitvateDraft') {
      this.deactivateSuccessDialog = 'Please Confirm to Activate '  + this.taxRateDetailsList.taxName ;
    }*/
	
	this.currentStatus = currentStatus
    this.isDeletePopupModal = true;
    if (currentStatus.toUpperCase() === ACTION_ACTIVE) {
     
	  this.confirmationService.confirm({
        message: 'Confirm.Common.Reactivate',
        placeHolder: [this.taxRateDetailsList.taxName],
        accept: (event) => {
            this.conf_delete();
        },
        reject: (event) => {
        }
    });
	
    } else if (currentStatus.toUpperCase() === ACTION_INACTIVE) {
      
	   this.confirmationService.confirm({
        message: 'Confirm.Common.Deactivate',
        placeHolder: [this.taxRateDetailsList.taxName],
        accept: (event) => {
            this.conf_delete();
        },
        reject: (event) => {
        }
    });
    } else if (currentStatus.toUpperCase() === ACTION_DRAFT) {
        this.confirmationService.confirm({
        message: 'Confirm.Common.Delete',
        placeHolder: [this.taxRateDetailsList.taxName],
        accept: (event) => {
            this.conf_delete();
        },
        reject: (event) => {
        }
    });
    } 
  }

 onHiddenpopup() {
    this.isDeletePopupModal = false;
    this.isDeactivatePopupModal = false;
    this.isupdateModal = false;
  }
  editTaxRateListDetails() {
    
      if (this.taxRateDetailsList.status == 'Draft') {
        this.whenDataNotTobeEdited = false;
        this.whenDataEdited = true;
        this.whenStatusInactive = false;
        this.whenStatusactive = false;
      }else if (this.taxRateDetailsList.status  == 'Active') {
        this.whenStatusactive = true;
        this.whenStatusInactive = false;
        this.whenDataEdited = false;
        this.whenDataNotTobeEdited = false;
      }else if (this.taxRateDetailsList.status  == 'InActive') {
        this.whenStatusInactive = true;
        this.whenDataEdited = false;
        this.whenStatusactive = false;
        this.whenDataNotTobeEdited = false;
      }
 const sizerollemapping = Number(JSON.stringify(this.taxRateService.selectTaxRateEdit.length));

 }

 closedeletepopup() {
   this.isDeletePopupModal = false;
   this.isCannotDeletePopup = false;
} 


  ngOnDestroy() { 
    //if(this.subscription != null)
		  // this.subscription.unsubscribe();
  }
  cancel(){
    this.router.navigate([listingPageTaxRate]);
  }
  close(){
    this.ispagebackPopupModal = false;
  }
   canDeactivate(): boolean {
      return this.isActionPerformed;
  }
 
}
