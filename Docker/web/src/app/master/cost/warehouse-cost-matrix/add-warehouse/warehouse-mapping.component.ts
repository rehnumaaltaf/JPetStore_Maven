import { Component, OnInit, Output, EventEmitter, Input , OnChanges} from '@angular/core';
import { FormGroup } from '@angular/forms';
import { WarehouseCostMatrixService } from '../service/warehouse-cost-matrix.service';
import { WareHouseMatrix , WarehouseCostArray} from '../model/cost-warehouse';
import { SharedModule } from '../../../../shared/shared.module';
import { BrowserModule } from '@angular/platform-browser';
import { Subscription } from 'rxjs/Subscription';
import { CostMatrixService } from '../../cost-matrix/service/cost-matrix.service';
// tslint:disable-next-line:max-line-length
import { DefaultUnit , SelectMatrix, CostMaster , ExternalSystem , PartyMaster, UomMaster, PrimaryPackingMaster, SecondaryPackingMaster , CurrencyMaster} from '../../cost/model/add-cost-model';
@Component({
  moduleId: module.id,
  selector: 'app-warehouse-mapping',
  templateUrl: 'warehouse-mapping.component.html',
})
export class WareHouseMappingComponent implements OnChanges {
    // tslint:disable-next-line:no-input-rename
  @Input('group')
  public whMappingForm: FormGroup;
  @Input() public whMappingList: WarehouseCostArray;
  @Input() public addEditId: number;
  @Input() public wharrayIndex: number;
  public defaultUnit: typeof DefaultUnit = DefaultUnit;
   constructor(public costMatrixService: CostMatrixService) { }
    ngOnChanges(): void {
        if (this.addEditId !== 0) {
            if (this.whMappingList[this.wharrayIndex] !== undefined) {
                // alert(this.whMappingList[this.wharrayIndex].whfkDefaultValueUomId);
                this.whMappingForm.controls['pkWhDetailId']
                    .setValue(this.whMappingList[this.wharrayIndex].pkWhDetailId);
                this.whMappingForm.controls['costName']
                    .setValue(this.whMappingList[this.wharrayIndex].costName);
                this.whMappingForm.controls['rateBasis']
                    .setValue(this.whMappingList[this.wharrayIndex].rateBasis);
                this.whMappingForm.controls['whfkDefaultValueUomId']
                    .setValue(this.whMappingList[this.wharrayIndex].whfkDefaultValueUomId);
                    this.whMappingForm.controls['whfkDefValPrimaryPackingId']
                    .setValue(this.whMappingList[this.wharrayIndex].whfkDefValPrimaryPackingId);
                    this.whMappingForm.controls['whfkDefValSecondaryPackingId']
                    .setValue(this.whMappingList[this.wharrayIndex].whfkDefValSecondaryPackingId);
                this.whMappingForm.controls['rateCurrency']
                    .setValue(this.whMappingList[this.wharrayIndex].rateCurrency);
                this.whMappingForm.controls['rate']
                     .setValue(this.whMappingList[this.wharrayIndex].rate);
                this.whMappingForm.controls['timeBasis']
                      .setValue(this.whMappingList[this.wharrayIndex].timeBasis);
            }
        }
    }
}
