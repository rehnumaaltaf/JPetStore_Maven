import { Component, Input, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Subscription } from 'rxjs/Subscription';
import { PartyMasterInterface,ExternalSystemMapping } from '../party-interface';
import { PartyMasterService } from '../party-master/../service/party-master.service';
import { Router, ActivatedRoute} from '@angular/router';
@Component({
  moduleId: module.id,
  selector: 'app-externalSystem-intgrade',
  templateUrl: 'add-externalSytem-multiple.component.html',
})
export class AddMultiExternalSystemComponent implements OnInit {
  public indexPosition;
  public data0;
  public data1;
  public data2;
  public data3;
  public pageName;
  whentoAdd: boolean;
  whentoEdit: boolean;
  exterSystem:ExternalSystemMapping= new ExternalSystemMapping();
  filterParty = [];  
  codeType: string;
  code: string;
  partyId:number;
  grade: any;
  destinationList: PartyMasterInterface[];
  whenPartyCodeinLimit:boolean;
  internalGradeDesc:string;
  subscription: Subscription;
   originList:PartyMasterInterface[];
   gradeList:PartyMasterInterface[];
  codedescription: string;
  @Input('group')
  public externalMapping: FormGroup;
  @Input() titleExternal: Number;
  @Input() financialCalendarModelsExternal: PartyMasterInterface[];
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
    this.getDropDownDestionSystem();
    this.indexPosition = this.titleExternal;
    if (this.financialCalendarModelsExternal != null) {
      if (this.financialCalendarModelsExternal[this.indexPosition] != undefined) {
        this.exterSystem = this.financialCalendarModelsExternal[this.indexPosition];
          this.data0 = this.financialCalendarModelsExternal[this.indexPosition].uniqueMapping;
       // this.data0 = this.financialCalendarModelsGrade[this.indexPosition].gradeMappingId;
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
