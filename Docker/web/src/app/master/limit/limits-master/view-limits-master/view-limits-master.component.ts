import { Component, OnInit, ViewChild } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { LimitsMasterService } from '../service/limits-master.service';
import { ModalDirective } from 'ngx-bootstrap';
import {
  LimitsMaster, LimitDetails, LimitsAttribute, Comments, inactive, active, draft, deactive, save,
  update, reactivate, msgsuccess, msgupdate, msgactivateorsubmit, msgdelete, msgdeactivate, msgreactivate, msgsave
  , msgconfirmdeactivate, msgconfirmdelete, msgconfirmreactive
} from '../model';

@Component({
  selector: 'app-view-limits-master',
  templateUrl: './view-limits-master.component.html',
  styleUrls: ['./view-limits-master.component.css']
})
export class ViewLimitsMasterComponent implements OnInit {
  @ViewChild('deletePopUpModal') public deletePopUpModal1: ModalDirective;
  @ViewChild('serverErrorModal') public serverErrorModal: ModalDirective;
  public status: any = {
    isFirstOpen: true,
    isFirstDisabled: false
  };
  errorMessage: string;
  servererrorflag: boolean;
  public dataloaded: Boolean = false;
  public validFromDate: Date;
  public validToDate: Date;
  status_change: boolean;
  limitdata: LimitsMaster = new LimitsMaster();
  limtDetails: LimitDetails = new LimitDetails();
  draft: boolean;
  active: boolean;
  inactive: boolean;
  public limitBasisTypeId;
  commentText = [];
  subscription: Subscription;
  isDeletePopupModal: boolean;
  deactiveStatus: boolean;
  isActiveStatus: boolean;
  createdBy: string;
  modifiedDate: string;
  statusmsg: string;
  constructor(private route: ActivatedRoute, private limitsMasterService: LimitsMasterService, private router: Router) { }
  ngOnInit() {
    this.servererrorflag = false;
    this.limitBasisTypeId = this.route.snapshot.params['limitHeaderId'];
    this.getLimitsDetailsById(JSON.parse(this.route.snapshot.params['limitHeaderId']));
  }

  backtoPrev() {
    this.router.navigate(['master/limit/limits-master']);
  }


  getLimitsDetailsById(limitBasisTypeId) {
    this.subscription = this.limitsMasterService.getLimitsDetailsById(limitBasisTypeId).subscribe(limitDetails => {
      this.limitdata = limitDetails.body;
      this.dataloaded = true;
      if (this.limitdata != null) {
        this.limitsMasterService.limitName = this.limitdata.limitBasisTypeName;
        this.limitdata.comments.forEach(list => {
          this.commentText.push(list.commentText)

        });
        if (this.limitdata.modifiedDate == null) {
          this.modifiedDate = this.limitdata.createdDate;
        } else {
          this.modifiedDate = this.limitdata.modifiedDate;
        }
        if (this.limitdata.modifiedBy == null) {
          this.createdBy = this.limitdata.createdBy;

        } else {
          this.createdBy = this.limitdata.modifiedBy;
        }
        this.validFromDate = new Date(this.limtDetails.validFrom);
        this.validToDate = new Date(this.limtDetails.validTo);

        if (this.limitdata.statusName === draft.toString()) {
          this.draft = true;
          this.active = false;
          this.inactive = false;
        } else if (this.limitdata.statusName === active.toString()) {
          this.active = true;
          this.draft = false;
          this.inactive = false;
        } else if (this.limitdata.statusName === inactive.toString()) {
          this.inactive = true;
          this.active = false;
          this.draft = false;
        }


      } else {
        this.router.navigate(['master/limit/limits-master']);
      }


    },

      error => {
        this.errorMessage = error;
        this.servererrorflag = true;
        setTimeout(() => { this.serverErrorModal.hide(); }, 3000);
      }
    );
  }
  conf_status_change_draft() {
    const addoreditpage = false; // add page is true , edit page is false
    this.limitdata.statusName = active.toString();
    this.limitsMasterService.limitName = this.limitdata.limitBasisTypeName;
    this.limitsMasterService.msgStatusName = active.toString()
    this.subscription = this.limitsMasterService.saveLimitMaster(this.limitdata, addoreditpage).
      subscribe(updateData => {
        this.router.navigate(['master/limit/limits-master']);
      }, error => {
        this.status_change = false;
      },
      () => console.log('Finished')

      );
  }
  conf_status_change_to_active() {
    const addoreditpage = false; // add page is true , edit page is false
    this.limitdata.statusName = active.toString();
    this.limitsMasterService.msgStatusName = deactive.toString();
    this.limitsMasterService.limitName = this.limitdata.limitBasisTypeName;
    this.deactiveStatus = true;
    this.statusmsg = msgconfirmdeactivate.toString();
    this.isDeletePopupModal = true;
  }

  // pop confirm active to deactive and deactive to active
  conf_deactive_reactive(event) {
    this.deactiveStatus = false;
    this.isActiveStatus = false;
    this.deletePopUpModal1.hide();
    this.limitsMasterService.limitName = this.limitdata.limitBasisTypeName;
    const addoreditpage = false; // add page is true , edit page is false
    this.subscription = this.limitsMasterService.saveLimitMaster(this.limitdata, addoreditpage).
      subscribe(updateData => {
        this.router.navigate(['master/limit/limits-master']);
      }, error => {
        this.status_change = false;
      },
      () => console.log('Finished')

      );

  }
  hide() {
    this.isDeletePopupModal = false;
    this.servererrorflag = false;
  }

  conf_status_change_deactivate() {
    this.limitsMasterService.limitName = this.limitdata.limitBasisTypeName;
    this.limitdata.statusName = inactive.toString();
    this.limitsMasterService.msgStatusName = active.toString();
    this.isActiveStatus = true;
    this.statusmsg = msgconfirmreactive.toString();
    this.isDeletePopupModal = true;
  }


}
