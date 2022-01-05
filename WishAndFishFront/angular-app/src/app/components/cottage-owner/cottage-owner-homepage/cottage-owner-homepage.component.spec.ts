import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CottageOwnerHomepageComponent } from './cottage-owner-homepage.component';

describe('CottageOwnerHomepageComponent', () => {
  let component: CottageOwnerHomepageComponent;
  let fixture: ComponentFixture<CottageOwnerHomepageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CottageOwnerHomepageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CottageOwnerHomepageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
