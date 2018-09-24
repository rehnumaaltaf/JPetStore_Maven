import { Injectable } from '@angular/core';

import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { ResponseData } from '../../../../shared/interface/responseData';
import { Observable } from 'rxjs/Observable';
import { Link } from '../../../../shared/interface/link';
import 'rxjs/add/operator/map';
import { FinancialCalendar } from '../model/financial-calendar';
import { ForwardTenor } from '../model/financial-calendar';
import { environment } from '../../../../../environments/environment';
import { AbstractLinkService } from '../../../../shared/service/abstract-link.service';
import { MasterSetupService } from '../../../master-setup/service/master-setup.service';
import { noLoading } from '../../../../shared/interface/router-links';
import { ProductMapping } from '../model/financial-calendar';

@Injectable()
export class FinancialCalendarService extends AbstractLinkService {
    public financialDetails: FinancialCalendar[];
    arrStringJSON: JSON;
    errorMessage: String;
    financialData: any = {};
    fiscalyearData: string[] = [];
    financialDetail: FinancialCalendar;
    public productMappingList: ProductMapping[] = [];
    public req_monthShortCode = [];
    public req_productId = [];
    public req_startDate = [];
    public req_endDate = [];
    public req_ctrmStatus = [];
    public req_erpStatus = [];
    // financialid: string;
    constructor(private http: Http, private masterService: MasterSetupService) {
        super();
    }

    addFinancedetails(newFinanceDetails: FinancialCalendar[]) {

        this.financialDetails = newFinanceDetails;
    }

    getFinancialCalendarDetailsJSON(param) {
        // const link: Link = this.masterService.getChildObject('View Uom.GET');
        const url = '/reference/referenceService/financialCalender';
        // const url: string = environment.path + '/financialCalender/viewFinCal';
        return this.http.get(url).map((response: Response) => <ResponseData>response.json());

    }

