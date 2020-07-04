import { TestBed } from '@angular/core/testing';

import { CodeSharingService } from './code-sharing.service';

describe('CodeSharingService', () => {
  let service: CodeSharingService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CodeSharingService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
