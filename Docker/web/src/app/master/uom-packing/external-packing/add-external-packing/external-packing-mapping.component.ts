import { Component, OnInit, Output, EventEmitter, Input , OnChanges} from '@angular/core';
import { FormGroup } from '@angular/forms';
// import { FinancialCalendarService } from '../service/financial-calendar.service'
// import { FinancialCalendar } from '../model/financial-calendar';
import { ExternalPacking } from '../model/external-packing';
import { SharedModule } from '../../../../shared/shared.module';
import { BrowserModule } from '@angular/platform-browser';
import { Subscription } from 'rxjs/Subscription';
import { ExternalPackingService } from '../service/external-packing.service';
@Component({
  moduleId: module.id,
  selector: 'app-external-packing-mapping',
  templateUrl: 'external-packing-mapping.component.html',
})
export class ExternalPackingMappingComponent implements OnChanges {
//   public monthList: string[] = ['January', 'February', 'March', 'April', 'May', 'June', 'July',
//     'August', 'September', 'October', 'November', 'December'];
//   public status: string[] = ['Open', 'Closed'];
//   errorMessage: string;
//   errorModal: boolean;
//   subscription: Subscription;
//   totaldays = 0;
//   tempmonth: string[] = [];
//   ERPStatus: String;
//   CTRMStatus: String;
//   endDate: Date;
//   startDate: Date;
//   monthName: String;
//   // tslint:disable-next-line:no-input-rename
// tslint:disable-next-line:no-input-rename
@Input('group')
public extPackMappingForm: FormGroup;
//   @Input() title: Number;
@Input() ArrayIndex: number;
@Input() AddEditId: number;
@Input() extPackingModel: ExternalPacking;

constructor(public externalPackingService: ExternalPackingService) { }
ngOnChanges(): void {
  if (this.AddEditId !== 0) {
    if (this.extPackingModel.secPackAssocList[this.ArrayIndex] !== undefined) {
      this.extPackMappingForm.controls['fkOriginId'].setValue(this.extPackingModel.secPackAssocList[this.ArrayIndex].fkOriginId);
      this.extPackMappingForm.controls['fkProdId'].setValue(this.extPackingModel.secPackAssocList[this.ArrayIndex].fkProdId);
      this.extPackMappingForm.controls['fkPrimaryPackingTypeId'].setValue(this.extPackingModel.secPackAssocList[this.ArrayIndex]
      .fkPrimaryPackingTypeId);
      this.extPackMappingForm.controls['countOfPrimary'].setValue(this.extPackingModel.secPackAssocList[this.ArrayIndex].countOfPrimary);
    }
  }
}
//  onChange(month) {
//       const fiscalyr = this.financialService.getFiscalYear();
//    /* if (this.tempmonth.indexOf(month) !== -1) {
//               alert('error');
//             } else {
//               this.tempmonth.push(month);
//             } */

//  /*  if ((fiscalyr) && (month)) {
//       this.financialService.uniqueMonthName(fiscalyr, month).subscribe(data => {

//         if (data.errorMessage === null) {
//           return false;
//         }

//         if (data != null) {
//           console.log(data.body);
//           // tslint:disable-next-line:radix
//           if (parseInt(data.body) === 1) {

//             this.errorMessage = month + ' already exists, please provide unique name !';
//             this.errorModal = true;
//             setTimeout(() => {
//               this.errorModal = false;
//             }, 3000);
//             return false;
//           }
//         }
//       }, error => { throw error });

//       this.tenorMappingForm.patchValue({ startDate: '' });
//       this.tenorMappingForm.patchValue({ endDate: '' });
//     }*/
//      this.tenorMappingForm.patchValue({ startDate: '' });
//       this.tenorMappingForm.patchValue({ endDate: '' });
//   }

//   fiscalyearSplit(fiscalyear, year) {
//     if (fiscalyear) {
//       const fiscalyr: string = fiscalyear.split('-');
//       if (year === 'S') {
//         return +fiscalyr[0];
//       }
//       if (year === 'E') {
//         return +fiscalyr[1];
//       }
//     }

//   }


//   getYearMonthDate(date, criteria) {
//     if (date) {
//       const dateArr: string = date.split('-');
//      if (criteria === 'D') {
//         return +dateArr[2];
//       } else if (criteria === 'Y') {
//         return +dateArr[0];
//       } else if (criteria === 'M') {
//         return +dateArr[1];
//       }
//     } else {
//       return null;
//     }
//   }


//   isFiscalYearValid(fiscalyear) {
//     if (fiscalyear === null || fiscalyear === undefined || fiscalyear === '') {
//       return false;
//     }
//   }

//   checkstartYearvalid(startyear, endyear) {
//     if (startyear > endyear) {
//       return false;
//     }
//     return true;
//   }

// getErrorMsg(erromsg) {
//     this.errorMessage = erromsg;
//     this.errorModal = true;
//     setTimeout(() => {
//      this.errorModal = false;
//     }, 3000);
//     return false;
//   }

//   getNumberofDays(strtdate, enddate) {
//     const startdte = new Date(strtdate);
//     const enddte = new Date(enddate);
//     const timeDiff = Math.abs(startdte.getTime() - enddte.getTime());
//     const totaldiff = Math.ceil(timeDiff / (1000 * 3600 * 24));
//     return totaldiff;
// }

//   isvalidDays(diffdays, leapyr) {
//     const isleapyr = (leapyr % 4 === 0) ? true : false;

//     if (isleapyr === true) {
//       if (diffdays > 366) {
//         return false;
//       }
//     } else {
//       if (diffdays > 365) {
//         return false;
//       }

//     }
//     return true;
//   }


// checkwithfiscalyear(fiscalstrtyear, fiscalendyear, startyear, endyear, monthname) {
//     // let msg: string;
//     const strtyr = this.getYearMonthDate(startyear, 'Y');
//     const endyrs = this.getYearMonthDate(endyear, 'Y');
//     const strnmnth = this.getYearMonthDate(startyear, 'M');
//     const endmnth = this.getYearMonthDate(endyear, 'M');
//     const strtdte = this.getYearMonthDate(startyear, 'D');
//     const enddte = this.getYearMonthDate(endyear, 'D');
//     if (monthname === null) {
//         if (strtyr !== null && endyrs !== null) {
//         const isvalid = this.checkstartYearvalid(strtyr, endyrs);
//         if (isvalid === false) {
//           const validmsg = this.getErrorMsg(' Start Date Year And End Date Year given as Invalid ');
//           if (validmsg === false) {
//             this.tenorMappingForm.patchValue({ endDate: '' });
//             return false;
//           }
//          }
//       }

//       if (strtyr && (strtyr < fiscalstrtyear || strtyr > fiscalendyear)) {
//         const validmsg = this.getErrorMsg('Start Date Year should not greater or lesser than Fiscal Year');
//         if (validmsg === false) {
//           this.tenorMappingForm.patchValue({ startDate: '' });
//           return false;
//         }
//       }

//       if (endyrs && (endyrs > fiscalendyear) && endyrs !== fiscalstrtyear) {
//         const valmsg = this.getErrorMsg('End Date Year should not greater or lesser than Fiscal Year');
//         if (valmsg === false) {
//           this.tenorMappingForm.patchValue({ endDate: '' });
//           return false;
//         }

//       }

//       if (strtyr === endyrs && strnmnth > endmnth && strtyr !== null && endyrs !== null
//         && strnmnth !== null && endmnth !== null) {
//          const valmsg = this.getErrorMsg('Start Date Month should not greater than End Date Month');
//         if (valmsg === false) {
//            this.tenorMappingForm.patchValue({ endDate: '' });
//           return false;
//         }
//       }

//       if (strtyr === endyrs && strnmnth === endmnth && strtdte > enddte && strtyr !== null && endyrs !== null
//         && strnmnth !== null && endmnth !== null && strtdte !== null && enddte !== null) {
//        const valmsg = this.getErrorMsg('Start Date should not greater than End Date');
//         if (valmsg === false) {
//            this.tenorMappingForm.patchValue({ endDate: '' });
//           return false;
//         }
//       }

//       if (startyear !== null && endyear !== null && endyear !== 0) {
//          const days = this.getNumberofDays(startyear, endyear);
//         const validday = this.isvalidDays(days, this.getYearMonthDate(endyear, 'Y'));
//         if (validday === false) {
//           const valmsg = this.getErrorMsg(' Selected No of Days should not greater than Fiscal Year');
//           if (valmsg === false) {
//          //   this.tenorMappingForm.patchValue({ endDate: '' });
//             return false;
//           }
//         } else {
//           this.totaldays = days;
//         }
//       }
//      } else {
//         const monthno = +this.monthList.indexOf(monthname) + 1;
//        if (strtyr && (strtyr < fiscalstrtyear || strtyr > fiscalendyear)) {
//          const validmsg = this.getErrorMsg('Start Date Year should not greater or lesser than Fiscal Year');
//         if (validmsg === false) {
//           this.tenorMappingForm.patchValue({ startDate: '' });
//           return false;
//         }
//       }

//       if (endyrs && (endyrs > fiscalendyear) && endyrs !== fiscalstrtyear) {
//         const valmsg = this.getErrorMsg('End Date Year should not greater or lesser than Fiscal Year');
//         if (valmsg === false) {
//           this.tenorMappingForm.patchValue({ endDate: '' });
//           return false;
//         }
//       }

//        if (strnmnth !== monthno && strtyr !== null && strnmnth !== null) {
//        const valmsg = this.getErrorMsg('Start Date Month should not greater or lesser than Selected Month');
//         if (valmsg === false) {
//           this.tenorMappingForm.patchValue({ startDate: '' });
//           return false;
//         }
//       }

//       if (endmnth !== monthno && endyrs !== null && endmnth !== null) {
//           const valmsg = this.getErrorMsg('End Date Month should not greater or lesser than Selected Month');
//         if (valmsg === false) {
//           this.tenorMappingForm.patchValue({ endDate: '' });
//           return false;
//         }
//       }

//       if (strtyr !== endyrs && strtyr !== null && endyrs !== null) {
//        const valmsg = this.getErrorMsg('Start Date Year and End Date Year should be same');
//         if (valmsg === false) {
//           this.tenorMappingForm.patchValue({ endDate: '' });
//           return false;
//         }
//       }

//       if (strtyr === endyrs && strnmnth === endmnth && strtdte > enddte && strtyr !== null && endyrs !== null
//         && strnmnth !== null && endmnth !== null && strtdte !== null && enddte !== null) {
//             const valmsg = this.getErrorMsg('Start Date should not greater than End Date');
//         if (valmsg === false) {
//            this.tenorMappingForm.patchValue({ endDate: '' });
//           return false;
//         }
//       }

//        if (startyear !== null && endyear !== null && endyear !== 0) {
//             const days = this.getNumberofDays(startyear, endyear);
//             const totalNumberofdays = this.totaldays + days;
//            const validday = this.isvalidDays(totalNumberofdays, this.getYearMonthDate(endyear, 'Y'));
//         if (validday === false) {
//           const valmsg = this.getErrorMsg(' Selected No of Days should not greater than Fiscal Year');
//           if (valmsg === false) {
//          //   this.tenorMappingForm.patchValue({ endDate: '' });
//             return false;
//           }
//         } else {
//           this.totaldays = days;
//         }
//       }
//     }

//   }

//   validateStartDate(formvalue) {
//     this.tenorMappingForm.patchValue({ endDate: '' });
//     const fiscalyr = this.financialService.getFiscalYear();
//     const isvalid = this.isFiscalYearValid(fiscalyr);
//     if (isvalid === false) {
//       const validmsg = this.getErrorMsg('Please enter Fiscal Year');
//       if (validmsg === false) {
//         return false;

//       }
//     }

//     const strtyr = this.fiscalyearSplit(this.financialService.getFiscalYear(), 'S');
//     const endyr = this.fiscalyearSplit(this.financialService.getFiscalYear(), 'E');
//     // .replace('"' , '' )).replace('"' , '' );
//     console.log(typeof this.tenorMappingForm.value.startDate);
//     const startdte = (!this.tenorMappingForm.value.startDate)
//       ? (+this.tenorMappingForm.value.startDate) : this.tenorMappingForm.value.startDate;
//     const enddte = (!this.tenorMappingForm.value.endDate)
//       ? (+this.tenorMappingForm.value.endDate) : this.tenorMappingForm.value.endDate;
//     const month = this.tenorMappingForm.value.monthName;
//     this.checkwithfiscalyear(strtyr, endyr, startdte, enddte, month);
//   }


//   validateEndDate(formvalue) {
//     const fiscalyr = this.financialService.getFiscalYear();
//     const isvalid = this.isFiscalYearValid(fiscalyr);
//     if (isvalid === false) {
//       const validmsg = this.getErrorMsg('Please enter Fiscal Year');
//       if (validmsg === false) {
//         return false;
//       }
//     }

//     const strtyr = this.fiscalyearSplit(this.financialService.getFiscalYear(), 'S');
//     const endyr = this.fiscalyearSplit(this.financialService.getFiscalYear(), 'E');
//     console.log(typeof this.tenorMappingForm.value.startDate);
//     const startdte = (!this.tenorMappingForm.value.startDate)
//       ? (+this.tenorMappingForm.value.startDate) : this.tenorMappingForm.value.startDate;
//     const enddte = (!this.tenorMappingForm.value.endDate)
//       ? (+this.tenorMappingForm.value.endDate) : this.tenorMappingForm.value.endDate;
//     const month = this.tenorMappingForm.value.monthName;
//     this.checkwithfiscalyear(strtyr, endyr, startdte, enddte, month);
//    }
}
