import { Component, OnInit, OnDestroy, Output, ViewChild } from '@angular/core';
import { process, SortDescriptor, orderBy, GroupDescriptor, State } from '@progress/kendo-data-query';
import { GridComponent, GridDataResult, DataStateChangeEvent, PageChangeEvent} from '@progress/kendo-angular-grid';
import { WeightTermModel} from './weight-terms';
import { WeightTermsService } from './service/weight-terms.service';
import { Subscription } from 'rxjs/Subscription';
import { Router } from '@angular/router';
import { addweightterm } from '../../../shared/interface/router-links';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { ExcelExportData } from '@progress/kendo-angular-excel-export';
import { Observable } from 'rxjs/Rx';
import { ActivatedRoute } from '@angular/router';
import { Link } from '../../../shared/interface/link';
import { ResponseData } from '../../../shared/interface/responseData';
import { SelectItem } from '../../../shared/interface/selectItem';
import { ModalModule, AccordionModule } from 'ngx-bootstrap';
import { ModalDirective } from 'ngx-bootstrap/modal';
import { master } from '../../../shared/interface/router-links';
import { MasterSetupService } from '../../master-setup/service/master-setup.service';
import { viewWeightTerms } from '../../../shared/interface/router-links';
import { listweight } from '../../../shared/interface/router-links';

@Component({
  selector: 'app-weight-terms',
  templateUrl: './weight-terms.component.html',
  styleUrls: ['./weight-terms.component.css']
})
export class WeightTermsComponent implements OnInit {
  @ViewChild('successModal') public successModal: ModalDirective;
  @ViewChild('deletesuccessModal') public deletesuccessModal: ModalDirective;
  @ViewChild('updateModal') public updateModal: ModalDirective;
 public data: any;
  public status;
  public savedData;
  multiple: Boolean = false;
  gridView: GridDataResult;
  allowUnsort: Boolean = true;
  group: GroupDescriptor[] = [];
  state: State = {
       skip: 0,
       take: 1000
 };
 @ViewChild('editModal') public editModal: ModalDirective;
gridData: GridDataResult;
weighttermmaterial: WeightTermModel[];
weighttermdetail: WeightTermModel = new WeightTermModel();
subscription: Subscription;
sort: SortDescriptor[] = [];
openEdit: Boolean = false;
allItemsChecked: Boolean = false;
message: string;
UpdateModal = false;
public isupdateModal;
public weightTerms_Id;
public isShowModal: boolean;
  public backdrop: boolean;
  public success;
  public weightTermNameForSuccess;
  delSuccess: number;
  isUpdate: any;
  isActivated: Number;
  isDeactivated: any;
  isInActivated: any;
  deactivateSuccessDialog: string;
  isDeletePopupModal: Boolean = false;
  deactivateSuccessModal: boolean;
  isdeleteSuccessModal: boolean;
  isDeactivatePopupModal: Boolean = false;
  successToast: string;
  weightTermName: string;
  groups;


  constructor(private route: ActivatedRoute, http: Http, public weightTermsService: WeightTermsService, private router: Router ) { }

