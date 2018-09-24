import { Component, Inject, OnInit, OnDestroy, Output, Input, ViewChild  } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Router } from '@angular/router';
import { OriginDefinition } from '../origin-definition';
import { OriginDefinitionService } from '../service/origin-definition.service';
import { origin } from '../../../../shared/interface/router-links';
import { Subscription } from 'rxjs/Subscription';
import { ModalModule } from 'ngx-bootstrap';
import { ModalDirective } from 'ngx-bootstrap/modal';
@Component({
  selector: 'app-view-origin-definition',
  templateUrl: './view-origin-definition.component.html',
  styleUrls: ['./view-origin-definition.component.css']
})
export class ViewOriginDefinitionComponent implements OnInit {
  originDefinitionDetails: OriginDefinition = new OriginDefinition();
  @ViewChild('pagebackModal') public pagebackModal: ModalDirective;
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
  status: any;
  statusParam: any;
  glName: any;
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
    public statuses: any = {
    isFirstOpen: true,
    isFirstDisabled: false
  };


  constructor(private route: ActivatedRoute, http: Http, private router: Router,
    private originDefinitionService: OriginDefinitionService) { }

  ngOnInit() {
   // this.pagebackModal.hide();
    this.status_change = false;
      this.isCannotDeletePopup = false;
      this.editMode = true;
       this.deletebyid = false;
      this.originDefinitionDetails = this.originDefinitionService.viewbyIdParam;
    // alert(JSON.stringify(this.originDefinitionDetails))
    //  alert("s"+this.glData.statusName)
       if (this.originDefinitionDetails.originDto.statusName === 'Draft') {
        this.draft = true;
        this.active = false;
        this.inactive = false;
      } else if (this.originDefinitionDetails.originDto.statusName === 'Active' ) {
        this.active = true;
        this.draft = false;
        this.inactive = false;
      } else if (this.originDefinitionDetails.originDto.statusName === 'InActive') {
        this.inactive = true;
        this.active = false;
        this.draft = false;
           }
  }

  backtoPrev() {
        this.router.navigate([origin]);
  }
close() {
    this.isCannotDeletePopup = false;
}

  enableEdit() {
    this.editMode = false;
    this.isEdit = true;
}

  /* update(partyName,glCode,glName,glDesc,glIsGroup,statusName){
      this.updateGl(partyName,glCode,glName,glDesc,glIsGroup,statusName);

 } */

    /*  updateGl(partyName,glCode,glName,glDesc,glIsGroup,statusName) {
       alert("ganga"+statusName)
       if(this.validate(glCode,glName)){

        if(statusName=='Active'){
              this.statusMsg = 'Activate';
        }else if(statusName=='InActive'){
              this.statusMsg = 'InActivate';
        }else if(statusName=='Draft'){
              this.statusMsg = 'Updated';
              this.glData.statusName =  'Active';
        }
        this.glCode = glCode;
        alert("==statusName==>"+this.glData.statusName);

        this.subscription = this.glAccountService.saveGL(this.glData).subscribe(updateData => {
          alert(JSON.stringify("data"+updateData))
              this.updateData = updateData;
              this.isShowModal = true;
              setTimeout(() => {this.isShowModal = false; }, 3000);
              this.glAccountService.flag=true;
               this.router.navigate([Gl]);

         }, error => {
               //alert(this.unitMeasurementService.errorMessage);
              this.isCannotDeletePopup = true;
             },
            () => console.log('Finished')

            );
      }

   } */

   confirm() {
     this.status_change = true;
     if (this.originDefinitionDetails.originDto.statusName === 'Active') {
            //  alert(1);
              this.statusMsg = 'InActivate';
              this.originDefinitionDetails.originDto.statusName = 'InActive';
              this.status = 'Active';
             //  this.pagebackModal.show();
        }else if (this.originDefinitionDetails.originDto.statusName === 'InActive') {
              // alert(2);
              this.statusMsg = 'Activate';
              this.originDefinitionDetails.originDto.statusName = 'Active';
              this.status = 'InActive';
            //   this.pagebackModal.show();
        }else if (this.originDefinitionDetails.originDto.statusName === 'Draft') {
              // alert(3);
              this.statusMsg = 'Activate';
              this.originDefinitionDetails.originDto.statusName = 'Active';
              this.status = 'Draft';
             //  this.pagebackModal.show();
        }
   }

   conf_status_change(event) {
     console.log(this.originDefinitionDetails);
        this.subscription = this.originDefinitionService.updateStatusInView
        (this.originDefinitionDetails, this.originDefinitionDetails.originDto.statusName).subscribe(updateData => {
              this.updateData = updateData;
              this.isShowModal = true;
              setTimeout(() => {this.isShowModal = false; }, 3000);
              this.router.navigate([origin]);

         }, error => {

               this.isCannotDeletePopup = true;
             },
            () => console.log('Finished')

            );
   }

/*
   delete(params) {
      this.deletebyid = true;
      this.statusMsg = 'Delete';
      this.status = 'Draft';

       this.gl_Id=params.glBasicDto.glId;
 //  this.status = params.glBasicDto.statusName;
   this.glCode = params.glBasicDto.glCode;
   this.glName=params.glBasicDto.glName;


   } */


/* conf_delete(id) {
     const param = { 'glId' : this.gl_Id};
     alert(JSON.stringify(param))
     this.subscription = this.glAccountService.deleteGlById(param).subscribe(deletedStatus => {
                           this.savedData = deletedStatus;
                           this.deletebyid = false;

 /*                           const  statusCode = this.getStatusCode(deletedStatus);
                           if(statusCode === 200)
                      {

                           if (this.status=="Draft") {
                                alert("in draft")
                           this.deleteSuccessModal=true;
                           setTimeout(()=>{this.deleteSuccessModal = false;},3000);
                           }

                      }
                        this.router.navigate([Gl]);  */
                      /*   this.router.navigate([Gl], {queryParams: {'delSuccess': 1, glname: this.glData.glBasicDto.glName}});
       },
         error => alert(error),
         () => console.log('Finished')
    );
} */

/*  getStatusCode(deletedStatus) {
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
} */

    closedeletepopup() {
     this.deletebyid = false;
     this.status_change = false;
    // this.pagebackModal.hide();
   }

   /*  validate(glCode,glName){

      if(glCode==""&&glName==""){
          this.errorMessage = "Please enter the GL Code and the GL Name";
           return false;
      }else if(glCode!=""&&glName==""){
           this.errorMessage = "Please enter the GL Name";
            return false;
      }else if(glCode==""&&glName!=""){
           this.errorMessage = "Please enter the GL Code";
            return false;
      }else{
            this.errorMessage = "";
            return true;
      }
  } */

}
