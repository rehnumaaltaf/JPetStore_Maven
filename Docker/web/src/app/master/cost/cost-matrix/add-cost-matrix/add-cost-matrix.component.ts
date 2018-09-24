import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AccordionModule } from 'ngx-bootstrap';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { FormBuilder, FormGroup, FormControl, Validators, FormArray, ReactiveFormsModule, FormsModule } from '@angular/forms';
import { costmatrix } from '../../../../shared/interface/router-links';
import { CostMatrixService } from '../service/cost-matrix.service';
import { Subscription } from 'rxjs/Subscription';
import { SelectMatrix, CostMatrix } from '../model/cost-matrix.model';
import { DefaultUnit } from '../../cost/model/add-cost-model';
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
import { CostService } from '../../cost/service/cost.service';
import { CanComponentDeactivate } from '../../../../shared/guards/can-deactivate-guard';

@Component({
  selector: 'app-add-cost-matrix',
  templateUrl: './add-cost-matrix.component.html',
  styleUrls: ['./add-cost-matrix.component.css']
})
export class AddCostMatrixComponent implements OnInit, CanComponentDeactivate {

  public editId;
  public addEditCostMatrixId;
  public isEditPage: Boolean = false;
  public isActionPerformed = false;
  public costMatrixForm: FormGroup;
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
  wareHouseList: WareHouseMatrix[] = [];
  public wareHouseLength: Number;
  cnfCostList: CnfCostMatrix[] = [];
  public cnfCostLength: Number;
  freightlist: FreightMatrix[] = [];
  public freightDataLength: Number;
  costMatrixModel: CostMatrix= new CostMatrix();

  actionSave = ACTION_SAVE;
  actionSubmit = ACTION_SUBMIT;
  actionUpdate = ACTION_UPDATE;
  actionActive = ACTION_ACTIVE;
  actionDeactive = ACTION_DEACTIVE;
   constructor(public datepipe: DatePipe, private fb: FormBuilder, private route: ActivatedRoute,
    private router: Router, public costMatrixService: CostMatrixService,
    public costService: CostService, private confirmationService: ConfirmationService) { }

  ngOnInit() {
    // this.costMatrixService.messages = new MessageModel();
    if (this.route.snapshot.params['id'] !== undefined) {
      this.editId = this.route.snapshot.params['id'];
      this.addEditCostMatrixId = this.editId;
      this.isEditPage = true;
      this.buildCostMatrixForm();
      this.getCostMatrixById(this.editId);
      this.loadDropdowns();
    } else {
      this.costMatrixService.costMatrixModel = new CostMatrix();
      this.addEditCostMatrixId = 0;
      this.buildCostMatrixForm();
      this.loadDropdowns();
      this.costMatrixService.resetMandateStyle();
    }
  }

  /*This function is called when user is trying to come out the component*/
   canDeactivate(): boolean {
    return this.isActionPerformed;
  }
  changedCostGroup() {
        this.costMatrixForm.controls['matrixTypeId'].setValue(this.costMatrixForm.value.fkCostGroupId);
        if (this.selectMatrix[+this.costMatrixForm.value.fkCostGroupId]) {
           this.costMatrixForm.controls['fkMatrixDemoValue'].setValue(this.selectMatrix[+this.costMatrixForm.value.fkCostGroupId]);
        }
  }
  buildCostMatrixForm(): void {
    this.costMatrixForm = this.fb.group({
      'pkCostMatrixId': [this.costMatrixService.costMatrixModel.pkCostMatrixId],
      'fkStatusId': [this.costMatrixService.costMatrixModel.fkStatusId],
      'matrixCode': [this.costMatrixService.costMatrixModel.matrixCode],
      'matrixName': [this.costMatrixService.costMatrixModel.matrixName],
      'matrixDesc': [this.costMatrixService.costMatrixModel.matrixDesc],
      'matrixTypeId': [this.costMatrixService.costMatrixModel.matrixTypeId],
      'fkCostId': [this.costMatrixService.costMatrixModel.fkCostId]
    });
  }

  loadDropdowns(): void {
    this.getPartyList();
    this.getCurrencyList();
    this.getUomList();
    this.getPrimaryPackingList();
    this.getSecondaryPackingList();
    this.loadingCostMasterList();
  }
  loadingCostMasterList() {
     this.subscription = this.costService.getCostMasterDetailsJSON().subscribe(getMasterDetail => {
     this.costMatrixService.costNameList = getMasterDetail.body;
    // alert(getMasterDetail.body);
     });
  }


