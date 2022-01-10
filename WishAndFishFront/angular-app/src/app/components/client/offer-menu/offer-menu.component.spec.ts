import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OfferMenuComponent } from './offer-menu.component';

describe('OfferMenuComponent', () => {
  let component: OfferMenuComponent;
  let fixture: ComponentFixture<OfferMenuComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OfferMenuComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OfferMenuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
