import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InternalPackagingComponent } from './internal-packaging.component';

describe('InternalPackagingComponent', () => {
  let component: InternalPackagingComponent;
  let fixture: ComponentFixture<InternalPackagingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InternalPackagingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InternalPackagingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
