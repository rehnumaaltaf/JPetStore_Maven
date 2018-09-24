import { addTaxRate } from '../../../shared/interface/router-links';
import { Component, OnInit, OnDestroy, Output, ViewEncapsulation, Inject, ViewChild } from '@angular/core';
import { PlatformLocation, Location } from '@angular/common';
import { Subscription } from 'rxjs/Subscription';
import { Router } from '@angular/router';
import { MasterSetupService } from '../../master-setup/service/master-setup.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { ExcelExportData } from '@progress/kendo-angular-excel-export';
import { GridComponent, GridDataResult, DataStateChangeEvent, PageChangeEvent } from '@progress/kendo-angular-grid';
import { process, SortDescriptor, orderBy, GroupDescriptor, State } from '@progress/kendo-data-query';
// import { Observable } from 'rxjs/Rx';
import { CanComponentDeactivate } from '../../../shared/guards/can-deactivate-guard';
import { TaxRateInterface } from './tax-rate-interface';
//import { Link } from '../../../shared/interface/link';
import { TaxRateService } from '../tax-rate/service/tax-rate.service';
import { viewTaxRate } from '../../../shared/interface/router-links';
import { listingPageTaxRate } from '../../../shared/interface/router-links';
import { ConfirmationService } from '../../../shared/confirm-box/confirm.service';
import { MESSAGE_SUCCESS, MESSAGE_ERROR, ACTION_SAVE } from '../../../shared/interface/common.constants';
import { ACTION_SUBMIT, ACTION_UPDATE, ACTION_ACTIVE, ACTION_DEACTIVE,ACTION_INACTIVE,ACTION_DRAFT } from '../../../shared/interface/common.constants';
import { MessageModel } from '../../../shared/message/message.model';

@Component({
  selector: 'app-tax-rate',
  templateUrl: './tax-rate.component.html',
  styleUrls: ['./tax-rate.component.css']
})
export class TaxRateComponent implements OnInit, OnDestroy, CanComponentDeactivate {
  

  isActionPerformed = false;
  //private link: Link;
  group: GroupDescriptor[] = [{ field: 'statusName' }];
  subscription: Subscription;
  sort: SortDescriptor[] = [];
  isShowModal: boolean;
  private taxRatedetails;
  data: TaxRateInterface[];
  taxRateCode: string;
  isupdateModal: boolean;
  public taxCodeUID;
  headerTermCode: string;
  headerTermName: string;
  headerTermCountry: string;
  baseTermCreditRiskListing: string;
  baseTermLCListing: string;
  headerTermRef: string;
  isDeactivatePopupModal: Boolean = false;
  headerTermLine: string;
  state: State = {
    skip: 0,
    take: 1000
  };
  public taxRateForSuccess;
  isDeletePopupModal: Boolean = false;
  public isCannotDeletePopup;
  public taxCode;
  public taxName;
  gridData: GridDataResult;
  public status: any = {
    isFirstOpen: true,
    isFirstDisabled: false
  };
  constructor(private route: ActivatedRoute, private router: Router,
    public taxRateService: TaxRateService, public masterSetupService: MasterSetupService,
    platformlocation: PlatformLocation, location: Location, private confirmationService: ConfirmationService) {

    // this.getListingPageData();
    this.loadingTaxRateList();
    this.allData = this.allData.bind(this);
    platformlocation.onPopState(() => {
    });
  }
  ngOnInit() {
    this.isDeletePopupModal = false;
    this.taxRateCode = this.taxRateService.taxCodeForPayment;
    this.route.queryParams.subscribe(params => {
      // Defaults to 0 if no query param provided.
      this.taxName = this.taxRateService.originCodeParam;
    });
    


  }
  changeShowStatus() {
    this.router.navigate([addTaxRate], { queryParams: { 'elem': 5 } });
  }
  allData(): ExcelExportData {
    const result: ExcelExportData = {
      group: this.group
    };

    return result;
  }

