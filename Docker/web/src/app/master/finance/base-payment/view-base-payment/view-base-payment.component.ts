import { Component, ViewChild, OnInit ,  ViewEncapsulation} from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Router } from '@angular/router';
import { ModalDirective } from 'ngx-bootstrap/modal';
import { listingPageBasePaymnt } from '../../../../shared/interface/router-links';
import { addBasePaymnt } from '../../../../shared/interface/router-links';
import { BasePaymentService } from '../service/base-payment.service';
import { Subscription } from 'rxjs/Subscription';
import { BasePaymentInterface } from '../base-payment-interface';
import { BaseNagotiationTerm } from '../base-payment-interface';
@Component({
  selector: 'app-view-base-payment',
  templateUrl: './view-base-payment.component.html',
  styleUrls: ['./view-base-payment.component.css'],

   encapsulation:  ViewEncapsulation.None
})
export class ViewBasePaymentComponent implements OnInit {
  @ViewChild('statusChange1') public statusChange1: ModalDirective;
  @ViewChild('statusChange2') public statusChange2: ModalDirective;
public del_id;
public errormodal;
public basetermName;
public statusMsg;
public isDeletePopupModal;
public isDraftStatus;
public isCannotDeletePopup;
subscription: Subscription;
public id;
public inactive;
public active;
public draft;
public status1;
public deletebyid;
 public code;
public name;
public description;
toShowNegoTerm:boolean;
public risk;
public lcbased;
public iseditModal;
 public featureValList= [];
  public featureList1= [];
  public listOfbasePaymentedit = [];
  public desclist= [];
  public userDetails;
  public moduleNameList= [];
  public obj1: BasePaymentInterface = new BasePaymentInterface();
 public deletedIdsList= [];
  basePaymentDeatilsview: BasePaymentInterface = new BasePaymentInterface();
   data: BasePaymentInterface[];
  dataView: BasePaymentInterface[];
public savedData;
baseTermId:number;
public isShowModal;
public status_change;
public isActiveStatus;
deleteSuccessModal: boolean;
termNameToShow: string;
public errorMessage;
   public status: any = {
    isFirstOpen: true

    // isFirstDisabled: fals
  };
  constructor(private route: ActivatedRoute, http: Http, private router: Router, private basePaymentService: BasePaymentService) { }

