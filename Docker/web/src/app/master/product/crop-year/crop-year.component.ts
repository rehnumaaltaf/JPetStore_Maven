import { Component, ViewChild, OnInit, OnChanges, Inject, ElementRef } from '@angular/core';
import { Observable } from 'rxjs/Rx';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { cropyear } from '../../../shared/interface/router-links';
// import { Cropyear } from './crop';
import { SelectItem } from '../../../shared/interface/selectItem';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { MasterSetupService } from '../../master-setup/service/master-setup.service';
import { CropYearService } from './service/crop-year.service';
import { ExcelExportData } from '@progress/kendo-angular-excel-export';
import { GridComponent, GridDataResult, DataStateChangeEvent, PageChangeEvent } from '@progress/kendo-angular-grid';
import { process, SortDescriptor, orderBy, GroupDescriptor, State } from '@progress/kendo-data-query';
import { CropYear, CropYearProductDto, ProdectModel } from './crop';
import { CanComponentDeactivate } from '../../../shared/guards/can-deactivate-guard';
import { ModalDirective } from 'ngx-bootstrap/modal';


@Component({
  selector: 'app-crop-year',
  templateUrl: './crop-year.component.html',
  styleUrls: ['./crop-year.component.css']
})
export class CropYearComponent implements OnInit,CanComponentDeactivate {
  @ViewChild('cropadded') public cropadded: ModalDirective;
  @ViewChild('cropedit') public cropedit: ModalDirective;
  @ViewChild('cropedits') public cropedits: ModalDirective;
  @ViewChild('cropdelete') public cropdelete: ModalDirective;
  @ViewChild('confirmdel') public confirmdel: ModalDirective;
  @ViewChild('confirmdels') public confirmdels: ModalDirective;
  @ViewChild('errormsg') public errormsg: ModalDirective;
  @ViewChild('validation') public validation: ModalDirective;
  @ViewChild('deletesuccessModal') public deletesuccessModal: ModalDirective;
  @ViewChild('successModal') public successModal: ModalDirective;
  @ViewChild('successModals') public successModals: ModalDirective;
  @ViewChild('Validation') public Validation: ModalDirective;
  @ViewChild('cycheckbox') cycheckbox; 
  @ViewChild('grid') grid; 
   
  sender:any;
  rowindex:number;
  flag=1;
  crop_product=[];
  getproduct:string;
  cropyearcode=[];
   isActionPerformed = false;
  public view: Observable<GridDataResult>;
  public errorMessage: any;
  public mode: boolean = false;
  public allowUnsort: boolean = true;
  public allItemsChecked: boolean = false;

  public formGroup: FormGroup;
  // private editService: EditService;
  private editedRowIndex: number;
  public deleteStatus: String;
  gridView: GridDataResult;
  subscription: Subscription;
  private isNew: boolean = false;
  private editedCropYearData: CropYear;
  Products: SelectItem[];
  PrdtDTOarr: Array<String> = [];
  state: State = {
    skip: 0,
    take: 1000
  };
  gridData: GridDataResult;
  group: GroupDescriptor[] = [];
  sort: SortDescriptor[] = [];
  isDeletePopupModal: boolean;
  deleteSuccessModal: boolean;
  isDeletePopupModals: boolean;
  isCannotDeletePopup: boolean;
  public submit: boolean = false;
  public edit: boolean = false;
  public create: boolean = false;
  public draft: boolean = false;
  public active: boolean = false;
  public inactive: boolean = false;
  public del_id;
  public savedData;
  public status;
  isActiveStatus: boolean;
  isDraftStatus: boolean;
  private cropYearCode;
  public cropcode;
  public statusDropDown;
  private cropYearName;
  public value: any = [];
  productList: ProdectModel[] = [];
  req_cropYearcode: boolean;
  req_cropYearname: boolean;
  req_prodName: boolean;
  counter: number;
  errormodal: boolean;
  errormodal1: boolean;
  statusmsg: string;
  public isShowModal: boolean;
  croperror=[];
  errormessage:string;

  /* [{'prodId': 1, 'prodCode': 'P101', 'prodName': 'Product 1'},
  {'prodId': 2, 'prodCode': 'P102', 'prodName': 'Product 2'},
  {'prodId': 3, 'prodCode': 'P103', 'prodName': 'Product 3'},
  {'prodId': 4, 'prodCode': 'PROD4', 'prodName': null}] */

