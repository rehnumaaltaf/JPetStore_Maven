import { Component, OnInit , OnDestroy } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, FormControl, Validators, FormArray, ReactiveFormsModule, FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { Subscription } from 'rxjs/Subscription';
import { OutputMappingComponent } from './output-mapping/output-mapping.component';
import { InputMappingComponent } from './input-mapping/input-mapping.component';
import { CertificationMappingComponent } from './certification-mapping/certification-mapping.component';
import { CanComponentDeactivate } from '../../../../shared/guards/can-deactivate-guard';
import { BlendService } from '../service/blend.service';
import { BlendMatrix , BlendOutputArray , BlendInputArray ,
   BlendCertificationArray , ProductMaster , OriginMaster , GradeMaster , CertificationMaster} from '../model/blend-model';
// tslint:disable-next-line:max-line-length
import { MESSAGE_SUCCESS, MESSAGE_ERROR, ACTION_SAVE, ACTION_SUBMIT, ACTION_UPDATE, YES, NO,
STATUS_DRAFT , STATUS_ACTIVE , STATUS_INACTIVE } from '../../../../shared/interface/common.constants';
import { ACTION_ACTIVE, ACTION_DEACTIVE } from '../../../../shared/interface/common.constants';
import { MessageModel } from '../../../../shared/message/message.model';
import { ConfirmationService } from '../../../../shared/confirm-box/confirm.service';
import { blend } from '../../../../shared/interface/router-links';

@Component({
  selector: 'app-add-edit-blend',
  templateUrl: './add-edit-blend.component.html',
  styleUrls: ['./add-edit-blend.component.css']
})
export class AddEditBlendComponent implements OnInit , OnDestroy, CanComponentDeactivate {
  public blendForm: FormGroup;
  isActionPerformed = false;
  subscription: Subscription;
  blendData: BlendMatrix = new BlendMatrix();
  totalOutPer: number;
  totalInPer: number;
  totalRatio: number;
  totalCerPer: number;
  isValid: boolean;
  isCheckPer: boolean;
  reqBlendCode: boolean;
  reqBlendName: boolean;
  isEditPage: boolean;
  public draft;
  public active;
  public inactive;
  actionSave = ACTION_SAVE;
  actionSubmit = ACTION_SUBMIT;
  actionUpdate = ACTION_UPDATE;
  actionActive = ACTION_ACTIVE;
  actionDeactive = ACTION_DEACTIVE;
  public editId;
  outMapList: BlendOutputArray [] = [];
  inMapList: BlendInputArray [] = [];
  cerMapList: BlendCertificationArray [] = [];
    public status: any = {
    isFirstOpen: true,
    isFirstDisabled: false
  };
 // outputCal: BlendOutputArray= new BlendOutputArray();
  constructor(private fb: FormBuilder , public blendService: BlendService , private route: ActivatedRoute,
  private router: Router , private confirmationService: ConfirmationService) { }

  ngOnInit() {
     if (this.route.snapshot.params['id'] !== undefined) {
        this.isEditPage = true;
        this.editId = this.route.snapshot.params['id'];
        this.buildBlendForm();
        this.dropdownList();
        this.totalInPer = 0;
        this.totalOutPer = 0;
        this.totalRatio = 0;
        this.totalCerPer = 0;
        this.getBlendDetails(this.editId);
     }else {
        this.isEditPage = false;
        this.totalInPer = 0;
        this.totalOutPer = 0;
        this.totalRatio = 0;
        this.totalCerPer = 0;
        this.buildBlendForm();
        this.addBlendOutput();
        this.addBlendInput();
        this.addBlendCertification();
        this.dropdownList();
        this.valueChangeCall();
     }
  }

