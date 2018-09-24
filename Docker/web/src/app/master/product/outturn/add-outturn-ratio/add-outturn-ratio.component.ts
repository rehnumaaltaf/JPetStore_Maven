import { Component, Inject, OnInit, OnDestroy, Output, Input, ViewChild, ViewEncapsulation } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormGroup, FormControl, FormsModule, FormBuilder, FormArray } from '@angular/forms';
import { AccordionModule } from 'ngx-bootstrap';
import { OriginDefinition } from '../../origin-definition/origin-definition';
import { OriginDefinitionService } from '../../origin-definition/service/origin-definition.service';
import { CanComponentDeactivate } from '../../../../shared/guards/can-deactivate-guard';
import { Subscription } from 'rxjs/Subscription';
import { Outturn } from '../outturn';
import { OutturnRation, OutturnRawGradeDto, Grade, Product, Origin } from '../outturn';
import { OutturnService } from '../service/outturn.service';
import { out } from '../../../../shared/interface/router-links';
import { ModalDirective } from 'ngx-bootstrap/modal';

@Component({
  selector: 'app-add-outturn-ratio',
  templateUrl: './add-outturn-ratio.component.html',
  styleUrls: ['./add-outturn-ratio.component.css'],
  encapsulation: ViewEncapsulation.None
})

export class AddOutturnRatioComponent implements OnInit, OnDestroy, CanComponentDeactivate {
  @ViewChild('statusChangePopupModal') public statusChangePopupModal: ModalDirective;
  @ViewChild('errorChangePopupModal') public errorChangePopupModal: ModalDirective;
  @ViewChild('invalidCount') public invalidCount: ModalDirective;

  public status: any = {
    isFirstOpen: true,
    isFirstDisabled: false
  };
  // origindetial: OriginDefinition[] = Array(new OriginDefinition);
  outturn: Outturn = new Outturn();
  OutturnData: Outturn = new Outturn();
  outturnRawGradeDto: OutturnRawGradeDto = new OutturnRawGradeDto();
  outturnRatioDto: OutturnRation = new OutturnRation();
  origindetial: Origin[];
  prductDetail: Product[];
  rawgradeDetail: Grade[] = new Array();
  title = 'Add Out-Turn Ratio';
  public status1;
  subscription: Subscription;
  outurn: Outturn;
  rawGrades: Grade[] = new Array();
  productfilter: Product[] = new Array();
  gradename: Grade[] = new Array();
  finshGrades: Grade[] = new Array();
  finshRemainGrades: Grade[];
  finshSelctdGrades: Grade[];
  outturnForm: FormGroup;
  formarraylength: Number = 0;
  bearRatio: boolean;
  showcross: boolean[] = [];
  showplus: boolean[] = [];
  private editId;
  productid = Number;
  product: boolean;
  origin: boolean;
  raw: boolean;
  finished: boolean;
  quantity: boolean;
  isActionPerformed = false;
  checkdropdown: number;
  colors: String[] = new Array;
  colors1: String[] = new Array;
  colors2: String[] = new Array;
  AddEditoutturnId: number;
  public invalidCountMsg;
  public isEditPage: Boolean = false;
  public draft;
  public active;
  public inactive;
  public errormodalpart;
  public errorModal = false;
  public deleteIds: Number[] = Array();
  isStatusChangePopupModal: Boolean = false;
  errorPopupModal: Boolean = false;
  validationError: String;
  public count;

  public statusName: String;
  requiedMesssage: String;
  /*public grades = [
    new Grade(1, 'Br01', 'R'),
    new Grade(2, 'It01', 'F'),
    new Grade(3, 'Sig01apure', 'F'),
    new Grade(4, 'In01', 'R')
  ]; */

  constructor(private OriginService: OriginDefinitionService, public outturnservice: OutturnService, private fb: FormBuilder,
    private router: Router, private route: ActivatedRoute) {
    this.outurn = new Outturn()
    this.outurn.outturnRawGradeDto = new OutturnRawGradeDto();
    this.outurn.outturnRatioDto = Array(new OutturnRation);
  }

