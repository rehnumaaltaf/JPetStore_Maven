import { NgModule, CUSTOM_ELEMENTS_SCHEMA, NO_ERRORS_SCHEMA } from '@angular/core';
import { ModalModule, AccordionModule } from 'ngx-bootstrap';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { SharedModule } from '../../shared/shared.module';
import { LimitRoutingModule } from './limit.routing';
import { PartyLimitComponent } from './party-limit/party-limit.component';
import { PartyLimitService } from './party-limit/service/party-limit.service';
import { ProductLimitComponent } from './product-limit/product-limit.component';
import { ProductLimitService } from './product-limit/service/product-limit.service';
import { ExchangeLimitComponent } from './exchange-limit/exchange-limit.component';
import { ExchangeLimitService } from './exchange-limit/service/exchange-limit.service';
import { ForexLimitComponent } from './forex-limit/forex-limit.component';
import { ForexLimitService } from './forex-limit/service/forex-limit.service';
import { TraderLimitComponent } from './trader-limit/trader-limit.component';
import { TraderLimitService } from './trader-limit/service/trader-limit.service';
import { LegalEntityLimitComponent } from './legal-entity-limit/legal-entity-limit.component';
import { LegalEntityLimitService } from './legal-entity-limit/service/legal-entity-limit.service';
import { UnitLimitComponent } from './unit-limit/unit-limit.component';
import { UnitLimitService } from './unit-limit/service/unit-limit.service';
import { AddUnitMasterComponent } from './unit-master/add-unit-master/add-unit-master.component';
import { ViewUnitMasterComponent } from './unit-master/view-unit-master/view-unit-master.component';
import { AddUnitLimitComponent } from './unit-limit/add-unit-limit/add-unit-limit.component';
import { ViewUnitLimitComponent } from './unit-limit/view-unit-limit/view-unit-limit.component';
import { AddLegalEntityLimitComponent } from './legal-entity-limit/add-legal-entity-limit/add-legal-entity-limit.component';
import { ViewLegalEntityLimitComponent } from './legal-entity-limit/view-legal-entity-limit/view-legal-entity-limit.component';
import { AddProductLimitComponent } from './product-limit/add-product-limit/add-product-limit.component';
import { ViewProductLimitComponent } from './product-limit/view-product-limit/view-product-limit.component';
import { AddTraderLimitComponent } from './trader-limit/add-trader-limit/add-trader-limit.component';
import { ViewTraderLimitComponent } from './trader-limit/view-trader-limit/view-trader-limit.component';
import { LimitsMasterComponent } from './limits-master/limits-master.component';
import { AddLimitsMasterComponent } from './limits-master/add-limits-master/add-limits-master.component';
import { ViewLimitsMasterComponent } from './limits-master/view-limits-master/view-limits-master.component';
import { MasterSetupService } from './../master-setup/service/master-setup.service';
import { LimitsMasterService } from './limits-master/service/limits-master.service';
import { GridModule, ExcelModule } from '@progress/kendo-angular-grid';
import { DateInputsModule } from '@progress/kendo-angular-dateinputs';
import { AddMultiLimitComponent } from './limits-master/add-limits-master/add-multi-limits-master';
import { LocalizationModule, LocaleValidationModule, LocaleService, TranslationService } from 'angular-l10n';
import { CanDeactivateGuard } from '../../shared/guards/can-deactivate-guard';
@NgModule({
  imports: [
    CommonModule,
    LimitRoutingModule,
    SharedModule,
    AccordionModule.forRoot(),
    LocalizationModule.forRoot(),
    FormsModule,
    GridModule,
    ExcelModule,
    ReactiveFormsModule,
    DateInputsModule,
    ModalModule
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  declarations: [
    PartyLimitComponent,
    ProductLimitComponent,
    ExchangeLimitComponent,
    ForexLimitComponent,
    TraderLimitComponent,
    LegalEntityLimitComponent,
    UnitLimitComponent,
    AddUnitMasterComponent,
    ViewUnitMasterComponent,
    AddUnitLimitComponent,
    ViewUnitLimitComponent,
    AddLegalEntityLimitComponent,
    ViewLegalEntityLimitComponent,
    AddProductLimitComponent,
    ViewProductLimitComponent,
    AddTraderLimitComponent,
    ViewTraderLimitComponent,
    LimitsMasterComponent,
    AddLimitsMasterComponent,
    ViewLimitsMasterComponent,
    AddMultiLimitComponent,
   
  ],
  providers: [
    PartyLimitService,
    ProductLimitService,
    ExchangeLimitService,
    LimitsMasterService,
    CanDeactivateGuard,
    ForexLimitService,
    TraderLimitService,
    LegalEntityLimitService,
    UnitLimitService,
    MasterSetupService
  ]
})

export class LimitModule {
  constructor(public locale: LocaleService, public translation: TranslationService) {
    this.locale.addConfiguration()
      // add the languages which will be supported with locale files
      .addLanguages(['en', 'it'])
      .setCookieExpiration(30)
      .defineDefaultLocale('en', 'US')
      .defineCurrency('USD');

    this.translation.addConfiguration()
      // .addProvider('./assets/locale/forex/olam-ctrm-');
      .addProvider('./assets/locale/limit/olam-ctrm-');

    this.translation.init();
  }
}