  valueChangeCall() {
    this.blendForm.valueChanges.subscribe(data => {
      this.blendData = data;
      this.totalOutPer = 0;
      this.totalInPer = 0;
      this.totalRatio = 0;
      this.totalCerPer = 0;
      console.log('value of o/p ' + JSON.stringify(this.blendData));
      for (let q = 0 ; q < this.blendData.blendOutputList.length ; q++) {
        this.totalOutPer += +this.blendData.blendOutputList[q].quantityPercentage;
        this.totalRatio += +this.blendData.blendOutputList[q].abilityToBearRatio;
        console.log('total output percentage    ' + this.totalOutPer);
        if (this.totalOutPer > 100) {
           this.blendData.blendOutputList[q].quantityPercentage = 0;
           this.blendForm.controls['blendOutputList'].setValue(this.blendData.blendOutputList);
           this.blendService.messages = { severity: MESSAGE_ERROR, summary: 'Message.Blend.Percentage',
              placeHolder: ['Output Quantity Percentage']};
        }
        if (this.totalRatio > 100) {
          this.blendData.blendOutputList[q].abilityToBearRatio = 0;
          this.blendForm.controls['blendOutputList'].setValue(this.blendData.blendOutputList);
            this.blendService.messages = { severity: MESSAGE_ERROR, summary: 'Message.Blend.Percentage',
              placeHolder: ['Value Ratio']};
        }
      }

      for (let r = 0 ; r < this.blendData.blendInputList.length ; r++) {
        this.totalInPer += +this.blendData.blendInputList[r].quantityPercentage;
        console.log('total input percentage   ' + this.totalInPer);
        if (this.totalInPer > 100) {
          this.blendData.blendInputList[r].quantityPercentage = 0;
          this.blendForm.controls['blendInputList'].setValue(this.blendData.blendInputList);
          this.blendService.messages = { severity: MESSAGE_ERROR, summary: 'Message.Blend.Percentage',
              placeHolder: ['Input Quantity Percentage']};
        }
      }

      for (let c = 0 ; c < this.blendData.blendInputCertificationList.length ; c++) {
        this.totalCerPer += +this.blendData.blendInputCertificationList[c].certificationPercentage;
        console.log('total certificate percentage   ' + this.totalCerPer);
        if (this.totalCerPer > 100) {
          this.blendData.blendInputCertificationList[c].certificationPercentage = 0;
          this.blendForm.controls['blendInputCertificationList'].setValue(this.blendData.blendInputCertificationList);
          this.blendService.messages = { severity: MESSAGE_ERROR, summary: 'Message.Blend.Percentage',
              placeHolder: ['Certification Percentage']};
        }
      }

    })
  }

  dropdownList() {
    this.getProducts();
    this.getOrigins();
    this.getGrades();
   // this.getCertifications();
  }

  // product list
  getProducts() {
     this.subscription = this.blendService.getProductList().subscribe(data => {
        if (data.body !== null && data.body !== '') {
          this.blendService.blendProductList = data.body;
         // console.log(JSON.stringify(this.blendService.blendProductList));
        }
      }, error => { throw error; });
  }

  // origin list
  getOrigins() {
     this.subscription = this.blendService.getOriginList().subscribe(data => {
        if (data.body !== null && data.body !== '') {
          for (let m = 0; m < data.body.length ; m++) {
            this.blendService.blendOriginList.push(data.body[m].originDto);
          }
        }
      }, error => { throw error; });
  }

  // grade list
  getGrades() {
    this.subscription = this.blendService.getGradeList().subscribe(data => {
        if (data.body !== null && data.body !== '') {
          this.blendService.blendGradeList = data.body;
        }
      }, error => { throw error; });
  }

  // certification list
  getCertifications() {
    this.subscription = this.blendService.getCertificationdd().subscribe(data => {
        if (data.body !== null && data.body !== '') {
          this.blendService.blendCertificationList = data.body;
        }
      }, error => { throw error; });
  }
  /*Cloning part*/
  getOutputList(blendForm) {
    return blendForm.get('blendOutputList').controls;
  }

  getInputList(blendForm) {
    return blendForm.get('blendInputList').controls;
  }

  getCertificationList(blendForm) {
    return blendForm.get('blendInputCertificationList').controls;
  }

  buildBlendForm(): void {
    this.blendForm = this.fb.group({
      'pkBlendTemplateId': [''],
      'templateCode': [''],
      'templateName': [''],
      'templateDesc': [''],
      'statusName': [''],
      'action': [''],
      'blendOutputList': this.fb.array([]),
      'blendInputList': this.fb.array([]),
      'blendInputCertificationList': this.fb.array([])
    });
  }

  /*Blend Output Array*/
  addBlendOutput() {
    if (this.totalOutPer < 100) {
      const control = <FormArray>this.blendForm.controls['blendOutputList'];
      const addrCtrl = this.initBlendOutput();
      control.push(addrCtrl);
    }else {
      this.blendService.messages = { severity: MESSAGE_ERROR, summary: 'Message.Blend.addClone',
              placeHolder: ['Blending Output Grades', 'Output Quantity Percentage']};
    }
  }

