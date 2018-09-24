import { DropdownModel } from './dropdown-model';
import { GLMappingModel } from './gl-mapping-model';
export class ProfitCenterModel {
    parentUnitList: DropdownModel[];
    legalEntityList: DropdownModel[];
    glCodeList: DropdownModel[];
    parentUnitId: string;
    parentUnitName: string;
    status: string;
    groupUnit: string;
    unitCode: string;
    unitName: string;
    unitFullName: string;
    toValidate: string;
    glMapping: GLMappingModel[];
    legalEntity: string;
    glCode: string;
    action: string;
    statusId: number;
    unitMasterId: string;
    message: string;
}
