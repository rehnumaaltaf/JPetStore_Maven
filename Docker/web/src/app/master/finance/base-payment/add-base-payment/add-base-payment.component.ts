import { Component, OnInit, OnDestroy, Output , ViewChild } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';
import {BasePaymentService} from '../base-payment/../service/base-payment.service';
import { AddMultiBasePaymentComponent } from './add-multi-base-payment';
import { BasePaymentInterface,BaseNagotiationTerm } from '../base-payment-interface';
import { listingPageBasePaymnt } from '../../../../shared/interface/router-links';
import { CanComponentDeactivate } from '../../../../shared/guards/can-deactivate-guard';
import { FormGroup, FormControl , FormArray,   FormBuilder, ReactiveFormsModule , FormsModule} from '@angular/forms';
import { Router , ActivatedRoute} from '@angular/router';
import { AccordionModule , ModalDirective } from 'ngx-bootstrap';
@Component({
  selector: 'app-add-base-payment',
  templateUrl: './add-base-payment.component.html',
  styleUrls: ['./add-base-payment.component.css']
})
export class AddBasePaymentComponent implements OnInit, OnDestroy, CanComponentDeactivate {
     @ViewChild('deletesuccessModal') public deletesuccessModal: ModalDirective;
      @ViewChild('serverError') public serverError: ModalDirective;
        @ViewChild('pagebackModal') public pagebackModal: ModalDirective;
  public errormodal: boolean;
  basePaymentForm :  FormGroup;
  creditRiskpayment : string;
  subscription: Subscription;
  lcpayment : string;
  basePaymentDeatils : BasePaymentInterface = new BasePaymentInterface();
   basePaymentCheck : BasePaymentInterface = new BasePaymentInterface();
  req_termCode: boolean;
 isActionPerformed = false;
  userRoleDataList: BasePaymentInterface = new BasePaymentInterface();
  financialCalendarModel: BaseNagotiationTerm [];
  financialCalendarModelData:BasePaymentInterface [];
  whenDataEdited: boolean;
  whenStatusactive: boolean;
  addpage:boolean = false;
   editpage:boolean = false;
  whenDataNotTobeEdited: boolean;
  whenDataTobeEdited: boolean;
  deletedIds= [];
  whenStatusInactive: boolean;
  public listOfbasePaymentedit = [];
  public pageName;
  pageTitle: String;
  editgradeId:number;
  baseCodeList=[];
  errorMsg:string;
  baseNameList=[];
  ispagebackPopupModal: boolean;
  req_termName: boolean;
  req_lcbased: boolean;
  req_crdit: boolean;
  errorMessageForbasepayment: string;
  isError: boolean;
  toShowLc:boolean = true;
  count: number;
  public status: any = {
    isFirstOpen: true
    // isFirstDisabled: false
  };
 
