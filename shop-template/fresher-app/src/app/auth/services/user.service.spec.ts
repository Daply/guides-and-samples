import { TestBed } from '@angular/core/testing';

import { UserService } from './user.service';

describe('UserService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: UserService = TestBed.get(UserService);
    expect(service).toBeTruthy();
  });

  it('#getUserById should return User value', () => {
    const service: UserService = TestBed.get(UserService);
    expect(service).toBeDefined();
  });
});
