import { AccordionModule } from 'ngx-bootstrap';
import { TreeviewItem, TreeviewConfig } from 'ngx-treeview';
import { Component, EventEmitter, OnInit, OnDestroy, Output, ViewChild  } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';
import { AddMultiBaseProductComponent } from './add-multiple-processing';
import { AddMultiBaseProductGradeComponent } from './add-multiple-grade';
import { ProductMasterService } from '../product-master/../service/product-master.service';
import { ProductMasterInterface, ProductsubModel } from '../product-master-interface';
import { listingpageproduct } from '../../../../shared/interface/router-links';
import { CanComponentDeactivate } from '../../../../shared/guards/can-deactivate-guard';
import { FormGroup, FormControl, FormArray, FormBuilder, ReactiveFormsModule, FormsModule } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { DropdownTreeviewSelectComponent } from '../../../../shared/dropdown-treeview-select/dropdown-treeview-select.component';
import { ModalDirective } from 'ngx-bootstrap/modal';

@Component({
  selector: 'app-add-product-master',
  templateUrl: './add-product-master.component.html',
  styleUrls: ['./add-product-master.component.css']
})
export class AddProductMasterComponent implements OnInit, OnDestroy, CanComponentDeactivate {
  productMasterForm: FormGroup;
  geo: ProductMasterInterface = new ProductMasterInterface();
  public pageName;
  public pageTitle;
  productsubModel: ProductsubModel[];
  // productsubModel = [{ 'prosubId': 1, 'prosubCode': 'P101', 'prosubName': 'Fully Washed' }, { 'prosubId': 3, 'prosubCode': 'P103', 'prosubName': 'Washed' }, { 'prosubId': 4, 'prosubCode': 'PROD4', 'prosubName': 'Semi Washed' }, { 'prosubId': 5, 'prosubCode': 'PROD5', 'Honey Processed': 'Product 5' }];
  @ViewChild('validate') public validate: ModalDirective;
  @ViewChild('deletesuccessModal') public deletesuccessModal: ModalDirective;
  public notvalid: boolean = false;
  public productcode = '';
  public productname = '';
  public productfullname = '';
  public pcode: boolean;
  errorMsg: string;
  public pname: boolean;
  productCodeList: string[] = [];
  productNameList: string[] = [];
  userRoleDataList: ProductMasterInterface = new ProductMasterInterface();
  financialCalendarModelArbb: ProductMasterInterface[];
  financialCalendarModelsGrade: ProductMasterInterface[];
  isBaseProduct: string;
  public toShowParentProd: boolean = true;
  public pfname: boolean;
  speciallityapp: string;
  errormodal1: boolean;
  arbshortname: any;
  arbagency: any;
  baseProductDetailsObj: ProductMasterInterface = new ProductMasterInterface();
  public prosublist: any;
  public procname: string;
  isActionPerformed = false;
  public gradeMappingdel = [];
  public arbtitrationMappingdel = [];
  
  public listOfProductedit = [];
  public washed: boolean;
  ispagebackPopupModal: boolean;
  specialityApplicable: string;
  whenDataEdited: boolean;
  whenStatusactive: boolean;
  whenDataNotTobeEdited: boolean;
  whenDataTobeEdited: boolean;
  whenStatusInactive: boolean;
  subscription: Subscription;
  whentoAdd: string;
  public blanksSubType = [];
  public status: any = {
    isFirstOpen: true,
    isFirstDisabled: false
  };
  items: TreeviewItem[];
  selectedItem: TreeviewItem;
  //values: number[];
  config = TreeviewConfig.create({
    hasFilter: true,
    hasAllCheckBox: false,
    maxHeight: 400,
    hasCollapseExpand: true
  });

  constructor(private router: Router, public productMasterService: ProductMasterService, private _routeParams: ActivatedRoute, private fb: FormBuilder, private route: ActivatedRoute) { }

