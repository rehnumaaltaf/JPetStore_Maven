import { Component, OnInit, AfterViewInit, ViewChild, ViewEncapsulation,
  ContentChild, ChangeDetectorRef  , ElementRef, TemplateRef, ChangeDetectionStrategy } from '@angular/core';
import { ModalDirective } from 'ngx-bootstrap/modal';
// import { FileHolder } from '../image-upload';
import { FileRestrictions, SelectEvent, ClearEvent, RemoveEvent } from '@progress/kendo-angular-upload';
import { CertifyMeasurementService } from '../service/certification.service';
import { master } from '../../../../shared/interface/router-links';
import { Observable } from 'rxjs/Rx';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Subscription } from 'rxjs/Subscription';
import { Router } from '@angular/router';
import { ActivatedRoute , Params } from '@angular/router';
import { EventLogComponent } from '../log';
import {CertificationDetails} from './../certification-details'
import { certify } from '../../../../shared/interface/router-links';
import { addcertify } from '../../../../shared/interface/router-links';
import { CanComponentDeactivate } from '../../../../shared/guards/can-deactivate-guard';

@Component({
  changeDetection: ChangeDetectionStrategy.OnPush,
  selector: 'app-add-certification',
  templateUrl: './add-certification.component.html',
  styleUrls: ['./add-certification.component.css'],
  encapsulation: ViewEncapsulation.None,
})
export class AddCertificationComponent implements OnInit, CanComponentDeactivate {
  @ViewChild('duplicate') public duplicate: ModalDirective;
  @ViewChild('filebrowse') public filebrowse: ModalDirective;
  @ContentChild('temp') testEl: any;
  @ViewChild('deletesuccessModal') public deletesuccessModal: ModalDirective;
  @ViewChild('errorMsgModal') public errorMsgModal: ModalDirective;

  isActionPerformed = false;
  public events: string[] = [];
  public imagePreviews = [];
  public uploadRemoveUrl: string = 'removeUrl';
  public uploadRestrictions: FileRestrictions = {
    allowedExtensions: ['.jpg', '.png', '.bmp', '.jpeg']
  };
  public byte_array:any;
  public duplicatedata:any;
  subscription: Subscription;
  public create_certify: any;
  public certifybody:boolean;
  draftsrc: any;
  public uploadSaveUrl: string = "saveUrl";
  public img: any;
  public subscriptions: Subscription[] = [];
  public messages: string[] = [];
  public message: any;
  public src: any;
  public flag: boolean;
  public imgFlag: boolean;
  public parties: any;
  public msg: any;
  draft: boolean;
  editview: boolean;
  deletedStatus: any;
  createview: boolean;
   prodCertCodeList: string[] = [];
  prodCertNameList: string[] = [];
   minTypeaheadLength = 3;
  public active;
  public inactive;
  editMode: boolean;
  public statusName;
  status_change: boolean;
  deletebyid: boolean;
  isEdit: boolean;
  statusMsg: any;
  activatedRoute: ActivatedRoute;
  status: any;
  del_id: any;
  isActiveStatus: boolean;
  isCannotDeletePopup: boolean;
  deleteStatus: any;
  isDraftStatus: boolean;
  isDeletePopupModal: boolean;
  certName: any;
  certCode: any;
  public id;
  getImagebase64: any;
  public validFromDate: Date;
  public validToDate: Date;
  // public editcertificationdata;
  public editcertificationdata: CertificationDetails = new CertificationDetails();
  public errormodal: boolean;
  counter: number;
  public certId;
  public cert_code: boolean;
  public cert_name: boolean;
  public party_name: boolean;
  public reg_no: boolean;
  public from: boolean;
  public to: boolean;
  datevalidationmsg: boolean;
  errorMsg: string;
  public errormodalList: boolean;
  defaultimg: boolean;
  defaultflag: boolean;

  constructor( private route: ActivatedRoute, http: Http, private router: Router,
    public certifyMeasurementService: CertifyMeasurementService,
    private cdr: ChangeDetectorRef) {
       // alert('in constructor' + http.get('id'));
    }

