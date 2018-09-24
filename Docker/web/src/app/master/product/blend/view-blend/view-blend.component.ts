import { Component, OnInit, OnDestroy, Output, ViewChild, EventEmitter, ViewEncapsulation } from '@angular/core';
import { MasterSetupService } from '../../../master-setup/service/master-setup.service';
import { FormGroup, FormControl } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { SelectItem } from '../../../../shared/interface/selectItem';
import { ResponseData } from '../../../../shared/interface/responseData';
import { Link } from '../../../../shared/interface/link';
import { blend } from './../../../../shared/interface/router-links';
import { ExcelExportData } from '@progress/kendo-angular-excel-export';
import { GridComponent, GridDataResult, DataStateChangeEvent, PageChangeEvent} from '@progress/kendo-angular-grid';
import { process, SortDescriptor, orderBy, GroupDescriptor, State } from '@progress/kendo-data-query';
import { ModalDirective } from 'ngx-bootstrap';
import { Subscription } from 'rxjs/Subscription';
import { ConfirmationService } from '../../../../shared/confirm-box/confirm.service';
// tslint:disable-next-line:max-line-length
import {MESSAGE_SUCCESS, MESSAGE_ERROR, ACTION_SAVE, ACTION_SUBMIT, ACTION_UPDATE, ACTION_ACTIVE, ACTION_DEACTIVE, STATUS_ACTIVE, STATUS_DRAFT, STATUS_INACTIVE } from '../../../../shared/interface/common.constants';
import { MessageModel } from '../../../../shared/message/message.model';
import { BlendService } from '.././service/blend.service';
import { BlendMatrix } from '.././model/blend-model';

@Component({
  selector: 'app-view-blend',
  templateUrl: './view-blend.component.html',
  styleUrls: ['./view-blend.component.css']
})
export class ViewBlendComponent implements OnInit, OnDestroy {
  blendData: BlendMatrix;
  subscription: Subscription;
  // tenorvalues: ForwardTenor;
  blendOutArray = [];
  blendOutlength: number;
  blendInArray = [];
  blendInlength: number;
  blendCerArray = [];
  blendCerlength: number;
  getSubmitValue: string;
  dialogValue: string;
  successDialog: string;
  index = 0;
  private viewId;
  id: any;
  public STATUS_DRAFT: any  = STATUS_DRAFT;
  public STATUS_ACTIVE: any = STATUS_ACTIVE;
  public STATUS_INACTIVE: any = STATUS_INACTIVE;
  @ViewChild('successModal') public successModal: ModalDirective;
  @ViewChild('confirmSuccessModal') public confirmSuccessModal: ModalDirective;
  public status: any = {
    isFirstOpen: true,
    isFirstDisabled: false
  };
  constructor(private route: ActivatedRoute, http: Http, private router: Router,
    private masterSetupService: MasterSetupService,
    public blendService: BlendService,
    private confirmationService: ConfirmationService) { }

 ngOnInit() {
     if (this.route.snapshot.params['id'] !== undefined) {
      this.viewId = this.route.snapshot.params['id'];
      this.loadViewById(this.viewId);
    }
  }

  loadViewById(viewId) {
    this.blendService.getSelectedMatrixData('', viewId).subscribe(data => {
      if (data.body !== null && data.body !== '') {
        this.blendData = data.body[0];
        console.log('blenddata' + this.blendData);
      }
    // this.blendData = this.blendService.getRowData();
     console.log('this.blendData    ' + JSON.stringify(this.blendData));
    if (this.blendData.blendOutputList !== null && this.blendData.blendOutputList !== undefined) {
      this.blendOutlength = this.blendData.blendOutputList.length;
      this.blendOutArray = this.blendData.blendOutputList;
      console.log('this.blendOutArray    ' + JSON.stringify(this.blendOutArray));
    }
    if (this.blendData.blendInputList !== null && this.blendData.blendInputList !== undefined) {
      this.blendInlength = this.blendData.blendInputList.length;
      this.blendInArray = this.blendData.blendInputList;
            console.log('this.blendInArray    ' + JSON.stringify(this.blendInArray));
    }
    if (this.blendData.blendInputCertificationList !== null && this.blendData.blendInputCertificationList !== undefined) {
      this.blendCerlength = this.blendData.blendInputCertificationList.length;
      this.blendCerArray = this.blendData.blendInputCertificationList;
        console.log('this.blendCerArray    ' + JSON.stringify(this.blendCerArray));
    }
     } , error => { console.log(JSON.stringify(error)) });
  }

