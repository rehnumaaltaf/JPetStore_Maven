import { Component, OnInit, OnDestroy, ViewEncapsulation, Output, ViewChild } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';
import { LocationModel } from './model/location.model';
import { StatusCode } from './model/status.enum';
import { ActivatedRoute, Router } from '@angular/router';
import { LocationService } from './service/location.service';
import { addLocation, viewLocation } from '../../../shared/interface/router-links';
import { MessageModel } from '../../../shared/message/message.model';
import { ModalDirective } from 'ngx-bootstrap';
import { ExcelExportData } from '@progress/kendo-angular-excel-export';
import { GridComponent, GridDataResult, DataStateChangeEvent, PageChangeEvent} from '@progress/kendo-angular-grid';
import { process, SortDescriptor, orderBy, GroupDescriptor, State } from '@progress/kendo-data-query';
import { ConfirmBoxModel } from './confirm-box/confirm-box.model';
import { ConfirmationService } from '../../../shared/confirm-box/confirm.service';
import { MESSAGE_SUCCESS, MESSAGE_ERROR, ACTION_SAVE } from '../../../shared/interface/common.constants';
import { ACTION_SUBMIT, ACTION_UPDATE, ACTION_ACTIVE, ACTION_DEACTIVE } from '../../../shared/interface/common.constants';


