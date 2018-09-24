import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { ResponseData } from '../../../../shared/interface/responseData';
import { Observable } from 'rxjs/Observable';
import { Link } from '../../../../shared/interface/link';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
// import { CnfCostMatrix } from '../cnf-cost-matrix';
import { environment } from '../../../../../environments/environment';
import { AbstractLinkService } from '../../../../shared/service/abstract-link.service';
import { MasterSetupService } from '../../../master-setup/service/master-setup.service';
import { PartyName, ProductName, CnfCostMatrixMapping, CnfCostMatrix, CurrencyMaster, UomMaster } from '../model/cnf-cost-model';
@Injectable()
export class CnfCostMatrixService extends AbstractLinkService {
 public  cnfDetails: CnfCostMatrix[];
    public  arrStringJSON: any;
    public  errorMessage: string;
    public  errorStatusCode: any;
    public  cnfCodeParam: any;
    public  viewbyIdParam: any;
     public editbyIdParam: CnfCostMatrix;
    public  flag: boolean;
    public  isBrowserEventBtwComponent: boolean;
    public  geoMaster: string;
    public productOption: ProductName[];
    public legalentityOption: PartyName[];
    public  cnfCostMatrixData: CnfCostMatrix = new CnfCostMatrix();
    public CnfCostMatrixMapping: CnfCostMatrixMapping = new CnfCostMatrixMapping();
    public currencyList: CurrencyMaster[];
    public defaultUnitUomList: UomMaster[] = [];

  constructor(private http: Http) {
    super();
  }

   getCnfCostMatrixJSON(param) {
   // const link: Link = this.masterService.getChildObject('View Uom.GET');
    const url: string = param;
    // return this.http.get('https://api.myjson.com/bins/i7nz3').map((response: Response) =>response.json());
    return this.http.get(url).map((response: Response) => <ResponseData>response.json());

  }
   getMockCnfCostMatrixJSON() {

   return this.http.get('https://api.myjson.com/bins/aqpf5').map((response: Response) => response.json());
  }

    getPartyDetails() {
    const url = '/party/partyservice/party';
    return this.http.get(url).map((response: Response) => response.json());
  }

    getCurrencyList() {
    const url = '/reference/currencyservice/currency';
    return this.http.get(url).map((response: Response) => <ResponseData>response.json());
  }

   getProduct() {
    const url = '/product/productservice/product';
    return this.http.get(url).map((response: Response) => response.json());
  }

  getLegalEntity() {

   return this.http.get('https://api.myjson.com/bins/aqpf5').map((response: Response) => response.json());
  }


  getGeoMasterList(): Observable<ResponseData> {
    const url = '/location/geoservice/geo';
        return this.http.get(url).map((response: Response) => <ResponseData>response.json());
    }



  addUOM(newCnfDetails: CnfCostMatrix[]) {
    // alert("add UOM"+JSON.stringify(this.uomDetails));
     this.cnfDetails = newCnfDetails;
  }


  saveCnfDetails(cnfData: CnfCostMatrix) {
    const headers = new Headers({ 'Content-Type': 'application/json' });
    const url = '/uomservice/uom';
    return this.http.post(url,
      JSON.stringify(cnfData),
      { headers: headers })
      .map((res: Response) => {
                  if (res) {
                    if (res.status === 201) {
                        return [{ status: res.status, json: res }]
                    } else if (res.status === 200) {
                        return [{ status: res.status, json: res }]
                    }
              }

         return res.json();
      }).catch((error: any) => {
                this.errorStatusCode = error.status;
                if (error.status === 500) {
                   return Observable.throw(new Error(error.status));
                } else if (error.status === 400) {
                    return Observable.throw(new Error(error.status));
                } else if (error.status === 409 || error.status === 404) {
                   return Observable.throw(this.errorMessage = 'UOM Code Already exists.Please Choose any Other..');
                } else if (error.status === 406) {
                    return Observable.throw(new Error(error.status));
                } });
  }
   saveCnf(param) {
    const headers = new Headers({ 'Content-Type': 'application/json' });
    const url = '/uomservice/uom';
    this.arrStringJSON = '[' + JSON.stringify(param) + ']';
    return this.http.put(url, this.arrStringJSON,
      { headers: headers })
       .map((res: Response) => {
                  if (res) {
                    if (res.status === 201) {
                        return [{ status: res.status, json: res }]
                    } else if (res.status === 200) {
                        return [{ status: res.status, json: res }]
                    } }
         return res.json();
      }).catch((error: any) => {
                this.errorStatusCode = error.status;
                if (error.status === 500) {
                    // TO DO: It should not be 500
                   // this needs to be removed once backend is handled for viewbyId response code for Duplicate UOM code
                   return Observable.throw(this.errorMessage = 'UOM Code Already exists.Please Choose any Other..');
                } else if (error.status === 400) {
                    return Observable.throw(new Error(error.status));
                } else if (error.status === 409 || error.status === 404) {
                   return Observable.throw(this.errorMessage = 'UOM Code Already exists.Please Choose any Other..');
                } else if (error.status === 406) {
                    return Observable.throw(new Error(error.status));
                } });
   }

}
