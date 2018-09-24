import { Component, OnInit, OnDestroy, Output, ViewEncapsulation, ViewChild  } from '@angular/core';
import { PlatformLocation, Location} from '@angular/common';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Subscription } from 'rxjs/Subscription';
import { Router } from '@angular/router';
// import { InternalPackaging } from './internalpackaging';
import { InternalPackingService } from './service/internal-packing.service';
import { MasterSetupService } from '../../master-setup/service/master-setup.service';
import { FormGroup, FormControl } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { SelectItem } from '../../../shared/interface/selectItem';
import { ResponseData } from '../../../shared/interface/responseData';
import { Link } from '../../../shared/interface/link';
import { addinternalPackaging } from '../../../shared/interface/router-links';
import { viewinternalPackaging } from '../../../shared/interface/router-links';
import { CanComponentDeactivate } from '../../../shared/guards/can-deactivate-guard'
import { InternalPacking } from '../../../shared/interface/router-links';
import { master } from '../../../shared/interface/router-links';
import { ExcelExportData } from '@progress/kendo-angular-excel-export';
import { GridComponent, GridDataResult, DataStateChangeEvent, PageChangeEvent} from '@progress/kendo-angular-grid';
import { process, SortDescriptor, orderBy, GroupDescriptor, State } from '@progress/kendo-data-query';
import { InternalPackagingModel } from './model/InternalPackagingModel';
import { ModalDirective } from 'ngx-bootstrap';
import { UnitMeasurementService } from '../../../master/uom-packing/unit-measurement/service/unit-measurement.service';

