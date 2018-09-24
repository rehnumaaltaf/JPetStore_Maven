import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';

import { ResponseData } from '../../../shared/interface/responseData';
import { environment } from '../../../../environments/environment';
import { AbstractLinkService } from '../../../shared/service/abstract-link.service';
import { Link } from '../../../shared/interface/link';

@Injectable()
export class MasterSetupService extends AbstractLinkService {

  constructor(private http: Http) {
    super();
  }

  public getMasterList(): Observable<ResponseData> {
    const url = '/masterService';
    return this.http.get(url).map((response: Response) => {
      console.log(response.json())
      return <ResponseData>response.json();
    }) 
  }

}
