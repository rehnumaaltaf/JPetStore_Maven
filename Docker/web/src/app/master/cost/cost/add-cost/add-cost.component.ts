import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AccordionModule } from 'ngx-bootstrap';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { FormBuilder, FormGroup, FormControl, Validators, FormArray, ReactiveFormsModule, FormsModule } from '@angular/forms';
import { cost } from '../../../../shared/interface/router-links';
import { CostService } from '../service/cost.service';
import { Subscription } from 'rxjs/Subscription';
import { DefaultUnit, SelectMatrix, CostMaster , ExternalSystem  } from '../model/add-cost-model';
import { WareHouseMatrix } from '../../warehouse-cost-matrix/model/cost-warehouse';
import { CnfCostMatrix } from '../../cnf-cost-matrix/model/cnf-cost-model';
// import { SelectItemModel } from 'app/master/geo-location/location/model/select-item.model';
import { DatePipe } from '@angular/common';
// tslint:disable-next-line:max-line-length
import { MESSAGE_SUCCESS, MESSAGE_ERROR, ACTION_SAVE, ACTION_SUBMIT, ACTION_UPDATE, YES, NO } from '../../../../shared/interface/common.constants';
import { ACTION_ACTIVE, ACTION_DEACTIVE } from '../../../../shared/interface/common.constants';
import { MessageModel } from '../../../../shared/message/message.model';
import { ConfirmationService } from '../../../../shared/confirm-box/confirm.service';
import { FreightMatrix } from '../../freight-cost-matrix/model/freight-cost.model';
import { CanComponentDeactivate } from '../../../../shared/guards/can-deactivate-guard';
@Component({
  selector: 'app-add-cost',
  templateUrl: './add-cost.component.html',
  styleUrls: ['./add-cost.component.css'],
  encapsulation: ViewEncapsulation.None,
})
export class AddCostComponent implements OnInit , CanComponentDeactivate {

  public editId;
  public addEditCostMasterId;
  public isEditPage: Boolean = false;
  public isActionPerformed = false;
  public costMasterForm: FormGroup;
  private subscription: Subscription;
  public errorModal = false;
  public defaultUnit: typeof DefaultUnit = DefaultUnit;
  public selectMatrix: typeof SelectMatrix = SelectMatrix;
  public status: any = {
    isFirstOpen: true,
    isFirstDisabled: false
  };
  public draft;
  public active;
  public inactive;
  mappingList: ExternalSystem[] = [];
  wareHouseList: WareHouseMatrix[] = [];
  public wareHouseLength: Number;
  cnfCostList: CnfCostMatrix[] = [];
  public cnfCostLength: Number;
  freightlist: FreightMatrix[] = [];
  public freightDataLength: Number;
  costModel: CostMaster= new CostMaster();

  actionSave = ACTION_SAVE;
  actionSubmit = ACTION_SUBMIT;
  actionUpdate = ACTION_UPDATE;
  actionActive = ACTION_ACTIVE;
  actionDeactive = ACTION_DEACTIVE;
  public defaultFlag = true;
  public matrixApplicableInd: String = 'N';
  public costTypeIsPrimaryInd: String = 'N';
  public nettedForMtmInd: String = 'N';
  public realizedInd: String = 'N';
  public capitalizeCostInd: String = 'N';

   constructor(public datepipe: DatePipe, private fb: FormBuilder, private route: ActivatedRoute,
    private router: Router, public costService: CostService, private confirmationService: ConfirmationService) { }

  ngOnInit() {
    // this.costService.messages = new MessageModel();
    if (this.route.snapshot.params['id'] !== undefined) {
      this.editId = this.route.snapshot.params['id'];
      this.addEditCostMasterId = this.editId;
      this.isEditPage = true;
      this.buildCostMasterForm();
      this.getCostMasterById(this.editId);
      this.loadDropdowns();
    } else {
      this.costService.costMasterModel = new CostMaster();
      this.addEditCostMasterId = 0;
      this.buildCostMasterForm();
      this.addExtSystemMapping();
      this.loadDropdowns();
      this.costService.resetMandateStyle();
    }
  }

