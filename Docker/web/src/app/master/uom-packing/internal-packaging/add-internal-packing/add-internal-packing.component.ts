import { Component, OnInit, OnDestroy, Input, Output, OnChanges, EventEmitter, ElementRef, ViewChild } from '@angular/core';
// import { InternalPackaging } from '../internalpackaging';
import { InternalPackagingModel } from '../model/InternalPackagingModel';
import { InternalPackingService } from '../service/internal-packing.service';
import { SelectItem } from '../../../../shared/interface/selectItem';
import { Subscription } from 'rxjs/Subscription';
import { Router } from '@angular/router';
import { addinternalPackaging } from '../../../../shared/interface/router-links';
import { InternalPacking } from '../../../../shared/interface/router-links';
import { ModalDirective } from 'ngx-bootstrap';
import { UnitMeasurementService } from '../../../../master/uom-packing/unit-measurement/service/unit-measurement.service';
import { CanComponentDeactivate } from '../../../../shared/guards/can-deactivate-guard';
import { FormBuilder, FormGroup, FormControl, Validators , FormArray , ReactiveFormsModule , FormsModule} from '@angular/forms';
import { MESSAGE_SUCCESS, MESSAGE_ERROR, ACTION_SAVE } from '../../../../shared/interface/common.constants';

@Component({
selector: 'app-add-internal-packaging',
templateUrl: './add-internal-packing.component.html',
styleUrls: ['./add-internal-packing.component.css']
})

export class AddInternalPackingComponent implements OnInit, OnDestroy, CanComponentDeactivate, OnChanges {
 public selectedOption: Number = 3;
 packingDetails: InternalPackagingModel[];
internalPacking: InternalPackagingModel = new InternalPackagingModel();
PrevinternalPacking: InternalPackagingModel = new InternalPackagingModel();
isActionPerformed = false;
subscription: Subscription;
req_ipname: boolean;
req_ipcode: boolean;
req_ipweight: boolean;
req_ipuomcode: boolean;
// ispagebackPopupModal: boolean;
isComplete: Boolean = false;
loadingData: Boolean = false;
displayDialog: Boolean = false;
public isShowModal = false ;
basecodeOption: SelectItem[] = [];
isShowValModalInternal: Boolean = false;
valMessageInternal: string;
disableSaveBtn: Boolean = false;
disableSubmitBtn: Boolean = false;
reqmsg = '';
req_typeCode: Boolean;
isModeChanged: Boolean = false;
errorModal: boolean;
public draft;
public active;
public inactive;
isEditCancel: Boolean = false;
isValid: boolean;


@Input() PackingDetails: InternalPackagingModel;
@ViewChild('successModal') public SuccessModal: ModalDirective;
@ViewChild('cancelModal') public cancelModal: ModalDirective;
@ViewChild('pagebackModal') public pagebackModal: ModalDirective;

@Output() onSubmit: EventEmitter<{msg: string}> = new EventEmitter<{msg: string}> ();
constructor(public internalPackingService: InternalPackingService ,
private unitMeasurementService: UnitMeasurementService , private router: Router) { }

ngOnInit() {
this.req_ipcode = false;
this.req_ipname = false;
this.req_ipuomcode = false;
this.req_ipweight = false;
// this.ispagebackPopupModal = false;
if (!this.internalPacking.internalPackingTypeId) {
this.internalPacking.isBulkCode = '0' ;
}

this.displayDialog = true;
this.getDropDownList();


}

canDeactivate(): boolean {
return this.isActionPerformed;
}

ngOnChanges (): void {
 // alert('onchange');
 this.isModeChanged = true;
 // console.log('before' + this.internalPacking);
 this.internalPacking = Object.assign({}, this.PackingDetails);
 // alert( JSON.stringify(this.internalPacking));
 // this.selectedLocationType = this.locationDetail.fkLocTypeId;

// this.setMultiSelect();
 this.PrevinternalPacking = Object.assign({}, this.PackingDetails);
 if (this.PrevinternalPacking.status === 'Draft') {
this.draft = true;
this.active = false;
this.inactive = false;
}else if (this.PrevinternalPacking.status === 'Active') {
this.active = true;
this.draft = false;
this.inactive = false;
}else if (this.PrevinternalPacking.status === 'InActive') {
this.inactive = true;
this.active = false;
this.draft = false;
}
}

closeConfirmBox() {
    this.cancelModal.hide();
  }
closedeletepopup() {
   this.SuccessModal.hide();
 }

