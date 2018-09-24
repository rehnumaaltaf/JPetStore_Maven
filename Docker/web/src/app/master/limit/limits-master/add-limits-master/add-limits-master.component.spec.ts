import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddLimitsMasterComponent } from './add-limits-master.component';

describe('AddLimitsMasterComponent', () => {
  let component: AddLimitsMasterComponent;
  let fixture: ComponentFixture<AddLimitsMasterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddLimitsMasterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddLimitsMasterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