  initBlendOutput() {
     return this.fb.group({
     'pkBlendOutputId': [''],
     'fkProdId': [''],
     'fkOriginId': [''],
     'fkGradeId': [''],
     'quantityPercentage': [''],
     'abilityToBearRatio': [''],
     'fkProdCertId': ['']
    });
  }

  removeOutput(i: number) {
    if (this.blendForm.value.blendOutputList.length > 1 ) {
          const control = <FormArray>this.blendForm.controls['blendOutputList'];
          control.removeAt(i);
          if (this.blendData !== undefined) {
              if (this.blendData.blendOutputList[i] != null) {
                this.blendData.blendOutputList.splice(i, 1);
              }
          }
     }
  }

  /*End of Blend Output Array*/

  /*Blend Input Array*/

  addBlendInput() {
    if (this.totalInPer < 100) {
      const control = <FormArray>this.blendForm.controls['blendInputList'];
      const addrCtrl = this.initBlendInput();
      control.push(addrCtrl);
    }else {
      this.blendService.messages = { severity: MESSAGE_ERROR, summary: 'Message.Blend.addClone',
              placeHolder: ['Blending Input Grades', 'Input Quantity Percentage']};
    }
  }

  initBlendInput() {
     return this.fb.group({
     'pkBlendInputId': [''],
     'fkProdId': [''],
     'fkOriginId': [''],
     'fkGradeId': [''],
     'quantityPercentage': ['']
    });
  }

  removeInput(j: number) {
    if (this.blendForm.value.blendInputList.length > 1 ) {
          const control = <FormArray>this.blendForm.controls['blendInputList'];
          control.removeAt(j);
          if (this.blendData !== undefined) {
              if (this.blendData.blendInputList[j] != null) {
                this.blendData.blendInputList.splice(j, 1);
              }
          }
     }
  }

  /*End of Blend Input Array*/

  /*Blend Certification Array*/

  addBlendCertification() {
    if (this.totalCerPer < 100) {
      const control = <FormArray>this.blendForm.controls['blendInputCertificationList'];
      const addrCtrl = this.initBlendCertification();
      control.push(addrCtrl);
    }else {
      this.blendService.messages = { severity: MESSAGE_ERROR, summary: 'Message.Blend.addClone',
              placeHolder: ['Blending Input Certification', 'Certification Percentage']};
    }
  }

  initBlendCertification() {
     return this.fb.group({
      'pkBlendInputCertificationId': [''],
      'fkProdCertId': [''],
      'certificationPercentage': ['']
    });
  }

  removeCertification(k: number) {
    if (this.blendForm.value.blendInputCertificationList.length > 1 ) {
          const control = <FormArray>this.blendForm.controls['blendInputCertificationList'];
          control.removeAt(k);
          if (this.blendData !== undefined) {
              if (this.blendData.blendInputCertificationList[k] != null) {
                this.blendData.blendInputCertificationList.splice(k, 1);
              }
          }
     }
  }

  /*End of Blend Certification Array*/


/*Save the blend master details*/
  saveBlend() {
    if (this.isValidForm()) {
      if (this.checkPercentages()) {
        this.blendData = this.blendForm.value;
          this.blendData.action = ACTION_SAVE;
          this.isActionPerformed = true;
          if (this.isEditPage) {
              const url = '';
              this.subscription = this.blendService.updateBlendDetail(url, this.blendData)
                  .subscribe(response => {
                    // Acknowledgement code
                    this.router.navigate([blend]);
                    this.blendService.messages = { severity: MESSAGE_SUCCESS, summary: 'Message.Blend.Update',
                    placeHolder: [ this.blendData.templateName] };
                  },
                  error => {});
          } else {
              this.subscription = this.blendService.saveBlendDetails(this.blendData).subscribe(data => {
              if (data.body !== null && data.body !== '' ) {
                    this.blendService.messages = { severity: MESSAGE_SUCCESS, summary: 'Message.Blend.Save',
                    placeHolder: [ this.blendData.templateName] };
                    this.router.navigate([blend]);
              }
            },
            error => {});
          }
      } else {
        if (this.totalOutPer < 100) {
            this.blendService.messages = { severity: MESSAGE_ERROR, summary: 'Message.Blend.validatePer',
              placeHolder: ['Output Quantity Percentage'] };
        }else if (this.totalRatio < 100) {
          this.blendService.messages = { severity: MESSAGE_ERROR, summary: 'Message.Blend.validatePer',
              placeHolder: ['Value Ratio'] };
        } else {
            this.blendService.messages = { severity: MESSAGE_ERROR, summary: 'Message.Blend.validatePer',
              placeHolder: ['Input Quantity Percentage'] };
        }
      }
    }else {
        this.blendService.messages = { severity: MESSAGE_ERROR, summary: 'Message.Common.Mandatory',
              placeHolder: [] };
    }
  }
  /*End of save*/

