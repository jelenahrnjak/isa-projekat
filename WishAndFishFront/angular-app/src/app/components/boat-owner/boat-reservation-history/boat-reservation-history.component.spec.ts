import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BoatReservationHistoryComponent } from './boat-reservation-history.component';

describe('BoatReservationHistoryComponent', () => {
  let component: BoatReservationHistoryComponent;
  let fixture: ComponentFixture<BoatReservationHistoryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BoatReservationHistoryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BoatReservationHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
