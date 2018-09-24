import { Component, OnInit, ElementRef, Renderer2, OnDestroy, Input, ViewEncapsulation, ViewChild } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, FormControl, Validators, FormArray, ReactiveFormsModule, FormsModule } from '@angular/forms';
import { AccordionModule, ModalModule } from 'ngx-bootstrap';
import { SharedModule } from '../../../../shared/shared.module';
import { BrowserModule } from '@angular/platform-browser';
import { Subscription } from 'rxjs/Subscription';
import { FinancialCalendarService } from '../service/financial-calendar.service';
import { FinancialCalendar } from '../model/financial-calendar';
import { ForwardTenor } from '../model/financial-calendar';
import { FinancialMappingComponent } from './financial-mapping.component';
import { financialCalendar } from '../../../../shared/interface/router-links';
import { NavBarComponent } from '../../../../shared/nav-bar/nav-bar.component';
import { Suggestion } from '../model/suggestion.model';
import { ModalDirective } from 'ngx-bootstrap';
import { CanComponentDeactivate } from '../../../../shared/guards/can-deactivate-guard';

@Component({
  selector: 'app-add-financial-cal',
  templateUrl: './add-financial-cal.component.html',
  styleUrls: ['./add-financial-cal.component.css'],
  encapsulation: ViewEncapsulation.None,
})

export class AddFinancialCalComponent implements OnInit, OnDestroy, CanComponentDeactivate {
  public status: any = {
    isFirstOpen: true,
    isFirstDisabled: false
  };
  public monthList: String[] = ['January', 'February', 'March', 'April', 'May', 'June', 'July',
    'August', 'September', 'October', 'November', 'December'];

  public startyear: String[] = new Array();
  public endyear: String[] = new Array();

  financialCalendarModel: FinancialCalendar = new FinancialCalendar();
  forwardTenor: ForwardTenor = new ForwardTenor();
  financialcalForm: FormGroup;
  subscription: Subscription;
  action: String;
  errorMessage: String;
  errorModal: boolean;
  suggestions: Suggestion;
  isReset: Boolean = false;
  @ViewChild('resetModal') public resetModal: ModalDirective;
  isActionPerformed = false;
  val_fiscalyear: Boolean;
  val_startyear: Boolean;
  val_startmonth: Boolean;
  val_endyear: Boolean;
  val_endmonth: Boolean;
  val_startdate: Boolean;
  val_enddate: Boolean;
  val_ctrmstatus: Boolean;
  val_erpstatus: Boolean;
  AddEditFinancialId: number;
  pageTitle: String;
  public draft;
  public active;
  public inactive;
  public isEditPage: Boolean = false;
  public returnVal: Boolean;
  public val_product: Boolean[];

  // tslint:disable-next-line:max-line-length
  constructor(private fb: FormBuilder, private router: Router, public financialService: FinancialCalendarService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.pageTitle = 'ADD FINANCIAL CALENDAR';
    this.getProductList();
    this.setDefaultValues();
    if (this.route.snapshot.params['id'] !== undefined) {
      this.AddEditFinancialId = +this.route.snapshot.params['id'];
      this.pageTitle = 'UPDATE FINANCIAL CALENDAR DETAILS';
      this.isEditPage = true;
      this.buildFinancialCalForm();
      this.financialService.getFinancialByIdJSON(this.AddEditFinancialId).subscribe(listDetails => {
        this.financialCalendarModel = listDetails.body[0];
        if (this.financialCalendarModel.statusName === 'Draft') {
          this.draft = true;
          this.active = false;
          this.inactive = false;
        } else if (this.financialCalendarModel.statusName === 'Active') {
          this.draft = false;
          this.active = true;
          this.inactive = false;
        } else {
          this.draft = false;
          this.active = false;
          this.inactive = true;
        }
        console.log(this.financialCalendarModel);
        this.editListDetails();
      },
        error => { throw error });
    } else {
      this.AddEditFinancialId = 0;
      this.buildFinancialCalForm();
      this.addTenor();
    }
    this.val_fiscalyear = false;
    this.val_startyear = false;
    this.errorModal = false;
  }

