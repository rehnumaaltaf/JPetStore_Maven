import { Component, OnInit , OnDestroy , NgModule , ViewChild } from '@angular/core';
import { FormGroup , FormGroupName, FormControl, Validators, FormArray, FormBuilder,
              ReactiveFormsModule , FormsModule} from '@angular/forms';
import { ProductGradeService } from '../service/product-grade.service';
import { SelectItem } from '../../../../shared/interface/selectItem';
import { colorcodeblue , colorcodered } from '../../../../shared/interface/colorcode';
import { Router , ActivatedRoute} from '@angular/router';
import { Grade, OriginList, active, draft, inactive, radioNo , radioYes , washedtype ,
    draftsuccess , activesuccess , save, update, inactivesuccess , deactive , reactivate  } from '../grade-model';
import { AccordionModule , ModalDirective } from 'ngx-bootstrap';
import { productgrade } from '../../../../shared/interface/router-links';
import { Subscription } from 'rxjs/Subscription';
import { CanComponentDeactivate } from '../../../../shared/guards/can-deactivate-guard';
import { msgsuccess , msgupdate , msgactivateorsubmit , msgdelete , msgdeactivate , msgreactivate , msgsave
          ,  msgconfirmdeactivate , msgconfirmdelete ,  msgconfirmreactive } from '../common-messages';

@Component({
  selector: 'app-add-product-grade',
  templateUrl: './add-product-grade.component.html',
  styleUrls: ['./add-product-grade.component.css']
})
export class AddProductGradeComponent implements OnInit , OnDestroy , CanComponentDeactivate {
  @ViewChild('successModal') public successModal: ModalDirective;
   @ViewChild('deletesuccessModal') public errorModal: ModalDirective;
   @ViewChild('serverErrorModal') public serverErrorModal: ModalDirective;
   @ViewChild('deletePopUpModal') public deletePopUpModal1: ModalDirective;
   @ViewChild('duplicatemodel') public deletesuccessModal: ModalDirective;
  public status: any = {
    isFirstOpen: true,
    isFirstDisabled: false
  };
   showModal: boolean;
   subscription: Subscription;
    gradeForm: FormGroup;
    productGrade: Grade = new Grade();
    myOptions: any;
    uomlist: SelectItem[] ;
    productlist: SelectItem[];
    brandlist: SelectItem[];
    gradeGrouplist: SelectItem[];
    currencyList: SelectItem[];
    processtypelist: SelectItem[];
    processsubtypelist: SelectItem[];
    icoindexlist: SelectItem[];
    originlist: OriginList[];
    baseGradelist: SelectItem[];
    checkdropdown: number;
    isBlended: string;
    errorMsg: string;
    errormodal1: boolean;
    req_gradecode: boolean;
    req_gradename: boolean;
    req_product: boolean;
    req_gradegroup: boolean;
    req_originlist: boolean;
    req_hedgeratio: boolean;
    isbranddefault: string;
    req_isblend: boolean;
    req_isbase: boolean;
    brandcheck: boolean;
    basegradecheck: boolean;
    req_gradebrand: boolean;
    req_prosuptype: boolean;
    processingsubtype: boolean;
    req_basegradename: boolean;
    radioNo: string;
    radioYes: string;
    editpage: boolean;
    addpage: boolean;
    draft: boolean;
    active: boolean ;
    inactive: boolean;
    deactiveStatus: boolean ;
    isActiveStatus: boolean;
    deleteSuccessModal: Boolean = false;
    addoreditpage: boolean;
    errorMessageForGrade: string;
    checkingexternalduplicate: number;
    checkingIntlduplicate: number;
    editgradeId: Number;
    pkProductProcessId: number;
    processingSubTypeId: number;
    productId: number;
    brandId: number;
    req_premiumOrDiscount: boolean;
    req_hedgeratiolimit: boolean;
    isActionPerformed: boolean;
    statusmsg: string;
    isDeletePopupModal: boolean;
    gradeName: string;
   constructor(private fb: FormBuilder , private productGradeService: ProductGradeService , private router: Router,
        private route: ActivatedRoute  ) { }
canDeactivate(): boolean {
      return this.isActionPerformed;
  }
  radioInit() {
    this.isBlended =  radioNo.toString();
    this.productGrade.isBlended = radioNo.toString();
    this.productGrade.isBrand =  radioNo.toString();
    this.productGrade.isTenderable = radioNo.toString();
    this.productGrade.isBase = radioNo.toString();
    this.productGrade.gradeIsRaw = radioNo.toString();
  }
  errorflagInit() {
      this.isActionPerformed = false;
    this.brandcheck = false;
    this.basegradecheck = true;
    this.req_gradecode = false;
    this.req_gradename = false;
    this.req_product = false;
    this.req_gradegroup = false;
    this.req_originlist = false;
    this.req_hedgeratio = false;
    this.req_isblend = false;
    this.req_isbase = false;
    this.req_gradebrand = false;
    this.req_prosuptype = false;
    this.processingsubtype = false;
    this.req_basegradename = false;
    this.req_hedgeratiolimit = false;
    this.req_premiumOrDiscount = false;
    this.deactiveStatus = false;
    this.isDeletePopupModal = false;
    this.isActiveStatus = false;

  }

