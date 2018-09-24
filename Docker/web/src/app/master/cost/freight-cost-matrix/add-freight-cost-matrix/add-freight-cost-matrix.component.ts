import { Component, OnInit, ViewEncapsulation , OnChanges , Input } from '@angular/core';
import { AccordionModule } from 'ngx-bootstrap';
import { FormBuilder, FormGroup, FormControl, Validators, FormArray, ReactiveFormsModule, FormsModule } from '@angular/forms';
import { SelectItem } from '../../../../shared/interface/selectItem';
import { TreeviewConfig, TreeviewItem } from 'ngx-treeview';
import { FreightMatrix, FreightMain, FreightDetailDto } from '../model/freight-cost.model';
import { FreightMappingComponent } from './freight-mapping.component';
import { CostMatrixService } from '../../cost-matrix/service/cost-matrix.service';
import { Subscription } from 'rxjs/Subscription';

@Component({
  selector: 'app-add-freight-cost-matrix',
  templateUrl: './add-freight-cost-matrix.component.html',
  styleUrls: ['./add-freight-cost-matrix.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class AddFreightCostMatrixComponent implements OnChanges {

  public status: any = {
    isFirstOpen: true,
    isFirstDisabled: false
  };
  private subscription: Subscription;
    // tslint:disable-next-line:no-input-rename
  @Input('group')
  public freightForm: FormGroup;
  @Input() public freightData: FreightMatrix;
  @Input() public addEditId: number;
  @Input() public arrIndex: number;
  warehouseLoc = [];
  legalEntityList = [];
  freightModel: FreightMatrix= new FreightMatrix();
  reqProductId: boolean;
  public freightDtlList: FreightDetailDto [] = [];
 // public freightCostIsDetailedInd: string;
  constructor(private fb: FormBuilder, public costMatrixService: CostMatrixService) { }

    ngOnChanges() {
    //  this.addFreightMapping();
      this.getPartyList();
      // tslint:disable-next-line:max-line-length
   //   this.warehouseLoc = [{value: 1, label: 'UOM'}, {value: 2, label: 'Primary Packing'}, {value: 3, label: 'Secondary Packing'}];
    //  this.legalEntityList = [{value: 1, label: 'OIL'}, {value: 2, label: 'Sample Data'}];
    if (this.addEditId !== 0 && this.freightData[this.arrIndex] !== undefined) {
        this.editFreightCostDetails();
      }else {
        this.addFreightMapping();
      }

  }


    // task related functions
    getFreightMappingList(freightForm) {
      return freightForm.get('masterFreightCostdetailDto').controls;
    }

  addFreightCostMapping() {
    const control = <FormArray> this.freightForm.controls['masterFreightCostdetailDto'];
    const addrCtrl = this.initfreightmappingCost();
    control.push(addrCtrl);
  }

  addFreightMapping() {
    this.addFreightCostMapping();
  }



  initfreightmappingCost() {
    return this.fb.group({
      'pkFreightCostDetailId': [''],
      'fkSourceGeoId': [''],
      'fkSourceLocationId': [''],
      'fkDestinationGeoId': [''],
      'fkDestinationLocationId': [''],
      'fkRateBasisRefId': [''],
      'fkRateUomId': [''],
      'rate': [''],
      'fkRateCurrencyId': [''],
      'transitDays': [''],
      'freeDaysAtLoadPort': [''],
      'freeDaysAtDestiantionPort': [''],
      'fkRatePrimaryPackingId': [''],
      'fkRateSecondaryPacking': ['']
    });
  }

  removeFreightMapping(k: number) {
    if (this.freightForm.value.masterFreightCostdetailDto.length > 1 ) {
            const control = <FormArray>this.freightForm.controls['masterFreightCostdetailDto'];
            control.removeAt(k);
       if (this.freightModel.masterFreightCostdetailDto != null) {
          this.freightModel.masterFreightCostdetailDto.splice(k, 1);
        }
     }
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
      /*this.errorModal = true;
      setTimeout(() => { this.errorModal = false; }, 3000);*/
    });
  }

  editFreightCostDetails() {
 //   alert(this.arrIndex);
   //  console.log('inside the edit' + JSON.stringify(this.freightData[this.arrIndex]));
     this.freightForm.controls['pkFreightCostId'].setValue(this.freightData[this.arrIndex].pkFreightCostId);
     this.freightForm.controls['fkPartyId'].setValue(this.freightData[this.arrIndex].fkPartyId);
     this.freightForm.controls['fkProdId'].setValue(this.freightData[this.arrIndex].fkProdId);
    // this.freightForm.controls['freightCostIsDetailedInd'].setValue(this.freightData[this.arrIndex].freightCostIsDetailedInd);
     // tslint:disable-next-line:max-line-length
     this.freightForm.controls['freightCostIsDetailedInd' + this.arrIndex].setValue(this.freightData[this.arrIndex].freightCostIsDetailedInd);
     this.freightForm.controls['fkServiceProviderPartyId'].setValue(this.freightData[this.arrIndex].fkServiceProviderPartyId);
     this.freightForm.controls['freightCostContrRefNo'].setValue(this.freightData[this.arrIndex].freightCostContrRefNo);
     this.freightForm.controls['freightCostRemarks'].setValue(this.freightData[this.arrIndex].freightCostRemarks);
     this.freightForm.controls['freightCostValidFrom'].setValue(new Date(this.freightData[this.arrIndex].freightCostValidFrom));
     this.freightForm.controls['freightCostValidTo'].setValue(new Date(this.freightData[this.arrIndex].freightCostValidTo));
    const sizerollemapping = Number( JSON.stringify(this.freightData[this.arrIndex].masterFreightCostdetailDto.length));
    this.freightDtlList = this.freightData[this.arrIndex].masterFreightCostdetailDto;
      if (sizerollemapping >= 1 ) {
        for (let i = 0; i <  sizerollemapping ; i++) {
          this.addFreightMapping();
        }
    }
  }

}
