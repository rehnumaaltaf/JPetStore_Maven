/**import { TestBed, ComponentFixture, async, fakeAsync, tick, inject } from '@angular/core/testing';
import { UnitMeasurementComponent } from './unit-measurement.component';
import { APP_BASE_HREF } from '@angular/common';
import { UnitMeasurementService } from './service/unit-measurement.service';
import { FormsModule, ReactiveFormsModule,FormBuilder } from '@angular/forms';
import { By } from '@angular/platform-browser';
import { Route, RouterModule,Router } from '@angular/router';
import { HttpModule, JsonpModule } from '@angular/http';

fdescribe('Component: LoginComponent', () => {

  let component: UnitMeasurementComponent;
  let uomservice:UnitMeasurementService;
  let formbuilder: FormBuilder;
  let router:Router;
  let el: HTMLElement;
  let fixture: ComponentFixture<UnitMeasurementComponent>;
  const ROUTES: Route[] = [
    { path: '', component: UnitMeasurementComponent }
  ]

beforeEach(() => {

    class RouterStub {
            navigateByUrl(url: string) { return url; }
        }

    // refine the test module by declaring the test component
    TestBed.configureTestingModule({
      imports: [ReactiveFormsModule, FormsModule, HttpModule, RouterModule.forRoot(ROUTES)],
      declarations: [UnitMeasurementComponent],
      providers: [UnitMeasurementService, { provide: APP_BASE_HREF, useValue: '/' }]
    });

    // create component and test fixture
    fixture = TestBed.createComponent(UnitMeasurementComponent);

    // get test component from the fixture
    component = fixture.componentInstance;
    component.ngOnInit();
  });
  fit('should be able to validate when form is empty', () => {
     expect(component.uomForm.valid).toBeFalsy();
   });
   fit('should be able to submit a valid form', () => {
    expect(component.uomForm.valid).toBeTruthy();
  });
  fit('should be able to validate when UOM Full Name field is empty', () => {
    component.uomForm.controls['uomName'].setValue('Killogram');
    component.uomForm.controls['uomFullName'].setValue('');
    component.uomForm.controls['uomCode'].setValue('KG');
    component.uomForm.controls['uomBaseCode'].setValue('KG');
    component.uomForm.controls['uomConversionFactor'].setValue('1000');
    expect(component.uomForm.valid).toBeTruthy();
  });
  fit('should be able to validate when UOM Conversion Factor field is empty', () => {
    component.uomForm.controls['uomName'].setValue('Killogram');
    component.uomForm.controls['uomFullName'].setValue('Metric Killogram');
    component.uomForm.controls['uomCode'].setValue('KG');
    component.uomForm.controls['uomBaseCode'].setValue('KG');
    component.uomForm.controls['uomConversionFactor'].setValue('');
    expect(component.uomForm.valid).toBeTruthy();
  });
  fit('should be able to validate when UOM Base Code field is empty', () => {
    component.uomForm.controls['uomName'].setValue('Killogram');
    component.uomForm.controls['uomFullName'].setValue('Metric Killogram');
    component.uomForm.controls['uomCode'].setValue('KG');
    component.uomForm.controls['uomBaseCode'].setValue('');
    component.uomForm.controls['uomConversionFactor'].setValue('1000');
    expect(component.uomForm.valid).toBeTruthy();
  });
  fit('should NOT be able to validate when UOM Name field is empty', () => {
    component.uomForm.controls['uomName'].setValue('');
    component.uomForm.controls['uomFullName'].setValue('Metric Killogram');
    component.uomForm.controls['uomCode'].setValue('KG');
    component.uomForm.controls['uomBaseCode'].setValue('KG');
    component.uomForm.controls['uomConversionFactor'].setValue('1000');
    expect(component.uomForm.valid).toBeFalsy();
  });
  fit('should NOT be able to validate when UOM Code field is empty', () => {
    component.uomForm.controls['uomName'].setValue('Killogram');
    component.uomForm.controls['uomFullName'].setValue('Metric Killogram');
    component.uomForm.controls['uomCode'].setValue('');
    component.uomForm.controls['uomBaseCode'].setValue('KG');
    component.uomForm.controls['uomConversionFactor'].setValue('1000');
    expect(component.uomForm.valid).toBeFalsy();
  });
  fit('should NOT be able to validate when UOM Code and UOM Name fields are empty', () => {
    component.uomForm.controls['uomName'].setValue('');
    component.uomForm.controls['uomFullName'].setValue('Metric Killogram');
    component.uomForm.controls['uomCode'].setValue('');
    component.uomForm.controls['uomBaseCode'].setValue('KG');
    component.uomForm.controls['uomConversionFactor'].setValue('1000');
    expect(component.uomForm.valid).toBeFalsy();
  });
  fit('should be able to validate constructor attributes', () => {
      let param = {uomCode: 'KG',uomName: 'Killogram', uomFullName: 'Metric Killogram',uomBaseCode:'KG',uomConversionFactor:'1000'};
      var uomComponent = new UnitMeasurementComponent(uomservice);
      expect(formbuilder).toBeTruthy();
      expect(uomComponent.showHide).toBeTruthy();
      //expect(newClubMember.firstname).toBe('John');
      //expect(newClubMember.surname).toBe('Velo');
      //expect(newClubMember.fullname()).toBe('John Velo');
  });
})**/