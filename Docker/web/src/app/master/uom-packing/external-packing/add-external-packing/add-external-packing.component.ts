import { Component, OnInit, ViewChild } from '@angular/core';
import { AccordionModule, ModalDirective } from 'ngx-bootstrap';
import { Router, ActivatedRoute } from '@angular/router';
import { CanComponentDeactivate } from '../../../../shared/guards/can-deactivate-guard';
import { FormBuilder, FormGroup, FormControl, Validators, FormArray, ReactiveFormsModule, FormsModule } from '@angular/forms';
import { ExternalPacking } from '../model/external-packing';
import { ExternalPackingMapping } from '../model/external-packing';
import { ExternalPackingMappingComponent } from './external-packing-mapping.component';
import { ExternalPackingService } from '../service/external-packing.service';
import { Subscription } from 'rxjs/Subscription';
import { MasterSetupService } from '../../../master-setup/service/master-setup.service';
import { secondaryPack } from '../../../../shared/interface/router-links';
import { MESSAGE_SUCCESS, MESSAGE_ERROR } from '../../../../shared/interface/common.constants';
import { MessageModel } from '../../../../shared/message/message.model';

// tslint:disable-next-line:max-line-length
import { ACTION_SAVE, ACTION_SUBMIT, ACTION_UPDATE, ACTION_ACTIVE, ACTION_DEACTIVE, STATUS_DRAFT, STATUS_ACTIVE, STATUS_INACTIVE } from '../../../../shared/interface/common.constants';

@Component({
  selector: 'app-add-external-packing',
  templateUrl: './add-external-packing.component.html',
  styleUrls: ['./add-external-packing.component.css']
})
export class AddExternalPackingComponent implements OnInit, CanComponentDeactivate {

  public status: any = {
    isFirstOpen: true,
    isFirstDisabled: false
  };
  public extPackingModel: ExternalPacking = new ExternalPacking();
  public extPackingMapping: ExternalPackingMapping = new ExternalPackingMapping();
  public externalPackingForm: FormGroup;
  public reqTypeCode: Boolean = false;
  public reqTypeName: Boolean = false;
  public reqIsBulk: Boolean = false;
  private returnVal: Boolean = true;
  private subscription: Subscription;
  private editId;
  public isEditPage: Boolean = false;
  public draft;
  public active;
  public inactive;
  public isActionPerformed = false;
  public errorModal = false;
  public packCodeList: String[] = [];
  public packNameList: String[] = [];
  AddEditFinancialId: number;
  public secondaryPackingTypeIsBulk: String = 'N';
  public messageLocale;

  constructor(private fb: FormBuilder, public externalPackingService: ExternalPackingService, private router: Router,
    private masterSetupService: MasterSetupService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.extPackingModel.secondaryPackingTypeIsBulk = 'N';
    this.getPrimaryPackingList();
    this.getProductList();
    this.getOriginCodeList();
    if (this.route.snapshot.params['id'] !== undefined) {
      // this.isActionPerformed = true;
      this.editId = this.route.snapshot.params['id'];
      this.AddEditFinancialId = this.editId;
      this.isEditPage = true;
      this.buildExternalPackingForm();
      this.getDataById(this.editId);
    } else {
      this.AddEditFinancialId = 0;
      this.buildExternalPackingForm();
      this.addSecPackMapping();
      // this.externalPackingForm.reset();
      // this.extPackingMapping = new ExternalPackingMapping();
      // this.resetForm();
      // this.externalPackingForm.controls['secondaryPackingTypeIsBulk'].setValue('N');
      this.resetForm();
    }
  }

