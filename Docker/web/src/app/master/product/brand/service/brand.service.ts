import { Injectable } from '@angular/core';
import { BrandModel } from '../model/brand.model';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { AbstractLinkService } from '../../../../shared/service/abstract-link.service';
import { Link } from '../../../../shared/interface/link';
// import { Product } from '../../product-definition/product-definition';
import { environment } from '../../../../../environments/environment';
import { Observable } from 'rxjs/Observable';
import { ModalDirective } from 'ngx-bootstrap/modal';
import { ResponseData } from '../../../../shared/interface/responseData';
import { MasterSetupService } from '../../../master-setup/service/master-setup.service';
import { MessageModel } from '../../../../shared/message/message.model';
import { noLoading } from '../../../../shared/interface/router-links';
import { MESSAGE_ERROR } from '../../../../shared/interface/common.constants';

@Injectable()
export class BrandService  extends AbstractLinkService {
  brandDetails: BrandModel[];
  brandDetail: BrandModel = new BrandModel();
  errorMessage: string;
  messages: MessageModel;
  constructor(private http: Http, public masterService: MasterSetupService) {
    super();
  }

   getBrandDetails(link): Observable<ResponseData> {
    // const link: Link = this.masterService.getChildObject('View Uom.GET');
    // const url = link.href;
    // const url = 'https://api.myjson.com/bins/zvmkx';
     const url = '/product/brand/viewBrand';
    return this.http.get(url).map((response: Response) =>  <ResponseData> response.json());
  }

  getBrandDetailsById(id): Observable<ResponseData> {
    // const link: Link = this.masterService.getChildObject('View Uom.GET');
    // const url = link.href;
    // const url = 'https://api.myjson.com/bins/zvmkx';
     const url = '/product/brand/viewBrand/' + id;
    return this.http.get(url).map((response: Response) =>  <ResponseData> response.json());
  }

  saveBrandDetails(link, dataItem: BrandModel) {
      // const headers = new Headers({ 'Content-Type': 'application/json' });
      return this.http.post('/product/brand/addBrand', JSON.stringify(dataItem)
      ).map((res: Response) => {
                  return res.json() ;

      }).catch(( error: any ) => {
                if (error.status === 500) {
                   return Observable.throw(new Error(error.status));
                } else if (error.status === 400) {
                    return Observable.throw(new Error(error.status));
                } else if (error.status === 409 || error.status === 404 || error.status === 403) {
                   this.messages = {severity: MESSAGE_ERROR, summary: error.json().body};
                   return Observable.throw(new Error(error.status));
                } else if (error.status === 406) {
                    return Observable.throw(new Error(error.status));
                }
      });
  }
  activeDeactivateBrand(link, dataItem: BrandModel) {
         dataItem.brandIsInternal = (dataItem.brandIsInternal === 'Internal') ? '1' : '0' ;
       return this.http.delete('/product/brand/editBrand', JSON.stringify(dataItem)).map((res: Response) => {
                  return res.json() ;

      }).catch(( error: any ) => {
         console.log(error);
                if (error.status === 500) {
                   return Observable.throw(new Error(error.status));
                } else if (error.status === 400) {
                    return Observable.throw(new Error(error.status));
                } else if (error.status === 409 || error.status === 404 || error.status === 403) {
                   this.messages = {severity: MESSAGE_ERROR, summary: error.json().body};
                } else if (error.status === 406) {
                    return Observable.throw(new Error(error.status));
                }
      });
  }
  editBrand(link, dataItem: BrandModel) {
      /*const headers = new Headers({ 'Content-Type': 'application/json' });
      return this.http.post('http://localhost:8082/cropyearservice/cropyear', JSON.stringify(dataItem),
        { headers: headers }).map((response: Response) => response.json());*/
         dataItem.brandIsInternal = (dataItem.brandIsInternal === 'Internal') ? '1' : '0' ;
       return this.http.post('/product/brand/editBrand', JSON.stringify(dataItem)).map((res: Response) => {
                  return res.json() ;

      }).catch(( error: any ) => {
                console.log(error);
                if (error.status === 500) {
                   return Observable.throw(new Error(error.status));
                } else if (error.status === 400) {
                    return Observable.throw(new Error(error.status));
                } else if (error.status === 409 || error.status === 404 || error.status === 403) {
                   this.messages = {severity: MESSAGE_ERROR, summary: error.json().body};
                } else if (error.status === 406) {
                    return Observable.throw(new Error(error.status));
                }
      });
  }
  deleteBrand(link, id) {
    return this.http.delete('/product/brand/deleteBrand/' + id) .map((res: Response) => {
                  return res.json() ;

      }).catch(( error: any ) => {
                if (error.status === 500) {
                   return Observable.throw(new Error(error.status));
                } else if (error.status === 400) {
                    return Observable.throw(new Error(error.status));
                } else if (error.status === 409 || error.status === 404 || error.status === 403) {
                   this.messages = {severity: MESSAGE_ERROR, summary: error.json().body};
                   return Observable.throw(new Error(error.status));
                } else if (error.status === 406) {
                    return Observable.throw(new Error(error.status));
                }
       });
  }

  suggestBrand(dataItem) {
    return this.http.post(noLoading + '/product/brand/suggest/' , JSON.stringify(dataItem)) .map((res: Response ) => {
        return res.json();
      });
  }
  setRowData(row: BrandModel) {
    this.brandDetail = row;
  }

  getRowData() {
    return this.brandDetail;
  }
}
