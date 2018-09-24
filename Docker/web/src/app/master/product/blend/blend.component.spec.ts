import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BlendComponent } from './blend.component';

describe('BlendComponent', () => {
  let component: BlendComponent;
  let fixture: ComponentFixture<BlendComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BlendComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BlendComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
