import { Component, OnInit, OnDestroy, ViewEncapsulation } from '@angular/core';
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
import { CostService } from './service/cost.service';
import { viewcost , addcost } from '../../../shared/interface/router-links';
import { CostMaster } from './model/add-cost-model';
import { Subscription } from 'rxjs/Subscription';
import { ConfirmationService } from '../../../shared/confirm-box/confirm.service';
import { MESSAGE_SUCCESS, MESSAGE_ERROR, ACTION_SAVE } from '../../../shared/interface/common.constants';
import { ACTION_SUBMIT, ACTION_UPDATE, ACTION_ACTIVE, ACTION_DEACTIVE } from '../../../shared/interface/common.constants';
import { MessageModel } from '../../../shared/message/message.model';

@Component({
  selector: 'app-cost',
  templateUrl: './cost.component.html',
  styleUrls: ['./cost.component.css'],
  encapsulation: ViewEncapsulation.None,
})
export class CostComponent implements OnInit, OnDestroy {

  private link: Link;
  group: GroupDescriptor[] = [{ field: 'status' }];
  multiple: Boolean = false;
  gridView: GridDataResult;
  allowUnsort: Boolean = true;
  sort: SortDescriptor[] = [];
  gridData: GridDataResult;
  public allItemsChecked: Boolean = false;
  subscription: Subscription;
  costmaster: CostMaster;
  pkCostId: number;
  state: State = {
        skip: 0,
        take: 1000
    };




  constructor(private route: ActivatedRoute, http: Http, private router: Router,
    private masterSetupService: MasterSetupService,
    public costmasterService: CostService,
    private confirmationService: ConfirmationService) { }

  ngOnInit() {
     // this.costmasterService.messages = new MessageModel();
     this.loadingCostMasterList();
  }
  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  loadingCostMasterList() {
     this.subscription = this.costmasterService.getCostMasterDetailsJSON().subscribe(getMasterDetail => {
     this.costmasterService.costMasterList = getMasterDetail.body;
     for ( let i = 0; i < this.costmasterService.costMasterList.length; i++ ) {
      this.costmasterService.costMasterList[i].matrixApplicableInd =
       (this.costmasterService.costMasterList[i].matrixApplicableInd === 'Y' ? 'Yes' : 'No')
     }
    // alert(getMasterDetail.body);
     });
  }

  /* Navigate to Add Cost */
  costAdd() {

  }

  pageChange(event: PageChangeEvent): void {
        this.state.skip = event.skip;
       // this.loadingCostMasterList();
  }

  viewById(event) {
      this.costmasterService.viewbyIdParam = event;
      this.router.navigate([viewcost]);
  }

   public dataStateChange(state: DataStateChangeEvent): void {
         this.state = state;
        this.gridData = process(this.costmasterService.costMasterList, this.state);
  }

  checkAllClicked(e) {
   // console.log('checkAllClicked', e);
   if (e.target.checked) {
      this.allItemsChecked = true;
      for (let i = 0; i < this.costmasterService.costMasterList.length; i++) {
        this.costmasterService.costMasterList[i].checked = true;
      }
    } else {
      this.allItemsChecked = false;
      for (let i = 0; i < this.costmasterService.costMasterList.length; i++) {
        this.costmasterService.costMasterList[i].checked = false;
      }
    }
  }
  selectUnSelectAllChecked(e) {
    if (!e.target.checked) {
      this.allItemsChecked = false;
    } else {
      let selected;
      selected = 0;
      for (let i = 0; i < this.costmasterService.costMasterList.length; i++) {
        if (!this.costmasterService.costMasterList[i].checked) {
          this.allItemsChecked = false;
          break;
        } else {
          selected++;
        }
      }
      if (selected === this.costmasterService.costMasterList.length) {
        this.allItemsChecked = true;
      }
    }
  //  console.log(this.allItemsChecked);
}

  goToAddCost() {
  this.router.navigate([addcost]);
}

cost_delete(costid, costcode, costname, statusname, param) {
 this.pkCostId = costid;
// confirm box Code

  this.confirmationService.confirm({
        message: 'Confirm.Common.Delete',
        // isCommentPresent: false,
        // icon: './../assets/image/Deactiveicon2.svg',
        placeHolder: [costname],
        accept: (event) => {
            this.changeCostStatus(costname);
        },
        reject: (event) => {
        }
    });

}

/*Delete Method for Cost */
changeCostStatus(costname) {
   // const link = this.masterSetupService.getChildObject('Update Brand.PUT');
   // const url = link.href;
   const url = '';
   this.subscription = this.costmasterService.deleteCostMaster(url, this.pkCostId)
      .subscribe(deletedStatus => {
        // Acknowledgement code
        this.costmasterService.messages = { severity: MESSAGE_SUCCESS, summary: 'Message.Common.Delete',
         placeHolder: ['Cost', costname] };
        this.loadingCostMasterList();
      });

}
/* -------End----------*/

/* -------- Activate --------*/

cost_Activate(costid, costcode, costname, statusname, param) {
 // this.pkCostId = costid;
// confirm box Code
// alert(JSON.stringify(param));
  this.confirmationService.confirm({
        message: 'Confirm.Common.Deactivate',
        // isCommentPresent: false,
        // icon: './../assets/image/Deactiveicon2.svg',
        placeHolder: [costname],
        accept: (event) => {
            this.costStatusChange(param, statusname);
        },
        reject: (event) => {
        }
    });

}

cost_DeActivate(costid, costcode, costname, statusname, param) {
// this.pkCostId = costid;
// confirm box Code
// alert(JSON.stringify(param));
  this.confirmationService.confirm({
        message: 'Confirm.Common.Reactivate',
        // isCommentPresent: false,
        // icon: './../assets/image/Deactiveicon2.svg',
        placeHolder: [costname],
        accept: (event) => {
            this.costStatusChange(param, statusname);
        },
        reject: (event) => {
        }
    });

}

/*Status Change Method for Cost */
costStatusChange(param, statusname) {
   // const link = this.masterSetupService.getChildObject('Update Brand.PUT');
   // const url = link.href;
    this.costmaster = param;
 // tslint:disable-next-line:max-line-length
 this.subscription = this.costmasterService.getSelectedMatrixData(this.costmaster.pkCostId).subscribe(matrixDetail => {
                     this.costmaster = matrixDetail.body[0];
    if ( statusname === 'Active') {
        this.costmaster.action = 'Deactive';
    } else {
        this.costmaster.action  = 'Submit';
    }

   const url = '';
   this.subscription = this.costmasterService.updateCost(url, this.costmaster)
      .subscribe(response => {
        // Acknowledgement code
         if (statusname === 'Active') {
          // this.costdata.action = 'Deactive';
          this.costmasterService.messages = { severity: MESSAGE_SUCCESS, summary: 'Message.Common.Deactivate',
         placeHolder: ['Cost', this.costmaster.costName] };
        } else if (statusname === 'InActive') {
          // this.costdata.action = 'Submit';
          this.costmasterService.messages = { severity: MESSAGE_SUCCESS, summary: 'Message.Common.Reactivate',
         placeHolder: ['Cost', this.costmaster.costName] };
        } else if (statusname === 'Draft') {
          this.costmasterService.messages = { severity: MESSAGE_SUCCESS, summary: 'Message.Common.Delete',
         placeHolder: ['Cost', this.costmaster.costName] };
        }
        // this.costmasterService.messages = { severity: MESSAGE_SUCCESS, summary: response.body};
        this.loadingCostMasterList();
      });
        });
}
/* -------End----------*/








}
