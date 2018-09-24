import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddTraderLimitComponent } from './add-trader-limit.component';

describe('AddTraderLimitComponent', () => {
  let component: AddTraderLimitComponent;
  let fixture: ComponentFixture<AddTraderLimitComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddTraderLimitComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddTraderLimitComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
