import { Component, OnInit, Output, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup, FormControl } from '@angular/forms';
import { CurrencyService} from '../service/currency.service';
import { CurrencyModal } from '../model/currency.modal';
import { Subscription } from 'rxjs/Subscription';
import { currency } from '../../../../shared/interface/router-links';
import { Suggestion } from '../model/suggestion.model';
import { ModalDirective } from 'ngx-bootstrap';

@Component({
  selector: 'app-view-currency',
  templateUrl: './view-currency.component.html',
  styleUrls: ['./view-currency.component.css']
})
export class ViewCurrencyComponent implements OnInit {
  currencyID: number;
  public draft;
  public active;
  public inactive;
  subscription: Subscription;
  status: any;
  deletebyid: Boolean;
  public deletedStatus;
  ispagebackPopupModal: Boolean;
  isCannotDeletePopup: Boolean;
  isUpdatedSuccessPopup: Boolean;
  isShowModal: boolean;
  statusMsg: any;
  status_change: boolean;
  public currencyData: any;
  public enableEditFlag: Boolean;
  suggestions: Suggestion;
  req_currencyCode: Boolean = false;
  errorMessage: string;
  errorModal: Boolean = false;
  /** Edit Page properties */
  id: string;
  statusId: string;
  message: string;
  public iseditModal;
  UpdateModal = false;
  @ViewChild('edit') public edit: ModalDirective;
  /** Edit Page properties - End */

  constructor(public currencyService: CurrencyService , private router: Router) { }

  ngOnInit() {
   // this.currencyService.getCurrencyListById();
  // this.currencyService.setCurrencyListById(2);
   // this.currencyID = this.currencyService.getCurrencyListById();
   this.callFn();
}
callFn() {
 /* this.currencyService.getCurrencyById(this.currencyService.viewbyIdParamCurrency).subscribe(data => {
      this.currencyService.currency = data.body;
      if (this.currencyService.currency.fkStatusId === 3) {
        this.draft = true;
        this.active = false;
        this.inactive = false;
      }else if (this.currencyService.currency.fkStatusId === 1) {
        this.active = true;
        this.draft = false;
        this.inactive = false;
      }else if (this.currencyService.currency.fkStatusId === 4) {
        this.inactive = true;
        this.active = false;
        this.draft = false;
      }
    }, error => {
      console.log('Error Loading UOM Listing: ' + <any>JSON.stringify(error))
    }, () => console.log('callFn - Finished'));*/
  }

  backtoPrev(): void {
    this.router.navigate([currency]);
  }

  editCurrency(id, statusId) {
    this.id = id;
    this.statusId = statusId;
    this.iseditModal = true;
   }

   close(obj) {
    // this.iseditModal = false;
    if (this.iseditModal) {
      this.edit.hide();
    }

    this.message = obj['msg'];
    if (this.message !== '' && this.message !== undefined && this.message !== null) {
      this.loadUpdatedCurrency();
      this.UpdateModal = true;
      setTimeout(() => {
        this.UpdateModal = false;
      }, 3000);
    }
  }

  loadUpdatedCurrency(): void {
   /* this.currencyService.getCurrencyById(this.currencyService.viewbyIdParamCurrency).subscribe(data => {
      this.currencyService.currency = data.body;
      if (this.currencyService.currency.fkStatusId === 3) {
        this.draft = true;
        this.active = false;
        this.inactive = false;
      }else if (this.currencyService.currency.fkStatusId === 1) {
        this.active = true;
        this.draft = false;
        this.inactive = false;
      }else if (this.currencyService.currency.fkStatusId === 4) {
        this.inactive = true;
        this.active = false;
        this.draft = false;
      }
    }, error => {
      console.log('Error Loading UOM Listing: ' + <any>JSON.stringify(error))
    }, () => console.log('loadUpdatedCurrency - Finished'));*/
  }

  updateCurrency(action) {
  /*  if (action === 'DEACTIVATE') {
      this.currencyService.currency.fkStatusId = 4;
    } else if (action === 'ACTIVATE' || action === 'SUBMIT') {
      this.currencyService.currency.fkStatusId = 1;
    }
    this.currencyService.UpdateCurrencyDetails(this.currencyService.currency).subscribe(data => {
      // this.onEditCancel.emit({msg: data.body});
      this.router.navigate([currency]);
    });*/
  }

  hideEdit() {
    this.iseditModal = false;
  }
}