  /*Submit the blend details*/

  submitBlend() {
    if (this.isValidForm()) {
      if (this.checkPercentages()) {
        this.blendData = this.blendForm.value;
        this.blendData.action = ACTION_SUBMIT;
        this.isActionPerformed = true;
        if (this.isEditPage) {
            const url = '';
              this.subscription = this.blendService.updateBlendDetail(url, this.blendData)
                  .subscribe(response => {
                    // Acknowledgement code
                    this.router.navigate([blend]);
                    this.blendService.messages = { severity: MESSAGE_SUCCESS, summary: 'Message.Blend.Update',
                    placeHolder: [ this.blendData.templateName] };
                  },
                  error => {});
        } else {
          this.subscription = this.blendService.saveBlendDetails(this.blendData).subscribe(data => {
          if (data.body !== null && data.body !== '' ) {
                this.blendService.messages = { severity: MESSAGE_SUCCESS, summary: 'Message.Blend.Submit',
                placeHolder: [ this.blendData.templateName] };
                this.router.navigate([blend]);
          }
        },
        error => {});
        }
      } else {
         if (this.totalOutPer < 100) {
            this.blendService.messages = { severity: MESSAGE_ERROR, summary: 'Message.Blend.validatePer',
              placeHolder: ['Output Quantity Percentage'] };
        }else if (this.totalRatio < 100) {
          this.blendService.messages = { severity: MESSAGE_ERROR, summary: 'Message.Blend.validatePer',
              placeHolder: ['Value Ratio'] };
        } else {
            this.blendService.messages = { severity: MESSAGE_ERROR, summary: 'Message.Blend.validatePer',
              placeHolder: ['Input Quantity Percentage'] };
        }
      }
    }else {
        this.blendService.messages = { severity: MESSAGE_ERROR, summary: 'Message.Common.Mandatory',
              placeHolder: [] };
    }
  }

