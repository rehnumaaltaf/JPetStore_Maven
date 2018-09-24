import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddEditBlendComponent } from './add-edit-blend.component';

describe('AddEditBlendComponent', () => {
  let component: AddEditBlendComponent;
  let fixture: ComponentFixture<AddEditBlendComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddEditBlendComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddEditBlendComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