  canDeactivate(): boolean {
    return this.isActionPerformed;
  }

  getYearMonthDate(date, criteria) {
    if (date) {
      const dateArr: String = date.split('-');
      if (criteria === 'D') {
        return dateArr[2];
      } else if (criteria === 'Y') {
        return dateArr[0];
      } else if (criteria === 'M') {
        return dateArr[1];
      }
    }
  }

  buildFinancialCalForm(): void {
    this.financialcalForm = this.fb.group({
      'fiscalYear': [this.financialCalendarModel.fiscalYear, Validators.required],
      'startYear': [this.financialCalendarModel.startYear, Validators.required],
      'startMonth': [this.financialCalendarModel.startMonth, Validators.required],
      'endYear': [this.financialCalendarModel.endYear, Validators.required],
      'endMonth': [this.financialCalendarModel.endMonth, Validators.required],
      'pkFinCalId': [this.financialCalendarModel.pkFinCalId],
      'productId': [this.forwardTenor.productId, Validators.required],
      'monthShortCode': [this.forwardTenor.monthShortCode, Validators.required],
      'startDate': [this.forwardTenor.startDate, Validators.required],
      'endDate': [this.forwardTenor.endDate, Validators.required],
      'ctrmStatus': [this.forwardTenor.ctrmStatus, Validators.required],
      'erpStatus': [this.forwardTenor.erpStatus, Validators.required],
      'pkFinCalMappindId': [this.forwardTenor.pkFinCalMappindId],
      'tenorFinancialArray': this.fb.array([])
    });
  }

  // task related functions
  getFinancialList(financialcalForm) {
    return financialcalForm.get('tenorFinancialArray').controls;
  }
  addTenor() {
    const control = <FormArray>this.financialcalForm.controls['tenorFinancialArray'];
    const addrCtrl = this.initTenor();
    control.push(addrCtrl);
  }

  initTenor() {
    return this.fb.group({
      productId: [this.forwardTenor.productId, Validators.required],
      monthShortCode: [this.forwardTenor.monthShortCode, Validators.required],
      startDate: [new Date(this.forwardTenor.startDate), Validators.required],
      endDate: [new Date(this.forwardTenor.endDate), Validators.required],
      ctrmStatus: [this.forwardTenor.ctrmStatus, Validators.required],
      erpStatus: [this.forwardTenor.erpStatus, Validators.required],
      pkFinCalMappindId: [this.forwardTenor.pkFinCalMappindId]
    });
  }

  removeTenor(i: number) {

    if (i !== null && i !== 0) {
      const control = <FormArray>this.financialcalForm.controls['tenorFinancialArray'];
      control.removeAt(i);
    }
  }
  editListDetails() {
    // this.financialService.setERPStatus('Open');
    this.financialcalForm.controls['pkFinCalId'].setValue(this.financialCalendarModel.pkFinCalId);
    this.financialcalForm.controls['fiscalYear'].setValue(this.financialCalendarModel.fiscalYear);
    this.financialService.setFiscalYear(this.financialCalendarModel.fiscalYear);
    this.financialcalForm.controls['startYear'].setValue(this.financialCalendarModel.startYear);
    if (this.financialCalendarModel.startYear !== null) {
      this.startyear.push(this.financialCalendarModel.startYear);
    }
    this.financialcalForm.controls['startMonth'].setValue(this.financialCalendarModel.startMonth);
    // this.endyear.push(temp.toString());
    this.financialcalForm.controls['endYear'].setValue(this.financialCalendarModel.endYear);
    if (this.financialCalendarModel.endYear !== null) {
      this.endyear.push(this.financialCalendarModel.endYear);
    }
    this.financialcalForm.controls['endMonth'].setValue(this.financialCalendarModel.endMonth);
    const sizerollemapping = Number(JSON.stringify(this.financialCalendarModel.tenorFinancialArray.length));
    if (sizerollemapping >= 1) {
      for (let i = 0; i < sizerollemapping; i++) {
        this.addTenor();
      }
    }
  }