  getUomList(): void {
    // const link = this.masterSetupService.getChildObject('Create SECPACK.POST');
    this.subscription = this.costMatrixService.getUomList().subscribe(data => {
      console.log(data);
      if (data.body !== null && data.body !== '') {
        this.costMatrixService.defaultUnitUomList = data.body;
      }
    }, error => {
      console.log(JSON.stringify(this.costMatrixService.errorMessage));
    });
  }

  getPrimaryPackingList(): void {
    // const link = this.masterSetupService.getChildObject('Create SECPACK.POST');
    this.subscription = this.costMatrixService.getPrimaryPackingList().subscribe(data => {
      console.log(data);
      if (data.body !== null && data.body !== '') {
        this.costMatrixService.defaultUnitPrimaryPackList = data.body;
      }
    }, error => {
      console.log(JSON.stringify(this.costMatrixService.errorMessage));
    });
  }

  getSecondaryPackingList(): void {
    // const link = this.masterSetupService.getChildObject('Create SECPACK.POST');
    this.subscription = this.costMatrixService.getSecondaryPackingList().subscribe(data => {
      console.log(data);
      if (data.body !== null && data.body !== '') {
        this.costMatrixService.defaultUnitSecondaryPackList = data.body;
      }
    }, error => {
      console.log(JSON.stringify(this.costMatrixService.errorMessage));
    });
  }

 getPartyList(): void {
    // const link = this.masterSetupService.getChildObject('Create SECPACK.POST');
    this.subscription = this.costMatrixService.getPartyList().subscribe(data => {
      console.log(data);
      if (data.body !== null && data.body !== '') {
        this.costMatrixService.partyList = data.body;
        for ( let i = 0; i < this.costMatrixService.partyList.length; i++  ) {
          this.costMatrixService.partyList[i].fkPartyId = this.costMatrixService.partyList[i].partyId;
          this.costMatrixService.partyList[i].fkPartyName = this.costMatrixService.partyList[i].partyName;
        }
      }
    }, error => {
      console.log(JSON.stringify(this.costMatrixService.errorMessage));
    });
  }
  getCurrencyList(): void {
    // const link = this.masterSetupService.getChildObject('Create SECPACK.POST');
    this.subscription = this.costMatrixService.getCurrencyList().subscribe(data => {
      if (data.body !== null && data.body !== '') {
        this.costMatrixService.currencyList = data.body;
      }
    }, error => {
      console.log(JSON.stringify(this.costMatrixService.errorMessage));
    });
  }

  changeDate(date) {
    return this.datepipe.transform(date, 'yyyy-MM-dd');
  }

