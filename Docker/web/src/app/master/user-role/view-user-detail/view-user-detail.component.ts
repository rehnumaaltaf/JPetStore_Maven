import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Router } from '@angular/router';
import { UserRoleService } from '../service/user-role.service';
import { Module1 } from './../moduleunit';
import { Module2 } from './../moduleunit';
import { Module } from './../module';
import { adduserrole } from '../../../shared/interface/router-links';
import { UserRoleData } from '../user-role-data';
import { UserRoleInterface } from '../user-role.interface';
import { Subscription } from 'rxjs/Subscription';
import { listuserrole } from '../../../shared/interface/router-links';
@Component({
  selector: 'app-view-user-detail',
  templateUrl: './view-user-detail.component.html',
  styleUrls: ['./view-user-detail.component.css']
})

export class ViewUserDetailComponent implements OnInit {

  public userDetails;
   public draft;
  public active;
  public isError;
  deletebyid:boolean;
  public id;
  isDeletePopupModal: boolean;
  isCannotDeletePopup:boolean;
  public del_id;
   public errorMessage;
  deleteSuccessModal: boolean;
  public savedData;
  public roleName;
  public features;
  public roleDesc;
  subscription: Subscription;
   isDraftStatus:boolean;
  isActiveStatus:boolean;
  isShowModal:boolean;
  public featureValList=[];
  public featureList1=[];
  public moduleNameList=[];
  roleNameToShow: string;
   userRoleDataListToBeEdited: UserRoleData = new UserRoleData();
  isComplete: Boolean = false;
  public inactive;
  public list=[Object];
  statusMsg : any;
   status_change:boolean;
   public map1: Map<string, Object> = new Map<string, Object>();
   userRoleDataValueToSave: UserRoleData = new UserRoleData();
   errorMessageForUser: string;
  public status: any = {
isFirstOpen: true
// isFirstDisabled: false
}; 
  constructor(private route:ActivatedRoute,http:Http,private router:Router,private userRoleService: UserRoleService) { }


  ngOnInit() {
    window.scrollTo(0, 0);
     this.deletebyid=false;
      this.status_change=false;
      this.isCannotDeletePopup = false;
     
      this.userDetails = this.userRoleService.viewbyIdParam;
     this.subscription=this.userRoleService.getSelectedFeatues(this.userDetails.roleId).subscribe(deletedStatus => {
                           this.map1 = deletedStatus.body.featureMap;
                           let i;
                           
                           
                           let k;
                           let z;
                          // alert("=featureMap==>"+JSON.stringify(deletedStatus.body.featureMap));
                           //console.log(JSON.stringify(deletedStatus.body.featureMap['feature']));
                           for(let arrVal in deletedStatus.body.featureMap)
                           {

                               
                           if (deletedStatus.body.featureMap.hasOwnProperty(arrVal)) {
                                      let featureVal = JSON.stringify(deletedStatus.body.featureMap[arrVal].feature.value);
                                      let moduleName = JSON.stringify(deletedStatus.body.featureMap[arrVal].module.value);
                                      this.featureValList.push(featureVal);
                                      this.moduleNameList.push(moduleName);
                                     
                                }
                              
                           }
                           
                            for(let i=0;i < this.featureValList.length;i++){
                                  this.featureList1[i]=this.featureValList[i].replace("\"","");
                                   
                                }
                                for(let j=0;j < this.featureList1.length;j++){
                                  this.featureList1[j]=this.featureList1[j].replace("\"","");
                                   
                                }
                             for(let i=0;i < this.moduleNameList.length;i++){
                                  this.moduleNameList[i]=this.moduleNameList[i].replace("\"","");
                                   
                                }
                                for(let j=0;j < this.featureList1.length;j++){
                                  this.moduleNameList[j]=this.moduleNameList[j].replace("\"","");
                                   
                                }
                           
     },
      error => alert(error),
      () => console.log('Finished')
    );
     //alert(JSON.stringify(this.userRoleService.viewbyIdParam));
      if(this.userDetails.roleStatusName==="Draft"){
        this.draft=true;
        this.active=false;
        this.inactive=false;
      }else if(this.userDetails.roleStatusName==="Active"){
        this.active=true;
        this.draft=false;
        this.inactive=false;
      }else if(this.userDetails.roleStatusName==="InActive"){
        this.inactive=true;
        this.active=false;
        this.draft=false;
  }
}

backtoPrev1(){
  
  this.router.navigate([listuserrole]);
}

