import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PartyLimitComponent } from './party-limit.component';

describe('PartyLimitComponent', () => {
  let component: PartyLimitComponent;
  let fixture: ComponentFixture<PartyLimitComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PartyLimitComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PartyLimitComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