  beforeSaveProcessData() {
    if (+this.costMatrixForm.value.matrixTypeId === +this.selectMatrix.Warehouse) {
      const warehousedto = this.costMatrixService.costMatrixModel.wareHouseCostDto;
      this.costMatrixService.costMatrixModel = this.costMatrixForm.value;
      this.costMatrixService.costMatrixModel.wareHouseCostDto = warehousedto;
      for ( let i = 0; i < this.costMatrixService.costMatrixModel.wareHouseCostDto.length; i++ ) {
        let tempLegalEntity;
        tempLegalEntity = [];
        let tempLegalWHL;
        tempLegalWHL = [];
        if (!this.costMatrixService.costMatrixModel.wareHouseCostDto[i].legalEntity) {
          this.costMatrixService.costMatrixModel.wareHouseCostDto[i].legalEntity = tempLegalEntity;
        }
      }
    } else if (+this.costMatrixForm.value.matrixTypeId === +this.selectMatrix.Clearing_Forwarding) {
      const cnfCostDto = this.costMatrixService.costMatrixModel.cnfCostDto;
      this.costMatrixService.costMatrixModel = this.costMatrixForm.value;
      this.costMatrixService.costMatrixModel.cnfCostDto = cnfCostDto;
      for (let i = 0; i < this.costMatrixService.costMatrixModel.cnfCostDto.length; i++) {
        let tempLegalEntity;
        tempLegalEntity = [];
        let tfkPartyWhStockLocationId;
        tfkPartyWhStockLocationId = [];
        for (let j = 0; j < this.costMatrixService.costMatrixModel.cnfCostDto[i].fkPartyId.length; j++) {
          tempLegalEntity.push({ fkPartyId: this.costMatrixService.costMatrixModel.cnfCostDto[i].fkPartyId[j].partyId });
        }
        this.costMatrixService.costMatrixModel.cnfCostDto[i].fkPartyId = tempLegalEntity;

        for (let j = 0; j < this.costMatrixService.costMatrixModel.cnfCostDto[i].fkPartyWhStockLocationId.length; j++) {
          tfkPartyWhStockLocationId.push({ fkPartyWhStockLocationId:
             this.costMatrixService.costMatrixModel.cnfCostDto[i].fkPartyWhStockLocationId[j].value });
        }
        this.costMatrixService.costMatrixModel.cnfCostDto[i].fkPartyWhStockLocationId = tfkPartyWhStockLocationId;
      }
   }else if (+this.costMatrixForm.value.matrixTypeId === +this.selectMatrix.Freight) {
      const masterFreightCostMatrixDto = this.costMatrixService.costMatrixModel.freightCostMatrixDto;
   //   alert('FreightCost' + this.costMatrixService.costMatrixModel.masterFreightCostMatrixDto);
      this.costMatrixService.costMatrixModel = this.costMatrixForm.value;
      this.costMatrixService.costMatrixModel.freightCostMatrixDto = masterFreightCostMatrixDto;
      for (let i = 0; i < this.costMatrixService.costMatrixModel.freightCostMatrixDto.length; i++) {
        let tempLegalEntity;
        tempLegalEntity = [];
        let tfkPartyWhStockLocationId;
        tfkPartyWhStockLocationId = [];
        for (let j = 0; j < this.costMatrixService.costMatrixModel.freightCostMatrixDto[i].fkPartyId.length; j++) {
          tempLegalEntity.push({ fkPartyId: this.costMatrixService.costMatrixModel.freightCostMatrixDto[i].fkPartyId[j].fkPartyId});
        }
        this.costMatrixService.costMatrixModel.freightCostMatrixDto[i].fkPartyId = tempLegalEntity;

    /*    for (let j = 0; j < this.costMatrixService.costMatrixModel.freightCostDto[i].fkPartyWhStockLocationId.length; j++) {
          tfkPartyWhStockLocationId.push({ fkPartyWhStockLocationId:
             this.costMatrixService.costMatrixModel.freightCostDto[i].fkPartyWhStockLocationId[j].value });
        }
        this.costMatrixService.costMatrixModel.freightCostDto[i].fkPartyWhStockLocationId = tfkPartyWhStockLocationId;
  */    }
    } else {
      this.costMatrixService.costMatrixModel = this.costMatrixForm.value;
      /* let tempCompany;
      tempCompany = [];
      console.log(this.costMatrixService.costMatrixModel.fkPartyId);
      for (let i = 0; i < this.costMatrixService.costMatrixModel.fkPartyId.length; i++) {
          tempCompany.push({ fkPartyId: this.costMatrixService.costMatrixModel.fkPartyId[i].partyId });
      }
      console.log('here', tempCompany);
      this.costMatrixService.costMatrixModel.fkPartyId = tempCompany;*/
    }
  }
  submit() {
    this.beforeSaveProcessData();
       if (this.costMatrixService.validateCostMatrix()) {
          this.costMatrixService.costMatrixModel.action = ACTION_SUBMIT;
          // const link = this.masterSetupService.getChildObject('Create Brand.POST');
          // const url = link.href;
          const url = '';
          if (this.costMatrixService.costMatrixModel.pkCostMatrixId) {
             this.subscription = this.costMatrixService.updateCost(url, this.costMatrixService.costMatrixModel).subscribe(costDetail => {
              // this.costMatrixService.messages = { severity: MESSAGE_SUCCESS, summary: costDetail.body };
               this.isActionPerformed = true;
              this.router.navigate([costmatrix]);
              if (this.isEditPage) {
                  this.costMatrixService.messages = { severity: MESSAGE_SUCCESS, summary: 'Message.Common.Update',
                  placeHolder: ['Cost Matrix', this.costMatrixService.costMatrixModel.matrixName] };
              }else {
                  this.costMatrixService.messages = { severity: MESSAGE_SUCCESS, summary: 'Message.Cost.Update',
                  placeHolder: [ this.costMatrixService.costMatrixModel.matrixName] };
              }
              // this.router.navigate([costmatrix]);
            },
            error => {
            });
          } else {
            this.subscription = this.costMatrixService.saveCostDetails(url, this.costMatrixService.costMatrixModel).
          subscribe(costDetail => {
             this.isActionPerformed = true;
          this.router.navigate([costmatrix]);
          if (this.isEditPage) {
            this.costMatrixService.messages = { severity: MESSAGE_SUCCESS, summary: 'Message.Common.Submit',
            placeHolder: ['Cost Matrix', this.costMatrixService.costMatrixModel.matrixName] };
          }else {
            this.costMatrixService.messages = { severity: MESSAGE_SUCCESS, summary: 'Message.Cost.Submit',
            placeHolder: [ this.costMatrixService.costMatrixModel.matrixName] };
          }
        },
        error => {
        });
        }
      }
  }

