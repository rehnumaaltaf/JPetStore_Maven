import { Injectable } from '@angular/core';
import { ExternalPacking } from '../model/external-packing';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { ResponseData } from '../../../../shared/interface/responseData';
import { ProductMapping } from '../model/external-packing';
import { PrimaryPackingList } from '../model/external-packing';
import { OriginList } from '../model/external-packing';
import { AbstractLinkService } from '../../../../shared/service/abstract-link.service';
import { MasterSetupService } from '../../../master-setup/service/master-setup.service';
import { noLoading } from '../../../../shared/interface/router-links';
import { MessageModel } from '../../../../shared/message/message.model';

@Injectable()
export class ExternalPackingService extends AbstractLinkService {

  public errorMessage: string;
  public productMappingList: ProductMapping[];
  public primaryPackingList: PrimaryPackingList[];
  public originCodeList: OriginList[];
  packingDetail: ExternalPacking;
  packList: any = {};
  arrStringJSON: JSON;
  public actionName;
  public secPackName;
  messages: MessageModel;

  constructor(private http: Http, private masterService: MasterSetupService) {
    super();
  }

  saveExtPacking(url, externalPackingModel: ExternalPacking) {
    // console.log(externalPackingModel);
    url = '/packing/secPackTypeService/secPackType';
    return this.http.post(url, JSON.stringify(externalPackingModel), { })
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
        this.errorMessage = JSON.parse(error._body).errorMessage
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

  getDataById(url, id) {
    url = '/packing/secPackTypeService/secPackType/' + id;
    return this.http.get(url).map((response: Response) => <ResponseData>response.json());
  }

  getPackingDetailsJSON() {
  const url = '/packing/secPackTypeService/secPackType';
  return this.http.get(url).map((response: Response) => <ResponseData>response.json());
}

  updateSecPack(url, externalPackingModel: ExternalPacking) {
    url = '/packing/secPackTypeService/secPackType';
    return this.http.put(url, JSON.stringify(externalPackingModel), { })
      .map((res: Response) => {
        console.log('updateSecPack -> ' + res);
        if (res) {
          if (res.status === 201) {
            return [{ status: res.status, json: res }]
          } else if (res.status === 200) {
            return [{ status: res.status, json: res }]
          }
        }
        return res.json();
      }).catch((error: any) => {
        this.errorMessage = JSON.parse(error._body).errorMessage
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

  getProductList(url) {
    url = '/product/productservice/product/basicdetails';
    return this.http.get(url).map((response: Response) => <ResponseData>response.json());
  }

  getPrimaryPackingList(url) {
    url = '/packing/PrimaryPackingType/basicdetails';
    return this.http.get(url).map((response: Response) => <ResponseData>response.json());
  }

  getOriginCodeList(url) {
    url = '/product/originservice/origin';
    return this.http.get(url).map((response: Response) => <ResponseData>response.json());
  }

  deletePackingMaterial(id) {
  // http://10.87.100.211:8091/secPackTypeService/secPackType/34
  const url = '/packing/secPackTypeService/secPackType/' + id ;
  return this.http.delete(url).map((response: Response) => <ResponseData>response.json());
 }

  setRowData(row: ExternalPacking) {
    this.packingDetail = row;
  }

  getRowData() {
    return this.packingDetail;
  }

 setPackingCode(packingcode: String) {
   this.packList.packingcode = packingcode;
}
getPackingCode() {
    return this.packList.packingcode;
}

setStatusname(statusname: String) {
   this.packList.statusname = statusname;
}
getStatusname() {
    return this.packList.statusname;
}

setPackingPkId(packingId: Number) {
   this.packList.packingId = packingId;
}
getPackingPkId() {
    return this.packList.packingId;
}

secPackCodeSuggestion(url, code) {
  url = noLoading + '/packing/secPackTypeService/secPackType/suggestSecCode/' + code;
  return this.http.get(url).map((response: Response) => <String[]>response.json());
}

secPackNameSuggestion(url, name) {
  url = noLoading + '/packing/secPackTypeService/secPackType/suggestSecName/' + name;
  return this.http.get(url).map((response: Response) => <String[]>response.json());
}

}
