import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { ResponseData } from '../../../../shared/interface/responseData';
import { Observable } from 'rxjs/Observable';
import { BehaviorSubject } from 'rxjs/Rx';
import { Link } from '../../../../shared/interface/link';
import { BasePaymentInterface } from '../base-payment-interface';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import { GridComponent, GridDataResult, DataStateChangeEvent} from '@progress/kendo-angular-grid';
import { environment } from '../../../../../environments/environment';
import { AbstractLinkService } from '../../../../shared/service/abstract-link.service';

@Injectable()
export class BasePaymentService extends AbstractLinkService {
      basePaymentdetails: BasePaymentInterface[];
      basePaymentCodeParam: string;
      errorMessage: string;
      baseCodeForPayment: string;
      statusName: string;
      editBasePaymentinAdd: BasePaymentInterface = new BasePaymentInterface();
      viewbasePaymentdetails: BasePaymentInterface = new BasePaymentInterface();
      viewbyIdParam: any;
      errorStatusCode: any;
      arrStringJSON: any;
      editPayment: BasePaymentInterface;
      editFeatures: BasePaymentInterface;
      editFeaturesMultiple: BasePaymentInterface;
      selectbasePaymentEdit: BasePaymentInterface[];
      constructor(private http: Http) {
            super();
      }
      /*this getListingPageFromJson methods is to get data in listing page  */
      getListingPageFromJson(param) {
            // alert('param==>' + param);
            // const link: Link = this.masterService.getChildObject('View Uom.GET');
            //const url: string = environment.path + param;
            return this.http.get('/terms/basepaymentservice/basepayment').map((response: Response) => response.json());
            // return this.http.get(url).map((response: Response) => <ResponseData>response.json());
      }
      /*this saveUOMDetails methods is to save data to base payment data*/
      saveBasePaymentDetails(basePaymntDet: BasePaymentInterface) {
            this.statusName = basePaymntDet.action;
            this.baseCodeForPayment = basePaymntDet.baseTermCode;
            const headers = new Headers({ 'Content-Type': 'application/json' });
            //    const url = '/uomservice/uom';
            const url = '/terms/basepaymentservice/basepayment';
            return this.http.post(url, basePaymntDet)
                  //{ headers: headers } )
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
                        return this.errorHandling(error);
                  });

      }
      deleteBasePayment(param, code) {
            this.baseCodeForPayment = code;
            const headers = new Headers({ 'Content-Type': 'application/json' });
            const url = '/terms/basepaymentservice/basepayment';
            // alert("stringify param==>"+JSON.stringify(param));
            this.arrStringJSON = ' [ ' + JSON.stringify(param) + ' ] ';
            return this.http.delete(url, new RequestOptions({
                  //headers: headers,
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
                  return this.errorHandling(error);
            });

      }

      updateBasePayemnt(basePaymntDetEdit: BasePaymentInterface[]) {
            this.statusName = basePaymntDetEdit[0].statusName;
            this.baseCodeForPayment = basePaymntDetEdit[0].baseTermCode;
            const headers = new Headers({ 'Content-Type': 'application/json' });
            //    const url = '/uomservice/uom';
            const url = '/terms/basepaymentservice/basepayment';
            return this.http.put(url, basePaymntDetEdit)
                  //{ headers: headers } )
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
                        return this.errorHandling(error);
                  });

      }


      getSelectedFeatues(param, basetermId): Observable<ResponseData> {
            // const url: string = environment.path + '/rolemap/features/' + basetermId;
            return  this.http.get('/terms/basepaymentservice/basepayment/' + basetermId)
                  .map((response: Response)  =>  <ResponseData>response.json())
                  .catch((error: any) => {
                        return this.errorHandling(error);
                  });

      }
       baseCodeSuggestion(param): Observable<ResponseData> {
    // const link: Link = this.masterService.getChildObject('View Uom.GET');
    // const url = link.href;
    const url = '/terms/basepaymentservice/basepayment/?termBaseCode=' + param;
    console.log(url);
    return this.http.get(url).map((response: Response) =>  <ResponseData> response.json());
     // return this.http.get(url).map((response: Response) => <ResponseData>response.json());
  }
   baseNameSuggestion(param): Observable<ResponseData> {
    // const link: Link = this.masterService.getChildObject('View Uom.GET');
    // const url = link.href;
    const url = '/terms/basepaymentservice/basepayment/?termBaseName=' + param;
    console.log(url);
    return this.http.get(url).map((response: Response) =>  <ResponseData> response.json());
     // return this.http.get(url).map((response: Response) => <ResponseData>response.json());
  }
      editUserRoleFeatures(userRoleData2: BasePaymentInterface) {
            console.log(" ddd " + JSON.stringify(userRoleData2))
            this.editFeatures = userRoleData2;
      }
      selectedFeatureToedit(userRoleData3: any) {
            this.selectbasePaymentEdit = userRoleData3;
      }
      selectedFeatureToeditDropDown(userRoleData4: BasePaymentInterface) {
            this.editFeaturesMultiple = userRoleData4;
      }

      selectedBaseToedit(userRoleData5: any) {
            console.log(JSON.stringify(userRoleData5));
            this.editBasePaymentinAdd = userRoleData5;
      }
}