  ngOnInit() {
  
  window.scrollTo(0, 0);
    this.getGeoDropDown();
    this.getdestinationlist();
   // this.prosublist = this.productsubModel;
    this.route.queryParams.subscribe(params => {
      this.pageName = +params['elem'];
    });
    this.whentoAdd = 'true';
    this.pageTitle = 'Add Product';
    if (this.pageName == 6) {
      
      this.pageTitle = 'Edit Product';
      this.productMasterForm = this.fb.group({
        prodCode: '',
        prodName: '',
        prodFullName: '',
        isBaseProduct: '',
        specialityApplicable: '',
        statusName: '',
        prosubName: '',
        parentProductName: '',
        destinationSystemId: '',
        procname: '',
        processingName: '',
        processingCode: '',
        processingSubType: '',
        parentProductId: '',
        icoCode: '',
        destinationname: '',
        deletedArbitrationMappingIds:'',
        icoName: '',
        attribute1: '',
        attribute2: '',
        deletedGradeMappingIds:'',
        productArbitrationCollection: this.fb.array([]),
        gradeCodeMappingCollection: this.fb.array([]),
        productsubModel: this.fb.array([])
      });
      this.userRoleDataList = this.productMasterService.selectedProductBodyToedit;
      console.log(this.productMasterService.selectedProductBodyToedit)
      this.editListDetails();
    } else {
      this.washed = false;
      this.specialityApplicable = 'N';
      this.isBaseProduct = 'N';
      this.whenDataNotTobeEdited = true;
      this.buildUSERROLEForm();
      this.addPermissionMapping();
      this.addGradeMapping();
    }

  }
  radioChangeTrue() {
    this.toShowParentProd = true;
  }
  radioChangeFalse() {
    this.toShowParentProd = false;
  }
  getdestinationlist() {
    this.subscription = this.productMasterService.getdestinationFromJson().subscribe(destination => {
      this.productsubModel = destination.body.processSubTypeValues;
      this.arbshortname = destination.body.externalSystemRefValues;
      this.arbagency = destination.body.arbitrationAgencyValues;
    },
      error => { throw error },
      () => console.log('Finished')
    );
  }

  addPermissionMapping() {
    const control = <FormArray>this.productMasterForm.controls['productArbitrationCollection'];
    const addrCtrl = this.fb.group({
      'arbitrationShortName': [],
      'arbitrationFullName': [],
      'arbitrationAgencyId': [],
      'mappingId': [],
    });
    control.push(addrCtrl);
  }
  addGradeMapping() {
    const control = <FormArray>this.productMasterForm.controls['gradeCodeMappingCollection'];
    const addrCtrl = this.fb.group({
      'codeType': [],
      'code': [],
      'codedescription': [],
      'gradeMappingId': [],
      'codeTypeId': [],
    });
    control.push(addrCtrl);
  }

  buildUSERROLEForm(): void {
    this.productMasterForm = this.fb.group({
      prodCode: '',
      prodName: '',
      prodFullName: '',
      isBaseProduct: '',
      specialityApplicable: '',
      statusName: '',
      parentProductId: '',
      prosubName: '',
      parentProductName: '',
      procname: '',
      destinationname: '',
      processingName: '',
      processingCode: '',
      destinationSystemId:'',
      processingSubType: '',
      icoCode: '',
      attribute1: '',
      deletedArbitrationMappingIds:'',
      deletedGradeMappingIds:'',
      attribute2: '',
      icoName: '',
      productArbitrationCollection: this.fb.array([]),
      gradeCodeMappingCollection: this.fb.array([]),
      productsubModel: this.fb.array([])
    });

  }
  getUserRoleArray(productMasterForm) {
    return productMasterForm.get('productArbitrationCollection').controls;
  }

  getIntGradeCodeMapping(productMasterForm) {
    return productMasterForm.get('gradeCodeMappingCollection').controls;
  }