  buildExternalPackingForm(): void {
    this.externalPackingForm = this.fb.group({
      'pkSecondaryPackingTypeId': [this.extPackingModel.pkSecondaryPackingTypeId],
      'secondaryPackingTypeCode': [this.extPackingModel.secondaryPackingTypeCode, Validators.required],
      'secondaryPackingTypeName': [this.extPackingModel.secondaryPackingTypeName, Validators.required],
      'secondaryPackingTypeDesc': [this.extPackingModel.secondaryPackingTypeDesc],
      'secondaryPackingTypeIsBulk': [this.extPackingModel.secondaryPackingTypeIsBulk, Validators.required],
      'fkStatusId': [this.extPackingModel.fkStatusId],
      'pkPackingAssocId': [this.extPackingMapping.pkPackingAssocId],
      'fkOriginId': [this.extPackingMapping.fkOriginId],
      'fkProdId': [this.extPackingMapping.fkProdId],
      'fkPrimaryPackingTypeId': [this.extPackingMapping.fkPrimaryPackingTypeId],
      'countOfPrimary': [this.extPackingMapping.countOfPrimary],
      'secPackAssocList': this.fb.array([])
    });
  }

  getSecondaryPackMappingList(externalPackingForm) {
    return externalPackingForm.get('secPackAssocList').controls;
  }

  addSecPackMapping() {
    const control = <FormArray>this.externalPackingForm.controls['secPackAssocList'];
    const addrCtrl = this.initSecPackMapping();
    control.push(addrCtrl);
  }

  initSecPackMapping() {
    return this.fb.group({
      pkPackingAssocId: [this.extPackingMapping.pkPackingAssocId],
      fkOriginId: [this.extPackingMapping.fkOriginId],
      fkProdId: [this.extPackingMapping.fkProdId],
      fkPrimaryPackingTypeId: [this.extPackingMapping.fkPrimaryPackingTypeId],
      countOfPrimary: [this.extPackingMapping.countOfPrimary]
    });
  }

  removeSecPackMapping(i: number) {
    if (i !== null && i !== 0) {
      const control = <FormArray>this.externalPackingForm.controls['secPackAssocList'];
      control.removeAt(i);
    }
  }

  saveExtPacking(action) {
    this.extPackingModel = this.externalPackingForm.value;
    if (this.isValidForm()) {
      this.isActionPerformed = true;
      this.extPackingModel.action = action;
      // const link = this.masterSetupService.getChildObject('Create SECPACK.POST');
      this.subscription = this.externalPackingService.saveExtPacking('', this.extPackingModel).subscribe(data => {
        if (data.body !== null && data.body !== '') {
          // this.externalPackingService.secPackName = this.extPackingModel.secondaryPackingTypeName;
          // this.externalPackingService.actionName = action;
          // this.router.navigate([secondaryPack], { queryParams: { 'success': 1 } });
          if (action === 'SUBMIT') {
            this.messageLocale = 'Message.SecondaryPacking.Activate';
          } else if (action === 'SAVE') {
            this.messageLocale = 'Message.SecondaryPacking.Save';
          }
          // tslint:disable-next-line:max-line-length
          this.externalPackingService.messages = { severity: MESSAGE_SUCCESS, summary: this.messageLocale, placeHolder: ['\'' + this.extPackingModel.secondaryPackingTypeName + '\'']};
          this.router.navigate([secondaryPack]);
        }
      }, error => {
        // console.log(JSON.stringify(this.externalPackingService.errorMessage));
        // this.errorModal = true;
        // setTimeout(() => { this.errorModal = false; }, 3000);
        this.externalPackingService.messages = { severity: MESSAGE_ERROR, summary: this.externalPackingService.errorMessage };
      });
    }
  }

  resetForm() {
    this.externalPackingForm.reset();
    this.extPackingMapping = new ExternalPackingMapping();
    this.reqTypeCode = false;
    this.reqTypeName = false;
    this.reqIsBulk = false;
    this.secondaryPackingTypeIsBulk = 'N';
    this.extPackingModel.secondaryPackingTypeIsBulk = 'N';
    this.externalPackingForm.controls['secondaryPackingTypeIsBulk'].setValue('N');
  }

