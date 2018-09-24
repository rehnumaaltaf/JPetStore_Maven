import { Component, OnInit , OnDestroy , Output } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { addForex } from '../../../shared/interface/router-links';
import { viewForex } from '../../../shared/interface/router-links';
import { ForexModel } from './forex-interface';
import { ForexService } from './service/forex.service';
import { editForex } from '../../../shared/interface/router-links';
import { Subscription } from 'rxjs/Subscription';

@Component({
  selector: 'app-forex',
  templateUrl: './forex.component.html',
  styleUrls: ['./forex.component.css']
})
export class ForexComponent implements OnInit , OnDestroy {
  forexModel: ForexModel[] = [];
  @Output() loadingData: Boolean = true;
  deleteSuccessModal: Boolean;
  isDeletePopupModal: boolean;
  dialogValue: String;
  deactivateSuccessDialog: String;
  forexId: String;
  forexStatusId: Number;
  forexName: String;
  subscription: Subscription;
  public success;
  public isShowModal;
  public message;

  constructor(private router: Router, private route: ActivatedRoute, private forexService: ForexService) { }

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
        // Defaults to 0 if no query param provided.
        this.success = +params['success'];
        if (this.success === 1) {
          this.message = this.forexService.getMessage();
          console.log(this.message + ' ' + this.forexService.getMessage());
          this.isShowModal = true;
          setTimeout(() => {this.isShowModal = false; }, 3000);
        }
    });
    this.loadingForexList();
    this.deleteSuccessModal = false;
    this.isDeletePopupModal = false;
  }
  loadingForexList() {
    this.forexService.getUnitJSON().subscribe(getUnitDetail => {
       this.loadingData = true;
       this.deleteSuccessModal = false;
       this.forexModel = getUnitDetail.body;
       // console.log(JSON.stringify(this.forexModel));
    },
    error => {throw error});
  }
  /*to add the forex*/
  forexAddView() {
    this.router.navigate([addForex]);
  }
  /*to get more details of forex selected*/
  forexDetailView(id, statusId) {
  //  this.forexService.setId(id);
  //  this.forexService.setStatusId(statusId);
   // this.router.navigate([viewForex]);
  }
  /*to edit the selected forex*/
  UpdateForex(id) {
    this.forexService.setId(id);
     this.router.navigate([editForex]);
  }
  /*to delete the selected forex*/
  DeleteForex(id, statusId, name) {
    this.forexService.setId(id);
    this.forexService.setStatusId(statusId);
    this.forexService.setForexName(name);
    if (statusId === 1) {
        this.dialogValue = 'Are you sure, you want to DeActivate ' + name + ' ?';
    }
    if (statusId === 3) {
      this.dialogValue = 'Are you sure, you want to delete ' + name + ' ?';
    }
    if (statusId === 4) {
      this.dialogValue = 'Are you sure, you want to ReActivate ' + name + ' ?';
    }
    this.isDeletePopupModal = true;
  }
  conf_delete() {
        this.forexId = this.forexService.getId();
        this.forexStatusId = this.forexService.getStatusId();
        this.forexName = this.forexService.getForexName();
        this.subscription = this.forexService.deleteForexJSON(this.forexId, this.forexStatusId).subscribe(deletedStatus => {
          this.isDeletePopupModal = false;
          this.deleteSuccessModal = true;
          this.deactivateSuccessDialog = deletedStatus.body;
          /*if (this.forexStatusId === 1) {
              this.deleteSuccessModal = true;
              this.deactivateSuccessDialog =  this.forexName + ' has been DeActivated.';
          }
          if ( this.forexStatusId === 3) {
            this.deleteSuccessModal = true;
            this.deactivateSuccessDialog = this.forexName + ' has been deleted.';
          }
          if (this.forexStatusId === 4) {
             this.deleteSuccessModal = true;
             this.deactivateSuccessDialog = this.forexName + ' has been ReActivated';
          }*/
          this.loadingForexList();
       },
         error => { throw error });
  }
  closedeletepopup() {
    this.isDeletePopupModal = false;
  }
  ngOnDestroy() {
    // prevent memory leak when component destroyed
    // this.subscription.unsubscribe();
  }

}
