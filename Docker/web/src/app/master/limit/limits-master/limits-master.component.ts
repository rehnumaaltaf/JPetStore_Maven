import { Component, OnInit , OnDestroy , ViewChild } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';
import { ExcelExportData } from '@progress/kendo-angular-excel-export';
import { GridComponent, GridDataResult, DataStateChangeEvent, PageChangeEvent } from '@progress/kendo-angular-grid';
import { process, SortDescriptor, orderBy, GroupDescriptor, State } from '@progress/kendo-data-query';
import { LimitsMasterService } from './service/limits-master.service';
import {
  LimitsMaster, LimitDetails, LimitsAttribute, Comments, inactive, active, draft, deactive, save,
  update, reactivate, msgsuccess, msgupdate, msgactivateorsubmit, msgdelete, msgdeactivate, msgreactivate, msgsave
  , msgconfirmdeactivate, msgconfirmdelete, msgconfirmreactive
} from './model';
import {  ModalDirective } from 'ngx-bootstrap';
import { ActivatedRoute, Router } from '@angular/router';


@Component({
  selector: 'app-limits-master',
  templateUrl: './limits-master.component.html',
  styleUrls: ['./limits-master.component.css']
})
export class LimitsMasterComponent implements OnInit , OnDestroy {
    @ViewChild('successModals') public successModals: ModalDirective;
    @ViewChild('deletePopUpModal') public deletePopUpModal: ModalDirective;
  @ViewChild('deletesuccessModalview') public deletesuccessModalview: ModalDirective;
  @ViewChild('deletesuccessModal') public deletesuccessModalList: ModalDirective;
  @ViewChild('serverErrorModal') public serverErrorModal: ModalDirective;
   limitName: string;
  public del_id;
  public id;
   public success: number;
   limitCodedelpop: string;
   errorMessage: string;
   servererrorflag: boolean;
  isDeletePopupModal: Boolean = false;
  isDeactivatePopupModal: Boolean = false;
  isActiveStatus: Boolean = false;
  isDraftStatus: Boolean = false;
  isCancelUpdatePopupModal: Boolean = false;
  isShowModals: boolean;
  public isCannotDeletePopup;
  statusdelname: string;
  statusmsg: string;
  deleteSuccessModal: Boolean = false;
  public isShowModal: boolean;
  public savedData;
  public statusName;
  headerLimitBasisTypeName: string;
  headerLimitBasisName: string;
  headerAdditionalLimitBasisTypeName: string;
  headerAdditionalLimitBasisName: string;
  subscription: Subscription;
  data: LimitsMaster;
  msgsuccess: string;
  state: State = {
    skip: 0,
    take: 1000
  };
  masterName: string;
  group: GroupDescriptor[] = [{ field: 'statusName' }];
  gridView: GridDataResult;
  constructor(private limitsMasterService: LimitsMasterService, private router: Router) {

  }

