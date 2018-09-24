import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { ResponseData } from '../../../../shared/interface/responseData';
import { Observable } from 'rxjs/Observable';
import { BehaviorSubject } from 'rxjs/Rx';
import { Link } from '../../../../shared/interface/link';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import { GridComponent, GridDataResult, DataStateChangeEvent} from '@progress/kendo-angular-grid';
import { environment } from '../../../../../environments/environment';
import { AbstractLinkService } from '../../../../shared/service/abstract-link.service';

@Injectable()
export class CertifyMeasurementService extends AbstractLinkService {
  arrStringJSON: any;
  errormessage: string;
  errorStatusCode: any;
  certifyCodeParam: any;
  listparty: any;
  addparam: any;
  viewbyIdParam: any;
  flag: boolean;
  editview: boolean
  editByIdParam: any;
  isBrowserEventBtwComponent: boolean;

    constructor( private http: Http) {
    super();
   }

   loadpartyJSON() {
     return this.http.get('/party/partyservice/party?partyTypeName=Certification%20Agency').map((response: Response) => response.json());
   }

  getcertifyJSON() {
         return this.http.get('/product/certificationservice/certification').map((response: Response) => response.json());
  }

   getCertDetailsById(certId) {
         const url: string = '/product/certificationservice/certification/' + certId;
         // alert('url b4 service call ' + url);
         return this.http.get(url).map((response: Response) => response.json());
  }

  addcertifyJSON(data) {
    sessionStorage.setItem('cer_code', data.prodCertName);
   //  alert('req JSOn--->' + JSON.stringify(data));
    return this.http.post('/product/certificationservice/certification',
      JSON.stringify(data))
      // { headers: headers })
    .map((res: Response) => {
      if (res) {
           /* if (res.status === 201) {
                return [{ status: res.status, json: res }] }else*/
             if (res.status === 200) {
                return [{ status: res.status, json: res }]
            }
          }
        return res.json();
      }).catch((error: any) => {
                /*if (error.status === 500) {
                    return Observable.throw(new Error(error.status));
                }else if (error.status === 400) {
                    return Observable.throw(new Error(error.status));
                }else if (error.status === 409)  {
                   return Observable.throw(new Error(this.errorMessage = 'Payment Term Code Already exists.Please Choose any Other..'));
                }else if (error.status === 406) {
                    return Observable.throw(new Error(error.status));
                }else if (error.status === 404) {
                    return Observable.throw(new Error(error.status));
                }*/
                 return this.errorHandling(error);
      });
}

submitcertifyJSON(data) {
 // alert('req JSOn--->' + JSON.stringify(data));
  return this.http.post('/product/certificationservice/certification',
      JSON.stringify(data))
      // { headers: headers })
       .map((res: Response) => {
      
      if (res) {
           /* if (res.status === 201) {
                return [{ status: res.status, json: res }] }else*/
             if (res.status === 200) {
                return [{ status: res.status, json: res }]
            }
          }
        return res.json();
      }).catch((error: any) => {
                /*if (error.status === 500) {
                    return Observable.throw(new Error(error.status));
                }else if (error.status === 400) {
                    return Observable.throw(new Error(error.status));
                }else if (error.status === 409)  {
                   return Observable.throw(new Error(this.errorMessage = 'Payment Term Code Already exists.Please Choose any Other..'));
                }else if (error.status === 406) {
                    return Observable.throw(new Error(error.status));
                }else if (error.status === 404) {
                    return Observable.throw(new Error(error.status));
                }*/
                 return this.errorHandling(error);
      });
}


updatecertifyJSON(data) {
  // this.arrStringJSON = ' [ ' + JSON.stringify(data) + ' ]';
  // alert('==updateJSon==>' + JSON.stringify(data));
   return this.http.put('/product/certificationservice/certification',
      data)
      // { headers: headers })
      .map((res: Response) => {
      
      if (res) {
           /* if (res.status === 201) {
                return [{ status: res.status, json: res }] }else*/
             if (res.status === 200) {
                return [{ status: res.status, json: res }]
            }
          }
        return res.json();
      }).catch((error: any) => {
                /*if (error.status === 500) {
                    return Observable.throw(new Error(error.status));
                }else if (error.status === 400) {
                    return Observable.throw(new Error(error.status));
                }else if (error.status === 409)  {
                   return Observable.throw(new Error(this.errorMessage = 'Payment Term Code Already exists.Please Choose any Other..'));
                }else if (error.status === 406) {
                    return Observable.throw(new Error(error.status));
                }else if (error.status === 404) {
                    return Observable.throw(new Error(error.status));
                }*/
                 return this.errorHandling(error);
      });
}

deleteCertificateById(param) {
              this.arrStringJSON = ' [ ' + JSON.stringify(param) + ' ] ';
             return this.http.delete('/product/certificationservice/certification' , new RequestOptions({
                              // headers: headers,
                                body:  this.arrStringJSON
                        })) .map((res: Response ) => {
                            if (res) {
                                    if (res.status === 201) {
                                        return [{ status: res.status, json: res }]
                                    } else if (res.status === 200) {
                                        return [{ status: res.status, json: res }]
                                    }
                              }
                        return res.json();
                     }).catch((error: any) => {
                           return this.errorHandling(error);
                                /*this.errorStatusCode = error.status;
                                if (error.status === 422) {
                                    // return Observable.throw(new Error(error.status));
                                    return Observable.throw(new Error(this.errormessage = ''));
                                    // this.errorMessage="UOM Code Already exists.Please Choose any Other..")
                                } else if (error.status === 400) {
                                    return Observable.throw(new Error(error.status));
                                } else if (error.status === 409 || error.status === 404) {
                                  return Observable.throw(new Error(this.errormessage = ''));
                                } else if (error.status === 406) {
                                    return Observable.throw(new Error(error.status));
                                }*/
                  });
            }

}
