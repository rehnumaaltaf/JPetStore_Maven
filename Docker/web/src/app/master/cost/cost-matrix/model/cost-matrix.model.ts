import { FreightMatrix } from '../../freight-cost-matrix/model/freight-cost.model';
import { WareHouseMatrix } from '../../warehouse-cost-matrix/model/cost-warehouse';
import { CnfCostMatrix } from '../../cnf-cost-matrix/model/cnf-cost-model';

export class CostMatrix {
      pkCostMatrixId: Number;
      matrixName: string;
      matrixCode: string;
      matrixDesc: string;
      fkCostId: Number;
      fkCostName: string;
      matrixTypeId: Number;
      matrixTypeName: string;
      serviceProvider: string;
      wareHouseCostDto: WareHouseMatrix[];
      cnfCostDto: CnfCostMatrix[];
      freightCostMatrixDto: FreightMatrix[];
      /*cropSeasonCostDto:
      MasterCnfCostDto cnfCostDto;
      MasterFreightCostMatrixDto freightCostMatrixDto;
      MasterCropSeasonCostDto cropSeasonCostDto;*/
      action?: String;
      fkStatusId: Number;
      fkStatusName: string;
      checked?: boolean;
}

export enum SelectMatrix {
    Freight = 1,
    Warehouse = 2,
    Clearing_Forwarding = 3,
    Crop_Season = 4
}
