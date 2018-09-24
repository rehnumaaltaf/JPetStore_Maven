import { TestBed, ComponentFixture, async, fakeAsync, tick, inject } from '@angular/core/testing';
import { LoginComponent } from './login.component';
import { APP_BASE_HREF } from '@angular/common';
import { LoginService } from './login.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { By } from '@angular/platform-browser';
import { Route, RouterModule } from '@angular/router';
import { HeaderComponent } from '../../shared/header/header.component';
import { HttpModule } from '@angular/http';

fdescribe('UserServiceTest', () => {

    const ROUTES: Route[] = [
    { path: '', component: LoginComponent }
  ]
  beforeEach(() => {
   TestBed.configureTestingModule({
       imports: [ReactiveFormsModule, FormsModule, HttpModule, RouterModule.forRoot(ROUTES)],
      declarations: [LoginComponent, HeaderComponent],
       providers: [LoginService, { provide: APP_BASE_HREF, useValue: '/' }]
    });
  });

  fit('should be able to assert the service is returning http response', inject([LoginService], (service: LoginService) => {
    expect(service.postJSON({"email":"test@test.com","pwd":"test@123"})).toBeTruthy();
  }));

  fit('should be able to assert the service is returning success JSON response', inject([LoginService], (service: LoginService) => {
    
        let param = {email: 'test@sample.com', pwd: 'test@123'};
        service.postJSON(param).subscribe((res: Response) => {
          expect(service.isLoggedIn).toBeTruthy();
          expect(res).toBeDefined();
          expect(res.status).toBe(200);
         
          
        });


  }));

  
});
