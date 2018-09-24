import { NgModule, CUSTOM_ELEMENTS_SCHEMA, NO_ERRORS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule  } from '@angular/forms';
import { LocalizationModule, LocaleValidationModule, LocaleService, TranslationService } from 'angular-l10n';
import { GridModule, ExcelModule } from '@progress/kendo-angular-grid';
import { UnitMeasurementComponent } from './unit-measurement/unit-measurement.component';
import { AddUnitMeasurementComponent } from './unit-measurement/add-unit-measurement/add-unit-measurement.component';
import { InternalPackagingComponent } from './internal-packaging/internal-packaging.component'
import { UnitMeasurementService } from './unit-measurement/service/unit-measurement.service';
import { InternalPackingService } from './internal-packaging/service/internal-packing.service';
import { SharedModule } from '../../shared/shared.module';
import { DataTableModule } from 'primeng/primeng';
import { uomRoutingModule } from './uom.routing';
import { ModalModule, AccordionModule } from 'ngx-bootstrap';
import { TypeaheadModule } from 'ngx-bootstrap';
import { ExternalPackingComponent } from './external-packing/external-packing.component';
import { ExternalPackingService } from './external-packing/service/external-packing.service';
import { PackingMaterialComponent } from './packing-material/packing-material.component';
import { PackingMaterialService } from './packing-material/service/packing-material.service';
import { ViewUnitMeasurementComponent } from './unit-measurement/view-unit-measurement/view-unit-measurement.component';
import { CanDeactivateGuard } from '../../shared/guards/can-deactivate-guard';
import { AddPackingMaterialComponent } from './packing-material/add-packing-material/add-packing-material.component';
import { ViewPackingMaterialComponent } from './packing-material/view-packing-material/view-packing-material.component';
import { AddInternalPackingComponent } from './internal-packaging/add-internal-packing/add-internal-packing.component';
import { ViewInternalPackingComponent } from './internal-packaging/view-internal-packing/view-internal-packing.component';
import { AddExternalPackingComponent } from './external-packing/add-external-packing/add-external-packing.component';
import { ExternalPackingMappingComponent } from './external-packing/add-external-packing/external-packing-mapping.component';
import { ViewExternalPackingComponent } from './external-packing/view-external-packing/view-external-packing.component';
// import { NavBarComponent } from '../../shared/nav-bar/nav-bar.component'
@NgModule({
  imports: [
    CommonModule,
    DataTableModule,
    FormsModule,
    ReactiveFormsModule,
    GridModule,
    ExcelModule,
    SharedModule,
    uomRoutingModule,
    LocalizationModule.forRoot(),
    ModalModule.forRoot(),
    TypeaheadModule.forRoot(),
    AccordionModule.forRoot()
  ],
  schemas: [ NO_ERRORS_SCHEMA ],
  declarations: [
    UnitMeasurementComponent,
    InternalPackagingComponent,
    ExternalPackingComponent,
    PackingMaterialComponent,
    AddUnitMeasurementComponent,
    ViewUnitMeasurementComponent,
    AddPackingMaterialComponent,
    ViewPackingMaterialComponent,
    AddInternalPackingComponent,
    ViewInternalPackingComponent,
    AddExternalPackingComponent,
    ViewExternalPackingComponent,
    ExternalPackingMappingComponent

  ],
  providers: [
    UnitMeasurementService,
    InternalPackingService,
    ExternalPackingService,
    PackingMaterialService,
    CanDeactivateGuard
  ]
})

export class UomModule {
  constructor(public locale: LocaleService, public translation: TranslationService) {
    this.locale.addConfiguration()
      // add the languages which will be supported with locale files
      .addLanguages(['en', 'it'])
      .setCookieExpiration(30)
      .defineDefaultLocale('en', 'US')
      .defineCurrency('USD');

    this.translation.addConfiguration()
      .addProvider('./assets/locale/uom/olam-ctrm-');

    this.translation.init();
  }
}
