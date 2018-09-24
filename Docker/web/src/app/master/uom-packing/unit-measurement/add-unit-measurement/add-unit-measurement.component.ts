import { Component, ViewChild, ContentChild, OnInit, ElementRef, Renderer2, ChangeDetectorRef, OnDestroy, Input } from '@angular/core';
import { FormGroup, FormControl, FormsModule } from '@angular/forms';
import { Subscription } from 'rxjs/Subscription';
import { Router } from '@angular/router';
import { UnitMeasurement } from '../unit-measurement';
import { UnitMeasurementService } from '../service/unit-measurement.service';
import { MasterSetupService } from '../../../master-setup/service/master-setup.service';
import { SelectItem } from '../../../../shared/interface/selectItem';
import { uom } from '../../../../shared/interface/router-links';
import { NavBarComponent } from '../../../../shared/nav-bar/nav-bar.component';
import { CanComponentDeactivate } from '../../../../shared/guards/can-deactivate-guard'
import { addUom } from '../../../../shared/interface/router-links';
import { ModalDirective } from 'ngx-bootstrap/modal';

@Component({
  selector: 'app-add-unit-measurement',
  templateUrl: './add-unit-measurement.component.html',
  styleUrls: ['./add-unit-measurement.component.css']
})
export class AddUnitMeasurementComponent implements OnInit, OnDestroy, CanComponentDeactivate {
  @ViewChild('duplicate') public duplicate: ModalDirective;
  @ViewChild('filebrowse') public filebrowse: ModalDirective;
  @ViewChild('deletesuccessModal') public deletesuccessModal: ModalDirective;
  @ContentChild('temp') testEl: any;
  basecodeOption: SelectItem [] = [] ;
   uomData: UnitMeasurement = new UnitMeasurement();
    subscription: Subscription;
    req_uomname: boolean;
    req_uomcode: boolean;
    public errormodal1: boolean;
    public uomCode;
    reqmsg1: string;
    reqmsg2: string;
    counter: number;
    public errormodal;
    public pattern;
    errorMsg:string;
  uomCodeList: string[] = [];
  uomNameList: string[] = [];
   minTypeaheadLength = 3;
    ispagebackPopupModal: boolean;
    isActionPerformed = false;
 // tslint:disable-next-line:max-line-length
  constructor(private unitMeasurementService: UnitMeasurementService, private masterSetupService: MasterSetupService,
     private router: Router, private _renderer: Renderer2,
     private _el: ElementRef) {
  }

  ngOnInit() {
    window.scrollTo(0, 0);
    // alert("browserEvent==>"+this.unitMeasurementService.isBrowserEventBtwComponent);
    this.getDropDownList();
    this.req_uomname = false;
    this.req_uomcode = false;
    this.ispagebackPopupModal = false;
  }
  
   uomCodeSuggestion($event) {
    this.uomCodeList=[];
    const uomCode = $event.target.value;
     this.subscription = this.unitMeasurementService.uomCodeSuggestion(uomCode).subscribe(data => {
    // this.geoCodeList = data.body;
     for(let g=0; g< data.body.length ; g++) {
     this.uomCodeList.push(data.body[g].uomCode);
    // this.geoCodeList = data.body[g].geoCode;
     }
    // console.log('geocodelist from type   ' + this.geoCodeList + '  ' +JSON.stringify(data.body));
     }, error =>  { throw error; } );
  }
/*  onNameSuggestion($event) {
      const packNameValue = $event.target.value;
    if (packNameValue !== null && packNameValue !== undefined && packNameValue !== '') {
      this.packingModel.toValidate = 'name';
      this.subscription = this.packingMaterialService.packNameSuggestion(this.packingModel).subscribe(descDetails => {
      this.packingMaterialService.packNameList = descDetails.body;
     }, error =>  { throw error; } );
    }
  }*/
  uomNameSuggestion($event) {
     this.uomNameList=[];
     const uomName = $event.target.value;
     this.subscription = this.unitMeasurementService.uomNameSuggestion(uomName).subscribe(data => {
    // this.geoCodeList = data.body;
     for(let g=0; g< data.body.length ; g++) {
       this.uomNameList.push(data.body[g].uomName);
     }
   //  console.log('geonamelist from type   ' + this.geoNameList + '  ' +JSON.stringify(data.body));
     }, error =>  { throw error; } );
  }

  save(actionstatus, uomCode) {
   // alert('uomname-->' + uomCode);
    this.unitMeasurementService.uomCodeParam = uomCode;
    this.isActionPerformed = true;
   // alert('in save ' + actionstatus);
    if (this.isValidForm()) {
       // alert('in validation pass ');
      this.uomData.action = actionstatus;
      this.subscription = this.unitMeasurementService.saveUOMDetails(this.uomData).subscribe(addUomDetail => {
      this.unitMeasurementService.uomDetails.push(<UnitMeasurement>addUomDetail);
      this.router.navigate([uom], { queryParams: { 'success': 1 } });
      },
        error => {
          // alert(this.unitMeasurementService.errorMessage);
          //this.ispagebackPopupModal = true;
          this.errorMsg = error;
                this.errorModal1();
                setTimeout(() => {this.hideModal(); }, 1000);  

        }, // Alerts need to be replaced by error
        () => console.log('Finished')
      );
    }
  }



