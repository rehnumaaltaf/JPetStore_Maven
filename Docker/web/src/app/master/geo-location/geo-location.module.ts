import { AddIncoComponent } from './incoterm/add-inco/add-inco.component';
import { ViewIncoComponent } from './incoterm/view-inco/view-inco.component';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SharedModule } from '../../shared/shared.module';
import { GeoLocationRoutingModule } from './geo-location.routing';
import { GeoComponent } from './geo/geo.component';
import { GeoService } from './geo/service/geo.service';
import { LocationComponent } from './location/location.component';
import { LocationService } from './location/service/location.service';
import { ShipmentMonthComponent } from './shipment-month/shipment-month.component';
import { ShipmentMonthService } from './shipment-month/service/shipment-month.service';
import { IncotermComponent } from './incoterm/incoterm.component';
import { IncotermService } from './incoterm/service/incoterm.service';
import { ModalModule, AccordionModule } from 'ngx-bootstrap';
import { AddGeoComponent } from './geo/add-geo/add-geo.component';
import { DataTableModule } from 'primeng/primeng';
import { TreeviewModule } from 'ngx-treeview';
import { ViewGeoComponent} from './geo/view-geo/view-geo.component';
import { DisabledOnSelectorDirective } from './geo/disabled-on-selector.directive';
import { AddEditLocationComponent } from './location/add-location/add-edit-location.component';
// import { ConfirmBoxComponent } from './location/confirm-box/confirm-box.component';
import { TypeaheadModule } from 'ngx-bootstrap';
import { LocalizationModule, LocaleValidationModule, LocaleService, TranslationService } from 'angular-l10n';
import { ViewLocationComponent } from './location/view-location/view-location.component';
import { GridModule, ExcelModule } from '@progress/kendo-angular-grid';
import { DropDownsModule, DropDownListModule } from '@progress/kendo-angular-dropdowns';
import { CanDeactivateGuard } from '../../shared/guards/can-deactivate-guard';
// import { AddShipmentMonthComponent } from './shipment-month/add-shipment-month/add-shipment-month.component';
// import { ViewShipmentMonthComponent } from './shipment-month/view-shipment-month/view-shipment-month.component';
// import { MessageComponent } from './location/message/message.component';

@NgModule({
  imports: [
    CommonModule,
    GeoLocationRoutingModule,
    DataTableModule,
    FormsModule,
    SharedModule,
    GridModule,
    ExcelModule,
    DropDownsModule,
    DropDownListModule,
    ModalModule.forRoot(),
    AccordionModule.forRoot(),
    TreeviewModule.forRoot(),
    ReactiveFormsModule,
    LocalizationModule.forRoot(),
    TypeaheadModule.forRoot()
  ],
  declarations: [
    GeoComponent,
    LocationComponent,
    ShipmentMonthComponent,
    IncotermComponent, AddGeoComponent,
    DisabledOnSelectorDirective,
    AddEditLocationComponent,
   // ConfirmBoxComponent,
   // MessageComponent,
    ViewLocationComponent,
    ViewGeoComponent,
    ViewIncoComponent,
    AddIncoComponent
  ],
  schemas: [ CUSTOM_ELEMENTS_SCHEMA],
  providers: [
    GeoService,
    LocationService,
    ShipmentMonthService,
    IncotermService,
    CanDeactivateGuard
  ]
})

export class GeoLocationModule {
  constructor(public locale: LocaleService, public translation: TranslationService) {
    this.locale.addConfiguration()
      // add the languages which will be supported with locale files
      .addLanguages(['en', 'it'])
      .setCookieExpiration(30)
      .defineDefaultLocale('en', 'US')
      .defineCurrency('USD');

    this.translation.addConfiguration()
      .addProvider('./assets/locale/geolocation/olam-ctrm-');

    this.translation.init();
  }
}
