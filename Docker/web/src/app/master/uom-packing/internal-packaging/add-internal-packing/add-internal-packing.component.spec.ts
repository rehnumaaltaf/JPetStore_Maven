import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddInternalPackingComponent } from './add-internal-packing.component';

describe('AddInternalPackingComponent', () => {
  let component: AddInternalPackingComponent;
  let fixture: ComponentFixture<AddInternalPackingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddInternalPackingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddInternalPackingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
