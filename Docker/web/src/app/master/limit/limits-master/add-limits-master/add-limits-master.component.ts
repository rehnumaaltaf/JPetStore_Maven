import { Component, OnInit, ViewEncapsulation, OnDestroy , ViewChild } from '@angular/core';
import { FormGroup, FormControl, FormArray, FormBuilder, ReactiveFormsModule, FormsModule } from '@angular/forms';
import { AddMultiLimitComponent } from './add-multi-limits-master';
import { Router, ActivatedRoute } from '@angular/router';
import { LimitsMaster, LimitDetails, LimitsAttribute, defaultSelectValue, Comments , inactive, active, draft , deactive, save, 
  update, reactivate , msgsuccess , msgupdate , msgactivateorsubmit , msgdelete , msgdeactivate , msgreactivate , msgsave
          ,  msgconfirmdeactivate , msgconfirmdelete ,  msgconfirmreactive } from '../model';
import { LimitsMasterService } from '../service/limits-master.service';
import { Subscription } from 'rxjs/Subscription';
import { AccordionModule, ModalDirective } from 'ngx-bootstrap';
import { SelectItem } from '../../../../shared/interface/selectItem';
import { ConfirmationService } from '../../../../shared/confirm-box/confirm.service';
import { CanComponentDeactivate } from '../../../../shared/guards/can-deactivate-guard';

@Component({
  selector: 'app-add-limits-master',
  templateUrl: './add-limits-master.component.html',
  styleUrls: ['./add-limits-master.component.css'],
  encapsulation: ViewEncapsulation.None,
})
 
export class AddLimitsMasterComponent implements OnInit , OnDestroy , CanComponentDeactivate {
  @ViewChild('serverErrorModal') public serverErrorModal: ModalDirective;
  @ViewChild('errorModal') public errorModal: ModalDirective;
   @ViewChild('deletePopUpModal') public deletePopUpModal1: ModalDirective;

  public status: any = {
    isFirstOpen: true,
    isFirstDisabled: false
  };
  statusmsg: string;
  limitName: string;
  addoreditpage: boolean;
  editpage: boolean;
  addpage: boolean;
  optionTextlimit: string;
  isDeletePopupModal: boolean;
  disableDropDown: boolean;
  draft: boolean;
  active: boolean ;
  inactive: boolean;
  deactiveStatus: boolean;
  isActiveStatus: boolean;
  isLimitBasisnotFxandExchange: boolean;
  isLimitBasisTypeDropDownDefault: boolean;
  isLimitBasisFX: boolean;
  isLimitBasisExchange: boolean;
  limitBasisTypeId: Number;
  basisTypeProduct: string;
  basisTypeProductId: Number;
  subscription: Subscription;
  defaultshow: boolean;
  limitMasterForm: FormGroup;
  basisNameUrl: string;
  limitBasisArr: LimitsMaster[];
  limitDetails: LimitDetails[];
  objOfComment: Comments = new Comments();
  limitsAttribute: LimitsAttribute[];
  limitBasisType: string;
  outRightAttributeArr = [];
  limitBasisNameArr: SelectItem[] = [];
  commentList : Comments[];
  limitAdditionalBasisName: SelectItem[] = [];
  param: any;
  volumeValueArr = [];
  isLimitBasisTypeEnabled: boolean;
  validToDate: Date;
  isPartySelected: boolean;
  isProductSelected: boolean;
  isExchangeSelected: boolean;
  isForexSelected: boolean;
  isAddMode: boolean;
  isUnitSelected: boolean;
  isEditMode: boolean;
  validFromDate: Date;
  errormodalflag: boolean;
  servererrorflag: boolean;
  isLimitBasisDropDownDefault: boolean;
  isTraderSelected: boolean;
  defaultSelectValue: Number;

  isProductSelectedfromAdditional: boolean;
  isLimitAdditionalBasisDropDownDefault: boolean;
  limitdata: LimitsMaster = new LimitsMaster();
  limitMaster = new LimitsMaster();
  limitMasterObj: LimitsMaster = new LimitsMaster();
  limitDetailsObj: LimitsMaster = new LimitsMaster();
  checkerrcount: number;
  req_limitbasis: boolean;
  req_limitBasisId: boolean;
  comments: Comments[] = [];
  commentsText: string;
  errorMessage: string;
  isActionPerformed = false;
  public i = 0;

  constructor(private route: ActivatedRoute, public confirmService: ConfirmationService,
     private fb: FormBuilder,  private limitsMasterService: LimitsMasterService, private router: Router  ) { }

canDeactivate(): boolean {
      return this.isActionPerformed;
  }
  setDefaultFlag() {
    this.req_limitbasis = false;
    this.req_limitBasisId = false;
    this.errormodalflag = false;
    this.servererrorflag = false;
  }
  ngOnInit() {
   this.buildUSERROLEForm();
    this.loadLimitBasisDropDown();
    this.loadVolumeValueDropDown();
    this.loadVolumeListDropDown();
    this.loadValueListDropDown();
    this.limitDetailsObj = new LimitsMaster();
    this.setDefalutSelectValue();
    this.limitBasisTypeId = this.route.snapshot.params['HeaderId'];
    if (this.limitBasisTypeId !== undefined || this.limitBasisTypeId != null) {
      this.getEditRecordsById(JSON.parse(this.route.snapshot.params['HeaderId']));
      this.editpage = true;
      this.addpage = false;
    }  else {
      this.editpage = false;
      this.addpage = true;
      this.optionTextlimit = '1';
      this.defaultshow = true;
      this.isLimitBasisnotFxandExchange = true;
    this.isLimitBasisDropDownDefault = true;
    this.isLimitBasisTypeDropDownDefault = true
    this.isProductSelectedfromAdditional = true;
    this.isLimitBasisTypeEnabled = false;
    this.isAddMode = true;
    this.addPermissionMapping();
    }
   
  }
  resetForm() {
    this.limitMasterForm.reset();
  }

   setDefalutSelectValue() {
    this.limitDetailsObj.limitBasisTypeId = defaultSelectValue;
    this.limitDetailsObj.limitBasisId =  defaultSelectValue;
    this.limitDetailsObj.additionallimitBasisId =  defaultSelectValue;
    this.limitDetailsObj.additionallimitBasisTypeId =  defaultSelectValue;
    }
  ngOnDestroy() {

  }
 loadVolumeValueDropDown() {
    this.subscription = this.limitsMasterService.VolumeValueDropDown().subscribe(loadVolumeValueDropDown => {
      this.limitsMasterService.volumeValueArr = loadVolumeValueDropDown.body;
      },
      error => {
       // alert(error);
        throw error;
      }
    );
  }
  loadVolumeListDropDown() {
    this.subscription = this.limitsMasterService.getVolumeListJSON().subscribe(volumeListArr => {
      this.limitsMasterService.volumeList = [];
      volumeListArr.body.forEach(volume => {
         if (volume.statusName === active.toString()) {
            this.limitsMasterService.volumeList.push({ label: volume.uomCode, value: volume.uomId });
         }

        });

    },
      error => {
      //  alert(error);
        throw error;
      }
    );
  }


  loadValueListDropDown() {
    this.subscription = this.limitsMasterService.getValueListJSON().subscribe(valueListArr => {
       this.limitsMasterService.valueList = [];

       valueListArr.body.forEach(value => {
          if (value.statusName ===  active.toString()) {
            this.limitsMasterService.valueList.push({ label: value.currencyCode, value: value.pkCurrencyId });
          }
        });
    },
      error => {
       // alert(error);
        throw error;
      }
    );
  }

