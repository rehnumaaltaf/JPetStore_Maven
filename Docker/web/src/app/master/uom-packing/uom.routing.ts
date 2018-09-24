import { ModuleWithProviders } from '@angular/core';
import { RouterModule } from '@angular/router';
import { UnitMeasurementComponent } from './unit-measurement/unit-measurement.component';
import { InternalPackagingComponent } from './internal-packaging/internal-packaging.component';
import { AddInternalPackingComponent } from './internal-packaging/add-internal-packing/add-internal-packing.component';
import { ViewInternalPackingComponent } from './internal-packaging/view-internal-packing/view-internal-packing.component';
import { AddUnitMeasurementComponent } from './unit-measurement/add-unit-measurement/add-unit-measurement.component';
import { ViewUnitMeasurementComponent } from './unit-measurement/view-unit-measurement/view-unit-measurement.component';
import { ExternalPackingComponent } from './external-packing/external-packing.component';
import { PackingMaterialComponent } from './packing-material/packing-material.component';
import { ViewPackingMaterialComponent } from './packing-material/view-packing-material/view-packing-material.component';
import { AddPackingMaterialComponent } from './packing-material/add-packing-material/add-packing-material.component';
import { CanDeactivateGuard } from '../../shared/guards/can-deactivate-guard';
import { AddExternalPackingComponent } from './external-packing/add-external-packing/add-external-packing.component';
import { ViewExternalPackingComponent } from './external-packing/view-external-packing/view-external-packing.component';

const uomRoutingConfig = [
    { path: '', component: UnitMeasurementComponent, data: {title: 'Master.UOM.Listing'} },
    { path: 'uom', component: UnitMeasurementComponent},
    { path: 'add', component: AddUnitMeasurementComponent, canDeactivate: [CanDeactivateGuard]},
    { path: 'primarypacking', component: InternalPackagingComponent, canDeactivate: [CanDeactivateGuard]},
    { path: 'primarypacking/addintPack', component: AddInternalPackingComponent, canDeactivate: [CanDeactivateGuard]},
    { path: 'primarypacking/viewintPack', component: ViewInternalPackingComponent},
    { path: 'extPack', component: ExternalPackingComponent},
    { path: 'pack', component: PackingMaterialComponent},
    { path: 'view', component: ViewUnitMeasurementComponent},
    {path: 'packing-material' , component: PackingMaterialComponent,  canDeactivate: [CanDeactivateGuard]},
    { path: 'packing-material/addpack', component: AddPackingMaterialComponent , canDeactivate: [CanDeactivateGuard]},
    {path: 'packing-material/view-packing-material' , component: ViewPackingMaterialComponent},
    { path: 'secondary-pack/addSecPack', component: AddExternalPackingComponent, canDeactivate: [CanDeactivateGuard]},
    { path: 'secondary-pack/editSecPack/:id', component: AddExternalPackingComponent, canDeactivate: [CanDeactivateGuard]},
    { path: 'secondary-pack', component: ExternalPackingComponent},
    { path: 'secondary-pack/viewPack', component: ViewExternalPackingComponent}
    ];

export const uomRoutingModule: ModuleWithProviders = RouterModule.forChild(uomRoutingConfig);