  ngOnInit() {
    this.servererrorflag = false;
    this.loadLimitsMasterData();
     this.masterName = 'Limit';
  }
  ngOnDestroy() {
  }
  loadLimitsMasterData() {
    this.subscription = this.limitsMasterService.getLimitMasterJSON().subscribe(limitsMaster => {
      this.data = limitsMaster.body;
      if (limitsMaster.view !== null) {
       
        const data1 = limitsMaster.view.column.split(',');
        // this.loadingData = false;
        if (data1[0] != null && data1[0] === 'limitBasisTypeName') {
          this.headerLimitBasisTypeName = ' Limit Basis';
        }
        if (data1[1] != null && data1[1] === 'limitBasisName') {
          this.headerLimitBasisName = 'Basis Name';
        }
        if (data1[2] != null && data1[2] === 'additionallimitBasisTypeName') {
          this.headerAdditionalLimitBasisTypeName = 'Additional Basis';
        }
        if (data1[3] != null && data1[3] === 'additionallimitBasisName') {
          this.headerAdditionalLimitBasisName = 'Additional Basis Name';
        }
       if ( this.limitsMasterService.limitName != null) {
        
            this.limitName = this.limitsMasterService.limitName;
           }
          if (this.limitsMasterService.msgStatusName != null  && this.limitsMasterService.msgStatusName !== undefined ) {
           this.msgsuccess = msgsuccess.toString();
          if (this.limitsMasterService.msgStatusName.toLowerCase() ===  save.toString().toLowerCase()) {
               this.statusmsg = msgsave.toString();
          }

          if (this.limitsMasterService.msgStatusName.toLowerCase() ===  update.toString().toLowerCase()) {
              this.statusmsg = msgupdate.toString();
          }
          if (this.limitsMasterService.msgStatusName.toLowerCase() ===  active.toString().toLowerCase()) {
               this.statusmsg = msgactivateorsubmit.toString();
          }
          if (this.limitsMasterService.msgStatusName.toLowerCase() ===  deactive.toString().toLowerCase()) {
              this.statusmsg = msgdeactivate.toString();
          }

        if (this.limitsMasterService.msgStatusName.toLowerCase() ===  reactivate.toString().toLowerCase()) {
            this.statusmsg = msgreactivate.toString();
          }
          
           this.isShowModals = true;
         setTimeout(() => {this.successModals.hide(); this.limitsMasterService.msgStatusName = null  } , 3000);

        }
      }
    }
      ,
      error => {
          this.servererrorflag = true;
        this.errorMessage = error;
         setTimeout(() => { this.serverErrorModal.hide(); }, 3000);
      }
    );

  }

  gotoAddPage() {
    // this.router.navigate([addUom]);
    this.router.navigate(['master/limit/limits-master/add']);
  }

  checkorUnCheckAll(ele) {
    const checkboxes = document.getElementsByTagName('input');
    if (ele.target.checked) {
      for (let intval = 0; intval < checkboxes.length; intval++) {
        if (checkboxes[intval].type === 'checkbox') {
          checkboxes[intval].checked = true;
        }
      }
    } else {
      for (let i = 0; i < checkboxes.length; i++) {
        if (checkboxes[i].type === 'checkbox') {
          checkboxes[i].checked = false;
        }
      }
    }
  }

  viewById(event) {
    this.router.navigate(['/master/limit/limits-master/view/' + event.limitHeaderId]);
  }

  editToAddPage(event) {
     this.router.navigate(['/master/limit/limits-master/edit/' + event.limitHeaderId]);
  }

  limit_delete(event) {
    this.del_id = event.limitHeaderId;
     console.log(this.del_id);
    this.limitCodedelpop = event.limitBasisTypeCode;
    this.statusdelname = event.statusName;
    this.limitName = event.limitBasisTypeName;
    this.limitsMasterService.limitName =  event.limitBasisTypeName;
    this.isDeletePopupModal = true;
    if (event.statusName === draft.toString()) {
      this.isDraftStatus = true;
    }
  }

  conf_delete(id) {
    this.deletePopUpModal.hide();
    this.id = id;
    this.limitsMasterService.msgStatusName = msgdelete.toString();
    
     console.log(this.id);
     const param = { 'limitHeaderId': this.del_id };
    this.subscription = this.limitsMasterService.deleteLimit(param, this.limitName).subscribe(deletedStatus => {
      this.savedData = deletedStatus;
      this.deleteSuccessModal = true;
      if (this.statusName === 'Draft') {
        this.isDraftStatus = true;
      } else if (this.statusName === 'Active') {
        this.isActiveStatus = true;
      }
      setTimeout(() => { this.deletesuccessModalList.hide(); }, 3000);
      this.loadLimitsMasterData();

    },
      error => {
        this.servererrorflag = true;
        this.errorMessage = error;
         setTimeout(() => { this.serverErrorModal.hide(); }, 3000);
        this.isCannotDeletePopup = true;
      },
      () => console.log('Finished')
    );
  }

  hide() {
    this.isDeletePopupModal = false;
    this.isShowModals = false;
    this.servererrorflag = false;
  }


}
