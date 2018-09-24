import { Component, OnInit, OnDestroy, ViewEncapsulation,ViewChild } from '@angular/core';
import { colorcodeblue , colorcodered } from '../../../../shared/interface/colorcode';
import { Geography } from '../geography';
import { SelectItem } from '../../../../shared/interface/selectItem';
import { FormGroup , FormGroupName, FormControl, Validators, FormArray, FormBuilder,
              ReactiveFormsModule , FormsModule} from '@angular/forms';
import { GeoService } from '../service/geo.service';
import { Router , ActivatedRoute} from '@angular/router';
import { AccordionModule , ModalDirective } from 'ngx-bootstrap';
import { productgrade } from '../../../../shared/interface/router-links';
import { Subscription } from 'rxjs/Subscription';
//import { GeoTypecode } from './../geography';
import { geo } from '../../../../shared/interface/router-links';
import { TypeaheadMatch } from 'ngx-bootstrap/typeahead';
import { Observable } from 'rxjs/Observable';

@Component({
  selector: 'app-add-geo',
  templateUrl: './add-geo.component.html',
  styleUrls: ['./add-geo.component.css'],
  encapsulation:  ViewEncapsulation.None,

})
export class AddGeoComponent implements OnInit , OnDestroy {

   @ViewChild('deletesuccessModal') public deletesuccessModal: ModalDirective;
   @ViewChild('successModal') public successModal: ModalDirective;
  // @ViewChild('deletesuccessModals') public deletesuccessModals: ModalDirective;
   errormodals:Boolean=false;
  req_geocode: boolean;
  req_geoname:boolean;
  req_geotype:boolean;
  marketdesk: boolean;
  isCannotDeletePopup: boolean;
  req_marketdesk:boolean;
 pagetitle: string;
  editpage: boolean;
  addpage: boolean;
  isError: boolean;
  ispagebackPopupModal:boolean;
  addoreditpage: boolean;
  editGeoId: Number;
 // errorMessageForPaymentterm: string;
  sumbitstatus: string;
  editPermissionId: Number;
  counter: number;
  reqmsg1: string;
  reqmsg2 : String;
  //geoCodeArray: string[];
  geoCodeList: string[] = [];
  geoNameList: string[] = [];
   minTypeaheadLength = 3;
  subscription: Subscription;
  geoDetails: Geography[];
  marketdata:Geography[];
  isActionPerformed = false;
   parentGeolist: SelectItem[];
 errormodal: boolean;
    draft: boolean;
   active: boolean ;
    inactive: boolean;
    isEdit: boolean;
    editMode: boolean;
    errorMsg:string;
    errormodal1: boolean;
    geoDetailsToUpadte:Geography = new Geography();
  errorMessageForPaymentterm: string;
  marketdesklist: SelectItem[];
  geo: Geography = new Geography();
  //geotypedata: GeoTypecode [] = [];
   pkGeoLevelId: SelectItem [] = [];
   public geo_validate: any;
  public geocountry:string;
  public geoparent :string;
  public geoParentName:string;
  isBeforeEdit: boolean;
   public status: any = {
    isFirstOpen: true
    // isFirstDisabled: false
  };
   public asyncSelected: string;
  public typeaheadLoading: boolean;
  public typeaheadNoResults: boolean;
  public dataSource: Observable<any>;
  geoerror = [];
  geotypeahead=[];
 //public asyncSelected: string;
  //public typeaheadLoading: boolean;
  //public typeaheadNoResults: boolean;
 // public dataSource: Observable<any>;

 constructor(private fb: FormBuilder , private geoService: GeoService , private router: Router,
        private route: ActivatedRoute  ) { 

          /*this.dataSource = Observable
      .create((observer: any) => {
        // Runs on every search
        observer.next(this.asyncSelected);
      })
      .mergeMap((token: string) => this.getStatesAsObservable(token));*/

          }

