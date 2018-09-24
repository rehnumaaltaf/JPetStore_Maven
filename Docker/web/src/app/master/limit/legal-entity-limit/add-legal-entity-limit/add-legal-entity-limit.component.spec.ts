import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddLegalEntityLimitComponent } from './add-legal-entity-limit.component';

describe('AddLegalEntityLimitComponent', () => {
  let component: AddLegalEntityLimitComponent;
  let fixture: ComponentFixture<AddLegalEntityLimitComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddLegalEntityLimitComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddLegalEntityLimitComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
