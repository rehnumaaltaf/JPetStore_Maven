import { Component, OnInit, OnDestroy , ViewChild , OnChanges } from '@angular/core';
import { AccordionModule , ModalDirective } from 'ngx-bootstrap';
import { FormGroup ,  FormControl,  Validators, FormArray,   FormBuilder, ReactiveFormsModule , FormsModule} from '@angular/forms';
import { PaymentTermsService } from './../service/payment-terms.service';
import { PaymentExternalMappingComponent} from './payment-external-system-mapping.component';
import { PaymentTerm } from './../payment-terms-model';
import { PaymentBasecode } from './../payment-terms-model';
import { BasisCode } from './../payment-terms-model';
import { SelectItem } from '../../../../shared/interface/selectItem';
import { Router , ActivatedRoute} from '@angular/router';
import { paymentterm } from '../../../../shared/interface/router-links';
import { Subscription } from 'rxjs/Subscription';
@Component({
  selector: 'app-add-payment-terms',
  templateUrl: './add-payment-terms.component.html',
  styleUrls: ['./add-payment-terms.component.css']
})
export class AddPaymentTermsComponent implements OnInit , OnDestroy  {
   @ViewChild('deletesuccessModal') public deletesuccessModal: ModalDirective;
   @ViewChild('successModal') public successModal: ModalDirective;
   
  public errormodal: boolean;
    Math: any;
    paymentTermsForm: FormGroup;
    lccheck: boolean;
    public paymentTermId: number;
    paymentterms: PaymentTerm = new PaymentTerm();
    sumbitstatus: string;
    checkdropdown: number;
    reqmsg: string;
    req_termcode: boolean;
    req_termname: boolean;
    req_dueday: boolean;
    req_contingencyDays: boolean;
    req_atsight: boolean;
    req_confirmation: boolean;
    req_revocable: boolean;
    req_basis: boolean;
    reqmsgcode: string;
    reqmsgname: string;
    reqmsgatsight: string;
    reqmsgrevocable: string;
    reqmsgconfirm: string;
    reqmsgbasis: string;
    reqmsgcon: string;
    reqmsgdueday: string;
     payTermCodeList: string[] = [];
   payTermNameList: string[] = [];
   minTypeaheadLength = 3;
    draft: boolean;
    active: boolean ;
    inactive: boolean;
    formarraylength: number;
    showcross:  boolean[]  =  [];
    showplus:  boolean[]  =  [];
    checkingexternalduplicate: number;
     isDeletePopupModal: Boolean = false;
    isDeactivatePopupModal: Boolean = false;
    isActiveStatus: Boolean = false;
    isDraftStatus: Boolean = false;
    deleteSuccessModal: Boolean = false;
  public status: any = {
    isFirstOpen: true
   // isFirstDisabled: false
  };
  basecodedata: PaymentBasecode [] = [];
  atsightdata: SelectItem [] = [];
  revocabledata: SelectItem [] = [];
  confirmeddata: SelectItem [] = [];
  basisdata: BasisCode [] = [];
  pagetitle: string;
  editpage: boolean;
  addpage: boolean;
  isError: boolean;
  public errormodalList: boolean;
  errorMsg:string;
  addoreditpage: boolean;
  editPermissionId: Number;
  errorMessageForPaymentterm: string;
  subscription: Subscription;
  constructor( private paymentTermsService: PaymentTermsService , private fb: FormBuilder , private router: Router,
        private route: ActivatedRoute  ) {
          this.Math = Math;
        }

