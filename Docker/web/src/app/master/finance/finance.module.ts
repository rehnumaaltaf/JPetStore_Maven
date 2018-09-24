import { NgModule, CUSTOM_ELEMENTS_SCHEMA, NO_ERRORS_SCHEMA} from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule  } from '@angular/forms';
import { SharedModule } from '../../shared/shared.module';
import { DataTableModule, GrowlModule, ConfirmDialogModule } from 'primeng/primeng';
import { FinanceRoutingModule } from './finance.routing';
import { PaymentTermsComponent } from './payment-terms/payment-terms.component';
import { PaymentTermsService } from './payment-terms/service/payment-terms.service';
import { FinancialCalendarComponent } from './financial-calendar/financial-calendar.component';
import { AddFinancialCalComponent } from './financial-calendar/add-financial-cal/add-financial-cal.component';
import { ViewFinancialCalComponent } from './financial-calendar/view-financial-cal/view-financial-cal.component';
import { FinancialCalendarService } from './financial-calendar/service/financial-calendar.service';
import { CurrencyComponent } from './currency/currency.component';
import { CurrencyService } from './currency/service/currency.service';
import { ForexComponent } from './forex/forex.component';
import { ForexService } from './forex/service/forex.service';
import { AddForexComponent } from './forex/add-forex/add-forex.component';
import { TenorMappingComponent } from './forex/add-forex/tenor-mapping.component';
import { GlAccountComponent } from './gl-account/gl-account.component';
import { GlAccountService } from './gl-account/service/gl-account.service';
import { TaxRateComponent } from './tax-rate/tax-rate.component';
import { TaxRateService } from './tax-rate/service/tax-rate.service';
import { WeightTermsComponent } from './weight-terms/weight-terms.component';
import { WeightTermsService } from './weight-terms/service/weight-terms.service';
import { AddGlAccountComponent } from './gl-account/add-gl-account/add-gl-account.component';
import {ModalModule , AccordionModule} from 'ngx-bootstrap';
import { TypeaheadModule } from 'ngx-bootstrap';
import { FinancialMappingComponent } from './financial-calendar/add-financial-cal/financial-mapping.component';
import { LocalizationModule, LocaleValidationModule, LocaleService, TranslationService } from 'angular-l10n';
import { AddEditCurrencyComponent } from './currency/add-edit-currency/add-edit-currency.component';
import { ViewCurrencyComponent } from './currency/view-currency/view-currency.component';
 import { ViewForexComponent } from './forex/view-forex/view-forex.component';
import { CanDeactivateGuard } from '../../shared/guards/can-deactivate-guard';
import { ViewGlAccountComponent } from './gl-account/view-gl-account/view-gl-account.component';
import { GridModule, ExcelModule } from '@progress/kendo-angular-grid';
import { AddWeightTermsComponent } from './weight-terms/add-weight-terms/add-weight-terms.component';
import { ViewWeightTermsComponent } from './weight-terms/view-weight-terms/view-weight-terms.component';
import { ExternalMappingComponent } from './gl-account/add-gl-account/external-mapping.component';
// import { EditGlComponent } from './gl-account/edit-gl/edit-gl.component';
import { ViewPaymentTermsComponent } from './payment-terms/view-payment-terms/view-payment-terms.component';
import { AddBasePaymentComponent } from './base-payment/add-base-payment/add-base-payment.component';

import { AddMultiBasePaymentComponent } from './base-payment/add-base-payment/add-multi-base-payment';
import { ViewBasePaymentComponent } from './base-payment/view-base-payment/view-base-payment.component';
import { BasePaymentComponent } from './base-payment/base-payment.component';
import {BasePaymentService} from './base-payment/service/base-payment.service';
// import { EditGlComponent } from './gl-account/edit-gl/edit-gl.component';
import { AddPaymentTermsComponent } from './payment-terms/add-payment-terms/add-payment-terms.component';
import {PaymentExternalMappingComponent} from './payment-terms/add-payment-terms/payment-external-system-mapping.component';
import { TaxRuleComponent } from './tax-rule/tax-rule.component';
import { AddTaxRuleComponent } from './tax-rule/add-tax-rule/add-tax-rule.component';
import { ViewTaxRuleComponent } from './tax-rule/view-tax-rule/view-tax-rule.component';
import { DateInputsModule } from '@progress/kendo-angular-dateinputs/dist/es/dateinputs.module';
import { DropDownsModule, DropDownListModule  } from '@progress/kendo-angular-dropdowns';

// import files for tax rate
import { AddTaxRateComponent } from './tax-rate/add-tax-rate/add-tax-rate.component';
import { AddMultiTaxRateComponent} from './tax-rate/add-tax-rate/multi-add-tax-rate.component'
import { ViewTaxRateComponent } from './tax-rate/view-tax-rate/view-tax-rate.component';
import {LocationService }  from '../geo-location/location/service/location.service';

@NgModule({
  imports: [
    CommonModule,
    DataTableModule,
    FormsModule,
    ReactiveFormsModule,
    GrowlModule,
    ExcelModule,
    SharedModule,
    ConfirmDialogModule,
    FinanceRoutingModule,
    ModalModule.forRoot(),
    AccordionModule.forRoot(),
    TypeaheadModule.forRoot(),
    LocalizationModule.forRoot(),
    TypeaheadModule.forRoot(),
    GridModule,
    ExcelModule,
    DateInputsModule,
    DropDownsModule,
    DropDownListModule
  ],
  schemas: [ NO_ERRORS_SCHEMA, CUSTOM_ELEMENTS_SCHEMA ],
  declarations: [
  PaymentTermsComponent,
  FinancialCalendarComponent,
  CurrencyComponent,
  ForexComponent,
  GlAccountComponent,
  TaxRateComponent,
  WeightTermsComponent,
  AddGlAccountComponent,
  AddForexComponent,
  TenorMappingComponent,
  AddFinancialCalComponent,
  FinancialMappingComponent,
  AddEditCurrencyComponent,
  ViewCurrencyComponent,
  ViewForexComponent,
  ViewFinancialCalComponent,
  AddPaymentTermsComponent,
  AddMultiBasePaymentComponent,
  ViewPaymentTermsComponent,
  PaymentExternalMappingComponent,
  AddBasePaymentComponent,
  ViewBasePaymentComponent,
  BasePaymentComponent,
  BasePaymentComponent,
  ViewGlAccountComponent,
  AddWeightTermsComponent,
   ViewWeightTermsComponent,
  ExternalMappingComponent,
  TaxRuleComponent,
  AddTaxRuleComponent,
  ViewTaxRuleComponent,
  AddTaxRateComponent,
  AddMultiTaxRateComponent,
  ViewTaxRateComponent
    ],

  providers: [
    PaymentTermsService,
    FinancialCalendarService,
    CurrencyService,
    ForexService,
    GlAccountService,
    TaxRateService,
    BasePaymentService,
    WeightTermsService,
    CanDeactivateGuard
  ]
})

export class FinanceModule {
  constructor(public locale: LocaleService, public translation: TranslationService) {
    this.locale.addConfiguration()
      // add the languages which will be supported with locale files
      .addLanguages(['en', 'it'])
      .setCookieExpiration(30)
      .defineDefaultLocale('en', 'US')
      .defineCurrency('USD');

    this.translation.addConfiguration()
      // .addProvider('./assets/locale/forex/olam-ctrm-');
      .addProvider('./assets/locale/finance/olam-ctrm-');

    this.translation.init();
  }
 }
