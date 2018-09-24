import { Component, OnInit, Output, EventEmitter, Input , OnChanges} from '@angular/core';
import { FormGroup } from '@angular/forms';
import { SharedModule } from '../../../../shared/shared.module';
import { BrowserModule } from '@angular/platform-browser';
import { Subscription } from 'rxjs/Subscription';
import { FreightMatrix, FreightDetailDto } from '../model/freight-cost.model';
import { CostMatrixService } from '../../cost-matrix/service/cost-matrix.service';
// tslint:disable-next-line:max-line-length
import { DefaultUnit , SelectMatrix, CostMaster , ExternalSystem , PartyMaster, UomMaster, PrimaryPackingMaster, SecondaryPackingMaster } from '../../cost/model/add-cost-model';

@Component({
  moduleId: module.id,
  selector: 'app-freight-mapping',
  templateUrl: 'freight-mapping.component.html',
})
export class FreightMappingComponent implements OnChanges {
    // tslint:disable-next-line:no-input-rename
  @Input('group')
  public freightMappingForm: FormGroup;
 @Input() public freightDtlList: FreightDetailDto;
  @Input() public addEditId: number;
  @Input() public freightarrayIndex: number;
  public defaultUnit: typeof DefaultUnit = DefaultUnit;
  private subscription: Subscription;

   constructor(public costMatrixService: CostMatrixService) { }
    ngOnChanges(): void {
     //   this.getGeoList();
      //  this.getLocationList()
        if (this.addEditId !== 0) {
            if (this.freightDtlList[this.freightarrayIndex] !== undefined) {
                this.freightMappingForm.controls['fkSourceGeoId']
                    .setValue(this.freightDtlList[this.freightarrayIndex].fkSourceGeoId);
                this.freightMappingForm.controls['fkSourceLocationId']
                    .setValue(this.freightDtlList[this.freightarrayIndex].fkSourceLocationId);
                this.freightMappingForm.controls['fkDestinationGeoId']
                    .setValue(this.freightDtlList[this.freightarrayIndex].fkDestinationGeoId);
                    this.freightMappingForm.controls['fkDestinationLocationId']
                    .setValue(this.freightDtlList[this.freightarrayIndex].fkDestinationLocationId);
                    this.freightMappingForm.controls['fkRateCurrencyId']
                    .setValue(this.freightDtlList[this.freightarrayIndex].fkRateCurrencyId);
                this.freightMappingForm.controls['fkRateBasisRefId']
                    .setValue(this.freightDtlList[this.freightarrayIndex].fkRateBasisRefId);
                this.freightMappingForm.controls['fkRateUomId']
                     .setValue(this.freightDtlList[this.freightarrayIndex].fkRateUomId);
                this.freightMappingForm.controls['rate']
                      .setValue(this.freightDtlList[this.freightarrayIndex].rate);
                this.freightMappingForm.controls['transitDays']
                      .setValue(this.freightDtlList[this.freightarrayIndex].transitDays);
                this.freightMappingForm.controls['freeDaysAtLoadPort']
                      .setValue(this.freightDtlList[this.freightarrayIndex].freeDaysAtLoadPort);
                this.freightMappingForm.controls['freeDaysAtDestiantionPort']
                      .setValue(this.freightDtlList[this.freightarrayIndex].freeDaysAtDestiantionPort);
                this.freightMappingForm.controls['pkFreightCostDetailId']
                      .setValue(this.freightDtlList[this.freightarrayIndex].pkFreightCostDetailId);
                this.freightMappingForm.controls['fkRatePrimaryPackingId']
                      .setValue(this.freightDtlList[this.freightarrayIndex].fkRatePrimaryPackingId);
                this.freightMappingForm.controls['fkRateSecondaryPacking']
                      .setValue(this.freightDtlList[this.freightarrayIndex].fkRateSecondaryPacking);
                this.freightMappingForm.controls['fkRateUomId']
                      .setValue(this.freightDtlList[this.freightarrayIndex].fkRateUomId);
            }
        }
    }


}
