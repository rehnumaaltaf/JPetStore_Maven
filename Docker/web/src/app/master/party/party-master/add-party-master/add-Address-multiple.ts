import { Component, Input, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Subscription } from 'rxjs/Subscription';
import { PartyMasterInterface,CreditLimit,TranslatedAddress } from '../party-interface';
import { PartyMasterService } from '../party-master/../service/party-master.service';
import { Router, ActivatedRoute} from '@angular/router';
@Component({
  moduleId: module.id,
  selector: 'app-address-multiple',
  templateUrl: 'add-Address-multiple.html',
})
export class AddMultiAddressSystemComponent implements OnInit {
  public indexPosition;
  public data0;
  pcode:boolean = false;
  public data1;
  public data2;
  public data3;
  public pageName;
  whentoAdd: boolean;
  whentoEdit: boolean;
  addressMulti: TranslatedAddress = new TranslatedAddress();
  filterParty = [];  
  codeType: string;
  code: string;
  partyNamedefault:string;
  grade: any;
    credit_limit: CreditLimit = new CreditLimit();
  destinationList: PartyMasterInterface[];
  whenPartyCodeinLimit:boolean;
  internalGradeDesc:string;
  subscription: Subscription;
   originList:PartyMasterInterface[];
   gradeList:PartyMasterInterface[];
   limitOnList:PartyMasterInterface[];
    unitList: PartyMasterInterface[];
  partyCodeList: PartyMasterInterface[];
  productList:PartyMasterInterface[];
  limitAlertLevel:PartyMasterInterface[];
  currencyList:PartyMasterInterface[];
  addressName:PartyMasterInterface[];
  commisionList:PartyMasterInterface[];
  languageList:PartyMasterInterface[];
  countryList:PartyMasterInterface[];
  codedescription: string;
  partyId:number;
  @Input('group')
  public addressMultiple: FormGroup;
  @Input() titleAddress: Number;
  @Input() financialCalendarModelsAddress: PartyMasterInterface[];
  constructor(public partyMasterService: PartyMasterService, private route: ActivatedRoute) {
   /* this.subscription = this.productMasterService.getdestinationFromJson().subscribe(destination => {

      this.grade = destination.body.intlCodeTypeRefValues;
    },
      error => { throw error },
      () => console.log('Finished')
    );*/
    
  }
  ngOnInit() {
     this.partyId = this.route.snapshot.params['pkPartyId'];
     this.whentoAdd = true;
     this.whentoAdd = true;
    if (this.partyId !=null) {
      this.whentoEdit = true;
      this.whentoAdd = false;
    } else {
      this.whentoAdd = true;
      this.whentoEdit = false;
    }
   this.getDropPartyCode();
   this.getDropDownUnit();
   this.getDropDownProduct();
   this.getDropDownLimitAlert();
   this.getDropDownCurrency();
   this.getDropDownForCommisionUom();
   this.getDropDownLimitOn();
   this.getDropDownAddressName();
   this.getDropDownForLanguage();
   this.getDropDownCountry();
    this.indexPosition = this.titleAddress;
    if (this.financialCalendarModelsAddress != null) {
      if (this.financialCalendarModelsAddress[this.indexPosition] != undefined) {
        this.addressMulti=this.financialCalendarModelsAddress[this.indexPosition];
         this.data0 = this.financialCalendarModelsAddress[this.indexPosition].uniqueMapping;
       // this.data0 = this.financialCalendarModelsGrade[this.indexPosition].gradeMappingId;
      }
    }
  }
    getDropDownForLanguage() {
    this.subscription = this.partyMasterService.getLanguageList().subscribe(addDoc => {
      this.languageList = addDoc.body;
      // this.arbshortname = destination.body.externalSystemRefValues;
      // this.arbagency = destination.body.arbitrationAgencyValues;
    },
      error => { },
      () => console.log('Finished')
    );
  }
  getDropDownCountry() {
    this.subscription = this.partyMasterService.getCountryDropDown().subscribe(addDoc => {
      this.countryList = addDoc.body;
      // this.arbshortname = destination.body.externalSystemRefValues;
      // this.arbagency = destination.body.arbitrationAgencyValues;
    },
      error => { },
      () => console.log('Finished')
    );
  }
getDropDownLimitOn() {
    this.subscription = this.partyMasterService.getLimitLevelOn().subscribe(addDoc => {
      this.limitOnList = addDoc.body;
      // this.arbshortname = destination.body.externalSystemRefValues;
      // this.arbagency = destination.body.arbitrationAgencyValues;
    },
      error => { },
      () => console.log('Finished')
    );
  }
  getDropDownDestionSystem() {
    this.subscription = this.partyMasterService.getDestinationDropDown().subscribe(addDoc => {
      this.destinationList = addDoc.body;
      // this.arbshortname = destination.body.externalSystemRefValues;
      // this.arbagency = destination.body.arbitrationAgencyValues;
    },
      error => { },
      () => console.log('Finished')
    );
  }
   getDropPartyCode() {
    this.subscription = this.partyMasterService.getDropDownPartyCode().subscribe(addDoc => {
      this.partyCodeList = addDoc.body;
      // this.arbshortname = destination.body.externalSystemRefValues;
      // this.arbagency = destination.body.arbitrationAgencyValues;
    },
      error => { },
      () => console.log('Finished')
    );
  }
   getDropDownAddressName() {
    this.subscription = this.partyMasterService.getDropDownAddressName().subscribe(addDoc => {
      this.addressName = addDoc.body;
      // this.arbshortname = destination.body.externalSystemRefValues;
      // this.arbagency = destination.body.arbitrationAgencyValues;
    },
      error => { },
      () => console.log('Finished')
    );
  }
   getDropDownProduct() {
    this.subscription = this.partyMasterService.getProductDropDown().subscribe(addDoc => {
      this.productList = addDoc.body;
      // this.arbshortname = destination.body.externalSystemRefValues;
      // this.arbagency = destination.body.arbitrationAgencyValues;
    },
      error => { },
      () => console.log('Finished')
    );
  }
  getDropDownForCommisionUom() {
    this.subscription = this.partyMasterService.getCommisionUom().subscribe(addDoc => {
      this.commisionList = addDoc.body;
      // this.arbshortname = destination.body.externalSystemRefValues;
      // this.arbagency = destination.body.arbitrationAgencyValues;
    },
      error => { },
      () => console.log('Finished')
    );
  }
  getDropDownUnit() {
    this.subscription = this.partyMasterService.getDropDownUnit().subscribe(addDoc => {
      this.unitList = addDoc.body;
      // this.arbshortname = destination.body.externalSystemRefValues;
      // this.arbagency = destination.body.arbitrationAgencyValues;
    },
      error => { },
      () => console.log('Finished')
    );
  }
   getDropDownLimitAlert() {
    this.subscription = this.partyMasterService.getLimitLevelAlert().subscribe(addDoc => {
      this.limitAlertLevel = addDoc.body;
      // this.arbshortname = destination.body.externalSystemRefValues;
      // this.arbagency = destination.body.arbitrationAgencyValues;
    },
      error => { },
      () => console.log('Finished')
    );
  }
  getDropDownCurrency() {
    this.subscription = this.partyMasterService.getCurrencyList().subscribe(addDoc => {
      this.currencyList = addDoc.body;
      // this.arbshortname = destination.body.externalSystemRefValues;
      // this.arbagency = destination.body.arbitrationAgencyValues;
    },
      error => { },
      () => console.log('Finished')
    );
  }
  numberonly(evt) {
    // if ( Number(this.gradeForm.value.hedgeRatio) < 0 || Number(this.gradeForm.value.hedgeRatio) > 100 ) {
    // this.req_hedgeratiolimit = true;
    // }
    const charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode !== 46 && charCode > 31 && (charCode < 48 || charCode > 57)) {
      return false;
    }
    return true;
  }
}
