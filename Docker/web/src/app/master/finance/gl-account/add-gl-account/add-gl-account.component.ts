import { Component, Inject, OnInit, OnDestroy, Output, Input, ViewChild, OnChanges, ViewEncapsulation } from '@angular/core';
import { FormGroup, FormControl, Validators, FormArray, FormBuilder, ReactiveFormsModule, FormsModule } from '@angular/forms';
import { Subscription } from 'rxjs/Subscription';
import { Router, ActivatedRoute } from '@angular/router';
import { GlAccountService } from '../service/gl-account.service';
import { GLAccount } from '../gl-account';
import { addGl } from '../../../../shared/interface/router-links';
import { Gl } from '../../../../shared/interface/router-links';
import { ModalModule, AccordionModule } from 'ngx-bootstrap';
import { ResponseData } from '../../../../shared/interface/responseData';
import { CanComponentDeactivate } from '../../../../shared/guards/can-deactivate-guard';
import { GlBasicDTO } from '../gl-basic';
import { ExternalGLMappingDto } from '../gl-externalmapping';
import { SelectItem } from '../../../../shared/interface/selectItem';
import { ExternalGlMappingName } from '../gl-externalmapping';
import { GlType } from '../gl-basic';
import { PartyName } from '../gl-basic';
import { GLCode } from '../gl-basic';
import { ExternalMappingComponent } from './external-mapping.component';
import { ModalDirective } from 'ngx-bootstrap/modal';


