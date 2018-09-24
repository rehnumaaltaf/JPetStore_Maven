import { Component, OnInit, OnDestroy, ViewEncapsulation, ViewChild } from '@angular/core';
import { MasterSetupService } from '../../master-setup/service/master-setup.service';
import { FormGroup, FormControl } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { SelectItem } from '../../../shared/interface/selectItem';
import { ResponseData } from '../../../shared/interface/responseData';
import { Link } from '../../../shared/interface/link';
import { ExcelExportData } from '@progress/kendo-angular-excel-export';
import { GridComponent, GridDataResult, DataStateChangeEvent, PageChangeEvent} from '@progress/kendo-angular-grid';
import { process, SortDescriptor, orderBy, GroupDescriptor, State } from '@progress/kendo-data-query';
import { ModalDirective } from 'ngx-bootstrap';
import { Subscription } from 'rxjs/Subscription';
import { ConfirmationService } from '../../../shared/confirm-box/confirm.service';
import { MESSAGE_SUCCESS, MESSAGE_ERROR, ACTION_SAVE } from '../../../shared/interface/common.constants';
// tslint:disable-next-line:max-line-length
import { ACTION_SUBMIT, ACTION_UPDATE, ACTION_ACTIVE, ACTION_DEACTIVE, STATUS_INACTIVE, STATUS_DRAFT, STATUS_ACTIVE } from '../../../shared/interface/common.constants';
import { MessageModel } from '../../../shared/message/message.model';
import { BlendService } from './service/blend.service';
import { BlendMatrix } from './model/blend-model';
import { blend, viewBlend, editBlend, addBlend} from '../../../shared/interface/router-links';

@Component({
  selector: 'app-blend',
  templateUrl: './blend.component.html',
  styleUrls: ['./blend.component.css']
})
export class BlendComponent implements OnInit, OnDestroy {
    loadingData: boolean;
    subscription: Subscription;
    blendMaster: BlendMatrix;
    blendModel: BlendMatrix = new BlendMatrix();
    blendUpdateModel: BlendMatrix = new BlendMatrix();
    state: State = {
        skip: 0,
        take: 1000
    };
    isDeletePopupModal: boolean;
    deactivateSuccessDialog: string;
    deleteSuccessModal: boolean;
    isupdateModal: boolean;
    openEdit: Boolean = false;
    @ViewChild('editModal') public editModal: ModalDirective;
    message: string;
    UpdateModal = false;
    public success;
    addModal: boolean;
    gridView: GridDataResult;
    allItemsChecked: Boolean = false;
    dialogValue: string;
    blendStatus;
    blendId;
    blendCode;
   public STATUS_DRAFT: any  = STATUS_DRAFT;
   public STATUS_ACTIVE: any = STATUS_ACTIVE;
   public STATUS_INACTIVE: any = STATUS_INACTIVE;
  constructor(private route: ActivatedRoute, http: Http, private router: Router,
    private masterSetupService: MasterSetupService,
    public blendService: BlendService,
    private confirmationService: ConfirmationService) { }

