import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { ResponseData } from '../../../../shared/interface/responseData';
import { CostMatrix } from '../model/cost-matrix.model';
import { SelectMatrix} from '../model/cost-matrix.model';
// tslint:disable-next-line:max-line-length
import { PartyMaster, UomMaster, PrimaryPackingMaster, SecondaryPackingMaster } from '../../cost/model/add-cost-model';
import { ProductMaster, GeoMaster, LocationMaster, CurrencyMaster, CostMaster } from '../../cost/model/add-cost-model';
import { Observable } from 'rxjs/Observable';
import { Link } from '../../../../shared/interface/link';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import { environment } from '../../../../../environments/environment';
import { AbstractLinkService } from '../../../../shared/service/abstract-link.service';
import { SelectItem } from '../../../../shared/interface/selectItem';
import { MessageModel } from '../../../../shared/message/message.model';
import { WareHouseMatrixMain , WareHouseMatrix } from '../../warehouse-cost-matrix/model/cost-warehouse';
import { CnfCostMatrix } from '../../cnf-cost-matrix/model/cnf-cost-model';
import { FreightMain } from '../../freight-cost-matrix/model/freight-cost.model';
import { MESSAGE_ERROR } from '../../../../shared/interface/common.constants';
@Injectable()
export class CostMatrixService extends AbstractLinkService {

  public errorMessage: String;
  public costMatrixModel: CostMatrix = new CostMatrix();
  // public extSystemMapping: ExternalSystem = new ExternalSystem();
  public costNameList: CostMaster[];
  public currencyList: CurrencyMaster[];
  public partyList: PartyMaster[];
  public costMatrixList: CostMatrix[];
  public viewbyIdParam: CostMatrix;
  public defaultUnitUomList: UomMaster[] = [];
  public defaultUnitPrimaryPackList: PrimaryPackingMaster[] = [];
  public defaultUnitSecondaryPackList: SecondaryPackingMaster[] = [];
  public defaultUnitProductList: ProductMaster[] = [];
  public costMatrixView: CostMatrix[];
  public costView: CostMatrix ;
  public wareHouseList: WareHouseMatrixMain = new WareHouseMatrixMain();
  public whDataList: WareHouseMatrix [] = [];
  public cnfMatrixDataList: CnfCostMatrix [] = [];
  public wareHouseArrayList: WareHouseMatrixMain[] = [];
  public freightlist: FreightMain = new FreightMain();
  public freightArraylist: FreightMain[] = [];
  public costGroupList = [{value: 1 , label: 'Freight'}, {value: 2 , label: 'Warehouse'},
  {value: 3 , label: 'Clearing & Forwarding'}, {value: 4 , label: 'Crop Season'},
  {value: 5 , label: 'Insurance'}, {value: 6 , label: 'Commission'}, {value: 7 , label: 'Others'}];
  public selectMatrixList = [{value: 1 , label: 'Freight'}, {value: 2 , label: 'Warehouse'},
  {value: 3 , label: 'Clearing & Forwarding'}, {value: 4 , label: 'Crop Season'}];
  public messages: MessageModel;
  public defaultGeoList: GeoMaster[] = [];
  public defaultLocationList: LocationMaster[] = [];
  // viewbyIdParam: CostMatrixView;
  public reqMatrixCode = false;
  public reqMatrixName = false;
  public reqMatrixType = false;
  public reqFkCostId = false;
  private returnVal = true;
  /*Warehouse Validations boolean value declarations*/
  public reqWhFkLegalEntity = [];
  public reqWhFkProdId = [];
  public reqWhFkWarehouse = [];
  public reqWhFromDate = [];
  public reqWhToDate = [];
  public reqFkPartyId = false;

  public reqWhCostName = [];
  public reqWhRateBasis = [];
  public reqWhRate = [];
  public reqWhTimeBasis = [];

  public selectMatrix: typeof SelectMatrix = SelectMatrix;

  constructor(private http: Http ) {
    super();
  }

 getPartyList() {
    const url = '/party/partyservice/party';
    return this.http.get(url).map((response: Response) => <ResponseData>response.json());
  }

  getUomList() {
    const url = '/reference/uomservice/uom';
    return this.http.get(url).map((response: Response) => <ResponseData>response.json());
  }

  getProductList() {
    const url = '/product/productservice/product';
    return this.http.get(url).map((response: Response) => <ResponseData>response.json());
  }

  getPrimaryPackingList() {
    const url = '/packing/PrimaryPackingType/viewPrimaryPackingType';
    return this.http.get(url).map((response: Response) => <ResponseData>response.json());
  }

  getSecondaryPackingList() {
    const url = '/packing/secPackTypeService/secPackType';
    return this.http.get(url).map((response: Response) => <ResponseData>response.json());
  }


  getCurrencyList() {
    const url = '/reference/currencyservice/currency';
    return this.http.get(url).map((response: Response) => <ResponseData>response.json());
  }

