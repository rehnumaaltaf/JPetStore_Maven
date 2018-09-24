import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ForexLimitComponent } from './forex-limit.component';

describe('ForexLimitComponent', () => {
  let component: ForexLimitComponent;
  let fixture: ComponentFixture<ForexLimitComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ForexLimitComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ForexLimitComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
