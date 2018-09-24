import { Component, OnInit,ViewChild } from '@angular/core';
import { Router,ActivatedRoute } from '@angular/router';
import {PartyMasterService} from '../party-master/service/party-master.service';
import { Subscription } from 'rxjs/Subscription';
import { master,Addpageparty,viewpageparty,listingpageparty } from '../../../shared/interface/router-links';
import { process, SortDescriptor, orderBy, GroupDescriptor, State } from '@progress/kendo-data-query';
import { ExcelExportData } from '@progress/kendo-angular-excel-export';
import { PartyMasterInterface } from './party-interface';
import { ModalDirective } from 'ngx-bootstrap/modal';

@Component({
  selector: 'app-party-master',
  templateUrl: './party-master.component.html',
  styleUrls: ['./party-master.component.css']
})
export class PartyMasterComponent implements OnInit {

  @ViewChild('successmodal') public successmodal: ModalDirective;
   @ViewChild('successmodalAdd') public successmodalAdd: ModalDirective;
  isModalShown: Boolean = false;
  subscription: Subscription;
  data: PartyMasterInterface[];
  headerPartyCode : string;
  statusName:string;
  headerPartyname : string;
  headerPartyCountry : string;
  errorMessgae:string;
  isModalShownForAdd: Boolean = false;
  headerStatus: string;
  partyCode:string;
  headerPartyMarketingDesk : string;
  partycode:string;
  public success;
  state: State = {
        skip: 0,
        take: 1000
    };

  constructor(public partyMasterService: PartyMasterService ,public router: Router,private route: ActivatedRoute) { }

  ngOnInit() {
    window.scrollTo(0, 0);
    this.partyCode = this.partyMasterService.partyName;
    console.log(this.partyCode);
    this.statusName = this.partyMasterService.statusName;
    this.route.queryParams.subscribe(params => {
        //this.partycode = this.unitMeasurementService.uomCodeParam;
         this.success = +params['success'];
        if ( this.success === 2) {
          this.isModalShown=true;
          setTimeout(() => {this.successmodal.hide();}, 3000);
          this.router.navigate([listingpageparty]);
        }
       if (this.success===1) {
         if(this.statusName== "L1 Approved") {
         this.errorMessgae = "Success: Party "+ this.partyCode + "L1 approved";
          } if (this.statusName== "Draft") {
            this.errorMessgae = "Success: Party " + this.partyCode + "Saved";
          }
          if (this.statusName== "Active") {
            this.errorMessgae = "Success: Party " + this.partyCode + "activated";
          }
          if (this.statusName== "L2 Approved") {
            this.errorMessgae = "Success: Party " + this.partyCode + "L2 approved";
          }
         //}
         this.isModalShownForAdd=true;
         setTimeout(() => {this.successmodalAdd.hide();}, 3000);
         this.router.navigate([listingpageparty]);
       }
      });
    this.loadingPartyList();
  }

  hide(){
    this.isModalShown=false;
    this.isModalShownForAdd = false;
  }

   loadingPartyList() {
     this.subscription = this.partyMasterService.getListingPageFromJson().subscribe(partyMaster => {
       for(var i=0;i<partyMaster.body.length;i++){
         if(partyMaster.body[i].partyBasicDetails!=undefined){
         if(partyMaster.body[i].partyBasicDetails.partyInternalExternal=="N"){
           partyMaster.body[i].partyBasicDetails.partyInternalExternal="External";
         }
         else if(partyMaster.body[i].partyBasicDetails.partyInternalExternal=="Y"){
           partyMaster.body[i].partyBasicDetails.partyInternalExternal="Internal";
         }
         }
       }
      this.partyMasterService.partyDetails = partyMaster.body;  


      console.log('Party Details' + JSON.stringify(this.partyMasterService.partyDetails));
      this.data = partyMaster.body;
      var data1 = partyMaster.view.column.split(",");
      if (data1[1] != null && data1[0] =='partyInternalExternal') {
        this.headerPartyCode = "Party Internal/External";
      }
      if (data1[0] != null && data1[1] =='partyName') {
        this.headerPartyname = "Party Name";
      }
      if (data1[2] != null && data1[2] =='country') {
        this.headerPartyCountry = "Geo Hierarchy";
      }
      if (data1[3] != null && data1[3] =='marketingDesk') {
        this.headerPartyMarketingDesk = "Marketing Desk";
      }
      if (data1[4] != null && data1[4] =='status') {
        this.headerStatus = "Status";
      }
     },
      error => console.log(error),
      () => console.log('Finished')
      );
  }

  viewById(partydata){
    this.router.navigate(['master/party/partymaster/view/' + partydata.pkPartyId]);
  }

  changeShowStatus(){
 // this.router.navigate(['master/party/partymaster/edit/' + partydata.pkPartyId]);
  this.router.navigate([Addpageparty]);
}

 editparty(partydetails){
   console.log(partydetails);
   var partyId = partydetails.pkPartyId;
  this.subscription = this.partyMasterService.getselectedparty(partyId).subscribe(editparty => {
      this.partyMasterService.partyBasicDetails(editparty.body.partyBasicDetails);
      this.partyMasterService.partyAddress(editparty.body.partyAddress);
      this.partyMasterService.partyBankAccountDetails(editparty.body.partyBankAccountDetails);
      this.partyMasterService.partyContacts(editparty.body.partyContacts);
      this.partyMasterService.partyGradeDefinition(editparty.body.partyGradeDefinition);
      this.partyMasterService.internalGradeMapping(editparty.body.internalGradeMapping);
      this.partyMasterService.paymentTermDetails(editparty.body.paymentTermDetails);
      this.partyMasterService.plantDetails(editparty.body.plantDetails);
      this.partyMasterService.externalSystemMapping(editparty.body.externalSystemMapping);
      this.partyMasterService.creditLimit(editparty.body.creditLimit);
      this.partyMasterService.partyLimit(editparty.body.partyLimit);
      this.partyMasterService.partyTranslations(editparty.body.partyTranslations);
      this.partyMasterService.translatedAddress(editparty.body.translatedAddress);
      this.partyMasterService.warehouseLocation(editparty.body.warehouseLocation);
      this.partyMasterService.exchangeDetails(editparty.body.exchangeDetails);
      this.partyMasterService.brokerDetails(editparty.body.brokerDetails);
      this.partyMasterService.partyDocuments(editparty.body.partyDocuments);
     // alert('ddd2s' + JSON.stringify(editBasePayment.body.baseNagotiationTerm));
     // this.partyMasterService.editProduct(event);
      //this.partyMasterService.selectedPartyBody = editparty.body;
      this.partyMasterService.selectedPartyBodyToedit = editparty.body;
      this.router.navigate(['master/party/partymaster/add/edit/' + partyId]);
     },
      error => alert(error),
      () => console.log('Finished')
      );
}

 }