import { Component, OnInit, OnChanges, Input, ViewEncapsulation,EventEmitter , Output, ViewChild, OnDestroy } from '@angular/core';
import { Tolerance, Franchise, WeightTermModel } from '../weight-terms';
import { CanComponentDeactivate } from '../../../../shared/guards/can-deactivate-guard';
import { Subscription } from 'rxjs/Subscription';
import { WeightTermsService } from '../service/weight-terms.service';
import { Router } from '@angular/router';
import { listweight } from '../../../../shared/interface/router-links';
import { ModalDirective } from 'ngx-bootstrap';



@Component({
  selector: 'app-add-weight-terms',
  templateUrl: './add-weight-terms.component.html',
  styleUrls: ['./add-weight-terms.component.css'],
  encapsulation: ViewEncapsulation.None,
})
export class AddWeightTermsComponent implements OnInit, OnChanges, OnDestroy, CanComponentDeactivate {
@ViewChild('invalidCount') public invalidCount: ModalDirective;
@ViewChild('cancelModal') public cancelModal: ModalDirective;
// editModal
@ViewChild('editModal') public editModal: ModalDirective;
  tolerancedetails: Tolerance[];
  franchisedetails: Franchise[];
  subscription: Subscription;
  weighttermModel: WeightTermModel = new WeightTermModel();
  preweighttermModel: WeightTermModel = new WeightTermModel();
  weighttermdetail: WeightTermModel = new WeightTermModel();
  @Input() packModel: WeightTermModel;
  @Output() onSubmit: EventEmitter<{msg: string}> = new EventEmitter<{msg: string}> ();
  code: boolean;
  name: boolean;
  franchisevalue: boolean;
  franchiseunit: boolean;
  tolerancevalue: boolean;
  toleranceunit: boolean;
  public errormodalpart;
   public errorModal = false;
  returnval = false;
  public invalidCountMsg;
  requiedMesssage: String;
  isModeChanged: boolean;
  reqmsg: string;
  modelName1: string;
  newModelName: string[];
  isEditCancel: Boolean = false;
  public count;
  isActionPerformed = false;

  public toleranceColor: Boolean = false;
  public franchiseColor: Boolean = false;

  constructor(public weightTermsService: WeightTermsService, private router: Router) { }

  ngOnInit() {
    window.scrollTo(0, 0);
    this.weighttermModel.weightTermsIsToleranceApplicable = 'N';
    this.weighttermModel.weightTermsIsFranchiseApplicable = 'N';
    this.requiedMesssage = 'is required';

     if (this.weighttermModel.weightTermsId !== undefined) {
      // alert('id ' + this.weighttermModel.weightTermsId)
    }

    this.loadtolerancedetails();
    this.loadfranchisedetails();
  }

  ngOnChanges(): void {
    this.isModeChanged = true;
  // this.loadtolerancedetails();
   // this.loadfranchisedetails();
    this.weighttermModel = Object.assign({}, this.packModel);
   //  alert('on Edit   ' + this.weighttermModel + '   ' + this.packModel);
    this.preweighttermModel =  Object.assign({}, this.packModel);
  }

  getStyle() {
    if (this.code === true) {
      return '#d43c3c';
    } else {
      return '#cfdee7';
    }

  }
  getStyle1() {
    if (this.name === true) {
      return '#d43c3c';
    } else {
      return '#cfdee7';
    }

  }

  getStyle2() {
    if (this.franchisevalue === true) {
      return '#d43c3c';
    } else {
      return '#cfdee7';
    }

  }

  getStyle3() {
    if (this.franchiseunit === true) {
      return '#d43c3c';
    } else {
      return '#cfdee7';
    }

  }

  getStyle4() {
    if (this.tolerancevalue === true) {
      return '#d43c3c';
    } else {
      return '#cfdee7';
    }

  }

  getStyle5() {
    if (this.toleranceunit === true) {
      return '#d43c3c';
    } else {
      return '#cfdee7';
    }

  }

  loadtolerancedetails() {

    this.subscription = this.weightTermsService.gettoleranceDetail().subscribe(toleranceDetails => {
      this.tolerancedetails = toleranceDetails.body;
      //  console.log('origindetail' + JSON.stringify(this.origindetial));
      //  this.originfilter = this.origindetial.filter(prod => prod.statusName === 'Active');
    });
  }


  loadfranchisedetails() {


    this.subscription = this.weightTermsService.getfranchiseDetail().subscribe(franchiseDetails => {
      this.franchisedetails = franchiseDetails.body;
      //  console.log('origindetail' + JSON.stringify(this.origindetial));
      //  this.originfilter = this.origindetial.filter(prod => prod.statusName === 'Active');
    });


  }


