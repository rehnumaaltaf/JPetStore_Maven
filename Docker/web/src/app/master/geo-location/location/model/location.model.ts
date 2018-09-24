import { SelectItemModel } from './select-item.model';

export class LocationModel {
    action?: String;
    pkLocId: number;
    locCode: string;
    locName: string;
    locFullName: string;
    locCountry: string;
    locationType: SelectItemModel[];
    fkGeoId: number;
    fkGeoName: string;
    fkLocTypeName: string;
    fkLocTypeId: number;
    locationTypeNames: string;
    fkStatusId: number;
    checked: Boolean;
    statusName: string;
    rate: number;
    fkCurrencyId: number;
    fkCurrencyName: string;
    rateBasis: string;
    fkRateBasisRefId: number;
    rateBasisUom: string;
    rateBasisUomId: number;
    rateBasisPrimaryPacking: string;
    rateBasisSecondaryPacking: string;
    rateBasisPrimaryPackingId: number;
    rateBasisSecondaryPackingId: number;
    fkRateBasisRefName: string;
}

