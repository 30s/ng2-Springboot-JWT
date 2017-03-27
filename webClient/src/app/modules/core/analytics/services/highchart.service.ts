/*
 * @Author : sohan kumawat
 * This service used to display highcharts.
 * 
 */
import { Injectable } from '@angular/core';
import {HighchartUtilService} from './highchart.util.service';
import { ChartSeries } from '../index';

@Injectable()
export class HighchartService{

    
    constructor(private highchartUtilService:HighchartUtilService) { }
    
    gethighChartObject():any{
        let chartSeries:ChartSeries[]=[];
        let highchartUtilService=new HighchartUtilService();
         let highchartObject={
                chart : {
                    type : '',
                    alignTicks:false,
                    reflow : true,
                    backgroundColor : '#fcfcfc', // '#f7f9fa',
                     },
                     title :{
                       text:''  
                     },
                    xAxis : highchartUtilService.chartXaxis,
                    yAxis : highchartUtilService.chartYaxis,
                    scrollbar : highchartUtilService.scrollbar,
                    legend : highchartUtilService.legend,
                    plotOptions : {},
                    tooltip : highchartUtilService.generalTooltip,
                    credits : {
                        enabled : false
                          },
                    series:chartSeries
                   };
         return highchartObject;
    }
    
   /*  This function is responsible for all the charts like line,area,spline,column,multicolumn,multistackcolumn */
    getGeneralHighChart(chartData:any,xAxisValues:string[],yAxisValue:string[],chartSeries:ChartSeries[]):any{
        console.log("** xAxis values are ** "+xAxisValues);
        console.log("** yAxis values are ** "+yAxisValue);
        console.log("** chart seriec are ** "+chartSeries);
        console.log("** chart title is ** "+chartData.title +" and chart type is "+chartData.chartType);
        
        chartData.title,chartData.yAxisLable,chartData.xAxisLable,chartData.chartType
        
        let highchart=this.gethighChartObject();
            highchart.chart.type=chartData.chartType;
            
        if(xAxisValues!=null && xAxisValues.length>7){
                var skipStepValue = Math
                        .floor(xAxisValues.length / 7);
                highchart.xAxis.tickInterval = skipStepValue + 1;

        }
        
       if(chartData.metricAggregation){
            let max=chartData.metricAggregation.maximum[0].values[0];
            let tickInteval=Math.round(max/10);
            if(tickInteval==0) tickInteval=1;
            highchart.yAxis[0].tickInterval=tickInteval;
        } 
        if(xAxisValues && xAxisValues.length>0)  highchart.xAxis.categories=xAxisValues; 
        if(yAxisValue && yAxisValue.length>0) highchart.yAxis[0].categories=yAxisValue; 
        if(chartData.xAxisLable) highchart.xAxis.title.text=chartData.xAxisLable; 
        if(chartData.yAxisLable) highchart.yAxis[0].title.text=chartData.yAxisLable; 
        if(chartSeries && chartSeries.length>0) highchart.series=chartSeries; 
        if(chartSeries && chartSeries.length>1){ highchart.legend.enabled=true; }
        
        highchart=  this.definedChartView(chartData.chartViewType,highchart);
        
        return highchart;
    };
    
    definedChartView(chartViewType:string,highchart:any):any{
        
        if(chartViewType==='stackColumn'){
            highchart.plotOptions={
                column: {
                    stacking: 'normal'
                }
            }
        }
        else if(chartViewType==='stackArea'){
            highchart.plotOptions={
                    area: {
                        stacking: 'normal',
                    }
                }
            }
        return highchart;
    }
}