  /*This function is called when user is trying to come out the component*/
   canDeactivate(): boolean {
    return this.isActionPerformed;
  }


  changedCostGroup() {
        this.costService.costMasterModel.fkCostGroupId = this.costMasterForm.value.fkCostGroupId;
        this.costMasterForm.controls['fkMatrixEntityId'].setValue(this.costMasterForm.value.fkCostGroupId);
        if (this.selectMatrix[+this.costMasterForm.value.fkCostGroupId]) {
           this.costMasterForm.controls['fkMatrixDemoValue'].setValue(this.selectMatrix[+this.costMasterForm.value.fkCostGroupId]);
        }
  }
  buildCostMasterForm(): void {
    this.costMasterForm = this.fb.group({
      'pkCostId': [this.costService.costMasterModel.pkCostId],
      'fkStatusId': [this.costService.costMasterModel.fkStatusId],
      'costName': [this.costService.costMasterModel.costName],
      'costCode': [this.costService.costMasterModel.costCode],
      'costFullName': [this.costService.costMasterModel.costFullName],
      'fkCostGroupId': [this.costService.costMasterModel.fkCostGroupId],
      'fkMatrixDemoValue': [''],
      'costTypeIsPrimaryInd': [this.costService.costMasterModel.costTypeIsPrimaryInd],
      'matrixApplicableInd': [this.costService.costMasterModel.matrixApplicableInd],
      'fkMatrixEntityId': [this.costService.costMasterModel.fkMatrixEntityId],
      'costDefaultValue': [this.costService.costMasterModel.costDefaultValue],
      'fkcostDefValueTypeId': [this.costService.costMasterModel.fkcostDefValueTypeId],
      'fkIndexUomId': [this.costService.costMasterModel.fkIndexUomId],
      'fkCurrencyId': [this.costService.costMasterModel.fkCurrencyId],
      'nettedForMtmInd': [this.costService.costMasterModel.nettedForMtmInd],
      'realizedInd': [this.costService.costMasterModel.realizedInd],
      'capitalizeCostInd': [this.costService.costMasterModel.capitalizeCostInd],
      'fkRevenueGlId': [this.costService.costMasterModel.fkRevenueGlId],
      'fkExpenseGlId': [this.costService.costMasterModel.fkExpenseGlId],
      'fkDefaultValueBasisRefId': [this.costService.costMasterModel.fkDefaultValueBasisRefId],
      'fkDefaultValueUomId': [this.costService.costMasterModel.fkDefaultValueUomId],
      'fkDefValPrimaryPackingId': [this.costService.costMasterModel.fkDefValPrimaryPackingId],
      'fkDefValSecondaryPackingId': [this.costService.costMasterModel.fkDefValSecondaryPackingId],
      'fkPartyId': [this.costService.costMasterModel.fkPartyId],
      'fkCostId': [this.costService.extSystemMapping.fkCostId],
      'fkExternalSystemRefId': [this.costService.extSystemMapping.fkExternalSystemRefId],
      'mappingType': [this.costService.extSystemMapping.mappingType],
      'externalCode': [this.costService.extSystemMapping.externalCode],
      'exterPackAssoc': this.fb.array([])
    });
  }

  getExtSystemMappingList(costMasterForm) {
    // console.log(costMasterForm.get('exterPackAssoc').controls);
    return costMasterForm.get('exterPackAssoc').controls;
  }

  addExtSystemMapping() {
    const control = <FormArray>this.costMasterForm.controls['exterPackAssoc'];
    const addrCtrl = this.initExtSystemMapping();
    control.push(addrCtrl);
    // console.log('addrCtrl -> ' + control);
  }

  initExtSystemMapping() {
    return this.fb.group({
      fkCostId: [this.costService.extSystemMapping.fkCostId],
      pkCostExternalMappingId: [this.costService.extSystemMapping.pkCostExternalMappingId],
      fkExternalSystemRefId: [this.costService.extSystemMapping.fkExternalSystemRefId],
      mappingType: [this.costService.extSystemMapping.mappingType],
      externalCode: [this.costService.extSystemMapping.externalCode]
    });
  }

