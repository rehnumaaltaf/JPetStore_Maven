import { Component, Input,  ViewEncapsulation, OnChanges } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { Subscription } from 'rxjs/Subscription';
// import { FormGroup, FormControl, FormArray, FormBuilder, ReactiveFormsModule, FormsModule } from '@angular/forms';
// import { BasePaymentService} from '../../base-payment/service/base-payment.service';
import { Router, ActivatedRoute } from '@angular/router';
import { LimitsMasterService } from '../service/limits-master.service';
import { LimitsMaster, LimitDetails, LimitsAttribute , defaultSelectValue } from '../model';

@Component({
  moduleId: module.id,
    selector: 'app-base-payment-mapping',
  templateUrl: 'add-multi-limits-master.component.html',
  encapsulation: ViewEncapsulation.None,

})
export class AddMultiLimitComponent implements  OnChanges {
    sectionName: string;

  checkerrcount: number;
  volumeValueArr = [];
  outrightListArr = [];

  volumeOrValueDetails = [];
  volumeOrValueDetailsbasis = [];
  volumeOrValueDetailsVega = [];
  volumeOrValueDetailsGamma = [];
  volumeOrValueDetailsDelta = [];
  volumeOrValueDetailsVaR = [];
  volumeOrValueDetailsDrawdown = [];
  volumeOrValueDetailsStructure = [];
  volumeOrValueDetailsforward = [];
  volumeOrValueDetailscurrent = [];
  volumeOrValueDetailstotal = [];
  volumeOrValueDetailsvalue = [];
  isTemporary: string;
  validFrom:  Date ;
  validTo:  Date ;
  limitDetails = new LimitDetails();
  limitMaster: LimitsMaster = new LimitsMaster();
  limitOutRightAttribute: LimitsAttribute = new LimitsAttribute();
  limitBasisAttribute: LimitsAttribute = new LimitsAttribute();
  limitStructureAttribute: LimitsAttribute = new LimitsAttribute();
  limitDrawDownAttribute: LimitsAttribute = new LimitsAttribute();
  limitVaRAttribute: LimitsAttribute = new LimitsAttribute();
  limitDeltaAttribute: LimitsAttribute = new LimitsAttribute();
  limitGammaAttribute: LimitsAttribute = new LimitsAttribute();
  limitVegaAttribute: LimitsAttribute = new LimitsAttribute();
  currentMonthAttribute:  LimitsAttribute = new LimitsAttribute();
  forwardMonthAttribute:  LimitsAttribute = new LimitsAttribute();
  totalAttribute:  LimitsAttribute = new LimitsAttribute();
  valueAttribute:  LimitsAttribute = new LimitsAttribute();

  limitsAttribute = new LimitsAttribute();
  limitDetailsListFinal: LimitDetails[] = [];
  limitDetailsId: string;
  defaultSelectValue: string;
  @Input('group')
  basePaymentMappingform: FormGroup;
  @Input() title: any;
  @Input() limitDetailsList: LimitsMaster;
  @Input() optionText: string;
 

  constructor(public limitmasterService: LimitsMasterService) {
  }

