import { Injectable } from '@angular/core';
import { Module1 } from './../moduleunit';
import { Module } from './../module';
import { UserRoleData } from '../user-role-data';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import { environment } from '../../../../environments/environment';
import { Http, Response, Headers, RequestOptions,URLSearchParams  } from '@angular/http';
import { ResponseData } from '../../../shared/interface/responseData';
import { UserRoleInterface } from '../user-role.interface';

import { Link } from '../../../shared/interface/link';
import { AbstractLinkService } from '../../../shared/service/abstract-link.service';
import { MasterSetupService } from '../../master-setup/service/master-setup.service';
import { DropDown } from '../dropdownlist'; 

@Injectable()
export class UserRoleService {
  userRoleDetails: UserRoleInterface[];
  userRole12: DropDown[];
  featureList:DropDown[];
  popUpList:DropDown[];
  autoSearchValue:DropDown[];
  editUser: UserRoleData;
  arrStringJSON: any;
  errorMessage : any;
  
  viewbyIdParam: any;
  roleNameToShow: string;
  editFeatures: UserRoleData;
  
  editFeaturesMultiple: UserRoleData;
  editSelectedFeatures: UserRoleData[];
  saveData:UserRoleData[];
  featuresList : UserRoleData[];
  selectedFeature : UserRoleData;
  public unit1: Module[];
  constructor(private http: Http, public masterService: MasterSetupService) {
  }
  public module = [
      new Module(1, 'Olam Brazil' ),
      new Module(2, 'Olam Italy' ),
      new Module(3, 'Olam Sigapure' ),
      new Module(4, 'Olam India')
  ];
  public   submodule = [
     new Module(1, 'Admin' ),
     new Module(2, 'Create' ),
     new Module(3, 'View' ),
    ];

   public features = [
     new Module(1, 'Olam1' ),
     new Module(2, 'Olam2' ),
     new Module(3, 'Olam3' ),
     new Module(4, 'Olam4')
  ];

   public getModules(): Module[] {

     this.unit1 = Module1;
       return Module1;
  }
 
getFeaturePickListValue() {
    const url = '/authorization/rolemap/features/getDropBox';
    return this.http.get(url)
        .map((response:Response) => response.json());

}


getSearchResults(srchval: string, partUrl: string) {
    const url: string = '/authorization/rolemap/features/autoSearchRoles/'+srchval;
   return this.http.get(url)
        .map((response:Response) => response.json());
}

getListingPageValues() {
     const url = '/authorization/rolemap/features/';
    return this.http.get(url)
        .map((response:Response) => response.json());

}
saveRoleDetails(userRoleDataList: UserRoleData) {
    this.roleNameToShow = userRoleDataList.roleName;
    const headers = new Headers({ 'Content-Type': 'application/json' });
    const url: string = '/authorization/rolemap/features/' + '?action=draft';
   // const url: string = 'http://localhost:8080/rolemap/features/'+ '?action=draft';
    return this.http.post(url,
      JSON.stringify(userRoleDataList))
      .map((res : Response) =>{
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
                if (error.status === 500) {
                    return Observable.throw(new Error(error.status));
                }
                else if (error.status === 400) {
                    return Observable.throw(new Error(error.status));
                }
                else if (error.status === 409 || error.status === 404) {
                   return Observable.throw(new Error(this.errorMessage="Role Name exists , please enter a different name"));
                }
                else if (error.status === 406) {
                    return Observable.throw(new Error(error.status));
                }

               
      });
}
updateRole(userRoleDataList: UserRoleData) {
     this.roleNameToShow = userRoleDataList.roleName;
    const headers = new Headers({ 'Content-Type': 'application/json' });
  //  const url: string = environment.path + '/rolemap/features/' + '?action=draft';
    const url: string = '/authorization/rolemap/features/';
    return this.http.put(url,
      JSON.stringify(userRoleDataList))
      .map((res : Response) =>{
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
                if (error.status === 500) {
                    return Observable.throw(new Error(error.status));
                }
                else if (error.status === 400) {
                    return Observable.throw(new Error(error.status));
                }
                else if (error.status === 409 || error.status === 404) {
                   return Observable.throw(new Error(this.errorMessage="Role Name exists , please enter a different name"));
                }
                else if (error.status === 406) {
                    return Observable.throw(new Error(error.status));
                }

               
      });
}
saveAndSubmitRoleDetails(userRoleDataList: UserRoleData) {

    this.roleNameToShow = userRoleDataList.roleName;
    const headers = new Headers({ 'Content-Type': 'application/json' });
   // const url: string = 'http://localhost:8080/rolemap/features/'+'?action=save';
    const url: string = '/authorization/rolemap/features/'+'?action=save';
    return this.http.post(url,
      JSON.stringify(userRoleDataList))
     .map((res : Response) =>{
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
                if (error.status === 500) {
                    return Observable.throw(new Error(error.status));
                }
                else if (error.status === 400) {
                    return Observable.throw(new Error(error.status));
                }
                else if (error.status === 409 || error.status === 404) {
                   return Observable.throw(new Error(this.errorMessage="Role Name exists , please enter a different name"));
                }
                else if (error.status === 406) {
                    return Observable.throw(new Error(error.status));
                }
      });
  }
  saveUSERROLE (userRoleDataList: UserRoleData ){
      
    this.roleNameToShow = userRoleDataList.roleName;
    const headers = new Headers({ 'Content-Type': 'application/json' });
   // const url: string = 'http://localhost:8080/rolemap/features/'+'?action=save';
    const url: string =  '/authorization/rolemap/features/'+'?action=save';
    return this.http.post(url,
      JSON.stringify(userRoleDataList))
     .map((res : Response) =>{
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
                if (error.status === 500) {
                    return Observable.throw(new Error(error.status));
                }
                else if (error.status === 400) {
                    return Observable.throw(new Error(error.status));
                }
                else if (error.status === 409 || error.status === 404) {
                   return Observable.throw(new Error(this.errorMessage="Role Name exists , please enter a different name"));
                }
                else if (error.status === 406) {
                    return Observable.throw(new Error(error.status));
                }
      });
  }
  deleteUserRole(permissionGroupId: any , status: any ) {
     // alert(permissionGroupId)
      const url: string = '/authorization/rolemap/features/'+permissionGroupId+ '?action='+status;
     return this.http.delete(url)
        .map((response:Response) => response.json());
  }
 
