import { Component, OnInit ,  ViewEncapsulation, ViewChild} from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Router } from '@angular/router';
import { listingPageTaxRate } from '../../../../shared/interface/router-links';
import { addTaxRate } from '../../../../shared/interface/router-links';
import { TaxRateService } from '../service/tax-rate.service';
import { Subscription } from 'rxjs/Subscription';
import { TaxRateInterface } from '../tax-rate-interface';
import { TaxRateNagotiationTerm } from '../tax-rate-interface';
import { ConfirmationService } from '../../../../shared/confirm-box/confirm.service';
import { MESSAGE_SUCCESS, MESSAGE_ERROR, ACTION_SAVE } from '../../../../shared/interface/common.constants';
import { ACTION_SUBMIT, ACTION_UPDATE, ACTION_ACTIVE, ACTION_DEACTIVE,ACTION_INACTIVE,ACTION_DRAFT } from '../../../../shared/interface/common.constants';
import { MessageModel } from '../../../../shared/message/message.model';

@Component({
  selector: 'app-view-tax-rate',
  templateUrl: './view-tax-rate.component.html',
  styleUrls: ['./view-tax-rate.component.css'],
  encapsulation:  ViewEncapsulation.None
})
export class ViewTaxRateComponent implements OnInit { 	

isDeletePopupModal: Boolean = false;
public isCannotDeletePopup;
subscription: Subscription;
isDeactivatePopupModal: Boolean = false;
public isupdateModal;
public inactive;
public active;
public draft;
public statusName;
public code;
public name;
public taxName;
public description;
public countryName;
public govtTaxRef;
public iseditModal;
public taxByLine;
public pageTitle;
public arrayList = [];
public taxRateViewByIdModel: TaxRateInterface = new TaxRateInterface();
viewtaxRatedetails : TaxRateInterface = new TaxRateInterface();
data: TaxRateInterface[];
dataView: TaxRateInterface[];
public savedData;
public currentStatus;
public isShowModal;
public errorMessage;
messages: MessageModel;
showMessage: any;
 public status: any = {
    isFirstOpen: true,
    isFirstDisabled: false
  };
  
  constructor(private route: ActivatedRoute, http: Http, private router: Router, private taxRateService: TaxRateService, private confirmationService: ConfirmationService) { }

  ngOnInit() {	  
	  
	  window.scrollTo(0, 0);    
      
      this.isCannotDeletePopup = false;
      this.isDeletePopupModal = false;
	  this.isDeactivatePopupModal = false;
      this.pageTitle = 'View Tax Rate Details';
      this.taxRateViewByIdModel = this.taxRateService.viewbyIdParam;
   this.taxRateService.paramPassingId    = this.taxRateViewByIdModel.taxCodeUID;
    this.subscription = this.taxRateService.getSelectedTaxRateByID ()
     .subscribe(addTaxRate => {
      this.taxRateService.viewtaxRatedetails = addTaxRate.body;
      this.code = this.taxRateService.viewtaxRatedetails.taxCode;
      this.name = this.taxRateService.viewtaxRatedetails.taxName;
      this.description = this.taxRateService.viewtaxRatedetails.taxDescription;
      this.countryName = this.taxRateService.viewtaxRatedetails.taxCountryName;
      this.govtTaxRef = this.taxRateService.viewtaxRatedetails.govTaxRef;
      this.taxByLine = this.taxRateService.viewtaxRatedetails.taxByLine;
      this.statusName = this.taxRateService.viewtaxRatedetails.status.toUpperCase();
       if (this.statusName === ACTION_DRAFT) {
        this.draft = true;
        this.active = false;
        this.inactive = false;
      }else if (this.statusName === ACTION_ACTIVE) {
        this.active = true;
        this.draft = false;
        this.inactive = false;
      }else if (this.statusName === ACTION_INACTIVE) {
        this.inactive = true;
        this.active = false;
        this.draft = false;
      }
       
        for (let i = 0 ; i < this.taxRateService.viewtaxRatedetails.taxRateDetails.length; i++) {
         const d = new Date(this.taxRateService.viewtaxRatedetails.taxRateDetails[i].effectiveFrom);
         let d1;
         if(this.taxRateService.viewtaxRatedetails.taxRateDetails[i].expirationDate){
            d1 = new Date(this.taxRateService.viewtaxRatedetails.taxRateDetails[i].expirationDate);
            d1 = d1.getDate() + '/' + (d1.getMonth()+1) + '/' + d1.getFullYear();
         }
         this.arrayList.push({
            effectiveFrom : d.getDate() + '/' + (d.getMonth()+1) + '/' + d.getFullYear(),
            expirationDate : d1,
            taxRatePercentage : this.taxRateService.viewtaxRatedetails.taxRateDetails[i].taxRatePercentage
         });
                                  
     }


     },
      error => {throw(error)},
      () => console.log('Finished')
    );
   
      if (this.statusName === ACTION_DRAFT) {
        this.draft = true;
        this.active = false;
        this.inactive = false;
      }else if (this.statusName === ACTION_ACTIVE) {
        this.active = true;
        this.draft = false;
        this.inactive = false;
      }else if (this.statusName === ACTION_INACTIVE) {
        this.inactive = true;
        this.active = false;
        this.draft = false;
  }
	  
	 
  }
  
