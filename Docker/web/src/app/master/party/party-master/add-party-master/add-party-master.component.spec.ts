import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddPartyMasterComponent } from './add-party-master.component';

describe('AddPartyMasterComponent', () => {
  let component: AddPartyMasterComponent;
  let fixture: ComponentFixture<AddPartyMasterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddPartyMasterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddPartyMasterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
