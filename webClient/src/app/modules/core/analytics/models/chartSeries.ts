/**
 * @Author : Sohan Kumawat
 * Functionality : this class defined for highchart series object
 */
export class ChartSeries {
  id:String='1';
  allowPointSelect:Boolean=true;
  constructor(public name :string, public data:any[], public type:string,public color:string) {
  
  }
}