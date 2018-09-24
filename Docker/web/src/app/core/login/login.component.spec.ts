import { TestBed, ComponentFixture, async, fakeAsync, tick, inject } from '@angular/core/testing';
import { LoginComponent } from './login.component';
import { APP_BASE_HREF } from '@angular/common';
import { LoginService } from './login.service';
import { FormsModule, ReactiveFormsModule,FormBuilder } from '@angular/forms';
import { By } from '@angular/platform-browser';
import { Route, RouterModule,Router } from '@angular/router';
import { HeaderComponent } from '../../shared/header/header.component';
import { HttpModule } from '@angular/http';
import { LocaleService } from 'angular-l10n';



fdescribe('Component: LoginComponent', () => {

  let component: LoginComponent;
  let loginservice:LoginService;
  let formbuilder: FormBuilder;
  let locale: LocaleService;
  let router:Router;
  let el: HTMLElement;
  let fixture: ComponentFixture<LoginComponent>;
  const ROUTES: Route[] = [
    { path: '', component: LoginComponent }
  ]

  beforeEach(() => {

    class RouterStub {
            navigateByUrl(url: string) { return url; }
        }

    // refine the test module by declaring the test component
    TestBed.configureTestingModule({
      imports: [ReactiveFormsModule, FormsModule, HttpModule, RouterModule.forRoot(ROUTES)],
      declarations: [LoginComponent, HeaderComponent],
      providers: [LoginService, { provide: APP_BASE_HREF, useValue: '/' }]
    });

    // create component and test fixture
    fixture = TestBed.createComponent(LoginComponent);

    // get test component from the fixture
    component = fixture.componentInstance;
    component.ngOnInit();
  });

   fit('should be able to validate when form is empty', () => {
     expect(component.userForm.valid).toBeFalsy();
   });

  fit('should be able to validate email field', () => {
    let errors = {};
    const email = component.userForm.controls['email'];
    expect(email.valid).toBeFalsy();

    // Email field is mandatory
    errors = email.errors || {};
    expect(errors['required']).toBeTruthy();

   // Set email to blank string
    email.setValue(' ');
    errors = email.errors || {};
    expect(errors['required']).toBeFalsy();
    expect(errors['pattern']).toBeTruthy();

    // Set email to invalid value
    email.setValue('test');
    errors = email.errors || {};
    expect(errors['required']).toBeFalsy();
    expect(errors['pattern']).toBeTruthy();

    // Set email to valid value
    email.setValue('test@example.com');
    errors = email.errors || {};
    expect(errors['required']).toBeFalsy();
    expect(errors['pattern']).toBeFalsy();
  });

  fit('should be able to validate password field', () => {
    let errors = {};
    const password = component.userForm.controls['pwd'];

    // Password field is mandatory
    errors = password.errors || {};
    expect(errors['required']).toBeTruthy();

     // Set email to blank string
    password.setValue('');
    errors = password.errors || {};
    expect(errors['required']).toBeTruthy();
    expect(errors['pattern']).toBeFalsy();

    // Set email to invalid value
    password.setValue('test');
    errors = password.errors || {};
    expect(errors['required']).toBeFalsy();
    expect(errors['pattern']).toBeFalsy();
 
    // Set email to valid value
    password.setValue('test@123');
    errors = password.errors || {};
    expect(errors['required']).toBeFalsy();
    expect(errors['pattern']).toBeFalsy();

  });

   

  fit('should be able to submit a valid form', () => {
    component.userForm.controls['email'].setValue('test@test.com');
    component.userForm.controls['pwd'].setValue('123456789');
    expect(component.userForm.valid).toBeTruthy();
  });

  fit('should be able to validate when email field is empty', () => {
    component.userForm.controls['email'].setValue('');
    component.userForm.controls['pwd'].setValue('123456789');
    expect(component.userForm.valid).toBeFalsy();
  });

   fit('should be able to validate when email field is Invalid', () => {
    component.userForm.controls['email'].setValue('test');
    component.userForm.controls['pwd'].setValue('test@123');
    expect(component.userForm.valid).toBeFalsy();
  });

  fit('should be able to validate when password field is empty', () => {
    expect(component.userForm.valid).toBeFalsy();
    component.userForm.controls['email'].setValue('test@test.com');
    component.userForm.controls['pwd'].setValue('');
    expect(component.userForm.valid).toBeFalsy();
  });

  fit('should be able to validate when password field is Invalid', () => {
    component.userForm.controls['email'].setValue('test@test.com');
    component.userForm.controls['pwd'].setValue('test');
    expect(component.userForm.valid).toBeFalsy();
  });

  fit('should be able to validate constructor attributes', () => {
      let param = {email: 'test@sample.com', pwd: 'test@123'};
      var loginComponent = new LoginComponent(router,loginservice,formbuilder,locale);
       const spy = spyOn(router, 'navigateByUrl');
        el.click();
        const navArgs = spy.calls.first().args[0];
        expect(navArgs).toBe('master/mastersetup');
        expect(formbuilder).toBeTruthy();
        expect(loginComponent.onTestPost()).toBeTruthy();
      //expect(newClubMember.firstname).toBe('John');
      //expect(newClubMember.surname).toBe('Velo');
      //expect(newClubMember.fullname()).toBe('John Velo');
  });

  it('should be able to log in and navigate to mastersetup', inject([Router], (router: Router) => {
        const spy = spyOn(router, 'navigateByUrl');
        el.click();
        const navArgs = spy.calls.first().args[0];
        expect(navArgs).toBe('master/mastersetup');
    }));


})