  ngOnInit() {
      window.scrollTo(0, 0);
    this.loadingWeightTremList();

    this.route.queryParams.subscribe(params => {
      // Defaults to 0 if no query param provided.
       this.success = Number(params['success']);
      this.isUpdate = params['isUpdate'];
      this.isActivated = Number(params['Activate']);
      this.delSuccess = Number(params['delSuccess']);

      // THIS BLOCK IS TOAST FROM SAVE AND SUBMIT FROM ADD PAGE

      if (this.success === 1) {
        this.weightTermName = 'Weight-Term Name' + ' ' + params['weightterms'] + ' ' + 'Successfully Added';
       // alert(this.weightTermName);
        this.isShowModal = true;
        // this.weightTermName = params['weightterms']
        // alert(this.weightTermName);
         if (this.successModal) {
        setTimeout(() => { this.successModal.hide(); }, 3000);
        }
        this.router.navigate([listweight]);

      } else if (this.isUpdate) {
        this.successToast = 'Success: Weight Terms Name ' + params['weightTermsName'] +  ' Deactivated'  ;
        this.isupdateModal = true;
        if (this.updateModal) {
        setTimeout(() => { this.updateModal.hide(); }, 3000);
        }

        // below code for reactivate
        if (params['currentStatus'] === 'Active' || params['currentStatus'] === 'Draft') {
          this.successToast = 'Success: Weight Terms Name ' + params['weightTermsName'] +  ' Updated'  ;
        } else if (params['currentStatus'] === 'InActive') {
          this.successToast = 'Success: Weight Terms Name ' + params['weightTermsName'] +  ' Deactivated'  ;
        } // alert("inside update");
        if (this.updateModal) {
        setTimeout(() => { this.updateModal.hide(); }, 3000);
        }
        this.router.navigate([listweight]);
      } else if (this.delSuccess === 5) {
         this.isupdateModal = true;
        this.successToast = 'Success: Weight Terms Name ' + params['weightTermsName'] +  ' Deleted'  ;
        if (this.updateModal) {
        setTimeout(() => { this.updateModal.hide(); }, 3000);
        }
        this.router.navigate([listweight]);

      }

      // THIS BBLOCK IS TOAST FROM UPDATE ACTIVATE AND DEACTIVATE FROM EDIT PAGE

      if (this.isActivated === 1) {
       // alert('In active 1')
        this.weightTermNameForSuccess = params['weightTermsName'];
        this.successToast = 'Success: Weight Terms Name ' + params['weightTermsName'] +  ' Activated'  ;
        this.isupdateModal = true;
        // alert("inside update");
        if (this.updateModal) {
        setTimeout(() => { this.updateModal.hide(); }, 3000);
        }
        this.router.navigate([listweight]);
        if (params['status'] === 'Active') {
          this.status = 'Activated'
        } else { this.status = 'Deactivated' }
        if (this.updateModal) {
        setTimeout(() => { this.updateModal.hide(); }, 3000);
        }
        this.router.navigate([listweight]);
      } else if (this.isActivated === 2) {
       // alert('In active 2')
        this.weightTermNameForSuccess = params['weightTermsName'];
        this.successToast = 'Success: Weight Terms Name ' + params['weightTermsName'] +  ' Deactivated'  ;
        this.isupdateModal = true;
        // alert("inside update");
        if (this.updateModal) {
                  setTimeout(() => { this.updateModal.hide(); }, 3000);
        }
        this.router.navigate([listweight]);
      }

    });

    this.isDeletePopupModal = false;
    this.isdeleteSuccessModal = false;
}
weightTerms_Delete(params) {
    console.log(JSON.stringify(params));
    // alert(JSON.stringify(params));
    this.weightTermNameForSuccess = params.weightTermsName;
          // alert(JSON.stringify(this.weightTermNameForSuccess));
    this.status = params.statusName;
    this.weightTerms_Id = params.weightTermsId;
  //  this.holidayCal_Code = params.originCode;
  //  this.holidayCal_Name = params.originName;
    this.isDeletePopupModal = true;
    if (this.status === 'Draft') {
      this.deactivateSuccessDialog = 'Please Confirm to Delete ' + params['weightTermsName'] ;
    } else if (this.status === 'Active') {
      this.deactivateSuccessDialog = 'Please Confirm to Activate ' + params['weightTermsName'] ;
    } else if (this.status === 'Inactive') {
      this.deactivateSuccessDialog = 'Please Confirm to Deactivate ' + params['weightTermsName'] ;
    }

  }

