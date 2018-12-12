import { HttpClientModule } from '@angular/common/http';
import { InputFormComponent } from './components/input-form/input-form.component';
import { FormsModule } from '@angular/forms';
import { TestBed, async } from '@angular/core/testing';
import { AppComponent } from './app.component';
import { OutputDivComponent } from './components/output-div/output-div.component';

describe('AppComponent', () => {
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        AppComponent,
        InputFormComponent,
        OutputDivComponent
      ],
      imports: [
        FormsModule,
        HttpClientModule
      ]
    }).compileComponents();
  }));

  it('should create the app', () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  });

  it(`should have as title 'NetEarningsCalculator'`, () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app.title).toEqual('NetEarningsCalculator');
  });
});
