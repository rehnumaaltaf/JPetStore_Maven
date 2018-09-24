import { async, ComponentFixture, TestBed, fakeAsync, tick, inject } from '@angular/core/testing';
import { ProfitCenterModel } from '../model/profit-center-model';
import {DialogModule} from 'primeng/primeng';
import { UnitService } from '../service/unit.service';
import { Subscription } from 'rxjs/Subscription';
import { HeaderComponent } from '../../../../shared/header/header.component';
import { NavBarComponent } from '../../../../shared/nav-bar/nav-bar.component';
import { AddUnitComponent } from './add-unit.component';
import { APP_BASE_HREF } from '@angular/common';
import { unit } from '../../../../shared/interface/router-links';
import { Route, RouterModule, Router } from '@angular/router';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

fdescribe('Component: AddUnitComponent', () => {
  let component: AddUnitComponent;
  let fixture: ComponentFixture<AddUnitComponent>;
  // let returnVal: Boolean;
  const ROUTES: Route[] = [
    { path: '', component: AddUnitComponent }
  ]

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [FormsModule, HttpModule, RouterModule.forRoot(ROUTES)],
      declarations: [AddUnitComponent, NavBarComponent],
      providers: [UnitService, { provide: APP_BASE_HREF, useValue: unit }]
    })
    .compileComponents();
  }));

  beforeEach(() => {

    // class RouterStub {
    //         navigateByUrl(url: string) { return url; }
    //     }

    // TestBed.configureTestingModule({
    //  imports: [DialogModule, FormsModule, ReactiveFormsModule, HttpModule, RouterModule.forRoot(ROUTES)],
    //  declarations: [AddUnitComponent, HeaderComponent],
    //  providers: [UnitService, { provide: APP_BASE_HREF, useValue: unit }],
    //  schemas: [ CUSTOM_ELEMENTS_SCHEMA ]
    // });

    fixture = TestBed.createComponent(AddUnitComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
    component.ngOnInit();
  });

  fit('should be created', () => {
    expect(component).toBeTruthy();
  });

  fit('should be able to validate all mandatory fields', () => {
    component.profitCenterModel.unitCode = '';
    component.profitCenterModel.unitName = '';
    component.profitCenterModel.unitFullName = '';
    expect(component.validateInput()).toBe(false);
  });

  fit('should be able to validate Unit Code is not empty', () => {
    component.profitCenterModel.unitCode = '';
    component.profitCenterModel.unitName = 'test';
    component.profitCenterModel.unitFullName = 'test';
    expect(component.validateInput()).toBe(false);
  });

  fit('should be able to validate Unit Name is not empty', () => {
    component.profitCenterModel.unitCode = 'test';
    component.profitCenterModel.unitName = '';
    component.profitCenterModel.unitFullName = 'test';
    // this.returnVal = component.validateInput();
    expect(component.validateInput()).toBeFalsy();
  });

  fit('should be able to validate Unit Full Name is not empty', () => {
    component.profitCenterModel.unitCode = 'test';
    component.profitCenterModel.unitName = 'test';
    component.profitCenterModel.unitFullName = '';
    expect(component.validateInput()).toBe(false);
  });

  fit('should be able to validate All mandatory fields is not empty', () => {
    component.profitCenterModel.unitCode = 'test';
    component.profitCenterModel.unitName = 'test';
    component.profitCenterModel.unitFullName = 'test';
    expect(component.validateInput()).toBe(true);
  });

  fit('On page load Group Unit Radio should be checked to No', () => {
    component.ngOnInit();
    expect(component.profitCenterModel.groupUnit).toBe('0');
  });

  fit('On page load Parent Unit dropdown list to be set', () => {
    component.loadDropdownList();
    expect(component.profitCenterModel.parentUnitList).not.toBe(null);
  });

  // fit('Validate Duplicate Unit Code', () => {
  //   component.profitCenterModel.unitCode = 'test';
  //   // component.profitCenterModel.toValidate = 'UC';
  //   component.validateUnitCode();
  //   tick(2000);
  //   console.log('message ---- ' + component.profitCenterModel.unitCode);
  //   expect(component.req_unitCode).not.toBe(true);
  // });

  fit('Validate Duplicate Unit Code', () => {
      component.profitCenterModel.unitCode = 'test';
      component.req_unitCode = true;
      component.validateUnitCode();
      tick();
      fixture.detectChanges();
      console.log('message ---- ' + component.valMessage);
      expect(component.valMessage).toBeTruthy();
  });

  // fit('Validate Duplicate Unit Code',
  //   fakeAsync(inject([AddUnitComponent], (addComponent: AddUnitComponent) => {
  //     // let val: string;
  //     // mockBackend.connections.subscribe(c => {
  //     //   expect(c.request.url).toBe('http://localhost:3000/todos');
  //     //   const response = new ResponseOptions({body: `[{"id": 5, "item": "do the laundry", "completed": false}]`});
  //     //   c.mockRespond(new Response(response));
  //     // });
  //     // originDefinitionService.saveOriginDetails({}).subscribe((response) => {
  //     //   res = response;
  //     // });
  //     addComponent.profitCenterModel.unitCode = 'test';
  //     addComponent.validateUnitCode();
  //     tick();
  //     console.log('message ---- ' + addComponent.profitCenterModel.unitCode);
  //     expect(addComponent.profitCenterModel.unitCode).toBe(null);
  //   }))
  // );
});
