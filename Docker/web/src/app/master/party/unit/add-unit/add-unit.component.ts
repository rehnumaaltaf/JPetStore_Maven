import { Component, OnInit, OnDestroy, Input, ViewChild, ElementRef, EventEmitter, Output } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';
import { FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';
import { UnitService } from '../service/unit.service';
import { ProfitCenterModel } from '../model/profit-center-model';
import {DialogModule} from 'primeng/primeng';
import { ConfirmationService, Message } from 'primeng/primeng'; // message
import { Router } from '@angular/router';
import { unit } from '../../../../shared/interface/router-links';
import { CanComponentDeactivate } from '../../../../shared/guards/can-deactivate-guard';
import { MasterSetupService } from '../../../master-setup/service/master-setup.service';

@Component({
  selector: 'app-add-unit',
  templateUrl: './add-unit.component.html',
  styleUrls: ['./add-unit.component.css']
})
export class AddUnitComponent implements OnInit, OnDestroy, CanComponentDeactivate {
  @ViewChild('myname') input: ElementRef;
  subscription: Subscription;
  isComplete: Boolean = false;
  loadingData: Boolean = false;
  puDisabled: Boolean = false;
  fcunitName: Boolean = true;
  fcunitFullName: Boolean = false;
  disableSaveBtn: Boolean = false;
  disableSubmitBtn: Boolean = false;
  req_unitCode: Boolean;
  req_unitName: Boolean;
  req_unitFullName: Boolean;
  validateRetVal: Boolean;
  public isShowValModal: Boolean = false;
  public valMessage: string;
  isActionPerformed = false;
  config: any;
  isEditPage: Boolean;
  statusDraft: Boolean;
  statusActive: Boolean;
  statusInactive: Boolean;

  @Input() editId: string;
  @Input() editStatusId: string;
  @Output() onEditCancel: EventEmitter<{msg: string}> = new EventEmitter<{msg: string}> ();


  profitCenterModel: ProfitCenterModel = new ProfitCenterModel();
  showValModal = false;

  constructor(private unitService: UnitService, private router: Router, private masterSetupService: MasterSetupService) {
    this.loadDropdownList();
    this.profitCenterModel.groupUnit = '0';
   }

  ngOnInit() {
    this.fcunitName = true;
    this.req_unitCode = false;
    this.req_unitName = false;
    this.req_unitFullName = false;
  }

  loadDropdownList() {
    this.subscription = this.unitService.getDropdownListJSON().subscribe(data => {
      this.loadingData = true;
      this.profitCenterModel = data.body;
      this.profitCenterModel.groupUnit = '0';
      this.isComplete = true
    }, error => {
      console.log('Error Loading UOM Listing: ' + <any>error)
      this.loadingData = false;
      // this.notificationService.addNotications(error);
    });
  }

  saveProfitCenter(): void {
    if (this.validateInput()) {
      this.disableSaveBtn = true;
      this.profitCenterModel.action = 'SAVE';
     //  alert('Saving the details   ' + JSON.stringify(this.profitCenterModel.parentUnitName));
     const link = this.masterSetupService.getChildObject('Create Unit.POST');
      this.subscription = this.unitService.saveProfitCenter1
        (link.href, this.profitCenterModel).subscribe(data => {
      if (data.body !== null && data.body !== '' ) {
          this.isComplete = true
          this.unitService.setMessage(data.body);
          this.router.navigate([unit], { queryParams: { 'success': 1 } });
        }
      }, error => {
        console.log('Error Loading UOM Listing: ' + <any>error)
      });
    }
  }

  submitProfitCenter(): void {
    if (this.validateInput()) {
      this.disableSubmitBtn = true;
      this.profitCenterModel.action = 'SUBMIT';
      const link = this.masterSetupService.getChildObject('Create Unit.POST');
      this.subscription = this.unitService.saveProfitCenter1
        (link.href, this.profitCenterModel).subscribe(data => {
        this.isComplete = true
        this.unitService.setMessage(data.body);
        this.router.navigate([unit], { queryParams: { 'success': 1 } });
      }, error => {
        console.log('Error Loading UOM Listing: ' + <any>error)
      });
    }
  }

  validateUnitName(): void {
    // this.validateInput();
    if (this.profitCenterModel.unitName !== ''
        || this.profitCenterModel.unitName !== null
        || this.profitCenterModel.unitName !== undefined) {
      this.profitCenterModel.toValidate = 'UN';
      this.subscription = this.unitService.validate(this.profitCenterModel).subscribe(data => {
        if (data.body !== null && data.body !== '' ) {
          this.isComplete = true
          this.isShowValModal = true;
          this.valMessage = data.body;
          setTimeout(() => {this.isShowValModal = false; }, 3000);
          this.profitCenterModel.unitName = null;
          this.disableSaveBtn = false;
          this.disableSubmitBtn = false;
        }
        this.req_unitName = false;
      }, error => {
        console.log('Error Loading UOM Listing: ' + <any>error)
        // this.loadingData = false;
        // this.notificationService.addNotications(error);
      });
    }
  }

  validateUnitFullName(): void {
    // this.validateInput();
    if (this.profitCenterModel.unitFullName !== ''
        || this.profitCenterModel.unitFullName !== null
        || this.profitCenterModel.unitFullName !== undefined) {
      this.profitCenterModel.toValidate = 'UFN';
      this.subscription = this.unitService.validate(this.profitCenterModel).subscribe(data => {
        if (data.body !== null && data.body !== '' ) {
          this.isComplete = true
          this.isShowValModal = true;
          this.valMessage = data.body;
          setTimeout(() => {this.isShowValModal = false; }, 3000);
          this.profitCenterModel.unitFullName = null;
          this.disableSaveBtn = false;
          this.disableSubmitBtn = false;
        }
        this.req_unitFullName = false;
      }, error => {
        console.log('Error Loading UOM Listing: ' + <any>error)
        // this.loadingData = false;
        // this.notificationService.addNotications(error);
      });
    }
  }

  validateUnitCode(): void {
    // this.validateInput();
    if (this.profitCenterModel.unitCode !== ''
        || this.profitCenterModel.unitCode !== null
        || this.profitCenterModel.unitCode !== undefined) {
          this.profitCenterModel.toValidate = 'UC';
          this.subscription = this.unitService.validate(this.profitCenterModel).subscribe(data => {
          if (data.body !== null && data.body !== '' ) {
              this.isComplete = true
              this.isShowValModal = true;
              this.valMessage = data.body;
              this.showValModal = true;
              setTimeout(() => {this.isShowValModal = false; }, 3000);
              this.profitCenterModel.unitCode = null;
              this.disableSaveBtn = false;
              this.disableSubmitBtn = false;
            }
            this.req_unitCode = false;
          }, error => {
            console.log('Error Loading UOM Listing: ' + <any>error)
            // this.loadingData = false;
            // this.notificationService.addNotications(error);
        });
    }
  }

  reset(): void {
    this.resetModel();
    this.disableSaveBtn = false;
    this.disableSubmitBtn = false;
    this.req_unitCode = false;
    this.req_unitName = false;
    this.req_unitFullName = false;
    // this.validateInput();
    // this.disableParentUnit();
  }

  ngOnDestroy() {
    // Called once, before the instance is destroyed.
    this.subscription.unsubscribe();
  }

  validateInput() {
    // this.validateRetVal = true;
    if (this.profitCenterModel.unitName === null
      || this.profitCenterModel.unitName === undefined || this.profitCenterModel.unitName === '') {
      this.req_unitName = true;
      this.validateRetVal = false;
    } else {
      this.req_unitName = false;
      this.validateRetVal = true;
    }
    if (this.profitCenterModel.unitFullName === null
      || this.profitCenterModel.unitFullName === undefined || this.profitCenterModel.unitFullName === '') {
      this.req_unitFullName = true;
      this.validateRetVal = false;
    } else {
      this.req_unitFullName = false;
      if (this.validateRetVal) {
        this.validateRetVal = true;
      }
    }
    if (this.profitCenterModel.unitCode === null
      || this.profitCenterModel.unitCode === undefined || this.profitCenterModel.unitCode === '') {
      this.req_unitCode = true;
      this.validateRetVal = false;
    } else {
      this.req_unitCode = false;
      if (this.validateRetVal) {
        this.validateRetVal = true;
      }
    }
    return this.validateRetVal;
  }

//  disableParentUnit() {
//     if (this.profitCenterModel.groupUnit === '0') {
//       this.puDisabled = true;
//     } else {
//       this.puDisabled = false;
//     }
//   }

  resetModel() {
    this.profitCenterModel.glCodeList = null;
    this.profitCenterModel.parentUnitId = null;
    this.profitCenterModel.parentUnitName = null;
    this.profitCenterModel.status = null;
    this.profitCenterModel.groupUnit = '0';
    this.profitCenterModel.unitCode = null;
    this.profitCenterModel.unitName = null;
    this.profitCenterModel.unitFullName = null;
    this.profitCenterModel.toValidate = null;
    this.profitCenterModel.legalEntity = null;
    this.profitCenterModel.glCode = null;
    this.profitCenterModel.action = null;
  }

  getUnitCodeStyle() {
    if (this.req_unitCode === true) {
      return '#d43c3c';
    } else {
      return '#cfdee7';
    }
  }

  getUnitNameStyle() {
    if (this.req_unitName === true) {
      return '#d43c3c';
    } else {
      return '#cfdee7';
    }
  }

  getUnitFullNameStyle() {
    if (this.req_unitFullName === true) {
      return '#d43c3c';
    } else {
      return '#cfdee7';
    }
  }

  cancelEdit() {
    this.onEditCancel.emit({msg: ''});
  }

  canDeactivate(): boolean {
      return this.isActionPerformed;
  }
}
