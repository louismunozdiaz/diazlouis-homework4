import { TestBed } from '@angular/core/testing';

import { SupervisorService } from './supervisor.service';

describe('SidService', () => {
  let service: SupervisorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SupervisorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
