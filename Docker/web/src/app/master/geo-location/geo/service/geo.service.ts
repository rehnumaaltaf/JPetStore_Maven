import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { ResponseData } from '../../../../shared/interface/responseData';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import { Geography } from '../geography';
import { environment } from '../../../../../environments/environment';
import { Link } from '../../../../shared/interface/link';
import { AbstractLinkService } from '../../../../shared/service/abstract-link.service';
import { MasterSetupService } from '../../../master-setup/service/master-setup.service';
import { noLoading } from '../../../../shared/interface/router-links';


@Injectable()
export class GeoService extends AbstractLinkService {
    geoDetails: Geography[];
    body: string;
    arrStringJSON: any;
    errorMessage: any;
    viewbyIdParam: any;
    errorStatusCode: any;
    geoCodeParam: any;
    geoCode: any;
    geoName: string;
    geoaction: string;
    status: string;
    addoreditpageflag: boolean;
    geotypecode: string;
      geoCodeList: string[] = [];
          constructor(private http: Http, public masterService: MasterSetupService) {
        super();
    }
    getListingPageFromJson() {
        //const url = 'https://api.myjson.com/bins/tu5hp';
        //const url ='https://api.myjson.com/bins/1g1fet';
        const url = '/location/geoservice/geo';
        return this.http.get(url).map((response: Response) => response.json());
    }
    getGeoDropDownJSON() {
        const url = '/location/geotypeservice/geotype';
        return this.http.get(url).map((response: Response) => <ResponseData>response.json());

    }

    getMarketDeskDropDownJSON() {
        const url = '/reference/marketDesk';
        return this.http.get(url).map((response: Response) => <ResponseData>response.json());
    }

    //   getselectedGeo(pkGeoId):  Observable<ResponseData> {
    //             return this.http.get('/location/geoservice/geo/' + pkGeoId).map((response:  Response) => <ResponseData>response.json()).catch((error:  any) => Observable.throw(error.json().error || 'Server error'));
    //         }
    getselectedGeo(pkGeoId): Observable<ResponseData> {
        // const url: string = environment.path + '/rolemap/features/' + basetermId;
        return this.http.get('/location/geoservice/geo/' + pkGeoId).map((response: Response) => <ResponseData>response.json()).catch((error: any) => Observable.throw(error.json().error || 'Server error'));

    }
      geoCodeSuggestion(param): Observable<ResponseData> {
          
             // const link: Link = this.masterService.getChildObject('View Uom.GET');
    // const url = link.href;
    const url = noLoading + '/location/geoservice/geo?geoCode=' + param;
    console.log(url);
    return this.http.get(url).map((response: Response) =>  <ResponseData> response.json());
     // return this.http.get(url).map((response: Response) => <ResponseData>response.json());
  }