  ngOnInit() {
    this.statusChange1.hide();
    this.statusChange2.hide();
    window.scrollTo(0, 0);
      this.deletebyid = false;
      this.status_change = false;
      this.isCannotDeletePopup = false;
       this.baseTermId = this.route.snapshot.params['id'];
       console.log(this.baseTermId);
    this.subscription = this.basePaymentService.getSelectedFeatues ('/uomservice/uom', this.baseTermId)
     .subscribe(addBasePayment => {
        this.userDetails = addBasePayment.body;
         console.log(this.userDetails);
            if (this.userDetails.statusName === 'Draft') {
        this.draft = true;
        this.active = false;
        this.inactive = false;
      }else if (this.userDetails.statusName === 'Active') {
        this.active = true;
        this.draft = false;
        this.inactive = false;
      }else if (this.userDetails.statusName === 'InActive') {
        this.inactive = true;
        this.active = false;
        this.draft = false;
  }


      this.basePaymentService.viewbasePaymentdetails = addBasePayment.body;
      this.code = this.basePaymentService.viewbasePaymentdetails.baseTermCode;
      this.name = this.basePaymentService.viewbasePaymentdetails.baseTermName;
      this.description = this.basePaymentService.viewbasePaymentdetails.baseTermDescription;
      this.risk = this.basePaymentService.viewbasePaymentdetails.baseTermCreditRisk;
      this.lcbased = this.basePaymentService.viewbasePaymentdetails.baseTermLCBased;
      this.status1 = this.basePaymentService.viewbasePaymentdetails.statusName;
       // tslint:disable-next-line:max-line-length
       if(this.basePaymentService.viewbasePaymentdetails.baseNagotiationTerm.length!=0) {
         this.toShowNegoTerm = true;
       } else {
         this.toShowNegoTerm = false;
       }
       console.log('===this.unitMeasurementService.uomDetails==>' + JSON.stringify(this.basePaymentService.viewbasePaymentdetails.baseNagotiationTerm));
    for (let i = 0 ; i < this.basePaymentService.viewbasePaymentdetails.baseNagotiationTerm.length; i++) {
                                      // tslint:disable-next-line:max-line-length
                                   //   alert(this.basePaymentService.viewbasePaymentdetails.baseNagotiationTerm[i].nagotiationCode);
                                      const featureVal = this.basePaymentService.viewbasePaymentdetails.baseNagotiationTerm[i].nagotiationCode;
                                    // tslint:disable-next-line:max-line-length
                                    const moduleName = this.basePaymentService.viewbasePaymentdetails.baseNagotiationTerm[i].nagotiationName;
                                      // tslint:disable-next-line:max-line-length
                                      const desc = this.basePaymentService.viewbasePaymentdetails.baseNagotiationTerm[i].printDescription;
                                      this.featureList1.push(featureVal);
                                      console.log(JSON.stringify(this.featureList1));
                                      this.moduleNameList.push(moduleName);
                                      this.desclist.push(desc);
     }
// console.log(JSON.stringify(this.basePaymentService.viewbasePaymentdetails.baseNagotiationTerm['nagotiationCode']));

                            /** for (let i = 0; i < this.featureValList.length; i++) {
                                  this.featureList1[i] = this.featureValList[i].replace("\"","");
                                }
                            for (let j = 0; j < this.featureList1.length; j++) {
                                  this.featureList1[j] = this.featureList1[j].replace("\"","");
                                }
                            for (let i = 0; i < this.moduleNameList.length; i++) {
                                  this.moduleNameList[i] = this.moduleNameList[i].replace("\"","");
                                }
                            for (let j = 0; j < this.featureList1.length; j++) {
                                  this.moduleNameList[j] = this.moduleNameList[j].replace("\"","");
                                }
                             for (let i = 0; i < this.desclist.length; i++) {
                                  this.desclist[i] = this.desclist[i].replace("\"","");
                                }
                            for (let j = 0; j < this.desclist.length; j++) {
                                  this.desclist[j] = this.desclist[j].replace("\"","");
                                }*/
// console.log(JSON.stringify(this.featureList1));
// console.log(JSON.stringify(this.moduleNameList));
// console.log(JSON.stringify(this.desclist));

     },
      error => alert(error),
      () => console.log('Finished')
    );
     // alert(JSON.stringify(this.userDetails.statusName));
   

}

backtoPrev1() {

  this.router.navigate([listingPageBasePaymnt]);
}
confirm1() {
  this.statusChange1.show();

}
confirm2() {
  this.statusChange2.show();

}
 updatewithStatus(statusName) {
  // alert(statusName);
  this.hideModal1();
  this.listOfbasePaymentedit =[];
  this.hideModal2();
   console.log(JSON.stringify(this.basePaymentService.viewbasePaymentdetails));
  this.basePaymentDeatilsview =  this.basePaymentService.viewbasePaymentdetails
   if (statusName === 'Active') {
     this.basePaymentDeatilsview.statusName = 'Active';
   } else if (statusName === 'InActive') {
      this.basePaymentDeatilsview.statusName = 'InActive';
   }
      this.listOfbasePaymentedit.push(this.basePaymentDeatilsview);
      this.subscription = this.basePaymentService.updateBasePayemnt(this.listOfbasePaymentedit).subscribe(savedwithStatus => {
      this.router.navigate([listingPageBasePaymnt], { queryParams: { 'success': 9} });
      },
      error => alert(error),
      () => console.log('Finished')
    );
 }
 updateSubmit(statusName){
  this.hideModal1();
  this.hideModal2();
  this.listOfbasePaymentedit =[];
   console.log(JSON.stringify(this.basePaymentService.viewbasePaymentdetails));
  this.basePaymentDeatilsview =  this.basePaymentService.viewbasePaymentdetails
   if (statusName === 'Active') {
     this.basePaymentDeatilsview.statusName = 'Active';
   } else if (statusName === 'InActive') {
      this.basePaymentDeatilsview.statusName = 'InActive';
   }
      this.listOfbasePaymentedit.push(this.basePaymentDeatilsview);
      this.subscription = this.basePaymentService.updateBasePayemnt(this.listOfbasePaymentedit).subscribe(savedwithStatus => {
      this.router.navigate([listingPageBasePaymnt], { queryParams: { 'success': 2} });
      },
      error => alert(error),
      () => console.log('Finished')
    ); 
 }
 user_delete(id1, event) {
   debugger;
   this.del_id = id1;
   this.code = event.baseTermName;
   // console.log(id1);
   // this.basetermName = termName;
   // this.BasePaymentService.roleNameToShow = roleName;
   this.statusMsg = 'Delete';
   this.status = 'Draft';
   this.isDeletePopupModal = true;
   this.isDraftStatus = true;

}
closedeletepopup() {
   this.isDeletePopupModal = false;
   this.isCannotDeletePopup = false;
}




conf_delete(id) {
      console.log(id);
      this.id = id;
     // this.termNameToShow = this.basePaymentService.termNameToShow;
      const paramList = [];
      const x = {'baseTermId': this.del_id};
      const paramobj = { 'baseTermId' : []};
      this.obj1.basePaymentTermId = this.del_id;
     paramobj.baseTermId = paramList;
     // {'basePaymentTermId': this.del_id}
     this.deletedIdsList.push(this.obj1);
     console.log(this.deletedIdsList);
    const param = {'basePaymentTermId': this.del_id};
    this.subscription = this.basePaymentService.deleteBasePayment(param,this.code).subscribe(deletedStatus => {
                           this.savedData = deletedStatus;
                           if (this.status === 'Draft') {
                            this.isDraftStatus = true;
                          }else if (this.status === 'Active') {
                            this.isActiveStatus = true;
                          }
                           this.router.navigate([listingPageBasePaymnt], { queryParams: { 'success': 8} });

       },
         error => {
            this.errorMessage = error;
            this.isDeletePopupModal = false;
            this.isCannotDeletePopup = true;
        },
         () => console.log('Finished')
    );
}

updatebasepayment1() {
  this.router.navigate(['master/base-payment/add-base-payment']);
   /** if (statusName === 'InActive') {
            this.userDetails.statusName  = 'InActive';

     } else if (statusName === 'Active') {
           this.userDetails.statusName  = 'Active';
        }

  this.userDetails.roleId = '';
  console.log('Requestbody' + JSON.stringify( this.userDetails))
     this.subscription = this.basePaymentService.updatebasepayment( this.userDetails).subscribe(savedwithStatus => {
     this.router.navigate(['/master/viewuserrole'], { queryParams: { 'success': 9 } });
      },
      error => alert(error),
      () => console.log('Finished')
    );*/
  }
conf_status_change(event) {
    this.isShowModal = true;
   setTimeout(() => {this.isShowModal = false; }, 3000);
  this.router.navigate(['master/base-payment/add-base-payment']);
}
editClick(baseTermId, baseevent) {
  this.subscription = this.basePaymentService.getSelectedFeatues('/uomservice/uom', baseTermId).subscribe(editBasePayment => {
      this.basePaymentService.selectedFeatureToedit(editBasePayment.body.baseNagotiationTerm);
      this.basePaymentService.editUserRoleFeatures(baseevent);
      this.basePaymentService.basePaymentdetails = editBasePayment.body;
      this.router.navigate([addBasePaymnt], { queryParams: { 'elem': 6 } });
     },
      error => alert(error),
      () => console.log('Finished')
      );
  }
 close() {
    this.iseditModal = false;
  }



public showModal(): void {
    this.errormodal = true;
  }

  public hideModal1(): void {
    this.statusChange1.hide();
  }
 public hideModal2(): void {
    this.statusChange2.hide();
  }
  public onHidden(): void {
    this.errormodal = false;
  }

}
