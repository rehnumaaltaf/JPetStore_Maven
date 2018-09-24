import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';
import { FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';
import { UnitService } from '../service/unit.service';
import { ProfitCenterModel } from '../model/profit-center-model';
import {DialogModule} from 'primeng/primeng';
import { Router } from '@angular/router';
import { ConfirmationService, Message } from 'primeng/primeng'; //message


@Component({
  selector: 'app-edit-unit',
  templateUrl: './edit-unit.component.html',
  styleUrls: ['./edit-unit.component.css']
})
export class EditUnitComponent implements OnInit {

  subscription: Subscription;
  isComplete: Boolean = false;
  loadingData: Boolean = false;
  showStatus:boolean=false;
  doDeActiveStatus:boolean=false;
  doReActiveStatus:boolean=false;
  msgs: Message[] = []; //message
  unitMasterID:number;
  unitMasterStatusId:string;
  profitCenterModel: ProfitCenterModel = new ProfitCenterModel();
  successModal = false;
  disableSaveBtn: Boolean = false;
  disableSubmitBtn: Boolean = false;
  disableUpdateBtn:boolean=false;
  puDisabled: Boolean = false;
  fcunitName:boolean=false;

  constructor(private unitService: UnitService,private router: Router) {
     this.loadDropdownList();
    //this.loadUnitDataList();
   }
   ngOnInit() {
      //this.loadDropdownList();
      this.fcunitName = true;
      //this.profitCenterModel.groupUnit = '0';
      this.unitMasterID=this.unitService.getId();
      this.unitMasterStatusId=this.unitService.getStatusId();
    //alert(this.unitMasterID+'      '+this.unitMasterStatus);
    if(this.unitMasterStatusId=='3'){
      this.showStatus= !this.showStatus;
    }
    if(this.unitMasterStatusId=='1'){
      this.doDeActiveStatus= !this.doDeActiveStatus;
    }
    if(this.unitMasterStatusId=='4'){
      this.doReActiveStatus= !this.doReActiveStatus;
    }
      this.loadUnitDataList(this.unitMasterID);
  }
   loadDropdownList() {
    this.subscription = this.unitService.getDropdownListJSON().subscribe(data => {
      this.isComplete = true;
      this.loadingData = true;
      this.profitCenterModel = data.body;
      //alert(JSON.stringify(data.body));
    }, error => {
      console.log('Error Loading UOM Listing: ' + <any>error)
      this.loadingData = false;
      // this.notificationService.addNotications(error);
    });
  }

  loadUnitDataList(id){
    this.subscription=this.unitService.listUnitJSON('', id).subscribe(viewUnitDetail => {
        this.isComplete = true;
       this.loadingData = true;
       this.profitCenterModel=viewUnitDetail.body[0];
       //alert(JSON.stringify(viewUnitDetail.body));
    },
    error => {
      console.log('Error Loading UOM Listing: ' + <any>error)
      this.loadingData = false;
    });

  }

  validateUnitName(): void {
    if (this.profitCenterModel.unitName !== ''
        || this.profitCenterModel.unitName !== null
        || this.profitCenterModel.unitName !== undefined) {
      this.profitCenterModel.toValidate = 'UN';
      this.subscription = this.unitService.validate(this.profitCenterModel).subscribe(data => {
        this.isComplete = true;
        if (data.body !== null && data.body !== '' ) {
          this.msgs = [];
          this.msgs.push({severity: 'error', summary: '', detail: data.body});
          this.profitCenterModel.unitName = null;
          this.disableSaveBtn = false;
          this.disableSubmitBtn = false;
        }
      }, error => {
        console.log('Error Loading UOM Listing: ' + <any>error)
      });
    }
  }

  validateUnitFullName(): void {
     if (this.profitCenterModel.unitFullName !== ''
        || this.profitCenterModel.unitFullName !== null
        || this.profitCenterModel.unitFullName !== undefined) {
        this.profitCenterModel.toValidate = 'UFN';
        this.subscription = this.unitService.validate(this.profitCenterModel).subscribe(data => {
          this.isComplete = true;
        if (data.body !== null && data.body !== '' ) {
          this.msgs = [];
          this.msgs.push({severity: 'error', summary: '', detail: data.body});
          this.profitCenterModel.unitFullName = null;
          this.disableSaveBtn = false;
          this.disableSubmitBtn = false;
          this.disableUpdateBtn = false;
        }
      }, error => {
        console.log('Error Loading UOM Listing: ' + <any>error)
        // this.loadingData = false;
        // this.notificationService.addNotications(error);
      });
    }
  }

    validateUnitCode(): void {
    if (this.profitCenterModel.unitCode !== ''
        || this.profitCenterModel.unitCode !== null
        || this.profitCenterModel.unitCode !== undefined) {
          this.profitCenterModel.toValidate = 'UC';
          this.subscription = this.unitService.validate(this.profitCenterModel).subscribe(data => {
            this.isComplete = true;
          if (data.body !== null && data.body !== '' ) {
              this.msgs = [];
              this.msgs.push({severity: 'error', summary: '', detail: data.body});
              this.profitCenterModel.unitCode = null;
              this.disableSaveBtn = false;
              this.disableSubmitBtn = false;
            }
          }, error => {
            console.log('Error Loading UOM Listing: ' + <any>error)
            // this.loadingData = false;
            // this.notificationService.addNotications(error);
        });
    }
  }


  validateInput() {
    if (this.profitCenterModel.unitName === null
      || this.profitCenterModel.unitFullName === null) {
      this.msgs = [];
      this.msgs.push({severity: 'error', summary: '', detail: 'You have not provided values for all mandatory attributes, Please check.'});
      return false;
    } else {
      return true;
    }
  }

  onCancelBtnClick(){
    this.router.navigate(['master/unitparty']);
  }

   disableParentUnit() {
    if (this.profitCenterModel.groupUnit === '0') {
      this.puDisabled = true;
    } else {
      this.puDisabled = false;
    }
  }

  onSubmitBtnClick(action){
     if (this.validateInput()) {
      this.disableSaveBtn = true;
      this.profitCenterModel.action = action;
      console.log('Edit   '+JSON.stringify(this.profitCenterModel));
      this.subscription = this.unitService.updateProfitCenter
        ('', this.profitCenterModel).subscribe(data => {
      if (data.body !== null && data.body !== '' ) {
          this.isComplete = true;
          this.msgs = [];
          this.msgs.push({severity: 'success', summary: '', detail: data.body});
          this.profitCenterModel = new ProfitCenterModel();
        }
      }, error => {
        console.log('Error Loading UOM Listing: ' + <any>error)
      });
    }
  }
  closePage(){
    this.router.navigate(['master/unitparty']);
  }
  goBack(){
    this.router.navigate(['master/unitparty']);
  }
  ngOnDestroy() {
    // Called once, before the instance is destroyed.
    //this.subscription.unsubscribe();
  }
}
