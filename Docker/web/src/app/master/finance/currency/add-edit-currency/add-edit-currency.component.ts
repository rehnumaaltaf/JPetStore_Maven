import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { CurrencyDetails } from '../model/currency';
import { CurrencyService} from '../service/currency.service';
import { Suggestion } from '../model/suggestion.model';

@Component({
  selector: 'app-add-edit-currency',
  templateUrl: './add-edit-currency.component.html',
  styleUrls: ['./add-edit-currency.component.css']
})
export class AddEditCurrencyComponent implements OnInit {

  @Input() editId: any;
  @Input() editStatusId: any;
  @Output() onECancel: EventEmitter<{msg: string}> = new EventEmitter<{msg: string}> ();
  public statusActive: Boolean;
  public statusDraft: Boolean;
  public statusInactive: Boolean;
  public currencyDetails: CurrencyDetails;
  errorMessage: string;
  errorModal: Boolean = false;
  validateRetVal: Boolean = false;
  req_currencyCode: Boolean = false;
  req_currencyName: Boolean = false;
  suggestions: Suggestion;
  results: Object;


  constructor(private  currencyService: CurrencyService) { }

  ngOnInit() {
    this.currencyDetails = new CurrencyDetails();
    this.setDefaultValues();
    if ((this.editId !== null || this.editId !== undefined)
      && (this.editStatusId !== null || this.editStatusId !== undefined)) {
        if (this.editStatusId === 1) {
          this.statusActive = true;
        }
        if (this.editStatusId === 2) {
          this.statusDraft = true;
        }
        if (this.editStatusId === 3) {
          this.statusInactive = true;
        }
      //  this.loadCurrencyById(this.editId);
    }
  }

  cancelEdit() {
    this.onECancel.emit({msg: ''});
  }

  setDefaultValues() {
    this.statusActive = false;
    this.statusDraft = false;
    this.statusInactive = false;
  }

/*  loadCurrencyById(id) {
    this.currencyService.getCurrencyById(id).subscribe(data => {
      this.currencyDetails = data.body;
    }, error => { throw error });
  }

  updateCurrency(action) {
    console.log(' Action ---- > ' + action);
    const link = '';
    if (this.validateInput()) {
      if (action === 'DEACTIVATE') {
        this.currencyDetails.fkStatusId = 3;
      } else if (action === 'ACTIVATE' || action === 'SUBMIT') {
        this.currencyDetails.fkStatusId = 1;
      }
      this.currencyService.updateCurrency(link, this.currencyDetails).subscribe(data => {
        this.onECancel.emit({msg: data.body});
      });
    }
  }
*/
 /* validateCurrencyCode() {
      if (this.currencyDetails.currencyCode !== null && this.currencyDetails.currencyCode !== undefined
          && this.currencyDetails.currencyCode.trim().length !== 0) {
        this.currencyService.UniqueCurrencyCode(this.currencyDetails.currencyCode).subscribe(data => {
          if (data === 1) {
            this.errorMessage = 'Duplicate Currency Code';
            this.errorModal = true;
            setTimeout(() => {
              this.errorModal = false;
            }, 3000);
            this.currencyDetails.currencyCode = null;
          }
        }, error => { throw error });
    }
  }

  validateCurrencyName() {
      if (this.currencyDetails.currencyName !== null && this.currencyDetails.currencyName !== undefined
          && this.currencyDetails.currencyName.trim().length !== 0) {
        this.currencyService.UniqueCurrencyName(this.currencyDetails.currencyName).subscribe(data => {
          if (data === 1) {
            this.errorMessage = 'Duplicate Currency Name';
            this.errorModal = true;
            setTimeout(() => {
              this.errorModal = false;
            }, 3000);
            this.currencyDetails.currencyName = null;
          }
        }, error => { throw error });
    }
  }*/

  validateInput() {
    this.validateRetVal = true;
    if (this.currencyDetails.currencyCode === null
      || this.currencyDetails.currencyCode === undefined || this.currencyDetails.currencyCode.trim().length === 0) {
      this.req_currencyCode = true;
      this.validateRetVal = false;
    } else {
      this.req_currencyCode = false;
      this.validateRetVal = true;
    }
    if (this.currencyDetails.currencyName === null
      || this.currencyDetails.currencyName === undefined || this.currencyDetails.currencyName.trim().length === 0) {
      this.req_currencyName = true;
      this.validateRetVal = false;
    } else {
      this.req_currencyName = false;
      if (this.validateRetVal) {
        this.validateRetVal = true;
      }
    }
    return this.validateRetVal;
  }

  getCurrencyCodeStyle() {
    if (this.req_currencyCode === true) {
      return '#d43c3c';
    } else {
      return '#cfdee7';
    }
  }

  getCurrencyNameStyle() {
    if (this.req_currencyName === true) {
      return '#d43c3c';
    } else {
      return '#cfdee7';
    }
  }
}
