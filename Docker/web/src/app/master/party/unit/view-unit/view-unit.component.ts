import { Component, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup, FormControl } from '@angular/forms';
import { UnitService } from '../service/unit.service';
import { ProfitCenterModel } from '../model/profit-center-model';
import { Subscription } from 'rxjs/Subscription';
import { ButtonModule, ConfirmationService, Message } from 'primeng/primeng';

@Component({
  selector: 'app-view-unit',
  templateUrl: './view-unit.component.html',
  styleUrls: ['./view-unit.component.css']
})
export class ViewUnitComponent implements OnInit {
    buttonValue: string;
    loadingData: Boolean = false;
    subscription: Subscription;
    // profitCenterModel: ProfitCenterModel[]=[];
     profitCenterModel: ProfitCenterModel = new ProfitCenterModel();
    isComplete: Boolean = false;
    unitMasterID: number;
    msgs: Message[] = [];
    showStatus: Boolean = false;
    doDeActiveStatus: Boolean = false;
    doReActiveStatus: Boolean = false;
    editStatus: Boolean= false;
    unitMasterStatusId: string;
    unitViewForm = new FormGroup({
      unitCode: new FormControl(),
      unitName: new FormControl(),
      unitFullname: new FormControl(),
      isgroupunit: new FormControl(),
      parentunit: new FormControl(),
      legalEntity: new FormControl(),
      glcode: new FormControl()
  })
  constructor(private router: Router, private unitService: UnitService, private confirmationService: ConfirmationService) {
     // this.unitID=this.unitService.getId();
     // this.loadingUnitList(this.unitMasterID);
  }

  ngOnInit() {
    this.unitMasterID = this.unitService.getId();
    this.unitMasterStatusId = this.unitService.getStatusId();
    // alert(this.unitMasterID+'      '+this.unitMasterStatus);
    if (this.unitMasterStatusId === '3') {
      this.showStatus = !this.showStatus;
      this.editStatus = ! this.editStatus;
    }
    if (this.unitMasterStatusId === '1') {
      this.doDeActiveStatus = !this.doDeActiveStatus;
      this.editStatus = !this.editStatus;
    }
    if (this.unitMasterStatusId === '4') {
      this.doReActiveStatus = !this.doReActiveStatus;
      this.editStatus = !this.editStatus;
    }
    this.loadingUnitList(this.unitMasterID);
  }


  onEditBtnClick(id, statusId) {
    this.unitService.setId(id);
    this.unitService.setStatusId(statusId);
    this.router.navigate(['master/editunitparty']);
  }
  onDeactivateBtnClick(id) {
    this.confirmationService.confirm({
            message: 'Do you want to Deactivate?',
            header: 'Confirmation',
            icon: 'fa fa-question-circle',
            accept: () => {
                this.unitService.deleteUnitJSON('', id).subscribe(getUnitDetail => {
                    // alert(getUnitDetail.body);
                    this.isComplete = true;
                    this.msgs = [];
                    this.msgs = [{severity: 'info', summary: 'Confirmed', detail: getUnitDetail.body}];
                    this.router.navigate(['master/unitparty']);
                },
                error => {
                  console.log('Error Loading UOM Listing: ' + <any>JSON.stringify(error))
                  // this.loadingData = false;
                });
            },
            reject: () => {
            }
        });
  }

  onReactivateBtnClick(id) {
     this.confirmationService.confirm({
            message: 'Do you want to Reactivate?',
            header: 'Confirmation',
            icon: 'fa fa-question-circle',
            accept: () => {
                this.unitService.deleteUnitJSON('', id).subscribe(getUnitDetail => {
                    // alert(getUnitDetail.body);
                    this.isComplete = true;
                    this.msgs = [];
                    this.msgs = [{severity: 'info', summary: 'Confirmed', detail: getUnitDetail.body}];
                    this.router.navigate(['master/unitparty']);
                },
                error => {
                  console.log('Error Loading UOM Listing: ' + <any>JSON.stringify(error))
                  // this.loadingData = false;
                });
            },
            reject: () => {

            }
        });
  }
  onDeleteBtnClick(id) {
    this.confirmationService.confirm({
          message: 'Do you want to Delete?',
          header: 'Confirmation',
          icon: 'fa fa-question-circle',
          accept: () => {
             this.unitService.deleteUnitJSON('', id).subscribe(getUnitDetail => {
                  // alert(getUnitDetail.body);
                  this.isComplete = true;
                  this.msgs = [];
                  this.msgs = [{severity: 'info', summary: 'Confirmed', detail: getUnitDetail.body}];
                  this.router.navigate(['master/unitparty']);
              },
              error => {
                console.log('Error Loading UOM Listing: ' + <any>JSON.stringify(error))
                // this.loadingData = false;
              });
          },
          reject: () => {

          }
      });
  }

  onSubmitBtnClick() {
      this.profitCenterModel[0].action = 'SUBMIT';
      this.subscription = this.unitService.updateProfitCenter
        ('', this.profitCenterModel[0]).subscribe(data => {
      if (data.body !== null && data.body !== '' ) {
          this.isComplete = true;
          this.msgs = [];
          this.msgs.push({severity: 'success', summary: '', detail: data.body});
          this.router.navigate(['master/unitparty']);
        }
      }, error => {
        console.log('Error Loading UOM Listing: ' + <any>error)
      });
  }


  loadingUnitList(id) {
    this.unitService.listUnitJSON('', id).subscribe(viewUnitDetail => {
      this.profitCenterModel = viewUnitDetail.body;

     // console.log("////////////////////"+JSON.stringify(this.profitCenterModel));
      this.loadingData = true;
    },
    error => {
      console.log('Error Loading UOM Listing: ' + <any>error)
      this.loadingData = false;
    });
  }
  /*closePage1(){
    this.router.navigate(['master/unitparty']);
  }*/

}
