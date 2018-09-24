import { Component, OnInit, OnDestroy, ViewEncapsulation, Input } from '@angular/core';
import { Router , ActivatedRoute } from '@angular/router';
// import { ForexModel } from '../model/forex-model';
import { Subscription } from 'rxjs/Subscription';
import { ForexService } from '../service/forex.service';
import { FormBuilder, FormGroup, FormControl, Validators , FormArray , ReactiveFormsModule , FormsModule} from '@angular/forms';
import { AccordionModule, ModalModule  } from 'ngx-bootstrap';
import { SharedModule } from '../../../../shared/shared.module';
import { BrowserModule } from '@angular/platform-browser';
import { TenorMappingComponent } from './tenor-mapping.component';
import { ForexModel } from '../forex-interface';
import { ForwardTenor } from '../forex-interface';
import { forex } from '../../../../shared/interface/router-links';
import { CanComponentDeactivate } from '../../../../shared/guards/can-deactivate-guard';

@Component({
  selector: 'app-add-forex',
  templateUrl: './add-forex.component.html',
  styleUrls: ['./add-forex.component.css'],
    encapsulation: ViewEncapsulation.None,
})
export class AddForexComponent implements OnInit , OnDestroy , CanComponentDeactivate {
  // @Input()
 // com1ref: TenorMappingComponent;
  forexModel: ForexModel = new ForexModel();
  forwardTenor: ForwardTenor= new ForwardTenor();
  forexForm: FormGroup;
  AddEditForexId: Number;
  forexModelList: ForexModel[] = [];
  pageTitle: String;
  subscription: Subscription;
  editDraft: Boolean = false;
  editActivate: Boolean = false;
  editDeactivate: Boolean = false;
  req_forexName: Boolean;
  req_tickerCode: Boolean;
  reqmsg = '';
  checkdropdown = 0;
  isShowValModalForex: Boolean = false;
  valMessageForex: string;
  disableSaveBtn: Boolean = false;
  disableSubmitBtn: Boolean = false;
  isActionPerformed = false;
  frequency: Number;
  counterCurrency: Number;
  baseCurrency: Number;

  constructor(private fb: FormBuilder, private router: Router, public forexService: ForexService, private route: ActivatedRoute) { }

  ngOnInit() {
     this.AddEditForexId = Number(this.route.snapshot.params['id']);
     this.pageTitle = 'ADD FOREX DETAILS';
     this.forexService.forexTenor = [];
      this.forexService.forexDuration = [];
      this.forexService.forexDayType = [];
     if (this.AddEditForexId) {
        this.pageTitle = 'EDIT FOREX DETAILS';
        this.forexService.getUnitJSON().subscribe(listDetails => {
          // this.forexService.displayForexDetails(listDetails.body[0]);
        },
        error => { throw error });
        // this.product = this.productService.getById(productId);
     }
    this.buildForexForm();
    // add forward tenor
    this.addTenor();

    this.forexService.getDropdownListJSON().subscribe(listDetails => {
        // console.log(JSON.stringify(listDetails));
        this.forexService.forexTenor = listDetails.body.tenorDropDownList;
        this.forexService.forexDuration = listDetails.body.tenorDurationTypeList;
        this.forexService.forexDayType = listDetails.body.tenorDayTypeList;
        this.forexModel.baseCurrencyList = listDetails.body.baseCurrencyList;
        this.forexModel.counterCurrencyList = listDetails.body.counterCurrencyList;
        this.forexModel.frequencyList = listDetails.body.frequencyList;
    },
    error => { throw error });
  }

  buildForexForm(): void {
    this.forexForm = this.fb.group({
      'forexName': [this.forexModel.forexName],
      'forexCode':    [this.forexModel.forexCode],
      'forexDesc':    [this.forexModel.forexDesc],
      'baseCurrency':  [this.forexModel.baseCurrency],
      'counterCurrency': [this.forexModel.counterCurrency],
      'frequency' : [this.forexModel.frequency],
      'toValidate': [this.forexModel.toValidate],
      'tenorList': this.fb.array([])
    });
  }
   // task related functions
  getTenorList(forexForm) {
    return forexForm.get('tenorList').controls;
  }

  addTenor() {
        const control = <FormArray>this.forexForm.controls['tenorList'];
        const addrCtrl = this.initTenor();
        control.push(addrCtrl);
  }
  initTenor() {
    return this.fb.group({
        ticketerCode: [this.forwardTenor.ticketerCode],
        tenorType: [],
        span: [this.forwardTenor.span],
        tenorDurationType: [],
        tenorDayType: []
    });
  }
  removeTenor(i: number) {
     // alert(i);
     if (i !== 0 ) {
        const control = <FormArray>this.forexForm.controls['tenorList'];
        control.removeAt(i);
     }
  }

  /*to save the details to backend using API*/
  saveForex(action) {
    this.isActionPerformed = true;
      if (this.isValidForm()) {
            this.forexForm.value.action = action;
            // alert(action + '  ' + this.forexModel.status);
            console.log('this.forexForm.value   ' + JSON.stringify(this.forexForm.value));
            this.subscription = this.forexService.saveForexDetails(this.forexForm.value).subscribe(data => {
                if (data.body !== null && data.body !== '' ) {
                    // console.log(data.body);
                    this.forexService.setMessage(data.body);
                    this.router.navigate([forex], { queryParams: { 'success': 1 } });
                }
            },
            error => {throw error; });
      }
  }

