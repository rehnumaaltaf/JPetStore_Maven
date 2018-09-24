import { ModuleWithProviders } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CostComponent } from './cost/cost.component';
import { WarehouseCostMatrixComponent } from './warehouse-cost-matrix/warehouse-cost-matrix.component';
import { CnfCostMatrixComponent } from './cnf-cost-matrix/cnf-cost-matrix.component';
import { FreightCostMatrixComponent } from './freight-cost-matrix/freight-cost-matrix.component';
import { AddFreightCostMatrixComponent } from './freight-cost-matrix/add-freight-cost-matrix/add-freight-cost-matrix.component';
import { AddCnfCostMatrixComponent } from './cnf-cost-matrix/add-cnf-cost-matrix/add-cnf-cost-matrix.component';
import { AddCostComponent } from './cost/add-cost/add-cost.component';
import { ViewCostComponent } from './cost/view-cost/view-cost.component';
import { AddWarehouseComponent } from './warehouse-cost-matrix/add-warehouse/add-warehouse.component';
import { WareHouseMappingComponent } from './warehouse-cost-matrix/add-warehouse/warehouse-mapping.component';
import { ViewWareHouseComponent } from './warehouse-cost-matrix/view-warehouse.component';
import { CropSeasonMatrixComponent } from './crop-season-matrix/crop-season-matrix.component';
import { AddCropSeasonComponent } from './crop-season-matrix/add-crop-season/add-crop-season.component';
import { ViewCropSeasonComponent } from './crop-season-matrix/view-crop-season/view-crop-season.component';
import { ViewCnfCostMatrixComponent } from './cnf-cost-matrix/view-cnf-cost-matrix/view-cnf-cost-matrix.component';
import { ViewFreightCostMatrixComponent } from './freight-cost-matrix/view-freight-cost-matrix/view-freight-cost-matrix.component';
import { ViewCnfComponent } from './cnf-cost-matrix/view-cnf.component';
import { ViewFreightComponent } from './freight-cost-matrix/view-freight.component';
import { CostMatrixComponent } from './cost-matrix/cost-matrix.component';
import { AddCostMatrixComponent } from './cost-matrix/add-cost-matrix/add-cost-matrix.component';
import { ViewCostMatrixComponent } from './cost-matrix/view-cost-matrix/view-cost-matrix.component';
import { CanDeactivateGuard } from '../../shared/guards/can-deactivate-guard';

const costRoutingConfig = [
    { path: '', component: CnfCostMatrixComponent},
    { path: 'cost-master', component: CostComponent},
    { path: 'cost-matrix', component: CostMatrixComponent},
    { path: 'warehouse', component: WarehouseCostMatrixComponent},
    { path: 'cnf', component: CnfCostMatrixComponent},
    { path: 'cost-master/cnfcostmatrix/add', component: ViewCnfComponent},
    { path: 'cost-master/cnfcostmatrix/view', component: ViewCnfCostMatrixComponent},
    { path: 'freight', component: FreightCostMatrixComponent},
    { path: 'freightcostmatrix/add', component: AddFreightCostMatrixComponent},
    { path: 'freightcostmatrix/view', component: ViewFreightCostMatrixComponent},
    { path: 'cost-master/add-cost', component: AddCostComponent , canDeactivate: [CanDeactivateGuard]},
    { path: 'cost-master/edit-cost/:id', component: AddCostComponent , canDeactivate: [CanDeactivateGuard]},
    { path: 'cost-master/view-cost/:id', component: ViewCostComponent},
    { path: 'cost-matrix/add-cost-matrix', component: AddCostMatrixComponent, canDeactivate: [CanDeactivateGuard]},
    { path: 'cost-matrix/edit-cost-matrix/:id/:name', component: AddCostMatrixComponent, canDeactivate: [CanDeactivateGuard]},
    { path: 'cost-matrix/view-cost-matrix/:id/:name', component: ViewCostMatrixComponent},
    { path: 'warehouse-cost-matrix/add-warehouse', component: AddWarehouseComponent},
    { path: 'warehouse-cost-matrix/view-warehouse', component: ViewWareHouseComponent},
    { path: 'freight-cost-matrix/view-freight', component: ViewFreightComponent},
    { path: 'crop-season-matrix/add', component: AddCropSeasonComponent},
    { path: 'crop-season-matrix/view', component: ViewCropSeasonComponent},
    { path: 'crop-season-matrix', component: CropSeasonMatrixComponent}

];

export const CostRoutingModule: ModuleWithProviders = RouterModule.forChild(costRoutingConfig);
