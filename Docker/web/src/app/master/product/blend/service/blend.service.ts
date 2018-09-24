import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { ResponseData } from '../../../../shared/interface/responseData';
import { Observable } from 'rxjs/Observable';
import { Link } from '../../../../shared/interface/link';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import { environment } from '../../../../../environments/environment';
import { AbstractLinkService } from '../../../../shared/service/abstract-link.service';
import { SelectItem } from '../../../../shared/interface/selectItem';
import { MessageModel } from '../../../../shared/message/message.model';
import { BlendMatrix , ProductMaster , OriginMaster , GradeMaster , CertificationMaster} from '../model/blend-model';
import { MESSAGE_ERROR } from '../../../../shared/interface/common.constants';
import { noLoading } from '../../../../shared/interface/router-links';

@Injectable()
export class BlendService extends AbstractLinkService {
  blendList: any = {};
  blendMasterList: BlendMatrix[];
  blendDetails: BlendMatrix;
   public blendProductList: ProductMaster[] = [];
  public blendOriginList: OriginMaster[] = [];
  public blendGradeList: GradeMaster[] = [];
  public blendCertificationList: CertificationMaster[] = [];
  errorMessage: string;
  public reqOutProduct = [];
  public reqOutOrigin = [];
  public reqOutGrade = [];
  public reqOutPer = [];
  public reqOutRatio = [];
  public reqInProduct = [];
  public reqInOrigin = [];
  public reqInGrade = [];
  public reqInPer = [];
  public viewbyIdParam: BlendMatrix;
  messages: MessageModel;
  blendNameList: String[] = [];
  blendCodeList: String[] = [];
  strValue = '/product';

  constructor(private http: Http) {
  super();
  }


    /* Basic View For Blend*/
  getBlendMasterDetailsJSON(param) {
   const url = this.strValue + '/blendService/blend';
  return this.http.get(url).map((response: Response) => <ResponseData>response.json());
 }
   getMockBlendMasterJSON(param) {
   const url =  this.strValue + '/blendService/blend';
   return this.http.get(url).map((response: Response) => <ResponseData>response.json());
  }

  // auto suggest

  getUniqueCheckCode( blendDetails: BlendMatrix): Observable<ResponseData> {
    const url = noLoading + this.strValue + '/blendService/blend/suggest';
    return this.http.post(url,
      JSON.stringify(blendDetails),
      {})
       .map((res) => res.json());
  }

  getUniqueCheckName( blendDetails: BlendMatrix): Observable<ResponseData> {
    const url = noLoading + this.strValue + '/blendService/blend/suggest';
    return this.http.post(url,
      JSON.stringify(blendDetails),
      {})
       .map((res) => res.json());
  }


  updateBlendDetail(url, blendDetails: BlendMatrix) {
    url = this.strValue + '/blendService/blend';
    console.log('PASSING DATA update>>' + JSON.stringify(blendDetails));
    return this.http.put(url, JSON.stringify(blendDetails), { })
      .map((res: Response) => {
        console.log('updateBlend -> ' + res);
        if (res) {
         if (res.status === 200) {
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
          this.messages = {severity: MESSAGE_ERROR, summary: error.json().errorMessage};
          return Observable.throw(new Error(error.status));
        } else if (error.status === 406) {
          return Observable.throw(new Error(error.status));
        }
      });
  }

  deleteBlendTemp(id) {
  const url = this.strValue + '/blendService/blend/' + id ;
  return this.http.delete(url).map((response: Response) => <ResponseData>response.json());
 }

  getSelectedMatrixData(matrixname, blendid) {
   const url = this.strValue + '/blendService/blend/' + blendid ;
   return this.http.get(url).map((response: Response) => <ResponseData>response.json());
  }

setRowData(row: BlendMatrix) {
  this.blendDetails = row;
}

getRowData() {
  return this.blendDetails;
}

setBlendCode(blendcode: String) {
  this.blendList.blendcode = blendcode;
}
getBlendCode() {
  return this.blendList.blendcode;
}

setStatusname(statusname: String) {
  this.blendList.statusname = statusname;
}
getStatusname() {
  return this.blendList.statusname;
}

setBlendPkId(blendId: Number) {
  this.blendList.blendId = blendId;
}

getBlendPkId() {
  return this.blendList.blendId;
}

getProductList() {
    const url =   '/product/productservice/product/basicdetails';
    return this.http.get(url).map((response: Response) => <ResponseData>response.json());
  }

  getOriginList() {
    const url = '/product/originservice/origin';
    return this.http.get(url).map((response: Response) => <ResponseData>response.json());
  }

  getGradeList() {
    const url = '/product/gradeservice/grade';
    return this.http.get(url).map((response: Response) => <ResponseData>response.json());
  }

  getCertificationdd() {
    const url = '/product/certificationservice/certification';
    return this.http.get(url).map((response: Response) => <ResponseData>response.json());
  }

  saveBlendDetails(blendData: BlendMatrix) {
    const url = this.strValue + '/blendService/blend';
    console.log('PASSING DATA>>' + JSON.stringify(blendData));
    return this.http.post(url,
      JSON.stringify(blendData),
      {})
      .map((res: Response) => {
          if (res) {
              if (res.status === 200) {
                return [{ status: res.status, json: res }]
            }
          }

         return res.json();
      }).catch((error: any) => {
                if (error.status === 500) {
                    return Observable.throw(new Error(error.status));
                }else if (error.status === 400) {
                    return Observable.throw(new Error(error.status));
                }else if (error.status === 409 || error.status === 404) {
                  this.messages = {severity: MESSAGE_ERROR, summary: error.json().errorMessage};
                  return Observable.throw(new Error(error.status));
                } else if (error.status === 406) {
                    return Observable.throw(new Error(error.status));
                }
      });
  }
}