  getSelectedMatrixData(costMatrixId, matrixTypeName) {
 // const url = '/costMatrix/costservice/matrixservice?&matrixname=' + matrixname + '&costid=' + costid ;
   const url = '/cost/costService/costMatrix/' + costMatrixId + '/' + matrixTypeName ;
   return this.http.get(url).map((response: Response) => <ResponseData>response.json());
  }

  /* Basic View For Cost*/
  getCostMatrixDetailsJSON() {
  const url = '/cost/costService/costMatrix';
  return this.http.get(url).map((response: Response) => <ResponseData>response.json());
 }
 /*-------*/

 /* For Country region from Geo Master*/
   getGeoList() {
    const url = '/location/geoservice/geo';
    return this.http.get(url).map((response: Response) => <ResponseData>response.json());
  }
/*--------- */

 /* For Location region from location Master*/
   getLocationList() {
    const url = '/location/locationservice/location';
    return this.http.get(url).map((response: Response) => <ResponseData>response.json());
  }
/*--------- */


   saveCostDetails(link, dataItem: CostMatrix) {
      // const headers = new Headers({ 'Content-Type': 'application/json' });
      return this.http.post('/cost/costService/costMatrix', JSON.stringify(dataItem)
      ).map((res: Response) => {
                  return res.json() ;

      }).catch(( error: any ) => {
                if (error.status === 500) {
                   return Observable.throw(new Error(error.status));
                } else if (error.status === 400) {
                    return Observable.throw(new Error(error.status));
                }  else if (error.status === 409 || error.status === 404 || error.status === 403) {
                   this.messages = {severity: MESSAGE_ERROR, summary: error.json().body};
                   return Observable.throw(new Error(error.status));
                } else if (error.status === 406) {
                    return Observable.throw(new Error(error.status));
                }
      });
  }

  deleteCostMatrix(link, id, name) {
    return this.http.delete('/cost/costService/costMatrix/' + id + '/' + name ) .map((res: Response) => {
                  return res.json() ;

      }).catch(( error: any ) => {
                if (error.status === 500) {
                   return Observable.throw(new Error(error.status));
                } else if (error.status === 400) {
                    return Observable.throw(new Error(error.status));
                } else if (error.status === 409 || error.status === 404 || error.status === 403) {
                   // this.messages = [];
                   // this.messages.push({severity: 'error', summary: error.json().body, detail: ''});
                   return Observable.throw(new Error(error.status));
                } else if (error.status === 406) {
                    return Observable.throw(new Error(error.status));
                }
       });
  }

  validateCostMatrix(): Boolean {
    if (this.validateBasic() && this.validateWarehouse()) {
          return true;
    } else {
          return false;
    }
  }

  /*CheckWareHouseMaster(): Boolean {
    if (this.costMatrixModel.matrixApplicableInd === 'Y' && this.costMatrixModel.fkCostGroupId === +this.selectMatrix.Warehouse) {
      return true;
    }else {
      return false;
    }
  }*/

  validateBasic(): Boolean {
    this.returnVal = true;
    if (this.costMatrixModel.matrixCode === null || this.costMatrixModel.matrixCode === undefined) {
      this.reqMatrixCode = true;
      this.returnVal = false;
    } else {
      this.reqMatrixCode = false;
    }
    if (this.costMatrixModel.matrixName === null || this.costMatrixModel.matrixName === undefined) {
      this.reqMatrixName = true;
      this.returnVal = false;
    } else {
      this.reqMatrixName = false;
    }
    if (this.costMatrixModel.matrixTypeId === null || this.costMatrixModel.matrixTypeId === undefined) {
      this.reqMatrixType = true;
      this.returnVal = false;
    } else {
      this.reqMatrixType = false;
    }
    if (this.costMatrixModel.fkCostId === null || this.costMatrixModel.fkCostId === undefined) {
      this.reqFkCostId = true;
      this.returnVal = false;
    } else {
      this.reqFkCostId = false;
    }
    return this.returnVal;
  }

  // Validate Warehouse only

