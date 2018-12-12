import { NetMonthlyEarningsFetcherService } from './../../services/net-monthly-earnings-fetcher.service';
import { Component} from '@angular/core';

@Component({
  selector: 'app-output-div',
  templateUrl: './output-div.component.html',
  styleUrls: ['./output-div.component.css']
})
export class OutputDivComponent {

  private netMonthlyEarnings: number;

  constructor(private netMonthlyEarningsFetcherService: NetMonthlyEarningsFetcherService) {
    this.netMonthlyEarningsFetcherService.getNetMonthlyEarnings()
    .subscribe(data => {this.netMonthlyEarnings = data; console.log(data); } , error => (console.log(error)));
  }

  public setNetMonthlyEarnings(countryCode: string, grossDailyEarnings: number) {
    this.netMonthlyEarningsFetcherService.getNetMonthlyEarnings()
      .subscribe(data => {this.netMonthlyEarnings = data; console.log(data); } , error => (console.log(error)));
  }

}