  ngOnInit() {
    this.isStatusChangePopupModal = false;
    this.errorPopupModal = false;
    this.requiedMesssage = 'is required'

    if (this.route.snapshot.params['id'] !== undefined) {
      this.editId = this.route.snapshot.params['id'];
      this.AddEditoutturnId = this.editId;
      this.isEditPage = true;
      this.buildoutturnForm();
      this.getDataById(this.editId);
      this.title = 'Edit Out-Turn Ratio';
      // this.outturnForm = null;
      this.outturnForm.reset();
      this.deleteIds = [];
    } else {
      this.AddEditoutturnId = 0;
      this.buildoutturnForm();
      // this.addSecPackMapping();
    }

    this.loadorigindetail();
    this.loadproductdetail();
    this.loadrawgradedetail();

    // alert('gradeDetail');
    // alert(this.rawgradeDetail);

    // this.rawGrades = this.rawgradeDetail.filter(grad => grad.gradeIsRaw === 'T');
    // alert('raw');
    // alert(this.rawGrades);
    // this.finshGrades = this.rawgradeDetail.filter(grad => grad.gradeIsRaw === 'F');
    // alert('finish');
    // alert(this.finshGrades)
    //  this.finshRemainGrades = this.finshGrades;
    // this.addoutturn(-1);
    this.addoutturn();

  }


  getDataById(editId) {
    this.outturnservice.getoutturnDataById('', editId).subscribe(data => {
      if (data.body !== null && data.body !== '') {
        this.outturn = data.body;

        if (this.outturn.statusName === 'Draft') {
          this.draft = true;
          this.active = false;
          this.inactive = false;
        } else if (this.outturn.statusName === 'Active') {
          this.draft = false;
          this.active = true;
          this.inactive = false;
        } else {
          this.draft = false;
          this.active = false;
          this.inactive = true;
        }
        // this.editListDetails();
        // alert(JSON.stringify(this.outturn));

        this.patchForm();
      }
    }, error => { console.log(JSON.stringify(error)) });
    this.isActionPerformed = true;
    // console.log('indie getdata by id');
    // console.log(this.outturn);
  }



  buildoutturnForm(): void {
    // alert('inside buildform');
    this.outturnForm =
      this.fb.group({
        outturnRawGradeId: [this.outturn.outturnRawGradeDto.outturnRawGradeId],
        statusName: [this.outturn.outturnRawGradeDto.statusName],
        prodId: [this.outturn.outturnRawGradeDto.prodId],
        prodName: [this.outturn.outturnRawGradeDto.prodName],
        originId: [this.outturn.outturnRawGradeDto.originId],
        gradeId: [this.outturn.outturnRawGradeDto.gradeId],
        outturnRationArray: this.fb.array([])
      });
  }

  /*
     editListDetails() {
       alert('edit detail');
       alert(this.outurn.outturnRawGradeDto.prodId);
       this.outturnForm.controls['prodId'].setValue(this.outurn.outturnRawGradeDto.prodId);
       this.outturnForm.controls['originId'].setValue(this.outurn.outturnRawGradeDto.originId);
      this.outturnForm.controls['gradeId'].setValue(this.outurn.outturnRawGradeDto.gradeId);
      console.log('setting the value');
      console.log(this.outturnForm);
     // console.log(JSON.stringify(this.outturnForm.controls['prodId'].setValue(7)));
     }  */
  // this.outurn.outturnRawGradeDto.prodId

  patchForm() {
    // alert("ptach form alery");
    // alert(this.outturn.outturnRawGradeDto.prodId);
    // alert(this.outturn.outturnRawGradeDto.originId);
    //  alert(this.outturn.outturnRawGradeDto.gradeId);

    this.outturnForm.patchValue({
      outturnRawGradeId: this.outturn.outturnRawGradeDto.outturnRawGradeId,
      statusName: this.outturn.outturnRawGradeDto.statusName,
      prodId: this.outturn.outturnRawGradeDto.prodId,
      originId: this.outturn.outturnRawGradeDto.originId,
      gradeId: this.outturn.outturnRawGradeDto.gradeId
    })
    // console.log("my coso");
    //  console.log(this.outturnForm);
    this.setoutturnRationArray();
  }