  ngOnInit() {
    this.lccheck = false;
    this.req_termcode = false;
    this.req_termname = false;
    this.req_dueday = false;
    this.req_contingencyDays = false;
    this.req_atsight = false;
    this.req_confirmation = false;
    this.req_revocable  = false;
    this.req_basis = false;
    this.isError = false;
    this.errormodal = false;
    this.getDropDown()
    this.initPaymentForm();
    this.editPermissionId = this.route.snapshot.params['id'];
    if (this.editPermissionId != null) {
           this.pagetitle = 'Edit Payment Term';
           this.paymentTermsService.getPaymentTermsById(this.editPermissionId).
           subscribe(paymenttermsDetails => {
             console.log('edit party ' + JSON.stringify(paymenttermsDetails.body));
              this.editpage = true;
              this.addpage = false;
              this.paymentterms = paymenttermsDetails.body;
              console.log('Edit page');
              this.editPaymentTerms();
                if (this.paymentterms.statusName === 'Draft') {
                    this.draft = true;
                    this.active = false;
                    this.inactive = false;
                } else if (this.paymentterms.statusName === 'Active' ) {
                    this.active = true;
                    this.draft = false;
                    this.inactive = false;
                } else if (this.paymentterms.statusName === 'InActive') {
                    this.inactive = true;
                    this.active = false;
                    this.draft = false;
                }
                if (this.paymentterms.atSightUsance != null && this.paymentterms.atSightUsance !== undefined ) {
                        this.lccheck  =  true;
                } else {
                        this.lccheck  =  false;
                    }

       },
         error => { throw error;
               }

        );

     }else {
        this.pagetitle = 'Add Payment Term';
        this.editpage = false;
        this.addpage = true;
        this.addExternalMapping();
     }

  }
 

  ngOnDestroy() {

  }
  initPaymentForm(): void {
        this.paymentTermsForm = this.fb.group({
            'paymentTermId': [this.paymentterms.paymentTermId] ,
            'payTermCode': [''],
            'payTermName': [''],
            'payTermDesc': [''],
            'paymentTermBaseId': [''],
            'atSightUsanceFlagId': [''],
            'revocableIrrevocableFlagId': [''],
            'confirmedNonconfirmedFlagId': [],
            'paymentBasisRefId': [''],
            'dueDays': [''],
            'contingencyDays': [''],
            'printDescription': [''],
            'statusName': [],
            'updateStatusName': ['0'],
            'externalMappingCollection': this.fb.array([]),
            'deletedMappings': this.fb.array([
               ])
         });
   }
   payTermCodeSuggestion($event) {
    this.payTermCodeList=[];
    const payTermCode = $event.target.value;
     this.subscription = this.paymentTermsService.payTermCodeSuggestion(payTermCode).subscribe(data => {
    // this.geoCodeList = data.body;
     for(let g=0; g< data.body.length ; g++) {
     this.payTermCodeList.push(data.body[g].payTermCode);
    // this.geoCodeList = data.body[g].geoCode;
     }
    // console.log('geocodelist from type   ' + this.geoCodeList + '  ' +JSON.stringify(data.body));
     }, error =>  { throw error; } );
  }

  payTermNameSuggestion($event) {
     this.payTermNameList=[];
     const payTermName = $event.target.value;
     this.subscription = this.paymentTermsService.payTermNameSuggestion(payTermName).subscribe(data => {
    // this.geoCodeList = data.body;
     for(let g=0; g< data.body.length ; g++) {
       this.payTermNameList.push(data.body[g].payTermName);
     }
   //  console.log('geonamelist from type   ' + this.geoNameList + '  ' +JSON.stringify(data.body));
     }, error =>  { throw error; } );
  }
 isNotBlank(value: string): Boolean {
    return (value && value.trim() !== '');
  }
   editPaymentTerms() {
        const sizerollemapping = Number( JSON.stringify(this.paymentterms.externalMappingCollection.length));
        if (sizerollemapping >= 1 ) {
            for (let i = 0; i <  sizerollemapping ; i++) {
                    this.addExternalMapping()
            }
        }
       }
  addExternalMapping() {
        const control = <FormArray>this.paymentTermsForm.controls['externalMappingCollection'];
        const addrCtrl =   this.fb.group({
            'mappingId': [],
            'externalSystemRefId': [''],
            'type': [''],
            'mapping': [''] });
        control.push(addrCtrl);
    }

