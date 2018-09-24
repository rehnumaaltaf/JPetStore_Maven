import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewLegalEntityLimitComponent } from './view-legal-entity-limit.component';

describe('ViewLegalEntityLimitComponent', () => {
  let component: ViewLegalEntityLimitComponent;
  let fixture: ComponentFixture<ViewLegalEntityLimitComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewLegalEntityLimitComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewLegalEntityLimitComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
