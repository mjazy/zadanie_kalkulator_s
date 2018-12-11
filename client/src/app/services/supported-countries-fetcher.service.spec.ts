import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TestBed} from '@angular/core/testing';

import { SupportedCountriesFetcherService, Country, CountryFinances } from './supported-countries-fetcher.service';

describe('SupportedCountriesFetcherService', () => {
  let httpMock: HttpTestingController;
  let service: SupportedCountriesFetcherService;
    beforeEach(() => { TestBed.configureTestingModule({
    imports: [
      HttpClientTestingModule
    ],
    providers: [
      SupportedCountriesFetcherService
    ]
  });
  httpMock = TestBed.get(HttpTestingController);
  service = TestBed.get(SupportedCountriesFetcherService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should return list of supported countries', async() => {
    const countryFinances: CountryFinances = { currency: 'PLN', incomeTax: 0.19, fixedCosts: 1200 };
    const country: Country = { name: 'Poland', code: 'PL', finances: countryFinances };

    const supportedCountryList: Country[] = [country];

    service.fetchSupportedCountries().subscribe(data =>
      (expect(data === supportedCountryList).toBeTruthy()));
    const request = httpMock.expectOne('http://localhost:8080/countries');
    request.flush(supportedCountryList);

  });

});