  removeExtSystemMapping(i: number) {
    if (this.costMasterForm.value.exterPackAssoc.length > 1 ) {
            const control = <FormArray>this.costMasterForm.controls['exterPackAssoc'];
            control.removeAt(i);
        if (this.costService.costMasterModel.exterPackAssoc != null) {
           this.costService.costMasterModel.exterPackAssoc.splice(i, 1);
        }
     }
  }

  loadDropdowns(): void {
    // this.getPartyList();
    this.getCurrencyList();
    this.getUomList();
    this.getPrimaryPackingList();
    this.getSecondaryPackingList();
  }

  getUomList(): void {
    // const link = this.masterSetupService.getChildObject('Create SECPACK.POST');
    this.subscription = this.costService.getUomList().subscribe(data => {
      console.log(data);
      if (data.body !== null && data.body !== '') {
        this.costService.defaultUnitUomList = data.body;
      }
    }, error => {
      console.log(JSON.stringify(this.costService.errorMessage));
      /* this.errorModal = true;
       setTimeout(() => { this.errorModal = false; }, 3000);*/
    });
  }

  getPrimaryPackingList(): void {
    // const link = this.masterSetupService.getChildObject('Create SECPACK.POST');
    this.subscription = this.costService.getPrimaryPackingList().subscribe(data => {
      console.log(data);
      if (data.body !== null && data.body !== '') {
        this.costService.defaultUnitPrimaryPackList = data.body;
      }
    }, error => {
      console.log(JSON.stringify(this.costService.errorMessage));
      /* this.errorModal = true;
       setTimeout(() => { this.errorModal = false; }, 3000);*/
    });
  }

  getSecondaryPackingList(): void {
    // const link = this.masterSetupService.getChildObject('Create SECPACK.POST');
    this.subscription = this.costService.getSecondaryPackingList().subscribe(data => {
      console.log(data);
      if (data.body !== null && data.body !== '') {
        this.costService.defaultUnitSecondaryPackList = data.body;
      }
    }, error => {
      console.log(JSON.stringify(this.costService.errorMessage));
      /*this.errorModal = true;
      setTimeout(() => { this.errorModal = false; }, 3000);*/
    });
  }

 getPartyList(): void {
    // const link = this.masterSetupService.getChildObject('Create SECPACK.POST');
    this.subscription = this.costService.getPartyList().subscribe(data => {
      console.log(data);
      if (data.body !== null && data.body !== '') {
        this.costService.partyList = data.body;
        for ( let i = 0; i < this.costService.partyList.length; i++  ) {
          this.costService.partyList[i].fkPartyId = this.costService.partyList[i].partyId;
          this.costService.partyList[i].fkPartyName = this.costService.partyList[i].partyName;
        }
      }
    }, error => {
      console.log(JSON.stringify(this.costService.errorMessage));
      /*this.errorModal = true;
      setTimeout(() => { this.errorModal = false; }, 3000);*/
    });
  }

  getCurrencyList(): void {
    // const link = this.masterSetupService.getChildObject('Create SECPACK.POST');
    this.subscription = this.costService.getCurrencyList().subscribe(data => {
      if (data.body !== null && data.body !== '') {
        this.costService.currencyList = data.body;
      }
    }, error => {
      console.log(JSON.stringify(this.costService.errorMessage));
      /*this.errorModal = true;
      setTimeout(() => { this.errorModal = false; }, 3000);*/
    });
  }

  /*getCurrentUnitBasis(obj) {
    // alert(obj.get('fkDefaultValueBasisRefId').value);
    console.log(this.costMasterForm.value.fkcostDefValueTypeId);
  }*/
  changeDate(date) {
    return this.datepipe.transform(date, 'yyyy-MM-dd');
  }

