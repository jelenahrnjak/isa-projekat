import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddBoatActionComponent } from './add-boat-action.component';

describe('AddBoatActionComponent', () => {
  let component: AddBoatActionComponent;
  let fixture: ComponentFixture<AddBoatActionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddBoatActionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddBoatActionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
