import { Component, OnInit, OnDestroy, Output, ViewChild, ViewEncapsulation } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { BrandService } from './service/brand.service';
import { ExcelExportData } from '@progress/kendo-angular-excel-export';
import { BrandModel, status, ConfirmBoxModel, BrandCodeSuggesion, BrandNameSuggesion } from './model/brand.model';
import { Observable } from 'rxjs/Observable';
import { GridComponent, GridDataResult, DataStateChangeEvent, PageChangeEvent} from '@progress/kendo-angular-grid';
import { process, SortDescriptor, orderBy, GroupDescriptor, State } from '@progress/kendo-data-query';
import { FileRestrictions, SelectEvent, ClearEvent, RemoveEvent } from '@progress/kendo-angular-upload';
import { viewBrand } from '../../../shared/interface/router-links';
import { ModalDirective } from 'ngx-bootstrap';
import { ConfirmationService } from '../../../shared/confirm-box/confirm.service';
import { MasterSetupService } from '../../master-setup/service/master-setup.service';
import { CanComponentDeactivate } from '../../../shared/guards/can-deactivate-guard';
import { MESSAGE_SUCCESS, MESSAGE_ERROR } from '../../../shared/interface/common.constants';

@Component({
  selector: 'app-brand',
  templateUrl: './brand.component.html',
  styleUrls: ['./brand.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class BrandComponent implements OnInit, OnDestroy, CanComponentDeactivate {
  subscription: Subscription;
  public view: Observable <GridDataResult>;
  gridView: GridDataResult;
  public mode: Boolean = false;
  public allowUnsort: Boolean = true;
  public multiple: Boolean = false;
  public allItemsChecked: Boolean = false;
  public editedRowIndex = -1;
  brandNameList = [];
  brandCodeList = [];
  prevBrandDetail: BrandModel;
  minTypeaheadLength =  2;
  kendoGridHeight = 500;
  rowHeight = 40;
  errors = [];
  gridHeight = this.kendoGridHeight;
  state: State = {
        skip: 0,
        take: 1000
    };
    group: GroupDescriptor[] = [{ field: 'statusName' }];
    sort: SortDescriptor[] = [];
    public submit: Boolean = false;
    public edit: Boolean = false;
    public create: Boolean = false;
    reqBrandCode: Boolean = false;
    reqBrandName: Boolean = false;
    isValid: Boolean = true;
    isActionPermitted: Boolean = true;
    public del_id;
    public savedData;
    status = status;
    isActiveStatus: Boolean;
    isDraftStatus: Boolean;
    @Output() loadingData: Boolean = true;
    confirmBoxConfigure: ConfirmBoxModel;
    sender: any;
    @ViewChild('confirmModal') public confirmModal: ModalDirective;
    @ViewChild('brandCode') brandCode;
    @ViewChild('brandName') brandName;
    @ViewChild('filebrowse') public filebrowse: ModalDirective;
    isHover: Boolean;
    isDelete: Boolean;
    groups:  BrandModel[];
    public flag: boolean;
    /********  upload image starts**********/
  public events: string[] = [];
  public imagePreviews: string[] = [];
  // public uploadRemoveUrl = 'removeUrl';
  public uploadRestrictions: FileRestrictions = {
    allowedExtensions: ['.jpg', '.png', '.bpm', '.tiff', '.jpeg'],
    maxFileSize: 200000
  };
  public  brandInternalExternal = [{code: 1, value: 'Internal'}, {code: 0, value: 'External'}];
  isActionPerformed = true;
  /************ upload image ends */
  constructor(private router: Router, public brandService: BrandService,
   private confirmationService: ConfirmationService, private masterSetupService: MasterSetupService) {
  }

  ngOnInit() {
    this.isHover = false;
    this.getBrandDetails();
  }
  /*This function is called when user is trying to come out the component*/
   canDeactivate(): boolean {
    return this.isActionPerformed;
  }

  resetGridFields() {
    this.submit = false;
    this.edit = false;
    this.create = false;
    this.isHover = false;
    this.isDelete = false;
    if (this.sender) {
      this.sender.closeRow(this.editedRowIndex);
    }
    if (this.brandService.brandDetail.isEdit) {
        this.brandService.brandDetail.isEdit = false;
    }
    this.editedRowIndex = null;
  }
  getBrandDetails() {
     // this.brandService.brandDetails = [];
      // const link = this.masterSetupService.getChildObject('View Brand.GET');
      // console.log(' LINK ++++ ' + link.href);
      // url = link.href;
      const url = '';
      this.subscription = this.brandService.getBrandDetails(url).subscribe(brand => {
      /*if (brand.body.length * this.rowHeight < this.kendoGridHeight ) {
         this.gridHeight = 196 + brand.body.length * this.rowHeight;
      }*/
      this.brandService.brandDetails  = brand.body;
       this.loadBrandDetails();
       this.resetGridFields();
       this.imagePreviews = [];
        this.isActionPerformed = true;
        this.isHover = false;
        this.isDelete = false;
       },
      error => {throw error}
       );
  }

   ngOnDestroy() {
    // prevent memory leak when component destroyed
    this.subscription.unsubscribe();
  }


   checkAllClicked(e) {
      /*this.event.forEach(x => x.state = ev.target.checked)*/
      // switch inactive checked value
        if (e.target.checked) {
            this.allItemsChecked = true;
            for (let i = 0; i < this.brandService.brandDetails.length; i++) {
                this.brandService.brandDetails[i].checked = true;
            }
        } else {
            this.allItemsChecked = false;
            for (let i = 0; i < this.brandService.brandDetails.length; i++) {
                this.brandService.brandDetails[i].checked = false;
            }
        }
  }
selectUnSelectAllChecked(e) {
      if (!e.target.checked) {
        this.allItemsChecked = false;
      } else  {
         let selected;
          selected = 0;
          for (let i = 0; i < this.brandService.brandDetails.length; i++) {
              if ( !this.brandService.brandDetails[i].checked ) {
                this.allItemsChecked = false;
                break;
              } else {
                selected++;
              }
          }
          if ( selected === this.brandService.brandDetails.length ) {
            this.allItemsChecked = true;
          }
      }
      console.log( this.allItemsChecked );
   }


public addHandler({sender, rowIndex, dataItem}) {
        this.isActionPerformed = false;
        this.submit = true;
        this.edit = false;
        this.create = true;
        this.flag = false;
        this.sender = sender;
        this.closeEditor(sender);
        this.brandService.brandDetail = new BrandModel();
        sender.addRow(this.brandService.brandDetail);
        this.isHover = true;
        this.isDelete = false;
  }

 public editHandler({sender, rowIndex, dataItem}) {
      this.isActionPerformed = false;
      this.submit = true;
      this.edit = true;
      this.create = false;
      this.flag = true;
      this.sender =  sender;
      this.closeEditor(sender);
      this.editedRowIndex = rowIndex;
      this.prevBrandDetail = Object.assign({}, dataItem);
      this.brandService.brandDetail = Object.assign({}, dataItem);
      this.brandService.brandDetail.isEdit = true;
      this.isHover = true;
      this.isDelete = true;
      sender.editRow(rowIndex);
  }

 public saveHandler({sender, rowIndex, dataItem, isNew}) {
        this.sender =  sender;
        this.editedRowIndex = rowIndex;
    }

    brandSaveAsActive(dataItem) {
       // this.brandService.brandDetail = Object.assign({}, dataItem);
        if (this.doValidate()) {
          if (this.brandService.brandDetail.brandId) {
            this.brandService.brandDetail.action = 'ACTIVATE';
            // const link = this.masterSetupService.getChildObject('Update Brand.PUT');
            // const url = link.href;
               const url = '';
              this.subscription = this.brandService.editBrand( url, this.brandService.brandDetail).subscribe(brandDetail => {
                  this.brandService.messages = {severity: MESSAGE_SUCCESS, summary: brandDetail.body};
                  this.getBrandDetails();
              }, error => {
              });
          } else {
            this.brandService.brandDetail.action = 'SUBMIT';
            // const link = this.masterSetupService.getChildObject('Create Brand.POST');
            // const url = link.href;
            const url = '';
            this.subscription = this.brandService.saveBrandDetails(url, this.brandService.brandDetail).subscribe(brandDetail => {
            this.brandService.messages = {severity: MESSAGE_SUCCESS, summary: brandDetail.body};
            this.getBrandDetails();
            },
            error => {
            });
          }
        }
    }
    brandSaveAsDraft(dataItem) {
     // this.brandService.brandDetail = Object.assign({}, dataItem);
      if (this.doValidate()) {
        // const link = this.masterSetupService.getChildObject('Create Brand.POST');
        // const url = link.href;
        const url = '';
        if (this.brandService.brandDetail.brandId) {
          this.updateBrandDetails(dataItem);
        } else {
          this.brandService.brandDetail.action = 'SAVE';
          this.subscription = this.brandService.saveBrandDetails(url, this.brandService.brandDetail).subscribe(brandDetail => {
          this.brandService.messages = {severity: MESSAGE_SUCCESS, summary: brandDetail.body};
          this.getBrandDetails();
          },
          error => {
          });
        }
      }
    }

   reset() {
     if (this.brandService.brandDetail.brandId) {
                if ( (this.brandService.brandDetail.brandCode !== this.prevBrandDetail.brandCode) ||
                (this.brandService.brandDetail.brandName !== this.prevBrandDetail.brandName) ||
                (this.brandService.brandDetail.brandFullName !== this.prevBrandDetail.brandFullName) ||
                (this.brandService.brandDetail.brandIsInternalCode !== this.prevBrandDetail.brandIsInternalCode ) ||
                (this.brandService.brandDetail.logoBlob !== this.prevBrandDetail.logoBlob)) {
                  this.confirmationService.confirm({
                      message: 'Do you really want to reset?',
                      header: 'Reset Confirmation',
                      accept: (event) => {
                            this.brandService.brandDetail = Object.assign({}, this.prevBrandDetail);
                            this.isValid = true;
                            this.flag = false;
                            this.reqBrandCode = false;
                            this.reqBrandName = false;
                            this.imagePreviews = [];
                            this.events = [];
                            this.isActionPerformed = false;
                            this.isHover = false;
                            this.isDelete = false;
                      },
                      reject: (event) => {
                      }
                  });
                } else {
                   this.resetGridFields();
                   this.brandService.brandDetail.isEdit = false;
                   this.closeEditor(this.sender, this.editedRowIndex);
                   this.isActionPerformed = true;
                   this.isHover = false;
                   this.isDelete = false;
                }
     }else {
          if ( (this.brandService.brandDetail.brandCode && this.brandService.brandDetail.brandCode !== '') ||
          (this.brandService.brandDetail.brandName && this.brandService.brandDetail.brandName !== '') ||
          (this.brandService.brandDetail.brandFullName && this.brandService.brandDetail.brandFullName !== '') ||
          (this.brandService.brandDetail.brandIsInternalCode && this.brandService.brandDetail.brandIsInternalCode !== '' ) ||
          (this.brandService.brandDetail.logoBlob && this.brandService.brandDetail.logoBlob !== '') ) {
            this.confirmationService.confirm({
                  message: 'Do you really want to reset?',
                  header: 'Reset Confirmation',
                  accept: (event) => {
                      this.brandService.brandDetail.brandCode = null;
                      this.brandService.brandDetail.brandName = null;
                      this.brandService.brandDetail.brandFullName = null;
                      this.brandService.brandDetail.brandIsInternalCode = null;
                      this.brandService.brandDetail.logoBlob = null;
                      this.isValid = true;
                      this.flag = false;
                      this.reqBrandCode = false;
                      this.reqBrandName = false;
                      this.imagePreviews = [];
                      this.isActionPerformed = false;
                      this.isHover = false;
                      this.isDelete = false;
                  },
                  reject: (event) => {
                  }
              });
          } else {
            this.resetGridFields();
            this.brandService.brandDetail.isEdit = false;
            this.closeEditor(this.sender, this.editedRowIndex);
            this.isActionPerformed = true;
            this.isHover = false;
            this.isDelete = false;
          }
     }
   }
   openConfirmAction(dataItem) {
    // const isEdit = this.brandService.brandDetail.isEdit;
    // this.brandService.brandDetail = dataItem;
    // this.brandService.brandDetail.isEdit = isEdit;
     if ( dataItem.status === this.status.draft ) {
         this.confirmationService.confirm({
            message: 'Do you really want to delete?',
            header: 'Delete Confirmation',
            accept: (event) => {
               this.deleteBrand(dataItem);
            },
            reject: (event) => {
            }
        });
     } else if ( dataItem.status === this.status.active ) {
       this.confirmationService.confirm({
            message: 'Do you really want to deactivate?',
            header: 'Deactivate Confirmation',
            accept: (event) => {
               this.brandDeactivate(dataItem) ;
            },
            reject: (event) => {
            }
        });
     } else if ( dataItem.status === this.status.inactive ) {
       this.confirmationService.confirm({
            message: 'Do you really want to reactivate?',
            header: 'Delete Confirmation',
            accept: (event) => {
                 this.brandReactivate(dataItem) ;
            },
            reject: (event) => {
            }
        });
     }
   }

    brandReactivate(dataItem) {
       this.brandService.brandDetail.action = 'ACTIVATE';
      // const link = this.masterSetupService.getChildObject('Update Brand.PUT');
      // const url = link.href;
      const url = '';
       if (this.brandService.brandDetail.isEdit) {
          if (this.doValidate()) {
            this.subscription = this.brandService.editBrand(url, this.brandService.brandDetail).subscribe(brandDetail => {
                this.brandService.messages = {severity: MESSAGE_SUCCESS, summary: brandDetail.body};
                this.getBrandDetails();
            },
              error => {
            });
          }
      } else {
          this.subscription = this.brandService.deleteBrand(url, dataItem.brandId).subscribe(brandDetail => {
          /*this.brandService.messages = [];
          this.brandService.messages.push({severity: 'success',
          summary: 'You have successfully reactivated ' + this.brandService.brandDetail.brandCode + ' & ' +
          this.brandService.brandDetail.brandName, detail: ''});*/
              this.brandService.messages = {severity: MESSAGE_SUCCESS, summary: brandDetail.body};
              this.getBrandDetails();
          },
            error => {
          });
       }
    }
    brandDeactivate(dataItem) {
      this.brandService.brandDetail.action = 'DEACTIVATE';
      // const link = this.masterSetupService.getChildObject('Update Brand.PUT');
      // const url = link.href;
      const url = '';
      if (this.brandService.brandDetail.isEdit) {
          if (this.doValidate()) {
            this.subscription = this.brandService.editBrand(url, this.brandService.brandDetail).subscribe(brandDetail => {
                this.brandService.messages = {severity: MESSAGE_SUCCESS, summary: brandDetail.body};
                this.getBrandDetails();
            },
              error => {
            });
          }
      } else {
          this.subscription = this.brandService.deleteBrand(url, dataItem.brandId).subscribe(brandDetail => {
            /* this.brandService.messages = [];
              this.brandService.messages.push({severity: 'success',
              summary: 'You have successfully deactivated ' + this.brandService.brandDetail.brandCode + ' & ' +
              this.brandService.brandDetail.brandName, detail: ''});*/
              this.brandService.messages = {severity: MESSAGE_SUCCESS, summary: brandDetail.body};
              this.getBrandDetails();
          },
            error => {
          });
       }
    }
    updateBrandDetails(dataItem) {
      // this.brandService.brandDetail = Object.assign({}, dataItem);
       if (this.doValidate()) {
          this.brandService.brandDetail.action = 'UPDATE';
         // const link = this.masterSetupService.getChildObject('Update Brand.PUT');
         // const url = link.href;
         const url = '';
          this.subscription = this.brandService.editBrand( url, this.brandService.brandDetail).subscribe(brandDetail => {
              this.brandService.messages = {severity: MESSAGE_SUCCESS, summary: brandDetail.body};
              this.getBrandDetails();
          }, error => {
          });
       }
    }
    deleteBrand(dataItem) {
        // const link = this.masterSetupService.getChildObject('Delete Brand.Delete');
        // const url = link.href;
        const url = '';
        this.subscription = this.brandService.deleteBrand(url, dataItem.brandId).subscribe(brandDetail => {
            /*this.brandService.messages = [];
            this.brandService.messages.push({severity: 'success',
            summary: 'You have successfully deleted ' + this.brandService.brandDetail.brandCode + ' & ' +
            this.brandService.brandDetail.brandName, detail: ''});*/
            this.brandService.messages = {severity: MESSAGE_SUCCESS, summary: brandDetail.body};
            this.getBrandDetails();
        },
        error => {
        });
    }

    private closeEditor(grid, rowIndex = this.editedRowIndex) {
        this.sender.closeRow(rowIndex);
        this.editedRowIndex = undefined;
        this.isHover = false;
        this.isDelete = false;
        // this.isActionPerformed = true;
        // this.formGroup = undefined;
    }

    public cancelHandler({ sender, rowIndex }) {
        this.closeEditor(sender, rowIndex);
    }

    sortChange(sort: SortDescriptor[]): void {
        this.sort = sort;
        this.loadBrandDetails();
    }

    public onStateChange(state: State) {
        this.state = state;
    }
    loadBrandDetails(): void {
        this.gridView = {
            data: orderBy(this.brandService.brandDetails, this.sort),
            total: this.brandService.brandDetails.length
        };
    }
    public dataStateChange(state: DataStateChangeEvent): void {
        console.log('here');
         this.isActionPermitted = false;
         this.state = state;
    }


   pageChange(event: PageChangeEvent): void {
        this.state.skip = event.skip;
        this.loadBrandDetails();
    }

   /*allData(): ExcelExportData {
    const result: ExcelExportData = {
    group: this.group
    };
   return result;
   }*/



  /********** upload image starts here **************/
 /*
  public clearEventHandler(e: ClearEvent): void {
    this.log('Clearing the file upload');
    this.imagePreviews = [];
  }*/

  public completeEventHandler($event) {
    this.log(`All files processed`);
  }

  public removeEventHandler(e: RemoveEvent): void {
    this.log(`Removing ${e.files[0].name}`);

    /* const index = this.imagePreviews.findIndex(item => item.uid === e.files[0].uid);

    if (index >= 0) {
      this.imagePreviews.splice(index, 1);
    } */
  }
/*
  public selectEventHandler(e: SelectEvent, dataItem: BrandModel): void {
    const that = this;
    e.files.forEach((file) => {
      that.log(`File selected: ${file.name}`);
      if (!file.validationErrors) {
        const reader = new FileReader();
        reader.onloadend = function() {
            dataItem.logoBlob = reader.result.split(',')[1];
        }
        reader.readAsDataURL(file.rawFile);
      }
    });
  }*/

  public selectEventHandler(e: SelectEvent): void {
  // if (this.imagePreviews.length === 0) {
      // this.flag=true;
      const that = this;
      that.errors = [];
      e.files.forEach((file) => {
         console.log(`File selected: ${file.name}`, file.validationErrors);

        if (!file.validationErrors) {
          const reader = new FileReader();
          reader.onloadend = function() {
                let img;
                img = new Image();
                img.onload = function() {
                    if (img.width > 200 || img.height > 200) {
                    console.log(img.width, img.height);
                    that.errors.push('Logo pixel size should not be more than 200 pixels wide x 200 pixels height');
                            }
                    if (that.errors.length === 0) {
                    that.brandService.brandDetail.logoBlob = reader.result.split(',')[1];
                    // sessionStorage.setItem('img', this.result);
                    that.imagePreviews = [];
                    that.imagePreviews.push(that.brandService.brandDetail.logoBlob);
                    }else {
                    that.brandService.brandDetail.logoBlob = '';
                    that.imagePreviews = [];
                    }
                                    };
                    img.src = reader.result;

                      }

          reader.readAsDataURL(file.rawFile);

        } else {
            if (file.validationErrors.indexOf('invalidMaxFileSize') > -1) {
              // this.brandService.messages = [];
              // this.brandService.messages.push({severity: 'error',
              // summary: 'Logo size should not be more than 200KB', detail: ''});
              this.errors.push('Logo size should not be more than 200KB');
            }
            if (file.validationErrors.indexOf('invalidFileExtension') > -1) {
            //  this.brandService.messages = [];
            // this.brandService.messages.push({severity: 'error',
            //  summary: 'Invalid file extension. Only .jpg,.png,jpeg,.bmp,.tiff extensions allowed', detail: ''});
            this.errors.push('Invalid file extension. Only .jpg,.png,jpeg,.bmp,.tiff extensions allowed');
            }
            }
      });

    /*} else {
      this.brandService.messages = [];
      this.brandService.messages.push({severity: 'error',
       summary: 'Please remove the selected file and upload', detail: ''});
    }*/

  }

  private log(event: string): void {
    this.events.unshift(`${event}`);
  }

  /************upload inage ends here **************/

    isNotBlank(value: string): Boolean {
        return (value && value.trim() !== '');
    }
    doValidate(): Boolean {
    this.isValid = true;
    if (!this.isNotBlank(this.brandService.brandDetail.brandCode)) {
      this.reqBrandCode = true;
      this.isValid = false;
    } else {
      this.reqBrandCode = false;
    }

    if (!this.isNotBlank(this.brandService.brandDetail.brandName)) {
      this.reqBrandName = true;
      this.isValid = false;
    } else {
      this.reqBrandName = false;
    }

    if (!this.isValid) {
      this.brandService.messages = {severity: MESSAGE_ERROR, summary: 'Message.Common.Mandatory'};
    }
    return this.isValid;
  }


  goToViewPage(rowData) {
     this.brandService.setRowData(rowData)
     this.router.navigate([viewBrand]);
  }

  brandNameSuggestion($event) {
    const brandName = $event.target.value;
    if (this.isNotBlank(brandName) && brandName.length >= this.minTypeaheadLength) {
     let brandNameSuggestion;
     brandNameSuggestion = new BrandCodeSuggesion();
     brandNameSuggestion = {brandName: brandName, toValidate: 'NAME'};
     this.subscription = this.brandService.suggestBrand(brandNameSuggestion).subscribe(data => {
      // console.log(data);
       this.brandNameList = data.body;
     }, error =>  { throw error; } );
    } else {
       this.brandNameList = [];
    }
  }

  brandCodeSuggestion($event) {
    const brandCode = $event.target.value;
    if (this.isNotBlank(brandCode) && brandCode.length >= this.minTypeaheadLength) {
      let brandCodeSuggestion;
      brandCodeSuggestion = new BrandCodeSuggesion();
      brandCodeSuggestion = {brandCode: brandCode, toValidate: 'CODE'};
      this.subscription = this.brandService.suggestBrand(brandCodeSuggestion).subscribe(data => {
       // console.log(data);
        this.brandCodeList = data.body;
      }, error =>  { throw error; } );
    } else {
         this.brandCodeList = [];
    }
  }
  browse() {
    this.imagePreviews = [];
    if (this.brandService.brandDetail.logoBlob) {
      this.imagePreviews.unshift(this.brandService.brandDetail.logoBlob);
    }
    this.filebrowse.show();
  }
  public clearEventHandler(e: ClearEvent): void {
    this.imagePreviews = [];
    sessionStorage.setItem('img', '');
    this.flag = false;
    this.brandService.brandDetail.logoBlob = null;
  }
  public upload() {
    this.filebrowse.hide();
    this.flag = true;
  }
   public clearimg(e: ClearEvent) {
    this.imagePreviews = [];
    sessionStorage.setItem('img', '');
    this.flag = false;
    this.clearEventHandler(e);
    this.brandService.brandDetail.logoBlob = null;
 }
  public handler(type: string, $event: ModalDirective) {
    // this.imagedata(sessionStorage.getItem('img'));
  }
  /*imagedata(data) {
    this.src = data;
  }*/
}

