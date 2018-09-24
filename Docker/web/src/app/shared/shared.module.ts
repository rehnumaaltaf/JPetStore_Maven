import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { DropdownTreeviewSelectComponent } from './dropdown-treeview-select/dropdown-treeview-select.component';
import { HeaderComponent } from './header/header.component';
import { LoaderComponent } from './loading-animation/loader.component';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { MessageComponent } from './message/message.component';
import { TreeviewModule } from 'ngx-treeview';
import { ModalModule } from 'ngx-bootstrap';
import { LocalizationModule, LocaleValidationModule, LocaleService, TranslationService } from 'angular-l10n';
// import { ConfirmBoxComponent } from './confirm-box/confirm-box.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ModalModule.forRoot(),
    TreeviewModule.forRoot()
  ],
  declarations: [
    HeaderComponent,
    LoaderComponent,
    NavBarComponent,
    MessageComponent,
    DropdownTreeviewSelectComponent
  ],
  exports: [
    HeaderComponent,
    LoaderComponent,
    NavBarComponent,
    MessageComponent,
    DropdownTreeviewSelectComponent
  ],
   schemas: [ CUSTOM_ELEMENTS_SCHEMA]
})
export class SharedModule {
  constructor(public locale: LocaleService, public translation: TranslationService) {
    this.locale.addConfiguration()
      // add the languages which will be supported with locale files
      .addLanguages(['en', 'it'])
      .setCookieExpiration(30)
      .defineDefaultLocale('en', 'US')
      .defineCurrency('USD');

    this.translation.addConfiguration()
      .addProvider('./assets/locale/shared/olam-ctrm-');

    this.translation.init();
  }
}
