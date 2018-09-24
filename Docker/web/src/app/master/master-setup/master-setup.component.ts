import { Component, OnInit, OnDestroy, Output, ViewEncapsulation } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { MasterSetupService } from './service/master-setup.service';
import { ResponseData } from '../../shared/interface/responseData';
import { uom } from '../../shared/interface/router-links';
import { Gl } from '../../shared/interface/router-links';
import { forex } from '../../shared/interface/router-links';
import { origin } from '../../shared/interface/router-links';
import { permissiongroup } from '../../shared/interface/router-links';
import { financialCalendar } from '../../shared/interface/router-links';
import { location } from '../../shared/interface/router-links';
import { geo } from '../../shared/interface/router-links';
import { freight } from '../../shared/interface/router-links';
import { cnf } from '../../shared/interface/router-links';
import { listuserrole } from '../../shared/interface/router-links';
import { listingpageproduct } from '../../shared/interface/router-links';
import { listingPageBasePaymnt } from '../../shared/interface/router-links';
import { InternalPacking } from '../../shared/interface/router-links';
import { brand } from '../../shared/interface/router-links';
import { packingMaterial } from '../../shared/interface/router-links';
import { paymentterm, cropyear } from '../../shared/interface/router-links';
import { secondaryPack } from '../../shared/interface/router-links';
import { holidayCalendar } from '../../shared/interface/router-links';
import { outturnRatio } from '../../shared/interface/router-links';
import { cost, costmatrix } from '../../shared/interface/router-links';
import { incoTerm } from '../../shared/interface/router-links';
import { currency } from '../../shared/interface/router-links';
import { taxrule } from '../../shared/interface/router-links';
import { blend } from '../../shared/interface/router-links';

import { productgrade } from '../../shared/interface/router-links';
@Component({
  selector: '<app-master-setup></app-master-setup>',
  templateUrl: './master-setup.component.html',
  styleUrls: ['./master-setup.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class MasterSetupComponent implements OnInit, OnDestroy {
  public master_more = false;
  subscription: Subscription;
  masters: ResponseData;
  @Output() loadingData: Boolean = true;

  constructor(private router: Router, private masterService: MasterSetupService) {

  }
  taxRate() {
    this.router.navigate(['master/finance/taxrate']);
  }
  taxRule() {
    this.router.navigate([taxrule]);
  }
  limitMaster() {
    this.router.navigate(['master/limit/limits-master']);
  }
  weightTerms() {
    this.router.navigate(['master/finance/weightterms']);
  }
  basepaymentTerms() {
    this.router.navigate(['master/finance/base-payment/base-payment']);
  }
  product() {
    this.router.navigate([listingpageproduct]);
  }
  productGrade() {
    this.router.navigate([productgrade]);
  }
  cropYear() {
    this.router.navigate(['master/product/cropyear']);
  }
  gradeAttribute() {
    // this.router.navigate(['master/product/productgrade']);
  }
  blendMaster() {
    this.router.navigate([blend]); // 'master/product/blend'
  }

  outTurn() {
    this.router.navigate(['master/product/outturn']);
  }
  BrandMaster() {
    this.router.navigate(['master/product/brand']);
  }
  CertificationMaster() {
    this.router.navigate(['master/party/certify']);
  }
  incoTerms() {
    this.router.navigate([incoTerm]);
  }
  onExternalClick() {
    this.router.navigate(['master/uomPack/extPack']);
  }
  partyMaster() {
    this.router.navigate(['master/party/partymaster']);
  }
  costMaster() {
    this.router.navigate(['master/cost/cost']);
  }

  warehouseCost() {
    this.router.navigate(['master/cost/warehouse']);
  }
  holidayCalendarList() {
    this.router.navigate([holidayCalendar]);
  }
  shipmentMonth() {
    this.router.navigate(['master/geoLoc/shipmentmonth']);
  }
  productLimit() {
    this.router.navigate(['master/limit/productlimit']);
  }
  exchangeLimit() {
    this.router.navigate(['master/limit/exchange']);
  }
  forexLimit() {
    this.router.navigate(['master/limit/forexlimit']);
  }
  traderLimit() {
    this.router.navigate(['master/limit/trade']);
  }
  legalEntity() {
    this.router.navigate(['master/limit/entity']);
  }
  unitLimit() {
    this.router.navigate(['master/limit/unitlimit']);
  }
  click_more(): void {
    this.master_more = true;
  }
  back(): void {
    this.master_more = false;
  }

  ngOnInit() {
    // commented out as there is no MS
    // this.loadingMasterList();
  }

  onInternalClick() {
    this.router.navigate([InternalPacking]);
  }

  // unitDetails() {
  //   this.router.navigate(['master/party/unitparty']);
  // }

  uomDetails() {
    // this.router.navigate(['/packagingdetails']);
    this.router.navigate([uom]);
  }

  secondaryPackDetails() {
    this.router.navigate([secondaryPack]);
  }

  packingMaterialDetails() {
     this.router.navigate([packingMaterial]);
   // this.router.navigate(['master/uomPack/pack']);
  }


  origin() {
    this.router.navigate([origin]);
  }
  currencyDetails() {
    // this.router.navigate(['master/finance/currency']);
    this.router.navigate([currency]);
  }

  goToViewUser() {
    this.router.navigate([listuserrole]);
  }
  permissionGroup() {
    this.router.navigate([permissiongroup]);
  }

  forexDetails() {
    // this.router.navigate([forex]);
    this.router.navigate(['master/finance/forex']);
  }

  glDetails() {
    this.router.navigate([Gl]);
  }

  financialCalDetails() {
    //  this.router.navigate([financialCalendar]);
    this.router.navigate(['master/finance/financial-calendar']);
  }

  loactionDetails() {
    // this.router.navigate([location]);
    this.router.navigate(['master/geoLoc/location']);
  }
  brandDetails() {
    this.router.navigate([brand]);
  }
  goToViewGeo() {
    this.router.navigate([geo]);
  }

  freightCostMatrixList() {
    this.router.navigate([freight]);
  }

  cnfCostMatrixList() {
    this.router.navigate([cnf]);
  }
  paymentListList() {
    // this.router.navigate([paymentterm]);
    this.router.navigate(['master/finance/payment']);
  }
  outturnRatioDetails() {
    this.router.navigate([outturnRatio]);
  }

  viewCostMaster() {
    this.router.navigate([cost]);
  }

  viewCostMatrix() {
    this.router.navigate([costmatrix]);
  }
  loadingMasterList() {
    this.subscription = this.masterService.getMasterList().subscribe(masterList => {
      this.masters = <ResponseData>masterList;
      this.masterService.addChildObjects(this.masters.links);
      // this.isDataAvailable = true; (What to show if there is not data)
      this.loadingData = false;

    }, error => {
      console.log('Error Loading UOM Listing: ' + <any>error)
      this.loadingData = false;
      // this.notificationService.addNotications(error);
    });
  }

  ngOnDestroy() {
    // Called once, before the instance is destroyed.
    if (this.subscription != null) {
      this.subscription.unsubscribe();
    }
  }
}
