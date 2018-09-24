import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CertificationAgencyComponent } from './certification-agency.component';

describe('CertificationAgencyComponent', () => {
  let component: CertificationAgencyComponent;
  let fixture: ComponentFixture<CertificationAgencyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CertificationAgencyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CertificationAgencyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
