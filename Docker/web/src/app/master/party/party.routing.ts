import { ModuleWithProviders } from '@angular/core';
import { RouterModule } from '@angular/router';
import { PartyDefinitionComponent } from './party-definition/party-definition.component';
import { UnitComponent } from './unit/unit.component';
import { CertificationAgencyComponent } from './certification-agency/certification-agency.component';
import { AddUnitComponent } from './unit/add-unit/add-unit.component';
import { ViewUnitComponent } from './unit/view-unit/view-unit.component';
import { EditUnitComponent } from './unit/edit-unit/edit-unit.component';
import { CertificationDetailsComponent } from './certification-details/certification-details.component';
import { ViewCertificationComponent } from './certification-details/view-certification/view-certification.component';
import { AddCertificationComponent } from './certification-details/add-certification/add-certification.component';
import { PartyMasterComponent } from './party-master/party-master.component';
import { CanDeactivateGuard } from '../../shared/guards/can-deactivate-guard';
import { AddPartyMasterComponent } from './party-master/add-party-master/add-party-master.component';
import { ViewPartyMasterComponent } from './party-master/view-party-master/view-party-master.component';

const partyRoutingConfig = [
    { path: '', component: null },
    { path: 'party', component: PartyDefinitionComponent },
    { path: 'unitparty', component: UnitComponent },
    { path: 'certAgency', component: CertificationAgencyComponent },
    { path: 'addunitparty', component: AddUnitComponent},
    { path: 'viewunitparty', component: ViewUnitComponent},
    { path: 'editunitparty' , component : EditUnitComponent},
    { path: 'certify/addcertify' , component : AddCertificationComponent , canDeactivate: [CanDeactivateGuard]},
    { path: 'certify/viewcertify/:certId' , component : ViewCertificationComponent},
    { path: 'certify' , component : CertificationDetailsComponent},
    { path: 'certify/editcertify/:certId', component: AddCertificationComponent},
    { path: 'partymaster' , component : PartyMasterComponent},
    { path: 'partymaster/add' , component : AddPartyMasterComponent},
    { path: 'partymaster/view/:pkPartyId' , component : ViewPartyMasterComponent},
    { path: 'partymaster/add/edit/:pkPartyId' , component : AddPartyMasterComponent}

];

export const PartyRoutingModule: ModuleWithProviders = RouterModule.forChild(partyRoutingConfig);