    removeExternalMapping(i: number) {
        const control = <FormArray>this.paymentTermsForm.controls['externalMappingCollection'];
        if (this.paymentTermsForm.value.externalMappingCollection != null ) {
           //  alert(this.paymentterms.externalMappingCollection[i].mappingId)
                if (this.paymentTermsForm.value.externalMappingCollection[i].mappingId != null  &&
                     this.paymentTermsForm.value.externalMappingCollection[i].mappingId !== undefined ) {
                  this.paymentTermsForm.value.deletedMappings.push(this.paymentTermsForm.value.externalMappingCollection[i].mappingId);
                 this.paymentTermsForm.value.externalMappingCollection.splice(i, 1);
                }
            }
        control.removeAt(i);
        if (control.length === 0 ) {
             this.addExternalMapping();
        }
    }
    addExternalGlMapping() {
// this.formarraylength++;

        const control  =  <FormArray>this.paymentTermsForm.controls['externalMappingCollection'];
        this.showplus[this.showplus.length - 1]  =  true;
        this.showplus.push(false);
        this.showcross.push(false);
        if  (this.showcross.length === 1) {
        this.showcross[0]  =  true;
        this.showplus[0]  = false;
        }else {
        this.showcross[0]  =  false;
        }

        const addrCtrl  =  this.fb.group({
            'mappingId': [],
            'destinationSystemId': [''],
            'type': [''],
            'mapping': [''] });
        // alert("addrCtrl" +addrCtrl);
        control.push(addrCtrl);

}

removePermissionMapping(i:  number) {
// if (this.formarraylength !== 1 ) {
    if  ( this.getPaymentExternalMappingList(this.paymentTermsForm).length >= 1) {
        const control  =  <FormArray>this.paymentTermsForm.controls['externalMappingCollection'];
        control.removeAt(i);
        this.formarraylength--;
        // console.log(this.showplus[i]);
        this.showplus.splice(i,  1);
        this.showcross.splice(i,  1);
        if (this.showcross.length === 1) {
            this.showcross[0]  =  true;
        }

        if  (this.showplus.indexOf(false) === -1) {
        this.showplus[this.showplus.length - 1 ]  =  false;

        }
    // console.log(this.showplus)

    }

}