  /* deleteUserById(param){
    const headers = new Headers({ 'Content-Type': 'application/json' });
   // const url: string = '/uomservice/uom';
    //alert("stringify param==>"+JSON.stringify(param));
     const url: string = 'http://localhost:8080/rolemap/features';
    this.arrStringJSON = JSON.stringify(param);
    return this.http.delete(url,new RequestOptions({
                      headers: headers,
                      body: this.arrStringJSON
       })) .map((res : Response) =>{
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
                // this.errorStatusCode = error.status;
                if (error.status === 500) {
                     //return Observable.throw(new Error(error.status));
                     return Observable.throw(new Error(this.errorMessage="Cannot Delete..USER role is already associated"));
                     //this.errorMessage="UOM Code Already exists.Please Choose any Other..")
                }
                else if (error.status === 400) {
                    return Observable.throw(new Error(error.status));
                }
                else if (error.status === 409 || error.status === 404) {
                   return Observable.throw(new Error(this.errorMessage="."));
                }
                else if (error.status === 406) {
                    return Observable.throw(new Error(error.status));
                }

               
      });
   }*/

  updateRoleDetails(userRoleDataList: UserRoleData) {
    const headers = new Headers({ 'Content-Type': 'application/json' });
     const url: string = '/authorization/rolemap/features/';
    //const url: string = 'http://localhost:8080/rolemap/features/';
    // console.log('Hi' + JSON.stringify(uomData));
    return this.http.put(url,
      JSON.stringify(userRoleDataList))
      .map((res) => { return res.json(); })
  }
/*getSelectedFeatues(roleId: any) {
     // const url: string = environment.path + '/rolemap/features/'+roleId;
      const url: string = 'http://localhost:8080/rolemap/features/'+roleId;
     return this.http.get(url)
        .map((response:Response) => response.json());
  }*/
   getSelectedFeatues(roleId): Observable<ResponseData> {
   const url: string = '/authorization/rolemap/features/' + roleId;
   return this.http.get(url)
    .map((response: Response) => <ResponseData>response.json())
   .catch((error: any) => Observable.throw(error.json().error || 'Server error'));

  }
  
   getSelectedFeatureBasedOnMod(module: any) {
       const url: string = '/authorization/rolemap/features/getModuleFeatures/'+ module;
     return this.http.get(url)
        .map((response:Response) => response.json());
  }
  getPopUpValues(feature: any){
       const url: string = '/authorization/rolemap/features/getPopup/'+ feature;
  return this.http.get(url)
        .map((response:Response) => response.json());
  }
  editUserRole(userRoleData:UserRoleData)
{ this.editUser = userRoleData; } 
  editUserRoleFeatures(userRoleData2:UserRoleData)
{ 
  console.log("ddd" + JSON.stringify(userRoleData2))
  this.editFeatures = userRoleData2; 
} 
selectedFeatureToedit(userRoleData3:any){
// alert('from json' + JSON.stringify(userRoleData3))
 this.editSelectedFeatures = userRoleData3;
}
selectedFeatureToeditDropDown (userRoleData4:UserRoleData) {
 this.editFeaturesMultiple = userRoleData4;
}
}
