import { Component, OnInit, Output, Input, ViewEncapsulation , OnDestroy } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { UnitService } from './service/unit.service';
import { ButtonModule, ConfirmationService, Message } from 'primeng/primeng';
import { Subscription } from 'rxjs/Subscription';
import { ActivatedRoute, Router } from '@angular/router';
import { ProfitCenterModel } from './model/profit-center-model';
import { MasterSetupService } from '../../master-setup/service/master-setup.service';

@Component({
  selector: 'app-unit',
  templateUrl: './unit.component.html',
  styleUrls: ['./unit.component.css'],
   encapsulation: ViewEncapsulation.None,
})
export class UnitComponent implements OnInit , OnDestroy {
  public success;
  public isShowModal;
  public message;
  showHide: boolean;
  hoverImage: boolean;
  subscription: Subscription;
  profitCenterModel: ProfitCenterModel[] = [];
  isComplete: Boolean = false;
  msgs: Message[] = [];
  showDelete: Boolean = false;
  statusValue: string;
  public iseditModal;
  displayDialog: Boolean;
  public isupdateModal;
  deleteSuccessModal: Boolean;
  isDeletePopupModal: boolean;
  unitMasterId: String;
  unitMasterStatusId: String;
  dialogValue: String;
  deactivateSuccessDialog: String;
  UnitMasterName: String;
  // loadingData: Boolean = false;
  imgSrc = '../../../assets/image/Edit_Icon_Hover.png.svg';

  id: string;
  statusId: string;

   @Output() loadingData: Boolean = true;
   @Input() parentUnitId: Number = 0;

   validateRetVal: Boolean;
   public isShowValModal: Boolean = false;
   public valMessage: string;

   constructor(private route: ActivatedRoute, public unitService: UnitService, private router: Router,
      private masterSetupService: MasterSetupService) {
     this.showHide = true;
   }
  changeShowStatus() {
    this.showHide = !this.showHide;
  }
  ngOnInit() {

    this.route.queryParams.subscribe(params => {
        // Defaults to 0 if no query param provided.
        this.success = +params['success'];
        if (this.success === 1) {
          this.message = this.unitService.getMessage();
          this.isShowModal = true;
          setTimeout(() => {this.isShowModal = false; }, 3000);
        }
    });
    this.loadingUnitList();
    this.deleteSuccessModal = false;
    this.isDeletePopupModal = false;
    this.imgSrc = '../../../assets/image/Edit_Icon_Hover.png.svg' ;
    this.iseditModal = false;
  }

  loadingUnitList() {
    const link = this.masterSetupService.getChildObject('View Unit.GET');
    console.log(' LINK ++++ ' + link.href);
    this.unitService.getUnitJSON(link.href).subscribe(getUnitDetail => {
      this.isComplete = true;
       this.loadingData = true;
       this.deleteSuccessModal = false;
       this.profitCenterModel = getUnitDetail.body;
       // alert(JSON.stringify(this.profitCenterModel));
       // alert(this.profitCenterModel);
       // alert(this.profitCenterModel[0].statusId);
    },
    error => {
      console.log('Error Loading UOM Listing: ' + <any>JSON.stringify(error))
      this.loadingData = false;
    });

  }
  over(event): void {
    this.imgSrc = '../../../assets/image/Edit_Icon_Hover.png.svg';
  }

  unitDetailView(id, statusId ) {
    this.unitService.setId(id);
    this.unitService.setStatusId(statusId);
    this.router.navigate(['master/viewunitparty']);
  }

