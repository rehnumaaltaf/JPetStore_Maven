import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { ResponseData } from '../../../../shared/interface/responseData';
import { Observable } from 'rxjs/Observable';
import { Link } from '../../../../shared/interface/link';
import 'rxjs/add/operator/map';
import { AbstractLinkService } from '../../../../shared/service/abstract-link.service';
import { MasterSetupService } from '../../../master-setup/service/master-setup.service';
import { LocationModel } from '../model/location.model';
import { noLoading } from '../../../../shared/interface/router-links';
import { MessageModel } from '../../../../shared/message/message.model';
import { MESSAGE_ERROR } from '../../../../shared/interface/common.constants';

@Injectable()
export class LocationService extends AbstractLinkService {
  locationDetail: LocationModel;
  messages: MessageModel;
  vmessages: MessageModel;
  errorStatusCode: any;
  errorMessage: any;
  constructor(private http: Http, public masterService: MasterSetupService) {
    super();
  }

  getLocationDetails(): Observable<ResponseData> {
    // const link: Link = this.masterService.getChildObject('View Uom.GET');
    // const url = link.href;
    // const url = 'https://api.myjson.com/bins/zvmkx';
     const url = '/location/locationservice/location';
    return this.http.get(url).map((response: Response) =>  <ResponseData> response.json());
     // return this.http.get(url).map((response: Response) => <ResponseData>response.json());
  }

  getCurrencyList(): Observable<ResponseData> {
    // const link: Link = this.masterService.getChildObject('View Uom.GET');
    // const url = link.href;
    // const url = 'https://api.myjson.com/bins/zvmkx';
     const url = '/reference/currencyservice/currency';
    return this.http.get(url).map((response: Response) =>  <ResponseData> response.json());
     // return this.http.get(url).map((response: Response) => <ResponseData>response.json());
  }

  getUomList() {
    const url = '/reference/uomservice/uom';
    return this.http.get(url).map((response: Response) => <ResponseData>response.json());
  }

  getPrimaryPackingList() {
    const url = '/packing/PrimaryPackingType/viewPrimaryPackingType';
    return this.http.get(url).map((response: Response) => <ResponseData>response.json());
  }

  getSecondaryPackingList() {
    const url = '/packing/secPackTypeService/secPackType';
    return this.http.get(url).map((response: Response) => <ResponseData>response.json());
  }

  getRateBasisList(): Observable<ResponseData> {
    // const link: Link = this.masterService.getChildObject('View Uom.GET');
    // const url = link.href;
    // const url = 'https://api.myjson.com/bins/zvmkx';
     const url = '/location/locationservice/ratebasis';
    return this.http.get(url).map((response: Response) =>  <ResponseData> response.json());
     // return this.http.get(url).map((response: Response) => <ResponseData>response.json());
  }
  getLocationDetail(param): Observable<ResponseData> {
    // const link: Link = this.masterService.getChildObject('View Uom.GET');
    // const url = link.href;
    // const url = 'https://api.myjson.com/bins/zvmkx';
     const url = '/location/locationservice/location';
    return this.http.get(url + '/' + param).map((response: Response) =>  <ResponseData> response.json());
     // return this.http.get(url).map((response: Response) => <ResponseData>response.json());
  }

  getLocationDropDownList(): Observable<ResponseData> {
    // const link: Link = this.masterService.getChildObject('View Uom.GET');
    // const url = link.href;
    // const url: string =  environment.path + '/locationservice/location/getLocDropDown';
    // console.log( url );
    // const url: string = "https://api.myjson.com/bins/15qqt9";
    const url = '/location/locationservice/location/getLocDropDown';
    return this.http.get(url).map((response: Response) =>  <ResponseData> response.json());
     // return this.http.get(url).map((response: Response) => <ResponseData>response.json());
  }

 /*  savOrSubmitLocationDetails(locationDetail: LocationModel): Observable<ResponseData> {
    // const headers = new Headers({ 'Content-Type': 'application/json' });
    const url = '/location/locationservice/location ';
    return this.http.post(url,
      JSON.stringify(locationDetail),
      {})
      .map((res) =>  res.json());
  } */