 editPage(roleId,role) {
    this.subscription = this.userRoleService.getSelectedFeatues(roleId).subscribe(userRole => {
    this.userRoleService.selectedFeatureToedit(userRole.body.featureMap);
    this.userRoleService.editUserRoleFeatures(role);
      this.router.navigate([adduserrole], { queryParams: { 'elem': 6 } });
    },
      error => alert(error),
      () => console.log('Finished')
    );
 }
 saveAndSubmitEdit() {
  //this.userRoleDataValueToSave.statusName  = 'Active';
   this.userDetails.statusName = 'Active';
  this.userDetails.roleId = '';
 // alert('submit'+JSON.stringify(this.userDetails))
  this.subscription = this.userRoleService.updateRole(this.userDetails).subscribe(userRole => {
      this.userRoleService.userRole12.push(userRole);
      this.router.navigate([listuserrole], { queryParams: { 'success': 9 } });
    },
      error => {
      this.errorMessageForUser = this.userRoleService.errorMessage;
      this.isError = 'true';
      });
  }
user_delete(event, roleName) {
   this.del_id = event;
   this.roleName = roleName;
   this.userRoleService.roleNameToShow =roleName;
   this.statusMsg = "Delete";
   this.status = "Draft";
   this.isDeletePopupModal = true;
   this.isDraftStatus = true;
   
 }
 closedeletepopup() {
   this.isDeletePopupModal = false;
   this.isCannotDeletePopup = false;
 }
 conf_delete(id) {
     const param = {'id': id};
     this.id=id;
      this.roleNameToShow = this.userRoleService.roleNameToShow;
     const paramList = [];
     paramList.push(param);
     const paramobj ={ 'userRoleId' : []};
     paramobj.userRoleId = paramList;
    this.subscription = this.userRoleService.deleteUserRole(this.del_id, this.status).subscribe(deletedStatus => {
                           this.savedData = deletedStatus;
                           if (this.status === 'Draft') {
                            this.isDraftStatus = true;
                          }else if (this.status === 'Active') {
                            this.isActiveStatus = true;
                          }
                           this.router.navigate([listuserrole] ,{ queryParams: { 'success': 9 }} );

       },
         error => {
            this.errorMessage = error;
            
            this.isDeletePopupModal = false;
            this.isCannotDeletePopup = true;
        },
         () => console.log('Finished')
    );
}
confirm(){

   this.status_change=true;
     //alert("===statusNamestatusName===>"+this.uomdata.statusName);
     if(this.userDetails.roleStatusName=='Active'){
            //  alert(1);
              this.statusMsg = "InActivate";
              this.userDetails.roleStatusName =  "InActive";
              this.status = 'Active';
        }else if(this.userDetails.roleStatusName=='InActive'){
              //alert(2);
              this.statusMsg = "Activate";  
              this.userDetails.roleStatusName =  "Active";
              this.status = 'InActive';
        }else if(this.userDetails.roleStatusName=='Draft'){
              // alert(3);
              this.statusMsg = "Activate";
              this.userDetails.roleStatusName=  "Active";
              this.status = 'Draft';
        }
        console.log(this.status);
        
}
updateUserRole(statusName) {
    if (statusName === 'InActive') {
            this.userDetails.statusName  = 'InActive';
     
     } else if (statusName === 'Active') {
           this.userDetails.statusName  = 'Active';
        }
     
  this.userDetails.roleId = '';
  console.log('Requestbody' + JSON.stringify( this.userDetails))
     this.subscription = this.userRoleService.updateRole( this.userDetails).subscribe(savedwithStatus => {
     this.router.navigate([listuserrole], { queryParams: { 'success': 9 } });
      },
      error => alert(error),
      () => console.log('Finished')
    );
  }
conf_status_change(event){
  
   this.isShowModal = true;
   setTimeout(() => {this.isShowModal = false; }, 3000);
  this.router.navigate([listuserrole]);
  /** 
  this.subscription = this.userRoleService.updateRoleDetails(this.userDetails).subscribe(updateData => {
              this.savedData = updateData;
              this.isShowModal = true;
              setTimeout(() => {this.isShowModal = false; }, 3000);
              this.router.navigate(['master/viewuserrole']);
              

         }, error => {
               //alert(this.unitMeasurementService.errorMessage);
               //console.log(this.isCannotDeletePopup);
               //this.status_change=false;
               this.errorMessage = error;
               this.isCannotDeletePopup = true;
               
             },
            () => console.log('Finished')

            );*/
   }
}

