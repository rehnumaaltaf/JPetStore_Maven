import { Component, OnInit, OnDestroy, Output, ViewEncapsulation , ViewChild } from '@angular/core';
import { PlatformLocation, Location} from '@angular/common';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Subscription } from 'rxjs/Subscription';
import { Router } from '@angular/router';
import { PackingModel } from './model/pack-model';
import { MasterSetupService } from '../../master-setup/service/master-setup.service';
import { FormGroup, FormControl } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { SelectItem } from '../../../shared/interface/selectItem';
import { ResponseData } from '../../../shared/interface/responseData';
import { Link } from '../../../shared/interface/link';
import { master } from '../../../shared/interface/router-links';
import { ExcelExportData } from '@progress/kendo-angular-excel-export';
import { GridComponent, GridDataResult, DataStateChangeEvent, PageChangeEvent} from '@progress/kendo-angular-grid';
import { process, SortDescriptor, orderBy, GroupDescriptor, State } from '@progress/kendo-data-query';
import { CanComponentDeactivate } from '../../../shared/guards/can-deactivate-guard';
import { PackingMaterialService } from './service/packing-material.service';
import { viewPackingMaterial } from '../../../shared/interface/router-links';
import { ModalDirective } from 'ngx-bootstrap';
import { AddPackingMaterial } from '../../../shared/interface/router-links';
import { ConfirmationService } from '../../../shared/confirm-box/confirm.service';
import { MESSAGE_SUCCESS, MESSAGE_ERROR } from '../../../shared/interface/common.constants';
// tslint:disable-next-line:max-line-length
import { ACTION_SAVE, ACTION_SUBMIT, ACTION_UPDATE, ACTION_ACTIVE, ACTION_DEACTIVE, STATUS_DRAFT, STATUS_ACTIVE, STATUS_INACTIVE } from '../../../shared/interface/common.constants';

@Component({
  selector: 'app-packing-material',
  templateUrl: './packing-material.component.html',
  styleUrls: ['./packing-material.component.css']
})
export class PackingMaterialComponent implements OnInit , OnDestroy , CanComponentDeactivate {
subscription: Subscription;
packingmaterial: PackingModel[];
packingModel: PackingModel = new PackingModel();
isActionPerformed = true;
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
 addModal: boolean;