  constructor(private router: Router, private _routeParams: ActivatedRoute,
    private fb:  FormBuilder, private route: ActivatedRoute, public basePaymentService: BasePaymentService ) { 

    this.whenDataNotTobeEdited = true;
    this.whenDataTobeEdited = false;
}
  ngOnInit() {
     this.errormodal = false;
    this.isError = false;
    window.scrollTo(0, 0);
   /*this.creditRiskpayment = '0';
     this.lcpayment = '0';
     this.basePaymentForm = this.fb.group({
      baseTermCode: '' ,
      baseTermName: '',
      baseTermDescription: '',
      baseTermCreditRisk: '',
      baseTermLCBased: '',
      statusName: '',
      baseNagotiationTerm: this.fb.array([])
    });
    this.addPermissionMapping();*/
   this.editgradeId = this.route.snapshot.params['id'];
    this.pageTitle = 'Add Base Payment ';
   
    if (this.editgradeId !=null) {
      this.addpage = false;
      this.editpage = true;
      this.pageTitle = 'Edit Base Payment ';
      this.basePaymentForm = this.fb.group({
      baseTermCode: '' ,
      baseTermName: '',
      baseTermDescription: '',
      baseTermCreditRisk: '',
      baseTermLCBased: '',
      deletedNego:'',
      baseNagotiationTerm: this.fb.array([])
    });
     this.subscription = this.basePaymentService.getSelectedFeatues('/basePayment/base', this.editgradeId).subscribe(editBasePayment => {
      this.userRoleDataList = editBasePayment.body;
      this.basePaymentService.editFeatures = this.userRoleDataList;
      this.editListDetails();
     },
      error => console.log(error),
      () => console.log('Finished')
      );
    } else {
      this.addpage = true;
      this.editpage = false;
      this.creditRiskpayment = 'N';
      this.lcpayment = 'N';
      this.buildUSERROLEForm();
      this.addPermissionMapping();
    }
    this.toHideLcBased();
    this.toShowLcBased();
}
    toHideLcBased() {
      this.toShowLc = false;
    }
    toShowLcBased(){
      this.toShowLc = true;
    }
   addPermissionMapping() {
        const control = <FormArray>this.basePaymentForm.controls['baseNagotiationTerm'];
        const addrCtrl =   this.fb.group({
           'nagotiationTermId':[],
            'nagotiationCode': [],
            'nagotiationName': [],
            'printDescription': []});
        control.push(addrCtrl);
    }
    buildUSERROLEForm(): void {
     this.basePaymentForm = this.fb.group({
      baseTermCode: '' ,
      baseTermName: '',
      baseTermDescription: '',
      baseTermCreditRisk: '',
      baseTermLCBased: '',
      statusName: '',
      deletedNego:'',
      baseNagotiationTerm: this.fb.array([])
    });

    }
 removePermissionMapping(i: number) {
   const control = <FormArray>this.basePaymentForm.controls['baseNagotiationTerm'];
      if (this.editgradeId !=null) {
        this.deletedIds.push(this.financialCalendarModel[i].nagotiationTermId);
      }
       control.removeAt(i);
    }
 getUserRoleArray(basePaymentForm) {
      return basePaymentForm.get('baseNagotiationTerm').controls;
  }
  /*saveBasePayment (){
    console.log(JSON.stringify(this.basePaymentForm.value));
  }*/
    getStyleForCode() {
    if (this.req_termCode === true) {
      return '#d43c3c';
    } else {
      return '#cfdee7';
    }
  }
  getStyleForName() {
    if (this.req_termName === true) {
      return '#d43c3c';
    } else {
      return '#cfdee7';
    }
  }
  reset () {
   // this.ngOnInit();
   this.basePaymentForm.reset();
  }
  isValidForm(): boolean {
    this.count = 0;
    if (this.basePaymentForm.value.baseTermCode == null ) {
          this.req_termCode  = true;
         this.count++;
    } else if (this.basePaymentForm.value.baseTermCode != null) {
          if (this.basePaymentForm.value.baseTermCode.trim() === '' ) {
            this.req_termCode  = true;
            this.count++;
      } else {
              this.req_termCode = false;
      }
    }
    if (this.basePaymentForm.value.baseTermName == null ) {
       this.req_termName  = true;
       this.count++;

    } else if (this.basePaymentForm.value.baseTermName != null) {
          if (this.basePaymentForm.value.baseTermName.trim() === '' ) {
            this.req_termName  = true;
            this.count++;
      } else {
              this.req_termName = false;
      }

      


    }

     if (this.basePaymentForm.value.baseTermLCBased == null ) {
       this.req_lcbased  = true;
       this.count++;

    } else if (this.basePaymentForm.value.baseTermLCBased != null) {
          if (this.basePaymentForm.value.baseTermLCBased.trim() === '' ) {
            this.req_lcbased  = true;
            this.count++;
      } else {
              this.req_lcbased = false;
      }

      


    }
     if (this.basePaymentForm.value.baseTermCreditRisk == null ) {
       this.req_crdit  = true;
       this.count++;

    } else if (this.basePaymentForm.value.baseTermCreditRisk != null) {
          if (this.basePaymentForm.value.baseTermCreditRisk.trim() === '' ) {
            this.req_crdit = true;
            this.count++;
      } else {
              this.req_crdit = false;
      }

      


    }


   this.basePaymentCheck = this.basePaymentForm.value;

    for (let i = 0; i <  this.basePaymentCheck.baseNagotiationTerm.length ; i++) {
                 for (let j = i + 1 ; j <  this.basePaymentCheck.baseNagotiationTerm.length ; j++) {
                    console.log('// check duplicate nego terms mapping' );
                    if (this.basePaymentCheck.baseNagotiationTerm[i].nagotiationCode ===
                            this.basePaymentCheck.baseNagotiationTerm[j].nagotiationCode  &&
                             this.basePaymentCheck.baseNagotiationTerm[i].nagotiationName ===
                             this.basePaymentCheck.baseNagotiationTerm[j].nagotiationName &&
                             this.basePaymentCheck.baseNagotiationTerm[i].printDescription ===
                             this.basePaymentCheck.baseNagotiationTerm[j].printDescription) {
                                console.log('// duplicate occured in nego terms')
                                this.basePaymentService.errorMessage = ' Nego Terms must be Unique ' ;
                                this.pagebackModal.show();
                                this.count++;

                    }
              }
              if (this.basePaymentCheck.baseNagotiationTerm[i].nagotiationCode == null) {
               this.basePaymentForm.value.baseNagotiationTerm.splice(i,1);
              }
            }

    if ( this.count !== 0) {
         this.showModal();
                setTimeout(() => {this.hideModal();  }, 3000)

        return false;
    } else {
        return true;
    }

}
public showModal(): void {
    this.errormodal = true;
  }