@Component({
  selector: 'app-location',
  templateUrl: './location.component.html',
  styleUrls: ['./location.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class LocationComponent implements OnInit, OnDestroy {
  subscription: Subscription;
  locationDetails: LocationModel[];
  locationDetail: LocationModel = new LocationModel();
  status: typeof StatusCode = StatusCode;
  openEdit: Boolean =  false;
  allItemsChecked: Boolean = false;
  groups:  LocationModel[];
  @Output() loadingData: Boolean = true;
  @ViewChild('editModal') public editModal: ModalDirective;
  @ViewChild('confirmModal') public confirmModal: ModalDirective;

  // Kendo ui grid
  group: GroupDescriptor[] = [{ field: 'fkStatusId' }];
  multiple: Boolean = false;
  gridView: GridDataResult;
  allowUnsort: Boolean = true;
  sort: SortDescriptor[] = [];
  state: State = {
        skip: 0,
        take: 1000
    };
  gridData: GridDataResult;
  disableSaveBtn: Boolean = false;
  disableSubmitBtn: Boolean = false;
  // confirmBoxConfigure: ConfirmBoxModel;
  changedStatusId: number;
  constructor(private route: ActivatedRoute, private router: Router,
   public locationService: LocationService, public confirmationService: ConfirmationService) {
    this.allData = this.allData.bind(this);
  }

  ngOnInit() {
    this.loadLocationDetails();
    this.openEdit = false;
  }

  ngOnDestroy() {
    // prevent memory leak when component destroyed
    this.subscription.unsubscribe();
  }

  loadLocationDetails() {
    this.loadingData = true;
      this.subscription = this.locationService.getLocationDetails().subscribe(locationDetail => {
        this.locationDetails = <LocationModel[]>locationDetail.body;
        for ( let i = 0; i < this.locationDetails.length; i++) {
          // this.locationDetails[i].locationTypeNames = this.locationDetails[i].fkLocTypeName.join();
          this.locationDetails[i].statusName =  this.status[this.locationDetails[i].fkStatusId] ;
        }
        console.log(  this.locationDetails );
        this.loadLocDetails();
        this.loadingData = false;
      }, error => {
        console.log(error)
        this.loadingData = false;
      },
        () => console.log('Finished')
      );
  }

   onDeleteBtnClick(): void {
        this.subscription = this.locationService.deleteLocationDetails(this.locationDetail.pkLocId).subscribe(data => {
        this.locationService.messages = {severity: MESSAGE_SUCCESS,  summary: 'Location ' + this.locationDetail.locCode + ' & ' +
        this.locationDetail.locName + ' has been successfully deleted', detail: ''};
        this.loadingData = false;
        this.loadLocationDetails();
      },
      error => {
        throw error;
      });
    }

  goToAddLocation() {
    this.router.navigate([addLocation]);
  }

  editRow(index) {
    this.openEdit = true;
    this.locationDetail = this.locationDetails[index];
  }

  closeEditModal($event) {
    this.editModal.hide();
    if ($event.message && $event.message.length !== 0) {
      this.locationService.messages = $event.message;
      this.loadLocationDetails();
    }
  }
   onHideEditModal(): void {
    this.openEdit = false;
   }


   // Kendo UI Grid integration


   checkAllClicked(e) {
      console.log('checkAllClicked', e);
      /*this.event.forEach(x => x.state = ev.target.checked)*/
      // switch inactive checked value
        if (e.target.checked) {
            this.allItemsChecked = true;
            for (let i = 0; i < this.locationDetails.length; i++) {
                this.locationDetails[i].checked = true;
            }
        } else {
            this.allItemsChecked = false;
            for (let i = 0; i < this.locationDetails.length; i++) {
                this.locationDetails[i].checked = false;
            }
        }
   }
   selectUnSelectAllChecked(e) {
      if (!e.target.checked) {
        this.allItemsChecked = false;
      } else  {
         let selected;
          selected = 0;
          for (let i = 0; i < this.locationDetails.length; i++) {
              if ( !this.locationDetails[i].checked ) {
                this.allItemsChecked = false;
                break;
              } else {
                selected++;
              }
          }
          if ( selected === this.locationDetails.length ) {
            this.allItemsChecked = true;
          }
      }
      console.log( this.allItemsChecked );
   }

   sortChange(sort: SortDescriptor[]): void {
        this.sort = sort;
        this.loadLocationDetails();
    }

    modalPopup(event) {
    }


   loadLocDetails(): void {
        this.gridView = {
            data: process(this.locationDetails, this.state).data,
            total: this.locationDetails.length
        };
    }


   public dataStateChange(state: DataStateChangeEvent): void {
        this.state = state;
        this.gridData = process(this.locationDetails, this.state);
    }


   pageChange(event: PageChangeEvent): void {
        this.state.skip = event.skip;
        this.loadLocationDetails();
    }

   allData(): ExcelExportData {
    const result: ExcelExportData = {
      data: process(this.locationDetails, this.state).data,
      group: this.group
    };

    return result;
  }


  goToViewPage(rowData) {
     this.locationService.setRowData(rowData)
     this.router.navigate([viewLocation]);
  }


   openConfirmAction(i) {
     this.changedStatusId =  -1 ;
     this.locationDetail = this.locationDetails[i];
     if ( this.locationDetail.fkStatusId === this.status.Draft ) {
        this.changedStatusId = this.status.Draft;
        this.confirmationService.confirm({
            message: 'Confirm.Common.Delete',
            placeHolder: [this.locationDetail.locName],
            accept: (event) => {
               this.onDeleteBtnClick();
            },
            reject: (event) => {
            }
        });
        // this.confirmBoxConfigure = {message: 'Do you really want to delete?',
       // header: 'Delete Confirmation', isVisible: true, isCommentPresent: false, comment: ''};
     } else if ( this.locationDetail.fkStatusId === this.status.Active ) {
        this.changedStatusId = this.status.Inactive;
        this.confirmationService.confirm({
            message: 'Confirm.Common.Deactivate',
            placeHolder: [this.locationDetail.locName],
            accept: (event) => {
               this.onSubmitBtnClick(this.changedStatusId);
            },
            reject: (event) => {
            }
        });
       // this.confirmBoxConfigure = {message: 'Do you really want to deactivate?',
       // header: 'Delete Confirmation', isVisible: true, isCommentPresent: false, comment: ''};
     } else if ( this.locationDetail.fkStatusId === this.status.Inactive ) {
         this.changedStatusId = this.status.Active;
         this.confirmationService.confirm({
            message: 'Confirm.Common.Reactivate',
            placeHolder: [this.locationDetail.locName],
            accept: (event) => {
               this.onSubmitBtnClick(this.changedStatusId);
            },
            reject: (event) => {
            }
        });
        // this.confirmBoxConfigure = {message: 'Do you really want to reactivate?',
         // header: 'Delete Confirmation', isVisible: true, isCommentPresent: false, comment: ''};
     }
   }
 /*
   confirmCancelation($event) {
     if ( this.changedStatusId === this.status.Draft ) {
        this.onDeleteBtnClick();
     } else {
        this.onSubmitBtnClick(this.changedStatusId);
     }
      this.confirmModal.hide();
     // this.confirmBoxConfigure.isVisible = false;
   }
   closeConfirmBox($event) {
      this.confirmModal.hide();
     // this.confirmBoxConfigure.isVisible = false;
   }

   onHiddenConfirm() {
      this.confirmBoxConfigure.isVisible = false;
   }*/
  onSubmitBtnClick(statusId): void {
        this.disableSubmitBtn = true;
        this.loadingData = true;
        this.disableSaveBtn = true;
        this.disableSubmitBtn = true;
        const locationDetail = Object.assign({}, this.locationDetail);
        locationDetail.fkStatusId = statusId;
        locationDetail.fkLocTypeName = '';
        if ( statusId !== this.status.Active) {
           locationDetail.action = 'Deactive';
        } else {
           locationDetail.action  = 'Submit';
        }
        this.subscription = this.locationService.updateLocationDetails(locationDetail).subscribe(data => {
          if (statusId === this.status.Active) {
            if (this.locationDetail.fkStatusId !== statusId ) {
                 this.locationService.messages = { severity: MESSAGE_SUCCESS,
                   placeHolder: ['Location', locationDetail.locName],
                 summary: 'Message.Common.Reactivate'};
            }else {
               this.locationService.messages = {severity: MESSAGE_SUCCESS,
                 placeHolder: ['Location', locationDetail.locName],
                summary: 'Message.Common.Save'};
            }
          }else if (statusId === this.status.Inactive) {
            if (this.locationDetail.fkStatusId !== statusId ) {
               this.locationService.messages = {severity: MESSAGE_SUCCESS,
                placeHolder: ['Location', locationDetail.locName],
                summary: 'Message.Common.Deactivate'};
            }else {
              this.locationService.messages = {severity: MESSAGE_SUCCESS,
                placeHolder: ['Location', locationDetail.locName],
                summary: 'Message.Common.Save'};
            }
          }else {
             this.locationService.messages = {severity: MESSAGE_SUCCESS,
               placeHolder: ['Location', locationDetail.locName],
               summary: 'Message.Common.Save'};
          }
          this.disableSaveBtn = false;
          this.disableSubmitBtn = false;
          this.loadingData = false;
          this.openEdit = false;
          this.loadLocationDetails();
      },
      error => {
        throw error;
      });
    }



}