  /* getStyle() {
     if (this.val_fiscalyear === true) {
       return '#d43c3c';
     } else {
      return '#cfdee7';
     }
   }*/

  // getStartYearStyle() {
  //   if (this.val_startyear === true) {
  //     return '#d43c3c';
  //   } else {
  //     return '#cfdee7';
  //   }
  // }

  // getStartMnthStyle() {
  //   if (this.val_startmonth === true) {
  //     return '#d43c3c';
  //   } else {
  //     return '#cfdee7';
  //   }
  // }

  // getEndYearStyle() {
  //   if (this.val_endyear === true) {
  //     return '#d43c3c';
  //   } else {
  //     return '#cfdee7';
  //   }
  // }

  // getEndMonthStyle() {
  //   if (this.val_endmonth === true) {
  //     return '#d43c3c';
  //   } else {
  //     return '#cfdee7';
  //   }
  // }




  fiscalyearsetter() {

  }


  addFinancialCalendar() {
    this.financialCalendarModel = this.financialcalForm.value;
    this.isActionPerformed = true;
    if (this.isValidForm()) {
      console.log(this.financialCalendarModel.tenorFinancialArray[0].productId);
      this.subscription = this.financialService.saveFinancialCalDetails(this.financialCalendarModel).subscribe(data => {
        if (data.body !== null && data.body !== '') {
          this.router.navigate([financialCalendar], { queryParams: { 'success': 1 } });
        }
      }, error => { console.log(JSON.stringify(error)) }

      );
    }
  }
  updateFC(param) {
    console.log('before edit   ' + JSON.stringify(this.financialCalendarModel));
    this.financialCalendarModel = this.financialcalForm.value;
    this.financialCalendarModel.action = param;
    console.log('update fc   ' + JSON.stringify(this.financialCalendarModel));
    if (this.isValidForm()) {
      this.subscription = this.financialService.updateFinancialCalDetails(this.financialCalendarModel).subscribe(data => {
        if (data.body !== null && data.body !== '') {
          this.router.navigate([financialCalendar], { queryParams: { 'success': 1 } });
        }
      }, error => {
        throw error;

      });
    }
  }

  isvalidReset(): boolean {
    this.financialCalendarModel = this.financialcalForm.value;
    const financialList = Number(JSON.stringify(this.financialcalForm.value.tenorFinancialArray.length));
    if ((this.financialCalendarModel.fiscalYear && this.financialCalendarModel.fiscalYear !== '')
      || (this.financialCalendarModel.startMonth && this.financialCalendarModel.startMonth !== '')
      || (this.financialCalendarModel.endMonth && this.financialCalendarModel.endMonth !== '')
      || (this.financialCalendarModel.startYear && this.financialCalendarModel.startYear !== '')
      || (this.financialCalendarModel.endYear && this.financialCalendarModel.endYear !== '')) {
      this.isReset = true;
      return false;
    }

    if (financialList >= 1) {
      for (let i = 0; i < financialList; i++) {

        if ((this.financialcalForm.value.tenorFinancialArray[i].startDate &&
          this.financialcalForm.value.tenorFinancialArray[i].startDate !== '')
          || (this.financialcalForm.value.tenorFinancialArray[i].endDate &&
            this.financialcalForm.value.tenorFinancialArray[i].endDate !== '')
          || (this.financialcalForm.value.tenorFinancialArray[i].CTRMStatus &&
            this.financialcalForm.value.tenorFinancialArray[i].CTRMStatus !== '')
          || (this.financialcalForm.value.tenorFinancialArray[i].ERPStatus &&
            this.financialcalForm.value.tenorFinancialArray[i].ERPStatus !== '')
          || (this.financialcalForm.value.tenorFinancialArray[i].monthName &&
            this.financialcalForm.value.tenorFinancialArray[i].monthName !== '')) {
          this.isReset = true;
          return false;
        }
      }
    }
    return true;
  }

