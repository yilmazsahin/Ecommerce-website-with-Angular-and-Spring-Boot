import { TestBed } from '@angular/core/testing';

import { ShopVistaFormService } from './shop-vista-form.service';

describe('ShopVistaFormService', () => {
  let service: ShopVistaFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ShopVistaFormService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
