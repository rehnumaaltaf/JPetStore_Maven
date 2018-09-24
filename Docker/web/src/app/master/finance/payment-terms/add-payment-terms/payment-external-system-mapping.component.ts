import { Component, Input, NgModule ,  OnChanges} from '@angular/core';
import { FormGroup , Validators} from '@angular/forms';
import { PaymentTermsService } from './../service/payment-terms.service';
import { SharedModule } from './../../../../shared/shared.module';
import { BrowserModule } from '@angular/platform-browser';
import { Subscription } from 'rxjs/Subscription';
import { PaymentTerm } from './../payment-terms-model';

@Component({
    moduleId: module.id,
    selector: 'app-external-system-mapping',
    templateUrl: 'payment-external-system-mapping.component.html',
    styleUrls: ['./add-payment-terms.component.css'],
})
export class PaymentExternalMappingComponent  implements OnChanges {

// tslint:disable-next-line:no-input-rename
    @Input('group')
    public externalSystemMappingForm: FormGroup;
    @Input() title: any;
    @Input() paymentTerm: PaymentTerm = new PaymentTerm();
    paymentextsysData: string;
    paymentattr1Data: string;
    paymentattr2Data: string;
    paymentMappingId: Number = 0;
    constructor(public paymentTermsService: PaymentTermsService ) {
     }
    ngOnChanges() {
        if (this.paymentTerm != null ) {
            if (this.paymentTerm.externalMappingCollection != null) {
                if (this.paymentTerm.externalMappingCollection[this.title] != null ) {

                      if (this.paymentTerm.externalMappingCollection[this.title].externalSystemRefId != null ) {
                        this.paymentextsysData = this.paymentTerm.externalMappingCollection [this.title].externalSystemRefId.toString();
                      }
                       if (this.paymentTerm.externalMappingCollection[this.title].type != null ) {
                       this.paymentattr1Data = this.paymentTerm.externalMappingCollection [this.title].type;
                      }
                       if (this.paymentTerm.externalMappingCollection[this.title].mapping != null ) {
                       this.paymentattr2Data  = this.paymentTerm.externalMappingCollection [this.title].mapping;
                      }
                    if ( this.paymentTerm.externalMappingCollection[this.title].mappingId != null) {
                         this.paymentMappingId = this.paymentTerm.externalMappingCollection[this.title].mappingId;
                    }
                   
                 }
            }
       }


     }
}