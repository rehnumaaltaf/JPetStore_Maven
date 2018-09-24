import { Injectable } from '@angular/core';
import { Incoterm } from '../inco-terms-interface';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { ResponseData } from '../../../../shared/interface/responseData';
import { Observable } from 'rxjs/Observable';
import { AbstractLinkService } from '../../../../shared/service/abstract-link.service';


@Injectable()
export class IncotermService extends AbstractLinkService {
incotermDetails: Incoterm[];
arrStringJSON: any;
errorMessage: any;
errorStatusCode: any;

  constructor(private http: Http)  {
          super();

  }

  // get All drop Down list via RESTAPI
getIncotermloadingJSON() {
     const url = '/terms/contracttermsservice/contractterms';
   // const url = 'https://api.myjson.com/bins/183s5l';
     return this.http.get(url)
    .map((response: Response) => <ResponseData>response.json())
    .catch((error: any) => Observable.throw(error.json().error || 'Server error')) ;
  }

// get Inco term  Record by id
getIncotermById(id: String) {
     const url = '/terms/contracttermsservice/contractterms/' + id;
    // const url = 'https://api.myjson.com/bins/1eks71';
    console.log(url);
    return this.http.get(url).map((response: Response) => <ResponseData>response.json())
    .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

  deleteIncotermdetails(dataItem) {
   const url = '/terms/contracttermsservice/contractterms';
    const contractTermsId = { 'contractTermsId': dataItem };
    this.arrStringJSON = ' [ ' + JSON.stringify(contractTermsId) + ' ] ';
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

updateIncoterm(incoterm: Incoterm[]) {
    const url = '/terms/contracttermsservice/contractterms';
    // return this.http.put(url, JSON.stringify(incoterm), { })
    return this.http.put(url, JSON.stringify(incoterm))
      .map((res: Response) => {
        console.log('updateIncoterm -> ' + res);
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
        const errMsg = JSON.stringify(error.json().errorMessage);
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


// form here all services wriiten by Amit
getProductFromJson() {
    // http://ctrmapp:8042/product/productservice/product/treeview
    return this.http.get('/product/productservice/product/treeview').map((response: Response) => <ResponseData>response.json());
}

// read all for Basis
getBasis() {
    return this.http.get('/terms/contracttermsbasisservice/contracttermsbasis').map((response: Response) => <ResponseData>response.json());
}

// read all for Base
getBaseRef() {
    return this.http.get('/terms/contracttermsbaseservice/contracttermsbase').map((response: Response) => <ResponseData>response.json());
}

// raed all for cost Group
getCostGroup() {
    return this.http.get('/cost/costgroupservice/costgroup').map((response: Response) => <ResponseData>response.json());
}

// read all for COST
getCost() {
    return this.http.get('/cost/costService/basicCost').map((response: Response) => <ResponseData>response.json());
}

// read all for ADD/REDUCE
getAddReduce() {
    return this.http.get('/terms/contrtrmsaddreduceservice/contrtrmsaddreduce').map((response: Response) => <ResponseData>response.json());
}



saveIncoterm(incotermData: Incoterm) {
        // const headers = new Headers({ 'Content-Type': 'application/json' });

        const url = '/terms/contracttermsservice/contractterms';
        return this.http.post(url, JSON.stringify(incotermData))
            // { headers: headers })
            .map((res: Response) => {
                if (res) {
                    if (res.status === 200) {
                        return [{ status: res.status, json: res }]
                    }
                }
                return res.json();
            }).catch((error: any, errorMessageFromDb: any) => {
                const errMsg = JSON.stringify(error.json().errorMessage);
                if (error.status === 500) {
                    return Observable.throw(new Error(this.errorMessage = error.status));
                } else if (error.status === 400) {
                    return Observable.throw(new Error(this.errorMessage = error.status));
                } else if (error.status === 409 || error.status === 404) {
                    // alert("==error.status=>"+error.status+"====message=====>"+errMsg);
                    return Observable.throw(new Error(this.errorMessage = errMsg));
                } else if (error.status === 406) {
                    return Observable.throw(new Error(this.errorMessage = error.status));
                }
            });
    }

}
