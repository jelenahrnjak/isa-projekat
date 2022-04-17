import { TestBed } from '@angular/core/testing';

import { FishingEquipmentService } from './fishing-equipment.service';

describe('FishingEquipmentService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: FishingEquipmentService = TestBed.get(FishingEquipmentService);
    expect(service).toBeTruthy();
  });
});
