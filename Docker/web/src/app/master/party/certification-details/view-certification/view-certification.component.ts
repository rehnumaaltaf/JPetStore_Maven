import { Component, OnInit, ViewChild, ViewEncapsulation, ContentChild, ElementRef, TemplateRef } from '@angular/core';
import { FileRestrictions, SelectEvent, ClearEvent, RemoveEvent } from '@progress/kendo-angular-upload';
import { CertifyMeasurementService } from '../service/certification.service';
import { Subscription } from 'rxjs/Subscription';
import { ModalDirective } from 'ngx-bootstrap/modal';
import { Router, ActivatedRoute } from '@angular/router';
import { certify } from '../../../../shared/interface/router-links';
import { SelectItem } from '../../../../shared/interface/selectItem';
import { CertificationDetails } from './../certification-details';

@Component({
  selector: 'app-view-certification',
  templateUrl: './view-certification.component.html',
  styleUrls: ['./view-certification.component.css'],
  encapsulation: ViewEncapsulation.None,
})
export class ViewCertificationComponent implements OnInit {
  @ViewChild('autoShownModal') public autoShownModal: ModalDirective;
  @ViewChild('deletecer') public deletecer: ModalDirective;
  @ViewChild('del') public del: ModalDirective;
  @ViewChild('filebrowse') public filebrowse: ModalDirective;
  @ViewChild('duplicate') public duplicate: ModalDirective;
  @ViewChild('statuschange') public statuschange: ModalDirective;
  @ViewChild('deletesuccessModal') public deletesuccessModal: ModalDirective;
   @ViewChild('profileImage') profileImage;
  public isModalShown: boolean = false;
  public delbyid: boolean = false;
  public status_changes: boolean = false;
  public deletebyid: boolean = false;
  @ContentChild('temp') testEl: any;
  public dupmsg: boolean = false;
  public events: string[] = [];
  public imagePreviews = [];
  public uploadRemoveUrl: string = "removeUrl";
  public uploadRestrictions: FileRestrictions = {
    allowedExtensions: [".jpg", ".png"]
  };
  public certificationdata: CertificationDetails = new CertificationDetails();
 // public certificationdata;
  public uomdata;
  draft: boolean;
  public active;
  public inactive;
  subscription: Subscription;
  updateData: any;
  isEdit: boolean;
  editMode: boolean;
  public del_id;
  errorMessage: any;
  defaultflag: boolean;
  uomViewByIdData: any;
  public certId;
  uomCode: any;
  status: any;
  isBeforeEdit: boolean;
  public deletedStatus;
  ispagebackPopupModal: boolean;
  isCannotDeletePopup: boolean;
  isUpdatedSuccessPopup: boolean;
  defaultimg: boolean;
  imgsrc: string;
  byte_array: any;
  isShowModal: boolean;
  certifyingBodyDropDown: SelectItem[] = [];
  statusMsg: any;
  status_change: boolean;
  public flag: boolean;
  public src: any;
  public validFromDate: Date;
  getImagebase64: any;
  public validToDate: Date;
  imgFlag: boolean;
  public parties: any;
  toArray: any;
  message: any;
  statusName: any;
  public errormodal: boolean;
  public id: any;
  counter: number;
  public cert_code: boolean; public cert_name: boolean; public party_name: boolean; public reg_no: boolean;
  public from: boolean; public to: boolean;
  constructor(private route: ActivatedRoute,
     public certifyMeasurementService: CertifyMeasurementService, private router: Router, private element: ElementRef) { }

  ngOnInit() {
    sessionStorage.setItem('img', ' ');
    this.defaultimg = true;
    this.certId = this.route.snapshot.params['certId'];
    this.getCertDetailsById(this.certId);
    this.isBeforeEdit = true;
    this.status_change = false;
    this.isCannotDeletePopup = false;
    this.editMode = true;
    this.deletebyid = false;
    this.loadparty();
  }

  backtoPrev() {
    this.router.navigate([certify]);
  }

  enableEdit() {
    this.isBeforeEdit = false;
    this.editMode = false;
    this.isEdit = true;
  }

  confirm() {

  }

  delete() {
    this.delbyid = true;
    this.statusMsg = 'Delete';
    this.status = 'Draft';
  }

  getCertDetailsById(certId) {
   // alert('--certId-->' + certId);
    this.subscription = this.certifyMeasurementService.getCertDetailsById(certId).subscribe(certificateRec => {
      this.certificationdata = certificateRec.body;
      this.validFromDate = new Date(this.certificationdata.validFrom);
      this.validToDate = new Date(this.certificationdata.validTo);
      if ( this.certificationdata.prodCertLogo !== null ) {
          this.defaultflag = true;
      }
     // this.defaultflag = true;
      if (this.certificationdata.statusName === 'Draft') {
        this.draft = true;
        this.active = false;
        this.inactive = false;
      } else if (this.certificationdata.statusName === 'Active') {
        this.active = true;
        this.draft = false;
        this.inactive = false;
      } else if (this.certificationdata.statusName === 'InActive') {
        this.inactive = true;
        this.active = false;
        this.draft = false;
      }

    },
      error => alert(error),
      () => console.log('Finished')
    );
  }


