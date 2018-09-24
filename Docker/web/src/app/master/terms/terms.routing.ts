import { ModuleWithProviders } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CanDeactivateGuard } from '../../shared/guards/can-deactivate-guard';
import { AddWeightTermsComponent } from './weight-terms/add-weight-terms/add-weight-terms.component';
import { ViewWeightTermsComponent } from './weight-terms/view-weight-terms/view-weight-terms.component';
import { WeightTermsComponent } from './weight-terms/weight-terms.component';

const termsRoutingConfig = [
    { path: '', component: null },
    { path: 'weight-terms/add', component: AddWeightTermsComponent},
    { path: 'weight-terms/view', component: ViewWeightTermsComponent},
     { path: 'weight-terms', component: WeightTermsComponent}
];

export const termsRoutingModule: ModuleWithProviders = RouterModule.forChild(termsRoutingConfig);
