import { Component, Inject, OnInit, ViewEncapsulation, OnDestroy, Output, Input, ViewChild } from '@angular/core';
import { FormGroup, FormControl, Validators, FormArray, FormBuilder, ReactiveFormsModule, FormsModule } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { ResponseData } from '../../../../shared/interface/responseData';
import { HolidayCalendarService } from '../service/holiday-calendar.service';
import { HolidayCalendar } from '../holiday-calendar-interface';
import { SelectItem } from '../../../../shared/interface/selectItem';
import { ModalModule } from 'ngx-bootstrap';
import { ConfirmationService, Message } from 'primeng/primeng'; // message
import { HolidayCalendarMultipleComponent } from './holiday-calendar-multiple.component';
import { addOrigin } from '../../../../shared/interface/router-links';
import { holidayCalendar } from '../../../../shared/interface/router-links';
import { ModalDirective } from 'ngx-bootstrap/modal';
import { CanComponentDeactivate } from '../../../../shared/guards/can-deactivate-guard'


@Component({
  selector: 'app-add-holiday-calendar',
  templateUrl: './add-holiday-calendar.component.html',
  styleUrls: ['./add-holiday-calendar.component.css'],
    encapsulation: ViewEncapsulation.None,
})
export class AddHolidayCalendarComponent implements OnInit, OnDestroy, CanComponentDeactivate {
  @ViewChild('pagebackModal') public pagebackModal: ModalDirective;
  @ViewChild('statusChangePopupModal') public statusChangePopupModal: ModalDirective;
  @ViewChild('invalidCount') public invalidCount: ModalDirective;

  holidayCalendarGroupForm: FormGroup;
  holidayCalendarNgModelBinding: HolidayCalendar = new HolidayCalendar();
  holidayCalendarData: HolidayCalendar[];
  holidayCalendarMultipleData: HolidayCalendar[];
  holidayCalendarGroupData: HolidayCalendar = new HolidayCalendar();
  status: string;
  subscription: Subscription;
  isComplete: Boolean = false;
  loadingData: Boolean = false;
  geoName: SelectItem[] = [];
  originRegionCode: SelectItem[] = [];
  originRegionName: SelectItem[] = [];
  destinationSystem: SelectItem[] = [];
  validateRetVal: Boolean;
  public valMessage: string;
  public showModal;
  public invalidCountMsg;
  public errormodal;
  req_calendarName: boolean;
  req_calendarCode: boolean;
  req_country: boolean;
  req_holidayName: boolean;
  req_holidayDate: boolean;
  req_originRegionName: boolean;
  showcrossfromsecondrow: boolean;
  isActionPerformed = false;
  isDuplicateGLPopupModal: boolean;
  isStatusChangePopupModal: Boolean = false;


  showPlusHolidayList: boolean[] = [];
  showCrossHolidayList: boolean[] = [];
  public crossid;
  public isError;
  errorMessage: string;
  errorMessageForHoliday: String;
  public reqmsg: string;
  // checkdropdown: number = 0;
  public pattern;
  successModal = false;
  // display: Boolean = false;
  // display: boolean = false;
  checkdropdown: number;
  sizerollemapping: any;
  // checkdropdown: number = 0;
  msgs: Message[] = []; // message
  public isShowModal: boolean;
  originCountryResp: ResponseData;
  statusMsg: any;
  public holidaybyid;
  showcreate: boolean;
  showedit: boolean;
  showdraftbutton: boolean;
  showactivebutton: boolean;
  showinactivebutton: boolean;
  holidyId: Number;
  isEdit: Boolean = false;
  statusChange: String
  reqMsgCalendarCode = 'Calendar code is required';
  reqMsgCalendarName = 'Calendar Name is required';
  reqMsgCountry = 'Country is required ';

  public statuses: any = {
    isFirstOpen: true,
    isFirstDisabled: false
  };
  // tslint:disable-next-line:max-line-length
  constructor(private holidayCalendarService: HolidayCalendarService, private router: Router, private route: ActivatedRoute, private fb: FormBuilder) {
    // this.loadDropdownList();

  }

