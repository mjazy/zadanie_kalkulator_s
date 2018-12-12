import { Country } from './supported-countries-fetcher.service';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, BehaviorSubject } from 'rxjs';

/**
 * Service allowing fetching net monthly earnings.
 * @author MJazy
 */
@Injectable({
  providedIn: 'root'
})
export class NetMonthlyEarningsFetcherService {

  private netMonthlyEarnings = new BehaviorSubject<number>(0);

  /**
   * Method for triggering fetching net monthly earnings.
   * @param countryCode should be of supported country and compliant with ISO 3166-1.
   * @param grossDailyEarnings should be higher than 0.
   */
  public fetchNetMonthlyEarnings(countryCode: string, grossDailyEarnings: number) {
    this.httpClient.get<number>('http://localhost:8080/earnings/' + countryCode + '/' + grossDailyEarnings)
    .subscribe(data => (this.netMonthlyEarnings.next(data)));
  }

  /**
   * Method for accessing fetched net monthly earnings.
   * @returns Observable<number> of net monthly earnings.
   */
  public getNetMonthlyEarnings() {
    return this.netMonthlyEarnings;
  }

  constructor(private httpClient: HttpClient) { }
}