    getPaymentExternalMappingList(paymentTermsForm) {
       return paymentTermsForm.get('externalMappingCollection').controls;
    }
    getDropDown(): void {
        this.revocabledata = [];
        this.atsightdata = [];
        this.confirmeddata = [];
         this.paymentTermsService.externalSystemdata = [];

       this.paymentTermsService.getPermissionDropDownJSON().subscribe(paymenttermsDetails => {
            this.paymentTermsService.basepaymentcode = paymenttermsDetails.body.basePaymentTermValues;
           this.basecodedata =  paymenttermsDetails.body.basePaymentTermValues;
            this.basisdata =  paymenttermsDetails.body.paymentBasisRefValues;
        /*paymenttermsDetails.body.basePaymentTermValues.forEach(role => {
             this.basecodedata.push({ label: role.value, value: role.id });

        });*/
        paymenttermsDetails.body.atSightUsanceValues.forEach(role => {
             this.atsightdata.push({ label: role.value, value: role.id });
             });

         paymenttermsDetails.body.revocableIrrevocableValues.forEach(role => {
             this.revocabledata.push({ label: role.value, value: role.id });
        });
         paymenttermsDetails.body.confirmedNonconfirmedValues.forEach(role => {
             this.confirmeddata.push({ label: role.value, value: role.id });
              console.log(role.value + ' ' + role.id)

        });

         paymenttermsDetails.body.externalSystemRefValues.forEach(role => {
             this.paymentTermsService.externalSystemdata.push({ label: role.value, value: role.id });

        });
      },
    error => { console.log(' server error ' + error);//throw error;
               }
      );
   }
clear() {
 //   this.ngOnInit();
    this.initPaymentForm();
    this. addExternalMapping() ;
  
}
    savedata() {
        if (this.addpage ) {
            this.addoreditpage = true;
        } else if (this.editpage) {
            this.addoreditpage = false;
        }
        this.sumbitstatus = 'draft';
        this.paymentTermsForm.value.updateStatusName = 'Draft';
         this.paymentTermsForm.value.statusName = 'Draft';
        this.addPaymentTerms();

    }
    saveandsubmit() {
        if (this.addpage ) {
            this.addoreditpage = true;
        } else if (this.editpage) {
            this.addoreditpage = false;
        }
        this.sumbitstatus = 'save';
        this.paymentTermsForm.value.updateStatusName = 'Active';
        this.paymentTermsForm.value.statusName = 'Active';
        this.addPaymentTerms();

    }
    saveactive() {
        this.addoreditpage = false;
        this.paymentTermsForm.value.updateStatusName = 'Active';
        this.paymentTermsForm.value.statusName = 'Active';
        this.sumbitstatus = 'Active';
        this.addPaymentTerms() ;
    }
    submitactivetodeactive() {
        this.addoreditpage = false;
        this.paymentTermsForm.value.updateStatusName = 'InActive';
        this.paymentTermsForm.value.statusName = 'InActive';
        this.sumbitstatus = 'InActive';
         this.addPaymentTerms() ;
       /* this.payment_delete(this.paymentTermsForm.value.paymentTermId,
            this.paymentTermsForm.value.paymenttermName, 'Active')*/

    }
    savedeactive() {
        this.addoreditpage = false;
        this.paymentTermsForm.value.updateStatusName = 'InActive';
        this.paymentTermsForm.value.statusName = 'none';
        this.sumbitstatus = 'InActive';
         this.addPaymentTerms() ;
    }
    submitdeactivetoreactive() {
        this.addoreditpage = false;
        this.paymentTermsForm.value.updateStatusName = 'Active';
        this.paymentTermsForm.value.statusName = 'Active';
        this.sumbitstatus = 'Active';
         this.addPaymentTerms() ;
    }



