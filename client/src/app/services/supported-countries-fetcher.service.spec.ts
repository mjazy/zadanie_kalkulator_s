import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TestBed} from '@angular/core/testing';

import { SupportedCountriesFetcherService, Country, CountryFinances } from './supported-countries-fetcher.service';

describe('SupportedCountriesFetcherService', () => {
  let httpMock: HttpTestingController;
  let service: SupportedCountriesFetcherService;
  let supportedCountryList: Country[];
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
  const countryFinances: CountryFinances = { currency: 'PLN', incomeTax: 0.19, fixedCosts: 1200, taxFreeAllowance: 3000 };
  const country: Country = { name: 'Poland', code: 'PL', finances: countryFinances };
  supportedCountryList = [country];
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should return list of supported countries', () => {

    service.getSupportedCountries().subscribe(data => expect (data === supportedCountryList).toBeTruthy());

  });

});
