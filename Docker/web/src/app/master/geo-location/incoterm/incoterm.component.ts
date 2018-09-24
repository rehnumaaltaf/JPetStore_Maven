import { Component, Inject, OnInit, OnDestroy, Output, Input, ViewChild } from '@angular/core';
import { IncotermService } from './service/incoterm.service';
import { Incoterm } from './inco-terms-interface';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { ExcelExportData } from '@progress/kendo-angular-excel-export';
import { GridComponent, GridDataResult, DataStateChangeEvent, PageChangeEvent} from '@progress/kendo-angular-grid';
import { process, SortDescriptor, orderBy, GroupDescriptor, State } from '@progress/kendo-data-query';
import { incoTerm } from '../../../shared/interface/router-links';
import { ModalDirective } from 'ngx-bootstrap/modal';
import { AddIncoComponent } from './add-inco/add-inco.component';
import { addIncoTerm } from '../../../shared/interface/router-links';



@Component({
  selector: 'app-incoterm',
  templateUrl: './incoterm.component.html',
  styleUrls: ['./incoterm.component.css']
})
export class IncotermComponent implements OnInit {
  @ViewChild('SuccessModal') public SuccessModal: ModalDirective;
  @ViewChild ('confirmdel') public confirmdel: ModalDirective;
  @ViewChild ('incotermdelete') public incotermdelete: ModalDirective;
  @ViewChild ('updatesuccessModal') public updatesuccessModal: ModalDirective;
  incotermObj: Incoterm = new Incoterm();
  gridView: GridDataResult;
  group: GroupDescriptor[] = [{ field: 'statusName' }];
  state: State = {
        skip: 0,
        take: 1000
    };
  sort: SortDescriptor[] = [];
  subscription: Subscription;
  public isincotermdelete: Boolean = false;
  public isupdatesuccessModal: Boolean = false;
  public isShowModal: Boolean = false;
  public del_id;
  public deleteStatus: String;
  public incotermName;
  public status;
  public statusMsg;
  headerTermsCode: String;
  headerTermsName: String;
  headerTermsbaseName: String;
  headerTermsbasisName: String;
  headerStatus: String;
  public update;
  public addUpdate;
  public delSuccess;
  public addSuccess;

  constructor(public incotermService: IncotermService, private route: ActivatedRoute, private router: Router) {
       this.allData = this.allData.bind(this);
  }

  ngOnInit() {
     this.loadingIncotermList();
      this.route.queryParams.subscribe(params => {
        this.delSuccess = +params['delSuccess'];
        this.update = +params['update'];
        this.addUpdate = Number(params['addupdate']);
        this.addSuccess = Number(params['success']);

        if (this.update === 3) {
           if (params['status'] === 'submit') {
             this.statusMsg = 'Success: Inco term' + ' ' + params['termName'] + ' ' + ' Activated';
            this.isupdatesuccessModal = true;
            setTimeout(() => { this.updatesuccessModal.hide() }, 3000);
           } else if (params['status'] === 'InActive') {
            this.statusMsg = 'Success: Inco term' + ' ' + params['termName'] + ' ' + ' Deactivated';
            this.isupdatesuccessModal = true;
            setTimeout(() => { this.updatesuccessModal.hide() }, 3000);
           } else if (params['status'] === 'Active') {
            this.statusMsg = 'Success: Inco term' + ' ' + params['termName'] + ' ' + ' Reactivated';
            this.isupdatesuccessModal = true;
            setTimeout(() => { this.updatesuccessModal.hide() }, 3000);
           }
        this.router.navigate([incoTerm]);
        }else if (this.delSuccess === 1) {
                  this.statusMsg = 'Success: Inco term' + ' ' + params['termName'] + ' ' + 'Deleted';
                   this.isupdatesuccessModal = true;
                  setTimeout(() => { this.updatesuccessModal.hide() }, 3000);
                  this.router.navigate([incoTerm]);
        }else if ( this.addUpdate === 0  ) {
                this.statusMsg = 'Success: Inco term ' +  params['incoterm'] + ' updated';
                this.isupdatesuccessModal = true;
                setTimeout(() => { this.updatesuccessModal.hide() }, 3000);
                this.router.navigate([incoTerm]);
        }else if ( this.addUpdate === 2  ) {
                this.statusMsg = 'Success: Inco term ' +  params['incoterm'] + ' reactivated';
                this.isupdatesuccessModal = true;
                setTimeout(() => { this.updatesuccessModal.hide() }, 3000);
                this.router.navigate([incoTerm]);
        }else if ( this.addUpdate === 3  ) {
                this.statusMsg = 'Success: Inco term ' +  params['incoterm'] + ' deactivated';
                this.isupdatesuccessModal = true;
                setTimeout(() => { this.updatesuccessModal.hide() }, 3000);
                this.router.navigate([incoTerm]);
        }else if ( this.addUpdate === 4  ) {
                this.statusMsg = 'Success: Inco term ' +  params['incoterm'] + ' activate';
                this.isupdatesuccessModal = true;
                setTimeout(() => { this.updatesuccessModal.hide() }, 3000);
                this.router.navigate([incoTerm]);
        }else if ( this.addSuccess === 1) {
                this.statusMsg = 'Success: Inco term ' +  params['incoterm'] + ' saved';
                this.isupdatesuccessModal = true;
                setTimeout(() => { this.updatesuccessModal.hide() }, 3000);
                this.router.navigate([incoTerm]);
        }else if ( this.addSuccess === 2) {
                this.statusMsg = 'Success: Inco term ' +  params['incoterm'] + ' activated';
                this.isupdatesuccessModal = true;
                setTimeout(() => { this.updatesuccessModal.hide() }, 3000);
                this.router.navigate([incoTerm]);
        }

      });
  }

