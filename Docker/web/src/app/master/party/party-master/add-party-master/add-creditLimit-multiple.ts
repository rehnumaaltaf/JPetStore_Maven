import { Component, Input, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Subscription } from 'rxjs/Subscription';
import { PartyMasterInterface,CreditLimit } from '../party-interface';
import { PartyMasterService } from '../party-master/../service/party-master.service';
import { Router, ActivatedRoute} from '@angular/router';
@Component({
  moduleId: module.id,
  selector: 'app-creditLimit-intgrade',
  templateUrl: 'add-creditLimit-multiple.component.html',
})
export class AddMultiCreditSystemComponent implements OnInit {
  public indexPosition;
  public data0;
  public data1;
  public data2;
  public data3;
  public data4;
  public data5;
  public data6;
  public data7;
  public data8;
  public data9;
  public data10;
  public data11;
  public data12;
  public data13;
  public data14;
  public data15;
  public data16;

  public pageName;
  whentoAdd: boolean;
  whentoEdit: boolean;
  
  filterParty = [];  
  codeType: string;
  code: string;
    factoringEndDate: Date = new Date(2000, 2, 10);
     validFrom: Date = new Date(2000, 2, 10);
  validTo: Date = new Date(2000, 2, 10);
  credit_partyname:boolean = false;
  partyNamedefault:string;
  grade: any;
    credit_limit: CreditLimit = new CreditLimit();
  destinationList: PartyMasterInterface[];
  whenPartyCodeinLimit:boolean;
  internalGradeDesc:string;
  subscription: Subscription;
   originList:PartyMasterInterface[];
   gradeList:PartyMasterInterface[];
    unitList: PartyMasterInterface[];
    partyId:number;
  partyCodeList: PartyMasterInterface[];
  productList:PartyMasterInterface[];
  limitAlertLevel:PartyMasterInterface[];
  currencyList:PartyMasterInterface[];
  codedescription: string;
  @Input('group')
  public creditLimit: FormGroup;
  @Input() titleCredit: Number;
  @Input() financialCalendarModelsCredit: PartyMasterInterface[];
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
    this.indexPosition = this.titleCredit;
    if (this.financialCalendarModelsCredit != null) {
      if (this.financialCalendarModelsCredit[this.indexPosition] != undefined) {
        this.credit_limit = this.financialCalendarModelsCredit[this.indexPosition];
        this.data1 = new Date (this.financialCalendarModelsCredit[this.indexPosition].FactoringEndDate);
        this.data15 = new Date (this.financialCalendarModelsCredit[this.indexPosition].validFrom);
        this.data16 = new Date (this.financialCalendarModelsCredit[this.indexPosition].validTo);
        this.data0 = this.financialCalendarModelsCredit[this.indexPosition].uniqueMapping;
      }
    }
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
  FactoringEndDatevalid(value){
   if (this.partyMasterService.creditLimitList != null ) {
          if (this.partyMasterService.creditLimitList[value] != null) {
            if (this.partyMasterService.creditLimitList[value].FactoringEndDate == null) {
                 return  true;
            } else {
                return  false;
            }
        }
        return false;
      }
  } 
  limitType(value){
     if (this.partyMasterService.creditLimitList != null ) {
          if (this.partyMasterService.creditLimitList[value] != null) {
            if (this.partyMasterService.creditLimitList[value].limitType == null) {
                 return  true;
            } else {
                return  false;
            }
        }
      }
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
    onchangePartyCode() {
    this.filterParty = [];
    var partyId = this.creditLimit.value.internalPartyCode;
    for (var i = 0; i < this.partyCodeList.length; i++) {
      if (this.partyCodeList[i].partyId == partyId) {
        var partyObj = this.partyCodeList[i];
        this.filterParty.push(partyObj);
        console.log(this.filterParty[0]);
      }
    }
    this.partyNamedefault = this.filterParty[0].partyName;
    this.credit_partyname = true;
    console.log(this.partyNamedefault);
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
  
  onchangePartyCodeForParty(event) {
    alert('');
    this.filterParty = [];  
    var partyId = this.creditLimit.value.partyLimit[event].internalPartyCodeOrg;
    for (var i = 0; i < this.partyCodeList.length; i++) {
      if (this.partyCodeList[i].partyId == partyId) {
        var partyObj = this.partyCodeList[i];
        this.filterParty.push(partyObj);
        console.log(this.filterParty[0]);
      }
    }
    this.whenPartyCodeinLimit = true;
    console.log(this.partyNamedefault);
  }
}
