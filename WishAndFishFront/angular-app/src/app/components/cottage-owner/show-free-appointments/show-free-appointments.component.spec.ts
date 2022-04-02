import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowFreeAppointmentsComponent } from './show-free-appointments.component';

describe('ShowFreeAppointmentsComponent', () => {
  let component: ShowFreeAppointmentsComponent;
  let fixture: ComponentFixture<ShowFreeAppointmentsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ShowFreeAppointmentsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ShowFreeAppointmentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
