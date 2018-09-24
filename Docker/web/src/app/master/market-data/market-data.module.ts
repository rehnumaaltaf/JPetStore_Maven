import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../../shared/shared.module';
import { GridModule, ExcelModule } from '@progress/kendo-angular-grid';
import { MarketDataRoutingModule } from './market-data.routing';
import { HolidayCalendarComponent } from './holiday-calendar/holiday-calendar.component';
import { HolidayCalendarService } from './holiday-calendar/service/holiday-calendar.service';
import { MarketPriceOptionsComponent } from './market-price-options/market-price-options.component';
import { MarketPriceOptionsService } from './market-price-options/service/market-price-options.service';
import { MarketPriceFuturesComponent } from './market-price-futures/market-price-futures.component';
import { MarketPriceFuturesService } from './market-price-futures/service/market-price-futures.service';
import { AddHolidayCalendarComponent } from './holiday-calendar/add-holiday-calendar/add-holiday-calendar.component';

import { HolidayCalendarMultipleComponent } from './holiday-calendar/add-holiday-calendar/holiday-calendar-multiple.component';
import { ViewHolidayCalendarComponent } from './holiday-calendar/view-holiday-calendar/view-holiday-calendar.component';
import { ModalModule , AccordionModule } from 'ngx-bootstrap';
import { FormsModule, ReactiveFormsModule  } from '@angular/forms';
import { LocalizationModule, LocaleValidationModule, LocaleService, TranslationService } from 'angular-l10n';
import { DataTableModule } from 'primeng/primeng';
import { CanDeactivateGuard } from '../../shared/guards/can-deactivate-guard';
import { DateInputsModule } from '@progress/kendo-angular-dateinputs';
//for market calendar setup
import { CalendarSetupComponent } from './calendar-setup/calendar-setup.component';
import { AddCalendarSetupComponent } from './calendar-setup/add-calendar-setup/add-calendar-setup.component';
import { ViewCalendarSetupComponent } from './calendar-setup/view-calendar-setup/view-calendar-setup.component';
import { CalendarSetupService } from './market-data/../calendar-setup/service/calendar-setup.service';

@NgModule({
  imports: [
    CommonModule,
    SharedModule,
    AccordionModule.forRoot(),
    ModalModule.forRoot(),
    MarketDataRoutingModule,
    FormsModule,
     ReactiveFormsModule,
    LocalizationModule.forRoot(),
    GridModule,
    ExcelModule,
    DateInputsModule
  ],
  declarations: [
    HolidayCalendarComponent,
    MarketPriceOptionsComponent,
    MarketPriceFuturesComponent,
    AddHolidayCalendarComponent,
    ViewHolidayCalendarComponent,
    HolidayCalendarMultipleComponent,
    // NavBarComponent
    //for market calendar setup
    CalendarSetupComponent,
    AddCalendarSetupComponent,
    ViewCalendarSetupComponent, 

  ],
  providers: [
    HolidayCalendarService,
     MarketPriceOptionsService,
     MarketPriceFuturesService,
     CanDeactivateGuard,
     //calendar setup service
     CalendarSetupService

  ],
  schemas: [ CUSTOM_ELEMENTS_SCHEMA ],
})

export class MarketDataModule {
  constructor(public locale: LocaleService, public translation: TranslationService) {
    this.locale.addConfiguration()
      // add the languages which will be supported with locale files
      .addLanguages(['en', 'it'])
      .setCookieExpiration(30)
      .defineDefaultLocale('en', 'US')
      .defineCurrency('USD');

    this.translation.addConfiguration()
      .addProvider('./assets/locale/holidaycalendar/olam-ctrm-');

    this.translation.init();
  }
 }
