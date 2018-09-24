import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { ResponseData } from '../../../../shared/interface/responseData';
import { Observable } from 'rxjs/Observable';
import { Link } from '../../../../shared/interface/link';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import { OriginDefinition } from '../origin-definition';
import { environment } from '../../../../../environments/environment';
import { AbstractLinkService } from '../../../../shared/service/abstract-link.service';
import { MasterSetupService } from '../../../master-setup/service/master-setup.service';
import { ModalModule } from 'ngx-bootstrap';
import {Origin } from '../origin';

@Injectable()
export class OriginDefinitionService extends AbstractLinkService {
  originDefinitionDetails: OriginDefinition[];
  originDefinitionCupProfileDetails: OriginDefinition[];
  originDefinitionGroup: OriginDefinition;
  originRegionCodeDetails: OriginDefinition[];
  originRegionNameDetails: OriginDefinition[];
  public originRegionCode: Origin[];
  public originRegionName: Origin[];
  public  originRegionMeanAboveSeaLevel: Origin[];
  public  regionEstate: Origin[];
  public  originCupProfileName: Origin[];
  public  originCupProfileCode: Origin[];
  public  geoName: Origin [];
  public  destinationSystem: Origin[];
  public  type: Origin[];
  public  mapping: Origin[];
  originCodeParam: any;
  originRegionCodeResp: any;
  originRegionNameResp: ResponseData;
  destinationSystemResp: ResponseData;
  // OriginDefinitionDetailsGroup: OriginDefinition[];
 errorMessage: any;
 errorMessageFromDb: any;
  successModal: ModalModule;
  sharingData: any = {};
    arrStringJSON: any;
    errorStatusCode: any;
    viewbyIdParam: any;
 // originModel: OriginModel;
 // originDetails: OriginDefinition[];
    constructor(private http: Http, public masterService: MasterSetupService) {
    super();
  }


getOriginDropDownJSON(): Observable<ResponseData> {
     const url = '/geoservice/geo';    // return this.http.get('http://ctrmsql:8080/geoservice/geo')
      return this.http.get(url)
    .map((response: Response) => response.json());

  }
  getPackingDropDownJSON(): Observable<ResponseData> {
      const url = '/packingservice/packing';     // for dev integration
   //  return this.http.get('https://api.myjson.com/bins/15yj25') // new mockjson execute in local
     // return this.http.get('http://10.66.194.9:9090/packingservice/packing')
     return this.http.get(url)
    .map((response: Response) => response.json());

  }
   getDestinationSystemDropDownJSON(): Observable<ResponseData> {
    // const url = '/glservice/gl/externalsystemref';
      return this.http.get('https://api.myjson.com/bins/a7vc1')
     // return this.http.get('http://10.66.194.9:8089/glservice/gl/externalsystemref')
     // return this.http.get(url)
    .map((response: Response) => response.json());

  }


  getProductDropDownJSON(): Observable<ResponseData> {
     const url = '/productservice/product ';
    // return this.http.get('http://10.66.194.9:8080/productservice/product')
    //  return this.http.get('https://api.myjson.com/bins/127hy5')  // mockjson
     return this.http.get(url)
    .map((response: Response) => response.json());

  }
// get permission Group list via RESTAPI
getPermissionGroupViewList() {
    const url = 'permissionmapping/permissions';
    return this.http.get('http://127.0.0.1:8080/permissionmapping/permissions/getDropBox')
    .map(res =>  res.json());
 }
addOriginDetailsMultiple(OriginDefinitionDetails: OriginDefinition[]) {
    this.originDefinitionDetails = OriginDefinitionDetails;
   }
   addOriginCupProfileDetailsMultiple(OriginDefinitionCupProfileDetails: OriginDefinition[]) {
    this.originDefinitionCupProfileDetails = OriginDefinitionCupProfileDetails;
   }
addOriginRecord(OriginDefinitionGroup: OriginDefinition) {
    this.originDefinitionGroup = OriginDefinitionGroup;
  }

addOriginRegionCodeDetails(originRegionCodeDetails: OriginDefinition[]) {
  this.originRegionCodeDetails = originRegionCodeDetails;
}
addOrigionRegionNameDetails(originRegionNameDetails: OriginDefinition[]) {
  this.originRegionNameDetails = originRegionNameDetails;

}




