import { NgModule,Optional,SkipSelf }            from '@angular/core';
import {SharedModule }                           from   '../common/index';
import {HomeComponent,DashboardComponent } from   './index';
import {ComponentRoutingModule }                           from   './component.routing';
import {AnalyticsService ,HighchartService,HighchartUtilService}                                 from   './index';
import { ChartModule }                                     from 'angular2-highcharts';
import {TooltipModule} from "ngx-tooltip";

@NgModule({
  imports      :  [SharedModule,ChartModule,ComponentRoutingModule,TooltipModule], //This imports available to declaration components
  declarations :  [HomeComponent,DashboardComponent ],
  providers    :  [ AnalyticsService,HighchartService ,HighchartUtilService]
})
export class CustomComponentModules { 
}