 /*To cancel the edit modal*/
  onCancel() {
    this.onSubmit.emit({msg: ''});
    this.isEditCancel = true;
  }

    confirmCancelation() {
    this.PrevinternalPacking =  Object.assign({}, this.PrevinternalPacking);
    this.cancelModal.hide();
  }

save(actionstatus) {
// alert('inside the save');
this.isShowModal = true;
this.reqmsg = '';
 if (this.isValidForm()) {

this.isActionPerformed = true;
this.internalPacking.action = actionstatus;

this.subscription = this.internalPackingService.saveInterPackingDetails(this.internalPacking).subscribe(data => {

this.reqmsg = data.body;


if (data.body !== null && data.body !== '' ) {

// this.internalPackingService.setMessage(data.body);
this.router.navigate([InternalPacking], { queryParams: { 'success': 1 } });
}

 },
error => {
    this.internalPackingService.messages = { severity: MESSAGE_ERROR, summary: this.internalPackingService.errorMessage };
},
() => console.log('Finished')
 );
}

}

 updateInternal(action) {
//  alert('inside the update');
     this.internalPacking.action = action;
    console.log(JSON.stringify(this.internalPacking));

    if (this.isValidForm()) {
      this.subscription = this.internalPackingService.updatePackingDetails(this.internalPacking).subscribe(savedData => {

     if (savedData.body !== null && savedData.body !== '' ) {

        this.onSubmit.emit({msg: 'Data Updated Successfully'});
      }

      }, error => {
     this.internalPackingService.messages = { severity: MESSAGE_ERROR, summary: this.internalPackingService.errorMessage };
      },
        () => console.log('Finished')
    );

   }
 }



onHiddenCancel() {
  this.isEditCancel = false;

}

/*onHiddendup() {
  this.ispagebackPopupModal = false;
} */

 validateNumericsOnly(keyEvent) {

  const result = (keyEvent.which) ? keyEvent.which : keyEvent.keyCode;
      if (result === 110 || result === 190) {
          return true;
      } else if (result !== 46 && result > 31
        && (result < 48 || result > 57)) {

          this.internalPackingService.messages = { severity: MESSAGE_ERROR, summary: 'Enter only numeric and Decimals'};
         // this.ispagebackPopupModal = true;
         // this.valMessageInternal = 'Enter only numeric and Decimals';
         // this.errorModal = true;
          return false;
        }

 /*const result = (keyEvent.which) ? keyEvent.which : keyEvent.keyCode;

 if (result === 110 || result === 190) {
return true;
 }else if (result === 17 || result === 67 || result === 86) {
alert('CTRL + C or CTRL + V are not allowed');
return false;
 }else if (result !== 46 && result > 31
&& (result < 48 || result > 57)) {
 alert('Enter only numeric and Decimals');
 return false;
} */
 return true;
}