  /* loadorigindetail() {
     this.subscription = this.OriginService.getOriginDefinitionJSON().subscribe(originDetails => {
       this.origindetial = originDetails.body;
      // console.log(this.origindetial);
     });
   } */
  // finishedGradeId: '',
  //     quantityRatio: '',
  //     abilityToBearRatio: ''

  setoutturnRationArray() {
    const control = <FormArray>this.outturnForm.controls.outturnRationArray;
    this.outturn.outturnRatioDto.forEach(x => {
      control.push(this.fb.group({
        finishedGradeId: x.finishedGradeId, quantityRatio: x.quantityRatio,
        abilityToBearRatio: x.abilityToBearRatio, outturnRatioId: x.outturnRatioId
      }))
      this.colors.push('#cfdee7');
      this.colors1.push('#cfdee7');
      this.colors2.push('#cfdee7');
    })
  }

  loadorigindetail() {
    this.subscription = this.outturnservice.getOriginDetail().subscribe(originDetails => {
      this.origindetial = originDetails.body;
      //  console.log('origindetail' + JSON.stringify(this.origindetial));
     //  this.originfilter = this.origindetial.filter(prod => prod.statusName === 'Active');
    });
  }


  loadproductdetail() {
    this.subscription = this.outturnservice.getProductDetail().subscribe(products => {
      this.prductDetail = products.body;
      // console.log(this.prductDetail);
      this.productfilter = this.prductDetail.filter(prod => prod.statusName === 'Active');
    });

  }


  loadrawgradedetail() {

    this.productid = this.outturnForm.value.prodId;
    this.subscription = this.outturnservice.getgradeDetail().subscribe(grade => {
      this.rawgradeDetail = grade.body;
      // console.log('inside loadraw grade details');
      //  console.log(this.rawgradeDetail);

      // alert('raw grd details');
      // alert(this.rawgradeDetail);

      this.rawGrades = this.rawgradeDetail.filter(grad => grad.gradeIsRaw === 'Y' && grad.statusName === 'Active');
      this.finshGrades = this.rawgradeDetail.filter(grad => grad.gradeIsRaw === 'N' && grad.statusName === 'Active' );
    });
  }

  getStyle() {
    if (this.product === true) {
      return '#d43c3c';
    } else {
      return '#cfdee7';
    }

  }
  getStyle1() {
    if (this.origin === true) {
      return '#d43c3c';
    } else {
      return '#cfdee7';
    }

  }

  getStyle2() {
    if (this.raw === true) {
      return '#d43c3c';
    } else {
      return '#cfdee7';
    }

  }


  getStyle3(i) {
    // alert(this.outturnForm.controls.outturnRationArray[i].controls.gradeId.value)
    // alert("value" + i);
    if (this.finished === true) {
      // return '#d43c3c';
      this.colors[i] = '#d43c3c'
    } else {
      this.colors[i] = '#cfdee7';
    }

  }

  getStyle4(i) {
    if (this.quantity === true) {
      // return '#d43c3c';
      this.colors1[i] = '#d43c3c'
    } else {
      // return '#cfdee7';
      this.colors1[i] = '#d43c3c'
    }

  }

  getStyle5(i) {
    if (this.bearRatio === true) {
      //  return '#d43c3c';
      this.colors2[i] = '#d43c3c'
    } else {
      //  return '#cfdee7';
      this.colors2[i] = '#d43c3c'
    }

  }


  validate(i?: number) {
    // this.bearRatio = false;
    // this.quantity = false;
    // this.finished = false;
    this.raw = false;
    this.origin = false;
    this.product = false;
    this.colors1[i] = '#cfdee7';
    this.colors[i] = '#cfdee7';
    this.colors2[i] = '#cfdee7';
  }

