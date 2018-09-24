import { ModuleWithProviders } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ProductGradeComponent } from './add-product-grade/product-grade.component';
import { ViewProductGradeComponent } from './view-product-grade/view-product-grade.component';

const productgradeRoutingConfig = [
    { path: '', component: null },
    { path: 'productgrade', component: ProductGradeComponent},
     { path: 'viewproductgrade', component: ViewProductGradeComponent},
    //{ path: 'intPack', component: InternalPackagingComponent}
];

export const productgradeRoutingModule: ModuleWithProviders = RouterModule.forChild(productgradeRoutingConfig);
