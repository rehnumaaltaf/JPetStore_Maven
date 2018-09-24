import { Component, OnInit, OnDestroy, Output } from '@angular/core';
import { PlatformLocation, Location} from '@angular/common';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Subscription } from 'rxjs/Subscription';
import { Router } from '@angular/router';
import { CnfCostMatrix } from './cnf-cost-matrix';
import { CnfCostMatrixService } from './service/cnf-cost-matrix.service';
import { MasterSetupService } from '../../master-setup/service/master-setup.service';
import { FormGroup, FormControl } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { SelectItem } from '../../../shared/interface/selectItem';
import { DropdownModule } from 'primeng/primeng';
import { ButtonModule } from 'primeng/primeng';
import { ConfirmDialogModule, ConfirmationService } from 'primeng/primeng';
import { ResponseData } from '../../../shared/interface/responseData';
import { Link } from '../../../shared/interface/link';
import { addCnfCostMatrix } from '../../../shared/interface/router-links';
import { CanComponentDeactivate } from '../../../shared/guards/can-deactivate-guard'
import { cnf } from '../../../shared/interface/router-links';
import { origin } from '../../../shared/interface/router-links';
import { master } from '../../../shared/interface/router-links';
@Component({
  selector: 'app-cnf-cost-matrix',
  templateUrl: './cnf-cost-matrix.component.html',
  styleUrls: ['./cnf-cost-matrix.component.css']
})
export class CnfCostMatrixComponent implements OnInit, OnDestroy {
showHide: boolean;
  subscription: Subscription;
  isComplete: Boolean = false;
  cnfCostMatrixData: CnfCostMatrix = new CnfCostMatrix();
  selectedUOMrow: CnfCostMatrix;
  UomCodeDropDown: SelectItem[]= [];
  nonDuplicateArray = [];
  displayDialog: boolean;
  public success;
  public isShowModal;
  public iseditModal;
  public cnfedit;
  private cnfDelete;
  public draft;
  public active;
  public inactive;
  public savedData;
  public isupdateModal;
  private cnfdetails;
   isDeletePopupModal: boolean;
  isCannotDeletePopup: boolean;
  isActiveStatus: boolean;
  isDraftStatus: boolean;
  isUpdate: boolean;
  isActivated: boolean;
  isInActivated: boolean;
  public del_id;
  public status;
  public errorMessage;
  deleteSuccessModal: boolean;
  platformlocation: PlatformLocation;
  location: Location;
  isNavbtwComponent: boolean;
  isActionPerformed = false;
  private link: Link;

  constructor(private route: ActivatedRoute, http: Http, private router: Router,
  public cnfCostMatrixService: CnfCostMatrixService,
  private masterSetupService: MasterSetupService, platformlocation: PlatformLocation, location: Location) {
      this.showHide = true;
      platformlocation.onPopState(() => {
                this.isNavbtwComponent = true;
                // this.router.navigate(['addUom'], { queryParams: {}});
                // this.location.go('addUom');
                // this.route.queryParams = [];
        });
  }
 changeShowStatus() {
    // this.showHide = !this.showHide;
     this.router.navigate([addCnfCostMatrix]);
   // this.router.navigate([origin]);
  }
  ngOnInit() {
    /* this.uomedit = {'uomCode': '', 'uomId': '', 'uomName': '', 'uomFullname': '',
      'baseUom': '', 'uomConversionFactor': '', 'statusName': ''};*/
    this.loadingCnfCostMatrixList();

    this.route.queryParams.subscribe(params => {
        // Defaults to 0 if no query param provided.
      //  this.uomCode = this.unitMeasurementService.uomCodeParam;
        this.success = +params['success'];
        // alert("==uomCodeIn unit==>"+this.uomCode);
        if (this.success === 1) {
          if (!this.isNavbtwComponent) {
            this.isShowModal = true;
            this.router.navigate([cnf]);
          }else {
            this.router.navigate([master]);
          }
           setTimeout(() => {this.isShowModal = false; }, 3000);
        }
      });
      this.deleteSuccessModal = false;
      this.isDeletePopupModal = false;
      this.isCannotDeletePopup = false;
  }
  loadingCnfCostMatrixList() {
       this.subscription = this.cnfCostMatrixService.getMockCnfCostMatrixJSON().subscribe(cnfDetails => {
      this.cnfCostMatrixService.cnfDetails = cnfDetails;

       /* const link = this.masterSetupService.getChildObject('View Uom.GET');
      this.subscription = this.unitMeasurementService.getUnitMeasurementJSON(link.href).subscribe(addUomDetail => {
      this.unitMeasurementService.uomDetails = addUomDetail.body;
      this.unitMeasurementService.addChildObjects(addUomDetail.links);
     },
      error => alert(error),
      () => console.log('Finished')
      );  */
  },
      error => alert(error),
      () => console.log('Finished')
    );
  }
errorHandler(errorMessage) {
    this.deleteSuccessModal = false;
    this.isCannotDeletePopup = true;
}
closedeletepopup() {
   this.isDeletePopupModal = false;
   this.isCannotDeletePopup = false;
 }
 canDeactivate(): boolean {
    return this.isActionPerformed;
  }

  ngOnDestroy() {
    // prevent memory leak when component destroyed
    // this.subscription.unsubscribe();
  }
}
