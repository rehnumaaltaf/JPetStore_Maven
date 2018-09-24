import { Component, OnChanges , Input } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { SharedModule } from '../../../../../shared/shared.module';
import { BrowserModule } from '@angular/platform-browser';
import { Subscription } from 'rxjs/Subscription';
import { BlendService } from '../../service/blend.service';
import { BlendMatrix , BlendOutputArray , BlendInputArray ,
   BlendCertificationArray , ProductMaster , OriginMaster , GradeMaster , CertificationMaster} from '../../model/blend-model';

@Component({
  selector: 'app-certification-mapping',
  templateUrl: './certification-mapping.component.html',
  styleUrls: ['./certification-mapping.component.css']
})
export class CertificationMappingComponent implements OnChanges {
  // tslint:disable-next-line:no-input-rename
  @Input('group')
  public CertificationMappingForm: FormGroup;
  @Input() arrCerIndex: number;
  @Input() cerMapList: BlendCertificationArray;
  @Input() addEditId: number;
  constructor(public blendService: BlendService) { }

  ngOnChanges() {
       if (this.addEditId !== 0) {
            if (this.cerMapList[this.arrCerIndex] !== undefined) {
                this.CertificationMappingForm.controls['pkBlendInputCertificationId']
                    .setValue(this.cerMapList[this.arrCerIndex].pkBlendInputCertificationId);
                if (this.cerMapList[this.arrCerIndex].fkProdCertId !== null) {
                    this.CertificationMappingForm.controls['fkProdCertId']
                    .setValue(this.cerMapList[this.arrCerIndex].fkProdCertId);
                }
                if (this.cerMapList[this.arrCerIndex].certificationPercentage !== null) {
                    this.CertificationMappingForm.controls['certificationPercentage']
                    .setValue(this.cerMapList[this.arrCerIndex].certificationPercentage);
                }
            }
        }
  }

}
