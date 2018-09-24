import { Component, Input, NgModule ,  OnChanges} from '@angular/core';
import { FormGroup , Validators} from '@angular/forms';
import { SharedModule } from './../../../../shared/shared.module';
import { BrowserModule } from '@angular/platform-browser';
import { Subscription } from 'rxjs/Subscription';
import { ProductGradeService } from '../service/product-grade.service';
import { Grade } from '../grade-model';
@Component({
    moduleId: module.id,
    selector: 'app-grade-international-mapping',
    templateUrl: 'grade-international-code-mapping.component.html',
    styleUrls: ['./add-product-grade.component.css'],
})
export class GradeInternationalMappingComponent  implements OnChanges {

// tslint:disable-next-line:no-input-rename
    @Input('group')
    public gradeIntalMappingForm: FormGroup;
    @Input() title: any;
   @Input() productGrade: Grade = new Grade();
    gradeIntlData: string;
    gradeIntlcodeData: string;
    gradeIntlnameData: string;
    gradeMappingId: Number ;
    constructor( public productGradeService: ProductGradeService) {
     }


    ngOnChanges() {
        if (this.productGrade != null ) {
            if (this.productGrade.intlGradeList != null) {
                if (this.productGrade.intlGradeList[this.title] != null ) {

                      if (this.productGrade.intlGradeList[this.title].intlId != null ) {
                        this.gradeIntlData = this.productGrade.intlGradeList [this.title].intlId.toString();
                      }
                       if (this.productGrade.intlGradeList[this.title].intlCode != null ) {
                       this.gradeIntlcodeData = this.productGrade.intlGradeList [this.title].intlCode;
                      }
                       if (this.productGrade.intlGradeList[this.title].intlDesc != null ) {
                       this.gradeIntlnameData  = this.productGrade.intlGradeList [this.title].intlDesc;
                      }
                    if ( this.productGrade.intlGradeList[this.title].intlGradeMappingId != null) {
                         this.gradeMappingId = this.productGrade.intlGradeList[this.title].intlGradeMappingId;
                    }
                 }
            }
       }

     }
}