import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PartyMasterComponent } from './party-master.component';

describe('PartyMasterComponent', () => {
  let component: PartyMasterComponent;
  let fixture: ComponentFixture<PartyMasterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PartyMasterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PartyMasterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});