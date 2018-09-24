import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddExternalPackingComponent } from './add-external-packing.component';

describe('AddExternalPackingComponent', () => {
  let component: AddExternalPackingComponent;
  let fixture: ComponentFixture<AddExternalPackingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddExternalPackingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddExternalPackingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
