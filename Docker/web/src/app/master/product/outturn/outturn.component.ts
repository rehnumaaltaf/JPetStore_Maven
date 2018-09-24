import { Component, Inject, OnInit, OnDestroy, Output, Input, ViewChild, ViewEncapsulation } from '@angular/core';
import { Observable } from 'rxjs/Rx';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { SelectItem } from '../../../shared/interface/selectItem';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { MasterSetupService } from '../../master-setup/service/master-setup.service';
import { outturnRatio } from '../../../shared/interface/router-links';
import { OutturnService } from './service/outturn.service';
import { ExcelExportData } from '@progress/kendo-angular-excel-export';
import { GridComponent, GridDataResult, DataStateChangeEvent, PageChangeEvent} from '@progress/kendo-angular-grid';
import { process, SortDescriptor, orderBy, GroupDescriptor, State } from '@progress/kendo-data-query';
import { ModalDirective } from 'ngx-bootstrap/modal';
import { Outturn } from './outturn';
import { ActivatedRoute } from '@angular/router';
import { viewOutturnratio } from '../../../shared/interface/router-links';
import { addoutturn } from '../../../shared/interface/router-links';
import { editout } from '../../../shared/interface/router-links';

@Component({
  selector: 'app-outturn-ratio',
  templateUrl: './outturn.component.html',
  styleUrls: ['./outturn.component.css']
})
export class OutturnComponent implements OnInit {
  @ViewChild('SuccessModal') public SuccessModal: ModalDirective;
  outturn: Outturn = new Outturn();
  public success;
  public isShowModal;
  public successDialog;
  gridView: GridDataResult;
  subscription: Subscription;
  group: GroupDescriptor[] = [{ field: 'statusName' }];
   state: State = {
        skip: 0,
        take: 1000
    };
  sort: SortDescriptor[] = [];
  public data: any;
   public delSuccess;
  public update;
    public rawGradeName;
  public del_id;
  public statusMsg;
  public status;
  public deleteStatus: String;
  public isoutturnratiodelete: Boolean = false;
  public isupdatesuccessModal: Boolean = false;
    headerProdName: String;
  headerOriginName: String;
 headerRawgradeName: String;
 headerStatus: String;
   @ViewChild ('confirmdel') public confirmdel: ModalDirective;
  @ViewChild ('outturnratiodelete') public outturnratiodelete: ModalDirective;
  @ViewChild ('outurnadded') public outurnadded: ModalDirective;
  @ViewChild ('updatesuccessModal') public updatesuccessModal: ModalDirective;

  constructor(private route: ActivatedRoute, private router: Router, public outturnRatioService: OutturnService) {
     this.allData = this.allData.bind(this);
  }

  ngOnInit() {
      window.scrollTo(0, 0);
      this.loadingOutturnList();

      this.route.queryParams.subscribe(params => {
        // Defaults to 0 if no query param provided.
        // this.glCode = this.glAccountService;

        this.success = Number(params['success']);
        this.delSuccess = +params['delSuccess'];
        this.update = +params['update'];
        if (this.success === 1) {
         // this.isShowModal = true;
          // this.glName = params['glName'];
          this.isShowModal = true;
          this.successDialog = 'Outturn' + ' ' + params['GradeName'] + ' ' + 'successfully added';
          setTimeout(() => { this.SuccessModal.hide(); } , 3000);
          this.router.navigate([outturnRatio]);

        }else if (this.success === 2) {
         // this.isShowModal = true;
          // this.glName = params['glName'];
          this.isShowModal = true;
          this.successDialog = 'Outturn'  + ' ' + params['GradeName'] + ' ' + 'successfully updated';
          setTimeout(() => { this.SuccessModal.hide(); } , 3000);
          this.router.navigate([outturnRatio]);

        }else if (this.delSuccess === 1) {
                  this.statusMsg = 'Deleted: ' + 'raw grade ' + params['GradeName'];
                this.isupdatesuccessModal = true;
                setTimeout(() => { this.updatesuccessModal.hide() }, 3000);
                  this.router.navigate([outturnRatio]);
                } else if (this.update === 3) {
                 /* if (params['status'] === 'Active') {
                  this.statusMsg = 'Reactivated';
                 this.isoutturnratiodelete = true;
                  } else {
                     this.statusMsg = 'Deactivated';
                 this.isoutturnratiodelete = true;
                  }*/
                   this.statusMsg = 'Updated: ' + 'raw grade ' + params['GradeName'];
                    this.isupdatesuccessModal = true;
                    setTimeout(() => { this.updatesuccessModal.hide() }, 3000);
                       this.router.navigate([outturnRatio]);
                }
      });
  }


     loadingOutturnList() {
     this.subscription = this.outturnRatioService.getOutturnloadingJSON().subscribe(outturnList => {
     this.outturnRatioService.outturnRatioDetails = outturnList.body;
     const data1 = outturnList.view.column.split(',');
      if (data1[1] != null && data1[1] === 'prodName') {
        this.headerProdName = 'Product Name';
      }
      if (data1[2] != null && data1[2] === 'originName') {
        this.headerOriginName = 'Origin Name';
      }
      if (data1[0] != null && data1[0] === 'rawGradeName') {
        this.headerRawgradeName = 'Input Raw Grade Name';
      }
      if (data1[3] != null && data1[3] === 'status') {
        this.headerStatus = 'Status';
      }
      this.loadoutturnDetails();
     this.data =  outturnList.body;
    },
      error => alert(error),
      () => console.log('Finished')
    );
  }