  closedeletepopup() {
   this.isDeletePopupModal = false;
   this.isCannotDeletePopup = false;
} 
  
  backtoPrev() {
	 this.router.navigate([listingPageTaxRate]);
}


 close() {
    this.iseditModal = false;
  }
  
  enableEdit(event){
	   this.taxRateService.paramPassingId=event.taxCodeUID;
      this.subscription = this.taxRateService.getSelectedTaxRateByID().subscribe(taxRateResponse => {  
       this.taxRateService.taxRatePaymentdetails = taxRateResponse.body;
       this.taxRateService.selectTaxRateEdit = taxRateResponse.body.taxRateDetails;
      this.router.navigate([addTaxRate], { queryParams: { 'elem': 6 } });
     },
       error => { throw error });
  }

  updatewithStatus(currentStatus: String) {
    
    this.currentStatus = currentStatus
    this.isDeletePopupModal = true;
    if (currentStatus.toUpperCase() === ACTION_ACTIVE) {
     
	  this.confirmationService.confirm({
        message: 'Confirm.Common.Reactivate',
        placeHolder: [this.taxRateViewByIdModel.taxName],
        accept: (event) => {
            this.conf_delete();
        },
        reject: (event) => {
        }
    });
	
    } else if (currentStatus.toUpperCase() === ACTION_INACTIVE) {
      
	   this.confirmationService.confirm({
        message: 'Confirm.Common.Deactivate',
        placeHolder: [this.taxRateViewByIdModel.taxName],
        accept: (event) => {
            this.conf_delete();
        },
        reject: (event) => {
        }
    });
    } else if (currentStatus.toUpperCase() === ACTION_DRAFT) {
        this.confirmationService.confirm({
        message: 'Confirm.Common.Delete',
        placeHolder: [this.taxRateViewByIdModel.taxName],
        accept: (event) => {
            this.conf_delete();
        },
        reject: (event) => {
        }
    });
    } 
  }  
   perfromupdate() {
    this.taxRateViewByIdModel = this.taxRateService.viewbyIdParam
    
 
    this.taxRateViewByIdModel.taxCode ="0";
    this.subscription = this.taxRateService.updateTaxRates(this.taxRateViewByIdModel.taxCodeUID).subscribe(savedData => {
      // for suceess toaster
	  if ((this.taxRateViewByIdModel.status).toUpperCase() === ACTION_ACTIVE) {					  
		this.showMessage = { severity: MESSAGE_SUCCESS, summary: 'Message.Common.Deactivate',
         placeHolder: ['Tax Rate Name', this.taxRateViewByIdModel.taxName] };
        } else if ((this.taxRateViewByIdModel.status).toUpperCase() === ACTION_INACTIVE) {			
         this.showMessage = { severity: MESSAGE_SUCCESS, summary: 'Message.Common.Reactivate',
         placeHolder: ['Tax Rate Name', this.taxRateViewByIdModel.taxName] };
        } else if ((this.taxRateViewByIdModel.status).toUpperCase() === ACTION_DRAFT) {
          this.showMessage = { severity: MESSAGE_SUCCESS, summary: 'Message.Common.Delete',
         placeHolder: ['Tax Rate Name', this.taxRateViewByIdModel.taxName] };
        }
	   //  
	  
	  this.router.navigate([listingPageTaxRate]);

    },
      error => {throw(error)},
      () => console.log('Finished')
    );
  }
  
  conf_delete() {
   // this.successModal.hide();
    if ((this.currentStatus).toUpperCase() === ACTION_DRAFT) {
	  const param = this.taxRateViewByIdModel.taxCodeUID;
      this.subscription = this.taxRateService.deletetaxRateById
        (param)
        .subscribe(deletedStatus => {
			this.showMessage = { severity: MESSAGE_SUCCESS, summary: 'Message.Common.Delete',
			placeHolder: ['Tax Rate Name', this.taxRateViewByIdModel.taxName] };			
          
          this.router.navigate([listingPageTaxRate], {
            queryParams:
            { 'delSuccess': 5, taxName: this.taxRateViewByIdModel.taxName }
          });
        },
        error => { throw error });
    } else {
       this.perfromupdate();
    }
  }
  
  

   onHiddenpopup() {
    this.isDeletePopupModal = false;
    this.isDeactivatePopupModal = false;
    this.isupdateModal = false;
  }
}