  removePermissionMapping(i: number) {
    if (i > 0) {
      const control = <FormArray>this.productMasterForm.controls['productArbitrationCollection'];
      if (this.pageName == 6) {
        this.arbtitrationMappingdel.push(this.financialCalendarModelArbb[i].mappingId);
      }
        control.removeAt(i);
    }
  }
  removeIntGradeMapping(i: number) {
    if (i > 0) {
      const control = <FormArray>this.productMasterForm.controls['gradeCodeMappingCollection'];
      if(this.pageName == 6) {
        this.gradeMappingdel.push(this.financialCalendarModelsGrade[i].gradeMappingId);
      }
      control.removeAt(i);
    }
  }
  saveproduct(actionstatus) {
    this.isActionPerformed = true;
    console.log(this.productMasterForm.value.processingSubType);
    if (this.isValidForm()) {
      this.baseProductDetailsObj.prodCode = this.productMasterForm.value.prodCode;
      this.baseProductDetailsObj.prodName = this.productMasterForm.value.prodName;
      if (this.productMasterForm.value.isBaseProduct == 'N' && this.selectedItem != undefined) {
      this.baseProductDetailsObj.parentProductId = this.selectedItem.value;
      }
      this.baseProductDetailsObj.processingCode = this.productMasterForm.value.processingCode;
      this.baseProductDetailsObj.processingName = this.productMasterForm.value.processingName;
      if (this.productMasterForm.value.processingSubType != "") {
        this.baseProductDetailsObj.processingSubType = this.productMasterForm.value.processingSubType;
      } else {
        this.baseProductDetailsObj.processingSubType = [];
      }
      this.baseProductDetailsObj.specialityApplicable = this.productMasterForm.value.specialityApplicable;
      this.baseProductDetailsObj.isBaseProduct = this.productMasterForm.value.isBaseProduct;
      this.baseProductDetailsObj.processingSubTypeId = this.productMasterForm.value.processingSubTypeId;
      this.baseProductDetailsObj.icoCode = this.productMasterForm.value.icoCode;
      this.baseProductDetailsObj.destinationSystemId = this.productMasterForm.value.destinationSystemId;
      this.baseProductDetailsObj.icoName = this.productMasterForm.value.icoName;
      this.baseProductDetailsObj.attribute1 = this.productMasterForm.value.attribute1;
      this.baseProductDetailsObj.attribute2 = this.productMasterForm.value.attribute2;
      this.baseProductDetailsObj.prodFullName = this.productMasterForm.value.prodFullName;
      this.baseProductDetailsObj.productArbitrationCollection = this.productMasterForm.value.productArbitrationCollection;
      this.baseProductDetailsObj.gradeCodeMappingCollection = this.productMasterForm.value.gradeCodeMappingCollection;
      this.baseProductDetailsObj.statusName = actionstatus;
      console.log(JSON.stringify(this.baseProductDetailsObj));
      this.subscription = this.productMasterService.saveProductDetails(this.baseProductDetailsObj).subscribe(addUomDetail => {
        //this.basePaymentService.uomDetails.push(addUomDetail);
        this.router.navigate([listingpageproduct], { queryParams: { 'success': 1 } });
      },
        error => {
          this.errorMsg = error;
                this.errorModal();
                setTimeout(() => {this.hideModal(); }, 3000);    
          //alert(error);
          //  this.ispagebackPopupModal = true;


        }, // Alerts need to be replaced by error
        () => console.log('Finished')
      );
    }

  }

  productCodeSuggestion($event) {
    this.productCodeList=[];
    const productCode = $event.target.value;
     this.subscription = this.productMasterService.productCodeSuggestion(productCode).subscribe(data => {
    // this.geoCodeList = data.body;
     for(let p=0; p< data.body.length; p++) {
     this.productCodeList.push(data.body[p].prodCode);
    // this.geoCodeList = data.body[g].geoCode;
     }
    // console.log('geocodelist from type   ' + this.geoCodeList + '  ' +JSON.stringify(data.body));
     }, error =>  { throw error; } );
  }

  productNameSuggestion($event) {
     this.productNameList=[];
     const productName = $event.target.value;
     this.subscription = this.productMasterService.productNameSuggestion(productName).subscribe(data => {
    // this.geoCodeList = data.body;
     for(let p=0; p< data.body.length ; p++) {
       this.productNameList.push(data.body[p].prodName);
     }
   //  console.log('geonamelist from type   ' + this.geoNameList + '  ' +JSON.stringify(data.body));
     }, error =>  { throw error; } );
  }


