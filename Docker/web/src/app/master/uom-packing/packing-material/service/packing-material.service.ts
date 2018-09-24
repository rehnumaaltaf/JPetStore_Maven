import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { ResponseData } from '../../../../shared/interface/responseData';
import { Observable } from 'rxjs/Observable';
import { Link } from '../../../../shared/interface/link';
import 'rxjs/add/operator/map';
import { AbstractLinkService } from '../../../../shared/service/abstract-link.service';
import { MasterSetupService } from '../../../master-setup/service/master-setup.service';
import { noLoading } from '../../../../shared/interface/router-links';
import { PackingModel } from '../model/pack-model';
import { MESSAGE_ERROR } from '../../../../shared/interface/common.constants';
import { MessageModel } from '../../../../shared/message/message.model';
@Injectable()
export class PackingMaterialService extends AbstractLinkService {
  public packingDetails: PackingModel[];
  arrStringJSON: JSON;
  errorMessage: string;
  packingData: any = {};
  packingDetail: PackingModel;
  packNameList: String[] = [];
  packCodeList: String[] = [];
  messages: MessageModel;


 // statusname;
 // packingcode;
 // packingId;
  constructor(private http: Http, private masterService: MasterSetupService ) {
    super();
  }

  getPackingDetailsJSON(param) {
  const url = '/packing/packingMaterial/viewPackMaterial';
  return this.http.get(url).map((response: Response) => <ResponseData>response.json());
  }

  deletePackingMaterial(id, statusId) {
  const url = '/packing/packingMaterial/deletePackingMaterial/' + id ;
  return this.http.delete(url).map((response: Response) => <ResponseData>response.json());
 }

  setRowData(row: PackingModel) {
    this.packingDetail = row;
  }

  getRowData() {
    return this.packingDetail;
  }

 getPackingIdJSON(id) {
  const url = '/packing/packingMaterial/viewPackingMaterialId/' + id;
  return this.http.get(url).map((response: Response) => <ResponseData>response.json());
 }

 setPackingCode(packingcode: String) {
   this.packingData.packingcode = packingcode;
}
getPackingCode() {
    return this.packingData.packingcode;
}

setStatusname(statusname: String) {
   this.packingData.statusname = statusname;
}
getStatusname() {
    return this.packingData.statusname;
}

setPackingPkId(packingId: String) {
   this.packingData.packingId = packingId;
}
getPackingPkId() {
    return this.packingData.packingId;
}

activePackingMaterial(id) {
 const url = '/packing/packingMaterial/changeStatus/' + id;
 return this.http.put(url , id).map((response: Response) => <ResponseData>response.json());

}

/*To save the details*/
  savePackingDetails(packingData: PackingModel) {
    const url = '/packing/packingMaterial/addPackingMaterial';
    console.log('PASSING DATA>>' + JSON.stringify(packingData));
    return this.http.post(url,
      JSON.stringify(packingData),
      {})
      .map((res: Response) => {
                  return res.json() ;

      }).catch(( error: any ) => {
              this.errorMessage = error.json().errorMessage;
                if (error.status === 500) {
                   return Observable.throw(new Error(error.status));
                } else if (error.status === 400) {
                    return Observable.throw(new Error(error.status));
                } else if (error.status === 409 || error.status === 404 || error.status === 403) {
                   // alert('1');
                  // this.messages = {severity: MESSAGE_ERROR, summary: error.json().errorMessage};
                   return Observable.throw(new Error(error.status));
                } else if (error.status === 406) {
                    return Observable.throw(new Error(error.status));
                }
      });
  }
  /*To update the selected packing details editPackingMaterial */
   updatePackingDetails(packingData: PackingModel) {
    const url = '/packing/packingMaterial/editPackingMaterial';
    console.log('PASSING DATA>>' + JSON.stringify(packingData));
    return this.http.put(url,
      JSON.stringify(packingData),
      {})
      .map((res: Response) => {
                  return res.json() ;

      }).catch(( error: any ) => {
               this.errorMessage = error.json().errorMessage;
                if (error.status === 500) {
                   return Observable.throw(new Error(error.status));
                } else if (error.status === 400) {
                    return Observable.throw(new Error(error.status));
                } else if (error.status === 409 || error.status === 404 || error.status === 403) {
                   // alert('1');
                 //  this.messages = {severity: MESSAGE_ERROR, summary: error.json().errorMessage};
                   return Observable.throw(new Error(error.status));
                } else if (error.status === 406) {
                    return Observable.throw(new Error(error.status));
                }
      });
  }
  /*TypeAhead list*/
  packCodeSuggestion(packingData: PackingModel): Observable<ResponseData> {
    // const link: Link = this.masterService.getChildObject('View Uom.GET');
    // const url: string = link.href;
    const url = noLoading + '/packing/packingMaterial/suggest/';
    return this.http.post(url,
      JSON.stringify(packingData),
      {})
       .map((res) => res.json());
      /*.map((res: Response) => {
             if (res) {
                 console.log('res 123   ' +  JSON.stringify(res));
                    if (res.status === 201) {
                        return [{ status: res.status, json: res  }]
                    } else if (res.status === 200) {
                        return [{ status: res.status, json: res }]
                    }
              }
         return res.json();
      }).catch((error: any) => {
                if (error.status === 500) {
                    return Observable.throw(new Error(error.status));
                }else if (error.status === 400) {
                    return Observable.throw(new Error(error.status));
                }else if (error.status === 409 || error.status === 404) {
                   return Observable.throw(new Error(this.errorMessage = 'Already exists.Please Choose any Other..'));
                } else if (error.status === 406) {
                    return Observable.throw(new Error(error.status));
                }
      });*/
  }
  /*TypeAhead list*/
  packNameSuggestion(packingData: PackingModel): Observable<ResponseData> {
    // const link: Link = this.masterService.getChildObject('View Uom.GET');
    // const url: string = link.href;
    const url = noLoading + '/packing/packingMaterial/suggest/';
    return this.http.post(url,
      JSON.stringify(packingData),
      {})
       .map((res) => res.json());
  }
  /*To validate the code and name*/
   validate(param): Observable<ResponseData> {
   // const headers = new Headers({ 'Content-Type': 'application/json' });
    const url = noLoading + '/packing/packingMaterial/validatePackingMaterial';
    return this.http.post(url,
      JSON.stringify(param),
      {})
      .map((res) => res.json());
      /*.map((res: Response) => {
             if (res) {
                    if (res.status === 201) {
                        return [{ status: res.status, json: res }]
                    } else if (res.status === 200) {
                        return [{ status: res.status, json: res }]
                    }
              }
         return res.json();
      }).catch((error: any) => {
                if (error.status === 500) {
                    return Observable.throw(new Error(error.status));
                }else if (error.status === 400) {
                    return Observable.throw(new Error(error.status));
                }else if (error.status === 409 || error.status === 404) {
                   return Observable.throw(new Error(this.errorMessage = 'Already exists.Please Choose any Other..'));
                } else if (error.status === 406) {
                    return Observable.throw(new Error(error.status));
                }
      });*/
  }



}