  isValidForm(): boolean {

    // this.checkdropdown = 0;

    const outturnarrayList = Number(JSON.stringify(this.outturnForm.value.outturnRationArray.length));
    this.count = 0;
    // console.log('consoling outurn array ');
    // alert("outturnarrayList" + outturnarrayList);
    // console.log(this.outturnForm.controls.outturnRationArray[0].controls);
    // console.log(typeof(this.outturnForm.controls.outturnRationArray[0].controls));
    // alert('inside isValidForm');
    // alert(this.outurn.outturnRawGradeDto.prodId);
    // alert(this.outturnForm.value.prodId);
    if (!this.outturnForm.value.prodId) {
      // alert(' product id verification')
      this.product = true;
      this.count++;
    } else {
      this.product = false;
    }

    if (!this.outturnForm.value.originId) { // === undefined || this.outturnForm.value.originId === null ) {
      this.origin = true;
      this.count++;
    } else {
      this.origin = false;
    }

    if (!this.outturnForm.value.gradeId) { // === undefined || this.outturnForm.value.gradeId === 0) {
      this.raw = true;
      this.count++;
    } else {
      this.raw = false;
    }


    if (outturnarrayList >= 1) {

      for (let i = 0; i < outturnarrayList; i++) {
        // console.log(' insdie valid form')
        // console.log(this.outturnForm.value.outturnRationArray[i]);
        //  alert(this.outturnForm.value.outturnRationArray[i].quantityRatio);
        //  console.log(typeof(this.outturnForm.value.outturnRationArray[i].quantityRatio));
        //  alert(this.outturnForm.value.outturnRationArray[i].gradeId);
        //  console.log(typeof(this.outturnForm.value.outturnRationArray[i].gradeId));

        //  alert(this.outturnForm.value.outturnRationArray[i].abilityToBearRatio);
        //  console.log(typeof(this.outturnForm.value.outturnRationArray[i].abilityToBearRatio));
        //  break;
        if (!this.outturnForm.value.outturnRationArray[i].quantityRatio) {

          this.quantity = true;
          this.colors1[i] = '#d43c3c';
          this.count++;
        } else {
          this.quantity = false;
          this.colors1[i] = '#cfdee7';
        }
        // this.reqmsg += 'Holiday Name , ';
        // this.checkdropdown++;

        if (!this.outturnForm.value.outturnRationArray[i].finishedGradeId) {
          this.finished = true;
          this.colors[i] = '#d43c3c';
          this.count++;
        } else {
          this.finished = false;
          this.colors[i] = '#cfdee7';
        }
        // this.reqmsg += 'Date ,';
        //  this.checkdropdown++;
        if (!this.outturnForm.value.outturnRationArray[i].abilityToBearRatio) {
          this.bearRatio = true;
          this.colors2[i] = '#d43c3c';
          this.count++;
        } else {
          this.bearRatio = false;
          this.colors2[i] = '#cfdee7';
        }
        // this.reqmsg += 'Date ,';
        // this.checkdropdown++;
      }
    }
    // this.OutturnData = this.outturnForm.value;

    // checking duplicate grade code in 2nd accordian
    const gradCd: Number[] = new Array;
    // this.outturnForm.controls.outturnRationArray[0].controls.forEach(element => {
    //   if (element.gradeId !== '') {
    //     gradCd.push(element.gradeId)
    //   }
    // });
    const percentage: Number[] = new Array;

    for (const element of this.outturnForm.value.outturnRationArray) {
      if (element.finishedGradeId !== '') {
        gradCd.push(Number(element.finishedGradeId));
      }
    }

    //  alert(gradCd.length);
    //  console.log(gradCd)
    //  alert(new Set(gradCd).size);
    // console.log(new Set(gradCd))
    if (gradCd.length !== new Set(gradCd).size) {
      //  alert('Please check deplicate Fisnished grade code are used ')
      this.validationError = 'Please check deplicate Fisnished grade code are used';
      this.errorPopupModal = true;
      return false;
    }

    // quantity radio total
    let quantitypercent: Number = 0;

    // alert("quantity radio" + this.outturnForm.value.outturnRationArray.quantityRatio);
    for (const percent of this.outturnForm.value.outturnRationArray) {
      if (percent.quantityRatio !== 0) {
        // console.log('quantityradio');
        //  console.log(typeof(percent.quantityRatio));
        //  alert(percent.quantityRatio);
        // const val = percent.quantityRatio;
        quantitypercent = (Number(quantitypercent) + Number(percent.quantityRatio));
        // alert('sum value' + quantitypercent);
      }
    }


    if (quantitypercent > 100) {
      //  alert('Total quantity-radio shouldnot be greater than 100');
      this.validationError = 'Total quantity-radio shouldnot be greater than 100';
      this.errorPopupModal = true;
      return false;
    }


    // bear radio total

    let percentbear: Number = 0;


    for (const bear of this.outturnForm.value.outturnRationArray) {
      if (bear.abilityToBearRatio !== 0) {
        //  console.log('bear-ratio');
        // console.log(typeof(bear.abilityToBearRatio));
        //  alert(bear.abilityToBearRatio);
        // const val = percent.quantityRatio;
        percentbear = (Number(percentbear) + Number(bear.abilityToBearRatio));
        // alert('sum value' + percentbear);
      }
    }


    if (percentbear > 100) {
      // alert('Total bear-radio shouldnot be greater than 100');
      this.validationError = 'Total bear-radio shouldnot be greater than 100';
      this.errorPopupModal = true;
      return false;
    }

    // alert('above check the conditoin');
    if (!(this.product || this.origin || this.raw)) {
      //   alert('upper if');
      if (this.colors.indexOf('#d43c3c') !== -1   || this.colors1.indexOf('#d43c3c') !== -1 || this.colors2.indexOf('#d43c3c') !== -1) {
        if (this.count === 1) {
        this.invalidCountMsg = this.count + ' Mandatory Field is required';
      } else {
        this.invalidCountMsg = this.count + ' Mandatory Fields are required';
      }
      this.errormodalpart = true;
      // this.invalidCount.show();
      // setTimeout({this.invalidCountMsg.hide(), 3000})
      setTimeout(() => { this.invalidCount.hide(); }, 3000);
      return false;
      // }
      // else if (this.colors1.indexOf('#d43c3c') !== -1) {
      //   return false;
      // } else if (this.colors2.indexOf('#d43c3c') !== -1) {
      //   return false;
        // alert('Hitting the nested of if ')
        // alert(this.colors.indexOf('#d43c3c'))
        // console.log(this.colors);
        // alert(this.colors1.indexOf('#d43c3c'))
        // console.log(this.colors1);
        // alert(this.colors2.indexOf('#d43c3c'))
        // console.log(this.colors2);
        //  return false;
      } else {
        // alert('hittin tthe the nested else');
        return true;
      }

    } else {
      this.isActionPerformed = true;
      if (this.count === 1) {
        this.invalidCountMsg = this.count + ' Mandatory Field is required';
      } else {
        this.invalidCountMsg = this.count + ' Mandatory Fields are required';
      }
      this.errormodalpart = true;
      // this.invalidCount.show();
      // setTimeout({this.invalidCountMsg.hide(), 3000})
      setTimeout(() => { this.invalidCount.hide(); }, 3000);
      // alert('Hitting the else of validation');
      return false;
    }

  }

