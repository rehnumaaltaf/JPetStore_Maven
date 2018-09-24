import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { ResponseData } from '../../../../shared/interface/responseData';
import { Observable } from 'rxjs/Observable';
import { Link } from '../../../../shared/interface/link';
import 'rxjs/add/operator/map';
import { AbstractLinkService } from '../../../../shared/service/abstract-link.service';
import { MasterSetupService } from '../../../master-setup/service/master-setup.service';
import { noLoading } from '../../../../shared/interface/router-links';
import { WareHouseMatrix } from '../model/cost-warehouse';

@Injectable()
export class WarehouseCostMatrixService extends AbstractLinkService {
  public wareHouseDetails: WareHouseMatrix[] = [];
  arrStringJSON: JSON;
  errorMessage: String;
  wareHouseData: any = {};
  wareHouseMatrix: WareHouseMatrix;
  constructor(private http: Http, private masterService: MasterSetupService ) {
    super();
  }

}
