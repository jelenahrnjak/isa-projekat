import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AppoitmentSearchComponent } from './appoitment-search.component';

describe('AppoitmentSearchComponent', () => {
  let component: AppoitmentSearchComponent;
  let fixture: ComponentFixture<AppoitmentSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AppoitmentSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AppoitmentSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
