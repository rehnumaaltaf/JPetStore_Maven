import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule  } from '@angular/forms';
import {userRoleRoutingModule } from '.././user-role/user-role-routing.module';
import { UserRoleComponent } from './add-user-role/user-role.component';
import { UserLoopComponent } from './add-user-role/user-loop.component';
import { LocalizationModule, LocaleValidationModule, LocaleService, TranslationService } from 'angular-l10n';
import { GridModule, ExcelModule } from '@progress/kendo-angular-grid';
import { ViewUserDetailComponent } from './view-user-detail/view-user-detail.component';
import { UserRoleService } from './service/user-role.service';
import { SharedModule } from '../../shared/shared.module';
import { ViewUserRoleComponent } from './view-user-role.component';
import { AccordionModule, ModalModule  } from 'ngx-bootstrap';
import { TypeaheadModule } from 'ngx-bootstrap/typeahead';


@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    SharedModule,
    GridModule,
    ExcelModule,
    LocalizationModule.forRoot(),
    userRoleRoutingModule,
    ModalModule.forRoot(),
    AccordionModule.forRoot(),
    TypeaheadModule.forRoot()
  ],
  declarations: [
    UserRoleComponent,
    UserLoopComponent,
   // USEREDITREPEATCOMPONENT,
    ViewUserRoleComponent,
    // EditUserRoleComponent,
    ViewUserDetailComponent,
  ],
  providers: [
    UserRoleService
  ],
  schemas: [ CUSTOM_ELEMENTS_SCHEMA ]
})
export class UserRoleModule { }
