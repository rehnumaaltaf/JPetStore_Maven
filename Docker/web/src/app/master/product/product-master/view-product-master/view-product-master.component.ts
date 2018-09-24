import { Component, EventEmitter, OnInit, OnDestroy, Output, ViewChild, ViewEncapsulation } from '@angular/core';
import { TreeviewItem, TreeviewConfig } from 'ngx-treeview';
import { Subscription } from 'rxjs/Subscription';
import { ProductMasterService } from '../product-master/../service/product-master.service';
import { FormGroup, FormControl, FormArray, FormBuilder, ReactiveFormsModule, FormsModule } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { DropdownTreeviewSelectComponent } from '../../../../shared/dropdown-treeview-select/dropdown-treeview-select.component';
import { ModalDirective } from 'ngx-bootstrap/modal';
import { listingpageproduct } from '../../../../shared/interface/router-links';
import { Addpageproduct } from '../../../../shared/interface/router-links';
import { ProductMasterInterface,ProductsubModel } from '../product-master-interface';

@Component({
  selector: 'app-view-product-master',
  templateUrl: './view-product-master.component.html',
  styleUrls: ['./view-product-master.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class ViewProductMasterComponent implements OnInit {
    @ViewChild('successModal') public successModal: ModalDirective;
  @ViewChild('successModal1') public successModal1: ModalDirective;
@ViewChild('deletesuccessModalview') public deletesuccessModalview: ModalDirective;
 @ViewChild('deletesuccessModalList') public deletesuccessModalList: ModalDirective;
@ViewChild('updateModal') public updateModal: ModalDirective;
public inactive;
public active;
public draft;
  public status: any = {
    isFirstOpen: true
    // isFirstDisabled: false
  };
  
  public listOfbasePaymentedit = [];
    public isActiveStatus;
  public savedData;
  public statusName;
  public errorMessage;
   private selectedProductBodyToedit;
  prodName:string;
    deleteSuccessModal : boolean;
      public id;
  public statusMsg;
  isDeletePopupModal:boolean;
  status_change:boolean;
public isDraftStatus;
public isCannotDeletePopup;
  baseCodedelpop : string;
statusdelname : string;
    public del_id;
    productViewById : ProductMasterInterface = new ProductMasterInterface();
  productcode: Number;
  subscription: Subscription;
  productdetails : ProductMasterInterface = new ProductMasterInterface();
  constructor(private router: Router, public productMasterService: ProductMasterService, private _routeParams: ActivatedRoute, private fb: FormBuilder, private route: ActivatedRoute) { }

  ngOnInit() {
    this.productcode = this.productMasterService.viewbyIdParam.prodId;
    this.loaddata();
  }
  

  loaddata() {
    this.subscription = this.productMasterService.getselectedProduct(this.productcode ).subscribe(addUomDetail => {
   this.productdetails=addUomDetail.body;
         if (this.productdetails.statusName === "Draft") {
        this.draft = true;
        this.active = false;
        this.inactive = false;
      }else if (this.productdetails.statusName === "Active") {
        this.active = true;
        this.draft = false;
        this.inactive = false;
      }else if (this.productdetails.statusName === "InActive") {
        this.inactive = true;
        this.active = false;
        this.draft = false;
  }
    },
      error => {
        //alert(error);
       

      }, // Alerts need to be replaced by error
      () => console.log('Finished')
    );
  }
editClick(baseTermId) {
  debugger;
  console.log(baseTermId);
this.subscription = this.productMasterService.getselectedProduct(baseTermId).subscribe(editBasePayment => {
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
 close() {
   // this.iseditModal = false; this.router.navigate([listingpageproduct], { queryParams: { 'success': 1 } });
  }
  backtoPrev1 (){
     this.router.navigate([listingpageproduct]);
  }
  user_delete(event) {
   this.del_id = event.prodId;
   this.prodName = event.prodName;
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


updatewithStatus(statusName) {
  //alert(statusName);
  this.listOfbasePaymentedit = [];
   console.log(JSON.stringify(this.productdetails));
  this.productViewById =  this.productdetails;
   if (statusName == 'Active') {
     this.productViewById.statusName = 'Active';
   } else if (statusName == 'InActive') {
      this.productViewById.statusName = 'InActive';
   }
   this.productViewById.deletedArbitrationMappingIds = [];
   this.productViewById.deletedGradeMappingIds = [];
      this.listOfbasePaymentedit.push(this.productViewById);
      this.subscription = this.productMasterService.updateProductDetails(this.productViewById).subscribe(savedwithStatus => {
       this.router.navigate([listingpageproduct], { queryParams: { 'success': 9 } });
      },
      error => alert(error),
      () => console.log('Finished')
    );
 }

conf_delete(id) {
      console.log(this.del_id);
      this.id = id;
     // this.termNameToShow = this.basePaymentService.termNameToShow;
    const param = {'prodId': this.del_id};
    this.subscription = this.productMasterService.deleteBasePayment(param,this.prodName).subscribe(deletedStatus => {
                           this.savedData = deletedStatus;
                            this.deleteSuccessModal = true;
                           if (this.statusName === 'Draft') {
                            this.isDraftStatus = true;
                          }else if (this.statusName === 'Active') {
                            this.isActiveStatus = true;
                          }
                            setTimeout(() => {this.deletesuccessModalview.hide();}, 3000);
                          this.router.navigate([listingpageproduct], { queryParams: { 'success': 8 } });
       },
         error => {
             this.errorMessage = error;
            this.isDeletePopupModal = false;
            this.isCannotDeletePopup = true;
        },
         () => console.log('Finished')
    );
}
  }

