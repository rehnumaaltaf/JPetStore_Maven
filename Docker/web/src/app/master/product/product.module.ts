import { NgModule, Directive, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LocalizationModule, LocaleValidationModule, LocaleService, TranslationService } from 'angular-l10n';
import { ProductDefinitionComponent } from './product-definition/product-definition.component';
import { ProductDefinitionService } from './product-definition/service/product-definition.service';
import { OriginDefinitionComponent } from './origin-definition/origin-definition.component';
import { OriginDefinitionService } from './origin-definition/service/origin-definition.service';
import { QualityTemplateComponent } from './quality-template/quality-template.component';
import { QualityTemplateService } from './quality-template/service/quality-template.service';
// import { GradeDefinitionComponent } from './grade-definition/grade-definition.component';
// import { GradeDefinitionService } from './grade-definition/service/grade-definition.service';
import { GridModule, ExcelModule } from '@progress/kendo-angular-grid';
import { DropDownsModule, DropDownListModule } from '@progress/kendo-angular-dropdowns';
import { BlendComponent } from './blend/blend.component';
import { CropYearComponent } from './crop-year/crop-year.component';
import { CropYearService } from './crop-year/service/crop-year.service';
import { BlendService } from './blend/service/blend.service';
import { OutturnComponent } from './outturn/outturn.component';
import { ProductMasterService } from './product-master/service/product-master.service';
import { AddOriginComponent } from './origin-definition/add-origin/add-origin.component';
import { ProductRoutingModule } from './product.routing';
import { SharedModule } from '../../shared/shared.module';
import { DataTableModule } from 'primeng/primeng';
import { ModalModule, AccordionModule, TypeaheadModule } from 'ngx-bootstrap';
import { CanDeactivateGuard } from '../../shared/guards/can-deactivate-guard';
import { ViewOriginDefinitionComponent } from './origin-definition/view-origin-definition/view-origin-definition.component';
import { AddProductMasterComponent } from './product-master/add-product-master/add-product-master.component';
import { AddMultiBaseProductComponent } from './product-master/add-product-master/add-multiple-processing';
import { AddMultiBaseProductGradeComponent } from './product-master/add-product-master/add-multiple-grade';
import { ViewProductMasterComponent } from './product-master/view-product-master/view-product-master.component';
// import { AddOutturnRatioComponent } from './outturn-ratio/add-outturn-ratio/add-outturn-ratio.component';

import { AddOutturnRatioComponent } from './outturn/add-outturn-ratio/add-outturn-ratio.component';
import { ViewOutturnRatioComponent } from './outturn/view-outturn-ratio/view-outturn-ratio.component';
import { ProductMasterComponent } from './product-master/product-master.component';
import { BrandComponent } from './brand/brand.component';
import { BrandService } from './brand/service/brand.service';
import { UploadModule } from '@progress/kendo-angular-upload';
import { OutturnService } from './outturn/service/outturn.service';
import { ProductGradeComponent } from './product-grade/product-grade.component';
import { AddProductGradeComponent } from './product-grade/add-product-grade/add-product-grade.component';
import { GradeInternationalMappingComponent } from './product-grade/add-product-grade/grade-international-code-mapping.component';
import { GradeExternalMappingComponent } from './product-grade/add-product-grade/grade-external-system-mapping.component';
import { ViewProductGradeComponent } from './product-grade/view-product-grade/view-product-grade.component';
import { ProductGradeService } from './product-grade/service/product-grade.service';
/*Blend Master*/
import { AddEditBlendComponent } from './blend/add-edit-blend/add-edit-blend.component';
import { ViewBlendComponent } from './blend/view-blend/view-blend.component';
import { OutputMappingComponent } from './blend/add-edit-blend/output-mapping/output-mapping.component';
import { InputMappingComponent } from './blend/add-edit-blend/input-mapping/input-mapping.component';
import { CertificationMappingComponent } from './blend/add-edit-blend/certification-mapping/certification-mapping.component';
@NgModule({
  imports: [
    CommonModule,
    DataTableModule,
    FormsModule,
    ReactiveFormsModule,
    SharedModule,
    ProductRoutingModule,
    GridModule,
    DropDownsModule,
    DropDownListModule,
    ExcelModule,
    LocalizationModule.forRoot(),
    ModalModule.forRoot(),
    AccordionModule.forRoot(),
    TypeaheadModule.forRoot(),
    UploadModule
  ],
  declarations: [
    ProductDefinitionComponent,
    OriginDefinitionComponent,
    QualityTemplateComponent,
    // GradeDefinitionComponent,
    BlendComponent,
    ViewBlendComponent,
    OutturnComponent,
    AddOriginComponent,
    CropYearComponent,
    AddProductMasterComponent,
    ViewProductMasterComponent,
    AddOutturnRatioComponent,
    ViewOutturnRatioComponent,
    ProductMasterComponent,
    BrandComponent,
    AddMultiBaseProductComponent,
    AddMultiBaseProductGradeComponent,
    ViewOriginDefinitionComponent,
    OutturnComponent,
    ProductGradeComponent,
    AddProductGradeComponent,
    ViewProductGradeComponent,
    GradeInternationalMappingComponent,
    GradeExternalMappingComponent,
    AddEditBlendComponent,
    ViewBlendComponent,
    OutputMappingComponent,
    InputMappingComponent,
    CertificationMappingComponent
    ],
  providers: [
   ProductDefinitionService,
   OriginDefinitionService,
   QualityTemplateService,
   CropYearService,
   ProductMasterService,
   // GradeDefinitionService,
    BlendService,
   BrandService,
   OutturnService,
   CanDeactivateGuard,
   ProductGradeService
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})

export class ProductModule {
  constructor(public locale: LocaleService, public translation: TranslationService) {
    this.locale.addConfiguration()
      // add the languages which will be supported with locale files
      .addLanguages(['en', 'it'])
      .setCookieExpiration(30)
      .defineDefaultLocale('en', 'US')
      .defineCurrency('USD');

    this.translation.addConfiguration()
      .addProvider('./assets/locale/product/olam-ctrm-');

    this.translation.init();
  }
}