  constructor(private router: Router, public cropYearService: CropYearService, private masterSetupService: MasterSetupService) {
    // this.editService = editServiceFactory();
    this.allData = this.allData.bind(this);
    // this.loadingCropYearList();
  }

  ngOnInit() {
    window.scrollTo(0, 0);
    this.loadingCropYearList();
    this.getProductList();
    this.draft = true;
    this.active = true;
    this.inactive = true;
  }

   canDeactivate(): boolean {
      return this.isActionPerformed;
  }

  loadingCropYearList() {
    this.subscription = this.cropYearService.getCropYearJSON().subscribe(addUomDetail => {
      this.cropYearService.cropyearDetails = addUomDetail.body;
      // for(var i=0;i<this.cropYearService.cropyearDetails.length;i++){
      //   var pro=[];
      //   pro=this.cropYearService.cropyearDetails[i].cropYearProductDto;
      //   for(var j=0;j<pro.length;i++){
      //     if(pro.length>1){
      //       this.crop_product.push(pro[j].prodName);
      //     }
      //     else{
      //       this.crop_product.push(pro[j].prodName);
      //     }

      //   }
        
      //   //  this.crop_product.push(product.prodName);
        
      // }
      //this.getproduct=JSON.stringify(this.crop_product);
      //debugger;
      //this.crop_product=JSON.stringify(this.cropYearService.cropyearDetails.cropYearProductDto. );
      this.loadcropyearDetails();
    },
      error => alert(error),
      () => console.log('Finished')
    );
  }

  private closeEditor(grid, rowIndex = this.editedRowIndex) {
        grid.closeRow(rowIndex);
        this.editedRowIndex = undefined;
        this.formGroup = undefined;
    }

  public addHandler({sender}) {
    //this.sender=sender;
    //this.rowindex=rowIndex;
    if(this.sender){
    this.closeEditor(this.sender);
    }
    this.submit = true;
    this.active = true;
    this.draft = true;
    this.inactive = true;
    this.edit = true;
    this.create = false;
    this.closeEditor(sender);
    sender.addRow(new CropYear());
    // debugger;
    // this.grid.wrapper.nativeElement.children[3].lastElementChild.firstElementChild.firstElementChild.rows[0].children[4].children.product.children[0].children[1].children[0].style.setProperty("border-color", "red", "important");
  }

  public editHandler({sender, rowIndex, dataItem}) {
    this.submit = false;
    this.edit = false;
    this.create = true;
    if (dataItem.statusName === 'Draft') {
      this.draft = false;
      this.active = true;
      this.inactive = true;
    }
    else if (dataItem.statusName === 'Active') {
      this.active = false;
      this.draft = true;
      this.inactive = true;
    }
    else if (dataItem.statusName === 'InActive') {
      this.inactive = false;
      this.draft = true;
      this.active = true;
    }
    this.closeEditor(sender);
    this.sender=sender;
    this.rowindex=rowIndex;
      //sender.editRow(this.rowindex);
    this.editedRowIndex = rowIndex;
    this.editedCropYearData = Object.assign({}, dataItem);
    sender.editRow(rowIndex);
  }

  public saveHandler({sender, rowIndex, dataItem, isNew}) {
    this.counter = 0;
    /*if(dataItem.cropYearCode === '' || dataItem.cropYearCode === null){
     this.req_cropYearcode=true;
     this.counter++;
   }*/
    this.sender=sender;
    this.rowindex=rowIndex;

    if (dataItem.cropYearCode == null) {
      this.req_cropYearcode = true;
      this.counter++;
    } else if (dataItem.cropYearCode != null) {
      if (dataItem.cropYearCode.trim() == '') {
        this.req_cropYearcode = true;
        this.counter++;
      } else if (dataItem.cropYearCode.trim().length > 20) {
        this.req_cropYearcode = true;
        this.counter++;
      }
      else {
        this.req_cropYearcode = false;
      }
    }

    if (dataItem.cropYearName == null) {
      this.req_cropYearname = true;
      this.counter++;
    } else if (dataItem.cropYearName != null) {
      if (dataItem.cropYearName.trim() == '') {
        this.req_cropYearname = true;
        this.counter++;
      } else if (dataItem.cropYearName.trim().length > 200) {
        this.req_cropYearname = true;
        this.counter++;
      }
      else {
        this.req_cropYearname = false;
      }
    }

    if (dataItem.cropYearProductDto == null) {
    //sender.editRow(this.editedRowIndex);
    
      this.req_prodName = true;
      this.counter++;
    }
    else {
      this.req_prodName = false;

    }

    if (this.counter !== 0) {
      // debugger;
      // this.grid.wrapper.nativeElement.children[3].lastElementChild.firstElementChild.firstElementChild.rows[0].childNodes[15].style.borderBottomColor="red";
      this.showModal();
      setTimeout(() => { this.hideModal(); }, 1000);
    } else if (dataItem.cropYearCode != null && dataItem.cropYearName != null && dataItem.cropYearProductDto != null) {
      //sender.closeRow(rowIndex);
      this.editedRowIndex = undefined;
      this.editedCropYearData = undefined;
      return false;
    }

  }

