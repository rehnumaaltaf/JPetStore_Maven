import { Component, OnInit, OnDestroy, Output, ViewChild } from '@angular/core';
import { FinancialCalendar } from './model/financial-calendar';
import { ActivatedRoute, Router } from '@angular/router';
import { addfinancialCalendar } from '../../../shared/interface/router-links'
import { FinancialCalendarService } from './service/financial-calendar.service';
import { MasterSetupService } from '../../master-setup/service/master-setup.service';
import { Subscription } from 'rxjs/Subscription';
import { ExcelExportData } from '@progress/kendo-angular-excel-export';
import { GridComponent, GridDataResult, DataStateChangeEvent, PageChangeEvent } from '@progress/kendo-angular-grid';
import { process, SortDescriptor, orderBy, GroupDescriptor, State } from '@progress/kendo-data-query';
import { ModalDirective } from 'ngx-bootstrap';
import { viewfinancialCalendar } from '../../../shared/interface/router-links';

@Component({
  selector: 'app-financial-calendar',
  templateUrl: './financial-calendar.component.html',
  styleUrls: ['./financial-calendar.component.css']
})
export class FinancialCalendarComponent implements OnInit, OnDestroy {
  financialCalendar: FinancialCalendar[];
  subscription: Subscription;
  public success;
  public isShowModal: boolean;
  public message;
  showModal: boolean;
  private data;
  isDeletePopupModal: boolean;
  isupdateModal: boolean;
  deleteSuccessModal: boolean;
  dialogValue: string;
  financialId: string;
  financialStatus: string;
  fiscalyear: string;
  deactivateSuccessDialog: string;
  @Output() loadingData: Boolean = true;
  multiple: Boolean = false;
  gridView: GridDataResult;
  allowUnsort: Boolean = true;
  sort: SortDescriptor[] = [];
  gridData: GridDataResult;
  state: State = {
    skip: 0,
    take: 1000
  };
  allItemsChecked: Boolean = false;
  status: string;
  @ViewChild('deletesuccessModal') public deletesuccessModal: ModalDirective;
  @ViewChild('successModal') public successModal: ModalDirective;
  groups;

 constructor(private router: Router, public financialService: FinancialCalendarService,
    private masterSetupService: MasterSetupService, private route: ActivatedRoute) {
    this.allData = this.allData.bind(this);
  }

  ngOnInit() {

    this.route.queryParams.subscribe(params => {
    this.success = +params['success'];
    if (this.success === 1 && this.success !== 'NaN') {
        this.message = 'Data Saved Successfully!!!';
        this.showModal = true;
        setTimeout(() => { this.showModal = false; }, 3000);
      }
    });
    this.loadingfinancialCalendarList();
    this.isDeletePopupModal = false;
    this.deleteSuccessModal = false;

  }

  allData(): ExcelExportData {
    const result: ExcelExportData = {
      data: process(this.financialCalendar, this.state).data,
      // group: this.group
    };

    return result;
  }

  financialAddView() {
   this.router.navigate([addfinancialCalendar]);
  }

 ngOnDestroy() {
    this.subscription.unsubscribe();
  }


  loadfinancialDetails(): void {
    this.gridView = {
      data: process(this.financialCalendar, this.state).data,
      total: this.financialCalendar.length
    };
  }

  public dataStateChange(state: DataStateChangeEvent): void {
    this.state = state;
    this.gridData = process(this.financialCalendar, this.state);
  }

 pageChange(event: PageChangeEvent): void {
    this.state.skip = event.skip;
    this.loadfinancialDetails();
  }

  goToViewPage(rowData) {
    this.financialService.setRowData(rowData)
    this.router.navigate([viewfinancialCalendar]);
   console.log(JSON.stringify(rowData))
  }

  openConfirmAction(val) {
}

  loadingfinancialCalendarList() {
// const link = this.masterSetupService.getChildObject('View Uom.GET');
    this.loadingData = true;
    this.subscription = this.financialService.getFinancialCalendarDetailsJSON('data').subscribe(addfinanceDetail => {
    this.financialCalendar = <FinancialCalendar[]>addfinanceDetail.body;
    this.loadfinancialDetails();
    this.loadingData = false;
   if (this.deletesuccessModal) {
        setTimeout(() => { this.deletesuccessModal.hide(); }, 2000);
      }
    }, error => {
     // console.log(error)
      this.loadingData = false;
    });
  }

financialDetailView(values, financialid, status) {
  return true;
  }

selectedrow() {
}
DeleteFincaneDetails(keyid, fiscalyear, status, i) {
    this.financialService.setFiscalYears(fiscalyear);
    this.financialService.setStatusname(status);
    this.financialService.setFinacialPkId(keyid);

    if (status === 'Active') {
      this.dialogValue = 'Are you want DeActivate Financial Calendar ' + fiscalyear + ' ?';
    }
    if (status === 'Draft') {
      this.dialogValue = 'Are you want Delete Financial Calendar  ' + fiscalyear + ' ?';
    }
    if (status === 'InActive') {
      this.dialogValue = 'Are you want Re-Activate Financial Calendar  ' + fiscalyear + ' ?';
    }
    this.isDeletePopupModal = true;

  }

  conf_delete() {
    this.financialId = this.financialService.getFinacialPkId();
    this.financialStatus = this.financialService.getStatusname();
    this.fiscalyear = this.financialService.getFiscalYears();
    this.subscription = this.financialService.deleteFinancialCalendar(this.financialId, this.financialStatus)
      .subscribe(deletedStatus => {
       this.successModal.hide();
      if (this.financialStatus === 'Active') {
          this.deactivateSuccessDialog = 'You have successfully deactivated financial calendar ' + this.fiscalyear;
        } else if (this.financialStatus === 'InActive') {
          this.deactivateSuccessDialog = 'You have successfully reactivated financial calendar ' + this.fiscalyear;
       } else if (this.financialStatus === 'Draft') {
          this.deactivateSuccessDialog = 'You have successfully deleted financial calendar ' + this.fiscalyear;
        }
         this.deleteSuccessModal = true;
        this.loadingfinancialCalendarList();
        },
      error => { throw error });
  }

  onHidden() {
   this.deleteSuccessModal = false;
  }

  onHiddenpopup() {
   this.isDeletePopupModal = false;
  }



  closedeletepopup() {
    this.successModal.hide();
    // this.isDeletePopupModal = false;
  }

  saveUpdatedFinance(rowData) {
}

  cancelFinancialUpdate() {
}

  checkAllClicked(e) {
   // console.log('checkAllClicked', e);
   if (e.target.checked) {
      this.allItemsChecked = true;
      for (let i = 0; i < this.financialCalendar.length; i++) {
        this.financialCalendar[i].checked = true;
      }
    } else {
      this.allItemsChecked = false;
      for (let i = 0; i < this.financialCalendar.length; i++) {
        this.financialCalendar[i].checked = false;
      }
    }
  }
  selectUnSelectAllChecked(e) {
    if (!e.target.checked) {
      this.allItemsChecked = false;
    } else {
      let selected;
      selected = 0;
      for (let i = 0; i < this.financialCalendar.length; i++) {
        if (!this.financialCalendar[i].checked) {
          this.allItemsChecked = false;
          break;
        } else {
          selected++;
        }
      }
      if (selected === this.financialCalendar.length) {
        this.allItemsChecked = true;
      }
    }
  //  console.log(this.allItemsChecked);
  }


}
