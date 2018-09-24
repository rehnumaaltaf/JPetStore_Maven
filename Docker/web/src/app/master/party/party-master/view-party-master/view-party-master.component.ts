import { Component, NgModule, OnInit, Input, Output, EventEmitter, ViewChild } from '@angular/core'
import { Subscription } from 'rxjs/Subscription';
import { Router,ActivatedRoute } from '@angular/router';
import { PartyMasterService } from '../party-master/../service/party-master.service';
import { PartyMasterInterface } from '../party-interface'
import { ModalDirective } from 'ngx-bootstrap/modal';
import { Addpageparty,listingpageparty } from '../../../../shared/interface/router-links';

@Component({
  selector: 'app-view-party-master',
  templateUrl: './view-party-master.component.html',
  styleUrls: ['./view-party-master.component.css']
})
export class ViewPartyMasterComponent implements OnInit {
  public status: any = {
    isFirstOpen: true,
    isFirstDisabled: false
  };

  statuses: string;

  subscription: Subscription;
  isDataLoaded: boolean = false;
  partygradefef: boolean = false;
  intgrademap: boolean = false;
  defpayterms: boolean = false;
  plantdetails: boolean = false;
  creditlimit: boolean = false;
  partylimit: boolean = false;
  warehouse: boolean = false;
   partyDetailsObjview: PartyMasterInterface = new PartyMasterInterface();
  exchange: boolean = false;
  broker: boolean = false;
  documents: boolean = false;

  statusstate: string;
  code: string;
  partyId:number;

  @ViewChild('view_all_address') public view_all_address: ModalDirective;
  @ViewChild('view_all_bank_details') public view_all_bank_details: ModalDirective;
  @ViewChild('view_all_contact_details') public view_all_contact_details: ModalDirective;
  @ViewChild('deletemodal') public deletemodal: ModalDirective;
  partydetails: any;//PartyMasterInterface=new PartyMasterInterface();
  constructor(public partyMasterService: PartyMasterService,public router: Router,public route: ActivatedRoute ) { }

  ngOnInit() {
    this.partydetails = [];
   // debugger;
    this.partyId = JSON.parse(this.route.snapshot.params['pkPartyId']);
    this.loadparty(this.partyId);

  }


  update(data) {
    this.statuses = data.partyBasicDetails.statusName;
    this.code = data.partyBasicDetails.partyCode
    if (this.statuses === 'Active') {
      this.statusstate = 'De-Activate';
    } else if (this.statuses === 'Draft') {
      this.statusstate = 'Activate';
    } else if (this.statuses === 'InActive') {
      this.statusstate = 'Reactivate';
    }
    this.deletemodal.show();

  }

  conf_delete(data) {
    const param = { 'partyCode': this.code };
    this.subscription = this.partyMasterService.changestatus(param).subscribe(updateData => {
      // this.updateData = updateData;
      // this.isShowModal = true;
      //       setTimeout(() => {this.isShowModal = false; }, 3000);
      //       this.unitMeasurementService.flag = true;
      //this.router.navigate([uom]);

    }, error => {
      // alert(this.unitMeasurementService.errorMessage);
      //this.isCannotDeletePopup = true;
    },
      () => console.log('Finished')

    );
    this.router.navigate([listingpageparty], { queryParams: { 'success': 2 }} );
  }


  viewaddress() {
    this.view_all_address.show();
  }
  viewbank_details() {
    this.view_all_bank_details.show();
  }
  viewcontact_details() {
    this.view_all_contact_details.show();
  }
  closecontact() {
    this.view_all_address.hide();
  }
  closecontact_details() {
    this.view_all_contact_details.hide();
  }
  closebank_details() {
    this.view_all_bank_details.hide();
  }
  loadparty(id) {
    this.subscription = this.partyMasterService.getselectedparty(id).subscribe(partyDetail => {
    //  this.subscription = this.partyMasterService.getselectedparty().subscribe(partyDetail => {
      this.partydetails = partyDetail.body;
      this.isDataLoaded = true;
      if (this.partydetails.partyBasicDetails.partyInternalExternal == "N") {
        this.defpayterms = true;
      }
      if(this.partydetails.partyBasicDetails.partyClassifications!=null || this.partydetails.partyBasicDetails.partyClassifications!=undefined){
      for (var j = 0; j < this.partydetails.partyBasicDetails.partyClassifications.length; j++) {
        if ((this.partydetails.partyBasicDetails.partyInternalExternal == "Y" || this.partydetails.partyBasicDetails.partyInternalExternal == "N") && this.partydetails.partyBasicDetails.partyClassifications[j].toLowerCase() == "warehouse") {
          this.warehouse = true;
        }
        if (this.partydetails.partyBasicDetails.partyInternalExternal == "N") {
           if(this.partydetails.partyBasicDetails.partyClassifications[j] != null) {
          var party_classifications = this.partydetails.partyBasicDetails.partyClassifications[j].toLowerCase();
          if (party_classifications == "roaster") {
            this.plantdetails = true;
          }
          if (party_classifications == "exchange") {
            this.exchange = true;
          }
          if (party_classifications.indexOf('broker') > -1) {
            this.broker = true;
          }
           }

        }

      }
    }

    if(this.partydetails.partyBasicDetails.partyTypes!=null || this.partydetails.partyBasicDetails.partyTypes!=undefined){
      for (var j = 0; j < this.partydetails.partyBasicDetails.partyTypes.length; j++) {
        if (this.partydetails.partyBasicDetails.partyInternalExternal == "N") {
          if(this.partydetails.partyBasicDetails.partyTypes[j] != null) {
          var partytype = this.partydetails.partyBasicDetails.partyTypes[j].toLowerCase();
          if (partytype == "customer") {
            this.partygradefef = true;
            this.intgrademap = true;
            this.creditlimit = true;
            this.partylimit = true;
            this.documents = true;
          }
        }
        }

      }
    }


    },
      error => {
        //alert(error);


      }, // Alerts need to be replaced by error
      () => console.log('Finished')
    );
  }

  editparty(partyId){
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
updateParty(status) {
 this.partyDetailsObjview =  this.partydetails;
 this.partyDetailsObjview.partyBasicDetails.fkStatusId = '';
 this.partyDetailsObjview.partyBasicDetails.statusName= status;
 console.log(JSON.stringify(this.partyDetailsObjview));
 this.subscription = this.partyMasterService.updtaePartyDetails(this.partyDetailsObjview).subscribe(addUomDetail => {
      //this.basePaymentService.uomDetails.push(addUomDetail);
      this.router.navigate([listingpageparty], { queryParams: { 'success': 2 } });
    },
      error => {
        //alert(error);
        //  this.ispagebackPopupModal = true;
      //  this.duplicatedError.show();

      }, // Alerts need to be replaced by error
      () => console.log('Finished')
    );
    }
    
 back(){
    this.router.navigate([listingpageparty]);
 }
}

