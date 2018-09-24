import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { ResponseData } from '../../../../shared/interface/responseData';
import { Observable } from 'rxjs/Observable';
import { Subject } from 'rxjs/Subject';
import 'rxjs/add/operator/map';
import { environment } from '../../../../../environments/environment';
import { SelectItem } from '../../../../shared/interface/selectItem';
import { AbstractLinkService } from '../../../../shared/service/abstract-link.service';
import { Grade, uomcurrencyurl, gradeurl, processingtypeurl ,
        externalsystemurl, originbasedobgradeurl , brandproducturl } from '../grade-model';



@Injectable()
export class ProductGradeService extends AbstractLinkService {
externallist: SelectItem[];
intlcodetypelist: SelectItem[];
addoreditpageflag: boolean;
errorMessage: string;
productGradeDetails: Grade[];
grade: Grade = new Grade();
gradeDetails;
gradeCode: string;
gradeName: string;
errorStatusCode: string;
msgStatusName: String;
 arrStringJSON: any;
errorresponse: ResponseData;
  constructor(private http: Http ) {
    super();
  }

  getProductGradeViewList(): Observable<ResponseData>  {
    const url =  gradeurl.toString();
    return this.http.get(url)
    .map(res =>  res.json())
        .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
 }

getGradeList() {
    return this.productGradeDetails ;
}

getProductGradeById(param): Observable<ResponseData> {
    const url: string = gradeurl + param;
     return this.http.get(url)
    .map(res =>  res.json())
        .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }


 getProductGradeById1(param): Observable<ResponseData> {
    const url: string = gradeurl + param;
    return this.http.get(url).map((res: Response ) => {
            if (res) {
               if (res.status === 200) {
                return [{ status: res.status, json: res }]
            }
          }
        return res.json();
      })
    .catch((error: any) => {
        /*this.errorStatusCode = error.status;
                if (error.status === 500) {
                    return Observable.throw(new Error(error.status));
                }else if (error.status === 400) {
                    return Observable.throw(new Error(error.status));
                }else if (error.status === 406) {
                    return Observable.throw(new Error(error.status));
                }else if (error.status === 404) {
                    return Observable.throw(new Error(error.status));
                }*/
                 this.msgStatusName = null;
                return this.errorHandling(error);
      });
  }


// save, submit , update the data based on  add or edit page parameter then send to rest api
saveProductGrade(param , addoredit) {
    this.gradeName = param.gradeName;
   this.addoreditpageflag = addoredit;
   const url = gradeurl.toString() ;
  console.log('In service save grade group');
  if (addoredit ) {
          return this.http.post(url  , param)
     .map((res: Response ) => {
            if (res) {
            if (res.status === 200) {
                return [{ status: res.status, json: res }]
            }
          }
        return res.json();
      }).catch((error: any) => {
          /*this.errorresponse = JSON.parse(error._body);
                if (error.status === 500) {
                    return Observable.throw(new Error( this.errorMessage = ' Kindly contack Administration, status:' + error.status));
                }else if (error.status === 400) {
                    return Observable.throw(new Error(error.status));
                }else if (error.status === 409)  {
                   return Observable.throw(new Error(this.errorMessage = this.errorresponse.errorMessage));
                }else if (error.status === 406) {
                    return Observable.throw(new Error(error.status));
                }else if (error.status === 404) {
                    return Observable.throw(new Error(error.status));
                }*/
                 this.msgStatusName = null;
                return this.errorHandling(error);
      });
   }else if ( addoredit === false) {
        return this.http.put(url  , param)
           .map((res: Response ) => {
           if (res) {
             if (res.status === 200) {
                return [{ status: res.status, json: res }]
            }
          }
        return res.json();
      }).catch((error: any) => {
           /* this.errorresponse = JSON.parse(error._body);
            if (error.status === 500) {
                return Observable.throw(new Error( this.errorMessage = ' Kindly contact Administration, status:' + error.status));
            }else if (error.status === 400) {
                return Observable.throw(new Error(error.status));
            }else if (error.status === 409)  {
                return Observable.throw(new Error(this.errorMessage = this.errorresponse.errorMessage));
            }else if (error.status === 406) {
                return Observable.throw(new Error(error.status));
            }else if (error.status === 404) {
                return Observable.throw(new Error(error.status));
            }*/
             this.msgStatusName = null;
            return this.errorHandling(error);
      });
   }
}
// get All drop Down list via RESTAPI
// UOM & Currency
getGradeDropDownUOMCurrency(): Observable<ResponseData> {
     const url =  uomcurrencyurl.toString();
     return this.http.get(url)
    .map((response: Response) => <ResponseData>response.json())
    .catch((error: any) => Observable.throw(error.json().error || 'Server error')) ;
}
// Product , Brand and Grade Grouping
getGradeDropDownProdGroupingBrand(): Observable<ResponseData> {
     const url =  brandproducturl.toString();
     return this.http.get(url)
    .map((response: Response) => <ResponseData>response.json())
    .catch((error: any) => Observable.throw(error.json().error || 'Server error')) ;
}
// Based on Product will get Processing type and sub type and ICO Index
getProcessingTypeBasedOnProduct(id): Observable<ResponseData> {
  if (id != null ) {
     const url = processingtypeurl + id;
     return this.http.get(url)
    .map((response: Response) => <ResponseData>response.json())
    .catch((error: any) => Observable.throw(error.json().error || 'Server error')) ;
  }
}
getOriginBasedOnGradeGroup(id): Observable<ResponseData> {
  if (id != null ) {
     const url =  originbasedobgradeurl + id;
     return this.http.get(url)
    .map((response: Response) => <ResponseData>response.json())
    .catch((error: any) => Observable.throw(error.json().error || 'Server error')) ;
  }
}

// get External system drop down
getExternalSystem(): Observable<ResponseData> {
     const url =  externalsystemurl.toString();
     return this.http.get(url)
    .map((response: Response) => <ResponseData>response.json())
    .catch((error: any) => Observable.throw(error.json().error || 'Server error')) ;

}