    addPaymentTerms() {
        // console.log( ' add part save' + JSON.stringify( this.paymentTermsForm.value.statusName));

        if (this.isValidForm()) {
            this.paymentTermsService.savePaymentTerms(this.paymentTermsForm.value,
                this.sumbitstatus, this.addoreditpage).subscribe(permissionGroupDetails => {

              if ( this.paymentTermsForm.value.statusName === 'Draft' ) {
                   this.router.navigate([paymentterm], { queryParams: { 'success': 1 } });
              } else  if ( this.paymentTermsForm.value.statusName === 'Active' ) {
                   this.router.navigate([paymentterm], { queryParams: { 'success': 2 } });
              }else  if ( this.paymentTermsForm.value.statusName === 'InActive' ) {
                   this.router.navigate([paymentterm], { queryParams: { 'success': 3 } });
              }
            },
            error => {
                  //  this.errorMessageForPaymentterm = this.paymentTermsService.errorMessage;
                     this.errorMsg = error;
                this.errorModalList();
                setTimeout(() => {this.hideModal(); }, 1000);  
                   // this.showIsError();
                   // alert(this.paymentTermsService.errorMessage)
                   // throw error;
            },
            () => console.log('Finished')
            )
        }
    }
    public errorModalList(): void {
    this.errormodalList = true;
  }
    isValidForm(): boolean {
         this.reqmsg = ''; this.checkdropdown = 0;
        if (this.paymentTermsForm.value.payTermCode  == null ) {
            this.req_termcode = true;
            this.checkdropdown++;

        }else  if (this.paymentTermsForm.value.payTermCode  != null ) {
            if (this.paymentTermsForm.value.payTermCode.trim() === '' ) {
                this.req_termcode = true;
                this.checkdropdown++;
            } else if (this.paymentTermsForm.value.payTermCode.trim().length > 20) {
                this.reqmsgcode += 'Payment Term Code maximum length is 20 and below ';
                this.req_termcode = true;
                this.checkdropdown++;
            } else {
                this.req_termcode  = false;
            }
        }
        if ( this.paymentTermsForm.value.payTermName  == null ) {
            this.req_termname = true;
            this.checkdropdown++;

        }else if (this.paymentTermsForm.value.payTermName != null  ) {

            if (this.paymentTermsForm.value.payTermName.trim() === '' ) {
                this.req_termname = true;
                this.checkdropdown++;
            } else if (this.paymentTermsForm.value.payTermName.trim().length > 200) {
                this.reqmsgname = 'Payment Term Name maximum length is 200 and below';
                this.req_termname = true;
                this.checkdropdown++;
            } else {
                this.req_termname  = false;
            }
        }

        if (this.lccheck ) {
              if (this.paymentTermsForm.value.atSightUsanceFlagId == null
                    || this.paymentTermsForm.value.atSightUsanceFlagId === 'Select' ) {
                this.req_atsight = true;
                this.checkdropdown++;
            }else {
                 this.req_atsight = false;
            }
            if (this.paymentTermsForm.value.revocableIrrevocableFlagId == null
                    || this.paymentTermsForm.value.revocableIrrevocableFlagId === 'Select' ) {
                this.req_revocable = true;
                this.checkdropdown++;
            }else {
                 this.req_revocable = false;
            }
            if (this.paymentTermsForm.value.confirmedNonconfirmedFlagId == null
                || this.paymentTermsForm.value.confirmedNonconfirmedFlagId === 'Select' ) {
                this.req_confirmation = true;
                this.checkdropdown++;
            }else {
                 this.req_confirmation = false;
            }

        } else {
            this.paymentTermsForm.value.atSightUsanceFlagId = null;
            this.paymentTermsForm.value.revocableIrrevocableFlagId = null;
            this.paymentTermsForm.value.confirmedNonconfirmedFlagId = null;
        }

         if (this.paymentTermsForm.value.paymentBasisRefId == null
                || this.paymentTermsForm.value.paymentBasisRefId === 'Select' ) {
                this.req_basis = true;
                this.checkdropdown++;
        } else {
                 this.req_basis  = false;
        }
        if (this.paymentTermsForm.value.dueDays  == null ) {
            this.req_dueday = true;
            this.checkdropdown++;
        }else {
                this.paymentTermsForm.value.dueDays = this.Math.round(this.paymentTermsForm.value.dueDays);
                 this.req_dueday  = false;
        }
        if (this.paymentTermsForm.value.contingencyDays  == null ) {
            this.req_contingencyDays = true;
            this.checkdropdown++;
        } else {
           this.paymentTermsForm.value.contingencyDays = this.Math.round(this.paymentTermsForm.value.contingencyDays);
            this.req_contingencyDays  = false;
        }

        if ( this.paymentTermsForm.value.externalMappingCollection != null) {
            const sizerollemapping = Number( JSON.stringify(this.paymentTermsForm.value.externalMappingCollection.length));
         this.paymentterms = this.paymentTermsForm.value;
         this.checkingexternalduplicate = 0;
        for (let i = 0; i <  sizerollemapping ; i++) {

                 for (let j = i + 1 ; j <  sizerollemapping ; j++) {
                    console.log('// check duplicate role mapping' );
                    if (this.paymentterms.externalMappingCollection[i].externalSystemRefId ===
                    this.paymentterms.externalMappingCollection[j].externalSystemRefId  &&
                        this.paymentterms.externalMappingCollection[i].type ===
                        this.paymentterms.externalMappingCollection[j].type &&
                        this.paymentterms.externalMappingCollection[i].mapping ===
                        this.paymentterms.externalMappingCollection[j].mapping ) {
                    console.log('// duplicate occured in role mapping')
                    this.checkdropdown++;
                    this.checkingexternalduplicate++;
                    }

              }


            }
        }
        if (this.checkingexternalduplicate !== 0 ) {
             this.errorMessageForPaymentterm = ' External System Mapping must be Unique ' ;
             this.showIsError();
            }

            if (this.checkdropdown !== 0) {
                this.showModal();
                setTimeout(() => {this.hideModal();  }, 3000);
                return false;
            } else {
                 return true;
            }


     }
    getStyletermcode() {
         if (this.req_termcode === true) {
           return '#d43c3c';
        } else {
        return '#cfdee7';
            }

    }
    getStyletermname() {
         if (this.req_termname === true) {
           return '#d43c3c';
        } else {
        return '#cfdee7';
            }

    }
    getStyletermbasis() {
         if (this.req_basis === true) {
           return '#d43c3c';
        } else {
        return '#cfdee7';
            }

    }
    getStyletermdueday() {
         if (this.req_dueday === true) {
           return '#d43c3c';
        } else {
        return '#cfdee7';
            }

    }
    getStyletermcontingencyday() {
         if (this.req_contingencyDays === true) {
           return '#d43c3c';
        } else {
        return '#cfdee7';
            }

    }
    getStyletermatsight() {
         if (this.req_atsight === true) {
           return '#d43c3c';
        } else {
        return '#cfdee7';
            }

    }
    getStyletermrevocable() {
         if (this.req_revocable === true) {
           return '#d43c3c';
        } else {
        return '#cfdee7';
            }

    }
    getStyletermconfirm() {
         if (this.req_confirmation === true) {
           return '#d43c3c';
        } else {
        return '#cfdee7';
            }

    }

