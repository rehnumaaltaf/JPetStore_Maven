import { Component, OnInit, OnDestroy,ViewChild, Output, ViewEncapsulation, Inject   } from '@angular/core';
import { PlatformLocation, Location} from '@angular/common';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Subscription } from 'rxjs/Subscription';
import { Router } from '@angular/router';
import { MasterSetupService } from '../../master-setup/service/master-setup.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { addBasePaymnt } from '../../../shared/interface/router-links';
import { SelectItem } from '../../../shared/interface/selectItem';
import { ResponseData } from '../../../shared/interface/responseData';
import { BasePaymentInterface } from './base-payment-interface';
import {  ModalDirective } from 'ngx-bootstrap';
import { viewBasePayment } from '../../../shared/interface/router-links';
import { BaseNagotiationTerm } from './base-payment-interface';
import { Link } from '../../../shared/interface/link';
import {BasePaymentService} from '../base-payment/service/base-payment.service';
import { CanComponentDeactivate } from '../../../shared/guards/can-deactivate-guard';
import { master } from '../../../shared/interface/router-links';
import { ExcelExportData } from '@progress/kendo-angular-excel-export';
import { GridComponent, GridDataResult, DataStateChangeEvent, PageChangeEvent} from '@progress/kendo-angular-grid';
import { process, SortDescriptor, orderBy, GroupDescriptor, State } from '@progress/kendo-data-query';
import { Observable } from 'rxjs/Rx';


@Component({
  selector: 'app-base-payment',
  templateUrl: './base-payment.component.html',
  styleUrls: ['./base-payment.component.css']
})
export class BasePaymentComponent implements OnInit, OnDestroy, CanComponentDeactivate {
   @ViewChild('deletesuccessModal') public deletesuccessModal: ModalDirective;
    @ViewChild('successModal') public successModal: ModalDirective;
     @ViewChild('updateModal') public updateModal: ModalDirective;
       @ViewChild('confirmPop') public confirmPop: ModalDirective;
            @ViewChild('updateModalOnly') public updateModalOnly: ModalDirective;
       @ViewChild('deletesuccessModals') public deletesuccessModals: ModalDirective;
  isActionPerformed = false;
  private link: Link;
  group: GroupDescriptor[] = [{ field: 'statusName' }];
  multiple: Boolean = false;
  gridView: GridDataResult;
  subscription: Subscription;
  allowUnsort: Boolean = true;
  sort: SortDescriptor[] = [];
  deleteStatus: string;
  isShowModal: boolean;
  private basePaymentdetails;
  data: BasePaymentInterface[];
  public dataView=[];
  basePaymentCode:string;
  isupdateModal: boolean;
  viewByIddelete: boolean = false;
  public iseditModal;
  public success;
  headerTermCode : string;
  headerTermName : string;
 headerTermDesc : string;
 baseTermCreditRiskListing: string;
 baseTermLCListing: string;
 headerTermCredit : string;
deleteSuccessModal : boolean;
headerTermLC : string;
  sizes: any;
  state: State = {
        skip: 0,
        take: 1000
    };
    public del_id;

   public statusName;
public basetermName;
public statusMsg;
isDeletePopupModal:boolean;
public isDraftStatus;
public isCannotDeletePopup;
public id;
public inactive;
public active;
public draft;
public status1;
public deletebyid;
 public code;
baseCodedelpop : string;
statusdelname : string;

public name;
public description;
public risk;
public obj1: BasePaymentInterface = new BasePaymentInterface();
public lcbased;
 public featureValList= [];
 public deletedIdsList= [];
  public featureList1= [];
  public desclist= [];
  public userDetails;
  public moduleNameList= [];
  isupdateModalOnly:boolean;
public savedData;
statusToPop:string;
public status_change;
public isActiveStatus;
termNameToShow: string;
public errorMessage;
msg:string;
  public formGroup: FormGroup;
  private editedRowIndex: number;
  public view: Observable<GridDataResult>;
 
 gridData: GridDataResult;
  constructor(private route: ActivatedRoute, http: Http, private router: Router ,
              public basePaymentService: BasePaymentService, public masterSetupService: MasterSetupService,
                            platformlocation: PlatformLocation, location: Location) {

 // this.getListingPageData();

  this.allData = this.allData.bind(this);
     platformlocation.onPopState(() => {
                //this.isNavbtwComponent = true;               
                //this.router.navigate(['addUom'], { queryParams: {}});
                //this.location.go('addUom');
                //this.route.queryParams = [];
        });                          
  }

