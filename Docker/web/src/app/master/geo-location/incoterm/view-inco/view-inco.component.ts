import { Component, Inject, OnInit, OnDestroy, Output, Input, ViewChild } from '@angular/core';
import { AccordionModule } from 'ngx-bootstrap';
import { Incoterm } from '../inco-terms-interface';
import { IncotermService } from '../service/incoterm.service';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { incoTerm } from '../../../../shared/interface/router-links';
import { ModalDirective } from 'ngx-bootstrap/modal';
import { editIncoTerm } from '../../../../shared/interface/router-links';

@Component({
  selector: 'app-view-inco',
  templateUrl: './view-inco.component.html',
  styleUrls: ['./view-inco.component.css']
})
export class ViewIncoComponent implements OnInit, OnDestroy {

public incotermData: Incoterm = new Incoterm();
  public status: any = {
    isFirstOpen: true,
    isFirstDisabled: false
  };
  public incotermDataObj;
  public featureList1 = [];
  public moduleNameList = [];
  public NameList = [];
  public budList = [];
  public SfeatureList1 = [];
  public SmoduleNameList = [];
  public SNameList = [];
  public SbudList = [];
  subscription: Subscription;
  public Purchasenotnull;
  public Salesnotnull;
    public draft;
  public active;
  public inactive;
  statusMsg: String;
  contractTermsName: String;
  deleteMsg: String;
  public del_id;
  statusVal: String;
  @ViewChild('statusChangeConfirm') public statusChangeConfirm: ModalDirective;
  @ViewChild('deletepopup') public deletepopup: ModalDirective;
  constructor(public incotermService: IncotermService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit() {
   this.incotermData.contractTermsId = this.route.snapshot.params['contractTermsId'];
    this.incotermDataObj = this.route.snapshot.params['contractTermsId'];
    this.subscription = this.incotermService.getIncotermById(this.incotermDataObj).subscribe(incoTermData => {
      this.incotermData = incoTermData.body;

       if (this.incotermData.statusName === 'Draft') {
        this.draft = true;
        this.active = false;
        this.inactive = false;
      } else if (this.incotermData.statusName === 'Active') {
        this.active = true;
        this.draft = false;
        this.inactive = false;
      } else if (this.incotermData.statusName === 'InActive') {
        this.inactive = true;
        this.active = false;
        this.draft = false;
      }
if (this.incotermData.contractTermsPurchaseDto != null) {
  this.Purchasenotnull = true;

    for (let i = 0; i < this.incotermData.contractTermsPurchaseDto.length; i++) {

        const featureVal = this.incotermData.contractTermsPurchaseDto[i].costGroupName;

        const moduleName = this.incotermData.contractTermsPurchaseDto[i].costName;

        const Name = this.incotermData.contractTermsPurchaseDto[i].budgetInd;

        const budget = this.incotermData.contractTermsPurchaseDto[i].addReduceName;

        this.featureList1.push(featureVal);
        console.log(JSON.stringify(this.featureList1));
        this.moduleNameList.push(moduleName);
        this.NameList.push(Name);
        this.budList.push(budget);
      }
    } else {
      this.Purchasenotnull = false;
    }
    if (this.incotermData.contractTermsSalesDto != null) {
    this.Salesnotnull = true;
        for (let i = 0; i < this.incotermData.contractTermsSalesDto.length; i++) {
        const sfeatureVal = this.incotermData.contractTermsSalesDto[i].costGroupName;
        const smoduleName = this.incotermData.contractTermsSalesDto[i].costName;

        const sName = this.incotermData.contractTermsSalesDto[i].budgetInd;

        const sbudget = this.incotermData.contractTermsSalesDto[i].addReduceName;
        this.SfeatureList1.push(sfeatureVal);
        console.log(JSON.stringify(this.SfeatureList1));
        this.SmoduleNameList.push(smoduleName);
        this.SNameList.push(sName);
        this.SbudList.push(sbudget);
      }
    } else {
 this.Salesnotnull = false;
    }
  },
      error => { throw error });
}

 ngOnDestroy() {
    this.subscription.unsubscribe();
  }

   backtoPrev() {
    this.router.navigate([incoTerm]);
  }

    confirm(statusname) {
    this.contractTermsName = this.incotermData.contractTermsName;
    this.statusChangeConfirm.show();

    if (statusname === 'Active') {
      this.statusVal = statusname;
        this.statusMsg = 'reactivate';
     this.incotermData.statusName = 'Active';
   } else if (statusname === 'InActive') {
     this.statusVal = statusname;
      this.statusMsg = 'deactivate';
      this.incotermData.statusName = 'InActive';
   }else if (statusname === 'submit') {
     this.statusVal = statusname;
      this.statusMsg = 'activate';
      this.incotermData.statusName = 'Active';
   }
  }

  conf_status_change(event) {

    const updateIncotermArray: Incoterm[] = Array();
    updateIncotermArray.push(this.incotermData);

    this.subscription = this.incotermService.updateIncoterm(updateIncotermArray).subscribe(updateData => {
      this.statusChangeConfirm.hide();
      this.router.navigate([incoTerm], {
        queryParams: {
          'update': 3, 'termName':
          this.incotermData.contractTermsName, 'status': this.statusVal
        }
      });

    }, error => {
      //this.errormsg.show();
    },
      () => console.log('Finished')

    );
  }

    incoterm_delete(params) {
    this.deletepopup.show();
    this.deleteMsg = 'delete';
    this.incotermData = params;
    this.contractTermsName = this.incotermData.contractTermsName;
      this.del_id = this.incotermData.contractTermsId;

  }

  conf_delete(id) {
    this.subscription = this.incotermService.deleteIncotermdetails(this.del_id).subscribe(deletedStatus => {
      this.deletepopup.hide();
      this.router.navigate([incoTerm], {
        queryParams: {
          'delSuccess': 1, 'termName':
          this.incotermData.contractTermsName
        }
      });
    },
      error => alert(error),
      () => console.log('Finished')
    );
  }

  editIncoterm(contractTermsId) {
  // alert(contractTermsId);
   this.router.navigate([editIncoTerm + '/' +  contractTermsId]);
  }
}
