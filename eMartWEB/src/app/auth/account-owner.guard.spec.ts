import { TestBed } from '@angular/core/testing';

import { AccountOwnerGuard } from './account-owner.guard';

describe('OwnerGuard', () => {
  let guard: AccountOwnerGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(AccountOwnerGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
