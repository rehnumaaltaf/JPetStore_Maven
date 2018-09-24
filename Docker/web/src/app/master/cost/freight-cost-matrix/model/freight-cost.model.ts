import { SelectItem } from '../../../../shared/interface/selectItem';

export class FreightMain {
    freightCostMatrixDto: FreightMatrix[]
}

export class FreightMatrix {
    pkFreightCostId: number;
    freightCostValidFrom: string;
    fkPartyId: PartyMaster[] = [];
    fkPartyName: string;
    freightCostValidTo: string;
    freightCostContrRefNo: string;
    freightCostRemarks: string;
    remarks: string;
    isApplicableToAllPartyInd: number;
    freightCostIsDetailedInd: string;
    fkProdId: number;
    fkProdName: string;
    fkServiceProviderPartyId: number;
    fkServiceProviderPartyName: string;
    masterFreightCostdetailDto: FreightDetailDto[];
    action: string;
    freightArray: FreightArray[];
}

export class FreightDetailDto {
    pkFreightCostDetailId: number;
    fkSourceGeoId: number;
    fkSourceGeoName: string;
    fkSourceLocationId: number;
    fkSourceLocationName: string;
    fkDestinationGeoId: number;
    fkDestinationGeoName: string;
    fkDestinationLocationId: number;
    fkDestinationLocationName: string;
    isBulkInd: number;
    rate: string;
    fkRateCurrencyId: number;
    fkRateCurrencyName: string;
    fkRateBasisRefId: number;
    fkRateBasisRefName: string;
    fkRateUomId: number;
    fkRateUomName: string;
    fkRatePrimaryPackingId: number;
    fkRatePrimaryPackingName: string;
    fkRateSecondaryPacking: number;
    fkRateSecondaryPackingName: string;
    transitDays: number;
    remarks: string;
    freeDaysAtLoadPort: number;
    freeDaysAtDestiantionPort: number;

}

export class PartyMaster {
    fkPartyId: Number;
    fkPartyName: String;
    partyCode: String;
    pkFreightCostMatrixPartyAssocId: number;
    pkFreightCostMatrixPartyAssocName: number;

}


export class FreightArray {
    label: string;
    value: number;
}
