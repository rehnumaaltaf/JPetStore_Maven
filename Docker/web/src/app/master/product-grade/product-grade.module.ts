import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule  } from '@angular/forms';
//import {DropdownTreeviewModule} from 'ng2-dropdown-treeview';
import { LocalizationModule, LocaleValidationModule, LocaleService, TranslationService } from 'angular-l10n';

import { ProductGradeComponent } from './add-product-grade/product-grade.component';
//import { UnitMeasurementService } from './unit-measurement/service/unit-measurement.service';
import { SharedModule } from '../../shared/shared.module';
import { productgradeRoutingModule } from './product-grade.routing';
import { ModalModule , AccordionModule } from 'ngx-bootstrap';
import { ViewProductGradeComponent } from './view-product-grade/view-product-grade.component';



@NgModule({
  imports: [
    CommonModule,
   // DataTableModule,
    FormsModule,
    ReactiveFormsModule,
    SharedModule,
    productgradeRoutingModule,
    LocalizationModule.forRoot(),
    ModalModule.forRoot(),
    AccordionModule.forRoot(),
    //DropdownTreeviewModule.forRoot()
  ],
  declarations: [
    ProductGradeComponent,
    ViewProductGradeComponent
   
  ],
  providers: [
    //UnitMeasurementService,
   
  ]
})

export class ProductgradeModule {
  constructor(public locale: LocaleService, public translation: TranslationService) {
    this.locale.addConfiguration()
      // add the languages which will be supported with locale files
      .addLanguages(['en', 'it'])
      .setCookieExpiration(30)
      .defineDefaultLocale('en', 'US')
      .defineCurrency('USD');

    this.translation.addConfiguration()
      .addProvider('./assets/locale/olam-ctrm-');

    this.translation.init();
  }
}
