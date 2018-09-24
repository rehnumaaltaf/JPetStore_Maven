import { Component, OnInit, OnDestroy, Output, ViewChild, ViewEncapsulation } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { Currency, CurrencyCodeSuggesion, CurrencyNameSuggesion } from './model/currency';
import { ExcelExportData } from '@progress/kendo-angular-excel-export';
import { CurrencyService } from './service/currency.service';
import { Observable } from 'rxjs/Observable';
import { GridComponent, GridDataResult, DataStateChangeEvent, PageChangeEvent } from '@progress/kendo-angular-grid';
import { process, SortDescriptor, orderBy, GroupDescriptor, State } from '@progress/kendo-data-query';
import { ModalDirective } from 'ngx-bootstrap';
import { ConfirmationService } from '../../../shared/confirm-box/confirm.service';
import { MasterSetupService } from '../../master-setup/service/master-setup.service';
import { CanComponentDeactivate } from '../../../shared/guards/can-deactivate-guard';
import { MESSAGE_SUCCESS, MESSAGE_ERROR } from '../../../shared/interface/common.constants';
// tslint:disable-next-line:max-line-length
import { ACTION_SAVE, ACTION_SUBMIT, ACTION_UPDATE, ACTION_ACTIVE, ACTION_DEACTIVE, STATUS_DRAFT, STATUS_ACTIVE, STATUS_INACTIVE } from '../../../shared/interface/common.constants';
import { viewCurrency } from '../../../shared/interface/router-links';
@Component({
  selector: 'app-currency',
  templateUrl: './currency.component.html',
  styleUrls: ['./currency.component.css'],
  encapsulation: ViewEncapsulation.None,
})

export class CurrencyComponent implements OnInit, OnDestroy, CanComponentDeactivate {
  subscription: Subscription;
  public view: Observable<GridDataResult>;
  gridView: GridDataResult;
  public mode: Boolean = false;
  public allowUnsort: Boolean = true;
  public multiple: Boolean = false;
  public allItemsChecked: Boolean = false;
  public editedRowIndex = -1;
  currencyNameList = [];
  currencyCodeList = [];
  prevCurrencyDetail: Currency;
  minTypeaheadLength = 2;
  kendoGridHeight = 650;
  rowHeight = 40;
  errors = [];
  gridHeight = this.kendoGridHeight;
  state: State = {
    skip: 0,
    take: 1000
  };
  group: GroupDescriptor[] = [{ field: 'statusName' }];
  sort: SortDescriptor[] = [];
  public submit: Boolean = false;
  public edit: Boolean = false;
  public create: Boolean = false;
  reqCurrencyCode: Boolean = false;
  reqCurrencyName: Boolean = false;
  isValid: Boolean = true;
  public del_id;
  public savedData;
  status = status;
  isActiveStatus: Boolean;
  isDraftStatus: Boolean;
  @Output() loadingData: Boolean = true;
  sender: any;
  @ViewChild('confirmModal') public confirmModal: ModalDirective;
  @ViewChild('currencyCode') currencyCode;
  @ViewChild('currencyName') currencyName;
  isHover: Boolean;
  isDelete: Boolean;

  groups: Currency[];
  public flag: boolean;
  isActionPerformed = true;

  constructor(private router: Router, public currencyService: CurrencyService,
    private confirmationService: ConfirmationService, private masterSetupService: MasterSetupService) {
  }

  ngOnInit() {
    this.isHover = false;
    this.getCurrencyDetails();
  }

  /*This function is called when user is trying to come out the component*/
  canDeactivate(): boolean {
    return this.isActionPerformed;
  }
/*Reset Field values */
  resetGridFields() {
    this.submit = false;
    this.edit = false;
    this.create = false;
    this.isHover = false;
    this.isDelete = false;
    if (this.sender) {
        this.sender.closeRow(this.editedRowIndex);
    }
    if (this.currencyService.currencyDetail.isEdit) {
        this.currencyService.currencyDetail.isEdit = false;
    }
    this.editedRowIndex = null;
  }