  /*To Save the packing material details in draft or active according to the action*/
  savePackingMaterial(actionstatus) {
   // alert('valid' + this.isValidForm());
// alert('count value' + this.count);
    if (this.isValidForm()) {
      this.isActionPerformed = true;
      this.weighttermModel.statusName = actionstatus;
     //  alert('weight' + JSON.stringify(this.weighttermModel));
      this.subscription = this.weightTermsService.savePackingDetails(this.weighttermModel).subscribe(data => {
        if (data.body !== null && data.body !== '') {
          this.router.navigate([listweight], { queryParams: { 'success': 1, 'weightterms': this.weighttermModel.weightTermsName } });
        }
      },
        error => {
        this.errorModal = true;
        setTimeout(() => { this.errorModal = false; }, 3000);
      });    }
  }

  onHiddenpopup() {
    this.errormodalpart = false;
    // this.isDeactivatePopupModal = false;
    // this.successModal.hide();
  }

  isValidForm() {
    this.count = 0;

    if (!this.weighttermModel.weightTermsCode) {
     // alert('code');
      this.code = true;
      this.count++;
    } else {
      this.code = false;
    }
    // return true;

    if (!this.weighttermModel.weightTermsName) {
     // alert('name');
      this.name = true;
      this.count++;
      // return false;

    } else {
      this.name = false;
    }

    if (this.weighttermModel.weightTermsIsFranchiseApplicable === 'Y') {
      // alert(this.weighttermModel.weightTermsIsFranchiseApplicable)
      if (!this.weighttermModel.weightTermsDefaultFranchiseValue && this.weighttermModel.weightTermsDefaultFranchiseValue !== 0) {
        this.franchisevalue = true;
        this.count++;
      } else {
        this.franchisevalue = false;
      }
      if (!this.weighttermModel.franchiseValueUnitId) {
        this.franchiseunit = true;
        this.count++;
      } else {
        this.franchiseunit = false;
      }
    } else {
     // alert('franc');
      // return false;
      this.franchisevalue = false;
      this.franchiseunit = false;
    }
    /*else if (this.weighttermModel.weightTermsIsFranchiseApplicable === 'N' ) {
     this.franchisevalue = false;
     this.franchiseunit = false;
     return false;
    } */


     if (this.weighttermModel.weightTermsDefaultFranchiseValue === 0 && this.weighttermModel.weightTermsDefaultToleranceValue === 0) {
         // alert('inside value f 0');
           this.invalidCountMsg = 'Both defaultFranchise and defaulttolerance  values should be more than 0';
           this.errormodalpart = true;
      // this.invalidCount.show();
      // setTimeout({this.invalidCountMsg.hide(), 3000})
      if (this.invalidCount) {
      setTimeout(() => { this.invalidCount.hide(); }, 3000);
          this.returnval = false;
        }
        return false;
          }


    // weightTermsIsToleranceApplicable
   // if (this.weighttermModel.weightTermsIsFranchiseApplicable === 'Y') {
        if (this.weighttermModel.weightTermsDefaultFranchiseValue === 0) {
         // alert('inside value f 0');
           this.invalidCountMsg = 'defaultFranchise value should be more than 0';
           this.errormodalpart = true;
      // this.invalidCount.show();
      // setTimeout({this.invalidCountMsg.hide(), 3000})
      if (this.invalidCount) {
      setTimeout(() => { this.invalidCount.hide(); }, 3000);
          this.returnval = false;
        }
        return false;
          }

     // }


    // weightTermsIsToleranceApplicable
// if (this.weighttermModel.weightTermsIsToleranceApplicable === 'Y') {
        if (this.weighttermModel.weightTermsDefaultToleranceValue === 0) {
         /// alert('inside value 0');
           this.invalidCountMsg = 'defaulttolerance value should be more than 0';
           this.errormodalpart = true;
      // this.invalidCount.show();
      // setTimeout({this.invalidCountMsg.hide(), 3000})
      if (this.invalidCount) {
      setTimeout(() => { this.invalidCount.hide(); }, 3000);
          this.returnval = false;
        }
        return false;
          }

// }


    if (this.weighttermModel.weightTermsIsToleranceApplicable === 'Y') {
     // alert(this.weighttermModel.weightTermsIsToleranceApplicable)
      if (!this.weighttermModel.weightTermsDefaultToleranceValue && this.weighttermModel.weightTermsDefaultToleranceValue !== 0) {
        this.tolerancevalue = true;
        this.count++;
      } else {
        this.tolerancevalue = false;
      }
      if (!this.weighttermModel.toleranceValueUnitId) {
        this.toleranceunit = true;
        this.count++;
      } else {
        this.toleranceunit = false;
      }
    } else {
     // alert('tole');
      // return false;
      this.tolerancevalue = false;
      this.toleranceunit = false;
    }
    /*else if (this.weighttermModel.weightTermsIsToleranceApplicable === 'N' ) {
        this.tolerancevalue = false;
        this.toleranceunit = false;
        return false;
       }  */


    if (!(this.code || this.name)) {
     // alert('codepart');
      if (this.weighttermModel.weightTermsIsFranchiseApplicable === 'Y') {
       // alert('namepart')
        if (!(this.franchisevalue || this.franchiseunit)) {
          this.returnval = true;
               this.validtolerancepart();
        } else {
          if (this.count === 1) {
        this.invalidCountMsg = this.count + ' Mandatory Field is required';
      } else {
        this.invalidCountMsg = this.count + ' Mandatory Fields are required';
      }
      this.errormodalpart = true;
      // this.invalidCount.show();
      // setTimeout({this.invalidCountMsg.hide(), 3000})
      if (this.invalidCount) {
      setTimeout(() => { this.invalidCount.hide(); }, 3000);
          this.returnval = false;
        }
        }

      } else {
        this.returnval = true;
        this.validtolerancepart();
      }
      return this.returnval;
    } else {
       if (this.count === 1) {
        this.invalidCountMsg = this.count + ' Mandatory Field is required';
      } else {
        this.invalidCountMsg = this.count + ' Mandatory Fields are required';
      }
      this.errormodalpart = true;
      // this.invalidCount.show();
      // setTimeout({this.invalidCountMsg.hide(), 3000})
      if (this.invalidCount) {
      setTimeout(() => { this.invalidCount.hide(); }, 3000);
      }


      return false;
    }

  }

