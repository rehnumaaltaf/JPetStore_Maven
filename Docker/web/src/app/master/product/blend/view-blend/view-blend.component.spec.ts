import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewBlendComponent } from './view-blend.component';

describe('ViewBlendComponent', () => {
  let component: ViewBlendComponent;
  let fixture: ComponentFixture<ViewBlendComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewBlendComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewBlendComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