  ngOnInit() {
      this.gradeName = null;
       this.editgradeId = this.route.snapshot.params['gradeId'];
        this.radioNo = radioNo.toString();
        this.radioYes = radioYes.toString();
        this.editpage = false;
        this.addpage = true;
        this.getDropDown();
        this.radioInit();
        this.errorflagInit();
        if (this.editgradeId != null) {
            this.productGrade = new Grade();
            this.initPaymentForm();
          this.productGradeService.getProductGradeById(this.editgradeId).
           subscribe(gradeDetails => {
               this.productGrade = gradeDetails.body ;
             console.log( 'Product ' + JSON.stringify(this.productGrade));
             console.log( 'Product ' + this.productGrade.product.prodId)

            // if (  this.productGradeService.errorStatusCode === '200' ) {
                this.editpage = true;
              this.addpage = false;
              this.productGrade = gradeDetails.body;
              console.log('Edit page');
              this.editProductGrade();
                if (this.productGrade.statusName.toLowerCase() === draft.toString().toLowerCase()) {
                    this.draft = true;
                    this.active = false;
                    this.inactive = false;
                } else if (this.productGrade.statusName.toLowerCase() === active.toString().toLowerCase()) {
                    this.active = true;
                    this.draft = false;
                    this.inactive = false;
                } else if (this.productGrade.statusName.toLowerCase() === inactive.toString().toLowerCase()) {
                    this.inactive = true;
                    this.active = false;
                    this.draft = false;
                }
            //  } else {
            //     alert( 'There is no record ') ;
            //  }

       },
         error => { throw error;
               }

        );
        this.editgradeId = null;

     }else {
        this.editpage = false;
        this.addpage = true;
        this.initPaymentForm();
        this.addGradeIntlMapping();
        this.addExternalMapping();
     }
  }
  editProductGrade() {
       this.initPaymentForm();
    //    this.gradeForm.controls['gradeCode'].patchValue(this.productGrade.gradeCode);
    //     this.gradeForm.controls['gradeName'].setValue(this.productGrade.gradeCode);
   // this.gradeForm.value.gradeCode = this.productGrade.gradeCode;
    if ( this.productGrade.product != null ) {
            if ( this.productGrade.product.prodId != null ) {
            this.productId = this.productGrade.product.prodId ;
            this.getProcessingTypes( this.productId )
            }
        }
        if ( this.productGrade.processingType != null ) {
            if ( this.productGrade.processingType.pkProductProcessId != null ) {
            this.pkProductProcessId = this.productGrade.processingType.pkProductProcessId ;
            if ( this.productGrade.processingType.processingName.toLowerCase() === washedtype.toLowerCase() ) {
                    this.processingsubtype = true;
                } else {
                      this.processingsubtype = false;
                }
            }
        }
        if ( this.productGrade.processingSubType != null ) {
           if ( this.productGrade.processingSubType.processSubTypeRefId != null ) {
                    this.processingSubTypeId = this.productGrade.processingSubType.processSubTypeRefId ;
            }
        }
     if ( this.productGrade.brand != null ) {
            if ( this.productGrade.brand.brandId != null ) {
            this.brandId =  this.productGrade.brand.brandId;
            this.brandcheck = true;
            }
        }
        if ( this.productGrade.isBase === radioYes ) {
           this.basegradecheck = false;
        } else {
             this.basegradecheck = true;
        }
        const sizerollemapping = Number( JSON.stringify(this.productGrade.externalSystemList.length));

        if (sizerollemapping >= 1 ) {
            for (let i = 0; i <  sizerollemapping ; i++) {
                    this.addExternalMapping();
            }
        } else {
            this.addExternalMapping();
        }
         const sizerollemappingin = Number( JSON.stringify(this.productGrade.intlGradeList.length));
        if (sizerollemappingin >= 1 ) {
            for (let i = 0; i <  sizerollemappingin ; i++) {
                    this.addGradeIntlMapping();
            }
        } else {
             this.addGradeIntlMapping();
        }


  }
  ngOnDestroy() {
    // this.subscription.unsubscribe();
}
 backtolist() {
    this.router.navigate([productgrade]);
}
   initPaymentForm(): void {
      this.gradeForm = this.fb.group({
          'action': [''],
          'gradeId': [] ,
          'gradeCode': [ this.productGrade.gradeCode],
          'gradeName': ['fdsg'],
          'gradeFullName': [''],
          'product':  this.fb.group({'prodId': ['']} ),
          'gradeGroupId': [''],
          'originList' : [''],
          'isBlended': [''],
          'isBrand': [''],
          'brand': this.fb.group({ 'brandId': [''] } ),
           'isBase': [''],
           'baseGradeId': [''],
           'processingType':  this.fb.group({ 'pkProductProcessId': [''] } ),
           'processingSubType': this.fb.group({ 'processSubTypeRefId': [''] } ),
           'icoIndexId': [''],
           'gradeIsRaw': [''],
           'isTenderable': [''],
           'hedgeRatio': [''],
           'premiumOrDiscount': [''],
           'currencyId': [''],
           'uomId': [''],
          'statusName': [],
          'intlGradeList': this.fb.array([]),
          'externalSystemList': this.fb.array([]),
          'deletedIntMappings': this.fb.array([
              ]),
              'deletedExternalMappings': this.fb.array([
              ])
        });
   }
   addGradeIntlMapping() {
        const control = <FormArray>this.gradeForm.controls['intlGradeList'];
        const addrCtrl =   this.fb.group({
            'intlGradeMappingId': [],
            'intlId': [''],
            'intlCode': [''],
            'intlDesc': [''] });
        control.push(addrCtrl);
    }

