import { ModuleWithProviders } from '@angular/core';
import { RouterModule } from '@angular/router';
import { MasterSetupComponent } from './master-setup/master-setup.component';

const masterRoutingConfig = [
    { path: '', redirectTo: 'mastersetup', pathMatch: 'full' },
    { path: 'finance', loadChildren: './finance/finance.module#FinanceModule'},
    { path: 'cost', loadChildren: './cost/cost.module#CostModule'},
    { path: 'geoLoc', loadChildren: './geo-location/geo-location.module#GeoLocationModule'},
    { path: 'limit', loadChildren: './limit/limit.module#LimitModule'},
    { path: 'marketData', loadChildren: './market-data/market-data.module#MarketDataModule'},
    { path: 'party', loadChildren: './party/party.module#PartyModule'},
    { path: 'product', loadChildren: './product/product.module#ProductModule'},
    { path: 'uomPack', loadChildren: './uom-packing/uom.module#UomModule'},
    { path: 'certification', loadChildren: './party/party.module#PartyModule'},
    { path: 'userrole', loadChildren: './user-role/user-role.module#UserRoleModule'},
    { path : 'permissiongroup', loadChildren: './permission-group/permission-group.module#PermissionGroupModule' },
    { path: 'mastersetup', component: MasterSetupComponent},
	// { path: 'master/product/origin', loadChildren: './product/product.module#ProductModule'},
    { path: 'terms', loadChildren: './terms/terms.module#TermsModule'}

  //  { path: 'master', loadChildren: './master/master.module#MasterModule'},
];

export const MasterRoutingModule: ModuleWithProviders = RouterModule.forChild(masterRoutingConfig);