  ngOnInit() {
    window.scrollTo(0, 0);
    this.pagebackModal.hide();
    this.req_holidayName = false;

    this.getCountryDropDownList();
    this.showcreate = true;
    this.showedit = false;
    this.isStatusChangePopupModal = false;


    this.holidaybyid = this.route.snapshot.params['holidayCalId'];

    if (this.holidaybyid) {
      // alert('in edit part');
      this.isEdit = true;
      this.holidayCalendarGroupForm = this.fb.group({
        holidayCalName: ['', Validators.pattern('^[a-zA-Z0-9-_]*$')],
        holidayCalCode: ['', Validators.pattern('^[a-zA-Z0-9-_]*$')],
        holidayCalDesc: [''],
        geoId: [''],
        statusName: [''],
        holidayListDto: this.fb.array([]),
        deletedIds: this.fb.array([])

      });
      this.subscription = this.holidayCalendarService.getSingleHolidayListJSON(this.holidaybyid).subscribe(glList => {
        console.log(JSON.stringify(glList.body));
        this.holidayCalendarNgModelBinding = glList.body;

        // alert(JSON.stringify( this.holidayCalendarNgModelBinding))
        console.log('check for length' + this.holidayCalendarNgModelBinding.holidayListDto.length)

        for (let i = 0; i < this.holidayCalendarNgModelBinding.holidayListDto.length; i++) {
          this.holidayCalendarNgModelBinding.holidayListDto[i].holidayListDate =
            new Date(this.holidayCalendarNgModelBinding.holidayListDto[i].holidayListDate);
        }

        for (let i = 0; i < this.holidayCalendarNgModelBinding.holidayListDto.length; i++) {
          this.addHolidayListMultiple();
        }
        if (this.holidayCalendarNgModelBinding.statusName === 'Draft') {
          this.showdraftbutton = true;
          this.showactivebutton = false;
          this.showinactivebutton = false;
        } else if (this.holidayCalendarNgModelBinding.statusName === 'Active') {
          this.showactivebutton = true;
          this.showdraftbutton = false;
          this.showinactivebutton = false;
        } else if (this.holidayCalendarNgModelBinding.statusName === 'InActive') {
          this.showinactivebutton = true;
          this.showactivebutton = false;
          this.showdraftbutton = false;
        }
      },
        error => { throw error });
      // this.isActionPerformed = true;
      this.showcreate = false;
      this.showedit = true;
    } else {
      // alert('in add part');
      this.holidayCalendarGroupForm = this.fb.group({
        holidayCalName: ['', Validators.pattern('^[a-zA-Z0-9-_]*$')],
        holidayCalCode: ['', Validators.pattern('^[a-zA-Z0-9-_]*$')],
        holidayCalDesc: [''],
        geoId: [''],
        holidayListDto: this.fb.array([]),
        deletedIds: this.fb.array([])

      });
      this.addHolidayListMultiple();
    }
    this.req_holidayDate = true;
    this.req_holidayName = true;
    this.req_calendarCode = false;
    this.req_calendarName = false;
    this.req_country = false;
  }

  saveHolidayCalendar() {
    // this.isActionPerformed = true;
   // alert(JSON.stringify(this.holidayCalendarGroupForm.value))
    if (this.isValidForm()) {
      this.isShowModal = true;
      // setTimeout(() => {this.isShowModal = false; }, 3000);
      // this.HolidayCalendarNgModelBinding.holidayListDto= this.holidayCalendarGroupForm.value.holidayListDto;
      // alert("===holidayListDto===>"+JSON.stringify(this.glData.holidayListDto));
      this.holidayCalendarNgModelBinding.statusName = 'draft';
      this.holidayCalendarGroupForm.value.statusName = 'draft';
      this.holidayCalendarService.originCodeParam = this.holidayCalendarGroupForm.value.holidayCalName;
      this.holidayCalendarNgModelBinding.holidayListDto = this.holidayCalendarGroupForm.value;

      this.holidayCalendarNgModelBinding.holidayCalName = this.holidayCalendarGroupForm.value.holidayCalName;

      this.subscription = this.holidayCalendarService.saveHolidayCalendarService
        (this.holidayCalendarGroupForm.value).subscribe(holidayCalendarSaveDetails => {
          // this.holidayCalendarService.holidayCalendarSaveDetails = <HolidayCalendar[]>holidayCalendarSaveDetails;
          //  alert( JSON.stringify('testing' + this.HolidayCalendarNgModelBinding))
          // console.log(this.holidayCalendarNgModelBinding);
          //  console.log(JSON.stringify(this.holidayCalendarNgModelBinding));
          this.isActionPerformed = true;
          this.router.navigate([holidayCalendar], { queryParams: { 'success': 1 } });
          // this.router.navigate([holidayCalendar])
          //   this.router.navigate([holidayCalendar],
          // {queryParams: {'success': 1 , holidayCalName: this.HolidayCalendarNgModelBinding.holidayCalName}});

        },
        error => {
          this.pagebackModal.show();
          this.errorMessageForHoliday = this.holidayCalendarService.errorMessage;
          // alert(this.holidayCalendarService.errorMessage);
          // this.router.navigate([holidayCalendar], { queryParams: { 'success': 1 } });
          this.isDuplicateGLPopupModal = true;

        },
        () => console.log('Finished')
        );
    }

  }

