import { Injectable, ViewChild } from '@angular/core';
import { CropYear } from '../crop';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { AbstractLinkService } from '../../../../shared/service/abstract-link.service';
import { Link } from '../../../../shared/interface/link';
//import { Product } from '../../product-definition/product-definition';
import { environment } from '../../../../../environments/environment';
import { Observable } from 'rxjs/Observable';
import { ModalDirective } from 'ngx-bootstrap/modal';

@Injectable()
export class CropYearService extends AbstractLinkService {
  @ViewChild ('validation') public validation: ModalDirective;
  cropyearDetails: CropYear[];
  // productdetais : Product[];
  viewbyIdParam: any;
  errorMessage: any;
  errorStatusCode: any;
  cropYearCodeParam: any;
  arrStringJSON: any;
  flag: boolean;

  constructor(private http: Http) { 
    super();
  }

   getCropYearJSON() {
    // alert('patyh===>'+environment.path+'/cropyearservice/cropyear');
   // const link: Link = this.masterService.getChildObject('View Uom.GET');
    // const url: string = 'http://localhost:8082/cropyearservice/cropyear';https://api.myjson.com/bins/otfux//http://localhost:8082/cropyearservice/cropyear
    //debugger;
    return this.http.get('/product/cropyearservice/cropyear').map((response: Response) =>response.json());
    
    // return this.http.get(url).map((response: Response) => <ResponseData>response.json());

  }
getProductList() {
    return this.http.get('/product/productservice/product').map((response: Response) => response.json());
}
savecropdetails(dataItem: CropYear) {
    //const headers = new Headers({ 'Content-Type': 'application/json' });
    return this.http.post('/product/cropyearservice/cropyear', JSON.stringify(dataItem),
    ).map((response: Response) => response.json()
    ).catch((error: any) => {
                 /*this.errorStatusCode = JSON.parse(error._body);
                 if (error.status === 409 || error.status === 404) {
                   return Observable.throw(new Error(this.errorMessage = 'Crop Year Code Already exists.Please Choose any Other..'));
                }else if (error.status === 400) {
                  this.validation.show();
                }*/
                return this.errorHandling(error);
         });
}

editcropdetails(dataItem: CropYear) {
    //const headers = new Headers({ 'Content-Type': 'application/json' });
    return this.http.put('/product/cropyearservice/cropyear', JSON.stringify(dataItem),
      ).map((response: Response) => response.json());
}

editcropdetailsstatus(dataItem: CropYear) {
    //const headers = new Headers({ 'Content-Type': 'application/json' });
    return this.http.put('/product/cropyearservice/cropyear', JSON.stringify(dataItem),
      ).map((response: Response) => response.json());
}

deletecropdetails(dataItem) {
    // const headers = new Headers({ 'Content-Type': 'application/json' }) ;
    const url = '/product/cropyearservice/cropyear';
    const cropYearId = { 'cropYearId': dataItem };
    this.arrStringJSON = ' [ ' + JSON.stringify(cropYearId) + ' ] ';
    return this.http.delete(url , new RequestOptions({
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
      });
}
}