    onchange(event:  Event) {
       // console.log(event + JSON.stringify(this.paymentTermsForm.value)  +  JSON.stringify(this.basecodedata))
        this.basecodedata.forEach(role => {
             if (this.paymentTermsForm.value.paymentTermBaseId === role.id.toString() ) {
                if (role.code === 'Y')      {
                    this.lccheck = true;
                } else {
                    this.lccheck = false;
            }
          }});

    }
    
    numberonly(evt) {
         const  charCode = (evt.which) ? evt.which : evt.keyCode;
          if (charCode !== 46 && charCode > 31 && (charCode < 48 || charCode > 57)) {
             return false;
          }
          return true;
    }
    closeIsError() {
        this.hideIsErrorModal();
    }
    backtolist() {
    this.router.navigate([paymentterm]);
}

focusOutFunction1() {
      return this.Math.round(this.paymentTermsForm.value.dueDays);
}
focusOutFunction() {
   return this.Math.round(this.paymentTermsForm.value.contingencyDays);
}

payment_delete( id , name , status ) {
    this.status.isFirstOpen = true;
    this.paymentTermId = id;
    this.paymentTermsService.paymenttermName = name;
    this.status = status; 
    if (this.status === 'Draft') {
      this.isDraftStatus = true;
    }else if (this.status === 'Active') {
        this.isActiveStatus = true;
    }
    this.showisDeletePopupModal();
    return false;
  }

   conf_delete(event) {
   /*  const param = {'id': this.paymentTermId};
     const otherarray = [];
     otherarray.push(param);
     const paramobj = { 'paymentTermIds' : [] };
     this.hideIsErrorModal();
     paramobj.paymentTermIds = otherarray;*/
     // this.status.isFirstOpen = true;
     
     this.addPaymentTerms() ;

}

  closedeletepopup()  {
     this.hideIsErrorModal();
    }

  public showModal(): void {
    this.errormodal = true;
  }

  

  public hideModal(): void {
    this.deletesuccessModal.hide();
     this.errormodalList = false;
  }
 
  public onHidden(): void {
    this.errormodal = false;
  }

  public showIsError(): void {
    this.isError = true;
  }

  public hideIsErrorModal(): void {
    this.successModal.hide();
  }
 
  public onHiddenIsError(): void {
    this.isError = false;
  }

    public showisDeletePopupModal(): void {
    this.isDeletePopupModal = true;
  }

  public onHiddenisDeletePopupModal(): void {
    this.isDeletePopupModal = false;
  }
}
