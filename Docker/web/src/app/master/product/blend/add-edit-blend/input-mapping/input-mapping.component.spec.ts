import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InputMappingComponent } from './input-mapping.component';

describe('InputMappingComponent', () => {
  let component: InputMappingComponent;
  let fixture: ComponentFixture<InputMappingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InputMappingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InputMappingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
