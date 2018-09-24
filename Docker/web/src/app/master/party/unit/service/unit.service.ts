import { Injectable, OnInit } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { ResponseData } from '../../../../shared/interface/responseData';
import { Observable } from 'rxjs/Observable';
import { environment } from '../../../../../environments/environment';
import { ProfitCenterModel } from '../model/profit-center-model';
import { AbstractLinkService } from '../../../../shared/service/abstract-link.service';

@Injectable()
export class UnitService extends AbstractLinkService {

  public id: string;
  public statusId: string;
  public iseditModal: Boolean;
  public displayDialog: Boolean;
  public isEditPage: Boolean = false;
  public statusActive: Boolean;
  public statusDraft: Boolean;
  public statusInactive: Boolean;
  req_unitCode: Boolean;
  req_unitName: Boolean;
  req_unitFullName: Boolean;

  public profitCenterModel: ProfitCenterModel;
  sharingData: any = {};
  private message: string;

  constructor(private http: Http) {
    super();
    this.profitCenterModel = new ProfitCenterModel();
  }

  setDefaultValues() {
    this.req_unitCode = false;
    this.req_unitName = false;
    this.req_unitFullName = false;

    this.statusActive = false;
    this.statusDraft = false;
    this.statusInactive = false;
  }

  getDropdownListJSON(): Observable<ResponseData> {
    const url = '/unitservice/unit/getddlist';
    return this.http.get(url).map((response: Response) => <ResponseData>response.json());
  }

  validate(param): Observable<ResponseData> {
    const url = '/unitservice/unit/validate';
    const headers = new Headers({ 'Content-Type': 'application/json' });
    return this.http.post(url,
      JSON.stringify(param),
      { headers: headers })
      .map((res) => {return res.json();});
  }

  saveProfitCenter1(link, param): Observable<ResponseData> {
    const headers = new Headers({ 'Content-Type': 'application/json' });
    const url: string = link;
    return this.http.post(url,
      JSON.stringify(param),
      { headers: headers })
      .map((res) => res.json());

  }


  updateProfitCenter(link, param): Observable<ResponseData> {
    const headers = new Headers({ 'Content-Type': 'application/json' });
    const url: string = link;
    return this.http.put(url,
      JSON.stringify(param),
      { headers: headers })
      .map((res) => res.json());

  }

  /*To get the list of unit master*/

  getUnitJSON(param) {
     const url: string = param;
     console.log(' URL --- > ' + url);
     return this.http.get(url).map((response: Response) => <ResponseData>response.json());

  }

  /*To get the individual detail view of unit master*/

   listUnitJSON(link, id) {
     const url: string = link + '/' + id;
     return this.http.get(url).map((response: Response) => <ResponseData>response.json());
  }

  deleteUnitJSON(link, param) {
    const url: string = link + '/' + param;
    return this.http.delete(url).map((response: Response) => <ResponseData>response.json());

  }


  // submitUnitJSON(param) {
  //   const headers = new Headers({ 'Content-Type': 'application/json' });
  //   const url: string ='/uomservice/uom';
  //   return this.http.post(url,
  //     JSON.stringify(param),
  //     { headers: headers })
  //     .map((res) => { return res.json(); })
  // }
  setId(id: number) {
    this.sharingData.id = id;
  }
  getId() {
    return this.sharingData.id;
  }
  setStatusId(statusId: number) {
    this.sharingData.statusId = statusId ;
  }
  getStatusId() {
    return this.sharingData.statusId;
  }

  setMessage(msg: string) {
    this.message = msg;
  }

  getMessage() {
    return this.message;
  }
  setUnitName(name: string) {
    this.sharingData.name = name;
  }
  getUnitName() {
    return this.sharingData.name;
  }

  getUnitCodeStyle() {
    if (this.req_unitCode === true) {
      return '#d43c3c';
    } else {
      return '#cfdee7';
    }
  }

  getUnitNameStyle() {
    if (this.req_unitName === true) {
      return '#d43c3c';
    } else {
      return '#cfdee7';
    }
  }

  getUnitFullNameStyle() {
    if (this.req_unitFullName === true) {
      return '#d43c3c';
    } else {
      return '#cfdee7';
    }
  }
}
