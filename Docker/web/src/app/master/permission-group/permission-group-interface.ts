import { Unit } from './unit';

export class PermissionGroup {

    permissionGroupId: number;
    permissionGroupName: string;
    permissionGroupDesc: string;
    permissionOwner: string;
    statusName: string;
    permissionGroupRoleMapping: PermissionGroupRoleMapping[];
    permissionGroupRoleMappingDto: PermissionGroupRoleMappingDto[];

    }
export class PermissionGroupRoleMapping {
   mappingId: number;
    role: Unit;
    party: Unit;
    product: Unit;
    unit: Unit;
    portfolio: Unit;
    roleId: string;
    partyId: string;
    productId: string;
    unitId: string;
    portfolioId: string;

  }


export class PermissionGroupRoleMappingDto {

    role: Unit[];
    party: Unit[];
    product: Unit[];
    unit: Unit[];
    roleId: Unit;
    portfolio: Unit[];
  }