  savecropyear(dataItem) {
    this.counter = 0;
    if (dataItem.cropYearCode == null) {
      this.req_cropYearcode = true;
      this.counter++;
    } else if (dataItem.cropYearCode != null) {
      if (dataItem.cropYearCode.trim() == '') {
        this.req_cropYearcode = true;
        this.counter++;
      } else if (dataItem.cropYearCode.trim().length > 20) {
        this.req_cropYearcode = true;
        this.counter++;
      }
      else {
        this.req_cropYearcode = false;
      }
    }

    if (dataItem.cropYearName == null) {
      this.req_cropYearname = true;
      this.counter++;
    } else if (dataItem.cropYearName != null) {
      if (dataItem.cropYearName.trim() == '') {
        this.req_cropYearname = true;
        this.counter++;
      } else if (dataItem.cropYearName.trim().length > 200) {
        this.req_cropYearname = true;
        this.counter++;
      }
      else {
        this.req_cropYearname = false;
      }
    }

    if (dataItem.cropYearProductDto == null) {
      this.req_prodName = true;
      this.counter++;
    }
    else {
      this.req_prodName = false;

    }

    this.subscription = this.cropYearService.savecropdetails(dataItem).subscribe(addcropetail => {
      this.cropYearService.cropyearDetails.push(<CropYear>addcropetail);
      this.cropadded.show();
      this.cropcode = dataItem.cropYearCode;
      setTimeout(() => { this.cropadded.hide(); }, 3000);
      this.loadingCropYearList();
    },
      error => {
        this.validation.show();
      },
      () => console.log('Finished')
    );

    if (this.counter !== 0) {
      this.showModal();

      setTimeout(() => { this.hideModal(); }, 1000);
    } else {
      return true;
    }
  }
  cropDraft(dataItem) {
    this.counter = 0;
    /*if(dataItem.cropYearCode === '' || dataItem.cropYearCode === null){
       this.req_cropYearcode=true;
       this.counter++;
    }*/
    if (dataItem.cropYearCode == null) {
      this.req_cropYearcode = true;
      this.counter++;
    } else if (dataItem.cropYearCode != null) {
      if (dataItem.cropYearCode.trim() == '') {
        this.req_cropYearcode = true;
        this.counter++;
      } else if (dataItem.cropYearCode.trim().length > 20) {
        this.req_cropYearcode = true;
        this.counter++;
      }
      else {
        this.req_cropYearcode = false;
      }
    }

    if (dataItem.cropYearName == null) {
      this.req_cropYearname = true;
      this.counter++;
    } else if (dataItem.cropYearName != null) {
      if (dataItem.cropYearName.trim() == '') {
        this.req_cropYearname = true;
        this.counter++;
      } else if (dataItem.cropYearName.trim().length > 200) {
        this.req_cropYearname = true;
        this.counter++;
      }
      else {
        this.req_cropYearname = false;
      }
    }

    if (dataItem.cropYearProductDto == null || dataItem.cropYearProductDto.length==0) {
      this.req_prodName = true;
      
      //this.grid.wrapper.nativeElement.children[3].lastElementChild.firstElementChild.firstElementChild.rows[0].style.background="#eaeaea";
      //this.grid.wrapper.nativeElement.children[3].lastElementChild.firstElementChild.firstElementChild.rows[0].children[4].children.product.children[0].children[1].children[0].style.borderBottomColor="red";
     // this.grid.wrapper.nativeElement.children[3].lastElementChild.firstElementChild.firstElementChild.rows[this.rowindex+1].children[4].children.product.children[0].children[1].children[0].style.setProperty("border-color", "red", "important");
      this.counter++;
    }
    else {
     // this.grid.wrapper.nativeElement.children[3].lastElementChild.firstElementChild.firstElementChild.rows[0].children[4].children.product.children[0].children[1].children[0].style.borderColor="#666666 !important";
      this.req_prodName = false;

    }
    if (this.counter !== 0) {
      this.showModal();
      setTimeout(() => { this.hideModal(); }, 1000);
      var sender=this.sender;
      sender.editRow(this.rowindex);
      return false;
    } 
    // else {
    //   return true;
    // }

    dataItem.action = 'DRAFT';
    this.subscription = this.cropYearService.savecropdetails(dataItem).subscribe(addcropetail => {
      console.log(JSON.stringify(dataItem));
      this.cropYearService.cropyearDetails.push(<CropYear>addcropetail);
      this.cropadded.show();
      this.cropcode = dataItem.cropYearName;
      // var sender=this.sender;
      // sender.editRow(this.rowindex);
      setTimeout(() => { this.cropadded.hide(); }, 3000);
      var sender=this.sender;
      sender.closeRow(this.rowindex);
      this.loadingCropYearList();
      this.gethighlight();
    },
      error => {
        this.croperror=[];
        if(this.cropYearService.errorStatusCode.errorMessage!=null){
        this.errormessage=this.cropYearService.errorStatusCode.errorMessage.split(";")
        for(var i=0;i<this.errormessage.length;i++){
          this.croperror.push(this.errormessage[i]);
        }
        this.validation.show();
        }
      },
      () => console.log('Finished')
    );
    

  }

