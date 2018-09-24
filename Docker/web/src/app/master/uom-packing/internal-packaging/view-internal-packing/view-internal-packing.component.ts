import { Component, OnInit, OnDestroy, Output, ViewChild, EventEmitter, ViewEncapsulation } from '@angular/core';
// import { InternalPackaging } from '../internalpackaging';
import { InternalPackagingModel } from '../model/InternalPackagingModel';
import { InternalPackingService } from '../service/internal-packing.service';
import { SelectItem } from '../../../../shared/interface/selectItem';
import { Subscription } from 'rxjs/Subscription';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { addinternalPackaging } from '../../../../shared/interface/router-links';
import { CanComponentDeactivate } from '../../../../shared/guards/can-deactivate-guard';
import { FormBuilder, FormGroup, FormControl, Validators , FormArray , ReactiveFormsModule , FormsModule} from '@angular/forms';
import { InternalPacking } from '../../../../shared/interface/router-links';
import { StatusCode } from '../model/status.enum';
import { ModalDirective } from 'ngx-bootstrap';
import { UnitMeasurementService } from '../../../../master/uom-packing/unit-measurement/service/unit-measurement.service';
import { MasterSetupService } from '../../../master-setup/service/master-setup.service';

@Component({
  selector: 'app-view-internal-packing',
  templateUrl: './view-internal-packing.component.html',
  styleUrls: ['./view-internal-packing.component.css']
})
export class ViewInternalPackingComponent implements OnInit {
 // packingDetails: InternalPackaging[];
  internalPacking: InternalPackagingModel = new InternalPackagingModel();
  public intpackdata;

  public draft;
  public active;
  public inactive;
  public iseditModal;
  editMode: boolean;
  isEdit: boolean;
  private PrimaryIntPacking;
  isUpdate: boolean;
  public internaledit;
  public savedData;
  dialogValue: string;
  displayDialog: boolean;
  req_ipcode: boolean;
  req_ipname: boolean;
  req_ipuomcode: boolean;
  req_ipweight: boolean;
   reqmsg = '';
  req_typeCode: Boolean;
   UpdateModal = false;
  subscription: Subscription;
  status: typeof StatusCode = StatusCode;
  disableSaveBtn: Boolean = false;
  disableSubmitBtn: Boolean = false;
  @Output() loadingData: Boolean = true;
  @ViewChild('editModal') public editModal: ModalDirective;
  @ViewChild('confirmModal') public confirmModal: ModalDirective;
   @ViewChild('successModal') public successModal: ModalDirective;
    @ViewChild('confirmSuccessModal') public confirmSuccessModal: ModalDirective;
  changedStatusId: number;
  openEdit: Boolean =  false;
  pkLocId: number;
  successDialog: string;
  isconfirmSuccessModal: boolean;
  isConfirmPopupModal: boolean;
  message: string;



  constructor(public internalPackingService: InternalPackingService, private route: ActivatedRoute, http: Http, private router: Router,
    private unitMeasurementService:  UnitMeasurementService ,
    private masterSetupService: MasterSetupService) { }


  ngOnInit() {
     this.loadingPackList1();

  }

 backtoPrev() {
  // alert('navigate internal packing');
     this.router.navigate([InternalPacking]);
  }

 edit() {
    this.openEdit = true;
  }

   onHideEditModal(): void {
    this.openEdit = false;
   }

 loadingPackList1() {
   if (this.internalPackingService.viewbyIdParam) {
   this.intpackdata = this.internalPackingService.viewbyIdParam;

    // console.log(JSON.stringify(this.internalPacking.internalPackingTypeId + 'internalpackingid'));
     this.subscription = this.internalPackingService.getPackingIdJSON(this.intpackdata.internalPackingTypeId)
     .subscribe(intPackDetail => {
     this.internalPackingService.internalModel = intPackDetail.body;
      this.intpackdata = intPackDetail.body;
  /*     if (this.intpackdata.action === 'DEACTIVATE') {
      this.intpackdata.status = 'InActive';
    } else if (this.intpackdata.action === 'REACTIVATE') {
       this.intpackdata.status = 'Active';
    }*/
    // alert(JSON.stringify(intPackDetail));
      console.log(JSON.stringify(this.intpackdata + 'intpackdata'));

     },
      error => alert(error),
      () => console.log('Finished')
      );
   }  else {
      this.router.navigate([InternalPacking]);
   }
  }

