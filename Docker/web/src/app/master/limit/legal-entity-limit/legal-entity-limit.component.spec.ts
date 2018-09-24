import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LegalEntityLimitComponent } from './legal-entity-limit.component';

describe('LegalEntityLimitComponent', () => {
  let component: LegalEntityLimitComponent;
  let fixture: ComponentFixture<LegalEntityLimitComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LegalEntityLimitComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LegalEntityLimitComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
