import { Component, Input, OnInit, OnChanges } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ForexService } from '../service/forex.service';
@Component({
    moduleId: module.id,
    selector: 'app-tenor-mapping',
    templateUrl: 'tenor-mapping.component.html'
})
export class TenorMappingComponent implements OnChanges {
    req_tickerCode: Boolean = false;
     @Input ('group') public tenorMappingForm: FormGroup;
     tenorType: Number;
     tenorDurationType: Number;
     tenorDayType: Number;

    constructor(public forexService: ForexService ) {}
    validateForexTickerCode() {
      if (this.tenorMappingForm.value.ticketerCode !== null || this.tenorMappingForm.value.ticketerCode !== undefined || 
      this.tenorMappingForm.value.ticketerCode !== '' ) {
         this.req_tickerCode = false;
         this.forexService.setTickerCode(this.req_tickerCode);
      }
    }
     getTickerCodeStyle() {
         this.req_tickerCode = this.forexService.getTickerCode();
        if (this.req_tickerCode === true) {
           return '#d43c3c';
        } else {
        return '#cfdee7';
        }
    }
    ngOnChanges() {

       // console.log( 'test==>', this.tenorMappingForm);

    }
}