  update(editcertificationdata, updatedFromDate, updatedToDate) {
    sessionStorage.setItem('cer_code', editcertificationdata.prodCertName);
    sessionStorage.setItem('status', editcertificationdata.statusName);
   // alert(this.defaultimg);

    if (!this.defaultimg) {
        editcertificationdata.prodCertLogo = '';

    } else if (sessionStorage.getItem('img').length !== 1) {
      // alert('in if')
      this.getImagebase64 = sessionStorage.getItem('img').split(',');
      editcertificationdata.prodCertLogo = this.getImagebase64[1];
    }
    this.counter = 0;
    if (editcertificationdata.fkCertfBodyPartyId == null) {
      this.party_name = true;
      this.counter++;
    } else {
      this.party_name = false;
    }
    if (editcertificationdata.prodCertCode === '') {
      this.cert_code = true;
      this.counter++;
    } else if (editcertificationdata.prodCertCode != null) {
      if (editcertificationdata.prodCertCode.trim() === '') {
        this.cert_code = true;
        this.counter++;
      } else if (editcertificationdata.prodCertCode.trim().length > 20) {
        this.cert_code = true;
        this.counter++;
      } else {
        this.cert_code = false;
      }
    }

    if (editcertificationdata.prodCertName === '') {
      this.cert_name = true;
      this.counter++;
    } else if (editcertificationdata.prodCertName != null) {
      if (editcertificationdata.prodCertName.trim() === '') {
        this.cert_name = true;
        this.counter++;
      } else if (editcertificationdata.prodCertName.trim().length > 200) {
        this.cert_name = true;
        this.counter++;
      } else {
        this.cert_name = false;
      }
    }
    if (editcertificationdata.registrationNumber === '') {
      this.reg_no = true;
      this.counter++;
    } else if (/[^a-zA-Z0-9\-\s\/]/.test(editcertificationdata.registrationNumber)) {
      this.reg_no = true;
      this.counter++;
    } else {
      this.reg_no = false;
    }
    if (editcertificationdata.validFrom === '') {
      this.from = true;
      this.counter++;
    } else {
      this.from = false;
    }
    if (editcertificationdata.validTo === '') {
      this.to = true;
      this.counter++;
    } else {
      this.to = false;
    }
    if (this.counter !== 0) {
      this.showModal();

      setTimeout(() => { this.hideModal(); }, 1000);
    }
    if (this.party_name === false && this.cert_code === false && this.cert_name === false
      && this.reg_no === false && this.from === false && this.to === false) {
      // alert('in viewbyid fromdate' + updatedFromDate);
      editcertificationdata.validFrom = updatedFromDate;
      // alert('editcertificationdata from' + editcertificationdata.validFrom);
      editcertificationdata.validTo = updatedToDate;
    //  alert('--editcertificationdata Param b4 update--' + JSON.stringify(editcertificationdata))
      this.subscription = this.certifyMeasurementService.updatecertifyJSON(editcertificationdata).subscribe(submit_detail => {
        this.certifyMeasurementService.addparam = submit_detail;
        // alert('--statusName in view-->' + status)
        if (editcertificationdata.statusName === 'InActive') {
          this.router.navigate([certify], { queryParams: { 'success': 3, 'statusName': editcertificationdata.statusName } });
        } else if (editcertificationdata.statusName === 'Active') {
          this.router.navigate([certify], { queryParams: { 'success': 2, 'statusName': editcertificationdata.statusName } });
        } else {
          this.router.navigate([certify], { queryParams: { 'success': 1, 'statusName': editcertificationdata.statusName } });
        }
      },
        error => {
          this.message = JSON.parse(error._body).errorMessage;
          // alert(this.message);
          // this.dupmsg=true;
          this.isModalShown = true;
        },
        () => console.log('Finished')
      );
    }

  }

  public showModal(): void {
    this.errormodal = true;
  }

  hideModal() {
    this.deletesuccessModal.hide();
    this.autoShownModal.hide();
  }

  submithide() {
    this.deletecer.hide();
  }

  public onHidden(): void {
    this.isModalShown = false;
    this.delbyid = false;
    this.errormodal = false;
  }



