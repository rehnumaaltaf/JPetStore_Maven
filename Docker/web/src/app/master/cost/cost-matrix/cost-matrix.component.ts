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
import { CostMatrixService } from './service/cost-matrix.service';
import { viewcost , addcost } from '../../../shared/interface/router-links';
import { CostMatrix } from './model/cost-matrix.model';
import { Subscription } from 'rxjs/Subscription';
import { ConfirmationService } from '../../../shared/confirm-box/confirm.service';
import { MESSAGE_SUCCESS, MESSAGE_ERROR, ACTION_SAVE } from '../../../shared/interface/common.constants';
import { ACTION_SUBMIT, ACTION_UPDATE, ACTION_ACTIVE, ACTION_DEACTIVE } from '../../../shared/interface/common.constants';
import { MessageModel } from '../../../shared/message/message.model';

@Component({
  selector: 'app-cost-matrix',
  templateUrl: './cost-matrix.component.html',
  styleUrls: ['./cost-matrix.component.css']
})
export class CostMatrixComponent implements OnInit, OnDestroy {

  private link: Link;
  group: GroupDescriptor[] = [{ field: 'status' }];
  multiple: Boolean = false;
  gridView: GridDataResult;
  allowUnsort: Boolean = true;
  sort: SortDescriptor[] = [];
  gridData: GridDataResult;
  public allItemsChecked: Boolean = false;
  subscription: Subscription;
  costMatrix: CostMatrix;
  pkCostMatrixId: number;
  matrixTypeName: string;
  state: State = {
        skip: 0,
        take: 1000
    };




  constructor(private route: ActivatedRoute, http: Http, private router: Router,
    private masterSetupService: MasterSetupService,
    public costMatrixService: CostMatrixService,
    private confirmationService: ConfirmationService) { }

  ngOnInit() {
     // this.costMatrixService.messages = new MessageModel();
      this.loadingCostMatrixList();
      /*
     this.costMatrixService.costMatrixList = [{'pkCostMatrixId': 1, 'matrixName': 'Frieght', 'matrixCode': 'OIL',
     'matrixDesc': 'Frieght Cost', 'fkCostId': 1, 'fkCostName': 'Frieght', 'matrixTypeId': 2, 'serviceProvider': 'Fright',
     'matrixTypeName': 'Frieght', 'fkStatusId': 2,  'fkStatusName': 'Draft', 'wareHouseCostDto': [], 'cnfCostDto': [],
     'freightCostMatrixDto': []},
    {'pkCostMatrixId': 2, 'matrixName': 'Warehouse', 'matrixCode': 'OIL', 'matrixDesc': 'Warehouse Cost', 'serviceProvider': 'Fright',
    'fkCostId': 2, 'fkCostName': 'Warehouse', 'matrixTypeId': 2, 'matrixTypeName': 'Warehouse', 'fkStatusId': 3,
    'fkStatusName': 'InActive', 'wareHouseCostDto': [], 'cnfCostDto': [], 'freightCostMatrixDto': []},
    {'pkCostMatrixId': 3, 'matrixName': 'Clearing & Forwarding', 'matrixCode': 'OIL',
    'matrixDesc': 'Clearing & Forwarding Cost', 'fkCostId': 3, 'fkCostName': 'Clearing & Forwarding', 'serviceProvider': 'Fright',
    'matrixTypeId': 2, 'matrixTypeName': 'Clearing & Forwarding', 'fkStatusId': 1, 'fkStatusName': 'Active', 'wareHouseCostDto': [],
     'cnfCostDto': [], 'freightCostMatrixDto': []}];*/
  }
  ngOnDestroy() {
    // this.subscription.unsubscribe();
  }

  loadingCostMatrixList() {
     this.subscription = this.costMatrixService.getCostMatrixDetailsJSON().subscribe(getMasterDetail => {
     this.costMatrixService.costMatrixList = getMasterDetail.body;
     });
  }


  pageChange(event: PageChangeEvent): void {
        this.state.skip = event.skip;
  }

  viewById(event) {
      this.costMatrixService.viewbyIdParam = event;
      this.router.navigate([viewcost]);
  }

   public dataStateChange(state: DataStateChangeEvent): void {
         this.state = state;
        this.gridData = process(this.costMatrixService.costMatrixList, this.state);
  }

