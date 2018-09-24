import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { ResponseData } from '../../../../shared/interface/responseData';
import { Observable } from 'rxjs/Observable';
import { Subject } from 'rxjs/Subject';
import 'rxjs/add/operator/map';
import { environment } from '../../../../../environments/environment';
import { PaymentTerm } from './../payment-terms.interface';
import { SelectItem } from '../../../../shared/interface/selectItem';
import { AbstractLinkService } from '../../../../shared/service/abstract-link.service';
import { noLoading } from '../../../../shared/interface/router-links';

@Injectable()
export class PaymentTermsService extends AbstractLinkService {
    paymentTermsDetails: PaymentTerm[];
    paymentTerm: PaymentTerm = new PaymentTerm();
    public payTermCode: string;
    public payTermName: string;
    paramaction: string;
    arrStringJSON: any;
    externalSystemdata: SelectItem [] = [];
    errorMessage: string;
    addoreditpageflag: boolean;
    paymenttermName: string;
    basepaymentcode:string;
  constructor(private http: Http ) {
      super();
  }

   getPaymentTermsById(id): Observable<ResponseData> {
   
    const url: string = '/terms/paymenttermservice/paymentterm/' + id;
     return this.http.get(url).map((response: Response) =>
     <ResponseData>response.json())
       .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
}

  getPaymentTermsViewList(): Observable<ResponseData>  {
    const url =  '/terms/paymenttermservice/paymentterm';
    return this.http.get(url)
    .map(res =>  res.json())
        .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
 }

addPayment(paymentTermsDetails: PaymentTerm[]) {
    this.paymentTermsDetails = paymentTermsDetails;
   }

payTermCodeSuggestion(param): Observable<ResponseData> {
    // const link: Link = this.masterService.getChildObject('View Uom.GET');
    // const url = link.href;
    const url = noLoading + '/terms/paymenttermservice/paymentterm/?termCode=' + param;
    console.log(url);
    return this.http.get(url).map((response: Response) =>  <ResponseData> response.json());
     // return this.http.get(url).map((response: Response) => <ResponseData>response.json());
  }

  payTermNameSuggestion(param): Observable<ResponseData> {
    // const link: Link = this.masterService.getChildObject('View Uom.GET');
    // const url = link.href;
    const url = noLoading + '/terms/paymenttermservice/paymentterm/?termName=' + param;
    console.log(url);
    return this.http.get(url).map((response: Response) =>  <ResponseData> response.json());
     // return this.http.get(url).map((response: Response) => <ResponseData>response.json());
  }
deleteGroupById(paramobj, status) {
    const headers = new Headers({ 'Content-Type': 'application/json' });
    this.paramaction = paramobj +  '?action=' + status  ;
    const url: string = '/terms/paymenttermservice/paymentterm/' + this.paramaction;
    this.arrStringJSON =  JSON.stringify(paramobj) ;
    return this.http.delete(url, new RequestOptions({
                     // headers: headers,
                   // body: this.arrStringJSON
       })) .map((res: Response) => {
            if (res) {
                /*if (res.status === 201) {
                    return [{ status: res.status, json: res }] }else*/
            if (res.status === 200) {
                    return [{ status: res.status, json: res }]
                }
            }
        return res.json();
      }).catch((error: any) => {
               //  this.errorStatusCode = error.status;
                /*if (error.status === 422) {
                    return Observable.throw(new Error(error.status));
                }else if (error.status === 400) {
                    return Observable.throw(new Error(error.status));
                }else if (error.status === 409 || error.status === 404) {
                   return Observable.throw(new Error(error.status));
                }else if (error.status === 406) {
                    return Observable.throw(new Error(error.status));
                }else if (error.status === 500) {
                    return Observable.throw(new Error(error.status));
                }*/
                 return this.errorHandling(error);
     });
   }

// save, submit , update the data based on status and add or edit page parameter then send to rest api
savePaymentTerms(param, status , addoredit) {
   console.log(JSON.stringify(param))
   this.paymenttermName = param.payTermName;
   this.addoreditpageflag = addoredit;
   const headers = new Headers({ 'Content-Type': 'application/json' });
   const url = '/terms/paymenttermservice/paymentterm' ;
    if (status === 'InActive' || status === 'Active' ) {
        status = '';
    }  else  if (status === 'draft' || status === 'save' ) {
           status = '?action=' + status
   }
   console.log('In service save permission group');
 console.log(JSON.stringify(param) + status + addoredit  + url);
   if (addoredit ) {
          return this.http.post(url + status , param)

    //  { headers: headers })
      .map((res: Response ) => {
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
   }else if ( addoredit === false) {
           return this.http.put(url + status , param)
           .map((res: Response ) => {
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
}
// get All drop Down list via RESTAPI
getPermissionDropDownJSON(): Observable<ResponseData> {
     const url =  '/terms/paymenttermservice/paymentterm/getDropBox';
     return this.http.get(url)
    .map((response: Response) => <ResponseData>response.json())
    .catch((error: any) => Observable.throw(error.json().error || 'Server error')) ;
  }

}