  beforeSaveProcessData() {
   /* if (this.costMasterForm.value.matrixApplicableInd === YES
      && +this.costMasterForm.value.fkMatrixEntityId === +this.selectMatrix.Warehouse) {
      const warehousedto = this.costService.costMasterModel.wareHouseCostDto;
      this.costService.costMasterModel = this.costMasterForm.value;
      this.costService.costMasterModel.wareHouseCostDto = warehousedto;
      for ( let i = 0; i < this.costService.costMasterModel.wareHouseCostDto.length; i++ ) {
        let tempLegalEntity;
        tempLegalEntity = [];
        let tempLegalWHL;
        tempLegalWHL = [];
        if (!this.costService.costMasterModel.wareHouseCostDto[i].legalEntity) {
          this.costService.costMasterModel.wareHouseCostDto[i].legalEntity = tempLegalEntity;
        }
      }
    } else if (this.costMasterForm.value.matrixApplicableInd === YES
      && +this.costMasterForm.value.fkMatrixEntityId === +this.selectMatrix.Clearing_Forwarding) {
      const cnfCostDto = this.costService.costMasterModel.cnfCostDto;
      this.costService.costMasterModel = this.costMasterForm.value;
      this.costService.costMasterModel.cnfCostDto = cnfCostDto;
      for (let i = 0; i < this.costService.costMasterModel.cnfCostDto.length; i++) {
        let tempLegalEntity;
        tempLegalEntity = [];
        let tfkPartyWhStockLocationId;
        tfkPartyWhStockLocationId = [];
        for (let j = 0; j < this.costService.costMasterModel.cnfCostDto[i].fkPartyId.length; j++) {
          tempLegalEntity.push({ fkPartyId: this.costService.costMasterModel.cnfCostDto[i].fkPartyId[j].partyId });
        }
        this.costService.costMasterModel.cnfCostDto[i].fkPartyId = tempLegalEntity;

        for (let j = 0; j < this.costService.costMasterModel.cnfCostDto[i].fkPartyWhStockLocationId.length; j++) {
          tfkPartyWhStockLocationId.push({ fkPartyWhStockLocationId:
             this.costService.costMasterModel.cnfCostDto[i].fkPartyWhStockLocationId[j].value });
        }
        this.costService.costMasterModel.cnfCostDto[i].fkPartyWhStockLocationId = tfkPartyWhStockLocationId;
      }
   }else if (this.costMasterForm.value.matrixApplicableInd === YES
      && +this.costMasterForm.value.fkMatrixEntityId === +this.selectMatrix.Freight) {
      const masterFreightCostMatrixDto = this.costService.costMasterModel.masterFreightCostMatrixDto;
   //   alert('FreightCost' + this.costService.costMasterModel.masterFreightCostMatrixDto);
      this.costService.costMasterModel = this.costMasterForm.value;
      this.costService.costMasterModel.masterFreightCostMatrixDto = masterFreightCostMatrixDto;
      for (let i = 0; i < this.costService.costMasterModel.masterFreightCostMatrixDto.length; i++) {
        let tempLegalEntity;
        tempLegalEntity = [];
        let tfkPartyWhStockLocationId;
        tfkPartyWhStockLocationId = [];
        for (let j = 0; j < this.costService.costMasterModel.masterFreightCostMatrixDto[i].fkPartyId.length; j++) {
          tempLegalEntity.push({ fkPartyId: this.costService.costMasterModel.masterFreightCostMatrixDto[i].fkPartyId[j].fkPartyId});
        }
        this.costService.costMasterModel.masterFreightCostMatrixDto[i].fkPartyId = tempLegalEntity;

       }
    } else {*/
      //  alert(this.costService.costMasterModel.fkStatusName);
      let fkCostGroupId;
      let matrixApplicableInd;
      if (this.costService.costMasterModel.fkStatusName) {
        fkCostGroupId = this.costService.costMasterModel.fkCostGroupId;
        matrixApplicableInd = this.costService.costMasterModel.matrixApplicableInd;
      }
      this.costService.costMasterModel = this.costMasterForm.value;
      if (!this.costService.costMasterModel.fkCostGroupId) {
        this.costService.costMasterModel.fkCostGroupId = fkCostGroupId;
      }
      if (!this.costService.costMasterModel.matrixApplicableInd) {
        this.costService.costMasterModel.matrixApplicableInd = matrixApplicableInd;
      }
      console.log(this.costService.costMasterModel);
      /* let tempCompany;
      tempCompany = [];
      console.log(this.costService.costMasterModel.fkPartyId);
      for (let i = 0; i < this.costService.costMasterModel.fkPartyId.length; i++) {
          tempCompany.push({ fkPartyId: this.costService.costMasterModel.fkPartyId[i].partyId });
      }
      console.log('here', tempCompany);
      this.costService.costMasterModel.fkPartyId = tempCompany;*/
    // }
  }
  submit() {
    this.beforeSaveProcessData();
     if (this.costService.validateCostMaster()) {
          this.costService.costMasterModel.action = ACTION_SUBMIT;
          // const link = this.masterSetupService.getChildObject('Create Brand.POST');
          // const url = link.href;
          const url = '';
          if (this.costService.costMasterModel.pkCostId) {
             this.subscription = this.costService.updateCost(url, this.costService.costMasterModel).subscribe(costDetail => {
              // this.costService.messages = { severity: MESSAGE_SUCCESS, summary: costDetail.body };
              this.isActionPerformed = true;
              this.router.navigate([cost]);
              if (this.isEditPage) {
                  this.costService.messages = { severity: MESSAGE_SUCCESS, summary: 'Message.Common.Update',
                  placeHolder: ['Cost', this.costService.costMasterModel.costName] };
              }else {
                  this.costService.messages = { severity: MESSAGE_SUCCESS, summary: 'Message.Cost.Update',
                  placeHolder: [ this.costService.costMasterModel.costName] };
              }
              // this.router.navigate([cost]);
            },
            error => {
            });
          } else {
              this.subscription = this.costService.saveCostDetails(url, this.costService.costMasterModel).subscribe(costDetail => {
                this.isActionPerformed = true;
                // this.costService.messages = { severity: MESSAGE_SUCCESS, summary: costDetail.body };
                this.router.navigate([cost]);
                if (this.isEditPage) {
                  this.costService.messages = { severity: MESSAGE_SUCCESS, summary: 'Message.Common.Submit',
                  placeHolder: ['Cost', this.costService.costMasterModel.costName] };
                }else {
                  this.costService.messages = { severity: MESSAGE_SUCCESS, summary: 'Message.Cost.Submit',
                  placeHolder: [ this.costService.costMasterModel.costName] };
                }
              },
              error => {
              });
         }
     }
  }

