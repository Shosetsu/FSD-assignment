import { TestBed } from '@angular/core/testing';

import { SellerCenterGuard } from './seller-center.guard';

describe('SellerCenterGuard', () => {
  let guard: SellerCenterGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(SellerCenterGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
