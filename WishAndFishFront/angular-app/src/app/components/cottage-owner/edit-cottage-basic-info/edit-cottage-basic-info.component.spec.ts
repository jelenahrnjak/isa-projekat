import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditCottageBasicInfoComponent } from './edit-cottage-basic-info.component';

describe('EditCottageBasicInfoComponent', () => {
  let component: EditCottageBasicInfoComponent;
  let fixture: ComponentFixture<EditCottageBasicInfoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditCottageBasicInfoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditCottageBasicInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