  gethighlight(){
    setTimeout(() => { this.grid.wrapper.nativeElement.children[3].lastElementChild.firstElementChild.firstElementChild.rows[0].style.background="#eaeaea";}, 3000);
    setTimeout(() => { this.grid.wrapper.nativeElement.children[3].lastElementChild.firstElementChild.firstElementChild.rows[0].style.background="";}, 6000);
  }

  public showModal(): void {
    this.errormodal = true;
  }

  public hideModal(): void {
    this.deletesuccessModal.hide();
  }

  closepopup() {
    this.isDeletePopupModal = false;
    this.errormodal = false;
    this.isDeletePopupModals = false;
  }

  public onHidden(): void {
    this.isDeletePopupModal = false;
    this.errormodal = false;
    this.isDeletePopupModals = false;
  }
  cropActive(dataItem, isNew) {
    this.counter = 0;
    if (dataItem.cropYearCode == null) {
      this.req_cropYearcode = true;
      this.counter++;
    } else if (dataItem.cropYearCode != null) {
      if (dataItem.cropYearCode.trim() == '') {
        this.req_cropYearcode = true;
        this.counter++;
      } else if (dataItem.cropYearCode.trim().length > 20) {
        this.req_cropYearcode = true;
        this.counter++;
      }
      else {
        this.req_cropYearcode = false;
      }
    }

    if (dataItem.cropYearName == null) {
      this.req_cropYearname = true;
      this.counter++;
    } else if (dataItem.cropYearName != null) {
      if (dataItem.cropYearName.trim() == '') {
        this.req_cropYearname = true;
        this.counter++;
      } else if (dataItem.cropYearName.trim().length > 200) {
        this.req_cropYearname = true;
        this.counter++;
      }
      else {
        this.req_cropYearname = false;
      }
    }

    if (dataItem.cropYearProductDto == null || dataItem.cropYearProductDto.length==0) {
      this.req_prodName = true;
      this.counter++;
    }
    else {
      this.req_prodName = false;

    }
    if (this.counter !== 0) {
      this.showModal();

      setTimeout(() => { this.hideModal(); }, 1000);
      return false;
    } 
     dataItem.action = 'SAVE';
    this.subscription = this.cropYearService.savecropdetails(dataItem).subscribe(addcropetail => {
      this.cropYearService.cropyearDetails.push(<CropYear>addcropetail);
      this.cropadded.show();
      this.cropcode = dataItem.cropYearName;
      // var sender=this.sender;
      // sender.editRow(this.rowindex);
      var sender=this.sender;
      sender.closeRow(this.rowindex);
      setTimeout(() => { this.cropadded.hide(); }, 3000);
      this.loadingCropYearList();
      this.gethighlight();
    },
      error => {
        this.croperror=[];
        if(this.cropYearService.errorStatusCode.errorMessage!=null){
        this.errormessage=this.cropYearService.errorStatusCode.errorMessage.split(";")
        for(var i=0;i<this.errormessage.length;i++){
          this.croperror.push(this.errormessage[i]);
        }
        this.validation.show();
        }
      },
      () => console.log('Finished')
    );

  }