 public onHidden() {
    this.isincotermdelete = false;
    this.isupdatesuccessModal = false;
     this.isShowModal = false;
  }

    loadingIncotermList() {
      this.subscription = this.incotermService.getIncotermloadingJSON().subscribe(incotermList => {
     this.incotermService.incotermDetails = incotermList.body;
    const data1 = incotermList.view.column.split(',');
      if (data1[0] != null && data1[0] === 'contractTermsCode') {
        this.headerTermsCode = 'Incoterm Code';
      }
      if (data1[1] != null && data1[1] === 'contractTermsName') {
        this.headerTermsName = 'Incoterm Name';
      }
      if (data1[2] != null && data1[2] === 'contractTermsBaseName') {
        this.headerTermsbaseName = 'Base Term';
      }
      if (data1[3] != null && data1[3] === 'contractTermsBasisName') {
        this.headerTermsbasisName = 'Basis';
      }
      if (data1[4] != null && data1[4] === 'status') {
        this.headerStatus = 'Status';
      }
      this.loadIncotermDetails();
    },
      error => alert(error),
      () => console.log('Finished')
    );
  }

 loadIncotermDetails(): void {

        this.gridView = {
            data: process(orderBy(this.incotermService.incotermDetails, this.sort), this.state).data,
            total: this.incotermService.incotermDetails.length
        };
       // alert("stringify in loadoutturn===>"+JSON.stringify(this.gridView));
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
    group: this.group
    };
   return result;
  }

     sortChange(sort: SortDescriptor[]): void {
        this.sort = sort;
        this.loadingIncotermList();
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
        this.loadingIncotermList();
    }

    incoterm_delete(dataItem) {
      this.del_id = dataItem.contractTermsId;
       this.incotermName = dataItem.contractTermsName;
      this.confirmdel.show();
      this.status = dataItem.statusName;
      if ( dataItem.statusName === 'Draft') {
        this.deleteStatus = 'delete';
      }else if (dataItem.statusName === 'Active') {
        this.deleteStatus = 'Dectivate';
      } else if (dataItem.statusName === 'InActive') {
        this.deleteStatus = 'Activate';
      }
   }

    deleteIncoterm(dataItem) {
      this.confirmdel.hide();
      this.subscription = this.incotermService.deleteIncotermdetails(this.del_id).subscribe(deletedStatus => {
      const  statusCode = this.getStatusCode(deletedStatus);
                        if (statusCode === 200) {
                           if (this.status === 'Draft') {
                               this.statusMsg = 'Deleted';
                                 this.isincotermdelete = true;
                                  setTimeout(() => { this.incotermdelete.hide(); }, 3000);
                           } else if (this.status === 'Active') {
                                this.statusMsg = 'Dectivated';
                                 this.isincotermdelete = true;
                                  setTimeout(() => { this.incotermdelete.hide(); }, 3000);
                           } else if (this.status === 'InActive') {
                                this.statusMsg = 'InActivated';
                                 this.isincotermdelete = true;
                                  setTimeout(() => { this.incotermdelete.hide(); }, 3000);
                           }
                        }
      this.loadingIncotermList();
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

/*incoterm_status_change(dataItem, statusname) {
        this.incotermObj =  dataItem;
   if (statusname === 'Active') {
     this.incotermObj.statusName = 'Active';
   } else if (statusname === 'InActive') {
      this.incotermObj.statusName = 'InActive';
   }
    this.subscription = this.incotermService.updateIncoterm(this.incotermObj).subscribe(updateData => {
       }, error => {
    },
      () => console.log('Finished')

    );
   }*/

   changeShowStatus() {
      this.router.navigate([addIncoTerm]);
   }

}
