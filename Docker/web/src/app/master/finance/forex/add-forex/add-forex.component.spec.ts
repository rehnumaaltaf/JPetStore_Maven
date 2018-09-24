import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddForexComponent } from './add-forex.component';

describe('AddForexComponent', () => {
  let component: AddForexComponent;
  let fixture: ComponentFixture<AddForexComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddForexComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddForexComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
