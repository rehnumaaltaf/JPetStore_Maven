import { Component, Inject, OnInit, OnDestroy, Output, Input, ViewChild  } from '@angular/core';
import { FormGroup ,  FormControl,  Validators, FormArray,   FormBuilder, ReactiveFormsModule , FormsModule} from '@angular/forms';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { ResponseData } from '../../../../shared/interface/responseData';
import { OriginDefinitionService } from '../service/origin-definition.service';
import { OriginDefinition } from '../origin-definition';
import { SelectItem } from '../../../../shared/interface/selectItem';
import { ModalModule } from 'ngx-bootstrap';
import { addOrigin } from '../../../../shared/interface/router-links';
import { origin } from '../../../../shared/interface/router-links';
import { ModalDirective } from 'ngx-bootstrap/modal';
import { CanComponentDeactivate } from '../../../../shared/guards/can-deactivate-guard'

@Component({
  selector: 'app-add-origin',
  templateUrl: './add-origin.component.html',
  styleUrls: ['./add-origin.component.css']
})
export class AddOriginComponent implements OnInit, OnDestroy, CanComponentDeactivate {
  // onDestroy is not working on above line
  @ViewChild('pagebackModal') public pagebackModal: ModalDirective;
  origionRegionGroupForm: FormGroup;
  status: string;
  originDefinitionDetails: OriginDefinition[];
  originDefinitionCupProfileDetails: OriginDefinition[];
  subscription: Subscription;
  isComplete: Boolean = false;
  loadingData: Boolean = false;
  geoName: SelectItem[] = [];
  originRegionCode: SelectItem[] = [];
  originRegionName: SelectItem[] = [];
  destinationSystem: SelectItem[] = [];
  validateRetVal: Boolean;
  public origin_Code;
  public origin_Name;
  public valMessage: string;
  public showModal;
  req_originName: boolean;
  req_originCode: boolean;
  req_originRegionCode: boolean;
  req_originRegionName: boolean;
  showcrossfromsecondrow: boolean;
  req_originRegionMeanAboveSeaLevel: boolean;
  isActionPerformed = false;
  originDefinition: OriginDefinition = new OriginDefinition();
  showcross: boolean[] = [];
  showplus: boolean[] = [];
  showPlusDefaultPacking: boolean[] = [];
  showCrossDefaultPacking: boolean[] = [];
  showPlusExternalSystemPacking: boolean[] = [];
  showCrossExternalSystemPacking: boolean[] = [];
  public crossid;
  // originModel: OriginModel = new OriginModel();
  // originRegionDto: OriginRegionDtoModel[] = [];
  // originCupProfileDto: OriginCupProfileDtoModel[] = [];

   // obj1: OriginRegionDtoModel[] = [];
  public isError;
  errorMessage: string;
 errorMessageForOrigin: String;
  public reqmsg: string;
  // checkdropdown: number = 0;
  public pattern;
  successModal = false;
  public isShowModal: boolean;
  originCountryResp: ResponseData;
  public statuses: any = {
    isFirstOpen: true,
    isFirstDisabled: false
  };
   constructor(private originDefinitionService: OriginDefinitionService, private router: Router, private fb: FormBuilder) {
    // this.loadDropdownList();

  }

 /* originForm = new FormGroup({
    originCode: new FormControl(),
    originName: new FormControl(),
    originFullName: new FormControl(),
    geoName: new FormControl(),
    estateName: new FormControl(),
    originRegionName: new FormControl(),
    originRegionMeanAboveSeaLevel: new FormControl(),
    cupProfileCode: new FormControl(),
    cupProfileName: new FormControl(),
  })*/

