import { Component, OnInit, ViewEncapsulation , OnChanges , Input } from '@angular/core';
import { AccordionModule } from 'ngx-bootstrap';
import { FormBuilder, FormGroup, FormControl, Validators, FormArray, ReactiveFormsModule, FormsModule } from '@angular/forms';
import { WareHouseMatrix  , WareHouseMatrixMain , WarehouseCostArray} from '../model/cost-warehouse';
 import { WareHouseMappingComponent } from './warehouse-mapping.component';
 import { SelectItem } from '../../../../shared/interface/selectItem';
 import { TreeviewConfig, TreeviewItem } from 'ngx-treeview';
 import { CostMatrixService } from '../../cost-matrix/service/cost-matrix.service';
 import { ProductMaster} from '../../cost/model/add-cost-model';
 @Component({
  selector: 'app-add-warehouse',
  templateUrl: './add-warehouse.component.html',
  styleUrls: ['./add-warehouse.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class AddWarehouseComponent implements OnChanges {

   public status: any = {
    isFirstOpen: true,
    isFirstDisabled: false
  };
    // tslint:disable-next-line:no-input-rename
  @Input('group')
  public whCostForm: FormGroup;
  @Input() public wareHouseData: WareHouseMatrix;
  @Input() public addEditId: number;
  @Input() public arrIndex: number;
  warehouseLoc =  [];
  whPartyList: SelectItem[];
  whModel: WareHouseMatrix= new WareHouseMatrix();
  reqProductId: boolean;
  public whMappingList: WarehouseCostArray [] = [];
  public costIsDetailedInd: string;
  constructor(private fb: FormBuilder , public costMatrixService: CostMatrixService) { }
    ngOnChanges() {
      // tslint:disable-next-line:max-line-length
      this.warehouseLoc = [{fkPartyWhStockLocId: 1, fkPartyWhStockLocIdName: 'Helsinki'},
      {fkPartyWhStockLocId: 2, fkPartyWhStockLocIdName: 'Singapore'}, {fkPartyWhStockLocId: 3, fkPartyWhStockLocIdName: 'India'} , {fkPartyWhStockLocId: 4, fkPartyWhStockLocIdName: 'USA'}];
      this.whPartyList = [{label: 1 , value: 'Trader'}, {label: 2 , value: 'Rosters'} ,
       {label: 3 , value: 'Service Provider'} , {label: 4 , value: 'Broker'} ,
       {label: 5 , value: 'Certification Agency'} , {label: 6 , value: 'Exchange'}];
      if (this.addEditId !== 0 && this.wareHouseData[this.arrIndex] !== undefined) {
        this.editWareDetails();
      }else {
        this.addWareHouseMapping();
      }
      console.log('99999999999999999999999', this.whCostForm.value);
      this.whCostForm.valueChanges.subscribe(data => {
        console.log(data);
      /*this.costMatrixService.costMasterModel.wareHouseCostDto = data.wareHouseMatrix;
      for ( let i = 0; i < this.costMatrixService.costMasterModel.wareHouseCostDto.length; i++) {
        this.costMatrixService.costMasterModel.wareHouseCostDto[i].costIsDetailedInd =
         this.costMatrixService.costMasterModel.wareHouseCostDto[i]['costIsDetailedInd' + i];
      }*/
    })
  }
    // task related functions
    getWarehouseMappingList(whCostForm) {
      return whCostForm.get('whCostDetailArray').controls;
    }

  addWareHouseMapping() {
    const control = <FormArray>this.whCostForm.controls['whCostDetailArray'];
    const addrCtrl = this.initwhCost();
    control.push(addrCtrl);
  }

  addWhMapping() {
    this.addWareHouseMapping();
  }

  initwhCost() {
    return this.fb.group({
      'costName': [''],
      'rateBasis': [''],
      'whfkDefaultValueUomId': [''],
      'whfkDefValPrimaryPackingId': [''],
      'whfkDefValSecondaryPackingId': [''],
      'rateCurrency': [''],
      'rate': [''],
      'timeBasis': [''],
      'pkWhDetailId': ['']
    });
  }
  removeWarehouseMapping(k: number) {
    if (this.whCostForm.value.whCostDetailArray.length > 1 ) {
            const control = <FormArray>this.whCostForm.controls['whCostDetailArray'];
            control.removeAt(k);
            if (this.costMatrixService.whDataList[this.arrIndex] !== undefined) {
                if (this.costMatrixService.whDataList[this.arrIndex].whCostDetailArray[k] != null) {
                  this.costMatrixService.whDataList[this.arrIndex].whCostDetailArray.splice(k, 1);
                }
            }
     }
  }

  editWareDetails() {
    // pkWhCostId
    this.whCostForm.controls['pkWhCostId'].setValue(this.wareHouseData[this.arrIndex].pkWhCostId);
     this.whCostForm.controls['legalEntity'].setValue(this.wareHouseData[this.arrIndex].legalEntity);
     this.whCostForm.controls['fkProdId'].setValue(this.wareHouseData[this.arrIndex].fkProdId);
     this.whCostForm.controls['costIsDetailedInd'].setValue(this.wareHouseData[this.arrIndex].costIsDetailedInd);
     // tslint:disable-next-line:max-line-length
     this.whCostForm.controls['costIsDetailedInd' + this.arrIndex].setValue(this.wareHouseData[this.arrIndex].costIsDetailedInd);
     this.whCostForm.controls['wareHouse'].setValue(this.wareHouseData[this.arrIndex].wareHouse);
     this.whCostForm.controls['wareHouseLocation'].setValue(this.wareHouseData[this.arrIndex].wareHouseLocation);
     this.whCostForm.controls['whCostContrRefNo'].setValue(this.wareHouseData[this.arrIndex].whCostContrRefNo);
     this.whCostForm.controls['whCostValidFrom'].setValue(new Date(this.wareHouseData[this.arrIndex].whCostValidFrom));
     this.whCostForm.controls['whCostValidTo'].setValue(new Date(this.wareHouseData[this.arrIndex].whCostValidTo));
     this.whCostForm.controls['whCostRemarks'].setValue(this.wareHouseData[this.arrIndex].whCostRemarks);
     // this.whCostForm.controls['whCostFreePeriodType'].setValue(this.wareHouseData[this.arrIndex].whCostFreePeriodType);
     this.whCostForm.controls['whCostNoOfFreeDays'].setValue(this.wareHouseData[this.arrIndex].whCostNoOfFreeDays);
    const sizerollemapping = Number( JSON.stringify(this.wareHouseData[this.arrIndex].whCostDetailArray.length));
    this.whMappingList = this.wareHouseData[this.arrIndex].whCostDetailArray;
      if (sizerollemapping >= 1 ) {
        for (let i = 0; i <  sizerollemapping ; i++) {
          this.addWareHouseMapping();
        }
    }
  }
}
