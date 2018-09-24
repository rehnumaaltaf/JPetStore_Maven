import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewPartyMasterComponent } from './view-party-master.component';

describe('ViewPartyMasterComponent', () => {
  let component: ViewPartyMasterComponent;
  let fixture: ComponentFixture<ViewPartyMasterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewPartyMasterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewPartyMasterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
