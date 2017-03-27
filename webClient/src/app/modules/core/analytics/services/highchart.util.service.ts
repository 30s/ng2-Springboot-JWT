/*
 * @ Author : Sohan kumawat
 *  Functionality :  This File used to support highchart Service;
 *  in this file we defined highchart related attribute like tool tips colors etc
 * 
 */
import { Injectable } from '@angular/core';
declare var $: any;
@Injectable()
export class HighchartUtilService {
    constructor() {}
  generalTooltip:any={
          useHTML : true,
          color : '#333',
          borderWidth : 0,
          backgroundColor : '#fff',
          borderRadius : 12,
          shadow : true,
          hideDelay:100,
          style : {
              border : '0px',
              fontSize : '12px',
              fontFamily : 'Open Sans, sans-serif',
              padding : '0px',
              textTransform : 'capitalize',
          },
       /*   formatter : function() {
              var header = '';
              if (this.x === undefined)
                  header = this.point.name;
              else header = this.x;

                  var s = '<div style="backgroud:#fff;"><span style="padding:4px 10px;text-transform:capitalize; color:#333; text-align:center; back ground:#4f5862; display:table; width:100%; bor der-radius:8px 8px 0px 0px;">'+header+'<br/></span></div>';
                  $.each(this.points, function(i:number, point:any) {
                      var series_Name="";
                      if(point.series.name && point.series.name.indexOf("Series")<-1) series_Name=point.series.name +" : ";
                       if(this.point.percentage === undefined)
                         var values = this.point.labelValue;
                       else values = this.point.labelValue + ' ('+this.point.percentage.toFixed(2)+'%)'
                       s += '<span style="padding:4px 10px; text-align:center;text-transform:capitalize; margin-bottom: 1px;color:#333; font-weight:600; background-color:#3C4854; display:table; width:100%; "><b>'+ series_Name + values +'</b></span>';
                  });
                  return s;
          }, */
          shared: true
     } 
    chartXaxis:any={
       //   tickLength : 1,
          categories : [],
        /*  showLastLabel : true,
          labels : {
              rotation : -0,
              align : 'center',
              useHTML : true,
              style : {
                  textTransform : "capitalize",
                  fontSize : "12px",
                  color : "#333"
              },
              formatter : function() { }
          },*/
          title : {
              text : '',
              style : {
                  fontSize : "12px",
                  color : "#333"
              }
          }
      }
      chartYaxis:any= [ {
              allowDecimals : false,
              categories:[],
              gridLineWidth : .8,
              gridLineColor : '#f2f2f2',
              labels : {
                  style : {
                      textTransform : "capitalize",
                      fontSize : "12px",
                      color : "#333"
                  }
              },
              title : {
                  text : '',
                  style : {
                      fontSize : "12px",
                      color : "#333"
                  }
              }
          } ];
    scrollbar :any={
        enabled : false,
        buttonBorderColor : '#d8d8d8',
        buttonBackgroundColor : "#d8d8d8",
        minWidth : 3,
        trackBackgroundColor : "#f2f2f2",
        barBackgroundColor : '#e6e6e6',
        barBorderColor : '#e6e6e6',
    };

      legend:any = {
          enabled : false,
          symbolWidth : 12,
          symbolRadius : 6,
          itemStyle : {
          textTransform : "capitalize"
          } 
      }
      
      private getLegend():any{
          return this.legend;
        }
    private getScrollbar():any{
          return this.scrollbar;
        }
    private getChartXaxis():any{
        return this.chartXaxis;
       }
    private getChartYaxis():any{
        return this.chartYaxis;
       }
    private getGeneralTooltip():any{
        return this.generalTooltip;
       }
    
}