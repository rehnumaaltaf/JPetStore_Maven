import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewForexComponent } from './view-forex.component';

describe('ViewForexComponent', () => {
  let component: ViewForexComponent;
  let fixture: ComponentFixture<ViewForexComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewForexComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewForexComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