    saveFinancialCalDetails(financialCalData: FinancialCalendar) {
        //  const headers = new Headers({ 'Content-Type': 'application/json' });
        const url = '/reference/referenceService/financialCalender';
        // const url: string = environment.path + '/financialCalender/addFinCal';

        // console.log('URL>>>' + url);
        console.log('PASSING DATA>>' + financialCalData);
        return this.http.post(url,
            JSON.stringify(financialCalData),
            {})
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
                if (error.status === 500) {
                    return Observable.throw(new Error(error.status));
                } else if (error.status === 400) {
                    return Observable.throw(new Error(error.status));
                } else if (error.status === 409 || error.status === 404) {
                    return Observable.throw(new Error(this.errorMessage = 'Already exists.Please Choose any Other..'));
                } else if (error.status === 406) {
                    return Observable.throw(new Error(error.status));
                }


            });
    }

    /*to update the selected financial calendar*/
    updateFinancialCalDetails(financialCalData: FinancialCalendar) {
        const headers = new Headers({ 'Content-Type': 'application/json' });
        // http://10.87.100.247:8080/financialCalender/editFinCal

        const url = '/reference/referenceService/financialCalender';
        // const url: string = environment.path + '/financialCalender/editFinCal';

        // console.log('URL>>>' + url);
        console.log('PASSING DATA>>' + JSON.stringify(financialCalData));
        return this.http.put(url , JSON.stringify(financialCalData)) .map((res: Response) => {
                  return res.json() ;

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
        // return this.http.put(url,
        //     JSON.stringify(financialCalData),
        //     // headers: headers
        //     {})
        //     .map((res: Response) => {
        //         if (res) {
        //             if (res.status === 201) {
        //                 return [{ status: res.status, json: res }]
        //             } else if (res.status === 200) {
        //                 return [{ status: res.status, json: res }]
        //             }
        //         }

        //         return res.json();
        //     }).catch((error: any) => {
        //         if (error.status === 500) {
        //             return Observable.throw(new Error(error.status));
        //         } else if (error.status === 400) {
        //             return Observable.throw(new Error(error.status));
        //         } else if (error.status === 409 || error.status === 404) {
        //             return Observable.throw(new Error(this.errorMessage = 'Already exists.Please Choose any Other..'));
        //         } else if (error.status === 406) {
        //             return Observable.throw(new Error(error.status));
        //         }


        //     });
    }


    uniqueFiscalYear(param) {

        // const headers = new Headers({ 'Content-Type': 'application/json' });
        const url = noLoading + '/reference/referenceService/financialCalender/validateFinCal/';
        //   const url = '/financialCalender/validateFinCal/';
        // const url: string = environment.path + '/financialCalender/validateFinCal/';
        return this.http.get(url + param).map((res: Response) => res.json())
            .do(data => console.log(data));
    }

    uniqueCalendarMonth(param) {
        /*  const headers = new Headers({ 'Content-Type': 'application/json' });
             const url = '/financialCalender/';
            const url: string = environment.path + '/financialCalender/';
           return this.http.get(url + param).map((res: Response) => res.json())
           .do(data => console.log(data)); */
    }

    getSearchResults(srchval: string, partUrl: string): Observable<string[]> {
        // const url: string = '/financialCalender/' + partUrl + '/';
         const url = noLoading + '/reference/referenceService/financialCalender/' + partUrl + '/';
        // const url: string = environment.path + '/financialCalender/' + partUrl + '/';
        return this.http.get
            (url + srchval)
            .map((response: Response) => <string[]>response.json())
            .do(data => console.log(data));
    }

    uniqueMonthName(fiscalyr, monthname) {
        // const url =  noLoading + '/financialCalender/validateFinCalMonth';
        const url = '/reference/referenceService/financialCalender/validateFinCalMonth';
        // const url: string = environment.path + '/financialCalender/validateFinCalMonth';
        return this.http.get(url + '/' + fiscalyr + '/' + monthname).map((res: Response) => res.json())
            .do(data => console.log(data));
    }


    setFiscalYear(fiscalYear: String) {
        this.financialData.fiscalYear = fiscalYear;
    }
    getFiscalYear() {
        return this.financialData.fiscalYear;
    }

    deleteFinancialCalendar(id, statusId) {
        const url = '/reference/referenceService/financialCalender/' + id;
        //  const url: string = environment.path + '/financialCalender/deleteFinCal/' + id ;
        return this.http.delete(url).map((response: Response) => <ResponseData>response.json());
    }

    deleteFinancialCalendarClone(id) {
        // http://10.87.100.247:8080/financialCalender/deleteCloning/122
        // console.log('from service   ' + id);
        // const url:  string = environment.path + '/financialCalender/deleteCloning/' + id;
        //  console.log(url);
        const url = '/reference/referenceService/financialCalender/deleteCloning/' + id;
        return this.http.delete(url).map((response: Response) => <ResponseData>response.json())
    }



    setFiscalYears(fiscalYear: String) {
        this.financialData.fiscalYear = fiscalYear;
    }
    getFiscalYears() {
        return this.financialData.fiscalYear;
    }

    setStatusname(statusname: String) {
        this.financialData.statusname = statusname;
    }
    getStatusname() {
        return this.financialData.statusname;
    }

    setFinacialPkId(financialid: String) {
        this.financialData.financialid = financialid;
    }
    getFinacialPkId() {
        return this.financialData.financialid;
    }

    setRowData(row: FinancialCalendar) {
        this.financialDetail = row;
    }

    getRowData() {
        return this.financialDetail;
    }

    getFinancialByIdJSON(id) {
        const url = '/reference/referenceService/financialCalender/' + id;
        return this.http.get(url).map((response: Response) => <ResponseData>response.json());
    }

    activeFinancialCalendar(id) {
        const url = '/reference/referenceService/financialCalender/' + id;
        return this.http.put(url, id).map((response: Response) => <ResponseData>response.json());
    }

    getProductList(url) {
        url = '/product/productservice/product/basicdetails';
        return this.http.get(url).map((response: Response) => <ResponseData>response.json());
    }

}
