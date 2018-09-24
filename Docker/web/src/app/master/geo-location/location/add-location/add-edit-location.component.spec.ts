import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddEditLocationComponent } from './add-edit-location.component';
import { Route, RouterModule, Router } from '@angular/router';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { NavBarComponent } from '../../../../shared/nav-bar/nav-bar.component';
import { ModalModule, AccordionModule, TypeaheadModule } from 'ngx-bootstrap';
import { TreeviewModule } from 'ngx-treeview';
import { LocalizationModule } from 'angular-l10n';
import { APP_BASE_HREF } from '@angular/common';
import { location } from '../../../../shared/interface/router-links';
import { ConfirmBoxComponent } from '../confirm-box/confirm-box.component';
import { MessageComponent } from '../message/message.component';
import { LocationService } from '../service/location.service';
import { MasterSetupService } from 'app/master/master-setup/service/master-setup.service';
import { DropDownsModule } from '@progress/kendo-angular-dropdowns';
fdescribe('AddEditLocationComponent', () => {
  let component: AddEditLocationComponent;
  let fixture: ComponentFixture<AddEditLocationComponent>;
   const ROUTES: Route[] = [
    { path: '', component: AddEditLocationComponent }
  ]

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [FormsModule, HttpModule,
      ModalModule.forRoot(),
      DropDownsModule,
    AccordionModule.forRoot(),
    TreeviewModule.forRoot(),
    LocalizationModule.forRoot(),
    TreeviewModule.forRoot(),
    TypeaheadModule.forRoot(), RouterModule.forRoot(ROUTES)], // , RouterModule.forRoot(ROUTES)
      declarations: [ AddEditLocationComponent, NavBarComponent, ConfirmBoxComponent, MessageComponent ],
      providers: [
      LocationService , MasterSetupService, { provide: APP_BASE_HREF, useValue: location }
    ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddEditLocationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
    component.ngOnInit();
  });

  fit('should be created', () => {
    expect(component).toBeTruthy();
  });

  fit('should be able to validate all mandatory fields', () => {
    component.locationDetail.locCode = '';
    component.locationDetail.locName = '';
    component.locationDetail.fkGeoId = 0;
    component.locationDetail.fkLocTypeId = [];
    expect(component.doValidate()).toBe(false);
  });

   fit('should be able to validate Location Code is not empty', () => {
    component.locationDetail.locCode = '';
    component.locationDetail.locName = 'test';
    component.locationDetail.fkGeoId = 2;
    component.locationDetail.fkLocTypeId = [1];
    expect(component.doValidate()).toBe(false);
  });

  fit('should be able to validate Location Name is not empty', () => {
    component.locationDetail.locCode = 'test';
    component.locationDetail.locName = '';
    component.locationDetail.fkGeoId = 2;
    component.locationDetail.fkLocTypeId = [1];
    expect(component.doValidate()).toBe(false);
  });

  fit('should be able to validate Location Country is not empty', () => {
    component.locationDetail.locCode = 'test';
    component.locationDetail.locName = 'test';
    component.locationDetail.fkGeoId = 0;
    component.locationDetail.fkLocTypeId = [1];
    expect(component.doValidate()).toBe(false);
  });

  fit('should be able to validate Location Type is not empty', () => {
    component.locationDetail.locCode = 'test';
    component.locationDetail.locName = 'test';
    component.locationDetail.fkGeoId = 0;
    component.locationDetail.fkLocTypeId = [];
    expect(component.doValidate()).toBe(false);
  });

  fit('should be able to validate all the fields', () => {
    component.locationDetail.locCode = 'test';
    component.locationDetail.locName = 'test';
    component.locationDetail.fkGeoId = 2;
    component.locationDetail.fkLocTypeId = [1];
    expect(component.doValidate()).toBe(true);
  });

  it('Validate Duplicate Location Code', async(() => {
      component.locationDetail.locCode = 'test';
     // component.reqLocationCode = true;
      component.locationCodeIsUnique();
      setTimeout(() => {
        fixture.detectChanges();
        fixture.whenStable().then(() => {
          // console.log('message ---- ' + component.reqLocationCode);
          expect(component.reqLocationCode).toBeTruthy();
        });
       }, 2000);
  }));


  fit('Validate Duplicate Location Name', async(() => {
      component.locationDetail.locName = 'test';
     // component.reqLocationCode = true;
      component.locationCodeIsUnique();
      setTimeout(() => {
        fixture.detectChanges();
        fixture.whenStable().then(() => {
          // console.log('message ---- ' + component.reqLocationCode);
          expect(component.reqLocationName).toBeTruthy();
        });
       }, 2000);
  }));

  fit('Validate Duplicate Location Full Name', async(() => {
      component.locationDetail.locFullName = 'test';
     // component.reqLocationCode = true;
      component.locationFullNameIsUnique();
      setTimeout(() => {
          fixture.detectChanges();
          fixture.whenStable().then(() => {
            console.log('message ---- ' + component.reqLocationFullName);
            expect(component.reqLocationFullName).toBeTruthy();
          });
        }, 2000);
  }));


  fit('Validate Suggestion Location Code', async(() => {
      const evt = { target: { value: 'g' } };
     // component.reqLocationCode = true;
      component.locCodeSuggestion(evt);
      setTimeout(() => {
             fixture.detectChanges();
          fixture.whenStable().then(() => {
             let check = false;
             for ( let i = 0 ; i < component.locationCodeList.length; i++) {
              console.log(component.locationCodeList[i].toLowerCase().indexOf(evt.target.value.toLowerCase()),
              component.locationCodeList[i].toLowerCase(), evt.target.value.toLowerCase());
              check = (component.locationCodeList[i].toLowerCase().indexOf(evt.target.value.toLowerCase()) >= 0) ? true : false;
              console.log(check);
               expect(check).toBeTruthy();
             }
              });
        }, 2000);
  }));

   fit('Validate Suggestion Location Name', async(() => {
      const evt = { target: { value: 'g' } };
     // component.reqLocationCode = true;
      component.locNameSuggestion(evt);
      setTimeout(() => {
             fixture.detectChanges();
          fixture.whenStable().then(() => {
             let check = false;
             for ( let i = 0 ; i < component.locationNameList.length; i++) {
              console.log(component.locationNameList[i].toLowerCase().indexOf(evt.target.value.toLowerCase()),
              component.locationNameList[i].toLowerCase(), evt.target.value.toLowerCase());
              check = (component.locationNameList[i].toLowerCase().indexOf(evt.target.value.toLowerCase()) >= 0) ? true : false;
              console.log(check);
               expect(check).toBeTruthy();
             }
              });
        }, 2000);
  }));
   /*fit('Validate Suggestion Location Code', (done) => {
     const evt = { target: { value: 'g' } };
      component.locCodeSuggestion(evt);
        setTimeout(() => {
           fixture.detectChanges();
        fixture.whenStable().then(() => {
             console.log('message ---- ' + JSON.stringify(component.locationCodeList));
             let check = false;
             for ( let i = 0 ; i < component.locationCodeList.length; i++) {
              console.log(component.locationCodeList[i].toLowerCase().indexOf(evt.target.value.toLowerCase()),
              component.locationCodeList[i].toLowerCase(), evt.target.value.toLowerCase());
              check = (component.locationCodeList[i].toLowerCase().indexOf(evt.target.value.toLowerCase()) >= 0) ? true : false;
              console.log(check);
               expect(check).toBeTruthy();
             }
            done();
        });
       }, 3000);
  });*/
/*
it('should respond with response1', () => {
    this.locationService.locCodeSuggestion(locCode).subscribe(data => {
      this.locationCodeList = data;
     }, error =>  { throw error; } );
  });*/

/*
   fit('Validate Duplicate Location Full Name', async(() => {
      component.locationDetail.locFullName = 'test';
     // component.reqLocationCode = true;
      component.locationFullNameIsUnique();
     setTimeout(() => {
        fixture.detectChanges();
        fixture.whenStable().then(() => {
          console.log('message ---- ' + component.reqLocationFullName);
          expect(component.reqLocationFullName).toBeTruthy();
        });
       }, 2000);
  })); */
});