  loadingTaxRateList() {

    this.subscription = this.taxRateService.getListingPageFromJson().subscribe(addTaxRate => {
      this.taxRateService.taxRatedetails = addTaxRate.body;
      this.headerTermCode = "TAX CODE";
      this.headerTermName = "TAX DESCRIPTION";
      this.headerTermCountry = "TAX COUNTRY";
      this.headerTermRef = "GOVT TAX REF";
      this.headerTermLine = "TAX BY LINE";

    },
      error => { throw (error) },
      () => console.log('Finished')
    );
  }

  viewById(event) {
    this.taxRateService.viewbyIdParam = event;
    this.router.navigate([viewTaxRate]);
  }

  checkorUnCheckAll(ele) {
    const checkboxes = document.getElementsByTagName('input');
    if (ele.target.checked) {
      for (let intval = 0; intval < checkboxes.length; intval++) {
        if (checkboxes[intval].type === 'checkbox') {
          checkboxes[intval].checked = true;
        }
      }
    } else {
      for (let i = 0; i < checkboxes.length; i++) {
        if (checkboxes[i].type === 'checkbox') {
          checkboxes[i].checked = false;
        }
      }
    }
  }
  editTaxRate(event) {
    this.taxRateService.paramPassingId = event.taxCodeUID;
    this.subscription = this.taxRateService.getSelectedTaxRateByID().subscribe(taxRateResponse => {
      this.taxRateService.taxRatePaymentdetails = taxRateResponse.body;
      this.taxRateService.selectTaxRateEdit = taxRateResponse.body.taxRateDetails;
      this.router.navigate([addTaxRate], { queryParams: { 'elem': 6 } });
    },
      error => { throw error });
  }

  taxrate_delete(params) {
    this.taxRateForSuccess = params.taxName;
    this.status = (params.status).toUpperCase();
    this.taxCodeUID = params.taxCodeUID;
    this.isDeletePopupModal = true;
    if (this.status === ACTION_DRAFT) {
       this.confirmationService.confirm({
        message: 'Confirm.Common.Delete',
        placeHolder: [this.taxRateForSuccess],
        accept: (event) => {
            this.conf_delete(this.taxCodeUID);
        },
        reject: (event) => {
        }
    });
    } else if (this.status === ACTION_ACTIVE) {
		
      this.confirmationService.confirm({
        message: 'Confirm.Common.Deactivate',
        placeHolder: [this.taxRateForSuccess],
        accept: (event) => {
            this.conf_delete(this.taxCodeUID);
        },
        reject: (event) => {
        }
    });
    } else if (this.status === ACTION_INACTIVE) {
       this.confirmationService.confirm({
        message: 'Confirm.Common.Reactivate',
        placeHolder: [this.taxRateForSuccess],
        accept: (event) => {
            this.conf_delete(this.taxCodeUID);
        },
        reject: (event) => {
        }
    });
    }
  }

  conf_delete(params) {
	  const param = this.taxCodeUID;
    this.subscription = this.taxRateService.deletetaxRateById
      (param)
	   .subscribe(deletedStatus => {
        if (this.status === ACTION_ACTIVE) {			
		this.taxRateService.messages = { severity: MESSAGE_SUCCESS, summary: 'Message.Common.Deactivate',
         placeHolder: ['Tax Rate Name', this.taxRateForSuccess] };
        } else if (this.status === ACTION_INACTIVE) {			
         this.taxRateService.messages = { severity: MESSAGE_SUCCESS, summary: 'Message.Common.Reactivate',
         placeHolder: ['Tax Rate Name', this.taxRateForSuccess] };
        } else if (this.status === ACTION_DRAFT) {			
          this.taxRateService.messages = { severity: MESSAGE_SUCCESS, summary: 'Message.Common.Delete',
         placeHolder: ['Tax Rate Name', this.taxRateForSuccess] };
        }     
        this.loadingTaxRateList();
      },
      error => { throw error });
  }

  closedeletepopup() {
    this.isDeletePopupModal = false;
    this.isCannotDeletePopup = false;
  }
  onHiddenpopup() {
    this.isDeletePopupModal = false;
    this.isDeactivatePopupModal = false;
    this.isupdateModal = false;
  }

  canDeactivate(): boolean {
    return this.isActionPerformed;
  }

  ngOnDestroy() {
    // prevent memory leak when component destroyed
    if (this.subscription != null) {
      this.subscription.unsubscribe();
    }
  }

}
