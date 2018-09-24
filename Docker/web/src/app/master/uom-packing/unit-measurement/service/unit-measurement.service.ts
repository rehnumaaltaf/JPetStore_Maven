import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { ResponseData } from '../../../../shared/interface/responseData';
import { Observable } from 'rxjs/Observable';
import { BehaviorSubject } from 'rxjs/Rx';
import { Link } from '../../../../shared/interface/link';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import { GridComponent, GridDataResult, DataStateChangeEvent} from '@progress/kendo-angular-grid';
import { UnitMeasurement } from '../unit-measurement';
import { environment } from '../../../../../environments/environment';
import { AbstractLinkService } from '../../../../shared/service/abstract-link.service';

@Injectable()
export class UnitMeasurementService extends AbstractLinkService {
  uomDetails: UnitMeasurement[];
  arrStringJSON: any;
  errorMessage: string;
  errorStatusCode: any;
  uomCodeParam: any;
  viewbyIdParam: any;
  flag: boolean;
  isBrowserEventBtwComponent: boolean;

    constructor( private http: Http) {
    super();
   }

  getUnitMeasurementJSON(param) {
     // alert('param==>' + param);
   // const link: Link = this.masterService.getChildObject('View Uom.GET');
    const url: string = param;
    // alert(url)
    // return this.http.get('https://api.myjson.com/bins/utq1p').map((response: Response) =>response.json());
    return this.http.get(url).map((response: Response) => <ResponseData>response.json());
  }

  addUOM(newUOMDetails: UnitMeasurement[]) {
    // alert("add UOM"+JSON.stringify(this.uomDetails));
     this.uomDetails = newUOMDetails;
  }
  
  uomCodeSuggestion(param): Observable<ResponseData> {
    // const link: Link = this.masterService.getChildObject('View Uom.GET');
    // const url = link.href;
    const url = '/reference/uomservice/uom?uomCode=' + param;
    console.log(url);
    return this.http.get(url).map((response: Response) =>  <ResponseData> response.json());
     // return this.http.get(url).map((response: Response) => <ResponseData>response.json());
  }

  uomNameSuggestion(param): Observable<ResponseData> {
    // const link: Link = this.masterService.getChildObject('View Uom.GET');
    // const url = link.href;
    const url = '/reference/uomservice/uom?uomName=' + param;
    return this.http.get(url).map((response: Response) =>  <ResponseData> response.json());
     // return this.http.get(url).map((response: Response) => <ResponseData>response.json());
  }

  saveUOMDetails(uomData: UnitMeasurement) {
    // const headers = new Headers({ 'Content-Type': 'application/json' });
   // alert('=uomData==>' + JSON.stringify(uomData));
    const url: string = '/reference/uomservice/uom';
     return this.http.post(url,
      JSON.stringify(uomData),
    //   { headers: headers }
    )
      .map((res: Response) => {
                  if (res) {
                    /*if (res.status === 201) {
                        return [{ status: res.status, json: res }] }else*/
                  if (res.status === 200) {
                        return [{ status: res.status, json: res }]
                    }
                }
                  return res.json() ;

                }).catch((error: any) => {

          return this.errorHandling(error);
      });

     /* }).catch(( error: any ) => {
                this.errorStatusCode = error.status;
                if (error.status === 500) {
                   return Observable.throw(new Error(error.status));
                } else if (error.status === 400) {
                    return Observable.throw(new Error(error.status));
                } else if (error.status === 409 || error.status === 404) {
                   return Observable.throw( this.errorMessage = 'UOM Code Already exists.Please Choose any Other..');
                } else if (error.status === 406) {
                    return Observable.throw(new Error(error.status));
                }
   });*/
  }

  saveUOM(param) {
   //  alert(''+JSON.stringify(param));
  //  const headers = new Headers({ 'Content-Type': 'application/json' });
    const url = '/reference/uomservice/uom';
     // alert('param==>' + JSON.stringify(param));
    this.arrStringJSON = ' [ ' + JSON.stringify(param) + ' ]';
    return this.http.put( url, this.arrStringJSON)
          .map((res: Response ) => {
                  if (res) {
                    /*if (res.status === 201) {
                        return [{ status: res.status, json: res }] } else*/
                     if (res.status === 200) {
                        return [{ status: res.status, json: res }]
                    }
              }
            return res.json();
             }).catch((error: any) => {

          return this.errorHandling(error);
      });

     /* }).catch((error: any ) => {
                this.errorStatusCode = error.status;
             if (error.status === 500) {
                    // TO DO: It should not be 500
                   // this needs to be removed once backend is handled for viewbyId response code for Duplicate UOM code
                   return Observable.throw( this.errorMessage = 'UOM Code Already exists.Please Choose any Other..' ) ;
              } else if (error.status === 400) {
                    return Observable.throw(new Error(error.status));
              } else if (error.status === 409 || error.status === 404) {
                   return Observable.throw(this.errorMessage = 'UOM Code Already exists.Please Choose any Other..');
              } else if (error.status === 406) {
                    return Observable.throw(new Error(error.status));
              }
      });*/
   }

 deleteUOMById(param) {
   // const headers = new Headers({ 'Content-Type': 'application/json' }) ;
    const url = '/reference/uomservice/uom';
    // alert("stringify param==>"+JSON.stringify(param));
    this.arrStringJSON = ' [ ' + JSON.stringify(param) + ' ] ';
    return this.http.delete(url , new RequestOptions({
                      body:  this.arrStringJSON
              })) .map((res: Response ) => {
             if (res) {
                    /*if (res.status === 201) {
                        return [{ status: res.status, json: res }] } else*/
                   if (res.status === 200) {
                         return [{ status: res.status, json: res }]
                    }
              }
        return res.json();
         }).catch((error: any) => {

          return this.errorHandling(error);
      });

     /* }).catch((error: any) => {
                 this.errorStatusCode = error.status;
                if (error.status === 422) {
                    // return Observable.throw(new Error(error.status));
                     return Observable.throw(new Error(this.errorMessage = 'Cannot Delete..UOM code is+'
                      + ' already associated as a parent to some other UOM'));
                     // this.errorMessage="UOM Code Already exists.Please Choose any Other..")
                } else if (error.status === 400) {
                    return Observable.throw(new Error(error.status));
                } else if (error.status === 409 || error.status === 404) {
                   return Observable.throw(new Error(this.errorMessage = 'UOM Code Already exists.Please Choose any Other..'));
                } else if (error.status === 406) {
                    return Observable.throw(new Error(error.status));
                }
         });*/
   }
}