  buildUSERROLEForm(): void {
    this.limitMasterForm = this.fb.group({
      'action': [''],
      'limitHeaderId': [],
      'statusName': [''],
      'limitBasisTypeId': [],
      'limitBasisId': [],
      'additionallimitBasisTypeId': [],
      'additionallimitBasisId': [],
      'commentText': [''],
      'deleteMappingId': this.fb.array([ ]),
      'comments': this.fb.array([ ]),
      'limitDetails': this.fb.array([]),
    });
  }

  getEditRecordsById(limitBasisTypeId) {
    this.subscription = this.limitsMasterService.getLimitsDetailsById(limitBasisTypeId).subscribe(
      limitDetails => {
        this.disableDropDown = false;
        this.limitDetailsObj = limitDetails.body;
         if (this.limitDetailsObj != null) {
              this.optionTextlimit = this.limitDetailsObj.limitBasisTypeId.toString();
            if ( this.limitDetailsObj.statusName != null ) {
              this.limitsMasterService.limitName = this.limitDetailsObj.limitBasisTypeName;
                if (this.limitDetailsObj.statusName !== draft.toString()) {
                  this.disableDropDown = true;
                      }
                      if (this.limitDetailsObj.statusName.toLowerCase() === draft.toString().toLowerCase()) {
                        this.draft = true;
                          this.active = false;
                          this.inactive = false;
                      } else if (this.limitDetailsObj.statusName.toLowerCase() === active.toString().toLowerCase()) {
                          this.active = true;
                          this.draft = false;
                          this.inactive = false;
                      } else if (this.limitDetailsObj.statusName.toLowerCase() === inactive.toString().toLowerCase()) {
                          this.inactive = true;
                          this.active = false;
                          this.draft = false;
                      }
                        this.editLimitMasters();
                  } else {
                      this.router.navigate(['master/limit/limits-master']);
                  }
              } else {
                  this.router.navigate(['master/limit/limits-master']);

              }
            },

      error => // alert(error),
        () => console.log('Finished')
    );
  }


  editLimitMasters() {
    if (  this.limitDetailsObj.comments != null) {
      if (this.limitDetailsObj.comments[0].commentText != null ) {
           this.commentsText = this.limitDetailsObj.comments[0].commentText;
      }
      //  for (let i = 0; i < this.limitDetailsObj.comments.length; i++) {
      //   this.commentsText = this.limitDetailsObj.comments[0].commentText;
      //   console.log(this.commentsText);
      // }
   }
      if ( this.limitDetailsObj.limitBasisTypeName != null ) {
         this.setBasisNameUrl(this.limitDetailsObj.limitBasisTypeName);
      }

       if ( this.limitDetailsObj.additionallimitBasisTypeName != null ) {
         this.getAdditionalBasisName(this.limitDetailsObj.additionallimitBasisTypeName);
      }

    const limitDetailsList = Number(JSON.stringify(this.limitDetailsObj.limitDetails.length));
    if (limitDetailsList >= 0) {
      for (let i = 0; i < limitDetailsList; i++) {
        this.addPermissionMapping()
      }
    }

  }



  loadLimitBasisDropDown() {
    this.subscription = this.limitsMasterService.LimitBasisDropDown().subscribe(limitBasisDropDown => {
      this.limitBasisArr = limitBasisDropDown.body;
      for (const key in this.limitBasisArr) {
        if (this.limitBasisArr.hasOwnProperty(key)) {
          if (this.limitBasisArr[key].limitBasisTypeName === 'PRODUCT') {
            this.basisTypeProductId = this.limitBasisArr[key].limitBasisTypeId
            this.basisTypeProduct = this.limitBasisArr[key].limitBasisTypeName;
            break;
          }
        }
      }
      // this.isLimitBasisTypeEnabled = true;
      // this.isLimitBasisTypeDropDownDefault = false;
    },
      error => {
        // alert(error);
        // throw error;
      }
    );
  }

  addPermissionMapping() {
    // alert('outrught attrinute-->' + JSON.stringify(param));
    const control = <FormArray>this.limitMasterForm.controls['limitDetails'];
    const addrCtrl = this.fb.group({
      'limitDetailsId': [''],
      'isTemporary': [''],
      'validFrom': new Date(),
      'validTo': new Date(),
      'limitOutRightAttribute': this.fb.group({
        'limitVolumeValueRefId': [''],
        'limitVolumeUomIdCurrencycode' : [''] ,
        'limitVolumeUomId': [''],
        'limitCurrencyId': [''],
        'limit': ['']
      }),

      'limitBasisAttribute': this.fb.group({
        'limitVolumeValueRefId': [''],
        'limitVolumeUomId': [''],
         'limitVolumeUomIdCurrencycode' : [''] ,
        'limitCurrencyId': [''],
        'limit': ['']
      }),
      'limitStructureAttribute': this.fb.group({
        'limitVolumeValueRefId': [''],
         'limitVolumeUomIdCurrencycode' : [''] ,
        'limitVolumeUomId': [''],
        'limitCurrencyId': [''],
        'limit': ['']
      }),
      'limitDrawDownAttribute': this.fb.group({
        'limitVolumeValueRefId': [''],
         'limitVolumeUomIdCurrencycode' : [''] ,
        'limitVolumeUomId': [''],
        'limitCurrencyId': [''],
        'limit': ['']
      }),
      'limitVaRAttribute': this.fb.group({
        'limitVolumeValueRefId': [''],
         'limitVolumeUomIdCurrencycode' : [''] ,
        'limitVolumeUomId': [''],
        'limitCurrencyId': [''],
        'limit': ['']
      }),
      'limitDeltaAttribute': this.fb.group({
        'limitVolumeValueRefId': [''],
         'limitVolumeUomIdCurrencycode' : [''] ,
        'limitVolumeUomId': [''],
        'limitCurrencyId': [''],
        'limit': ['']
      }),
      'limitGammaAttribute': this.fb.group({
        'limitVolumeValueRefId': [''],
         'limitVolumeUomIdCurrencycode' : [''] ,
        'limitVolumeUomId': [''],
        'limitCurrencyId': [''],
        'limit': ['']
      }),
      'limitVegaAttribute': this.fb.group({
        'limitVolumeValueRefId': [''],
         'limitVolumeUomIdCurrencycode' : [''] ,
        'limitVolumeUomId': [''],
        'limitCurrencyId': [''],
        'limit': ['']
      }),
      'currentMonthAttribute': this.fb.group({
        'limitVolumeValueRefId': [''],
        'limitVolumeUomId': [''],
         'limitVolumeUomIdCurrencycode' : [''] ,
        'limitCurrencyId': [''],
        'limit': ['']
      }),
      'forwardMonthAttribute': this.fb.group({
        'limitVolumeValueRefId': [''],
        'limitVolumeUomId': [''],
         'limitVolumeUomIdCurrencycode' : [''] ,
        'limitCurrencyId': [''],
        'limit': ['']
      }),
      'totalAttribute': this.fb.group({
        'limitVolumeValueRefId': [''],
        'limitVolumeUomId': [''],
         'limitVolumeUomIdCurrencycode' : [''] ,
        'limitCurrencyId': [''],
        'limit': ['']
      }),
      'valueAttribute': this.fb.group({
        'limitVolumeValueRefId': [''],
        'limitVolumeUomId': [''],
         'limitVolumeUomIdCurrencycode' : [''] ,
        'limitCurrencyId': [''],
        'limit': ['']
      })
    });
    control.push(addrCtrl);
  
  }


  removeExchange(i) {
    const control = <FormArray>this.limitMasterForm.controls['LimitExchangeform'];
    control.removeAt(i);
  }


