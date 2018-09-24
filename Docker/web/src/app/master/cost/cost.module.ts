import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LocalizationModule, LocaleValidationModule, LocaleService, TranslationService } from 'angular-l10n';
import { SharedModule } from '../../shared/shared.module';
import { CostRoutingModule } from './cost.routing';
import { CostComponent } from './cost/cost.component';
import { CostService } from './cost/service/cost.service';
import { CostMatrixService } from './cost-matrix/service/cost-matrix.service';
import { WarehouseCostMatrixComponent } from './warehouse-cost-matrix/warehouse-cost-matrix.component';
import { WarehouseCostMatrixService } from './warehouse-cost-matrix/service/warehouse-cost-matrix.service';
import { CnfCostMatrixComponent } from './cnf-cost-matrix/cnf-cost-matrix.component';
import { CnfCostMatrixService } from './cnf-cost-matrix/service/cnf-cost-matrix.service';
import { FreightCostMatrixComponent } from './freight-cost-matrix/freight-cost-matrix.component';
import { FreightCostMatrixService } from './freight-cost-matrix/service/freight-cost-matrix.service';
import { AddFreightCostMatrixComponent } from './freight-cost-matrix/add-freight-cost-matrix/add-freight-cost-matrix.component';
import { DataTableModule } from 'primeng/primeng';
import { ModalModule , AccordionModule } from 'ngx-bootstrap';
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
import { DropDownsModule, DropDownListModule  } from '@progress/kendo-angular-dropdowns';
import { DateInputsModule } from '@progress/kendo-angular-dateinputs/dist/es/dateinputs.module';
import { AddCostExternalMappingComponent } from './cost/add-cost/add-cost-external-mapping.component';
import { AddCnfCostMappingComponent } from './cnf-cost-matrix/add-cnf-cost-matrix/add-cnf-cost-mapping.component';
import { GridModule, ExcelModule } from '@progress/kendo-angular-grid';
import { ViewCnfComponent } from './cnf-cost-matrix/view-cnf.component';
import { ViewFreightComponent } from './freight-cost-matrix/view-freight.component';
import { FreightMappingComponent } from './freight-cost-matrix/add-freight-cost-matrix/freight-mapping.component';
import { ViewWarehouseComponent } from './warehouse-cost-matrix/view-warehouse/view-warehouse.component';
import { CostMatrixComponent } from './cost-matrix/cost-matrix.component';
import { AddCostMatrixComponent } from './cost-matrix/add-cost-matrix/add-cost-matrix.component';
import { ViewCostMatrixComponent } from './cost-matrix/view-cost-matrix/view-cost-matrix.component';
import { CanDeactivateGuard } from '../../shared/guards/can-deactivate-guard';

@NgModule({
  imports: [
    CommonModule,
    DataTableModule,
    FormsModule,
    ReactiveFormsModule,
    SharedModule,
    CostRoutingModule,
    LocalizationModule.forRoot(),
    ModalModule.forRoot(),
    AccordionModule.forRoot(),
    DropDownsModule,
    DropDownListModule,
    DateInputsModule,
    GridModule,
    ExcelModule
  ],
  declarations: [
    CostComponent,
    WarehouseCostMatrixComponent,
    CnfCostMatrixComponent,
    FreightCostMatrixComponent,
    AddFreightCostMatrixComponent,
    AddCnfCostMatrixComponent,
    AddCostComponent,
    ViewCostComponent,
    AddWarehouseComponent,
    WareHouseMappingComponent,
    ViewWareHouseComponent,
    CropSeasonMatrixComponent,
    AddCropSeasonComponent,
    ViewCropSeasonComponent,
    ViewCnfCostMatrixComponent,
    ViewFreightCostMatrixComponent,
    AddCostExternalMappingComponent,
    AddCnfCostMappingComponent,
    ViewCnfComponent,
    ViewFreightComponent,
    FreightMappingComponent,
    ViewWarehouseComponent,
    CostMatrixComponent,
    AddCostMatrixComponent,
    ViewCostMatrixComponent
  ],
  providers: [
    CostService,
    WarehouseCostMatrixService,
    CnfCostMatrixService,
    FreightCostMatrixService,
    CostMatrixService,
    DatePipe,
    CanDeactivateGuard
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CostModule {
   constructor(public locale: LocaleService, public translation: TranslationService) {
    this.locale.addConfiguration()
      // add the languages which will be supported with locale files
      .addLanguages(['en', 'it'])
      .setCookieExpiration(30)
      .defineDefaultLocale('en', 'US')
      .defineCurrency('USD');

    this.translation.addConfiguration()
      .addProvider('./assets/locale/cnf-cost-matrix/olam-ctrm-');

    this.translation.init();
  }

 }
