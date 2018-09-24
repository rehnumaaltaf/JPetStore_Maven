import { Component, OnInit, ElementRef, Renderer2, OnDestroy, Input, OnChanges } from '@angular/core';
import { FormBuilder, FormGroup, FormControl, Validators, FormArray, ReactiveFormsModule, FormsModule } from '@angular/forms';
import { Subscription } from 'rxjs/Subscription';
import { Router, ActivatedRoute } from '@angular/router';
import { CnfCostMatrixMapping, ProductName, PartyName, CnfCostMatrix, MasterCnfCostDetail } from '../model/cnf-cost-model';
import { CnfCostMatrixService } from '../service/cnf-cost-matrix.service';
import { MasterSetupService } from '../../../master-setup/service/master-setup.service';
import { SelectItem } from '../../../../shared/interface/selectItem';
import { uom } from '../../../../shared/interface/router-links';
import { NavBarComponent } from '../../../../shared/nav-bar/nav-bar.component';
import { addCnfCostMatrix } from '../../../../shared/interface/router-links';
import { AccordionModule } from 'ngx-bootstrap';
import { SelectItemModel } from '../model/select-item.model';
import { TreeviewConfig, TreeviewItem } from 'ngx-treeview';
import { AddCnfCostMappingComponent } from './add-cnf-cost-mapping.component';
import { CostMatrixService } from '../../cost-matrix/service/cost-matrix.service';


@Component({
  selector: 'app-add-cnf-cost-matrix',
  templateUrl: './add-cnf-cost-matrix.component.html',
  styleUrls: ['./add-cnf-cost-matrix.component.css']
})

export class AddCnfCostMatrixComponent implements OnChanges, OnDestroy {
public status: any = {
  isFirstOpen: true,
  isFirstDisabled: false
};
// productOption: ProductName[];
// legalentityOption: PartyName[];

 // tslint:disable-next-line:no-input-rename
 @Input('group')
public cnfCostForm: FormGroup;
@Input() public cnfCostData: CnfCostMatrix;
@Input() public addEditId: number;
@Input() public arrIndex: number;

locationTypeItems: SelectItem[];
subscription: Subscription;
cnfModel: CnfCostMatrix= new CnfCostMatrix();
AddEditId: number;
private editId;
public errorModal = false;
public cnfMappingList: MasterCnfCostDetail [] = [];
public costIsDetailedInd: string ;

  public legalEntity: Array<{partyId: number, partyName: string}> = [ {partyId: 1, partyName: 'Oil'},
    {partyId: 2, partyName: 'Gas'}, {partyId: 3, partyName: 'Crud'}, {partyId: 4, partyName: 'waste '}];



public product: Array<{value: number, label: string}> = [{value: 1, label: 'Arabica'},
    {value: 2, label: 'Sample'}, {value: 3, label: 'data'}, {value: 4, label: 'test '}];

  public warehouse: Array<{value: number, label: string}> = [{value: 1, label: 'Trader'},
    {value: 2, label: 'Rosters'}, {value: 3, label: 'Provvalueer'}, {value: 4, label: 'Exchange'}];

public warehouseloc: Array<{value: number, label: string}> = [{value: 1, label: 'Singapore'},
    {value: 2, label: 'Malaysia'}, {value: 3, label: 'India'}, {value: 4, label: 'China '}];




  constructor(private fb: FormBuilder, public cnfCostMatrixService: CnfCostMatrixService, private masterSetupService: MasterSetupService,
     private router: Router, private _renderer: Renderer2, private _el: ElementRef,
      private route: ActivatedRoute, public costMatrixService: CostMatrixService) {
  }

  ngOnChanges() {
   //   this.addCNFMapping();
    // this.loadDropdowns();
  //    if (this.addEditId !== 0) {
          if (this.addEditId !== 0 && this.cnfCostData[this.arrIndex] !== undefined) {
        this.editListDetails();
      }else {
        this.addCNFMapping();
      }
    //  console.log(this.cnfCostForm.value);
  }

  loadDropdowns(): void {
    // this.getProduct();
    this.getLegalEntity();
    this.getUomList();
  //  this.getGeoMasterList();
  }

  getGeoMasterList() {
     this.subscription = this.cnfCostMatrixService.getGeoMasterList().subscribe(data => {
     this.cnfCostMatrixService.geoMaster = data.body;
     console.log(' cnfCostMatrixService.geoMaster  ' + this.cnfCostMatrixService.geoMaster);
    }, error => {throw error});
  }

  getUomList() {
     this.subscription = this.costMatrixService.getUomList().subscribe(data => {
      this.cnfCostMatrixService.defaultUnitUomList = data.body;
     // console.log(this.cnfCostMatrixData.legalentityOption);
    }, error => {throw error});
  }