  getExternalMappingList(outturnForm) {
    return outturnForm.get('outturnRationArray').controls;
  }


  resetForm(): void {
    this.outturnForm.reset();
    this.product = false;
    this.origin = false;
    this.raw = false;
    this.finished = false;
    this.bearRatio = false;
    this.quantity = false;

    for (let i = 0; i < this.colors.length; i++) {
      this.colors[i] = '#cfdee7';
      this.colors1[i] = '#cfdee7';
      this.colors2[i] = '#cfdee7';
    }

    // this.colors.forEach(x => x = '#cfdee7');

    // this.colors1.forEach(x => x = '#cfdee7');
    // this.colors2.forEach(x => x = '#cfdee7');
  }


  // addoutturn(i)
  addoutturn(flag?: boolean) {
    const control = <FormArray>this.outturnForm.controls['outturnRationArray'];
    this.showplus[this.showplus.length - 1] = true;
    this.showplus.push(false);
    this.showcross.push(false);
    if (this.showcross.length === 1) {
      this.showcross[0] = true;
    } else {
      this.showcross[0] = false;
    }
    const addrCtrl = this.initMapping();
    if (flag) {
      control.push(addrCtrl);
    } else if (!this.editId) {
      control.push(addrCtrl);
    }
    //  alert(this.outturnForm.value.outturnRationArray);
    this.colors.push('#cfdee7');
    this.colors1.push('#cfdee7');
    this.colors2.push('#cfdee7');

    /*  if ( i >= 0) {
             const id = Number(this.getExternalMappingList(this.outturnForm)[ i ].controls.finishedGradeId.value);
            this.finshSelctdGrades.push(this.finshGrades.filter(object => object.gradeId === id)[0]);
            // console.log(this.finshGrades);
            // console.log(this.finshSelctdGrades);
            // this.finshRemainGrades = this.finshGrades.filter(object => this.selectedGrades.index() )
          //  const set: Grade =  this.getExternalMappingList(this.outturnForm)[ i ].controls;
          //  console.log(this.finshGrades.filter(object => object === set));
          // this.finshSelctdGrades.push(this.finshGrades.filter(object => object === set)[0]);
        }  */


  }
  /*
    save() {
      if (this.isValidForm()) {
        // this.isShowModal = true;
        this.isActionPerformed = true;
        // setTimeout(() => {this.isShowModal = false; }, 3000);
        //this.externalPackingForm.value;
        this.outturn.outturnRawGradeDto = this.outturnForm.value;
        this.outturn.outturnRatioDto = this.outturnForm.value.outturnRationArray;
        this.outturn.statusName = 'draft';
        //  alert('this.outturn value' + JSON.stringify(this.outturn));
        console.log('this.outturn value' + JSON.stringify(this.outturn));
        this.subscription = this.outturnservice.saveoutturnDetails(this.outturn).subscribe(glDetail => {
       this.router.navigate([out], { queryParams: { 'success': 1, ProductId: this.outturn.outturnRawGradeDto.prodId } });
        },
          error => {
            // alert(this.glAccountService.errorMessage);
            // this.isDuplicateGLPopupModal = true;
          },
          () => console.log('Finished')
        );
      }
    } */
  /*
    submit() {
      if (this.isValidForm()) {
       // this.isShowModal = true;
        this.isActionPerformed = true;
        // setTimeout(() => {this.isShowModal = false; }, 3000);
        this.outturn.outturnRawGradeDto = this.outturnForm.value;
        this.outturn.outturnRatioDto = this.outturnForm.value.outturnRationArray;
        this.outturn.statusName = 'active';
        // alert('this.outturn value' + JSON.stringify(this.outturn));
        // console.log('this.outturn value' + JSON.stringify(this.outturn));
        this.subscription = this.outturnservice.submitoutturnDetails(this.outturn).subscribe(glDetail => {
    this.router.navigate([out], { queryParams: { 'success': 1, ProductId: this.outturn.outturnRawGradeDto.prodId } });
        },
          error => {
            // alert(this.glAccountService.errorMessage);
            // this.isDuplicateGLPopupModal = true;
          },
          () => console.log('Finished')
        );
      }
    } */
  onHiddenReset() {
    this.isStatusChangePopupModal = false;
  }
  onHiddenErrorReset() {
    this.errorPopupModal = false;
  }