  isNotBlank(value: String): Boolean {
    console.log(value && value.trim());
    console.log(value && value.trim() !== '');
    return ((value && value.trim() !== '') && (value && value.trim() !== null) && (value && value.trim() !== undefined));
  }

  isValidForm(): Boolean {
    this.returnVal = true;
    const financialList = Number(JSON.stringify(this.financialcalForm.value.tenorFinancialArray.length));

    if (this.isNotBlank(this.financialCalendarModel.fiscalYear)) {
      this.val_fiscalyear = false;
    } else {
      this.val_fiscalyear = true;
      this.returnVal = false;
    }
    if (this.isNotBlank(this.financialCalendarModel.startYear)) {
      this.val_startyear = false;
    } else {
      this.val_startyear = true;
      this.returnVal = false;
    }
    if (this.isNotBlank(this.financialCalendarModel.startMonth)) {
      this.val_startmonth = false;
    } else {
      this.val_startmonth = true;
      this.returnVal = false;
    }
    if (this.isNotBlank(this.financialCalendarModel.endYear)) {
      this.val_endyear = false;
    } else {
      this.val_endyear = true;
      this.returnVal = false;
    }
    if (this.isNotBlank(this.financialCalendarModel.endMonth)) {
      this.val_endmonth = false;
    } else {
      this.val_endmonth = true;
      this.returnVal = false;
    }

    if (this.financialcalForm.value.tenorFinancialArray !== undefined) {
      const size = Number( JSON.stringify(this.financialcalForm.value.tenorFinancialArray.length));
      for (let index = 0 ; index < size ; index++) {

        if (this.financialcalForm.value.tenorFinancialArray[index].monthShortCode == null
            || this.financialcalForm.value.tenorFinancialArray[index].monthShortCode === undefined
            || this.financialcalForm.value.tenorFinancialArray[index].monthShortCode === '') {
            this.financialService.req_monthShortCode[index] = true;
            this.returnVal = false;
        } else {
          this.financialService.req_monthShortCode[index] = false;
        }
        if (this.financialcalForm.value.tenorFinancialArray[index].productId == null
            || this.financialcalForm.value.tenorFinancialArray[index].productId === undefined
            || this.financialcalForm.value.tenorFinancialArray[index].productId === '') {
            this.financialService.req_productId[index] = true;
            this.returnVal = false;
        } else {
          this.financialService.req_productId[index] = false;
        }
        if (this.financialcalForm.value.tenorFinancialArray[index].startDate == null
            || this.financialcalForm.value.tenorFinancialArray[index].startDate === undefined
            || this.financialcalForm.value.tenorFinancialArray[index].startDate === '') {
            this.financialService.req_startDate[index] = true;
            this.returnVal = false;
        } else {
          this.financialService.req_startDate[index] = false;
        }
        if (this.financialcalForm.value.tenorFinancialArray[index].endDate == null
            || this.financialcalForm.value.tenorFinancialArray[index].endDate === undefined
            || this.financialcalForm.value.tenorFinancialArray[index].endDate === '') {
            this.financialService.req_endDate[index] = true;
            this.returnVal = false;
        } else {
          this.financialService.req_endDate[index] = false;
        }
        if (this.financialcalForm.value.tenorFinancialArray[index].ctrmStatus == null
            || this.financialcalForm.value.tenorFinancialArray[index].ctrmStatus === undefined
            || this.financialcalForm.value.tenorFinancialArray[index].ctrmStatus === '') {
            this.financialService.req_ctrmStatus[index] = true;
            this.returnVal = false;
        } else {
          this.financialService.req_ctrmStatus[index] = false;
        }
        if (this.financialcalForm.value.tenorFinancialArray[index].erpStatus == null
            || this.financialcalForm.value.tenorFinancialArray[index].erpStatus === undefined
            || this.financialcalForm.value.tenorFinancialArray[index].erpStatus === '') {
            this.financialService.req_erpStatus[index] = true;
            this.returnVal = false;
        } else {
          this.financialService.req_erpStatus[index] = false;
        }
        if (!this.isValidDate(this.financialcalForm.value.tenorFinancialArray[index].startDate)) {
            this.financialService.req_startDate[index] = true;
            this.returnVal = false;
            this.errorMessage = 'Start date should be within fiscal year';
            this.errorModal = true;
            setTimeout(() => {
              this.errorModal = false;
            }, 3000);
        } else {
          this.financialService.req_startDate[index] = false;

          if (!this.isValidDate(this.financialcalForm.value.tenorFinancialArray[index].endDate)) {
            this.financialService.req_endDate[index] = true;
            this.returnVal = false;
            this.errorMessage = 'End date should be within fiscal year';
            this.errorModal = true;
            setTimeout(() => {
              this.errorModal = false;
            }, 3000);
          } else {
            this.financialService.req_endDate[index] = false;
          }
        }

      }
    }

    // if ((this.financialCalendarModel.fiscalYear === '' || this.financialCalendarModel.fiscalYear === undefined
    //   || this.financialCalendarModel.fiscalYear === null) || (this.financialCalendarModel.startMonth === ''
    //     || this.financialCalendarModel.startMonth === undefined || this.financialCalendarModel.startMonth === null)
    //   || (this.financialCalendarModel.endMonth === '' || this.financialCalendarModel.endMonth === undefined
    //     || this.financialCalendarModel.endMonth === null) || (this.financialCalendarModel.startYear === ''
    //       || this.financialCalendarModel.startYear === undefined || this.financialCalendarModel.startYear === null)
    //   || (this.financialCalendarModel.endYear === '' || this.financialCalendarModel.startYear === undefined
    //     || this.financialCalendarModel.endYear === null)
    // ) {


    //   if ((this.financialCalendarModel.fiscalYear && this.financialCalendarModel.fiscalYear !== null
    //     && this.financialCalendarModel.fiscalYear !== ''
    //     && this.financialCalendarModel.fiscalYear !== undefined)
    //     //  && this.financialCalendarModel.startYear === null || this.financialCalendarModel.endYear === null
    //   ) {
    //     this.financialService.setFiscalYear(this.financialCalendarModel.fiscalYear);
    //     const spltval = this.financialCalendarModel.fiscalYear.split('-', 3);
    //     this.financialcalForm.patchValue({ startYear: spltval[0], endYear: spltval[1] });
    //   }

    //   this.errorMessage = 'You have not provided values for all mandatory attributes. Please check';
    //   this.errorModal = true;
    //   setTimeout(() => {
    //     this.errorModal = false;
    //   }, 3000);
    //   return false;
    // }

    // if (financialList >= 1) {
    //   for (let i = 0; i < financialList; i++) {

    //     if ((this.financialcalForm.value.tenorFinancialArray[i].product === null)
    //       || (this.financialcalForm.value.tenorFinancialArray[i].startDate === undefined)) {
    //       this.val_product[i] = true;

    //       // this.errorMessage = 'You have not provided values for all mandatory attributes. Please check';
    //       // this.errorModal = true;
    //       // setTimeout(() => {
    //       //   this.errorModal = false;
    //       // }, 3000);
    //       // return false;
    //     } else {
    //       this.val_product[i] = false;
    //     }

    //   }
    // }
    return this.returnVal;
  }

