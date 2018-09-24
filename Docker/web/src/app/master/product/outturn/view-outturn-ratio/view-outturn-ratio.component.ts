import { Component, Inject, OnInit, OnDestroy, Output, Input, ViewEncapsulation, ViewChild } from '@angular/core';
import { AccordionModule } from 'ngx-bootstrap';
import { outturnRatio } from '../../../../shared/interface/router-links';
import { Router } from '@angular/router';
import { OutturnService } from '../service/outturn.service';
import { Outturn } from '../outturn';
import { ActivatedRoute } from '@angular/router';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Subscription } from 'rxjs/Subscription';
import { ModalModule } from 'ngx-bootstrap';
import { ModalDirective } from 'ngx-bootstrap/modal';
import { DialogModule } from 'primeng/primeng';
import { Link } from '../../../../shared/interface/link';
import { editout } from '../../../../shared/interface/router-links';
@Component({
  selector: 'app-view-outturn-ratio',
  templateUrl: './view-outturn-ratio.component.html',
  styleUrls: ['./view-outturn-ratio.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class ViewOutturnRatioComponent implements OnInit {
  public status: any = {
    isFirstOpen: true,
    isFirstDisabled: false
  };
  outturnData: Outturn = new Outturn();
  public outturnratioData: Outturn = new Outturn();

  public draft;
  public active;
  public inactive;
  statusMsg: any;
  public outturnDataObj;
  subscription: Subscription;
  public featureList1 = [];
  public moduleNameList = [];
  public NameList = [];
  rawGradeName: any;
  gradecode: any;
  deleteMsg: any;
  deleteStatus: any;
  del_id: any;
  @ViewChild('errormsg') public errormsg: ModalDirective;
  @ViewChild('statusChangeConfirm') public statusChangeConfirm: ModalDirective;
  @ViewChild('deletepopup') public deletepopup: ModalDirective;
  @ViewChild('outturnratiodelete') public outturnratiodelete: ModalDirective;

  constructor(private router: Router, public outturnRatioService: OutturnService, private route: ActivatedRoute) { }

  ngOnInit() {
   this.outturnData.outturnRawGradeDto.outturnRawGradeId = this.route.snapshot.params['outturnRawGradeId'];
    this.outturnDataObj = this.route.snapshot.params['outturnRawGradeId'];
    this.subscription = this.outturnRatioService.getOutturnratioById(this.outturnDataObj).subscribe(OutturnData => {
      this.outturnratioData = OutturnData.body;
      if (this.outturnratioData.statusName === 'Draft') {
        this.draft = true;
        this.active = false;
        this.inactive = false;
      } else if (this.outturnratioData.statusName === 'Active') {
        this.active = true;
        this.draft = false;
        this.inactive = false;
      } else if (this.outturnratioData.statusName === 'InActive') {
        this.inactive = true;
        this.active = false;
        this.draft = false;
      }

      for (let i = 0; i < this.outturnratioData.outturnRatioDto.length; i++) {
        // tslint:disable-next-line:max-line-length
        //   alert(this.basePaymentService.viewbasePaymentdetails.baseNagotiationTerm[i].nagotiationCode);
        const featureVal = this.outturnratioData.outturnRatioDto[i].finishedGradeCode;
        // tslint:disable-next-line:max-line-length
        const moduleName = this.outturnratioData.outturnRatioDto[i].quantityRatio;

        const Name = this.outturnratioData.outturnRatioDto[i].abilityToBearRatio;
        // tslint:disable-next-line:max-line-length
        this.featureList1.push(featureVal);
        console.log(JSON.stringify(this.featureList1));
        this.moduleNameList.push(moduleName);
        this.NameList.push(Name);
      }
    },
      error => { throw error });
    //  this.outturnData = this.outturnRatioService.viewbyIdParam;
    // this.outturnData.outturnRawGradeDto.GradeName = this.outturnratioData.outturnRawGradeDto.GradeName;
    //  alert('grade name' + this.outturnData.outturnRawGradeDto.GradeName);
    this.deletepopup.hide();
    this.statusChangeConfirm.hide();
    this.errormsg.hide();
  }


  backtoPrev() {
    this.router.navigate([outturnRatio]);
  }

  confirm(statusname) {
    this.rawGradeName = this.outturnratioData.outturnRawGradeDto.rawGradeName;
    this.statusChangeConfirm.show();

    if (statusname === 'Active') {
        this.statusMsg = 'reactivate';
     this.outturnratioData.statusName = 'Active';
   } else if (statusname === 'InActive') {
      this.statusMsg = 'deactivate';
      this.outturnratioData.statusName = 'InActive';
   }
  }

  conf_status_change(event) {
    this.subscription = this.outturnRatioService.updateOutturnratio(this.outturnratioData).subscribe(updateData => {
      this.statusChangeConfirm.hide();
      this.router.navigate([outturnRatio], {
        queryParams: {
          'update': 3, 'GradeName':
          this.outturnratioData.outturnRawGradeDto.rawGradeName, status: this.outturnratioData.statusName
        }
      });
      // this.router.navigate([outturnRatio]);

    }, error => {
      this.errormsg.show();
    },
      () => console.log('Finished')

    );
  }

  outturn_delete(params) {
    this.deletepopup.show();
    this.deleteMsg = 'Delete';
    this.deleteStatus = 'Draft';
    this.rawGradeName = params.outturnRawGradeDto.rawGradeName;
      this.del_id = params.outturnRawGradeDto.outturnRawGradeId;

  }

  conf_delete(id) {
    this.subscription = this.outturnRatioService.deleteOutturnratiodetails(this.del_id).subscribe(deletedStatus => {
      //  alert(this.outturnratioData.outturnRawGradeDto.outturnRawGradeId)
      this.deletepopup.hide();
      this.router.navigate([outturnRatio], {
        queryParams: {
          'delSuccess': 1, 'GradeName':
          this.outturnratioData.outturnRawGradeDto.rawGradeName
        }
      });
      // this.router.navigate([outturnRatio]);
    },
      error => alert(error),
      () => console.log('Finished')
    );
  }
  editOut(id) {
   /*  alert(id); */
   this.router.navigate([editout + '/' +  id]);
  }

  /*onHidden() {
     this.isoutturnratiodelete = false;
  }*/
}