   checkorUnCheckAll(ele) {
     
     const checkboxes = this.cycheckbox.nativeElement.form.elements["dataItem.cropYearCode"];//document.getElementsByTagName('input');
     if (ele.target.checked) {
         for (let intval = 0; intval < checkboxes.length; intval++) {
             if (checkboxes[intval].type === 'checkbox') {
               if(this.cropYearService.cropyearDetails[intval].statusName=="Draft"){
                 checkboxes[intval].checked = true;
                 //this.cropcode=dataItem.cropYearCode;
               }
             }
         }
     } else {
         for (let i = 0; i < checkboxes.length; i++) {
              if (checkboxes[i].type === 'checkbox') {
                if(this.cropYearService.cropyearDetails[i].statusName=="Draft"){
                 checkboxes[i].checked = false;
                }
              }
         }
     }
 }


  getStyle() {
    // if (this.req_prodName === true) {
    //   return '#d43c3c';
    //  } else {
    //   return '#cfdee7';
    //  }
      return '#cfdee7';
  }

  editcropyear(dataItem) {
    this.counter = 0;
    this.cropcode = dataItem.cropYearName;
    if (dataItem.cropYearCode == null) {
      this.req_cropYearcode = true;
      this.counter++;
    } else if (dataItem.cropYearCode != null) {
      if (dataItem.cropYearCode.trim() == '') {
        this.req_cropYearcode = true;
        this.counter++;
      } else if (dataItem.cropYearCode.trim().length > 20) {
        this.req_cropYearcode = true;
        this.counter++;
      }
      else {
        this.req_cropYearcode = false;
      }
    }


    if (dataItem.cropYearName == null) {
      this.req_cropYearname = true;
      this.counter++;
    } else if (dataItem.cropYearName != null) {
      if (dataItem.cropYearName.trim() == '') {
        this.req_cropYearname = true;
        this.counter++;
      } else if (dataItem.cropYearName.trim().length > 200) {
        this.req_cropYearname = true;
        this.counter++;
      }
      else {
        this.req_cropYearname = false;
      }
    }
    if (dataItem.cropYearProductDto.length==0 || dataItem.cropYearProductDto == null) {
      this.req_prodName = true;
      this.counter++;
    }
    else {
      this.req_prodName = false;
    }


    if (this.counter !== 0) {
      this.showModal();

      setTimeout(() => { this.hideModal(); }, 1000);
       this.loadingCropYearList();
      return false;
    }

    this.subscription = this.cropYearService.editcropdetails(dataItem).subscribe(addcropetail => {
      this.cropYearService.cropyearDetails.push(<CropYear>addcropetail);
      this.cropcode = dataItem.cropYearCode;
      this.cropedits.show();
      setTimeout(() => { this.cropedits.hide(); }, 3000);
      var sender=this.sender;
      sender.closeRow(this.rowindex);
      //sender.editRow(this.rowindex);
      this.loadingCropYearList();
      this.gethighlight();
    },
      error => {
      },
      () => console.log('Finished')
    );
  }


