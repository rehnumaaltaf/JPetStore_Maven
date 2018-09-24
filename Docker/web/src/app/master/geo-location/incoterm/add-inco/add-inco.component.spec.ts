import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddIncoComponent } from './add-inco.component';

describe('AddIncoComponent', () => {
  let component: AddIncoComponent;
  let fixture: ComponentFixture<AddIncoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddIncoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddIncoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