    removeGradeIntlMapping(i: number) {
        const control = <FormArray>this.gradeForm.controls['intlGradeList'];
        if (this.gradeForm.value.intlGradeList != null ) {
           //  alert(this.paymentterms.externalMappingCollection[i].mappingId)
                if (this.gradeForm.value.intlGradeList[i].intlGradeMappingId != null  &&
                     this.gradeForm.value.intlGradeList[i].intlGradeMappingId !== undefined ) {
                  this.gradeForm.value.deletedIntMappings.push(this.gradeForm.value.intlGradeList[i].intlGradeMappingId);
                 this.gradeForm.value.intlGradeList.splice(i, 1);
                }
            }
        control.removeAt(i);
        if (control.length === 0 ) {
             this.addGradeIntlMapping();
        }
    }

     addExternalMapping() {
        const control = <FormArray>this.gradeForm.controls['externalSystemList'];
        const addrCtrl =   this.fb.group({
            'externalSystemMappingId': [],
            'externalSystemId': [''],
            'attribute1': [''],
            'attribute2': [''] });
        control.push(addrCtrl);
    }

    removeExternalMapping(i: number) {
        const control = <FormArray>this.gradeForm.controls['externalSystemList'];
       if (this.gradeForm.value.externalSystemList != null ) {
           //  alert(this.paymentterms.externalMappingCollection[i].mappingId)
                if (this.gradeForm.value.externalSystemList[i].intlGradeMappingId != null  &&
                     this.gradeForm.value.externalSystemList[i].intlGradeMappingId !== undefined ) {
                  this.gradeForm.value.deletedIntMappings.push(this.gradeForm.value.externalSystemList[i].externalSystemMappingId);
                 this.gradeForm.value.externalSystemList.splice(i, 1);
                }
            }
         control.removeAt(i);
        if (control.length === 0 ) {
             this.addExternalMapping();
        }
    }