 clear () {
this.internalPacking = new InternalPackagingModel();

 this.req_typeCode = false;
 this.ngOnInit();
}

/*To get the UOM dropdown list*/
getDropDownList() {
this.subscription = this.unitMeasurementService.getUnitMeasurementJSON('/reference/uomservice/uom').subscribe(data => {
this.internalPacking.uombaseOption = data.body;
console.log(data.body);
 }, error => {throw error});

}

/*To vaildate the form*/
isNotBlank(value: string): Boolean {
 //  if(value !)
    if (value === null && value === undefined ) {
          return true;
    } else {
       return false;
    }

  }

isValidForm(): Boolean {

  // alert(this.internalPacking.internalPackingWeight);


 // alert('inside the valid form');
  this.isValid = true;
  // alert(this.packingModel.packingMaterialWeight.length);

    if (this.internalPacking.internalPackingTypeCode === null || this.internalPacking.internalPackingTypeCode === undefined ||
        this.internalPacking.internalPackingTypeCode === '')  {
           this.req_ipcode = true;
            this.isValid = false;
       } else {
                if (this.internalPacking.internalPackingTypeCode.trim().length === 0) {
                this.req_ipcode = true;
                this.isValid = false;
                } else {
                 this.req_ipcode = false;
                }
      }

    if (this.internalPacking.internalPackingTypeName === null || this.internalPacking.internalPackingTypeName === undefined ||
         this.internalPacking.internalPackingTypeName === '') {
      this.req_ipname = true;
      this.isValid = false;
    } else {
         if (this.internalPacking.internalPackingTypeName.trim().length === 0) {
              this.req_ipname = true;
              this.isValid = false;
            } else {
               this.req_ipname = false;
            }
          }

     // tslint:disable-next-line:max-line-length
    // if (this.packingModel.packingMaterialWeight === undefined || this.packingModel.packingMaterialWeight == null ) {

    if (this.internalPacking.internalPackingWeight === null ||  this.internalPacking.internalPackingWeight === undefined
         ) {
          this.req_ipweight = true;
         this.isValid = false;
    } else {
               const packingweight = +this.internalPacking.internalPackingWeight;
            if ( (this.internalPacking.internalPackingWeight).toString().length === 0) {
                  this.req_ipweight = true;
                   this.isValid = false;
                    } else {
                       this.req_ipweight = false;
                      }

        // this.req_ipweight = false;

    }

   // if (this.packingModel.uomValue === undefined || this.packingModel.uomValue == null) {
      if (this.internalPacking.internalPackingUomCode === null || this.internalPacking.internalPackingUomCode === undefined ||
         this.internalPacking.internalPackingUomCode  === '') {
              this.req_ipuomcode = true;
              this.isValid = false;
    } else {
             if (this.internalPacking.internalPackingUomCode.trim().length === 0) {
               this.req_ipuomcode = true;
               this.isValid = false;

             } else {
                this.req_ipuomcode = false;
             }

    }
    return this.isValid;





 /* if ((this.internalPacking.internalPackingTypeCode === null || this.internalPacking.internalPackingTypeCode === undefined)
&& (this.internalPacking.internalPackingTypeName === null || this.internalPacking.internalPackingTypeName === undefined)
 && (this.internalPacking.internalPackingWeight === null || this.internalPacking.internalPackingWeight === undefined)
 && (this.internalPacking.internalPackingUomCode === null || this.internalPacking.internalPackingUomCode === undefined)) {
this.req_ipcode = true;
this.req_ipname = true;
this.req_ipuomcode = true;
this.req_ipweight = true;
// this.reqmsg += 'Please enter Primary Packing Type Code, Primary Packing Type Name,Primary Packing Weight, Primary Packing UOM Code'
this.req_typeCode = true;
return false;
} else if ( (this.internalPacking.internalPackingTypeCode !== null || this.internalPacking.internalPackingTypeCode !== undefined)
&& (this.internalPacking.internalPackingTypeName === null || this.internalPacking.internalPackingTypeName === undefined)
 && (this.internalPacking.internalPackingWeight === null || this.internalPacking.internalPackingWeight === undefined)
 && (this.internalPacking.internalPackingUomCode === null || this.internalPacking.internalPackingUomCode === undefined)) {
this.req_ipcode = false;
this.req_ipname = true;
this.req_ipuomcode = true;
this.req_ipweight = true;
// this.reqmsg += 'Please enter Primary Packing Type Name,Primary Packing Weight, Primary Packing UOM Code'
this.req_typeCode = true;
return false;
}else if ( (this.internalPacking.internalPackingTypeCode === null || this.internalPacking.internalPackingTypeCode === undefined)
&& (this.internalPacking.internalPackingTypeName !== null || this.internalPacking.internalPackingTypeName !== undefined)
&& (this.internalPacking.internalPackingWeight === null || this.internalPacking.internalPackingWeight === undefined)
 && (this.internalPacking.internalPackingUomCode === null || this.internalPacking.internalPackingUomCode === undefined)) {
this.req_ipcode = true;
this.req_ipname = false;
this.req_ipuomcode = true;
this.req_ipweight = true;
// this.reqmsg += 'Please enter Primary Packing Type Code, Primary Packing Weight, Primary Packing UOM Code'
this.req_typeCode = true;
return false;
} else if ( (this.internalPacking.internalPackingTypeCode === null || this.internalPacking.internalPackingTypeCode === undefined)
&& (this.internalPacking.internalPackingTypeName === null || this.internalPacking.internalPackingTypeName === undefined)
 && (this.internalPacking.internalPackingWeight !== null || this.internalPacking.internalPackingWeight !== undefined)
 && (this.internalPacking.internalPackingUomCode === null || this.internalPacking.internalPackingUomCode === undefined)) {
this.req_ipcode = true;
this.req_ipname = true;
this.req_ipuomcode = false;
this.req_ipweight = true;
// this.reqmsg += 'Please enter Primary Packing Type Code, Primary Packing Type Name, Primary Packing UOM Code'
this.req_typeCode = true;
return false;
 } else if ( (this.internalPacking.internalPackingTypeCode === null || this.internalPacking.internalPackingTypeCode === undefined)
&& (this.internalPacking.internalPackingTypeName === null || this.internalPacking.internalPackingTypeName === undefined)
&& (this.internalPacking.internalPackingWeight === null || this.internalPacking.internalPackingWeight === undefined)
 && (this.internalPacking.internalPackingUomCode !== null || this.internalPacking.internalPackingUomCode !== undefined)) {
this.req_ipcode = true;
this.req_ipname = true;
this.req_ipuomcode = true;
this.req_ipweight = false;
// this.reqmsg += 'Please enter Primary Packing Type Code, Primary Packing Type Name,Primary Packing Weight'
this.req_typeCode = true;
return false;
 } else if ( (this.internalPacking.internalPackingTypeCode !== null || this.internalPacking.internalPackingTypeCode !== undefined)
&& (this.internalPacking.internalPackingTypeName !== null || this.internalPacking.internalPackingTypeName !== undefined)
&& (this.internalPacking.internalPackingWeight === null || this.internalPacking.internalPackingWeight === undefined)
 && (this.internalPacking.internalPackingUomCode === null || this.internalPacking.internalPackingUomCode === undefined)) {
this.req_ipcode = false;
this.req_ipname = false;
this.req_ipuomcode = true;
this.req_ipweight = true;
// this.reqmsg += 'Please enter Primary Packing Weight, Primary Packing UOM Code'
this.req_typeCode = true;
return false;
 } else if ( (this.internalPacking.internalPackingTypeCode !== null || this.internalPacking.internalPackingTypeCode !== undefined)
&& (this.internalPacking.internalPackingTypeName === null || this.internalPacking.internalPackingTypeName === undefined)
 && (this.internalPacking.internalPackingWeight !== null || this.internalPacking.internalPackingWeight !== undefined)
 && (this.internalPacking.internalPackingUomCode === null || this.internalPacking.internalPackingUomCode === undefined)) {
this.req_ipcode = false;
this.req_ipname = true;
this.req_ipuomcode = false;
this.req_ipweight = true;
// this.reqmsg += 'Please enterPrimary Packing Type Name, Primary Packing UOM Code'
this.req_typeCode = true;
return false;
 } else if ( (this.internalPacking.internalPackingTypeCode === null || this.internalPacking.internalPackingTypeCode === undefined)
&& (this.internalPacking.internalPackingTypeName !== null || this.internalPacking.internalPackingTypeName !== undefined)
&& (this.internalPacking.internalPackingWeight !== null || this.internalPacking.internalPackingWeight !== undefined)
 && (this.internalPacking.internalPackingUomCode === null || this.internalPacking.internalPackingUomCode === undefined)) {
this.req_ipcode = true;
this.req_ipname = false;
this.req_ipuomcode = false;
this.req_ipweight = true;
// this.reqmsg += 'Please enterPrimary Packing Type Name,Primary Packing Weight'
this.req_typeCode = true;
return false;
 } else if ( (this.internalPacking.internalPackingTypeCode === null || this.internalPacking.internalPackingTypeCode === undefined)
&& (this.internalPacking.internalPackingTypeName === null || this.internalPacking.internalPackingTypeName === undefined)
 && (this.internalPacking.internalPackingWeight !== null || this.internalPacking.internalPackingWeight !== undefined)
 && (this.internalPacking.internalPackingUomCode !== null || this.internalPacking.internalPackingUomCode !== undefined)) {
this.req_ipcode = true;
this.req_ipname = true;
this.req_ipuomcode = false;
this.req_ipweight = false;
// this.reqmsg += 'Please enter Primary Packing Type Code, Primary Packing Type Name'
this.req_typeCode = true;
return false;
 } else if ( (this.internalPacking.internalPackingTypeCode === null || this.internalPacking.internalPackingTypeCode === undefined)
&& (this.internalPacking.internalPackingTypeName !== null || this.internalPacking.internalPackingTypeName !== undefined)
 && (this.internalPacking.internalPackingWeight === null || this.internalPacking.internalPackingWeight === undefined)
 && (this.internalPacking.internalPackingUomCode !== null || this.internalPacking.internalPackingUomCode !== undefined)) {
this.req_ipcode = true;
this.req_ipname = false;
this.req_ipuomcode = true;
this.req_ipweight = false;
// this.reqmsg += 'Please enter Primary Packing Type Code, Primary Packing Weight'
this.req_typeCode = true;
return false;
 } else if ( (this.internalPacking.internalPackingTypeCode !== null || this.internalPacking.internalPackingTypeCode !== undefined)
&& (this.internalPacking.internalPackingTypeName !== null || this.internalPacking.internalPackingTypeName !== undefined)
 && (this.internalPacking.internalPackingWeight !== null || this.internalPacking.internalPackingWeight !== undefined)
 && (this.internalPacking.internalPackingUomCode === null || this.internalPacking.internalPackingUomCode === undefined)) {
this.req_ipcode = false;
this.req_ipname = false;
this.req_ipuomcode = false;
this.req_ipweight = true;
// this.reqmsg += 'Please enterPrimary Packing UOM Code'
this.req_typeCode = true;
return false;
 } else if ( (this.internalPacking.internalPackingTypeCode === null || this.internalPacking.internalPackingTypeCode === undefined)
&& (this.internalPacking.internalPackingTypeName !== null || this.internalPacking.internalPackingTypeName !== undefined)
&& (this.internalPacking.internalPackingWeight !== null || this.internalPacking.internalPackingWeight !== undefined)
 && (this.internalPacking.internalPackingUomCode !== null || this.internalPacking.internalPackingUomCode !== undefined)) {
this.req_ipcode = true;
this.req_ipname = false;
this.req_ipuomcode = false;
this.req_ipweight = false;
// this.reqmsg += 'Please enter Primary Packing Type Code'
this.req_typeCode = true;
return false;
 } else if ( (this.internalPacking.internalPackingTypeCode !== null || this.internalPacking.internalPackingTypeCode !== undefined)
&& (this.internalPacking.internalPackingTypeName === null || this.internalPacking.internalPackingTypeName === undefined)
 && (this.internalPacking.internalPackingWeight !== null || this.internalPacking.internalPackingWeight !== undefined)
 && (this.internalPacking.internalPackingUomCode !== null || this.internalPacking.internalPackingUomCode !== undefined)) {
this.req_ipcode = false;
this.req_ipname = true;
this.req_ipuomcode = false;
this.req_ipweight = false;
// this.reqmsg += ' Please enter Primary Packing Type Name'
this.req_typeCode = true;
return false;
 } else if ( (this.internalPacking.internalPackingTypeCode !== null || this.internalPacking.internalPackingTypeCode !== undefined)
&& (this.internalPacking.internalPackingTypeName !== null || this.internalPacking.internalPackingTypeName !== undefined)
&& (this.internalPacking.internalPackingWeight === null || this.internalPacking.internalPackingWeight === undefined)
 && (this.internalPacking.internalPackingUomCode !== null || this.internalPacking.internalPackingUomCode !== undefined)) {
this.req_ipcode = false;
this.req_ipname = false;
this.req_ipuomcode = true;
this.req_ipweight = false;
// this.reqmsg += 'Please enter Primary Packing Weight'
this.req_typeCode = true;
return false;
 } else if ( (this.internalPacking.internalPackingTypeCode !== null || this.internalPacking.internalPackingTypeCode !== undefined)
&& (this.internalPacking.internalPackingTypeName !== null || this.internalPacking.internalPackingTypeName !== undefined)
 && (this.internalPacking.internalPackingWeight !== null || this.internalPacking.internalPackingWeight !== undefined)
 && (this.internalPacking.internalPackingUomCode !== null || this.internalPacking.internalPackingUomCode !== undefined)) {
this.req_ipcode = false;
this.req_ipname = false;
this.req_ipuomcode = false;
this.req_ipweight = false;

return true;
 } else {
this.req_ipcode = true;
this.req_ipname = true;
this.req_ipuomcode = true;
this.req_ipweight = true;
return true;
 } */
}


getStyle() {
 if (this.req_ipcode === true) {
return '#d43c3c';
 } else {
return '#cfdee7';
 }

}
getStyle1() {
 if (this.req_ipname === true) {
return '#d43c3c';
 } else {
return '#cfdee7';
 }

}
getStyle2() {
 if (this.req_ipuomcode === true) {
return '#d43c3c';
 } else {
return '#cfdee7';
 }

}
getStyle3() {
 if (this.req_ipweight === true) {
return '#d43c3c';
 } else {
return '#cfdee7';
 }

}
ngOnDestroy() {
 // Called once, before the instance is destroyed.
 // this.subscription.unsubscribe();
}
}
