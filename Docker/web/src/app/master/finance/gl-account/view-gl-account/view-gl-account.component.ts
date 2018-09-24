import { Component, Inject, OnInit, OnDestroy, Output, Input, ViewEncapsulation, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Router } from '@angular/router';
import { GLAccount } from '../gl-account';
import { GlAccountService } from '../service/gl-account.service';
import { Gl } from '../../../../shared/interface/router-links';
import { Subscription } from 'rxjs/Subscription';
import { GlBasicDTO } from '../gl-basic';
import { ExternalGLMappingDto } from '../gl-externalmapping';
import { ModalModule } from 'ngx-bootstrap';
import { ModalDirective } from 'ngx-bootstrap/modal';
import { editgl } from '../../../../shared/interface/router-links';

@Component({
  selector: 'app-view-gl-account',
  templateUrl: './view-gl-account.component.html',
  styleUrls: ['./view-gl-account.component.css']
})
export class ViewGlAccountComponent implements OnInit {
  glData: GLAccount = new GLAccount();
    public status: any = {
    isFirstOpen: true,
    isFirstDisabled: false
  };
  public draft;
  public active;
  public inactive;
  subscription: Subscription;
  updateData: any;
  isEdit: boolean;
  editMode: boolean;
  public gl_Id;
  errorMessage: any;
  glCode: any;
  glName: any;
  public glDataObj;
  public featureList1 = [];
  public moduleNameList = [];
  public NameList = [];
  deletebyid: boolean;
  public deletedStatus;
  ispagebackPopupModal: boolean;
  isCannotDeletePopup: boolean;
  isUpdatedSuccessPopup: boolean;
  isShowModal: boolean;
  statusMsg: any;
  status_change: boolean;
   isDeletePopupModal: boolean;
  deactivateSuccessModal: boolean;
  deleteSuccessModal: boolean;
  isDeactivatePopupModal: Boolean = false;
  public savedData;
  isActiveStatus: boolean;
  isDraftStatus: boolean;
  @ViewChild('errormsg') public errormsg: ModalDirective;
  @ViewChild('statusChangeConfirm') public statusChangeConfirm: ModalDirective;
  @ViewChild('deletepopup') public deletepopup: ModalDirective;
  @ViewChild('outturnratiodelete') public outturnratiodelete: ModalDirective;

  constructor(private route: ActivatedRoute, http: Http, private router: Router, public glAccountService: GlAccountService) { }

  ngOnInit() {
    this.editMode = true;
    this.glData.glBasicDto.glId = this.route.snapshot.params['glId'];
    this.glDataObj = this.route.snapshot.params['glId'];
    this.subscription = this.glAccountService.getSingleGlListJSON(this.glDataObj).subscribe(glData => {
      this.glData = glData.body;
      console.log('' + JSON.stringify(this.glData))
      alert('status name in view' +  this.glData.statusName)
      if (this.glData.statusName === 'Draft') {
        this.draft = true;
        this.active = false;
        this.inactive = false;
      } else if (this.glData.statusName === 'Active' ) {
        this.active = true;
        this.draft = false;
        this.inactive = false;
      } else if (this.glData.statusName === 'InActive') {
        this.inactive = true;
        this.active = false;
        this.draft = false;
      }

      for (let i = 0; i < this.glData.externalGLMappingDto.length; i++) {
        const featureVal = this.glData.externalGLMappingDto[i].externalSystemRefId;
        // tslint:disable-next-line:max-line-length
        const moduleName = this.glData.externalGLMappingDto[i].externalSystemRefName;

        const Name = this.glData.externalGLMappingDto[i].externalSystemRefCode;
        // tslint:disable-next-line:max-line-length
        this.featureList1.push(featureVal);
        console.log(JSON.stringify(this.featureList1));
        this.moduleNameList.push(moduleName);
        this.NameList.push(Name);
      }
    },
      error => { throw error });

    // this.deletepopup.hide();
    // this.statusChangeConfirm.hide();
    // this.errormsg.hide();

  }

  backtoPrev() {
        this.router.navigate([Gl]);
  }
close() {
    this.isCannotDeletePopup = false;
}