  validtolerancepart() {
    if (this.weighttermModel.weightTermsIsToleranceApplicable === 'Y') {
// alert('tolerance')
      if (!(this.tolerancevalue || this.toleranceunit)) {
        this.returnval = true;
      } else {
        if (this.count === 1) {
        this.invalidCountMsg = this.count + ' Mandatory Field is required';
      } else {
        this.invalidCountMsg = this.count + ' Mandatory Fields are required';
      }
      this.errormodalpart = true;
      // this.invalidCount.show();
      // setTimeout({this.invalidCountMsg.hide(), 3000})
      if (this.invalidCount) {
      setTimeout(() => { this.invalidCount.hide(); }, 3000);
      }
        this.returnval = false;
      }

    } else {
            this.returnval = true;
          }
  }


  reset() {
   // alert('inside reset');
    this.weighttermModel = new WeightTermModel();
    this.weighttermModel.weightTermsIsToleranceApplicable = 'N';
    this.weighttermModel.weightTermsIsFranchiseApplicable = 'N';
    this.name = false;
    this.code = false;
    this.franchisevalue = false;
    this.franchiseunit = false;
    this.tolerancevalue = false;
    this.toleranceunit = false;
  }

  validate() {
    this.name = false;
    this.code = false;
    this.franchisevalue = false;
    this.franchiseunit = false;
    this.tolerancevalue = false;
    this.toleranceunit = false;
  }

  // Edit Weight-term starts here
  updatePack(param) {
  //  alert('update param' + param);
    this.weighttermModel.statusName = param;
    if (this.isValidForm()) {
      this.isActionPerformed = true;

       const myArray: WeightTermModel[] = Array();
    myArray.push(this.weighttermModel);
        //  alert('weight' + JSON.stringify(myArray));
      this.subscription = this.weightTermsService.updatePackingDetails(myArray).subscribe(data => {
        if (data.body !== null && data.body !== '') {
            this.onSubmit.emit({msg: 'Data Updated Successfully'});
        }
      }, error => {
        this.errorModal = true;
        setTimeout(() => { this.errorModal = false; }, 3000);
      });
    }
  }
   // Edit Weight-term ends here

   /*To cancel the edit modal starts here*/
  onCancel() {
   // this.onSubmit.emit({msg: ''});
  // alert('on cancel')
     this.isEditCancel = true;
// this.cancelModal.show();
  }

  changeFirstRadio(val: string) {
    if (val === 'Y') {
     // alert('chagne yes first radio')
      this.toleranceColor = true;
    }else if (val === 'N') {
     // alert('chagne NO first radio')
      this.toleranceColor = false;
    }
  }

  changeFirstfRadio(val: string) {
    if (val === 'Y') {
// alert('chagne yes first radio')
      this.franchiseColor = true;
    }else if (val === 'N') {
    //  alert('chagne NO first radio')
      this.franchiseColor = false;
    }
  }

  getToleranceClass() {
    if ( this.toleranceColor) {
      return 'colr-red font-regular font-14';
    }else {
      return 'colr-lblue font-regular font-14';
    }

}

getFranchiseClass() {
    if ( this.franchiseColor) {
      return 'colr-red font-regular font-14';
    }else {
      return 'colr-lblue font-regular font-14';
    }
}

onHiddenCancel() {
 this.isEditCancel = false;
}


closeConfirmBox() {
  this.cancelModal.hide();

}

confirmCancelation() {
// this.weighttermModel =  Object.assign({}, this.preweighttermModel);
// this.cancelModal.hide();
   this.onSubmit.emit({msg: '' });
//  this.editModal.hide();
// alert('navigate');
  // this.router.navigate([listweight]);
 //  this.editModal.hide();
}

canDeactivate(): boolean {
    return this.isActionPerformed;
  }


/*To cancel the edit modal ends here*/

ngOnDestroy() {
    // Called once, before the instance is destroyed.
    this.subscription.unsubscribe();
  }
}
