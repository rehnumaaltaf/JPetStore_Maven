import { Component, OnInit, OnDestroy, Output, ViewChild, ViewEncapsulation, OnChanges, Inject, ElementRef  } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Subscription } from 'rxjs/Subscription';
import { Router } from '@angular/router';
import { ExcelExportData } from '@progress/kendo-angular-excel-export';
import { GridComponent, GridDataResult, DataStateChangeEvent, PageChangeEvent} from '@progress/kendo-angular-grid';
import { process, SortDescriptor, orderBy, GroupDescriptor, State } from '@progress/kendo-data-query';
import { Observable } from 'rxjs/Rx';
import { ShipmentMonthService } from './service/shipment-month.service';
import { ActivatedRoute } from '@angular/router';
import { Link } from '../../../shared/interface/link';
import { ResponseData } from '../../../shared/interface/responseData';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { SelectItem } from '../../../shared/interface/selectItem';
import { DropdownModule } from 'primeng/primeng';
import { ButtonModule } from 'primeng/primeng';
import { ConfirmDialogModule, ConfirmationService } from 'primeng/primeng';
import { ModalModule , AccordionModule } from 'ngx-bootstrap';
import { ModalDirective } from 'ngx-bootstrap/modal';
import { master } from '../../../shared/interface/router-links';
import { shipmentmonth } from '../../../shared/interface/router-links';
import { MasterSetupService } from '../../master-setup/service/master-setup.service';
import { ShipmentWeek, ShipmentRule, } from '../shipment-month/shipment-month-interface';
import { CanComponentDeactivate } from '../../../shared/guards/can-deactivate-guard';


