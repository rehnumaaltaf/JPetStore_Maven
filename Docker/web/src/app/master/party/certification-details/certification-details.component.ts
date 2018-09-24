import { Component, OnInit, OnDestroy, ViewChild, AfterViewInit  } from '@angular/core';
import { ExcelExportData } from '@progress/kendo-angular-excel-export';
import { GridComponent, GridDataResult, DataStateChangeEvent, PageChangeEvent} from '@progress/kendo-angular-grid';
import { process, SortDescriptor, orderBy, GroupDescriptor, State } from '@progress/kendo-data-query';
import { Observable } from 'rxjs/Rx';
import {IntlModule} from '@progress/kendo-angular-intl';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Subscription } from 'rxjs/Subscription';
import { Router, ActivatedRoute } from '@angular/router';
import { CertifyMeasurementService } from './service/certification.service';
import { addcertify } from '../../../shared/interface/router-links';
import { viewcertify } from '../../../shared/interface/router-links';
import { certify, master } from '../../../shared/interface/router-links';
import { ModalDirective } from 'ngx-bootstrap/modal/modal.component';

@Component({
  selector: 'app-certification-details',
  templateUrl: './certification-details.component.html',
  styleUrls: ['./certification-details.component.css']
})
export class CertificationDetailsComponent implements OnInit , OnDestroy {
public data: any;
public success: any;
public status;
public isInActiveStatus: boolean;
@ViewChild('autoShownModal') public autoShownModal: ModalDirective;
@ViewChild('deletemodal') public deletemodal: ModalDirective;
@ViewChild('deletesuccessModals') public deletesuccessModals: ModalDirective;
public isModalShown: boolean = false;
public deleteshown: boolean = false;
public successdelete: boolean = false;
isNavbtwComponent: boolean;
public del_id;
subscription: Subscription;
state: State = {
        skip: 0,
        take: 1000
    };
group: GroupDescriptor[] = [];
showModals: boolean;
public columnOrder: any;
headerCertCode: string;
headerCertName: string;
headerCertDesc: string;
headerLogo: string;
prodCertLogo: any;
isActiveStatus: boolean;
isDraftStatus: boolean;
isUpdate: boolean;
isActivated: boolean;
statusUrl: string;
isInActivated: boolean;
deleteStatus: string;
public ccode;
public id;
public certnamepopup;
private certCode;
private certName;
public iseditModal;
public pkProdCertId;
public savedData;
cer_code: any;

constructor (private route: ActivatedRoute, http: Http, private router: Router ,
  public certifyMeasurementService: CertifyMeasurementService) { }

 /*ngOnInit() {

 }*/