  isValidForm() {
    if (this.productMasterForm.value.prodCode === '' || this.productMasterForm.value.prodCode === undefined) {
      this.pcode = true;
    }
    else {
      this.pcode = false;
    }
    if (this.productMasterForm.value.prodName === '' || this.productMasterForm.value.prodName === undefined) {
      this.pname = true;
    }
    else {
      this.pname = false;
    }

    if (this.productMasterForm.value.prodFullName === '' || this.productMasterForm.value.prodFullName === undefined) {
      this.pfname = true;
    }
    else {
      this.pfname = false;
    }
    if (this.pcode === false && this.pname === false && this.pfname === false) {
      //alert('save');
      return true;
    }
    else {
      this.notvalid = true;
      //setTimeout(() => { this.validate.hide(); }, 3000);
      return false;
    }

  }
  reset() {
    this.ngOnInit();
  }
  editListDetails() {
    console.log(this.userRoleDataList)
    if (this.userRoleDataList.statusName == 'Draft') {
      this.whenDataNotTobeEdited = false;
      this.whenDataEdited = true;
      this.whenStatusInactive = false;
      this.whenStatusactive = false;
    } else if (this.userRoleDataList.statusName == 'Active') {
      this.whenStatusactive = true;
      this.whenStatusInactive = false;
      this.whenDataEdited = false;
      this.whenDataNotTobeEdited = false;
    } else if (this.userRoleDataList.statusName == 'InActive') {
      this.whenStatusInactive = true;
      this.whenDataEdited = false;
      this.whenStatusactive = false;
      this.whenDataNotTobeEdited = false;
    }
    this.specialityApplicable = this.userRoleDataList.specialityApplicable;
    this.isBaseProduct = this.userRoleDataList.isBaseProduct;
    // console.log('subtrype' + JSON.stringify(this.userRoleDataList.processingSubType))
    
    if (this.userRoleDataList.processingName == 'washed') {
      this.washed = true;
    } else {
      this.washed = false;
    }
    if (this.userRoleDataList.isBaseProduct == 'Y') {
      this.toShowParentProd = true;
    } else {
      this.toShowParentProd = false;
    }
    //this.selectedItem=this.userRoleDataList.parentProductName;
    console.log(this.productMasterService.editProductdet.length + '  check of length');
    this.financialCalendarModelArbb = this.productMasterService.editProductdet;
    // this.financialCalendarModelsGrade = 
    //alert(JSON.stringify(this.financialCalendarModel));
    // const sizerollemapping = Number(JSON.stringify(this.basePaymentService.selectbasePaymentEdit.length));
    if (this.productMasterService.editProductdet.length >= 1) {
      for (let i = 0; i < this.productMasterService.editProductdet.length; i++) {
        this.addPermissionMapping();

      }
    }
    if (this.productMasterService.editGrade != null) {
      console.log(this.productMasterService.editGrade.length + '  check of length');
      this.financialCalendarModelsGrade = this.productMasterService.editGrade;
      if (this.productMasterService.editGrade.length >= 1) {
        for (let i = 0; i < this.productMasterService.editGrade.length; i++) {
          this.addGradeMapping();

        }
      }
    }
  }
  applyDropDownChange($evt) {
    console.log($evt);
  }

  public onHidden() {
    this.notvalid = false;
  }
  public onHidden1() {
    this.ispagebackPopupModal = false;
  }

  public washedtype(e, name) {
    if(JSON.parse(e.which) >= 65 && JSON.parse(e.which)<=111){
      if (name.toLowerCase() === 'washed') {
        this.washed = true;
      }
      else {
        this.washed = false; ``
      }
    }
    
  }

  getGeoDropDown() {
    this.subscription = this.productMasterService.getProductFromJson().subscribe(addGeoDetail => {
      //  this.productMasterService = addGeoDetail.body;
      this.items = this.populatetree(addGeoDetail.body);
    },
      error => { throw error },
      () => console.log('Finished')
    );
  }

