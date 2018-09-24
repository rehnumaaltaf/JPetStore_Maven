import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { ResponseData } from '../../../../shared/interface/responseData';
import { CostMaster } from '../model/add-cost-model';
import { ExternalSystem , SelectMatrix} from '../model/add-cost-model';
import { CurrencyMaster } from '../model/add-cost-model';
// tslint:disable-next-line:max-line-length
import { PartyMaster, UomMaster, PrimaryPackingMaster, SecondaryPackingMaster , ProductMaster, GeoMaster, LocationMaster } from '../model/add-cost-model';
import { Observable } from 'rxjs/Observable';
import { Link } from '../../../../shared/interface/link';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import { environment } from '../../../../../environments/environment';
import { AbstractLinkService } from '../../../../shared/service/abstract-link.service';
import { SelectItem } from '../../../../shared/interface/selectItem';
import { MessageModel } from '../../../../shared/message/message.model';
import { WareHouseMatrixMain , WareHouseMatrix } from '../../warehouse-cost-matrix/model/cost-warehouse';
import { CnfCostMatrix} from '../../cnf-cost-matrix/model/cnf-cost-model';
import { FreightMain } from '../../freight-cost-matrix/model/freight-cost.model';
import { MESSAGE_ERROR } from '../../../../shared/interface/common.constants';
@Injectable()
export class CostService extends AbstractLinkService {

  public errorMessage: String;
  public costMasterModel: CostMaster = new CostMaster();
  public extSystemMapping: ExternalSystem = new ExternalSystem();
  public currencyList: CurrencyMaster[];
  public partyList: PartyMaster[];
  public costMasterList: CostMaster[];
  public viewbyIdParam: CostMaster;
  public defaultUnitUomList: UomMaster[] = [];
  public defaultUnitPrimaryPackList: PrimaryPackingMaster[] = [];
  public defaultUnitSecondaryPackList: SecondaryPackingMaster[] = [];
  public defaultUnitProductList: ProductMaster[] = [];
  public costMasterView: CostMaster[];
  public costView: CostMaster ;
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
  // viewbyIdParam: CostMasterView;

  public reqFkPartyId = false;
  public reqCostCode = false;
  public reqCostName = false;
  public reqFkCostGroupId = false;
  public reqCostTypeIsPrimaryInd = false;
  public reqMatrixApplicableInd = false;
  public reqFkMatrixEntityId = false;
  public reqNettedForMtmInd = false;
  public reqRealizedInd = false;
  public reqCapitalizeCostInd = false;
  public reqFkRevenueGlId = false;
  public reqFkExpenseGlId = false;
  private returnVal = true;
  /*Warehouse Validations boolean value declarations*/
  public reqWhFkPartyId = [];
  public reqWhFkProdId = [];
  public reqWhFkWarehouse = [];
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

  getSelectedMatrixData(costid) {
 // const url = '/basicCost/costservice/matrixservice?&matrixname=' + matrixname + '&costid=' + costid ;
   const url = '/cost/costService/basicCost/' + costid ;
   return this.http.get(url).map((response: Response) => <ResponseData>response.json());
  }

