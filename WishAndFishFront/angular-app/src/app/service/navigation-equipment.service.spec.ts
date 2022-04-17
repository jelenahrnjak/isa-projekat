import { TestBed } from '@angular/core/testing';

import { NavigationEquipmentService } from './navigation-equipment.service';

describe('NavigationEquipmentService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: NavigationEquipmentService = TestBed.get(NavigationEquipmentService);
    expect(service).toBeTruthy();
  });
});
