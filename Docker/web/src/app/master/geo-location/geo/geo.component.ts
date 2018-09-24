import { Component, EventEmitter, Input, Output, HostListener, ViewChild, TemplateRef, OnInit } from '@angular/core';
//import { TreeviewItem, TreeviewConfig,TreeviewComponent,TreeviewItemTemplateContext} from 'ngx-treeview';
import { FormGroup, FormControl } from '@angular/forms';
import { process, SortDescriptor, orderBy, GroupDescriptor, State } from '@progress/kendo-data-query';
import { GridComponent, GridDataResult, DataStateChangeEvent, PageChangeEvent} from '@progress/kendo-angular-grid';
import { Router } from '@angular/router';
import { CommonModule, PlatformLocation, Location} from '@angular/common';
import { Geography } from './geography';
import { GeoService } from './service/geo.service';
import { ModalModule } from 'ngx-bootstrap';
import { AccordionModule , ModalDirective } from 'ngx-bootstrap';
import { Subscription } from 'rxjs/Subscription';
import { addGeo ,viewGeo} from '../../../shared/interface/router-links';
import { MasterSetupService } from './../../master-setup/service/master-setup.service';
import { ActivatedRoute } from '@angular/router';
import { SelectItem } from '../../../shared/interface/selectItem';
import { DropdownModule } from 'primeng/primeng';
import { ButtonModule } from 'primeng/primeng';
import { ConfirmDialogModule, ConfirmationService } from 'primeng/primeng';
import { ResponseData } from '../../../shared/interface/responseData';
import { Link } from '../../../shared/interface/link';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { ExcelExportData } from '@progress/kendo-angular-excel-export';


@Component({
  selector: 'app-geo',
  templateUrl: './geo.component.html',
  styleUrls: ['./geo.component.css']
})
export class GeoComponent extends MasterSetupService implements OnInit {

  public geodetails;
   state: State = {
        skip: 0,
        take: 1000
    };

  //showHide: boolean;
   showHide: boolean;
  subscription: Subscription;
  isComplete: Boolean = false;
 // uomData: UnitMeasurement = new UnitMeasurement();
  //selectedGEOrow: GeoInterface;
  data: Geography[];
  displayDialog:boolean;
  public success;
  @ViewChild('successModal') public successModal: ModalDirective;
  @ViewChild('deletesuccessModal') public deletesuccessModal: ModalDirective;
  public geoCode;
  public isShowModal :boolean;
  showModal:boolean;
  public iseditModal;
  public uomedit;
  private geoDelete;
  public draft;
  public active;
  public inactive;
  public savedData;
  public isupdateModal;
  selectedGEOrow: Geography;
  isDeletePopupModal : boolean;
  public del_id;
  public status;
  deleteSuccessModal:boolean;
 deactivateSuccessModal : boolean;
  isDeactivatePopupModal: Boolean = false;
  private link: Link;
  public geoDetails;
  headerGeoCode : string;
  headerGeoName : string;
 headerGeoType : string;
 headerMarketing: string;
 headerStatus: string;
 msg:string;
 group: GroupDescriptor[] = [{ field: 'statusName' }];
 isNavbtwComponent: boolean;
 geoname:string;
  gridData: GridDataResult;
   sort: SortDescriptor[] = [];
    allowUnsort: Boolean = true;

 

   public GeoIdMap:Map<string, number> = new Map<string, number>();
  enableButton = true;
  //@Input() template: TemplateRef<TreeviewItemTemplateContext>;
    @Output() loadingData: Boolean = true;
   masters: ResponseData;
  // @Input() items: TreeviewModule[];
  @Output() hide = new EventEmitter();
  @Output() selectedChange = new EventEmitter<any[]>(true);
  //@ViewChild(TreeviewComponent) treeviewComponent: TreeviewComponent;
  isOpen = false;
  private mouseEvent: MouseEvent;
  
    //values: number[];
//    config: TreeviewModule = {
//    hasAllCheckBox: false,
//    hasFilter: true,
//    hasCollapseExpand: false,
//    maxHeight: 500
// };
  constructor(private route:ActivatedRoute,http:Http,private router:Router, platformlocation: PlatformLocation, location: Location,public geoService: GeoService,private masterSetupService: MasterSetupService) {
      super(http);
      this.showHide = true;
      this.allData = this.allData.bind(this);
     platformlocation.onPopState(() => {
         this.isNavbtwComponent = true;

        });
    
  }

