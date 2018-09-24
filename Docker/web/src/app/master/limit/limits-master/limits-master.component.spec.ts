import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LimitsMasterComponent } from './limits-master.component';

describe('LimitsMasterComponent', () => {
  let component: LimitsMasterComponent;
  let fixture: ComponentFixture<LimitsMasterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LimitsMasterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LimitsMasterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
