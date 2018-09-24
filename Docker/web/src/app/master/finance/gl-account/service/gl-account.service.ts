import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { ResponseData } from '../../../../shared/interface/responseData';
import { Observable } from 'rxjs/Observable';
import { Link } from '../../../../shared/interface/link';
import 'rxjs/add/operator/map';
import { GLAccount } from '../gl-account';
import { environment } from '../../../../../environments/environment';
import { AbstractLinkService } from '../../../../shared/service/abstract-link.service';
import { MasterSetupService } from '../../../master-setup/service/master-setup.service';
import { SelectItem } from '../../../../shared/interface/selectItem';
@Injectable()
export class GlAccountService {
    glDetails: GLAccount[];
    legalentityOption: SelectItem[] = [];
    glTypeOption: SelectItem[] = [];
    groupglOption: SelectItem[] = [];
    parentglOption: SelectItem[] = [];
    glTypeRefName: SelectItem[] = [];
    externalglOption: SelectItem[] = [];
    externalSystemRefName: SelectItem[] = [];
    glCode: SelectItem[] = [];
    arrStringJSON: any;
    errorMessage: any;
    errorStatusCode: any;
    editGlId: number; // for edit GL
    viewbyIdParam: any;
    flag: boolean;

    constructor(private http: Http, public masterService: MasterSetupService) {
        // super();

    }


    getDropdownListJSON(): Observable<ResponseData> {
        const url = 'https://api.myjson.com/bins/bnvsd';
        return this.http.get(url).map((response: Response) => <ResponseData>response.json());
    }

    getGLTypeDropdown(): Observable<ResponseData> {
        // const url = 'http://localhost:8089/glservice/gl/gltype';
        const url = '/finance/glservice/gl/gltype';
        return this.http.get(url).map((response: Response) => <ResponseData>response.json());
    }


    getLegalEntityDropdown(): Observable<ResponseData> {
        // const url: string = environment.path + '/partyservice/party';
        const url = '/party/partyservice/party';
        // http://localhost:8083/partyservice/party
        // const url: string = environment.path + '/glservice/gl/gltype'
        return this.http.get(url).map((response: Response) => <ResponseData>response.json());
    }


    getExternalSystemDropdown(): Observable<ResponseData> {
        // const url = 'http://localhost:8089/glservice/gl/gltype';
        // http://localhost:8089/glservice/gl/externalsystemref
        const url = '/finance/glservice/gl/externalsystemref'
        return this.http.get(url).map((response: Response) => <ResponseData>response.json());
    }

    getSingleGlListJSON(id: String) {
        const url = '/finance/glservice/gl/' + id;
        return this.http.get(url).map((response: Response) => <ResponseData>response.json());
    }

    getParentGlDropdown(): Observable<ResponseData> {
        // const url = 'http://localhost:8089/glservice/gl/gltype';
        const url = '/finance/glservice/gl'
        return this.http.get(url).map((response: Response) => <ResponseData>response.json());
    }

    getGLloadingJSON() {
        const url = '/finance/glservice/gl'
        return this.http.get(url).map((response: Response) => <ResponseData>response.json());
    }

    addGL(newglDetails: GLAccount[]) {
        this.glDetails = newglDetails;
    }

    saveglDetails(glData: GLAccount) {
        // const headers = new Headers({ 'Content-Type': 'application/json' });
        // const url: string = environment.path + '/glservice/gl' + '?action=draft';
        const url = '/finance/glservice/gl' + '?action=draft';
        return this.http.post(url, glData)
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
                this.errorMessage = error.json().message;
                //  alert("this.errorMessage"+error.json().message);
                //  alert("this.errorMessage"+this.errorMessage);
                if (error.status === 409) {
                    return Observable.throw(new Error(error.status));
                }
                // else if (error.status === 400 || (error.status === 404 )) {
                //     return Observable.throw(new Error(error.status));
                // }
                //  else if (error.status === 405) {
                //     return Observable.throw(new Error(this.errorMessage="GL Name already exists.Please Choose any Other.."));
                // }
                // else if (error.status === 406) {
                // tslint:disable-next-line:max-line-length
                //     return Observable.throw(new Error(this.errorMessage="Both GL Code and GL Name already exists.Please Choose any Other.."));
                // }else if (error.status === 409) {
                //     return Observable.throw(new Error(this.errorMessage="GL Code already exists.Please Choose any Other.."));
                // }
            });
    }

    submitGLDetails(glData: GLAccount) {
        // const headers = new Headers({ 'Content-Type': 'application/json' });
        const url = '/finance/glservice/gl' + '?action=save';
        return this.http.post(url, glData)
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
                } else if (error.status === 400 || (error.status === 404)) {
                    return Observable.throw(new Error(error.status));
                } else if (error.status === 405) {
                    return Observable.throw(new Error(this.errorMessage = 'GL Name already exists.Please Choose any Other..'));
                } else if (error.status === 406) {
                    return Observable.throw(new Error
                        (this.errorMessage = 'Both GL Code and GL Name already exists.Please Choose any Other..'));
                } else if (error.status === 409) {
                    return Observable.throw(new Error(this.errorMessage = 'GL Code already exists.Please Choose any Other..'));
                }


            });
    }

    deleteGlById(param) {
        // const headers = new Headers({ 'Content-Type': 'application/json' });
        const url = '/finance/glservice/gl';
        // alert("stringify param==>"+JSON.stringify(param));
        this.arrStringJSON = '[' + JSON.stringify(param) + ' ] ';
        return this.http.delete(url, new RequestOptions({
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
                return Observable.throw(new Error
                    (this.errorMessage = 'Cannot Delete..GL code is already associated as a parent to some other GL'));
                // this.errorMessage="UOM Code Already exists.Please Choose any Other..")
            } else if (error.status === 400) {
                return Observable.throw(new Error(error.status));
            } else if (error.status === 409 || error.status === 404) {
                return Observable.throw(new Error(this.errorMessage = 'GL Code Already exists.Please Choose any Other..'));
            } else if (error.status === 406) {
                return Observable.throw(new Error(error.status));
            } else if (error.status === 409 || error.status === 404) {
                return Observable.throw(new Error(this.errorMessage = 'GL Code Already exists.Please Choose any Other..'));
            } else if (error.status === 406) {
                return Observable.throw(new Error(error.status));
            }
        });
    }
    // for edit GL
    updateGL(param) {
       // const headers = new Headers({ 'Content-Type': 'application/json' });
        const url = '/finance/glservice/gl';
        this.arrStringJSON = '[' + JSON.stringify(param) + ']';
        console.log(this.arrStringJSON);
        return this.http.put(url, this.arrStringJSON )
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
                    return Observable.throw(this.errorMessage = 'GL Code Already exists.Please Choose any Other..');
                } else if (error.status === 400) {
                    return Observable.throw(new Error(error.status));
                } else if (error.status === 409 || error.status === 404) {
                    return Observable.throw(this.errorMessage = 'GL Code Already exists.Please Choose any Other..');
                } else if (error.status === 406) {
                    return Observable.throw(new Error(error.status));
                }
            });
    }


}