  closeEditModal($event) {
    this.editModal.hide();
    this.message = $event['msg'];
    if (this.message !== '' && this.message !== undefined && this.message !== null) {
      this.UpdateModal = true;
      this.loadingPackList1();
      setTimeout(() => {
        this.UpdateModal = false;
      }, 3000);
    }
  }

  isValidForm(): Boolean {
    // alert(thisTypeCode);
       if ((this.PrimaryIntPacking.internalPackingTypeCode === null || this.PrimaryIntPacking.internalPackingTypeCode === undefined)
      && (this.PrimaryIntPacking.internalPackingTypeName === null || this.PrimaryIntPacking.internalPackingTypeName === undefined)
    && (this.PrimaryIntPacking.internalPackingWeight === null || this.PrimaryIntPacking.internalPackingWeight === undefined)
    && (this.PrimaryIntPacking.internalPackingUomCode === null || this.PrimaryIntPacking.internalPackingUomCode === undefined)) {
        this.req_ipcode = true;
        this.req_ipname = true;
        this.req_ipuomcode = true;
        this.req_ipweight = true;
     //   this.reqmsg += 'Please enter PrimaryIntPacking Packing Type Code,  Packing Type Name, Packing Weight,  Packing UOM Code';
        this.req_typeCode = true;
        return false;
      } else if ( (this.PrimaryIntPacking.internalPackingTypeCode !== null || this.PrimaryIntPacking.internalPackingTypeCode !== undefined)
      && (this.PrimaryIntPacking.internalPackingTypeName === null || this.PrimaryIntPacking.internalPackingTypeName === undefined)
    && (this.PrimaryIntPacking.internalPackingWeight === null || this.PrimaryIntPacking.internalPackingWeight === undefined)
    && (this.PrimaryIntPacking.internalPackingUomCode === null || this.PrimaryIntPacking.internalPackingUomCode === undefined)) {
        this.req_ipcode = false;
        this.req_ipname = true;
        this.req_ipuomcode = true;
        this.req_ipweight = true;
     //  this.reqmsg += 'Please enter PrimaryIntPacking Packing Type Name, Packing Weight, PrimaryIntPacking Packing UOM Code'
        this.req_typeCode = true;
        return false;
      } else if ( (this.PrimaryIntPacking.internalPackingTypeCode === null || this.PrimaryIntPacking.internalPackingTypeCode === undefined)
      && (this.PrimaryIntPacking.internalPackingTypeName !== null || this.PrimaryIntPacking.internalPackingTypeName !== undefined)
   && (this.PrimaryIntPacking.internalPackingWeight === null || this.PrimaryIntPacking.internalPackingWeight === undefined)
    && (this.PrimaryIntPacking.internalPackingUomCode === null || this.PrimaryIntPacking.internalPackingUomCode === undefined)) {
        this.req_ipcode = true;
        this.req_ipname = false;
        this.req_ipuomcode = true;
        this.req_ipweight = true;
      //  this.reqmsg += 'Please enter PrimaryIntPacking Packing Type Code,  Packing Weight, PrimaryIntPacking Packing UOM Code'
        this.req_typeCode = true;
        return false;
      }  else if ( (this.PrimaryIntPacking.internalPackingTypeCode === null || this.PrimaryIntPacking.internalPackingTypeCode === undefined)
      && (this.PrimaryIntPacking.internalPackingTypeName === null || this.PrimaryIntPacking.internalPackingTypeName === undefined)
    && (this.PrimaryIntPacking.internalPackingWeight !== null || this.PrimaryIntPacking.internalPackingWeight !== undefined)
    && (this.PrimaryIntPacking.internalPackingUomCode === null || this.PrimaryIntPacking.internalPackingUomCode === undefined)) {
      this.req_ipcode = true;
      this.req_ipname = true;
      this.req_ipuomcode = false;
      this.req_ipweight = true;
    //  this.reqmsg += 'Please enter PrimaryIntPacking Packing Type Code,  Packing Type Name, PrimaryIntPacking Packing UOM Code'
        this.req_typeCode = true;
      return false;
    }    else if ( (this.PrimaryIntPacking.internalPackingTypeCode === null || this.PrimaryIntPacking.internalPackingTypeCode === undefined)
      && (this.PrimaryIntPacking.internalPackingTypeName === null || this.PrimaryIntPacking.internalPackingTypeName === undefined)
   && (this.PrimaryIntPacking.internalPackingWeight === null || this.PrimaryIntPacking.internalPackingWeight === undefined)
    && (this.PrimaryIntPacking.internalPackingUomCode !== null || this.PrimaryIntPacking.internalPackingUomCode !== undefined)) {
      this.req_ipcode = true;
      this.req_ipname = true;
      this.req_ipuomcode = true;
      this.req_ipweight = false;
   //   this.reqmsg += 'Please enter PrimaryIntPacking Packing Type Code,  Packing Type Name,PrimaryIntPacking Packing Weight'
      this.req_typeCode = true;
      return false;
    }   else if ( (this.PrimaryIntPacking.internalPackingTypeCode !== null || this.PrimaryIntPacking.internalPackingTypeCode !== undefined)
      && (this.PrimaryIntPacking.internalPackingTypeName !== null || this.PrimaryIntPacking.internalPackingTypeName !== undefined)
     && (this.PrimaryIntPacking.internalPackingWeight === null || this.PrimaryIntPacking.internalPackingWeight === undefined)
    && (this.PrimaryIntPacking.internalPackingUomCode === null || this.PrimaryIntPacking.internalPackingUomCode === undefined)) {
      this.req_ipcode = false;
      this.req_ipname = false;
      this.req_ipuomcode = true;
      this.req_ipweight = true;
    //  this.reqmsg += 'Please enter PrimaryIntPacking Packing Weight, PrimaryIntPacking Packing UOM Code'
        this.req_typeCode = true;
      return false;
    }	else if ( (this.PrimaryIntPacking.internalPackingTypeCode !== null || this.PrimaryIntPacking.internalPackingTypeCode !== undefined)
      && (this.PrimaryIntPacking.internalPackingTypeName === null || this.PrimaryIntPacking.internalPackingTypeName === undefined)
    && (this.PrimaryIntPacking.internalPackingWeight !== null || this.PrimaryIntPacking.internalPackingWeight !== undefined)
    && (this.PrimaryIntPacking.internalPackingUomCode === null || this.PrimaryIntPacking.internalPackingUomCode === undefined))  {
      this.req_ipcode = false;
      this.req_ipname = true;
      this.req_ipuomcode = false;
      this.req_ipweight = true;
    //  this.reqmsg += 'Please enter  PrimaryIntPacking Packing Type Name, PrimaryIntPacking Packing UOM Code'
        this.req_typeCode = true;
      return false;
    }    else if ( (this.PrimaryIntPacking.internalPackingTypeCode === null || this.PrimaryIntPacking.internalPackingTypeCode === undefined)
      && (this.PrimaryIntPacking.internalPackingTypeName !== null || this.PrimaryIntPacking.internalPackingTypeName !== undefined)
   && (this.PrimaryIntPacking.internalPackingWeight !== null || this.PrimaryIntPacking.internalPackingWeight !== undefined)
    && (this.PrimaryIntPacking.internalPackingUomCode === null || this.PrimaryIntPacking.internalPackingUomCode === undefined)) {
      this.req_ipcode = true;
      this.req_ipname = false;
      this.req_ipuomcode = false;
      this.req_ipweight = true;
     // this.reqmsg += 'Please enter  PrimaryIntPacking Packing Type Name,PrimaryIntPacking Packing Weight'
        this.req_typeCode = true;
      return false;
    }    else if ( (this.PrimaryIntPacking.internalPackingTypeCode === null || this.PrimaryIntPacking.internalPackingTypeCode === undefined)
      && (this.PrimaryIntPacking.internalPackingTypeName === null || this.PrimaryIntPacking.internalPackingTypeName === undefined)
    && (this.PrimaryIntPacking.internalPackingWeight !== null || this.PrimaryIntPacking.internalPackingWeight !== undefined)
    && (this.PrimaryIntPacking.internalPackingUomCode !== null || this.PrimaryIntPacking.internalPackingUomCode !== undefined))  {
      this.req_ipcode = true;
      this.req_ipname = true;
      this.req_ipuomcode = false;
      this.req_ipweight = false;
      // this.reqmsg += 'Please enter PrimaryIntPacking Packing Type Code, PrimaryIntPacking Packing Type Name'
        this.req_typeCode = true;
      return false;
    }	else if ( (this.PrimaryIntPacking.internalPackingTypeCode === null || this.PrimaryIntPacking.internalPackingTypeCode === undefined)
      && (this.PrimaryIntPacking.internalPackingTypeName !== null || this.PrimaryIntPacking.internalPackingTypeName !== undefined)
    && (this.PrimaryIntPacking.internalPackingWeight === null || this.PrimaryIntPacking.internalPackingWeight === undefined)
    && (this.PrimaryIntPacking.internalPackingUomCode !== null || this.PrimaryIntPacking.internalPackingUomCode !== undefined)) {
      this.req_ipcode = true;
      this.req_ipname = false;
      this.req_ipuomcode = true;
      this.req_ipweight = false;
    //   this.reqmsg += 'Please enter PrimaryIntPacking Packing Type Code, PrimaryIntPacking Packing Weight'
      this.req_typeCode = true;
      return false;
    }  else if ( (this.PrimaryIntPacking.internalPackingTypeCode !== null || this.PrimaryIntPacking.internalPackingTypeCode !== undefined)
      && (this.PrimaryIntPacking.internalPackingTypeName !== null || this.PrimaryIntPacking.internalPackingTypeName !== undefined)
    && (this.PrimaryIntPacking.internalPackingWeight !== null || this.PrimaryIntPacking.internalPackingWeight !== undefined)
    && (this.PrimaryIntPacking.internalPackingUomCode === null || this.PrimaryIntPacking.internalPackingUomCode === undefined)) {
      this.req_ipcode = false;
      this.req_ipname = false;
      this.req_ipuomcode = false;
      this.req_ipweight = true;
      this.reqmsg += 'Please enter  PrimaryIntPacking Packing UOM Code'
        this.req_typeCode = true;
      return false;
    }	 else if ( (this.PrimaryIntPacking.internalPackingTypeCode === null || this.PrimaryIntPacking.internalPackingTypeCode === undefined)
      && (this.PrimaryIntPacking.internalPackingTypeName !== null || this.PrimaryIntPacking.internalPackingTypeName !== undefined)
   && (this.PrimaryIntPacking.internalPackingWeight !== null || this.PrimaryIntPacking.internalPackingWeight !== undefined)
    && (this.PrimaryIntPacking.internalPackingUomCode !== null || this.PrimaryIntPacking.internalPackingUomCode !== undefined)) {
      this.req_ipcode = true;
      this.req_ipname = false;
      this.req_ipuomcode = false;
      this.req_ipweight = false;
      this.reqmsg += 'Please enter PrimaryIntPacking Packing Type Code'
        this.req_typeCode = true;
      return false;
    }	 else if ( (this.PrimaryIntPacking.internalPackingTypeCode !== null || this.PrimaryIntPacking.internalPackingTypeCode !== undefined)
      && (this.PrimaryIntPacking.internalPackingTypeName === null || this.PrimaryIntPacking.internalPackingTypeName === undefined)
    && (this.PrimaryIntPacking.internalPackingWeight !== null || this.PrimaryIntPacking.internalPackingWeight !== undefined)
    && (this.PrimaryIntPacking.internalPackingUomCode !== null || this.PrimaryIntPacking.internalPackingUomCode !== undefined)) {
      this.req_ipcode = false;
      this.req_ipname = true;
      this.req_ipuomcode = false;
      this.req_ipweight = false;
    //  this.reqmsg += ' Please enter PrimaryIntPacking Packing Type Name'
        this.req_typeCode = true;
      return false;
    }	else if ( (this.PrimaryIntPacking.internalPackingTypeCode !== null || this.PrimaryIntPacking.internalPackingTypeCode !== undefined)
      && (this.PrimaryIntPacking.internalPackingTypeName !== null || this.PrimaryIntPacking.internalPackingTypeName !== undefined)
   && (this.PrimaryIntPacking.internalPackingWeight === null || this.PrimaryIntPacking.internalPackingWeight === undefined)
    && (this.PrimaryIntPacking.internalPackingUomCode !== null || this.PrimaryIntPacking.internalPackingUomCode !== undefined)) {
      this.req_ipcode = false;
      this.req_ipname = false;
      this.req_ipuomcode = true;
      this.req_ipweight = false;
   //   this.reqmsg += 'Please enter PrimaryIntPacking Packing Weight'
        this.req_typeCode = true;
      return false;
    }	else if ( (this.PrimaryIntPacking.internalPackingTypeCode !== null || this.PrimaryIntPacking.internalPackingTypeCode !== undefined)
      && (this.PrimaryIntPacking.internalPackingTypeName !== null || this.PrimaryIntPacking.internalPackingTypeName !== undefined)
    && (this.PrimaryIntPacking.internalPackingWeight !== null || this.PrimaryIntPacking.internalPackingWeight !== undefined)
    && (this.PrimaryIntPacking.internalPackingUomCode !== null || this.PrimaryIntPacking.internalPackingUomCode !== undefined))  {
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



 onSubmitBtnClick() {
 //  alert(this.intpackdata.status);

    if (this.intpackdata.status === 'Draft') {
      // this.dialogValue = 'Do you want Active the Financial Calendar ' + this.financialCal.fiscalYear;
      //   this.isConfirmPopupModal = true;
     // alert(JSON.stringify(this.intpackdata));
      this.intpackdata.action = 'ACTIVATE';
      this.subscription = this.internalPackingService.activePrimaryPacking(this.intpackdata)
        .subscribe(data => {
          //   this.successModal.hide();
        //  this.successDialog = 'You have successfully Activated Packing material' + this.PrimaryIntPacking.internalPackingTypeId;
          // this.isconfirmSuccessModal = true;
        //  setTimeout(() => {
         //   this.confirmSuccessModal.hide();
            this.router.navigate([InternalPacking]);
        //  }, 1000);

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

   // alert(status);
    if (status === 'Draft') {
      this.dialogValue = 'Do you want Delete the primary packing ' + this.intpackdata.internalPackingTypeCode;
    } else if (status === 'Active') {
      this.dialogValue = 'Do you want DeActivate the primary packing ' + this.intpackdata.internalPackingTypeCode;
    } else if (status === 'InActive') {
      this.dialogValue = 'Do you want Re-Activate the primary packing ' + this.intpackdata.internalPackingTypeCode;
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
    this.subscription = this.internalPackingService.deletepackingid(this.intpackdata.internalPackingTypeId)
      .subscribe(deletedStatus => {
        this.successModal.hide();
        if (this.intpackdata.statusName === 'Active') {
          this.successDialog = 'You have successfully deactivated primary packing ' + this.intpackdata.internalPackingTypeCode;
        } else if (this.intpackdata.statusName === 'InActive') {
          this.successDialog = 'You have successfully reactivated primary packing ' + this.intpackdata.internalPackingTypeCode;
        } else if (this.intpackdata.statusName === 'Draft') {
          this.successDialog = 'You have successfully deleted primary packing ' + this.intpackdata.internalPackingTypeCode;
        }
     //   this.isconfirmSuccessModal = true;

        setTimeout(() => {
        //  this.isconfirmSuccessModal = false;
          // this.confirmSuccessModal.hide();
          this.router.navigate([InternalPacking]);
        }, 1000);


      });

  }


}