          /*public getStatesAsObservable(token: string): Observable<any> {
    let query = new RegExp(token, 'ig');
 
    return Observable.of(
      this.geoCodeArray.filter((state: any) => {
        //debugger;
        return query.test(state.geoName);
      })
    );
  }*/

   public changeTypeaheadLoading(e: boolean): void {
    this.typeaheadLoading = e;
  }
 
  public changeTypeaheadNoResults(e: boolean): void {
    this.typeaheadNoResults = e;
  }
 
  public typeaheadOnSelect(e: TypeaheadMatch): void {
    console.log('Selected value: ', e.value);
  }

  ngOnInit() {
    //this.getgeodata();
    this.getGeoDropDown();
    this. getMarketDeskDropDown();
    this.getBaseGrade();
this.geotypeahead=[{"pkGeoId":"","geoName":"","geoParentId":""}];
  this.geo_validate={"pkGeoLevelId":""};
    this.editGeoId = this.route.snapshot.params['id'];
    var id = this.editGeoId;
    if (this.editGeoId != null) {
           this.pagetitle = 'Edit Geo Details';
             this.subscription = this.geoService.getselectedGeo(this.editGeoId).subscribe(geoDetails => {
              this.editpage = true;
              this.addpage = false;
              this.geo = geoDetails.body;
                if (this.geo.statusName === 'Draft') {
                    this.draft = true;
                    this.active = false;
                    this.inactive = false;
                } else if (this.geo.statusName === 'Active' ) {
                    this.active = true;
                    this.draft = false;
                    this.inactive = false;
                } else if (this.geo.statusName === 'InActive') {
                    this.inactive = true;
                    this.active = false;
                    this.draft = false;
                }
                if(this.geo.geoTypeName == "Country") {
                  this.marketdesk= true;
                } else {
                   this.marketdesk= false;
                }
              
       },
         error => { throw error;
               }

        );
      
     } else {
        this.pagetitle = 'Add Geo';
        this.editpage = false;
        this.addpage = true;
     }

  }
    getStylegeocode() {
         if (this.req_geocode === true) {
           return '#d43c3c';
        } else {
        return '#cfdee7';
            }

    }
      getStylegeoname() {
         if (this.req_geoname === true) {
           return '#d43c3c';
        } else {
        return '#cfdee7';
            }

    } 
       getStylegeotype() {
         if (this.req_geotype === true) {
           return colorcodered;
        } else {
        return colorcodeblue;
            }
    }

   /* getgeodata(){
       this.subscription = this.geoService.getGeolist().subscribe(data => {
     //this.geoCodeList = data.body;
      this.geoCodeArray=[];
     for(let g=0; g < data.body.length ; g++) {
       //this.geotypeahead[g].pkGeoId=data.body[g].pkGeoId;
       this.geotypeahead[g].geoName=data.body[g].geoName;
       //this.geotypeahead[g].geoParentId=data.body[g].geoParentId;

     this.geoCodeArray.push(this.geotypeahead[g]);
    // this.geoCodeList = data.body[g].geoCode;
     }
     this.geoService.geoCodeList=this.geoCodeArray;
     console.log('geocodelist from type   ' + this.geoCodeArray + ' ======= ' +JSON.stringify(this.geoService.geoCodeList));
      }, error =>  { throw error; } );

    }*/

/*  geoCodeSuggestion($event)  {
    const geoCode = $event.target.value;
     this.subscription = this.geoService.geoCodeSuggestion(geoCode).subscribe(data => {
    // this.geoCodeList = data.body;
      this.geoCodeArray=[];
     for(let g=0; g< data.body.length ; g++) {
       this.geotypeahead[g].pkGeoId=data.body[g].geoCode;
       this.geotypeahead[g].geoName=data.body[g].geoName;
       this.geotypeahead[g].geoParentId=data.body[g].geoParentId;

     this.geoCodeArray.push(this.geotypeahead[g]);
    // this.geoCodeList = data.body[g].geoCode;
     }
     debugger;
     this.geoService.geoCodeList=this.geoCodeArray;
     console.log('geocodelist from type   ' + this.geoCodeArray + ' ======= ' +JSON.stringify(this.geoService.geoCodeList));
      }, error =>  { throw error; } );
 /*     const geoCode = $event.target.value;
        this.dataSource = Observable
      .create((observer: any) => {
        // Runs on every search
        observer.next(this.asyncSelected);
      })
      .mergeMap((geoCode: string) => this.geoService.geoCodeSuggestion(geoCode));


  }*/
 geoCodeSuggestion($event) {
    this.geoCodeList=[];
    const geoCode = $event.target.value;
     this.subscription = this.geoService.geoCodeSuggestion(geoCode).subscribe(data => {
    // this.geoCodeList = data.body;
     for(let g=0; g< data.body.length ; g++) {
     this.geoCodeList.push(data.body[g].geoCode);
    // this.geoCodeList = data.body[g].geoCode;
     }
    // console.log('geocodelist from type   ' + this.geoCodeList + '  ' +JSON.stringify(data.body));
     }, error =>  { throw error; } );
  }

