import { Component, Input, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Subscription } from 'rxjs/Subscription';
import { PartyMasterInterface,CreditLimit } from '../party-interface';
import { PartyMasterService } from '../party-master/../service/party-master.service';
import { Router, ActivatedRoute} from '@angular/router';
@Component({
  moduleId: module.id,
  selector: 'app-party-intgrade',
  templateUrl: 'add-partyLimit-multiple.component.html',
})
export class AddMultiPartySystemComponent implements OnInit {
  public indexPosition;
  public data0;
  public data1;
  public data2;
  public data3;
  public pageName;
  whentoAdd: boolean;
  whentoEdit: boolean;
  public partyDateFrom;
  public partyDateTo;
  filterParty = [];  
  codeType: string;
  code: string;
  partyNamedefault:string;
  grade: any;
    credit_limit: CreditLimit = new CreditLimit();
  destinationList: PartyMasterInterface[];
  whenPartyCodeinLimit:boolean;
  internalGradeDesc:string;
  partyNameLimit:string;
  partyUomId:number;
  productId:number;
  partyId:number;
  subscription: Subscription;
   originList:PartyMasterInterface[];
   gradeList:PartyMasterInterface[];
   limitOnList:PartyMasterInterface[];
    unitList: PartyMasterInterface[];
  partyCodeList: PartyMasterInterface[];
  productList:PartyMasterInterface[];
  limitAlertLevel:PartyMasterInterface[];
  currencyList:PartyMasterInterface[];
  commisionList:PartyMasterInterface[];
    partyVaidtODate: Date = new Date(2000, 2, 10);
  partyVaidFromDate: Date = new Date(2000, 2, 10);
  party_limit : PartyMasterInterface = new PartyMasterInterface();
  codedescription: string;
  @Input('group')
  public partyLimit: FormGroup;
  @Input() titleParty: Number;
  @Input() financialCalendarModelsParty: PartyMasterInterface[];
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
    this.indexPosition = this.titleParty;
    if (this.financialCalendarModelsParty != null) {
      if (this.financialCalendarModelsParty[this.indexPosition] != undefined) {
        this.party_limit = this.financialCalendarModelsParty[this.indexPosition];
        this.partyLimit.value.partyValidFrom = new Date (this.financialCalendarModelsParty[this.indexPosition].partyValidFrom);
        this.partyLimit.value.partyValidTo = new Date (this.financialCalendarModelsParty[this.indexPosition].partyValidTo);
        this.data0 = this.financialCalendarModelsParty[this.indexPosition].uniqueMapping;
        this.partyDateFrom = new Date (this.financialCalendarModelsParty[this.indexPosition].partyValidFrom);
        this.partyDateTo = new Date (this.financialCalendarModelsParty[this.indexPosition].partyValidTo);
       // this.data0 = this.financialCalendarModelsGrade[this.indexPosition].gradeMappingId;
      }
    }
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
    onchangePartyCode(event) {
    this.filterParty = [];
    var partyId = this.partyLimit.value.internalPartyCode;
    for (var i = 0; i < this.partyCodeList.length; i++) {
      if (this.partyCodeList[i].partyId == partyId) {
        var partyObj = this.partyCodeList[i];
        this.filterParty.push(partyObj);
        console.log(this.filterParty[0]);
      }
    }
    this.partyNamedefault = this.filterParty[0].partyName;
    console.log(this.partyNamedefault);
  }
  onchangePartyCodeForParty() {
    this.filterParty = [];  
    var partyId = this.partyLimit.value.internalPartyCodeOrg;
    for (var i = 0; i < this.partyCodeList.length; i++) {
      if (this.partyCodeList[i].partyId == partyId) {
        var partyObj = this.partyCodeList[i];
        this.filterParty.push(partyObj);
        console.log(this.filterParty[0]);
      }
    }
    this.whenPartyCodeinLimit = true;
    this.partyNameLimit = this.filterParty[0].partyName;
    console.log(this.partyNamedefault);
  }
}