  isValidForm(): Boolean {
    this.isValid = true;
    this.blendData = this.blendForm.value;
    if (this.blendData.templateCode == null || this.blendData.templateCode === undefined
       || this.blendData.templateCode === '') {
      this.reqBlendCode = true;
      this.isValid = false;
    }else {
       this.reqBlendCode = false;
    }
    if (this.blendData.templateName == null || this.blendData.templateName === undefined
       || this.blendData.templateName === '') {
      this.reqBlendName = true;
      this.isValid = false;
    }else {
      this.reqBlendName = false;
    }
    /*blend Output list*/
    if (this.blendForm.value.blendOutputList !== undefined) {
        const outputSize = Number( JSON.stringify(this.blendForm.value.blendOutputList.length));
        for (let ou = 0 ; ou < outputSize ; ou++) {
            if (this.blendForm.value.blendOutputList[ou].fkProdId == null ||
              this.blendForm.value.blendOutputList[ou].fkProdId === undefined
              || this.blendForm.value.blendOutputList[ou].fkProdId.toString() === '') {
              this.blendService.reqOutProduct[ou] = true;
              this.isValid = false;
            }else {
                this.blendService.reqOutProduct[ou] = false;
            }
            if (this.blendForm.value.blendOutputList[ou].fkOriginId == null ||
              this.blendForm.value.blendOutputList[ou].fkOriginId === undefined
              || this.blendForm.value.blendOutputList[ou].fkOriginId.toString() === '') {
                  this.blendService.reqOutOrigin[ou] = true;
                  this.isValid = false;
            }else {
              this.blendService.reqOutOrigin[ou] = false;
            }
            if (this.blendForm.value.blendOutputList[ou].fkGradeId == null ||
              this.blendForm.value.blendOutputList[ou].fkGradeId === undefined
              || this.blendForm.value.blendOutputList[ou].fkGradeId.toString() === '') {
                  this.blendService.reqOutGrade[ou] = true;
                  this.isValid = false;
            }else {
              this.blendService.reqOutGrade[ou] = false;
            }
            if (this.blendForm.value.blendOutputList[ou].quantityPercentage == null ||
              this.blendForm.value.blendOutputList[ou].quantityPercentage === undefined
              || this.blendForm.value.blendOutputList[ou].quantityPercentage.toString() === '') {
                  this.blendService.reqOutPer[ou] = true;
                  this.isValid = false;
            }else {
              this.blendService.reqOutPer[ou] = false;
            }
            if (this.blendForm.value.blendOutputList[ou].abilityToBearRatio == null ||
              this.blendForm.value.blendOutputList[ou].abilityToBearRatio === undefined
              || this.blendForm.value.blendOutputList[ou].abilityToBearRatio.toString() === '') {
                  this.blendService.reqOutRatio[ou] = true;
                  this.isValid = false;
            }else {
              this.blendService.reqOutRatio[ou] = false;
            }
        }
    }
    /*Blend Input List*/
    if (this.blendForm.value.blendInputList !== undefined) {
        const inputSize = Number( JSON.stringify(this.blendForm.value.blendInputList.length));
        for (let iList = 0 ; iList < inputSize ; iList++) {
            if (this.blendForm.value.blendInputList[iList].fkProdId == null ||
              this.blendForm.value.blendInputList[iList].fkProdId === undefined
              || this.blendForm.value.blendInputList[iList].fkProdId.toString() === '') {
              this.blendService.reqInProduct[iList] = true;
              this.isValid = false;
            }else {
                this.blendService.reqInProduct[iList] = false;
            }
            if (this.blendForm.value.blendInputList[iList].fkOriginId == null ||
              this.blendForm.value.blendInputList[iList].fkOriginId === undefined
              || this.blendForm.value.blendInputList[iList].fkOriginId.toString() === '') {
                  this.blendService.reqInOrigin[iList] = true;
                  this.isValid = false;
            }else {
              this.blendService.reqInOrigin[iList] = false;
            }
            if (this.blendForm.value.blendInputList[iList].fkGradeId == null ||
              this.blendForm.value.blendInputList[iList].fkGradeId === undefined
              || this.blendForm.value.blendInputList[iList].fkGradeId.toString() === '') {
                  this.blendService.reqInGrade[iList] = true;
                  this.isValid = false;
            }else {
              this.blendService.reqInGrade[iList] = false;
            }
            if (this.blendForm.value.blendInputList[iList].quantityPercentage == null ||
              this.blendForm.value.blendInputList[iList].quantityPercentage === undefined
              || this.blendForm.value.blendInputList[iList].quantityPercentage.toString() === '') {
                  this.blendService.reqInPer[iList] = true;
                  this.isValid = false;
            }else {
              this.blendService.reqInPer[iList] = false;
            }
        }
    }
    return this.isValid;
  }

  validateCode() {
    if (this.blendForm.value.templateCode !== null && this.blendForm.value.templateCode !== undefined
      && this.blendForm.value.templateCode.toString() !== '') {
      this.reqBlendCode = false;
    }
  }

  validateName() {
    if (this.blendForm.value.templateName !== null && this.blendForm.value.templateName !== undefined) {
      this.reqBlendName = false;
    }
  }

  checkPercentages(): Boolean {
    this.isCheckPer = true;
    if (this.totalOutPer === 100) {
        if (this.totalRatio === 100 ) {
            if (this.totalInPer === 100 ) {
                return true;
            } else {
              return false;
            }
        }else {
          return false;
        }
      }else {
        return false;
      }
  }

/*Edit Blend Details*/

  getBlendDetails(id) {
    this.blendService.getSelectedMatrixData('', id).subscribe(data => {
      if (data.body !== null && data.body !== '') {
        this.blendData = data.body[0];
        this.editBlendDetails();
        console.log('blend edit values' + this.blendData + '   ' + JSON.stringify(data.body));
        // STATUS_DRAFT , STATUS_ACTIVE , STATUS_INACTIVE
        if (this.blendData.statusName === STATUS_DRAFT) {
          this.draft = true;
          this.active = false;
          this.inactive = false;
        } else if (this.blendData.statusName === STATUS_ACTIVE) {
          this.draft = false;
          this.active = true;
          this.inactive = false;
        } else {
          this.draft = false;
          this.active = false;
          this.inactive = true;
        }
      }
     } , error => { console.log(JSON.stringify(error)) });
  }