  isValidForm(): Boolean {
    this.reqTypeCode = false;
    this.reqTypeName = false;
    this.reqIsBulk = false;
    this.returnVal = true;

    if (this.externalPackingForm.value.secondaryPackingTypeCode === null
      || this.externalPackingForm.value.secondaryPackingTypeCode === undefined
      || this.externalPackingForm.value.secondaryPackingTypeCode === '') {
      this.reqTypeCode = true;
      this.returnVal = false;
    }
    if (this.externalPackingForm.value.secondaryPackingTypeName === null
      || this.externalPackingForm.value.secondaryPackingTypeName === undefined
      || this.externalPackingForm.value.secondaryPackingTypeName === '') {
      this.reqTypeName = true;
      this.returnVal = false;
    }
    if (this.externalPackingForm.value.secondaryPackingTypeIsBulk === null
      || this.externalPackingForm.value.secondaryPackingTypeIsBulk === undefined
      || this.externalPackingForm.value.secondaryPackingTypeIsBulk === '') {
      this.reqIsBulk = true;
      this.returnVal = false;
    }

    // const length = Number(JSON.stringify(this.externalPackingForm.value.secPackAssocList.length));
    // if (length >= 1) {
    //   for (let i = 0; i < length; i++) {
    //     if (this.externalPackingForm.value.secPackAssocList[i].countOfPrimary === null) {
    //       this.reqCountOfPrimary = true;
    //       this.returnVal = false;
    //     }
    //   }
    // }

    return this.returnVal;
  }

  getTypeCodeStyle() {
    if (this.reqTypeCode === true) {
      return '#d43c3c';
    } else {
      return '#cfdee7';
    }
  }

  getTypeCodeNameStyle() {
    if (this.reqTypeName === true) {
      return '#d43c3c';
    } else {
      return '#cfdee7';
    }
  }

  getIsBulkStyle() {
    if (this.reqIsBulk === true) {
      return '#d43c3c';
    } else {
      return '#cfdee7';
    }
  }

  getDataById(editId) {
    this.externalPackingService.getDataById('', editId).subscribe(data => {
      if (data.body !== null && data.body !== '') {
        this.extPackingModel = data.body[0];
        console.log(this.extPackingModel);

        if (this.extPackingModel.statusName === 'Draft') {
          this.draft = true;
          this.active = false;
          this.inactive = false;
        } else if (this.extPackingModel.statusName === 'Active') {
          this.draft = false;
          this.active = true;
          this.inactive = false;
        } else {
          this.draft = false;
          this.active = false;
          this.inactive = true;
        }

        this.editListDetails();
      }
    }, error => { console.log(JSON.stringify(error)) });
  }

  editListDetails() {
    this.externalPackingForm.controls['pkSecondaryPackingTypeId'].setValue(this.extPackingModel.pkSecondaryPackingTypeId);
    this.externalPackingForm.controls['secondaryPackingTypeCode'].setValue(this.extPackingModel.secondaryPackingTypeCode);
    this.externalPackingForm.controls['secondaryPackingTypeName'].setValue(this.extPackingModel.secondaryPackingTypeName);
    this.externalPackingForm.controls['secondaryPackingTypeDesc'].setValue(this.extPackingModel.secondaryPackingTypeDesc);
    this.externalPackingForm.controls['secondaryPackingTypeIsBulk'].setValue(this.extPackingModel.secondaryPackingTypeIsBulk);
    this.secondaryPackingTypeIsBulk = this.extPackingModel.secondaryPackingTypeIsBulk;
    this.externalPackingForm.controls['fkStatusId'].setValue(this.extPackingModel.fkStatusId);

    const size = Number(JSON.stringify(this.extPackingModel.secPackAssocList.length));
    if (size >= 1) {
      for (let i = 0; i < size; i++) {
        this.addSecPackMapping();
      }
    }
    console.log(this.externalPackingForm);
  }

  onCancel() {
    this.router.navigate([secondaryPack]);
  }