  onHiddenReset() {
    this.isReset = false;
  }
  getNumberofDays(strtdate, enddate) {

    const startdte = new Date(strtdate);
    const enddte = new Date(enddate);
    const timeDiff = Math.abs(startdte.getTime() - enddte.getTime());
    const totaldiff = Math.ceil(timeDiff / (1000 * 3600 * 24));
    return totaldiff;

  }

  isvalidDays(diffdays, leapyr) {
    const isleapyr = (leapyr % 4 === 0) ? true : false;

    if (isleapyr === true) {
      if (diffdays > 366) {
        return false;
      }
    } else {
      if (diffdays > 365) {
        return false;
      }

    }
    return true;
  }


  getErrorMsg(erromsg) {
    this.errorMessage = erromsg;
    this.errorModal = true;
    setTimeout(() => {
      // this.financialcalForm.reset();
      this.errorModal = false;
    }, 3000);
    return false;
  }



  save() {

    this.financialcalForm.value.action = 'save';
    this.addFinancialCalendar();
  }

  submit() {
    this.financialcalForm.value.action = 'submit';
    this.addFinancialCalendar();
  }

  resetCancel($event) {
    this.resetModal.hide();
  }

  resetConfirm($event) {
    this.financialcalForm.reset();
    this.resetModal.hide();
  }