  ngOnInit() {

    // this.showcross.push(true);
    // this.showplus.push(true);
    this.showcrossfromsecondrow = false;
       if (this.crossid !== undefined ) {
       }



           this.pagebackModal.hide();
             this.origionRegionGroupForm = this.fb.group({
         //  permissionGroupId:[''] ,
          //  permissionGroupName: ['', [Validators.required, Validators.minLength(5)]],
            // maxLength(20)]],
            // pattern("[a-zA-z0-9 ]")]],
          originDto : this.fb.group({originCode: ['', [Validators.pattern('[a-zA-Z0-9]+')]],
                 originName: [''],
                  originFullName: [''],
                   geoName: ['']}),
          originCupProfileDto: this.fb.array([]),
          originRegionDto: this.fb.array([]),
          externalSystemMappingDto : this.fb.array([])
          });

 this.addOriginRegionMultiple();
 this.addOriginCupProfileMultiple();
 this.addExternalSystemMappingMultiple();
 this.subscription = this.originDefinitionService.getOriginDropDownJSON().subscribe(originDefinitionDetails => {
 this.originDefinitionService.geoName =  originDefinitionDetails.body.roles;
 this.originDefinitionService.originRegionCode = originDefinitionDetails.body.originRegionCode;
      },
    error => alert(error),
    () => console.log('Finished')
      )
      this.req_originName = false;
      this.req_originCode = false;
      this.req_originRegionCode = false;
      this.req_originRegionName = false;
      this.req_originRegionMeanAboveSeaLevel = false;
      // this.reset();
     // this.originModel.originDto = new OriginDtoModel;
     // this.originModel.originRegionDto = new OriginRegionDtoModel()[0];
     // this.originModel.originCupProfileDto = new OriginCupProfileDtoModel()[0];
     this.getDropDownList();
     this.getDropDownProductList();
     this.getDropDownPackingList();
     this.getDropDownDestinationSystemList();

  }
      addOriginDetailsMultiple() {
    if (this.isValidForm()) {
   console.log( JSON.stringify(this.origionRegionGroupForm.value))
    this.subscription = this.originDefinitionService.savePermissionGroup
    (this.origionRegionGroupForm.value, this.status).subscribe(OriginDefinitionDetails => {
        this.originDefinitionService.originDefinitionDetails = <OriginDefinition[]>OriginDefinitionDetails;
         this.router.navigate([origin], { queryParams: { 'success': 1 } });
         // this.router.navigate([origin]);
         },
         error => {
        // alert(error )
         this.pagebackModal.show();
                 this.errorMessageForOrigin = this.originDefinitionService.errorMessage;
                 // this.isError= true;

        },
        () => console.log('Finished')
        )
   }
 }

    addOriginRegionMultiple() {
      const control = <FormArray>this.origionRegionGroupForm.controls['originRegionDto'];
       this.showPlusDefaultPacking[this.showPlusDefaultPacking.length - 1] = true;
        this.showPlusDefaultPacking.push(false);
        this.showCrossDefaultPacking.push(false);
        if (this.showCrossDefaultPacking.length === 1) {
          this.showCrossDefaultPacking[0] = true;
        }else {
          this.showCrossDefaultPacking[0] = false;
        }
        const addrCtrl = this.initOriginRegionMapping();
        control.push(addrCtrl);
    }
    addOriginCupProfileMultiple() {
       // this.showcross = true;
       this.showcrossfromsecondrow = true;
        const control = <FormArray>this.origionRegionGroupForm.controls['originCupProfileDto'];
        this.showplus[this.showplus.length - 1] = true;
        this.showplus.push(false);
        this.showcross.push(false);
        if (this.showcross.length === 1) {
          this.showcross[0] = true;
        }else {
          this.showcross[0] = false;
        }

        // console.log("---- profile multiple -----")
        // console.log(control.);
        const addrCtrl = this.initOriginCupProfileMapping();
        // console.log(addrCtrl);
       control.push(addrCtrl);
      //  console.log(control.controls.length);
    }
       addExternalSystemMappingMultiple() {
        const control = <FormArray>this.origionRegionGroupForm.controls['externalSystemMappingDto'];
        this.showPlusExternalSystemPacking[this.showPlusExternalSystemPacking.length - 1] = true;
        this.showPlusExternalSystemPacking.push(false);
        this.showCrossExternalSystemPacking.push(false);
        if (this.showCrossExternalSystemPacking.length === 1) {
          this.showCrossExternalSystemPacking[0] = true;
        }else {
          this.showCrossExternalSystemPacking[0] = false;
        }
        const addrCtrl = this.initExternalSystemMapping();
       control.push(addrCtrl);
    }

