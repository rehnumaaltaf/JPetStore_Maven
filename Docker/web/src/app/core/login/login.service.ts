import {Injectable} from '@angular/core';
import {Http, Response, Headers, RequestOptions} from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { environment } from '../../../environments/environment';
import 'rxjs/add/operator/map';
import { Link } from '../../shared/interface/link';
import { ResponseData } from '../../shared/interface/responseData';


@Injectable()
export class LoginService {
    public tabChildObjects: Map<string, Link> = new Map<string, Link>();
    isLoggedIn: Boolean = false;

    constructor (private _http: Http) {
    }

   getData(param1: string, param2: string): Observable<ResponseData> {
      const url = 'https://api.myjson.com/bins/uig0r'; // "../../mock/login/mock-login.json";
        return this._http.get(url).map((response: Response) => response.json());
   }

   addChildObjects(links: Link[]) {
        for (const childData of links) {
            this.tabChildObjects.set(childData.rel + '.' + childData.method, childData);
        }
    }

    postJSON(param) {
        // const headers = new Headers({'Content-Type': 'application/json'});
        const url: string = '/masterService/authenticate';
        return this._http.post(url,JSON.stringify(param))// ,{ headers: headers })
        // .map((res: Response) => res.json());
        .map((res) => {return res.json();})
      // map((res)=>{return this.responseService.extractData(res);})
    }

    getLocale() {
        const url = 'https://api.myjson.com/bins/tgbsr'; // "../../mock/login/mock-login.json";
        return this._http.get(url).map((response: Response) => response.json());
    }
}
