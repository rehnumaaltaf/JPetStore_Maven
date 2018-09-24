import { Component, NgModule, OnInit, ViewChild, OnDestroy, Output, Input , ViewEncapsulation } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { add } from '../../../shared/interface/router-links';
import {ProductGradeService} from './service/product-grade.service';
import { CommonModule, PlatformLocation, Location} from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';
import { FormGroup ,  FormControl,  Validators,  FormBuilder, ReactiveFormsModule , FormsModule} from '@angular/forms';
import { Subscription } from 'rxjs/Subscription';
import { MasterSetupService } from './../../master-setup/service/master-setup.service';
import { ExcelExportData } from '@progress/kendo-angular-excel-export';
import { GridComponent, GridDataResult, DataStateChangeEvent, PageChangeEvent} from '@progress/kendo-angular-grid';
import { process, SortDescriptor, orderBy, GroupDescriptor, State } from '@progress/kendo-data-query';
import { view } from '../../../shared/interface/router-links';
import {  ModalDirective } from 'ngx-bootstrap';
import { ResponseData } from '../../../shared/interface/responseData';
import { Observable } from 'rxjs/Rx';
import { Link } from '../../../shared/interface/link';
import { CanComponentDeactivate } from '../../../shared/guards/can-deactivate-guard';
import { Grade, OriginList, active, draft, inactive, radioNo , radioYes , washedtype ,
          draftsuccess , MasterName, activesuccess ,  inactivesuccess , kendoHightcheckbox,
         kendoHight, radioYesValue , radioNoValue  , save, update ,  deactive , reactivate } from './grade-model';

import { msgsuccess , msgupdate , msgactivateorsubmit , msgdelete , msgdeactivate , msgreactivate , msgsave
          ,  msgconfirmdeactivate , msgconfirmdelete ,  msgconfirmreactive } from './common-messages';

@Component({
  selector: 'app-product-grade',
  templateUrl: './product-grade.component.html',
  styleUrls: ['./product-grade.component.css'],
  encapsulation: ViewEncapsulation.None,
})
export class ProductGradeComponent implements OnInit, OnDestroy, CanComponentDeactivate {
  @ViewChild('successModals') public successModals: ModalDirective;
  @ViewChild('reactivatePopupModal') public reactivatePopupModal: ModalDirective;

  @ViewChild('deletesuccessModal') public deletesuccessModal: ModalDirective;
  @ViewChild('deletePopUpModal') public deletePopUpModal: ModalDirective;

  @ViewChild('updateModal') public updateModal: ModalDirective;
  @ViewChild('errorModal') public errorModal: ModalDirective;
  
  group: GroupDescriptor[] = [{ field: 'statusName' }];
  productdetails: Grade = new Grade();
  productGradeDetails: Grade[];
  isActionPerformed = false;
  private link: Link;
  headerGradeCode: string;
  headerGradeName: string;
  headerProductName: string;
  headerTermCredit: string;
  headerOriginName: string;

  isDeletePopupModal: Boolean = false;
  deleteSuccessModals: Boolean  = false;
  SuccessModals: Boolean = false;
  isShowModals: Boolean = false;

  headerBlended: string;
  headerBase: string;
  headerHedgeRatio: string;
  data: Grade[];
  subscription: Subscription;
  multiple: Boolean = false;
  view: Observable<GridDataResult>;
  dataView = [];
  gridView: GridDataResult;
  gradeId: number;
  gradeCode: string;
  gradeName: string;
  showHide: boolean;



  deleteSuccessModals1: Boolean = false;