  save() {
      this.beforeSaveProcessData();
       if (this.costMatrixService.validateCostMatrix()) {
          this.costMatrixService.costMatrixModel.action = ACTION_SAVE;
          // const link = this.masterSetupService.getChildObject('Create Brand.POST');
          // const url = link.href;
          console.log('master with warehouse matrix   ' +  JSON.stringify(this.costMatrixService.costMatrixModel));
          const url = '';
          if (this.costMatrixService.costMatrixModel.pkCostMatrixId) {
             this.subscription = this.costMatrixService.updateCost(url, this.costMatrixService.costMatrixModel).subscribe(costDetail => {
              // this.costMatrixService.messages = { severity: MESSAGE_SUCCESS, summary: costDetail.body };
               this.isActionPerformed = true;
              this.router.navigate([costmatrix]);
              if (this.isEditPage) {
                  this.costMatrixService.messages = { severity: MESSAGE_SUCCESS, summary: 'Message.Common.Update',
                  placeHolder: ['Cost Matrix', this.costMatrixService.costMatrixModel.matrixName] };
              }else {
                  this.costMatrixService.messages = { severity: MESSAGE_SUCCESS, summary: 'Message.Cost.Update',
                  placeHolder: [ this.costMatrixService.costMatrixModel.matrixName] };
              }
              // this.router.navigate([costmatrix]);
            },
            error => {
            });
          } else {
             this.subscription = this.costMatrixService.saveCostDetails(url, this.costMatrixService.costMatrixModel)
             .subscribe(costDetail => {
              // this.costMatrixService.messages = { severity: MESSAGE_SUCCESS, summary: costDetail.body };
               this.isActionPerformed = true;
              this.router.navigate([costmatrix]);
              if (this.isEditPage) {
                  this.costMatrixService.messages = { severity: MESSAGE_SUCCESS, summary: 'Message.Common.Save',
                  placeHolder: ['Cost Matrix', this.costMatrixService.costMatrixModel.matrixName] };
              }else {
                  this.costMatrixService.messages = { severity: MESSAGE_SUCCESS, summary: 'Message.Cost.Save',
                  placeHolder: [ this.costMatrixService.costMatrixModel.matrixName] };
              }
              // this.router.navigate([costmatrix]);
            },
            error => {
            });
          }
      }
  }


