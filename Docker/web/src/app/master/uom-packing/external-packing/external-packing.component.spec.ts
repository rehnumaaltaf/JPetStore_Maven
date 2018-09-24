import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ExternalPackingComponent } from './external-packing.component';

describe('ExternalPackingComponent', () => {
  let component: ExternalPackingComponent;
  let fixture: ComponentFixture<ExternalPackingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ExternalPackingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ExternalPackingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