  ngOnInit() {
         this.loadingBlendMasterList();
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  blend_delete() {
     this.blendStatus = this.blendService.getStatusname();
     if (this.blendStatus === 'Draft') {
       this.confirmDelete();
     }else {
        this.onSubmitBtnClick(this.blendStatus);
     }
  }
    confirmDelete() {
    this.blendId = this.blendService.getBlendPkId();
    alert(this.blendId);
    this.blendCode = this.blendService.getBlendCode();
    this.subscription = this.blendService.deleteBlendTemp(this.blendId)
      .subscribe(deletedStatus => {
      //    this.successModal.hide();
         // this.deactivateSuccessDialog = 'You have successfully deleted Secondary Packing ' + this.blendCode;
       //    this.blendService.messages = {severity: MESSAGE_SUCCESS, summary: brandDetail.body};
           this.blendService.messages = {severity: MESSAGE_SUCCESS, summary: 'Message.Common.Delete',
                placeHolder: ['Blend', this.blendCode.templateName] } ;
          this.deleteSuccessModal = true;
          this.loadingBlendMasterList();
        },
      error => { throw error });
  }

onSubmitBtnClick(status) {
    this.blendId = this.blendService.getBlendPkId();
    this.blendCode = this.blendService.getBlendCode();
    this.blendUpdateModel = this.blendService.getRowData();

    if (status === ACTION_ACTIVE) {
       this.blendUpdateModel.statusName = 'InActive';
        this.subscription = this.blendService.updateBlendDetail('', this.blendUpdateModel).subscribe(data => {
     //     this.successModal.hide();
          //  this.deactivateSuccessDialog = 'You have successfully deactivated Secondary Packing ' + this.blendCode;
          this.blendService.messages = {severity: MESSAGE_SUCCESS, summary: 'Message.Blend.DeActivate',
            placeHolder: ['Blend', this.blendCode.templateName] } ;
          this.deleteSuccessModal = true;
          this.loadingBlendMasterList();
      },
      error => {
        throw error;
      });
    }else if (status === ACTION_DEACTIVE) {
        this.blendUpdateModel.statusName = 'Active';
        this.subscription = this.blendService.updateBlendDetail('', this.blendUpdateModel).subscribe(data => {
            this.blendService.messages = {severity: MESSAGE_SUCCESS, summary: 'Message.Blend.Activate',
            placeHolder: ['Blend', this.blendCode.templateName] } ;
            this.loadingBlendMasterList();
      },
      error => {
        throw error;
      });
    }
  }

 onHidden() {
   this.deleteSuccessModal = false;
  }

  onHiddenpopup() {
   this.isDeletePopupModal = false;
  }

  loadingBlendMasterList() {
    this.loadingData = true;
   this.subscription = this.blendService.getBlendMasterDetailsJSON(this.blendMaster).subscribe(data => {
    this.blendService.blendMasterList = <BlendMatrix[]>data.body;
    this.loadPackingDetails();
    this.loadingData = false;
    }, error => {
      this.loadingData = false;
    });
  }

 goToAddBlend() {
    this.router.navigate([addBlend]);
  }

 loadPackingDetails(): void {
    this.gridView = {
      data: process(this.blendService.blendMasterList, this.state).data,
      total:   this.blendService.blendMasterList.length
    };
    console.log(JSON.stringify(this.blendService.blendMasterList));
  }

pageChange(event: PageChangeEvent): void {
        this.state.skip = event.skip;
       // this.loadingCostMasterList();
  }

  viewById(event, id) {
      this.blendService.viewbyIdParam = event;
      this.router.navigate([viewBlend + '/' +  id]);
  }

  checkAllClicked(e) {
   // console.log('checkAllClicked', e);
   if (e.target.checked) {
      this.allItemsChecked = true;
      for (let i = 0; i < this.blendService.blendMasterList.length; i++) {
        this.blendService.blendMasterList[i].checked = true;
      }
    } else {
      this.allItemsChecked = false;
      for (let i = 0; i < this.blendService.blendMasterList.length; i++) {
        this.blendService.blendMasterList[i].checked = false;
      }
    }
  }
   public dataStateChange(state: DataStateChangeEvent): void {
         this.state = state;
        this.gridView = process(this.blendService.blendMasterList, this.state);
  }

   DeleteBlendDetails(keyid, blendCode, status, i, rowData) {
     alert(keyid);
    // alert(status);
    this.blendModel = this.blendService.blendMasterList[i];
     this.blendService.setBlendCode(blendCode);
     this.blendService.setStatusname(status);
     this.blendService.setBlendPkId(keyid);
     this.blendService.setRowData(rowData);
console.log( this.blendService.getBlendPkId());

  this.confirmationService.confirm({
          message: 'Confirm.Common.Delete', placeHolder: [this.blendModel.templateName],
          accept: (event) => {
             this.blend_delete();
          },
          reject: (event) => {
              this.confirmCancelation();
          }
      });
      }

confirmCancelation() {
    this.blendModel =  Object.assign({}, this.blendUpdateModel);
    this.router.navigate([blend]);
    // this.cancelModal.hide();
  }

}
