import { Component, OnInit, Output, Input } from '@angular/core';
import { FormBuilder, FormGroup, FormControl, Validators, FormArray, ReactiveFormsModule, FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { Subscription } from 'rxjs/Subscription';
// import { AddWarehouseComponent } from './add-warehouse/add-warehouse.component';
import { FreightMatrix } from './model/freight-cost.model';
import { CostMatrixService } from '../cost-matrix/service/cost-matrix.service';
import { FreightMain } from './model/freight-cost.model'

@Component({
  moduleId: module.id,
  selector: 'app-view-freight-mapping',
  templateUrl: 'view-freight.component.html',
})
export class ViewFreightComponent implements OnInit {
// public wareArray: WareHouseMatrix[] = [];
public freightFormMain: FormGroup;
private subscription: Subscription;
 FreightCostMatrixModel: FreightMain = new FreightMain();
@Input() public freightData: FreightMain;
@Input() public freightDataLength: number;
@Input() public addEditId: number;
constructor(private fb: FormBuilder , private costMatrixService: CostMatrixService) { }
  ngOnInit() {
    this.buildWare();
   // this.addFreightCost();
    this.dropdownList();
    this.freightFormMain.valueChanges.subscribe(data => {
    //  data.freightCostMatrixDto.fkPartyId = 1;
     this.costMatrixService.costMatrixModel.freightCostMatrixDto = data.freightCostMatrixDto;
     for ( let i = 0; i < this.costMatrixService.costMatrixModel.freightCostMatrixDto.length; i++) {
        this.costMatrixService.costMatrixModel.freightCostMatrixDto[i].freightCostIsDetailedInd =
         this.costMatrixService.costMatrixModel.freightCostMatrixDto[i]['freightCostIsDetailedInd' + i];
      }
    })
 if (this.addEditId !== 0) {
       const size = Number( JSON.stringify(this.freightDataLength));
        if (size > 0 ) {
          for (let i = 0; i < size; i++) {
            console.log('i value   ' + i);
            this.addFreightCost(i - 1);
          }
        }
    }else {
      this.addFreightCost(-1);
    }
  }

  dropdownList() {
     // product list
     this.subscription = this.costMatrixService.getProductList().subscribe(data => {
        if (data.body !== null && data.body !== '') {
          this.costMatrixService.defaultUnitProductList = data.body;
        }
      }, error => { console.log(error); });
  }

  buildWare(): void {
    this.freightFormMain = this.fb.group({
      'freightCostMatrixDto': this.fb.array([])
    });
  }

  // task related functions
  getfreightList(freightFormMain) {
    return freightFormMain.get('freightCostMatrixDto').controls;
  }
  addFreightCost(i) {
    const control = <FormArray>this.freightFormMain.controls['freightCostMatrixDto'];
    const addrCtrl = this.initfreightCostMain(i + 1);
    control.push(addrCtrl);
  }

  initfreightCostMain(i) {
    return this.fb.group({
      'pkFreightCostId': [''],
      'freightCostIsDetailedInd': [''],
      ['freightCostIsDetailedInd' + i ]: [''],
      'freightCostValidFrom': [''],
      'freightCostValidTo': [''],
      'freightCostContrRefNo': [''],
      'fkPartyId': [''],
      'fkProdId': [''],
      'fkServiceProviderPartyId': [''],
      'freightCostRemarks': [''],
      'masterFreightCostdetailDto': this.fb.array([]),
    //  'freightArray': this.fb.array([])
    });
  }



  removeFreightCost(i: number) {
   if (this.freightFormMain.value.freightCostMatrixDto.length > 1 ) {
            const control = <FormArray>this.freightFormMain.controls['freightCostMatrixDto'];
            control.removeAt(i);
       if (this.FreightCostMatrixModel.freightCostMatrixDto != null) {
          this.FreightCostMatrixModel.freightCostMatrixDto.splice(i, 1);
        }
     }
  }



  SaveWareDetails(list) {
   // this.wareHouseDetails= list;
    console.log(list);
  }

}
