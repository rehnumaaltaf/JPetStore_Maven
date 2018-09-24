import { ModuleWithProviders } from '@angular/core';
import { RouterModule } from '@angular/router';
import { ProductDefinitionComponent } from './product-definition/product-definition.component';
import { OriginDefinitionComponent } from './origin-definition/origin-definition.component';
import { QualityTemplateComponent } from './quality-template/quality-template.component';
import { CropYearComponent } from './crop-year/crop-year.component';
// import { GradeDefinitionComponent } from './grade-definition/grade-definition.component';
import { OutturnComponent } from './outturn/outturn.component';
import { AddOriginComponent } from './origin-definition/add-origin/add-origin.component';
import { CanDeactivateGuard } from '../../shared/guards/can-deactivate-guard';
import { AddOutturnRatioComponent } from './outturn/add-outturn-ratio/add-outturn-ratio.component';
import { ViewOutturnRatioComponent } from './outturn/view-outturn-ratio/view-outturn-ratio.component';
import { BrandComponent } from './brand/brand.component';
import { ProductGradeComponent } from './product-grade/product-grade.component';
import { ProductMasterComponent } from './product-master/product-master.component';
import { ViewProductMasterComponent } from './product-master/view-product-master/view-product-master.component';
import { AddProductMasterComponent } from './product-master/add-product-master/add-product-master.component';
import { AddProductGradeComponent } from './product-grade/add-product-grade/add-product-grade.component';
// import { AddOutturnRatioComponent } from './outturn/add-outturn-ratio/add-outturn-ratio.component';
// import { OutturnComponent } from './outturn/outturn.component';
// import { ViewOutturnRatioComponent } from './outturn/view-outturn-ratio/view-outturn-ratio.component';
import { ViewProductGradeComponent } from './product-grade/view-product-grade/view-product-grade.component';
/*Blend Master*/
import { BlendComponent } from './blend/blend.component';
import { AddEditBlendComponent } from './blend/add-edit-blend/add-edit-blend.component';
import { ViewBlendComponent } from './blend/view-blend/view-blend.component';

const productRoutingConfig = [
        { path: '', component: OriginDefinitionComponent },
        { path: 'origin', component: OriginDefinitionComponent },
        { path: 'template', component: QualityTemplateComponent },
        // { path: 'grade', component: GradeDefinitionComponent },
        { path: 'cropyear', component: CropYearComponent, canDeactivate: [CanDeactivateGuard]  },
        { path: 'outturn', component: OutturnComponent },
        { path: 'outturn-ratio', component: OutturnComponent },
        { path: 'product-master/view-product-master', component: ViewProductMasterComponent},
        { path: 'product-master/add-product-master', component: AddProductMasterComponent, canDeactivate: [CanDeactivateGuard]},
    // { path: 'outturn/add-outturn-ratio', component: AddOutturnRatioComponent},
        { path: 'outturn/add', component: AddOutturnRatioComponent, canDeactivate: [CanDeactivateGuard] },
        { path: 'outturn/view/:outturnRawGradeId', component: ViewOutturnRatioComponent },
        { path: 'product-master/product-master', component: ProductMasterComponent},
        { path: 'brand', component: BrandComponent, canDeactivate: [CanDeactivateGuard] },
        { path: 'outturn/editout/:id', component: AddOutturnRatioComponent, canDeactivate: [CanDeactivateGuard] },
        { path: 'outturn-ratio/view/:outturnRawGradeId', component: ViewOutturnRatioComponent},
        { path: 'productgrade/view/:gradeId', component: ViewProductGradeComponent},
        { path: 'productgrade/edit/:gradeId', component: AddProductGradeComponent},
        {path: 'productgrade/view/:gradeId/edit/:gradeId', component: AddProductGradeComponent },
        { path: 'productgrade', component: ProductGradeComponent},
        { path: 'productgrade/add', component: AddProductGradeComponent ,  canDeactivate: [CanDeactivateGuard] },
        { path: 'blend', component: BlendComponent , data: {title: 'Master.Blend.Listing'} },
        { path: 'blend/view-blend/:id', component: ViewBlendComponent , data: {title: 'Master.Blend.View'} },
        {path: 'blend/add' , component: AddEditBlendComponent , canDeactivate: [CanDeactivateGuard] , data: {title: 'Master.Blend.Add'}  },
        {path: 'blend/edit/:id', component: AddEditBlendComponent , canDeactivate: [CanDeactivateGuard] ,
           data: {title: 'Master.Blend.Edit'}},
    ];


export const ProductRoutingModule: ModuleWithProviders = RouterModule.forChild(productRoutingConfig);
