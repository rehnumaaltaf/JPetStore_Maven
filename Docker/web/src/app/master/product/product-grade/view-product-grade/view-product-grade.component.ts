import { Component, OnInit , ViewChild } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { ProductGradeService } from '../service/product-grade.service';
import { productgrade } from '../../../../shared/interface/router-links';
import {  ModalDirective } from 'ngx-bootstrap';
import { Grade, OriginList, active, draft, inactive, radioNo , radioYes , washedtype ,
    draftsuccess , activesuccess ,  inactivesuccess ,  update ,  deactive , reactivate ,
    radioYesValue , radioNoValue  } from '../grade-model';
    import { msgsuccess , msgupdate , msgactivateorsubmit , msgdelete , msgdeactivate , msgreactivate , msgsave
          ,  msgconfirmdeactivate , msgconfirmdelete ,  msgconfirmreactive } from '../common-messages';


@Component({
  selector: 'app-view-product-grade',
  templateUrl: './view-product-grade.component.html',
  styleUrls: ['./view-product-grade.component.css']
})
export class ViewProductGradeComponent implements OnInit {
   @ViewChild('successModal') public successModal: ModalDirective;
    @ViewChild('successModal1') public successModal1: ModalDirective;
@ViewChild('deletesuccessModalview') public deletesuccessModalview: ModalDirective;
 @ViewChild('deletesuccessModalList') public deletesuccessModalList: ModalDirective;
@ViewChild('updateModal') public updateModal: ModalDirective;
  @ViewChild('deletesuccessModal') public deletesuccessModal: ModalDirective;

@ViewChild('deletePopUpModal') public deletePopUpModal1: ModalDirective;
 public status: any = {
    isFirstOpen: true,
    isFirstDisabled: false
  };
   isDeletePopupModal: boolean;
  public id;
  public del_id;
  public brandName;
  public gradeCode;
  public gradeName;
  public gradeFullName;
  public processingName;
  public processingSubType;
  public attributeName;
  public attributeValue;
  public baseGradeName;
  public gradeGroupName;
  public icoIndexName;
  public intlGradeList ;
  public gradeIsRaw;
  public isTenderable;
  public hedgeRatio;
  public isBrand;
  productName: string;
  savedData: string;
  statusName: string;
statusmsg: string;
  public externalSystemList;
  public isBlended;
  public premiumOrDiscount;
  public currencyName;
  isCannotDeletePopup: boolean;
  public uomName;
  brandcheck: boolean;
  isBase: string;
  basecheck: boolean;
  deactiveStatus: boolean;
  processingcheck: boolean;
   draft: boolean;
   active: boolean;
   inactive: boolean ;
   gradeCodedelpop: string;
  statusdelname: string;
    errorMessage: any;
  subscription: Subscription;
   status_change: boolean;
  gradeViewData: Grade = new Grade();
  deleteSuccessModal: Boolean = false;
  deleteSuccessModals: Boolean  = false;
    isActiveStatus: boolean;
    isDraftStatus: boolean;

isShowModal: boolean;
  constructor(private route: ActivatedRoute , private router: Router,
    private productGradeService: ProductGradeService) {
  }

  ngOnInit() {
      this.isDeletePopupModal = false;
     this.id = this.route.snapshot.params['gradeId'];
     this.brandcheck = false;
     this.basecheck = false;
     this.processingcheck = true;
  this.deactiveStatus = false;
     this.getGradeDetailsById(this.id);

  }


