import { ModuleWithProviders } from '@angular/core';
import { RouterModule } from '@angular/router';
import { PartyLimitComponent } from './party-limit/party-limit.component';
import { ProductLimitComponent } from './product-limit/product-limit.component';
import { ExchangeLimitComponent } from './exchange-limit/exchange-limit.component';
import { ForexLimitComponent } from './forex-limit/forex-limit.component';
import { TraderLimitComponent } from './trader-limit/trader-limit.component';
import { LegalEntityLimitComponent } from './legal-entity-limit/legal-entity-limit.component';
import { UnitLimitComponent } from './unit-limit/unit-limit.component';
import { AddUnitMasterComponent } from './unit-master/add-unit-master/add-unit-master.component';
import { ViewUnitMasterComponent } from './unit-master/view-unit-master/view-unit-master.component';
import { AddUnitLimitComponent } from './unit-limit/add-unit-limit/add-unit-limit.component';
import { ViewUnitLimitComponent } from './unit-limit/view-unit-limit/view-unit-limit.component';
import { AddLegalEntityLimitComponent } from './legal-entity-limit/add-legal-entity-limit/add-legal-entity-limit.component';
import { ViewLegalEntityLimitComponent } from './legal-entity-limit/view-legal-entity-limit/view-legal-entity-limit.component';
import { AddProductLimitComponent } from './product-limit/add-product-limit/add-product-limit.component';
import { ViewProductLimitComponent } from './product-limit/view-product-limit/view-product-limit.component';
import { AddTraderLimitComponent } from './trader-limit/add-trader-limit/add-trader-limit.component';
import { ViewTraderLimitComponent } from './trader-limit/view-trader-limit/view-trader-limit.component';
import { LimitsMasterComponent } from './limits-master/limits-master.component';
import { AddLimitsMasterComponent } from './limits-master/add-limits-master/add-limits-master.component';
import { ViewLimitsMasterComponent } from './limits-master/view-limits-master/view-limits-master.component';
import { CanDeactivateGuard } from '../../shared/guards/can-deactivate-guard';
const limitRoutingConfig = [
    { path: '', component: null },
    { path: 'party', component: PartyLimitComponent },
    { path: 'productlimit', component: ProductLimitComponent },
    { path: 'exchange', component: ExchangeLimitComponent },
    { path: 'forexlimit', component: ForexLimitComponent },
    { path: 'trade', component: TraderLimitComponent },
    { path: 'entity', component: LegalEntityLimitComponent },
    { path: 'unitlimit', component: UnitLimitComponent },
    { path: 'unit-master/add-unit-master', component: AddUnitMasterComponent },
    { path: 'unit-master/view-unit-master', component: ViewUnitMasterComponent },
    { path: 'unit-limit/add-unit-limit', component: AddUnitLimitComponent },
    { path: 'unit-limit/view-unit-limit', component: ViewUnitLimitComponent },
    { path: 'legal-entity/add', component: AddLegalEntityLimitComponent },
    { path: 'legal-entity/view', component: ViewLegalEntityLimitComponent },
    { path: 'product-limit/add', component: AddProductLimitComponent },
    { path: 'product-limit/view', component: ViewProductLimitComponent },
    { path: 'trader-limit', component: TraderLimitComponent },
    { path: 'trader-limit/add', component: AddTraderLimitComponent },
    { path: 'trader-limit/view', component: ViewTraderLimitComponent },
    { path: 'limits-master', component: LimitsMasterComponent },
    { path: 'limits-master/add', component: AddLimitsMasterComponent , canDeactivate: [CanDeactivateGuard] },
    { path: 'limits-master/view/:limitHeaderId', component: ViewLimitsMasterComponent },
    { path: 'limits-master/edit/:HeaderId', component: AddLimitsMasterComponent ,canDeactivate: [CanDeactivateGuard] },
     { path: 'limits-master/view/:limitHeaderId/edit/:HeaderId', component: AddLimitsMasterComponent ,canDeactivate: [CanDeactivateGuard] },
];

export const LimitRoutingModule: ModuleWithProviders = RouterModule.forChild(limitRoutingConfig);