  onHiddenpopup() {
    this.errormodalpart = false;
    // this.isDeactivatePopupModal = false;
    // this.successModal.hide();
  }


  initMapping() {
    return this.fb.group({
      outturnRatioId: '',
      finishedGradeId: '',
      quantityRatio: '',
      abilityToBearRatio: ''
    });
  }

  removeoutturn(i: number, id?: Number) {
    // alert(id);
    if (this.outturnForm.value.outturnRationArray.length > 1) {
      const control = <FormArray>this.outturnForm.controls['outturnRationArray'];

      this.deleteIds.push(this.outturnForm.value.outturnRationArray[i].outturnRatioId);
      control.removeAt(i);
      // alert('removed' + i );
      //  alert('outturnradioid')
      //  console.log(this.outturnForm.value.outturnRationArray[i].outturnRatioId);

      //  console.log('id shold be ');
      // console.log(this.outturn.outturnRawGradeDto.outturnRawGradeId);
      // console.log(this.outturn.outturnRatioDto[i].outturnRatioId);
      // console.log(id);
      // this.deleteIds.push(this.outturn.outturnRatioDto[i].outturnRatioId);
      // this.deleteIds.push(this.outturnForm.value.outturnRationArray[i].outturnRatioId);

      //  console.log(this.outturnForm);

      this.showplus.splice(i, 1);
      this.showcross.splice(i, 1);
      if (this.showcross.length === 1) {
        this.showcross[0] = true;
      }

      if (this.showplus.indexOf(false) === -1) {
        this.showplus[this.showplus.length - 1] = false;
      }
      // this.colors.push('#cfdee7');
      this.colors.slice(i, 1);
      this.colors1.slice(i, 1);
      this.colors2.slice(i, 1);
      // console.log(this.showplus)
    }
  }

