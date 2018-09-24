import { OAuthCallbackHandler } from './shared/guards/oAuthCallbackHandler';
import { OAuthCallbackComponent } from './shared/guards/oAuthCallback.component';
import { AuthGuardService } from './shared/guards/auth-guard.service';
import { ModuleWithProviders } from '@angular/core';
import { Routes, RouterModule, PreloadAllModules } from '@angular/router';
import { LoginComponent } from './core/login/login.component';
import { HomeComponent } from './core/home/home.component';
import { NotFoundComponent } from './shared/not-found/not-found.component';
import { ErrorComponent } from './shared/error/error.component';

const appRoutes: Routes = [
  { path: '', redirectTo: 'master', pathMatch: 'full' },
  // { path: 'login', component: LoginComponent, pathMatch: 'full' },
  { path: 'id_token', component: OAuthCallbackComponent, canActivate: [OAuthCallbackHandler] },
  // { path: '.auth/login/aad/callback', canActivate: [AuthGuardService], loadChildren: './master/master.module#MasterModule'},
  { path: 'home', component: HomeComponent},
  { path: 'master', canActivateChild: [AuthGuardService], loadChildren: './master/master.module#MasterModule'},
  { path: 'error', component: ErrorComponent },
  { path: '**', component: NotFoundComponent }

];

// export const RoutingModule: ModuleWithProviders = RouterModule.forRoot(appRoutes);

export const RoutingModule: ModuleWithProviders = RouterModule.forRoot(appRoutes, {preloadingStrategy: PreloadAllModules, useHash: true});