  geoNameSuggestion(param): Observable<ResponseData> {
    // const link: Link = this.masterService.getChildObject('View Uom.GET');
    // const url = link.href;
    const url = noLoading + '/location/geoservice/geo?geoName=' + param;
    return this.http.get(url).map((response: Response) =>  <ResponseData> response.json());
     // return this.http.get(url).map((response: Response) => <ResponseData>response.json());
  }
    saveGEODetails(geoData: Geography, addoredit) {
       // console.log(JSON.stringify(geoData));
        this.geoCode = geoData.geoName;
        this.addoreditpageflag = addoredit;
        // alert(this.geoCode)
        // const headers = new Headers({ 'Content-Type': 'application/json' });
        const url: string = '/location/geoservice/geo' /* + '?action=draft' */;
        return this.http.post(url,
            JSON.stringify(geoData),
        )
            .map((res: Response) => {
                if (res) {
                    if (res.status === 201) {
                        return [{ status: res.status, json: res }]
                    }
                    else if (res.status === 200) {
                        return [{ status: res.status, json: res }]
                    }
                }

                return res.json();
            }).catch((error: any) => {
                //return error;
                /*this.errorStatusCode=JSON.parse(error._body);
                if (error.status === 500) {
                    return Observable.throw(new Error(error.status));
                }
                else if (error.status === 400) {
                    return Observable.throw(new Error(error.status));
                }
                else if (error.status === 409 || error.status === 404) {
                    return Observable.throw(new Error(this.errorMessage = "Both GEO Code and GEO Name already exists.Please Choose any Other.."));
                } else if (error.status === 422) {
                    return Observable.throw(new Error(this.errorMessage = "GEO Code already exists.Please Choose any Other.."));
                } else if (error.status === 406) {
                    return Observable.throw(new Error(this.errorMessage = "GEO Name already exists.Please Choose any Other.."));
                }*/
                return this.errorHandling(error);

            });
    }
    deleteGroupById(geoobj, status) {
    const headers = new Headers({ 'Content-Type': 'application/json' });
    this.geoaction = geoobj +  '?action=' + status  ;
    const url: string = '/location/geoservice/geo/' + this.geoaction;
    this.arrStringJSON =  JSON.stringify(geoobj) ;
    return this.http.delete(url, new RequestOptions({
                     // headers: headers,
                   // body: this.arrStringJSON
       })) .map((res: Response) => {
            if (res) {
                if (res.status === 201) {
                    return [{ status: res.status, json: res }]
                }else if (res.status === 200) {
                    return [{ status: res.status, json: res }]
                }
            }
        return res.json();
      }).catch((error: any) => {
               //  this.errorStatusCode = error.status;
                /*if (error.status === 422) {
                    return Observable.throw(new Error(error.status));
                }else if (error.status === 400) {
                    return Observable.throw(new Error(error.status));
                }else if (error.status === 409 || error.status === 404) {
                   return Observable.throw(new Error(error.status));
                }else if (error.status === 406) {
                    return Observable.throw(new Error(error.status));
                }else if (error.status === 500) {
                    return Observable.throw(new Error(error.status));
                }*/
                return this.errorHandling(error);
     });
   }
    updateGeoDetails(geoData: Geography, addoredit) {
      //  console.log(JSON.stringify(geoData));
        this.geoCode = geoData.geoName;
        this.status = geoData.statusName;
        this.addoreditpageflag = addoredit;
        // alert(this.geoCode)
        // const headers = new Headers({ 'Content-Type': 'application/json' });
        const url: string = '/location/geoservice/geo' /* + '?action=draft' */;
        return this.http.put(url,
            JSON.stringify(geoData),
        )
            .map((res: Response) => {
                if (res) {
                    if (res.status === 201) {
                        return [{ status: res.status, json: res }]
                    }
                    else if (res.status === 200) {
                        return [{ status: res.status, json: res }]
                    }
                }

                return res.json();
            }).catch((error: any) => {
                /*this.errorStatusCode=JSON.parse(error._body);
                this.errorStatusCode=error.body;
                if (error.status === 500) {
                    return Observable.throw(new Error(error.status));
                }
                else if (error.status === 400) {
                    return Observable.throw(new Error(error.status));
                }
                else if (error.status === 409 || error.status === 404) {
                    return Observable.throw(new Error(this.errorMessage = "Both GEO Code and GEO Name already exists.Please Choose any Other.."));
                } else if (error.status === 422) {
                    return Observable.throw(new Error(this.errorMessage = "GEO Code already exists.Please Choose any Other.."));
                } else if (error.status === 406) {
                    return Observable.throw(new Error(this.errorMessage = "GEO Name already exists.Please Choose any Other.."));
                }*/
                return this.errorHandling(error);


            });
    }

