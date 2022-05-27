import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CottageReportComponent } from './cottage-report.component';

describe('CottageReportComponent', () => {
  let component: CottageReportComponent;
  let fixture: ComponentFixture<CottageReportComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CottageReportComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CottageReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