  geoNameSuggestion($event) {
     this.geoNameList=[];
     const geoName = $event.target.value;
     this.subscription = this.geoService.geoNameSuggestion(geoName).subscribe(data => {
    // this.geoCodeList = data.body;
     for(let g=0; g< data.body.length ; g++) {
       this.geoNameList.push(data.body[g].geoName);
     }
   //  console.log('geonamelist from type   ' + this.geoNameList + '  ' +JSON.stringify(data.body));
     }, error =>  { throw error; } );
  }
 isNotBlank(value: string): Boolean {
    return (value && value.trim() !== '');
  }
  clear() {
   this.geo = new Geography();
   this.getGeoDropDown();
    this. getMarketDeskDropDown();
    this.getBaseGrade();
 }
      onchange(event) {
       // debugger;
        if(event==""){
          return false;
        }
        else{
        for(var i=0;i<this.geoDetails.length;i++){
          if(this.geoDetails[i].geoTypeId == JSON.parse(event)){
            this.geocountry=this.geoDetails[i].geoTypeName;
          }
        }
                if (this.geocountry === 'Country')      {
                    this.marketdesk = true;
                    this.geo.geoTypeName = undefined;
                } else {
                    this.marketdesk = false;
            }
        }
    }
    update(){
     // debugger;
      this.geoDetailsToUpadte = this.geo;
      this.geoDetailsToUpadte.geoCode = this.geo.geoCode;
      this.geoDetailsToUpadte.geoName = this.geo.geoName;
      this.geoDetailsToUpadte.geoTypeId = this.geo.geoTypeId;
      if(this.geo.geoTypeName == "Country") {
                  this.marketdesk= true;
                } else {
                   this.marketdesk= false;
                   
                }
      this.geoDetailsToUpadte.marketDeskId = this.geo.marketDeskId;
      this.geoDetailsToUpadte.geoParentId = this.geo.geoParentId;
      if(this.isValidForm()){
          this.subscription = this.geoService.updateGeoDetails(this.geoDetailsToUpadte,this.addoreditpage).subscribe(addGeoDetail => {
          this.router.navigate([geo],{ queryParams: { 'success': 2} }); 
        },
          error => {
            this.errorMsg = error;
                this.errorModal();
                setTimeout(() => {this.hideModal(); }, 1000);    
          }, 
          () => console.log('Finished')
        );
   }
    }
    backtolist(){
      this.router.navigate([geo]); 
    }
    updatewithStatus(status) {
         this.geoDetailsToUpadte = this.geo;
      this.geoDetailsToUpadte.geoCode = this.geo.geoCode;
      this.geoDetailsToUpadte.geoName = this.geo.geoName;
      this.geoDetailsToUpadte.geoTypeId = this.geo.geoTypeId;
      if(this.geo.geoTypeName == "Country") {
                  this.marketdesk= true;
                } else {
                   this.marketdesk= false;
                   
                }
      this.geoDetailsToUpadte.marketDeskId = this.geo.marketDeskId;
      this.geoDetailsToUpadte.geoParentId = this.geo.geoParentId;
      this.geoDetailsToUpadte.statusName = status;
      
      if(this.isValidForm()){
          this.subscription = this.geoService.updateGeoDetails(this.geoDetailsToUpadte,this.addoreditpage).subscribe(addGeoDetail => {
          
          this.router.navigate([geo],{ queryParams: { 'success': 9} }); 
        },
          error => {
               /*this.geoerror=[];
                let errormsg=this.geoService.errorStatusCode.errorBeans;
                for(var i=0;i<errormsg.length;i++){
                  this.geoerror.push(errormsg[i].errorMessage);
                }
                this.errorModal();*/
                this.errorMsg = error;
                this.errorModal();
                setTimeout(() => {this.hideModal(); }, 1000);
          }, 
          () => console.log('Finished')
        );
   }
    }