   getIntlGrade(gradeForm) {
    return gradeForm.get('intlGradeList').controls;
  }
   getExternalSysGrade(gradeForm) {
    return gradeForm.get('externalSystemList').controls;
  }

 savedata() {

        if (this.addpage ) {
            this.addoreditpage = true;
            this.productGradeService.msgStatusName = save.toString();
        } else if (this.editpage) {
             this.productGradeService.msgStatusName = update.toString();
            this.addoreditpage = false;
        }
        this.gradeForm.value.statusName = draft.toString();
         this.gradeForm.value.action = draft.toString();
      this.saveProductGrade();

    }
    drafttoactive() {
         this.addoreditpage = false;
        this.productGradeService.msgStatusName = active.toString();
        this.gradeForm.value.statusName = active.toString();
      this.gradeForm.value.action = active.toString();
      this.saveProductGrade();
    }
     saveactive() {
        if (this.addpage ) {
            this.productGradeService.msgStatusName = active.toString();
            this.addoreditpage = true;
        } else if (this.editpage) {
               this.productGradeService.msgStatusName = update.toString();
            this.addoreditpage = false;
        }
      this.gradeForm.value.statusName = active.toString();
      this.gradeForm.value.action = active.toString();
      this.saveProductGrade();

    }
    submitactivetodeactive() {
        this.addoreditpage = false;
        this.gradeName = this.gradeForm.value.gradeName;
        this.productGradeService.msgStatusName = deactive.toString();
        this.gradeForm.value.statusName = inactive.toString();
        this.isActiveStatus = true;
        this.statusmsg = msgconfirmdeactivate.toString();
        this.isDeletePopupModal = true;
    }
    savedeactive() {
      this.addoreditpage = false;
       this.productGradeService.msgStatusName = update.toString();
      this.gradeForm.value.statusName = inactive.toString();
      this.saveProductGrade();
    }
    submitdeactivetoreactive() {
      this.addoreditpage = false;
      this.gradeName = this.gradeForm.value.gradeName;
      this.productGradeService.msgStatusName = reactivate.toString();
      this.gradeForm.value.statusName = active.toString();
      this.deactiveStatus = true;
       this.statusmsg = msgconfirmreactive.toString();
      this.isDeletePopupModal = true;
    }

    // pop confirm active to deactive and deactive to active
    conf_deactive_reactive ( event) {
       this.deactiveStatus = false;
       this.isActiveStatus = false;
       this.deletePopUpModal1.hide();
       this.saveProductGrade();
    }
    hide() {
         this.isDeletePopupModal = false;
    }

  saveProductGrade() {
      this.gradeForm.value.gradeId = this.productGrade.gradeId;
      this.isActionPerformed = true;
      if (this.isValidForm()  ) {
            this.productGradeService.saveProductGrade(this.gradeForm.value ,
                 this.addoreditpage).subscribe(permissionGroupDetails => {
                     this.router.navigate([productgrade]);
            },
            error => {
                    //this.errorMessageForGrade = this.productGradeService.errorMessage;
                    //this.showIsError();
                   // alert(this.paymentTermsService.errorMessage)
                   // throw error;
                   this.errorMsg = error;
                    this.errorModal1();
                   setTimeout(() => {this.hideModal();  }, 3000);
            },
            () => console.log('Finished')
            )
        }
  }

  getDropDown() {
      this.getDropDownUOMCurrency();
      this.getDropDownBrandProductGroup();
      this.getExternalSystem();
      this.getBaseGrade();

  }

  getDropDownUOMCurrency(): void {
       this.productGradeService.getGradeDropDownUOMCurrency().subscribe(dropDownDetails => {
        this.uomlist = [];
        this.currencyList = [];
        dropDownDetails.body.uomList.forEach(list => {
            if ( list.statusName != null) {
                if (list.statusName.toUpperCase() === active.toUpperCase() ) {
                 this.uomlist.push({  label: list.uomName, value: list.uomId });
            }
            }

        });
         dropDownDetails.body.currencyList.forEach(list => {
            if ( list.fkStatusId === 1 ) {
                this.currencyList.push({ label: list.currencyCode, value: list.pkCurrencyId });
            }

        });
      },
    error => { console.log(' server error ' + error); // throw error;
               }
      );
   }

