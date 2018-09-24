import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddUnitMasterComponent } from './add-unit-master.component';

describe('AddUnitMasterComponent', () => {
  let component: AddUnitMasterComponent;
  let fixture: ComponentFixture<AddUnitMasterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddUnitMasterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddUnitMasterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
