import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { ResponseData } from '../../../../shared/interface/responseData';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import { ForexModel } from './../forex-interface';
import { environment } from '../../../../../environments/environment';
import { SelectItem } from '../../../../shared/interface/selectItem';
import { DropdownModel } from './../forex-interface';
import { AbstractLinkService } from '../../../../shared/service/abstract-link.service';
import { MasterSetupService } from '../../../master-setup/service/master-setup.service';
@Injectable()
export class ForexService extends AbstractLinkService {
  forexDetails: ForexModel[];
  forexTenor: DropdownModel[];
  forexDuration: DropdownModel[];
  forexDayType: DropdownModel[];
  forexNameList: String[] = [];
  forexDescList: String[] = [];
  forexData: any = {};
  constructor(private http: Http , public masterService: MasterSetupService) {
    super();
   }

  /*To get the list of forex master*/
  getUnitJSON() {
      const url = '/forex/viewForex';
      return this.http.get(url).map((response: Response) => <ResponseData>response.json());
  }
  /*To get the list of drop down values for forex master*/
  getDropdownListJSON(): Observable<ResponseData> {
       // return this.http.get('https://api.myjson.com/bins/yl3vr').map((response: Response) => response.json());
       const url = '/forex/ddlist';
       return this.http.get(url).map((response: Response) => <ResponseData>response.json());
  }
  /*to display the value*/
  displayForexDetails(listDetails: ForexModel[]) {
    this.forexDetails = listDetails;
    // alert(JSON.stringify(this.forexDetails));
   }
   /*To save the forex details*/
   saveForexDetails(param): Observable<ResponseData> {
    const headers = new Headers({ 'Content-Type': 'application/json' });
    const url = '/forex/addForex';
    return this.http.post(url,
      JSON.stringify(param),
      { headers: headers })
      .map((res) => res.json());
   }
   /*To delete the selected forex*/
   deleteForexJSON(id, statusId) {
    const url: string = '/forex/deleteForex/' + id + '/' + statusId;
    return this.http.delete(url).map((response: Response) => <ResponseData>response.json());
  }
  /*To view the selected forex*/
  listUnitJSON(id) {
    const url: string = '/forex/viewForexDetail/' + id ;
    return this.http.get(url).map((response: Response) => <ResponseData>response.json());
  }
  /*To validate the forex fields*/
   validate(param): Observable<ResponseData> {
    const url = '/forex/validate';
    const headers = new Headers({ 'Content-Type': 'application/json' });
    return this.http.post(url,
      JSON.stringify(param),
      { headers: headers })
      .map((res) => res.json());
  }
  /*To list the suggestions for forex name*/
  forexNameSuggestion(param, code): Observable<ResponseData> {
    // const link: Link = this.masterService.getChildObject('View Uom.GET');
    // const url: string = link.href;
    const url: string = '/forex/suggest/' + code + '/' + param;
    return this.http.get(url).map((response: Response) =>  <ResponseData> response.json());
     // return this.http.get(url).map((response: Response) => <ResponseData>response.json());
  }
   /*To list the suggestions for forex name*/
  forexDescSuggestion(param, code): Observable<ResponseData> {
    // const link: Link = this.masterService.getChildObject('View Uom.GET');
    // const url: string = link.href;
    const url: string = '/forex/suggest/' + code + '/' + param;
    return this.http.get(url).map((response: Response) =>  <ResponseData> response.json());
     // return this.http.get(url).map((response: Response) => <ResponseData>response.json());
  }
  /*to set the id for edit or delete of forex*/
  setId(id: number) {
    this.forexData.id = id;
  }
  getId() {
    return this.forexData.id;
  }
  /*to set the statusid for edit or delete of forex*/
  setStatusId(statusId: number) {
    this.forexData.statusId = statusId;
  }
  getStatusId() {
    return this.forexData.statusId;
  }
   setForexName(name: string) {
    this.forexData.name = name;
  }
  getForexName() {
    return this.forexData.name;
  }
  setTickerCode(tickerCode: Boolean) {
    this.forexData.tickerCode = tickerCode;
  }
  getTickerCode() {
    return this.forexData.tickerCode;
  }
  setMessage(message: String) {
     this.forexData.message = message;
  }
  getMessage() {
    return this.forexData.message;
  }
 /* setTenorList(tenorList: number) {
    this.forexData.tenorList = tenorList;
  }
  getTenorList() {
    return this.forexData.tenorList;
  }*/
}
