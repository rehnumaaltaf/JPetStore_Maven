import { Component, OnInit } from '@angular/core';
import { AccordionModule } from 'ngx-bootstrap';
import { ActivatedRoute } from '@angular/router';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import {PaymentTermsService} from '../service/payment-terms.service';
import { PaymentTerm } from '../payment-terms.interface';
import { FormGroup ,  FormControl,  Validators, FormArray,   FormBuilder, ReactiveFormsModule , FormsModule} from '@angular/forms';
import { viewpaymentterm, paymentterm } from '../../../../shared/interface/router-links';
@Component({
  selector: 'app-view-payment-terms',
  templateUrl: './view-payment-terms.component.html',
  styleUrls: ['./view-payment-terms.component.css']
})
export class ViewPaymentTermsComponent implements OnInit {

  isFirstDisabled: boolean;
 public status: any = {
    isFirstOpen: true,
    isFirstDisabled: false
  };
paymentTermsDetails: PaymentTerm[];


  public paymentTerm: PaymentTerm = new PaymentTerm() ;
  public paymentTerms;
  public draft;
  public active;
  public inactive;
  lccheck: boolean;
  subscription: Subscription;
  updateData: any;
  isEdit: boolean;
  editMode: boolean;
  public del_id;
  errorMessage: any;
  payTermName: any;
  isDeletePopupModal: boolean;
  isDraftStatus: boolean;
  deleteSuccessModal: boolean;
  deletebyid: boolean;
  public deletedStatus;
  ispagebackPopupModal: boolean;
  isCannotDeletePopup: boolean;
  isUpdatedSuccessPopup: boolean;
  isShowModal: boolean;
  statusMsg: any;
  status_change: boolean;
  public payterm: any;
  editPermissionId: Number;
  isActiveStatus: boolean;
  public paymentTermId: number;
  public paymentTermBaseId: number;
  constructor(private route: ActivatedRoute, http: Http, private router: Router, private paymentTermsService: PaymentTermsService) { }

  ngOnInit() {
      window.scrollTo(0, 0);
     this.status_change = false;
      this.isCannotDeletePopup = false;
      this.editMode = true;
       this.deletebyid = false;
      
      this.paymentTerm = this.paymentTermsService.paymentTerm;

     

       if (this.paymentTermsService.paymentTerm.atSightUsance != null && this.paymentTermsService.paymentTerm.atSightUsance != undefined ) {
             this.lccheck  =  true;
       } else {
             this.lccheck  =  false;
        } 

     // debugger;
      if (this.paymentTerm.statusName === 'Draft') {
        this.draft = true;
        this.active = false;
        this.inactive = false;
      } else if (this.paymentTerm.statusName === 'Active' ) {
        this.active = true;
        this.draft = false;
        this.inactive = false;
      } else if (this.paymentTerm.statusName === 'InActive') {
        this.inactive = true;
        this.active = false;
        this.draft = false;
      }
      }

 backtoPrev() {
         this.router.navigate([paymentterm]);
}
close() {
    this.isCannotDeletePopup = false;
}

  enableEdit() {
    this.editMode = false;
    this.isEdit = true;
}

 confirm() {
     this.status_change = true;
          if (this.paymentTerm.statusName === 'Active') {
              this.statusMsg = 'InActivate';
              this.paymentTerm.statusName =  'InActive';
              this.status = 'Active';
        } else if (this.paymentTerm.statusName === 'InActive') {
              this.statusMsg = 'Activate';
              this.paymentTerm.statusName =  'Active';
              this.status = 'InActive';
        } else if (this.paymentTerm.statusName === 'Draft') {
              this.statusMsg = 'Activate';
              this.paymentTerm.statusName =  'Active';
              this.status = 'Draft';
        }
   }

closedeletepopup() {
   this.isDeletePopupModal = false;
   this.isCannotDeletePopup = false;
 }
conf_status_change_draft() {
  const submitstatus = 'save';
  const addoreditpage = false; // add page is true , edit page is false
  this.paymentTerm.statusName = 'Active';
      this.subscription = this.paymentTermsService.savePaymentTerms(this.paymentTerm, submitstatus, addoreditpage).
        subscribe(updateData => {
              this.updateData = updateData;
              this.isShowModal = true;
              setTimeout(() => { this.isShowModal = false; }, 3000);
              this.router.navigate([paymentterm]);
          }, error => {
              this.status_change = false;
             },
            () => console.log('Finished')

            );
}
conf_status_change_to_active() {
    const submitstatus = 'InActive';
    const addoreditpage = false; // add page is true , edit page is false
    this.paymentTerm.statusName = 'Active';
          this.subscription = this.paymentTermsService.savePaymentTerms(this.paymentTerm, submitstatus, addoreditpage).
          subscribe(updateData => {
              this.updateData = updateData;
              this.isShowModal = true;
              setTimeout(() => { this.isShowModal = false; }, 3000);
              this.router.navigate([paymentterm]);
          }, error => {
              this.status_change = false;
             },
            () => console.log('Finished')

            );
}
conf_status_change_deactivate() {
  this.paymentTerm.statusName = 'InActive';
  const deactivestaus = 'InActive';
  const addoreditpage = false; // add page is true , edit page is false
          this.subscription = this.paymentTermsService.savePaymentTerms(this.paymentTerm, deactivestaus, addoreditpage).
            subscribe(updateData => {
              this.updateData = updateData;
              this.isShowModal = true;
              setTimeout(() => { this.isShowModal = false; }, 3000);
              this.router.navigate([paymentterm]);
          }, error => {
                   this.status_change = false;
             },
            () => console.log('Finished')

            );
}
delete() {
      this.deletebyid = true;
      this.statusMsg = 'Delete';
      this.status = 'Draft';
   }

 conf_delete(id) {
      const paramobj = {'paymentTermId': id};
          this.subscription = this.paymentTermsService.deleteGroupById(this.paymentTermId, this.status).subscribe(deletedStatus => {
          this.deletedStatus = deletedStatus;
          this.deletebyid = false;
          if (this.status === 'Draft') {
          this.isDraftStatus = true;
        }else if (this.status === 'Active') {
          this.isActiveStatus = true;
        }
          this.router.navigate([paymentterm]);

 },
         error => {
            this.errorMessage = error;
            this.isDeletePopupModal = false;
            this.isCannotDeletePopup = true;
        },
         () => console.log('Finished')
    );
}
payment_delete( id , name , status ) {
    this.paymentTermId = id;
    this.paymentTermsService.payTermName = name;
    this.status = status;
    if (this.status === 'Draft') {
      this.isDraftStatus = true;
    }else if (this.status === 'Active') {
      this.isActiveStatus = true;
    }
    this.isDeletePopupModal = true;

    return false;
  }
}