  editBlendDetails() {
    this.blendForm.controls['pkBlendTemplateId'].setValue(this.blendData.pkBlendTemplateId);
    this.blendForm.controls['templateCode'].setValue(this.blendData.templateCode);
    this.blendForm.controls['templateName'].setValue(this.blendData.templateName);
    this.blendForm.controls['templateDesc'].setValue(this.blendData.templateDesc);
    this.blendForm.controls['statusName'].setValue(this.blendData.statusName);
    const outSize = Number( JSON.stringify(this.blendData.blendOutputList.length));
    this.outMapList = this.blendData.blendOutputList;
    if (outSize >= 1) {
      for (let outIndex = 0 ; outIndex < outSize ; outIndex ++) {
          this.addBlendOutput();
      }
    }
    const inSize = Number( JSON.stringify(this.blendData.blendInputList.length));
    this.inMapList = this.blendData.blendInputList;
    if (inSize >= 1) {
      for (let inIndex = 0 ; inIndex < inSize ; inIndex ++) {
          this.addBlendInput();
      }
    }
    const cerSize = Number( JSON.stringify(this.blendData.blendInputCertificationList.length));
    this.cerMapList = this.blendData.blendInputCertificationList;
    if (cerSize >= 1) {
      for (let cerIndex = 0 ; cerIndex < cerSize ; cerIndex ++) {
          this.addBlendCertification();
      }
    }
    if (cerSize === 0) {
        this.addBlendCertification();
    }
    this.valueChangeCall();
  }

  /* Status Change for Blend*/
  openConfirmAction() {
    console.log(this.blendData.statusName + '   ' + STATUS_ACTIVE);
      if (this.blendData.statusName === STATUS_ACTIVE) {
          this.confirmationService.confirm({
              message: 'Confirm.Common.Deactivate',
              // isCommentPresent: false,
              // icon: './../assets/image/Deactiveicon2.svg',
              placeHolder: [this.blendData.templateName],
              accept: (event) => {
                this.blendStatusChange();
              },
              reject: (event) => {
              }
          });
      } else if (this.blendData.statusName === STATUS_INACTIVE) {
          this.confirmationService.confirm({
            message: 'Confirm.Common.Reactivate',
            // isCommentPresent: false,
            // icon: './../assets/image/Deactiveicon2.svg',
            placeHolder: [this.blendData.templateName],
            accept: (event) => {
              this.blendStatusChange();
            },
            reject: (event) => {
            }
          });
        }
     }

   blendStatusChange() {
       this.isActionPerformed = true;
       if (this.isValidForm()) {
         if (this.checkPercentages()) {
              this.subscription = this.blendService.getSelectedMatrixData('', this.blendData.pkBlendTemplateId).subscribe(matrixDetail => {
              this.blendData = matrixDetail.body[0];
              this.blendData = this.blendForm.value;
              if ( this.blendData.statusName === STATUS_ACTIVE) {
                  this.blendData.action = ACTION_DEACTIVE;
              } else {
                  this.blendData.action  = ACTION_SUBMIT;
              }
              const url = '';
              this.subscription = this.blendService.updateBlendDetail(url, this.blendData)
                  .subscribe(response => {
                    // Acknowledgement code
                        if (this.blendData.statusName === STATUS_ACTIVE) {
                          this.blendService.messages = { severity: MESSAGE_SUCCESS, summary: 'Message.Common.Deactivate',
                          placeHolder: ['Blend', this.blendData.templateName] };
                        } else if (this.blendData.statusName === STATUS_INACTIVE) {
                          this.blendService.messages = { severity: MESSAGE_SUCCESS, summary: 'Message.Common.Reactivate',
                          placeHolder: ['Blend', this.blendData.templateName] };
                        } else if (this.blendData.statusName === STATUS_DRAFT) {
                          this.blendService.messages = { severity: MESSAGE_SUCCESS, summary: 'Message.Common.Delete',
                          placeHolder: ['Blend', this.blendData.templateName] };
                        }
                      // this.getBlendDetails(this.editId);
                      this.router.navigate([blend]);
                  }, error => {});
              });
         } else {
           if (this.totalOutPer < 100) {
              this.blendService.messages = { severity: MESSAGE_ERROR, summary: 'Message.Blend.validatePer',
                placeHolder: ['Output Quantity Percentage'] };
            }else if (this.totalRatio < 100) {
              this.blendService.messages = { severity: MESSAGE_ERROR, summary: 'Message.Blend.validatePer',
                  placeHolder: ['Value Ratio'] };
            } else {
                this.blendService.messages = { severity: MESSAGE_ERROR, summary: 'Message.Blend.validatePer',
                  placeHolder: ['Input Quantity Percentage'] };
            }
         }
       } else {
            this.blendService.messages = { severity: MESSAGE_ERROR, summary: 'Message.Common.Mandatory',
              placeHolder: [] };
        }
     }
  /*--------End---------*/