  save() {
       this.beforeSaveProcessData();
        if (this.costService.validateCostMaster()) {
          this.costService.costMasterModel.action = ACTION_SAVE;
          // const link = this.masterSetupService.getChildObject('Create Brand.POST');
          // const url = link.href;
          console.log('master with warehouse matrix   ' +  JSON.stringify(this.costService.costMasterModel));
          const url = '';
          if (this.costService.costMasterModel.pkCostId) {
             this.subscription = this.costService.updateCost(url, this.costService.costMasterModel).subscribe(costDetail => {
               this.isActionPerformed = true;
              // this.costService.messages = { severity: MESSAGE_SUCCESS, summary: costDetail.body };
              this.router.navigate([cost]);
              if (this.isEditPage) {
                  this.costService.messages = { severity: MESSAGE_SUCCESS, summary: 'Message.Common.Update',
                  placeHolder: ['Cost', this.costService.costMasterModel.costName] };
              }else {
                  this.costService.messages = { severity: MESSAGE_SUCCESS, summary: 'Message.Cost.Update',
                  placeHolder: [ this.costService.costMasterModel.costName] };
              }
              // this.router.navigate([cost]);
            },
            error => {
            });
          } else {
             this.subscription = this.costService.saveCostDetails(url, this.costService.costMasterModel).subscribe(costDetail => {
              this.isActionPerformed = true;
              // this.costService.messages = { severity: MESSAGE_SUCCESS, summary: costDetail.body };
              this.router.navigate([cost]);
              if (this.isEditPage) {
                  this.costService.messages = { severity: MESSAGE_SUCCESS, summary: 'Message.Common.Save',
                  placeHolder: ['Cost', this.costService.costMasterModel.costName] };
              }else {
                  this.costService.messages = { severity: MESSAGE_SUCCESS, summary: 'Message.Cost.Save',
                  placeHolder: [ this.costService.costMasterModel.costName] };
              }
              // this.router.navigate([cost]);
            },
            error => {
            });
          }
       }
  }


