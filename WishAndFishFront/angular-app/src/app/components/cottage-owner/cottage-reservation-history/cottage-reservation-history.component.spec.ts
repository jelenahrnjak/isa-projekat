import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CottageReservationHistoryComponent } from './cottage-reservation-history.component';

describe('CottageReservationHistoryComponent', () => {
  let component: CottageReservationHistoryComponent;
  let fixture: ComponentFixture<CottageReservationHistoryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CottageReservationHistoryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CottageReservationHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