  canDeactivate(): boolean {
    return this.isActionPerformed;
  }

  ngOnDestroy() {
    // Called once, before the instance is destroyed.
    this.subscription.unsubscribe();
  }

  saveOutTurn(action) {
    /* alert( this.count + 'vlaue is required'); */
    if (this.isValidForm()) {
      // this.outturnratioData.outturnRawGradeDto.rawGradeName
      // alert('inside save');
      // alert("action is"+ action);
      this.isActionPerformed = true;
      this.outturn.outturnRawGradeDto = this.outturnForm.value;
      this.outturn.outturnRatioDto = this.outturnForm.value.outturnRationArray;
      this.outturn.statusName = action;
      //  this.getName();
      // alert('this.outturn value' + JSON.stringify(this.outturn));
      // console.log('this.outturn value' + JSON.stringify(this.outturn));
      this.subscription = this.outturnservice.saveoutturnDetails(this.outturn).subscribe(glDetail => {
        this.router.navigate([out], { queryParams: { 'success': 1, 'GradeName': this.getName() } });
        // this.outturn.outturnRawGradeDto.gradeId } });
      }, error => {
        // console.log(JSON.stringify(this.outturnservice.errorMessage));
        /* outturn.outturnRawGradeDto.prodName */
        this.errorModal = true;
        setTimeout(() => { this.errorModal = false; }, 3000);
      });
    }
  }

  getName() {
   // this.persons =  this.personService.getPersons().find(x => x == this.personId);
   // alert('gradeId' + this.outturn.outturnRawGradeDto.gradeId);
  //  alert(this.rawGrades)
    for (const element of this.rawGrades) {
      if (element.gradeId === Number(this.outturn.outturnRawGradeDto.gradeId )) {
      //  alert('checkinig the element' + element);
          return element.gradeCode;
      }
    }
   // alert('gradecode'+this.outturn.outturnRawGradeDto.)
   // this.rawgrthis.outturn.outturnRawGradeDto.gradeId
    // this.gradename = this.rawgradeDetail.filter(grad => grad.gradeId === Number(this.outturn.outturnRawGradeDto.gradeId));
    // alert(this.gradename);
    // return this.gradename[0].gradeCode
  //  alert('gradename' + this.gradename.h);
  }

  conf_update(selection: boolean) {
    // if (selection) {
    //   this.perfromUpdate()
    // } else {
    //   this.isStatusChangePopupModal = false;
    // }
    this.perfromUpdate();

  }

  updateOutTurn(statusName) {
    this.statusName = statusName;
    if (this.isValidForm()) {
      this.isStatusChangePopupModal = true;
    }
  }

  perfromUpdate() {

    //  alert('inside update');
    //  alert('status name' + this.statusName);
    this.isActionPerformed = true;
    // this.isStatusChangePopupModal = true;
    this.outturn.outturnRawGradeDto = this.outturnForm.value
    // this.outturn.outturnRawGradeDto = this.outturnForm.value.outturnRawGradeDto;
    this.outturn.outturnRatioDto = this.outturnForm.value.outturnRationArray;
    // this.outturn.statusName = statusName;
    this.outturn.deletedIds = this.deleteIds;
    if (this.statusName !== 'update') {
      this.outturn.statusName = this.statusName;
     // alert('status1' + this.statusName);
    }
    //  console.log('update fc ');
    //  alert(JSON.stringify(this.outturn));
    // if (this.isValidForm()) {
    // const link = this.masterSetupService.getChildObject('Create SECPACK.PUT');
    const myArray: Outturn[] = Array();
    myArray.push(this.outturn);
    //  console.log(myArray);
    this.subscription = this.outturnservice.updateOutturn('', myArray).subscribe(data => {
      if (data.body !== null && data.body !== '') {
        this.router.navigate([out], { queryParams: { 'success': 2, 'GradeName': this.getName() } });
      }
    }, error => { throw error; });
    // }
  }

  onCancel() {
    this.router.navigate([out]);
  }


}