  /*To list the suggestions for name*/
  onNameSuggestion($event) {
   const forexNameValue = $event.target.value;
    if (forexNameValue !== null && forexNameValue !== undefined && forexNameValue !== '') {
      this.subscription = this.forexService.forexNameSuggestion(forexNameValue, 'NAME').subscribe(nameDetails => {
      this.forexService.forexNameList = nameDetails.body;
     }, error =>  { throw error; } );
    }
  }
  /*To list the suggestions for desc*/
  onDescSuggestion($event) {
   const forexDescriptionValue = $event.target.value;
    if (forexDescriptionValue !== null && forexDescriptionValue !== undefined && forexDescriptionValue !== '') {
      this.subscription = this.forexService.forexDescSuggestion(forexDescriptionValue, 'DESC').subscribe(descDetails => {
      this.forexService.forexDescList = descDetails.body;
     }, error =>  { throw error; } );
    }
  }
  /*To validate the form*/
   isValidForm(): Boolean {
      this.reqmsg = '';
      this.checkdropdown = 0;
      const sizerollemapping = Number( JSON.stringify(this.forexForm.value.tenorList.length));
      // this.forexService.setTenorList(this.forexForm.value.tenorList.length);
      if ((this.forexForm.value.forexName === '' || this.forexForm.value.forexName === undefined
       || this.forexForm.value.forexName === null)) {
             this.reqmsg += 'Forex Name,';
             this.checkdropdown++;
             this.req_forexName = true;
      }
      if ( sizerollemapping >= 1 ) {
            for (let i = 0; i <  sizerollemapping ; i++) {
                if (this.forexForm.value.tenorList[i] === undefined ) {
                   this.reqmsg += 'Forward Tenor,';
                   this.checkdropdown++;
                }else if (this.forexForm.value.tenorList[i] !== undefined ) {
                    if ( this.forexForm.value.tenorList[i].ticketerCode === undefined ||
                     this.forexForm.value.tenorList[i].ticketerCode === null) {
                       if ( ! this.reqmsg.includes('Ticker code,')) {
                          this.reqmsg += 'Ticker code,';
                          this.checkdropdown++;
                       }
                       this.req_tickerCode = true;
                       this.forexService.setTickerCode(this.req_tickerCode);
                    }
                    if ( this.forexForm.value.tenorList[i].tenorType === undefined ||
                    this.forexForm.value.tenorList[i].tenorType === null) {
                      if (! this.reqmsg.includes('Tenor,')) {
                          this.reqmsg += 'Tenor';
                          this.checkdropdown++;
                      }
                    }
                 }
             }
        }
        if (this.checkdropdown === 0 ) {
             console.log('in if');
                 return true;
        }else {
            this.reqmsg += ' Fields are Required';
            return false;
        }
  }
   getForexNameStyle() {
    if (this.req_forexName === true) {
      return '#d43c3c';
    } else {
      return '#cfdee7';
    }
  }
  /*To compare the base currency and counter currency selected*/
  onCurrencyChange(value, action) {
    if ((this.forexForm.value.baseCurrency !== null && this.forexForm.value.counterCurrency !== null)
     && (this.forexForm.value.baseCurrency !== undefined && this.forexForm.value.counterCurrency !== undefined)
     && (this.forexForm.value.baseCurrency !== '' && this.forexForm.value.counterCurrency !== '')) {
      // console.log('in if of cp  ' + this.forexForm.value.baseCurrency  + this.forexForm.value.counterCurrency );
      this.toValidateFn(action);
    }
  }
  /*to check the duplication of forex name*/
  validateForexName(name) {
     if (this.forexForm.value.forexName !== null || this.forexForm.value.forexName !== undefined || this.forexForm.value.forexName !== '') {
       this.req_forexName = false;
        this.toValidateFn(name);
     }
  }
  /*To check the duplication of forex code*/
  validateForexCode(code) {
    if (this.forexForm.value.forexCode !== null || this.forexForm.value.forexCode !== undefined || this.forexForm.value.forexCode !== '' ) {
       this.toValidateFn(code);
    }
  }

  toValidateFn(action) {
    this.forexForm.value.toValidate = action;
    this.subscription = this.forexService.validate(this.forexForm.value).subscribe(data => {
          if (data.body !== null && data.body !== '' ) {
              this.isShowValModalForex = true;
              this.valMessageForex = data.body;
              setTimeout(() => {this.isShowValModalForex = false; }, 3000);
              this.isShowValModalForex = true;
              if (action === 'CP') {
                this.forexForm.patchValue({baseCurrency: null});
                this.forexForm.patchValue({counterCurrency: null});
              }
              if (action === 'NAME') {
                 this.forexForm.patchValue({forexName: null});
              }
              if ( action === 'CODE') {
                this.forexForm.patchValue({forexCode: null });
              }
              this.disableSaveBtn = false;
              this.disableSubmitBtn = false;
            }
          }, error => { throw error });
  }
  /*end of validations */
  reset () {
     this.forexForm.reset();
     this.req_forexName = false;
     this.forexService.setTickerCode(false);
     this.checkdropdown = 0;
     this.reqmsg = '';
     this.ngOnInit();
  }
  ngOnDestroy() {
    // prevent memory leak when component destroyed
    // this.subscription.unsubscribe();
  }
  canDeactivate(): boolean {
      return this.isActionPerformed;
  }

}