  removeFxLimit(i: number) {
    const control = <FormArray>this.limitMasterForm.controls['LimitFXform'];
    control.removeAt(i);
  }


  removePermissionMapping(i: number) {
    const control = <FormArray>this.limitMasterForm.controls['limitDetails'];

    if (this.limitMasterForm.value.limitDetails != null ) {
                if (this.limitMasterForm.value.limitDetails[i].limitDetailsId != null  &&
                     this.limitMasterForm.value.limitDetails[i].limitDetailsId !== undefined ) {
                  this.limitMasterForm.value.deleteMappingId.push(this.limitMasterForm.value.limitDetails[i].limitDetailsId);
                 this.limitMasterForm.value.limitDetails.splice(i, 1);
                }
            }
    control.removeAt(i);
      if (control.length === 0 ) {
             this.addPermissionMapping();
        }

  }
  backtolist() {
     this.router.navigate(['master/limit/limits-master']);

  }
  removePermissionMappings(i: number) {
    const control = <FormArray>this.limitMasterForm.controls['baseNagotiationTerms'];
    control.removeAt(i);
  }

  getUserRoleArray(limitMasterForm) {
    return limitMasterForm.get('limitDetails').controls;
  }
  getUserRoleArrays(limitMasterForm) {
    return limitMasterForm.get('baseNagotiationTerms').controls;
  }

  getLimitExchangeArray(limitMasterForm) {
    return limitMasterForm.get('LimitExchangeform').controls;
  }



  onLimitBasisChange($event) {
    const selectElement = $event.target;
    const optionIndex = selectElement.selectedIndex;
    const optionText = selectElement.options[optionIndex].text;
    this.limitName = optionText.toString();
    const limitBasisTypeId = selectElement.options[optionIndex].value;
    this.optionTextlimit = limitBasisTypeId;
    this.limitsMasterService.limitName =  optionText;
    this.setBasisNameUrl(optionText);
    this.req_limitbasis = false;
    this.req_limitBasisId = false;
  }
  resetchild() {
     const control = <FormArray>this.limitMasterForm.controls['limitDetails'];
    control.reset();
  }

  setBasisNameUrl(optionText)  {
    let url = '';
    if (optionText.toLowerCase() === 'forexcurrency' ) {
      this.isProductSelected = false;
      this.isUnitSelected = false;
      this.isPartySelected = false;
      this.isForexSelected = true;
      this.isExchangeSelected = false;
      this.isTraderSelected = false;
      this.basisNameUrl = '/forex/forex/forexCurrency';
    } else if (optionText.toLowerCase() === 'exchange' ) {
      this.isExchangeSelected = true;
      this.isProductSelected = false;
      this.isForexSelected = false;
      this.isUnitSelected = false;
      this.isPartySelected = false;
      this.isTraderSelected = false;
      this.basisNameUrl = '/party/partyservice/party?partyForLimitByClass=Exchange&party=false';
    } else {
    if (optionText.toLowerCase() === 'party' ) {
      this.isPartySelected = true;
      this.isProductSelected = false;
      this.isUnitSelected = false;
      this.isForexSelected = false;
      this.isExchangeSelected = false;
      this.isTraderSelected = false;
      url = '/party/partyservice/party';
      this.basisNameUrl = url + '?partyForLimitByClass=Exchange&party=true';
    } else if (optionText.toLowerCase() === 'product' ) {
    this.isProductSelected = true;
      this.isUnitSelected = false;
      this.isPartySelected = false;
      this.isForexSelected = false;
      this.isExchangeSelected = false;
      this.isTraderSelected = false;
      this.basisNameUrl = '/product/productservice/product';
    } else if (optionText.toLowerCase() === 'unit') {
      this.isUnitSelected = true;
      this.isProductSelected = false;
      this.isPartySelected = false;
      this.isForexSelected = false;
      this.isExchangeSelected = false;
       this.isTraderSelected = false;
      this.basisNameUrl = '/party/unitservice/unit';
    } else if (optionText.toLowerCase() === 'trader') {
      this.isTraderSelected = true;
      this.isUnitSelected = false;
      this.isProductSelected = false;
      this.isPartySelected = false;
      this.isForexSelected = false;
      this.isExchangeSelected = false;
      this.basisNameUrl = '/authorization/traderservice/trader';
    }
    }
  this.getLimitBasisNameDropDown(this.basisNameUrl);
  }

  getLimitBasisNameDropDown(url) {
    if (url !== '') {
      this.subscription = this.limitsMasterService.getLimitBasisNameDropDown(url).subscribe(limitBasisDropDown => {
        this.limitBasisNameArr = [];
        if ( this.isUnitSelected) {
            limitBasisDropDown.body.forEach(volume => {
                this.limitBasisNameArr.push({ label: volume.unitName, value: volume.unitId });
                        });
        }
         if ( this.isProductSelected) {
            limitBasisDropDown.body.forEach(volume => {
               this.limitBasisNameArr.push({ label: volume.prodName, value: volume.prodId });
          });
        }
         if ( this.isPartySelected) {
            limitBasisDropDown.body.forEach(volume => {
               this.limitBasisNameArr.push({ label: volume.partyCode, value: volume.partyId });
          });
        }
         if ( this.isTraderSelected) {
            limitBasisDropDown.body.forEach(volume => {
               this.limitBasisNameArr.push({ label: volume.userCode, value: volume.userId });

          });
        }
         if ( this.isExchangeSelected) {
            limitBasisDropDown.body.forEach(volume => {
               this.limitBasisNameArr.push({ label: volume.partyCode, value: volume.partyId });

          });
        }
         if ( this.isForexSelected) {
            limitBasisDropDown.body.forEach(volume => {
              this.limitBasisNameArr.push({ label: volume.forexcurrencyCode, value: volume.forexcurrencyId });
          });
        }



      },
        error => {
          //  alert(error);
          // throw error;
          this.limitBasisNameArr = [];
          this.isProductSelected = false;
          this.isUnitSelected = false;
          this.isPartySelected = false;
          this.isForexSelected = false;
          this.isExchangeSelected = false;
          this.isTraderSelected = false;
        }
      );
    }
  }

  onAdditionalBasisChange($event) {
    const selectElement = $event.target;
    const optionIndex = selectElement.selectedIndex;
    const optionText = selectElement.options[optionIndex].text;
    this.getAdditionalBasisName(optionText)
  }

  getAdditionalBasisName(optionText) {
   // const url = selectElement.options[optionIndex].value;
    // alert('product url-->' + url);
    if (optionText === 'Product' || optionText === 'PRODUCT') {
      this.isLimitAdditionalBasisDropDownDefault = false;
      this.isProductSelectedfromAdditional = true;
      this.subscription = this.limitsMasterService.getLimitBasisNameDropDown('/product/productservice/product')
        .subscribe(limitBasisDropDown => {
         this.limitAdditionalBasisName = [];
      limitBasisDropDown.body.forEach(list => {
            this.limitAdditionalBasisName.push({ label: list.prodName, value: list.prodId });
        });

        },
        error => {
          // alert(error);
          //  throw error;
        }
        );

    }
  }