     getBaseGrade(): void {
        this.baseGradelist = [];
           if ( this.productGradeService.productGradeDetails != null) {
               this.productGradeService.productGradeDetails.forEach(list => {
             if ( list.statusName != null) {
                if (list.statusName.toUpperCase() === active.toString().toUpperCase() ) {
                    this.baseGradelist.push({  label: list.gradeName, value: list.gradeId });
                }
            }
        });
         } else {
             this.subscription = this.productGradeService.getProductGradeViewList().subscribe(productGrade => {
              this.productGradeService.productGradeDetails = productGrade.body;
               this.productGradeService.productGradeDetails.forEach(list => {
             if ( list.statusName != null) {
                if (list.statusName.toUpperCase() === active.toString().toUpperCase() ) {
                    this.baseGradelist.push({  label: list.gradeName, value: list.gradeId });
                }
             }
        });

   }, error => {
      console.log('Error Loading Grade Listing: ' + <any>error)
      });
         }
   }

   getDropDownBrandProductGroup(): void {
       this.productGradeService.getGradeDropDownProdGroupingBrand().subscribe(dropDownDetails => {
        this.productlist = [];
        this.brandlist = [];
        this.gradeGrouplist = [];
        this.productGradeService.intlcodetypelist = [];
        if (dropDownDetails.body != null) {

        dropDownDetails.body.productList.forEach(list => {
               if (list.statusName === active) {
                this.productlist.push({  label: list.prodName, value: list.prodId });
            }

        });
         dropDownDetails.body.brandList.forEach(list => {
              if ( list.statusName != null) {
                if (list.status.toUpperCase() === active.toUpperCase() ) {
                this.brandlist.push({ label: list.brandName, value: list.brandId });
                }
              }
        });
        dropDownDetails.body.gradeGroupList.forEach(list => {
                this.gradeGrouplist.push({ label: list.gradeGroupName, value: list.gradeGroupId });
        });
        dropDownDetails.body.intlCodeTypeList.forEach(list => {
                this.productGradeService.intlcodetypelist.push({ label: list.intlCodeTypeName, value: list.typeRefId });
        });
        }
      },
    error => { console.log(' server error ' + error); // throw error;
               }
      );
   }
   getExternalSystem(): void {
       this.productGradeService.getExternalSystem().subscribe(dropDownDetails => {
        this.productGradeService.externallist = [];
        dropDownDetails.body.forEach(list => {
             this.productGradeService.externallist.push({  label: list.externalSystemRefName, value: list.externalSystemRefId });

        });
        },
    error => { console.log(' server error ' + error); // throw error;
               }
      );
   }

   getProcessingTypes( id ) {
     this.processtypelist = [];
           this.processsubtypelist = [];
           this.icoindexlist = [];
           this.productGradeService.getProcessingTypeBasedOnProduct( id).subscribe(dropDownDetails => {
            if ( dropDownDetails.body.processingName != null) {
                this.processtypelist.push({  label: dropDownDetails.body.processingName, value: dropDownDetails.body.processMappingId });
            }
            /* dropDownDetails.body.processtypeList.forEach(list => {
                this.processtypelist.push({  label: list.processingName, value: list.processMappingId });
                alert(list.processMappingId)
            });*/
            if ( dropDownDetails.body.processingSubType != null) {
                 dropDownDetails.body.processingSubType.forEach(list => {
                  this.processsubtypelist.push({ label: list.subTypeName, value: list.processSubTypeRefId });
                });
            }
         if ( dropDownDetails.body.icoName != null) {
           this.icoindexlist.push({ label:  dropDownDetails.body.icoName, value:  dropDownDetails.body.icoMappingId });
            }

        /*dropDownDetails.body.icoList.forEach(list => {
                  this.icoindexlist.push({ label: list.icoName, value: list.icoMappingId });
            });*/
        },
    error => { console.log(' server error ' + error); // throw error;
               }
      );

   }
   onProductChange() {
       this.req_product = false;
        if (this.gradeForm.value.product.prodId != null) {
          this.getProcessingTypes(this.gradeForm.value.product.prodId);
       }
       this.getStyleproduct();

   }
   changegradegroup() {
       this.req_gradegroup = false;
     if (this.gradeForm.value.gradeGroupId != null) {
         this.originlist = [];
           this.productGradeService.getOriginBasedOnGradeGroup(this.gradeForm.value.gradeGroupId).subscribe(dropDownDetails => {
               this.originlist = dropDownDetails.body;
        },
    error => { console.log(' server error ' + error); // throw error;
               }
      );

       }
       this.getStylegradegroup();
   }
   changeProcessingType() {
        this.processtypelist.forEach(type => {
            if (type.value.toString() === this.gradeForm.value.processingType.pkProductProcessId) {
                if ( type.label.toLowerCase() === washedtype.toString().toLowerCase() ) {
                    this.processingsubtype = true;
                } else {
                    this.processingsubtype = false;
                }
            }
        });
   }