  UpdateUnitView(id, statusId) {
    this.unitService.id = id;
    this.unitService.statusId = statusId;
    this.unitService.iseditModal = true;

    if ((id !== null && id !== undefined)
      && (statusId !== null && statusId !== undefined)) {
      this.initiateEditPage(statusId);
    } else {
      this.unitService.isEditPage = false;
    }
  }
  conf_delete() {
        this.unitMasterId = this.unitService.getId();
        this.unitMasterStatusId = this.unitService.getStatusId();
        this.UnitMasterName = this.unitService.getUnitName();
       /// alert(this.UnitMasterName);
       const link = this.masterSetupService.getChildObject('Delete Unit.DELETE');
        this.subscription = this.unitService.deleteUnitJSON(link.href, this.unitMasterId).subscribe(deletedStatus => {
          this.isDeletePopupModal = false;
          if (this.unitMasterStatusId === '1') {
              this.deleteSuccessModal = true;
              this.deactivateSuccessDialog =  this.UnitMasterName + ' has been DeActivated.';
          }
          if ( this.unitMasterStatusId === '3') {
            this.deleteSuccessModal = true;
            this.deactivateSuccessDialog = this.UnitMasterName + 'has been deleted.';
          }
          if (this.unitMasterStatusId === '4') {
             this.deleteSuccessModal = true;
             this.deactivateSuccessDialog = this.UnitMasterName + 'has been ReActivated';
          }
          this.loadingUnitList();
       },
         error => alert(error),
         () => console.log('Finished')
    );
}
  closedeletepopup() {
    this.isDeletePopupModal = false;
  }

  DeleteUnitView(id, statusId , unitname) {
    this.unitService.setId(id);
    this.unitService.setStatusId(statusId);
    this.unitService.setUnitName(unitname);
    // alert(statusId);
    if (statusId === '1') {
        this.dialogValue = 'Do you want to Deactivate the' + unitname + ' ?';
    }
    if (statusId === '3') {
      this.dialogValue = 'Do you want to Delete the ' + unitname + ' ?';
    }
    if (statusId === '4') {
      this.dialogValue = 'Do you want to Reactivate the ' + unitname + ' ?';
    }
    this.isDeletePopupModal = true;
  }


unitAddView() {
  this.router.navigate(['master/addunitparty']);
}
/*  onMouseOut(): void {
    this.imgSrc = "../../../assets/image/Delete icon.svg";
  }*/
  ngOnDestroy() {
    // prevent memory leak when component destroyed
    // this.subscription.unsubscribe();
  }

  close() {
    this.unitService.iseditModal = false;
    // this.message = obj['msg'];
    // if (this.message !== '' && this.message !== undefined && this.message !== null) {
    //   this.isShowModal = true;
    //   setTimeout(() => {this.isShowModal = false; }, 3000);
    //   this.loadingUnitList();
    // }
  }

  initiateEditPage(statusId) {
    this.unitService.setDefaultValues();
    this.loadUnitDataList(this.unitService.id);
    this.unitService.setDefaultValues();
    this.unitService.isEditPage = true;
    if (statusId === '1') {
      this.unitService.statusActive = true;
    }
    if (statusId === '3') {
      this.unitService.statusDraft = true;
    }
    if (statusId === '4') {
      this.unitService.statusInactive = true;
    }
  }

  loadUnitDataList(id) {
    this.unitService.profitCenterModel = new ProfitCenterModel();
    const link = this.masterSetupService.getChildObject('View Unit.GET');
    this.subscription = this.unitService.listUnitJSON(link.href, id).subscribe(viewUnitDetail => {
       this.isComplete = true;
       this.loadingData = true;
       this.unitService.profitCenterModel = viewUnitDetail.body[0];
    },
    error => {
      console.log('Error Loading UOM Listing: ' + <any>error)
      this.loadingData = false;
    });
  }

