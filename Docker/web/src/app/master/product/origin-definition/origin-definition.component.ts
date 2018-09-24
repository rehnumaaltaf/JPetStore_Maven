import { Component, OnInit, OnDestroy, Output, ViewChild  } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';
import { OriginDefinition } from './origin-definition';
import { Router } from '@angular/router';
import { ResponseData } from '../../../shared/interface/responseData';
import { ActivatedRoute } from '@angular/router';
import { OriginDefinitionService } from './service/origin-definition.service';
import { FormGroup, FormControl } from '@angular/forms';
import { SelectItem } from '../../../shared/interface/selectItem';
import { ModalModule , AccordionModule } from 'ngx-bootstrap';
import { Link } from '../../../shared/interface/link';
import { origin } from '../../../shared/interface/router-links';
import { addOrigin } from '../../../shared/interface/router-links';
import { viewOrigin } from '../../../shared/interface/router-links';
import { master } from '../../../shared/interface/router-links';
import { ModalDirective } from 'ngx-bootstrap/modal';
import { CanComponentDeactivate } from '../../../shared/guards/can-deactivate-guard'
@Component({
  selector: 'app-origin-definition',
  templateUrl: './origin-definition.component.html',
  styleUrls: ['./origin-definition.component.css']
})
export class OriginDefinitionComponent implements  OnInit, OnDestroy {
 @ViewChild('successModal') public successModal: ModalDirective;
  // addOriginDetail: OriginDefinition[];
  showHide: boolean;
  subscription: Subscription;
  public origin;
  isComplete: Boolean = false;
  selectedOriginrow: OriginDefinition;
  UomCodeDropDown: SelectItem[];
  private originCode;
  private originName;
   isNavbtwComponent: boolean;
  isActionPerformed = false;
  private link: Link;
  public isShowModal: boolean;
  public backdrop: boolean;
  public success;
  @Output() loadingData: Boolean = true;
  isDeletePopupModal: Boolean = false;
  deactivateSuccessModal: boolean;
  deleteSuccessModal: boolean;
  isDeactivatePopupModal: Boolean = false;
  public status;
  public origin_Code;
  public origin_Name;
  public origin_Id;
   public savedData;

  constructor(private route: ActivatedRoute , private router: Router, private originDefinitionService: OriginDefinitionService) {
  }
  changeShowStatus() {
    // this.showHide = !this.showHide;
    this.router.navigate([addOrigin]);
  }

  // tslint:disable-next-line:member-ordering
   /* originForm = new FormGroup({
    originCode: new FormControl(),
    originName: new FormControl(),
    originFullName: new FormControl(),
    country: new FormControl(),
    estateName: new FormControl(),
    originRegionName: new FormControl(),
    originRegionMeanAboveSeaLevel: new FormControl(),
    originCupProfileCode: new FormControl(),
    originCupProfileName: new FormControl(),



  })  */
 ngOnInit() {
this.loadingOriginList();
this.isShowModal = false;
/*this.route.queryParams.subscribe(params => {
        // Defaults to 0 if no query param provided.
        this.success = +params['success'];
        if(this.success==1){
          this.isShowModal = true;
          setTimeout(()=>{this.isShowModal = false;},3000);
        }
      });*/
      this.route.queryParams.subscribe(params => {
        // Defaults to 0 if no query param provided.
        this.origin_Name = this.originDefinitionService.originCodeParam;
        this.success = +params['success'];
        if (this.success === 1) {
          if (!this.isNavbtwComponent) {
            this.isShowModal = true;
            this.router.navigate([origin]);
          }else {
            this.router.navigate([master]);
          }
           setTimeout(() => {this.closesuccess(); }, 3000);
        }
      });
  }
viewById(event) {
     alert('==eventparam==> ' + JSON.stringify(event));
    this.originDefinitionService.viewbyIdParam = event;
    this.router.navigate([viewOrigin]);
  }

   conf_delete(id) {
     const param = { 'pkOriginId' : this.origin_Id};
     this.subscription = this.originDefinitionService.deleteOriginById(param).subscribe(deletedStatus => {
                           this.savedData = deletedStatus;
                           this.isDeletePopupModal = false;
                           this.isDeactivatePopupModal = false;
                           const statusCode = this.getStatusCode(deletedStatus);
                        if (statusCode === 200) {
                         if (this.status === 'Draft') {
                           this.deleteSuccessModal = true;
                           setTimeout(() => {this.deleteSuccessModal = false; }, 3000);
                           } else if (this.status === 'Active') {
                             this.deactivateSuccessModal = true;
                             setTimeout(() => {this.deactivateSuccessModal = false; }, 3000);
                           }
                           this.loadingOriginList();
                      }
       },
         error => alert(error),
         () => console.log('Finished')
    );
}

 getStatusCode(deletedStatus) {
  let statusCode: number;
   for (const obj in deletedStatus) {
                                if (deletedStatus.hasOwnProperty(obj)) {
                                for (const prop in deletedStatus[obj]) {
                                    if (deletedStatus[obj].hasOwnProperty(prop)) {
                                      if (prop === 'status') {
                                          statusCode = deletedStatus[obj][prop];
                                      }
                                    }
                                  }
                       }
                    }
  return statusCode;
}

 origin_delete(params) {

      this.origin_Id = params.originDto.pkOriginId;
     this.status = params.originDto.statusName;
     this.origin_Code = params.originDto.originCode;
      this.origin_Name = params.originDto.originName;
   if (this.status === 'Draft') {
      this.isDeletePopupModal = true;
    } else if (this.status === 'Active') {
      this.isDeactivatePopupModal = true;
    }

 }

 closedeletepopup() {

   this.isDeactivatePopupModal = false;
  this.isDeletePopupModal = false;

 }

  public closesuccess(): void {
    this.successModal.hide();
  }


  delete() {
  //  const index = this.findSelectedOriginIndex();
   // this.originDefinitionService.originDetails = this.originDefinitionService.originDetails.filter((val, i) => i !== index);
  }

 /* findSelectedOriginIndex(): number {

        return this.originDefinitionService.originDetails.indexOf(this.selectedOriginrow);
    }*/

  loadingOriginList() {
 this.subscription = this.originDefinitionService.getOriginDefinitionJSON().subscribe(originDefinitionDetails => {
     // alert(JSON.stringify(originDefinitionDetails.body))
     this.origin = originDefinitionDetails.body;
    },
      error => alert(error),
      () => console.log('Finished')
    );


  }


  addOrigin() {

  }
   canDeactivate(): boolean {
    return this.isActionPerformed;
  }

   ngOnDestroy() {
    // prevent memory leak when component destroyed
    this.subscription.unsubscribe();
  }

}
