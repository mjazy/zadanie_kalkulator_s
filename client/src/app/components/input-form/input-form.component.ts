import { Country, SupportedCountriesFetcherService } from './../../services/supported-countries-fetcher.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-input-form',
  templateUrl: './input-form.component.html',
  styleUrls: ['./input-form.component.css']
})
export class InputFormComponent implements OnInit {

  private supportedCountries: Country[];
  private grossDailyEarnings: number;
  private countryCode: string;
  private countryCurrency = 'currency';

  private setCurrency() {
    for (const supportedCountry of this.supportedCountries) {
      if (supportedCountry.code === this.countryCode) {
        this.countryCurrency = supportedCountry.finances.currency;
      }
    }
  }

  constructor(private supportedCountriesFetcherService: SupportedCountriesFetcherService) { }

  ngOnInit() {
    this.supportedCountriesFetcherService.fetchSupportedCountries().subscribe(data => (this.supportedCountries = data));
  }

  getSupportedCountries() {
    return this.supportedCountries;
  }

}
