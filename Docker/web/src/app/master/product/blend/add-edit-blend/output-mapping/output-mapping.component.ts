import { Component, OnChanges , Input } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { SharedModule } from '../../../../../shared/shared.module';
import { BrowserModule } from '@angular/platform-browser';
import { Subscription } from 'rxjs/Subscription';
import { BlendService } from '../../service/blend.service';
import { BlendMatrix , BlendOutputArray , BlendInputArray ,
   BlendCertificationArray , ProductMaster , OriginMaster , GradeMaster , CertificationMaster} from '../../model/blend-model';


@Component({
  selector: 'app-output-mapping',
  templateUrl: './output-mapping.component.html',
  styleUrls: ['./output-mapping.component.css']
})
export class OutputMappingComponent implements OnChanges {
  // tslint:disable-next-line:no-input-rename
  @Input('group')
  public OutputMappingForm: FormGroup;
  @Input() arrOutIndex: number;
  @Input() outMapList: BlendOutputArray;
  @Input() addEditId: number;
  subscription: Subscription;
  constructor(public blendService: BlendService) { }

  ngOnChanges() {
    if (this.addEditId !== 0) {
            if (this.outMapList[this.arrOutIndex] !== undefined) {
                this.OutputMappingForm.controls['pkBlendOutputId']
                    .setValue(this.outMapList[this.arrOutIndex].pkBlendOutputId);
                this.OutputMappingForm.controls['fkProdId']
                    .setValue(this.outMapList[this.arrOutIndex].fkProdId);
                this.OutputMappingForm.controls['fkOriginId']
                    .setValue(this.outMapList[this.arrOutIndex].fkOriginId);
                this.OutputMappingForm.controls['fkGradeId']
                    .setValue(this.outMapList[this.arrOutIndex].fkGradeId);
                    this.OutputMappingForm.controls['quantityPercentage']
                    .setValue(this.outMapList[this.arrOutIndex].quantityPercentage);
                    this.OutputMappingForm.controls['abilityToBearRatio']
                    .setValue(this.outMapList[this.arrOutIndex].abilityToBearRatio);
                this.OutputMappingForm.controls['fkProdCertId']
                    .setValue(this.outMapList[this.arrOutIndex].fkProdCertId);
            }
        }
  }

  getOtherDropdowns(id) {
      // alert(id);
     /* this.blendService.blendGradeList = [];
      this.subscription = this.blendService.getGradeList().subscribe(data => {
        if (data.body !== null && data.body !== '') {
          for (let g = 0; g < data.body.length ; g++) {
            console.log(data.body[g].product.prodId);
            if (data.body[g].product.prodId == id) {
                this.blendService.blendGradeList.push(data.body[g]);
            }
          }
        }
      }, error => { throw error; });*/
  }

}