  enableEdit() {
    this.editMode = false;
    this.isEdit = true;
}

  update(partyName, glCode, glName, glDesc, glIsGroup, parentGl, statusName) {
      this.updateGl(partyName, glCode, glName, glDesc, glIsGroup, parentGl, statusName);
 }

     updateGl(partyName, glCode, glName, glDesc, glIsGroup, parentGl, statusName) {
       if (this.validate(glCode, glName)) {

        if (statusName === 'Active') {
              this.statusMsg = 'Activate';
        }else if (statusName === 'InActive') {
              this.statusMsg = 'InActivate';
        }else if ( statusName === 'Draft') {
              this.statusMsg = 'Updated';
              this.glData.statusName =  'Active';
        }
        this.glCode = glCode;
        this.subscription = this.glAccountService.updateGL(this.glData).subscribe(updateData => {
              this.updateData = updateData;
              this.isShowModal = true;
              setTimeout(() => {this.isShowModal = false; }, 3000);
              this.glAccountService.flag = true;
               this.router.navigate([Gl]);

         }, error => {
              this.isCannotDeletePopup = true;
             },
            () => console.log('Finished')

            );
      }

   }

/*  confirm() {
     this.status_change = true;
     if (this.glData.statusName === 'Active') {
              this.statusMsg = 'InActivate';
              this.glData.statusName = 'InActive';
             // this.status = 'Active';
        }else if (this.glData.statusName === 'InActive') {
              this.statusMsg = 'Activate';
              this.glData.statusName = 'Active';
            //  this.status = 'InActive';
        }else if (this.glData.statusName === 'Draft') {
              this.statusMsg = 'Activate';
              this.glData.statusName = 'Active';
            //  this.status = 'Draft';
        }
   }*/

  confirm(statusname) {

    // this.rawGradeName = this.glData.outturnRawGradeDto.rawGradeName;
    this.statusChangeConfirm.show();

    if (statusname === 'Active') {
        this.statusMsg = 'Activate';
     this.glData.statusName = 'Active';
   } else if (statusname === 'InActive') {
      this.statusMsg = 'InActivate';
      this.glData.statusName = 'InActive';
   }
  }

   conf_status_change(event) {
        this.subscription = this.glAccountService.updateGL(this.glData).subscribe(updateData => {
              this.updateData = updateData;
            //  alert(this.glData.statusName)
              /*this.isShowModal = true;
              setTimeout(() => {this.isShowModal = false; }, 3000);*/
             this.router.navigate([Gl], {queryParams: {'delSuccess': 1, glName: this.glData.glBasicDto.glName,
             status: this.glData.statusName}});

         }, error => {
               this.isCannotDeletePopup = true;
             },
            () => console.log('Finished')

            );
   }


   delete(params) {
      this.deletebyid = true;
      this.statusMsg = 'Delete';
      this.status = 'Draft';

       this.gl_Id = params.glBasicDto.glId;
   this.glCode = params.glBasicDto.glCode;
   this.glName = params.glBasicDto.glName;


   }


conf_delete(id) {
     const param = { 'glId' : this.gl_Id};
     this.subscription = this.glAccountService.deleteGlById(param).subscribe(deletedStatus => {
                           this.savedData = deletedStatus;
                           this.deletebyid = false;
                         this.router.navigate([Gl], {queryParams: {'delSuccess': 1, glName: this.glData.glBasicDto.glName,
                         status: this.glData.statusName}});
       },
         error => alert(error),
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

   closedeletepopup() {
     this.deletebyid = false;
     this.status_change = false;
   }

    validate(glCode, glName) {
      if (glCode === '' && glName === '') {
          this.errorMessage = 'Please enter the GL Code and the GL Name';
           return false;
      }else if (glCode !== '' && glName === '') {
           this.errorMessage = 'Please enter the GL Name';
            return false;
      }else if (glCode === '' && glName !== '') {
           this.errorMessage = 'Please enter the GL Code';
            return false;
      }else {
            this.errorMessage = '';
            return true;
      }
  }

editGlAccount(glId) {
  alert(glId);
   this.router.navigate([editgl + '/' +  glId]);
  }

}
