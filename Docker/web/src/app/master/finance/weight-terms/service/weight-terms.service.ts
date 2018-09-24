import { Injectable } from '@angular/core';
import { Tolerance, Franchise, WeightTermModel } from '../weight-terms';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { ResponseData } from '../../../../shared/interface/responseData';
import { Observable } from 'rxjs/Observable';
import { ModalModule } from 'ngx-bootstrap';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import { environment } from '../../../../../environments/environment';
import { AbstractLinkService } from '../../../../shared/service/abstract-link.service';
import { MasterSetupService } from '../../../master-setup/service/master-setup.service';

@Injectable()
export class WeightTermsService {
weightTermForListing: WeightTermModel[];
viewbyIdParam: WeightTermModel;
 weightTermModel: WeightTermModel[];
    errorMessageFromDb: any;
    successModal: ModalModule;
    sharingData: any = {};
    arrStringJSON: any;
    errorStatusCode: any;
 public errorMessage: String;

  constructor(private http: Http) { }


getWeightTermDetailsJSON() {
  // alert('insidejson');
  // const url = 'https://api.myjson.com/bins/yt7eh';
     const url = '/terms/weighttermsservice/weightterms';
     return this.http.get(url).map((response: Response) => <ResponseData>response.json());
   //  return this.http.get(url).map((response: Response) => response.json());
  }


  gettoleranceDetail(): Observable<ResponseData> {

   // alert('tolerance');
    /* const url = '/originservice/origin';
    const url = 'https://api.myjson.com/bins/p8idh'; */
  //  const url = 'https://api.myjson.com/bins/y7rsp';
     const url = '/terms/toleranceservice/tolerance';
     return this.http.get(url).map((response: Response) => <ResponseData>response.json());
    // return this.http.get(url).map((response: Response) => response.json());
   }



   getfranchiseDetail(): Observable<ResponseData> {

// alert('francise');
    /* const url = '/originservice/origin';
    const url = 'https://api.myjson.com/bins/p8idh'; */
  // const url = 'https://api.myjson.com/bins/o5re1';
     const url = '/terms/franchiseservice/franchise';
     return this.http.get(url).map((response: Response) => <ResponseData>response.json());
    //  return this.http.get(url).map((response: Response) => response.json());
   }


   /*To save the details*/
  savePackingDetails(weighttermData: WeightTermModel) {
    const url = '/terms/weighttermsservice/weightterms';
   // console.log('PASSING DATA>>' + JSON.stringify(weighttermData));
    return this.http.post(url,
      JSON.stringify(weighttermData),
      {})
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
                this.errorMessage = error.json().errorMessage;
             // alert(this.errorMessage);
                if (error.status === 409) {
                       return Observable.throw(new Error(error.status));
                }
      });
  }

  deleteHolidayCalendarById(param) {
        const url = '/terms/weighttermsservice/weightterms/';   // alert("stringify param==>"+JSON.stringify(param));
        this.arrStringJSON = ' [ ' + JSON.stringify(param) + ']';
        console.log(this.arrStringJSON);
        return this.http.delete(url, new RequestOptions({
            body: this.arrStringJSON
        })).map((res: Response) => {
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
            if (error.status === 422) {
                // return Observable.throw(new Error(error.status));
                return Observable.throw(new Error(this.errorMessage = 'Cannot Delete..Weight Terms is+'
                    + ' already associated as a parent to some other Weight Terms'));
                // this.errorMessage="UOM Code Already exists.Please Choose any Other..")
            } else if (error.status === 400) {
                return Observable.throw(new Error(error.status));
            } else if (error.status === 409 || error.status === 404) {
                return Observable.throw(new Error(this.errorMessage = 'weight Terms Code Already exists.Please Choose any Other..'));
            } else if (error.status === 406) {
                return Observable.throw(new Error(error.status));
            }
        });
    }

  /*To update the selected packing details editPackingMaterial */
   updatePackingDetails(weighttermData: WeightTermModel[]) {
    const url = '/terms/weighttermsservice/weightterms';
    console.log('PASSING DATA>>' + JSON.stringify(weighttermData));
    return this.http.put(url,
      JSON.stringify(weighttermData),
      {})
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
        console.log(error);
        if (error.status === 500) {
          return Observable.throw(new Error(error.status));
        }else if (error.status === 400) {
          return Observable.throw(new Error(error.status));
        }else if (error.status === 409 || error.status === 404) {
          return Observable.throw(new Error(this.errorMessage = JSON.parse(error._body).errorMessage));
        } else if (error.status === 406) {
          return Observable.throw(new Error(error.status));
        }
      });
  }

  updateWeightTerms(param): Observable<ResponseData> {
        const url = '/terms/weighttermsservice/weightterms';
        this.arrStringJSON = '[' + JSON.stringify(param) + ']';
        console.log('passing JSON in db' + this.arrStringJSON);
        return this.http.put(url, this.arrStringJSON)
            // { headers: headers })
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
                this.errorMessage = error.json().message;
                if (error.status === 409) {
                    return Observable.throw(new Error(error.status));
                }
            });
    }





}