  ngOnInit() {
     this.defaultimg = true;
     if (sessionStorage.getItem('id') !== null || sessionStorage.getItem('id') !== undefined) {
      // this.id = JSON.parse(sessionStorage.getItem('id'));
     }

     this.route.queryParams.subscribe(params => {
        this.certId = +params['id'];

     })

    sessionStorage.setItem('img', '');
    this.create_certify = {
      'pkProdCertId': '', 'prodCertCode': '',
      'prodCertName': '', 'prodCertDescription': '', 'prodCertLogo': '',
      'fkCertfBodyPartyId': '', 'registrationNumber': '',
      'validFrom': '', 'validTo': '', 'action': ''
    };
    this.loadparty();
    this.errormodalList = false;
   if (this.certifyMeasurementService.editview === false) {
                this.createview = true;
                this.editview = false;
    } else {
    this.createview = false;
      this.certId = this.route.snapshot.params['certId'];
      this.status_change = false;
      this.isCannotDeletePopup = false;
      this.isEdit = true;
      this.deletebyid = false;
      this.editview = true;
      // alert('this.certId' + this.certId)
      if (this.certId !== undefined) {
        this.subscription = this.certifyMeasurementService.getCertDetailsById(this.certId).subscribe(certificateRec => {
          this.editcertificationdata = certificateRec.body;
          this.validFromDate = new Date(this.editcertificationdata.validFrom);
          this.validToDate = new Date(this.editcertificationdata.validTo);
         // alert('--prodCertLogo--->' + this.editcertificationdata.prodCertLogo);
           if ( this.editcertificationdata.prodCertLogo !== null ) {
                           this.defaultflag = true;
              }
                 if (this.editcertificationdata.statusName === 'Draft') {
                      this.draft = true;
                      this.active = false;
                      this.inactive = false;
                 } else if (this.editcertificationdata.statusName === 'Active') {
                      this.active = true;
                      this.draft = false;
                      this.inactive = false;
                } else if (this.editcertificationdata.statusName === 'InActive') {
                      this.inactive = true;
                      this.active = false;
                      this.draft = false;
                }
               },
          error => alert(error),
          () => console.log('Finished')
        );
     }else {
         this.createview = true;
         this.editview = false;
              // alert('id==>' + this.certId);
           }
    }
   // this.editcertificationdata = '';
    //  alert(this.certifyMeasurementService.editview)
    // this.certifyMeasurementService.editByIdParam = '';
  }


  // ngAfterViewInit() {
  //   this.message = 'all done loading :)'
  //   alert('in ngAfterViewInit')
  //   this.cdr.detectChanges();
  // }

  public handler(type: string, $event: ModalDirective) {
    this.imagedata(sessionStorage.getItem('img'));
  }


  loadparty() {
      this.subscription = this.certifyMeasurementService.loadpartyJSON().subscribe(listpartyDetail => {
      this.certifyMeasurementService.listparty = listpartyDetail.body;
      this.parties = listpartyDetail.body;
      // alert('partydropdown-->' + JSON.stringify(this.parties));
     },
      error => alert(error),
      () => console.log('Finished')
      );
  }