@Component({
  selector: 'app-internal-packaging',
  templateUrl: './internal-packaging.component.html',
  styleUrls: ['./internal-packaging.component.css'],
   encapsulation: ViewEncapsulation.None
})

 export class InternalPackagingComponent implements OnInit, OnDestroy, CanComponentDeactivate {

  public allItemsChecked: Boolean = false;
  showHide: boolean;
  subscription: Subscription;
  isComplete: Boolean = false;
  primarypacking: InternalPackagingModel[];
  internalPacking: InternalPackagingModel = new InternalPackagingModel();
  CodeDropDown: SelectItem[]= [];
  nonDuplicateArray = [];
  displayDialog: boolean;
  public success;
  private PrimaryPacking;
  private PackingID;
  private internalPackingTypeName;
  public isShowModal;
  public iseditModal;
  public iseditPopupModal;
  public internaledit;
  private uomDelete;
  public draft;
  public active;
  public inactive;
  public savedData;
  public isupdateModal;
  private uomdetails;
  isDeletePopupModal: boolean;
  isCannotDeletePopup: boolean;
  isActiveStatus: boolean;
  isDraftStatus: boolean;
  isUpdate: boolean;
  isActivated: boolean;
  isInActivated: boolean;
  public del_id;
  public status;
  public errorMessage;
  deleteSuccessModal: boolean;
  editSuccessModal: boolean;
  successModal: Boolean;
  platformlocation: PlatformLocation;
  location: Location;
  req_ipcode: boolean;
  req_ipname: boolean;
  req_ipuomcode: boolean;
  req_ipweight: boolean;
   reqmsg = '';
  req_typeCode: Boolean;
  dialogValue: string;
  isNavbtwComponent: boolean;
  isActionPerformed = true;
  private link: Link;
  group: GroupDescriptor[] = [{ field: 'status' }];
  multiple: Boolean = false;
  gridView: GridDataResult;
  allowUnsort: Boolean = true;
  sort: SortDescriptor[] = [];
  state: State = {
        skip: 0,
        take: 1000
    };
  openEdit: Boolean =  false;
  editMode: boolean;
  isEdit: boolean;
  public intpackdata;
  UpdateModal = false;

  @ViewChild('deletesuccessModal') public deletesuccessModal: ModalDirective;
    @ViewChild('editSuccessModal') public editsuccessModal: ModalDirective;
  @ViewChild('successModal') public SuccessModal: ModalDirective;
   @ViewChild('editModal') public editModal: ModalDirective;
  @ViewChild('Edit') public Edit: ModalDirective;
  public UomIdMap: Map<string, number> = new Map<string, number>();
  @Output() loadingData: Boolean = true;
  masters: ResponseData;
  deactivateSuccessDialog: string;
  addModal: boolean;
 gridData: GridDataResult;
 message: string;


  constructor(private route: ActivatedRoute, http: Http, private router: Router,
    private unitMeasurementService: UnitMeasurementService ,
    private masterSetupService: MasterSetupService,
    platformlocation: PlatformLocation, public internalPackingService: InternalPackingService) {
   //  this.showHide = true;
    // this.allData = this.allData.bind(this);
   /*  platformlocation.onPopState(() => {
                this.isNavbtwComponent = true;
                // this.router.navigate(['addUom'], { queryParams: {}});
                // this.location.go('addUom');
                // this.route.queryParams = [];
        });*/

  }


    ngOnInit() {
      this.req_ipcode = false;
      this.req_ipname = false;
      this.req_ipuomcode = false;
      this.req_ipweight = false;
      this.internalPacking.isBulkCode = '0' ;
      this.displayDialog = true;
     //  this.loadingPackList();
     if (this.route.queryParams) {
        this.route.queryParams.subscribe(params => {
        // Defaults to 0 if no query param provided.
        this.success = +params['success'];

       if (this.success === 1 && this.success !== 'NaN') {
            this.message = 'Data Saved Successfully!!!';
            this.addModal = true;
            setTimeout(() => { this.addModal = false; }, 3000);
          }
        });
     }
     this.internalPackingService.internalModel = [];
        this.loadingPackList();
        this.editMode = true;

  }

 canDeactivate(): boolean {
    return this.isActionPerformed;
  }

  ngOnDestroy() {
    // prevent memory leak when component destroyed
//    this.subscription.unsubscribe();
  }

  changeShowStatus() {
    // this.showHide = !this.showHide;
    this.isActionPerformed = true;
    this.router.navigate([addinternalPackaging]);
  }

  viewById(event) {
    // alert("==eventparam==>"+JSON.stringify(event));
    this.isActionPerformed = true;
    this.internalPackingService.viewbyIdParam = event;
    this.router.navigate([viewinternalPackaging]);
  }
  sortChange(sort: SortDescriptor[]): void {
        this.sort = sort;
        this.loadingPackList();
    }

   loadingPackList() {
     this.subscription = this.internalPackingService.getInternalPackingDetailsJSON(InternalPackagingModel).subscribe(intPackDetail => {
     this.internalPackingService.internalModel = intPackDetail.body;
      this.openEdit = false;
     this.loadIntPackDetails();
  if (this.iseditModal) {
         this.iseditModal = false;
     }
       if (this.deletesuccessModal) {
        setTimeout(() => { this.deletesuccessModal.hide(); }, 2000);
      }
     },
      error => alert(error),
      () => console.log('Finished')
      );
  }

  loadIntPackDetails(): void {
        this.gridView = {
            data: process(this.internalPackingService.internalModel, this.state).data,
            total: this.internalPackingService.internalModel.length
        };
        // alert("stringify in loaduom===>"+JSON.stringify(this.gridView));
    }

 edit() {
    this.openEdit = true;
  }

   onHideEditModal(): void {
    this.openEdit = false;
   }

    enableEdit() {
    this.editMode = false;
     this.isEdit = true;
  }

   pageChange(event: PageChangeEvent): void {
        this.state.skip = event.skip;
        this.loadingPackList();
    }

   public dataStateChange(state: DataStateChangeEvent): void {
         this.state = state;
        this.gridData = process(this.internalPackingService.internalModel, this.state);
    }


    
  /*allData(): ExcelExportData {
    const result: ExcelExportData = {
      data: process(this.internalPackingService.internalModel, this.state).data,
      group: this.group
    };

    return result;

  }*/

 /*  delete() {
    const index = this.findSelectedUOMIndex();
    this.InternalPackingService.internalModel = this.InternalPackingService.internalModel.filter((val, i) => i !== index);
  }
    findSelectedUOMIndex(): number {
    return this.InternalPackingService.internalModel.indexOf(this.selectedPackingrow);
    }*/


  checkAllClicked(e) {
   // console.log('checkAllClicked', e);
   if (e.target.checked) {
      this.allItemsChecked = true;
      for (let i = 0; i < this.internalPackingService.internalModel.length; i++) {
        this.internalPackingService.internalModel[i].checked = true;
      }
    } else {
      this.allItemsChecked = false;
      for (let i = 0; i < this.internalPackingService.internalModel.length; i++) {
        this.internalPackingService.internalModel[i].checked = false;
      }
    }
  }
  selectUnSelectAllChecked(e) {
    if (!e.target.checked) {
      this.allItemsChecked = false;
    } else {
      let selected;
      selected = 0;
      for (let i = 0; i < this.internalPackingService.internalModel.length; i++) {
        if (!this.internalPackingService.internalModel[i].checked) {
          this.allItemsChecked = false;
          break;
        } else {
          selected++;
        }
      }
      if (selected === this.internalPackingService.internalModel.length) {
        this.allItemsChecked = true;
      }
    }
  //  console.log(this.allItemsChecked);
  }

  validateNumericsOnly(keyEvent) {
       const result = (keyEvent.which) ? keyEvent.which : keyEvent.keyCode;
       if (result === 110 || result === 190) {
              return true;
          }else if (result === 17 || result === 67 || result === 86) {
           alert('CTRL + C or CTRL + V are not allowed');
           return false;
          }else if (result !== 46 && result > 31
            && (result < 48 || result > 57)) {
             alert('Enter only numeric and Decimals');
             return false;
            }
          return true;
  }

getDropDownField() {
   this.subscription = this.unitMeasurementService.getUnitMeasurementJSON('/uomservice/uom').subscribe(data => {
      this.loadingData = true;
      this.internalPacking.uombaseOption = data.body;
      console.log( ' dropdownfield ' + this.internalPacking.uombaseOption);
    }, error => {
      this.loadingData = false;

    });
  }
  /* editRow(index) {
    this.getDropDownField();
    //this.openEdit = true;
    this.openEdit = true;
    this.internalPacking = this.primarypacking[index];
   }*/
    editinternal(event) {
    //  debugger;
   //  this.getDropDownField();
     // this.iseditModal = true;
      this.openEdit = true;
      this.CodeDropDown = [];
      this.isActionPerformed = false;
      // console.log(event.internalPackingUomCode.target.value);
      this.internaledit = Object.assign({}, event);
       console.log(event);

      if (this.internaledit.status === 'Draft') {
        this.draft = true;
        this.active = false;
        this.inactive = false;
      }else if (this.internaledit.status === 'Active') {
        this.active = true;
        this.draft = false;
        this.inactive = false;
      }else if (this.internaledit.status === 'InActive') {
        this.inactive = true;
        this.active = false;
        this.draft = false;
      }

    }
closedeletepopup() {
   this.SuccessModal.hide();
 }

   onHidden() {
   this.deleteSuccessModal = false;
  }

  onHiddenpopup() {
   this.isDeletePopupModal = false;
  // this.iseditPopupModal = false;
  }

  close() {
  // this.iseditModal = false;
     this.SuccessModal.hide();
     this.openEdit = true;
  // this.Edit.hide();
    // this.UomCodeDropDown.pop();
  }



  closeEditModal($event) {
    // alert('Inside Edit Modal');
    this.editModal.hide();
   // this.loadingPackList();
     this.isActionPerformed = true;
    this.message = $event['msg'];
    if (this.message !== '' && this.message !== undefined && this.message !== null) {
      this.loadingPackList();
      this.UpdateModal = true;
       setTimeout(() => {
        this.UpdateModal = false;
      }, 1000);
    }
  }

  editModalRemove() {
    this.editModal.hide();
    this.isActionPerformed = true;

  }


internal_delete(keyid, primaryPacking, internalPackingTypeName, status) {
  this.PrimaryPacking = primaryPacking;
  this.internalPackingTypeName = internalPackingTypeName;
  this.PackingID = keyid;
  this.status = status;
 // alert(keyid);
// console.log(this.PrimaryPacking);
// alert(1123);

    if (status === 'Active') {
      this.deactivateSuccessDialog = 'Are you want DeActivate Packing Type Code ' + internalPackingTypeName + ' ?';
    }
    if (status === 'Draft') {
      this.deactivateSuccessDialog = 'Are you want Delete Packing Type Code  ' + internalPackingTypeName + ' ?';
    }
    if (status === 'InActive') {
      this.deactivateSuccessDialog = 'Are you want Re-Activate Packing Type Code  ' + internalPackingTypeName + ' ?';
    }
    this.isDeletePopupModal = true;

  }

  conf_delete() {
    this.subscription = this.internalPackingService.deletepackingid(this.PackingID)
      .subscribe(deletedStatus => {
       this.SuccessModal.hide();
      if (this.status === 'Active') {
          this.deactivateSuccessDialog = deletedStatus.body;
        } else if (this.status === 'InActive') {
          this.deactivateSuccessDialog = deletedStatus.body;
       } else if (this.status === 'Draft') {
          this.deactivateSuccessDialog = deletedStatus.body;
        }
         this.deleteSuccessModal = true;
        this.loadingPackList();
        },
      error => { throw error });
  }


 onAddBtnClick() {
    this.showHide = !this.showHide;
  }

  errorHandler(errorMessage) {
    this.deleteSuccessModal = false;
    this.editSuccessModal = false;
    this.isCannotDeletePopup = true;
}
clear () {
this.internalPacking = new InternalPackagingModel();

 // this.internalPacking.isBulk = '0';
 this.req_typeCode = false;
 // this.ngOnInit();
}
   updateInternal(CodeParam, action) {
    this.internaledit = CodeParam;
    this.isUpdate = true;
    this.internaledit.action = action;
     this.internaledit.internalPackingUomCode = ''; // this.internaledit.selectedUomId;
     this.internaledit.uomCode = this.internaledit.selectedUomId;
   // console.log(JSON.stringify(this.internaledit + 'test' + this.internaledit.selectedUomId));
    if (this.isValidForm()) {
      this.subscription = this.internalPackingService.updatePackingDetails(this.internaledit).subscribe(savedData => {
      this.savedData = savedData;
      this.deactivateSuccessDialog = savedData.body;
     if (savedData.body !== null && savedData.body !== '' ) {
      // this.reqmsg += 'Primary Packing Type Code,';
       alert(JSON.stringify(savedData.body));
        if ((savedData.body === 'Internal packing type code' + this.internaledit.internalPackingTypeCode + 'already exists')
        || (savedData.body === 'Internal packing type name' + this.internaledit.internalPackingTypeName + 'already exists')) {
        this.clear ();
        }else {
             // this.displayDialog = true;
          this.openEdit = false;
          this.successModal = true;
          this.loadingPackList();
        }
      }

      if (this.successModal) {
      //  setTimeout(() => { this.iseditModal = false; }, 20);
      }
      }, error => {
        console.log(error)
        this.openEdit = true;
      },
        () => console.log('Finished')
    );

   }
 }

getStyle() {
    if (this.req_ipcode === true) {
      return '#d43c3c';
    } else {
      return '#cfdee7';
    }

  }

  getStyle1() {
    if (this.req_ipname === true) {
      return '#d43c3c';
    } else {
      return '#cfdee7';
    }

  }

getStyle2() {
    if (this.req_ipuomcode === true) {
      return '#d43c3c';
    } else {
      return '#cfdee7';
    }

  }

  getStyle3() {
    if (this.req_ipweight === true) {
      return '#d43c3c';
    } else {
      return '#cfdee7';
    }

  }

  isValidForm(): Boolean {
    // alert(thisTypeCode);
       if ((this.internaledit.internalPackingTypeCode === null || this.internaledit.internalPackingTypeCode === undefined)
      && (this.internaledit.internalPackingTypeName === null || this.internaledit.internalPackingTypeName === undefined)
    && (this.internaledit.internalPackingWeight === null || this.internaledit.internalPackingWeight === undefined)
    && (this.internaledit.internalPackingUomCode === null || this.internaledit.internalPackingUomCode === undefined)) {
        this.req_ipcode = true;
        this.req_ipname = true;
        this.req_ipuomcode = true;
        this.req_ipweight = true;
        this.reqmsg += 'Please enter internaledit Packing Type Code,  Packing Type Name, Packing Weight,  Packing UOM Code';
        this.req_typeCode = true;
        return false;
      } else if ( (this.internaledit.internalPackingTypeCode !== null || this.internaledit.internalPackingTypeCode !== undefined)
      && (this.internaledit.internalPackingTypeName === null || this.internaledit.internalPackingTypeName === undefined)
    && (this.internaledit.internalPackingWeight === null || this.internaledit.internalPackingWeight === undefined)
    && (this.internaledit.internalPackingUomCode === null || this.internaledit.internalPackingUomCode === undefined)) {
        this.req_ipcode = false;
        this.req_ipname = true;
        this.req_ipuomcode = true;
        this.req_ipweight = true;
        this.reqmsg += 'Please enter PrimaryPacking Packing Type Name,PrimaryPacking Packing Weight, PrimaryPacking Packing UOM Code'
        this.req_typeCode = true;
        return false;
      }   else if ( (this.internaledit.internalPackingTypeCode === null || this.internaledit.internalPackingTypeCode === undefined)
      && (this.internaledit.internalPackingTypeName !== null || this.internaledit.internalPackingTypeName !== undefined)
   && (this.internaledit.internalPackingWeight === null || this.internaledit.internalPackingWeight === undefined)
    && (this.internaledit.internalPackingUomCode === null || this.internaledit.internalPackingUomCode === undefined)) {
        this.req_ipcode = true;
        this.req_ipname = false;
        this.req_ipuomcode = true;
        this.req_ipweight = true;
        this.reqmsg += 'Please enter PrimaryPacking Packing Type Code, PrimaryPacking Packing Weight, PrimaryPacking Packing UOM Code'
        this.req_typeCode = true;
        return false;
      }  else if ( (this.internaledit.internalPackingTypeCode === null || this.internaledit.internalPackingTypeCode === undefined)
      && (this.internaledit.internalPackingTypeName === null || this.internaledit.internalPackingTypeName === undefined)
    && (this.internaledit.internalPackingWeight !== null || this.internaledit.internalPackingWeight !== undefined)
    && (this.internaledit.internalPackingUomCode === null || this.internaledit.internalPackingUomCode === undefined)) {
      this.req_ipcode = true;
      this.req_ipname = true;
      this.req_ipuomcode = false;
      this.req_ipweight = true;
      this.reqmsg += 'Please enter PrimaryPacking Packing Type Code, PrimaryPacking Packing Type Name, PrimaryPacking Packing UOM Code'
        this.req_typeCode = true;
      return false;
    }    else if ( (this.internaledit.internalPackingTypeCode === null || this.internaledit.internalPackingTypeCode === undefined)
      && (this.internaledit.internalPackingTypeName === null || this.internaledit.internalPackingTypeName === undefined)
   && (this.internaledit.internalPackingWeight === null || this.internaledit.internalPackingWeight === undefined)
    && (this.internaledit.internalPackingUomCode !== null || this.internaledit.internalPackingUomCode !== undefined)) {
      this.req_ipcode = true;
      this.req_ipname = true;
      this.req_ipuomcode = true;
      this.req_ipweight = false;
      this.reqmsg += 'Please enter PrimaryPacking Packing Type Code, PrimaryPacking Packing Type Name,PrimaryPacking Packing Weight'
      this.req_typeCode = true;
      return false;
    }   else if ( (this.internaledit.internalPackingTypeCode !== null || this.internaledit.internalPackingTypeCode !== undefined)
      && (this.internaledit.internalPackingTypeName !== null || this.internaledit.internalPackingTypeName !== undefined)
     && (this.internaledit.internalPackingWeight === null || this.internaledit.internalPackingWeight === undefined)
    && (this.internaledit.internalPackingUomCode === null || this.internaledit.internalPackingUomCode === undefined)) {
      this.req_ipcode = false;
      this.req_ipname = false;
      this.req_ipuomcode = true;
      this.req_ipweight = true;
      this.reqmsg += 'Please enter PrimaryPacking Packing Weight, PrimaryPacking Packing UOM Code'
        this.req_typeCode = true;
      return false;
    }	else if ( (this.internaledit.internalPackingTypeCode !== null || this.internaledit.internalPackingTypeCode !== undefined)
      && (this.internaledit.internalPackingTypeName === null || this.internaledit.internalPackingTypeName === undefined)
    && (this.internaledit.internalPackingWeight !== null || this.internaledit.internalPackingWeight !== undefined)
    && (this.internaledit.internalPackingUomCode === null || this.PrimaryPacking.internalPackingUomCode === undefined))  {
      this.req_ipcode = false;
      this.req_ipname = true;
      this.req_ipuomcode = false;
      this.req_ipweight = true;
      this.reqmsg += 'Please enter  PrimaryPacking Packing Type Name, PrimaryPacking Packing UOM Code'
        this.req_typeCode = true;
      return false;
    }    else if ( (this.internaledit.internalPackingTypeCode === null || this.internaledit.internalPackingTypeCode === undefined)
      && (this.internaledit.internalPackingTypeName !== null || this.internaledit.internalPackingTypeName !== undefined)
   && (this.internaledit.internalPackingWeight !== null || this.internaledit.internalPackingWeight !== undefined)
    && (this.internaledit.internalPackingUomCode === null || this.internaledit.internalPackingUomCode === undefined)) {
      this.req_ipcode = true;
      this.req_ipname = false;
      this.req_ipuomcode = false;
      this.req_ipweight = true;
      this.reqmsg += 'Please enter  PrimaryPacking Packing Type Name,PrimaryPacking Packing Weight'
        this.req_typeCode = true;
      return false;
    }    else if ( (this.internaledit.internalPackingTypeCode === null || this.internaledit.internalPackingTypeCode === undefined)
      && (this.internaledit.internalPackingTypeName === null || this.internaledit.internalPackingTypeName === undefined)
    && (this.internaledit.internalPackingWeight !== null || this.internaledit.internalPackingWeight !== undefined)
    && (this.internaledit.internalPackingUomCode !== null || this.internaledit.internalPackingUomCode !== undefined))  {
      this.req_ipcode = true;
      this.req_ipname = true;
      this.req_ipuomcode = false;
      this.req_ipweight = false;
      this.reqmsg += 'Please enter PrimaryPacking Packing Type Code, PrimaryPacking Packing Type Name'
        this.req_typeCode = true;
      return false;
    }	else if ( (this.internaledit.internalPackingTypeCode === null || this.internaledit.internalPackingTypeCode === undefined)
      && (this.internaledit.internalPackingTypeName !== null || this.internaledit.internalPackingTypeName !== undefined)
    && (this.internaledit.internalPackingWeight === null || this.internaledit.internalPackingWeight === undefined)
    && (this.internaledit.internalPackingUomCode !== null || this.internaledit.internalPackingUomCode !== undefined)) {
      this.req_ipcode = true;
      this.req_ipname = false;
      this.req_ipuomcode = true;
      this.req_ipweight = false;
      this.reqmsg += 'Please enter PrimaryPacking Packing Type Code, PrimaryPacking Packing Weight'
      this.req_typeCode = true;
      return false;
    }      else if ( (this.internaledit.internalPackingTypeCode !== null || this.internaledit.internalPackingTypeCode !== undefined)
      && (this.internaledit.internalPackingTypeName !== null || this.internaledit.internalPackingTypeName !== undefined)
    && (this.internaledit.internalPackingWeight !== null || this.internaledit.internalPackingWeight !== undefined)
    && (this.internaledit.internalPackingUomCode === null || this.internaledit.internalPackingUomCode === undefined)) {
      this.req_ipcode = false;
      this.req_ipname = false;
      this.req_ipuomcode = false;
      this.req_ipweight = true;
      this.reqmsg += 'Please enter  PrimaryPacking Packing UOM Code'
        this.req_typeCode = true;
      return false;
    }	 else if ( (this.internaledit.internalPackingTypeCode === null || this.internaledit.internalPackingTypeCode === undefined)
      && (this.internaledit.internalPackingTypeName !== null || this.internaledit.internalPackingTypeName !== undefined)
   && (this.internaledit.internalPackingWeight !== null || this.internaledit.internalPackingWeight !== undefined)
    && (this.internaledit.internalPackingUomCode !== null || this.internaledit.internalPackingUomCode !== undefined)) {
      this.req_ipcode = true;
      this.req_ipname = false;
      this.req_ipuomcode = false;
      this.req_ipweight = false;
      this.reqmsg += 'Please enter PrimaryPacking Packing Type Code'
        this.req_typeCode = true;
      return false;
    }	 else if ( (this.internaledit.internalPackingTypeCode !== null || this.internaledit.internalPackingTypeCode !== undefined)
      && (this.internaledit.internalPackingTypeName === null || this.internaledit.internalPackingTypeName === undefined)
    && (this.internaledit.internalPackingWeight !== null || this.internaledit.internalPackingWeight !== undefined)
    && (this.internaledit.internalPackingUomCode !== null || this.internaledit.internalPackingUomCode !== undefined)) {
      this.req_ipcode = false;
      this.req_ipname = true;
      this.req_ipuomcode = false;
      this.req_ipweight = false;
      this.reqmsg += ' Please enter PrimaryPacking Packing Type Name'
        this.req_typeCode = true;
      return false;
    }	else if ( (this.internaledit.internalPackingTypeCode !== null || this.internaledit.internalPackingTypeCode !== undefined)
      && (this.internaledit.internalPackingTypeName !== null || this.internaledit.internalPackingTypeName !== undefined)
   && (this.internaledit.internalPackingWeight === null || this.internaledit.internalPackingWeight === undefined)
    && (this.internaledit.internalPackingUomCode !== null || this.internaledit.internalPackingUomCode !== undefined)) {
      this.req_ipcode = false;
      this.req_ipname = false;
      this.req_ipuomcode = true;
      this.req_ipweight = false;
      this.reqmsg += 'Please enter PrimaryPacking Packing Weight'
        this.req_typeCode = true;
      return false;
    }	else if ( (this.internaledit.internalPackingTypeCode !== null || this.internaledit.internalPackingTypeCode !== undefined)
      && (this.internaledit.internalPackingTypeName !== null || this.internaledit.internalPackingTypeName !== undefined)
    && (this.internaledit.internalPackingWeight !== null || this.internaledit.internalPackingWeight !== undefined)
    && (this.internaledit.internalPackingUomCode !== null || this.internaledit.internalPackingUomCode !== undefined))  {
      this.req_ipcode = false;
      this.req_ipname = false;
      this.req_ipuomcode = false;
      this.req_ipweight = false;

      return true;
    }    else {
      this.req_ipcode = true;
      this.req_ipname = true;
      this.req_ipuomcode = true;
      this.req_ipweight = true;
      return true;
    }
  }

}
