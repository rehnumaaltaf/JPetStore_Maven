import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule, ReactiveFormsModule,FormBuilder } from '@angular/forms';
import { By }              from '@angular/platform-browser';
import { AppComponent } from '../../../../app.component';
import { AddUnitMeasurementComponent } from './add-unit-measurement.component';
import { UnitMeasurementService } from '../service/unit-measurement.service';
import { MasterSetupComponent } from '../../../master-setup/master-setup.component';
import { MasterSetupService } from '../../../master-setup/service/master-setup.service';
import { AbstractLinkService } from  '../../../../shared/service/abstract-link.service';
import { APP_BASE_HREF } from '@angular/common';
import { Route, RouterModule,Router } from '@angular/router';
import { NavBarComponent} from '../../../../shared/nav-bar/nav-bar.component';
import { ModalModule } from "ngx-bootstrap";
import { HttpModule,Http } from '@angular/http';
import { Link } from '../../../../shared/interface/link';



fdescribe('AddUnitMeasurementComponent', () => {
  let component: AddUnitMeasurementComponent;
  let fixture: ComponentFixture<AddUnitMeasurementComponent>;
  let http: Http;
  let link: Link;

  const ROUTES: Route[] = [
    { path: '', component: AddUnitMeasurementComponent }
  ]

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [AddUnitMeasurementComponent,NavBarComponent,MasterSetupComponent],
      providers: [UnitMeasurementService,MasterSetupService,AbstractLinkService, { provide: APP_BASE_HREF, useValue: '/' }],
       imports: [
         FormsModule,ModalModule,HttpModule,RouterModule.forRoot(ROUTES)
        ]
      
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddUnitMeasurementComponent);
    component = fixture.componentInstance;
  
    let masterSetupService = new UnitMeasurementService(http);
    link = masterSetupService.getChildObject('View Uom.GET');
    console.log("==link===>"+link);
    fixture.detectChanges();
  });

  /*fit('should be created', () => {
    expect(component).toBeTruthy();
  });*/

 
 fit('should be able to validate when the UOM Code is Empty', async(() => {
    let fixture = TestBed.createComponent(AddUnitMeasurementComponent);
    fixture.detectChanges();
    fixture.whenStable().then(() => {
      let input = fixture.debugElement.query(By.css('input'));
      let el = input.nativeElement;

      expect(el.value).toBe('');

      el.value = 'someValue';
      el.dispatchEvent(new Event('input'));

     
    });
  
     expect(fixture.componentInstance.uomData.uomCode).toBeTruthy();
  }));


  it('should map state state1 to url /state1 ', function() {
       // expect($state.href('state1', {})).toEqual('#/state1');
    });

 /* fit('should be able to validate when UOM code is empty', () => {
    let errors = {};
    const uomcode = component.uomData.uomCode;
    if(expect(uomcode).toBeNull()){
       expect(uomcode).toBeFalsy();
    };
   
  });*/
});
