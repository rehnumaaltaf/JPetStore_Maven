import { MockBackend, MockConnection } from '@angular/http/testing';
import { Http, ConnectionBackend, BaseRequestOptions, Response, ResponseOptions } from '@angular/http';
import { discardPeriodicTasks, tick, async, inject, TestBed, fakeAsync } from '@angular/core/testing';
import { HttpModule, JsonpModule } from '@angular/http';

import { OriginDefinitionService } from './origin-definition.service';

describe('OriginDefinitionService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [OriginDefinitionService]
    });
  });

  it('should be created', inject([OriginDefinitionService], (service: OriginDefinitionService) => {
    expect(service).toBeTruthy();
  }));
});
