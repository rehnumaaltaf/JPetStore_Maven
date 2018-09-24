import { Component, OnInit,ViewChild } from '@angular/core';
import { GeoService } from './../service/geo.service';
import { Subscription } from 'rxjs/Subscription';
import { Geography } from '../geography';
import { Router } from '@angular/router';
import { geo } from '../../../../shared/interface/router-links';
import { AccordionModule , ModalDirective } from 'ngx-bootstrap';

@Component({
  selector: 'app-view-geo',
  templateUrl: './view-geo.component.html',
  styleUrls: ['./view-geo.component.css']
})
export class ViewGeoComponent implements OnInit {

   @ViewChild('successModal') public successModal: ModalDirective;
  @ViewChild('deletesuccessModal') public deletesuccessModal: ModalDirective;

  public geodetails: Geography = new Geography();
  public inactive;
  public active;
  public draft;
  gCode: number;
  public listOfGeoedit = [];
  subscription: Subscription;
  public code;
  public statusMsg;
  public isDeletePopupModal;
  errorMessage: any;
  isEdit: boolean;
  editMode: boolean;
  public geoId: number;
  isShowModal: boolean;
  updateData: any;
  isActiveStatus: boolean;
  status_change: boolean;
  isCannotDeletePopup: boolean;
  isDeactivatePopupModal:boolean;
  public pkGeoId: number;
  deletebyid: boolean;
  isDraftStatus: boolean;
   deleteSuccessModal:boolean;
  marketdesk: boolean;
  public deletedStatus;
  public del_id;
  public status: any = {
    isFirstOpen: true,
    isFirstDisabled: false
  };
  constructor(public geoService: GeoService, private router: Router) { }

  ngOnInit() {
    this.gCode = this.geoService.viewbyIdParam.pkGeoId;
    this.status_change = false;
    this.isCannotDeletePopup = false;
    this.editMode = true;
    this.loaddata();
  }

  loaddata() {
    this.subscription = this.geoService.getselectedGeo(this.gCode).subscribe(addGeoDetail => {
      this.geodetails = addGeoDetail.body;
      if (this.geodetails.geoTypeName == "Country") {
        this.marketdesk = true;
      } else {
        this.marketdesk = false;
      }

      //this.geodetails = this.geoService.viewGeodetails;
      if (this.geodetails.statusName === "Draft") {
        this.draft = true;
        this.active = false;
        this.inactive = false;
      } else if (this.geodetails.statusName === "Active") {
        this.active = true;
        this.draft = false;
        this.inactive = false;
      } else if (this.geodetails.statusName === "InActive") {
        this.inactive = true;
        this.active = false;
        this.draft = false;
      }
    },
      error => {
        //alert(error);
      }, // Alerts need to be replaced by error
      () => console.log('Finished')
    );
  }

  backtoPrev() {
    this.router.navigate([geo]);
  }
  enableEdit() {
    this.editMode = false;
    this.isEdit = true;
  }

  closedeletepopup() {
    this.isDeletePopupModal = false;
    this.isCannotDeletePopup = false;
  }

  //  conf_delete(id) {
  //    debugger;
  //       const geoobj = {'pkGeoId': this.geoId};
  //           this.subscription = this.geoService.deleteGroupById(this.geoId, this.status).subscribe(deletedStatus => {
  //           this.deletedStatus = deletedStatus;
  //           this.deletebyid = false;
  //           if (this.status === 'Draft') {
  //           this.isDraftStatus = true;
  //         }else if (this.status === 'Active') {
  //           this.isActiveStatus = true;
  //         }
  //           this.router.navigate([geo]);

  //  },
  //          error => {
  //             this.errorMessage = error;
  //             this.isDeletePopupModal = false;
  //             this.isCannotDeletePopup = true;
  //         },
  //          () => console.log('Finished')
  //     );
  // }
  conf_delete(id) {
    let param = { "pkGeoId": this.geoId };
    this.subscription = this.geoService.deleteGEOById(param).subscribe(deletedStatus => {
      this.deletedStatus = deletedStatus;
      /*this.successModal.hide();    
                           this.deletesuccessModal.show();
                           
                           setTimeout(()=>{this.deletesuccessModal.hide();},3000);
                           if (this.status=="Draft") {
                           this.deleteSuccessModal=true;    
                           setTimeout(()=>{this.deletesuccessModal.hide();},3000);
                           }*/
      this.deletebyid = false;
      if (this.status === 'Draft') {
        this.isDraftStatus = true;
      } else if (this.status === 'Active') {
        this.isActiveStatus = true;
      }
      this.router.navigate([geo], { queryParams: { 'success': 8} });

    },
      error => alert(error),
      () => console.log('Finished')
    );
  }

  close() {
    this.ngOnInit();
    this.isCannotDeletePopup = false;
    this.isDeactivatePopupModal = false;
  }

  conf_status_change_draft() {
    const submitstatus = 'save';
    const addoreditpage = false; // add page is true , edit page is false
    this.geodetails.statusName = 'Active';
    this.subscription = this.geoService.saveGeog(this.geodetails, submitstatus, addoreditpage).
      subscribe(updateData => {
        this.updateData = updateData;
        this.isShowModal = true;
        setTimeout(() => { this.isShowModal = false; }, 3000);
        this.router.navigate([geo],{ queryParams: { 'success': 3 } });
      }, error => {
        this.status_change = false;
      },
      () => console.log('Finished')

      );
  }
deactivate(id, name, status){
    this.geoId = id;
    this.geoService.geoName = name;
    this.status = status;
    this.isDeactivatePopupModal = true;
}
  geo_delete(id, name, status) {
    this.geoId = id;
    this.geoService.geoName = name;
    this.status = status;
    if (this.status === 'Draft') {
      this.isDraftStatus = true;
    } else if (this.status === 'Active') {
      this.isActiveStatus = true;
    }
    this.isDeletePopupModal = true;

    return false;
  }

  conf_status_change(status) {
    this.geodetails.statusName = status;
    const deactivestaus = status;
    const addoreditpage = false; // add page is true , edit page is false
    this.subscription = this.geoService.saveGeog(this.geodetails, deactivestaus, addoreditpage).
      subscribe(updateData => {
        this.updateData = updateData;
        this.router.navigate([geo], { queryParams: { 'success': 9 } });
        //this.router.navigate([geo]);
      }, error => {
        this.status_change = false;
      },
      () => console.log('Finished')

      );
  }

  // conf_status_change_to_active() {
  //     const submitstatus = 'InActive';
  //     const addoreditpage = false; // add page is true , edit page is false
  //     this.geodetails.statusName = 'Active';
  //           this.subscription = this.geoService.saveGeog(this.geodetails, submitstatus, addoreditpage).
  //           subscribe(updateData => {
  //               this.updateData = updateData;
  //               this.isShowModal = true;
  //               setTimeout(() => { this.isShowModal = false; }, 3000);
  //              // this.router.navigate([geo]);
  //           }, error => {
  //               this.status_change = false;
  //              },
  //             () => console.log('Finished')

  //             );
  // }


}
