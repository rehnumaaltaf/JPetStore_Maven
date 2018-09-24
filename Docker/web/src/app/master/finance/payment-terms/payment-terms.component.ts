import { Component, NgModule, OnInit, ViewChild, OnDestroy, Output, Input , ViewEncapsulation } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { addpaymentterm } from '../../../shared/interface/router-links';
import { PaymentTerm } from './payment-terms.interface';
import {PaymentTermsService} from './service/payment-terms.service';
import { CommonModule, PlatformLocation, Location} from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';
import { FormGroup ,  FormControl,  Validators,  FormBuilder, ReactiveFormsModule , FormsModule} from '@angular/forms';
import { Subscription } from 'rxjs/Subscription';
import { MasterSetupService } from './../../master-setup/service/master-setup.service';
import { ExcelExportData } from '@progress/kendo-angular-excel-export';
import { GridComponent, GridDataResult, DataStateChangeEvent, PageChangeEvent} from '@progress/kendo-angular-grid';
import { process, SortDescriptor, orderBy, GroupDescriptor, State } from '@progress/kendo-data-query';
import { viewpaymentterm } from '../../../shared/interface/router-links';
import {  ModalDirective } from 'ngx-bootstrap';

@Component({
  selector: 'app-payment-terms',
  templateUrl: './payment-terms.component.html',
  styleUrls: ['./payment-terms.component.css'],
  encapsulation: ViewEncapsulation.None,
})
export class PaymentTermsComponent implements OnInit, OnDestroy {
 @ViewChild('successModal') public successModal: ModalDirective;
  group: GroupDescriptor[] = [{ field: 'statusName' }];
  paymentTermsDetails: PaymentTerm[];
  paymentTerm: PaymentTerm;
  subscription: Subscription;
  multiple: Boolean = false;
  gridView: GridDataResult;
  public paymentTermId :number;
  public payTermCode : string;
  paytemname: string;
  showHide: boolean;
  public isShowModal: boolean;
  public success: number;
  isNavbtwComponent: boolean;
  allowUnsort: Boolean = true;
  addoreditpageflag: boolean;
  addoreditpagemsg: string;
  sort: SortDescriptor[] = [];
  isComplete: Boolean = false;
  isDeletePopupModal: Boolean = false;
  isDeactivatePopupModal: Boolean = false;
  isActiveStatus: Boolean = false;
  isDraftStatus: Boolean = false;
  deleteSuccessModal: Boolean = false;
  isCancelUpdatePopupModal: Boolean = false;
  status: String;
  isReactivatePopupModal: boolean;
  errorMessageForPermission: String;
  isError: Boolean;
  @Output() loadingData: Boolean = true;
  statusmsg: string;
  state: State = {
        skip: 0,
        take: 1000
    };
  gridData: GridDataResult;

  constructor(private route: ActivatedRoute, private router: Router, private paymentTermsService: PaymentTermsService,
              private masterSetupService: MasterSetupService , platformlocation: PlatformLocation, location: Location) { 
            this.showHide = true;
            this.success = 0;
            this.allData = this.allData.bind(this);
     platformlocation.onPopState(() => {
         this.isNavbtwComponent = true;

        });
  }
  
  ngOnInit() {
    window.scrollTo(0, 0);
    this.success = 0;
   this.loadingPaymentTermsList();
    console.log('permission-group loading');
    this.route.queryParams.subscribe(params => {
        // Defaults to 0 if no query param provided.
        this.success = + params['success'];
        if (this.success === 1) {
           this.showModalshowmodel();
           this.statusmsg = ' Draft'
          setTimeout(() => {this.hideModalshowmodel(); } , 3000);
        }
        if (this.success === 2) {
           this.showModalshowmodel();
          this.statusmsg = ' Active'
          setTimeout(() => {this.hideModalshowmodel(); } , 3000);
        }
        if (this.success === 3) {
           this.showModalshowmodel();
           this.statusmsg = ' InActive'
          setTimeout(() => {this.hideModalshowmodel(); } , 3000);
        }
      });
   }

 public showModalshowmodel(): void {
    this.isShowModal = true;
  }

  public hideModalshowmodel(): void {
    this.successModal.hide();
  }
 