  constructor(private router: Router, public packingService: PackingMaterialService,
  private masterSetupService: MasterSetupService, private route: ActivatedRoute , private confirmationService: ConfirmationService) {
     // this.allData = this.allData.bind(this);
  }

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
    this.success = +params['success'];
    if (this.success === 1 && this.success !== 'NaN') {
        this.message = 'Data Saved Successfully!!!';
        this.addModal = true;
        setTimeout(() => { this.addModal = false; }, 3000);
      }
    });
    this.loadingPackingMaterialList();
    this.isDeletePopupModal = false;
    this.deleteSuccessModal = false;
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
      data: process(this.packingmaterial, this.state).data,
      total: this.packingmaterial.length
    };
  }

   loadingPackingMaterialList() {
// const link = this.masterSetupService.getChildObject('View Uom.GET');
    this.loadingData = true;
    this.subscription = this.packingService.getPackingDetailsJSON('data').subscribe(addPackingDetail => {
    this.packingmaterial = <PackingModel[]>addPackingDetail.body;
  //  console.log(JSON.stringify(addPackingDetail.body) + '     kendo check');
    this.loadPackingDetails();
    this.loadingData = false;
/*  if (this.deletesuccessModal) {
        setTimeout(() => { this.deletesuccessModal.hide(); }, 2000);
      } */
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
     this.isActionPerformed = true;
     this.packingService.setRowData(rowData)
     this.router.navigate([viewPackingMaterial]);
   }

    packAddView() {
    this.isActionPerformed = true;
    this.router.navigate([AddPackingMaterial]);
  }

    editRow(index) {
    this.openEdit = true;
    this.packingModel = this.packingmaterial[index];
  }

  closeEditModal($event) {
    this.editModal.hide();
    this.isActionPerformed = true;
    this.message = $event['msg'];
    if (this.message !== '' && this.message !== undefined && this.message !== null) {
      this.loadingPackingMaterialList();
      this.UpdateModal = true;
      setTimeout(() => {
        this.UpdateModal = false;
      }, 2000);
    } else {
      // this.loadingPackingMaterialList();
    }
  }


  editModalRemove() {
    this.editModal.hide();
    this.isActionPerformed = true;

  }

  onHideEditModal(): void {
    this.openEdit = false;
   this.isActionPerformed = true;
   }

   public dataStateChange(state: DataStateChangeEvent): void {
    this.state = state;
    this.gridData = process(this.packingmaterial, this.state);
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

  /*Confirm Box Messages and Header Declarations for Delete, Activate and Reactivate*/
  openConfirmAction(dataItem) {

    if ((dataItem.statusName).toUpperCase() === STATUS_DRAFT.toUpperCase()) {
      this.confirmationService.confirm({
        message: 'Do you really want to delete ' + dataItem.packingMaterialName + '?',
        header: 'Delete Confirmation',
        accept: (event) => {
          this.deletePackingMaterial(dataItem);
        },
        reject: (event) => {
        }
      });
    } else if ((dataItem.statusName).toUpperCase() === STATUS_ACTIVE.toUpperCase()) {
      this.confirmationService.confirm({
        message: 'Do you really want to deactivate ' + dataItem.packingMaterialName + '?',
        header: 'Deactivate Confirmation',
        accept: (event) => {
          this.packingMaterialDeactivate(dataItem);
        },
        reject: (event) => {
        }
      });
    } else if ((dataItem.statusName).toUpperCase()  === STATUS_INACTIVE.toUpperCase()) {
      this.confirmationService.confirm({
        message: 'Do you really want to reactivate ' + dataItem.packingMaterialName + '?',
        header: 'Reactive Confirmation',
        accept: (event) => {
          this.packingMaterialReactivate(dataItem);
        },
        reject: (event) => {
        }
      });
    }
}

  packingMaterialDeactivate(dataItem) {
  //  alert( dataItem.statusName);
  //  alert( dataItem.pkPackingMaterialId);
     this.subscription = this.packingService.deletePackingMaterial(dataItem.pkPackingMaterialId, dataItem.statusName)
      .subscribe(res => {
        this.loadingPackingMaterialList();
        this.packingService.messages = {severity: MESSAGE_SUCCESS, summary: 'Message.Currency.DeActivate',
                                              placeHolder: [dataItem.packingMaterialName]};
      });

  }

  packingMaterialReactivate(dataItem) {
  //  alert( dataItem.statusName);
  //  alert( dataItem.pkPackingMaterialId);
     this.subscription = this.packingService.deletePackingMaterial(dataItem.pkPackingMaterialId, dataItem.statusName)
      .subscribe(res => {
        this.loadingPackingMaterialList();
        this.packingService.messages = {severity: MESSAGE_SUCCESS, summary: 'Message.Currency.DeActivate',
                                              placeHolder: [dataItem.packingMaterialName]};
      });

  } NavigatorGeolocation


   deletePackingMaterial(dataItem) {
  //  alert( dataItem.statusName);
  //  alert( dataItem.pkPackingMaterialId);
     this.subscription = this.packingService.deletePackingMaterial(dataItem.pkPackingMaterialId, dataItem.statusName)
      .subscribe(deletedStatus => {
        this.loadingPackingMaterialList();
        this.packingService.messages = {severity: MESSAGE_SUCCESS, summary: 'Message.Common.Delete',
                                              placeHolder: ['Packing Material' , dataItem.packingMaterialName]};
      });

  }

  /* DeletePackingDetails(keyid, packingCode, status, i) {
    // alert(status);
     this.packingService.setPackingCode(packingCode);
     this.packingService.setStatusname(status);
     this.packingService.setPackingPkId(keyid);

    if (status === 'Active') {
      this.dialogValue = 'Are you want DeActivate Packing Material ' + packingCode + ' ?';
    }
    if (status === 'Draft') {
      this.dialogValue = 'Are you want Delete Packing Material  ' + packingCode + ' ?';
    }
    if (status === 'InActive') {
      this.dialogValue = 'Are you want Re-Activate Packing Material  ' + packingCode + ' ?';
    }
      this.isDeletePopupModal = true;

    }

   pack_delete() {
    this.packingId = this.packingService.getPackingPkId();
    this.packingStatus = this.packingService.getStatusname();
    this.packingCode = this.packingService.getPackingCode();
    this.subscription = this.packingService.deletePackingMaterial(this.packingId, this.packingStatus)
      .subscribe(deletedStatus => {
       this.successModal.hide();
      if (this.packingStatus === 'Active') {
          this.deactivateSuccessDialog = 'You have successfully deactivated packing material ' + this.packingCode;
        } else if (this.packingStatus === 'InActive') {
          this.deactivateSuccessDialog = 'You have successfully reactivated packing material ' + this.packingCode;
       } else if (this.packingStatus === 'Draft') {
          this.deactivateSuccessDialog = 'You have successfully deleted packing material ' + this.packingCode;
        }
         this.deleteSuccessModal = true;
        this.loadingPackingMaterialList();
        },
      error => { throw error });
  }*/

  checkAllClicked(e) {
   // console.log('checkAllClicked', e);
   if (e.target.checked) {
      this.allItemsChecked = true;
      for (let i = 0; i < this.packingmaterial.length; i++) {
        this.packingmaterial[i].checked = true;
      }
    } else {
      this.allItemsChecked = false;
      for (let i = 0; i < this.packingmaterial.length; i++) {
        this.packingmaterial[i].checked = false;
      }
    }
  }
  selectUnSelectAllChecked(e) {
    if (!e.target.checked) {
      this.allItemsChecked = false;
    } else {
      let selected;
      selected = 0;
      for (let i = 0; i < this.packingmaterial.length; i++) {
        if (!this.packingmaterial[i].checked) {
          this.allItemsChecked = false;
          break;
        } else {
          selected++;
        }
      }
      if (selected === this.packingmaterial.length) {
        this.allItemsChecked = true;
      }
    }
  //  console.log(this.allItemsChecked);
  }





}
