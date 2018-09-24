import { Component, OnInit, OnDestroy, ViewEncapsulation, Output,
   Input, OnChanges, EventEmitter, ElementRef, ViewChild } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';
import { StatusCode } from '../model/status.enum';
import { LocationModel } from '../model/location.model';
import { LocationService } from '../service/location.service';
import { ActivatedRoute, Router } from '@angular/router';
import { SelectItemModel } from '../model/select-item.model';
import { location } from '../../../../shared/interface/router-links';
import { TreeviewConfig, TreeviewItem } from 'ngx-treeview';
import { MessageModel } from '../../../../shared/message/message.model';
import { ConfirmBoxModel } from '../confirm-box/confirm-box.model';
import { ModalDirective } from 'ngx-bootstrap';
import { CanComponentDeactivate } from '../../../../shared/guards/can-deactivate-guard';
import { ConfirmationService } from '../../../../shared/confirm-box/confirm.service';
import { MESSAGE_SUCCESS, MESSAGE_ERROR, ACTION_SAVE } from '../../../../shared/interface/common.constants';
import { ACTION_SUBMIT, ACTION_UPDATE, ACTION_ACTIVE, ACTION_DEACTIVE } from '../../../../shared/interface/common.constants';

// import { CurrencyService } from '../../../../master/finance/currency/service/currency.service';
// import { UnitMeasurementService } from '../../../../master/uom-packing/unit-measurement/service/unit-measurement.service';
import { RateBasis } from '../model/ratebasis.enum';

@Component({
  selector: 'app-add-edit-location',
  templateUrl: './add-edit-location.component.html',
  styleUrls: ['./add-edit-location.component.css'],
  encapsulation: ViewEncapsulation.None
})

export class AddEditLocationComponent implements OnInit, OnDestroy, OnChanges, CanComponentDeactivate  {
  subscription: Subscription;
  locationDetails: LocationModel[];
  locationDetail: LocationModel = new LocationModel();
  prevLocationDetail: LocationModel = new LocationModel();
  status: typeof StatusCode = StatusCode;
  rateBasis: typeof RateBasis = RateBasis;
  locationType: SelectItemModel[];
  countries: SelectItemModel[];
  disableSaveBtn: Boolean = false;
  disableSubmitBtn: Boolean = false;
  confirmBoxConfigure: ConfirmBoxModel ;
  reqLocationCode: Boolean = false;
  reqLocationName: Boolean = false;
  reqLocationCountry: Boolean = false;
  reqLocationType: Boolean = false;
  isActionPerformed = true;
  reqThc: Boolean = false;
  reqLocationFullName: Boolean = false;
  isModeChanged: Boolean = false;
  locationCodeList: string[] = [];
  locationNameList: string[] = [];
  rateBasisList: SelectItemModel[] = [{code: 1, value: 'UOM'}, {code: 2, value: 'Primary Packing'}, {code: 3, value: 'Secondary Packing'}];
  currencyList: SelectItemModel[];
  rateBasisUomList: SelectItemModel[];
  rateBasisSecondaryPackList: SelectItemModel[];
  // rateBasisUomList = [{uomId: 1, uomName: 'KiloGram'}, {uomId: 2, uomName: 'milligram'},
  // {uomId: 4, uomName: 'milligram'}];
  rateBasisPrimaryPackList: SelectItemModel[] = [{code: 1, value: 'pp1'}, {code: 2, value: 'pp2'},
  {code: 3, value: 'pp3'}];
/*   rateBasisSecondaryPackList: SelectItemModel[] = [{code: 1, value: 'sp1'}, {code: 2, value: 'sp2'},
  {code: 3, value: 'sp3'}]; */
  locationFullNameList: string[] = [];
  multiSelectConfig = TreeviewConfig.create({
        hasAllCheckBox: false,
        hasFilter: false,
        hasCollapseExpand: false,
        maxHeight: 400
    });
  // locationTypeItems: TreeviewItem[];
  locationTypeItems: SelectItemModel[];
  isValid: Boolean = true;
  pkLocId: number;
  isReset: Boolean = false;
  isEditCancel: Boolean = false;
  timeout: Number = 2000;
  public openEdit: Boolean =  false;
  @ViewChild('locationCode') locationCode;
  @ViewChild('locationName') locationName;
  @ViewChild('locationFullName') locationFullName;
  @ViewChild('thc') thc
  // @ViewChild('resetModal') public resetModal: ModalDirective;
  // @ViewChild('cancelModal') public cancelModal: ModalDirective;
 // @Output() loadingData: Boolean = true;
  @Input() locDetail: LocationModel;
  minTypeaheadLength = 3;
  @Output() onSubmit: EventEmitter<{message: MessageModel}> = new EventEmitter<{message: MessageModel}> ();
  constructor(private route: ActivatedRoute, private router: Router, public locationService: LocationService,
  private confirmationService: ConfirmationService ) {
    // this.loadCountryList();
    // this.loadLocationTypeList();
   }
  ngOnInit() {
    // this.locationDetail = Object.assign({}, this.locDetail);
    // this.locationService.messages = new MessageModel();
     this.loadLocationDropDownList();
     this.loadCurrencyList();
     this.loadUomList();
     this.loadPrimaryPackagingList();
     this.loadSecondaryPackagingList();
   }