  submitHolidayCalendar() {
    if (this.isValidForm()) {
      this.isShowModal = true;
      this.holidayCalendarNgModelBinding.holidayListDto = this.holidayCalendarGroupForm.value;
      this.holidayCalendarService.originCodeParam = this.holidayCalendarGroupForm.value.holidayCalName;
      this.holidayCalendarGroupForm.value.statusName = 'save';
      this.subscription = this.holidayCalendarService.submitHolidayCalendarService(this.holidayCalendarGroupForm.value).
        subscribe(holidayCalendarSaveDetails => {
          // this.router.navigate([holidayCalendar])
          this.isActionPerformed = true;
          this.router.navigate([holidayCalendar], { queryParams: { 'success': 1 } });
        },
        error => {

          this.pagebackModal.show();
          this.errorMessageForHoliday = this.holidayCalendarService.errorMessage;
          // alert(this.glAccountService.errorMessage);
          this.isDuplicateGLPopupModal = true;

        },
        () => console.log('Finished')
        );
    }
  }

  getCountryDropDownList() {

    this.subscription = this.holidayCalendarService.getOriginDropDownJSON().subscribe(geoLocation => {
      this.originCountryResp = <ResponseData>geoLocation.body;
      // this.router.navigate([origin],{ queryParams: { "success": 1 } });
      //  this.router.navigate([origin]);
    },
      error => {
        // alert(error )
        // this.pagebackModal.show();
        this.holidayCalendarService = this.holidayCalendarService.errorMessage;
      },
      () => console.log('Finished')
    )
  }

  // tslint:disable-next-line:member-ordering
  updateHolidayCal(holidayid, status) {

    console.log('in update' + JSON.stringify(this.holidayCalendarGroupForm.value))
    // this.holidayCalendarNgModelBinding.holidayCalId = holidayid;
    // this.holidayCalendarNgModelBinding.statusName = status;
    // console.log('updated JSON in Update button' + JSON.stringify(this.holidayCalendarNgModelBinding));
    if (this.isValidForm()) {
      
      this.holidayCalendarGroupForm.value.holidayCalId = holidayid;
      this.holidayCalendarGroupForm.value.statusName = status;
      // this.holidayCalendarNgModelBinding = this.holidayCalendarGroupForm.value;
      console.log(this.holidayCalendarGroupForm.value);
      console.log(typeof (this.holidayCalendarGroupForm.value));
      this.subscription = this.holidayCalendarService.updateHolidayCal(this.holidayCalendarGroupForm.value).subscribe(savedData => {
        // this.glAccountService.glDetails.push(<GLAccount>savedData);
        this.isActionPerformed = true;
        this.router.navigate([holidayCalendar], {
          queryParams:
          { 'isUpdate': 1, holidayCalName: this.holidayCalendarNgModelBinding.holidayCalName }
        });

      },
        error => {
          this.pagebackModal.show();
          this.errorMessageForHoliday = this.holidayCalendarService.errorMessage;
        },
        () => console.log('Finished')

      );

    }

  }

  // alert(statusName);

  onHiddenpopup() {
    this.isStatusChangePopupModal = false;
    this.errormodal = false;
    // this.isDeactivatePopupModal = false;
    // this.successModal.hide();
  }

