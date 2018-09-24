import { PartyMaster } from '../../cost/model/add-cost-model';

export class CnfCostMatrixMain {
    cnfCostMatrix: CnfCostMatrix[]
}

export class CnfCostMatrix {
    fkPartyId: PartyMaster[] = [];
    cnfCostFreightRate: string;
    currencyName: string;
    cnfCostMatrixValidFrom: String;
    cnfCostMatrixValidTo: string;
    cnfCostMatrixContrRefNo: string;
    cnfCostMatrixRemarks: string;
    statusName: string;
    fkProdId: number;
    legalEntity: string;
    productOption: string;
    warehouse: string;
    warehouseloc: string;
    legalentityOption: string;
    costIsDetailedInd: number;
    costIsDetailedIndValue: String;
    fkWarehousePartyId: number;
    fkPartyWhStockLocationId: DropDownModel[];
    cnfCostContrRefNo: string;
    cnfCostValidFrom: Date;
    cnfCostValidTo: Date;
    cnfCostRemarks: String;
    masterCnfCostDetail: MasterCnfCostDetail[];
    // masterCnfCostDetail: MasterCnfCostDetail[];
   // groupdetail: boolean;

}
export class DropDownModel {
    code: string;
    value: string;
    label: string;
}

export class ProductName {
     prodId: number;
     prodName: string;
}

export class PartyName {
     partyId: number;
     partyName: string;
}

export class CurrencyMaster {
    pkCurrencyId: Number;
    fkStatusId: Number;
    currencySymbol: String;
    currencyCode: String;
    currencyName: String;
}

export class MasterCnfCostDetail {
    fkFromContractTermsId: number ;
    fkToContractTermsId: number ;
    fkToCountryId: number ;
    fkToLocationId: number ;
    fkRateBasisRefId: number ;
    fkRateUomId: number ;
    rate: number ;
    fkRateCurrencyId: number ;
    transitDays: number
}

export class CnfCostMatrixMapping {


    fkOriginId: Number;
    fkOriginName: String;
    fkProdId: Number;
    fkProdName: String;
    countOfPrimary: Number;
    fkStatusIdSec: Number;
    fromIncoterm: string;
    toIncoterm: string;

}



export class UomMaster {
    uomId: Number;
    uomName: String;
    uomCode: String;
}
