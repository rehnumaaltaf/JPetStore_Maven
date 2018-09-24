import { Component, OnInit, Output, Input } from '@angular/core';
import { FormBuilder, FormGroup, FormControl, Validators, FormArray, ReactiveFormsModule, FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { Subscription } from 'rxjs/Subscription';
import { AddCnfCostMatrixComponent } from './add-cnf-cost-matrix/add-cnf-cost-matrix.component';
import { CnfCostMatrixMapping, ProductName, PartyName, CnfCostMatrix , CnfCostMatrixMain } from './model/cnf-cost-model';
import { CostMatrixService } from '../cost-matrix/service/cost-matrix.service';
import { WareHouseMatrix , WareHouseMatrixMain } from '../warehouse-cost-matrix/model/cost-warehouse';

@Component({
  moduleId: module.id,
  selector: 'app-view-cnf-mapping',
  templateUrl: 'view-cnf.component.html',
})

export class ViewCnfComponent implements OnInit {
public wareArray: CnfCostMatrix[] = [];
public CnfHouseFormMain: FormGroup;
private subscription: Subscription;
CnfHouseFormMainModel: CnfCostMatrixMain = new CnfCostMatrixMain();
@Input() public cnfCostData: CnfCostMatrixMain;
@Input() public cnfCostLength: number;
@Input() public addEditId: number;

constructor(private fb: FormBuilder, public costMatrixService: CostMatrixService) { }


  ngOnInit() {
    this.buildCnf();
  //  this.addCnf();
    this.CnfHouseFormMain.valueChanges.subscribe(data => {
      this.costMatrixService.costMatrixModel.cnfCostDto = data.cnfCostMatrix;
      for ( let i = 0; i < this.costMatrixService.costMatrixModel.cnfCostDto.length; i++) {
        this.costMatrixService.costMatrixModel.cnfCostDto[i].costIsDetailedInd =
         this.costMatrixService.costMatrixModel.cnfCostDto[i]['costIsDetailedInd' + i];
      }
      // this.output = data
    })

  //  console.log('cnf Cost details   ' + JSON.stringify(this.cnfCostData) + '  ' + this.cnfCostLength);
    if (this.addEditId !== 0) {
       const size = Number( JSON.stringify(this.cnfCostLength));
        if (size > 0 ) {
          for (let i = 0; i < size; i++) {
          //  this.addWarehouse();
      //    alert('edit new' + i);
          this.addCnf(i - 1);
          }
        }
    }else {
 //   this.addWarehouse();
 //       alert('edit new else');
        this.addCnf(-1);
    }
  }

  removeCnfCost(i: number) {
    if (this.CnfHouseFormMain.value.cnfCostMatrix.length > 1 ) {
            const control = <FormArray>this.CnfHouseFormMain.controls['cnfCostMatrix'];
            control.removeAt(i);
        if (this.costMatrixService.costMatrixModel.cnfCostDto[i] != null) {
           this.costMatrixService.costMatrixModel.cnfCostDto.splice(i, 1);
        }
     }
  }

  buildCnf(): void {
    this.CnfHouseFormMain = this.fb.group({
      'cnfCostMatrix': this.fb.array([])
    });
  }




  // task related functions
  getWarehouseList(CnfHouseFormMain) {
    return CnfHouseFormMain.get('cnfCostMatrix').controls;
  }

  addCnf(i) {
    const control = <FormArray>this.CnfHouseFormMain.controls['cnfCostMatrix'];
    const addrCtrl = this.initcnfCostMain(i + 1);
    control.push(addrCtrl);
  }

  initcnfCostMain(i) {
  //  alert(i);
       return this.fb.group({
        'fkPartyId': [''],
        'fkProdId': [''],
        'isApplicableToAllPartyInd': ['0'],
        'isApplicableToAllStockLocInd': ['0'],
      //  'costIsDetailedInd': [''],
        ['costIsDetailedInd' + i]: [''],
     //   'costIsDetailedIndValue': [''],
        'fkWarehousePartyId': [''],
        'fkPartyWhStockLocationId': [''],
        'cnfCostContrRefNo': [''],
        'cnfCostValidFrom': [''],
        'cnfCostValidTo': [''],
        'cnfCostRemarks': [''],
        'statusName': [''],
        'masterCnfCostDetail': this.fb.array([])
    });
  }

  removeCnf(i: number) {
     if (this.CnfHouseFormMain.value.cnfCostMatrix.length > 1 ) {
            const control = <FormArray>this.CnfHouseFormMain.controls['cnfCostMatrix'];
            control.removeAt(i);
        if (this.CnfHouseFormMainModel.cnfCostMatrix != null) {
           this.CnfHouseFormMainModel.cnfCostMatrix.splice(i, 1);
        }
     }
  }

  SaveCnfDetails(list) {
   // this.wareHouseDetails= list;
    console.log(list);
  }

}