  conf_update(selection: boolean) {
    if (selection) {
      // this.statusChangePopupModal.show();
      this.updatewithStatus();
      // alert('12')
    } else {
      this.statusChangePopupModal.hide();
      // this.isStatusChangePopupModal = false;
    }

  }
  updateHolidayCalwithStatus(holidayid, status) {
    // this.holidayCalendarNgModelBinding.holidayCalId = holidayid;
    // this.holidayCalendarNgModelBinding.statusName = status;
    // console.log('updated JSON in Update button with status' + JSON.stringify(this.holidayCalendarNgModelBinding));
    this.isStatusChangePopupModal = true;
    this.holidyId = holidayid;
    this.statusChange = status;
    if (this.statusChange === 'Active') {
      this.statusMsg = 'Please Confirm to Deactivate ' + this.holidayCalendarNgModelBinding.holidayCalName ;
    } else {
      this.statusMsg = 'Please Confirm to Activate '  + this.holidayCalendarNgModelBinding.holidayCalName ;

    }
  }

  updatewithStatus() {
    // for deactivate and reactivate buttons
    let actvataionStatus = 1;
    if (this.isValidForm()) {
      
      this.holidayCalendarGroupForm.value.holidayCalId = this.holidyId;
      this.holidayCalendarGroupForm.value.statusName = this.statusChange;
      if (this.holidayCalendarNgModelBinding.statusName === 'Draft') {
        // this.holidayCalendarNgModelBinding.statusName = 'Active';
        this.holidayCalendarGroupForm.value.statusName = 'Active';

      } else if (this.holidayCalendarNgModelBinding.statusName === 'Active') {
        // this.holidayCalendarNgModelBinding.statusName = 'InActive';
        this.holidayCalendarGroupForm.value.statusName = 'InActive';
        actvataionStatus = 2;

      } else if (this.holidayCalendarNgModelBinding.statusName === 'InActive') {
        // this.holidayCalendarNgModelBinding.statusName = 'Active';
        this.holidayCalendarGroupForm.value.statusName = 'Active';

      }
      console.log('in update' + JSON.stringify(this.holidayCalendarGroupForm.value))
      console.log(typeof (this.holidayCalendarGroupForm.value));
      
      this.subscription = this.holidayCalendarService.updateHolidayCal(this.holidayCalendarGroupForm.value).subscribe(savedData => {
        // this.glAccountService.glDetails.push(<GLAccount>savedData);
        this.isActionPerformed = true;
        this.router.navigate([holidayCalendar], {
          queryParams:
          { 'Activate': actvataionStatus, holidayCalName: this.holidayCalendarNgModelBinding.holidayCalName }
        });

      },
        error => {
          this.pagebackModal.show();
          this.errorMessageForHoliday = this.holidayCalendarService.errorMessage;
        } ,
        () => console.log('Finished')
      ); // alert(' End inside aman update ');

    }

  }


  addHolidayListMultiple() {
    // alert('in add multiple row');
    const control = <FormArray>this.holidayCalendarGroupForm.controls['holidayListDto'];
    const addrCtrl = this.initHolidayCalendarMapping();
    control.push(addrCtrl);

    this.showPlusHolidayList[this.showPlusHolidayList.length - 1] = true;
    this.showPlusHolidayList.push(false);
    this.showCrossHolidayList.push(false);
    if (this.showCrossHolidayList.length === 1) {
      this.showCrossHolidayList[0] = true;
    } else {
      this.showCrossHolidayList[0] = false;
    }

    this.req_holidayName = true;
    this.req_holidayDate = true;
  }
  /*      addPermissionMapping() {
        const control  = <FormArray>this.holidayCalendarGroupForm.controls['holidayListDto'];
        const addrCtrl = this.initHolidayCalendarMapping();
        control.push(addrCtrl);
    }*/

  removeHolidayListMultiple(i: number) {
    if (this.getHolidayMultipleList(this.holidayCalendarGroupForm).length >= 1) {
      const control = <FormArray>this.holidayCalendarGroupForm.controls['holidayListDto'];
      // alert(this.holidayCalendarGroupForm.value.holidayListDto[i].holidayListId)

      if (Number(this.holidayCalendarGroupForm.value.holidayListDto[i].holidayListId) !== 0) {
        this.holidayCalendarGroupForm.value.deletedIds.push(Number(this.holidayCalendarGroupForm.value.holidayListDto[i].holidayListId));
    }
        if (this.isEdit) {
          this.holidayCalendarNgModelBinding.holidayListDto.splice(i, 1);
        }
        //  this.holidayCalendarGroupForm.value.holidayListDto.splice(i, 1);
        /*this.showPlusHolidayList.splice(i, 1);
        this.showCrossHolidayList.splice(i, 1);*/
      control.removeAt(i);
      // }
      // console.log(this.showPlusDefaultPacking[i]);

      // control.removeAt(i);
      this.showPlusHolidayList.splice(i, 1);
      this.showCrossHolidayList.splice(i, 1);
      if (this.showCrossHolidayList.length === 1) {
        this.showCrossHolidayList[0] = true;
      }

      if (this.showPlusHolidayList.indexOf(false) === -1) {
        this.showPlusHolidayList[this.showPlusHolidayList.length - 1] = false;

      }
      // console.log(this.showplus)

    }
  }