      submitGEODetails(geoData: Geography) {
        this.geoCode = geoData.geoName;
        // const headers = new Headers({ 'Content-Type': 'application/json' });
        const url: string = '/location/geoservice/geo' /*+ '?action=save'*/;
        //console.log('Hi' + JSON.stringify(geoData));
        return this.http.post(url,
            JSON.stringify(geoData),
        )
            .map((res: Response) => {
                if (res) {
                    if (res.status === 201) {
                        return [{ status: res.status, json: res }]
                    }
                    else if (res.status === 200) {
                        return [{ status: res.status, json: res }]
                    }
                }
                return res.json();

            }).catch((error: any) => {
                /*this.errorStatusCode=JSON.parse(error._body);
                if (error.status === 500) {
                    return Observable.throw(new Error(error.status));
                }
                else if (error.status === 400) {
                    return Observable.throw(new Error(error.status));
                }
                else if (error.status === 409 || error.status === 404) {
                    return Observable.throw(new Error(this.errorMessage = "Both GEO Code and GEO Name already exists.Please Choose any Other.."));
                } else if (error.status === 422) {
                    return Observable.throw(new Error(this.errorMessage = "GEO Code already exists.Please Choose any Other.."));
                } else if (error.status === 406) {
                    return Observable.throw(new Error(this.errorMessage = "GEO Name already exists.Please Choose any Other.."));
                }*/
                return this.errorHandling(error);


            });
    }
    saveGeog(param, status, addoredit) {
       // console.log(JSON.stringify(param))
        this.geoCode = param.geoName;
        this.status = param.statusName;
        this.addoreditpageflag = addoredit;
        const headers = new Headers({ 'Content-Type': 'application/json' });
        const url = '/location/geoservice/geo';
        if (status === 'InActive' || status === 'Active') {
            status = '';
        } else if (status === 'draft' || status === 'save') {
            status = '?action=' + status
        }
      //  console.log(JSON.stringify(param) + status + addoredit + url);
        if (addoredit) {
            return this.http.post(url + status, param)

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
                }).catch((error: any) => {
                    /*if (error.status === 500) {
                        return Observable.throw(new Error(error.status));
                    } else if (error.status === 400) {
                        return Observable.throw(new Error(error.status));
                    } else if (error.status === 409) {
                        return Observable.throw(new Error(this.errorMessage = 'Payment Term Code Already exists.Please Choose any Other..'));
                    } else if (error.status === 406) {
                        return Observable.throw(new Error(error.status));
                    } else if (error.status === 404) {
                        return Observable.throw(new Error(error.status));
                    }*/
                     return this.errorHandling(error);
                });
        } else if (addoredit === false) {
            return this.http.put(url + status, param)
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

                    /*if (error.status === 500) {
                        return Observable.throw(new Error(error.status));
                    } else if (error.status === 400) {
                        return Observable.throw(new Error(error.status));
                    } else if (error.status === 409) {
                        return Observable.throw(new Error(this.errorMessage = 'Payment Term Code Already exists.Please Choose any Other..'));
                    } else if (error.status === 406) {
                        return Observable.throw(new Error(error.status));
                    } else if (error.status === 404) {
                        return Observable.throw(new Error(error.status));
                    }*/
                     return this.errorHandling(error);
                });
        }
    }
    saveGEO(param) {
        //const headers = new Headers({ 'Content-Type': 'application/json' });
        this.geoCode = param.geoName;
        this.status = param.statusName;
        const url = '/location/geoservice/geo';
        this.arrStringJSON = "[" + JSON.stringify(param) + "]";
        return this.http.put(url, this.arrStringJSON,
        )
            .map((res: Response) => {
                if (res) {
                    if (res.status === 201) {
                        return [{ status: res.status, json: res }]
                    }
                    else if (res.status === 200) {
                        return [{ status: res.status, json: res }]
                    }
                }

                return res.json();
            }).catch((error: any) => {
                /*this.errorStatusCode = error.body;
                if (error.status === 500) {
                    //TO DO: It should not be 500
                    // this needs to be removed once backend is handled for viewbyId response code for Duplicate UOM code 
                    return Observable.throw(this.errorMessage = "GEO Code Already exists.Please Choose any Other..");
                }
                else if (error.status === 400) {
                    return Observable.throw(new Error(error.status));
                }
                else if (error.status === 409 || error.status === 404) {
                    return Observable.throw(this.errorMessage = "GEO Code Already exists.Please Choose any Other..");
                }
                else if (error.status === 406) {
                    return Observable.throw(new Error(error.status));
                }*/
                 return this.errorHandling(error);


            });
    }

    deleteGEOById(param) {
        // const headers = new Headers({ 'Content-Type': 'application/json' });
        this.geoCode = this.geoName;
        const url = '/location/geoservice/geo';
        // alert("stringify param==>"+JSON.stringify(param));
        this.arrStringJSON = "[" + JSON.stringify(param) + "]";
        return this.http.delete(url, new RequestOptions({

            body: this.arrStringJSON
        })).map((res) => { return res.json(); })
    }




    public getMasterList(): Observable<ResponseData> {
        const url = '/location/geoservice/geo';
        return this.http.get(url).map((response: Response) => <ResponseData>response.json());
    }

    getMarketDeskBasedOnGeoType(id): Observable<ResponseData> {
        if (id != null) {
            const url = '/location/geoservice/geo' + id;
            return this.http.get(url)
                .map((response: Response) => <ResponseData>response.json())
                .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
        }
    }

}
