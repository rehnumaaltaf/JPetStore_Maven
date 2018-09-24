import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewIncoComponent } from './view-inco.component';

describe('ViewIncoComponent', () => {
  let component: ViewIncoComponent;
  let fixture: ComponentFixture<ViewIncoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewIncoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewIncoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
