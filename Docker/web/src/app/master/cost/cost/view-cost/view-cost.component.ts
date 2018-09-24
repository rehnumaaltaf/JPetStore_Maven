import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { AccordionModule } from 'ngx-bootstrap';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { MasterSetupService } from '../../../master-setup/service/master-setup.service';
import { CostMaster } from '../model/add-cost-model';
import { CostService } from '../service/cost.service';
import { Subscription } from 'rxjs/Subscription';
import { cost, editcost } from '../../../../shared/interface/router-links';
import { ConfirmationService } from '../../../../shared/confirm-box/confirm.service';
import { MESSAGE_SUCCESS, MESSAGE_ERROR } from '../../../../shared/interface/common.constants';
import { MessageModel } from '../../../../shared/message/message.model';
import { DefaultUnit, SelectMatrix, ExternalSystem  } from '../model/add-cost-model';
@Component({
  selector: 'app-view-cost',
  templateUrl: './view-cost.component.html',
  styleUrls: ['./view-cost.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class ViewCostComponent implements OnInit {
  public status: any = {
    isFirstOpen: true,
    isFirstDisabled: false
  };
  costdata: CostMaster = new CostMaster();
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


  constructor(private costmasterservice: CostService, private route: ActivatedRoute,
    http: Http, private router: Router, private masterSetupService: MasterSetupService,
    private confirmationService: ConfirmationService) { }

  ngOnInit() {
    if (this.route.snapshot.params['id'] !== undefined) {
      this.costdata.pkCostId = this.route.snapshot.params['id'];
      this.loadViewById();
    } else {
    }
  }
  loadViewById() {
        this.subscription = this.costmasterservice.getSelectedMatrixData(this.costdata.pkCostId).subscribe(matrixDetail => {
        this.costdata = matrixDetail.body[0];
          if (this.costdata.matrixApplicableInd !== null && this.costdata.matrixApplicableInd !== undefined) {
            for (let i = 0; i < this.costmasterservice.selectMatrixList.length; i++) {
              if (this.costmasterservice.selectMatrixList[i].value === +this.costdata.fkMatrixEntityId) {
                this.matrixname = this.costmasterservice.selectMatrixList[i].label;
              }
            }
          }
          this.externalArraylength = this.costdata.exterPackAssoc.length
          this.externalsysArray = this.costdata.exterPackAssoc;
        });
  }
  matrixServiceCalls(matrixname, costid) {
    this.subscription = this.costmasterservice.getSelectedMatrixData(costid)
      .subscribe(matrixDetail => {
        this.externalArraylength = this.costdata.exterPackAssoc.length
        this.externalsysArray = this.costdata.exterPackAssoc;
      //  alert('Matrix Details' + matrixDetail);
        if (this.matrixname === 'CNF') {
          this.costdata = matrixDetail.body[0];
          if (this.costdata.exterPackAssoc !== null && this.costdata.exterPackAssoc !== undefined) {

          }
        }

        console.log(matrixDetail);
      });
  }

  /* BAck to Grid*/
  backtoPrev() {
    this.router.navigate([cost]);
  }


  /*-------End-----*/

  /* Draft record save as active */
  onSubmitBtnClick(costdata) {
     const url = '';
     this.costdata.action = 'Submit';
    this.subscription = this.costmasterservice.updateCost(url, this.costdata)
      .subscribe(response => {
        // Acknowledgement code
        this.costmasterservice.messages = { severity: MESSAGE_SUCCESS,  summary: 'Message.Common.Submit',
         placeHolder: ['Cost', this.costdata.costName] };
          this.router.navigate([cost]);
       //  this.loadViewById();
 });


  }
  /*------------End-----------*/


  /*Delete Method for Cost */
  changeCostStatus(costdate) {
    // const link = this.masterSetupService.getChildObject('Update Brand.PUT');
    // const url = link.href;
    const url = '';
    this.subscription = this.costmasterservice.deleteCostMaster(url, costdate.pkCostId)
      .subscribe(deletedStatus => {
        // Acknowledgement code
        this.costmasterservice.messages = { severity: MESSAGE_SUCCESS,  summary: 'Message.Common.Delete',
         placeHolder: ['Cost', this.costdata.costName] };
          this.router.navigate([cost]);
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
    this.subscription = this.costmasterservice.updateCost(url, this.costdata)
      .subscribe(response => {
        // Acknowledgement code
        // this.messages = { severity: MESSAGE_SUCCESS, summary: response.body };
        if (statusname === 'Active') {
          this.costmasterservice.messages = { severity: MESSAGE_SUCCESS, summary: 'Message.Common.Deactivate',
         placeHolder: ['Cost', this.costdata.costName] };
        } else if (statusname === 'InActive') {
          this.costmasterservice.messages = { severity: MESSAGE_SUCCESS, summary: 'Message.Common.Reactivate',
         placeHolder: ['Cost', this.costdata.costName] };
        } else if (statusname === 'Draft') {
          this.costmasterservice.messages = { severity: MESSAGE_SUCCESS, summary: 'Message.Common.Delete',
         placeHolder: ['Cost', this.costdata.costName] };
        }
        this.router.navigate([cost]);
       // this.loadViewById();
        // console.log(this.messages);
        // this.router.navigate([cost]);
      });

  }
  /* -------End----------*/
  /* Status Change for Cost*/
  openConfirmAction(costdate, statusname) {
    // alert('inside the confirmbox' + statusname);

    if (statusname === 'Draft') {
      this.confirmationService.confirm({
        message: 'Confirm.Common.Delete',
        placeHolder: [this.costdata.costName],
        accept: (event) => {
          this.changeCostStatus(costdate);
        },
        reject: (event) => {
        }
      });
    } else if (statusname === 'Active') {
      this.confirmationService.confirm({
        message: 'Confirm.Common.Deactivate',
        placeHolder: [this.costdata.costName],
        accept: (event) => {
          this.costStatusChange(costdate, statusname);
        },
        reject: (event) => {
        }
      });
     } else if (statusname === 'InActive') {
      this.confirmationService.confirm({
        message: 'Confirm.Common.Reactivate',
        placeHolder: [this.costdata.costName],
        accept: (event) => {
          this.costStatusChange(costdate, statusname);
        },
        reject: (event) => {
        }
      });
      }
     }
  /*--------End---------*/

  edit() {
    this.router.navigate([editcost, this.costdata.pkCostId]);
  }
}