  getHolidayMultipleList(holidayCalendarGroupForm) {
    return holidayCalendarGroupForm.get('holidayListDto').controls;
  }

  initHolidayCalendarMapping() {
    // alert('in init holiday mapping');
    return this.fb.group({
      holidayListId: '',
      holidayListName: '',
      holidayListDate: '',
      // 'deletedIds': this.fb.array([
      //   ])
      // holidayListName: [],
      // holidayListDate: []
    });
  }

  validate(select: Number) {
    // this.req_holidayDate = false;
    // this.req_holidayName = false;

    if (select === 0) {
      this.req_calendarCode = false;
    } else if (select === 1) {
      this.req_calendarName = false;
    } else if (select === 2) {
      this.req_country = false;
    }

  }
  reset(): void {
    this.holidayCalendarGroupForm.reset();
    // console.log(JSON.stringify(this.originDefinitionDetails));
    this.req_holidayDate = true;
    this.req_holidayName = true;
    this.req_calendarCode = false;
    this.req_calendarName = false;
    this.req_country = false;
    this.holidayCalendarNgModelBinding = new HolidayCalendar();
  }
  showDialog() {
    this.msgs = [];
    this.msgs.push({ severity: 'success', summary: 'Success Message', detail: 'Order submitted' });
    // this.display = true;
  }

  close() {
    this.pagebackModal.hide();
    // this.showModal=false;
  }


  back() {
    this.router.navigate([holidayCalendar]);
  }

  getStyleCode() {
    if (this.req_calendarCode === true) {
      return '#d43c3c';
    } else {
      return '#cfdee7';
    }
  }

  getStyleName() {
    if (this.req_calendarName === true) {
      return '#d43c3c';
    } else {
      return '#cfdee7';
    }
  }

