import { TestBed } from '@angular/core/testing';

import { CottageOwnerService } from './cottage-owner.service';

describe('CottageOwnerService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CottageOwnerService = TestBed.get(CottageOwnerService);
    expect(service).toBeTruthy();
  });
});