  public hideModal(): void {
    this.deletesuccessModal.hide();
  }
 
  public onHidden(): void {
    this.errormodal = false;
  }

  saveBasePayment(actionstatus) {
    console.log(actionstatus);

    //this.basePaymentService.basePaymentCodeParam = uomCode;
    this.isActionPerformed = true;
    if (this.isValidForm()) {
      this.basePaymentDeatils.baseTermCode = this.basePaymentForm.value.baseTermCode;
      this.basePaymentDeatils.baseTermName = this.basePaymentForm.value.baseTermName;
      this.basePaymentDeatils.baseTermDescription = this.basePaymentForm.value.baseTermDescription;
      this.basePaymentDeatils.baseTermCreditRisk = this.basePaymentForm.value.baseTermCreditRisk;
      this.basePaymentDeatils.baseTermLCBased = this.basePaymentForm.value.baseTermLCBased;
      this.basePaymentDeatils.baseNagotiationTerm = this.basePaymentForm.value.baseNagotiationTerm;
      this.basePaymentDeatils.action = actionstatus;
      console.log(JSON.stringify(this.basePaymentDeatils));
      this.subscription = this.basePaymentService.saveBasePaymentDetails(this.basePaymentDeatils).subscribe(addUomDetail => {
      //this.basePaymentService.uomDetails.push(addUomDetail);
      this.router.navigate([listingPageBasePaymnt], { queryParams: { 'success': 1 } });
      },
        error => {
          this.errorMsg=error;
          this.serverError.show();
          setTimeout(() => {this.serverError.hide()}, 3000);
        // this.ispagebackPopupModal = true;

        }, // Alerts need to be replaced by error
        () => console.log('Finished')
      );
    }
  }
   editListDetails() {
      if (this.userRoleDataList.statusName == 'Draft') {
        this.whenDataNotTobeEdited = false;
        this.whenDataEdited = true;
        this.whenStatusInactive = false;
        this.whenStatusactive = false;
      }else if (this.userRoleDataList.statusName == 'Active') {
        this.whenStatusactive = true;
        this.whenStatusInactive = false;
        this.whenDataEdited = false;
        this.whenDataNotTobeEdited = false;
      }else if (this.userRoleDataList.statusName == 'InActive') {
        this.whenStatusInactive = true;
        this.whenDataEdited = false;
        this.whenStatusactive = false;
        this.whenDataNotTobeEdited = false;
    }
    this.creditRiskpayment = this.userRoleDataList.baseTermCreditRisk;
    this.lcpayment = this.userRoleDataList.baseTermLCBased;
    console.log(this.userRoleDataList.baseNagotiationTerm.length);
    this.financialCalendarModel = this.userRoleDataList.baseNagotiationTerm;
    if (this.userRoleDataList.baseNagotiationTerm.length!=0) {
      for (var i=0;i<this.userRoleDataList.baseNagotiationTerm.length;i++) {
        this.addPermissionMapping();
      }
    } else {
      this.addPermissionMapping();
    }
   }
  
