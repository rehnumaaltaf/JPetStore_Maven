import { Component, EventEmitter, OnInit, OnDestroy, Output, ViewChild  } from '@angular/core';
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
import { ProductMasterService } from './service/product-master.service';
import {ProductMasterInterface} from './product-master-interface';
import { Addpageproduct , viewpageproduct} from '../../../shared/interface/router-links';
import { Link } from '../../../shared/interface/link';
import { CanComponentDeactivate } from '../../../shared/guards/can-deactivate-guard';
import { master } from '../../../shared/interface/router-links';
import { ExcelExportData } from '@progress/kendo-angular-excel-export';
import { GridComponent, GridDataResult, DataStateChangeEvent, PageChangeEvent} from '@progress/kendo-angular-grid';
import { process, SortDescriptor, orderBy, GroupDescriptor, State } from '@progress/kendo-data-query';
import { Observable } from 'rxjs/Rx';
import { ModalDirective } from 'ngx-bootstrap/modal';
@Component({
  selector: 'app-list-product-master',
  templateUrl: './product-master.component.html',
  styleUrls: ['./product-master.component.css']
})
export class ProductMasterComponent implements OnInit, OnDestroy, CanComponentDeactivate {
   isActionPerformed = false;
  private link: Link;
  group: GroupDescriptor[] = [{ field: 'statusName' }];
  multiple: Boolean = false;
  gridView: GridDataResult;
  
  @ViewChild('successModal') public successModal: ModalDirective;
  @ViewChild('successModal1') public successModal1: ModalDirective;
@ViewChild('deletesuccessModalview') public deletesuccessModalview: ModalDirective;
 @ViewChild('deletesuccessModalList') public deletesuccessModalList: ModalDirective;
@ViewChild('updateModal') public updateModal: ModalDirective;

  subscription: Subscription;
  productName:string;
  private baseProductdetails;
  allowUnsort: Boolean = true;
  sort: SortDescriptor[] = [];
  headerTermCode : string;
  headerTermName : string;
  deleteSuccessModal : boolean;
  public savedData;
  public statusName;
  public errorMessage;
    public success;
    public id;
    prodName :string;
    public obj1: ProductMasterInterface = new ProductMasterInterface();
  isupdateModal: boolean;
  viewByIddelete: boolean = false;
  
    productViewById : ProductMasterInterface = new ProductMasterInterface();
  isShowModal: boolean;
  headerTermfull : string;
    private selectedProductBodyToedit;
  headerparentProductName : string;
  headerprocessingCode : string;
  public view: Observable<GridDataResult>;
  public dataView=[];
  public draft: boolean = false;
    public active: boolean = false;
    public inactive: boolean = false;
  public isActiveStatus;
isDeletePopupModal:boolean;
public isDraftStatus;
public isCannotDeletePopup;
baseCodedelpop : string;
statusdelname : string;
    public del_id;
 sizes: any;
  state: State = {
        skip: 0,
        take: 1000
    };
     gridData: GridDataResult;
  constructor(private route: ActivatedRoute,public productMasterService: ProductMasterService, http: Http, private router: Router ,
               public masterSetupService: MasterSetupService,
                            platformlocation: PlatformLocation, location: Location) { 

 this.loadingBasePaymentList();
  this.allData = this.allData.bind(this);
     platformlocation.onPopState(() => {
                //this.isNavbtwComponent = true;               
                //this.router.navigate(['addUom'], { queryParams: {}});
                //this.location.go('addUom');
                //this.route.queryParams = [];
        });  

      }

  ngOnInit() {
     this.productName = this.productMasterService.prodName; 
    this.route.queryParams.subscribe(params => {
        // Defaults to 0 if no query param provided.
        this.success = +params['success'];
        if (this.success === 1) {
          this.isShowModal = true;
          setTimeout(() => {this.successModal1.hide();}, 3000);
        } if (this.success === 9) {
          this.isupdateModal = true;
          setTimeout(() => {this.updateModal.hide();}, 3000);
        } if (this.success === 8) {
          this.viewByIddelete = true;
           setTimeout(() => {this.deletesuccessModalview.hide(); }, 3000);
        }
      });
       
  }
loadingBasePaymentList() {
  // this.isDeletePopupModal = false;
     this.subscription = this.productMasterService.getListingPageFromJson('/basepaymentservice/basepayment').subscribe(addBasePayment => {
      this.productMasterService.baseProductdetails = addBasePayment.body;
       console.log('===this.unitMeasurementService.uomDetails==>' + JSON.stringify(this.productMasterService.baseProductdetails));
      var data1 = addBasePayment.view.column.split(",");
      if (data1[0] != null && data1[0] =='prodCode') {
        this.headerTermCode = "Product Code";
      }
      if (data1[1] != null && data1[1] =='prodName') {
        this.headerTermName = "Product Name ";
      }
      if (data1[2] != null && data1[2] =='prodFullName') {
        this.headerTermfull = "Product FullName";
      }
      /*if (data1[3] != null && data1[3] =='baseTermCreditRisk') {
        this.headerparentProductName = "Credit Risk";
      }
       if (data1[4] != null && data1[4] =='baseTermLCBased') {
        this.headerprocessingCode = "LC Based";
       }*/
     },
      error => alert(error),
      () => console.log('Finished')
      );
  }
  viewById(event) {
    // alert("==eventparam==>"+JSON.stringify(event));
    this.productMasterService.viewbyIdParam = event;
    this.router.navigate([viewpageproduct]);
  }

changeShowStatus () {
  // this.router.navigate([Addpageproduct], { queryParams: { 'elem': 5 } });
  this.router.navigate([Addpageproduct], { queryParams: { 'elem': 5 } });
  }