  editcropyearwithstatus(dataItem) {

    this.counter = 0;
    this.cropcode = dataItem.cropYearName;
    if (dataItem.cropYearCode == null) {
      this.req_cropYearcode = true;
      this.counter++;
    } else if (dataItem.cropYearCode != null) {
      if (dataItem.cropYearCode.trim() == '') {
        this.req_cropYearcode = true;
        this.counter++;
      } else if (dataItem.cropYearCode.trim().length > 20) {
        this.req_cropYearcode = true;
        this.counter++;
      }
      else {
        this.req_cropYearcode = false;
      }
    }


    if (dataItem.cropYearName == null) {
      this.req_cropYearname = true;
      this.counter++;
    } else if (dataItem.cropYearName != null) {
      if (dataItem.cropYearName.trim() == '') {
        this.req_cropYearname = true;
        this.counter++;
      } else if (dataItem.cropYearName.trim().length > 200) {
        this.req_cropYearname = true;
        this.counter++;
      }
      else {
        this.req_cropYearname = false;
      }
    }
    if (dataItem.cropYearProductDto.length==0 || dataItem.cropYearProductDto == null) {
      this.req_prodName = true;
      this.counter++;
    }
    else {
      this.req_prodName = false;
    }


    if (this.counter !== 0) {
      this.showModal();

      setTimeout(() => { this.hideModal(); }, 1000);
       this.loadingCropYearList();
      return false;
    }

    
    if (dataItem.statusName === "Draft") {
      dataItem.statusName = "Active";
      this.statusmsg = "Activated";
      this.cropedit.show();
    } else if (dataItem.statusName === "Active") {
      this.statusmsg = "InActivated";
      dataItem.statusName = "InActive";
      this.cropedit.show();
    } else if (dataItem.statusName === "InActive") {
      dataItem.statusName = "Active";
      this.statusmsg = "Activated";
      this.cropedit.show();
    }
    if (dataItem.cropYearCode == null) {
      this.req_cropYearcode = true;
      this.counter++;
    } else if (dataItem.cropYearCode != null) {
      if (dataItem.cropYearCode.trim() == '') {
        this.req_cropYearcode = true;
        this.counter++;
      } else if (dataItem.cropYearCode.trim().length > 20) {
        this.req_cropYearcode = true;
        this.counter++;
      }
      else {
        this.req_cropYearcode = false;
      }
    }

    this.subscription = this.cropYearService.editcropdetailsstatus(dataItem).subscribe(addcropetail => {
      this.cropYearService.cropyearDetails.push(<CropYear>addcropetail);
      this.cropedit.show();
      this.cropcode = dataItem.cropYearCode;
      setTimeout(() => { this.cropedit.hide(); }, 3000);
      var sender=this.sender;
      sender.closeRow(this.rowindex);
      this.loadingCropYearList();
      this.gethighlight();
    },
      error => {
      },
      () => console.log('Finished')
    );
  }


  confirmdelete(dataItem) {
    this.cropcode=dataItem.cropYearCode;
    this.del_id = dataItem.cropYearId;
    if (dataItem.statusName != "Draft") {

      this.confirmdel.show();
    }
    else {
      // this.isDeletePopupModal=true;
      this.confirmdels.show();
    }
    this.status = dataItem.statusName;
    if (dataItem.statusName === 'Draft') {
      this.deleteStatus = 'Activate';
    }
    else if (dataItem.statusName === 'Active') {
      this.deleteStatus = 'Dectivate';
    }
    else if (dataItem.statusName === 'InActive') {
      this.deleteStatus = 'Activate';
    }
  }
  deletecropyear(dataItem,code) {
     this.cropcode=code;
    dataItem = this.del_id;
    this.confirmdel.hide();
    this.confirmdels.hide();
    // this.isDeletePopupModals=false;
    // this.isDeletePopupModal=false;
    this.subscription = this.cropYearService.deletecropdetails(this.del_id).subscribe(addcropetail => {
      this.cropYearService.cropyearDetails.push(<CropYear>addcropetail);
      this.cropdelete.show();
      var sender=this.sender;
      if(sender!=undefined){
      sender.closeRow(this.rowindex);
    }
    this.loadingCropYearList();
      // this.cropcode = dataItem.cropYearCode;
      setTimeout(() => { this.cropdelete.hide(); }, 3000);
      
    },
      error => {
      },
      () => console.log('Finished')
    );
  }



  public cancelHandler({ sender, rowIndex }) {
    this.draft = true;
    this.active = true;
    this.inactive = true;
    this.isActionPerformed = false;
    this.loadingCropYearList();
    this.closeEditor(sender, rowIndex);
  }

  sortChange(sort: SortDescriptor[]): void {
    this.sort = sort;
    this.loadingCropYearList();
  }

  public onStateChange(state: State) {
    this.state = state;
  }

  loadcropyearDetails(): void {
    this.gridView = {
      data: process(orderBy(this.cropYearService.cropyearDetails, this.sort), this.state).data,
      total: this.cropYearService.cropyearDetails.length
    };
  }
  dataStateChange(state: DataStateChangeEvent): void {
    this.state = state;
  }


  pageChange(event: PageChangeEvent): void {
    this.state.skip = event.skip;
    this.loadingCropYearList();
  }

  allData(): ExcelExportData {
    const result: ExcelExportData = {
      group: this.group
    };
    return result;
  }

  applyDropDownChange($evt) {
    console.log($evt);
  }

  getProductList() {
    this.subscription = this.cropYearService.getProductList().subscribe(detail => {
      this.productList = detail.body;
    },
      error => { throw error }
    );
  }

}
