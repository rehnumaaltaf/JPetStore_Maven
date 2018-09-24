import { Injectable, ViewChild } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { ResponseData } from '../../../../shared/interface/responseData';
import { Observable } from 'rxjs/Observable';
import { Link } from '../../../../shared/interface/link';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import { ShipmentWeek } from '../shipment-month-interface';
import { environment } from '../../../../../environments/environment';
import { AbstractLinkService } from '../../../../shared/service/abstract-link.service';
import { MasterSetupService } from '../../../master-setup/service/master-setup.service';
import { ModalModule } from 'ngx-bootstrap';
// import { MessageModel } from '../../../../shared/message/message';
import { noLoading } from '../../../../shared/interface/router-links';
import { ModalDirective } from 'ngx-bootstrap/modal';


@Injectable()
export class ShipmentMonthService {
@ViewChild ('validation') public validation: ModalDirective;
 shipmentdetails: ShipmentWeek[];
 shipmentweekdetails: ShipmentWeek = new ShipmentWeek();
 errorMessageFromDb: any;
 successModal: ModalModule;
 sharingData: any = {};
 arrStringJSON: any;
 errorStatusCode: any;
 errorMessage: string;
 // messages: MessageModel[] = [];
 viewbyIdParam: any;

  constructor(private http: Http) {

   }
getShipmentMonthJSON() {

    // const url = 'https://api.myjson.com/bins/6s3e5';
      const url = '/reference/shipmentweekservice/shipmentweek';
    return this.http.get(url).map((response: Response) =>  response.json());
  }

getRulesDetailsJSON() {
      const url = '/reference/shipmentweekservice/shipmentweek/shipmentrule';
   // const url = 'https://api.myjson.com/bins/9r9f1';
  return this.http.get(url).map((response: Response) => response.json());

}

  saveShipmentDetails(dataItem: ShipmentWeek) {

      return this.http.post('/reference/shipmentweekservice/shipmentweek', JSON.stringify(dataItem)
      ).map((res: Response) => {
                  return res.json() ;

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

editShipmentDetails(dataItem: ShipmentWeek) {

    this.arrStringJSON = ' [ ' + JSON.stringify(dataItem) + ' ] ';
 return this.http.put('/reference/shipmentweekservice/shipmentweek', this.arrStringJSON,
      ).map((res: Response) => {
                  return res.json() ;

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

editShipmentWithStatus(dataItem: ShipmentWeek) {

this.arrStringJSON = ' [ ' + JSON.stringify(dataItem) + ' ] ';
return this.http.put('/reference/shipmentweekservice/shipmentweek', this.arrStringJSON,
      ).map((res: Response) => {
                  return res.json() ;

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

deleteShipmentDetails(dataItem: ShipmentWeek) {

    const shipmentWeekTimeframeId = { 'shipmentWeekTimeframeId': dataItem };
    this.arrStringJSON = ' [ ' + JSON.stringify(shipmentWeekTimeframeId) + ' ] ';
    console.log('shipmentWeekTimeframeId in service' + this.arrStringJSON)
      return this.http.delete('/reference/shipmentweekservice/shipmentweek', new RequestOptions({
body: this.arrStringJSON
})).map((res: Response) => {
                  return res.json();

      }).catch(( error: any ) => {
                if (error.status === 500) {
                   return Observable.throw(new Error(error.status));
                } else if (error.status === 400) {
                    return Observable.throw(new Error(error.status));
                } else if (error.status === 409 || error.status === 404 || error.status === 403) {
                   // this.messages = [];
                   // this.messages.push({severity: 'error', summary: error.json().body, detail: ''});
                   return Observable.throw(new Error(error.status));
                } else if (error.status === 406) {
                    return Observable.throw(new Error(error.status));
                }
      });
  }

}
