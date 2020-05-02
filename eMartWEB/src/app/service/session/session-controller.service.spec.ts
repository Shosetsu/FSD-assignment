/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { SessionControllerService } from './session-controller.service';

describe('Service: SessionControl', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [SessionControllerService]
    });
  });

  it('should ...', inject([SessionControllerService], (service: SessionControllerService) => {
    expect(service).toBeTruthy();
  }));
});
