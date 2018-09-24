import { Component, OnInit, OnDestroy, Output, ViewEncapsulation, ViewChild } from '@angular/core';
import { PlatformLocation, Location } from '@angular/common';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Subscription } from 'rxjs/Subscription';
import { Router } from '@angular/router';
import { ExternalPacking } from './model/external-packing';
import { MasterSetupService } from '../../master-setup/service/master-setup.service';
import { FormGroup, FormControl } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { SelectItem } from '../../../shared/interface/selectItem';
import { ResponseData } from '../../../shared/interface/responseData';
import { Link } from '../../../shared/interface/link';
import { master } from '../../../shared/interface/router-links';
import { ExcelExportData } from '@progress/kendo-angular-excel-export';
import { GridComponent, GridDataResult, DataStateChangeEvent, PageChangeEvent } from '@progress/kendo-angular-grid';
import { process, SortDescriptor, orderBy, GroupDescriptor, State } from '@progress/kendo-data-query';
import { CanComponentDeactivate } from '../../../shared/guards/can-deactivate-guard';
import { ExternalPackingService } from './service/external-packing.service';
import { viewSecondaryPacking } from '../../../shared/interface/router-links';
import { ModalDirective } from 'ngx-bootstrap';
import { AddSecondaryPacking } from '../../../shared/interface/router-links';
import { editSecondaryPacking } from '../../../shared/interface/router-links';
import { MESSAGE_SUCCESS, MESSAGE_ERROR } from '../../../shared/interface/common.constants';
import { MessageModel } from '../../../shared/message/message.model';

@Component({
  selector: 'app-external-packing',
  templateUrl: './external-packing.component.html',
  styleUrls: ['./external-packing.component.css']
})
export class ExternalPackingComponent implements OnInit, OnDestroy {
  subscription: Subscription;
  secondaryPacking: ExternalPacking[];
  packingModel: ExternalPacking = new ExternalPacking();
  packUpdateModel: ExternalPacking = new ExternalPacking();
  isActionPerformed = false;
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
  dialogValue: string;
  loadingData: boolean;
  packingId;
  packingStatus;
  packingCode;
  // successModal;
  isDeletePopupModal: boolean;
  deactivateSuccessDialog: string;
  deleteSuccessModal: boolean;
  isupdateModal: boolean;
  @ViewChild('deletesuccessModal') public deletesuccessModal: ModalDirective;
  @ViewChild('successModal') public successModal: ModalDirective;
  openEdit: Boolean = false;
  @ViewChild('editModal') public editModal: ModalDirective;
  message: string;
  UpdateModal = false;
  public success;
  public actionName;
  addModal: boolean;

