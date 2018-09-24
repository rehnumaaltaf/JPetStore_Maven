import { SelectItem } from '../../../../shared/interface/selectItem';
import { PartyMaster } from '../../cost/model/add-cost-model';

export class WareHouseMatrixMain {
    wareHouseMatrix: WareHouseMatrix[]
}

export class WareHouseMatrix {
    pkWhCostId: number;
    public isApplicableToAllPartyInd = 0;
    legalEntity: PartyMaster[] = [];
    fkProdId: number;
    fkProdValue: string;
    costIsDetailedInd: string;
    wareHouse: number;
    wareHouseValue: string;
    public wareHouseLocation = [];
    whLocId: number[];
    whCostContrRefNo: string;
    whCostValidFrom: Date;
    whCostValidTo: Date;
    whCostRemarks: string;
    whCostFreePeriodType: string;
    whCostNoOfFreeDays: number;
    whCostDetailArray: WarehouseCostArray[];
    whAction: string;
}

export class WarehouseCostArray {
    wareHouseId: number;
    costName: number;
    rateBasis: number;
    whfkDefaultValueUomId: number;
    whfkDefValPrimaryPackingId: number;
    whfkDefValSecondaryPackingId: number;
    whfkDefaultValueUomName: string;
    whfkDefValPrimaryPackingName: string;
    whfkDefValSecondaryPackingName: string;
    rateCurrency: number;
    rate: number;
    timeBasis: number;
    costNameValue: string;
    fkRateBasisName: string;
    fkUomName: string;
    fkCurrencyName: string;
    fkTimeBaisisName: string;
    pkWhDetailId: number;
}