  updatewithstatus(editcertificationdata, updatedFromDate, updatedToDate) {
    // this.status_changes = true;
    if (editcertificationdata.statusName === 'Active') {
      sessionStorage.setItem('status_check', '1');
    } else if (editcertificationdata.statusName === 'InActive') {
      sessionStorage.setItem('status_check', '2');
    }
    this.deletebyid = true;
    this.statusName = editcertificationdata.statusName;
    editcertificationdata.validFrom = updatedFromDate;
    editcertificationdata.validTo = updatedToDate;
    if (!this.defaultimg) {
        editcertificationdata.prodCertLogo = '';

    } else if (sessionStorage.getItem('img').length !== 1) {
      // alert('in if')
      this.getImagebase64 = sessionStorage.getItem('img').split(',');
      editcertificationdata.prodCertLogo = this.getImagebase64[1];
    }
    // alert("===statusNamestatusName===>"+this.uomdata.statusName);
    if (this.statusName === 'Active') {
      // alert(1);
      this.statusMsg = 'InActivate';
      this.certificationdata.statusName = 'InActive';
      this.status = 'Active';
    } else if (this.statusName === 'InActive') {
      // alert(2);
      this.statusMsg = 'Activate';
      this.certificationdata.statusName = 'Active';
      this.status = 'InActive';
    } else if (this.statusName === 'Draft') {
      // alert(3);
      this.statusMsg = 'Activate';
      this.certificationdata.statusName = 'Active';
      this.status = 'Draft';
    }
    // alert('editcertificationdata' + JSON.stringify(editcertificationdata));
  }


  conf_status_change(certificationdata) {
    // alert('--statusName--->'+JSON.stringify(certificationdata.statusName));
    // this.statuschange.hide();
    // alert('in status chnage');
    this.subscription = this.certifyMeasurementService.updatecertifyJSON(certificationdata).subscribe(submit_detail => {
      this.certifyMeasurementService.addparam = submit_detail;
    },
      error => {
        this.message = JSON.parse(error._body).errorMessage;
        // alert(this.message);
        // this.dupmsg=true;
      },
      () => console.log('Finished')
    );
  }


  closedeletepopup() {
    this.del.hide();
    // this.status_change = false;
    // this.status_changes = false;
  }

  conf_delete(id, status, code) {
    sessionStorage.setItem('cer_code', code);
    sessionStorage.setItem('status', status);
    const param = { 'pkProdCertId': id };
    this.subscription = this.certifyMeasurementService.deleteCertificateById(param).subscribe(deletedStatus => {
      this.deletedStatus = deletedStatus;
      this.del.hide();
      // this.router.navigate([certify]);
      this.router.navigate([certify], { queryParams: { 'success': 0 } });
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

  public handler(type: string, $event: ModalDirective) {
    this.imagedata(sessionStorage.getItem("img"));
  }

  public clearEventHandler(e: ClearEvent): void {
    this.imagePreviews = [];
    sessionStorage.setItem('img', '');
    // alert('session img--->' + sessionStorage.getItem('img'));
    this.flag = false;

  }
  public upload() {
    this.defaultimg = true;
    this.defaultflag = false;
    this.filebrowse.hide();
    this.flag = true;
    this.imgFlag = true;
  }

  public clearimg(e: ClearEvent) {
    this.imagePreviews = [];
    sessionStorage.setItem('img', '');
    this.flag = false;
    this.clearEventHandler(e);
  }

  cleardefaultimg() {
    this.defaultimg = false;
   }

  public selectEventHandler(e: SelectEvent): void {
   if (this.imagePreviews.length === 0) {
      // this.flag=true;
      let that = this;
      e.files.forEach((file) => {
        // that.log(`File selected: ${file.name}`);

        if (!file.validationErrors) {
          let reader = new FileReader();

          reader.onload = function (ev) {
            let image = {
              src: this.result,
              uid: file.uid
            };

            sessionStorage.setItem("img", this.result);
            that.imagePreviews.unshift(image);

          };

          reader.readAsDataURL(file.rawFile);

        }
      });

    } else {
      alert('Please remove the selected file and upload');
    }

  }

  imagedata(data) {
    this.src = data;
  }

  private log(event: string, arg: any): void {
    this.events.unshift(`${event}`);
  }

  browse() {
    this.filebrowse.show();
  }

  loadparty() {
    this.subscription = this.certifyMeasurementService.loadpartyJSON().subscribe(listpartyDetail => {
      this.certifyMeasurementService.listparty = listpartyDetail.body;
      this.parties = listpartyDetail.body;
    },
      error => alert(error),
      () => console.log('Finished')
    );
    // this.UomCodeDropDown.slice();shawdowed name
    //  this.certifyMeasurementService.certificationDetails.forEach( certifyingBody => {
    //   if (certifyingBody.statusName === 'Active') {
    //        this.certifyingBodyDropDown.push({ label: certifyingBody.prodCertCode, value: certifyingBody.pkProdCertId });
    //   }
    // alert('==UomCodeDropDown==>' + JSON.stringify(this.UomCodeDropDown));
    // });

  }

}
