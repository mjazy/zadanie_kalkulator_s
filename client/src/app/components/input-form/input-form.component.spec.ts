import { Country, CountryFinances } from './../../services/supported-countries-fetcher.service';
import { FormsModule } from '@angular/forms';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InputFormComponent } from './input-form.component';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

describe('InputFormComponent', () => {
  let component: InputFormComponent;
  let fixture: ComponentFixture<InputFormComponent>;
  let httpMock: HttpTestingController;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InputFormComponent ],
      imports: [
        FormsModule,
        HttpClientTestingModule
      ]
    })
    .compileComponents();
    httpMock = TestBed.get(HttpTestingController);
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InputFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should have array of supported countries after initialization', async() => {
    const countryFinances: CountryFinances = { currency: 'PLN', incomeTax: 0.19, fixedCosts: 1200 };
    const country: Country = { name: 'Poland', code: 'PL', finances: countryFinances };
    const supportedCountryList: Country[] = [country];

    const request = httpMock.expectOne('http://localhost:8080/countries');
    request.flush(supportedCountryList);

    expect(component.getSupportedCountries().length > 0).toBeTruthy();

  });


});
