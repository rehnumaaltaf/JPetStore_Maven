import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewLimitsMasterComponent } from './view-limits-master.component';

describe('ViewLimitsMasterComponent', () => {
  let component: ViewLimitsMasterComponent;
  let fixture: ComponentFixture<ViewLimitsMasterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewLimitsMasterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewLimitsMasterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
