import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewCertificationComponent } from './view-certification.component';

describe('ViewCertificationComponent', () => {
  let component: ViewCertificationComponent;
  let fixture: ComponentFixture<ViewCertificationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewCertificationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewCertificationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