  conf_delete(params) {
   this.successModal.hide();
   const param = { 'weightTermsId': this.weightTerms_Id };
    this.subscription = this.weightTermsService.deleteHolidayCalendarById
      (param)
      .subscribe(deletedStatus => {
        // this.successModal.hide();
        const statusCode = this.getStatusCode(deletedStatus);
        if (this.status === 'Active') {
          // alert('activate')
          this.deactivateSuccessDialog = 'Success: Weight Terms Name ' + this.weightTermNameForSuccess +  ' Deactivated'  ;
        } else if (this.status === 'InActive') {
          // alert('reactivate')
          this.deactivateSuccessDialog = 'Success: Weight Terms Name ' + this.weightTermNameForSuccess +  ' Activated'  ;
        } else if (this.status === 'Draft') {
          this.deactivateSuccessDialog = 'Success: Weight Terms Name ' + this.weightTermNameForSuccess +  ' Deleted'  ;
          // this.deactivateSuccessDialog = 'successfully deleted calendar ';
        }
        this.isdeleteSuccessModal = true;
        if (this.deletesuccessModal) {
        setTimeout(() => { this.deletesuccessModal.hide(); }, 3000);
      }
        this.loadingWeightTremList();
      },
      error => { throw error });
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

  onHiddenpopup() {
    this.isDeletePopupModal = false;
    this.isDeactivatePopupModal = false;
    this.isupdateModal = false;
    // this.successModal.hide();
  }

  onHidden() {
     this.isShowModal = false;
  }


 dataStateChange(state: DataStateChangeEvent): void {
    this.state = state;
    this.gridData = process(this.weightTermsService.weightTermForListing, this.state);
  }

 allData(): ExcelExportData {
    const result: ExcelExportData = {
      data: process(this.weightTermsService.weightTermForListing, this.state).data,
      group: this.group
    };

    return result;
  }

pageChange(event: PageChangeEvent): void {
    this.state.skip = event.skip;
    this.loadingWeightTremList();
   }
viewById(event) {
    // alert('==eventparam==>' + JSON.stringify(event));
    this.weightTermsService.viewbyIdParam = event;
    console.log('weight term listing component', this.weightTermsService.viewbyIdParam);
    this.router.navigate([viewWeightTerms]);
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


  loadingWeightTremList() {
// const link = this.masterSetupService.getChildObject('View Uom.GET');
  //  this.loadingData = true;
    this.subscription = this.weightTermsService.getWeightTermDetailsJSON().subscribe(addWeightTermDetail => {
    this.weightTermsService.weightTermModel = addWeightTermDetail.body;
    this.weighttermmaterial = addWeightTermDetail.body;
   // alert('this.weight' + this.weighttermmaterial);
    console.log(JSON.stringify(addWeightTermDetail.body) + 'kendo check');

  //  this.loadPackingDetails();
 //   this.loadingData = false;
    }, error => {
     // console.log(error)
     // this.loadingData = false;
    });
    }

// sort change is not implemented in html page
sortChange(sort: SortDescriptor[]): void {
        this.sort = sort;
        this.loadingWeightTremList();
    }

    changeShowStatus() {
             // alert('inside add');
     this.router.navigate([addweightterm]);
    }

editRow(index) {
    // alert('inside edit  ' + this.weighttermmaterial + '    ' + index);
    this.openEdit = true;
    this.weighttermdetail = this.weighttermmaterial[index];
     // alert('id is' + JSON.stringify(this.weighttermdetail));
  }

  onHideEditModal() {
      this.openEdit = false;

  }

 /* closeEditModal($event) {
    //  alert('on click');
    this.editModal.hide();
  //  alert('close popup');
   this.loadingWeightTremList();
      this.UpdateModal = true;
      setTimeout(() => {
        this.UpdateModal = false;
      }, 3000);
   // }
  } */

  closeEditModal($event) {
// alert('event value' + $event)
    this.editModal.hide();
    this.loadingWeightTremList();
    this.message = $event['msg'];
   //  alert('maggae vale' + this.message);
    if (this.message === undefined || this.message === '' ) {
       this.UpdateModal = false;
    }
    if (this.message !== '' && this.message !== undefined && this.message !== null) {
      this.loadingWeightTremList();
      this.UpdateModal = true;
      setTimeout(() => {
        this.UpdateModal = false;
      }, 3000);
    }
  }

  }
