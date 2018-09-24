import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import { ResponseData } from '../../../../shared/interface/responseData';
import { environment } from '../../../../../environments/environment';


import { Outturn } from '../outturn';

@Injectable()
export class OutturnService {

outturnDetails: Outturn[];
outturnRatioDetails: Outturn[];
viewbyIdParam: any;
arrStringJSON: any;
errorMessage: any;
errorStatusCode: any;

  constructor(private http: Http) { }


// getting product service

//   getProductDetail(): Observable<ResponseData> {
//     const url = '/productservice/product';
//     return this.http.get(url).map((response: Response) => <ResponseData>response.json());
//    }

// get All drop Down list via RESTAPI
getOutturnloadingJSON() {
     const url = '/product/outturnratioservice/outturnratio';
     return this.http.get(url)
    .map((response: Response) => <ResponseData>response.json())
    .catch((error: any) => Observable.throw(error.json().error || 'Server error')) ;
  }
// get permission Group Record by id
getOutturnratioById(id: String) {

     const url = '/product/outturnratioservice/outturnratio/' + id;
    // const url = 'https://api.myjson.com/bins/1eks71';
    console.log(url);
    return this.http.get(url).map((response: Response) => <ResponseData>response.json())
    .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }


getProductDetail() {
   // const url = 'https://api.myjson.com/bins/s3e2t';
   const url = '/product/productservice/product';
     return this.http.get(url).map((response: Response) => response.json());
   }

   getgradeDetail() {
    const url = '/product/gradeservice/grade';
   // const url = 'https://api.myjson.com/bins/p8idh';
    return this.http.get(url).map((response: Response) => <ResponseData>response.json());
   }

   getgradeDetailByid(id: Number, rawFinishGrade: String ) {
    const url = '/product/gradeservice/grade/?prodId=' + id + '&gradeIsRaw=' + rawFinishGrade;
   // const url = 'https://api.myjson.com/bins/p8idh';
    return this.http.get(url).map((response: Response) => <ResponseData>response.json());
   }

   getoutturnDataById(url, id) {
    url = '/product/outturnratioservice/outturnratio/' + id;
    return this.http.get(url).map((response: Response) => <ResponseData>response.json());
  }



// getting origin service

getOriginDetail(): Observable<ResponseData> {
    /* const url = '/originservice/origin';
    const url = 'https://api.myjson.com/bins/p8idh'; */
    const url = '/product/originservice/origin';
    return this.http.get(url).map((response: Response) => <ResponseData>response.json());
   }

saveoutturnDetails(outturn: Outturn) {
   // alert("inside out");
   // const headers = new Headers({ 'Content-Type': 'application/json' });
   // http://10.66.194.55:8082/outturnratioservice/outturnratio
   // const url: string = environment.path + 'outturnratioservice/outturnratio';
    return this.http.post('/product/outturnratioservice/outturnratio', JSON.stringify(outturn))
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
                this.errorMessage = error.json().errorMessage;
                if (error.status === 409) {
                       return Observable.throw(new Error(error.status));
                }
      });
                }

/*
submitoutturnDetails(outturn: Outturn) {
   // const headers = new Headers({ 'Content-Type': 'application/json' });
  //  const url: string = environment.path + 'outturnratioservice/outturnratio';
    return this.http.post('/product/outturnratioservice/outturnratio',  JSON.stringify(outturn))
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
                this.errorMessage = error.json().errorMessage;
                if (error.status === 409) {
                       return Observable.throw(new Error(error.status));
                }
      });
        } */



updateOutturn(url, outturn: Outturn[]) {
    url = '/product/outturnratioservice/outturnratio';
    return this.http.put(url, JSON.stringify(outturn), { })
      .map((res: Response) => {
        console.log('updateOutturn -> ' + res);
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

deleteOutturnratiodetails(dataItem) {
    const url = '/product/outturnratioservice/outturnratio';
    const outturnRawGradeId = { 'outturnRawGradeId': dataItem };
    this.arrStringJSON = ' [ ' + JSON.stringify(outturnRawGradeId) + ' ] ';
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

updateOutturnratio(param) {
     const url = '/product/outturnratioservice/outturnratio';
     this.arrStringJSON = '[' + JSON.stringify(param) + ']';
     console.log(this.arrStringJSON);
     return this.http.put(url, this.arrStringJSON)
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
                this.errorStatusCode = error.status;
                if (error.status === 500) {
                    // TO DO: It should not be 500
                   // this needs to be removed once backend is handled for viewbyId response code for Duplicate GL code
                   return Observable.throw(this.errorMessage = 'Outturn ratio code Already exists.Please Choose any Other..');
                } else if (error.status === 400) {
                    return Observable.throw(new Error(error.status));
                } else if (error.status === 409 || error.status === 404) {
                   return Observable.throw(this.errorMessage = 'Outturn ratio code Already exists.Please Choose any Other..');
                } else if (error.status === 406) {
                    return Observable.throw(new Error(error.status));
                }
 });
   }


}