  setDefalutSelectValue() {
      // set default value for Volume or Currency code
    this.limitOutRightAttribute.limitVolumeValueRefId =  defaultSelectValue;
    this.limitBasisAttribute.limitVolumeValueRefId =  defaultSelectValue;
    this.limitStructureAttribute.limitVolumeValueRefId =  defaultSelectValue;
    this.limitDeltaAttribute.limitVolumeValueRefId =  defaultSelectValue;
    this.limitDrawDownAttribute.limitVolumeValueRefId =  defaultSelectValue;
    this.limitGammaAttribute.limitVolumeValueRefId =  defaultSelectValue;
    this.limitVaRAttribute.limitVolumeValueRefId =  defaultSelectValue;
    this.limitVegaAttribute.limitVolumeValueRefId =  defaultSelectValue;
    this.currentMonthAttribute.limitVolumeValueRefId =  defaultSelectValue;
    this.forwardMonthAttribute.limitVolumeValueRefId =  defaultSelectValue;
    this.totalAttribute.limitVolumeValueRefId =  defaultSelectValue;
    this.valueAttribute.limitVolumeValueRefId =  defaultSelectValue;
    // set default value for uom or currency code
    this.limitOutRightAttribute.limitVolumeUomIdCurrencycode =  defaultSelectValue.toString();
    this.limitBasisAttribute.limitVolumeUomIdCurrencycode =  defaultSelectValue.toString();
    this.limitStructureAttribute.limitVolumeUomIdCurrencycode =  defaultSelectValue.toString();
    this.limitDeltaAttribute.limitVolumeUomIdCurrencycode =  defaultSelectValue.toString();
    this.limitDrawDownAttribute.limitVolumeUomIdCurrencycode =  defaultSelectValue.toString();
    this.limitGammaAttribute.limitVolumeUomIdCurrencycode =  defaultSelectValue.toString();
    this.limitVaRAttribute.limitVolumeUomIdCurrencycode =  defaultSelectValue.toString();
    this.limitVegaAttribute.limitVolumeUomIdCurrencycode =  defaultSelectValue.toString();
    this.currentMonthAttribute.limitVolumeUomIdCurrencycode =  defaultSelectValue.toString();
    this.forwardMonthAttribute.limitVolumeUomIdCurrencycode =  defaultSelectValue.toString();
    this.totalAttribute.limitVolumeUomIdCurrencycode =  defaultSelectValue.toString();
    this.valueAttribute.limitVolumeUomIdCurrencycode =  defaultSelectValue.toString();
    this.isTemporary =  defaultSelectValue.toString();
  }
  ngOnChanges() {
      this.setDefalutSelectValue();
    if (this.limitDetailsList != null ) {
    if (this.limitDetailsList.limitDetails != null) {
        if (this.limitDetailsList.limitDetails[this.title] != null ) {
             if (this.limitDetailsList.limitDetails[this.title].limitDetailsId != null ) {
            this.limitDetailsId = this.limitDetailsList.limitDetails[this.title].limitDetailsId.toString();
        }
            if ( this.limitDetailsList.limitDetails[this.title].limitOutRightAttribute != null ) {
                this.limitOutRightAttribute = this.limitDetailsList.limitDetails[this.title].limitOutRightAttribute;
                if ( this.limitOutRightAttribute.limitVolumeValueRefId === 2 ) {
                    if (this.limitOutRightAttribute.limitCurrencyId != null ) {
                         this.limitOutRightAttribute.limitVolumeUomIdCurrencycode = this.limitOutRightAttribute.limitCurrencyId.toString();
                    }
                     this.getCurrencyOrUomOption(this.limitOutRightAttribute.limitVolumeValueRefName , 'Outright')
                }
                if ( this.limitOutRightAttribute.limitVolumeValueRefId === 1 ) {
                    if (this.limitOutRightAttribute.limitVolumeUomId != null ) {
                         this.limitOutRightAttribute.limitVolumeUomIdCurrencycode = this.limitOutRightAttribute.limitVolumeUomId.toString();
                    }
                    this.limitOutRightAttribute.limitVolumeUomIdCurrencycode = this.limitOutRightAttribute.limitVolumeUomId.toString();
                     this.getCurrencyOrUomOption(this.limitOutRightAttribute.limitVolumeValueRefName , 'Outright')
                }
            }
             if ( this.limitDetailsList.limitDetails[this.title].limitBasisAttribute != null ) {
                this.limitBasisAttribute = this.limitDetailsList.limitDetails[this.title].limitBasisAttribute;
                if ( this.limitBasisAttribute.limitVolumeValueRefId === 2 ) {
                    if (this.limitBasisAttribute.limitCurrencyId != null ) {
                          this.limitBasisAttribute.limitVolumeUomIdCurrencycode = this.limitBasisAttribute.limitCurrencyId.toString();
                    }
                     this.getCurrencyOrUomOption(this.limitBasisAttribute.limitVolumeValueRefName , 'Basis')
                }
                if ( this.limitBasisAttribute.limitVolumeValueRefId === 1 ) {
                    if (this.limitBasisAttribute.limitVolumeUomId != null ) {
                          this.limitBasisAttribute.limitVolumeUomIdCurrencycode = this.limitBasisAttribute.limitVolumeUomId.toString();
                    }
                     this.getCurrencyOrUomOption(this.limitBasisAttribute.limitVolumeValueRefName , 'Basis')
                }
            }
            if ( this.limitDetailsList.limitDetails[this.title].limitStructureAttribute != null ) {
                      this.limitStructureAttribute = this.limitDetailsList.limitDetails[this.title].limitStructureAttribute;
                if ( this.limitStructureAttribute.limitVolumeValueRefId === 2 ) {
                     if (this.limitStructureAttribute.limitCurrencyId != null ) {
                          this.limitStructureAttribute.limitVolumeUomIdCurrencycode =
                            this.limitStructureAttribute.limitCurrencyId.toString();
                    }
                    this.getCurrencyOrUomOption(this.limitStructureAttribute.limitVolumeValueRefName , 'Structure')
                }
                if ( this.limitStructureAttribute.limitVolumeValueRefId === 1 ) {
                      if (this.limitStructureAttribute.limitVolumeUomId != null ) {
                          this.limitStructureAttribute.limitVolumeUomIdCurrencycode =
                          this.limitStructureAttribute.limitVolumeUomId.toString();
                    }
                     this.getCurrencyOrUomOption(this.limitStructureAttribute.limitVolumeValueRefName , 'Structure')
                }
            }
            if ( this.limitDetailsList.limitDetails[this.title].limitDeltaAttribute != null ) {
                this.limitDeltaAttribute = this.limitDetailsList.limitDetails[this.title].limitDeltaAttribute;
                if ( this.limitDeltaAttribute.limitVolumeValueRefId === 2 ) {
                     if (this.limitDeltaAttribute.limitCurrencyId != null ) {
                         this.limitDeltaAttribute.limitVolumeUomIdCurrencycode = this.limitDeltaAttribute.limitCurrencyId.toString();
                    }
                     this.getCurrencyOrUomOption(this.limitDeltaAttribute.limitVolumeValueRefName , 'Delta')
                }
                if ( this.limitDeltaAttribute.limitVolumeValueRefId === 1 ) {
                     if (this.limitDeltaAttribute.limitVolumeUomId != null ) {
                         this.limitDeltaAttribute.limitVolumeUomIdCurrencycode = this.limitDeltaAttribute.limitVolumeUomId.toString();
                    }
                     this.getCurrencyOrUomOption(this.limitDeltaAttribute.limitVolumeValueRefName , 'Delta')
                }
            }
            if ( this.limitDetailsList.limitDetails[this.title].limitDrawDownAttribute != null ) {
                this.limitDrawDownAttribute = this.limitDetailsList.limitDetails[this.title].limitDrawDownAttribute;
                if ( this.limitDrawDownAttribute.limitVolumeValueRefId === 2 ) {
                     if (this.limitDrawDownAttribute.limitCurrencyId != null ) {
                         this.limitDrawDownAttribute.limitVolumeUomIdCurrencycode = this.limitDrawDownAttribute.limitCurrencyId.toString();
                    }
                    this.getCurrencyOrUomOption(this.limitDrawDownAttribute.limitVolumeValueRefName , 'Drawdown')
                }
                if ( this.limitDrawDownAttribute.limitVolumeValueRefId === 1 ) {
                    if (this.limitDrawDownAttribute.limitVolumeUomId != null ) {
                         this.limitDrawDownAttribute.limitVolumeUomIdCurrencycode = this.limitDrawDownAttribute.limitVolumeUomId.toString();
                    }
                    this.getCurrencyOrUomOption(this.limitDrawDownAttribute.limitVolumeValueRefName , 'Drawdown')
                }
            }
            if ( this.limitDetailsList.limitDetails[this.title].limitGammaAttribute != null ) {
                this.limitGammaAttribute = this.limitDetailsList.limitDetails[this.title].limitGammaAttribute;
                if ( this.limitGammaAttribute.limitVolumeValueRefId === 2 ) {
                    if (this.limitGammaAttribute.limitCurrencyId != null ) {
                         this.limitGammaAttribute.limitVolumeUomIdCurrencycode = this.limitGammaAttribute.limitCurrencyId.toString();
                    }
                    this.getCurrencyOrUomOption(this.limitGammaAttribute.limitVolumeValueRefName , 'Gamma')
                }
                if ( this.limitGammaAttribute.limitVolumeValueRefId === 1 ) {
                     if (this.limitGammaAttribute.limitVolumeUomId != null ) {
                         this.limitGammaAttribute.limitVolumeUomIdCurrencycode = this.limitGammaAttribute.limitVolumeUomId.toString();
                    }
                    this.getCurrencyOrUomOption(this.limitGammaAttribute.limitVolumeValueRefName , 'Gamma')
                }
            }
            if ( this.limitDetailsList.limitDetails[this.title].limitVaRAttribute != null ) {
                this.limitVaRAttribute = this.limitDetailsList.limitDetails[this.title].limitVaRAttribute;
                if ( this.limitVaRAttribute.limitVolumeValueRefId === 2 ) {
                    if (this.limitVaRAttribute.limitCurrencyId != null ) {
                         this.limitVaRAttribute.limitVolumeUomIdCurrencycode = this.limitVaRAttribute.limitCurrencyId.toString();
                    }
                    this.getCurrencyOrUomOption(this.limitVaRAttribute.limitVolumeValueRefName , 'VaR')
                }
                if ( this.limitVaRAttribute.limitVolumeValueRefId === 1 ) {
                     if (this.limitVaRAttribute.limitVolumeUomId != null ) {
                         this.limitVaRAttribute.limitVolumeUomIdCurrencycode = this.limitVaRAttribute.limitVolumeUomId.toString();
                    }
                    this.getCurrencyOrUomOption(this.limitVaRAttribute.limitVolumeValueRefName , 'VaR')
                }
            }
            if ( this.limitDetailsList.limitDetails[this.title].limitVegaAttribute != null ) {
                this.limitVegaAttribute = this.limitDetailsList.limitDetails[this.title].limitVegaAttribute;
                if ( this.limitVegaAttribute.limitVolumeValueRefId === 2 ) {
                    if (this.limitVegaAttribute.limitCurrencyId != null ) {
                         this.limitVegaAttribute.limitVolumeUomIdCurrencycode = this.limitVegaAttribute.limitCurrencyId.toString();
                    }
                    this.getCurrencyOrUomOption(this.limitVegaAttribute.limitVolumeValueRefName , 'Vega')
                }
                if ( this.limitVegaAttribute.limitVolumeValueRefId === 1 ) {
                     if (this.limitVegaAttribute.limitVolumeUomId != null ) {
                         this.limitVegaAttribute.limitVolumeUomIdCurrencycode = this.limitVegaAttribute.limitVolumeUomId.toString();
                    }
                    this.getCurrencyOrUomOption(this.limitVegaAttribute.limitVolumeValueRefName , 'Vega')
                }
            }
             if ( this.limitDetailsList.limitDetails[this.title].forwardMonthAttribute != null ) {
                this.forwardMonthAttribute = this.limitDetailsList.limitDetails[this.title].forwardMonthAttribute;
                if ( this.forwardMonthAttribute.limitVolumeValueRefId === 2 ) {
                     if (this.forwardMonthAttribute.limitCurrencyId != null ) {
                         this.forwardMonthAttribute.limitVolumeUomIdCurrencycode = this.forwardMonthAttribute.limitCurrencyId.toString();
                    }
                   this.getCurrencyOrUomOption(this.forwardMonthAttribute.limitVolumeValueRefName , 'ForwardMonth')
                }
                if ( this.forwardMonthAttribute.limitVolumeValueRefId === 1 ) {
                     if (this.forwardMonthAttribute.limitVolumeUomId != null ) {
                         this.forwardMonthAttribute.limitVolumeUomIdCurrencycode = this.forwardMonthAttribute.limitVolumeUomId.toString();
                    }
                    this.getCurrencyOrUomOption(this.forwardMonthAttribute.limitVolumeValueRefName , 'ForwardMonth')
                }
            }
             if ( this.limitDetailsList.limitDetails[this.title].currentMonthAttribute != null ) {
                this.currentMonthAttribute = this.limitDetailsList.limitDetails[this.title].currentMonthAttribute;
                if ( this.currentMonthAttribute.limitVolumeValueRefId === 2 ) {
                    if (this.currentMonthAttribute.limitCurrencyId != null ) {
                         this.currentMonthAttribute.limitVolumeUomIdCurrencycode = this.currentMonthAttribute.limitCurrencyId.toString();
                    }
                   this.getCurrencyOrUomOption(this.currentMonthAttribute.limitVolumeValueRefName , 'CurrentMonth')
                }
                if ( this.currentMonthAttribute.limitVolumeValueRefId === 1 ) {
                    if (this.currentMonthAttribute.limitVolumeUomId != null ) {
                         this.currentMonthAttribute.limitVolumeUomIdCurrencycode = this.currentMonthAttribute.limitVolumeUomId.toString();
                    }
                     this.getCurrencyOrUomOption(this.currentMonthAttribute.limitVolumeValueRefName , 'CurrentMonth')
                }
            }
             if ( this.limitDetailsList.limitDetails[this.title].totalAttribute != null ) {
                this.totalAttribute = this.limitDetailsList.limitDetails[this.title].totalAttribute;
                if ( this.totalAttribute.limitVolumeValueRefId === 2 ) {
                     if (this.totalAttribute.limitCurrencyId != null ) {
                         this.totalAttribute.limitVolumeUomIdCurrencycode = this.totalAttribute.limitCurrencyId.toString();
                    }
                    this.getCurrencyOrUomOption(this.totalAttribute.limitVolumeValueRefName , 'totalAttribute')
                }
                if ( this.totalAttribute.limitVolumeValueRefId === 1 ) {
                    if (this.totalAttribute.limitVolumeUomId != null ) {
                         this.totalAttribute.limitVolumeUomIdCurrencycode = this.totalAttribute.limitVolumeUomId.toString();
                    }
                    this.getCurrencyOrUomOption(this.totalAttribute.limitVolumeValueRefName , 'totalAttribute')
                }
            }
            if ( this.limitDetailsList.limitDetails[this.title].valueAttribute != null ) {
                this.valueAttribute = this.limitDetailsList.limitDetails[this.title].valueAttribute;
                if ( this.valueAttribute.limitVolumeValueRefId === 2 ) {
                     if (this.valueAttribute.limitCurrencyId != null ) {
                         this.valueAttribute.limitVolumeUomIdCurrencycode = this.valueAttribute.limitCurrencyId.toString();
                    }
                   this.getCurrencyOrUomOption(this.valueAttribute.limitVolumeValueRefName , 'valueAttribute')
                }
                if ( this.valueAttribute.limitVolumeValueRefId === 1 ) {
                     if (this.valueAttribute.limitVolumeUomId != null ) {
                         this.valueAttribute.limitVolumeUomIdCurrencycode = this.valueAttribute.limitVolumeUomId.toString();
                    }
                   this.getCurrencyOrUomOption(this.valueAttribute.limitVolumeValueRefName , 'valueAttribute')
                }
            }
             if ( this.limitDetailsList.limitDetails[this.title].isTemporary != null ) {
                this.isTemporary = this.limitDetailsList.limitDetails[this.title].isTemporary
            }
             if ( this.limitDetailsList.limitDetails[this.title].validFrom != null ) {
                 this.validFrom = new Date(this.limitDetailsList.limitDetails[this.title].validFrom);
            }else {
                    this.validFrom = new Date();
             }
             if ( this.limitDetailsList.limitDetails[this.title].validTo != null ) {
                this.validTo = new Date(this.limitDetailsList.limitDetails[this.title].validTo);
            }else {
                    this.validTo = new Date();
            }

        }
    }
}

  }
  numberonly(evt) {
         const  charCode = (evt.which) ? evt.which : evt.keyCode;
          if (charCode !== 46 && charCode > 31 && (charCode < 48 || charCode > 57)) {
             return false;
          }
          return true;
    }

