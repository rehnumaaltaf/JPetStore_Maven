import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { ResponseData } from 'app/shared/interface/responseData';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import { environment } from 'environments/environment';
import { Currency } from '../model/currency';
import { MessageModel } from '../../../../shared/message/message.model';
import { noLoading } from '../../../../shared/interface/router-links';
import { MESSAGE_ERROR } from '../../../../shared/interface/common.constants';
import { AbstractLinkService } from '../../../../shared/service/abstract-link.service';

@Injectable()
export class CurrencyService extends AbstractLinkService {

  currencyDetails: Currency[];
  currencyDetail: Currency = new Currency();
  errorMessage: string;
  messages: MessageModel;

  constructor(private _http: Http) {
    super();
  }
  /*Grid Data Service Call Function*/
  getCurrencyJSON(link): Observable<ResponseData> {
    const url = '/reference/currencyservice/currency';
    return this._http.get(url).map((response: Response) => <ResponseData>response.json());
  }

  addCurrency(newCurrency: Currency[]) {
    this.currencyDetails = <Currency[]>newCurrency;
  }

  updateCurrencydtl(newCurrency: Currency[]) {
    this.currencyDetails = newCurrency;
  }
  /*Add Function*/
  saveCurrency(link, param) {
    const headers = new Headers({ 'Content-Type': 'application/json' });
    const url = '/reference/currencyservice/currency';
    // const url = '/currencyservice/currency';
    return this._http.post(url,
      JSON.stringify(param),
      {}).map((res: Response) => {
        return res.json();

      }).catch((error: any) => {
        //  this.errorStatusCode = error.status;
        this.errorMessage = error.json().errorMessage;
        // alert(this.errorMessage);
        if (error.status === 500) {
          return Observable.throw(new Error(error.status));
        } else if (error.status === 400) {
          return Observable.throw(new Error(error.status));
        } else if (error.status === 409 || error.status === 404) {
          return Observable.throw(this.errorMessage);
        } else if (error.status === 406) {
          return Observable.throw(new Error(error.status));
        }
      });
  }
  /*Update Function*/
  updateCurrency(link, param) {
    const headers = new Headers({ 'Content-Type': 'application/json' });
    const url = '/reference/currencyservice/currency';
    console.log(url);
    return this._http.put(url,
      JSON.stringify(param),
      {})
      .map((res) => {
        return res.json();
      }).catch((error: any) => {
        //  this.errorStatusCode = error.status;
        //  this.errorMessage = JSON.stringify(error.json().body);
        this.errorMessage = error.json().errorMessage;
        if (error.status === 500) {
          return Observable.throw(new Error(error.status));
        } else if (error.status === 400) {
          return Observable.throw(new Error(error.status));
        } else if (error.status === 409 || error.status === 404) {
          return Observable.throw(this.errorMessage);
        } else if (error.status === 406) {
          return Observable.throw(new Error(error.status));
        }
      });
  }
  /*Delete Function*/
  deleteCurrency(link, param) {
    const url = '/reference/currencyservice/currency';
    return this._http.delete(url + '/' + param).map((res) => res.json())
  }

  /*Currency Name Suggestion Service Call*/
  getCurrencyNamelist(srchval: string): Observable<any> {
    const url = noLoading + '/reference/currencyservice/currency/suggestCurrName/';
    return this._http.get(url + srchval).map((response: Response) => <any>response.json())
   }

  /*Currency Code Suggestion Service Call*/
  getCurrencyCodelist(srchval: string) {
    const url = noLoading + '/reference/currencyservice/currency/suggestCurrCode/';
    return this._http.get(url + srchval).map((response: Response) => <any>response.json())
   }

  setRowData(row: Currency) {
    this.currencyDetail = row;
  }

  getRowData() {
    return this.currencyDetail;
  }

}

