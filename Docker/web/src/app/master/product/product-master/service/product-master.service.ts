import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { ResponseData } from '../../../../shared/interface/responseData';
import { Observable } from 'rxjs/Observable';
import { BehaviorSubject } from 'rxjs/Rx';
import { Link } from '../../../../shared/interface/link';
import {ProductMasterInterface} from '../product-master-interface';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import { GridComponent, GridDataResult, DataStateChangeEvent} from '@progress/kendo-angular-grid';
import { environment } from '../../../../../environments/environment';
import { AbstractLinkService } from '../../../../shared/service/abstract-link.service';
import { TreeviewItem, TreeviewConfig } from 'ngx-treeview';
import { noLoading } from '../../../../shared/interface/router-links';

@Injectable()
export class ProductMasterService extends AbstractLinkService {
 baseProductdetails: ProductMasterInterface[];
 prodName:string;
  errorMessage: string;
 baseCodeForPayment: string;
  viewbyIdParam: any;
  errorStatusCode: any;
   selectedProductBodyToedit: any;
  arrStringJSON: any;
  editProductdet: ProductMasterInterface[];
  editGrade: ProductMasterInterface[];
  selectbaseProdEdit: ProductMasterInterface;
  editProductMultiple: ProductMasterInterface;
constructor(private http: Http) { 
 super();
  }
   getListingPageFromJson(param) {
     // alert('param==>' + param);
   // const link: Link = this.masterService.getChildObject('View Uom.GET');
    //const url: string = environment.path + param;
    return this.http.get('/product/productservice/product/').map((response: Response) =>response.json());
   // return this.http.get(url).map((response: Response) => <ResponseData>response.json());
  }
  getdestinationFromJson(){
   return this.http.get('/product/productservice/product/getDropBox').map((response: Response) => <ResponseData>response.json());
}
 
getProductFromJson() {
    // https://api.myjson.com/bins/1fdv9h
    return this.http.get('/product/productservice/product/treeview').map((response: Response) => <ResponseData>response.json());
        // const childrenCategory = new TreeviewItem({
        //     text: 'Children', value: 1, collapsed: true, children: [
        //         { text: 'Baby 3-5', value: 11 },
        //         { text: 'Baby 6-8', value: 12 },
        //         { text: 'Baby 9-12', value: 13 }
        //     ]
        // });
        // return [childrenCategory];
}

productCodeSuggestion(param): Observable<ResponseData> {
    const url = noLoading + '/product/productservice/product/basicdetails?prodCode=' + param;
    return this.http.get(url).map((response: Response) =>  <ResponseData> response.json());
  }

  productNameSuggestion(param): Observable<ResponseData> {
    const url = noLoading + '/product/productservice/product/basicdetails?prodName=' + param;
    return this.http.get(url).map((response: Response) =>  <ResponseData> response.json());
  }
 saveProductDetails(baseDet: ProductMasterInterface) {
  this.prodName = baseDet.prodName;
    const headers = new Headers({ 'Content-Type': 'application/json' });
    //    const url = '/uomservice/uom';
    const url = '/product/productservice/product/';
    return this.http.post( url, baseDet) 
    .map((res: Response ) => {
                  if (res) {
                    if (res.status === 201) {
                        return [{ status: res.status, json: res }]
                    } else if (res.status === 200) {
                        return [{ status: res.status, json: res }]
                    }
              }
            return res.json();
      }).catch((error: any ) => {
           /* this.errorStatusCode = error.status;
             if (error.status === 500) {
                    // TO DO: It should not be 500
                   // this needs to be removed once backend is handled for viewbyId response code for Duplicate UOM code
                   return Observable.throw( this.errorMessage = ' Server Error' ) ;
              } else if (error.status === 400) {
                    return Observable.throw(new Error(error.status));
              } else if (error.status === 409 || error.status === 404) {
                   return Observable.throw(this.errorMessage = 'Server Error');
              } else if (error.status === 406) {
                    return Observable.throw(new Error(error.status));
              }*/
              return this.errorHandling(error);
      });
   }
   updateProductDetails(baseDet: ProductMasterInterface){
   this.prodName = baseDet.prodName;
    const headers = new Headers({ 'Content-Type': 'application/json' });
    const url = '/product/productservice/product/';
    return this.http.put( url, baseDet)
       //{ headers: headers } )
          .map((res: Response ) => {
                  if (res) {
                    if (res.status === 201) {
                        return [{ status: res.status, json: res }]
                    } else if (res.status === 200) {
                        return [{ status: res.status, json: res }]
                    }
              }
            return res.json();
      }).catch((error: any ) => {
            /*this.errorStatusCode = error.status;
             if (error.status === 500) {
                    // TO DO: It should not be 500
                   // this needs to be removed once backend is handled for viewbyId response code for Duplicate UOM code
                   return Observable.throw( this.errorMessage = 'Product Already exists.Please Choose any Other..' ) ;
              } else if (error.status === 400) {
                    return Observable.throw(new Error(error.status));
              } else if (error.status === 409 || error.status === 404) {
                   return Observable.throw(this.errorMessage = 'Product Already exists.Please Choose any Other..');
              } else if (error.status === 406) {
                    return Observable.throw(new Error(error.status));
              }*/
              return this.errorHandling(error);
      });
   }
    deleteBasePayment(param,prodName) {
      //  alert(param);
     this.prodName = prodName;
    const headers = new Headers({ 'Content-Type': 'application/json' }) ;
   const url = '/product/productservice/product/';
    // alert("stringify param==>"+JSON.stringify(param));
    this.arrStringJSON = ' [ ' + JSON.stringify(param) + ' ] ';
    return this.http.delete(url , new RequestOptions({
                      //headers: headers,
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
      }).catch((error: any) => {
                /* this.errorStatusCode = error.status;
                if (error.status === 422) {
                    // return Observable.throw(new Error(error.status));
                     return Observable.throw(new Error());
                     // this.errorMessage="UOM Code Already exists.Please Choose any Other..")
                } else if (error.status === 400) {
                    return Observable.throw(new Error(error.status));
                } else if (error.status === 409 || error.status === 404) {
                   return Observable.throw(new Error(this.errorMessage = 'Server error'));
                } else if (error.status === 406) {
                    return Observable.throw(new Error(error.status));
                }*/
                return this.errorHandling(error);
         });
   }
    
         getselectedProduct(prodId):  Observable<ResponseData> {
            // const url: string = environment.path + '/rolemap/features/' + basetermId;
            return this.http.get('/product/productservice/product/' + prodId)
            .map((response:  Response) => <ResponseData>response.json())
            .catch((error:  any) => Observable.throw(error.json().error || 'Server error'));
            }
        productArbitrationCollection(productArb : any) {
          this.editProductdet = productArb;
        }
        gradeMapping(gradeMapping : any) {
            this.editGrade = gradeMapping;
        }
        editProduct(selectedEvent:any) {
         this.selectbaseProdEdit = selectedEvent;
        }
        selectedProductBody(fetchedBody:ProductMasterInterface) {
          this.editProductMultiple=fetchedBody;
        }
}