  getGradeDetailsById(gradeId) {
      this.subscription = this.productGradeService.getProductGradeById(gradeId).subscribe(prodcutGradeRec => {
      this.gradeViewData = prodcutGradeRec.body;
      if (this.gradeViewData.gradeCode !== null) {
            this.gradeCode = this.gradeViewData.gradeCode;
      }
      if (this.gradeViewData.gradeName !== null) {
            this.gradeName = this.gradeViewData.gradeName;
      }
      if (this.gradeViewData.gradeFullName !== null) {
            this.gradeFullName = this.gradeViewData.gradeFullName;
      }
      if (this.gradeViewData.product.prodName !== null ) {
        this.productName = this.gradeViewData.product.prodName;
      }
      if (this.gradeViewData.brand !== null ) {
        this.brandName = this.gradeViewData.brand.brandName;
      }
      if (this.gradeViewData.processingType !== null) {
         this.processingName = this.gradeViewData.processingType.processingName;
         if ( this.processingName.toLowerCase() === washedtype.toString().toLowerCase()) {
            this.processingcheck = true;
         }
      }
      if (this.gradeViewData.isBlended !== null) {
          this.isBlended = this.gradeViewData.isBlended;
           if (this.isBlended === radioYes.toString() ) {
               this.isBlended = radioYesValue.toString();
          } else {
               this.isBlended = radioNoValue.toString();
          }
      }
      if (this.gradeViewData.isBrand !== null) {
          this.isBrand = this.gradeViewData.isBrand;
          if (this.isBrand === radioYes.toString() ) {
              this.brandcheck = true;
              this.isBrand = radioYesValue.toString();
          } else {
             this.brandcheck = false;
            this.isBrand = radioNoValue.toString();
          }
      }
      if (this.gradeViewData.processingSubType !== null)  {
      this.processingSubType = this.gradeViewData.processingSubType.subTypeName;
      }
      if (this.gradeViewData.product !== null) {
       this.attributeName = this.gradeViewData.product.attribute1;
       this.attributeValue = this.gradeViewData.product.attribute2;
      }
      if (this.gradeViewData.isBase !== null) {
          this.isBase = this.gradeViewData.isBase;
          if (this.isBase === radioNo.toString() ) {
              this.basecheck = true;
              this.isBase = radioNoValue.toString();
          } else {
             this.basecheck = false;
            this.isBase = radioYesValue.toString();
          }
      }

      if (this.gradeViewData.baseGradeName !== null) {
         this.baseGradeName = this.gradeViewData.baseGradeName;
      }

      if (this.gradeViewData.gradeGroupName !== null) {
          this.gradeGroupName = this.gradeViewData.gradeGroupName;
      }

      if (this.gradeViewData.gradeIsRaw !== null) {
          this.gradeIsRaw = this.gradeViewData.gradeIsRaw;
          if (this.gradeIsRaw === radioYes.toString() ) {
              this.gradeIsRaw = radioYesValue.toString();
          } else {
            this.gradeIsRaw = radioNoValue.toString();
          }
      }

      if (this.gradeViewData.icoIndexName !== null) {
         this.icoIndexName = this.gradeViewData.icoIndexName;
      }

      if (this.gradeViewData.isTenderable !== null) {
           this.isTenderable = this.gradeViewData.isTenderable;
            if (this.isTenderable === radioYes.toString() ) {
               this.isTenderable = radioYesValue.toString();
          } else {
              this.isTenderable = radioNoValue.toString();
          }
      }
      if (this.gradeViewData.hedgeRatio !== null) {
        this.hedgeRatio = this.gradeViewData.hedgeRatio;
      }
      if (this.gradeViewData.premiumOrDiscount !== null) {
         this.premiumOrDiscount = this.gradeViewData.premiumOrDiscount;
      }
      if (this.gradeViewData.currencyCode !== null) {
         this.currencyName = this.gradeViewData.currencyCode;
      }
      if (this.gradeViewData.uomName !== null) {
          this.uomName = this.gradeViewData.uomName;
      }

      if (this.gradeViewData.intlGradeList.length > 0) {
       this.intlGradeList = (this.gradeViewData.intlGradeList);
      }
      if (this.gradeViewData.externalSystemList.length > 0) {
      this.externalSystemList = (this.gradeViewData.externalSystemList);
    }
     if (this.gradeViewData.statusName === draft.toString()) {
        this.draft = true;
        this.active = false;
        this.inactive = false;
      } else if (this.gradeViewData.statusName === active.toString() ) {
        this.active = true;
        this.draft = false;
        this.inactive = false;
      } else if (this.gradeViewData.statusName === inactive.toString()) {
        this.inactive = true;
        this.active = false;
        this.draft = false;
      }
   },
    error => alert(error),
      () => console.log('Finished')
      );
  }


   backtoPrev() {
    this.router.navigate([productgrade]);
  }

conf_status_change_draft() {
  const addoreditpage = false; // add page is true , edit page is false
  this.gradeViewData.statusName = active.toString();
  this.productGradeService.msgStatusName = active.toString()
      this.subscription = this.productGradeService.saveProductGrade(this.gradeViewData,  addoreditpage).
        subscribe(updateData => {
              this.router.navigate([productgrade]);
          }, error => {
              this.status_change = false;
             },
            () => console.log('Finished')

            );
}
conf_status_change_to_active() {
   const addoreditpage = false; // add page is true , edit page is false
  this.gradeViewData.statusName = active.toString();
   this.productGradeService.msgStatusName = deactive.toString();
   this.deactiveStatus = true;
    this.statusmsg = msgconfirmdeactivate.toString();
    this.isDeletePopupModal = true;
}

// pop confirm active to deactive and deactive to active
    conf_deactive_reactive ( event) {
       this.deactiveStatus = false;
       this.isActiveStatus = false;
       this.deletePopUpModal1.hide();
         const addoreditpage = false; // add page is true , edit page is false
        this.subscription = this.productGradeService.saveProductGrade(this.gradeViewData,  addoreditpage).
        subscribe(updateData => {
               this.router.navigate([productgrade] );
          }, error => {
              this.status_change = false;
             },
            () => console.log('Finished')

            );

    }
    hide() {
         this.isDeletePopupModal = false;
    }

conf_status_change_deactivate() {

  this.gradeViewData.statusName = inactive.toString();
  this.productGradeService.msgStatusName = active.toString();
   this.isActiveStatus = true;
    this.statusmsg = msgconfirmreactive.toString();
  this.isDeletePopupModal = true;
}

conf_delete(id) {

     // this.successModal.hide();
      this.id = id;
     // this.termNameToShow = this.basePaymentService.termNameToShow;
    const param = {'gradeId': this.id};
    this.subscription = this.productGradeService.deleteProductGrade(param, this.gradeName).subscribe(deletedStatus => {
            this.savedData = deletedStatus;
            this.deleteSuccessModal = true;
            
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
         
          /*  if (this.statusName.toLowerCase() === draft.toString().toLowerCase()) {
            this.isDraftStatus = true;
            }else if (this.statusName.toLowerCase() === active.toString().toLowerCase()) {
            this.isActiveStatus = true;
            }
            setTimeout(() => {this.deletesuccessModalList.hide(); }, 3000);*/
            this.router.navigate([productgrade], { queryParams: { 'success': 9} })
       },

         error => {
             this.errorMessage = error;
            this.isDeletePopupModal = false;
            this.isCannotDeletePopup = true;
        },
         () => console.log('Finished')
    );
}

/*product_grade_delete(event) {
    console.log('ddd' + event.statusName);
   this.del_id = event.gradeId;
      this.gradeCodedelpop = event.gradeCode;
     this.statusdelname = event.statusName;
       this.gradeName = event.gradeName;
   this.isDeletePopupModal = true;
  /* if (event.statusName.toLowerCase() === draft.toString().toLowerCase()) {
      this.isDraftStatus = true;
    }else if (event.statusName.toLowerCase() === active.toString().toLowerCase()) {
      this.isActiveStatus = true;
    }*/
 //}

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

}
