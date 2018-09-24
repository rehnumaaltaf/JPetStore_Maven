import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserRoleComponent } from './add-user-role/user-role.component';
import { ViewUserRoleComponent } from './view-user-role.component';
//import { EditUserRoleComponent } from './../edit-user-role/edit-user-role.component';
import { ViewUserDetailComponent } from './view-user-detail/view-user-detail.component';
import {ModuleWithProviders} from '@angular/core'

const userroleRoutingConfig = [
    { path: '', component: null },
    { path: 'userrole', component: UserRoleComponent},
    { path: 'viewuserrole', component: ViewUserRoleComponent },
    {path: 'viewuserdetails', component: ViewUserDetailComponent},
    // {path: 'edituser', component: EditUserRoleComponent}
];


export const userRoleRoutingModule: ModuleWithProviders = RouterModule.forChild(userroleRoutingConfig);