  ngOnDestroy() {
    // prevent memory leak when component destroyed
   // this.subscription.unsubscribe();
  }

  canDeactivate(): boolean {
     // alert(this.isActionPerformed);
      return this.isActionPerformed;
  }


  locCodeSuggestion($event) {
    const locCode = $event.target.value;
    if (this.isNotBlank(locCode) && locCode.length >= this.minTypeaheadLength) {
     this.subscription = this.locationService.locCodeSuggestion(locCode).subscribe(data => {
      this.locationCodeList = data;
     }, error =>  { console.log(error); } );
    } else {
      this.locationCodeList = [];
    }
  }

  locNameSuggestion($event) {
    const locName = $event.target.value;
    if (this.isNotBlank(locName) && locName.length >= this.minTypeaheadLength) {
     this.subscription = this.locationService.locNameSuggestion(locName).subscribe(data => {
      this.locationNameList = data;
     }, error =>  { console.log(error); } );
    } else {
      this.locationNameList = [];
    }
  }

  locFullNameSuggestion($event) {
    const locFullName = $event.target.value;
    if (this.isNotBlank(locFullName) && locFullName.length >= this.minTypeaheadLength) {
     this.subscription = this.locationService.locFullNameSuggestion(locFullName).subscribe(data => {
      this.locationFullNameList = data;
     }, error =>  { console.log(error); } );
    } else {
      this.locationFullNameList = [];
    }
  }

  loadCurrencyList() {
     this.subscription = this.locationService.getCurrencyList().subscribe(data => {
      this.currencyList = data.body;
     }, error =>  { console.log(error); } );
  }
  loadUomList() {
     this.subscription = this.locationService.getUomList().subscribe(data => {
      this.rateBasisUomList = data.body;
     }, error =>  { console.log(error); } );
  }
  loadPrimaryPackagingList() {
     this.subscription = this.locationService.getPrimaryPackingList().subscribe(data => {
      this.rateBasisPrimaryPackList = data.body;
     }, error =>  { console.log(error); } );
  }
  loadSecondaryPackagingList() {
     this.subscription = this.locationService.getSecondaryPackingList().subscribe(data => {
      this.rateBasisSecondaryPackList = data.body;

     }, error =>  { console.log(error); } );
  }