  getStyleCountry() {
    if (this.req_country === true) {
      return '#d43c3c';
    } else {
      return '#cfdee7';
    }
  }
   isValidForm() {
    this.reqmsg = ''; this.checkdropdown = 0;
    const sizerollemapping = Number(JSON.stringify(this.holidayCalendarGroupForm.value.holidayListDto.length));

    // Checking th pattern for code and Name
    if (this.holidayCalendarGroupForm.controls.holidayCalCode.hasError('pattern')) {
      this.errorMessageForHoliday = 'Invalid Calendar Code';
      this.pagebackModal.show();
      return false;
    } else if (this.holidayCalendarGroupForm.controls.holidayCalName.hasError('pattern')) {
      this.errorMessageForHoliday = 'Invalid Calendar Name';
      this.pagebackModal.show();
      return false;
    }




    if (this.holidayCalendarGroupForm.value.holidayCalCode === '' || this.holidayCalendarGroupForm.value.holidayCalCode == null) {
      this.reqmsg += 'Calendar Code ,';
      this.req_calendarCode = true;
      this.checkdropdown++;
    } else if (this.holidayCalendarGroupForm.value.holidayCalCode != null) {
      if (this.holidayCalendarGroupForm.value.holidayCalCode.length > 50) {
        this.reqmsg += 'Calendar Code maximum length is 20 and below ,';
        this.req_calendarCode = true;
        this.checkdropdown++;
      }

    }
    // if (this.holidayCalendarGroupForm.value.holidayCalName === '' || this.holidayCalendarGroupForm.value.holidayCalName == null) {
    if (!this.holidayCalendarGroupForm.value.holidayCalName) {
      this.reqmsg += 'Calendar Name';
      this.req_calendarName = true;
      this.checkdropdown++;
    }  // else if (this.holidayCalendarGroupForm.value.holidayCalName.length > 200) {
    // this.reqmsg += 'Calendar Name maximum length is 200 and below,';
    // this.req_calendarName = true;
    // this.checkdropdown++;
    //  }
    // if (this.holidayCalendarGroupForm.value.geoId === '' || this.holidayCalendarGroupForm.value.geoId == null) {
    if (!this.holidayCalendarGroupForm.value.geoId) {
      this.reqmsg += 'Country';
      this.req_country = true;
      this.checkdropdown++;
    } // else if (this.holidayCalendarGroupForm.value.geoId.length > 200) {
    // this.reqmsg += 'Country maximum length is 200 and below,';
    // this.req_country = true;
    // this.checkdropdown++;
    //   }
    





    if (this.holidayCalendarGroupForm.value.holidayListDto != null) {
      this.holidayCalendarService.setHolidayName(this.holidayCalendarGroupForm.value.holidayListDto);

    }
    if (sizerollemapping >= 1) {

      for (let i = 0; i < sizerollemapping; i++) {
        if (this.holidayCalendarGroupForm.value.holidayListDto[i] == null) {

          this.reqmsg += 'Holiday List,';
          this.checkdropdown++;
        } else if (this.holidayCalendarGroupForm.value.holidayListDto[i] !== null) {
          if (this.holidayCalendarGroupForm.value.holidayListDto[i].holidayListName == null ||
            this.holidayCalendarGroupForm.value.holidayListDto[i].holidayListName === '') {
            // if ( ! this.holidayCalendarGroupForm.value.holidayListDto[i].holidayListName) {
            this.reqmsg += 'Holiday Name , ';
            this.req_holidayName = false;
            this.checkdropdown++;
          }
          if (this.holidayCalendarGroupForm.value.holidayListDto[i].holidayListDate == null ||
            this.holidayCalendarGroupForm.value.holidayListDto[i].holidayListDate === '') {
            // if (! this.holidayCalendarGroupForm.value.holidayListDto[i].holidayListDate) {
            this.reqmsg += 'Date ,';
            this.req_holidayDate = false;
            this.checkdropdown++;
          }
        }
      }
      this.holidayCalendarGroupData = this.holidayCalendarGroupForm.value;
      /* for (let i = 0; i <  sizerollemapping ; i++) {
           for (let j = i + 1 ; j <  sizerollemapping ; j++) {
              console.log('// check duplicate role mapping' );
              if (this.holidayCalendarGroupData.holidayListDto[i].holidayListName ===
                      this.holidayCalendarGroupData.holidayListDto[j].holidayListName  &&
                 this.holidayCalendarGroupData.holidayListDto[i].holidayListDate ===
                 this.holidayCalendarGroupData.holidayListDto[j].holidayListDate
                  ) {
                      console.log('// duplicate occured in role mapping')
                      this.errorMessageForHoliday = ' Role Mapping must be Unique ' ;
                      this.isError = true;
                      this.checkdropdown++;
              }
        }
      }  */    // multi block comment end of above
    }
    if (this.checkdropdown === 0) {
      const idsArray = new Array();
      this.holidayCalendarGroupData.holidayListDto.forEach(x => idsArray.push(x.holidayListName.trim().toLowerCase() +
      x.holidayListDate.getTime() ))

     // alert(idsArray.length);
     // alert(new Set(idsArray).size);
      // const dates = new Array();
      // // console.log(this.holidayCalendarGroupData.holidayListDto);
      // this.holidayCalendarGroupData.holidayListDto.forEach(x => dates.push(x.holidayListDate))

      // console.log('inside the valid');
      // console.log(dates);

      if (idsArray.length !== new Set(idsArray).size) {
        this.errorMessageForHoliday = 'Duplicate Holiday Name and Holiday Date  is not allowed';
        this.pagebackModal.show();
      }else {
        return true;
      }
    } else {
        this.invalidCountMsg = this.checkdropdown + ' Mandatory Fields are required';
        this.errormodal = true;
        // this.invalidCount.show();
        // setTimeout({this.invalidCountMsg.hide(), 3000})
        setTimeout(() => {this.invalidCount.hide(); }, 3000);
      return false;
    }

  }
  canDeactivate(): boolean {
    return this.isActionPerformed;
  }
  // close() {
  // this.router.navigate([holidayCalendar])
  // }
  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

}
