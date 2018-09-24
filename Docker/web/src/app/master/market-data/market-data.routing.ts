import { ModuleWithProviders } from '@angular/core';
import { RouterModule } from '@angular/router';
import { HolidayCalendarComponent } from './holiday-calendar/holiday-calendar.component';
import { MarketPriceOptionsComponent } from './market-price-options/market-price-options.component';
import { MarketPriceFuturesComponent } from './market-price-futures/market-price-futures.component';
import { AddHolidayCalendarComponent } from './holiday-calendar/add-holiday-calendar/add-holiday-calendar.component';
import { ViewHolidayCalendarComponent } from './holiday-calendar/view-holiday-calendar/view-holiday-calendar.component';
import { CanDeactivateGuard } from '../../shared/guards/can-deactivate-guard';
//for market calendar
import { CalendarSetupComponent } from './calendar-setup/calendar-setup.component';
import { AddCalendarSetupComponent } from './calendar-setup/add-calendar-setup/add-calendar-setup.component';
import { ViewCalendarSetupComponent } from './calendar-setup/view-calendar-setup/view-calendar-setup.component'; 


const marketDataRoutingConfig = [
    { path: '', component: null },
    { path: 'holidaycalendar', component: HolidayCalendarComponent },
    { path: 'options', component: MarketPriceOptionsComponent },
    { path: 'futures', component: MarketPriceFuturesComponent },
    { path: 'addholidaycalendar', component: AddHolidayCalendarComponent, canDeactivate: [CanDeactivateGuard] },
    { path: 'holidaycalendar/edit/:holidayCalId', component: AddHolidayCalendarComponent, canDeactivate: [CanDeactivateGuard] },
    { path: 'viewholidaycalendar', component: ViewHolidayCalendarComponent },
    { path: 'viewholidaycalendar/edit/:holidayCalId', component: AddHolidayCalendarComponent, canDeactivate: [CanDeactivateGuard] },
    //for market calendar
    { path: 'calendarsetup', component: CalendarSetupComponent },
    { path: 'calendarsetup/add', component: AddCalendarSetupComponent },
    { path: 'calendarsetup/view', component: ViewCalendarSetupComponent }, 

];

export const MarketDataRoutingModule: ModuleWithProviders = RouterModule.forChild(marketDataRoutingConfig);

