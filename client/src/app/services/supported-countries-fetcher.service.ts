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

  private supportedCountriesObservable: Observable<Country[]>;


  /**
   * Method getting supported countries.
   * @author MJazy
   */
  public getSupportedCountries(): Observable<Country[]> {
    return this.supportedCountriesObservable;
  }

  constructor(private httpClient: HttpClient) {
    this.supportedCountriesObservable = this.httpClient.get<Country[]>('http://localhost:8080/countries');
   }
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