  savOrSubmitLocationDetails(locationDetail: LocationModel) {
  //  console.log('savOrSubmitLocationDetails' + locationDetail);
      return this.http.post('/location/locationservice/location', JSON.stringify(locationDetail)
      ).map((res: Response) => {
                  return res.json() ;

      }).catch(( error: any ) => {
                this.errorStatusCode = error.status;
                this.errorMessage = JSON.stringify(error.json().errorMessage);
                if (error.status === 500) {
                   return Observable.throw(new Error(error.status));
                } else if (error.status === 400) {
                    return Observable.throw(new Error(error.status));
                } else if (error.status === 409 || error.status === 404) {
                   this.vmessages = {severity: MESSAGE_ERROR, summary: error.json().errorMessage};
                   return Observable.throw(new Error(error.status));
                } else if (error.status === 406) {
                    return Observable.throw(new Error(error.status));
                }
      });
  }

/*    updateLocationDetails(locationDetail: LocationModel): Observable<ResponseData> {
         console.log('updateLocationDetails' + locationDetail);
   // const headers = new Headers({ 'Content-Type': 'application/json' });
    const url = '/location/locationservice/location';
    return this.http.put(url,
      JSON.stringify(locationDetail),
      {})
      .map((res) =>  res.json());

  }
 */


  //  console.log('savOrSubmitLocationDetails' + locationDetail);
   updateLocationDetails(locationDetail: LocationModel) {
  //    console.log('updateLocationDetails' + JSON.stringify(locationDetail));
      return this.http.put('/location/locationservice/location', JSON.stringify(locationDetail)
      ).map((res: Response) => {
                  return res.json() ;

      }).catch(( error: any ) => {
                this.errorStatusCode = error.status;
                this.errorMessage = JSON.stringify(error.json().errorMessage);
                if (error.status === 500) {
                   return Observable.throw(new Error(error.status));
                } else if (error.status === 400) {
                    return Observable.throw(new Error(error.status));
                } else if (error.status === 409 || error.status === 404) {
                   this.vmessages = {severity: MESSAGE_ERROR, summary: error.json().errorMessage};
                   return Observable.throw(new Error(error.status));
                } else if (error.status === 406) {
                    return Observable.throw(new Error(error.status));
                }
      });
}
  uniqueLocationCode(param): Observable<number> {
    // const link: Link = this.masterService.getChildObject('View Uom.GET');
    // const url = link.href;
    const url = noLoading + '/location/locationservice/location/uniqueLocCode/' + param;
    return this.http.get(url).map((response: Response) =>  <number> response.json());
     // return this.http.get(url).map((response: Response) => <ResponseData>response.json());
  }

  uniqueLocationName(param): Observable<number> {
    // const link: Link = this.masterService.getChildObject('View Uom.GET');
    // const url = link.href;
    const url = noLoading + '/location/locationservice/location/uniqueLocName/' + param;
    return this.http.get(url).map((response: Response) =>  <number> response.json());
     // return this.http.get(url).map((response: Response) => <ResponseData>response.json());
  }

  uniqueLocationFullName(param): Observable<number> {
    // const link: Link = this.masterService.getChildObject('View Uom.GET');
    // const url = link.href;
    const url = noLoading + '/location/locationservice/location/uniqueLocFullName/' + param;
    return this.http.get(url).map((response: Response) =>  <number> response.json());
     // return this.http.get(url).map((response: Response) => <ResponseData>response.json());
  }

    locCodeSuggestion(param): Observable<string[]> {
    // const link: Link = this.masterService.getChildObject('View Uom.GET');
    // const url = link.href;
    const url = noLoading + '/location/locationservice/location/suggestLocCode/' + param;
    return this.http.get(url).map((response: Response) =>  <string[]> response.json());
     // return this.http.get(url).map((response: Response) => <ResponseData>response.json());
  }

  locNameSuggestion(param): Observable<string[]> {
    // const link: Link = this.masterService.getChildObject('View Uom.GET');
    // const url = link.href;
    const url = noLoading + '/location/locationservice/location/suggestLocName/' + param;
    return this.http.get(url).map((response: Response) =>  <string[]> response.json());
     // return this.http.get(url).map((response: Response) => <ResponseData>response.json());
  }

  locFullNameSuggestion(param): Observable<string[]> {
    // const link: Link = this.masterService.getChildObject('View Uom.GET');
    // const url = link.href;
    const url = noLoading + '/location/locationservice/location/suggestLocFullName/' + param;
    return this.http.get(url).map((response: Response) =>  <string[]> response.json());
     // return this.http.get(url).map((response: Response) => <ResponseData>response.json());
  }

  deleteLocationDetails(param) {
      // const headers = new Headers({ 'Content-Type': 'application/json' });
     const url = '/location/locationservice/location';
    // const url: string = 'http://10.87.67.124:8080' + '/currencyservice/currency';
   // console.log(param);
    return this.http.delete(url + '/' + param)
      .map((res) => res.json())
    }

  setRowData(row: LocationModel) {
    this.locationDetail = row;
  }

  getRowData() {
    return this.locationDetail;
  }

}