  getCostMasterById(editId) {
    this.subscription = this.costService.getCostMasterById(editId).subscribe(data => {
      if (data.body !== null && data.body !== '') {
        this.costService.costMasterModel = data.body[0];
        console.log(JSON.stringify(data.body[0]));
         /*if (this.costService.costMasterModel.wareHouseCostDto.length >= 1 ) {
           // this.wareHouseList = this.costService.costMasterModel.wareHouseCostDto;
            this.costService.whDataList = this.costService.costMasterModel.wareHouseCostDto
            this.wareHouseLength = this.costService.costMasterModel.wareHouseCostDto.length;
         }
       if (this.costService.costMasterModel.cnfCostDto.length >= 1 ) {
            this.cnfCostList = this.costService.costMasterModel.cnfCostDto;
            this.cnfCostLength = this.costService.costMasterModel.cnfCostDto.length;
            }

         if (this.costService.costMasterModel.masterFreightCostMatrixDto.length >= 1 ) {
            this.freightlist = this.costService.costMasterModel.masterFreightCostMatrixDto;
            this.freightDataLength = this.costService.costMasterModel.masterFreightCostMatrixDto.length;

         }*/

        this.populateEditDetails();
        if (this.costService.costMasterModel.fkStatusName === 'Draft') {
          this.draft = true;
          this.active = false;
          this.inactive = false;
        } else if (this.costService.costMasterModel.fkStatusName === 'Active') {
          this.draft = false;
          this.active = true;
          this.inactive = false;
        } else {
          this.draft = false;
          this.active = false;
          this.inactive = true;
        }
      }
    }, error => {
      console.log(JSON.stringify(this.costService.errorMessage));
      /* this.errorModal = true;
       setTimeout(() => { this.errorModal = false; }, 3000);*/
    });
  }

