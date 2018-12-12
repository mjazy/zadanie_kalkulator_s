import { NetMonthlyEarningsFetcherService } from './../../services/net-monthly-earnings-fetcher.service';
import { UserInputValidatorService } from './../../services/user-input-validator.service';
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
  private invalidInputMessage: string;

  private setCurrency() {
    for (const supportedCountry of this.supportedCountries) {
      if (supportedCountry.code === this.countryCode) {
        this.countryCurrency = supportedCountry.finances.currency;
      }
    }
  }

  private fetchNetMonthlyEarnings() {
    if (this.userInputValidatorService.validateUserInput(this.countryCode, this.grossDailyEarnings)) {
      this.invalidInputMessage = '';
      this.netMonthlyEarningsFetcherService.fetchNetMonthlyEarnings(this.countryCode, this.grossDailyEarnings);
    } else {
      this.invalidInputMessage = 'Invalid input entered.';
    }
  }

  constructor(private supportedCountriesFetcherService: SupportedCountriesFetcherService,
    private userInputValidatorService: UserInputValidatorService,
    private netMonthlyEarningsFetcherService: NetMonthlyEarningsFetcherService) { }

  ngOnInit() {
    this.supportedCountriesFetcherService.getSupportedCountries()
      .subscribe(data => (this.supportedCountries = data), error => (console.log(error)));
  }

  getSupportedCountries() {
    return this.supportedCountries;
  }

}