  /* Basic View For Cost*/
  getCostMasterDetailsJSON() {
  const url = '/cost/costService/basicCost';
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


   saveCostDetails(link, dataItem: CostMaster) {
      // const headers = new Headers({ 'Content-Type': 'application/json' });
      return this.http.post('/cost/costService/basicCost', JSON.stringify(dataItem)
      ).map((res: Response) => {
                  return res.json() ;

      }).catch(( error: any ) => {
                if (error.status === 500) {
                   return Observable.throw(new Error(error.status));
                } else if (error.status === 400) {
                    return Observable.throw(new Error(error.status));
                }  else if (error.status === 409 || error.status === 404 || error.status === 403) {
                   this.messages = {severity: MESSAGE_ERROR, summary: error.json().errorMessage};
                   return Observable.throw(new Error(error.status));
                } else if (error.status === 406) {
                    return Observable.throw(new Error(error.status));
                }
      });
  }

  deleteCostMaster(link, id) {
    return this.http.delete('/cost/costService/basicCost/' + id) .map((res: Response) => {
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

  validateCostMaster(): Boolean {
    if (this.validateBasic()) {
          return true;
    } else {
          return false;
    }
    /*else {
      if (this.CheckWareHouseMaster()) {
         if (this.validateBasic()) {
            const whLength = Number( JSON.stringify(this.costMasterModel.wareHouseCostDto.length));
            if (whLength >= 1) {
              for (let s = 0; s < whLength ; s++) {
                this.validateWarehouse(s);
              }
            }
         }
      }
    }*/
  }
  CheckCostMaster(): Boolean {
    if (this.costMasterModel.matrixApplicableInd === 'N') {
      return true;
    }else {
      return false;
    }
  }
  CheckWareHouseMaster(): Boolean {
    if (this.costMasterModel.matrixApplicableInd === 'Y' && this.costMasterModel.fkCostGroupId === +this.selectMatrix.Warehouse) {
      return true;
    }else {
      return false;
    }
  }

  validateBasic(): Boolean {
    this.returnVal = true;
    if (this.costMasterModel.fkPartyId === null || this.costMasterModel.fkPartyId === undefined) {
      this.reqFkPartyId = true;
      this.returnVal = false;
    } else {
      this.reqFkPartyId = false;
    }
    if (this.costMasterModel.costCode === null || this.costMasterModel.costCode === undefined) {
      this.reqCostCode = true;
      this.returnVal = false;
    } else {
      this.reqCostCode = false;
    }
    if (this.costMasterModel.costName === null || this.costMasterModel.costName === undefined) {
      this.reqCostName = true;
      this.returnVal = false;
    } else {
      this.reqCostName = false;
    }
    if (this.costMasterModel.fkCostGroupId === null || this.costMasterModel.fkCostGroupId === undefined) {
      this.reqFkCostGroupId = true;
      this.returnVal = false;
    } else {
      this.reqFkCostGroupId = false;
    }
    if (this.costMasterModel.costTypeIsPrimaryInd === null || this.costMasterModel.costTypeIsPrimaryInd === undefined) {
      this.reqCostTypeIsPrimaryInd = true;
      this.returnVal = false;
    } else {
      this.reqCostTypeIsPrimaryInd = false;
    }
    if (this.costMasterModel.matrixApplicableInd === null || this.costMasterModel.matrixApplicableInd === undefined) {
      this.reqMatrixApplicableInd = true;
      this.returnVal = false;
    } else {
      this.reqMatrixApplicableInd = false;
    }
    if (this.costMasterModel.matrixApplicableInd === 'Y'
      && (this.costMasterModel.fkMatrixEntityId === null || this.costMasterModel.fkMatrixEntityId === undefined)) {
      this.reqFkMatrixEntityId = true;
      this.returnVal = false;
    } else {
      this.reqFkMatrixEntityId = false;
    }
    if (this.costMasterModel.nettedForMtmInd === null || this.costMasterModel.nettedForMtmInd === undefined) {
      this.reqNettedForMtmInd = true;
      this.returnVal = false;
    } else {
      this.reqNettedForMtmInd = false;
    }
    if (this.costMasterModel.realizedInd === null || this.costMasterModel.realizedInd === undefined) {
      this.reqRealizedInd = true;
      this.returnVal = false;
    } else {
      this.reqRealizedInd = false;
    }
    if (this.costMasterModel.capitalizeCostInd === null || this.costMasterModel.capitalizeCostInd === undefined) {
      this.reqCapitalizeCostInd = true;
      this.returnVal = false;
    } else {
      this.reqCapitalizeCostInd = false;
    }
    if (this.costMasterModel.fkRevenueGlId === null || this.costMasterModel.fkRevenueGlId === undefined) {
      this.reqFkRevenueGlId = true;
      this.returnVal = false;
    } else {
      this.reqFkRevenueGlId = false;
    }
    if (this.costMasterModel.fkExpenseGlId === null || this.costMasterModel.fkExpenseGlId === undefined) {
      this.reqFkExpenseGlId = true;
      this.returnVal = false;
    } else {
      this.reqFkExpenseGlId = false;
    }
    return this.returnVal;
  }

  // Validate Warehouse only

  validateWarehouse(s): Boolean {
    //  alert(s + 'check of product');
    if (this.costMasterModel.wareHouseCostDto[s].fkProdId == null || this.costMasterModel.wareHouseCostDto[s].fkProdId === undefined ||
      this.costMasterModel.wareHouseCostDto[s].fkProdId.toString() === '') {
     // alert('inside if of product value  ');
      this.reqWhFkProdId[s] = true;
      this.returnVal = false;
    }else {
      this.reqWhFkProdId[s] = false;
    }
    if (this.costMasterModel.wareHouseCostDto[s].wareHouse == null || this.costMasterModel.wareHouseCostDto[s].wareHouse === undefined
       || this.costMasterModel.wareHouseCostDto[s].wareHouse.toString() === '') {
          this.reqWhFkWarehouse[s] = true;
          this.returnVal = false;
       }else {
          this.reqWhFkWarehouse[s] = false;
       }
       // const whCloneLength = Number( JSON.stringify(this.costMasterModel.wareHouseCostDto[s].whCostDetailArray.length));
      //  for (let w = 0; w < whCloneLength; w++) {

      //  }
    return this.returnVal;
  }

  getCostMasterById(id) {
    const url = '/cost/costService/basicCost/' + id;
    return this.http.get(url).map((response: Response) => <ResponseData>response.json());
  }

  resetMandateStyle(): void {
    this.reqFkPartyId = false;
    this.reqCostCode = false;
    this.reqCostName = false;
    this.reqFkCostGroupId = false;
    this.reqCostTypeIsPrimaryInd = false;
    this.reqMatrixApplicableInd = false;
    this.reqFkMatrixEntityId = false;
    this.reqNettedForMtmInd = false;
    this.reqRealizedInd = false;
    this.reqCapitalizeCostInd = false;
    this.reqFkRevenueGlId = false;
    this.reqFkExpenseGlId = false;
    this.reqWhFkPartyId = [];
    this.reqWhFkProdId = [];
    this.reqWhFkWarehouse = [];
  }

  updateCost(link, param) {
    return this.http.put('/cost/costService/basicCost/' , JSON.stringify(param)) .map((res: Response) => {
                  return res.json() ;

      }).catch(( error: any ) => {
                if (error.status === 500) {
                   return Observable.throw(new Error(error.status));
                } else if (error.status === 400) {
                    return Observable.throw(new Error(error.status));
                // } else if (error.status === 409 || error.status === 404 || error.status === 403) {
                }  else if (error.status === 409 || error.status === 404 || error.status === 403) {
                   this.messages = {severity: MESSAGE_ERROR, summary: error.json().errorMessage};
                   return Observable.throw(new Error(error.status));
                } else if (error.status === 406) {
                    return Observable.throw(new Error(error.status));
                }
       });
  }

}