  populatetree(geoList: ProductMasterInterface[]): TreeviewItem[] {
    const treeDropDown: TreeviewItem[] = [];
    geoList.forEach(geo => {
      let treeList;
      treeList = new TreeviewItem({
        text: geo.prodName, value: geo.prodId, checked: false
      });
      if (geo.children != null && geo.children.length > 0) {
        this.recursion(geo.children, treeList);
      }
      treeDropDown.push(new TreeviewItem(treeList));
    });
    return treeDropDown;


  }
  recursion(geoList: ProductMasterInterface[], treeList: TreeviewItem): TreeviewItem {
    geoList.forEach(geo => {
      let newView;
      newView = new TreeviewItem({
        text: geo.prodName, value: geo.prodId, checked: false
      });

      if (geo.children != null && geo.children.length > 0) {
        if (treeList.children == null) {
          treeList.children = [this.recursion(geo.children, newView)];
        } else {
          treeList.children.push(this.recursion(geo.children, newView));
        }
      } else {
        if (treeList.children == null) {
          treeList.children = [newView];
        } else {
          treeList.children.push(newView);
        }
      }
    });
    return treeList;
  }

  update() {
    this.listOfProductedit = [];
    console.log(this.selectedItem);
    this.baseProductDetailsObj = this.productMasterService.selectedProductBodyToedit;
    this.isActionPerformed = true;
    if (this.isValidForm()) {
      this.baseProductDetailsObj.prodCode = this.productMasterForm.value.prodCode;
      this.baseProductDetailsObj.prodName = this.productMasterForm.value.prodName;
      if(this.selectedItem != undefined) {
      this.baseProductDetailsObj.parentProductId = this.selectedItem.value;
      }
      this.baseProductDetailsObj.processingCode = this.productMasterForm.value.processingCode;
      this.baseProductDetailsObj.processingName = this.productMasterForm.value.processingName;
     if (this.productMasterForm.value.processingSubType != "") {
        this.baseProductDetailsObj.processingSubType = this.productMasterForm.value.processingSubType;
      } else {
        this.baseProductDetailsObj.processingSubType = [];
      }
      this.baseProductDetailsObj.specialityApplicable = this.productMasterForm.value.specialityApplicable;
      this.baseProductDetailsObj.isBaseProduct = this.productMasterForm.value.isBaseProduct;
      this.baseProductDetailsObj.processingSubTypeId = this.productMasterForm.value.processingSubTypeId;
      this.baseProductDetailsObj.icoCode = this.productMasterForm.value.icoCode;
      this.baseProductDetailsObj.icoName = this.productMasterForm.value.icoName;
      this.baseProductDetailsObj.attribute1 = this.productMasterForm.value.attribute1;
      this.baseProductDetailsObj.attribute2 = this.productMasterForm.value.attribute2;
      this.baseProductDetailsObj.prodFullName = this.productMasterForm.value.prodFullName;
        this.baseProductDetailsObj.destinationSystemId = this.productMasterForm.value.destinationSystemId;
      this.baseProductDetailsObj.deletedArbitrationMappingIds = this.arbtitrationMappingdel;
      this.baseProductDetailsObj.deletedGradeMappingIds =  this.gradeMappingdel;
      this.baseProductDetailsObj.productArbitrationCollection = this.productMasterForm.value.productArbitrationCollection;
      this.baseProductDetailsObj.gradeCodeMappingCollection = this.productMasterForm.value.gradeCodeMappingCollection;
      this.listOfProductedit.push(this.baseProductDetailsObj);
      console.log(JSON.stringify(this.listOfProductedit))
      
      this.subscription = this.productMasterService.updateProductDetails(this.baseProductDetailsObj).subscribe(addUomDetail => {
        //this.basePaymentService.uomDetails.push(addUomDetail);
        this.router.navigate([listingpageproduct], { queryParams: { 'success': 9 } });
      },
        error => {
          this.errorMsg = error;
                this.errorModal();
                setTimeout(() => {this.hideModal(); }, 3000);    
          //alert(error);
          // this.ispagebackPopupModal = true;

        }, // Alerts need to be replaced by error
        () => console.log('Finished')
      );
    }
  }
 
