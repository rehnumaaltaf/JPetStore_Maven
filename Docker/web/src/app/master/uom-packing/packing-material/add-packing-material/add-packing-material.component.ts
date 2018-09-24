import { Component, OnInit , OnDestroy , ViewEncapsulation , Input , ViewChild , OnChanges , EventEmitter , Output} from '@angular/core';
import { CanComponentDeactivate } from '../../../../shared/guards/can-deactivate-guard';
import { Subscription } from 'rxjs/Subscription';
import { PackingModel } from '../model/pack-model';
import { PackingMaterialService } from '../service/packing-material.service';
import { SelectItem } from '../../../../shared/interface/selectItem';
import { Router } from '@angular/router';
import { ModalDirective } from 'ngx-bootstrap';
import { UnitMeasurementService } from '../../../../master/uom-packing/unit-measurement/service/unit-measurement.service';
import { packingMaterial } from '../../../../shared/interface/router-links';
import { MessageModel } from '../../../../shared/message/message.model';
// import { MESSAGE_SUCCESS, MESSAGE_ERROR } from '../../../shared/interface/common.constants';
import { MESSAGE_SUCCESS, MESSAGE_ERROR, ACTION_SAVE } from '../../../../shared/interface/common.constants';
@Component({
  selector: 'app-add-packing-material',
  templateUrl: './add-packing-material.component.html',
  styleUrls: ['./add-packing-material.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class AddPackingMaterialComponent implements OnInit, OnDestroy, OnChanges, CanComponentDeactivate {
  /*declare the variables which are used in the component*/
  isActionPerformed = false;
  subscription: Subscription;
  reqmsg: string;
  packingModel: PackingModel= new PackingModel();
  prevPackingModel: PackingModel= new PackingModel();
  // uombaseOption: SelectItem[] = [];
  reqPackingCode: boolean;
  reqPackingName: boolean;
  reqPackingWeight: boolean;
  reqUomValue: boolean;
  isValid: boolean;
  errorModal: boolean;
  valMessagePack: string;
  isModeChanged: boolean;
   @Input() packModel: PackingModel;
  @ViewChild('showModal') public showModal: ModalDirective;
  isEditCancel: Boolean = false;
  @ViewChild('cancelModal') public cancelModal: ModalDirective;
  @Output() onSubmit: EventEmitter<{msg: string}> = new EventEmitter<{msg: string}> ();
  modelName1: string;
  newModelName: string[];


  // tslint:disable-next-line:max-line-length
  constructor(public packingMaterialService: PackingMaterialService, private unitMeasurementService:  UnitMeasurementService , private router: Router) { }

  ngOnInit() {
    if (this.packingModel.pkPackingMaterialId === undefined) {
       this.packingModel.packingMaterialIsBulk = '0';
    }
     this.getDropDownList();
     console.log('packModel init ' + JSON.stringify(this.packModel));
     this.packingMaterialService.messages =  new MessageModel()
  }
   ngOnChanges (): void {
    this.isModeChanged = true;
    this.getDropDownList();
    this.reqmsg = '';
    this.modelName1 = '';
    this.newModelName = [];
    this.packingModel = Object.assign({}, this.packModel);
    console.log('on Edit   ' , this.packingModel);
    this.prevPackingModel =  Object.assign({}, this.packModel);
  }
  /*To get the UOM dropdown list*/
  getDropDownList() {
   this.subscription = this.unitMeasurementService.getUnitMeasurementJSON('/reference/uomservice/uom').subscribe(data => {
      this.packingModel.uombaseOption = data.body;
      console.log(data.body);
    }, error => {throw error});

  }
  /*To Save the packing material details in draft or active according to the action*/
  savePackingMaterial(actionstatus) {
      if (this.isValidForm()) {
        this.isActionPerformed = true;
        this.packingModel.action = actionstatus;
        this.subscription = this.packingMaterialService.savePackingDetails(this.packingModel).subscribe(data => {
          if (data.body !== null && data.body !== '' ) {
             this.router.navigate([packingMaterial], { queryParams: { 'success': 1 } });
          }
        },
        error => {
          this.packingMaterialService.messages = { severity: MESSAGE_ERROR, summary: this.packingMaterialService.errorMessage };

        });
      }
}
/*To vaildate the form*/
isNotBlank(value: string): Boolean {
    return (value && value.trim() !== '');
  }
isValidForm(): Boolean {
 // alert('inside the valid form');
  this.isValid = true;
  // alert(this.packingModel.packingMaterialWeight.length);

    if (this.packingModel.packingMaterialCode === null || this.packingModel.packingMaterialCode === undefined
       || this.packingModel.packingMaterialCode === '') {
            this.reqPackingCode = true;
            this.isValid = false;
    } else {
               if (this.packingModel.packingMaterialCode.trim().length === 0) {
                     this.reqPackingCode = true;
                    this.isValid = false;
               } else {
                   this.reqPackingCode = false;
               }
    }

    if (this.packingModel.packingMaterialName === null || this.packingModel.packingMaterialName === undefined ||
        this.packingModel.packingMaterialName === '') {
          this.reqPackingName = true;
           this.isValid = false;
    } else {
                 if (this.packingModel.packingMaterialName.trim().length === 0) {
                    this.reqPackingName = true;
                     this.isValid = false;
                 } else {
                      this.reqPackingName = false;
                 }
    }

     // tslint:disable-next-line:max-line-length
    // if (this.packingModel.packingMaterialWeight === undefined || this.packingModel.packingMaterialWeight == null ) {

    if (this.packingModel.packingMaterialWeight === null || this.packingModel.packingMaterialWeight === undefined ) {
      this.reqPackingWeight = true;
      this.isValid = false;
    } else {
     // alert('inside the else' + this.packingModel.packingMaterialWeight.length );
      /* if (this.packingModel.packingMaterialWeight.length === 0) {
             this.reqPackingWeight = true;
             this.isValid = false;
          } else {
               this.reqPackingWeight = false;
          }
     */
            const packingweight = +this.packingModel.packingMaterialWeight;
            if ( (this.packingModel.packingMaterialWeight).toString().length === 0) {
                  this.reqPackingWeight = true;
                   this.isValid = false;
                    } else {
                       this.reqPackingWeight = false;
                      }
      //  this.reqPackingWeight = false;
    }

   // if (this.packingModel.uomValue === undefined || this.packingModel.uomValue == null) {
      if (this.packingModel.uomValue === null || this.packingModel.uomValue === undefined || this.packingModel.uomValue === '' ) {
      this.reqUomValue = true;
      this.isValid = false;
    } else {
                if (this.packingModel.uomValue.trim().length === 0) {
                    this.reqUomValue = true;
                    this.isValid = false;
                } else {
                       this.reqUomValue = false;
                }

    }
    return this.isValid;
}
/*To validate the numeric field*/
validateNumericsOnly(keyEvent, modelName) {
    /*if (modelName) {
      alert('in if');
      this.modelName1 = '1.0';
      alert(this.modelName1);
      this.newModelName = [];
        this.modelName1 = modelName;
        alert(this.modelName1);
        if (this.modelName1.includes('.')) {
          this.newModelName = this.modelName1.split('.');
          if ( this.newModelName[1].length > 6 ) {
              this.valMessagePack = 'Decimal value cannot be more than 6';
              this.errorModal = true;
              setTimeout(() => {
                this.errorModal = false;
              }, 3000);
          }
        }
    }*/
    const result = (keyEvent.which) ? keyEvent.which : keyEvent.keyCode;
      if (result === 110 || result === 190) {
          return true;
      } else if (result !== 46 && result > 31
        && (result < 48 || result > 57)) {
          this.valMessagePack = 'Enter only numeric and Decimals';
          this.errorModal = true;
          setTimeout(() => {
            this.errorModal = false;
          }, 3000);
          return false;
        }/*else if (result === 17 || result === 67 || result === 86) {
       this.valMessagePack = 'CTRL + C or CTRL + V are not allowed';
        this.errorModal = true;
        setTimeout(() => {
          this.errorModal = false;
        }, 3000);
        return false;
      }*/
      return true;
  }
  /*To check and validate the code*/
  toValidateFn(action) {
     this.packingModel.toValidate = action;
    this.subscription = this.packingMaterialService.validate(this.packingModel).subscribe(data => {
      this.reqPackingName = false;
      this.reqPackingCode = false;
          if (data.body !== null && data.body !== '' ) {
              if (action === 'CODE') {
                  let isUnique = true;
                  if (this.packingModel.pkPackingMaterialId) {
                    if (this.prevPackingModel.packingMaterialCode !== this.packingModel.packingMaterialCode) {
                      isUnique = true;
                    } else {
                      isUnique = false;
                    }
                  }else {
                    isUnique = true;
                  }
                  if (isUnique) {
                    this.packingModel.packingMaterialCode = '';
                    this.reqPackingName = true;
                    this.valMessagePack = data.body;
                    this.errorModal = true;
                    setTimeout(() => {
                      this.errorModal = false;
                    }, 3000);
                  }
              }
              if (action === 'NAME') {
                 let isUnique = true;
                  if (this.packingModel.pkPackingMaterialId) {
                    if (this.prevPackingModel.packingMaterialName !== this.packingModel.packingMaterialName) {
                      isUnique = true;
                    } else {
                      isUnique = false;
                    }
                  }else {
                    isUnique = true;
                  }
                  if (isUnique) {
                    this.packingModel.packingMaterialName = '';
                    this.reqPackingName = true;
                    this.valMessagePack = data.body;
                    this.errorModal = true;
                    setTimeout(() => {
                      this.errorModal = false;
                    }, 3000);
                  }
              }
             // this.disableSaveBtn = false;
             // this.disableSubmitBtn = false;
            }
          }, error => { throw error });
  }
  validatepackingMaterialCode(code) {
    if (!this.isNotBlank(this.packingModel.packingMaterialCode)) {
      this.reqPackingCode = true;
    }else {
     // this.toValidateFn(code);
      this.reqPackingCode = false;
    }
  }
  /*To check and validate the name*/
  validatepackingMaterialName(name) {
     if (!this.isNotBlank(this.packingModel.packingMaterialName)) {
      this.reqPackingName = true;
    }else {
     // this.toValidateFn(name);
      this.reqPackingName = false;
    }
  }
  /*To check and validate the weight*/
  validatepackingMaterialWeight() {
    if (this.packingModel.packingMaterialWeight === undefined || this.packingModel.packingMaterialWeight == null) {
      this.reqPackingWeight = true;
    }else {
      this.reqPackingWeight = false;
    }
  }
  /*To list the suggestions for code*/
  onCodeSuggestion($event) {
   const packCodeValue = $event.target.value;
    if (packCodeValue !== null && packCodeValue !== undefined && packCodeValue !== '') {
      this.packingModel.toValidate = 'code';
      this.subscription = this.packingMaterialService.packCodeSuggestion(this.packingModel).subscribe(descDetails => {
      this.packingMaterialService.packCodeList = descDetails.body;
     }, error =>  { throw error; } );
    }
  }
  /*To list the suggestions for name*/
  onNameSuggestion($event) {
      const packNameValue = $event.target.value;
    if (packNameValue !== null && packNameValue !== undefined && packNameValue !== '') {
      this.packingModel.toValidate = 'name';
      this.subscription = this.packingMaterialService.packNameSuggestion(this.packingModel).subscribe(descDetails => {
      this.packingMaterialService.packNameList = descDetails.body;
     }, error =>  { throw error; } );
    }
  }

  /*To reset the list of details entered*/
  clear() {
    this.packingModel = new PackingModel();
    this.packingModel.packingMaterialIsBulk = '0';
    this.reqPackingCode =  false;
    this.reqPackingName = false;
    this.reqPackingWeight = false;
    this.reqUomValue = false;
    this.reqmsg = '';
    this.getDropDownList();
  }

  /*Edit Packing Material starts*/
  updatePack(param) {
    this.packingModel.action = param;
    if (this.isValidForm()) {
      this.subscription = this.packingMaterialService.updatePackingDetails(this.packingModel).subscribe(data => {
        if (data.body !== null && data.body !== '') {
            this.onSubmit.emit({msg: 'Data Updated Successfully'});
        }
      }, error => {
              this.packingMaterialService.messages = { severity: MESSAGE_ERROR, summary: this.packingMaterialService.errorMessage };
      });
    }
  }
  /*To cancel the edit modal*/
  onCancel() {
   /* if (this.locationDetail.locName ===  this.prevLocationDetail.locName
      && this.locationDetail.locCode ===  this.prevLocationDetail.locCode
      && this.locationDetail.locFullName ===  this.prevLocationDetail.locFullName
       && this.locationDetail.fkGeoId ===  this.prevLocationDetail.fkGeoId
       && this.arraysEqual(this.locationDetail.fkLocTypeId , this.prevLocationDetail.fkLocTypeId)) {
    }*/
    this.onSubmit.emit({msg: ''});
    this.isEditCancel = true;
  }
  onHiddenCancel() {
     this.isEditCancel = false;
  }
   confirmCancelation() {
    this.packingModel =  Object.assign({}, this.prevPackingModel);
    this.cancelModal.hide();
  }

  closeConfirmBox() {
    this.cancelModal.hide();
  }
   /*This function is called when user is trying to come out the component*/
   canDeactivate(): boolean {
    return this.isActionPerformed;
  }

  ngOnDestroy() {
     // prevent memory leak when component destroyed
     if (this.subscription) {
       this.subscription.unsubscribe();
     }
  }


}
