import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewUnitMasterComponent } from './view-unit-master.component';

describe('ViewUnitMasterComponent', () => {
  let component: ViewUnitMasterComponent;
  let fixture: ComponentFixture<ViewUnitMasterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewUnitMasterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewUnitMasterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
