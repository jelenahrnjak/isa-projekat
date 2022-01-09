import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MyBoatsComponent } from './my-boats.component';

describe('MyBoatsComponent', () => {
  let component: MyBoatsComponent;
  let fixture: ComponentFixture<MyBoatsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MyBoatsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MyBoatsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