  openConfirmAction(status) {
    if (status === STATUS_DRAFT) {
      this.getSubmitValue = '';
    //  this.dialogValue = 'Do you want Delete the  Blend Template ' + this.blendData.templateName;
      this.confirmationService.confirm({
          message: 'Confirm.Common.Delete', placeHolder: [this.blendData.templateName],
          accept: (event) => {
             this.confirmDelete();
          },
          reject: (event) => {
             // this.confirmCancelation();
          }
      });
    } else if (status === STATUS_ACTIVE) {
   //   this.dialogValue = 'Do you want DeActivate the Blend Template ' + this.blendData.templateName;
       this.confirmationService.confirm({
          message: 'Confirm.Common.Deactivate', placeHolder: [this.blendData.templateName],
          accept: (event) => {
             this.onSubmitBtnClick(this.blendData.statusName);
          },
          reject: (event) => {
             // this.confirmCancelation();
          }
      });
    } else if (status === STATUS_INACTIVE) {
   //   this.dialogValue = 'Do you want Re-Activate the  Blend Template ' + this.blendData.templateName;

       this.confirmationService.confirm({
          message: 'Confirm.Common.Reactivate', placeHolder: [this.blendData.templateName],
          accept: (event) => {
             this.onSubmitBtnClick(this.blendData.statusName);
          },
          reject: (event) => {
             // this.confirmCancelation();
          }
      });
    }else if (status === 'submit') {
       this.getSubmitValue = status;
  //     this.dialogValue = 'Do you want Activate the Blend Template ' + this.blendData.templateName;
      this.confirmationService.confirm({
          message: 'Confirm.Common.Activate', placeHolder: [this.blendData.templateName],
          accept: (event) => {
             this.onSubmitBtnClick(this.blendData.statusName);
          },
          reject: (event) => {
             // this.confirmCancelation();
          }
      });
    }

  }

  closedeletepopup() {
    this.successModal.hide();
  }

  status_update(statusName) {
    if (this.blendData.statusName === 'Draft' && this.getSubmitValue === '') {
   // this.confirmdeletepop();
    }else if (this.blendData.statusName === 'Draft' && this.getSubmitValue === 'submit') {
     // this.confirmupdatepop();
    } else {
     // this.confirmsubmitpop();
    }
  }

  onSubmitBtnClick(statusName) {
    if (this.blendData.statusName === STATUS_DRAFT) {
      this.blendData.statusName = 'Draft';
      this.blendData.action = ACTION_SUBMIT;
      this.subscription = this.blendService.updateBlendDetail('', this.blendData)
        .subscribe(data => {
            this.router.navigate([blend]);
             this.blendService.messages = {severity: MESSAGE_SUCCESS, summary: 'Message.Common.Activate',
            placeHolder: ['Blend', this.blendData.templateName]} ;
        });
    } else if (this.blendData.statusName === STATUS_ACTIVE) {
      this.blendData.statusName = 'InActive';
      this.blendData.action = ACTION_DEACTIVE;
      this.subscription = this.blendService.updateBlendDetail('', this.blendData)
        .subscribe(data => {
          this.router.navigate([blend]);
          this.blendService.messages = {severity: MESSAGE_SUCCESS, summary: 'Message.Common.Deactivate',
            placeHolder: ['Blend', this.blendData.templateName]} ;
        });
    } else if (this.blendData.statusName === STATUS_INACTIVE) {
      this.blendData.statusName = 'Active';
      this.blendData.action  = ACTION_SUBMIT;
      this.subscription = this.blendService.updateBlendDetail('', this.blendData)
        .subscribe(data => {
            this.router.navigate([blend]);
            this.blendService.messages = {severity: MESSAGE_SUCCESS, summary: 'Message.Common.Reactivate',
            placeHolder: ['Blend', this.blendData.templateName]} ;
        });
    }
  }


   confirmDelete() {
    this.subscription = this.blendService.deleteBlendTemp(this.blendData.pkBlendTemplateId)
      .subscribe(deletedStatus => {
             this.router.navigate([blend]);
             this.blendService.messages = {severity: MESSAGE_SUCCESS, summary: 'Message.Common.Delete',
                placeHolder: ['Blend', this.blendData.templateName] } ;
      },
      error => { throw error });
  }

  ngOnDestroy() {

  }

  backtoView() {
    this.router.navigate([blend]);
  }

}
