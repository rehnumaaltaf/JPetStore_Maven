import { ModuleWithProviders } from '@angular/core';
import { RouterModule } from '@angular/router';
import { GeoComponent } from './geo/geo.component';
import { AddGeoComponent } from './geo/add-geo/add-geo.component';
import { ViewGeoComponent} from './geo/view-geo/view-geo.component';
import { ShipmentMonthComponent } from './shipment-month/shipment-month.component';
import { IncotermComponent } from './incoterm/incoterm.component';
import { LocationComponent } from './location/location.component';
import { AddEditLocationComponent } from './location/add-location/add-edit-location.component';
import { ViewLocationComponent } from './location/view-location/view-location.component';
import { CanDeactivateGuard } from '../../shared/guards/can-deactivate-guard';
import { AddIncoComponent } from './incoterm/add-inco/add-inco.component';
import { ViewIncoComponent } from './incoterm/view-inco/view-inco.component';

const geoLocationRoutingConfig = [
    { path: '', component: GeoComponent },
    { path: 'geo', component: GeoComponent },
    { path: 'addgeo', component: AddGeoComponent },
    {path: 'edit/:id', component: AddGeoComponent },
    {path: 'viewgeo/edit/:id', component: AddGeoComponent },
    { path: 'viewgeo', component: ViewGeoComponent },
    { path: 'location', component: LocationComponent },
    { path: 'shipmentmonth', component: ShipmentMonthComponent , canDeactivate: [CanDeactivateGuard] },
    { path: 'incoterm', component: IncotermComponent },
    { path: 'viewGeo', component: IncotermComponent },
    { path: 'location/add', component: AddEditLocationComponent, canDeactivate: [CanDeactivateGuard] },
    { path: 'location/view/:id', component: ViewLocationComponent },
    { path: 'incoterm/add', component: AddIncoComponent , canDeactivate: [CanDeactivateGuard]},
    { path: 'incoterm/edit/:id', component: AddIncoComponent , canDeactivate: [CanDeactivateGuard]},
    { path: 'incoterm/view/:contractTermsId', component: ViewIncoComponent }

];

export const GeoLocationRoutingModule: ModuleWithProviders = RouterModule.forChild(geoLocationRoutingConfig);