  checkAllClicked(e) {
   if (e.target.checked) {
      this.allItemsChecked = true;
      for (let i = 0; i < this.costMatrixService.costMatrixList.length; i++) {
        this.costMatrixService.costMatrixList[i].checked = true;
      }
    } else {
      this.allItemsChecked = false;
      for (let i = 0; i < this.costMatrixService.costMatrixList.length; i++) {
        this.costMatrixService.costMatrixList[i].checked = false;
      }
    }
  }
  selectUnSelectAllChecked(e) {
    if (!e.target.checked) {
      this.allItemsChecked = false;
    } else {
      let selected;
      selected = 0;
      for (let i = 0; i < this.costMatrixService.costMatrixList.length; i++) {
        if (!this.costMatrixService.costMatrixList[i].checked) {
          this.allItemsChecked = false;
          break;
        } else {
          selected++;
        }
      }
      if (selected === this.costMatrixService.costMatrixList.length) {
        this.allItemsChecked = true;
      }
    }
}

  goToAddCost() {
  this.router.navigate([addcost]);
}

cost_matrix_delete(pkCostMatrixId, matrixTypeName, matrixName) {
 this.pkCostMatrixId = pkCostMatrixId;
 this.matrixTypeName = matrixTypeName;
  this.confirmationService.confirm({
        message: 'Confirm.Common.Delete',
        // isCommentPresent: false,
        // icon: './../assets/image/Deactiveicon2.svg',
        placeHolder: [matrixName],
        accept: (event) => {
            this.changeCostStatus(matrixName);
        },
        reject: (event) => {
        }
    });

}

/*Delete Method for Cost */
changeCostStatus(matrixName) {
   // const link = this.masterSetupService.getChildObject('Update Brand.PUT');
   // const url = link.href;
   const url = '';
   this.subscription = this.costMatrixService.deleteCostMatrix(url, this.pkCostMatrixId, this.matrixTypeName)
      .subscribe(deletedStatus => {
        // Acknowledgement code
        this.costMatrixService.messages = { severity: MESSAGE_SUCCESS, summary: 'Message.Common.Delete',
         placeHolder: ['Cost', matrixName] };
        this.loadingCostMatrixList();
      });

}
/* -------End----------*/

/* -------- Activate --------*/

cost_matrix_activate(costid, costcode, matrixName, statusname, param) {
 // this.pkCostMatrixId = costid;
// confirm box Code
// alert(JSON.stringify(param));
  this.confirmationService.confirm({
        message: 'Confirm.Common.Deactivate',
        // isCommentPresent: false,
        // icon: './../assets/image/Deactiveicon2.svg',
        placeHolder: [matrixName],
        accept: (event) => {
            this.costStatusChange(param, statusname);
        },
        reject: (event) => {
        }
    });

}

cost_matrix_deactivate(costid, costcode, matrixName, statusname, param) {
// this.pkCostMatrixId = costid;
// confirm box Code
// alert(JSON.stringify(param));
  this.confirmationService.confirm({
        message: 'Confirm.Common.Reactivate',
        // isCommentPresent: false,
        // icon: './../assets/image/Deactiveicon2.svg',
        placeHolder: [matrixName],
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
    this.costMatrix = param;
 // tslint:disable-next-line:max-line-length
/* this.subscription = this.costMatrixService.getSelectedMatrixData(this.costMatrix.pkCostMatrixId).subscribe(matrixDetail => {
                     this.costMatrix = matrixDetail.body[0];
    if ( statusname === 'Active') {
        this.costMatrix.action = 'Deactive';
    } else {
        this.costMatrix.action  = 'Submit';
    }

   const url = '';
   this.subscription = this.costMatrixService.updateCost(url, this.costMatrix)
      .subscribe(response => {
        // Acknowledgement code
         if (statusname === 'Active') {
          // this.costdata.action = 'Deactive';
          this.costMatrixService.messages = { severity: MESSAGE_SUCCESS, summary: 'Message.Common.Deactivate',
         placeHolder: ['Cost', this.costMatrix.matrixName] };
        } else if (statusname === 'InActive') {
          // this.costdata.action = 'Submit';
          this.costMatrixService.messages = { severity: MESSAGE_SUCCESS, summary: 'Message.Common.Reactivate',
         placeHolder: ['Cost', this.costMatrix.matrixName] };
        } else if (statusname === 'Draft') {
          this.costMatrixService.messages = { severity: MESSAGE_SUCCESS, summary: 'Message.Common.Delete',
         placeHolder: ['Cost', this.costMatrix.matrixName] };
        }
        // this.costMatrixService.messages = { severity: MESSAGE_SUCCESS, summary: response.body};
        this.loadingCostMatrixList();
      });
        });*/
}
/* -------End----------*/








}
