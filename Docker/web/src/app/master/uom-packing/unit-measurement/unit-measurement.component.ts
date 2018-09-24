import { Component, OnInit, ContentChild, ViewChild, OnDestroy, Output, ViewEncapsulation, Inject   } from '@angular/core';
import { PlatformLocation, Location} from '@angular/common';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Subscription } from 'rxjs/Subscription';
import { Router } from '@angular/router';
import { UnitMeasurement } from './unit-measurement';
import { UnitMeasurementService } from './service/unit-measurement.service';
import { MasterSetupService } from '../../master-setup/service/master-setup.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { SelectItem } from '../../../shared/interface/selectItem';
import { ResponseData } from '../../../shared/interface/responseData';
import { Link } from '../../../shared/interface/link';
import { addUom } from '../../../shared/interface/router-links';
import { viewUom } from '../../../shared/interface/router-links';
import { CanComponentDeactivate } from '../../../shared/guards/can-deactivate-guard'
import { uom } from '../../../shared/interface/router-links';
import { master } from '../../../shared/interface/router-links';
import { ExcelExportData } from '@progress/kendo-angular-excel-export';
import { GridComponent, GridDataResult, DataStateChangeEvent, PageChangeEvent} from '@progress/kendo-angular-grid';
import { process, SortDescriptor, orderBy, GroupDescriptor, State } from '@progress/kendo-data-query';
import { Observable } from 'rxjs/Rx';
import { ModalDirective } from 'ngx-bootstrap/modal';
@Component({
  selector: 'app-unit-measurement',
  templateUrl: './unit-measurement.component.html',
  styleUrls: ['./unit-measurement.component.css'],
  encapsulation: ViewEncapsulation.None,
 })
export class UnitMeasurementComponent implements OnInit, OnDestroy, CanComponentDeactivate {
 @ViewChild('duplicate') public duplicate: ModalDirective;
  @ViewChild('filebrowse') public filebrowse: ModalDirective;
  @ViewChild('deletesuccessModal') public deletesuccessModal: ModalDirective;
  @ContentChild('temp') testEl: any;
  // addUomDetail: UnitMeasurement[];
  public allItemsChecked: Boolean = false;
  showHide: Boolean;
  subscription: Subscription;
  isComplete: Boolean = false;
  uomData: UnitMeasurement = new UnitMeasurement();
  selectedUOMrow: UnitMeasurement;
  UomCodeDropDown: SelectItem[]= [];
  nonDuplicateArray = [];
  counter: number;
  displayDialog: boolean;
  public success;
  private uomCode;
  public uomName;
  public isShowModal;
  public iseditModal;
  public uomedit;
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
  public statusmsg;
  public errorMessage;
  deleteSuccessModal: boolean;
  platformlocation: PlatformLocation;
  location: Location;
  req_uomname: boolean;
  req_uomcode: boolean;
  isNavbtwComponent: boolean;
  isActionPerformed = false;
  private link: Link;
  group: GroupDescriptor[] = [{ field: 'statusName' }];
  multiple: Boolean = false;
  gridView: GridDataResult;
  allowUnsort: Boolean = true;
  sort: SortDescriptor[] = [];
  deleteStatus: string;
  data: UnitMeasurement[];
  sizes: any;
  state: State = {
        skip: 0,
        take: 1000
    };
  public errormodal: boolean;
  public formGroup: FormGroup;
  private editedRowIndex: number;
  public view: Observable<GridDataResult>;

  public UomIdMap: Map<string, number> = new Map<string, number>();
  @Output() loadingData: Boolean = true;
   masters: ResponseData;

 gridData: GridDataResult;

  constructor(private route: ActivatedRoute, http: Http, private router: Router ,
              public unitMeasurementService: UnitMeasurementService,
              public masterSetupService: MasterSetupService,
              platformlocation: PlatformLocation, location: Location
                             ) {
     this.showHide = true;
     this.allData = this.allData.bind(this);
     platformlocation.onPopState(( ) => {

                this.isNavbtwComponent = true;

                // this.router.navigate(['addUom'], { queryParams: {}});
                // this.location.go('addUom');
                // this.route.queryParams = [];
     });
  }

   getMasterScreen() {
    // alert('in uom');
   }

