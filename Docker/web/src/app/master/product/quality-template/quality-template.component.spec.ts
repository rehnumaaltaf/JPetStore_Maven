import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { QualityTemplateComponent } from './quality-template.component';

describe('QualityTemplateComponent', () => {
  let component: QualityTemplateComponent;
  let fixture: ComponentFixture<QualityTemplateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ QualityTemplateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(QualityTemplateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
