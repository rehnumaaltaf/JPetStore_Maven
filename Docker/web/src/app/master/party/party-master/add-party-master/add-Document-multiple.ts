import { Component, Input, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Subscription } from 'rxjs/Subscription';
import { PartyMasterInterface,CreditLimit ,PartyDocuments} from '../party-interface';
import { PartyMasterService } from '../party-master/../service/party-master.service';
import { Router, ActivatedRoute} from '@angular/router';
@Component({
  moduleId: module.id,
  selector: 'app-documnet-multiple',
  templateUrl: 'add-Document-multiple.html',
})
export class AddMultiDocument implements OnInit {
  public indexPosition;
  public data0;
  public data1;
  public data2;
  public data3;
  public pageName;
  whentoAdd: boolean;
  whentoEdit: boolean;
  
  filterParty = [];  
  codeType: string;
  code: string;
  docList:PartyDocuments= new PartyDocuments();
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
  docItemType:PartyMasterInterface[];
  docItem:PartyMasterInterface[];
  codedescription: string;
  partyId:number;
  @Input('group')
  public documentMultiple: FormGroup;
  @Input() titleDoc: Number;
  @Input() financialCalendarModelsdoc: PartyMasterInterface[];
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
    if (this.partyId != null) {
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
   this.getDropDownForDocumentType();
   this.getDropDownForDocumentName();
    this.indexPosition = this.titleDoc;
    if (this.financialCalendarModelsdoc != null) {
      if (this.financialCalendarModelsdoc[this.indexPosition] != undefined) {
        this.docList = this.financialCalendarModelsdoc[this.indexPosition];
        this.data0 = this.financialCalendarModelsdoc[this.indexPosition].uniqueMapping;
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
   getDropDownForDocumentName() {
    this.subscription = this.partyMasterService.getDocumentName().subscribe(addDoc => {
      this.docItem = addDoc.body;
      // this.arbshortname = destination.body.externalSystemRefValues;
      // this.arbagency = destination.body.arbitrationAgencyValues;
    },
      error => { },
      () => console.log('Finished')
    );
  }
   getDropDownForDocumentType() {
    this.subscription = this.partyMasterService.getDocumentType().subscribe(addDoc => {
      this.docItemType = addDoc.body;
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
