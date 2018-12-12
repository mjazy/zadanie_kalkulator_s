import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { InputFormComponent } from './components/input-form/input-form.component';
import { OutputDivComponent } from './components/output-div/output-div.component';
import { NetMonthlyEarningsFetcherService } from './services/net-monthly-earnings-fetcher.service';

@NgModule({
  declarations: [
    AppComponent,
    InputFormComponent,
    OutputDivComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [NetMonthlyEarningsFetcherService],
  bootstrap: [AppComponent]
})
export class AppModule { }