  /*
  generateMultiSelect() {
    this.locationTypeItems = [];
    this.locationType.forEach(locType => {
        let checked = false;
        if (this.locationDetail.fkLocTypeId && this.locationDetail.fkLocTypeId.indexOf(+locType.code) !== -1) {
          checked = true;
        }

        let item;
        item = new TreeviewItem({
          text: locType.value, value: locType.code, checked: checked
        });
        this.locationTypeItems.push(new TreeviewItem(item));
      });
  }*/
  /* setMultiSelect() {
     this.locationTypeItems = [];
     for ( let i = 0; i < this.locationDetail.fkLocTypeId.length ; i++) {
        this.locationTypeItems.push({code: this.locationDetail.fkLocTypeId[i], value: this.locationDetail.fkLocTypeName[i]});
     }
   }*/
  loadLocationDropDownList(): void {
     this.subscription = this.locationService.getLocationDropDownList().subscribe(data => {
      this.countries = <SelectItemModel[]>data.body.locCountryList;
      this.locationType = <SelectItemModel[]>data.body.locationTypeList;
     // this.generateMultiSelect();
    }, error => { console.log(error); });
  }
  savOrSubmitLocationDetails(statusId , action): void {
     if (this.doValidate() && !this.reqThc) {
        this.disableSubmitBtn = true;
      //  this.loadingData = true;
        this.locationDetail.fkStatusId = statusId;
        this.locationDetail.action = action;
      //  alert(this.locationDetail.action);
        this.isActionPerformed = true;
        // console.log(this.locationDetail);
         if (+this.locationDetail.fkRateBasisRefId === this.rateBasis.uom) {
            this.locationDetail.rateBasisPrimaryPackingId = 0;
            this.locationDetail.rateBasisSecondaryPackingId = 0;
         }else if (+this.locationDetail.fkRateBasisRefId === this.rateBasis.primaryPack) {
            this.locationDetail.rateBasisUomId = 0;
            this.locationDetail.rateBasisSecondaryPackingId = 0;
         }else if (+this.locationDetail.fkRateBasisRefId === this.rateBasis.secondaryPack) {
            this.locationDetail.rateBasisUomId = 0;
            this.locationDetail.rateBasisPrimaryPackingId = 0;
         }
        this.subscription = this.locationService.savOrSubmitLocationDetails(this.locationDetail).subscribe(locationDetail => {
          // this.locationDetails = <LocationModel[]> locationDetail.body;
          // this.locationDetails = JSON.parse(localStorage.getDataList);// will remove
          // this.locationDetails.push(this.locationDetail);
          // localStorage.getDataList = JSON.stringify(this.locationDetails);// will remove
          // console.log('this.locationDetails' +JSON.stringify(this.locationDetails));
          if (statusId === this.status.Active) {
            this.locationService.messages = {severity: MESSAGE_SUCCESS,
             summary: 'Message.Common.Submit', placeHolder: ['Location', this.locationDetail.locName]};
          } else {
             this.locationService.messages = {severity: MESSAGE_SUCCESS,
             summary: 'Message.Common.Save', placeHolder: ['Location', this.locationDetail.locName]};
          }
          this.router.navigate([location]);
        //  this.loadingData = false;
      },
      error => {
        console.log(error);
      });
    }
  }