    isValidForm(): boolean {
        this.checkdropdown = 0;
          if (this.gradeForm.value.gradeCode  == null ) {
            this.req_gradecode = true;
            this.checkdropdown++;

        }else  if (this.gradeForm.value.gradeCode  != null ) {
            if (this.gradeForm.value.gradeCode.trim() === '' ) {
                this.req_gradecode = true;
                this.checkdropdown++;
            } else if (this.gradeForm.value.gradeCode.trim().length > 20) {
             //   this.reqmsgcode += 'Payment Term Code maximum length is 20 and below ';
                this.req_gradecode = true;
                this.checkdropdown++;
            } else {
                this.req_gradecode  = false;
            }
        }
        if ( this.gradeForm.value.gradeName  == null ) {
            this.req_gradename = true;
            this.checkdropdown++;

        }else if (this.gradeForm.value.gradeName != null  ) {

            if (this.gradeForm.value.gradeName.trim() === '' ) {
                this.req_gradename = true;
                this.checkdropdown++;
            } else if (this.gradeForm.value.gradeName.trim().length > 200) {
               // this.reqmsgname = 'Payment Term Name maximum length is 200 and below';
                this.req_gradename = true;
                this.checkdropdown++;
            } else {
                this.req_gradename  = false;
            }
        }
        if ( this.gradeForm.value.gradeFullName  != null ) {
            this.gradeForm.value.gradeFullName = this.gradeForm.value.gradeFullName.trim();
        }

        if (this.gradeForm.value.product.prodId == null) {
                this.req_product = true;
                this.checkdropdown++;
            }else {
                 this.req_product = false;
            }
        if (this.gradeForm.value.gradeGroupId == null ) {
                this.req_gradegroup = true;
                this.checkdropdown++;
            }else {
                 this.req_gradegroup = false;
            }
             if (this.gradeForm.value.originList == null ) {
                this.req_originlist = true;
                this.checkdropdown++;
            }else {
                 this.req_originlist = false;
            }
            if (this.gradeForm.value.hedgeRatio == null ) {
                this.req_hedgeratio = true;
                this.checkdropdown++;
            } else if (this.gradeForm.value.hedgeRatio != null ) {

                if ( ! Number(this.gradeForm.value.hedgeRatio)) {
                        this.req_hedgeratio = true;
                        this.checkdropdown++;
                }
            }else {
                 this.req_hedgeratio = false;
                 this.req_hedgeratiolimit = false;
            }
            if (this.gradeForm.value.isBlended == null ) {
                this.req_isblend = true;
                this.checkdropdown++;
            }else {
                 this.req_isblend = false;
            }
             if (this.gradeForm.value.isBase == null ) {
                this.req_isbase = true;
                this.checkdropdown++;
            } else if (this.gradeForm.value.isBase != null) {
                if (this.gradeForm.value.isBase === radioNo.toString() ) {
                      if ( this.gradeForm.value.baseGradeId == null ) {
                          this.req_basegradename = true;
                        this.checkdropdown++;
                      } else {
                         this.req_basegradename = false;
                      }
                }
            } else {
                 this.req_isbase = false;
            }

             if (this.gradeForm.value.isBrand === radioYes ) {
                 if ( this.gradeForm.value.brand.brandId == null ) {
                        this.req_gradebrand = true;
                        this.checkdropdown++;
                } else {
                    this.req_gradebrand = false;
                }
            }else {
                  this.req_gradebrand = false;
            }
             if (this.gradeForm.value.isBase === radioNo ) {
                 if ( this.gradeForm.value.brand.brandId == null ) {
                        this.req_isbase = true;
                        this.checkdropdown++;
                } else {
                    this.req_isbase = false;
                }
            }else {
                 this.req_isbase = false;
            }
             if (this.gradeForm.value.processSubTypeRefId != null ) {
                 if ( this.gradeForm.value.processSubTypeRefId == null ) {
                        this.req_prosuptype = true;
                        this.checkdropdown++;
                } else {
                    this.req_prosuptype = false;
                }
            }
            if ( this.gradeForm.value.externalSystemList != null) {
            const sizerollemapping = Number( JSON.stringify(this.gradeForm.value.externalSystemList.length));
         this.productGrade = new Grade();
         this.productGrade = this.gradeForm.value;
         this.checkingexternalduplicate = 0;
        for (let i = 0; i <  sizerollemapping ; i++) {

                 for (let j = i + 1 ; j <  sizerollemapping ; j++) {
                    console.log('// check duplicate external system mapping' );
                    if (this.productGrade.externalSystemList[i].externalSystemId ===
                    this.productGrade.externalSystemList[j].externalSystemId  &&
                        this.productGrade.externalSystemList[i].attribute1 ===
                        this.productGrade.externalSystemList[j].attribute1 &&
                        this.productGrade.externalSystemList[i].attribute2 ===
                        this.productGrade.externalSystemList[j].attribute2 ) {
                    console.log('// duplicate occured in external system mapping')
                    this.checkdropdown++;
                    this.checkingexternalduplicate++;
                }
              }


            }
        }
           if ( this.gradeForm.value.intlGradeList != null) {
            const sizerollemapping = Number( JSON.stringify(this.gradeForm.value.intlGradeList.length));
         this.productGrade = new Grade();
         this.productGrade = this.gradeForm.value;
         for (let i = 0; i <  sizerollemapping ; i++) {

                 for (let j = i + 1 ; j <  sizerollemapping ; j++) {
                    console.log('// check duplicate International code mapping' );
                    if (this.productGrade.intlGradeList[i].intlId ===
                    this.productGrade.intlGradeList[j].intlId  &&
                        this.productGrade.intlGradeList[i].intlCode ===
                        this.productGrade.intlGradeList[j].intlCode &&
                        this.productGrade.intlGradeList[i].intlDesc ===
                        this.productGrade.intlGradeList[j].intlDesc ) {
                    console.log('// duplicate occured in International code mapping')
                    this.checkdropdown++;
                    this.checkingexternalduplicate++;
                    }
              }
            }
        }
        if (this.checkingexternalduplicate !== 0 ) {
             this.errorMessageForGrade = 'Please check, International Code Or External System Mapping  must be Unique ' ;
             this.showIsError();
            }
            if (this.checkdropdown !== 0) {
                this.showMsgModal();
                setTimeout(() => {this.hideMsgModal();  }, 3000);
                return false;
            } else {
                 return true;
            }

    }
    numberonly(evt) {
        this.changeStyleratio();
        // if ( Number(this.gradeForm.value.hedgeRatio) < 0 || Number(this.gradeForm.value.hedgeRatio) > 100 ) {
        //          this.req_hedgeratiolimit = true;
        //  }
         const  charCode = (evt.which) ? evt.which : evt.keyCode;
          if (charCode !== 46 && charCode > 31 && (charCode < 48 || charCode > 57)) {
             return false;
          }
          return true;
    }
    blockspecialcharacter(e) {
    const key = document.all ? e.keyCode :  e.which;
       return ((key > 64 && key < 91) || (key > 96 && key < 123) || key === 95 ||
                 key === 45 || key === 8 || key === 32 || (key >= 48 && key <= 57));
    }