  getCostMatrixById(editId) {
    this.subscription = this.costMatrixService.getCostMatrixById(editId, 'Warehouse').subscribe(data => {
      if (data.body !== null && data.body !== '') {
        this.costMatrixService.costMatrixModel = data.body;
         if (this.costMatrixService.costMatrixModel.wareHouseCostDto &&
         this.costMatrixService.costMatrixModel.wareHouseCostDto.length >= 1 ) {
           // this.wareHouseList = this.costMatrixService.costMatrixModel.wareHouseCostDto;
            this.costMatrixService.whDataList = this.costMatrixService.costMatrixModel.wareHouseCostDto
            this.wareHouseLength = this.costMatrixService.costMatrixModel.wareHouseCostDto.length;
         }
         if (this.costMatrixService.costMatrixModel.cnfCostDto &&
         this.costMatrixService.costMatrixModel.cnfCostDto.length >= 1 ) {
            this.cnfCostList = this.costMatrixService.costMatrixModel.cnfCostDto;
            this.cnfCostLength = this.costMatrixService.costMatrixModel.cnfCostDto.length;
            }

         if (this.costMatrixService.costMatrixModel.freightCostMatrixDto &&
          this.costMatrixService.costMatrixModel.freightCostMatrixDto.length >= 1 ) {
            this.freightlist = this.costMatrixService.costMatrixModel.freightCostMatrixDto;
            this.freightDataLength = this.costMatrixService.costMatrixModel.freightCostMatrixDto.length;
         }


        this.populateEditDetails();
        if (this.costMatrixService.costMatrixModel.fkStatusName === 'Draft') {
          this.draft = true;
          this.active = false;
          this.inactive = false;
        } else if (this.costMatrixService.costMatrixModel.fkStatusName === 'Active') {
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
      console.log(JSON.stringify(this.costMatrixService.errorMessage));
      /* this.errorModal = true;
       setTimeout(() => { this.errorModal = false; }, 3000);*/
    });
  }

  populateEditDetails() {
    this.costMatrixForm.controls['pkCostMatrixId'].setValue(this.costMatrixService.costMatrixModel.pkCostMatrixId);
    this.costMatrixForm.controls['fkStatusId'].setValue(this.costMatrixService.costMatrixModel.fkStatusId);
    this.costMatrixForm.controls['matrixName'].setValue(this.costMatrixService.costMatrixModel.matrixName);
    this.costMatrixForm.controls['matrixCode'].setValue(this.costMatrixService.costMatrixModel.matrixCode);
    this.costMatrixForm.controls['matrixDesc'].setValue(this.costMatrixService.costMatrixModel.matrixDesc);
    this.costMatrixForm.controls['matrixTypeId'].setValue(this.costMatrixService.costMatrixModel.matrixTypeId);
    this.costMatrixForm.controls['fkCostId'].setValue(this.costMatrixService.costMatrixModel.fkCostId);
  }

updateMatrix() {
   this.beforeSaveProcessData();
   // const link = this.masterSetupService.getChildObject('Update Brand.PUT');
   // const url = link.href;
   if (this.costMatrixService.validateCostMatrix()) {
   this.costMatrixService.costMatrixModel.action = ACTION_UPDATE;
   const url = '';
   this.subscription = this.costMatrixService.updateCost(url, this.costMatrixService.costMatrixModel)
      .subscribe(response => {
        // Acknowledgement code
        this.isActionPerformed = true;
        this.router.navigate([costmatrix]);
         this.costMatrixService.messages = { severity: MESSAGE_SUCCESS, summary: 'Message.Cost.Update',
         placeHolder: [ this.costMatrixService.costMatrixModel.matrixName] };
        // this.costMatrixService.messages = { severity: MESSAGE_SUCCESS, summary: response.body};
        // this.loadingCostMatrixList();
      });
   }
}

 /*Status Change Method for Cost */
  costStatusChange() {
    // const link = this.masterSetupService.getChildObject('Update Brand.PUT');
   // const url = link.href;
 // tslint:disable-next-line:max-line-length
    this.beforeSaveProcessData();
    if (this.costMatrixService.validateCostMatrix()) {
    if ( +this.costMatrixService.costMatrixModel.fkStatusId === 1) {
          this.costMatrixService.costMatrixModel.action = 'Deactive';
      } else {
          this.costMatrixService.costMatrixModel.action  = 'Submit';
      }

   const url = '';
   this.subscription = this.costMatrixService.updateCost(url, this.costMatrixService.costMatrixModel)
      .subscribe(response => {
        // Acknowledgement code
            this.isActionPerformed = true;
             this.router.navigate([costmatrix]);
            if (this.costMatrixService.costMatrixModel.fkStatusName === 'Active') {
              this.costMatrixService.messages = { severity: MESSAGE_SUCCESS, summary: 'Message.Common.Activate',
              placeHolder: ['Cost Matrix', this.costMatrixService.costMatrixModel.matrixName] };
            } else if (this.costMatrixService.costMatrixModel.fkStatusName === 'InActive') {
              this.costMatrixService.messages = { severity: MESSAGE_SUCCESS, summary: 'Message.Common.Reactivate',
              placeHolder: ['Cost Matrix', this.costMatrixService.costMatrixModel.matrixName] };
            } else if (this.costMatrixService.costMatrixModel.fkStatusName === 'Draft') {
              this.costMatrixService.messages = { severity: MESSAGE_SUCCESS, summary: 'Message.Common.Delete',
              placeHolder: ['Cost Matrix', this.costMatrixService.costMatrixModel.matrixName] };
            }
            //  this.getCostMatrixById(this.editId);
            // this.loadingCostMatrixList();
          });
     }
}
/* -------End----------*/

/* Status Change for Cost*/
  openConfirmAction() {
    if (this.costMatrixService.costMatrixModel.fkStatusName === 'Active') {
      this.confirmationService.confirm({
        message: 'Confirm.Common.Deactivate',
        // isCommentPresent: false,
        // icon: './../assets/image/Deactiveicon2.svg',
        placeHolder: [this.costMatrixService.costMatrixModel.matrixName],
        accept: (event) => {
          this.costStatusChange();
        },
        reject: (event) => {
        }
      });
     } else if (this.costMatrixService.costMatrixModel.fkStatusName === 'InActive') {
      this.confirmationService.confirm({
        message: 'Confirm.Common.Reactivate',
        // isCommentPresent: false,
        // icon: './../assets/image/Deactiveicon2.svg',
        placeHolder: [this.costMatrixService.costMatrixModel.matrixName],
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
    this.router.navigate([costmatrix]);
  }
  onReset() {
    this.costMatrixForm.reset();
    this.costMatrixService.resetMandateStyle();
  }
}