 /** submit(uomCode) {
         if(this.isValidForm()){
            this.subscription = this.unitMeasurementService.submitUOMDetails(this.uomData).subscribe(addUomDetail => {
            this.unitMeasurementService.uomDetails.push(<UnitMeasurement>addUomDetail);
            this.router.navigate([uom],{ queryParams: { "success": 1 } });
          },
          error => {

            alert(this.unitMeasurementService.errorMessage);
          }, // Alerts need to be replaced by error
               () => console.log('Finished')
             )
          }
  }**/

  clear() {
    this.uomData = new UnitMeasurement();
  }

  close() {
    this.ispagebackPopupModal = false;
  }

  getDropDownList() {
    if (this.unitMeasurementService.uomDetails == null) {
      const link = this.masterSetupService.getChildObject('View Uom.GET');

      this.subscription = this.unitMeasurementService.getUnitMeasurementJSON(link.href).subscribe(addUomDetail => {
        this.unitMeasurementService.uomDetails = addUomDetail.body;
        this.unitMeasurementService.addChildObjects(addUomDetail.links);
        this.unitMeasurementService.uomDetails.forEach(uomDropDown => {
          if (uomDropDown.statusName === 'Active') {
            this.basecodeOption.push({ label: uomDropDown.uomCode, value: uomDropDown.uomId });
          }
        });
      },
      error => { // throw error
       },
      () => console.log('Finished')
      );
    } else {
      this.unitMeasurementService.uomDetails.forEach( uomDropDownData => {
        if (uomDropDownData.statusName === 'Active') {
          this.basecodeOption.push({ label: uomDropDownData.uomCode, value: uomDropDownData.uomId });
        }
      });
    }
  }

  getStyle() {
    if (this.req_uomcode === true) {
      return '#d43c3c';
     } else {
      return '#cfdee7';
     }
  }

  getStyle1() {
    if (this.req_uomname === true) {
         return '#d43c3c';
    } else {
         return '#cfdee7';
    }

  }

  isValidForm(): boolean {
   this.counter = 0;
    this.reqmsg1 = '';
    this.reqmsg2 = '';
    if (this.uomData.uomCode == null) {
      this.req_uomcode = true;
      this.counter++;
      this.reqmsg1 = 'Enter Uom Code';
      // this.errormodal=true;

    } else if ( this.uomData.uomCode != null ) {
        if ((this.uomData.uomCode.trim() === '') ) {
           this.req_uomcode = true;
        this.counter++;
    } else if ((this.uomData.uomCode.trim().length > 20)) {
       this.req_uomcode = true;
        this.counter++;
        this.reqmsg1 = 'Length of Uom Code should be less than 20';
      // this.errormodal=true;

    }else {
      this.req_uomcode = false;

    }

    }
    if ( this.uomData.uomName == null) {
      // alert(1);
      this.req_uomname = true;
      this.counter++;
      this.reqmsg2 = 'Enter Uom Name';
     // this.errormodal=true;

    } else if (this.uomData.uomName.trim() != null ) {
       // alert(2);
        if (this.uomData.uomName.trim() === '') {
        this.req_uomname = true;
        this.counter++;
        this.reqmsg2 = 'Enter Uom Name';
      // this.errormodal=true;

      }   else if ((this.uomData.uomName.trim().length > 200)) {
          // alert(3);
          this.req_uomname = true;
          this.counter++;
          this.reqmsg2 = 'Length of Uom Name should be less than 200';
        // this.errormodal=true;

      } else {
      this.req_uomname = false;

    }
    }

    if (this.counter !== 0) {
        this.showModal();

         setTimeout(() => {this.hideModal(); }, 3000);

      return false;
    } else {
      return true;
     }


}


public errorModal1(): void {
    this.errormodal1 = true;
  }

public showModal(): void {
    this.errormodal = true;
  }
 
  public hideModal(): void {
    this.deletesuccessModal.hide();
	this.errormodal1 = false;
  }
 
  public onHidden(): void {
    this.errormodal = false;
  }


  validateNumericsOnly(keyEvent) {
       const result = (keyEvent.which) ? keyEvent.which : keyEvent.keyCode;
       // alert("==result==>"+result);
        /**if(result == 190 || result == 110){
              return true;
        }else if (result == 16 || result > 31 && (result < 48 || result > 57)) {
            alert("Enter only Numeric values");
            return false;
        }else if(result == 17 || result == 67 || result == 86){
           alert("CTRL + C or CTRL + V are not allowed");
           return false;
        }else if(result >= 96 ||  result <= 105){
              return true;
        }**/

          if (result === 110 || result === 190) {
              return true;
          } else if (result === 17 || result === 67 || result === 86) {
           alert('CTRL + C or CTRL + V are not allowed');
           return false;
          } else if (result !== 46 && result > 31
            && (result < 48 || result > 57)) {
             alert('Enter only numeric and Decimals');
             return false;
         }
         return true;
  }

  ngOnDestroy() {
    // Called once, before the instance is destroyed.
    // this.subscription.unsubscribe();
  }

  canDeactivate(): boolean {
      return this.isActionPerformed;
  }
}
