import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ExchangeLimitComponent } from './exchange-limit.component';

describe('ExchangeLimitComponent', () => {
  let component: ExchangeLimitComponent;
  let fixture: ComponentFixture<ExchangeLimitComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ExchangeLimitComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ExchangeLimitComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