     onchangeparent(event) {

       for(var i=0;i<this.geoService.geoDetails.length;i++){
         if(this.geoService.geoDetails[i].pkGeoId==event){
            this.geo.geoParentName=this.geoService.geoDetails[i].geoName;
         }
       }
       /* for(var i=0;i<this.geoDetails.length;i++){
          if(this.geoDetails[i].geoParentCode == JSON.parse(event)){
            this.geoparent=this.geoDetails[i].geoParentName;
          }
        }*/
         
    }
        getStylemarketdesk() {
         if (this.req_marketdesk === true) {
           return '#d43c3c';
        } else {
        return '#cfdee7';
            }

    }
   
   savegeo(actionstatus,geoCode){

    this.geoService.geoCodeParam = geoCode;
    this.isActionPerformed = true;
    if (this.addpage ) {
            this.addoreditpage = true;
        } else if (this.editpage) {
            this.addoreditpage = false;
        }
    if(this.isValidForm()){
          this.geo.action = actionstatus;
          this.subscription = this.geoService.saveGEODetails(this.geo,this.addoreditpage).subscribe(addGeoDetail => {
            this.geoService.geoDetails.push(<Geography>addGeoDetail);
          this.router.navigate([geo] , { queryParams: { 'success': 1} }); 
        },
          error => {
            /*this.geoerror=[];
                let errormsg=this.geoService.errorStatusCode.errorBeans;
                for(var i=0;i<errormsg.length;i++){
                  this.geoerror.push(errormsg[i].errorMessage);
                }
                this.errorModal();*/
                this.errorMsg = error;
                this.errorModal();
                setTimeout(() => {this.hideModal(); }, 1000);
          },
          () => console.log('Finished')
        );
   }
  }
  
  
  submitgeo(actionstatus,geoCode) {
 
    this.isCannotDeletePopup = false;
    this.geoService.geoCodeParam = geoCode;
    this.isActionPerformed = true;
    if (this.addpage ) {
            this.addoreditpage = true;
        } else if (this.editpage) {
            this.addoreditpage = false;
        }
         if(this.isValidForm()){
           this.geo.action = actionstatus;
            this.subscription = this.geoService.submitGEODetails(this.geo).subscribe(addGeoDetail => {
          this.geoService.geoDetails.push(<Geography>addGeoDetail);
           this.router.navigate([geo],{ queryParams: { 'success': 1} });
          },
          error => {
            this.errorMsg = error;
                this.errorModal();
                setTimeout(() => {this.hideModal(); }, 1000);
          }, 
            () => console.log('Finished')
            )
      }
  }

