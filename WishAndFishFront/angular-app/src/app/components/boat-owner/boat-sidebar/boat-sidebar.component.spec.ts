import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BoatSidebarComponent } from './boat-sidebar.component';

describe('BoatSidebarComponent', () => {
  let component: BoatSidebarComponent;
  let fixture: ComponentFixture<BoatSidebarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BoatSidebarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BoatSidebarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
