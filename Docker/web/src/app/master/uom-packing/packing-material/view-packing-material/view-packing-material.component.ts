import { NavBarComponent } from '../../../../shared/nav-bar/nav-bar.component';
import { Component, OnInit, OnDestroy, Output, ViewChild, EventEmitter, ViewEncapsulation } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';
import { PackingModel } from '../model/pack-model';
import { ModalDirective } from 'ngx-bootstrap';
import { PackingMaterialService } from '../service/packing-material.service';
import { ActivatedRoute, Router } from '@angular/router';
import { packingMaterial } from './../../../../shared/interface/router-links';
import { ResponseData } from '../../../../shared/interface/responseData';
// import { packingMaterial } from './../../../../shared/interface/router-links';

@Component({
  selector: 'app-view-packing-material',
  templateUrl: './view-packing-material.component.html',
  styleUrls: ['./view-packing-material.component.css']
})
export class ViewPackingMaterialComponent implements OnInit, OnDestroy {

  getDetails;
  subscription: Subscription;
  // packingmaterial: PackingModel[];
  packingDetail: PackingModel;
  getDeatils: PackingModel;
  isconfirmSuccessModal;
  isConfirmPopupModal;
  dialogValue: string;
  successDialog: string;
  primaryid: string;
  errorMessage: string;
  errorModal;
  @ViewChild('successModal') public successModal: ModalDirective;
  @ViewChild('confirmSuccessModal') public confirmSuccessModal: ModalDirective;
  @ViewChild('editModal') public editModal: ModalDirective;
  message: string;
  UpdateModal = false;
  openEdit: Boolean =  false;
  constructor(private route: ActivatedRoute, private router: Router, private packingMaterialService: PackingMaterialService) { }

  ngOnInit() {
  //
    this.loadPack();
  }
  loadPack() {
    this.getDetails = this.packingMaterialService.getRowData();
    if (this.getDetails !== undefined && this.getDetails !== null) {
      this.subscription = this.packingMaterialService.getPackingIdJSON(this.getDetails.pkPackingMaterialId).
        subscribe(getData => {
          this.packingDetail = getData.body[0];
          console.log(JSON.stringify(getData.body[0]) +   '   view check');
        });
    }
  }

   editPack() {
    this.openEdit = true;
  }

  closeEditModal($event) {
    this.editModal.hide();
    this.message = $event['msg'];
    if (this.message !== '' && this.message !== undefined && this.message !== null) {
      this.loadPack();
      this.UpdateModal = true;
      setTimeout(() => {
        this.UpdateModal = false;
      }, 3000);
    }
  }
   onHideEditModal(): void {
    this.openEdit = false;
   }

  backtoView() {
    this.router.navigate([packingMaterial]);
  }
  ngOnDestroy() {

  }


  onSubmitBtnClick() {
    // alert(this.packingDetail.statusName);
    if (this.packingDetail.statusName === 'Draft') {
      // this.dialogValue = 'Do you want Active the Financial Calendar ' + this.financialCal.fiscalYear;
      //   this.isConfirmPopupModal = true;
      this.packingDetail.statusName = 'Active';
      this.packingDetail.action = 'submit';
      this.subscription = this.packingMaterialService.updatePackingDetails(this.packingDetail)
        .subscribe(data => {
          //   this.successModal.hide();
          this.successDialog = 'You have successfully Activated Packing material' + this.packingDetail.packingMaterialCode;
          this.isconfirmSuccessModal = true;
          setTimeout(() => {
            this.confirmSuccessModal.hide();
            this.router.navigate([packingMaterial]);
          }, 1000);

        });
    }
  }

  onHidden() {
    this.isconfirmSuccessModal = false;
  }

  onHiddenpopup() {
    this.isConfirmPopupModal = false;
  }

  openConfirmAction(status) {
    // alert(status);\

    if (status === 'Draft') {
      this.dialogValue = 'Do you want Delete the Packing Material ' + this.packingDetail.packingMaterialCode;
    } else if (status === 'Active') {
      this.dialogValue = 'Do you want DeActivate the Packing Material ' + this.packingDetail.packingMaterialCode;
    } else if (status === 'InActive') {
      this.dialogValue = 'Do you want Re-Activate the Packing Material ' + this.packingDetail.packingMaterialCode;
    }
    this.isConfirmPopupModal = true;

  }

  closedeletepopup() {
    this.successModal.hide();
  }

  status_update() {
    // alert(this.financialCal.pkFinCalId);
    //  alert(this.financialCal.statusName);
    // tslint:disable-next-line:max-line-length
    this.subscription = this.packingMaterialService.deletePackingMaterial(this.packingDetail.pkPackingMaterialId, this.packingDetail.statusName)
      .subscribe(deletedStatus => {
        this.successModal.hide();
        if (this.packingDetail.statusName === 'Active') {
          this.successDialog = 'You have successfully deactivated packing material ' + this.packingDetail.packingMaterialCode;
        } else if (this.packingDetail.statusName === 'InActive') {
          this.successDialog = 'You have successfully reactivated packing material ' + this.packingDetail.packingMaterialCode;
        } else if (this.packingDetail.statusName === 'Draft') {
          this.successDialog = 'You have successfully deleted packing material ' + this.packingDetail.packingMaterialCode;
        }
        this.isconfirmSuccessModal = true;

        setTimeout(() => {
          this.isconfirmSuccessModal = false;
          // this.confirmSuccessModal.hide();
          this.router.navigate([packingMaterial]);
        }, 1000);


      });

  }


}
