import { Component, OnInit, OnDestroy, Output, ViewChild, EventEmitter, ViewEncapsulation } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';
import { LocationModel } from '../model/location.model';
import { StatusCode } from '../model/status.enum';
import { ActivatedRoute, Router } from '@angular/router';
import { LocationService } from '../service/location.service';
import { location } from '../../../../shared/interface/router-links';
import { MessageModel } from '../../../../shared/message/message.model';
import { ModalDirective } from 'ngx-bootstrap';
import { ConfirmBoxModel } from '../confirm-box/confirm-box.model';
import { SelectItemModel } from '../model/select-item.model';
import { RateBasis } from '../model/ratebasis.enum';
import { ConfirmationService } from '../../../../shared/confirm-box/confirm.service';
import { MESSAGE_SUCCESS, MESSAGE_ERROR, ACTION_SAVE } from '../../../../shared/interface/common.constants';
import { ACTION_SUBMIT, ACTION_UPDATE, ACTION_ACTIVE, ACTION_DEACTIVE } from '../../../../shared/interface/common.constants';

@Component({
  selector: 'app-view-location',
  templateUrl: './view-location.component.html',
  styleUrls: ['./view-location.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class ViewLocationComponent implements OnInit, OnDestroy {
  subscription: Subscription;
  locationDetail: LocationModel;
  status: typeof StatusCode = StatusCode;
  disableSaveBtn: Boolean = false;
  disableSubmitBtn: Boolean = false;
  rateBasis: typeof RateBasis = RateBasis;
  @Output() loadingData: Boolean = true;
  @ViewChild('editModal') public editModal: ModalDirective;
  @ViewChild('confirmModal') public confirmModal: ModalDirective;
  // locationMessages: MessageModel[];
  confirmBoxConfigure: ConfirmBoxModel;
  changedStatusId: number;
  openEdit: Boolean =  false;
  pkLocId: number;
  rateBasisList: SelectItemModel[] = [{code: 1, value: 'UOM'}, {code: 2, value: 'Primary Packing'}, {code: 3, value: 'Secondary Packing'}];
  rateBasisPrimaryPackList: SelectItemModel[] = [{code: 1, value: 'pp1'}, {code: 2, value: 'pp2'},
  {code: 3, value: 'pp3'}];
  rateBasisSecondaryPackList: SelectItemModel[] = [{code: 1, value: 'sp1'}, {code: 2, value: 'sp2'},
  {code: 3, value: 'sp3'}];
  constructor(private route: ActivatedRoute, private router: Router, public locationService: LocationService,
  public confirmationService: ConfirmationService) { }

  ngOnInit() {
    // this.locationDetail = this.locationService.getRowData();
    this.locationService.messages = new MessageModel();
    // console.log('locationDetail', this.locationDetail);
     if (this.route.snapshot.params['id'] !== undefined) {
      this.pkLocId = this.route.snapshot.params['id'];
    // if ( this.locationDetail ) {
      // this.pkLocId = this.locationDetail.pkLocId;
        this.loadLocationDetail();
        /*this.locationDetail.rateBasisPrimaryPacking = this.getValueByCode(this.rateBasisPrimaryPackList,
        this.locationDetail.rateBasisPrimaryPackingId);
        this.locationDetail.rateBasisSecondaryPacking = this.getValueByCode(this.rateBasisSecondaryPackList,
        this.locationDetail.rateBasisSecondaryPackingId);
        this.locationDetail.fkRateBasisRefName = this.getValueByCode(this.rateBasisList,
        this.locationDetail.fkRateBasisRefId);*/
     }
     if ( !this.pkLocId ) {
       this.router.navigate([location]);
     }
  }

  ngOnDestroy() {
    // prevent memory leak when component destroyed
    // this.subscription.unsubscribe();
  }

   loadLocationDetail() {
    this.loadingData = true;
      this.subscription = this.locationService.getLocationDetail(this.pkLocId).subscribe(data => {
        this.locationDetail = data.body[0];
      //  console.log(JSON.stringify(this.locationDetail));
        /*this.locationDetail.locationTypeNames = this.locationDetail.fkLocTypeName.join();
        this.locationDetail.rateBasisPrimaryPacking = this.getValueByCode(this.rateBasisPrimaryPackList,
        this.locationDetail.rateBasisPrimaryPackingId);
        this.locationDetail.rateBasisSecondaryPacking = this.getValueByCode(this.rateBasisSecondaryPackList,
        this.locationDetail.rateBasisSecondaryPackingId);
        this.locationDetail.fkRateBasisRefName = this.getValueByCode(this.rateBasisList,
        this.locationDetail.fkRateBasisRefId);*/
        // console.log('here', this.locationDetail );
        this.loadingData = false;
      }, error => {
       throw error;
      });
   }
      openConfirmAction() {
     this.changedStatusId =  -1 ;
     if ( this.locationDetail.fkStatusId === this.status.Draft ) {
        this.changedStatusId = this.status.Draft;
        // this.confirmBoxConfigure = {message: 'Do you really want to delete?',
        // header: 'Delete Confirmation', isVisible: true, isCommentPresent: false, comment: ''};
         this.confirmationService.confirm({
            message: 'Confirm.Common.Delete',
            placeHolder: [this.locationDetail.locName],
            accept: (event) => {
               this.onDeleteBtnClick();

            },
            reject: (event) => {
            }
        });
     } else if ( this.locationDetail.fkStatusId === this.status.Active ) {
        this.changedStatusId = this.status.Inactive;
        // this.confirmBoxConfigure = {message: 'Do you really want to deactivate?',
        // header: 'Delete Confirmation', isVisible: true, isCommentPresent: false, comment: ''};
        this.confirmationService.confirm({
            message: 'Confirm.Common.Deactivate',
            placeHolder: [this.locationDetail.locName],
            accept: (event) => {
               this.onSubmitBtnClick(this.changedStatusId);
            },
            reject: (event) => {
            }
        });
     } else if ( this.locationDetail.fkStatusId === this.status.Inactive ) {
         this.changedStatusId = this.status.Active;
        //  this.confirmBoxConfigure = {message: 'Do you really want to reactivate?',
        //  header: 'Delete Confirmation', isVisible: true, isCommentPresent: false, comment: ''};
        this.confirmationService.confirm({
            message: 'Confirm.Common.Reactivate',
            placeHolder: [this.locationDetail.locName],
            accept: (event) => {
               this.onSubmitBtnClick(this.changedStatusId);
            },
            reject: (event) => {
            }
        });
     }
   }
   /*
   confirmCancelation($event) {
     if ( this.changedStatusId === this.status.Draft ) {
        this.onDeleteBtnClick();
     } else {
        this.onSubmitBtnClick(this.changedStatusId);
     }
     this.confirmModal.hide();
    // this.confirmBoxConfigure.isVisible = false;
   }
   closeConfirmBox($event) {
     this.confirmModal.hide();
     // this.confirmBoxConfigure.isVisible = false;
   }
   onHiddenConfirm() {
      this.confirmBoxConfigure.isVisible = false;
   } */
   onDeleteBtnClick(): void {
        this.subscription = this.locationService.deleteLocationDetails(this.locationDetail.pkLocId).subscribe(data => {
       /* this.locationService.messages = [];
        this.locationService.messages.push({severity: 'success',  summary: 'Location ' + this.locationDetail.locCode + ' & ' +
        this.locationDetail.locName + ' has been successfully deleted', detail: ''});*/
       console.log(data.body[0]);
     //  this.locationDetail = data.body[0];
        this.router.navigate([location]);
        this.locationService.messages = {severity: MESSAGE_SUCCESS, summary: 'Message.Location.Delete',
              placeHolder: [this.locationDetail.locCode , this.locationDetail.locName]};
     //   this.loadingData = false;

      },
      error => {
        throw error;
      });
    }
  onSubmitBtnClick(statusId): void {
        this.disableSubmitBtn = true;
        this.loadingData = true;
        this.disableSaveBtn = true;
        this.disableSubmitBtn = true;
        const locationDetail = Object.assign({}, this.locationDetail);
        locationDetail.fkStatusId = statusId;
        locationDetail.fkLocTypeName = '';
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
        if ( statusId !== this.status.Active) {
           locationDetail.action = 'Deactive';
        } else {
           locationDetail.action  = 'Submit';
        }
         locationDetail.rateBasisPrimaryPackingId = +this.locationDetail.rateBasisPrimaryPackingId;
         locationDetail.rateBasisSecondaryPackingId = +this.locationDetail.rateBasisSecondaryPackingId;
        this.subscription = this.locationService.updateLocationDetails(locationDetail).subscribe(data => {
          // this.locationMessages = [];
          this.router.navigate([location]);
          if (statusId === this.status.Active) {
            if (this.locationDetail.fkStatusId !== statusId ) {
                /* this.locationMessages.push({severity: 'success',
             summary: 'Location ' + locationDetail.locCode + ' & ' +
             locationDetail.locName + ' has been saved and Activated', detail: ''}); */
             this.locationService.messages = {severity: MESSAGE_SUCCESS, summary: 'Message.Common.Activate',
             placeHolder: ['Location' , this.locationDetail.locName]};
            }else {
              /* this.locationMessages.push({severity: 'success',
             summary: 'Location ' + locationDetail.locCode +
             ' & ' + locationDetail.locName + ' has been saved successfully', detail: ''});*/
              this.locationService.messages = {severity: MESSAGE_SUCCESS, summary: 'Message.Common.Save',
              placeHolder: ['Location' , this.locationDetail.locName]};
            }
          }else if (statusId === this.status.Inactive) {
            if (this.locationDetail.fkStatusId !== statusId ) {
                /*  this.locationMessages.push({severity: 'success',
             summary: 'Location ' +
             locationDetail.locCode + ' & ' + locationDetail.locName
              + ' has been saved and Deactivated', detail: ''});*/
              this.locationService.messages = {severity: MESSAGE_SUCCESS, summary: 'Message.Common.Deactivate',
              placeHolder: ['Location' , this.locationDetail.locName]};
            }else {
               /* this.locationMessages.push({severity: 'success',
             summary: 'Location ' + locationDetail.locCode +
             ' & ' + locationDetail.locName + ' has been saved successfully', detail: ''});*/
              this.locationService.messages = {severity: MESSAGE_SUCCESS, summary: 'Message.Common.Save',
              placeHolder: ['Location' , this.locationDetail.locName]};
            }
          }else {
            /* this.locationMessages.push({severity: 'success',
             summary: 'Location ' + locationDetail.locCode +
             ' & ' + locationDetail.locName + ' has been saved successfully', detail: ''});*/
              this.locationService.messages = {severity: MESSAGE_SUCCESS, summary: 'Message.Common.Save',
              placeHolder: [this.locationDetail.locCode , this.locationDetail.locName]};
          }
          this.disableSaveBtn = false;
          this.disableSubmitBtn = false;
          this.loadingData = false;
          this.loadLocationDetail();
      },
      error => {
        throw error;
      });
    }


  edit() {
    this.openEdit = true;
  }

  closeEditModal($event) {
    this.editModal.hide();
  if ($event.message && $event.message.length !== 0) {
      this.locationService.messages = $event.message;
      this.loadLocationDetail();
    }
  }
   onHideEditModal(): void {
    this.openEdit = false;
   }

   back() {
     this.router.navigate([location]);
   }

   getValueByCode(arr, id): string {
    let val: string;
    for (let i = 0; i < arr.length; i++ ) {
      if (arr[i].code === id) {
        val = arr[i].value;
        break;
      }
    }
    return val;
  }

}