 isError: boolean;
  success: number;
  isNavbtwComponent: boolean;
  allowUnsort: Boolean = true;
  addoreditpageflag: boolean;
  addoreditpagemsg: string;
  sort: SortDescriptor[] = [];
  isComplete: Boolean = false;
  isDeactivatePopupModal: Boolean = false;
  isActiveStatus: Boolean = false;
  isDraftStatus: Boolean = false;
  isCannotDeletePopup;
  isCancelUpdatePopupModal: Boolean = false;
  errorMessageForPermission: string;
  gradeCodedelpop: string;
  statusdelname: string;
  msgsuccess: string;
  id;
    del_id;
  isupdateModal: boolean;
  @Output() loadingData: Boolean = true;
  statusmsg: string;
  state: State = {
        skip: 0,
        take: 1000
    };
      savedData;
   statusName;
   errorMessage;
  gridData: GridDataResult;
   viewByIddelete: Boolean = false;
   mastername: string;
  public status: any = {
    isFirstOpen: true,
    isFirstDisabled: false
  };
  constructor(private route: ActivatedRoute, private router: Router, public productGradeService: ProductGradeService,
              private masterSetupService: MasterSetupService , platformlocation: PlatformLocation, location: Location) {
                 this.showHide = true;
            this.success = 0;
            this.allData = this.allData.bind(this);
     platformlocation.onPopState(() => {
         this.isNavbtwComponent = true;

        });
  }
  ngOnInit() {
       this.mastername = MasterName.toString();
    this.loadingProductGradeList();
        if (this.productGradeService.msgStatusName != null  && this.productGradeService.msgStatusName !== undefined ) {
           this.msgsuccess = msgsuccess.toString();
          if (this.productGradeService.msgStatusName.toLowerCase() ===  save.toString().toLowerCase()) {
               this.statusmsg = msgsave.toString();
          }

          if (this.productGradeService.msgStatusName.toLowerCase() ===  update.toString().toLowerCase()) {
              this.statusmsg = msgupdate.toString();
          }
          if (this.productGradeService.msgStatusName.toLowerCase() ===  active.toString().toLowerCase()) {
               this.statusmsg = msgactivateorsubmit.toString();
          }
          if (this.productGradeService.msgStatusName.toLowerCase() ===  deactive.toString().toLowerCase()) {
              this.statusmsg = msgdeactivate.toString();
          }

        if (this.productGradeService.msgStatusName.toLowerCase() ===  reactivate.toString().toLowerCase()) {
            this.statusmsg = msgreactivate.toString();
          }
           this.isShowModals = true;
         setTimeout(() => {this.successModals.hide(); this.productGradeService.msgStatusName = null  } , 3000);

        }
         
        
          // this.SuccessModalsaftersave = true;
        //     setTimeout(() => {this.isShowModal = false; } , 3000);

  }

  // kendoHight() {
  //   return kendoHight;
  // }

  kendoHightcheckbox() {
    return kendoHightcheckbox;
  }

  //  public showModal(): void {
  //     this.isShowModal = true;
  //     this.successModal.show();
  // }

  public hideModalshowmodel(): void {
     this.productGradeService.msgStatusName = null;
    this.successModals.hide();
    }
  // public onHiddenshowmodel(): void {
  //   this.isShowModal = false;
  // }

hide() {
  this.isDeletePopupModal = false;
  this.deleteSuccessModals = false;
  this.deleteSuccessModals1 = false;
  this.isShowModals = false;
}


 loadingProductGradeList() {
  this.gradeName = this.productGradeService.gradeName;
   this.addoreditpageflag = this.productGradeService.addoreditpageflag;
    console.log(' addoreditpageflag ' + this.addoreditpageflag);
    if (this.addoreditpageflag) {
        this.addoreditpagemsg = 'Added';
    } else {
        this.addoreditpagemsg = 'Updated';
    }
     this.subscription = this.productGradeService.getProductGradeViewList().subscribe(productGrade => {
      this.productGradeService.productGradeDetails = productGrade.body;
      this.data = productGrade.body;
      const data1 = productGrade.view.column.split(',');
    this.loadingData = false;
      if (data1[0] != null && data1[0] === 'gradeCode') {
        this.headerGradeCode = 'Grade Code';
      }
      if (data1[1] != null && data1[1] === 'gradeName') {
        this.headerGradeName = 'Grade Name';
      }
      if (data1[2] != null && data1[2] === 'product') {
        this.headerProductName = 'Product';
      }
      if (data1[3] != null && data1[3] === 'originList') {
        this.headerOriginName = 'Origin';
      }
       if (data1[4] != null && data1[4] === 'isBlended') {
        this.headerBlended = 'Is Blended Grade';
       }
       if (data1[5] != null && data1[5] === 'isBase') {
        this.headerBase = 'Is Base Grade';
       }
       if (data1[6] != null && data1[6] === 'hedgeRatio') {
        this.headerHedgeRatio = 'Hedge Ratio';
       }

   }, error => {
      console.log('Error Loading Grade Listing: ' + <any>error)
      this.loadingData = false;
    });
  }