  onVolumeValueChange($event, sectionName) {
    const selectElement = $event.target;
    const optionIndex = selectElement.selectedIndex;
    const optionText = selectElement.options[optionIndex].text;
    this.getCurrencyOrUomOption(optionText , sectionName);
   }

   getCurrencyOrUomOption(optionText , sectionName) {
    if (optionText === 'Volume' || optionText === 'VOLUME') {
      if (sectionName === 'Outright') {
          this.volumeOrValueDetails = this.limitmasterService.volumeList;
      } else if (sectionName === 'Basis') {
        this.volumeOrValueDetailsbasis = this.limitmasterService.volumeList;
      } else if (sectionName === 'Structure') {
        this.volumeOrValueDetailsStructure = this.limitmasterService.volumeList;
      } else if (sectionName === 'Drawdown') {
         this.volumeOrValueDetailsDrawdown = this.limitmasterService.volumeList;
      } else if (sectionName === 'VaR') {
         this.volumeOrValueDetailsVaR = this.limitmasterService.volumeList;
      } else if (sectionName === 'Delta') {
         this.volumeOrValueDetailsDelta = this.limitmasterService.volumeList;
      } else if (sectionName === 'Gamma') {
         this.volumeOrValueDetailsGamma = this.limitmasterService.volumeList;
      } else if (sectionName === 'Vega') {
        this.volumeOrValueDetailsVega = this.limitmasterService.volumeList;
      } else if (sectionName === 'CurrentMonth') {
        this.volumeOrValueDetailscurrent = this.limitmasterService.volumeList;
      }  else if (sectionName === 'ForwardMonth') {
        this.volumeOrValueDetailsforward = this.limitmasterService.volumeList;
      }else if (sectionName === 'totalAttribute') {
        this.volumeOrValueDetailstotal = this.limitmasterService.volumeList;
      }  else if (sectionName === 'valueAttribute') {
        this.volumeOrValueDetailsvalue = this.limitmasterService.volumeList;
      }

    } else if (optionText === 'Value' || optionText === 'VALUE') {
      if (sectionName === 'Outright') {
         this.volumeOrValueDetails = this.limitmasterService.valueList;
      } else if (sectionName === 'Basis') {
        this.volumeOrValueDetailsbasis = this.limitmasterService.valueList;
      } else if (sectionName === 'Structure') {
         this.volumeOrValueDetailsStructure = this.limitmasterService.valueList;
      } else if (sectionName === 'Drawdown') {
         this.volumeOrValueDetailsDrawdown = this.limitmasterService.valueList;
      } else if (sectionName === 'VaR') {
        this.volumeOrValueDetailsVaR = this.limitmasterService.valueList;
      } else if (sectionName === 'Delta') {
         this.volumeOrValueDetailsDelta = this.limitmasterService.valueList;
      } else if (sectionName === 'Gamma') {
        this.volumeOrValueDetailsGamma = this.limitmasterService.valueList;
      } else if (sectionName === 'Vega') {
        this.volumeOrValueDetailsVega = this.limitmasterService.valueList;
      } else if (sectionName === 'CurrentMonth') {
        this.volumeOrValueDetailscurrent = this.limitmasterService.valueList;
      }  else if (sectionName === 'ForwardMonth') {
        this.volumeOrValueDetailsforward = this.limitmasterService.valueList;
      }else if (sectionName === 'totalAttribute') {
        this.volumeOrValueDetailstotal = this.limitmasterService.valueList;
      }  else if (sectionName === 'valueAttribute') {
        this.volumeOrValueDetailsvalue = this.limitmasterService.valueList;
      }
    }
   }


req_isTemporary(value) {
     if ( this.limitmasterService.limitMaster != null ) {
    if ( this.limitmasterService.limitMaster.limitDetails != null ) {
        if ( this.limitmasterService.limitMaster.limitBasisTypeId !== defaultSelectValue) {
             this.checkerrcount = 0;
        if ( ! this.limitmasterService.limitMaster.limitDetails[value].isTemporary ||
                 this.limitmasterService.limitMaster.limitDetails[value].isTemporary === defaultSelectValue.toString()) {
            this.checkerrcount++;
        }
        if ( this.checkerrcount !== 0) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
    return false;
   }
    return false;

 }
req_validity(value) {
     if ( this.limitmasterService.limitMaster != null ) {
    if ( this.limitmasterService.limitMaster.limitDetails != null ) {
        this.checkerrcount = 0;
        if ( !this.limitmasterService.limitMaster.limitDetails[value].validFrom ) {
            if ( this.limitmasterService.limitMaster.limitDetails[value].validFrom == null) {
            this.checkerrcount++;
         }
        }
        if ( !this.limitmasterService.limitMaster.limitDetails[value].validTo ) {
             if (this.limitmasterService.limitMaster.limitDetails[value].validTo == null) {

            this.checkerrcount++;
         }
        }
     }
     if ( this.checkerrcount !== 0) {
                return true;
            } else {
                return false;
            }
    }

    return false;
}

req_validity_betweendate(value) {
     if ( this.limitmasterService.limitMaster != null ) {
    if ( this.limitmasterService.limitMaster.limitDetails != null ) {
        this.checkerrcount = 0;
        if (this.limitmasterService.limitMaster.limitDetails[value].validFrom != null &&
                     this.limitmasterService.limitMaster.limitDetails[value].validFrom != null) {
              if (this.limitmasterService.limitMaster.limitDetails[value].validFrom >
                             this.limitmasterService.limitMaster.limitDetails[value].validTo) {
                             this.checkerrcount++;
              }
               if (this.limitmasterService.limitMaster.limitDetails[value].validTo <
                             this.limitmasterService.limitMaster.limitDetails[value].validFrom) {
                             this.checkerrcount++;
              }

          }
        }
     if ( this.checkerrcount !== 0) {
                return true;
            } else {
                return false;
            }
    }
    return false;
}

req_vega(value) {
  if ( this.limitmasterService.limitMaster != null ) {
      if (this.limitmasterService.limitMaster.limitBasisTypeId !== defaultSelectValue &&  
      this.limitmasterService.limitMaster.limitBasisTypeId !== 19 && this.limitmasterService.limitMaster.limitBasisTypeId !== 20 ) {
   if ( this.limitmasterService.limitMaster.limitDetails != null ) {
     this.checkerrcount = 0;
     if ( this.limitmasterService.limitMaster.limitDetails[value].limitVegaAttribute.limitVolumeValueRefId == null ||
            this.limitmasterService.limitMaster.limitDetails[value].limitVegaAttribute.limitVolumeValueRefId === defaultSelectValue ||
       this.limitmasterService.limitMaster.limitDetails[value].limitVegaAttribute.limitVolumeUomIdCurrencycode === defaultSelectValue.toString() ) {
          return true;
      } else  if (this.limitmasterService.limitMaster.limitDetails[value].limitVegaAttribute.limitVolumeValueRefId != null) {

         if (!this.limitmasterService.limitMaster.limitDetails[value].limitVegaAttribute.limitVolumeUomIdCurrencycode) {
                this.checkerrcount++;
           }else if (this.limitmasterService.limitMaster.limitDetails[value].limitVegaAttribute.limitVolumeUomIdCurrencycode == null) {
               this.checkerrcount++;
           }
           if (!this.limitmasterService.limitMaster.limitDetails[value].limitVegaAttribute.limit) {
                this.checkerrcount++;
           }else if (this.limitmasterService.limitMaster.limitDetails[value].limitVegaAttribute.limit == null) {
               this.checkerrcount++;
           }else if ( this.limitmasterService.limitMaster.limitDetails[value].limitVegaAttribute.limit != null) {
              if (Number(this.limitmasterService.limitMaster.limitDetails[value].limitVegaAttribute.limit) == null ) {
                 this.checkerrcount++;
              }
            }
            if ( this.checkerrcount !== 0) {
                return true;
            } else {
                return false;
            }

        }

   }
     return false;
   }
   return false;
}
   return false;
}
req_gamma(value) {
  if ( this.limitmasterService.limitMaster != null ) {
      if (this.limitmasterService.limitMaster.limitBasisTypeId !== defaultSelectValue && 
      this.limitmasterService.limitMaster.limitBasisTypeId !== 19 && this.limitmasterService.limitMaster.limitBasisTypeId !== 20 ) {
   if ( this.limitmasterService.limitMaster.limitDetails != null ) {
     this.checkerrcount = 0;
     if ( this.limitmasterService.limitMaster.limitDetails[value].limitGammaAttribute.limitVolumeValueRefId == null ||
                this.limitmasterService.limitMaster.limitDetails[value].limitGammaAttribute.limitVolumeValueRefId === defaultSelectValue ||
             this.limitmasterService.limitMaster.limitDetails[value].limitGammaAttribute.limitVolumeUomIdCurrencycode === defaultSelectValue.toString()) {
          return true;
      } else  if (this.limitmasterService.limitMaster.limitDetails[value].limitGammaAttribute.limitVolumeValueRefId != null) {

         if (!this.limitmasterService.limitMaster.limitDetails[value].limitGammaAttribute.limitVolumeUomIdCurrencycode) {
                this.checkerrcount++;
           }else if (this.limitmasterService.limitMaster.limitDetails[value].limitGammaAttribute.limitVolumeUomIdCurrencycode == null) {
               this.checkerrcount++;
           }
           if (!this.limitmasterService.limitMaster.limitDetails[value].limitGammaAttribute.limit) {
                this.checkerrcount++;
           }else if (this.limitmasterService.limitMaster.limitDetails[value].limitGammaAttribute.limit == null) {
               this.checkerrcount++;
           }else if ( this.limitmasterService.limitMaster.limitDetails[value].limitGammaAttribute.limit != null) {
              if (Number(this.limitmasterService.limitMaster.limitDetails[value].limitGammaAttribute.limit) == null ) {
                 this.checkerrcount++;
              }
            }
            if ( this.checkerrcount !== 0) {
                return true;
            } else {
                return false;
            }

        }

   }
   return false;
}
return false;
}
   return false;
}
req_delta(value) {
  if ( this.limitmasterService.limitMaster != null ) {
      if (this.limitmasterService.limitMaster.limitBasisTypeId !== defaultSelectValue
      && this.limitmasterService.limitMaster.limitBasisTypeId !== 19 && this.limitmasterService.limitMaster.limitBasisTypeId !== 20 ) {
   if ( this.limitmasterService.limitMaster.limitDetails != null ) {
     this.checkerrcount = 0;
     if ( this.limitmasterService.limitMaster.limitDetails[value].limitDeltaAttribute.limitVolumeValueRefId == null ||
            this.limitmasterService.limitMaster.limitDetails[value].limitDeltaAttribute.limitVolumeValueRefId === defaultSelectValue ||
             this.limitmasterService.limitMaster.limitDetails[value].limitDeltaAttribute.limitVolumeUomIdCurrencycode === defaultSelectValue.toString()) {
          return true;
      } else  if (this.limitmasterService.limitMaster.limitDetails[value].limitDeltaAttribute.limitVolumeValueRefId != null) {

         if (!this.limitmasterService.limitMaster.limitDetails[value].limitDeltaAttribute.limitVolumeUomIdCurrencycode) {
                this.checkerrcount++;
           }else if (this.limitmasterService.limitMaster.limitDetails[value].limitDeltaAttribute.limitVolumeUomIdCurrencycode == null) {
               this.checkerrcount++;
           }
           if (!this.limitmasterService.limitMaster.limitDetails[value].limitDeltaAttribute.limit) {
                this.checkerrcount++;
           }else if (this.limitmasterService.limitMaster.limitDetails[value].limitDeltaAttribute.limit == null) {
               this.checkerrcount++;
           }else if ( this.limitmasterService.limitMaster.limitDetails[value].limitDeltaAttribute.limit != null) {
              if (Number(this.limitmasterService.limitMaster.limitDetails[value].limitDeltaAttribute.limit) == null ) {
                 this.checkerrcount++;
              }
            }
            if ( this.checkerrcount !== 0) {
                return true;
            } else {
                return false;
            }

        }

   }
   return false;
}
return false;
}
   return false;
}
req_var(value) {
  if ( this.limitmasterService.limitMaster != null ) {
      if (this.limitmasterService.limitMaster.limitBasisTypeId !== defaultSelectValue &&
         this.limitmasterService.limitMaster.limitBasisTypeId !== 19 && this.limitmasterService.limitMaster.limitBasisTypeId !== 20 ) {
   if ( this.limitmasterService.limitMaster.limitDetails != null ) {
     this.checkerrcount = 0;
     if ( this.limitmasterService.limitMaster.limitDetails[value].limitVaRAttribute.limitVolumeValueRefId == null ||
            this.limitmasterService.limitMaster.limitDetails[value].limitVaRAttribute.limitVolumeValueRefId === defaultSelectValue ||
             this.limitmasterService.limitMaster.limitDetails[value].limitVaRAttribute.limitVolumeUomIdCurrencycode === defaultSelectValue.toString()) {
          return true;
      } else  if (this.limitmasterService.limitMaster.limitDetails[value].limitVaRAttribute.limitVolumeValueRefId != null) {

         if (!this.limitmasterService.limitMaster.limitDetails[value].limitVaRAttribute.limitVolumeUomIdCurrencycode) {
                this.checkerrcount++;
           }else if (this.limitmasterService.limitMaster.limitDetails[value].limitVaRAttribute.limitVolumeUomIdCurrencycode == null) {
               this.checkerrcount++;
           }
           if (!this.limitmasterService.limitMaster.limitDetails[value].limitVaRAttribute.limit) {
                this.checkerrcount++;
           }else if (this.limitmasterService.limitMaster.limitDetails[value].limitVaRAttribute.limit == null) {
               this.checkerrcount++;
           }else if ( this.limitmasterService.limitMaster.limitDetails[value].limitVaRAttribute.limit != null) {
              if (Number(this.limitmasterService.limitMaster.limitDetails[value].limitVaRAttribute.limit) == null ) {
                 this.checkerrcount++;
              }
            }
            if ( this.checkerrcount !== 0) {
                return true;
            } else {
                return false;
            }

        }

   }
  return false;
   }
   return false;
}
   return false;
}
req_drawdown(value) {
  if ( this.limitmasterService.limitMaster != null ) {
      if ( this.limitmasterService.limitMaster.limitBasisTypeId !== defaultSelectValue 
      && this.limitmasterService.limitMaster.limitBasisTypeId !== 19 && this.limitmasterService.limitMaster.limitBasisTypeId !== 20 ) {
   if ( this.limitmasterService.limitMaster.limitDetails != null ) {
     this.checkerrcount = 0;
     if ( this.limitmasterService.limitMaster.limitDetails[value].limitDrawDownAttribute.limitVolumeValueRefId == null ||
     this.limitmasterService.limitMaster.limitDetails[value].limitDrawDownAttribute.limitVolumeValueRefId === defaultSelectValue ||
      this.limitmasterService.limitMaster.limitDetails[value].limitGammaAttribute.limitVolumeUomIdCurrencycode === defaultSelectValue.toString() ) {
          return true;
      } else  if (this.limitmasterService.limitMaster.limitDetails[value].limitDrawDownAttribute.limitVolumeValueRefId != null) {

         if (!this.limitmasterService.limitMaster.limitDetails[value].limitDrawDownAttribute.limitVolumeUomIdCurrencycode) {
                this.checkerrcount++;
           }else if (this.limitmasterService.limitMaster.limitDetails[value].limitDrawDownAttribute.limitVolumeUomIdCurrencycode == null) {
               this.checkerrcount++;
           }
           if (!this.limitmasterService.limitMaster.limitDetails[value].limitDrawDownAttribute.limit) {
                this.checkerrcount++;
           }else if (this.limitmasterService.limitMaster.limitDetails[value].limitDrawDownAttribute.limit == null) {
               this.checkerrcount++;
           }else if ( this.limitmasterService.limitMaster.limitDetails[value].limitDrawDownAttribute.limit != null) {
              if (Number(this.limitmasterService.limitMaster.limitDetails[value].limitDrawDownAttribute.limit) == null ) {
                 this.checkerrcount++;
              }
            }
            if ( this.checkerrcount !== 0) {
                return true;
            } else {
                return false;
            }

        }

   }
   return false;
}
return false;
}
   return false;
}
req_structure(value) {
  if ( this.limitmasterService.limitMaster != null ) {
      if ( this.limitmasterService.limitMaster.limitBasisTypeId !== defaultSelectValue
       && this.limitmasterService.limitMaster.limitBasisTypeId !== 19 && this.limitmasterService.limitMaster.limitBasisTypeId !== 20 ) {
   if ( this.limitmasterService.limitMaster.limitDetails != null ) {
     this.checkerrcount = 0;
     if ( this.limitmasterService.limitMaster.limitDetails[value].limitStructureAttribute.limitVolumeValueRefId == null ||
            this.limitmasterService.limitMaster.limitDetails[value].limitStructureAttribute.limitVolumeValueRefId === defaultSelectValue ||
             this.limitmasterService.limitMaster.limitDetails[value].limitStructureAttribute.limitVolumeUomIdCurrencycode === defaultSelectValue.toString()) {
          return true;
      } else  if (this.limitmasterService.limitMaster.limitDetails[value].limitStructureAttribute.limitVolumeValueRefId != null) {

         if (!this.limitmasterService.limitMaster.limitDetails[value].limitStructureAttribute.limitVolumeUomIdCurrencycode) {
                this.checkerrcount++;
           }else if (this.limitmasterService.limitMaster.limitDetails[value].limitStructureAttribute.limitVolumeUomIdCurrencycode == null) {
               this.checkerrcount++;
           }
           if (!this.limitmasterService.limitMaster.limitDetails[value].limitStructureAttribute.limit) {
                this.checkerrcount++;
           }else if (this.limitmasterService.limitMaster.limitDetails[value].limitStructureAttribute.limit == null) {
               this.checkerrcount++;
           }else if ( this.limitmasterService.limitMaster.limitDetails[value].limitStructureAttribute.limit != null) {
              if (Number(this.limitmasterService.limitMaster.limitDetails[value].limitStructureAttribute.limit) == null ) {
                 this.checkerrcount++;
              }
            }
            if ( this.checkerrcount !== 0) {
                return true;
            } else {
                return false;
            }

        }

   }
 return false;
   }
   return false;
}
   return false;
}
req_basis(value) {
   if ( this.limitmasterService.limitMaster != null ) {
   if ( this.limitmasterService.limitMaster.limitBasisTypeId !== defaultSelectValue
     && this.limitmasterService.limitMaster.limitBasisTypeId !== 19 && this.limitmasterService.limitMaster.limitBasisTypeId !== 20 ) {
   if ( this.limitmasterService.limitMaster.limitDetails != null ) {
     this.checkerrcount = 0;
     if ( this.limitmasterService.limitMaster.limitDetails[value].limitBasisAttribute.limitVolumeValueRefId == null ||
        this.limitmasterService.limitMaster.limitDetails[value].limitBasisAttribute.limitVolumeValueRefId === defaultSelectValue ||
         this.limitmasterService.limitMaster.limitDetails[value].limitBasisAttribute.limitVolumeUomIdCurrencycode === defaultSelectValue.toString()) {
          return true;
      } else  if (this.limitmasterService.limitMaster.limitDetails[value].limitBasisAttribute.limitVolumeValueRefId != null) {

         if (!this.limitmasterService.limitMaster.limitDetails[value].limitBasisAttribute.limitVolumeUomIdCurrencycode) {
                this.checkerrcount++;
           }else if (this.limitmasterService.limitMaster.limitDetails[value].limitBasisAttribute.limitVolumeUomIdCurrencycode == null) {
               this.checkerrcount++;
           }
           if (!this.limitmasterService.limitMaster.limitDetails[value].limitBasisAttribute.limit) {
                this.checkerrcount++;
           }else if (this.limitmasterService.limitMaster.limitDetails[value].limitBasisAttribute.limit == null) {
               this.checkerrcount++;
           }else if ( this.limitmasterService.limitMaster.limitDetails[value].limitBasisAttribute.limit != null) {
              if (Number(this.limitmasterService.limitMaster.limitDetails[value].limitBasisAttribute.limit) == null ) {
                 this.checkerrcount++;
              }
            }
            if ( this.checkerrcount !== 0) {
                return true;
            } else {
                return false;
            }

        }

   }
  return false;
   }
   return false;
}
   return false;
}
req_outright(value) {
       if ( this.limitmasterService.limitMaster != null ) {
    if ( this.limitmasterService.limitMaster.limitBasisTypeId !== defaultSelectValue
    && this.limitmasterService.limitMaster.limitBasisTypeId !== 19 && this.limitmasterService.limitMaster.limitBasisTypeId !== 20 ) {
   if ( this.limitmasterService.limitMaster.limitDetails != null ) {
     this.checkerrcount = 0;
     if ( this.limitmasterService.limitMaster.limitDetails[value].limitOutRightAttribute.limitVolumeValueRefId == null ||
            this.limitmasterService.limitMaster.limitDetails[value].limitOutRightAttribute.limitVolumeValueRefId === defaultSelectValue ||
            this.limitmasterService.limitMaster.limitDetails[value].limitOutRightAttribute.limitVolumeUomIdCurrencycode === defaultSelectValue.toString()) {
          return true;
      } else  if (this.limitmasterService.limitMaster.limitDetails[value].limitOutRightAttribute.limitVolumeValueRefId != null) {

         if (!this.limitmasterService.limitMaster.limitDetails[value].limitOutRightAttribute.limitVolumeUomIdCurrencycode) {
                this.checkerrcount++;
           }else if (this.limitmasterService.limitMaster.limitDetails[value].limitOutRightAttribute.limitVolumeUomIdCurrencycode == null) {
               this.checkerrcount++;
           }
           if (!this.limitmasterService.limitMaster.limitDetails[value].limitOutRightAttribute.limit) {
                this.checkerrcount++;
           }else if (this.limitmasterService.limitMaster.limitDetails[value].limitOutRightAttribute.limit == null) {
               this.checkerrcount++;
           }else if ( this.limitmasterService.limitMaster.limitDetails[value].limitOutRightAttribute.limit != null) {
              if (Number(this.limitmasterService.limitMaster.limitDetails[value].limitOutRightAttribute.limit) == null ) {
                 this.checkerrcount++;
              }
            }
            if ( this.checkerrcount !== 0) {
                return true;
            } else {
                return false;
            }

        }

   }
  return false;
   }
   return false;
}
   return false;
}

 req_current(value) {
   if ( this.limitmasterService.limitMaster != null ) {
       if (this.limitmasterService.limitMaster.limitBasisTypeId === 19 ) {
   if ( this.limitmasterService.limitMaster.limitDetails != null ) {
     this.checkerrcount = 0;
     if ( this.limitmasterService.limitMaster.limitDetails[value].currentMonthAttribute.limitVolumeValueRefId == null ||
            this.limitmasterService.limitMaster.limitDetails[value].currentMonthAttribute.limitVolumeValueRefId === defaultSelectValue ||
             this.limitmasterService.limitMaster.limitDetails[value].currentMonthAttribute.limitVolumeUomIdCurrencycode === defaultSelectValue.toString()) {
          return true;
      } else  if (this.limitmasterService.limitMaster.limitDetails[value].currentMonthAttribute.limitVolumeValueRefId != null) {

         if (!this.limitmasterService.limitMaster.limitDetails[value].currentMonthAttribute.limitVolumeUomIdCurrencycode) {
                this.checkerrcount++;
           }else if (this.limitmasterService.limitMaster.limitDetails[value].currentMonthAttribute.limitVolumeUomIdCurrencycode == null) {
               this.checkerrcount++;
           }
           if (!this.limitmasterService.limitMaster.limitDetails[value].currentMonthAttribute.limit) {
                this.checkerrcount++;
           }else if (this.limitmasterService.limitMaster.limitDetails[value].currentMonthAttribute.limit == null) {
               this.checkerrcount++;
           }else if ( this.limitmasterService.limitMaster.limitDetails[value].currentMonthAttribute.limit != null) {
              if (Number(this.limitmasterService.limitMaster.limitDetails[value].currentMonthAttribute.limit) == null ) {
                 this.checkerrcount++;
              }
            }
            if ( this.checkerrcount !== 0) {
                return true;
            } else {
                return false;
            }

        }

   }
   return false;
}
return false;
}
   return false;
}
  req_forward(value) {
   if ( this.limitmasterService.limitMaster != null ) {
       if (this.limitmasterService.limitMaster.limitBasisTypeId === 19 ) {
   if ( this.limitmasterService.limitMaster.limitDetails != null ) {
     this.checkerrcount = 0;
     if ( this.limitmasterService.limitMaster.limitDetails[value].forwardMonthAttribute.limitVolumeValueRefId == null ||
            this.limitmasterService.limitMaster.limitDetails[value].forwardMonthAttribute.limitVolumeValueRefId === defaultSelectValue ||
            this.limitmasterService.limitMaster.limitDetails[value].forwardMonthAttribute.limitVolumeUomIdCurrencycode === defaultSelectValue.toString()) {
          return true;
      } else  if (this.limitmasterService.limitMaster.limitDetails[value].forwardMonthAttribute.limitVolumeValueRefId != null) {

         if (!this.limitmasterService.limitMaster.limitDetails[value].forwardMonthAttribute.limitVolumeUomIdCurrencycode) {
                this.checkerrcount++;
           }else if (this.limitmasterService.limitMaster.limitDetails[value].forwardMonthAttribute.limitVolumeUomIdCurrencycode == null) {
               this.checkerrcount++;
           }
           if (!this.limitmasterService.limitMaster.limitDetails[value].forwardMonthAttribute.limit) {
                this.checkerrcount++;
           }else if (this.limitmasterService.limitMaster.limitDetails[value].forwardMonthAttribute.limit == null) {
               this.checkerrcount++;
           }else if ( this.limitmasterService.limitMaster.limitDetails[value].forwardMonthAttribute.limit != null) {
              if (Number(this.limitmasterService.limitMaster.limitDetails[value].forwardMonthAttribute.limit) == null ) {
                 this.checkerrcount++;
              }
            }
            if ( this.checkerrcount !== 0) {
                return true;
            } else {
                return false;
            }

        }

   }
   return false;
}
return false;
}
   return false;
}
  req_total(value) {
   if ( this.limitmasterService.limitMaster != null ) {
        if (this.limitmasterService.limitMaster.limitBasisTypeId === 19 ) {
   if ( this.limitmasterService.limitMaster.limitDetails != null ) {
     this.checkerrcount = 0;
     if ( this.limitmasterService.limitMaster.limitDetails[value].totalAttribute.limitVolumeValueRefId == null ||
        this.limitmasterService.limitMaster.limitDetails[value].totalAttribute.limitVolumeValueRefId === defaultSelectValue ||
         this.limitmasterService.limitMaster.limitDetails[value].totalAttribute.limitVolumeUomIdCurrencycode === defaultSelectValue.toString()) {
          return true;
      } else  if (this.limitmasterService.limitMaster.limitDetails[value].totalAttribute.limitVolumeValueRefId != null) {

         if (!this.limitmasterService.limitMaster.limitDetails[value].totalAttribute.limitVolumeUomIdCurrencycode) {
                this.checkerrcount++;
           }else if (this.limitmasterService.limitMaster.limitDetails[value].totalAttribute.limitVolumeUomIdCurrencycode == null) {
               this.checkerrcount++;
           }
           if (!this.limitmasterService.limitMaster.limitDetails[value].totalAttribute.limit) {
                this.checkerrcount++;
           }else if (this.limitmasterService.limitMaster.limitDetails[value].totalAttribute.limit == null) {
               this.checkerrcount++;
           }else if ( this.limitmasterService.limitMaster.limitDetails[value].totalAttribute.limit != null) {
              if (Number(this.limitmasterService.limitMaster.limitDetails[value].totalAttribute.limit) == null ) {
                 this.checkerrcount++;
              }
            }
            if ( this.checkerrcount !== 0) {
                return true;
            } else {
                return false;
            }

        }

   }
  return false;
   }
   return false;
}
   return false;
}
  req_value(value) {
   if ( this.limitmasterService.limitMaster != null ) {
          if (this.limitmasterService.limitMaster.limitBasisTypeId === 20 ) {
   if ( this.limitmasterService.limitMaster.limitDetails != null ) {
     this.checkerrcount = 0;
     if ( this.limitmasterService.limitMaster.limitDetails[value].valueAttribute.limitVolumeValueRefId == null ||
     this.limitmasterService.limitMaster.limitDetails[value].valueAttribute.limitVolumeValueRefId === defaultSelectValue ||
      this.limitmasterService.limitMaster.limitDetails[value].valueAttribute.limitVolumeUomIdCurrencycode === defaultSelectValue.toString()) {
          return true;
      } else  if (this.limitmasterService.limitMaster.limitDetails[value].valueAttribute.limitVolumeValueRefId != null) {

         if (!this.limitmasterService.limitMaster.limitDetails[value].valueAttribute.limitVolumeUomIdCurrencycode) {
                this.checkerrcount++;
           }else if (this.limitmasterService.limitMaster.limitDetails[value].valueAttribute.limitVolumeUomIdCurrencycode == null) {
               this.checkerrcount++;
           }
           if (!this.limitmasterService.limitMaster.limitDetails[value].valueAttribute.limit) {
                this.checkerrcount++;
           }else if (this.limitmasterService.limitMaster.limitDetails[value].valueAttribute.limit == null) {
               this.checkerrcount++;
           }else if ( this.limitmasterService.limitMaster.limitDetails[value].valueAttribute.limit != null) {
              if (Number(this.limitmasterService.limitMaster.limitDetails[value].valueAttribute.limit) == null ) {
                 this.checkerrcount++;
              }
            }
            if ( this.checkerrcount !== 0) {
                return true;
            } else {
                return false;
            }

        }

   }
   return false;
}
return false;
}
   return false;
}
  getStyleoutright(value) {
   if ( this.limitmasterService.limitMaster != null ) {
       if ( this.limitmasterService.limitMaster.limitBasisTypeId !== defaultSelectValue  &&
       this.limitmasterService.limitMaster.limitBasisTypeId !== 19 && this.limitmasterService.limitMaster.limitBasisTypeId !== 20 ) {
   if ( this.limitmasterService.limitMaster.limitDetails != null ) {
     this.checkerrcount = 0;
     if ( this.limitmasterService.limitMaster.limitDetails[value].limitOutRightAttribute.limitVolumeValueRefId == null ||
            this.limitmasterService.limitMaster.limitDetails[value].limitOutRightAttribute.limitVolumeValueRefId === defaultSelectValue ||
             this.limitmasterService.limitMaster.limitDetails[value].limitOutRightAttribute.limitVolumeUomIdCurrencycode === defaultSelectValue.toString() ) {
         return '#d43c3c';
      } else  if (this.limitmasterService.limitMaster.limitDetails[value].limitOutRightAttribute.limitVolumeValueRefId != null) {

         if (!this.limitmasterService.limitMaster.limitDetails[value].limitOutRightAttribute.limitVolumeUomIdCurrencycode) {
                this.checkerrcount++;
           }else if (this.limitmasterService.limitMaster.limitDetails[value].limitOutRightAttribute.limitVolumeUomIdCurrencycode == null) {
               this.checkerrcount++;
           }
           if (!this.limitmasterService.limitMaster.limitDetails[value].limitOutRightAttribute.limit) {
                this.checkerrcount++;
           }else if (this.limitmasterService.limitMaster.limitDetails[value].limitOutRightAttribute.limit == null) {
               this.checkerrcount++;
           }else if ( this.limitmasterService.limitMaster.limitDetails[value].limitOutRightAttribute.limit != null) {
              if (Number(this.limitmasterService.limitMaster.limitDetails[value].limitOutRightAttribute.limit) == null ) {
                 this.checkerrcount++;
              }
            }
            if ( this.checkerrcount !== 0) {
                 return '#d43c3c';
            } else {
                return '#cfdee7';
            }

        }

   }
    return '#cfdee7';
   }
    return '#cfdee7';
   }
   return '#cfdee7';
 }

 getStylebasis(value) {
   if ( this.limitmasterService.limitMaster != null ) {
         if ( this.limitmasterService.limitMaster.limitBasisTypeId !== defaultSelectValue
         && this.limitmasterService.limitMaster.limitBasisTypeId !== 19 && this.limitmasterService.limitMaster.limitBasisTypeId !== 20 ) {
   if ( this.limitmasterService.limitMaster.limitDetails != null ) {
     this.checkerrcount = 0;
     if ( this.limitmasterService.limitMaster.limitDetails[value].limitBasisAttribute.limitVolumeValueRefId == null ||
        this.limitmasterService.limitMaster.limitDetails[value].limitBasisAttribute.limitVolumeValueRefId === defaultSelectValue ||
         this.limitmasterService.limitMaster.limitDetails[value].limitBasisAttribute.limitVolumeUomIdCurrencycode === defaultSelectValue.toString()) {
         return '#d43c3c';
      } else  if (this.limitmasterService.limitMaster.limitDetails[value].limitBasisAttribute.limitVolumeValueRefId != null) {

         if (!this.limitmasterService.limitMaster.limitDetails[value].limitBasisAttribute.limitVolumeUomIdCurrencycode) {
                this.checkerrcount++;
           }else if (this.limitmasterService.limitMaster.limitDetails[value].limitBasisAttribute.limitVolumeUomIdCurrencycode == null) {
               this.checkerrcount++;
           }
           if (!this.limitmasterService.limitMaster.limitDetails[value].limitBasisAttribute.limit) {
                this.checkerrcount++;
           }else if (this.limitmasterService.limitMaster.limitDetails[value].limitBasisAttribute.limit == null) {
               this.checkerrcount++;
           }else if ( this.limitmasterService.limitMaster.limitDetails[value].limitBasisAttribute.limit != null) {
              if (Number(this.limitmasterService.limitMaster.limitDetails[value].limitBasisAttribute.limit) == null ) {
                 this.checkerrcount++;
              }
            }
            if ( this.checkerrcount !== 0) {
                 return '#d43c3c';
            } else {
                return '#cfdee7';
            }

        }

   }
    return '#cfdee7';
   }
    return '#cfdee7';
   }
   return '#cfdee7';
 }

 getStylestruture(value) {
    if ( this.limitmasterService.limitMaster != null ) {
         if ( this.limitmasterService.limitMaster.limitBasisTypeId !== defaultSelectValue &&
          this.limitmasterService.limitMaster.limitBasisTypeId !== 19 && this.limitmasterService.limitMaster.limitBasisTypeId !== 20 ) {
        if ( this.limitmasterService.limitMaster.limitDetails != null ) {
            this.checkerrcount = 0;
            if ( this.limitmasterService.limitMaster.limitDetails[value].limitStructureAttribute.limitVolumeValueRefId == null ||
                this.limitmasterService.limitMaster.limitDetails[value].limitStructureAttribute.limitVolumeValueRefId 
                        === defaultSelectValue || this.limitmasterService.limitMaster.limitDetails[value].limitStructureAttribute.limitVolumeUomIdCurrencycode === defaultSelectValue.toString()) {
                return '#d43c3c';
            } else  if (this.limitmasterService.limitMaster.limitDetails[value].limitStructureAttribute.limitVolumeValueRefId != null) {

                if (!this.limitmasterService.limitMaster.limitDetails[value].limitStructureAttribute.limitVolumeUomIdCurrencycode) {
                        this.checkerrcount++;
                }else if (this.limitmasterService.limitMaster.limitDetails[value].limitStructureAttribute.
                                                                                limitVolumeUomIdCurrencycode == null) {
                    this.checkerrcount++;
                }
                if (!this.limitmasterService.limitMaster.limitDetails[value].limitStructureAttribute.limit) {
                        this.checkerrcount++;
                }else if (this.limitmasterService.limitMaster.limitDetails[value].limitStructureAttribute.limit == null) {
                    this.checkerrcount++;
                }else if ( this.limitmasterService.limitMaster.limitDetails[value].limitStructureAttribute.limit != null) {
                    if (Number(this.limitmasterService.limitMaster.limitDetails[value].limitStructureAttribute.limit) == null ) {
                        this.checkerrcount++;
                    }
                    }
                    if ( this.checkerrcount !== 0) {
                        return '#d43c3c';
                    } else {
                        return '#cfdee7';
                    }

                }

        }
         return '#cfdee7';
        }
            return '#cfdee7';
    }
        return '#cfdee7';
 }

getStyledran(value) {
   if ( this.limitmasterService.limitMaster != null ) {
       if ( this.limitmasterService.limitMaster.limitBasisTypeId !== defaultSelectValue && 
        this.limitmasterService.limitMaster.limitBasisTypeId !== 19 && this.limitmasterService.limitMaster.limitBasisTypeId !== 20 ) {
        if ( this.limitmasterService.limitMaster.limitDetails != null ) {
            this.checkerrcount = 0;
            if ( this.limitmasterService.limitMaster.limitDetails[value].limitDrawDownAttribute.limitVolumeValueRefId == null || 
            this.limitmasterService.limitMaster.limitDetails[value].limitDrawDownAttribute.limitVolumeValueRefId === defaultSelectValue ||
            this.limitmasterService.limitMaster.limitDetails[value].limitDrawDownAttribute.limitVolumeUomIdCurrencycode === defaultSelectValue.toString()) {
                return '#d43c3c';
            } else  if (this.limitmasterService.limitMaster.limitDetails[value].limitDrawDownAttribute.limitVolumeValueRefId != null) {

                if (!this.limitmasterService.limitMaster.limitDetails[value].limitDrawDownAttribute.limitVolumeUomIdCurrencycode) {
                        this.checkerrcount++;
                }else if (this.limitmasterService.limitMaster.limitDetails[value].limitDrawDownAttribute.
                                                                        limitVolumeUomIdCurrencycode == null) {
                    this.checkerrcount++;
                }
                if (!this.limitmasterService.limitMaster.limitDetails[value].limitDrawDownAttribute.limit) {
                        this.checkerrcount++;
                }else if (this.limitmasterService.limitMaster.limitDetails[value].limitDrawDownAttribute.limit == null) {
                    this.checkerrcount++;
                }else if ( this.limitmasterService.limitMaster.limitDetails[value].limitDrawDownAttribute.limit != null) {
                    if (Number(this.limitmasterService.limitMaster.limitDetails[value].limitDrawDownAttribute.limit) == null ) {
                        this.checkerrcount++;
                    }
                    }
                    if ( this.checkerrcount !== 0) {
                        return '#d43c3c';
                    } else {
                        return '#cfdee7';
                    }

                }

        }
         return '#cfdee7';
        }
            return '#cfdee7';
   }
        return '#cfdee7';
 }

getStyleVar(value) {
   if ( this.limitmasterService.limitMaster != null ) {
       if ( this.limitmasterService.limitMaster.limitBasisTypeId !== defaultSelectValue && 
       this.limitmasterService.limitMaster.limitBasisTypeId !== 19 && this.limitmasterService.limitMaster.limitBasisTypeId !== 20 ) {
        if ( this.limitmasterService.limitMaster.limitDetails != null ) {
            this.checkerrcount = 0;
            if ( this.limitmasterService.limitMaster.limitDetails[value].limitVaRAttribute.limitVolumeValueRefId == null ||
                this.limitmasterService.limitMaster.limitDetails[value].limitVaRAttribute.limitVolumeValueRefId === defaultSelectValue ||
                this.limitmasterService.limitMaster.limitDetails[value].limitVaRAttribute.limitVolumeUomIdCurrencycode === defaultSelectValue.toString() ) {
                return '#d43c3c';
            } else  if (this.limitmasterService.limitMaster.limitDetails[value].limitVaRAttribute.limitVolumeValueRefId != null) {

                if (!this.limitmasterService.limitMaster.limitDetails[value].limitVaRAttribute.limitVolumeUomIdCurrencycode) {
                        this.checkerrcount++;
                }else if (this.limitmasterService.limitMaster.limitDetails[value].limitVaRAttribute.limitVolumeUomIdCurrencycode == null) {
                    this.checkerrcount++;
                }
                if (!this.limitmasterService.limitMaster.limitDetails[value].limitVaRAttribute.limit) {
                        this.checkerrcount++;
                }else if (this.limitmasterService.limitMaster.limitDetails[value].limitVaRAttribute.limit == null) {
                    this.checkerrcount++;
                }else if ( this.limitmasterService.limitMaster.limitDetails[value].limitVaRAttribute.limit != null) {
                    if (Number(this.limitmasterService.limitMaster.limitDetails[value].limitVaRAttribute.limit) == null ) {
                        this.checkerrcount++;
                    }
                    }
                    if ( this.checkerrcount !== 0) {
                        return '#d43c3c';
                    } else {
                        return '#cfdee7';
                    }

                }

        }
         return '#cfdee7';
        }
            return '#cfdee7';
   }
        return '#cfdee7';
 }
  getStyleDelta(value) {
   if ( this.limitmasterService.limitMaster != null ) {
        if ( this.limitmasterService.limitMaster.limitBasisTypeId !== defaultSelectValue
         && this.limitmasterService.limitMaster.limitBasisTypeId !== 19 && this.limitmasterService.limitMaster.limitBasisTypeId !== 20 ) {
        if ( this.limitmasterService.limitMaster.limitDetails != null ) {
            this.checkerrcount = 0;
            if ( this.limitmasterService.limitMaster.limitDetails[value].limitDeltaAttribute.limitVolumeValueRefId == null ||
            this.limitmasterService.limitMaster.limitDetails[value].limitDeltaAttribute.limitVolumeValueRefId === defaultSelectValue ||
            this.limitmasterService.limitMaster.limitDetails[value].limitDeltaAttribute.limitVolumeUomIdCurrencycode === defaultSelectValue.toString() ) {
                return '#d43c3c';
            } else  if (this.limitmasterService.limitMaster.limitDetails[value].limitDeltaAttribute.limitVolumeValueRefId != null) {

                if (!this.limitmasterService.limitMaster.limitDetails[value].limitDeltaAttribute.limitVolumeUomIdCurrencycode) {
                        this.checkerrcount++;
                }else if (this.limitmasterService.limitMaster.limitDetails[value].limitDeltaAttribute.
                                                                            limitVolumeUomIdCurrencycode == null) {
                    this.checkerrcount++;
                }
                if (!this.limitmasterService.limitMaster.limitDetails[value].limitDeltaAttribute.limit) {
                        this.checkerrcount++;
                }else if (this.limitmasterService.limitMaster.limitDetails[value].limitDeltaAttribute.limit == null) {
                    this.checkerrcount++;
                }else if ( this.limitmasterService.limitMaster.limitDetails[value].limitDeltaAttribute.limit != null) {
                    if (Number(this.limitmasterService.limitMaster.limitDetails[value].limitDeltaAttribute.limit) == null ) {
                        this.checkerrcount++;
                    }
                    }
                    if ( this.checkerrcount !== 0) {
                        return '#d43c3c';
                    } else {
                        return '#cfdee7';
                    }

                }

        }
         return '#cfdee7';
        }
            return '#cfdee7';
   }
        return '#cfdee7';
 }

 getStyleGamma(value) {
   if ( this.limitmasterService.limitMaster != null ) {
       if (this.limitmasterService.limitMaster.limitBasisTypeId !== defaultSelectValue && 
            this.limitmasterService.limitMaster.limitBasisTypeId !== 19 && this.limitmasterService.limitMaster.limitBasisTypeId !== 20 ) {
        if ( this.limitmasterService.limitMaster.limitDetails != null ) {
            this.checkerrcount = 0;
            if ( this.limitmasterService.limitMaster.limitDetails[value].limitGammaAttribute.limitVolumeValueRefId == null ||
                this.limitmasterService.limitMaster.limitDetails[value].limitGammaAttribute.limitVolumeValueRefId === defaultSelectValue ||
                this.limitmasterService.limitMaster.limitDetails[value].limitGammaAttribute.limitVolumeUomIdCurrencycode === defaultSelectValue.toString()) {
                return '#d43c3c';
            } else  if (this.limitmasterService.limitMaster.limitDetails[value].limitGammaAttribute.limitVolumeValueRefId != null) {

                if (!this.limitmasterService.limitMaster.limitDetails[value].limitGammaAttribute.limitVolumeUomIdCurrencycode) {
                        this.checkerrcount++;
                }else if (this.limitmasterService.limitMaster.limitDetails[value].limitGammaAttribute.
                                                                                limitVolumeUomIdCurrencycode == null) {
                    this.checkerrcount++;
                }
                if (!this.limitmasterService.limitMaster.limitDetails[value].limitGammaAttribute.limit) {
                        this.checkerrcount++;
                }else if (this.limitmasterService.limitMaster.limitDetails[value].limitGammaAttribute.limit == null) {
                    this.checkerrcount++;
                }else if ( this.limitmasterService.limitMaster.limitDetails[value].limitGammaAttribute.limit != null) {
                    if (Number(this.limitmasterService.limitMaster.limitDetails[value].limitGammaAttribute.limit) == null ) {
                        this.checkerrcount++;
                    }
                    }
                    if ( this.checkerrcount !== 0) {
                        return '#d43c3c';
                    } else {
                        return '#cfdee7';
                    }

                }

        }
         return '#cfdee7';
        }
            return '#cfdee7';
   }
        return '#cfdee7';
 }

 getStyleVega(value) {
   if ( this.limitmasterService.limitMaster != null ) {
          if ( this.limitmasterService.limitMaster.limitBasisTypeId !== defaultSelectValue &&
             this.limitmasterService.limitMaster.limitBasisTypeId !== 19 && this.limitmasterService.limitMaster.limitBasisTypeId !== 20 ) {
        if ( this.limitmasterService.limitMaster.limitDetails != null ) {
            this.checkerrcount = 0;
            if ( this.limitmasterService.limitMaster.limitDetails[value].limitVegaAttribute.limitVolumeValueRefId == null ||
                this.limitmasterService.limitMaster.limitDetails[value].limitVegaAttribute.limitVolumeValueRefId === defaultSelectValue ||
                this.limitmasterService.limitMaster.limitDetails[value].limitVegaAttribute.limitVolumeUomIdCurrencycode === defaultSelectValue.toString()) {
                return '#d43c3c';
            } else  if (this.limitmasterService.limitMaster.limitDetails[value].limitVegaAttribute.limitVolumeValueRefId != null) {

                if (!this.limitmasterService.limitMaster.limitDetails[value].limitVegaAttribute.limitVolumeUomIdCurrencycode) {
                        this.checkerrcount++;
                }else if (this.limitmasterService.limitMaster.limitDetails[value].limitVegaAttribute.limitVolumeUomIdCurrencycode == null) {
                    this.checkerrcount++;
                }
                if (!this.limitmasterService.limitMaster.limitDetails[value].limitVegaAttribute.limit) {
                        this.checkerrcount++;
                }else if (this.limitmasterService.limitMaster.limitDetails[value].limitVegaAttribute.limit == null) {
                    this.checkerrcount++;
                }else if ( this.limitmasterService.limitMaster.limitDetails[value].limitVegaAttribute.limit != null) {
                    if (Number(this.limitmasterService.limitMaster.limitDetails[value].limitVegaAttribute.limit) == null ) {
                        this.checkerrcount++;
                    }
                    }
                    if ( this.checkerrcount !== 0) {
                        return '#d43c3c';
                    } else {
                        return '#cfdee7';
                    }

                }

        }
         return '#cfdee7';
        }
            return '#cfdee7';
   }
        return '#cfdee7';
 }

 getStyleCurrent(value) {
   if ( this.limitmasterService.limitMaster != null ) {
    if (this.limitmasterService.limitMaster.limitBasisTypeId === 19 ) {
    if ( this.limitmasterService.limitMaster.limitDetails != null ) {
        this.checkerrcount = 0;
        if ( this.limitmasterService.limitMaster.limitDetails[value].currentMonthAttribute.limitVolumeValueRefId == null || 
         this.limitmasterService.limitMaster.limitDetails[value].currentMonthAttribute.limitVolumeValueRefId === defaultSelectValue ||
         this.limitmasterService.limitMaster.limitDetails[value].currentMonthAttribute.limitVolumeUomIdCurrencycode === defaultSelectValue.toString()) {
            return '#d43c3c';
        } else  if (this.limitmasterService.limitMaster.limitDetails[value].currentMonthAttribute.limitVolumeValueRefId != null) {

            if (!this.limitmasterService.limitMaster.limitDetails[value].currentMonthAttribute.limitVolumeUomIdCurrencycode) {
                    this.checkerrcount++;
            }else if (this.limitmasterService.limitMaster.limitDetails[value].currentMonthAttribute.limitVolumeUomIdCurrencycode == null) {
                this.checkerrcount++;
            }
            if (!this.limitmasterService.limitMaster.limitDetails[value].currentMonthAttribute.limit) {
                    this.checkerrcount++;
            }else if (this.limitmasterService.limitMaster.limitDetails[value].currentMonthAttribute.limit == null) {
                this.checkerrcount++;
            }else if ( this.limitmasterService.limitMaster.limitDetails[value].currentMonthAttribute.limit != null) {
                if (Number(this.limitmasterService.limitMaster.limitDetails[value].currentMonthAttribute.limit) == null ) {
                    this.checkerrcount++;
                }
                }
                if ( this.checkerrcount !== 0) {
                    return '#d43c3c';
                } else {
                    return '#cfdee7';
                }

            }

    }
     return '#cfdee7';
   }
    return '#cfdee7';
   }
    return '#cfdee7';
 }
 getStyleForward(value) {
   if ( this.limitmasterService.limitMaster != null ) {
       if (this.limitmasterService.limitMaster.limitBasisTypeId === 19 ) {
        if ( this.limitmasterService.limitMaster.limitDetails != null ) {
            this.checkerrcount = 0;
            if ( this.limitmasterService.limitMaster.limitDetails[value].forwardMonthAttribute.limitVolumeValueRefId == null ||
            this.limitmasterService.limitMaster.limitDetails[value].forwardMonthAttribute.limitVolumeValueRefId === defaultSelectValue ||
            this.limitmasterService.limitMaster.limitDetails[value].forwardMonthAttribute.limitVolumeUomIdCurrencycode === defaultSelectValue.toString()) {
                return '#d43c3c';
            } else  if (this.limitmasterService.limitMaster.limitDetails[value].forwardMonthAttribute.limitVolumeValueRefId != null) {

                if (!this.limitmasterService.limitMaster.limitDetails[value].forwardMonthAttribute.limitVolumeUomIdCurrencycode) {
                        this.checkerrcount++;
                }else if (this.limitmasterService.limitMaster.limitDetails[value].forwardMonthAttribute.
                                                                            limitVolumeUomIdCurrencycode == null) {
                    this.checkerrcount++;
                }
                if (!this.limitmasterService.limitMaster.limitDetails[value].forwardMonthAttribute.limit) {
                        this.checkerrcount++;
                }else if (this.limitmasterService.limitMaster.limitDetails[value].forwardMonthAttribute.limit == null) {
                    this.checkerrcount++;
                }else if ( this.limitmasterService.limitMaster.limitDetails[value].forwardMonthAttribute.limit != null) {
                    if (Number(this.limitmasterService.limitMaster.limitDetails[value].forwardMonthAttribute.limit) == null ) {
                        this.checkerrcount++;
                    }
                    }
                    if ( this.checkerrcount !== 0) {
                        return '#d43c3c';
                    } else {
                        return '#cfdee7';
                    }

                }

        }
             return '#cfdee7';
            }
                return '#cfdee7';
        }
    return '#cfdee7';
 }

  getStyleTotal(value) {
   if ( this.limitmasterService.limitMaster != null ) {
        if (this.limitmasterService.limitMaster.limitBasisTypeId === 19 ) {
            if ( this.limitmasterService.limitMaster.limitDetails != null ) {
                this.checkerrcount = 0;
                if ( this.limitmasterService.limitMaster.limitDetails[value].totalAttribute.limitVolumeValueRefId == null ||
                this.limitmasterService.limitMaster.limitDetails[value].totalAttribute.limitVolumeValueRefId === defaultSelectValue ||
                this.limitmasterService.limitMaster.limitDetails[value].totalAttribute.limitVolumeUomIdCurrencycode === defaultSelectValue.toString()) {
                    return '#d43c3c';
                } else  if (this.limitmasterService.limitMaster.limitDetails[value].totalAttribute.limitVolumeValueRefId != null) {

                    if (!this.limitmasterService.limitMaster.limitDetails[value].totalAttribute.limitVolumeUomIdCurrencycode) {
                            this.checkerrcount++;
                    }else if (this.limitmasterService.limitMaster.limitDetails[value].totalAttribute.limitVolumeUomIdCurrencycode == null) {
                        this.checkerrcount++;
                    }
                    if (!this.limitmasterService.limitMaster.limitDetails[value].totalAttribute.limit) {
                            this.checkerrcount++;
                    }else if (this.limitmasterService.limitMaster.limitDetails[value].totalAttribute.limit == null) {
                        this.checkerrcount++;
                    }else if ( this.limitmasterService.limitMaster.limitDetails[value].totalAttribute.limit != null) {
                        if (Number(this.limitmasterService.limitMaster.limitDetails[value].totalAttribute.limit) == null ) {
                            this.checkerrcount++;
                        }
                        }
                        if ( this.checkerrcount !== 0) {
                            return '#d43c3c';
                        } else {
                            return '#cfdee7';
                        }

                    }

            }
             return '#cfdee7';
            }
                return '#cfdee7';
   }
       return '#cfdee7';
 }

  getStyleValue(value) {
   if ( this.limitmasterService.limitMaster != null ) {
         if (this.limitmasterService.limitMaster.limitBasisTypeId === 20 ) {
        if ( this.limitmasterService.limitMaster.limitDetails != null ) {
            this.checkerrcount = 0;
            if ( this.limitmasterService.limitMaster.limitDetails[value].valueAttribute.limitVolumeValueRefId == null ||
            this.limitmasterService.limitMaster.limitDetails[value].valueAttribute.limitVolumeValueRefId === defaultSelectValue ||
             this.limitmasterService.limitMaster.limitDetails[value].valueAttribute.limitVolumeUomIdCurrencycode === defaultSelectValue.toString()) {
                return '#d43c3c';
            } else  if (this.limitmasterService.limitMaster.limitDetails[value].valueAttribute.limitVolumeValueRefId != null) {

                if (!this.limitmasterService.limitMaster.limitDetails[value].valueAttribute.limitVolumeUomIdCurrencycode) {
                        this.checkerrcount++;
                }else if (this.limitmasterService.limitMaster.limitDetails[value].valueAttribute.limitVolumeUomIdCurrencycode == null) {
                    this.checkerrcount++;
                }
                if (!this.limitmasterService.limitMaster.limitDetails[value].valueAttribute.limit) {
                        this.checkerrcount++;
                }else if (this.limitmasterService.limitMaster.limitDetails[value].valueAttribute.limit == null) {
                    this.checkerrcount++;
                }else if ( this.limitmasterService.limitMaster.limitDetails[value].valueAttribute.limit != null) {
                    if (Number(this.limitmasterService.limitMaster.limitDetails[value].valueAttribute.limit) == null ) {
                        this.checkerrcount++;
                    }
                    }
                    if ( this.checkerrcount !== 0) {
                        return '#d43c3c';
                    } else {
                        return '#cfdee7';
                    }

                }

        }
         return '#cfdee7';
        }
            return '#cfdee7';
   }
        return '#cfdee7';
 }

 getStyletemp(value) {
       if ( this.limitmasterService.limitMaster != null ) {
        if ( this.limitmasterService.limitMaster.limitDetails != null ) {
         if ( this.limitmasterService.limitMaster.limitBasisTypeId !== defaultSelectValue) {
             this.checkerrcount = 0;
             if (  this.limitmasterService.limitMaster.limitDetails[value].isTemporary == null ||
                 this.limitmasterService.limitMaster.limitDetails[value].isTemporary === defaultSelectValue.toString()) {
                this.checkerrcount++;
            }
         if ( this.checkerrcount !== 0) {
                    return '#d43c3c';
                } else {
                   return '#cfdee7';
                }
         }
             return '#cfdee7';
        }
         return '#cfdee7';
    }
     return '#cfdee7';
    }
    getStyleValidity(value) {
        if ( this.limitmasterService.limitMaster != null ) {
        if ( this.limitmasterService.limitMaster.limitDetails != null ) {
            this.checkerrcount = 0;
            if ( ! this.limitmasterService.limitMaster.limitDetails[value].validFrom) {
                this.checkerrcount++;
            }
             if ( ! this.limitmasterService.limitMaster.limitDetails[value].validTo) {
                this.checkerrcount++;
            }
        if ( this.checkerrcount !== 0) {
                    return true;
                } else {
                   return false;
                }
        }
        }
    }

}
