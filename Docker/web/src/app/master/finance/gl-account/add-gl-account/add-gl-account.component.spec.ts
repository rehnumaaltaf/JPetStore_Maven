import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddGlAccountComponent } from './add-gl-account.component';

describe('AddGlAccountComponent', () => {
  let component: AddGlAccountComponent;
  let fixture: ComponentFixture<AddGlAccountComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddGlAccountComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddGlAccountComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
