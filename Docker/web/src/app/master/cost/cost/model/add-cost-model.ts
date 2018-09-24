import { WareHouseMatrix } from '../../warehouse-cost-matrix/model/cost-warehouse';
import { CnfCostMatrix } from '../../cnf-cost-matrix/model/cnf-cost-model';
import { FreightMatrix } from '../../freight-cost-matrix/model/freight-cost.model';

export class CostMaster {
    action?: String;
    pkCostId: Number;
    fkStatusId: Number;
    fkStatusName: String;
    costName: string;
    costCode: String;
    costFullName: String;
    fkCostGroupId: number;
    fkCostGroupName: String;
    costTypeIsPrimaryInd: String = 'N';
    matrixApplicableInd: String = 'N';
    fkMatrixEntityId: Number;
    fkMatrixEntityName: String;
    costDefaultValue: Number;
    fkcostDefValueTypeId: Number;
    fkcostDefValueTypeName: String;
    fkIndexUomId: Number;
    fkCurrencyId: Number;
    fkCurrencyName: String;
    nettedForMtmInd: String = 'N';
    realizedInd: String = 'N';
    capitalizeCostInd: String = 'N';
    fkRevenueGlId: Number;
    fkRevenueGlName: String;
    fkExpenseGlId: Number;
    fkExpenseGlName: String;
    fkDefaultValueBasisRefId: Number;
    fkDefaultValueBasisRefName: String;
    fkDefaultValueUomId: Number;
    fkDefaultValueUomName: String;
    fkDefValPrimaryPackingId: Number;
    fkDefValPrimaryPackingName: String;
    fkDefValSecondaryPackingId: Number;
    fkDefValSecondaryPackingName: String;
    exterPackAssoc: ExternalSystem[];
    fkPartyId: PartyMaster[] = [];
    wareHouseCostDto: WareHouseMatrix[];
    cnfCostDto: CnfCostMatrix[];
    masterFreightCostMatrixDto: FreightMatrix[];
    tenorCostExteranlArray: ExternalSystem[];
    checked?: Boolean;
}

export class ExternalSystem {
    fkCostId: Number;
    fkExternalSystemRefId: Number;
    mappingType: String;
    externalCode: String;
    pkCostExternalMappingId: Number;
    fkExternalSystemRefName: string;
}

export class PartyMaster {
    partyId: Number;
    partyName: String;
    partyCode: String;
    fkPartyId: Number;
    fkPartyName: String;
    pkCostPartyAssocId?: Number;
}

export class CurrencyMaster {
    pkCurrencyId: Number;
    fkStatusId: Number;
    currencySymbol: String;
    currencyCode: String;
    currencyName: String;
}
export class PrimaryPackingMaster {
    internalPackingTypeId: Number;
    internalPackingTypeName: String;
}
export class UomMaster {
    uomId: Number;
    uomName: String;
    uomCode: String;
}
export class SecondaryPackingMaster {
    internalPackingTypeId: Number;
    internalPackingTypeName: String;
}
export class ProductMaster {
    prodId: Number;
    prodName: String;
    prodCode: String;
}
export enum DefaultUnit {
    uom = 1 ,
    primaryPack = 2,
    secondaryPack = 3
}

export enum SelectMatrix {
    Freight = 1 ,
    Warehouse = 2,
    Clearing_Forwarding = 3,
    Crop_Season = 4
}
export class GeoMaster {
    pkGeoId: Number;
    geoName: String;
}

export class LocationMaster {
    pkLocId: Number;
    locName: string;
 }
