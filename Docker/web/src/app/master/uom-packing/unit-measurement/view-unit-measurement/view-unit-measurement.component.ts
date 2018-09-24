import { Component, ViewChild, ContentChild,  OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { SelectItem } from '../../../../shared/interface/selectItem';
import { Router } from '@angular/router';
import { UnitMeasurement } from '../unit-measurement';
import { UnitMeasurementService } from '../service/unit-measurement.service';
import { uom } from '../../../../shared/interface/router-links';
import { Subscription } from 'rxjs/Subscription';
import { ModalDirective } from 'ngx-bootstrap/modal';

@Component({
  selector: 'app-view-unit-measurement',
  templateUrl: './view-unit-measurement.component.html',
  styleUrls: ['./view-unit-measurement.component.css']
})
export class ViewUnitMeasurementComponent implements OnInit {
   @ViewChild('duplicate') public duplicate: ModalDirective;
  @ViewChild('filebrowse') public filebrowse: ModalDirective;
  @ViewChild('deletesuccessModal') public deletesuccessModal: ModalDirective;
  @ContentChild('temp') testEl: any;
  public uomdata;
  UomCodeDropDown: SelectItem[]= [];
  uomDataObj: UnitMeasurement = new UnitMeasurement();
  public draft;
  public active;
  public inactive;
  subscription: Subscription;
  updateData: any;
  isEdit: boolean;
  editMode: boolean;
  errormodal: boolean;
  reqmsg1: string;
  reqmsg2: string;
  counter: number;
  public del_id;
  errorMessage: any;
  uomViewByIdData: any;
  uomCode: any;
  status: any;
  deletebyid: boolean;
  req_uomname: boolean;
  req_uomcode: boolean
  isBeforeEdit: boolean;
  public deletedStatus;
  ispagebackPopupModal: boolean;
  isCannotDeletePopup: boolean;
  isUpdatedSuccessPopup: boolean;
  isShowModal: boolean;
  statusMsg: any;
  status_change: boolean;
  constructor(private route: ActivatedRoute, http: Http, private router: Router, private unitMeasurementService: UnitMeasurementService) { }

  ngOnInit() {
    window.scrollTo(0, 0);
    this.errormodal = false;
    this.isBeforeEdit = true;
    this.status_change = false;
      this.isCannotDeletePopup = false;
      this.editMode = true;
       this.deletebyid = false;
      this.uomdata = this.unitMeasurementService.viewbyIdParam;
      if (this.uomdata.statusName === 'Draft') {
        this.draft = true;
        this.active = false;
        this.inactive = false;
      } else if (this.uomdata.statusName === 'Active' ) {
        this.active = true;
        this.draft = false;
        this.inactive = false;
      } else if (this.uomdata.statusName === 'InActive') {
        this.inactive = true;
        this.active = false;
        this.draft = false;
      }
      this.getDropDownList();
  }

  backtoPrev() {
        this.router.navigate([uom]);
  }

  getDropDownList() {
      // this.UomCodeDropDown.slice();shawdowed name
      this.unitMeasurementService.uomDetails.forEach( uomDropDown => {
       if (uomDropDown.statusName === 'Active') {
              this.UomCodeDropDown.push({ label: uomDropDown.uomCode, value: uomDropDown.uomId });
        }
        // alert('==UomCodeDropDown==>' + JSON.stringify(this.UomCodeDropDown));
     });

  }
getStyle() {
    if (this.uomdata.uomCode === '') {
      return '#d43c3c';
     } else {
      return '#cfdee7';
     }
  }

  getStyle1() {
    if (this.uomdata.uomName === '') {
         return '#d43c3c';
    } else {
         return '#cfdee7';
    }

  }
close() {
    this.isCannotDeletePopup = false;
}

  enableEdit() {
    this.isBeforeEdit = false;
    this.editMode = false;
    this.isEdit = true;
    this.getDropDownList();
}

update(uomCode, uomName, uomFullname,
        baseUom, uomConversionFactor, statusName) {
      this.updateUOM(uomCode, uomName, uomFullname,
      baseUom, uomConversionFactor, statusName);
}

  updateUOM(uomCode, uomName, uomFullname,
        baseUom, uomConversionFactor, statusName) {
       if (this.validate( uomCode, uomName)) {

        if (statusName === 'Active') {
              this.statusMsg = 'Activate';
        } else if (statusName === 'InActive') {
              this.statusMsg = 'De-Activate';
        } else if (statusName === 'Draft') {
              this.statusMsg = 'Updated';
              this.uomdata.statusName =  'Active';
        }
        this.uomCode = uomCode;
        this.subscription = this.unitMeasurementService.saveUOM(this.uomdata).subscribe(updateData => {
        this.updateData = updateData;
        this.isShowModal = true;
              setTimeout(() => {this.isShowModal = false; }, 3000);
              this.unitMeasurementService.flag = true;
               this.router.navigate([uom]);

         }, error => {
               // alert(this.unitMeasurementService.errorMessage);
               this.isCannotDeletePopup = true;
             },
            () => console.log('Finished')

            );
      }

   }

   confirm() {
     this.status_change = true;
     // alert("===statusNamestatusName===>"+this.uomdata.statusName);
       if (this.uomdata.statusName === 'Active') {
            //  alert(1);
              this.statusMsg = 'InActivate';
              this.uomdata.statusName =  'InActive';
              this.status = 'Active';
        } else if (this.uomdata.statusName === 'InActive') {
              // alert(2);
              this.statusMsg = 'Activate';
              this.uomdata.statusName =  'Active';
              this.status = 'InActive';
        } else if (this.uomdata.statusName === 'Draft') {
              // alert(3);
              this.statusMsg = 'Activate';
              this.uomdata.statusName =  'Active';
              this.status = 'Draft';
        }
   }

   conf_status_change(event) {
          // this.uomdata = event;
        // alert("==statusName==>"+this.uomdata.statusName);
        this.subscription = this.unitMeasurementService.saveUOM(this.uomdata).subscribe(updateData => {
              this.updateData = updateData;
              this.isShowModal = true;
              setTimeout(() => { this.isShowModal = false; }, 3000);
              this.router.navigate([uom]);
      }, error => {
               // alert(this.unitMeasurementService.errorMessage);
               this.isCannotDeletePopup = true;
             },
            () => console.log('Finished')

            );
   }

  validateNumericsOnly(keyEvent) {
       const result = (keyEvent.which) ? keyEvent.which : keyEvent.keyCode;
       // alert("==result==>"+result);
        /**if(result == 190 || result == 110){
              return true;
        }else if (result == 16 || result > 31 && (result < 48 || result > 57)) {
            alert("Enter only Numeric values");
            return false;
        }else if(result == 17 || result == 67 || result == 86){
           alert("CTRL + C or CTRL + V are not allowed");
           return false;
        }else if(result >= 96 ||  result <= 105){
              return true;
        }**/

          if (result === 110 || result === 190) {
              return true;
          } else if (result === 17 || result === 67 || result === 86) {
           alert('CTRL + C or CTRL + V are not allowed');
           return false;
          } else if (result !== 46 && result > 31
            && (result < 48 || result > 57)) {
             alert('Enter only numeric and Decimals');
             return false;
         }
         return true;
  }

   delete() {
      this.deletebyid = true;
      this.statusMsg = 'Delete';
      this.status = 'Draft';
   }
   conf_delete(id) {
      const param = {'uomId': id};
      // alert("==param==>"+param);
      this.subscription = this.unitMeasurementService.deleteUOMById(param).subscribe(deletedStatus => {
                           this.deletedStatus = deletedStatus;
                           this.deletebyid = false;
                           this.router.navigate([uom]);
                         //  this.deleteSuccessModal = true;
                          // setTimeout(() => {this.deleteSuccessModal = false; }, 3000);

       },
         error => {
            // alert("==error==>"+error);
            // alert(errorMsg[1]);
           // this.errorMessage = error;
            // alert(this.unitMeasurementService.errorMessage);
            this.deletebyid = false;
            this.isCannotDeletePopup = true;
        },
         () => console.log('Finished')
    );
   }

   closedeletepopup() {
     this.deletebyid = false;
     this.status_change = false;
   }

   validate(uomCode, uomName) {
    this.counter = 0;
    this.reqmsg1 = '';
    this.reqmsg2 = '';
    if (uomCode == null) {
      this.req_uomcode = true;
      this.counter++;
     // this.reqmsg1 = 'Enter Uom Code';
      // this.errormodal=true;

    } else if ( uomCode != null ) {
        if ((uomCode.trim() === '') ) {
           this.req_uomcode = true;
        this.counter++;
        } else if ((uomCode.trim().length > 20)) {
        this.req_uomcode = true;
        this.counter++;
      //  this.reqmsg1 = 'Length of Uom Code should be less than 20';
      // this.errormodal=true;

    } else {  this.req_uomcode = false; }

    }
    if (uomName == null) {
      this.req_uomname = true;
      this.counter++;
   //   this.reqmsg2 = 'Enter Uom Name';
     // this.errormodal=true;

    } else if (uomName.trim() != null ) {
        if (uomName.trim() === '') {
        this.req_uomname = true;
        this.counter++;
      //  this.reqmsg2 = 'Enter Uom Name';
      // this.errormodal=true;

      }   else if ((uomName.trim().length > 200)) {

          this.req_uomname = true;
          this.counter++;
        //  this.reqmsg2 = 'Length of Uom Name should be less than 200';
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

}