  updateBlend() {
    if (this.isValidForm()) {
      if (this.checkPercentages()) {
          this.blendData = this.blendForm.value;
          this.blendData.action = ACTION_UPDATE;
          this.isActionPerformed = true;
          const url = '';
          this.subscription = this.blendService.updateBlendDetail(url, this.blendData)
              .subscribe(response => {
                // Acknowledgement code
                this.router.navigate([blend]);
                this.blendService.messages = { severity: MESSAGE_SUCCESS, summary: 'Message.Blend.Update',
                placeHolder: [ this.blendData.templateName] };
              }, error => {});
      } else {
          if (this.totalOutPer < 100) {
            this.blendService.messages = { severity: MESSAGE_ERROR, summary: 'Message.Blend.validatePer',
              placeHolder: ['Output Quantity Percentage'] };
          }else if (this.totalRatio < 100) {
            this.blendService.messages = { severity: MESSAGE_ERROR, summary: 'Message.Blend.validatePer',
                placeHolder: ['Value Ratio'] };
          } else {
              this.blendService.messages = { severity: MESSAGE_ERROR, summary: 'Message.Blend.validatePer',
                placeHolder: ['Input Quantity Percentage'] };
          }
      }
    }else {
        this.blendService.messages = { severity: MESSAGE_ERROR, summary: 'Message.Common.Mandatory',
              placeHolder: [] };
    }
  }

  /*To list the suggestions for code*/
  onCodeSuggestion($event) {
   const blendCodeValue = $event.target.value;
    if (blendCodeValue !== null && blendCodeValue !== undefined && blendCodeValue !== '') {
      this.blendData = this.blendForm.value;
      this.blendData.toValidate = 'code';
      this.subscription = this.blendService.getUniqueCheckCode(this.blendData).subscribe(descDetails => {
      this.blendService.blendCodeList = descDetails.body;
     }, error =>  { throw error; } );
    }
  }

  /*To list the suggestions for name*/
  onNameSuggestion($event) {
      const blendNameValue = $event.target.value;
    if (blendNameValue !== null && blendNameValue !== undefined && blendNameValue !== '') {
      this.blendData = this.blendForm.value;
      this.blendData.toValidate = 'name';
      this.subscription = this.blendService.getUniqueCheckName(this.blendData).subscribe(descDetails => {
      this.blendService.blendNameList = descDetails.body;
     }, error =>  { throw error; } );
    }
  }


  onCancel() {
    this.router.navigate([blend]);
  }

  reset() {
     this.confirmationService.confirm({
        message: 'Do you really want to reset?',
        header: 'Reset Confirmation',
        accept: (event) => {
          this.blendForm.reset();
          this.isValid = true;
          this.reqBlendCode = false;
          this.reqBlendName = false;
          this.blendService.reqInGrade = [];
          this.blendService.reqInOrigin = [];
          this.blendService.reqInPer = [];
          this.blendService.reqInProduct = [];
          this.blendService.reqOutGrade = [];
          this.blendService.reqOutOrigin = [];
          this.blendService.reqOutPer = [];
          this.blendService.reqOutProduct = [];
          this.blendService.reqOutRatio = [];
          this.isActionPerformed = false;
      },
      reject: (event) => {
      }
     });
  }


  canDeactivate(): boolean {
      return this.isActionPerformed;
  }

  ngOnDestroy() {
   if (this.subscription) {
     this.subscription.unsubscribe();
   }
  }

}