  populateEditDetails() {
    this.costMasterForm.controls['pkCostId'].setValue(this.costService.costMasterModel.pkCostId);
    this.costMasterForm.controls['fkStatusId'].setValue(this.costService.costMasterModel.fkStatusId);
    this.costMasterForm.controls['costName'].setValue(this.costService.costMasterModel.costName);
    this.costMasterForm.controls['costCode'].setValue(this.costService.costMasterModel.costCode);
    this.costMasterForm.controls['costFullName'].setValue(this.costService.costMasterModel.costFullName);
    if ( this.isEditPage && this.costService.costMasterModel.fkStatusName !== 'Draft'
     && this.costService.costMasterModel.matrixApplicableInd === 'Y'  ) {
      this.costMasterForm.get('fkCostGroupId').disable();
    }
    this.costMasterForm.controls['fkCostGroupId'].setValue(this.costService.costMasterModel.fkCostGroupId);
    this.costMasterForm.controls['costTypeIsPrimaryInd'].setValue(this.costService.costMasterModel.costTypeIsPrimaryInd);
    if ( this.isEditPage && this.costService.costMasterModel.fkStatusName !== 'Draft') {
      this.costMasterForm.get('matrixApplicableInd').disable();
    }
    this.costMasterForm.controls['matrixApplicableInd'].setValue(this.costService.costMasterModel.matrixApplicableInd);
    /*if (this.selectMatrix[+this.costMasterForm.value.fkCostGroupId]) {
          this.costMasterForm.controls['fkMatrixDemoValue'].setValue(this.selectMatrix[+this.costMasterForm.value.fkCostGroupId]);
    }*/
    if ( this.costService.costMasterModel.matrixApplicableInd === 'Y') {
       this.setDefaultFlag(false);
    } else {
       this.setDefaultFlag(true);
    }
    this.costMasterForm.controls['fkMatrixEntityId'].setValue(this.costService.costMasterModel.fkMatrixEntityId);
    this.costMasterForm.controls['costDefaultValue'].setValue(this.costService.costMasterModel.costDefaultValue);
    this.costMasterForm.controls['fkcostDefValueTypeId'].setValue(this.costService.costMasterModel.fkcostDefValueTypeId);
    this.costMasterForm.controls['fkIndexUomId'].setValue(this.costService.costMasterModel.fkIndexUomId);
    this.costMasterForm.controls['fkCurrencyId'].setValue(this.costService.costMasterModel.fkCurrencyId);
    this.costMasterForm.controls['nettedForMtmInd'].setValue(this.costService.costMasterModel.nettedForMtmInd);
    this.costMasterForm.controls['realizedInd'].setValue(this.costService.costMasterModel.realizedInd);
    this.costMasterForm.controls['capitalizeCostInd'].setValue(this.costService.costMasterModel.capitalizeCostInd);
    this.costMasterForm.controls['fkRevenueGlId'].setValue(this.costService.costMasterModel.fkRevenueGlId);
    this.costMasterForm.controls['fkExpenseGlId'].setValue(this.costService.costMasterModel.fkExpenseGlId);
    this.costMasterForm.controls['fkDefaultValueBasisRefId'].setValue(this.costService.costMasterModel.fkDefaultValueBasisRefId);
    this.costMasterForm.controls['fkDefaultValueUomId'].setValue(this.costService.costMasterModel.fkDefaultValueUomId);
    this.costMasterForm.controls['fkDefValPrimaryPackingId'].setValue(this.costService.costMasterModel.fkDefValPrimaryPackingId);
    this.costMasterForm.controls['fkDefValSecondaryPackingId'].setValue(this.costService.costMasterModel.fkDefValSecondaryPackingId);
    this.costMasterForm.controls['fkPartyId'].setValue(this.costService.costMasterModel.fkPartyId);
    this.mappingList = this.costService.costMasterModel.exterPackAssoc;

    this.matrixApplicableInd = this.costService.costMasterModel.matrixApplicableInd;
    this.costTypeIsPrimaryInd = this.costService.costMasterModel.costTypeIsPrimaryInd;
    this.nettedForMtmInd = this.costService.costMasterModel.nettedForMtmInd;
    this.realizedInd = this.costService.costMasterModel.realizedInd;
    this.capitalizeCostInd = this.costService.costMasterModel.capitalizeCostInd;

    const size = Number( JSON.stringify(this.costService.costMasterModel.exterPackAssoc.length));
    console.log('mapping alone   ' + JSON.stringify(this.costService.costMasterModel.exterPackAssoc));
    if (size >= 1 ) {
      for (let i = 0; i < size; i++) {
        this.addExtSystemMapping();
      }
    }
  }

updateCost() {
   this.beforeSaveProcessData();
   // const link = this.masterSetupService.getChildObject('Update Brand.PUT');
   // const url = link.href;
  if (this.costService.validateCostMaster()) {
   this.costService.costMasterModel.action = ACTION_UPDATE;
   console.log(JSON.stringify(this.costService.costMasterModel));
   const url = '';
   this.subscription = this.costService.updateCost(url, this.costService.costMasterModel)
      .subscribe(response => {
        // Acknowledgement code
        this.isActionPerformed = true;
        this.router.navigate([cost]);
         this.costService.messages = { severity: MESSAGE_SUCCESS, summary: 'Message.Cost.Update',
         placeHolder: [ this.costService.costMasterModel.costName] };
      });
  }
}


  setDefaultFlag(val) {
    this.costService.costMasterModel.matrixApplicableInd = (this.costMasterForm.value.matrixApplicableInd === 'Y' ) ? 'N' : 'Y' ;
    this.defaultFlag = val;
  }

