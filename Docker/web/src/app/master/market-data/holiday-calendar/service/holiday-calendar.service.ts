import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { ResponseData } from '../../../../shared/interface/responseData';
import { Observable } from 'rxjs/Observable';


import { Link } from '../../../../shared/interface/link';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import { HolidayCalendar } from '../holiday-calendar-interface';
import { environment } from '../../../../../environments/environment';
import { AbstractLinkService } from '../../../../shared/service/abstract-link.service';
import { MasterSetupService } from '../../../master-setup/service/master-setup.service';
import { ModalModule } from 'ngx-bootstrap';
import { HolidayListDto } from '../holiday-calendar-interface';


@Injectable()
export class HolidayCalendarService {
    holidayCalendarSaveDetails: HolidayCalendar[];
    holidayCodeParam: any;
    viewHolidayCalendarObject: HolidayCalendar = new HolidayCalendar();
    editcode: HolidayListDto;


    // OriginDefinitionDetailsGroup: OriginDefinition[];
    errorMessage: any;
    errorMessageFromDb: any;
    successModal: ModalModule;
    sharingData: any = {};
    arrStringJSON: any;
    errorStatusCode: any;
    public holidaydetails: any;
    viewbyIdParam: HolidayCalendar;
    originCodeParam: any;
    constructor(private http: Http) {

    }

    getHolidayJSON() {
        const url = '/reference/holidaycalendarservice/holidaycalendar/';
        return this.http.get(url).map((response: Response) => response.json());
    }

    getOriginDropDownJSON(): Observable<ResponseData> {
        const url = '/location/geoservice/geo';       // for execute in local
        // const url = '/geoservice/geo';               // for dev integration
        // return this.http.get('http://ctrmsql:8080/geoservice/geo')
        return this.http.get(url)
            .map((response: Response) => response.json());

    }
    getSelectedFeatues(param, holidayCalId): Observable<ResponseData> {
        // const url: string = environment.path + '/holidaycalendarservice/holidaycalendar/' + holidayCalId;
        return  this.http.get('/reference/holidaycalendarservice/holidaycalendar/' + holidayCalId)
            .map((response: Response)  =>  <ResponseData>response.json())
            .catch((error: any)  =>  Observable.throw(error.json().error  ||  'Server error'));

    }

    // for edit Holiday Cal
    getSingleHolidayListJSON(id: String) {

        const url = '/reference/holidaycalendarservice/holidaycalendar/' + id;
        // const url = 'https://api.myjson.com/bins/1eks71';
        return this.http.get(url).map((response: Response) => <ResponseData>response.json());
    }

    saveHolidayCalendarService(holidayData: HolidayCalendar) {
        // const headers = new Headers({ 'Content-Type': 'application/json' });

        const url = '/reference/holidaycalendarservice/holidaycalendar';
        return this.http.post(url, JSON.stringify(holidayData))
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
    submitHolidayCalendarService(holidayData: HolidayCalendar) {
        //  const headers = new Headers({ 'Content-Type': 'application/json' });
        // const url: string = environment.path + '/glservice/gl' + '?action=draft';
        const url = '/reference/holidaycalendarservice/holidaycalendar';
        return this.http.post(url, JSON.stringify(holidayData))
            //  { headers: headers })
            .map((res: Response) => {
                if (res) {
                    if (res.status === 201) {
                        return [{ status: res.status, json: res }]
                    } else if (res.status === 200) {
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

    deleteHolidayCalendarById(param) {
        //  const headers = new Headers({ 'Content-Type': 'application/json' });
        const url = '/reference/holidaycalendarservice/holidaycalendar';   // alert("stringify param==>"+JSON.stringify(param));
        this.arrStringJSON = ' [ ' + JSON.stringify(param) + ']';
        console.log(this.arrStringJSON);
        return this.http.delete(url, new RequestOptions({
            //    headers: headers,
            body: this.arrStringJSON
        })).map((res: Response) => {
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
            if (error.status === 422) {
                // return Observable.throw(new Error(error.status));
                return Observable.throw(new Error(this.errorMessage = 'Cannot Delete..Holiday calendar is+'
                    + ' already associated as a parent to some other Holiday calendar'));
                // this.errorMessage="UOM Code Already exists.Please Choose any Other..")
            } else if (error.status === 400) {
                return Observable.throw(new Error(error.status));
            } else if (error.status === 409 || error.status === 404) {
                return Observable.throw(new Error(this.errorMessage = 'calendar Code Already exists.Please Choose any Other..'));
            } else if (error.status === 406) {
                return Observable.throw(new Error(error.status));
            }
        });
    }

    updateStatusInView(param, status) {
       const url = '/reference/holidaycalendarservice/holidaycalendar';
        this.arrStringJSON = ' [ ' + JSON.stringify(param) + ']';
        return this.http.delete(url, new RequestOptions({
            //    headers: headers,
            body: this.arrStringJSON
        })).map((res: Response) => {
            if (res) {
                if (res.status === 201) {
                    return [{ status: res.status, json: res }]
                } else if (res.status === 200) {
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

    // for edit Holiday Cal
    updateHolidayCal(param): Observable<ResponseData> {
        const url = '/reference/holidaycalendarservice/holidaycalendar/';
        this.arrStringJSON = '[' + JSON.stringify(param) + ']';
        console.log('passing JSON in db' + this.arrStringJSON);
        return this.http.put(url, this.arrStringJSON)
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

    setHolidayName(holidayName: HolidayListDto) {
        this.editcode = holidayName;

    }

}