  public onHidden() {
    this.isoutturnratiodelete = false;
    this.isupdatesuccessModal = false;
     this.isShowModal = false;
  }

  changeShowStatus() {
    this.router.navigate([addoutturn]);
  }

     loadoutturnDetails(): void {

        this.gridView = {
            data: process(orderBy(this.outturnRatioService.outturnRatioDetails, this.sort), this.state).data,
            total: this.outturnRatioService.outturnRatioDetails.length
        };
       // alert("stringify in loadoutturn===>"+JSON.stringify(this.gridView));
    }

     allData(): ExcelExportData {
    const result: ExcelExportData = {
    group: this.group
    };
   return result;
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

     sortChange(sort: SortDescriptor[]): void {
        this.sort = sort;
        this.loadingOutturnList();
    }

    public onStateChange(state: State) {
        this.state = state;
    }

    dataStateChange(state: DataStateChangeEvent): void {
         this.state = state;
         // this.gridData = process(this.glAccountService.glDetails, this.state);
    }


   pageChange(event: PageChangeEvent): void {
        this.state.skip = event.skip;
        this.loadingOutturnList();
    }
  viewById(event) {
    // alert("==eventparam==>"+JSON.stringify(event));
    this.outturnRatioService.viewbyIdParam = event;
    this.router.navigate([viewOutturnratio]);
  }
/* onHidden() {
} */
  editOutturn(id) {
   // alert(id);
   this.router.navigate([editout + '/' +  id]);
  }

    outturnratio_delete(dataItem) {
      this.del_id = dataItem.outturnRawGradeDto.outturnRawGradeId;
      // this.gradecode = dataItem.outturnRawGradeDto.rawGradeCode;
       this.rawGradeName = dataItem.outturnRawGradeDto.rawGradeName;
      this.confirmdel.show();
      this.status = dataItem.statusName;
      if ( dataItem.statusName === 'Draft') {
        this.deleteStatus = 'Delete';
        this.status = 'Draft';
      }else if (dataItem.statusName === 'Active') {
        this.deleteStatus = 'Dectivate';
          this.status = 'Active';
      } else if (dataItem.statusName === 'InActive') {
        this.deleteStatus = 'Activate';
        this.status = 'InActive';
      }
   }

      outturnratio_status_change(dataItem, statusname) {
        this.outturn =  dataItem;
   if (statusname === 'Active') {
     this.outturn.statusName = 'Active';
   } else if (statusname === 'InActive') {
      this.outturn.statusName = 'InActive';
   }
    this.subscription = this.outturnRatioService.updateOutturnratio(this.outturn).subscribe(updateData => {
       }, error => {
    },
      () => console.log('Finished')

    );
     /*
      this.del_id = dataItem.outturnRawGradeDto.outturnRawGradeId;
      // this.gradecode = dataItem.outturnRawGradeDto.rawGradeCode;
       this.rawGradeName = dataItem.outturnRawGradeDto.rawGradeName;
      this.confirmdel.show();
      this.status = dataItem.statusName;
      if ( dataItem.statusName === 'Draft') {
        this.deleteStatus = 'Delete';
        this.status = 'Draft';
      }else if (dataItem.statusName === 'Active') {
        this.deleteStatus = 'Dectivate';
          this.status = 'Active';
      } else if (dataItem.statusName === 'InActive') {
        this.deleteStatus = 'Activate';
        this.status = 'InActive';
      }*/
   }

       deleteoutturnratio(dataItem) {
     /* this.del_id = dataItem.outturnRawGradeDto.outturnRawGradeId;*/
     // dataItem = this.del_id;
      this.confirmdel.hide();
      this.subscription = this.outturnRatioService.deleteOutturnratiodetails(this.del_id).subscribe(deletedStatus => {
     // this.outturnRatioService.outturnDetails.push(<CropYear>addcropetail);
      const  statusCode = this.getStatusCode(deletedStatus);
                        if (statusCode === 200) {
                           if (this.status === 'Draft') {
                               this.statusMsg = 'Deleted';
                                 this.isoutturnratiodelete = true;
                                  setTimeout(() => { this.outturnratiodelete.hide(); }, 3000);
                           } else if (this.status === 'Active') {
                                this.statusMsg = 'Dectivated';
                                 this.isoutturnratiodelete = true;
                                  setTimeout(() => { this.outturnratiodelete.hide(); }, 3000);
                           } else if (this.status === 'InActive') {
                                this.statusMsg = 'InActivated';
                                 this.isoutturnratiodelete = true;
                                  setTimeout(() => { this.outturnratiodelete.hide(); }, 3000);
                           }
                        }
      this.loadingOutturnList();
      },
      error => {
      },
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

}
