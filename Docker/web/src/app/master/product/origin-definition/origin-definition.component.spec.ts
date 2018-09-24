import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { Http, ConnectionBackend, BaseRequestOptions, Response, ResponseOptions } from '@angular/http';
import { OriginDefinitionComponent } from './origin-definition.component';
import { OriginDefinitionService } from './service/origin-definition.service';
import { HttpModule, JsonpModule } from '@angular/http';
import { MockBackend } from '@angular/http/testing';


describe('OriginDefinitionComponent', () => {
  let component: OriginDefinitionComponent;
  let fixture: ComponentFixture<OriginDefinitionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OriginDefinitionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OriginDefinitionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

   it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
