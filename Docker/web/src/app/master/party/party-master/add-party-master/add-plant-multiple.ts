import { Component, Input, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Subscription } from 'rxjs/Subscription';
import { PartyMasterInterface,PlantDetails } from '../party-interface';
import { PartyMasterService } from '../party-master/../service/party-master.service';
import { Router, ActivatedRoute} from '@angular/router';
@Component({
  moduleId: module.id,
  selector: 'app-plant-intgrade',
  templateUrl: 'add-plant-multiple.component.html',
})
export class AddMultiPantComponent implements OnInit {
  public indexPosition;
  public data0;
  public data1;
  public data2;
  public dateTax;
  plant_edit: PlantDetails = new PlantDetails(); 
  pcode:boolean;
  public pageName;
  whentoAdd: boolean;
  whentoEdit: boolean;
  partyId:number;
  filterParty = [];  
  codeType: string;
  code: string;
  grade: any;
   countryList: PartyMasterInterface[];
  whenPartyCodeinLimit:boolean;
  internalGradeDesc:string;
  subscription: Subscription;
   originList:PartyMasterInterface[];
   gradeList:PartyMasterInterface[];
  codedescription: string;
   taxregdate11: Date = new Date(2000, 2, 10);
  @Input('group')
  public plantMapping: FormGroup;
  @Input() titlePlant: Number;
  @Input() financialCalendarModelsPlant: PartyMasterInterface[];
  constructor(public partyMasterService: PartyMasterService, private route: ActivatedRoute) {
   /* this.subscription = this.productMasterService.getdestinationFromJson().subscribe(destination => {

      this.grade = destination.body.intlCodeTypeRefValues;
    },
      error => { throw error },
      () => console.log('Finished')
    );*/
    
  }
  ngOnInit() {
     this.partyId =this.route.snapshot.params['pkPartyId'];
     this.whentoAdd = true;
    if (this.partyId !=null) {
      this.whentoEdit = true;
      this.whentoAdd = false;
    } else {
      this.whentoAdd = true;
      this.whentoEdit = false;
    }
    this.getDropDownCountry();
    this.indexPosition = this.titlePlant;
    if (this.financialCalendarModelsPlant != null) {
      if (this.financialCalendarModelsPlant[this.indexPosition] != undefined) {
        this.plant_edit = this.financialCalendarModelsPlant[this.indexPosition];
        this.dateTax = new Date(this.financialCalendarModelsPlant[this.indexPosition].taxRegistrationDate)
        this.data0 = this.financialCalendarModelsPlant[this.indexPosition].uniqueMapping;
       // this.data0 = this.financialCalendarModelsGrade[this.indexPosition].gradeMappingId;
      }
    }
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
