import { TestBed } from '@angular/core/testing';

import { CottageService } from './cottage.service';

describe('CottageService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CottageService = TestBed.get(CottageService);
    expect(service).toBeTruthy();
  });
});
