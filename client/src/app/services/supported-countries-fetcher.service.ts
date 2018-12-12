import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
/**
 * Service for fetching supported countries from server.
 * @author MJazy
 */
export class SupportedCountriesFetcherService {

  private supportedCountries: Country[];


  /**
   * Method fetching supported countries from server.
   * @author MJazy
   */
  public fetchSupportedCountries(): Observable<Country[]> {
     return this.httpClient.get<Country[]>('http://localhost:8080/countries');
  }

  constructor(private httpClient: HttpClient) { }
}

/**
 * Data model for country.
 * @author MJazy
 */
export interface Country {
  name: string;
  code: string;
  finances: CountryFinances;
}

/**
 * Data model for country's finances.
 * @author MJazy
 */
export interface CountryFinances {
  currency: string;
  incomeTax: number;
  fixedCosts: number;
  taxFreeAllowance: number;
}