 updatewithStatus (statusName) {
    this.listOfbasePaymentedit =[];
  this.basePaymentDeatils =  this.basePaymentService.editFeatures;
      console.log(JSON.stringify(this.basePaymentService.editFeatures.statusName));
    this.isActionPerformed = true;
   console.log('update' + 'statusName=' + statusName );
    console.log('updateJSON' + this.basePaymentForm.value);
 if (this.isValidForm()) {
   if (statusName == 'Active') {
     this.basePaymentDeatils.statusName = 'Active';
   } else if (statusName == 'InActive') {
      this.basePaymentDeatils.statusName = 'InActive';
   }
    this.basePaymentDeatils.baseTermCode = this.basePaymentForm.value.baseTermCode;
      this.basePaymentDeatils.baseTermName = this.basePaymentForm.value.baseTermName;
      this.basePaymentDeatils.baseTermDescription = this.basePaymentForm.value.baseTermDescription;
      this.basePaymentDeatils.baseTermCreditRisk = this.basePaymentForm.value.baseTermCreditRisk;
      this.basePaymentDeatils.baseTermLCBased = this.basePaymentForm.value.baseTermLCBased;
      this.basePaymentDeatils.baseNagotiationTerm = this.basePaymentForm.value.baseNagotiationTerm;
      this.basePaymentDeatils.deletedNego = this.deletedIds;
      console.log('basepayment' + JSON.stringify(this.basePaymentDeatils));
      this.listOfbasePaymentedit.push(this.basePaymentDeatils);
      this.subscription = this.basePaymentService.updateBasePayemnt(this.listOfbasePaymentedit).subscribe(savedwithStatus => {
     
      this.deletedIds = [];
      this.router.navigate([listingPageBasePaymnt], { queryParams: { 'success': 9} });
      },
      error =>{
         this.errorMsg=error;
          this.serverError.show();
          setTimeout(() => {this.serverError.hide()}, 3000);
        // this.ispagebackPopupModal = true;
      },
      () => console.log('Finished')
    );
 }
 }

    update(){
      this.listOfbasePaymentedit =[];
      this.basePaymentDeatils =  this.basePaymentService.editFeatures;
      console.log(JSON.stringify(this.basePaymentService.editFeatures.statusName));
      this.isActionPerformed = true;
      console.log('updateJSON' + this.basePaymentForm.value);
      if (this.isValidForm()) {
      this.basePaymentDeatils.baseTermCode = this.basePaymentForm.value.baseTermCode;
      this.basePaymentDeatils.baseTermName = this.basePaymentForm.value.baseTermName;
      this.basePaymentDeatils.baseTermDescription = this.basePaymentForm.value.baseTermDescription;
      this.basePaymentDeatils.baseTermCreditRisk = this.basePaymentForm.value.baseTermCreditRisk;
      this.basePaymentDeatils.baseTermLCBased = this.basePaymentForm.value.baseTermLCBased;
      this.basePaymentDeatils.baseNagotiationTerm = this.basePaymentForm.value.baseNagotiationTerm;
      this.basePaymentDeatils.deletedNego = this.deletedIds;
      console.log('basepayment' + JSON.stringify(this.basePaymentDeatils));
      this.listOfbasePaymentedit.push(this.basePaymentDeatils);
      this.subscription = this.basePaymentService.updateBasePayemnt(this.listOfbasePaymentedit).subscribe(savedwithStatus => {
     
        this.deletedIds = [];
       this.router.navigate([listingPageBasePaymnt], { queryParams: { 'success': 4} });
      },
      error =>{
       this.errorMsg=error;
       this.serverError.show();
     //   this.ispagebackPopupModal = true;
          setTimeout(() => {this.serverError.hide()}, 3000);
      },
      () => console.log('Finished')
    );
 }
 }
  baseCodeSuggestion($event) {
    debugger;
    this.baseCodeList=[];
    const baseCode = $event.target.value;
     this.subscription = this.basePaymentService.baseCodeSuggestion(baseCode).subscribe(data => {
    // this.geoCodeList = data.body;
     for(let i=0; i< data.body.length ; i++) {
     this.baseCodeList.push(data.body[i].baseTermCode);
    // this.geoCodeList = data.body[g].geoCode;
     }
    // console.log('geocodelist from type   ' + this.geoCodeList + '  ' +JSON.stringify(data.body));
     }, error =>  { throw error; } );
  }

  baseNameSuggestion($event) {
     this.baseNameList=[];
     const baseName = $event.target.value;
     this.subscription = this.basePaymentService.baseNameSuggestion(baseName).subscribe(data => {
    // this.geoCodeList = data.body;
     for(let g=0; g< data.body.length ; g++) {
       this.baseNameList.push(data.body[g].baseTermName);
     }
   //  console.log('geonamelist from type   ' + this.geoNameList + '  ' +JSON.stringify(data.body));
     }, error =>  { throw error; } );
  }
  ngOnDestroy() {
    // Called once, before the instance is destroyed.
    //this.subscription.unsubscribe();
  }
  cancel(){
    this.router.navigate([listingPageBasePaymnt]);
  }
  close(){
    this.ispagebackPopupModal = false;
  }
   canDeactivate(): boolean {
      return this.isActionPerformed;
  }
}