  onSubmitBtnClick(statusId): void {
    if (this.doValidate() && !this.reqThc) {
        this.disableSubmitBtn = true;
      //  this.loadingData = true;
        this.disableSaveBtn = true;
        this.disableSubmitBtn = true;
        this.isActionPerformed =  true;
        const locationDetail = Object.assign({}, this.locationDetail);
        locationDetail.fkStatusId = statusId;
        locationDetail.fkLocTypeName = '';
        if (+locationDetail.fkRateBasisRefId === this.rateBasis.uom) {
            locationDetail.rateBasisPrimaryPackingId = 0;
            locationDetail.rateBasisSecondaryPackingId = 0;
         }else if (+this.locationDetail.fkRateBasisRefId === this.rateBasis.primaryPack) {
            locationDetail.rateBasisUomId = 0;
            locationDetail.rateBasisSecondaryPackingId = 0;
         }else if (+this.locationDetail.fkRateBasisRefId === this.rateBasis.secondaryPack) {
            locationDetail.rateBasisUomId = 0;
            locationDetail.rateBasisPrimaryPackingId = 0;
         }
          if ( statusId === this.status.Active) {
           locationDetail.action = 'Submit';
        } else if ( statusId === this.status.Inactive) {
           locationDetail.action  = 'Deactive';
        } else {
          locationDetail.action  = 'Save';
        }
      //  console.log('In side onSubmitBtnClick' + JSON.stringify(this.locationDetail));
        locationDetail.rateBasisPrimaryPackingId = +this.locationDetail.rateBasisPrimaryPackingId;
        locationDetail.rateBasisSecondaryPackingId = +this.locationDetail.rateBasisSecondaryPackingId;

    //     console.log('In side onSubmitBtnClick' + JSON.stringify(locationDetail.rate));
        this.subscription = this.locationService.updateLocationDetails(locationDetail).subscribe(data => {
          let tempMessage: MessageModel;
           this.router.navigate([location]);
          if (statusId === this.status.Active) {
            if (this.locationDetail.fkStatusId !== statusId ) {
               /* tempMessage.push({severity: 'success',
             summary: 'Location ' + locationDetail.locCode + ' & ' +
             locationDetail.locName + ' has been saved and Activated', detail: ''});*/
             tempMessage = {severity: MESSAGE_SUCCESS,
             summary: 'Message.Common.Submit', placeHolder: ['Location', this.locationDetail.locName]};
            }else {
             /* tempMessage.push({severity: 'success',
             summary: 'Location ' + locationDetail.locCode +
             ' & ' + locationDetail.locName + ' has been saved successfully', detail: ''});*/

            tempMessage = {severity: MESSAGE_SUCCESS,
             summary: 'Message.Common.Save', placeHolder: ['Location', this.locationDetail.locName]};
            }
          }else if (statusId === this.status.Inactive) {
            if (this.locationDetail.fkStatusId !== statusId ) {
                 /*tempMessage.push({severity: 'success',
             summary: 'Location ' +
             locationDetail.locCode + ' & ' + locationDetail.locName
              + ' has been saved and Deactivated', detail: ''});*/

            tempMessage = {severity: MESSAGE_SUCCESS,
             summary: 'Message.Common.Deactivate', placeHolder: ['Location', this.locationDetail.locName]};
            }else {
              /*tempMessage.push({severity: 'success',
             summary: 'Location ' + locationDetail.locCode +
             ' & ' + locationDetail.locName + ' has been saved successfully', detail: ''});*/

            tempMessage = {severity: MESSAGE_SUCCESS,
             summary: 'Message.Common.Save', placeHolder: ['Location', this.locationDetail.locName]};
            }
          }else {
            /*tempMessage.push({severity: 'success',
             summary: 'Location ' + locationDetail.locCode +
             ' & ' + locationDetail.locName + ' has been saved successfully', detail: ''});*/

            tempMessage = {severity: MESSAGE_SUCCESS,
             summary: 'Message.Common.Save', placeHolder: ['Location', this.locationDetail.locName]};
          }
          this.disableSaveBtn = false;
          this.disableSubmitBtn = false;
          this.onSubmit.emit({message: tempMessage});
         // this.loadingData = false;

      },
      error => {
        console.log(error);
      });
    }
  }


  onReset() {
    if ( (this.locationDetail.locCode && this.locationDetail.locCode !== '') ||
    (this.locationDetail.locName && this.locationDetail.locName !== '') ||
    (this.locationDetail.locFullName && this.locationDetail.locFullName !== '') ||
    (this.locationDetail.fkGeoId && this.locationDetail.fkGeoId !== 0 ) ||
    (this.locationDetail.fkLocTypeId  ) ) {
      // this.isReset = true;
      this.confirmationService.confirm({
          message: 'Confirm.Common.Reset',
          accept: (event) => {
            this.resetConfirm();
          },
          reject: (event) => {
          }
      });
    } else {
      this.router.navigate([location]);
    }
  }

  /*resetCancel($event) {
     this.resetModal.hide();
  }*/

  resetConfirm() {
    this.locationDetail.locCode = null;
    this.locationDetail.locName = null;
    this.locationDetail.locFullName = null;
    this.locationDetail.fkGeoId = null;
    this.locationDetail.fkLocTypeId = 0;
    this.locationTypeItems = [];
    this.locationDetail.fkCurrencyId = null;
    this.locationDetail.rate = null;
    this.locationDetail.fkRateBasisRefId = null;
    this.locationDetail.rateBasisUom = null;
    this.locationDetail.rateBasisPrimaryPacking = null;
    this.locationDetail.rateBasisSecondaryPacking = null;
    // this.generateMultiSelect();
    this.reqLocationName = false;
    this.reqLocationCode = false;
    this.reqLocationCountry = false;
    this.reqLocationType = false;
    this.locationFullName = false;
    this.isValid = true;
    // this.resetModal.hide();
  }
  /*
  onHiddenReset() {
    this.isReset = false;
  }*/
  goToViewPage($event) {
    this.router.navigate([location]);
  }