  validateInput() {
    this.validateRetVal = true;
    if (this.unitService.profitCenterModel.unitName === null
      || this.unitService.profitCenterModel.unitName === undefined || this.unitService.profitCenterModel.unitName === '') {
      this.unitService.req_unitName = true;
      this.validateRetVal = false;
    } else {
      this.unitService.req_unitName = false;
      this.validateRetVal = true;
    }
    if (this.unitService.profitCenterModel.unitFullName === null
      || this.unitService.profitCenterModel.unitFullName === undefined || this.unitService.profitCenterModel.unitFullName === '') {
      this.unitService.req_unitFullName = true;
      this.validateRetVal = false;
    } else {
      this.unitService.req_unitFullName = false;
      if (this.validateRetVal) {
        this.validateRetVal = true;
      }
    }
    if (this.unitService.profitCenterModel.unitCode === null
      || this.unitService.profitCenterModel.unitCode === undefined || this.unitService.profitCenterModel.unitCode === '') {
      this.unitService.req_unitCode = true;
      this.validateRetVal = false;
    } else {
      this.unitService.req_unitCode = false;
      if (this.validateRetVal) {
        this.validateRetVal = true;
      }
    }
    return this.validateRetVal;
  }

  onSubmitBtnClick(action) {
     if (this.validateInput()) {
      // this.disableSaveBtn = true;
      this.unitService.profitCenterModel.action = action;
      const link = this.masterSetupService.getChildObject('Update Unit.PUT');
      this.subscription = this.unitService.updateProfitCenter
        (link.href, this.unitService.profitCenterModel).subscribe(data => {
      if (data.body !== null && data.body !== '' ) {
          this.isComplete = true;
          this.unitService.profitCenterModel = new ProfitCenterModel();
          // this.onEditCancel.emit({msg: data.body});
          this.close();
          this.loadingUnitList();
          this.message = data.body;
          this.isShowModal = true;
          setTimeout(() => {this.isShowModal = false; }, 3000);
        }
      },
      error => {throw error},
      () => console.log('Finished')
      );
    }
  }

  validateUnitCode(): void {
    if (this.unitService.profitCenterModel.unitCode !== ''
        || this.unitService.profitCenterModel.unitCode !== null
        || this.unitService.profitCenterModel.unitCode !== undefined) {
          this.unitService.profitCenterModel.toValidate = 'UC';
          this.subscription = this.unitService.validate(this.unitService.profitCenterModel).subscribe(data => {
          if (data.body !== null && data.body !== '' ) {
              this.isComplete = true
              this.isShowValModal = true;
              this.valMessage = data.body;
              setTimeout(() => {this.isShowValModal = false; }, 3000);
              this.unitService.profitCenterModel.unitCode = null;
            }
            this.unitService.req_unitCode = false;
          },
          error => {throw error},
          () => console.log('validateUnitCode Finished'));
    }
  }

  validateUnitName(): void {
    if (this.unitService.profitCenterModel.unitName !== ''
        || this.unitService.profitCenterModel.unitName !== null
        || this.unitService.profitCenterModel.unitName !== undefined) {
      this.unitService.profitCenterModel.toValidate = 'UN';
      this.subscription = this.unitService.validate(this.unitService.profitCenterModel).subscribe(data => {
        if (data.body !== null && data.body !== '' ) {
          this.isComplete = true
          this.isShowValModal = true;
          this.valMessage = data.body;
          setTimeout(() => {this.isShowValModal = false; }, 3000);
          this.unitService.profitCenterModel.unitName = null;
        }
        this.unitService.req_unitName = false;
      },
      error => {throw error},
      () => console.log('validateUnitName Finished'));
    }
  }

  validateUnitFullName(): void {
    if (this.unitService.profitCenterModel.unitFullName !== ''
        || this.unitService.profitCenterModel.unitFullName !== null
        || this.unitService.profitCenterModel.unitFullName !== undefined) {
      this.unitService.profitCenterModel.toValidate = 'UFN';
      this.subscription = this.unitService.validate(this.unitService.profitCenterModel).subscribe(data => {
        if (data.body !== null && data.body !== '' ) {
          this.isComplete = true
          this.isShowValModal = true;
          this.valMessage = data.body;
          setTimeout(() => {this.isShowValModal = false; }, 3000);
          this.unitService.profitCenterModel.unitFullName = null;
        }
        this.unitService.req_unitFullName = false;
      },
      error => {throw error},
      () => console.log('validateUnitFullName Finished'));
    }
  }
}