@Component({
  selector: 'app-shipment-month',
  templateUrl: './shipment-month.component.html',
  styleUrls: ['./shipment-month.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class ShipmentMonthComponent implements OnInit , CanComponentDeactivate  {
  @ViewChild ('weekdded') public weekdded: ModalDirective;
  @ViewChild ('weekedit') public weekedit: ModalDirective;
  @ViewChild ('weekdelete') public weekdelete: ModalDirective;
  @ViewChild ('confirmdel') public confirmdel: ModalDirective;
  @ViewChild('confirmdels') public confirmdels: ModalDirective;
  @ViewChild ('errormsg') public errormsg: ModalDirective;
  @ViewChild('deletesuccessModal') public deletesuccessModal: ModalDirective;
  @ViewChild('successModal') public successModal: ModalDirective;
  @ViewChild('successModals') public successModals: ModalDirective;
  @ViewChild('Validation') public Validation: ModalDirective;
  @ViewChild('deletesuccessModals') public deletesuccessModals: ModalDirective;
  @ViewChild('pagebackModal') public pagebackModal: ModalDirective;
  public errormodals = false;

public view: Observable<GridDataResult>;
  public errorMessage: any;
  public mode = false;
  public allowUnsort = true;
  public allItemsChecked = false;
  shipmentData: ShipmentWeek;
  public formGroup: FormGroup;
  private editedRowIndex: number;
  public deleteStatus: String;
  gridView: GridDataResult;
  subscription: Subscription;
  private isNew = false;
  isValid: Boolean = true;
  private editedShipmentData: ShipmentWeek;
  Products: SelectItem[];
  PrdtDTOarr: Array<String> = [];
  state: State = {
        skip: 0,
        take: 1000
    };
    gridData: GridDataResult;
    group: GroupDescriptor[] = [{ field: 'statusName' }];
    sort: SortDescriptor[] = [];
    isDeletePopupModal: boolean;
    deleteSuccessModal: boolean;
    isDeletePopupModals: boolean;
    isCannotDeletePopup: boolean;
    reqShipmentWeekCode: Boolean = false;
    reqShipmentWeekName: Boolean = false;
    reqShipmentRuleName: Boolean = false;
    public submit = false;
    public edit = false;
    public create = false;
    public draft = false;
    public active = false;
    public inactive = false;
    public del_id;
    public savedData;
    public status;
    isActiveStatus: boolean;
    isDraftStatus: boolean;
    errorMessageforshipment: String;
    private shipmentTimeframeCode;
    public shipmentCode;
    public shipmentName;
    counter: number;
    errormodal: boolean;
    statusmsg: string;
    public statusDropDown;
    private shipmentTimeframeName;
    public value: any= [];
    sender:any;
    rowindex:any;
    shipmentRuleList: ShipmentRule[] = [];
    isActionPerformed = true;
  constructor(private router: Router, public shipmentMonthService: ShipmentMonthService, private masterSetupService: MasterSetupService) {

    this.allData = this.allData.bind(this);

  }

  ngOnInit() {

    this.loadingShipmentWeekList();
    this.getShipmentRuleList();
    this.draft = true;
    this.active = true;
    this.inactive = true;
    this.isActionPerformed = true;
  }

  loadingShipmentWeekList() {

      this.subscription = this.shipmentMonthService.getShipmentMonthJSON().subscribe(shipmentDetail => {
      this.shipmentMonthService.shipmentdetails  = shipmentDetail.body;
      for ( let i = 0; i < this.shipmentMonthService.shipmentdetails.length ; i++ ) {
         this.shipmentMonthService.shipmentdetails[i].shipmentRule = {
            shipmentRuleId:  +this.shipmentMonthService.shipmentdetails[i].shipmentRuleId,
            shipmentRuleName: this.shipmentMonthService.shipmentdetails[i].shipmentRuleName
            }
      }

       this.loadshipmentDetails();
       // this.isActionPerformed = true;
       },
      error => alert(error),
      () => console.log('Finished')
      );
  }

 getShipmentRuleList() {
     this.subscription = this.shipmentMonthService.getRulesDetailsJSON().subscribe(shipmentDetail => {
         this.shipmentRuleList  = shipmentDetail.body;

    },
    error => { throw error}
    );
  }

checkAllClicked(e) {

        if (e.target.checked) {
            this.allItemsChecked = true;
            for (let i = 0; i < this.shipmentMonthService.shipmentdetails.length; i++) {
                this.shipmentMonthService.shipmentdetails[i].checked = true;
            }
        } else {
            this.allItemsChecked = false;
            for (let i = 0; i < this.shipmentMonthService.shipmentdetails.length; i++) {
                this.shipmentMonthService.shipmentdetails[i].checked = false;
            }
        }
  }


selectUnSelectAllChecked(e) {

      if (!e.target.checked) {
        this.allItemsChecked = false;
      } else  {
         let selected;
          selected = 0;
          for (let i = 0; i < this.shipmentMonthService.shipmentdetails.length; i++) {
              if ( !this.shipmentMonthService.shipmentdetails[i].checked ) {
                this.allItemsChecked = false;
                break;
              } else {
                selected++;
              }
          }
          if ( selected === this.shipmentMonthService.shipmentdetails.length ) {
            this.allItemsChecked = true;
          }
      }
      console.log( this.allItemsChecked );
   }

 public addHandler({sender}) {
    if (!this.isActionPerformed) {
        return;
        // call confirmation box-
        // on reject return //return;
        // if accept do nothing
      }
        this.isActionPerformed = false;
        this.submit = true;
        this.active = true;
        this.draft = true;
        this.inactive = true;
        this.edit = true;
        this.create = false;
        this.closeEditor(sender);
        sender.addRow(new ShipmentWeek());
  }


    public editHandler({sender, rowIndex, dataItem}) {
      if (!this.isActionPerformed) {
        return;
        // call confirmation box-
        // on reject return //return;
        // if accept do nothing
      }
      this.isActionPerformed = false;
      if (!dataItem.shipmentTimeframeCode) {
        this.reqShipmentWeekCode = true;
      } else {
         this.reqShipmentWeekCode = false;
      }
       if (!dataItem.shipmentTimeframeName) {
        this.reqShipmentWeekName = true;
      } else {
         this.reqShipmentWeekName = false;
      }
       if (!dataItem.shipmentRule) {
        this.reqShipmentRuleName = true;
      } else {
         this.reqShipmentRuleName = false;
      }
      if ( !dataItem.shipmentTimeframeCode || !dataItem.shipmentTimeframeName || !dataItem.shipmentRule) {
      this.errormodals = true;
      setTimeout(() => {this.deletesuccessModals.hide(); }, 3000);
      this.loadingShipmentWeekList();
      return false;
    }
      this.submit = false;
      this.edit = false;
      this.create = true;
      if (dataItem.statusName === 'Draft') {
        this.draft = false;
        this.active = true;
        this.inactive = true;
      } else if (dataItem.statusName === 'Active') {
        this.active = false;
        this.draft = true;
        this.inactive = true;
      } else if (dataItem.statusName === 'InActive') {
        this.inactive = false;
        this.draft = true;
        this.active = true;
      }
      this.sender=sender;
    //this.closeEditor(sender);
    this.editedRowIndex = rowIndex;
    this.editedShipmentData = Object.assign({}, dataItem);
    sender.editRow(rowIndex);
  }


    public saveHandler({sender, rowIndex, dataItem, isNew}) {

      // this.isActionPerformed = false;
      if (!dataItem.shipmentTimeframeCode) {
        this.reqShipmentWeekCode = true;
      } else {
         this.reqShipmentWeekCode = false;
      }
       if (!dataItem.shipmentTimeframeName) {
        this.reqShipmentWeekName = true;
      } else {
         this.reqShipmentWeekName = false;
      }
       if (!dataItem.shipmentRule) {
        this.reqShipmentRuleName = true;
      } else {
         this.reqShipmentRuleName = false;
      }
      if ( !dataItem.shipmentTimeframeCode || !dataItem.shipmentTimeframeName || !dataItem.shipmentRule) {
      this.errormodals = true;
      setTimeout(() => {this.deletesuccessModals.hide(); }, 3000);
      this.loadingShipmentWeekList();
      return false;
    }
    else{
      debugger;
         this.sender=sender;
         this.rowindex=rowIndex;
         //sender.closeRow(rowIndex);
         this.editedRowIndex = undefined;
         this.editedShipmentData = undefined;
    }
     }


    /*saveshipmentweek(dataItem) {
        this.isActionPerformed = false;
        this.subscription = this.shipmentMonthService.saveShipmentDetails(dataItem).subscribe(shipmentDetail => {
        this.shipmentMonthService.shipmentdetails.push(<ShipmentWeek>shipmentDetail);
        this.weekdded.show();
        // this.shipmentCode = dataItem.shipmentTimeframeCode;
        this.shipmentName = dataItem.shipmentTimeframeName;
        setTimeout(() => { this.weekdded.hide(); }, 3000);
        this.loadingShipmentWeekList();

        },
        error => {

          this.pagebackModal.show();
          this.errorMessageforshipment = this.shipmentMonthService.errorMessage;
          this.isActionPerformed = true;
        },
        () => console.log('Finished')
        );
    }*/

 close() {
    this.pagebackModal.hide();
    // this.showModal=false;
  }

    canDeactivate(): boolean {
    return this.isActionPerformed;
  }



  shipmentweekDraft(dataItem) {
    // this.isActionPerformed = false;
    this.counter = 0;
    if ( !dataItem.shipmentTimeframeCode || !dataItem.shipmentTimeframeName || !dataItem.shipmentRule) {
      
      return false;
    }

      dataItem.statusName = 'Draft';
      dataItem.shipmentRuleId = dataItem.shipmentRule.shipmentRuleId;
      this.shipmentData = dataItem;

        this.subscription = this.shipmentMonthService.saveShipmentDetails(dataItem).subscribe(shipmentDetail => {
        this.shipmentMonthService.shipmentdetails.push(<ShipmentWeek>shipmentDetail.shipmentdetails);
        this.weekdded.show();
        // this.shipmentCode = dataItem.shipmentTimeframeCode;
        this.shipmentName = dataItem.shipmentTimeframeName;
        setTimeout(() => { this.weekdded.hide(); }, 3000);
        this.loadingShipmentWeekList();
        var sender=this.sender;
        sender.closeRow(this.rowindex);

        },
        error => {
          // this.isActionPerformed = true;
          this.pagebackModal.show();
          this.errorMessageforshipment = this.shipmentMonthService.errorMessage;
           // this.errormsg.show();
        },
        () => console.log('Finished')
      );

  }


    shipmentweekActive(dataItem, isNew) {
      // this.isActionPerformed = false;
      if ( !dataItem.shipmentTimeframeCode || !dataItem.shipmentTimeframeName || !dataItem.shipmentRule) {
      return false;
    }
      dataItem.statusName = 'Active';
      dataItem.shipmentRuleId = dataItem.shipmentRule.shipmentRuleId;
      this.subscription = this.shipmentMonthService.saveShipmentDetails(dataItem).subscribe(shipmentDetail => {
      this.shipmentMonthService.shipmentdetails.push(<ShipmentWeek>shipmentDetail);
      this.weekdded.show();
      // this.shipmentCode = dataItem.shipmentTimeframeCode;
      this.shipmentName = dataItem.shipmentTimeframeName;
      setTimeout(() => { this.weekdded.hide(); }, 3000);
       var sender=this.sender;
       sender.closeRow(this.rowindex);
       this.loadingShipmentWeekList();
      },
        error => {
          // this.isActionPerformed = true;
          this.pagebackModal.show();
          this.errorMessageforshipment = this.shipmentMonthService.errorMessage;
       },
      () => console.log('Finished')
      );

    }


editshipmentweek(dataItem) {
// this.isActionPerformed = false;
 if ( !dataItem.shipmentTimeframeCode || !dataItem.shipmentTimeframeName || !dataItem.shipmentRule) {
      return false;
    }
  if (dataItem.statusName === 'Draft') {
      this.statusmsg = 'saved';
      // this.weekedit.show();
    } else if (dataItem.statusName === 'Active') {
      this.statusmsg = 'activated';
      // this.weekedit.show();
    } else if (dataItem.statusName === 'InActive') {
      this.statusmsg = 'deactivated';
      // this.weekedit.show();
    }

      dataItem.shipmentRuleId = dataItem.shipmentRule.shipmentRuleId;
      this.subscription = this.shipmentMonthService.editShipmentDetails(dataItem).subscribe(shipmentDetail => {
      this.shipmentMonthService.shipmentdetails.push(<ShipmentWeek>shipmentDetail);
      this.weekedit.show();
      // this.shipmentCode = dataItem.shipmentTimeframeCode;
      this.shipmentName = dataItem.shipmentTimeframeName;
      setTimeout(() => { this.weekedit.hide(); }, 3000);
      var sender=this.sender;
      sender.closeRow(this.rowindex);
      this.loadingShipmentWeekList();
      },
      error => {
          // this.isActionPerformed = true;
          this.pagebackModal.show();
          this.errorMessageforshipment = this.shipmentMonthService.errorMessage;
      },
      () => console.log('Finished')
    );
  }


editshipmentweekwithstatus(dataItem) {
   // this.isActionPerformed = false;
   if ( !dataItem.shipmentTimeframeCode || !dataItem.shipmentTimeframeName || !dataItem.shipmentRule) {
      return false;
    }
    if (dataItem.statusName === 'Draft') {
      dataItem.statusName = 'Active';
      this.statusmsg = 'activated';
      // this.weekedit.show();
    } else if (dataItem.statusName === 'Active') {
      dataItem.statusName = 'InActive';
      this.statusmsg = 'deactivated';
      // this.weekedit.show();
    } else if (dataItem.statusName === 'InActive') {
      dataItem.statusName = 'Active';
      this.statusmsg = 'activated';
      // this.weekedit.show();
    }

    dataItem.shipmentRuleId = dataItem.shipmentRule.shipmentRuleId;
    this.subscription = this.shipmentMonthService.editShipmentWithStatus(dataItem).subscribe(shipmentDetail => {
     this.shipmentMonthService.shipmentdetails.push(<ShipmentWeek>shipmentDetail);
     this.weekedit.show();
     // this.shipmentCode = dataItem.shipmentTimeframeCode;
     this.shipmentName = dataItem.shipmentTimeframeName;
     setTimeout(() => { this.weekedit.hide(); }, 3000);
     var sender=this.sender;
     sender.closeRow(this.rowindex);
     this.loadingShipmentWeekList();
    },
      error => {
          // this.isActionPerformed = true;
          this.pagebackModal.show();
          this.errorMessageforshipment = this.shipmentMonthService.errorMessage;
      },
      () => console.log('Finished')
    );
  }

public showModal(): void {
    this.errormodal = true;
  }

  public hideModal(): void {
    this.deletesuccessModal.hide();
  }


  confirmdelete(dataItem) {

      this.del_id = dataItem.shipmentWeekTimeframeId;
      // this.shipmentCode = dataItem.shipmentTimeframeCode;
      this.shipmentName = dataItem.shipmentTimeframeName;
      this.confirmdel.show();
      this.status = dataItem.statusName;
      if ( dataItem.statusName === 'Draft') {
        this.deleteStatus = 'Delete';
      } else if (dataItem.statusName === 'Active') {
        this.deleteStatus = 'Deactivate';
      } else if (dataItem.statusName === 'InActive') {
        this.deleteStatus = 'Activate';
      }
   }


    deleteshipmentweek(dataItem) {

      this.confirmdel.hide();
      this.subscription = this.shipmentMonthService.deleteShipmentDetails(this.del_id).subscribe(shipmentDetail => {
      this.shipmentMonthService.shipmentdetails.push(<ShipmentWeek>shipmentDetail);
      this.weekdelete.show();
      setTimeout(() => { this.weekdelete.hide(); }, 3000);
      var sender=this.sender;
      sender.closeRow(this.rowindex);
      this.loadingShipmentWeekList();
      },
      error => {
      },
      () => console.log('Finished')
      );
  }

    private closeEditor(grid, rowIndex = this.editedRowIndex) {
        grid.closeRow(rowIndex);
        this.editedRowIndex = undefined;
        this.formGroup = undefined;
    }

    public cancelHandler({ sender, rowIndex }) {
        this.isActionPerformed = true;
        this.draft = true;
        this.active = true;
        this.inactive = true;
        this.reqShipmentWeekCode = false;
        this.reqShipmentWeekName = false;
        this.reqShipmentRuleName = false;
        this.closeEditor(sender, rowIndex);
        // this.loadingShipmentWeekList();
    }

    sortChange(sort: SortDescriptor[]): void {
        this.sort = sort;
        this.loadingShipmentWeekList();
    }

    public onStateChange(state: State) {
        this.state = state;
    }

    loadshipmentDetails(): void {
        this.gridView = {
            data: process(orderBy(this.shipmentMonthService.shipmentdetails, this.sort), this.state).data,
            total: this.shipmentMonthService.shipmentdetails.length
        };
    }
     dataStateChange(state: DataStateChangeEvent): void {
         this.state = state;
    }


   pageChange(event: PageChangeEvent): void {
        this.state.skip = event.skip;
        this.loadingShipmentWeekList();
    }

   allData(): ExcelExportData {
    const result: ExcelExportData = {
    group: this.group
    };
   return result;
  }

public onHidden(): void {
    this.isDeletePopupModal = false;
    this.errormodal = false;
    this.isDeletePopupModals = false;
    this.errormodals = false;
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


}