  updateSecPack(statusName, isReactivate) {
    this.isActionPerformed = true;
    this.extPackingModel = this.externalPackingForm.value;
    if (statusName !== 'update') {
      this.extPackingModel.statusName = statusName;
    }
    if (this.isValidForm()) {
      // const link = this.masterSetupService.getChildObject('Create SECPACK.PUT');
      this.subscription = this.externalPackingService.updateSecPack('', this.extPackingModel).subscribe(data => {
        if (data.body !== null && data.body !== '') {
            if (statusName === 'update') {
              this.messageLocale = 'Message.SecondaryPacking.Update';
            } else if (statusName === 'InActive') {
              this.messageLocale = 'Message.SecondaryPacking.Deactivate';
            } else if (statusName === 'Active' && isReactivate) {
              this.messageLocale = 'Message.SecondaryPacking.Reactivate';
            } else if (statusName === 'Active' || statusName === 'SUBMIT') {
              this.messageLocale = 'Message.SecondaryPacking.Activate';
            } else if (statusName === 'Draft') {
              this.messageLocale = 'Message.SecondaryPacking.Save';
            }
            // tslint:disable-next-line:max-line-length
            this.externalPackingService.messages = { severity: MESSAGE_SUCCESS, summary: this.messageLocale, placeHolder: ['\'' + this.extPackingModel.secondaryPackingTypeName + '\'']};
            this.router.navigate([secondaryPack]);
        }
      }, error => {
        this.externalPackingService.messages = { severity: MESSAGE_ERROR, summary: this.externalPackingService.errorMessage };
      });
    }
  }

  getProductList() {
    // const link = this.masterSetupService.getChildObject('Create SECPACK.GET');
    this.subscription = this.externalPackingService.getProductList('').subscribe(data => {
      if (data.body !== null && data.body !== '') {
        this.externalPackingService.productMappingList = data.body;
      }
    }, error => { throw error; });
  }

  getPrimaryPackingList() {
    // const link = this.masterSetupService.getChildObject('Create SECPACK.GET');
    this.subscription = this.externalPackingService.getPrimaryPackingList('').subscribe(data => {
      if (data.body !== null && data.body !== '') {
        this.externalPackingService.primaryPackingList = data.body;
      }
    }, error => { throw error; });
  }

  getOriginCodeList() {
    // const link = this.masterSetupService.getChildObject('Create SECPACK.GET');
    this.subscription = this.externalPackingService.getOriginCodeList('').subscribe(data => {
      if (data.body !== null && data.body !== '') {
        this.externalPackingService.originCodeList = data.body;
      }
      console.log(this.externalPackingService.originCodeList);
    }, error => { throw error; });
  }

  /*This function is called when user is trying to come out the component*/
  canDeactivate(): boolean {
    return this.isActionPerformed;
  }

  /*To list the suggestions for code*/
  onCodeSuggestion($event) {
    this.isActionPerformed = true;
    const packCodeValue = $event.target.value;
    console.log(packCodeValue);
    if (packCodeValue !== null && packCodeValue !== undefined && packCodeValue !== '') {
      // const link = this.masterSetupService.getChildObject('Create SECPACK.GET');
      this.subscription = this.externalPackingService.secPackCodeSuggestion('', packCodeValue).subscribe(descDetails => {
        this.packCodeList = descDetails;
      }, error => { throw error; });
    }
  }

  /*To list the suggestions for code*/
  onTypeNameSuggestion($event) {
    this.isActionPerformed = true;
    const packNameValue = $event.target.value;
    if (packNameValue !== null && packNameValue !== undefined && packNameValue !== '') {
      // const link = this.masterSetupService.getChildObject('Create SECPACK.GET');
      this.subscription = this.externalPackingService.secPackNameSuggestion('', packNameValue).subscribe(descDetails => {
        this.packNameList = descDetails;
      }, error => { throw error; });
    }
  }
}
