import { OAuthCallbackHandler } from './shared/guards/oAuthCallbackHandler';
import { OAuthCallbackComponent } from './shared/guards/oAuthCallback.component';
import { ConfigService } from './shared/service/config.service';
import { Router } from '@angular/router';
import { AdalService } from './shared/service/adal.service';
import { AuthGuardService } from './shared/guards/auth-guard.service';
import { BrowserModule, Title } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule, ErrorHandler } from '@angular/core';
// Vendor
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule, Http, XHRBackend, RequestOptions, JsonpModule } from '@angular/http';
import { LocalizationModule, LocaleService, TranslationService } from 'angular-l10n';
// Components & Pipes
import { AppComponent } from './app.component';
import { LoginComponent } from './core/login/login.component';
import { HomeComponent } from './core/home/home.component';
import { NotFoundComponent } from './shared/not-found/not-found.component'
import { FilterPipe } from './shared/pipes/filter.pipe';
// Services
import { LoginService } from './core/login/login.service';
import { httpServiceFactory } from './shared/interceptor/http-service.factory';
import { LoaderService } from './shared/loading-animation/loader.service';
// Modules
import { RoutingModule } from './app.routing';
import { SharedModule } from './shared/shared.module';
import { ErrorComponent } from './shared/error/error.component';
import { GlobalErrorHandler } from './shared/error/global-error-handler';
import { ConfirmBoxComponent } from './shared/confirm-box/confirm-box.component';
import { ConfirmationService } from './shared/confirm-box/confirm.service';
import { ModalModule } from 'ngx-bootstrap';
// import { AdalService } from 'ng2-adal/core';

@NgModule({
  declarations: [
    AppComponent,
    OAuthCallbackComponent,
    LoginComponent,
    HomeComponent,
    NotFoundComponent,
    ErrorComponent,
    FilterPipe,
    ConfirmBoxComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpModule,
    JsonpModule,
    LocalizationModule.forRoot(),
    ReactiveFormsModule,
    RoutingModule,
    // DataTableModule,
    // GrowlModule,
    SharedModule,
    ModalModule.forRoot()
  ],
  exports: [
    ConfirmBoxComponent
  ],
  providers: [
    Title,
    LoginService,
    ConfirmationService,
    ConfigService,
    AuthGuardService,
    OAuthCallbackHandler,
    AdalService,
    {
      provide: ErrorHandler,
      useClass: GlobalErrorHandler
    },
    LoaderService,
        {
          provide: Http,
          useFactory: httpServiceFactory,
          deps: [XHRBackend, RequestOptions, AdalService, Router, LoaderService]
        }
  ],
  bootstrap: [AppComponent]
})

export class AppModule {

  constructor(public locale: LocaleService, public translation: TranslationService) {
    this.locale.addConfiguration()
      // add the languages which will be supported with locale files
      .addLanguages(['en', 'it'])
      .setCookieExpiration(30)
      .defineDefaultLocale('en', 'US')
      .defineCurrency('USD');

    this.translation.addConfiguration()
      .addProvider('./assets/locale/olam-ctrm-');

    this.translation.init();
  }

}
