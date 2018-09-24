import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewOriginDefinitionComponent } from './view-origin-definition.component';

describe('ViewOriginDefinitionComponent', () => {
  let component: ViewOriginDefinitionComponent;
  let fixture: ComponentFixture<ViewOriginDefinitionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewOriginDefinitionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewOriginDefinitionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