  isValidForm(): boolean {
    this.checkerrcount = 0;
    this.commentList = [];
     this.objOfComment.commentText = this.limitMasterForm.value.commentText;
     this.commentList.push(this.objOfComment);
     this.limitMasterForm.value.comments = this.commentList;
    if (this.limitMasterForm.value.limitBasisTypeId == null || this.limitMasterForm.value.limitBasisTypeId === defaultSelectValue ) {
      this.req_limitbasis = true;
      this.checkerrcount++;

    } else {
      this.limitMasterForm.value.limitBasisTypeId = Number(this.limitMasterForm.value.limitBasisTypeId);
      this.req_limitbasis = false;
    }
    if (this.limitMasterForm.value.limitBasisId == null || this.limitMasterForm.value.limitBasisId === defaultSelectValue ) {
      this.req_limitBasisId = true;
      this.checkerrcount++;

    } else {
      this.limitMasterForm.value.limitBasisId = Number(this.limitMasterForm.value.limitBasisId);
      this.req_limitBasisId = false;
    }
    if ( this.limitMasterForm.value.additionallimitBasisTypeId !== defaultSelectValue) {
        if (this.limitMasterForm.value.additionallimitBasisTypeId != null ) {
      this.limitMasterForm.value.additionallimitBasisTypeId = Number(this.limitMasterForm.value.additionallimitBasisTypeId);
     }
    }
     if ( this.limitMasterForm.value.additionallimitBasisId !== defaultSelectValue) {
       if (this.limitMasterForm.value.additionallimitBasisId != null) {
      this.limitMasterForm.value.additionallimitBasisId = Number(this.limitMasterForm.value.additionallimitBasisId);
    }
     }


    if (this.limitMasterForm.value.limitBasisTypeId != null) {
      if (this.limitMasterForm.value.limitDetails != null) {
        this.limitsMasterService.setlimitDetails( this.limitMasterForm.value);
        const sizelimitDetails = Number(JSON.stringify(this.limitMasterForm.value.limitDetails.length));
        for (let i = 0; i < sizelimitDetails; i++) {
          if (this.limitMasterForm.value.limitDetails[i].limitDetailsId != null ) {
               this.limitMasterForm.value.limitDetails[i].limitDetailsId =
                     Number(this.limitMasterForm.value.limitDetails[i].limitDetailsId);
          }
          // outright
          if (this.limitMasterForm.value.limitBasisTypeId !== defaultSelectValue && this.limitMasterForm.value.limitBasisTypeId !== 19 &&
                   this.limitMasterForm.value.limitBasisTypeId !== 20) {

            if (this.limitMasterForm.value.limitDetails[i].limitOutRightAttribute.limitVolumeValueRefId == null ||
                  this.limitMasterForm.value.limitDetails[i].limitOutRightAttribute.limitVolumeValueRefId === defaultSelectValue ) {
              this.checkerrcount++;
            } else if (this.limitMasterForm.value.limitDetails[i].limitOutRightAttribute.limitVolumeValueRefId != null) {
                this.limitMasterForm.value.limitDetails[i].limitOutRightAttribute.limitVolumeValueRefId =
                Number(this.limitMasterForm.value.limitDetails[i].limitOutRightAttribute.limitVolumeValueRefId);

              if (JSON.parse(this.limitMasterForm.value.limitDetails[i].limitOutRightAttribute.limitVolumeValueRefId) === 1) {
                  this.limitMasterForm.value.limitDetails[i].limitOutRightAttribute.limitVolumeUomId  =
                  Number(this.limitMasterForm.value.limitDetails[i].limitOutRightAttribute.limitVolumeUomIdCurrencycode);
                if (this.limitMasterForm.value.limitDetails[i].limitOutRightAttribute.limitVolumeUomId == null ||
                      this.limitMasterForm.value.limitDetails[i].limitOutRightAttribute.limitVolumeUomId === defaultSelectValue ) {
                  this.checkerrcount++;
                }
              }
              if (JSON.parse(this.limitMasterForm.value.limitDetails[i].limitOutRightAttribute.limitVolumeValueRefId) === 2) {
                 this.limitMasterForm.value.limitDetails[i].limitOutRightAttribute.limitCurrencyId  =
                 Number(this.limitMasterForm.value.limitDetails[i].limitOutRightAttribute.limitVolumeUomIdCurrencycode);
                if (this.limitMasterForm.value.limitDetails[i].limitOutRightAttribute.limitCurrencyId == null || 
                    this.limitMasterForm.value.limitDetails[i].limitOutRightAttribute.limitCurrencyId === defaultSelectValue ) {
                  this.checkerrcount++;
                }
              }

              if (!this.limitMasterForm.value.limitDetails[i].limitOutRightAttribute.limit) {
                this.checkerrcount++;
              } else if (this.limitMasterForm.value.limitDetails[i].limitOutRightAttribute.limit != null) {
                if (this.limitMasterForm.value.limitDetails[i].limitOutRightAttribute.limit === '') {
                  this.checkerrcount++;
                } else {
                  this.limitMasterForm.value.limitDetails[i].limitOutRightAttribute.limit =
                    Number(this.limitMasterForm.value.limitDetails[i].limitOutRightAttribute.limit);
                }

              }
            }
            // basis
            if (this.limitMasterForm.value.limitDetails[i].limitBasisAttribute.limitVolumeValueRefId == null ||
                  this.limitMasterForm.value.limitDetails[i].limitBasisAttribute.limitVolumeValueRefId === defaultSelectValue) {
              this.checkerrcount++;
            } else if (this.limitMasterForm.value.limitDetails[i].limitBasisAttribute.limitVolumeValueRefId != null) {
              this.limitMasterForm.value.limitDetails[i].limitBasisAttribute.limitVolumeValueRefId =
                Number(this.limitMasterForm.value.limitDetails[i].limitBasisAttribute.limitVolumeValueRefId);
              if (JSON.parse(this.limitMasterForm.value.limitDetails[i].limitBasisAttribute.limitVolumeValueRefId) === 1) {
                 this.limitMasterForm.value.limitDetails[i].limitBasisAttribute.limitVolumeUomId  =
                 Number(this.limitMasterForm.value.limitDetails[i].limitBasisAttribute.limitVolumeUomIdCurrencycode);
                if (this.limitMasterForm.value.limitDetails[i].limitBasisAttribute.limitVolumeUomId == null || 
                  this.limitMasterForm.value.limitDetails[i].limitBasisAttribute.limitVolumeUomId === defaultSelectValue) {
                  this.checkerrcount++;
                }
              }
              if (JSON.parse(this.limitMasterForm.value.limitDetails[i].limitBasisAttribute.limitVolumeValueRefId) === 2) {
                 this.limitMasterForm.value.limitDetails[i].limitBasisAttribute.limitCurrencyId  =
                 Number(this.limitMasterForm.value.limitDetails[i].limitBasisAttribute.limitVolumeUomIdCurrencycode);
                if (this.limitMasterForm.value.limitDetails[i].limitBasisAttribute.limitCurrencyId == null ||
                this.limitMasterForm.value.limitDetails[i].limitBasisAttribute.limitCurrencyId === defaultSelectValue) {
                  this.checkerrcount++;
                }
              }

              if (!this.limitMasterForm.value.limitDetails[i].limitBasisAttribute.limit) {
                this.checkerrcount++;
              } else if (this.limitMasterForm.value.limitDetails[i].limitBasisAttribute.limit != null) {
                if (this.limitMasterForm.value.limitDetails[i].limitBasisAttribute.limit === '') {
                  this.checkerrcount++;
                } else {
                  this.limitMasterForm.value.limitDetails[i].limitBasisAttribute.limit =
                    Number(this.limitMasterForm.value.limitDetails[i].limitBasisAttribute.limit);
                }
              }
            }
// structure
            if (this.limitMasterForm.value.limitDetails[i].limitStructureAttribute.limitVolumeValueRefId == null ||
                  this.limitMasterForm.value.limitDetails[i].limitStructureAttribute.limitVolumeValueRefId === defaultSelectValue  ) {
              this.checkerrcount++;
            } else if (this.limitMasterForm.value.limitDetails[i].limitStructureAttribute.limitVolumeValueRefId != null) {
               this.limitMasterForm.value.limitDetails[i].limitStructureAttribute.limitVolumeValueRefId =
                Number(this.limitMasterForm.value.limitDetails[i].limitStructureAttribute.limitVolumeValueRefId);
              if (JSON.parse(this.limitMasterForm.value.limitDetails[i].limitStructureAttribute.limitVolumeValueRefId) === 1) {
                   this.limitMasterForm.value.limitDetails[i].limitStructureAttribute.limitVolumeUomId  =
                   Number(this.limitMasterForm.value.limitDetails[i].limitStructureAttribute.limitVolumeUomIdCurrencycode);
                if (this.limitMasterForm.value.limitDetails[i].limitStructureAttribute.limitVolumeUomId == null ||
                    this.limitMasterForm.value.limitDetails[i].limitStructureAttribute.limitVolumeUomId === defaultSelectValue) {
                  this.checkerrcount++;
                }
              }
              if (JSON.parse(this.limitMasterForm.value.limitDetails[i].limitStructureAttribute.limitVolumeValueRefId) === 2) {
                  this.limitMasterForm.value.limitDetails[i].limitStructureAttribute.limitCurrencyId  =
                  Number(this.limitMasterForm.value.limitDetails[i].limitStructureAttribute.limitVolumeUomIdCurrencycode);
                if (this.limitMasterForm.value.limitDetails[i].limitStructureAttribute.limitCurrencyId == null || 
                    this.limitMasterForm.value.limitDetails[i].limitStructureAttribute.limitCurrencyId === defaultSelectValue) {
                  this.checkerrcount++;
                }
              }

              if (!this.limitMasterForm.value.limitDetails[i].limitStructureAttribute.limit) {
                this.checkerrcount++;
              } else if (this.limitMasterForm.value.limitDetails[i].limitStructureAttribute.limit != null) {
                if (this.limitMasterForm.value.limitDetails[i].limitStructureAttribute.limit === '') {
                  this.checkerrcount++;
                }else {
                  this.limitMasterForm.value.limitDetails[i].limitStructureAttribute.limit =
                    Number(this.limitMasterForm.value.limitDetails[i].limitStructureAttribute.limit);
                }

              }
            }
            if (this.limitMasterForm.value.limitDetails[i].limitDrawDownAttribute.limitVolumeValueRefId == null ||
                  this.limitMasterForm.value.limitDetails[i].limitDrawDownAttribute.limitVolumeValueRefId === defaultSelectValue ) {
              this.checkerrcount++;
            } else if (this.limitMasterForm.value.limitDetails[i].limitDrawDownAttribute.limitVolumeValueRefId != null) {
              this.limitMasterForm.value.limitDetails[i].limitDrawDownAttribute.limitVolumeValueRefId =
                Number(this.limitMasterForm.value.limitDetails[i].limitDrawDownAttribute.limitVolumeValueRefId);
              if (JSON.parse(this.limitMasterForm.value.limitDetails[i].limitDrawDownAttribute.limitVolumeValueRefId) === 1) {
                  this.limitMasterForm.value.limitDetails[i].limitDrawDownAttribute.limitVolumeUomId  =
                  Number(this.limitMasterForm.value.limitDetails[i].limitDrawDownAttribute.limitVolumeUomIdCurrencycode);
                if (this.limitMasterForm.value.limitDetails[i].limitDrawDownAttribute.limitVolumeUomId == null ||
                    this.limitMasterForm.value.limitDetails[i].limitDrawDownAttribute.limitVolumeUomId === defaultSelectValue) {
                  this.checkerrcount++;
                }
              }
              if (JSON.parse(this.limitMasterForm.value.limitDetails[i].limitDrawDownAttribute.limitVolumeValueRefId) === 2) {
                  this.limitMasterForm.value.limitDetails[i].limitDrawDownAttribute.limitCurrencyId  =
                  Number(this.limitMasterForm.value.limitDetails[i].limitDrawDownAttribute.limitVolumeUomIdCurrencycode);
                if (this.limitMasterForm.value.limitDetails[i].limitDrawDownAttribute.limitCurrencyId == null ||
                    this.limitMasterForm.value.limitDetails[i].limitDrawDownAttribute.limitCurrencyId === defaultSelectValue) {
                  this.checkerrcount++;
                }
              }

              if (!this.limitMasterForm.value.limitDetails[i].limitDrawDownAttribute.limit) {
                this.checkerrcount++;
              } else if (this.limitMasterForm.value.limitDetails[i].limitDrawDownAttribute.limit != null) {
                if (this.limitMasterForm.value.limitDetails[i].limitDrawDownAttribute.limit === '') {
                  this.checkerrcount++;
                }else {
                  this.limitMasterForm.value.limitDetails[i].limitDrawDownAttribute.limit =
                    Number(this.limitMasterForm.value.limitDetails[i].limitDrawDownAttribute.limit);
                }
              }
            }
            if (this.limitMasterForm.value.limitDetails[i].limitVaRAttribute.limitVolumeValueRefId == null || 
            this.limitMasterForm.value.limitDetails[i].limitVaRAttribute.limitVolumeValueRefId === defaultSelectValue) {
              this.checkerrcount++;
            } else if (this.limitMasterForm.value.limitDetails[i].limitVaRAttribute.limitVolumeValueRefId != null) {
               this.limitMasterForm.value.limitDetails[i].limitVaRAttribute.limitVolumeValueRefId =
                Number(this.limitMasterForm.value.limitDetails[i].limitVaRAttribute.limitVolumeValueRefId);
              if (JSON.parse(this.limitMasterForm.value.limitDetails[i].limitVaRAttribute.limitVolumeValueRefId) === 1) {
                 this.limitMasterForm.value.limitDetails[i].limitVaRAttribute.limitVolumeUomId  =
                 Number(this.limitMasterForm.value.limitDetails[i].limitVaRAttribute.limitVolumeUomIdCurrencycode);
                if (this.limitMasterForm.value.limitDetails[i].limitVaRAttribute.limitVolumeUomId == null  ||
                  this.limitMasterForm.value.limitDetails[i].limitVaRAttribute.limitVolumeUomId === defaultSelectValue) {
                  this.checkerrcount++;
                }
              }
              if (JSON.parse(this.limitMasterForm.value.limitDetails[i].limitVaRAttribute.limitVolumeValueRefId) === 2) {
                 this.limitMasterForm.value.limitDetails[i].limitVaRAttribute.limitCurrencyId  =
                 Number(this.limitMasterForm.value.limitDetails[i].limitVaRAttribute.limitVolumeUomIdCurrencycode);
                if (this.limitMasterForm.value.limitDetails[i].limitVaRAttribute.limitCurrencyId == null ||
                    this.limitMasterForm.value.limitDetails[i].limitVaRAttribute.limitCurrencyId === defaultSelectValue) {
                  this.checkerrcount++;
                }
              }

              if (!this.limitMasterForm.value.limitDetails[i].limitVaRAttribute.limit) {
                this.checkerrcount++;
              } else if (this.limitMasterForm.value.limitDetails[i].limitVaRAttribute.limit != null) {
                if (this.limitMasterForm.value.limitDetails[i].limitVaRAttribute.limit === '') {
                  this.checkerrcount++;
                }else {
                  this.limitMasterForm.value.limitDetails[i].limitVaRAttribute.limit =
                    Number(this.limitMasterForm.value.limitDetails[i].limitVaRAttribute.limit);
                }

              }
            }
            if (this.limitMasterForm.value.limitDetails[i].limitDeltaAttribute.limitVolumeValueRefId == null ||
              this.limitMasterForm.value.limitDetails[i].limitDeltaAttribute.limitVolumeValueRefId === defaultSelectValue) {
              this.checkerrcount++;
            } else if (this.limitMasterForm.value.limitDetails[i].limitDeltaAttribute.limitVolumeValueRefId != null) {
               this.limitMasterForm.value.limitDetails[i].limitDeltaAttribute.limitVolumeValueRefId =
                Number(this.limitMasterForm.value.limitDetails[i].limitDeltaAttribute.limitVolumeValueRefId);
              if (JSON.parse(this.limitMasterForm.value.limitDetails[i].limitDeltaAttribute.limitVolumeValueRefId) === 1) {
                  this.limitMasterForm.value.limitDetails[i].limitDeltaAttribute.limitVolumeUomId  =
                 Number(this.limitMasterForm.value.limitDetails[i].limitDeltaAttribute.limitVolumeUomIdCurrencycode);
                if (this.limitMasterForm.value.limitDetails[i].limitDeltaAttribute.limitVolumeUomId == null || this.limitMasterForm.value.limitDetails[i].limitDeltaAttribute.limitVolumeUomId === defaultSelectValue) {
                  this.checkerrcount++;
                }
              }
              if (JSON.parse(this.limitMasterForm.value.limitDetails[i].limitDeltaAttribute.limitVolumeValueRefId) === 2) {
                this.limitMasterForm.value.limitDetails[i].limitDeltaAttribute.limitCurrencyId  =
                Number(this.limitMasterForm.value.limitDetails[i].limitDeltaAttribute.limitVolumeUomIdCurrencycode);
                if (this.limitMasterForm.value.limitDetails[i].limitDeltaAttribute.limitCurrencyId == null ||
                  this.limitMasterForm.value.limitDetails[i].limitDeltaAttribute.limitCurrencyId === defaultSelectValue) {
                  this.checkerrcount++;
                }
              }

              if (!this.limitMasterForm.value.limitDetails[i].limitDeltaAttribute.limit) {
                this.checkerrcount++;
              } else if (this.limitMasterForm.value.limitDetails[i].limitDeltaAttribute.limit != null) {
                if (this.limitMasterForm.value.limitDetails[i].limitDeltaAttribute.limit === '') {
                  this.checkerrcount++;
                }else {
                  this.limitMasterForm.value.limitDetails[i].limitDeltaAttribute.limit =
                    Number(this.limitMasterForm.value.limitDetails[i].limitDeltaAttribute.limit);
                }
              }
            }
            if (this.limitMasterForm.value.limitDetails[i].limitGammaAttribute.limitVolumeValueRefId == null ||
                this.limitMasterForm.value.limitDetails[i].limitGammaAttribute.limitVolumeValueRefId === defaultSelectValue) {
              this.checkerrcount++;
            } else if (this.limitMasterForm.value.limitDetails[i].limitGammaAttribute.limitVolumeValueRefId != null) {
               this.limitMasterForm.value.limitDetails[i].limitGammaAttribute.limitVolumeValueRefId =
                Number(this.limitMasterForm.value.limitDetails[i].limitGammaAttribute.limitVolumeValueRefId);
              if (JSON.parse(this.limitMasterForm.value.limitDetails[i].limitGammaAttribute.limitVolumeValueRefId) === 1) {
                 this.limitMasterForm.value.limitDetails[i].limitGammaAttribute.limitVolumeUomId  =
                 Number(this.limitMasterForm.value.limitDetails[i].limitGammaAttribute.limitVolumeUomIdCurrencycode);
                if (this.limitMasterForm.value.limitDetails[i].limitGammaAttribute.limitVolumeUomId == null || 
                    this.limitMasterForm.value.limitDetails[i].limitGammaAttribute.limitVolumeUomId === defaultSelectValue) {
                  this.checkerrcount++;
                }
              }
              if (JSON.parse(this.limitMasterForm.value.limitDetails[i].limitGammaAttribute.limitVolumeValueRefId) === 2) {
                this.limitMasterForm.value.limitDetails[i].limitGammaAttribute.limitCurrencyId  =
                Number(this.limitMasterForm.value.limitDetails[i].limitGammaAttribute.limitVolumeUomIdCurrencycode);
                if (this.limitMasterForm.value.limitDetails[i].limitGammaAttribute.limitCurrencyId == null ||
                  this.limitMasterForm.value.limitDetails[i].limitGammaAttribute.limitCurrencyId === defaultSelectValue) {
                  this.checkerrcount++;
                }
              }

              if (!this.limitMasterForm.value.limitDetails[i].limitGammaAttribute.limit) {
                this.checkerrcount++;
              } else if (this.limitMasterForm.value.limitDetails[i].limitGammaAttribute.limit != null) {
                if (this.limitMasterForm.value.limitDetails[i].limitGammaAttribute.limit === '') {
                  this.checkerrcount++;
                } else {
                  this.limitMasterForm.value.limitDetails[i].limitGammaAttribute.limit =
                    Number(this.limitMasterForm.value.limitDetails[i].limitGammaAttribute.limit);
                }
              }
            }
            if (this.limitMasterForm.value.limitDetails[i].limitVegaAttribute.limitVolumeValueRefId == null ||
                  this.limitMasterForm.value.limitDetails[i].limitVegaAttribute.limitVolumeValueRefId === defaultSelectValue ) {
              this.checkerrcount++;
            } else if (this.limitMasterForm.value.limitDetails[i].limitVegaAttribute.limitVolumeValueRefId != null) {
               this.limitMasterForm.value.limitDetails[i].limitVegaAttribute.limitVolumeValueRefId =
                Number(this.limitMasterForm.value.limitDetails[i].limitVegaAttribute.limitVolumeValueRefId);
              if (JSON.parse(this.limitMasterForm.value.limitDetails[i].limitVegaAttribute.limitVolumeValueRefId) === 1) {
                 this.limitMasterForm.value.limitDetails[i].limitVegaAttribute.limitVolumeUomId  =
                 Number(this.limitMasterForm.value.limitDetails[i].limitVegaAttribute.limitVolumeUomIdCurrencycode);
                if (this.limitMasterForm.value.limitDetails[i].limitVegaAttribute.limitVolumeUomId == null ||
                    this.limitMasterForm.value.limitDetails[i].limitVegaAttribute.limitVolumeUomId === defaultSelectValue) {
                  this.checkerrcount++;
                }
              }
              if (JSON.parse(this.limitMasterForm.value.limitDetails[i].limitVegaAttribute.limitVolumeValueRefId) === 2) {
                 this.limitMasterForm.value.limitDetails[i].limitVegaAttribute.limitCurrencyId  =
                 Number(this.limitMasterForm.value.limitDetails[i].limitVegaAttribute.limitVolumeUomIdCurrencycode);
                if (this.limitMasterForm.value.limitDetails[i].limitVegaAttribute.limitCurrencyId == null  || 
                      this.limitMasterForm.value.limitDetails[i].limitVegaAttribute.limitCurrencyId === defaultSelectValue) {
                  this.checkerrcount++;
                }
              }

              if (!this.limitMasterForm.value.limitDetails[i].limitVegaAttribute.limit) {
                this.checkerrcount++;
              } else if (this.limitMasterForm.value.limitDetails[i].limitVegaAttribute.limit != null) {
                if (this.limitMasterForm.value.limitDetails[i].limitVegaAttribute.limit === '') {
                  this.checkerrcount++;
                }  else {
                  this.limitMasterForm.value.limitDetails[i].limitVegaAttribute.limit =
                    Number(this.limitMasterForm.value.limitDetails[i].limitVegaAttribute.limit);
                }
              }
            }
          }
          if (this.limitMasterForm.value.limitBasisTypeId === 19) {
            if (this.limitMasterForm.value.limitDetails[i].currentMonthAttribute.limitVolumeValueRefId == null ||
                  this.limitMasterForm.value.limitDetails[i].currentMonthAttribute.limitVolumeValueRefId === defaultSelectValue) {
              this.checkerrcount++;
            } else if (this.limitMasterForm.value.limitDetails[i].currentMonthAttribute.limitVolumeValueRefId != null) {
               this.limitMasterForm.value.limitDetails[i].currentMonthAttribute.limitVolumeValueRefId =
                Number(this.limitMasterForm.value.limitDetails[i].currentMonthAttribute.limitVolumeValueRefId);
              if (JSON.parse(this.limitMasterForm.value.limitDetails[i].currentMonthAttribute.limitVolumeValueRefId) === 1) {
                this.limitMasterForm.value.limitDetails[i].currentMonthAttribute.limitVolumeUomId  =
                Number(this.limitMasterForm.value.limitDetails[i].currentMonthAttribute.limitVolumeUomIdCurrencycode);
                if (this.limitMasterForm.value.limitDetails[i].currentMonthAttribute.limitVolumeUomId == null ||
                      this.limitMasterForm.value.limitDetails[i].currentMonthAttribute.limitVolumeUomId === defaultSelectValue) {
                  this.checkerrcount++;
                }
              }
              if (JSON.parse(this.limitMasterForm.value.limitDetails[i].currentMonthAttribute.limitVolumeValueRefId) === 2) {
                   this.limitMasterForm.value.limitDetails[i].currentMonthAttribute.limitCurrencyId  =
                   Number(this.limitMasterForm.value.limitDetails[i].currentMonthAttribute.limitVolumeUomIdCurrencycode);
                if (this.limitMasterForm.value.limitDetails[i].currentMonthAttribute.limitCurrencyId == null ||
                this.limitMasterForm.value.limitDetails[i].currentMonthAttribute.limitCurrencyId === defaultSelectValue) {
                  this.checkerrcount++;
                }
              }

              if (!this.limitMasterForm.value.limitDetails[i].currentMonthAttribute.limit) {
                this.checkerrcount++;
              } else if (this.limitMasterForm.value.limitDetails[i].currentMonthAttribute.limit != null) {
                if (this.limitMasterForm.value.limitDetails[i].currentMonthAttribute.limit === '') {
                  this.checkerrcount++;
                }else {
                  this.limitMasterForm.value.limitDetails[i].currentMonthAttribute.limit =
                    Number(this.limitMasterForm.value.limitDetails[i].currentMonthAttribute.limit);
                }
              }
            }
            if (this.limitMasterForm.value.limitDetails[i].forwardMonthAttribute.limitVolumeValueRefId == null ||
                  this.limitMasterForm.value.limitDetails[i].forwardMonthAttribute.limitVolumeValueRefId === defaultSelectValue ||
                  this.limitMasterForm.value.limitDetails[i].forwardMonthAttribute.limitVolumeUomIdCurrencycode === defaultSelectValue) {
              this.checkerrcount++;
            } else if (this.limitMasterForm.value.limitDetails[i].forwardMonthAttribute.limitVolumeValueRefId != null) {
               this.limitMasterForm.value.limitDetails[i].forwardMonthAttribute.limitVolumeValueRefId =
                Number(this.limitMasterForm.value.limitDetails[i].forwardMonthAttribute.limitVolumeValueRefId);
              if (JSON.parse(this.limitMasterForm.value.limitDetails[i].forwardMonthAttribute.limitVolumeValueRefId) === 1) {
                 this.limitMasterForm.value.limitDetails[i].forwardMonthAttribute.limitVolumeUomId  =
                 Number(this.limitMasterForm.value.limitDetails[i].forwardMonthAttribute.limitVolumeUomIdCurrencycode);
                if (this.limitMasterForm.value.limitDetails[i].forwardMonthAttribute.limitVolumeUomId == null) {
                  this.checkerrcount++;
                }
              }
              if (JSON.parse(this.limitMasterForm.value.limitDetails[i].forwardMonthAttribute.limitVolumeValueRefId) === 2) {
                 this.limitMasterForm.value.limitDetails[i].forwardMonthAttribute.limitCurrencyId  =
                 Number(this.limitMasterForm.value.limitDetails[i].forwardMonthAttribute.limitVolumeUomIdCurrencycode);
                if (this.limitMasterForm.value.limitDetails[i].forwardMonthAttribute.limitCurrencyId == null) {
                  this.checkerrcount++;
                }
              }

              if (!this.limitMasterForm.value.limitDetails[i].forwardMonthAttribute.limit) {
                this.checkerrcount++;
              } else if (this.limitMasterForm.value.limitDetails[i].forwardMonthAttribute.limit != null) {
                if (this.limitMasterForm.value.limitDetails[i].forwardMonthAttribute.limit === '') {
                  this.checkerrcount++;
                }else {
                  this.limitMasterForm.value.limitDetails[i].forwardMonthAttribute.limit =
                    Number(this.limitMasterForm.value.limitDetails[i].forwardMonthAttribute.limit);
                }
              }
            }



            if (this.limitMasterForm.value.limitDetails[i].totalAttribute.limitVolumeValueRefId == null ||
                  this.limitMasterForm.value.limitDetails[i].totalAttribute.limitVolumeValueRefId === defaultSelectValue ||
                    this.limitMasterForm.value.limitDetails[i].totalAttribute.limitVolumeUomIdCurrencycode === defaultSelectValue) {
              this.checkerrcount++;
            } else if (this.limitMasterForm.value.limitDetails[i].totalAttribute.limitVolumeValueRefId != null) {
              this.limitMasterForm.value.limitDetails[i].totalAttribute.limitVolumeValueRefId =
                Number(this.limitMasterForm.value.limitDetails[i].totalAttribute.limitVolumeValueRefId);
              if (JSON.parse(this.limitMasterForm.value.limitDetails[i].totalAttribute.limitVolumeValueRefId) === 1) {
                this.limitMasterForm.value.limitDetails[i].totalAttribute.limitVolumeUomId  =
                Number(this.limitMasterForm.value.limitDetails[i].totalAttribute.limitVolumeUomIdCurrencycode);
                if (this.limitMasterForm.value.limitDetails[i].totalAttribute.limitVolumeUomId == null) {
                  this.checkerrcount++;
                }
              }
              if (JSON.parse(this.limitMasterForm.value.limitDetails[i].totalAttribute.limitVolumeValueRefId) === 2) {
                this.limitMasterForm.value.limitDetails[i].totalAttribute.limitCurrencyId  =
                Number(this.limitMasterForm.value.limitDetails[i].totalAttribute.limitVolumeUomIdCurrencycode);
                if (this.limitMasterForm.value.limitDetails[i].totalAttribute.limitCurrencyId == null) {
                  this.checkerrcount++;
                }
              }

              if (!this.limitMasterForm.value.limitDetails[i].totalAttribute.limit ) {
                this.checkerrcount++;
              } else if (this.limitMasterForm.value.limitDetails[i].totalAttribute.limit != null) {
                if (this.limitMasterForm.value.limitDetails[i].totalAttribute.limit === '') {
                  this.checkerrcount++;
                }else {
                  this.limitMasterForm.value.limitDetails[i].totalAttribute.limit =
                    Number(this.limitMasterForm.value.limitDetails[i].totalAttribute.limit);
                }
              }
            }
          }
          if (this.limitMasterForm.value.limitBasisTypeId === 20) {
            if (this.limitMasterForm.value.limitDetails[i].valueAttribute.limitVolumeValueRefId == null ||
                this.limitMasterForm.value.limitDetails[i].valueAttribute.limitVolumeValueRefId === defaultSelectValue ||
                  this.limitMasterForm.value.limitDetails[i].valueAttribute.limitVolumeUomIdCurrencycode === defaultSelectValue) {
              this.checkerrcount++;
            } else if (this.limitMasterForm.value.limitDetails[i].valueAttribute.limitVolumeValueRefId != null) {
              this.limitMasterForm.value.limitDetails[i].valueAttribute.limitVolumeValueRefId =
                Number(this.limitMasterForm.value.limitDetails[i].valueAttribute.limitVolumeValueRefId);
              if (JSON.parse(this.limitMasterForm.value.limitDetails[i].valueAttribute.limitVolumeValueRefId) === 1) {
                this.limitMasterForm.value.limitDetails[i].valueAttribute.limitVolumeUomId  =
                Number(this.limitMasterForm.value.limitDetails[i].valueAttribute.limitVolumeUomIdCurrencycode);
                if (this.limitMasterForm.value.limitDetails[i].valueAttribute.limitVolumeUomId == null) {
                  this.checkerrcount++;
                }
              }
              if (JSON.parse(this.limitMasterForm.value.limitDetails[i].valueAttribute.limitVolumeValueRefId) === 2) {
                 this.limitMasterForm.value.limitDetails[i].valueAttribute.limitCurrencyId  =
                Number(this.limitMasterForm.value.limitDetails[i].valueAttribute.limitVolumeUomIdCurrencycode);
                if (this.limitMasterForm.value.limitDetails[i].valueAttribute.limitCurrencyId == null) {
                  this.checkerrcount++;
                }
              }

              if (!this.limitMasterForm.value.limitDetails[i].valueAttribute.limit) {
                this.checkerrcount++;
              } else if (this.limitMasterForm.value.limitDetails[i].valueAttribute.limit != null) {
                if (this.limitMasterForm.value.limitDetails[i].valueAttribute.limit === '') {
                  this.checkerrcount++;
                }else {
                  this.limitMasterForm.value.limitDetails[i].valueAttribute.limit =
                    Number(this.limitMasterForm.value.limitDetails[i].valueAttribute.limit);
                }

              }
            }

          }
          if ( this.limitMasterForm.value.limitBasisTypeId !== defaultSelectValue ) {
                  if (this.limitMasterForm.value.limitDetails[i].validFrom == null) {
                this.checkerrcount++;
              }
              if (this.limitMasterForm.value.limitDetails[i].validTo == null) {
                this.checkerrcount++;
              }
              if (this.limitMasterForm.value.limitDetails[i].validFrom != null &&
                      this.limitMasterForm.value.limitDetails[i].validTo != null) {
                  if (this.limitMasterForm.value.limitDetails[i].validFrom > this.limitMasterForm.value.limitDetails[i].validTo) {
                      this.checkerrcount++;
                  }
                  if (this.limitMasterForm.value.limitDetails[i].validTo < this.limitMasterForm.value.limitDetails[i].validFrom) {
                      this.checkerrcount++;
                  }


              }
               if (this.limitMasterForm.value.limitDetails[i].isTemporary == null ||
                  this.limitMasterForm.value.limitDetails[i].isTemporary === defaultSelectValue ) {
                this.checkerrcount++;
          }
          }
        }

      }
    }
    console.log(' count --> ' + this.checkerrcount)

    if (this.checkerrcount !== 0) {

      // mandatory field
      this.errormodalflag = true;
      setTimeout(() => { this.errorModal.hide(); }, 3000);
      return false;
    } else {
      return true;
    }
  }

