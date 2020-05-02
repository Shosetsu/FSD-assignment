/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { SellerManagementService } from './seller-management.service';

describe('Service: SellerManagement', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [SellerManagementService]
    });
  });

  it('should ...', inject([SellerManagementService], (service: SellerManagementService) => {
    expect(service).toBeTruthy();
  }));
});