    removeOriginRegionMultiple(i: number) {
       // if (i !== 0 ) {
          if ( this.getOriginRegionList(this.origionRegionGroupForm).length >= 1) {
            const control = <FormArray>this.origionRegionGroupForm.controls['originRegionDto'];
            control.removeAt(i);
           // console.log(this.showPlusDefaultPacking[i]);
            this.showPlusDefaultPacking.splice(i, 1);
            this.showCrossDefaultPacking.splice(i, 1);
            if (this.showCrossDefaultPacking.length === 1) {
              this.showCrossDefaultPacking[0] = true;
            }

             if (this.showPlusDefaultPacking.indexOf(false) === -1) {
              this.showPlusDefaultPacking[this.showPlusDefaultPacking.length - 1 ] = false;

            }
           // console.log(this.showplus)

            }
    }
       removeOriginCupProfileMultiple(i: number) {

        // console.log(this.getOriginCupProfileList(this.origionRegionGroupForm).length);
        // if (i !== 0 ) {
          if ( this.getOriginCupProfileList(this.origionRegionGroupForm).length >= 1) {
            const control = <FormArray>this.origionRegionGroupForm.controls['originCupProfileDto'];
            control.removeAt(i);

            // console.log(this.showplus[i]);
            this.showplus.splice(i, 1);
            this.showcross.splice(i, 1);
            if (this.showcross.length === 1) {
              this.showcross[0] = true;
            }

            if (this.showplus.indexOf(false) === -1) {
              this.showplus[this.showplus.length - 1 ] = false;

            }
           // console.log(this.showplus)

            }
    }
           removeExternalSystemMappingMultiple(i: number) {
        // if (i !== 0 ) {
            if ( this.getExternalSystemMappingList(this.origionRegionGroupForm).length >= 1) {
            const control = <FormArray>this.origionRegionGroupForm.controls['externalSystemMappingDto'];
            control.removeAt(i);
            this.showPlusExternalSystemPacking.splice(i, 1);
            this.showCrossExternalSystemPacking.splice(i, 1);
            if (this.showCrossExternalSystemPacking.length === 1) {
              this.showCrossExternalSystemPacking[0] = true;
            }

            if (this.showPlusExternalSystemPacking.indexOf(false) === -1) {
              this.showPlusExternalSystemPacking[this.showPlusExternalSystemPacking.length - 1 ] = false;

            }
           // console.log(this.showplus)

            }
    }
      getOriginRegionList(origionRegionGroupForm) {
      return origionRegionGroupForm.get('originRegionDto').controls;
  }
    getOriginCupProfileList(origionRegionGroupForm) {
      return origionRegionGroupForm.get('originCupProfileDto').controls;
  }
    getExternalSystemMappingList(origionRegionGroupForm) {
      return origionRegionGroupForm.get('externalSystemMappingDto').controls;
  }
    initOriginRegionMapping() {
        return this.fb.group({
            originRegionCode: [],
            originRegionName: []
       });
    }
     initOriginCupProfileMapping() {
        return this.fb.group({
            originCupProfileName: [],
            originCupProfileCode: []
       });
    }
      initExternalSystemMapping() {
        return this.fb.group({
            destinationSystem: [],
            type: [],
            mapping: []

       });
    }


  loadDropdownList() {
    this.subscription = this.originDefinitionService.getOriginDefinitionJSON().finally(() => this.isComplete = true).subscribe(data => {
      this.loadingData = true;
      this.originDefinitionDetails = data.body;
    }, error => {
      console.log('Error Loading Origin Listing: ' + <any>error)
      this.loadingData = false;
      // this.notificationService.addNotications(error);
    });
  }

