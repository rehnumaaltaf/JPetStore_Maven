import { NgModule , CUSTOM_ELEMENTS_SCHEMA} from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule  } from '@angular/forms';
import { SharedModule } from '../../shared/shared.module';
import { PermissionGroupComponent} from './permission-group.component';
import { PermissionGroupService } from './service/permission-group.service';
import { permissionGroupRoutingModule } from './permission-group.routing';
import { AccordionModule, ModalModule  } from 'ngx-bootstrap';
import {AddPermissionComponent} from './add-permission/add-permission.component';
import {PermissionMappingComponent} from './add-permission/permission-mapping.component';
import { LocalizationModule, LocaleValidationModule, LocaleService, TranslationService } from 'angular-l10n';
import { GridModule, ExcelModule } from '@progress/kendo-angular-grid';
import {ViewPermissionComponent} from './view-permission/view-permission.component';
import { UserRoleService } from './../user-role/service/user-role.service';
@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    SharedModule,
    permissionGroupRoutingModule,
    ReactiveFormsModule,
    ModalModule.forRoot(),
    AccordionModule.forRoot(),
    GridModule,
    ExcelModule,
    LocalizationModule.forRoot()
  ],
  declarations: [
    PermissionGroupComponent,
    AddPermissionComponent,
    PermissionMappingComponent,
    ViewPermissionComponent
    ],
  providers: [
    PermissionGroupService,UserRoleService
  ],
  schemas: [ CUSTOM_ELEMENTS_SCHEMA ]
})
export class PermissionGroupModule { }