  savecertify(action) {
    this.isActionPerformed = true;
    this.counter = 0;
    this.create_certify.action = action;
    const stDate = new Date(this.create_certify.validFrom);
    const enDate = new Date(this.create_certify.validTo);
    const compDate = this.create_certify.validFrom - this.create_certify.validTo;
    this.draftsrc = this.src;
    if (this.src !== undefined) {
      if (this.src !== null || this.src.length !== 1) {
        // alert('this src not undefined' + this.src);
        if (this.src === true) {
          //  alert('in src true ' + this.draftsrc);
          this.byte_array = this.draftsrc.split(',');
          this.create_certify.prodCertLogo = this.byte_array[1];
        } else {
          this.byte_array = this.src.split(',');
          this.create_certify.prodCertLogo = this.byte_array[1];
        }
      }
    }
    if (this.create_certify.fkCertfBodyPartyId === '' || this.create_certify.fkCertfBodyPartyId === null) {
      this.party_name = true;
      // this.errormodal = true;
       this.counter++;
     } else {
       this.party_name = false;
     }
     if (this.create_certify.prodCertCode === '' || this.create_certify.prodCertCode === null) {
       this.cert_code = true;
       this.counter++;
     }else if (this.create_certify.prodCertCode != null) {
        if (this.create_certify.prodCertCode.trim() === '') {
            this.cert_code = true;
            this.counter++;
        }else if (this.create_certify.prodCertCode.trim().length > 20) {
          this.cert_code = true;
          this.counter++;
        } else {
           this.cert_code = false;
     }}

     if (this.create_certify.prodCertName === '' || this.create_certify.prodCertName === null) {
       this.cert_name = true;
       this.counter++;
     }else if (this.create_certify.prodCertName != null) {
        if (this.create_certify.prodCertName.trim() === '') {
            this.party_name = true;
            this.counter++;
        }else if (this.create_certify.prodCertName.trim().length > 200) {
          this.party_name = true;
          this.counter++;
        }else {
       this.cert_name = false;
     }
     }
     if (this.create_certify.registrationNumber === '' || this.create_certify.registrationNumber === null) {
       this.reg_no = true;
       this.counter++;
     }else if (this.create_certify.registrationNumber != null) {
      if (/[^a-zA-Z0-9\-\s\/]/.test(this.create_certify.registrationNumber)) {
        this.reg_no = true;
        this.counter++;
      } else {
        this.reg_no = false;
      }
    }
    if (this.create_certify.validFrom === '') {
         this.from = true;
         this.counter++;
     } else {
       this.from = false;
     }
      // alert("validto--->"+ this.create_certify.validTo);
     if (this.create_certify.validTo === '') {
       this.to = true;
       this.datevalidationmsg = false;
       // this.errormodal = true;
      this.counter++;
    } else if (compDate >= 0) {
       this.to = false;
       this.datevalidationmsg = true;
       this.counter++;
     } else {
       this.to = false;
       this.datevalidationmsg = false;
     }

   //  alert( "7" + this.counter);
      if (this.counter !== 0) {
        this.showModal();

         setTimeout(() => {this.hideModal(); }, 1000);
      }
      //  }else {
      //    return false;
      //  }
     //  alert("8");
if ( this.party_name === false && this.cert_code === false && this.cert_name === false
  && this.reg_no === false && this.from === false && this.to === false && this.datevalidationmsg === false) {
    sessionStorage.setItem('cer_code', this.create_certify.prodCertName);
    this.subscription = this.certifyMeasurementService.addcertifyJSON(this.create_certify).subscribe(listcertifyDetail => {
      this.certifyMeasurementService.addparam = listcertifyDetail;
      this.router.navigate([certify], { queryParams: { 'success': 1 } });
     },
      error => {
         this.errorMsg = error;
         // alert('--errorMsg-->' + this.errorMsg);
               // this.showModal();
                this.errorModalList();
              //  setTimeout(() => {this.hideModal(); }, 1000);
      },
      () => console.log('Finished')
      );
        }

   }

public errorModalList(): void {
    this.errormodalList = true;
  }
  numberonly(evt) {
    const charCode = (evt.which) ? evt.which : evt.keyCode;
    const actualkey = String.fromCharCode(charCode);
    // alert('actualkey-->' + actualkey + '---charCode-->' + charCode);
    if (charCode === 45) {
      return true;
    } else if (charCode !== 46 && charCode > 31 && (charCode < 48 || charCode > 57)) {
      return false;
    }
    return true;
  }
public showModal(): void {
    this.errormodal = true;
  }

   public hideModal(): void {
      this.deletesuccessModal.hide();
      this.errormodalList = false;
   }

  public onHidden(): void {
    this.errormodal = false;
  }