  getDropDownList() {

   this.subscription = this.originDefinitionService.getOriginDropDownJSON().subscribe(geoLocation => {
          this.originCountryResp = <ResponseData> geoLocation.body;
         },
         error => {
        // alert(error )
                  // this.pagebackModal.show();
                 this.errorMessageForOrigin = this.originDefinitionService.errorMessage;
        },
        () => console.log('Finished')
        )
  }
      getDropDownPackingList() {

   this.subscription = this.originDefinitionService.getPackingDropDownJSON().subscribe(packingSize => {
          this.originDefinitionService.originRegionNameResp = <ResponseData> packingSize.body;
         },
         error => {
        // alert(error )
                  // this.pagebackModal.show();
                 this.errorMessageForOrigin = this.originDefinitionService.errorMessage;
        },
        () => console.log('Finished')
        )
  }
       getDropDownDestinationSystemList() {

          this.subscription = this.originDefinitionService.getDestinationSystemDropDownJSON().subscribe(packingSize => {
          this.originDefinitionService.destinationSystemResp = <ResponseData> packingSize.body;
         },
         error => {
        // alert(error )
                  // this.pagebackModal.show();
                 this.errorMessageForOrigin = this.originDefinitionService.errorMessage;
        },
        () => console.log('Finished')
        )
  }
       getDropDownProductList() {
      this.subscription = this.originDefinitionService.getProductDropDownJSON().subscribe(originRegionCode => {
        // alert(JSON.stringify(originRegionCode.body))
         const arr = [];
         for (const key in originRegionCode.body) {
           if (originRegionCode.body.hasOwnProperty(key)) {
                  const  prdtDTO = originRegionCode.body[key].productDto;
                  arr.push(prdtDTO.prodName)
               }
        }

       // alert("arr push==>"+JSON.stringify(arr));
          this.originDefinitionService.originRegionCodeResp =  arr;
        // alert("complete dropdown==>"+ JSON.stringify(this.originDefinitionService.originRegionCodeResp));

         },
         error => {
        // alert(error )
                  // this.pagebackModal.show();
                 this.errorMessageForOrigin = this.originDefinitionService.errorMessage;
        },
        () => console.log('Finished')
        )
  }


/* isValidForm(): boolean {
    console.log(JSON.stringify(this.origionRegionGroupForm.value) );
       this.reqmsg = '';
          if (this.originDefinition.originCode === '' || this.originDefinition.originCode === undefined ) {
           this.errorMessageForOrigin = "Origin Name Is Mandatory ";
            this.req_originCode = true;
             this.isError= "true";
            this.checkdropdown++;
         }
       else if (this.originDefinition.originName === '' || this.originDefinition.originName === undefined ) {
             this.errorMessageForOrigin = "Origin Name Is Mandatory ";
            this.req_originName = true;
            this.isError= "true";
            this.checkdropdown++;
          }
        if (this.checkdropdown == 0 || this.checkdropdown === 0) {
                 return true;
             }else {
                  this.reqmsg += ' Fields are Required';
                 return false;
             }
     }*/
     isValidForm(): boolean {
    if ((this.originDefinition.originCode === '' || this.originDefinition.originCode === undefined)
      && (this.originDefinition.originName === '' || this.originDefinition.originName === undefined)) {
      this.req_originCode = true;
      this.req_originName = true;
      return false;
    } else if ((this.originDefinition.originCode === '' || this.originDefinition.originCode === undefined)
      && (this.originDefinition.originName !== '' || this.originDefinition.originName !== undefined)) {
      this.req_originCode = true, this.req_originName = false;
      return false;

   } else if ((this.originDefinition.originCode !== '' || this.originDefinition.originCode !== undefined)
      && (this.originDefinition.originName === '' || this.originDefinition.originName === undefined)) {
      this.req_originCode = false, this.req_originName = true;
      return false;
  } else if ((this.originDefinition.originCode !== '' || this.originDefinition.originCode === undefined)) {
           if (this.origionRegionGroupForm.valid) {
                 this.req_originCode = false;
      return true;
    } else  {
       this.req_originCode = true;
       alert('space and special characters are not allowed in origin code')
      return false;
   }} else {
      return true;
    }
  }

    saveOrigin(origin_Name): void {
      this.isActionPerformed = true;
     this.originDefinitionService.originCodeParam = origin_Name;
         alert(JSON.stringify(this.origionRegionGroupForm.value));
        this.status = 'draft';
        this.addOriginDetailsMultiple() ;
        // this.addOriginCupProfileDetailsMultiple();
       // this.router.navigate([origin],{ queryParams: { "success": 1 } });
        // this.router.navigate([origin]);
  }

  submitOrigin(origin_Name): void {
    this.isActionPerformed = true;
     this.originDefinitionService.originCodeParam = origin_Name;
        this.status = 'save';
        this.addOriginDetailsMultiple() ;
         // this.addOriginCupProfileDetailsMultiple();
  }


  reset(): void {
 this.origionRegionGroupForm.reset();
 console.log(JSON.stringify(this.originDefinitionDetails));
  this.req_originCode = false;
      this.req_originName = false;
       this.originDefinition = new OriginDefinition();
    // this.originModel = new OriginModel();
   // this.clearModelObjects();
                 }


   getOriginCodeStyle() {
    if (this.req_originCode === true) {
      return '#d43c3c';
    } else {
      return '#cfdee7';
    }                  }

  getOriginNameStyle() {
    if (this.req_originName === true) {
      return '#d43c3c';
    } else {
      return '#cfdee7';
    }                  }

   getStyle2() {
    if (this.req_originRegionCode === true) {
      return '#d43c3c';
    } else {
      return '#cfdee7';
    }                  }
  close() {
  this.pagebackModal.hide();
  // this.showModal=false;
}
  clickToClosePop() {
  this.isError = false;
  return false;
  // this.showModal=false;
}
   ngOnDestroy() {
    // Called once, before the instance is destroyed.
    this.subscription.unsubscribe();
  }
    canDeactivate(): boolean {
      return this.isActionPerformed;
  }
}