  public onHiddenshowmodel(): void {
    this.isShowModal = false;
  }


loadingPaymentTermsList() {
    this.paytemname = this.paymentTermsService.paymenttermName;
    this.addoreditpageflag = this.paymentTermsService.addoreditpageflag;
    console.log(' addoreditpageflag ' + this.addoreditpageflag);
    if (this.addoreditpageflag) {
        this.addoreditpagemsg = 'Added';
    } else {
        this.addoreditpagemsg = 'Updated';
    }

    this.subscription = this.paymentTermsService.getPaymentTermsViewList()
    .subscribe(paymentTermsDetails => {
    this.paymentTermsService.addPayment(<PaymentTerm[]>paymentTermsDetails.body);
    this.loadingData = false;

    /*
    this.masterSetupService.addChildObjects(this.masters.links);
       this.link = this.masterSetupService.getChildObject('View PermissionGroup.GET');
    */

    }, error => {
      console.log('Error Loading Payment Listing: ' + <any>error)
      this.loadingData = false;
      //throw error;
      // this.notificationService.addNotications(error);
    });
  }

redirecttoadd() {
    this.paymentTermsService.paymentTerm = null;
     this.router.navigate([addpaymentterm]);
   }
  OnDestroy() {
    // prevent memory leak when component destroyed
    this.subscription.unsubscribe();
  }


    ngOnDestroy() {
     this.subscription.unsubscribe();
   }

   checkAllClicked($event) {
    console.log('checkAllClicked ', $event);
    /*this.event.forEach(x => x.state = ev.target.checked)*/
  }

   sortChange(sort: SortDescriptor[]): void {
        this.sort = sort;
        this.loadingPaymentTermsList();
    }

  modalPopup(event) {
    // alert('popup');

  }

 protected dataStateChange(state: DataStateChangeEvent): void {
         this.state = state;
         this.gridData = process(this.paymentTermsService.paymentTermsDetails, this.state);
    }


   pageChange(event: PageChangeEvent): void {
        this.state.skip = event.skip;
        this.loadingPaymentTermsList();
    }
  
  viewById(id) {
    this.subscription = this.paymentTermsService.getPaymentTermsById(id).subscribe(paymentTermsDetails => {
    this.paymentTermsService.paymentTerm = paymentTermsDetails.body;
       this.router.navigate([viewpaymentterm]);
       },
         error => {
           this.errorMessageForPermission = error ;
            this.isDeletePopupModal = false;
             this.isError = true;
             throw error;
        }

    );
   
   }

    closedeletepopup()  {
    this.hideIsErrorModal();
    // this.isDeactivatePopupModal = false;
   }

   clickToClosePop() {
   this.hideIsErrorModal();
}

 allData(): ExcelExportData {
    const result: ExcelExportData = {
    data: process(this.paymentTermsService.paymentTermsDetails, this.state).data,
      group: this.group
    };

    return result;
  }
payment_delete( id , name , status ) {
    this.paymentTermId = id;
    this.paymentTermsService.paymenttermName = name;
    this.status = status;
    if (this.status === 'Draft') {
      this.isDraftStatus = true;
    }else if (this.status === 'Active') {
      this.isActiveStatus = true;
    }
    this.showisDeletePopupModal();
    return false;
  }
  conf_delete(event) {
     const param = {'id': this.paymentTermId};
     const otherarray = [];
     otherarray.push(param);
     const paramobj = { 'paymentTermIds' : [] };
        this.hideIsErrorModal();
     paramobj.paymentTermIds = otherarray;
     this.subscription = this.paymentTermsService.deleteGroupById(this.paymentTermId, this.status).subscribe(deletedStatus => {
          this.hideIsErrorModal();
          this.deleteSuccessModal = true;
          if (this.status === 'Draft') {
             this.isDraftStatus = true;
          }else if (this.status === 'Active') {
             this.isActiveStatus = true;
          }
          setTimeout(() => {this.deleteSuccessModal = false;  }, 3000);
          this.loadingPaymentTermsList();

       },
         error => {
           this.errorMessageForPermission = error ;
            this.showIsError();
             throw error;
        }

    );
}

  public showisDeletePopupModal(): void {
    this.isDeletePopupModal = true;
  }

  public onHiddenisDeletePopupModal(): void {
    this.isDeletePopupModal = false;
  }

   public hideIsErrorModal(): void {
    this.successModal.hide();
  }

    public showIsError(): void {
    this.isError = true;
  }

    public onHiddenIsError(): void {
    this.isError = false;
  }
}
