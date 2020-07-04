import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InterviewRoomComponent } from './interview-room.component';

describe('InterviewRoomComponent', () => {
  let component: InterviewRoomComponent;
  let fixture: ComponentFixture<InterviewRoomComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InterviewRoomComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InterviewRoomComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