  formReset() {
    this.val_fiscalyear = false;
    this.isvalidReset();
  }

  monthSelect(val) {
  }



  validateNumericsOnly(keyEvent, val) {
    const result = (keyEvent.which) ? keyEvent.which : keyEvent.keyCode;
    // let hypenvalue:Number[] = [];
    console.log('result>>' + result);
    if (result === 110 || result === 190 || result === 189 || result === 38 || result === 40) {
      return true;
    } else if (result === 17 || result === 67 || result === 86) {
      // alert('CTRL + C or CTRL + V are not allowed');
      this.errorMessage = 'CTRL + C or CTRL + V are not allowed for Fiscal Year';
      this.errorModal = true;
      setTimeout(() => {
        this.errorModal = false;
      }, 3000);
      return false;
    } else if (result !== 46 && result > 31 && (result < 48 || result > 57)) {
      // alert('Enter only numeric and Decimals');
      this.errorMessage = 'Enter only numeric and ' + '"-" character for Fiscal Year';
      this.errorModal = true;
      setTimeout(() => {
        this.errorModal = false;
      }, 3000);
      return false;
    }/*else if ( result === 13) {
         // this.fillYears(val);
         // console.log(val.value.fiscalYear);
       // this.selectYear(val);
        this.financialService.setFiscalYear(val.value.fiscalYear);
      }*/


    return true;
  }



  search(event, partUrl) {
    // this.suggestions = new Suggestion();
    if (event.target.value.trim() !== '') {
      this.subscription = this.financialService.getSearchResults(event.target.value, partUrl).subscribe
        (data => { this.financialService.fiscalyearData = data });
    } else {
      // this.suggestions[partUrl] = [];
    }
  }

  selectYear(val) {
    // console.log(val.value.fiscalYear);
    this.financialService.setFiscalYear(val.value.fiscalYear);
    this.fillYears(val);
  }


