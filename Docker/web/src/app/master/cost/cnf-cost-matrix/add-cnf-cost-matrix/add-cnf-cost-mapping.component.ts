import { Component, OnInit, Output, EventEmitter, Input , OnChanges} from '@angular/core';
import { FormGroup } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { Subscription } from 'rxjs/Subscription';
import { CnfCostMatrixService } from '../service/cnf-cost-matrix.service';
import { CnfCostMatrix, MasterCnfCostDetail } from '../model/cnf-cost-model';

@Component({
  moduleId: module.id,
  selector: 'app-add-cnf-cost-mapping',
  templateUrl: 'add-cnf-cost-mapping.component.html',
})
export class AddCnfCostMappingComponent implements OnChanges {

 // tslint:disable-next-line:no-input-rename
 @Input('group') public cnfMappingForm: FormGroup;
// @Input() extPackingModel: ExternalPacking;
/*  @Input() ArrayIndex: number;
 @Input() AddEditId: number;
 @Input()  cnfCostMatrixData: CnfCostMatrix; */

  @Input() public cnfMappingList: MasterCnfCostDetail;
  @Input() public addEditId: number;
  @Input() public cnfarrayIndex: number;

public fromIncoterm: Array<{value: number, label: string}> = [
    {value: 0, label: 'USA'},
    {value: 1, label: 'Singapore'},
    {value: 2, label: 'China'},
    {value: 3, label: 'India '},
];

public toIncoterm: Array<{value: number, label: string}> = [
    {value: 1, label: 'USA'},
    {value: 2, label: 'Singapore'},
    {value: 3, label: 'China'},
    {value: 4, label: 'India '},
];

constructor(public cnfCostMatrixService: CnfCostMatrixService) {

}

  ngOnChanges(): void {
// console.log('cnf Cost Mapping details   ' + JSON.stringify(this.cnfMappingList) + '  ' + this.cnfarrayIndex);
// console.log('cnf Cost Mapping details   ' + this.cnfMappingList + '  ' + this.cnfarrayIndex);
        if (this.addEditId !== 0) {
            if (this.cnfMappingList[this.cnfarrayIndex] !== undefined) {
                this.cnfMappingForm.controls['fkFromContractTermsId']
                    .setValue(this.cnfMappingList[this.cnfarrayIndex].fkFromContractTermsId);
                        this.cnfMappingForm.controls['fkToContractTermsId']
                    .setValue(this.cnfMappingList[this.cnfarrayIndex].fkToContractTermsId);
                        this.cnfMappingForm.controls['fkToCountryId']
                    .setValue(this.cnfMappingList[this.cnfarrayIndex].fkToCountryId);
                        this.cnfMappingForm.controls['fkToLocationId']
                    .setValue(this.cnfMappingList[this.cnfarrayIndex].fkToLocationId);
                        this.cnfMappingForm.controls['fkRateBasisRefId']
                    .setValue(this.cnfMappingList[this.cnfarrayIndex].fkRateBasisRefId);
                        this.cnfMappingForm.controls['fkRateUomId']
                    .setValue(this.cnfMappingList[this.cnfarrayIndex].fkRateUomId);

                            this.cnfMappingForm.controls['rate']
                    .setValue(this.cnfMappingList[this.cnfarrayIndex].rate);
                            this.cnfMappingForm.controls['fkRateCurrencyId']
                    .setValue(this.cnfMappingList[this.cnfarrayIndex].fkRateCurrencyId);
                            this.cnfMappingForm.controls['transitDays']
                    .setValue(this.cnfMappingList[this.cnfarrayIndex].transitDays);
            }
        }
  }


}
