import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule  } from '@angular/forms';
import { SharedModule } from '../../shared/shared.module';
import { LocalizationModule, LocaleValidationModule, LocaleService, TranslationService } from 'angular-l10n';
import { DataTableModule, ConfirmDialogModule, ConfirmationService, GrowlModule } from 'primeng/primeng';
import { PartyRoutingModule } from './party.routing';
import { PartyDefinitionComponent } from './party-definition/party-definition.component';
import { PartyDefinitionService } from './party-definition/service/party-definition.service';
import { UnitComponent } from './unit/unit.component';
import { UnitService } from './unit/service/unit.service';
import { AddUnitComponent } from './unit/add-unit/add-unit.component';
import { CertificationAgencyComponent } from './certification-agency/certification-agency.component';
import { CertificationAgencyService } from './certification-agency/service/certification-agency.service';
import { ModalModule , AccordionModule } from 'ngx-bootstrap';
import { DropDownsModule, DropDownListModule  } from '@progress/kendo-angular-dropdowns';
import { ViewUnitComponent } from './unit/view-unit/view-unit.component';
import { EditUnitComponent } from './unit/edit-unit/edit-unit.component';
import { CertificationDetailsComponent } from './certification-details/certification-details.component';
import { AddCertificationComponent } from './certification-details/add-certification/add-certification.component';
import { ViewCertificationComponent } from './certification-details/view-certification/view-certification.component';
import { CertifyMeasurementService } from './certification-details/service/certification.service';
import { GridModule, ExcelModule } from '@progress/kendo-angular-grid';
import { CanDeactivateGuard } from '../../shared/guards/can-deactivate-guard';
import { UploadModule } from '@progress/kendo-angular-upload';
import { DateInputsModule } from '@progress/kendo-angular-dateinputs';
import { PartyMasterComponent } from './party-master/party-master.component';
import { AddMultiPartySystemComponent } from './party-master/add-party-master/add-partyLimit-multiple';
import { AddMultiAddressSystemComponent } from './party-master/add-party-master/add-Address-multiple';
import { AddMultiWareHouseSystemComponent } from './party-master/add-party-master/add-warehouse-multiple';
import { AddMultiExchangeComponent } from './party-master/add-party-master/add-exchange-multiple';
import { AddMultiDocument } from './party-master/add-party-master/add-Document-multiple';
import { AddMultiPartyGradeComponent } from './party-master/add-party-master/add-internal-grade-multiple';
import { AddMultiExternalSystemComponent } from './party-master/add-party-master/add-externalSytem-multiple';
import { AddMultiCreditSystemComponent } from './party-master/add-party-master/add-creditLimit-multiple';
import { PartyMasterService } from './party-master/service/party-master.service';
import { AddPartyMasterComponent } from './party-master/add-party-master/add-party-master.component';
import { ViewPartyMasterComponent } from './party-master/view-party-master/view-party-master.component';
import { AddMultiPantComponent } from './party-master/add-party-master/add-plant-multiple';

@NgModule({
  imports: [
    CommonModule,
    PartyRoutingModule,
    FormsModule,
    GridModule,
    ExcelModule,
    ReactiveFormsModule,
    DropDownsModule,
    DropDownListModule,
    LocalizationModule.forRoot(),
    SharedModule,
    DataTableModule,
    UploadModule,
    DateInputsModule,
    ModalModule.forRoot(),
    AccordionModule.forRoot(),
    ConfirmDialogModule, // message
    GrowlModule // message

  ],
  declarations: [
    PartyDefinitionComponent,
    UnitComponent,
    AddUnitComponent,
    ViewUnitComponent,
    CertificationAgencyComponent,
    EditUnitComponent,
    CertificationDetailsComponent,
    AddCertificationComponent,
    ViewCertificationComponent,
    AddMultiPantComponent,
    AddMultiAddressSystemComponent,
    AddMultiWareHouseSystemComponent,
    PartyMasterComponent,
    AddMultiExternalSystemComponent,
    AddMultiCreditSystemComponent,
    AddMultiPartySystemComponent,
    AddPartyMasterComponent,
    AddMultiPartyGradeComponent,
    AddMultiExchangeComponent,
    AddMultiDocument,
    ViewPartyMasterComponent
  ],
  schemas: [ CUSTOM_ELEMENTS_SCHEMA ],
  providers: [
    PartyDefinitionService,
    UnitService,
    CanDeactivateGuard,
	PartyMasterService,
    CertificationAgencyService,
    CertifyMeasurementService,
    ConfirmationService // message
  ]

})

export class PartyModule {
  constructor(public locale: LocaleService, public translation: TranslationService) {
    this.locale.addConfiguration()
      // add the languages which will be supported with locale files
      .addLanguages(['en', 'it'])
      .setCookieExpiration(30)
      .defineDefaultLocale('en', 'US')
      .defineCurrency('USD');

    this.translation.addConfiguration()
      // .addProvider('./assets/locale/forex/olam-ctrm-');
      .addProvider('./assets/locale/party/olam-ctrm-');

    this.translation.init();
 }
 }
