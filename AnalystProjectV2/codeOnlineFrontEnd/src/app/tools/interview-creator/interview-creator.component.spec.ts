import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InterviewCreatorComponent } from './interview-creator.component';

describe('InterviewCreatorComponent', () => {
  let component: InterviewCreatorComponent;
  let fixture: ComponentFixture<InterviewCreatorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InterviewCreatorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InterviewCreatorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