  isNotBlank(value: string): Boolean {
    return (value && value.trim() !== '');
  }
  checkNumeric(): Boolean {
     this.reqThc = false;
    if (this.locationDetail.rate && isNaN(+this.locationDetail.rate)) {
      this.reqThc = true;
      // this.validationMessages = [];
      // this.validationMessages.push({severity: 'error', summary: 'Only numeric inputs allowed', detail: ''});
      this.locationService.messages = {severity: MESSAGE_ERROR, summary: 'Message.Location.NumericValidation' }
      setTimeout(() => {
        this.locationDetail.rate = null;
        this.thc.nativeElement.focus();
      }, this.timeout);
    }
    return this.reqThc;
  }
  doValidate(): Boolean {
    this.isValid = true;
    if (!this.isNotBlank(this.locationDetail.locCode)) {
      this.reqLocationCode = true;
      this.isValid = false;
    } else {
      this.reqLocationCode = false;
    }

    if (!this.isNotBlank(this.locationDetail.locName)) {
      this.reqLocationName = true;
      this.isValid = false;
    } else {
      this.reqLocationName = false;
    }

    if (!this.locationDetail.fkGeoId) {
      this.reqLocationCountry = true;
      this.isValid = false;
    } else {
      this.reqLocationCountry = false;
    }
   // (this.selectedLocationType && this.selectedLocationType.length !== 0) ||
    if (!(this.locationDetail.fkLocTypeId )) {
      this.reqLocationType = true;
      this.isValid = false;
    } else {
      this.reqLocationType = false;
    }

   /* if (!this.checkNumeric(this.locationDetail.rate)) {

    }*/

    if (!this.isValid) {
     // this.validationMessages = [];
     // this.validationMessages.push({severity: 'error', summary: 'Please fill all the mandatory highlighted fields', detail: ''});
      this.locationService.messages = {severity: MESSAGE_ERROR, summary: 'Message.Common.Mandatory' }
    }
    return this.isValid;
  }

 arraysEqual(arr1, arr2) {
      if (arr1.length !== arr2.length) {
        return false;
      }
      for ( let i = arr1.length; i--; ) {
          if (arr1[i] !== arr2[i]) {
             return false;
          }
      }
      return true;
  }
  onCancel() {
    if (this.locationDetail.locName ===  this.prevLocationDetail.locName
      && this.locationDetail.locCode ===  this.prevLocationDetail.locCode
      && this.locationDetail.locFullName ===  this.prevLocationDetail.locFullName
       && this.locationDetail.fkGeoId ===  this.prevLocationDetail.fkGeoId
       && this.arraysEqual(this.locationDetail.fkLocTypeId , this.prevLocationDetail.fkLocTypeId)) {
         this.confirmationService.confirm({
          message: 'Confirm.Common.CancelConfirm',
          accept: (event) => {
            this.confirmCancelation();
          },
          reject: (event) => {
          }
      });
    }
    this.isEditCancel = true;
  }
  confirmCancelation() {
    this.locationDetail =  Object.assign({}, this.prevLocationDetail);
    this.onSubmit.emit({message: null});
    // this.router.navigate([location]);
    // this.cancelModal.hide();
  }

  /* closeConfirmBox() {
    this.cancelModal.hide();
  } */
  onHiddenCancel() {
     this.isEditCancel = false;
  }
  locationCodeIsUnique() {
     if (this.isNotBlank(this.locationDetail.locCode)) {
         this.subscription = this.locationService.uniqueLocationCode(this.locationDetail.locCode).subscribe(data => {
              let isUnique = true;
              if (this.locationDetail.pkLocId && this.locationDetail.pkLocId !== 0 && data === 1) {
                if (this.prevLocationDetail.locCode !== this.locationDetail.locCode) {
                  isUnique = false;
                } else {
                  isUnique = true;
                }
              } else if ( data > 0) {
                isUnique = false;
              } else {
                isUnique = true;
              }
              if ( !isUnique ) {
                /*this.validationMessages = [];
                this.validationMessages.push({severity: 'error',
                summary: this.locationDetail.locCode + ' already exists, please provide unique name', detail: ''});*/
                this.locationService.messages = {severity: MESSAGE_ERROR, summary: 'Message.Location.Unique',
                placeHolder: ['code'] }
                 setTimeout(() => {
                   this.locationDetail.locCode = null;
                   this.locationCode.nativeElement.focus();
                 }, this.timeout);
                // this.locationCode.nativeElement.focus();
                this.reqLocationCode = true;
              }else {
                this.reqLocationCode = false;
              }
          }, error => {
           console.log(error);
        });
     }
  }

