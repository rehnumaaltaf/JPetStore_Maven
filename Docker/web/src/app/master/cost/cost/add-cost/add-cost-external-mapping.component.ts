import { Component, OnInit, Output, EventEmitter, Input, OnChanges } from '@angular/core';
import { CostMaster , ExternalSystem } from '../model/add-cost-model';
import { FormGroup } from '@angular/forms';
import { CostService } from '../service/cost.service';

@Component({
    moduleId: module.id,
    selector: 'app-add-cost-external-mapping',
    templateUrl: 'add-cost-external-mapping.component.html',
})

export class AddCostExternalMappingComponent implements OnChanges {

    @Input() public extSystemMappingForm: FormGroup;
    @Input() public mappingList: ExternalSystem;
    @Input() public addEditId: number;
    @Input() public arrayIndex: number;

    constructor(public costService: CostService) { }

    ngOnChanges(): void {
        if (this.addEditId !== 0) {
            // + '  '  + this.costMaster.exterPackAssoc[this.arrayIndex]
            if (this.mappingList[this.arrayIndex] !== undefined) {
                this.extSystemMappingForm.controls['fkCostId']
                    .setValue(this.mappingList[this.arrayIndex].fkCostId);
                this.extSystemMappingForm.controls['fkExternalSystemRefId']
                    .setValue(this.mappingList[this.arrayIndex].fkExternalSystemRefId);
                this.extSystemMappingForm.controls['mappingType']
                    .setValue(this.mappingList[this.arrayIndex].mappingType);
                this.extSystemMappingForm.controls['externalCode']
                    .setValue(this.mappingList[this.arrayIndex].externalCode);
                this.extSystemMappingForm.controls['pkCostExternalMappingId']
                    .setValue(this.mappingList[this.arrayIndex].pkCostExternalMappingId);
            }
        }
    }
}
