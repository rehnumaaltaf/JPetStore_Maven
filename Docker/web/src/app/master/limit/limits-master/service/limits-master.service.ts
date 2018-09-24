import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { ResponseData } from '../../../../shared/interface/responseData';
import { Observable } from 'rxjs/Observable';
import { LimitsMaster, LimitDetails, LimitsAttribute } from '../model';
import { AbstractLinkService } from '../../../../shared/service/abstract-link.service';
@Injectable()
export class LimitsMasterService extends AbstractLinkService {
  volumeValueArr = [];
  valueList = [];
  volumeList = [];
   public limitBasisTypeName: string;
   arrStringJSON: any;
  errorStatusCode: Number;
  errorMessage: string;
    isVolumeDropDownOutright: boolean;
  isValueDropDownOutright: boolean;
  limitMaster: LimitsMaster;
  forexFlag: boolean;
  exchangeFlag: boolean;
  otherlimitFlag: boolean;
  errorresponse: ResponseData;
  addoreditpageflag: boolean;
  msgStatusName: string;
  limitName: string;
  constructor(private http: Http) {
     super();
  }


  LimitBasisDropDown() {
    const url = '/limit/limitservice/limit/limitBasisType';
       return this.http.get(url).map((response: Response) => response.json());
  }

  VolumeValueDropDown() {
    // https://api.myjson.com/bins/fiz49
    // limit/limktervice/limit/limitVolumeValueType
    // limit/limitservice
    return this.http.get('/limit/limitservice/limit/limitVolumeValueType').map((response: Response) => response.json());
  }
  getLimitMasterJSON() {
       return this.http.get('/limit/limitservice/limit/').map((response: Response) => response.json()).catch((error: any) => {
          return this.errorHandling(error);
      });
  }

  getVolumeListJSON() {
    // https://api.myjson.com/bins/efk7p
    // http://10.66.23.132:8042/reference/uomservice/uom
    return this.http.get('/reference/uomservice/uom').map((response: Response) => response.json());
  }

  getValueListJSON() {
        return this.http.get('/reference/currencyservice/currency').map((response: Response) => response.json());
  }

  getLimitBasisNameDropDown(url) {
     return this.http.get(url).map((response: Response) => response.json());
  }

  getCurrencyListDropDown() {
     return this.http.get('/reference/currencyservice/currency').map((response: Response) => response.json());
  }
    getLimitsDetailsById(param) {
    const url: string = '/limit/limitservice/limit/' + param;
      return this.http.get(url).map((response: Response) => response.json());

  }


  setlimitDetails(limit: LimitsMaster) {
       this.limitMaster = limit;
       }





// save, submit , update the data based on  add or edit page parameter then send to rest api
saveLimitMaster(param , addoredit) {
   this.addoreditpageflag = addoredit;
   const url: string = '/limit/limitservice/limit/'.toString();
  console.log('In service save limit ' + addoredit);
  if (addoredit ) {
          return this.http.post(url  , param)
     .map((res: Response ) => {
            if (res) {
            if (res.status === 200) {
                return [{ status: res.status, json: res }]
            }
          }
        return res.json();
      }).catch((error: any) => {
         this.msgStatusName = null;
          return this.errorHandling(error);
      });
   }else if ( addoredit === false) {
        return this.http.put(url  , param)
           .map((res: Response ) => {
           if (res) {
             if (res.status === 200) {
                return [{ status: res.status, json: res }]
            }
          }
        return res.json();
      }).catch((error: any) => {
        this.msgStatusName = null;
            return this.errorHandling(error);
      });
   }
}


  deleteLimit(param, limitBasisTypeName) {
     this.limitName = limitBasisTypeName;
     
const url = '/limit/limitservice/limit';
    this.arrStringJSON = ' [ ' + JSON.stringify(param) + ' ] ';
   return this.http.delete(url , new RequestOptions({
                     body:  this.arrStringJSON
              })) .map((res: Response ) => {
             if (res) {
                    if (res.status === 200) {
                        return [{ status: res.status, json: res }]
                    }
              }
        return res.json();
      }).catch((error: any) => {
        this.msgStatusName = null;
                 return this.errorHandling(error);
         });
   }

}

