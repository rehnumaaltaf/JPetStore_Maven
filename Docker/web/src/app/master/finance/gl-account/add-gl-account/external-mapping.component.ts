import { Component, Input, NgModule , OnChanges} from '@angular/core';
import { FormGroup ,  FormControl,  Validators, FormArray,   FormBuilder, ReactiveFormsModule , FormsModule} from '@angular/forms';
import { GlAccountService } from '../service/gl-account.service';
import { GLAccount } from '../gl-account';
import { ExternalGLMappingDto } from '../gl-externalmapping';
import { SharedModule } from '../../../../shared/shared.module';
import { BrowserModule } from '@angular/platform-browser';
import { Subscription } from 'rxjs/Subscription';
import { Router } from '@angular/router';


@Component({
  moduleId: module.id,
  selector: 'app-external-mapping',
  templateUrl: './external-mapping.component.html',
  styleUrls: ['./add-gl-account.component.css']
})
export class ExternalMappingComponent implements OnChanges {
    @Input('group')
    public externalMappingForm: FormGroup;
    @Input() title: any;
    @Input() glData: GLAccount = new GLAccount();
   // @Input() externalGLMappingDto: ExternalGLMappingDto[] = Array(new ExternalGLMappingDto());
     externalSystemRefId: number;
    mappingType: string;
    externalGlMappingCode: string
    externalMappingId: number;
    constructor(public glAccountService: GlAccountService) { }

  ngOnChanges() {
    if ( this.glData != null) {
         if ( this.glData.externalGLMappingDto != null) {
              if ( this.glData.externalGLMappingDto[this.title]) {

                    if (this.glData.externalGLMappingDto[this.title]
                        .externalSystemRefId != null) {
                          this.externalSystemRefId  = this.glData.externalGLMappingDto[this.title]
                        .externalSystemRefId;
                        console.log('in mapping id ' + this.externalSystemRefId )
                    }
                    if (this.glData.externalGLMappingDto[this.title].externalGlMappingCode != null) {

                           this.externalGlMappingCode  = this.glData.externalGLMappingDto[this.title].externalGlMappingCode;
                    }
                    if (this.glData.externalGLMappingDto[this.title].mappingType != null) {
                          this.mappingType = this.glData.externalGLMappingDto[this.title].mappingType;
                    }

}
}
    }
  }

}
