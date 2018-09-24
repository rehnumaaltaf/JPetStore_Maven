import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CertificationMappingComponent } from './certification-mapping.component';

describe('CertificationMappingComponent', () => {
  let component: CertificationMappingComponent;
  let fixture: ComponentFixture<CertificationMappingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CertificationMappingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CertificationMappingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