    ngOnInit() {
    window.scrollTo(0, 0);
    this.loadingGEOList();
     // debugger;
    this.route.queryParams.subscribe(params => {   
        this.success = +params['success'];
        if(this.success==1){
          this.isShowModal = true;
          setTimeout(()=>{this.isShowModal = false;},3000);
        }
      });
      this.route.queryParams.subscribe(params => {
        this.geoCode = this.geoService.geoCode;
        this.status = this.geoService.status;
        this.success = +params['success'];
        if (this.success === 2) {
          this.msg = "Success:Geo " +  this.geoCode + ' ' + "updated";
          this.isShowModal = true;
          setTimeout(() => {this.isShowModal = false; }, 3000);
        }
        if (this.success === 1) {
          this.msg = "Success:Geo " +  this.geoCode + ' ' + "saved";
          this.isShowModal = true;
          setTimeout(() => {this.isShowModal = false; }, 3000);
        }
        if (this.success === 3) {
          this.msg = "Success:Geo " +  this.geoCode + ' ' + "activated";
          this.isShowModal = true;
          setTimeout(() => {this.isShowModal = false; }, 3000);
        }
        if (this.success === 9) {
          this.msg = "Success:Geo " +  this.geoCode + ' '+ this.status;
          this.isShowModal = true;
          setTimeout(() => {this.isShowModal = false; }, 3000);
        }
        if (this.success === 8) {
          this.msg = "Success :Geo :" + this.geoCode+ ' ' + "deleted";
          this.isShowModal = true;
          setTimeout(() => {this.isShowModal = false; }, 3000);
        }
      });

       this.deleteSuccessModal=false;
      this.isDeletePopupModal=false;
      this.isDeactivatePopupModal=false;
      this.deactivateSuccessModal=false;
  }

  allData(): ExcelExportData {
    const result: ExcelExportData = {
    data: process(this.geoService.geoDetails, this.state).data,
      group: this.group
    };

    return result;
  }

   checkAllClicked($event) {
    console.log('checkAllClicked ', $event);
    /*this.event.forEach(x => x.state = ev.target.checked)*/
  }

   sortChange(sort: SortDescriptor[]): void {
        this.sort = sort;
        this.loadingGEOList();
    }

  modalPopup(event) {
    // alert('popup');

  }

 public dataStateChange(state: DataStateChangeEvent): void {
         this.state = state;
         this.gridData = process(this.geoService.geoDetails, this.state);
    }


   pageChange(event: PageChangeEvent): void {
        this.state.skip = event.skip;
        this.loadingGEOList();
    }

    changeShowStatus() {
    // this.showHide = !this.showHide;
    this.router.navigate([addGeo]);
  }


loadingGEOList() {
  this.geoname = this.geoService.geoName;
     this.subscription = this.geoService.getListingPageFromJson().subscribe(geoMaster => {
      this.geoService.geoDetails = geoMaster.body;
      this.data = geoMaster.body;
      var data1 = geoMaster.view.column.split(",");
      if (data1[0] != null && data1[0] =='geoCode') {
        this.headerGeoCode = "Geo Code";
      }
      if (data1[1] != null && data1[1] =='geoName') {
        this.headerGeoName = "Geo Name";
      }
      if (data1[2] != null && data1[2] =='geoTypeName') {
        this.headerGeoType = "Geo Type";
      }
      if (data1[3] != null && data1[3] =='marketDeskName') {
        this.headerMarketing = "Marketing Desk";
      } 
     },
      error => console.log(error),
      () => console.log('Finished')
      );
  }

  checkorUnCheckAll(ele) {
     const checkboxes = document.getElementsByTagName('input');
     if (ele.target.checked) {
         for (let intval = 0; intval < checkboxes.length; intval++) {
             if (checkboxes[intval].type === 'checkbox') {
                 checkboxes[intval].checked = true;
             }
         }
     } else {
         for (let i = 0; i < checkboxes.length; i++) {
              if (checkboxes[i].type === 'checkbox') {
                 checkboxes[i].checked = false;
              }
         }
     }
 }

  viewById(event) {
    // alert("==eventparam==>"+JSON.stringify(event));
    this.geoService.viewbyIdParam = event;
    this.router.navigate([viewGeo]);
  }


 geo_delete(pkGeoId,statusName,geoCode) {
   //alert(geoCodeParam);
   this.del_id=pkGeoId;
   this.status = statusName;
   this.geoCode = geoCode;
   if (this.status =="Draft") {
      // this.isDeletePopupModal=true;
      this.successModal.show();     
    }
    else if (this.status === "Active") {
      this.isDeactivatePopupModal = true;
    }
    
 }

 conf_delete(id,geoname){
    // this.geoname=geoname;
     let param = {"pkGeoId":this.del_id};
     this.subscription = this.geoService.deleteGEOById(param).subscribe(deletedStatus => {
                           this.savedData = deletedStatus;
                          // this.isDeletePopupModal = false;
                          this.successModal.hide();    
                           this.deletesuccessModal.show();
                           
                           setTimeout(()=>{this.deletesuccessModal.hide();},3000);
                           if (this.status=="Draft") {
                           this.deleteSuccessModal=true;    
                           setTimeout(()=>{this.deletesuccessModal.hide();},3000);
                           }
                          else if (this.status === "Active") {
                             this.deactivateSuccessModal = true;
                             setTimeout(()=>{this.deactivateSuccessModal = false;},3000);
                           }    
                           this.loadingGEOList();
       },
         error => alert(error),
         () => console.log('Finished')
    );
}


closedeletepopup(){
  this.isDeletePopupModal = false;
    this.isDeactivatePopupModal = false;
   
   
}

 
  close(){
    this.iseditModal = false;
  }

 delete() {
    // const index = this.findSelectedGEOIndex();
    // this.GeoService.geoDetails = this.GeoService.geoDetails.filter((val, i) => i !== index);
  }

 findSelectedGEOIndex() {
    // return this.GeoService.geoDetails.indexOf(this.selectedGEOrow);
  }

ngOnDestroy() {
    // prevent memory leak when component destroyed
   this.subscription.unsubscribe();
  }

}