  getOriginDefinitionJSON() {
   // const link: Link = this.masterService.getChildObject('View Origin.GET');
  //  const url: string = link.href;

   const url: string = environment.path + '/originservice/origin';   // alert(url + 'sdfkjksadfj')
      return this.http.get(url).map((response: Response) => response.json());
     // return this.http.get(url).map((response: Response) =><ResponseData>response.json());
 }
getDropdownListJSON(): Observable<ResponseData> {
    // const url: string = '/uomservice/uom';
    const url = 'http://10.87.67.121:8080/origin/getddlist';
    return this.http.get(url).map((response: Response) => <ResponseData>response.json());
  }
// below method from uom
  saveOriginDetails(originData: OriginDefinition) {
    const headers = new Headers({ 'Content-Type': 'application/json' });
    const url = '/originservice/origin?action=draft';
    return this.http.post(url,
      JSON.stringify(originData),
      { headers: headers })
      .map((res) => res.json())

  }
  // below code from add-unit service
   saveOrigin1(param): Observable<ResponseData> {
    const headers = new Headers({ 'Content-Type': 'application/json' });
    console.log(JSON.stringify(param));
    // const url = 'http://10.87.67.121:8080/origin/create';
    const url = 'http://10.66.194.55:8080/originservice/origin';
 // alert('Hi' + JSON.stringify(uomData));
    return this.http.post(url, param,
      { headers: headers })
      .map((res) => res.json());

  }
   submitOriginDetails(originDefinition: OriginDefinition) {
  /* const headers = new Headers({ 'Content-Type': 'application/json' });
    const url: string = environment.path + '/uomservice/uom' + '?action=save';    // console.log('Hi' + JSON.stringify(uomData));
    return this.http.post(url,
      JSON.stringify(originDefinition),
      { headers: headers })
      .map((res : Response) =>{
             if (res) {
                    if (res.status === 201) {
                        return [{ status: res.status, json: res }]
                    }
                    else if (res.status === 200) {
                        return [{ status: res.status, json: res }]
                    }
              }
         return res.json();
      }).catch((error: any) => {
               if (error.status === 500) {
                    return Observable.throw(new Error(error.status));
                }
                else if (error.status === 400) {
                    return Observable.throw(new Error(error.status));
                }
                else if (error.status === 409 || error.status === 404) {
                   return Observable.throw(new Error(this.errorMessage="UOM Code Already exists.Please Choose any Other.."));
                }
                else if (error.status === 406) {
                    return Observable.throw(new Error(error.status));
                }
                });*/
  }

validateOriginName(param): Observable<ResponseData> {
    // const url: string = '/profitcenter/validate';
    const url = 'http://10.87.67.121:8080/origin/validate';
    const headers = new Headers({ 'Content-Type': 'application/json' });
    console.log(JSON.stringify(param));
    return this.http.post(url,
      JSON.stringify(param),
      { headers: headers })
      .map((res) => res.json());
  }
  validateOriginFullName(): Observable<ResponseData> {
    const url = 'http://10.87.67.121:8080/origin/validate';
    return this.http.get(url).map((response: Response) => <ResponseData>response.json());
  }


  updateOrigin(param): Observable<ResponseData> {
    const headers = new Headers({ 'Content-Type': 'application/json' });
    console.log(JSON.stringify(param));
    const url = 'http://10.87.67.121:8080/origin/create';
    return this.http.post(url,
      JSON.stringify(param),
      { headers: headers })
      .map((res) => res.json());

  }
   savePermissionGroup(param, status) {
   const headers = new Headers({ 'Content-Type': 'application/json' });
     console.log(JSON.stringify(param));
     const url: string = '/originservice/origin?action=' + status ;
    // const url: string = " http://ctrmsql:8080/originservice/origin?action=save";
      return this.http.post(url,
      JSON.stringify(param),
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
      }).catch((error: any, errorMessageFromDb: any) => {
               const errMsg = JSON.stringify(error.json().errorMessage);
               if (error.status === 500) {
                    return Observable.throw(new Error(error.status));
                } else if (error.status === 400) {
                    return Observable.throw(new Error(error.status));
                } else if (error.status === 409 || error.status === 404) {
                  // alert("==error.status=>"+error.status+"====message=====>"+errMsg);
                   return Observable.throw(new Error(this.errorMessage = errMsg));
                } else if (error.status === 406) {
                    return Observable.throw(new Error(error.status));
                }
      });
}
    updateStatusInView(param, status) {
      console.log(status);
     const headers = new Headers({ 'Content-Type': 'application/json' });
     const url: string = environment.path + '/originservice/origin?action=' + status ;
     this.arrStringJSON = '[' + JSON.stringify(param) + ']';
     console.log(this.arrStringJSON);
     return this.http.put(url, this.arrStringJSON,
       { })
        .map((res: Response) => {
                  if (res) {
                    if (res.status === 201) {
                        return [{ status: res.status, json: res }]
                    } else if (res.status === 200) {
                        return [{ status: res.status, json: res }]
                    }
              }
          return res.json();
    /*   }).catch((error: any) => {
                this.errorStatusCode = error.status;
                if (error.status === 500) {
                    // TO DO: It should not be 500
                   // this needs to be removed once backend is handled for viewbyId response code for Duplicate GL code
                   return Observable.throw(this.errorMessage = ' Origin Name Already exists.Please Choose any Other..');
                } else if (error.status === 400) {
                    return Observable.throw(new Error(error.status));
                } else if (error.status === 409 || error.status === 404) {
                   return Observable.throw(this.errorMessage = 'Origin Name Already exists.Please Choose any Other..');
                } else if (error.status === 406) {
                    return Observable.throw(new Error(error.status));
                }
 }); */
                }).catch((error: any, errorMessageFromDb: any) => {
               const errMsg = JSON.stringify(error.json().errorMessage);
               if (error.status === 500) {
                    return Observable.throw(new Error(error.status));
                } else if (error.status === 400) {
                    return Observable.throw(new Error(error.status));
                } else if (error.status === 409 || error.status === 404) {
                  // alert("==error.status=>"+error.status+"====message=====>"+errMsg);
                   return Observable.throw(new Error(this.errorMessage = errMsg));
                } else if (error.status === 406) {
                    return Observable.throw(new Error(error.status));
                }
      });
   }
deleteOriginById(param) {
    const headers = new Headers({ 'Content-Type': 'application/json' });
const url = '/originservice/origin';   // alert("stringify param==>"+JSON.stringify(param));
    this.arrStringJSON = ' [ ' + JSON.stringify(param) + ']';
       return this.http.delete(url, new RequestOptions({
                      headers: headers,
                      body: this.arrStringJSON
       })) .map((res: Response) => {
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
               if (error.status === 400) {
                    return Observable.throw(new Error(error.status));
                } else if (error.status === 409 || error.status === 404) {
                   return Observable.throw(new Error(this.errorMessage = 'service is not working'));
                } else if (error.status === 406) {
                    return Observable.throw(new Error(error.status));
                }

      });
   }
}
