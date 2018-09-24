import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TraderLimitComponent } from './trader-limit.component';

describe('TraderLimitComponent', () => {
  let component: TraderLimitComponent;
  let fixture: ComponentFixture<TraderLimitComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TraderLimitComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TraderLimitComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