  onHiddenErrorMsg(): void {
    this.errormodalList = false;
  }

submit(editcertificationdata) {
  this.statusName = editcertificationdata.statusName;
 // alert('--statusName--' + this.statusName);
}

update(editcertificationdata, updatedFromDate, updatedToDate) {
    this.isActionPerformed = true;
 // alert('in update');
  this.counter = 0;
  const compDate = editcertificationdata.validFrom - editcertificationdata.validTo;
//  alert('editcertificationdata' + JSON.stringify(editcertificationdata));
 sessionStorage.setItem('cer_code', editcertificationdata.prodCertName);
      sessionStorage.setItem('status', editcertificationdata.statusName);

      if (!this.defaultimg) {
        editcertificationdata.prodCertLogo = '';

    } else if (sessionStorage.getItem('img').length !== 1) {
        // alert('in if')
        this.getImagebase64 = sessionStorage.getItem('img').split(',');
        editcertificationdata.prodCertLogo = this.getImagebase64[1];
     }
     if (editcertificationdata.fkCertfBodyPartyId === '') {
       this.party_name = true;
       this.counter++;
     }else {
       this.party_name = false;
     }
      if (editcertificationdata.prodCertCode === '') {
       this.cert_code = true;
       this.counter++;
     }else if (editcertificationdata.prodCertCode != null) {
        if (editcertificationdata.prodCertCode.trim() === '') {
            this.cert_code = true;
            this.counter++;
        }else if (editcertificationdata.prodCertCode.trim().length > 20) {
          this.cert_code = true;
          this.counter++;
        } else {
       this.cert_code = false;
     }}

     if (editcertificationdata.prodCertName === '') {
       this.cert_name = true;
       this.counter++;
     }else if (editcertificationdata.prodCertName != null) {
        if (editcertificationdata.prodCertName.trim() === '') {
            this.cert_name = true;
            this.counter++;
        }else if (editcertificationdata.prodCertName.trim().length > 200) {
          this.cert_name = true;
          this.counter++;
        }else {
       this.cert_name = false;
     }
     }
     if (editcertificationdata.registrationNumber === '') {
       this.reg_no = true;
       this.counter++;
     }else if (/[^a-zA-Z0-9\-\s\/]/.test(this.create_certify.registrationNumber)){
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
       this.datevalidationmsg = false;
       // this.errormodal = true;
      this.counter++;
    } else if (compDate >= 0) {
      this.to = false;
      this.datevalidationmsg = true;
       this.counter++;
     } else {
       this.to = false;
       this.datevalidationmsg = false;
     }
      if (this.counter !== 0) {
        this.showModal();

         setTimeout(() => {this.hideModal(); }, 1000);
       }
      //   else {
      //    return false;
      //  }

if ( this.party_name === false && this.cert_code === false && this.cert_name === false
  && this.reg_no === false && this.from === false && this.to === false && this.datevalidationmsg === false) {
    this.editcertificationdata.validFrom = updatedFromDate;
  this.editcertificationdata.validTo = updatedToDate;
  // alert('fromdate1-->' + editcertificationdata.validFrom);
  this.subscription = this.certifyMeasurementService.updatecertifyJSON(editcertificationdata).subscribe(submit_detail => {
      this.certifyMeasurementService.addparam = submit_detail;
      this.router.navigate([certify], { queryParams: { 'success': 2 } });
      },
      error => {
        /*this.message = JSON.parse(error._body).errorMessage;
        this.duplicate.show();*/
          this.errorMsg = error;
                this.errorModalList();
                setTimeout(() => {this.hideModal(); }, 1000);
      },
      () => console.log('Finished')
      );
        }

}

onChange(event) {
  alert('onchange');
}

gotolistingPage() {
  this.router.navigate([certify]);
}

  updatewithstatus(editcertificationdata, updatedFromDate, updatedToDate) {
   // alert('--statusName-->' + editcertificationdata.statusName)
    this.isActionPerformed = true;
    if (editcertificationdata.statusName === 'Active') {
      sessionStorage.setItem('status_check', '1');
    } else if (editcertificationdata.statusName === 'InActive') {
      sessionStorage.setItem('status_check', '2');
    }
    this.status_change = true;
    this.statusName = editcertificationdata.statusName;

  this.editcertificationdata.validFrom = updatedFromDate;
  this.editcertificationdata.validTo = updatedToDate;

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
              this.editcertificationdata.statusName =  'InActive';
              this.status = 'Active';
        } else if (this.statusName === 'InActive') {
                // alert(2);
              this.statusMsg = 'Activate';
              this.editcertificationdata.statusName =  'Active';

              this.status = 'InActive';
        } else if (this.statusName === 'Draft') {
              // alert(3);
              this.statusMsg = 'Activate';
              this.editcertificationdata.statusName =  'Active';
              this.status = 'Draft';
        }

 // alert('editcertificationdata' + JSON.stringify(editcertificationdata));
}

conf_status_change(event) {
      // this.statusName = this.editcertificationdata.statusName;
      this.statusName = event.statusName;
      this.deletebyid = false;
      // alert('conf statusName===>' + this.statusName);
      this.subscription = this.certifyMeasurementService.updatecertifyJSON(this.editcertificationdata).subscribe(submit_detail => {
      this.certifyMeasurementService.addparam = submit_detail;
      this.router.navigate([certify], { queryParams: { 'success': 1 } });
      },
      error => {
        this.message = JSON.parse(error._body).errorMessage;
        this.duplicate.show();
      },
      () => console.log('Finished')
      );

}

closedeletepopup() {
     this.deletebyid = false;
     this.status_change = false;
   }