@Component({
  selector: 'app-add-gl-account',
  templateUrl: './add-gl-account.component.html',
  styleUrls: ['./add-gl-account.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class AddGlAccountComponent implements OnInit, OnDestroy, CanComponentDeactivate {
  @ViewChild('invalidCount') public invalidCount: ModalDirective;
  glData: GLAccount = new GLAccount();
  GLcodeOption: SelectItem[] = [];
  glBasicDto: GlBasicDTO = new GlBasicDTO();
  externalGLMappingDto: ExternalGLMappingDto[] = Array(new ExternalGLMappingDto());
  subscription: Subscription;
  partyName: boolean;
  partyType: boolean;
  isComplete: Boolean = false;
  loadingData: Boolean = false;
  public req_glName: boolean;
  public isShowModal;
  req_glCode: boolean;
  isDuplicateGLPopupModal: boolean;
  isActionPerformed = false;
  showcross: boolean[] = [];
  showplus: boolean[] = [];
  // for edit GL
  iseditModal: boolean;
  iscreateModal: boolean;
  public statusName;
  public viewbyIdParam;
  public gldatabyid;
  showcreate: boolean;
  showedit: boolean;
  showdraftbutton: boolean;
  showactivebutton: boolean;
  showinactivebutton: boolean;
  edit: boolean;
  private glCode;
  public isShoweditModal;
  public errormodalpart;
  public savedData;
  public disParent: Boolean = true;
  // public isupdateModal;
  displayDialog: boolean;
  // isUpdate: boolean;
  // isActivated: boolean;
  // isInActivated: boolean;
  externalGlForm: FormGroup;
  public invalidCountMsg: String; // msg for 30 sec count
  formarraylength = 0;
  public countModal: Boolean = false;
  originCountryResp: ResponseData;
  externalSystemRefName: ExternalGlMappingName[];
  glTypeRefName: GlType[];
  legalentityOption: PartyName[];
  // parentGLName: GLCode[];
  //  parentGLName2: GLAccount[];
  //  parentGLName: GLAccount[];
  //  parentGLName: GLCode[];
  title: number;
  parentGLName2: GLAccount[] = Array(new GLAccount());
  parentGLName: GLAccount[] = Array(new GLAccount());
  //  parentGLName2: GLCode[];
  // legalentityOption
  // externalGLMappingDto:ExternalGLMappingDto[];
  public status: any = {
    isFirstOpen: true,
    isFirstDisabled: false
  };

  constructor(public glAccountService: GlAccountService, private fb: FormBuilder, private router: Router, private route: ActivatedRoute) {
  }


  ngOnInit(): any {
    console.log('dd' + this.title)
    this.getDropDownList();
    this.getParentGlDropdown();
    this.getExternalSystemDropdown();
    this.getGLTypeDropdown();
    this.getLegalEntityDropdown();
    this.showcreate = true;
    this.showedit = false;
    // this.glData.glBasicDto = new GlBasicDTO();
    // this.glData.externalGLMappingDto = Array(new ExternalGLMappingDto());
    // this.glData.externalGLMappingDto.push(new ExternalGLMappingDto());
    // for edit GL
    this.gldatabyid = this.route.snapshot.params['glId'];
    this.glData.glBasicDto = this.glBasicDto;
    // this.glData.glBasicDto.glIsGroup = 'N'; // for Is Group default No
    if (this.gldatabyid !== null && this.gldatabyid !== undefined) {
      this.externalGlForm =
        this.fb.group({
          partyName: [''],
          glCode: [''],
          glName: [''],
          glDesc: [''],
          glIsGroup: [''],
          glType: [''],
          parentGl: [''],
          // glBasicDto:['']
          externalGLMapping: this.fb.array([])
        });

      this.subscription = this.glAccountService.getSingleGlListJSON(this.gldatabyid).subscribe(glList => {
        console.log('mere console');
        console.log(JSON.stringify(glList));
        this.glData = glList.body;
        this.glBasicDto = this.glData.glBasicDto
        this.externalGLMappingDto = this.glData.externalGLMappingDto;
        console.log(this.glData.externalGLMappingDto.length + 'check for length')
        for (let i = 0; i < this.glData.externalGLMappingDto.length; i++) {
          this.addExternalGlMapping();
        }

        if (this.glData.glBasicDto.statusName === 'Draft') {
          this.showdraftbutton = true;
          this.showactivebutton = false;
          this.showinactivebutton = false;
        } else if (this.glData.glBasicDto.statusName === 'Active') {
          this.showactivebutton = true;
          this.showdraftbutton = false;
          this.showinactivebutton = false;
        } else if (this.glData.glBasicDto.statusName === 'InActive') {
          this.showinactivebutton = true;
          this.showactivebutton = false;
          this.showdraftbutton = false;
        }
      },
        error => { throw error });

      this.isActionPerformed = true;
      this.showcreate = false;
      this.showedit = true;

    } else {

      this.glBasicDto.glIsGroup = 'N';
      this.externalGlForm =
        this.fb.group({
          partyName: [''],
          glCode: [''],
          glName: [''],
          glDesc: [''],
          glIsGroup: [''],
          glType: [''],
          parentGl: [''],
          // glBasicDto:['']
          externalGLMapping: this.fb.array([])
        });

      this.addExternalGlMapping();
    }
  }
  // for edit GL with update with same status
  updateGL(glid, status) {
    // alert("glid inside updateGL-->"+JSON.stringify(glid));
    this.glData.glId = glid;
    this.glData.statusName = glid;
    this.glData.glBasicDto = this.glBasicDto;
    this.glData.externalGLMappingDto = this.externalGLMappingDto;
    console.log(this.glData);
    if (this.isValidForm()) {
      this.isActionPerformed = true;
      this.subscription = this.glAccountService.updateGL(this.glData).subscribe(savedData => {
        // this.glAccountService.glDetails.push(<GLAccount>savedData);
        this.router.navigate([Gl], { queryParams: { 'isUpdate': 1, glcode: this.glData.glBasicDto.glCode } });

      },
        error => alert(error),
        () => console.log('Finished')

      );

    }
  }

  getExternalMappingList(externalGlForm) {
    return externalGlForm.get('externalGLMapping').controls;
  }
  // for edit GL with update and status change
  updateGLwithStatus(glid, status) {
    // alert("status inside updateGLwithStatus-->"+JSON.stringify(status));
    this.glData.statusName = status;

    if (this.isValidForm()) {
      this.isActionPerformed = true;
      if (this.glData.glBasicDto.statusName === 'Draft') {
        this.glData.glBasicDto.statusName = 'Active';

      } else if (this.glData.glBasicDto.statusName === 'Active') {
        this.glData.glBasicDto.statusName = 'InActive';

      } else if (this.glData.glBasicDto.statusName === 'InActive') {
        this.glData.glBasicDto.statusName = 'Active';

      }
      this.glData.statusName = this.glData.glBasicDto.statusName;
      this.subscription = this.glAccountService.updateGL(this.glData).subscribe(savedwithStatus => {
        // this.glAccountService.glDetails.push(<GLAccount>savedwithStatus);
        this.router.navigate([Gl], {
          queryParams: {
            'Activate': 1, glcode: this.glData.glBasicDto.glCode,
            status: this.glData.statusName
          }
        });
      },
        error => alert(error),
        () => console.log('Finished')
      );

    }
  }
  save() {

    if (this.isValidForm()) {
      this.isShowModal = true;
      this.isActionPerformed = true;
      setTimeout(() => { this.isShowModal = false; }, 3000);
      // alert("partyName"+this.externalGlForm.value.partyName);
      // this.glData.glBasicDto= this.externalGlForm.value.glBasicDto;
      this.glData.glBasicDto = this.glBasicDto;
      // alert("===glBasicDto===>"+JSON.stringify(this.glData.glBasicDto));
      this.glData.externalGLMappingDto = this.externalGlForm.value.externalGLMapping;
      // alert("===externalGLMappingDto===>"+JSON.stringify(this.glData.externalGLMappingDto));
      this.glData.statusName = 'draft';
      // this.externalGlForm.contains(actionstatus);
      // alert( this.externalGlForm.contains(actionstatus));
      //  this.externalGlForms.statu
      // alert("this.gldata"+JSON.stringify(this.glData));
      // console.log("this.gldatanew"+JSON.stringify(this.glData));
      this.subscription = this.glAccountService.saveglDetails(this.glData).subscribe(glDetail => {

        this.router.navigate([Gl], { queryParams: { 'success': 1, glname: this.glData.glBasicDto.glName } });
        // this.router.navigate([Gl]);
      },
        error => {
          // alert(this.glAccountService.errorMessage);
          this.isDuplicateGLPopupModal = true;

        },
        () => console.log('Finished')
      );
    }

  }

  submit() {
    if (this.isValidForm()) {
      this.isShowModal = true;
      this.isActionPerformed = true;
      // if(
      // this.isActionPerformed = true){
      //     this.isShowModal = false;
      // }
      setTimeout(() => { this.isShowModal = false; }, 3000);
      this.glData.glBasicDto = this.glBasicDto;
      // alert("===glBasicDto===>"+JSON.stringify(this.glData.glBasicDto));
      this.glData.externalGLMappingDto = this.externalGlForm.value.externalGLMapping;
      // alert("===externalGLMappingDto===>"+JSON.stringify(this.glData.externalGLMappingDto));
      this.glData.statusName = 'active';
      this.subscription = this.glAccountService.submitGLDetails(this.glData).subscribe(addglDetail => {
        this.router.navigate([Gl], { queryParams: { 'success': 1, glname: this.glData.glBasicDto.glName } });
        // this.router.navigate([Gl]);
      },
        error => {
          // alert(this.glAccountService.errorMessage);
          this.isDuplicateGLPopupModal = true;

        },
        () => console.log('Finished')
      );
    }
  }

  reset(): void {
    //  this.glData = new GLAccount();
    //  this.glBasicDto = new GlBasicDTO();
    //  this.externalGLMappingDto = new ExternalGLMappingDto();
    // this.glData.externalGLMappingDto = new ExternalGLMappingDto();
    this.ngOnInit();
    this.glBasicDto.glIsGroup = 'N';
  }
  getDropDownList() {
    // alert("hfewfteri");
    this.subscription = this.glAccountService.getDropdownListJSON().finally(() => this.isComplete = true).subscribe(data => {
      // this.loadingData = true;
      // this.glAccountService.legalentityOption = data.body.legalentityOption;
      // this.glAccountService.glTypeOption = data.body.legalentityOption;
      // this.glAccountService.groupglOption = data.body.groupglOption;
      // this.glAccountService.parentglOption = data.body.parentglOption;
      //  this.glAccountService.externalglOption = data.body.externalglOption;
    }, error => {
      // console.log('Error Loading UOM Listing: ' + <any>error)
      this.loadingData = false;
    });
  }
  /*
    getParentGlDropdown() {
      if (this.glAccountService.glDetails == null)
        {
          this.subscription = this.glAccountService.getParentGlDropdown().finally(() => this.isComplete = true).subscribe(data => {
          //this.loadingData = true;
          alert("gldetailnow"+this.glAccountService.glDetails);
          this.glAccountService.glDetails = data.body;
         // alert("gldetailnow"+this.glAccountService.glDetails);
           this.glAccountService.glDetails.forEach(GLAccount => {
             // alert(GLAccount.glBasicDto.statusName);
            if ((GLAccount.glBasicDto.statusName === 'draft') || (GLAccount.glBasicDto.statusName ==='active')) {
              this.GLcodeOption.push({ label: GLAccount.glBasicDto.glCode, value: GLAccount.glBasicDto.glId });
            }
          });
      }, error => { throw error },
       // console.log('Error Loading UOM Listing: ' + <any>error)
       //this.loadingData = false;
   () => console.log('Finished')
      );
    }
    else {
        this.glAccountService.glDetails.forEach(GLAccount => {
          if (GLAccount.statusName === 'Active') {
            this.GLcodeOption.push({ label: GLAccount.glBasicDto.glCode, value: GLAccount.glBasicDto.glId });
          }
        });
      }
    } */


  getParentGlDropdown() {
    // alert("parentgl");
    this.subscription = this.glAccountService.getParentGlDropdown().finally(() => this.isComplete = true).subscribe(data => {
      // this.loadingData = true;
      this.parentGLName = data.body;
      // console.log("parentGLName"+this.parentGLName);
      // <ResponseData> geoLocation.body
    }, error => {
      // console.log('Error Loading UOM Listing: ' + <any>error)
      this.loadingData = false;
    });

  }


  getGLTypeDropdown() {
    // alert("gltype");
    this.subscription = this.glAccountService.getGLTypeDropdown().finally(() => this.isComplete = true).subscribe(data => {
      // this.loadingData = true;
      this.glTypeRefName = data.body;
      // console.log("gltype"+this.glTypeRefName);
      // <ResponseData> geoLocation.body
    }, error => {
      // console.log('Error Loading UOM Listing: ' + <any>error)
      this.loadingData = false;
    });
  }

  getLegalEntityDropdown() {
    // alert("gltype");
    //  alert("legal entity");
    this.subscription = this.glAccountService.getLegalEntityDropdown().finally(() => this.isComplete = true).subscribe(data => {
      // this.loadingData = true;
      this.legalentityOption = data.body;
      // console.log("legalentity"+this.legalentityOption);
      // console.log("legalentity12"+JSON.stringify(this.legalentityOption));
      // <ResponseData> geoLocation.body
    }, error => {
      // console.log('Error Loading UOM Listing: ' + <any>error)
      this.loadingData = false;
    });
  }

  getExternalSystemDropdown() {
    // alert("external");
    this.subscription = this.glAccountService.getExternalSystemDropdown().finally(() => this.isComplete = true).subscribe(data => {
      // this.loadingData = true;
      this.glAccountService.externalSystemRefName = data.body;
      console.log('  drop down ' + JSON.stringify(this.glAccountService.externalSystemRefName));

    }, error => {
      // console.log('Error Loading UOM Listing: ' + <any>error)
      this.loadingData = false;

    });
  }

  close() {
    // this.isDuplicateGLPopupModal = false;
    this.router.navigate([Gl]); // added for cancel button in edit GL
  }

  validate() {
    this.req_glCode = false;
    this.req_glName = false;
    this.partyName = false;
    this.partyType = false;

  }

  getStyle1() {
    if (this.partyName === true) {
      return '#d43c3c';
    } else {
      return '#cfdee7';
    }

  }
  getStyle() {
    if (this.req_glCode === true) {
      return '#d43c3c';
    } else {
      return '#cfdee7';
    }

  }
  getStyle2() {
    if (this.req_glName === true) {
      return '#d43c3c';
    } else {
      return '#cfdee7';


    }

  }

  getStyle3() {
    if (this.partyType === true) {
      return '#d43c3c';
    } else {
      return '#cfdee7';
    }
  }


  isValidForm(): boolean {
    if (this.glBasicDto.partyId === undefined) {
      this.partyName = true;
    } else {
      this.partyName = false;
    }

    if (this.glBasicDto.glTypeRefId === undefined) {
      this.partyType = true;
    } else {
      this.partyType = false;
    }

    if (this.glBasicDto.glCode === undefined || this.glBasicDto.glCode.length === 0) {
      this.req_glCode = true;
    } else {
      this.req_glCode = false;
    }

    if (this.glBasicDto.glName === '' || this.glBasicDto.glName === undefined) {
      this.req_glName = true;
    } else {
      this.req_glName = false;
    }

    if (this.req_glName == false && this.req_glCode == false || this.partyType == false) {
      return true;
    } else {
      this.countModal = true;
      setTimeout(() => { this.invalidCount.hide(); }, 3000);

      return false;

    }
  }

  canDeactivate(): boolean {
    return this.isActionPerformed;
  }


  addExternalGlMapping() {
    //  this.formarraylength++;

    const control = <FormArray>this.externalGlForm.controls['externalGLMapping'];
    this.showplus[this.showplus.length - 1] = true;
    this.showplus.push(false);
    this.showcross.push(false);
    if (this.showcross.length === 1) {
      this.showcross[0] = true;
    } else {
      this.showcross[0] = false;
    }

    const addrCtrl = this.initMapping();
    // alert("addrCtrl" +addrCtrl);
    control.push(addrCtrl);


  }

  initMapping() {
    return this.fb.group({
      externalGlMappingId: [],
      externalGlMappingName: [],
      mappingType: '',
      externalGlMappingCode: ''
    });
  }

  removePermissionMapping(i: number) {
    //  if (this.formarraylength !== 1  ) {
    if (this.getExternalMappingList(this.externalGlForm).length >= 1) {
      // this.isActionPerformed = true;
      const control = <FormArray>this.externalGlForm.controls['externalGLMapping'];
      control.removeAt(i);
      this.formarraylength--;
      // console.log(this.showplus[i]);
      this.showplus.splice(i, 1);
      this.showcross.splice(i, 1);
      if (this.showcross.length === 1) {
        this.showcross[0] = true;
      }

      if (this.showplus.indexOf(false) === -1) {
        this.showplus[this.showplus.length - 1] = false;

      }

    }
  }
  dropdownChange() {
    // alert(this.glBasicDto.glTypeRefId + '   ' + this.glBasicDto.glIsGroup);
    // console.log(' bre');
    // console.log(this.parentGLName);
    // console.log(typeof(this.parentGLName[0].glBasicDto.glTypeRefId), typeof(this.glBasicDto.glTypeRefId));
    if (this.glBasicDto.glTypeRefId === undefined || this.glBasicDto.glIsGroup === 'N') {
      this.disParent = false;
    } else {
      this.disParent = true;
    }
    this.parentGLName2 = this.parentGLName.filter(gl => gl.glBasicDto.glTypeRefId === Number(this.glBasicDto.glTypeRefId));
    console.log(' afre');
    console.log(this.parentGLName2);
  }

  ngOnDestroy() {
    // Called once, before the instance is destroyed.
    this.subscription.unsubscribe();
  }
  onHiddenReset() {
    //this.errorModal = false;

    // this to be set on click of ok button also
    //this.updatePopupModal = false;
    this.countModal = false;
    this.errormodalpart = false;
  }

}