   editListDetails() {
 //    alert(JSON.stringify(this.arrIndex + 1));
 //    console.log('editListDetails' + JSON.stringify(this.cnfCostData[this.arrIndex].costIsDetailedInd));
 //  console.log('cnf Cost editListDetails details   ' + JSON.stringify(this.cnfCostData) + '  ' + this.arrIndex);
    this.cnfCostForm.controls['fkPartyId'].setValue(this.cnfCostData[this.arrIndex].fkPartyId);
    this.cnfCostForm.controls['fkProdId'].setValue(this.cnfCostData[this.arrIndex].fkProdId);
    this.cnfCostForm.controls['fkWarehousePartyId'].setValue(this.cnfCostData.fkWarehousePartyId);
   // this.cnfCostForm.controls['costIsDetailedInd'].setValue(this.cnfCostData[this.arrIndex].costIsDetailedInd);
    this.cnfCostForm.controls['costIsDetailedInd' + this.arrIndex].setValue(this.cnfCostData[this.arrIndex].costIsDetailedIndValue);

  //  this.cnfCostForm.controls['costIsDetailedIndValue'].setValue(this.cnfCostData[this.arrIndex].costIsDetailedIndValue);
    this.cnfCostForm.controls['fkWarehousePartyId'].setValue(this.cnfCostData.fkWarehousePartyId);
    this.cnfCostForm.controls['fkPartyWhStockLocationId'].setValue(this.cnfCostData[this.arrIndex].fkPartyWhStockLocationId);
    this.cnfCostForm.controls['cnfCostContrRefNo'].setValue(this.cnfCostData[this.arrIndex].cnfCostContrRefNo);
    this.cnfCostForm.controls['cnfCostValidFrom'].setValue(new Date(this.cnfCostData[this.arrIndex].cnfCostValidFrom));
    this.cnfCostForm.controls['cnfCostValidTo'].setValue(new Date(this.cnfCostData[this.arrIndex].cnfCostValidTo));
    this.cnfCostForm.controls['cnfCostRemarks'].setValue(this.cnfCostData[this.arrIndex].cnfCostRemarks);

    this.costIsDetailedInd = this.cnfCostData[this.arrIndex].costIsDetailedIndValue;

   const sizerollemapping = Number( JSON.stringify(this.cnfCostData[this.arrIndex].masterCnfCostDetail.length));
    this.cnfMappingList = this.cnfCostData[this.arrIndex].masterCnfCostDetail;
    if (sizerollemapping >= 1 ) {
      for (let m = 0; m <  sizerollemapping ; m++) {
      this.addCNFMapping();
      }
  }
   // console.log(' costIsDetailedIndvalue > ' + this.costIsDetailedInd +
 //   ' Backend >>>>>>' + this.cnfCostData[this.arrIndex].costIsDetailedInd);
  }


  getCnfCostMatrixMappingList(cnfCostForm) {
    return cnfCostForm.get('masterCnfCostDetail').controls;
  }

  addCNFMapping() {
    const control = <FormArray>this.cnfCostForm.controls['masterCnfCostDetail'];
     const addrCtrl = this.initCNFMapping();
    control.push(addrCtrl);
   // console.log('addrCtrl -> ' + control);
  }

  addCNFMappingList() {
     this.addCNFMapping();
  }

  initCNFMapping() {
    return this.fb.group({
      fkFromContractTermsId: [],
      fkToContractTermsId: [],
      fkToCountryId: [],
      fkToLocationId: [],
      fkRateBasisRefId: [],
      fkRateUomId: [],
      rate: [],
      fkRateCurrencyId: [],
      transitDays: []
    });
  }

  removeCNFmat(k: number) {
    if (this.cnfCostForm.value.masterCnfCostDetail.length > 1 ) {
            const control = <FormArray>this.cnfCostForm.controls['masterCnfCostDetail'];
            control.removeAt(k);
        if (this.costMatrixService.cnfMatrixDataList[this.arrIndex] !== undefined) {
            if (this.costMatrixService.cnfMatrixDataList[this.arrIndex].masterCnfCostDetail[k] != null) {
              this.costMatrixService.cnfMatrixDataList[this.arrIndex].masterCnfCostDetail.splice(k, 1);
            }
        }
     }
  }


  getProduct() {
   this.subscription = this.cnfCostMatrixService.getProduct().subscribe(data => {
     this.cnfCostMatrixService.productOption = data.body;
    // console.log(this.cnfCostMatrixData.legalentityOption);
    }, error => {});

  }

  getLegalEntity() {
     this.subscription = this.cnfCostMatrixService.getPartyDetails().subscribe(data => {
      this.cnfCostMatrixService.legalentityOption = data.body;
//      console.log(this.cnfCostMatrixService.legalentityOption);
    }, error => {});
  }


save() {
/*   console.log(this.cnfCostForm.value);

   console.log(JSON.stringify(this.cnfCostForm.value)); */
  }
   clear() {
   // this.uomData = new CnfCostMatrix();
  }





  ngOnDestroy() {
    // Called once, before the instance is destroyed.
    // this.subscription.unsubscribe();
  }

}
