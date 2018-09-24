import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PartyDefinitionComponent } from './party-definition.component';

describe('PartyDefinitionComponent', () => {
  let component: PartyDefinitionComponent;
  let fixture: ComponentFixture<PartyDefinitionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PartyDefinitionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PartyDefinitionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