  locationNameIsUnique() {
    if (this.isNotBlank(this.locationDetail.locName)) {
         this.subscription = this.locationService.uniqueLocationName(this.locationDetail.locName).subscribe(data => {
              let isUnique = true;
              if (this.locationDetail.pkLocId && this.locationDetail.pkLocId !== 0 && data === 1) {
                if (this.prevLocationDetail.locName !== this.locationDetail.locName) {
                  isUnique = false;
                } else {
                  isUnique = true;
                }
              } else if ( data > 0) {
                isUnique = false;
              } else {
                isUnique = true;
              }
              if ( !isUnique ) {
                /*this.validationMessages = [];
                this.validationMessages.push({severity: 'error',
                summary: this.locationDetail.locName + ' already exists, please provide unique name', detail: ''});*/
                this.locationService.messages = {severity: MESSAGE_ERROR, summary: 'Message.Location.Unique',
                placeHolder: ['name'] }
                // this.locationName.nativeElement.focus();
                setTimeout(() => {
                    this.locationDetail.locName = null;
                    this.locationName.nativeElement.focus();
                  }, this.timeout);
                this.reqLocationName = true;
              }else {
                this.reqLocationName = false;
              }
          }, error => {
           console.log(error);
        });
     }
  }

  locationFullNameIsUnique() {
    if (this.isNotBlank(this.locationDetail.locFullName)) {
         this.subscription = this.locationService.uniqueLocationFullName(this.locationDetail.locFullName).subscribe(data => {
            let isUnique = true;
              if (this.locationDetail.pkLocId && this.locationDetail.pkLocId !== 0 && data === 1) {
                if (this.prevLocationDetail.locFullName !== this.locationDetail.locFullName) {
                  isUnique = false;
                } else {
                  isUnique = true;
                }
              }  else if ( data > 0) {
                isUnique = false;
              } else {
                isUnique = true;
              }
              if ( !isUnique ) {
                /*this.validationMessages = [];
                this.validationMessages.push({severity: 'error',
                summary: this.locationDetail.locFullName + ' already exists, please provide unique name', detail: ''});*/
                this.locationService.messages = {severity: MESSAGE_ERROR, summary: 'Message.Location.Unique',
                placeHolder: ['full name'] }
                // this.locationName.nativeElement.focus();
                setTimeout(() => {
                    this.locationDetail.locFullName = null;
                    this.locationFullName.nativeElement.focus();
                  }, this.timeout);
                this.reqLocationFullName = true;
              }else {
                this.reqLocationFullName = false;
              }
          }, error => {
           console.log(error);
        });
     }
  }

  ngOnChanges (): void {
    this.isModeChanged = true;
    this.pkLocId = this.locDetail.pkLocId;
   // alert(this.pkLocId);
    this.loadLocationDetail();
    // this.locationDetail = Object.assign({}, this.locDetail);
    // this.selectedLocationType = this.locationDetail.fkLocTypeId;
    // console.log(this.locationDetail)
   // this.setMultiSelect();
   // this.prevLocationDetail =  Object.assign({}, this.locDetail);
  }

  loadLocationDetail() {
      this.subscription = this.locationService.getLocationDetail(this.pkLocId).subscribe(data => {
        this.locationDetail = data.body[0];
        this.prevLocationDetail = data.body[0];
      }, error => {
       throw error;
      });
   }
  /*
  applyDropDownChange($evt): void {
    // if ($evt.length !== 0) {
      if ( this.isModeChanged ) {
        this.isModeChanged = false;
       //  console.log( this.locationDetail );
      }else {
        this.locationDetail.fkLocTypeId = $evt;
      }
       // this.selectedLocationType = this.locationDetail.fkLocTypeId;
    // }
  }
  */
  /*applyDropDownChange(selectedArr: SelectItemModel[]) {
    this.reqLocationType = false;
    this.locationDetail.fkLocTypeId = [];
    for ( let i = 0; i < selectedArr.length; i++ ) {
      this.locationDetail.fkLocTypeId.push(selectedArr[i].code);
    }
    // console.log($evt);
  }*/
}