  ngOnInit() {
     this.loadingBasePaymentList();
    window.scrollTo(0, 0); 
    this.basePaymentCode = this.basePaymentService.baseCodeForPayment;
    this.statusToPop=this.basePaymentService.statusName;
    this.route.queryParams.subscribe(params => {
        // Defaults to 0 if no query param provided.
        this.success = +params['success'];
        if (this.success === 1) {
          if(this.statusToPop == "Draft") {
          this.msg="Success : Base Payment" + " " + this.basePaymentCode + " " + "saved";
          } 
          if(this.statusToPop == "Save") {
            this.msg="Success : Base Payment" + " " + this.basePaymentCode + " " + "activated";
          }
          this.isShowModal = true;
          setTimeout(() => {this.successModal.hide()}, 3000);
        } if (this.success === 9) {
          if (this.statusToPop=="InActive") {
             this.msg="Success : Base Payment" + " " + this.basePaymentCode + " " + "deactivated";
          }
          if (this.statusToPop=="Active") {
             this.msg="Success : Base Payment" + " " + this.basePaymentCode + " " + "reactivated";
          }
          this.isupdateModal = true;
          setTimeout(() => {this.updateModal.hide()}, 3000);
        } if (this.success === 4) {
          this.msg = "Success : Base Payment" + " " + this.basePaymentCode + " " + "updated";
          this.isupdateModalOnly = true;
          setTimeout(() => {this.updateModalOnly.hide()}, 3000);
        }
        if (this.success === 8) {
          this.viewByIddelete = true;
           setTimeout(() => {this.deletesuccessModal.hide()}, 3000);
        }
          if (this.success === 2) {
             this.msg="Success : Base Payment" + " " + this.basePaymentCode + " " + "activated";
          this.isupdateModal = true;
           setTimeout(() => {this.updateModal.hide()}, 3000);
        }
      });
  }
 allData(): ExcelExportData {
   console.log('ddd');
    const result: ExcelExportData = {
     // data: process(this.basePaymentService.userRole12, this.state).data,
      group: this.group
    };

    return result;
  }
  pageChange(event: PageChangeEvent): void {
        this.state.skip = event.skip;
        //this.getListingPageData();
        this.loadingBasePaymentList();
    }

onRowSelect(event) {
 // alert(event.data);
}
sortChange(sort: SortDescriptor[]): void {
        this.sort = sort;
        //this.getListingPageData();
        this.loadingBasePaymentList();
    } 
public  dataStateChange(state: DataStateChangeEvent): void {
         this.state = state;
       // this.gridData = process(this.userRoleService.userRole12, this.state);
    }  
    
checkAllClicked($event) {
    console.log('checkAllClicked', $event);
     /*this.event.forEach(x => x.state = ev.target.checked)*/
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
 hide(){
   this.isDeletePopupModal = false;
   this.isupdateModal = false;
   this.deleteSuccessModal = false;
   this.viewByIddelete= false;
   this.isShowModal = false;
   this.isupdateModalOnly = false;
 }
 loadingBasePaymentList() {
  this.isDeletePopupModal = false;
     this.subscription = this.basePaymentService.getListingPageFromJson('/basepaymentservice/basepayment').subscribe(addBasePayment => {
      this.basePaymentService.basePaymentdetails = addBasePayment.body;
      this.data = addBasePayment.body;
      var data1 = addBasePayment.view.column.split(",");
      if (data1[1] != null && data1[1] =='baseTermCode') {
        this.headerTermCode = "Term Code";
      }
      if (data1[0] != null && data1[0] =='baseTermName') {
        this.headerTermName = "Term Name";
      }
      if (data1[2] != null && data1[2] =='baseTermDescription') {
        this.headerTermDesc = "Term Description";
      }
      if (data1[3] != null && data1[3] =='baseTermCreditRisk') {
        this.headerTermCredit = "Credit Risk";
      }
       if (data1[4] != null && data1[4] =='baseTermLCBased') {
        this.headerTermLC = "LC Based";
       }
       /*for(let i=0; i<this.basePaymentService.basePaymentdetails.length; i++) {
       if (this.basePaymentService.basePaymentdetails[i].baseTermCreditRisk == 'Y') {
         this.basePaymentService.basePaymentdetails[i].baseTermCreditRisk = 'Yes';
       } if (this.basePaymentService.basePaymentdetails[i].baseTermLCBased == 'Y') {
          this.basePaymentService.basePaymentdetails[i].baseTermLCBased = 'Yes';
       } if (this.basePaymentService.basePaymentdetails[i].baseTermLCBased == 'N') {
          this.basePaymentService.basePaymentdetails[i].baseTermLCBased = 'No';
       } if (this.basePaymentService.basePaymentdetails[i].baseTermCreditRisk == 'N') {
          this.basePaymentService.basePaymentdetails[i].baseTermCreditRisk = 'No';
       }
       }*/
     console.log('value after split' + this.headerTermCode)
       console.log('===this.unitMeasurementService.uomDetails==>' + JSON.stringify(this.basePaymentService.basePaymentdetails));
     // this.loadBaseDetails(this.data);
    //  this.basePaymentService.addChildObjects(addBasePayment.links);
     },
      error => alert(error),
      () => console.log('Finished')
      );
  }
   editBasePayment(event) {
   this.subscription = this.basePaymentService.getSelectedFeatues('/uomservice/uom', event.baseTermId).subscribe(editBasePayment => {
      this.basePaymentService.selectedFeatureToedit(editBasePayment.body.baseNagotiationTerm);
     // alert('ddd2s' + JSON.stringify(editBasePayment.body.baseNagotiationTerm));
      this.basePaymentService.editUserRoleFeatures(event);
      this.basePaymentService.basePaymentdetails = editBasePayment.body;
      this.router.navigate([addBasePaymnt], { queryParams: { 'elem': 6 } });
     },
      error => alert(error),
      () => console.log('Finished')
      );
  }
  changeShowStatus () {
   this.router.navigate([ addBasePaymnt]);
  }

 canDeactivate(): boolean {
    return this.isActionPerformed;
  }
  
   ngOnDestroy() {
    // prevent memory leak when component destroyed
    //this.subscription.unsubscribe();
  }
  loadBaseDetails(param): void {
        // For virtual scrolling
       /* this.gridView = {
            data: param.slice(this.state.skip, this.state.skip + this.state.take),
            total: this.data.length
        };*/
        /** this.gridView = {
            data: process(this.unitMeasurementService.uomDetails, this.state).data,
            total: this.unitMeasurementService.uomDetails.length
        };**/

        // alert("stringify in loaduom===>"+JSON.stringify(this.gridView));
    }
    viewById(event) {
    // alert("==eventparam==>"+JSON.stringify(event));
    console.log('viewpage' + JSON.stringify(event)
    );
    this.basePaymentService.viewbyIdParam = event;
    this.router.navigate([viewBasePayment]);
  }
   basePayment_delete(baseTermId, statusName, code) {
    console.log('ddd' + statusName);
   this.del_id = baseTermId;
      this.baseCodedelpop = code;
     this.statusdelname = statusName;
    this.confirmPop.show();
   if (statusName === 'Draft') {
      this.isDraftStatus = true;
    }else if (statusName === 'Active') {
      this.isActiveStatus = true;
    }
 }

 closedeletepopup() {
   //alert('d');
   //this.isDeletePopupModal = false;
   this.confirmPop.hide();
 }
 conf_delete(id) {
   this.confirmPop.hide();
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
    this.subscription = this.basePaymentService.deleteBasePayment(param,this.baseCodedelpop).subscribe(deletedStatus => {
                           this.savedData = deletedStatus;
                            this.deletesuccessModals.show();
                           if (this.statusName === 'Draft') {
                            this.isDraftStatus = true;
                          }else if (this.statusName === 'Active') {
                            this.isActiveStatus = true;
                          }
                            setTimeout(() => {this.deletesuccessModals.hide()}, 3000);
                            this.loadingBasePaymentList();

       },
         error => {
             this.errorMessage = error;
            // this.isDeletePopupModal = false;
            //this.confirmPop.hide();
            this.isCannotDeletePopup = true;
        },
         () => console.log('Finished')
    );
}
}
