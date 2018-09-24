import { Component, Input, NgModule ,  OnChanges} from '@angular/core';
import { FormGroup , Validators} from '@angular/forms';
import { SharedModule } from './../../../../shared/shared.module';
import { BrowserModule } from '@angular/platform-browser';
import { Subscription } from 'rxjs/Subscription';
import { ProductGradeService } from '../service/product-grade.service';
import { Grade } from '../grade-model';
@Component({
    moduleId: module.id,
    selector: 'app-grade-external-system-mapping',
    templateUrl: 'grade-external-system-mapping.component.html',
    styleUrls: ['./add-product-grade.component.css'],
})
export class GradeExternalMappingComponent  implements OnChanges {

// tslint:disable-next-line:no-input-rename
    @Input('group')
    public gradeExternalSystemMappingForm: FormGroup;
    @Input() title: any;
    @Input() productGrade: Grade = new Grade();
    gradeextsysData: string;
    gradeattr1Data: string;
    gradeattr2Data: string;
    gradeMappingId: Number ;
    constructor( public productGradeService: ProductGradeService) {
     }
    ngOnChanges() {
        if (this.productGrade != null ) {
            if (this.productGrade.externalSystemList != null) {
                if (this.productGrade.externalSystemList[this.title] != null ) {

                      if (this.productGrade.externalSystemList[this.title].externalSystemId != null ) {
                        this.gradeextsysData = this.productGrade.externalSystemList [this.title].externalSystemId.toString();
                      }
                       if (this.productGrade.externalSystemList[this.title].attribute1 != null ) {
                       this.gradeattr1Data = this.productGrade.externalSystemList [this.title].attribute1;
                      }
                       if (this.productGrade.externalSystemList[this.title].attribute2 != null ) {
                       this.gradeattr2Data  = this.productGrade.externalSystemList [this.title].attribute2;
                      }
                    if ( this.productGrade.externalSystemList[this.title].externalSystemMappingId != null) {
                         this.gradeMappingId = this.productGrade.externalSystemList[this.title].externalSystemMappingId;
                    }
                 }
            }
       }
        

     }
}