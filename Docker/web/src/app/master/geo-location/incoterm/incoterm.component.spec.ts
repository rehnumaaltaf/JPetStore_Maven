import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IncotermComponent } from './incoterm.component';

describe('IncotermComponent', () => {
  let component: IncotermComponent;
  let fixture: ComponentFixture<IncotermComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IncotermComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IncotermComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