  fillYears(val) {
    this.startyear = [];
    this.endyear = [];
    const dte = new Date().getFullYear();

    if (val.value.fiscalYear === null || val.value.fiscalYear === '' || val.value.fiscalYear === undefined) {
      return false;
    }

    if (val && val.value && val.value.fiscalYear && val.value.fiscalYear !== null && val.value.fiscalYear.trim().length !== 0 &&
      val.value.fiscalYear.trim().length > 9 || val.value.fiscalYear.trim().length < 9 && val.value.fiscalYear.charAt('-') !== 2) {
      // alert( 'please enter the follow format YYYY-YYYY');
      this.errorMessage = 'please enter the follow format YYYY-YYYY for Fiscal Year';
      this.errorModal = true;
      setTimeout(() => {
        this.financialcalForm.reset();
        this.errorModal = false;
      }, 3000);
      return false;
    }

    if (val.value.fiscalYear !== null && val && val.value && val.value.fiscalYear.trim().length === 9) {
      const spltval = val.value.fiscalYear.split('-', 3);
      if (spltval !== null) {
        const strtyear = spltval[0];
        const endyr = spltval[1];
        // tslint:disable-next-line:radix
        const startyrint = parseInt(strtyear.toString());
        // tslint:disable-next-line:radix
        const endyrint = parseInt(endyr.toString());
        const diff = endyrint - startyrint;

        if (startyrint !== endyrint) {
          // tslint:disable-next-line:radix
          if (parseInt(strtyear.toString()) < parseInt(endyr.toString())) {
            if (diff !== 1) {
              // alert('Fiscal year gap shoul be one year');
              this.errorMessage = 'Fiscal year gap should be one year';
              this.errorModal = true;
              setTimeout(() => {
                this.financialcalForm.reset();
                this.errorModal = false;
              }, 3000);
              return false;
            }
          } else {
            // alert('End year greater than start year');
            this.errorMessage = 'Years given as Invalid for Fiscal Years';
            this.errorModal = true;
            setTimeout(() => {
              this.financialcalForm.reset();
              this.errorModal = false;
            }, 3000);
            return false;
          }
        } else {
          this.errorMessage = 'Years should not be same for Fiscal Years';
          this.errorModal = true;
          setTimeout(() => {
            this.financialcalForm.reset();
            this.errorModal = false;
          }, 3000);
          return false;

        }

        if (strtyear !== null && strtyear.length === 4) {
          if (val && val.value && val.value.fiscalYear && val.value.fiscalYear.trim().length !== 0) {
            this.subscription = this.financialService.uniqueFiscalYear(val.value.fiscalYear).subscribe(data => {

              if (data != null) {
                this.val_fiscalyear = false;
                // tslint:disable-next-line:radix
                if (parseInt(data.body) === 1) {
                  this.errorMessage = val.value.fiscalYear + ' already exists, please provide unique value !';
                  this.errorModal = true;
                  setTimeout(() => {
                    this.financialcalForm.reset();
                    this.errorModal = false;
                  }, 3000);
                  return false;
                }
              }
            }, error => { throw error; });
          }

          for (let i = 0; i < 1; i++) {
            // tslint:disable-next-line:radix
            const temp = parseInt(strtyear.toString()) + i;
            this.startyear.push(temp.toString());
            // console.log(this.startyear)
          }

        }

        if (endyr !== null && endyr.length === 4) {
          console.log('fiscal year  ' + val.value.fiscalYear);
          this.financialService.setFiscalYear(val.value.fiscalYear);
          for (let i = 0; i < 1; i++) {
            // tslint:disable-next-line:radix
            const temp = parseInt(endyr.toString()) + i;
            this.endyear.push(temp.toString());
            // console.log(this.endyear)
          }
        }
      }
    }
  }

  onCancel() {
    this.router.navigate([financialCalendar]);
  }

  ngOnDestroy() {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }

  fiscalYearSelect(event) {
    this.subscription = this.financialService.uniqueFiscalYear(event).subscribe(data => {
      if (data != null) {
        // tslint:disable-next-line:radix
        if (parseInt(data.body) === 1) {
          this.errorMessage = event + ' already exists, please provide unique value !';
          this.errorModal = true;
          setTimeout(() => {
            this.financialcalForm.reset();
            this.errorModal = false;
          }, 3000);
          return false;
        }
      }
    }, error => { throw error; });
  }

  getProductList() {
    // const link = this.masterSetupService.getChildObject('Create SECPACK.GET');
    this.subscription = this.financialService.getProductList('').subscribe(data => {
      if (data.body !== null && data.body !== '') {
        for ( let i = 0; i < data.body.length; i++  ) {
          this.financialService.productMappingList.push({'fkProdId': data.body[i].prodId, 'fkProdName': data.body[i].prodName});
        }
      }
    }, error => { throw error; });
  }

  setDefaultValues() {
    this.val_fiscalyear = false;
    this.val_startyear = false;
    this.val_startmonth = false;
    this.val_endyear = false;
    this.val_endmonth = false;
    this.financialService.req_monthShortCode = [];
    this.financialService.req_productId = [];
    this.financialService.req_startDate = [];
    this.financialService.req_endDate = [];
    this.financialService.req_ctrmStatus = [];
    this.financialService.req_erpStatus = [];
  }

  isValidDate(date) {
    const year = date.getFullYear().toString();
    if (year !== this.financialcalForm.value.startYear && year !== this.financialcalForm.value.endYear) {
      return false;
    } else {
      return true;
    }
  }

}
