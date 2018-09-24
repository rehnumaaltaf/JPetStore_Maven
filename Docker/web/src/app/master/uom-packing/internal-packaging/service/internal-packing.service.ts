import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
// import { InternalPackaging } from '../internalpackaging';
import { InternalPackagingModel } from '../model/internalPackagingModel';

import { ResponseData } from '../../../../shared/interface/responseData';
import { Observable } from 'rxjs/Observable';
import { Link } from '../../../../shared/interface/link';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import { environment } from '../../../../../environments/environment';
import { AbstractLinkService } from '../../../../shared/service/abstract-link.service';
import { SelectItem } from '../../../../shared/interface/selectItem';
import { MESSAGE_ERROR } from '../../../../shared/interface/common.constants';
import { MessageModel } from '../../../../shared/message/message.model';

@Injectable()
export class InternalPackingService extends AbstractLinkService {
internalModel: InternalPackagingModel[];
errorStatusCode: any;
errorMessage: string;
arrStringJSON: any;
interpackingData: any = {};
// uombaseOption: SelectItem[] = [];
viewbyIdParam: any;
messages: MessageModel;
  constructor(private http: Http) {
    super();
  }

 getInternalPackingDetailsJSON(param) {
   // const link: Link = this.masterService.getChildObject('View Uom.GET');
    const url = '/packing/PrimaryPackingType/viewPrimaryPackingType';
   return this.http.get(url).map((response: Response) => <ResponseData>response.json());

   }

   getPackingIdJSON(id) {
   //   alert(JSON.stringify(id));
  const url = '/packing/PrimaryPackingType/viewPrimaryPackingType/' + id;
  return this.http.get(url).map((response: Response) => <ResponseData>response.json());
 }

  /*saveInterPackingDetails(param): Observable<ResponseData> {
    // const headers = new Headers({ 'Content-Type': 'application/json' });
    const url =  '/PrimaryPackingType/addPrimaryPackingType';
    return this.http.post(url,
      JSON.stringify(param),
      {  })
      .map((res) => res.json());
   }*/

saveInterPackingDetails(param) {
  const url =  '/packing/PrimaryPackingType/addPrimaryPackingType';
  return this.http.post(url,
      JSON.stringify(param))
      // { headers: headers })
      .map((res: Response) => {
                  if (res) {
                    if (res.status === 201) {
                        return [{ status: res.status, json: res }]
                    } else if (res.status === 200) {
                        return [{ status: res.status, json: res }]
                    }
                }
                  return res.json() ;

      }).catch(( error: any ) => {
               // this.errorStatusCode = error.status;
             //   this.errorMessage = JSON.stringify(error.json().body);
             this.errorMessage = error.json().errorMessage;
                if (error.status === 500) {
                   return Observable.throw(new Error(error.status));
                } else if (error.status === 400) {
                    return Observable.throw(new Error(error.status));
                } else if (error.status === 409 || error.status === 404) {
                   return Observable.throw( this.errorMessage);
                } else if (error.status === 406) {
                    return Observable.throw(new Error(error.status));
                }
   });
  }

updatePackingDetails(param): Observable<ResponseData> {
    const url = '/packing/PrimaryPackingType/editPrimaryPacking';
  //  alert(JSON.stringify(param));
    console.log(param);
    return this.http.put(url,
      JSON.stringify(param),
      {  })
       .map((res: Response) => {
                  if (res) {
                    if (res.status === 201) {
                        return [{ status: res.status, json: res }]
                    } else if (res.status === 200) {
                        return [{ status: res.status, json: res }]
                    }
                }
                  return res.json() ;

      }).catch(( error: any ) => {
               // this.errorStatusCode = error.status;
             //   this.errorMessage = JSON.stringify(error.json().body);
             this.errorMessage = error.json().errorMessage;
                if (error.status === 500) {
                   return Observable.throw(new Error(error.status));
                } else if (error.status === 400) {
                    return Observable.throw(new Error(error.status));
                } else if (error.status === 409 || error.status === 404) {
                   return Observable.throw( this.errorMessage);
                } else if (error.status === 406) {
                    return Observable.throw(new Error(error.status));
                }
   });
  }
   setMessage(message: String) {
     this.interpackingData.message = message;
  }
  getMessage() {
    return this.interpackingData.message;
  }

  /*To validate the internalpacking code*/
   validate(param): Observable<ResponseData> {
    const url = '/packing/PrimaryPackingType/validate';
  //  const headers = new Headers({ 'Content-Type': 'application/json' });
    return this.http.post(url,
      JSON.stringify(param),
      {  })
      .map((res) => res.json());
  }
     deletepackingid(param) {
    // const url: string = environment.path + '/financialCalender/deleteFinCal/' + param.InternalPackagingModel ;

    const url = '/packing/PrimaryPackingType/deletePrimaryPackingType/' + param;
    return this.http.delete(url).map((response: Response) => <ResponseData>response.json());
  }

  activePrimaryPacking(param) {
 const url = '/packing/PrimaryPackingType/viewSubmit';
 // return this.http.put(url , id).map((response: Response) => <ResponseData>response.json());
  return this.http.post(url,
      JSON.stringify(param),
      {  })
      .map((res) => res.json());
}


}