submitcertify() {
  this.create_certify.action = "SAVE";
   if (this.src !== undefined ) {
  this.byte_array = this.src.split(",");
  this.create_certify.prodCertLogo=this.byte_array[1];
   }
   if (this.create_certify.fkCertfBodyPartyName === ''){
       this.party_name=true;
     }
     else {
       this.party_name=false;
     }
     if (this.create_certify.prodCertCode === ''){
       this.cert_code=true;
     }
     else{
       this.cert_code=false;
     }

     if (this.create_certify.prodCertName === ''){
       this.cert_name=true;
     }
     else{
       this.cert_name=false;
     }
     if (this.create_certify.registrationNumber === ''){
       this.reg_no=true;
     }
     else{
       this.reg_no=false;
     }
     if (this.create_certify.validFrom === ''){
       this.from=true;
     }
     else{
       this.from=false;
     }
     if (this.create_certify.validTo === ''){
       this.to=true;
     }
     else{
       this.to=false;
     }

if( this.party_name===false && this.cert_code===false && this.cert_name===false && this.reg_no===false && this.from===false && this.to===false){
  this.subscription = this.certifyMeasurementService.submitcertifyJSON(this.create_certify).subscribe(submit_detail => {
      this.certifyMeasurementService.addparam = submit_detail;
      this.router.navigate([certify], { queryParams: { 'success': 1 } });
     },
      error => {
        /*this.message = JSON.parse(error._body).errorMessage;
        this.duplicate.show();*/
          this.errorMsg = error;
                this.errorModalList();
                setTimeout(() => {this.hideModal(); }, 1000);  
      },
      () => console.log('Finished')
      );
        }

}

  public clearEventHandler(e: ClearEvent): void {
    this.imagePreviews = [];
    sessionStorage.setItem("img", "");
    this.flag = false;

  }
  public upload() {
    if (this.editview) {
     this.createview = false;
     this.defaultimg = true;
     this.defaultflag = false;
    }else {
      this.editview = false;
      this.createview = true;
    }
    this.filebrowse.hide();
    this.flag = true;
    this.imgFlag = true;
  }


  delete() {
      this.deletebyid = true;
      this.statusMsg = 'Delete';
      this.status = 'Draft';
 }

  cleardefaultimg() {
    this.defaultimg = false;
   }

  certificate_delete(event, certCode, certName, statusName) {
    this.del_id = event;
   this.status = statusName;
   if (this.status === 'Active') {
       this.isActiveStatus = true;
       this.deleteStatus = 'InActivate';
   } else if (this.status === 'Draft') {
        this.isDraftStatus = true;
        this.deleteStatus = 'Delete';
   } else if (this.status === 'InActive') {
        this.deleteStatus = 'Activate';
   }

   this.certCode = certCode;
   this.certName = certName;
   this.isDeletePopupModal = true;
}
  clearimage(event) {
     this.imagePreviews = [];
     sessionStorage.setItem("img", "");
     this.flag = false;
     this.party_name = false;
     this.cert_code = false;
     this.cert_name = false;
     this.reg_no = false;
     this.from = false;
     this.to = false;
     this.datevalidationmsg = false;
     if (this.createview) {
       this.createview = true;
       this.editview = false;
    }else if (this.editview) {
       this.createview = false;
       this.editview = true;
    }
  }

 conf_delete(id, code, status) {
    sessionStorage.setItem('cer_code',code);
      sessionStorage.setItem('status',status);
      const param = {'pkProdCertId': id};
      // alert("==param==>" + param);
      this.subscription = this.certifyMeasurementService.deleteCertificateById(param).subscribe(deletedStatus => {
                           this.deletedStatus = deletedStatus;
                           this.deletebyid = false;
                            this.router.navigate([certify], { queryParams: { 'success': 0 } });
                         //  this.deleteSuccessModal = true;
                          // setTimeout(() => {this.deleteSuccessModal = false; }, 3000);

       },
         error => {
            this.deletebyid = false;
            this.isCannotDeletePopup = true;
        },
         () => console.log('Finished')
    );
   }

  public clearimg(e: ClearEvent) {
    this.imagePreviews = [];
    sessionStorage.setItem("img", "");
    this.flag = false;
    this.clearEventHandler(e);
  }

  public completeEventHandler() {
    // this.log(`All files processed`);
  }

  public selectEventHandler(e: SelectEvent): void {
    if (this.imagePreviews.length === 0) {
      //this.flag=true;
      let that = this;
      e.files.forEach((file) => {
        //that.log(`File selected: ${file.name}`);

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

  canDeactivate(): boolean {
    return this.isActionPerformed;
  }



}