  constructor(private router: Router, public packingService: ExternalPackingService,
    private masterSetupService: MasterSetupService, private route: ActivatedRoute) {
    // this.allData = this.allData.bind(this);
  }

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      // this.success = +params['success'];
      // if (this.success === 1 && this.success !== 'NaN') {
      //   console.log(this.packingService.actionName);
        // if (this.packingService.actionName === 'update') {
        //   this.message = 'Success: Secondary Packing Material \'' + this.packingService.secPackName + '\' Updated';
        // } else if (this.packingService.actionName === 'InActive') {
        //   this.message = 'Success: Secondary Packing Material \'' + this.packingService.secPackName + '\' Deactivated';
        // } else if (this.packingService.actionName === 'Active' || this.packingService.actionName === 'SUBMIT') {
        //   this.message = 'Success: Secondary Packing Material \'' + this.packingService.secPackName + '\' Activated';
        // } else if (this.packingService.actionName === 'Draft') {
        //   this.message = 'Success: Secondary Packing Material \'' + this.packingService.secPackName + '\' Saved';
        // } else if (this.packingService.actionName === 'Reactivate') {
        //   this.message = 'Success: Secondary Packing Material \'' + this.packingService.secPackName + '\' Reactivated';
        // }
      //   this.addModal = true;
      //   setTimeout(() => {
      //     this.addModal = false;
      //     this.loadingPackingList();
      //     this.isDeletePopupModal = false;
      //     this.deleteSuccessModal = false;
      //   }, 3000);
      // } else {
        this.loadingPackingList();
        this.isDeletePopupModal = false;
        this.deleteSuccessModal = false;
      // }
    });
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  canDeactivate(): boolean {
    return this.isActionPerformed;
  }

  /* allData(): ExcelExportData {
    const result: ExcelExportData = {
      data: process(this.packingmaterial, this.state).data,
      // group: this.group
    }
   };*/


  loadPackingDetails(): void {
    this.gridView = {
      data: process(this.secondaryPacking, this.state).data,
      total: this.secondaryPacking.length
    };
  }

  loadingPackingList() {
    // const link = this.masterSetupService.getChildObject('View Uom.GET');
    this.loadingData = true;
    this.subscription = this.packingService.getPackingDetailsJSON().subscribe(data => {
      this.secondaryPacking = <ExternalPacking[]>data.body;
      this.loadPackingDetails();
      this.loadingData = false;
      if (this.deletesuccessModal) {
        setTimeout(() => { this.deletesuccessModal.hide(); }, 2000);
      }
    }, error => {
      // console.log(error)
      this.loadingData = false;
    });
  }


  pageChange(event: PageChangeEvent): void {
    this.state.skip = event.skip;
    // this.loadfinancialDetails();
  }

  goToViewPage(rowData) {
    this.packingService.setRowData(rowData);
    this.router.navigate([viewSecondaryPacking]);
  }

  packAddView() {
    this.router.navigate([AddSecondaryPacking]);
  }

  editRow(id) {
    // this.openEdit = true;
    // this.packingModel = this.secondaryPacking[index];
    this.router.navigate([editSecondaryPacking + '/' + id]);
  }

  closeEditModal($event) {
    this.editModal.hide();
    this.loadingPackingList();
    this.message = $event['msg'];
    if (this.message !== '' && this.message !== undefined && this.message !== null) {
      this.loadingPackingList();
      this.UpdateModal = true;
      setTimeout(() => {
        this.UpdateModal = false;
      }, 3000);
    }
  }

  onHideEditModal(): void {
    this.openEdit = false;
  }

  public dataStateChange(state: DataStateChangeEvent): void {
    this.state = state;
    this.gridData = process(this.secondaryPacking, this.state);
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

  DeletePackingDetails(keyid, packingCode, status, i, rowData) {
    // alert(status);
    this.packingModel = this.secondaryPacking[i];
    this.packingService.setPackingCode(packingCode);
    this.packingService.setStatusname(status);
    this.packingService.setPackingPkId(keyid);
    this.packingService.setRowData(rowData);

    if (status === 'Active') {
      this.dialogValue = 'Please Confirm to Deactivate ' + packingCode;
    }
    if (status === 'Draft') {
      this.dialogValue = 'Please Confirm to Delete ' + packingCode;
    }
    if (status === 'InActive') {
      this.dialogValue = 'Please Confirm to Reactivate ' + packingCode;
    }
    this.isDeletePopupModal = true;

  }

  pack_delete() {
    this.packingStatus = this.packingService.getStatusname();
    if (this.packingStatus === 'Draft') {
      this.confirmDelete();
    } else {
      this.onSubmitBtnClick(this.packingStatus);
    }
  }
  confirmDelete() {
    this.packingId = this.packingService.getPackingPkId();
    this.packingCode = this.packingService.getPackingCode();
    this.subscription = this.packingService.deletePackingMaterial(this.packingId)
      .subscribe(deletedStatus => {
        this.closedeletepopup();
        // tslint:disable-next-line:max-line-length
        this.packingService.messages = { severity: MESSAGE_SUCCESS, summary: 'Message.SecondaryPacking.Delete', placeHolder: ['\'' + this.packingCode + '\'']};
        this.loadingPackingList();
      },
      error => {
        this.packingService.messages = { severity: MESSAGE_ERROR, summary: this.packingService.errorMessage };
      });
  }

  onSubmitBtnClick(status) {
    this.packingId = this.packingService.getPackingPkId();
    this.packingCode = this.packingService.getPackingCode();
    this.packUpdateModel = this.packingService.getRowData();
    if (status === 'Active') {
      this.packUpdateModel.statusName = 'InActive';
      this.subscription = this.packingService.updateSecPack('', this.packUpdateModel).subscribe(data => {
        this.successModal.hide();
        this.deactivateSuccessDialog = 'You have successfully deactivated Secondary Packing ' + this.packingCode;
        this.deleteSuccessModal = true;
        this.loadingPackingList();
      },
        error => {
          throw error;
        });
    } else if (status === 'InActive') {
      this.packUpdateModel.statusName = 'Active';
      this.subscription = this.packingService.updateSecPack('', this.packUpdateModel).subscribe(data => {
        this.successModal.hide();
        this.deactivateSuccessDialog = 'You have successfully reactivated Secondary Packing ' + this.packingCode;
        this.deleteSuccessModal = true;
        this.loadingPackingList();
      },
        error => {
          throw error;
        });
    }
  }


  checkAllClicked(e) {
    // console.log('checkAllClicked', e);
    if (e.target.checked) {
      this.allItemsChecked = true;
      for (let i = 0; i < this.secondaryPacking.length; i++) {
        this.secondaryPacking[i].checked = true;
      }
    } else {
      this.allItemsChecked = false;
      for (let i = 0; i < this.secondaryPacking.length; i++) {
        this.secondaryPacking[i].checked = false;
      }
    }
  }
  selectUnSelectAllChecked(e) {
    if (!e.target.checked) {
      this.allItemsChecked = false;
    } else {
      let selected;
      selected = 0;
      for (let i = 0; i < this.secondaryPacking.length; i++) {
        if (!this.secondaryPacking[i].checked) {
          this.allItemsChecked = false;
          break;
        } else {
          selected++;
        }
      }
      if (selected === this.secondaryPacking.length) {
        this.allItemsChecked = true;
      }
    }
  }

}
