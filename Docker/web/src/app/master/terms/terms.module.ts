import { NgModule, CUSTOM_ELEMENTS_SCHEMA, NO_ERRORS_SCHEMA } from '@angular/core';
import { AccordionModule } from 'ngx-bootstrap';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule  } from '@angular/forms';
import { LocalizationModule, LocaleValidationModule, LocaleService, TranslationService } from 'angular-l10n';
import { termsRoutingModule } from './terms.routing';
import { SharedModule } from '../../shared/shared.module';
import { AddWeightTermsComponent } from './weight-terms/add-weight-terms/add-weight-terms.component';
import { ViewWeightTermsComponent } from './weight-terms/view-weight-terms/view-weight-terms.component';
import { WeightTermsComponent } from './weight-terms/weight-terms.component';
@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    LocalizationModule.forRoot(),
    AccordionModule.forRoot(),
    termsRoutingModule,
    SharedModule
    ],
  schemas: [ CUSTOM_ELEMENTS_SCHEMA],
  declarations: [
   // NavBarComponent
   AddWeightTermsComponent,
   ViewWeightTermsComponent,
   WeightTermsComponent],
  providers: [

  ]
})

export class TermsModule {
  constructor(public locale: LocaleService, public translation: TranslationService) {
    this.locale.addConfiguration()
      // add the languages which will be supported with locale files
      .addLanguages(['en', 'it'])
      .setCookieExpiration(30)
      .defineDefaultLocale('en', 'US')
      .defineCurrency('USD');

    this.translation.addConfiguration()
      .addProvider('./assets/locale/term/olam-ctrm-');

    this.translation.init();
  }
}