 updateProductGradeDetails(baseDet: Grade) {
   this.gradeName = baseDet.gradeName;
     const url =  externalsystemurl.toString();
    return this.http.put( url, baseDet)
        .map((res: Response ) => {
                  if (res) {
                    if (res.status === 200) {
                        return [{ status: res.status, json: res }]
                    }
              }
            return res.json();
      }).catch((error: any ) => {
            /*this.errorStatusCode = error.status;
             if (error.status === 500) {
                    // TO DO: It should not be 500
                   // this needs to be removed once backend is handled for viewbyId response code for Duplicate UOM code
                   return Observable.throw( this.errorMessage = 'Product Grade Code Already exists.Please Choose any Other..' ) ;
              } else if (error.status === 400) {
                    return Observable.throw(new Error(error.status));
              } else if (error.status === 409 || error.status === 404) {
                   return Observable.throw(this.errorMessage = 'Product Grade Code Already exists.Please Choose any Other..');
              } else if (error.status === 406) {
                    return Observable.throw(new Error(error.status));
              }*/
               this.msgStatusName = null;
              return this.errorHandling(error);
      });
   }


deleteProductGrade(param, gradeName) {
     this.gradeName = gradeName;
   const url =  gradeurl.toString();
    this.arrStringJSON = ' [ ' + JSON.stringify(param) + ' ] ';
    return this.http.delete(url , new RequestOptions({
                     body:  this.arrStringJSON
              })) .map((res: Response ) => {
             if (res) {
                     if (res.status === 200) {
                         return [{ status: res.status, json: res }]
                    }
              }
        return res.json();
      }).catch((error: any) => {
                 /*this.errorStatusCode = error.status;
                if (error.status === 422) {
                    // return Observable.throw(new Error(error.status));
                     return Observable.throw(new Error(this.errorMessage = 'Cannot Delete..Product Grade Code is+'
                      + ' already associated as a parent to some other UOM'));
                     // this.errorMessage="UOM Code Already exists.Please Choose any Other..")
                } else if (error.status === 400) {
                    return Observable.throw(new Error(error.status));
                } else if (error.status === 409 || error.status === 404) {
                   return Observable.throw(new Error(this.errorMessage = 'Product Grade Code Already exists.Please Choose any Other..'));
                } else if (error.status === 406) {
                    return Observable.throw(new Error(error.status));
                }*/
                 this.msgStatusName = null;
                return this.errorHandling(error);
         });
   }
}

