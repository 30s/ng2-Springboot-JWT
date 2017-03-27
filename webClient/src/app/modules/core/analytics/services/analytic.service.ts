import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions, Response } from '@angular/http';
import { User } from '../../../../models/index';
import { ChartSeries } from '../index';
import {HighchartService }  from   '../services/highchart.service';
import { Observable } from 'rxjs'

/*This service used to across analytics module*/
@Injectable()
export class AnalyticsService {

    constructor( ) {
    
    }
   
}