  changeShowStatus() {
    // this.showHide = !this.showHide;
    this.router.navigate([addUom]);
  }

ngOnInit() {
  window.scrollTo(0, 0);
  this.errormodal = false;
   this.uomedit = {'uomCode': '', 'uomId': '', 'uomName': '', 'uomFullname': '',
      'parentUomId': '', 'uomBasis': '', 'uomConversionFactor': '', 'statusName': ''};
    this.loadingUOMList();
    if (this.success === 1) {
          this.isShowModal = true;
           this.statusmsg = ' Draft'
          setTimeout(() => {this.isShowModal = false; } , 3000);
        }
        if (this.success === 2) {
          this.isShowModal = true;
          this.statusmsg = ' Active'
          setTimeout(() => {this.isShowModal = false; } , 3000);
        }
        if (this.success === 3) {
          this.isShowModal = true;
           this.statusmsg = ' InActive'
          setTimeout(() => {this.isShowModal = false; } , 3000);
        }
    this.route.queryParams.subscribe(params => {
        // Defaults to 0 if no query param provided.
        this.uomCode = this.unitMeasurementService.uomCodeParam;
        this.success = +params['success'];
        // alert("==uomCodeIn unit==>"+this.uomCode);
        if ( this.success === 1) {
          if ( !this.isNavbtwComponent ) {
             this.isShowModal = true;
             this.router.navigate([uom]);
          } else {
             this.router.navigate([master]);
          }
           setTimeout(() => {this.isShowModal = false; }, 3000);
        }
      });
      this.deleteSuccessModal = false;
      this.isDeletePopupModal = false;
      this.isCannotDeletePopup = false;
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


  delete() {
    const index = this.findSelectedUOMIndex();
    this.unitMeasurementService.uomDetails = this.unitMeasurementService.uomDetails.filter((val, i) => i !== index);
  }

  findSelectedUOMIndex(): number {
    return this.unitMeasurementService.uomDetails.indexOf(this.selectedUOMrow);
  }


  loadingUOMList() {
     /* const link = this.masterSetupService.getChildObject('View Uom.GET');
      this.subscription = this.unitMeasurementService.getUnitMeasurementJSON(link.href).subscribe(addUomDetail => {
      this.unitMeasurementService.uomDetails = addUomDetail.body;
      // alert("===this.unitMeasurementService.uomDetails==>"+JSON.stringify(this.unitMeasurementService.uomDetails));
      this.loaduomDetails();
      this.unitMeasurementService.addChildObjects(addUomDetail.links);
     },
      error => alert(error),
      () => console.log('Finished')
      );*/

     // tslint:disable-next-line:max-line-length
     this.subscription = this.unitMeasurementService.getUnitMeasurementJSON('/reference/uomservice/uom').subscribe(addUomDetail => {
      this.unitMeasurementService.uomDetails = addUomDetail.body;
      this.data = addUomDetail.body;
      // alert('===this.unitMeasurementService.uomDetails==>' + JSON.stringify(this.unitMeasurementService.uomDetails));
      this.loaduomDetails(this.data);
      this.unitMeasurementService.addChildObjects(addUomDetail.links);
     },
      error => alert(error),
      () => console.log('Finished')
      );
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

  onAddBtnClick() {
    this.showHide = !this.showHide;
  }

sortChange(sort: SortDescriptor[]): void {
        this.sort = sort;
        this.loadingUOMList();
    }

  modalPopup(event) {
    // alert('popup');

  }


   loaduomDetails(param): void {
        // For virtual scrolling
        this.gridView = {
            data: param.slice(this.state.skip, this.state.skip + this.state.take),
            total: this.data.length
        };
        /** this.gridView = {
            data: process(this.unitMeasurementService.uomDetails, this.state).data,
            total: this.unitMeasurementService.uomDetails.length
        };**/

        // alert("stringify in loaduom===>"+JSON.stringify(this.gridView));
    }


    dataStateChange(state: DataStateChangeEvent): void {
         this.state = state;
         this.gridData = process(this.unitMeasurementService.uomDetails, this.state);
    }


   pageChange(event: PageChangeEvent): void {
      this.loadingUOMList();
    }

   allData(): ExcelExportData {
    const result: ExcelExportData = {
      data: process(this.unitMeasurementService.uomDetails, this.state).data,
      group: this.group
    };

    return result;
  }

    /*edituom(event: any) {
      this.iseditModal = true;
      this.UomCodeDropDown = [];
      this.getDropDownList();
      this.uomedit.uomId = event.path[4].cells[0].innerText;
      this.uomedit.parentUomId = event.path[4].cells[1].innerText;
      this.uomedit.uomCode = event.path[4].cells[3].innerText;
      this.uomedit.uomName = event.path[4].cells[4].innerText;
      this.uomedit.uomFullname = event.path[4].cells[5].innerText;
      this.uomedit.uomConversionFactor = event.path[4].cells[7].innerText;
      this.uomedit.statusName = event.path[4].cells[8].innerText;
      if (this.uomedit.statusName === 'Draft') {
        this.draft = true;
        this.active = false;
        this.inactive = false;
      }else if (this.uomedit.statusName === 'Active') {
        this.active = true;
        this.draft = false;
        this.inactive = false;
      }else if (this.uomedit.statusName === 'InActive') {
        this.inactive = true;
        this.active = false;
        this.draft = false;
      }

         this.displayDialog = true;

    }*/

    edituom(event) {
      this.iseditModal = true;
      this.req_uomcode = false;
      this.req_uomname = false;
      this.UomCodeDropDown = [];
      this.uomedit.uomId = event.uomId;
      this.uomedit.parentUomId = event.parentUomId;
      this.uomedit.uomCode = event.uomCode;
      this.uomedit.uomName = event.uomName;
      this.uomedit.uomFullname = event.uomFullname;
      this.uomedit.uomBasis = event.uomBasis;
      this.uomedit.uomConversionFactor = event.uomConversionFactor;
      this.uomedit.statusName = event.statusName;
      if (this.uomedit.statusName === 'Draft') {
        this.draft = true;
        this.active = false;
        this.inactive = false;
      }else if (this.uomedit.statusName === 'Active') {
        this.active = true;
        this.draft = false;
        this.inactive = false;
      }else if (this.uomedit.statusName === 'InActive') {
        this.inactive = true;
        this.active = false;
        this.draft = false;
      }

        // alert("parentUomId==>"+JSON.stringify(this.uomedit.parentUomId));
         this.displayDialog = true;
        this.getDropDownList();

    }


  protected editHandler({sender, rowIndex, dataItem}) {
      //  alert('In editHandler');
        this.closeEditor(sender);
         this.formGroup = new FormGroup({
           'uomCode': new FormControl(dataItem.uomCode),
           'uomName': new FormControl(dataItem.uomName),
           'uomFullname': new FormControl(dataItem.uomFullname),
           'uomConversionFactor': new FormControl(dataItem.uomConversionFactor)
          });
          this.editedRowIndex = rowIndex;
          sender.editRow(rowIndex, this.formGroup);
  }

  private closeEditor(grid, rowIndex = this.editedRowIndex) {
        grid.closeRow(rowIndex);
        this.editedRowIndex = undefined;
        this.formGroup = undefined;
    }



conf_delete(id) {
     const param = {'uomId': this.del_id};
     this.subscription = this.unitMeasurementService.deleteUOMById(param).subscribe(deletedStatus => {
                           this.savedData = deletedStatus;
                           this.isDeletePopupModal = false;
                           this.deleteSuccessModal = true;
                           setTimeout(() => {this.deleteSuccessModal = false; }, 3000);
                           this.loadingUOMList();
                         
       },
         error => {
            // alert("==error==>"+error);
            // alert(errorMsg[1]);
           // this.errorMessage = error;
          // alert(this.unitMeasurementService.errorMessage);
            this.isDeletePopupModal = false;
            this.isCannotDeletePopup = true;
        },
         () => console.log('Finished')
    );
}


errorHandler(errorMessage) {
    this.deleteSuccessModal = false;
    this.isCannotDeletePopup = true;
}


 uom_delete(event, uomCodeParam, uomName, statusName) {
   this.del_id = event;
   this.status = statusName;
   if (this.status === 'Active') {
       this.isActiveStatus = true;
       this.deleteStatus = 'De-Activate';
   } else if (this.status === 'Draft') {
        this.isDraftStatus = true;
        this.deleteStatus = 'Delete';
   } else if (this.status === 'InActive') {
        this.deleteStatus = 'Activate';
   }

   this.uomCode = uomCodeParam;
   this.uomName = uomName;
   this.isDeletePopupModal = true;
 }



closedeletepopup() {
   this.isDeletePopupModal = false;
   this.isCannotDeletePopup = false;
 }

  close() {
    this.iseditModal = false;
    // this.UomCodeDropDown.pop();
  }

  getDropDownList() {
      // this.UomCodeDropDown.slice();shawdowed name
      this.unitMeasurementService.uomDetails.forEach( uomDropDown => {
       if (uomDropDown.statusName !== 'InActive' && uomDropDown.statusName !== 'Draft') {
              this.UomCodeDropDown.push({ label: uomDropDown.uomCode, value: uomDropDown.uomId });
        }
        // alert('==UomCodeDropDown==>' + JSON.stringify(this.UomCodeDropDown));
     });

  }

  isValidForm(): boolean {
   this.counter = 0;
    if (this.uomedit.uomCode == null) {
                this.req_uomcode = true;
      this.counter++;
      // this.errormodal=true;

    } else if ( this.uomedit.uomCode != null ) {
        if ((this.uomedit.uomCode.trim() === '') ) {
           this.req_uomcode = true;
        this.counter++;
        } else if ((this.uomedit.uomCode.trim().length > 20)) {
        this.req_uomcode = true;
        this.counter++;
      // this.reqmsg1 = 'Length of Uom Code should be less than 20';
      // this.errormodal=true;

    } else {
      this.req_uomcode = false;
    }

    }
    if ( this.uomedit.uomName == null) {
                this.req_uomname = true;
      this.counter++;
   //   this.reqmsg2 = 'Enter Uom Name';
     // this.errormodal=true;

    } else if (this.uomedit.uomName.trim() != null ) {
        if (this.uomedit.uomName.trim() === '') {
        this.req_uomname = true;
        this.counter++;
     //   this.reqmsg2 = 'Enter Uom Name';
      // this.errormodal=true;

      }   else if ((this.uomedit.uomName.trim().length > 200)) {

          this.req_uomname = true;
          this.counter++;
         // this.reqmsg2 = 'Length of Uom Name should be less than 200';
        // this.errormodal=true;

      }else {
      this.req_uomname = false;
    }
    }
   if (this.counter !== 0) {
        this.showModal();

         setTimeout(() => {this.hideModal(); }, 3000);

                return false;
       } else {
               return true;
           }


}
public showModal(): void {
    this.errormodal = true;
  }
 
  public hideModal(): void {
    this.deletesuccessModal.hide();
  }
 
  public onHidden(): void {
    this.errormodal = false;
  }

  viewById(event) {
    // alert("==eventparam==>"+JSON.stringify(event));
    this.unitMeasurementService.viewbyIdParam = event;
    this.router.navigate([viewUom]);
  }

  getStyle1() {
    if (this.req_uomcode === true) {
      return '#d43c3c';
    } else {
      return '#cfdee7';
    }
  }
  getstyle2() {
     if (this.req_uomname === true) {
      return '#d43c3c';
    } else {
      return '#cfdee7';
    }
  }
  updateUOM(uomCodeParam) {
    this.uomCode = uomCodeParam;
    this.isUpdate = true;
    if (this.isValidForm()) {
      this.subscription = this.unitMeasurementService.saveUOM(this.uomedit).subscribe(savedData => {
      this.savedData = savedData;
      this.displayDialog = false;
      this.iseditModal = false;
      this.isupdateModal = true;
          setTimeout(() => {this.isupdateModal = false; }, 3000);
         this.loadingUOMList();
      },
      error => alert(error),
      () => console.log('Finished')

    );

   }
 }

  updateUOMwithStatus(uomCodeParam, statusName) {
    this.uomCode = uomCodeParam;
    if (this.isValidForm()) {
     if (this.uomedit.statusName === 'Draft') {
        this.uomedit.statusName = 'Active';
        if (statusName === 'Active') {
            this.isActivated = true;
        }
     }else if (this.uomedit.statusName === 'Active') {
         this.uomedit.statusName = 'InActive';
          if (statusName === 'InActive') {
            this.isInActivated = true;
        }
     }else if (this.uomedit.statusName = 'InActive') {
       // alert('12');
        this.uomedit.statusName = 'Active';
        if (statusName === 'Active') {
            this.isActivated = true;
        }
     }
     this.subscription = this.unitMeasurementService.saveUOM(this.uomedit).subscribe(savedwithStatus => {
     this.savedData = savedwithStatus;
     this.displayDialog = false;
      this.iseditModal = false;
      this.isupdateModal = true;
      setTimeout(() => {this.isupdateModal = false; }, 3000);
      this.loadingUOMList();
      },
      error => alert(error),
      () => console.log('Finished')
    );

    }
  }

  canDeactivate(): boolean {
    return this.isActionPerformed;
  }

  ngOnDestroy() {
    // prevent memory leak when component destroyed
    this.subscription.unsubscribe();
  }


}

