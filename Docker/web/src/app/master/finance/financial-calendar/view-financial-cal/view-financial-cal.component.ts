import { Component, OnInit, OnDestroy, Output, ViewChild, EventEmitter, ViewEncapsulation } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';
import { FinancialCalendar } from '../model/financial-calendar';
import { ModalDirective } from 'ngx-bootstrap';
import { FinancialCalendarService } from '../service/financial-calendar.service';
import { ActivatedRoute, Router } from '@angular/router';
import { financialCalendar } from './../../../../shared/interface/router-links';
import { ResponseData } from '../../../../shared/interface/responseData';
import { ForwardTenor } from '../model/financial-calendar';


@Component({
  selector: 'app-view-financial-cal',
  templateUrl: './view-financial-cal.component.html',
  styleUrls: ['./view-financial-cal.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class ViewFinancialCalComponent implements OnInit, OnDestroy {
  financeDetail: FinancialCalendar;
  getDeatils: FinancialCalendar;
  subscription: Subscription;
  financialCalendar: FinancialCalendar[];
  financialCal: FinancialCalendar;
  isconfirmSuccessModal;
  isConfirmPopupModal;
  dialogValue: string;
  successDialog: string;
  primaryid: string;
  errorMessage: string;
  errorModal;
 public status: any = {
    isFirstOpen: true,

    isFirstDisabled: false,
  };
  // tenorvalues: ForwardTenor;
  tenorArray = [];
  // edit;
  tenorlength: number;
  index = 0;
  @ViewChild('successModal') public successModal: ModalDirective;
  @ViewChild('confirmSuccessModal') public confirmSuccessModal: ModalDirective;
  // financialdata: ResponseData;
  constructor(private route: ActivatedRoute, private router: Router, private financialService: FinancialCalendarService) { }

  ngOnInit() {
    this.getDeatils = this.financialService.getRowData();
    if (this.getDeatils !== undefined && this.getDeatils !== null) {
      // console.log ('In View ' + JSON.stringify(this.financeDetail));
      this.subscription = this.financialService.getFinancialByIdJSON(this.getDeatils).
        subscribe(getData => {
        //  console.log(JSON.stringify(getData.body));

          this.financialCal = getData.body[0];
          if (this.financialCal.tenorFinancialArray !== null && this.financialCal.tenorFinancialArray !== undefined) {
            this.tenorlength = this.financialCal.tenorFinancialArray.length
            this.tenorArray = this.financialCal.tenorFinancialArray;
            // this.financialCal.tenorFinancialArray[1].startDate;
          }

         // console.log('Response Data ', this.financialCal);
        //  console.log(this.financialCal.tenorFinancialArray);

          // const financialList = Number(JSON.stringify(this.financialCal.controls.tenorFinancialArray))


        });
    }

  }

  ngOnDestroy() {

  }

  backtoView() {
    this.router.navigate([financialCalendar]);
  }

 /* edit() {
     this.errorMessage = 'Edit part implementation is Work in progress';
          this.errorModal = true;
          setTimeout(() => {
          this.errorModal = false;
          }, 3000);
          return false;
  }*/

  onSubmitBtnClick() {
alert(this.financialCal.statusName);
   if (this.financialCal.statusName === 'Draft') {
     // this.dialogValue = 'Do you want Active the Financial Calendar ' + this.financialCal.fiscalYear;
  //   this.isConfirmPopupModal = true;
      this.subscription = this.financialService.activeFinancialCalendar(this.financialCal.pkFinCalId)
      .subscribe(data => {
     //   this.successModal.hide();
        this.successDialog = 'You have successfully Activated financial calendar ' + this.financialCal.fiscalYear;
         this.isconfirmSuccessModal = true;
         setTimeout(() => { this.confirmSuccessModal.hide();
           this.router.navigate([financialCalendar]);
     }, 1000);

      });
   }
  }

  onHidden() {
    this.isconfirmSuccessModal = false;
  }

  onHiddenpopup() {
    this.isConfirmPopupModal = false;
  }

  openConfirmAction(status) {
    // alert(status);\

    if (status === 'Draft') {
      this.dialogValue = 'Do you want Delete the Financial Calendar ' + this.financialCal.fiscalYear;
    } else if (status === 'Active') {
      this.dialogValue = 'Do you want DeActivate the Financial Calendar ' + this.financialCal.fiscalYear;
    } else if (status === 'InActive') {
      this.dialogValue = 'Do you want Re-Activate the Financial Calendar ' + this.financialCal.fiscalYear;
    }
    this.isConfirmPopupModal = true;

  }

  closedeletepopup() {
    this.successModal.hide();
  }

  status_update() {
    // alert(this.financialCal.pkFinCalId);
    //  alert(this.financialCal.statusName);
    this.subscription = this.financialService.deleteFinancialCalendar(this.financialCal.pkFinCalId, this.financialCal.statusName)
      .subscribe(deletedStatus => {
        this.successModal.hide();
        if (this.financialCal.statusName === 'Active') {
          this.successDialog = 'You have successfully deactivated financial calendar ' + this.financialCal.fiscalYear;
        } else if (this.financialCal.statusName === 'InActive') {
          this.successDialog = 'You have successfully reactivated financial calendar ' + this.financialCal.fiscalYear;
        } else if (this.financialCal.statusName === 'Draft') {
          this.successDialog = 'You have successfully deleted financial calendar ' + this.financialCal.fiscalYear;
        }
       this.isconfirmSuccessModal = true;

    setTimeout(() => {
            this.isconfirmSuccessModal = false;
           // this.confirmSuccessModal.hide();
             this.router.navigate([financialCalendar]);
     }, 1000);


      });

  }
}



