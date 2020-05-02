/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { GoodManagementService } from './good-management.service';

describe('Service: GoodManagement', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [GoodManagementService]
    });
  });

  it('should ...', inject([GoodManagementService], (service: GoodManagementService) => {
    expect(service).toBeTruthy();
  }));
});
