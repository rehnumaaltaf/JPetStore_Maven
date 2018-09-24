import { Component, Input, NgModule , OnChanges} from '@angular/core';
import { FormGroup , Validators} from '@angular/forms';
import {PermissionGroupService} from './../service/permission-group.service';
import { PermissionGroup } from '../permission-group-interface';
import { PermissionGroupRoleMapping } from '../permission-group-interface';
import { Unit } from './../unit';
import { SharedModule } from './../../../shared/shared.module';
import { BrowserModule } from '@angular/platform-browser';
import { Subscription } from 'rxjs/Subscription';
@Component({
    moduleId: module.id,
    selector: 'app-permission-mapping',
    templateUrl: 'permission-mapping.component.html',
    styleUrls: ['./add-permission.component.css'],
})
export class PermissionMappingComponent  implements OnChanges {

// tslint:disable-next-line:no-input-rename
    @Input('group')
    public permissionMappingForm: FormGroup;
    @Input() title: any;
    @Input() permissionGroup: PermissionGroup = new PermissionGroup();
    permissiongrpData: string;
    permissionPartyData: string;
    permissionUnitData: string;
    permissionProdData: string;
    permissionPortData: string;
    permissionMappingId: Number= 0;
    per_role: PermissionGroupRoleMapping;
    constructor(public permissionGroupService: PermissionGroupService ) {
     }
    ngOnChanges() {
        if (this.permissionGroup != null ) {
            if (this.permissionGroup.permissionGroupRoleMapping != null) {
                if (this.permissionGroup.permissionGroupRoleMapping[this.title] != null ) {
                    this.permissiongrpData = this.permissionGroup.permissionGroupRoleMapping[this.title].role.id.toString();
                    this.permissionPartyData = this.permissionGroup.permissionGroupRoleMapping[this.title].party.id.toString();
                    this.permissionUnitData  = this.permissionGroup.permissionGroupRoleMapping[this.title].unit.id.toString();
                    this.permissionProdData = this.permissionGroup.permissionGroupRoleMapping[this.title].product.id.toString();
                    this.permissionPortData = this.permissionGroup.permissionGroupRoleMapping[this.title].portfolio.id.toString();

                     if ( this.permissionGroup.permissionGroupRoleMapping[this.title].mappingId != null) {
                    this.permissionMappingId = this.permissionGroup.permissionGroupRoleMapping[this.title].mappingId;
                    }
                   
                }
            }
       }
     }
      getStylerole(value) {
          if (this.permissionGroupService.editcode != null ) {
          this.per_role = this.permissionGroupService.editcode;
          if (this.permissionGroupService.editcode[value] != null) {
            if (this.permissionGroupService.editcode[value].roleId == null) {
                return '#d43c3c';
            } else {
                return '#cfdee7';
            }
        }
    }
      }
      checkrole(value) {
         if (this.permissionGroupService.editcode != null ) {
          this.per_role = this.permissionGroupService.editcode;
          if (this.permissionGroupService.editcode[value] != null) {
            if (this.permissionGroupService.editcode[value].roleId == null) {
                 return  true;
            } else {
                return  false;
            }
        }
      }
      }
    getStyleparty(value) {
         if (this.permissionGroupService.editcode != null ) {
          this.per_role = this.permissionGroupService.editcode;
          if (this.permissionGroupService.editcode[value] != null) {
            if (this.permissionGroupService.editcode[value].partyId == null) {
                return '#d43c3c';
                } else {
                return '#cfdee7';
                    }
    }
           }
    }
     checkparty(value) {
         if (this.permissionGroupService.editcode != null ) {
          this.per_role = this.permissionGroupService.editcode;
          if (this.permissionGroupService.editcode[value] != null) {
            if (this.permissionGroupService.editcode[value].partyId == null) {
                return true;
                } else {
                return false;
                    }
    }
           }
    }
    getStyleproduct(value) {
       if (this.permissionGroupService.editcode != null ) {
          this.per_role = this.permissionGroupService.editcode;
          if (this.permissionGroupService.editcode[value] != null) {
            if (this.permissionGroupService.editcode[value].productId == null) {
                return '#d43c3c';
                } else {
                return '#cfdee7';
                }
    }
           }
    }

     checkproduct(value) {
       if (this.permissionGroupService.editcode != null ) {
          this.per_role = this.permissionGroupService.editcode;
          if (this.permissionGroupService.editcode[value] != null) {
            if (this.permissionGroupService.editcode[value].productId == null) {
                return true;
                } else {
                return false;
                }
    }
           }
    }
    getStyleportfolio(value) {
          if (this.permissionGroupService.editcode != null ) {
          this.per_role = this.permissionGroupService.editcode;
           if (this.permissionGroupService.editcode[value] != null) {
            if (this.permissionGroupService.editcode[value].portfolioId == null) {
                return '#d43c3c';
                } else {
                return '#cfdee7';
                    }
    }
           }
    }

     checkportfolio(value) {
          if (this.permissionGroupService.editcode != null ) {
          this.per_role = this.permissionGroupService.editcode;
           if (this.permissionGroupService.editcode[value] != null) {
            if (this.permissionGroupService.editcode[value].portfolioId == null) {
                return true;
                } else {
                return false;
                    }
    }
           }
    }
    getStyleunit(value) {
        if (this.permissionGroupService.editcode != null ) {
        this.per_role = this.permissionGroupService.editcode;
        if (this.permissionGroupService.editcode[value] != null) {
        if (this.permissionGroupService.editcode[value].unitId == null) {
             return '#d43c3c';
        } else {
            return '#cfdee7';
        }
    }
           }
    }
     checkunit(value) {
        if (this.permissionGroupService.editcode != null ) {
        this.per_role = this.permissionGroupService.editcode;
        if (this.permissionGroupService.editcode[value] != null) {
        if (this.permissionGroupService.editcode[value].unitId == null) {
             return true;
        } else {
            return  false;
        }
    }
           }
    }

}