import { ModuleWithProviders } from '@angular/core';
import { RouterModule } from '@angular/router';

import {PermissionGroupComponent} from './permission-group.component';
import {AddPermissionComponent} from './add-permission/add-permission.component';
import {ViewPermissionComponent} from './view-permission/view-permission.component';
const permissionGroupRoutingConfig  = [

    {path: '' , component: null},

    {path: 'addpermissiongroup', component : AddPermissionComponent },
    {path: 'permissiongroup', component : PermissionGroupComponent },
    {path: 'permissiongroup/edit/:id', component: AddPermissionComponent },
    {path: 'viewpermissiongroup', component : ViewPermissionComponent },
    {path: 'viewpermissiongroup/edit/:id', component: AddPermissionComponent },
];

export const permissionGroupRoutingModule: ModuleWithProviders = RouterModule.forChild(permissionGroupRoutingConfig);
