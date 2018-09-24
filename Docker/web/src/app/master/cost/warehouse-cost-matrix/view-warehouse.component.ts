import { Component, OnChanges, Output, Input } from '@angular/core';
import { FormBuilder, FormGroup, FormControl, Validators, FormArray, ReactiveFormsModule, FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { Subscription } from 'rxjs/Subscription';
import { AddWarehouseComponent } from './add-warehouse/add-warehouse.component';
import { WareHouseMatrix , WareHouseMatrixMain } from './model/cost-warehouse';
import { CostMatrixService } from '../cost-matrix/service/cost-matrix.service';
import { ProductMaster} from './../cost/model/add-cost-model';
import { DatePipe } from '@angular/common';

@Component({
  moduleId: module.id,
  selector: 'app-view-warehouse-mapping',
  templateUrl: 'view-warehouse.component.html',
})
export class ViewWareHouseComponent implements OnChanges {
public wareArray: WareHouseMatrix[] = [];
public wareHouseFormMain: FormGroup;
private subscription: Subscription;
WareHouseMatrixModel: WareHouseMatrixMain = new WareHouseMatrixMain();
@Input() public wareHouseData: WareHouseMatrixMain;
@Input() public wareHouseLength: number;
@Input() public addEditId: number;
constructor(private fb: FormBuilder , private costMatrixService: CostMatrixService, private datePipe: DatePipe) { }
  ngOnChanges() {
    this.buildWare();
    this.dropdownList();

    this.wareHouseFormMain.valueChanges.subscribe(data => {
      this.costMatrixService.costMatrixModel.wareHouseCostDto = data.wareHouseMatrix;
      for ( let i = 0; i < this.costMatrixService.costMatrixModel.wareHouseCostDto.length; i++) {
        /*this.costMatrixService.costMatrixModel.wareHouseCostDto[i].whCostValidFrom =
        this.transformDate(this.costMatrixService.costMatrixModel.wareHouseCostDto[i].whCostValidFrom)
        this.costMatrixService.costMatrixModel.wareHouseCostDto[i].whCostValidTo =
        this.transformDate(this.costMatrixService.costMatrixModel.wareHouseCostDto[i].whCostValidTo)*/
        this.costMatrixService.costMatrixModel.wareHouseCostDto[i].costIsDetailedInd =
         this.costMatrixService.costMatrixModel.wareHouseCostDto[i]['costIsDetailedInd' + i];
      }
    })

    if (this.addEditId !== 0) {
       const size = Number( JSON.stringify(this.wareHouseLength));
        if (size > 0 ) {
          for (let i = 0; i < size; i++) {
            console.log('i value   ' + i);
            this.addWarehouse(i - 1);
          }
        }
    }else {
      this.addWarehouse(-1);
    }
  }


  transformDate ( date: string ) {
    return this.datePipe.transform(date, 'dd/MM/yyyy');
   }
  buildWare(): void {
    this.wareHouseFormMain = this.fb.group({
      'wareHouseMatrix': this.fb.array([])
    });
  }

  // task related functions
  getWarehouseList(wareHouseFormMain) {
    return wareHouseFormMain.get('wareHouseMatrix').controls;
  }
  addWarehouse(i) {
    const control = <FormArray>this.wareHouseFormMain.controls['wareHouseMatrix'];
    const addrCtrl = this.initwhCostMain(i + 1);
    control.push(addrCtrl);
  }

  initwhCostMain(i) {
   // alert(i)
    return this.fb.group({
     'pkWhCostId': [''],
      'isApplicableToAllPartyInd': ['0'],
      'isApplicableToAllStockLocInd': ['0'],
      'legalEntity': [''],
      'fkProdId': [''],
      'costIsDetailedInd': [''],
      ['costIsDetailedInd' + i] : [''],
      'wareHouse': [''],
      'wareHouseLocation': [''],
      'whCostContrRefNo': [''],
      'whCostValidFrom': [],
      'whCostValidTo': [''],
      'whCostRemarks': [''],
      'whCostFreePeriodType': [''],
      'whCostNoOfFreeDays': [''],
      'whLocId': [''],
      'whCostDetailArray': this.fb.array([]),
      'WhArray': this.fb.array([])
    });
  }

  dropdownList() {
     // product list
     this.subscription = this.costMatrixService.getProductList().subscribe(data => {
        if (data.body !== null && data.body !== '') {
          this.costMatrixService.defaultUnitProductList = data.body;
        }
      }, error => { console.log(error); });
  }

  removeWarehouse(i: number) {
    if (this.wareHouseFormMain.value.wareHouseMatrix.length > 1 ) {
            const control = <FormArray>this.wareHouseFormMain.controls['wareHouseMatrix'];
            control.removeAt(i);
        if (this.costMatrixService.costMatrixModel.wareHouseCostDto[i] != null) {
           this.costMatrixService.costMatrixModel.wareHouseCostDto.splice(i, 1);
        }
     }
  }

  SaveWareDetails(list) {
   // this.wareHouseDetails= list;
  }

}
