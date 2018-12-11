import { HttpTestingController, HttpClientTestingModule } from '@angular/common/http/testing';
import { TestBed, async } from '@angular/core/testing';

import { NetMonthlyEarningsFetcherService } from './net-monthly-earnings-fetcher.service';
import { Observable } from 'rxjs';

describe('NetMonthlyEarningsFetcherService', () => {
  let httpMock: HttpTestingController;
  let service: NetMonthlyEarningsFetcherService;
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule
      ]
    });
    httpMock = TestBed.get(HttpTestingController);
    service = TestBed.get(NetMonthlyEarningsFetcherService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should return observable with relevant value', () => {
    const valueToBeReturnedFromMock = 810.00;
    service.fetchNetMonthlyEarnings('PL', 100);
    const result: Observable<number> = service.getNetMonthlyEarnings();
    result.subscribe((data) => expect(data === valueToBeReturnedFromMock).toBeTruthy());
    const request = httpMock.expectOne('http://localhost:8080/earnings/PL/100');
    request.flush(valueToBeReturnedFromMock);

  });


});