  /*Currency Grid Data Calling Function*/
  getCurrencyDetails() {
      const url = '';
      this.subscription = this.currencyService.getCurrencyJSON(url).subscribe(currencyData => {
      this.currencyService.currencyDetails = currencyData.body;
      this.loadcurrencyDetails();
      this.resetGridFields();
      this.isActionPerformed = true;
      this.isHover = false;
      this.isDelete = false;
      },
      error => { throw error }
    );
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  checkAllClicked(e) {
    if (e.target.checked) {
      this.allItemsChecked = true;
      for (let i = 0; i < this.currencyService.currencyDetails.length; i++) {
        this.currencyService.currencyDetails[i].checked = true;
      }
    } else {
      this.allItemsChecked = false;
      for (let i = 0; i < this.currencyService.currencyDetails.length; i++) {
        this.currencyService.currencyDetails[i].checked = false;
      }
    }
  }
  selectUnSelectAllChecked(e) {
    if (!e.target.checked) {
      this.allItemsChecked = false;
    } else {
      let selected;
      selected = 0;
      for (let i = 0; i < this.currencyService.currencyDetails.length; i++) {
        if (!this.currencyService.currencyDetails[i].checked) {
          this.allItemsChecked = false;
          break;
        } else {
          selected++;
        }
      }
      if (selected === this.currencyService.currencyDetails.length) {
        this.allItemsChecked = true;
      }
    }
    console.log(this.allItemsChecked);
  }

  public addHandler({ sender, rowIndex, dataItem }) {
    this.isActionPerformed = false;
    this.submit = true;
    this.edit = false;
    this.create = true;
    this.flag = false;
    this.sender = sender;
    this.closeEditor(sender);
    this.currencyService.currencyDetail = new Currency();
    sender.addRow(this.currencyService.currencyDetail);
    this.isHover = true;
    this.isDelete = false;

  }

  public editHandler({ sender, rowIndex, dataItem }) {
    this.isActionPerformed = false;
    this.submit = true;
    this.edit = true;
    this.create = false;
    this.flag = true;
    this.sender = sender;
    this.closeEditor(sender);
    this.editedRowIndex = rowIndex;
    this.prevCurrencyDetail = Object.assign({}, dataItem);
    this.currencyService.currencyDetail = Object.assign({}, dataItem);
    this.currencyService.currencyDetail.isEdit = true;
    this.isHover = true;
    this.isDelete = true;
    sender.editRow(rowIndex);

  }

  public saveHandler({ sender, rowIndex, dataItem, isNew }) {
    this.sender = sender;
    this.editedRowIndex = rowIndex;
  }
  /*Currency Update Function*/
  updatecurrencyDetails(dataItem) {
    if (this.doValidate()) {
      if ((dataItem.statusName).toUpperCase() === STATUS_ACTIVE.toUpperCase()) {
        this.currencyService.currencyDetail.action = ACTION_SUBMIT;
      } else if ((dataItem.statusName).toUpperCase()  === STATUS_INACTIVE.toUpperCase()) {
        this.currencyService.currencyDetail.action = ACTION_DEACTIVE;
      } else if ((dataItem.statusName).toUpperCase()  === STATUS_DRAFT.toUpperCase()) {
        this.currencyService.currencyDetail.action = ACTION_SAVE;
      }
     // const link = this.masterSetupService.getChildObject('Update Brand.PUT');
      // const url = link.href;
      const url = '';
      this.subscription = this.currencyService.updateCurrency(url, this.currencyService.currencyDetail).subscribe(currencyDetail => {
      this.resetGridFields();
      this.getCurrencyDetails();
      this.currencyService.messages = {severity: MESSAGE_SUCCESS, summary: 'Message.Currency.Update',
                                      placeHolder: [this.currencyService.currencyDetail.currencyName]};
      }, error => {
        this.currencyService.messages = { severity: MESSAGE_ERROR, summary: this.currencyService.errorMessage };
      });
    }
  }

  /*Currency Submit Function*/
  currencySaveAsActive(dataItem) {
    if (this.doValidate()) {
      if (this.currencyService.currencyDetail.pkCurrencyId) {
        this.currencyService.currencyDetail.action = ACTION_SUBMIT;
        // const link = this.masterSetupService.getChildObject('Update Brand.PUT');
        // const url = link.href;
        const url = '';
        this.subscription = this.currencyService.updateCurrency(url, this.currencyService.currencyDetail).
                             subscribe(currencyDetail => {
        this.resetGridFields();
        this.getCurrencyDetails();
        this.currencyService.messages = {severity: MESSAGE_SUCCESS, summary: 'Message.Currency.Update',
                                        placeHolder: [this.currencyService.currencyDetail.currencyName]};

        }, error => {
          this.currencyService.messages = { severity: MESSAGE_ERROR, summary: this.currencyService.errorMessage };

        });
      } else {
        this.currencyService.currencyDetail.action = ACTION_SUBMIT;
        // const link = this.masterSetupService.getChildObject('Create Brand.POST');
        // const url = link.href;
        const url = '';
        this.subscription = this.currencyService.saveCurrency(url, this.currencyService.currencyDetail).
                            subscribe(currencyDetail => {
        this.resetGridFields();
        this.getCurrencyDetails();
        this.currencyService.messages = {severity: MESSAGE_SUCCESS, summary: 'Message.Currency.Submit',
                                        placeHolder: [this.currencyService.currencyDetail.currencyName]};
        },
        error => {
            this.currencyService.messages = { severity: MESSAGE_ERROR, summary: this.currencyService.errorMessage };
        });
      }
    }
  }

  /*Currency Save Function*/
  currencySaveAsDraft(dataItem) {
    if (this.doValidate()) {
      // const link = this.masterSetupService.getChildObject('Create Brand.POST');
      // const url = link.href;
      const url = '';
      if (this.currencyService.currencyDetail.pkCurrencyId) {
        dataItem.action = ACTION_SAVE;
        this.updatecurrencyDetails(dataItem);
      } else {
        this.currencyService.currencyDetail.action = ACTION_SAVE;
        this.subscription = this.currencyService.saveCurrency(url,
        this.currencyService.currencyDetail).subscribe(currencyDetail => {
        this.resetGridFields();
        this.getCurrencyDetails();
        this.currencyService.messages = {severity: MESSAGE_SUCCESS, summary: 'Message.Currency.Save',
                                        placeHolder: [this.currencyService.currencyDetail.currencyName]};
        },
          error => {
             this.currencyService.messages = { severity: MESSAGE_ERROR, summary: this.currencyService.errorMessage };
          });
       }
    }
  }
  /*Field Reset Confirmation and Resetting the fields*/
  reset() {
    if (this.currencyService.currencyDetail.pkCurrencyId) {
      if ((this.currencyService.currencyDetail.currencyCode !== this.prevCurrencyDetail.currencyCode) ||
        (this.currencyService.currencyDetail.currencyName !== this.prevCurrencyDetail.currencyName) ||
        (this.currencyService.currencyDetail.currencyDesc !== this.prevCurrencyDetail.currencyDesc) ||
        (this.currencyService.currencyDetail.currencySymbol !== this.prevCurrencyDetail.currencySymbol)) {
        this.confirmationService.confirm({
          message: 'Do you really want to reset?',
          header: 'Reset Confirmation',
          accept: (event) => {
            this.currencyService.currencyDetail = Object.assign({}, this.prevCurrencyDetail);
            this.isValid = true;
            this.flag = false;
            this.reqCurrencyCode = false;
            this.reqCurrencyName = false;
            this.isActionPerformed = false;
            this.isHover = false;
            this.isDelete = false;
          },
          reject: (event) => {
          }
        });
      } else {
        this.resetGridFields();
        this.currencyService.currencyDetail.isEdit = false;
        this.closeEditor(this.sender, this.editedRowIndex);
        this.isActionPerformed = true;
        this.isHover = false;
        this.isDelete = false;
      }
    } else {
      if ((this.currencyService.currencyDetail.currencyCode && this.currencyService.currencyDetail.currencyCode !== '') ||
        (this.currencyService.currencyDetail.currencyName && this.currencyService.currencyDetail.currencyName !== '') ||
        (this.currencyService.currencyDetail.currencyDesc && this.currencyService.currencyDetail.currencyDesc !== '') ||
        (this.currencyService.currencyDetail.currencySymbol && this.currencyService.currencyDetail.currencySymbol !== '')) {
        this.confirmationService.confirm({
          message: 'Do you really want to reset?',
          header: 'Reset Confirmation',
          accept: (event) => {
            this.currencyService.currencyDetail.currencyCode = null;
            this.currencyService.currencyDetail.currencyName = null;
            this.currencyService.currencyDetail.currencyDesc = null;
            this.currencyService.currencyDetail.currencySymbol = null;
            this.isValid = true;
            this.flag = false;
            this.reqCurrencyCode = false;
            this.reqCurrencyName = false;
            this.isActionPerformed = false;
            this.isHover = false;
            this.isDelete = false;
          },
          reject: (event) => {
          }
        });
      } else {
        this.resetGridFields();
        this.currencyService.currencyDetail.isEdit = false;
        this.closeEditor(this.sender, this.editedRowIndex);
        this.isActionPerformed = true;
        this.isHover = false;
        this.isDelete = false;
      }
    }
  }
  /*Confirm Box Messages and Header Declarations for Delete, Activate and Reactivate*/
  openConfirmAction(dataItem) {
    if (this.doValidate()) {
    if ((dataItem.statusName).toUpperCase() === STATUS_DRAFT.toUpperCase()) {
      this.confirmationService.confirm({
        message: 'Do you really want to delete?',
        header: 'Delete Confirmation',
        accept: (event) => {
          this.deleteCurrency(dataItem);
        },
        reject: (event) => {
        }
      });
    } else if ((dataItem.statusName).toUpperCase() === STATUS_ACTIVE.toUpperCase()) {
      this.confirmationService.confirm({
        message: 'Do you really want to deactivate?',
        header: 'Deactivate Confirmation',
        accept: (event) => {
          this.currencyDeactivate(dataItem);
        },
        reject: (event) => {
        }
      });
    } else if ((dataItem.statusName).toUpperCase()  === STATUS_INACTIVE.toUpperCase()) {
      this.confirmationService.confirm({
        message: 'Do you really want to reactivate?',
        header: 'Delete Confirmation',
        accept: (event) => {
          this.currencyReactivate(dataItem);
        },
        reject: (event) => {
        }
      });
    }
    }
  }

  /*Currency Reactivate*/
  currencyReactivate(dataItem) {
    // const link = this.masterSetupService.getChildObject('Update Brand.PUT');
    // const url = link.href;
    if (this.edit === false) {
      this.currencyService.currencyDetail = dataItem;
    }

    const url = '';
    if ((this.currencyService.currencyDetail.pkCurrencyId !== null) &&
      (this.currencyService.currencyDetail.pkCurrencyId !== undefined)) {
      if (this.doValidate()) {
        this.currencyService.currencyDetail.action = ACTION_SUBMIT;
        this.subscription = this.currencyService.updateCurrency(url, this.currencyService.currencyDetail).subscribe(currencyDetail => {
        this.resetGridFields();
        this.getCurrencyDetails();
        this.currencyService.messages = { severity: MESSAGE_SUCCESS, summary: 'Message.Currency.Activate',
                                         placeHolder: [this.currencyService.currencyDetail.currencyName]};
        },
          error => {
            this.currencyService.messages = { severity: MESSAGE_ERROR, summary: this.currencyService.errorMessage };
         });
      }
    }
  }

  /*Currency Deactivate*/
  currencyDeactivate(dataItem) {
    // const link = this.masterSetupService.getChildObject('Update Brand.PUT');
    // const url = link.href;
    if (this.edit === false) {
      this.currencyService.currencyDetail = dataItem;
    }
    const url = '';
    if ((this.currencyService.currencyDetail.pkCurrencyId !== null) &&
        (this.currencyService.currencyDetail.pkCurrencyId !== undefined)) {
          if (this.doValidate()) {
             this.currencyService.currencyDetail.action = ACTION_DEACTIVE;
             this.subscription = this.currencyService.updateCurrency(url, this.currencyService.currencyDetail).
             subscribe(currencyDetail => {
             this.resetGridFields();
             this.getCurrencyDetails();
             this.currencyService.messages = {severity: MESSAGE_SUCCESS, summary: 'Message.Currency.DeActivate',
                                              placeHolder: [this.currencyService.currencyDetail.currencyName]};
             },
             error => {
             this.currencyService.messages = { severity: MESSAGE_ERROR, summary: this.currencyService.errorMessage };
            });
          }
    }
  }

 /*Delete Currency Record*/
  deleteCurrency(dataItem) {
    // const link = this.masterSetupService.getChildObject('Delete Brand.Delete');
    // const url = link.href;
    const url = '';
    this.currencyService.currencyDetail = dataItem;
    this.subscription = this.currencyService.deleteCurrency(url, dataItem.pkCurrencyId).subscribe(currencyDetail => {
      this.getCurrencyDetails();
      this.currencyService.messages = {
        severity: MESSAGE_SUCCESS, summary: 'Message.Currency.Delete',
        placeHolder: [this.currencyService.currencyDetail.currencyName]
      };
    },
      error => {
        this.currencyService.messages = { severity: MESSAGE_ERROR, summary: this.currencyService.errorMessage };
      });
  }

  private closeEditor(grid, rowIndex = this.editedRowIndex) {
    this.sender.closeRow(rowIndex);
    this.editedRowIndex = undefined;
    this.isHover = false;
    this.isDelete = false;
    // this.isActionPerformed = true;
    // this.formGroup = undefined;
  }

  public cancelHandler({ sender, rowIndex }) {
    this.closeEditor(sender, rowIndex);
  }

  sortChange(sort: SortDescriptor[]): void {
    this.sort = sort;
    this.loadcurrencyDetails();
  }

  public onStateChange(state: State) {
    this.state = state;
  }
  loadcurrencyDetails(): void {
    this.gridView = {
      data: orderBy(this.currencyService.currencyDetails, this.sort),
      total: this.currencyService.currencyDetails.length
    };
  }
  public dataStateChange(state: DataStateChangeEvent): void {
    this.state = state;
  }


  pageChange(event: PageChangeEvent): void {
    this.state.skip = event.skip;
    this.loadcurrencyDetails();
  }

  NumberRestrictions(keyEvent) {
    const result = (keyEvent.which) ? keyEvent.which : keyEvent.keyCode;
    if (result >= 48 && result <= 57) {
      return false;
    } else {
      return true;
    }

  }

  /*allData(): ExcelExportData {
   const result: ExcelExportData = {
   group: this.group
   };
  return result;
  }*/

 isNotBlank(value: string): Boolean {
    return (value && value.trim() !== '');
  }

  /*Currency Mandatory validations*/
  doValidate(): Boolean {
    this.isValid = true;
    if (!this.isNotBlank(this.currencyService.currencyDetail.currencyCode)) {
      this.reqCurrencyCode = true;
      this.isValid = false;
    } else {
      this.reqCurrencyCode = false;
    }

    if (!this.isNotBlank(this.currencyService.currencyDetail.currencyName)) {
      this.reqCurrencyName = true;
      this.isValid = false;
    } else {
      this.reqCurrencyName = false;
    }

    if (!this.isValid) {
      this.currencyService.messages = {
        severity: MESSAGE_ERROR, summary: 'Message.Currency.Mandatory',
        placeHolder: []
      };
    }
    return this.isValid;
  }

 /*For Currency Name- Auto Suggestions*/
  currencyNameSuggestion($event) {
    const currencyName = $event.target.value;
    if (this.isNotBlank(currencyName) && currencyName.length >= this.minTypeaheadLength) {
      let currencyNameSuggestion;
      currencyNameSuggestion = new CurrencyCodeSuggesion();
      currencyNameSuggestion = { currencyName: currencyName, toValidate: 'NAME' };
      this.subscription = this.currencyService.getCurrencyNamelist(currencyNameSuggestion.currencyName).subscribe(data => {
        // console.log(data);
        this.currencyNameList = data;
      }, error => { throw error; });
    } else {
      this.currencyNameList = [];
    }
  }

  /*For Currecny Code- Auto Suggestions*/
  currencyCodeSuggestion($event) {
    const currencyCode = $event.target.value;
    if (this.isNotBlank(currencyCode) && currencyCode.length >= this.minTypeaheadLength) {
      let currencyCodeSuggestion;
      currencyCodeSuggestion = new CurrencyCodeSuggesion();
      currencyCodeSuggestion = { currencyCode: currencyCode, toValidate: 'CODE' };
      /// alert(JSON.stringify(currencyCodeSuggestion.));
      this.subscription = this.currencyService.getCurrencyCodelist(currencyCode).subscribe(data => {
        // console.log(data);
        //  alert(data);
        this.currencyCodeList = data;
      }, error => { throw error; });
    } else {
      this.currencyCodeList = [];
    }
  }

  public handler(type: string, $event: ModalDirective) {
     // Event handler
  }
}

