import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditBoatBasicInfoComponent } from './edit-boat-basic-info.component';

describe('EditBoatBasicInfoComponent', () => {
  let component: EditBoatBasicInfoComponent;
  let fixture: ComponentFixture<EditBoatBasicInfoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditBoatBasicInfoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditBoatBasicInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