 /*Status Change Method for Cost */
  costStatusChange() {
    // const link = this.masterSetupService.getChildObject('Update Brand.PUT');
   // const url = link.href;
 // tslint:disable-next-line:max-line-length
      this.beforeSaveProcessData();
       if (this.costService.validateCostMaster()) {
      if ( +this.costService.costMasterModel.fkStatusId === 1) {
          this.costService.costMasterModel.action = 'Deactive';
      } else {
          this.costService.costMasterModel.action  = 'Submit';
      }
      const url = '';
      this.subscription = this.costService.updateCost(url, this.costService.costMasterModel)
      .subscribe(response => {
         this.isActionPerformed = true;
        // Acknowledgement code
             this.router.navigate([cost]);
            if (this.costService.costMasterModel.fkStatusName === 'Active') {
              this.costService.messages = { severity: MESSAGE_SUCCESS, summary: 'Message.Common.Activate',
              placeHolder: ['Cost', this.costService.costMasterModel.costName] };
            } else if (this.costService.costMasterModel.fkStatusName === 'InActive') {
              this.costService.messages = { severity: MESSAGE_SUCCESS, summary: 'Message.Common.Reactivate',
              placeHolder: ['Cost', this.costService.costMasterModel.costName] };
            } else if (this.costService.costMasterModel.fkStatusName === 'Draft') {
              this.costService.messages = { severity: MESSAGE_SUCCESS, summary: 'Message.Common.Delete',
              placeHolder: ['Cost', this.costService.costMasterModel.costName] };
            }
           // this.router.navigate([cost]);
            // this.getCostMasterById(this.editId);
            // this.loadingCostMasterList();
          });
       }
}
/* -------End----------*/

/* Status Change for Cost*/
  openConfirmAction() {
    if (this.costService.costMasterModel.fkStatusName === 'Active') {
      this.confirmationService.confirm({
        message: 'Confirm.Common.Deactivate',
        // isCommentPresent: false,
        // icon: './../assets/image/Deactiveicon2.svg',
        placeHolder: [this.costService.costMasterModel.costName],
        accept: (event) => {
          this.costStatusChange();
        },
        reject: (event) => {
        }
      });
     } else if (this.costService.costMasterModel.fkStatusName === 'InActive') {
      this.confirmationService.confirm({
        message: 'Confirm.Common.Reactivate',
        // isCommentPresent: false,
        // icon: './../assets/image/Deactiveicon2.svg',
        placeHolder: [this.costService.costMasterModel.costName],
        accept: (event) => {
          this.costStatusChange();
        },
        reject: (event) => {
        }
      });
      }
     }
  /*--------End---------*/
  onCancel() {
     this.isActionPerformed = true;
    this.router.navigate([cost]);
  }

  onReset() {

    this.costMasterForm.reset();
    this.costService.resetMandateStyle();
   // this.costService.costMasterModel.costName = null;
   /* costCode: String;
    costFullName: String;
    fkCostGroupId: number;
    fkCostGroupName: String;
    costTypeIsPrimaryInd: String = 'N';
    matrixApplicableInd: String = 'N';
    fkMatrixEntityId: Number;
    fkMatrixEntityName: String;
    costDefaultValue: Number;
    fkcostDefValueTypeId: Number;
    fkcostDefValueTypeName: String;
    fkIndexUomId: Number;
    fkCurrencyId: Number;
    fkCurrencyName: String;
    nettedForMtmInd: String = 'N';
    realizedInd: String = 'N';
    capitalizeCostInd: String = 'N';
    fkRevenueGlId: Number;
    fkRevenueGlName: String;
    fkExpenseGlId: Number;
    fkExpenseGlName: String;
    fkDefaultValueBasisRefId: Number;
    fkDefaultValueBasisRefName: String;
    fkDefaultValueUomId: Number;
    fkDefaultValueUomName: String;
    fkDefValPrimaryPackingId: Number;
    fkDefValPrimaryPackingName: String;
    fkDefValSecondaryPackingId: Number;
    fkDefValSecondaryPackingName: String;
    this.costService.costMasterModel = new CostMaster();*/
  }

}
