import { TestBed } from '@angular/core/testing';

import { BoatOwnerService } from './boat-owner.service';

describe('BoatOwnerService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: BoatOwnerService = TestBed.get(BoatOwnerService);
    expect(service).toBeTruthy();
  });
});
