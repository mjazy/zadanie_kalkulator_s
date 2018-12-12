import { Country, CountryFinances } from './supported-countries-fetcher.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';

import { UserInputValidatorService } from './user-input-validator.service';

describe('UserInputValidatorService', () => {
  let service: UserInputValidatorService;
  let httpMock: HttpTestingController;
  let validCountryCode: string;
  let validGrossDailyEarnings: number;
  beforeEach(() => { TestBed.configureTestingModule({
    imports: [
      HttpClientTestingModule
    ]
  });
  service = TestBed.get(UserInputValidatorService);
  httpMock = TestBed.get(HttpTestingController);

  const countryFinances: CountryFinances = { currency: 'PLN', incomeTax: 0.19, fixedCosts: 1200, taxFreeAllowance: 3000 };
  const country: Country = { name: 'Poland', code: 'PL', finances: countryFinances };
  const supportedCountries: Country[] = [country];

  validCountryCode = supportedCountries[0].code;
  validGrossDailyEarnings = 100;

  const request = httpMock.expectOne('http://localhost:8080/countries');
  request.flush(supportedCountries);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should be falsy', () => {
    expect(service.validateUserInput('PPL', validGrossDailyEarnings)).toBeFalsy();
    expect(service.validateUserInput(validCountryCode, 0)).toBeFalsy();
    expect(service.validateUserInput(validCountryCode, -1)).toBeFalsy();
    expect(service.validateUserInput(validCountryCode, null)).toBeFalsy();
    expect(service.validateUserInput(validCountryCode, undefined)).toBeFalsy();

  });

  it('should be truthy', () => {
    expect(service.validateUserInput(validCountryCode, validGrossDailyEarnings)).toBeTruthy();
  });


});