    brandchanged(event) {
        this.gradeForm.value.isBrand = event;
        if (event === radioYes) {
            this.brandcheck = true;
        } else {
            this.brandcheck = false;
        }
    }
     numberonlyPlussym(evt) {
         const  charCode = (evt.which) ? evt.which : evt.keyCode;
        if ( this.gradeForm.value.premiumOrDiscount != null ) {
             if (this.gradeForm.value.premiumOrDiscount === '' ) {
                  this.gradeForm.value.premiumOrDiscount = null;
              }
        }
        if ((this.gradeForm.value.premiumOrDiscount == null || this.gradeForm.value.premiumOrDiscount === undefined )
                   &&   charCode !== 43 && charCode !== 45 && charCode !== 46 && charCode > 31
                    && (charCode < 48 || charCode > 57)) {
            return false;
         } else if ( this.gradeForm.value.premiumOrDiscount != null) {
             if (charCode !== 46 && charCode > 31 && (charCode < 48 || charCode > 57)  ) {
             return false;
          } else {
               if ( this.gradeForm.value.premiumOrDiscount != null ) {
                     if (this.gradeForm.value.premiumOrDiscount.trim() === '' ) {
                        this.gradeForm.value.premiumOrDiscount = null
                    }
                }
              return true;
          }
         }
         return true;
    }
    basegradechanged(event) {
        this.req_isbase = false;
          this.gradeForm.value.isBase = event;
        if (event === radioNo) {
            this.basegradecheck = true;
        } else {
            this.basegradecheck = false;
        }
    }
    getStylecode() {

         if (this.req_gradecode === true) {
           return colorcodered;
        } else {
        return colorcodeblue;
            }

    }
    changeStylecode() {
        this.req_gradecode = false;
        this.getStylecode();
    }
    getStylename() {
         if (this.req_gradename === true) {
           return colorcodered;
        } else {
        return colorcodeblue;
            }
    }
     changeStylename() {
        this.req_gradename = false;
        this.getStylename();
     }
    getStyleproduct() {
         if (this.req_product === true) {
           return colorcodered;
        } else {
        return colorcodeblue;
            }
    }
    changeStyleorigin () {
        this.req_product = false;
        this.getStyleproduct();
    }
    getStylegradegroup() {
         if (this.req_gradegroup === true) {
           return colorcodered;
        } else {
        return colorcodeblue;
            }

    }
    getStyleorigin() {
         if (this.req_originlist === true) {
           return colorcodered;
        } else {
        return colorcodeblue;
            }

    }
    getStyleblend() {
         if (this.req_isblend === true) {
           return colorcodered;
        } else {
        return colorcodeblue;
            }

    }
    getStylebrandname() {
         if (this.req_gradebrand === true) {
           return colorcodered;
        } else {
        return colorcodeblue;
            }
    }
    changeStylebrandname() {
        this.req_gradebrand = false;
         this.getStylebrandname();
    }
    getStylebasename() {
         if (this.req_basegradename === true) {
           return colorcodered;
        } else {
        return colorcodeblue;
            }

    }
     changeStylebasename() {
        this.req_basegradename = false;
        this.getStylebasename();
    }
    getStyleratio() {
         if (this.req_hedgeratio === true) {
           return colorcodered;
        } else if (this.req_hedgeratiolimit === true) {
            return colorcodered;
        } else {
        return colorcodeblue;
            }

    }
     changeStyleratio() {
        this.req_hedgeratio = false;
        this.req_hedgeratiolimit = false;
        this.getStyleratio();
    }
    getStyleprocessingsubtype() {
         if (this.req_prosuptype === true) {
           return colorcodered;
        } else {
        return colorcodeblue;
            }

    }
     changeStyleprocessingsubtype() {
        this.req_prosuptype = false;
        this.getStyleprocessingsubtype();
    }
    public errorModal1():void {
    this.errormodal1 = true;
  }
  public hideModal():void {
    this.deletesuccessModal.hide();
    this.errormodal1 = false;
  }

     public showIsError(): void {
    this.serverErrorModal.show()
  }

  public hideIsErrorModal(): void {
    this.serverErrorModal.hide();
  }
   public showMsgModal(): void {
    this.errorModal.show()
  }

  public hideMsgModal(): void {
    this.errorModal.hide();
  }

   closeIsError() {
        this.hideIsErrorModal();
    }

    clear() {
        this.editpage = false;
        this.addpage = true;
        this.initPaymentForm();
        this.addGradeIntlMapping();
        this.addExternalMapping();
         this.radioInit();
        this.errorflagInit();
    }

}
