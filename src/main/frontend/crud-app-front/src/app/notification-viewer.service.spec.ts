import { TestBed } from '@angular/core/testing';

import { NotificationViewerService } from './notification-viewer.service';

describe('NotificationViewerService', () => {
  let service: NotificationViewerService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(NotificationViewerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
