import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SuccessfullySingUpComponent } from './successfully-sing-up.component';

describe('SuccessfullySingUpComponent', () => {
  let component: SuccessfullySingUpComponent;
  let fixture: ComponentFixture<SuccessfullySingUpComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SuccessfullySingUpComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SuccessfullySingUpComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
