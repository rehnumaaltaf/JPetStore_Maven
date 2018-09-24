import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { AccordionModule } from 'ngx-bootstrap';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { MasterSetupService } from '../../../master-setup/service/master-setup.service';
import { CostMatrix } from '../model/cost-matrix.model';
import { DefaultUnit, SelectMatrix, ExternalSystem} from '../../cost/model/add-cost-model';
import { CostMatrixService } from '../service/cost-matrix.service';
import { Subscription } from 'rxjs/Subscription';
import { cost, editcost, editcostmatrix, costmatrix } from '../../../../shared/interface/router-links';
import { ConfirmationService } from '../../../../shared/confirm-box/confirm.service';
import { MESSAGE_SUCCESS, MESSAGE_ERROR } from '../../../../shared/interface/common.constants';
import { MessageModel } from '../../../../shared/message/message.model';

@Component({
  selector: 'app-view-cost-matrix',
  templateUrl: './view-cost-matrix.component.html',
  styleUrls: ['./view-cost-matrix.component.css']
})
export class ViewCostMatrixComponent implements OnInit {
  public status: any = {
    isFirstOpen: true,
    isFirstDisabled: false
  };
  costdata: CostMatrix = new CostMatrix();
  matrixname: string;
  isFreight: boolean;
  isWarehouse: boolean;
  isCNF: boolean;
  isPacking: boolean;
  isCropSeason: boolean;
  cloningData: number;
  subscription: Subscription;
  /* External Array*/
  externalsysArray = [];
  externalArraylength: number;
  matrixMasterArray = [];
  matrixMasterArraylength: number;
  matrixDetailArray = [];
  matrixDetailArraylength: number;
  index = 0;
  costid;
  messages: MessageModel;
  public defaultUnit: typeof DefaultUnit = DefaultUnit;


  constructor(private costMatrixService: CostMatrixService, private route: ActivatedRoute,
    http: Http, private router: Router, private masterSetupService: MasterSetupService,
    private confirmationService: ConfirmationService) { }

  ngOnInit() {
    if (this.route.snapshot.params['id'] !== undefined && this.route.snapshot.params['name'] !== undefined) {
      this.costdata.pkCostMatrixId = this.route.snapshot.params['id'];
      this.costdata.matrixTypeName = this.route.snapshot.params['name'];
      this.loadViewById();
    } else {
    }
  }
  loadViewById() {
        this.subscription = this.costMatrixService.getSelectedMatrixData(this.costdata.pkCostMatrixId, this.costdata.matrixTypeName)
        .subscribe(matrixDetail => {
        this.costdata = matrixDetail.body;
        });
  }


  /* BAck to Grid*/
  backtoPrev() {
    this.router.navigate([costmatrix]);
  }


  /*-------End-----*/

  /* Draft record save as active */
  onSubmitBtnClick(costdata) {
     const url = '';
     this.costdata.action = 'Submit';
    this.subscription = this.costMatrixService.updateCost(url, this.costdata)
      .subscribe(response => {
        // Acknowledgement code
        this.messages = { severity: MESSAGE_SUCCESS,  summary: 'Message.Common.Submit',
         placeHolder: ['Cost', this.costdata.matrixName] };
         this.router.navigate([costmatrix]);
         // this.loadViewById();
 });


  }
  /*------------End-----------*/


  /*Delete Method for Cost */
  changeCostStatus(costdata) {
    // const link = this.masterSetupService.getChildObject('Update Brand.PUT');
    // const url = link.href;
    const url = '';
    this.subscription = this.costMatrixService.deleteCostMatrix(url, costdata.pkCostMatrixId, costdata.matrixTypeName)
      .subscribe(deletedStatus => {
        // Acknowledgement code
        this.router.navigate([costmatrix]);
        this.costMatrixService.messages = { severity: MESSAGE_SUCCESS,  summary: 'Message.Common.Delete',
         placeHolder: ['Cost', this.costdata.matrixName] };
      });

  }


  /*Status Change Method for Cost */
  costStatusChange(param, statusname) {
    // const link = this.masterSetupService.getChildObject('Update Brand.PUT');
    // const url = link.href;

    if (statusname === 'Active') {
      this.costdata.action = 'Deactive';
    } else {
      this.costdata.action = 'Submit';
    }
    const url = '';
    this.subscription = this.costMatrixService.updateCost(url, this.costdata)
      .subscribe(response => {
        // Acknowledgement code
        // this.messages = { severity: MESSAGE_SUCCESS, summary: response.body };
        if (statusname === 'Active') {
          this.messages = { severity: MESSAGE_SUCCESS, summary: 'Message.Common.Deactivate',
         placeHolder: ['Cost', this.costdata.matrixName] };
        } else if (statusname === 'InActive') {
          this.messages = { severity: MESSAGE_SUCCESS, summary: 'Message.Common.Reactivate',
         placeHolder: ['Cost', this.costdata.matrixName] };
        } else if (statusname === 'Draft') {
          this.messages = { severity: MESSAGE_SUCCESS, summary: 'Message.Common.Delete',
         placeHolder: ['Cost', this.costdata.matrixName] };
        }
         this.router.navigate([costmatrix]);
        // this.loadViewById();
        // console.log(this.messages);
        // this.router.navigate([costmatrix]);
      });

  }
  /* -------End----------*/
  /* Status Change for Cost*/
  openConfirmAction(costdata, statusname) {
    // alert('inside the confirmbox' + statusname);

    if (statusname === 'Draft') {
      this.confirmationService.confirm({
        message: 'Confirm.Common.Delete',
        placeHolder: [this.costdata.matrixName],
        accept: (event) => {
          this.changeCostStatus(costdata);
        },
        reject: (event) => {
        }
      });
    } else if (statusname === 'Active') {
      this.confirmationService.confirm({
        message: 'Confirm.Common.Deactivate',
        placeHolder: [this.costdata.matrixName],
        accept: (event) => {
          this.costStatusChange(costdata, statusname);
        },
        reject: (event) => {
        }
      });
     } else if (statusname === 'InActive') {
      this.confirmationService.confirm({
        message: 'Confirm.Common.Reactivate',
        placeHolder: [this.costdata.matrixName],
        accept: (event) => {
          this.costStatusChange(costdata, statusname);
        },
        reject: (event) => {
        }
      });
      }
     }
  /*--------End---------*/

  edit(matrixTypeName) {
    this.router.navigate([editcostmatrix, this.costdata.pkCostMatrixId, matrixTypeName]);
  }
}