  updatewithStatus(statusName) {
    this.listOfProductedit = [];
    console.log(this.productMasterService.selectedProductBodyToedit);
    this.baseProductDetailsObj = this.productMasterService.selectedProductBodyToedit;
    this.isActionPerformed = true;
    if (this.isValidForm()) {
      if (statusName == 'Active') {
        this.baseProductDetailsObj.statusName = 'Active';
      } else if (statusName == 'InActive') {
        this.baseProductDetailsObj.statusName = 'InActive';
      }
      this.baseProductDetailsObj.prodCode = this.productMasterForm.value.prodCode;
      this.baseProductDetailsObj.prodName = this.productMasterForm.value.prodName;
     if(this.selectedItem != undefined) {
      this.baseProductDetailsObj.parentProductId = this.selectedItem.value;
      }
      this.baseProductDetailsObj.processingCode = this.productMasterForm.value.processingCode;
      this.baseProductDetailsObj.processingName = this.productMasterForm.value.processingName;
    if (this.productMasterForm.value.processingSubType != "") {
        this.baseProductDetailsObj.processingSubType = this.productMasterForm.value.processingSubType;
      } else {
        this.baseProductDetailsObj.processingSubType = [];
      }
      this.baseProductDetailsObj.specialityApplicable = this.productMasterForm.value.specialityApplicable;
      this.baseProductDetailsObj.isBaseProduct = this.productMasterForm.value.isBaseProduct;
      this.baseProductDetailsObj.processingSubTypeId = this.productMasterForm.value.processingSubTypeId;
      this.baseProductDetailsObj.icoCode = this.productMasterForm.value.icoCode;
      this.baseProductDetailsObj.icoName = this.productMasterForm.value.icoName;
      this.baseProductDetailsObj.attribute1 = this.productMasterForm.value.attribute1;
      this.baseProductDetailsObj.attribute2 = this.productMasterForm.value.attribute2;
      this.baseProductDetailsObj.prodFullName = this.productMasterForm.value.prodFullName;
       this.baseProductDetailsObj.deletedArbitrationMappingIds = this.arbtitrationMappingdel;
         this.baseProductDetailsObj.destinationSystemId = this.productMasterForm.value.destinationSystemId;
      this.baseProductDetailsObj.deletedGradeMappingIds =  this.gradeMappingdel;
      this.baseProductDetailsObj.productArbitrationCollection = this.productMasterForm.value.productArbitrationCollection;
      this.baseProductDetailsObj.gradeCodeMappingCollection = this.productMasterForm.value.gradeCodeMappingCollection;
      this.listOfProductedit.push(this.baseProductDetailsObj);
      this.subscription = this.productMasterService.updateProductDetails(this.baseProductDetailsObj).subscribe(addUomDetail => {
        //this.basePaymentService.uomDetails.push(addUomDetail);
        this.router.navigate([listingpageproduct], { queryParams: { 'success': 9 } });
      },
        error => {
          //alert(error);
          // this.ispagebackPopupModal = true;
          this.errorMsg = error;
                this.errorModal();
                setTimeout(() => {this.hideModal(); }, 3000);    

        }, // Alerts need to be replaced by error
        () => console.log('Finished')
      );
    }
  }
   public errorModal():void {
    this.errormodal1 = true;
  }
  public hideModal():void {
    this.deletesuccessModal.hide();
    this.errormodal1 = false;
  }

  ngOnDestroy() {
    // Called once, before the instance is destroyed.
    //  this.subscription.unsubscribe();
  }
  cancel() {
    this.router.navigate([listingpageproduct]);
  }
  close() {
    this.ispagebackPopupModal = false;
  }
  canDeactivate(): boolean {
    return this.isActionPerformed;
  }

}