  onHidden() {
  this.errormodalflag = false;
  this.servererrorflag = false;
  }

  saveLimit () {
    if (this.isValidForm()) {
      this.isActionPerformed = true;
      if ( this.limitDetailsObj.limitHeaderId != null) {
         this.limitMasterForm.value.limitHeaderId = this.limitDetailsObj.limitHeaderId;
      }
        this.subscription = this.limitsMasterService.saveLimitMaster(this.limitMasterForm.value,  this.addoreditpage)
                                .subscribe(limitBasisDropDown => {
        this.limitBasisNameArr = limitBasisDropDown.body;
        this.router.navigate(['master/limit/limits-master']);
      },
        error => {
             this.limitsMasterService.msgStatusName = null;
             this.errorMessage = error;
             this.servererrorflag = true;
            setTimeout(() => { this.serverErrorModal.hide(); }, 3000);
        }
      );
    }

  }
   
 savedata() {

        if (this.addpage ) {
            this.addoreditpage = true;
            this.limitsMasterService.msgStatusName = save.toString();
        } else if (this.editpage) {
             this.limitMasterForm.value.statusName = this.limitDetailsObj.statusName;
             this.limitsMasterService.msgStatusName = update.toString();
            this.addoreditpage = false;
        }
         this.limitMasterForm.value.action = draft.toString();
      this.saveLimit();

    }
    drafttoactive() {
         this.addoreditpage = false;
        this.limitsMasterService.msgStatusName = active.toString();
        this.limitMasterForm.value.statusName = active.toString();
      this.limitMasterForm.value.action = active;
      this.saveLimit();
    }
     saveactive() {
        if (this.addpage ) {
           this.limitsMasterService.msgStatusName = active.toString();
            this.limitMasterForm.value.statusName = active.toString();
            this.addoreditpage = true;
        } else if (this.editpage) {
              this.limitsMasterService.msgStatusName = update.toString();
               this.limitMasterForm.value.statusName = this.limitDetailsObj.statusName;
            this.addoreditpage = false;
        }
      this.limitMasterForm.value.action = save.toString();
      this.saveLimit();

    }
    submitactivetodeactive() {
        this.addoreditpage = false;
        this.limitsMasterService.msgStatusName = deactive.toString();
        this.limitMasterForm.value.statusName = inactive.toString();
         this.isActiveStatus = true;
         this.statusmsg = msgconfirmdeactivate.toString();
         this.isDeletePopupModal = true;
    }
    savedeactive() {
      this.addoreditpage = false;
       this.limitsMasterService.msgStatusName = update.toString();
      this.limitMasterForm.value.statusName = this.limitDetailsObj.statusName;
      this.saveLimit();
    }
    submitdeactivetoreactive() {
      this.addoreditpage = false;
      this.limitsMasterService.msgStatusName = reactivate.toString();
      this.limitMasterForm.value.statusName = active.toString();
       this.deactiveStatus = true;
        this.statusmsg = msgconfirmreactive.toString();
       this.isDeletePopupModal = true;
    }

    // pop confirm active to deactive and deactive to active
    conf_deactive_reactive ( event) {
       this.deactiveStatus = false;
       this.isActiveStatus = false;
       this.deletePopUpModal1.hide();
       this.saveLimit();
    }
    hide() {
         this.isDeletePopupModal = false;
    }

  getStyletebasis() {
    if (this.req_limitbasis === true) {
      return '#d43c3c';
    } else {
      return '#cfdee7';
    }
  }
  getStyletebasisName() {
    if (this.req_limitBasisId === true) {
      return '#d43c3c';
    } else {
      return '#cfdee7';
    }
  }
  public showIsError(): void {
    this.serverErrorModal.show()
  }

  public hideIsErrorModal(): void {
    this.serverErrorModal.hide();
  }
   closeIsError() {
        this.hideIsErrorModal();
    }

}
