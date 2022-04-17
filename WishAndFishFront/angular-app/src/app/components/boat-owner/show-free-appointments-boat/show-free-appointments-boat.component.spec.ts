import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowFreeAppointmentsBoatComponent } from './show-free-appointments-boat.component';

describe('ShowFreeAppointmentsBoatComponent', () => {
  let component: ShowFreeAppointmentsBoatComponent;
  let fixture: ComponentFixture<ShowFreeAppointmentsBoatComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ShowFreeAppointmentsBoatComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ShowFreeAppointmentsBoatComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
