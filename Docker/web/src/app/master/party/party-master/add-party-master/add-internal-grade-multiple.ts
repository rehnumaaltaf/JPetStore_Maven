import { Component, Input, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Subscription } from 'rxjs/Subscription';
import { PartyMasterInterface,internalGradeMapping } from '../party-interface';
import { PartyMasterService } from '../party-master/../service/party-master.service';
import { Router, ActivatedRoute} from '@angular/router';
@Component({
  moduleId: module.id,
  selector: 'app-base-product-intgrade',
  templateUrl: 'add-internal-grade-multiple.component.html',
})
export class AddMultiPartyGradeComponent implements OnInit {
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
   gradelList:internalGradeMapping = new internalGradeMapping();
  grade: any;
  whenPartyCodeinLimit:boolean;
  internalGradeDesc:string;
  subscription: Subscription;
  partyId:number;
   originList:PartyMasterInterface[];
   gradeList:PartyMasterInterface[];
  codedescription: string;
  @Input('group')
  public baseProductMappingforms: FormGroup;
  @Input() title: Number;
  @Input() financialCalendarModelsGrade: PartyMasterInterface[];
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
    this.getDropDownOrigin();
    this.getDropDownGradeName();
    this.indexPosition = this.title;
    if (this.financialCalendarModelsGrade != null) {
      if (this.financialCalendarModelsGrade[this.indexPosition] != undefined) {
        this.gradelList = this.financialCalendarModelsGrade[this.indexPosition];
         this.data0 = this.financialCalendarModelsGrade[this.indexPosition].uniqueMapping;
       // this.data0 = this.financialCalendarModelsGrade[this.indexPosition].gradeMappingId;
      }
    }
  }
  getDropDownOrigin(){
  this.subscription = this.partyMasterService.getOriginDropDown().subscribe(addDoc => {
      this.originList = addDoc.body;
      // this.arbshortname = destination.body.externalSystemRefValues;
      // this.arbagency = destination.body.arbitrationAgencyValues;
    },
      error => {  },
      () => console.log('Finished')
    );
}
 getDropDownGradeName(){
  this.subscription = this.partyMasterService.getGradeDropDown().subscribe(addDoc => {
      this.gradeList = addDoc.body;
      // this.arbshortname = destination.body.externalSystemRefValues;
      // this.arbagency = destination.body.arbitrationAgencyValues;
    },
      error => {  },
      () => console.log('Finished')
    );
}
 onchangePartyCodeForGrade(event) {
   debugger;
    this.filterParty = [];  
    var index = this.title;
    var partyId = this.baseProductMappingforms.value.gradeNameId;
    for (var i = 0; i < this.gradeList.length; i++) {
      if (this.gradeList[i].gradeId == partyId) {
        var partyObj = this.gradeList[i];
        this.filterParty.push(partyObj);
        console.log(this.filterParty[0]);
      }
    }
    this.internalGradeDesc = this.filterParty[0].partyName;
    this.whenPartyCodeinLimit = true;
  }
}
