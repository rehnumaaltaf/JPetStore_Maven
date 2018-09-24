import { Component, OnInit, OnDestroy, Output, ViewChild, EventEmitter, ViewEncapsulation } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';
import { ExternalPacking } from '../model/external-packing';
import { ModalDirective } from 'ngx-bootstrap';
import { ExternalPackingService } from '../service/external-packing.service';
import { ActivatedRoute, Router } from '@angular/router';
import { secondaryPack } from './../../../../shared/interface/router-links';
import { ResponseData } from '../../../../shared/interface/responseData';
import { ExternalPackingMapping } from '../model/external-packing';
import { MESSAGE_SUCCESS, MESSAGE_ERROR } from '../../../../shared/interface/common.constants';
import { MessageModel } from '../../../../shared/message/message.model';

@Component({
  selector: 'app-view-external-packing',
  templateUrl: './view-external-packing.component.html',
  styleUrls: ['./view-external-packing.component.css']
})
export class ViewExternalPackingComponent implements OnInit, OnDestroy {
   public status: any = {
    isFirstOpen: true,
    isFirstDisabled: false
  };

  subscription: Subscription;
  secondaryPacking: ExternalPacking;
  isconfirmSuccessModal;
  isConfirmPopupModal;
  dialogValue: string;
  successDialog: string;
  primaryid: string;
  errorMessage: string;
  errorModal;
  // tenorvalues: ForwardTenor;
  packArray = [];
  // edit;
  packlength: number;
  index = 0;
  @ViewChild('successModal') public successModal: ModalDirective;
  @ViewChild('confirmSuccessModal') public confirmSuccessModal: ModalDirective;
  getSubmitValue: string;
  // financialdata: ResponseData;
  constructor(private route: ActivatedRoute, private router: Router, public packingService: ExternalPackingService) { }

  ngOnInit() {
    this.loadUpdatePack();
  }

  loadUpdatePack() {
    this.secondaryPacking = this.packingService.getRowData();
    // console.log('this.secondaryPack    ' + JSON.stringify(this.secondaryPacking));
    // if (this.secondaryPacking.secPackAssocList !== null && this.secondaryPacking.secPackAssocList !== undefined) {
    //   this.packlength = this.secondaryPacking.secPackAssocList.length
    //   this.packArray = this.secondaryPacking.secPackAssocList;
    // }
    this.subscription = this.packingService.getDataById('', this.secondaryPacking.pkSecondaryPackingTypeId).
      subscribe(getData => {
        this.secondaryPacking = getData.body[0];
        console.log(this.secondaryPacking);
        if (this.secondaryPacking.secPackAssocList !== null && this.secondaryPacking.secPackAssocList !== undefined) {
          this.packlength = this.secondaryPacking.secPackAssocList.length
          this.packArray = this.secondaryPacking.secPackAssocList;
        }
      });
  }

  ngOnDestroy() {

  }

  backtoView() {
    this.router.navigate([secondaryPack]);
  }

  onHidden() {
    this.isconfirmSuccessModal = false;
  }

  onHiddenpopup() {
    this.isConfirmPopupModal = false;
  }

  openConfirmAction(status) {
    if (status === 'Draft') {
      this.getSubmitValue = '';
      this.dialogValue = 'Please Confirm to Delete ' + this.secondaryPacking.secondaryPackingTypeName;
    } else if (status === 'Active') {
      this.dialogValue = 'Please Confirm to Deactivate ' + this.secondaryPacking.secondaryPackingTypeName;
    } else if (status === 'InActive') {
      this.dialogValue = 'Please Confirm to Reactivate ' + this.secondaryPacking.secondaryPackingTypeName;
    }else if (status === 'submit') {
       this.getSubmitValue = status;
       this.dialogValue = 'Please Confirm to Activate ' + this.secondaryPacking.secondaryPackingTypeName;
    }
    this.isConfirmPopupModal = true;

  }

  closedeletepopup() {
    this.successModal.hide();
  }

  status_update() {
    if (this.secondaryPacking.statusName === 'Draft' && this.getSubmitValue === '') {
      this.confirmDelete();
    }else if (this.secondaryPacking.statusName === 'Draft' && this.getSubmitValue === 'submit') {
       this.onUpdateActive();
    } else {
      this.onSubmitBtnClick(this.secondaryPacking.statusName);
    }
  }

  confirmDelete() {
    this.subscription = this.packingService.deletePackingMaterial(this.secondaryPacking.pkSecondaryPackingTypeId)
      .subscribe(deletedStatus => {
        this.closedeletepopup();
        // tslint:disable-next-line:max-line-length
        this.packingService.messages = { severity: MESSAGE_SUCCESS, summary: 'Message.SecondaryPacking.Delete', placeHolder: ['\'' + this.secondaryPacking.secondaryPackingTypeName + '\'']};
        this.router.navigate([secondaryPack]);
      },
      error => {
        this.packingService.messages = { severity: MESSAGE_ERROR, summary: this.packingService.errorMessage };
      });
  }

  onSubmitBtnClick(statusName) {
    if (this.secondaryPacking.statusName === 'Draft') {
      this.secondaryPacking.statusName = 'Draft';
      this.subscription = this.packingService.updateSecPack('', this.secondaryPacking)
        .subscribe(data => {
          this.closedeletepopup();
        // tslint:disable-next-line:max-line-length
        this.packingService.messages = { severity: MESSAGE_SUCCESS, summary: 'Message.SecondaryPacking.Activate', placeHolder: ['\'' + this.secondaryPacking.secondaryPackingTypeName + '\'']};
        this.router.navigate([secondaryPack]);
        });
    } else if (this.secondaryPacking.statusName === 'Active') {
      this.secondaryPacking.statusName = 'InActive';
      console.log('this.secondaryPacking     ' + this.secondaryPacking);
      this.subscription = this.packingService.updateSecPack('', this.secondaryPacking)
        .subscribe(data => {
          this.closedeletepopup();
          // tslint:disable-next-line:max-line-length
          this.packingService.messages = { severity: MESSAGE_SUCCESS, summary: 'Message.SecondaryPacking.Deactivate', placeHolder: ['\'' + this.secondaryPacking.secondaryPackingTypeName + '\'']};
          this.router.navigate([secondaryPack]);
        });
    } else if (this.secondaryPacking.statusName === 'InActive') {
      this.secondaryPacking.statusName = 'Active';
      this.subscription = this.packingService.updateSecPack('', this.secondaryPacking)
        .subscribe(data => {
        this.closedeletepopup();
        // tslint:disable-next-line:max-line-length
        this.packingService.messages = { severity: MESSAGE_SUCCESS, summary: 'Message.SecondaryPacking.Reactivate', placeHolder: ['\'' + this.secondaryPacking.secondaryPackingTypeName + '\'']};
        this.router.navigate([secondaryPack]);
      });
    }
  }

  onUpdateActive() {
    this.secondaryPacking.statusName = 'Active';
    this.subscription = this.packingService.updateSecPack('', this.secondaryPacking)
      .subscribe(data => {
        this.closedeletepopup();
        // tslint:disable-next-line:max-line-length
        this.packingService.messages = { severity: MESSAGE_SUCCESS, summary: 'Message.SecondaryPacking.Activate', placeHolder: ['\'' + this.secondaryPacking.secondaryPackingTypeName + '\'']};
        this.router.navigate([secondaryPack]);
      });
  }

}
