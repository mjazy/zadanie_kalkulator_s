import { SupportedCountriesFetcherService, Country } from './supported-countries-fetcher.service';
import { Injectable } from '@angular/core';

/**
 * Service for validating user input.
 * @author MJazy
 */
@Injectable({
  providedIn: 'root'
})
export class UserInputValidatorService {
  supportedCountries: Country[];

  private validateCountryCode(countryCode: string) {
    if (countryCode) {
      for (const supportedCountry of this.supportedCountries) {
        if (supportedCountry.code === countryCode) {
          return true;
        }
      }
    }
    return false;
  }

  private validateGrossDailyEarnings(grossDailyEarnings: number) {
    if (typeof grossDailyEarnings !== 'undefined') {
      if (grossDailyEarnings !== null) {
        if (grossDailyEarnings.valueOf() > 0) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Function validating user input.
   * @author MJazy
   */
  public validateUserInput(countryCode: string, grossDailyEarnings: number) {
    if (this.validateCountryCode(countryCode)) {
      return (this.validateGrossDailyEarnings(grossDailyEarnings));
    }
  }

  constructor(supportedCountriesFetcherService: SupportedCountriesFetcherService) {
    supportedCountriesFetcherService.fetchSupportedCountries()
      .subscribe((data) => (this.supportedCountries = data), (error) => (console.log(error)));
  }
}
