import { TestBed } from '@angular/core/testing';

import { UserContextServiceService } from './user-context-service.service';

describe('UserContextServiceService', () => {
  let service: UserContextServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UserContextServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