  isValidForm(): boolean {
   // debugger
    this.counter=0;
  this.errormodal=false;
    
     if (this.geo.geoCode == null){
       this.req_geocode=true;
       this.counter++;
     }
     else if(this.geo.geoCode != null){
       if(this.geo.geoCode.trim() == ''){
         this.req_geocode=true;
       this.counter++;
       }else if(this.geo.geoCode.trim().length > 20){
          this.req_geocode=true;
          this.counter++;
       }else{
         this.req_geocode=false;
       }
     }


     if (this.geo.geoName == null){
       this.req_geoname=true;
       this.counter++;
     }else
      if(this.geo.geoName != null){

       if(this.geo.geoName.trim() == ''){
         this.req_geoname=true;
       this.counter++;
       }else if(this.geo.geoName.trim().length > 20){
          this.req_geoname=true;
          this.counter++;
       }else{
         this.req_geoname=false;
       }
     }

     if (this.geo.geoTypeId === undefined) {
       this.req_geotype = true;
       this.counter++;
     } else {
       this.req_geotype = false;
     }
     
    
       if(this.geo.geoTypeName!= undefined) {
        if(this.geo.geoTypeName === "Country") {
        if(this.geo.marketDeskId == null || this.geo.marketDeskId ===undefined) {
                  this.marketdesk= true;
                  this.req_marketdesk = true;
                  this.counter++;
                } else {
                   this.marketdesk= false;
                   this.req_marketdesk = false;
                   
                }
     }
  } else {
     for (var i = 0; i < this.geoDetails.length; i++) {
      if (this.geoDetails[i].geoTypeId == this.geo.geoTypeId) {
        this.geo.geoTypeName = this.geoDetails[i].geoTypeName;
      }
    }  
    if(this.geo.geoTypeName === "Country") {
        if(this.geo.marketDeskId == null || this.geo.marketDeskId ===undefined) {
                  this.marketdesk= true;
                  this.req_marketdesk = true;
                  this.counter++;
                } else {
                   this.marketdesk= false;
                   this.req_marketdesk = false;
                   
                }
     }
  }
        
     
     /*if (this.geo.marketDeskId === undefined) {
       this.req_marketdesk = true;
       this.counter++;
     } else {
       this.req_marketdesk = false;
     }*/

       if(this.counter !== 0){
        this.showModal();

         setTimeout(() => {this.hideModal(); }, 1000);
         return false
       }else{
         return true;
       }

  }
   getBaseGrade(): void {
        this.parentGeolist = [];
           if ( this.geoService.geoDetails != null) {
               this.geoService.geoDetails.forEach(list => {
           /* if (list.statusName.toUpperCase() === active.toString().toUpperCase() ) {
                 this.parentGeolist.push({  label: list.geoName, value: list.geoId });
            }*/
            if (list.statusName === 'Active') {
            this.parentGeolist.push({ label: list.pkGeoId, value: list.geoCode });
          }
        });
         } else {
             this.subscription = this.geoService.getListingPageFromJson().subscribe(productGrade => {
              this.geoService.geoDetails = productGrade.body;
               this.geoService.geoDetails.forEach(list => {
           /* if (list.statusName.toUpperCase() === active.toString().toUpperCase() ) {
                 this.parentGeolist.push({  label: list.geoName, value: list.geoId });
            }*/
              if (list.statusName === 'Active') {
            this.parentGeolist.push({ label: list.pkGeoId, value: list.geoCode });
          }
        });

   }, error => {
      console.log('Error Loading Grade Listing: ' + <any>error)
      });
         }
   }
   
 getGeoDropDown() {
    this.subscription = this.geoService.getGeoDropDownJSON().subscribe(addGeoDetail => {
      this.geoDetails = addGeoDetail.body;
    },
      error => { 
      throw error },
      () => console.log('Finished')
    );
  }

  getMarketDeskDropDown() {
    this.subscription = this.geoService.getMarketDeskDropDownJSON().subscribe(addGeoDetail => {
      this.marketdata = addGeoDetail.body;
     
    },
      error => { 
      throw error },
      () => console.log('Finished')
    );
  }
  
   
   public errorModal():void {
    this.errormodal1 = true;
  }
  public showModal():void {
    this.errormodal = true;
  }

  public hideModal():void {
    this.deletesuccessModal.hide();
    this.errormodal1 = false;
  }
   public onHidden(): void {
    this.errormodal = false;
    //this.errormodals = false;
  }

    ngOnDestroy() {

  }
}

