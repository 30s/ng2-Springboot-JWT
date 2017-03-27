import { Component, OnInit ,ElementRef,ViewEncapsulation,ViewChildren} from '@angular/core';
import { User,UserTeantProfile } from '../../../../models/index';
import {AnalyticsService }                                 from   '../services/analytic.service';


//import{UpgradeComponentsDirective} from '../../../common/index';
declare var $: any;
@Component({
  //  moduleId: __moduleName,
    styles :['./dashboard.css'],
    templateUrl: './dashboard.component.html'
})

export class DashboardComponent implements OnInit {
     constructor(private analyticService: AnalyticsService) {
     }
    ngOnInit() {
       console.log('Hello dashboard component');
        }
}