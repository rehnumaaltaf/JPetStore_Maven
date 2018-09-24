import { ModuleWithProviders } from '@angular/core';
import { RouterModule } from '@angular/router';
import { PaymentTermsComponent } from './payment-terms/payment-terms.component';
import { FinancialCalendarComponent } from './financial-calendar/financial-calendar.component';
import { CurrencyComponent } from './currency/currency.component';
import { ViewCurrencyComponent } from './currency/view-currency/view-currency.component';
import { ForexComponent } from './forex/forex.component';
import { AddForexComponent } from './forex/add-forex/add-forex.component';
import { GlAccountComponent } from './gl-account/gl-account.component';
import { TaxRateComponent } from './tax-rate/tax-rate.component';
import { ViewForexComponent } from './forex/view-forex/view-forex.component';
import { WeightTermsComponent } from './weight-terms/weight-terms.component';
import { AddGlAccountComponent } from './gl-account/add-gl-account/add-gl-account.component';
import { AddFinancialCalComponent } from './financial-calendar/add-financial-cal/add-financial-cal.component';
import { ViewFinancialCalComponent } from './financial-calendar/view-financial-cal/view-financial-cal.component';
import { CanDeactivateGuard } from '../../shared/guards/can-deactivate-guard';
import { ViewGlAccountComponent } from './gl-account/view-gl-account/view-gl-account.component';
import { AddPaymentTermsComponent } from './payment-terms/add-payment-terms/add-payment-terms.component';
import { ViewPaymentTermsComponent } from './payment-terms/view-payment-terms/view-payment-terms.component';
import { AddBasePaymentComponent } from './base-payment/add-base-payment/add-base-payment.component';
import { ViewBasePaymentComponent } from './base-payment/view-base-payment/view-base-payment.component';
import { BasePaymentComponent } from './base-payment/base-payment.component';
import { AddWeightTermsComponent } from './weight-terms/add-weight-terms/add-weight-terms.component';
import { ViewWeightTermsComponent } from './weight-terms/view-weight-terms/view-weight-terms.component';
import { TaxRuleComponent } from './tax-rule/tax-rule.component';
import { AddTaxRuleComponent } from './tax-rule/add-tax-rule/add-tax-rule.component';
import { ViewTaxRuleComponent } from './tax-rule/view-tax-rule/view-tax-rule.component';
import { AddTaxRateComponent } from './tax-rate/add-tax-rate/add-tax-rate.component';
import { ViewTaxRateComponent } from './tax-rate/view-tax-rate/view-tax-rate.component';

const financeRoutingConfig = [
  { path: '', component: null },
  { path: 'payment', component: PaymentTermsComponent },
  { path: 'payment-terms/add-payment-terms', component: AddPaymentTermsComponent },
  { path: 'payment-terms/view-payment-terms', component: ViewPaymentTermsComponent },
  { path: 'payment-terms/view-payment-terms/edit/:id', component: AddPaymentTermsComponent },
  { path: 'payment/edit/:id', component: AddPaymentTermsComponent },
  { path: 'financial-calendar', component: FinancialCalendarComponent },
  { path: 'financial-calendar/add-financial-cal', component: AddFinancialCalComponent},
  { path: 'financial-calendar/edit-financial-cal/:id', component: AddFinancialCalComponent},
  { path: 'financial-calendar/view-financial-cal', component: ViewFinancialCalComponent},
  { path: 'currency', component: CurrencyComponent },
  { path: 'currency/viewCurrency', component: ViewCurrencyComponent },
  // { path: 'forexfinance', component: ForexComponent },
  { path: 'glaccount', component: GlAccountComponent },
  { path: 'taxrate', component: TaxRateComponent },
  { path: 'weightterms', component: WeightTermsComponent },
  { path: 'addgl', component: AddGlAccountComponent, canDeactivate: [CanDeactivateGuard] },
  { path: 'glaccount/view/:glId', component: ViewGlAccountComponent },
  { path: 'glaccount/editgl/:glId', component: AddGlAccountComponent, canDeactivate: [CanDeactivateGuard] }, // for edit GL
  { path: 'forex', component: ForexComponent },
  { path: 'forex/add-forex', component: AddForexComponent },
  { path: 'forex/edit-forex/:id', component: AddForexComponent },
  { path: 'forex/view-forex', component: ViewForexComponent },
  { path: 'base-payment/add-base-payment', component: AddBasePaymentComponent, canDeactivate: [CanDeactivateGuard] },
  { path: 'base-payment/base-payment/edit/:id', component: AddBasePaymentComponent},
  { path: 'base-payment/base-payment', component: BasePaymentComponent },
  { path: 'base-payment/base-payment/view-base-payment/:id', component: ViewBasePaymentComponent },
  { path: 'base-payment/base-payment/view-base-payment/:id/edit/:id', component: AddBasePaymentComponent },
  { path: 'weight-terms/add', component: AddWeightTermsComponent, canDeactivate: [CanDeactivateGuard]},
  { path: 'weight-terms/view', component: ViewWeightTermsComponent },
  { path: 'taxrule', component: TaxRuleComponent },
  { path: 'taxrule/view/:id', component: ViewTaxRuleComponent },
  { path: 'taxrule/add', component: AddTaxRuleComponent, canDeactivate: [CanDeactivateGuard]},
  { path: 'taxrule/edit/:id', component: AddTaxRuleComponent },
  { path: 'tax-rate/add', component: AddTaxRateComponent},
  { path: 'tax-rate/view', component: ViewTaxRateComponent}
];

export const FinanceRoutingModule: ModuleWithProviders = RouterModule.forChild(financeRoutingConfig);