 canDeactivate(): boolean {
    return this.isActionPerformed;
  }
   editBasePayment(event) {
   this.subscription = this.productMasterService.getselectedProduct(event.prodId).subscribe(editBasePayment => {
      this.productMasterService.productArbitrationCollection(editBasePayment.body.productArbitrationCollection);
      this.productMasterService.gradeMapping(editBasePayment.body.gradeCodeMappingCollection);
     // alert('ddd2s' + JSON.stringify(editBasePayment.body.baseNagotiationTerm));
      this.productMasterService.editProduct(event);
      this.productMasterService.selectedProductBody = editBasePayment.body;
       this.productMasterService.selectedProductBodyToedit = editBasePayment.body;
      this.router.navigate([Addpageproduct], { queryParams: { 'elem': 6 } });
     },
      error => alert(error),
      () => console.log('Finished')
      );
  }
  updatewithStatus(dataItem,status) {
  //alert(statusName);
  this.productViewById =  dataItem;
   if (status == 'Active') {
     this.productViewById.statusName = 'Active';
   } else if (status == 'InActive') {
      this.productViewById.statusName = 'InActive';
   }
   this.productViewById.deletedArbitrationMappingIds = [];
   this.productViewById.deletedGradeMappingIds = [];
      this.subscription = this.productMasterService.updateProductDetails(this.productViewById).subscribe(savedwithStatus => {
        this.isupdateModal = true;
          setTimeout(() => {this.updateModal.hide();}, 3000);
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
 onHiddenSuccesss (){
   this.successModal1.hide();
 }
 allData(): ExcelExportData {
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
onHiddenReset() {
    this.isDeletePopupModal = false;
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
onHideList(){
  this.deleteSuccessModal = false;
  this.isDeletePopupModal=false;
  this.isShowModal = false;
  this.isupdateModal = false;
  //this.deletesuccessModalview.hide();
}
onHideView(){
  this.deletesuccessModalList.hide();
}
onHideupdateModal(){
  this.updateModal.hide();
}
product_delete(event) {
    console.log('ddd' + event.statusName);
   this.del_id = event.prodId;
      this.baseCodedelpop = event.prodName;
     this.statusdelname = event.statusName;
       this.prodName = event.prodName;
   this.isDeletePopupModal = true;
   if (event.statusName === 'Draft') {
      this.isDraftStatus = true;
    }else if (event.statusName === 'Active') {
      this.isActiveStatus = true;
    }
 }
 closedeletepopup() {
   this.successModal.hide();
 }
  conf_delete(id) {
      this.successModal.hide();
      console.log(this.del_id);
      this.id = id;
     // this.termNameToShow = this.basePaymentService.termNameToShow;
    const param = {'prodId': this.del_id};
    this.subscription = this.productMasterService.deleteBasePayment(param,this.prodName).subscribe(deletedStatus => {
                           this.savedData = deletedStatus;
                            this.deleteSuccessModal = true;
                            debugger;
                           if (this.statusName === 'Draft') {
                            this.isDraftStatus = true;
                          }else if (this.statusName === 'Active') {
                            this.isActiveStatus = true;
                          }
                            setTimeout(() => {this.deletesuccessModalList.hide();}, 3000);
                            this.loadingBasePaymentList();

       },
         error => {
             this.errorMessage = error;
            this.isDeletePopupModal = false;
            this.isCannotDeletePopup = true;
        },
         () => console.log('Finished')
    );
}
ngOnDestroy() {
    // Called once, before the instance is destroyed.
   // this.subscription.unsubscribe();
  }
}
