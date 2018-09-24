import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { ResponseData } from '../../../shared/interface/responseData';
import { Observable } from 'rxjs/Observable';
import { Subject } from 'rxjs/Subject';
import 'rxjs/add/operator/map';
import { PermissionGroup } from './../permission-group-interface';
import { PermissionGroupRoleMapping } from './../permission-group-interface';
import { environment } from '../../../../environments/environment';
import {Unit } from '../unit';
import { SelectItem } from '../../../shared/interface/selectItem';
import {RoleFeatures } from '../role-features';
import { UserRoleService } from './../../user-role/service/user-role.service';
@Injectable()
export class PermissionGroupService {
    permissionGroupDetails: PermissionGroup[];
    permissionGroup: PermissionGroup;
    permissionRoleDetails: PermissionGroup[];
    permissionCompanyDetails: PermissionGroup[];
    roleFeatures: RoleFeatures[];
    public req_permisiongrprole: boolean;
    private subject = new Subject<any>();
    errorMessage: any;
    public permissionGroupName: string;
    arrStringJSON: any;
    public units: Unit[];
    public selectunit: SelectItem[];
    public companies: Unit[];
    public   roles: Unit [];
    public  products: Unit[];
    public portfolios: Unit[];
    paramaction: string;
    req_permisionrole: boolean;
    req_permisionunit: boolean;
    req_permisionrparty: boolean;
    editcode: PermissionGroupRoleMapping;
    req_permisionproduct: boolean;
    req_permisionportfolio: boolean;
    addoreditpageflag: boolean;
    constructor(private http: Http , private userroleserve: UserRoleService) {
 }
// get All drop Down list via RESTAPI
getPermissionDropDownJSON(): Observable<ResponseData> {
     const url = '/authorization/permissionmapping/permissions/getDropBox';
     return this.http.get(url)
    .map((response: Response) => <ResponseData>response.json())
    .catch((error: any) => Observable.throw(error.json().error || 'Server error')) ;
  }
// get permission Group Record by id
getPermissionGroupById(id): Observable<ResponseData> {
const url: string =  '/authorization/permissionmapping/permissions/' + id;
     return this.http.get(url)        .map((response: Response) => <ResponseData>response.json())
       .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
}

// get permission Group list via RESTAPI
getPermissionGroupViewList(): Observable<ResponseData>  {
    const url = '/authorization/permissionmapping/permissions';
    return this.http.get(url)
    .map(res =>  res.json()) .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
 }
addPermission(permissionGroupDetails: PermissionGroup[]) {
    this.permissionGroupDetails = permissionGroupDetails;
   }
// Assigning permissin group to object
addPermissionRecord(permissionGroup: PermissionGroup) {
    this.permissionGroup = permissionGroup; }

// Assigning role details to array of object
addRole(permissionRoleDetails: PermissionGroup[]) {
  this.permissionRoleDetails = permissionRoleDetails;
}

addCompany(permissionCompanyDetails: PermissionGroup[]) {
  this.permissionCompanyDetails = permissionCompanyDetails;
}

// save, submit , update the data based on status and add or edit page parameter then send to rest api
savePermissionGroup(param, status , addoredit) {

    console.log(JSON.stringify(param));

   this.permissionGroupName = param.permissionGroupName;
   this.addoreditpageflag = addoredit;
   const headers = new Headers({ 'Content-Type': 'application/json' });
const url = '/authorization/permissionmapping/permissions' ;
    if (status === 'InActive' || status === 'Active' ) {
        status = '';
    }  else  if (status === 'draft' || status === 'save' ) {
           status = '?action=' + status
   }
   console.log('In service save permission group');

   if (addoredit ) {
           return this.http.post(url + status ,      JSON.stringify(param))
    //  { headers: headers })
      .map((res) => {
            if (res) {
            if (res.status === 201) {
                return [{ status: res.status, json: res }]
            }else if (res.status === 200) {
                return [{ status: res.status, json: res }]
            }
          }
        return res.json();
      }).catch((error: any) => {
                if (error.status === 500) {
                    return Observable.throw(new Error(error.status));
                }else if (error.status === 400) {
                    return Observable.throw(new Error(error.status));
                }else if (error.status === 409)  {
                   return Observable.throw(new Error(this.errorMessage = 'Permission Group Already exists.Please Choose any Other..'));
                }else if (error.status === 406) {
                    return Observable.throw(new Error(error.status));
                }else if (error.status === 404) {
                    return Observable.throw(new Error(error.status));
                }
      });
   }else if ( addoredit === false) {
           return this.http.put(url + status ,
      JSON.stringify(param))
   //   { headers: headers })
      .map((res) => {
           if (res) {
            if (res.status === 201) {
                return [{ status: res.status, json: res }]
            }else if (res.status === 200) {
                return [{ status: res.status, json: res }]
            }
          }
        return res.json();
      }).catch((error: any) => {

            if (error.status === 500) {
                return Observable.throw(new Error(error.status));
            }else if (error.status === 400) {
                return Observable.throw(new Error(error.status));
            }else if (error.status === 409)  {
                return Observable.throw(new Error(this.errorMessage = 'Permission Group Already exists.Please Choose any Other..'));
            }else if (error.status === 406) {
                return Observable.throw(new Error(error.status));
            }else if (error.status === 404) {
                return Observable.throw(new Error(error.status));
            } else  {
                return Observable.throw(new Error(this.errorMessage = error.status + ' Please Check the Administration' ));
            }
      });
   }
}

// Get user role features based on role selection
getRoleFeatures(param): Observable<ResponseData> {
     const url: string = '/authorization/permissionmapping/permissions/getPopup/' + param;
     return this.http.get(url  )
    .map((response: Response) => <ResponseData>response.json())
      .catch((error: any) => Observable.throw(error.json().error));
  }


// delete permission group based on group id with status draft
deleteGroupById(paramobj, status) {

  
    const headers = new Headers({ 'Content-Type': 'application/json' });
    this.paramaction = paramobj +  '?action=' + status  ;
    const url: string = '/authorization/permissionmapping/permissions/' + this.paramaction;
    this.arrStringJSON =  JSON.stringify(paramobj) ;
    return this.http.delete(url, new RequestOptions({
                     // headers: headers,
                    body: this.arrStringJSON
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
                if (error.status === 422) {
                    return Observable.throw(new Error(error.status));
                }else if (error.status === 400) {
                    return Observable.throw(new Error(error.status));
                }else if (error.status === 409 || error.status === 404) {
                   return Observable.throw(new Error(error.status));
                }else if (error.status === 406) {
                    return Observable.throw(new Error(error.status));
                }else if (error.status === 500) {
                    return Observable.throw(new Error(error.status));
                }
     });
   }

   setRoleCode(roleCode: PermissionGroupRoleMapping) {
       this.editcode = roleCode;

  }

  setUnit(unitcode: boolean) {
      this.req_permisionunit = unitcode;
  }
  getUnit() {
      return this.req_permisionunit;
  }

  redirectUserRoleView( param)
  {
      this.userroleserve.viewbyIdParam = param;
  }

}
