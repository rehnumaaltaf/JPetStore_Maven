import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { fakeAsync, tick, inject } from '@angular/core/testing';
import { APP_BASE_HREF } from '@angular/common';
import { FormsModule, ReactiveFormsModule, FormBuilder } from '@angular/forms';
import { By } from '@angular/platform-browser';

import { UserRoleData } from '../user-role-data';
import { UserRoleService } from '../service/user-role.service';
import { Route, RouterModule, Router } from '@angular/router';
import { HeaderComponent } from '../../../shared/header/header.component';
import { HttpModule } from '@angular/http';
import { LocaleService } from 'angular-l10n';
import { UserRoleComponent } from './user-role.component';

describe('UserRoleComponent', () => {
  const component: UserRoleComponent;
  const fixture: ComponentFixture<UserRoleComponent>;
  const userRoleService: UserRoleService;
  const formbuilder: FormBuilder;
  const locale: LocaleService;
  const router: Router;
  const el: HTMLElement;
  const ROUTES: Route[] = [
    { path: '', component: UserRoleComponent }
  ]

  beforeEach(() => {
    class RouterStub {
            navigateByUrl(url: string) { return url; }
        }
    // refine the test module by declaring the test component
    TestBed.configureTestingModule({
      imports: [ReactiveFormsModule, FormsModule, HttpModule, RouterModule.forRoot(ROUTES)],
      declarations: [UserRoleComponent, HeaderComponent],
      providers: [userRoleService, LocaleService, { provide: APP_BASE_HREF, useValue: '/' }]
    });

    // create component and test fixture
    fixture = TestBed.createComponent(UserRoleComponent);

    // get test component from the fixture
    component = fixture.componentInstance;
  });

});
