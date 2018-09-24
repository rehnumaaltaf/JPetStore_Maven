import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GradeDefinitionComponent } from './grade-definition.component';

describe('GradeDefinitionComponent', () => {
  let component: GradeDefinitionComponent;
  let fixture: ComponentFixture<GradeDefinitionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GradeDefinitionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GradeDefinitionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
