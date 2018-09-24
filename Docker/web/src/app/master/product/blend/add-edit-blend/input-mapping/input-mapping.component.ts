import { Component, OnChanges , Input } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { SharedModule } from '../../../../../shared/shared.module';
import { BrowserModule } from '@angular/platform-browser';
import { Subscription } from 'rxjs/Subscription';
import { BlendService } from '../../service/blend.service';
import { BlendMatrix , BlendOutputArray , BlendInputArray ,
   BlendCertificationArray , ProductMaster , OriginMaster , GradeMaster , CertificationMaster} from '../../model/blend-model';

@Component({
  selector: 'app-input-mapping',
  templateUrl: './input-mapping.component.html',
  styleUrls: ['./input-mapping.component.css']
})
export class InputMappingComponent implements OnChanges {
  // tslint:disable-next-line:no-input-rename
  @Input('group')
  public InputMappingForm: FormGroup;
  @Input() arrInIndex: number;
  @Input() inMapList: BlendInputArray;
  @Input() addEditId: number;
  constructor(public blendService: BlendService) { }

  ngOnChanges() {
    if (this.addEditId !== 0) {
            if (this.inMapList[this.arrInIndex] !== undefined) {
                this.InputMappingForm.controls['pkBlendInputId']
                    .setValue(this.inMapList[this.arrInIndex].pkBlendInputId);
                this.InputMappingForm.controls['fkProdId']
                    .setValue(this.inMapList[this.arrInIndex].fkProdId);
                this.InputMappingForm.controls['fkOriginId']
                    .setValue(this.inMapList[this.arrInIndex].fkOriginId);
                this.InputMappingForm.controls['fkGradeId']
                    .setValue(this.inMapList[this.arrInIndex].fkGradeId);
                    this.InputMappingForm.controls['quantityPercentage']
                    .setValue(this.inMapList[this.arrInIndex].quantityPercentage);
                    if (this.inMapList[this.arrInIndex].fkProdCertId !== null) {
                        this.InputMappingForm.controls['fkProdCertId']
                       .setValue(this.inMapList[this.arrInIndex].fkProdCertId);
                    }
            }
        }
  }

}
