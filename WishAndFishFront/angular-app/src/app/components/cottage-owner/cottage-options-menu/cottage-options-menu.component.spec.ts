import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CottageOptionsMenuComponent } from './cottage-options-menu.component';

describe('CottageOptionsMenuComponent', () => {
  let component: CottageOptionsMenuComponent;
  let fixture: ComponentFixture<CottageOptionsMenuComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CottageOptionsMenuComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CottageOptionsMenuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