  validateWarehouse(): Boolean {
    if (this.costMatrixModel.wareHouseCostDto == null ||
     this.costMatrixModel.wareHouseCostDto === undefined ||
     this.costMatrixModel.wareHouseCostDto.length === 0) {
         return true;
     }
    if (this.costMatrixModel.matrixTypeId && +this.costMatrixModel.matrixTypeId !== +this.selectMatrix.Warehouse) {
      return true;
    }
    if (this.costMatrixModel.wareHouseCostDto[0].legalEntity == null
     || this.costMatrixModel.wareHouseCostDto[0].legalEntity === undefined ||
      this.costMatrixModel.wareHouseCostDto[0].legalEntity.length === 0) {
      this.reqWhFkLegalEntity[0] = true;
      this.returnVal = false;
    }else {
      this.reqWhFkLegalEntity[0] = false;
    }

    if (this.costMatrixModel.wareHouseCostDto[0].fkProdId == null || this.costMatrixModel.wareHouseCostDto[0].fkProdId === undefined ||
      this.costMatrixModel.wareHouseCostDto[0].fkProdId.toString() === '') {
      this.reqWhFkProdId[0] = true;
      this.returnVal = false;
    }else {
      this.reqWhFkProdId[0] = false;
    }
    if (this.costMatrixModel.wareHouseCostDto[0].wareHouse == null || this.costMatrixModel.wareHouseCostDto[0].wareHouse === undefined
    || this.costMatrixModel.wareHouseCostDto[0].wareHouse.toString() === '') {
      this.reqWhFkWarehouse[0] = true;
      this.returnVal = false;
    }else {
      this.reqWhFkWarehouse[0] = false;
    }
    if (this.costMatrixModel.wareHouseCostDto[0].whCostValidFrom == null
     || this.costMatrixModel.wareHouseCostDto[0].whCostValidFrom === undefined) {
      this.reqWhFromDate[0] = true;
      this.returnVal = false;
    }else {
      this.reqWhFromDate[0] = false;
    }
    if (this.costMatrixModel.wareHouseCostDto[0].whCostValidTo == null
     || this.costMatrixModel.wareHouseCostDto[0].whCostValidTo === undefined ) {
      this.reqWhToDate[0] = true;
      this.returnVal = false;
    }else {
      this.reqWhToDate[0] = false;
    }

    for ( let i = 0; i < this.costMatrixModel.wareHouseCostDto[0].whCostDetailArray.length; i++) {

      if (this.costMatrixModel.wareHouseCostDto[0].whCostDetailArray[i].costName == null
        || this.costMatrixModel.wareHouseCostDto[0].whCostDetailArray[i].costName === undefined ||
          this.costMatrixModel.wareHouseCostDto[0].whCostDetailArray[i].costName + '' === '' ) {
          this.reqWhCostName[i] = true;
          this.returnVal = false;
        }else {
          this.reqWhCostName[i] = false;
        }

        if (this.costMatrixModel.wareHouseCostDto[0].whCostDetailArray[i].rateBasis == null
        || this.costMatrixModel.wareHouseCostDto[0].whCostDetailArray[i].rateBasis === undefined ||
          this.costMatrixModel.wareHouseCostDto[0].whCostDetailArray[i].rateBasis + '' === '' ) {
          this.reqWhRateBasis[i] = true;
          this.returnVal = false;
        }else {
          this.reqWhRateBasis[i] = false;
        }

        if (this.costMatrixModel.wareHouseCostDto[0].whCostDetailArray[i].rate == null
        || this.costMatrixModel.wareHouseCostDto[0].whCostDetailArray[i].rate === undefined ||
          this.costMatrixModel.wareHouseCostDto[0].whCostDetailArray[i].rate + '' === '' ) {
          this.reqWhRate[i] = true;
          this.returnVal = false;
        }else {
          this.reqWhRate[i] = false;
        }

        if (this.costMatrixModel.wareHouseCostDto[0].whCostDetailArray[i].timeBasis == null
        || this.costMatrixModel.wareHouseCostDto[0].whCostDetailArray[i].timeBasis === undefined ||
          this.costMatrixModel.wareHouseCostDto[0].whCostDetailArray[i].timeBasis + '' === '' ) {
          this.reqWhTimeBasis[i] = true;
          this.returnVal = false;
        }else {
          this.reqWhTimeBasis[i] = false;
        }
    }
    return this.returnVal;
  }

  getCostMatrixById(id, name) {
    const url = '/cost/costService/costMatrix/' + id + '/' + name;
    return this.http.get(url).map((response: Response) => <ResponseData>response.json());
  }

  resetMandateStyle(): void {
    this.reqMatrixCode = false;
    this.reqMatrixName = false;
    this.reqMatrixType = false;
    this.reqFkCostId = false;
    this.reqWhFkProdId = [];
    this.reqWhFkWarehouse = [];
    this.reqWhFkLegalEntity = [];
    this.reqWhFromDate = [];
    this.reqWhToDate = [];
    this.reqWhCostName = [];
    this.reqWhRateBasis = [];
    this.reqWhRate = [];
    this.reqWhTimeBasis = [];
  }

  updateCost(link, param) {
    return this.http.put('/cost/costService/costMatrix/' , JSON.stringify(param)) .map((res: Response) => {
                  return res.json() ;

      }).catch(( error: any ) => {
                if (error.status === 500) {
                   return Observable.throw(new Error(error.status));
                } else if (error.status === 400) {
                    return Observable.throw(new Error(error.status));
                // } else if (error.status === 409 || error.status === 404 || error.status === 403) {
                }  else if (error.status === 409 || error.status === 404 || error.status === 403) {
                   this.messages = {severity: MESSAGE_ERROR, summary: error.json().body};
                   return Observable.throw(new Error(error.status));
                } else if (error.status === 406) {
                    return Observable.throw(new Error(error.status));
                }
       });
  }
}