  redirecttoadd() {
     this.router.navigate([add]);
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
    allData(): ExcelExportData {
    const result: ExcelExportData = {
     data: process(this.productGradeService.productGradeDetails, this.state).data,
      group: this.group
    };

    return result;
  }
  pageChange(event: PageChangeEvent): void {
        this.state.skip = event.skip;
        this.loadingProductGradeList();
    }

onRowSelect(event) {
 // alert(event.data);
}


 sortChange(sort: SortDescriptor[]): void {
        this.sort = sort;
        this.loadingProductGradeList();
    }
public  dataStateChange(state: DataStateChangeEvent): void {
         this.state = state;
        this.gridData = process(this.productGradeService.productGradeDetails, this.state);
    }
checkAllClicked($event) {
    console.log('checkAllClicked', $event);
     /*this.event.forEach(x => x.state = ev.target.checked)*/
  }
onHideList() {
   this.isupdateModal = false;
}
// delete success pop up hide
// onHideView() {
//   this.deleteSuccessModal.hide();
// }

onHideupdateModal() {
  this.updateModal.hide();
}
  updatewithStatus(productGrade, status) {
  this.productdetails =  productGrade;
   if (status.toLowerCase() === active.toString().toLowerCase()) {
     this.productdetails.statusName = active.toString();
   } else if (status.toLowerCase() === inactive.toString().toLowerCase()) {
      this.productdetails.statusName =  inactive.toString();
   }
   this.productdetails.deletedArbitrationMappingIds = [];
   this.productdetails.deletedGradeMappingIds = [];
      this.subscription = this.productGradeService.updateProductGradeDetails(this.productdetails).subscribe(savedwithStatus => {
        this.isupdateModal = true;
          setTimeout(() => {this.updateModal.hide(); }, 3000);
      },
      error => alert(error),
      () => console.log('Finished')
    );
 }
  viewById(gradeId) {
    this.subscription = this.productGradeService.getProductGradeById(gradeId).subscribe(productGradeDetails => {
    this.productGradeService.grade = productGradeDetails.body;
       this.router.navigate([view + gradeId]);
       },
         error => {
           this.errorMessageForPermission = error ;
           this.errorModal.show();
            // throw error;
        }

    );
 }
 clickToClosePop() {
   this.errorModal.hide();
 }
  product_grade_delete(event) {
    console.log('ddd' + event.statusName);
   this.del_id = event.gradeId;
      this.gradeCodedelpop = event.gradeCode;
     this.statusdelname = event.statusName;
       this.gradeName = event.gradeName;
      //this.deletePopUpModal.show()
    if (event.statusName.toLowerCase() === draft.toString().toLowerCase()) {
      this.isDraftStatus = true;
      this.statusmsg = msgconfirmdelete.toString();
    }else if (event.statusName.toLowerCase() === active.toString().toLowerCase()) {
      this.statusmsg = msgconfirmdeactivate.toString();
      this.isActiveStatus = true;
    }
      this.isDeletePopupModal = true;
 }

 closedeletepopup() {
   this.deletePopUpModal.hide();
 }

 conf_delete(id) {
      this.deletePopUpModal.hide();
      console.log(this.del_id);
    const param = {'gradeId': this.del_id};
    this.subscription = this.productGradeService.deleteProductGrade(param, this.gradeName).subscribe(deletedStatus => {
          if (this.statusdelname != null || this.statusdelname !== undefined  ) {
               if (this.statusdelname.toLowerCase() === draft.toString().toLowerCase()) {
              this.isDraftStatus = true;
              this.statusmsg = msgdelete.toString();
              this.savedData = deletedStatus;
             
              if ( this.statusmsg != null ) {
                   this.deleteSuccessModals = true;
              setTimeout(() => {this.deletesuccessModal.hide(); this.statusmsg = null  }, 3000);

              }
            }else if (this.statusdelname.toLowerCase() === active.toString().toLowerCase()) {
              this.isActiveStatus = true;
            }
          }
         

          // setTimeout(() => {this.deleteSuccessModal.hide(); }, 3000);
          this.loadingProductGradeList();

       },
         error => {
             this.errorMessage = error;
             this.isCannotDeletePopup = true;
        },
         () => console.log('Finished')
    );
}

 canDeactivate(): boolean {
    return this.isActionPerformed;
  }

  ngOnDestroy() {
    // Called once, before the instance is destroyed.
   // this.subscription.unsubscribe();
  }

 closereactivatepopup() {
  //  this.isShowModal = false;
   this.reactivatePopupModal.hide();

 }
 confirmReactivate($event) {
  //  this.isShowModal = false;
   this.reactivatePopupModal.hide();
 }


}