  // ngAfterViewInit() {
    ngOnInit() {
      sessionStorage.setItem('id', '');
      this.route.queryParams.subscribe(params => {
        // Defaults to 0 if no query param provided.
        this.success = +params['success'];
        this.statusUrl = params['statusName'];
        // alert('==this.success==>' + this.success);
        if ( this.success === 1) {
          if ( !this.isNavbtwComponent ) {
             this.cer_code = sessionStorage.getItem('cer_code');
             this.certnamepopup = sessionStorage.getItem('certName');
            //  alert('==cer_name==>' + this.certnamepopup);
             this.router.navigate([certify]);
          } else {
             this.router.navigate([master]);
          }
          this.isModalShown = true;
           setTimeout(() => {this.autoShownModal.hide(); }, 3000);
        } else if ( this.success === 2) {
        // alert('status_check-->' + sessionStorage.getItem('status_check'));
         if (sessionStorage.getItem('status_check') === '1' || sessionStorage.getItem('status_check')==='2'){
          // debugger;
         // alert('--status-->'+sessionStorage.getItem('status')+'---status_check--->'+sessionStorage.getItem('status_check'));
          if (sessionStorage.getItem('status')==="Active" && sessionStorage.getItem('status_check')=='2'){
              // alert('in 123');
            this.isInActiveStatus = true;
             this.ccode = sessionStorage.getItem('cer_code');
             this.certnamepopup = sessionStorage.getItem('certName');
             this.router.navigate([certify]);
          } else if (sessionStorage.getItem('status')==="InActive" && sessionStorage.getItem('status_check')=='1'){
             // alert('in 456');
             this.isActiveStatus = true;
             this.ccode = sessionStorage.getItem('cer_code');
             this.certnamepopup = sessionStorage.getItem('certName');
             this.router.navigate([certify]);
          } else {
              // alert('in else' + this.statusUrl);
                if (this.statusUrl === 'InActive') {
                     this.isActiveStatus = true;
                } else if ( this.statusUrl === 'Active') {
                     this.isInActiveStatus = true;
                }else if ( this.statusUrl === 'Draft') {
                     this.isDraftStatus = true;
                }
          }
          // alert('in 789')
          this.successdelete = true;
          // alert('here')
           setTimeout(() => {this.deletesuccessModals.hide();}, 3000);
         } else if (sessionStorage.getItem('status_check')!=undefined || sessionStorage.getItem('status_check')==='') {
              this.isUpdate=true;
             this.ccode=sessionStorage.getItem('cer_code');
             this.certnamepopup = sessionStorage.getItem('certName');
             this.router.navigate([certify]);
             this.successdelete=true;
           setTimeout(() => {this.deletesuccessModals.hide();}, 3000);
         }
        }else if ( this.success === 0) {
          if ( !this.isNavbtwComponent ) {
              if(sessionStorage.getItem('status')==="Draft"){
                this.isDraftStatus=true;
             this.ccode=sessionStorage.getItem('cer_code');
             this.certnamepopup = sessionStorage.getItem('certName');
             this.router.navigate([certify]);
          }
          this.successdelete = true;
           setTimeout(() => {this.successdelete = false }, 3000);
        }
        }
      });
     this.loadcertifydetails();
  }

public onHidden():void {
  // successdelete
  this.successdelete = false;
  this.isModalShown = false;
    this.deleteshown=false;
    this.successdelete=false;
    this.isDraftStatus=false;
    this.isActiveStatus=false;
    this.isInActiveStatus=false;
    sessionStorage.setItem('cer_code','');
    sessionStorage.setItem('certName','');
    sessionStorage.setItem('status','');
    sessionStorage.setItem('status_check','');
}

  addcertify() {
    this.certifyMeasurementService.editview = false;
    this.router.navigate([addcertify]);
  }

  certificate_delete(event, certCode, certName, statusName) {
   this.del_id = event;
   this.ccode = certCode;
   this.status = statusName;
 
   if (this.status === 'Active') {
       this.isActiveStatus = true;
       this.deleteStatus = 'InActivate';
   } else if (this.status === 'Draft') {
        this.isDraftStatus = true;
        this.deleteStatus = 'Delete';
   } else if (this.status === 'InActive') {
        this.deleteStatus = 'Activate';
   }

   this.certCode = certCode;
   this.certName = certName;
   this.deleteshown = true;
   // this.successModals.show();
 }

 closedeletepopup() {
  this.deletemodal.hide();
 }

conf_delete(id) {
      this.deletemodal.hide();
      this.successdelete = true;
      const param = {'pkProdCertId': this.del_id};
      this.subscription = this.certifyMeasurementService.deleteCertificateById(param).subscribe(deletedStatus => {
                           this.savedData = deletedStatus;
                           setTimeout(() => {this.successdelete = false; }, 3000);
                           this.loadcertifydetails();
        },
         error => {
            // this.successModals.hide();

        },
         () => console.log('Finished')
    );
}
 editcertificate(event) {
   this.certifyMeasurementService.editview = true;
   // alert('event' + event.validFrom)
   sessionStorage.setItem('id', event.pkProdCertId);
   this.certifyMeasurementService.editByIdParam = event;
  // this.router.navigate(['master/party/addcertify']);
     this.router.navigate(['master/party/certify/editcertify/' + event.pkProdCertId]);
  }

   loadcertifydetails() {
    this.subscription = this.certifyMeasurementService.getcertifyJSON().subscribe(listcertifyDetail => {
      this.certifyMeasurementService.certifyCodeParam = listcertifyDetail.body;
      this.data = listcertifyDetail.body;
      this.certifyMeasurementService.addChildObjects(listcertifyDetail.links);
     },
      error => alert(error),
      () => console.log('Finished')
      );

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
    this.certifyMeasurementService.viewbyIdParam = event;
    sessionStorage.setItem('id', event.pkProdCertId);
    this.router.navigate(['master/party/certify/viewcertify/' + event.pkProdCertId]);
  }

   ngOnDestroy() {
    this.subscription.unsubscribe();
  }



}
