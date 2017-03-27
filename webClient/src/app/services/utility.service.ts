import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions, Response } from '@angular/http';
import * as moment from 'moment';
import { Observable }       from 'rxjs/Observable';
import { User } from '../models/index';

@Injectable()
export class UtilityService {
    
    dateFormat:string= 'dd mmm, yyyy';

    constructor(private http: Http) { }

    public jwt(type:String) {
         let currentUser = JSON.parse(localStorage.getItem('currentUser'));
        if(type==null || type=='token'){
       
        if (currentUser && currentUser.token) {
            let headers = new Headers({ 'Authorization': 'Bearer ' + currentUser.token });
            return new RequestOptions({ headers: headers });
        }
       }else if(type=='refreshToken'){
             if (currentUser && currentUser.refreshToken) {
            let headers = new Headers({ 'Authorization': 'Bearer ' + currentUser.refreshToken });
            return new RequestOptions({ headers: headers });
        }
        }
    }
   public changeDateFormat(value:string):string {
        
        return "";
    
    }
   public getSearchResult(seachText:string):Observable<any>{
       return new  Observable<string[]>();
       
       //return  this.ajaxService.httpGet("", seachText)
     }
    
   public dateFormation(date:string,format:string):string{
       if(format){
           this.dateFormat=format;
          }
       try{
           
        }catch(Error){
           console.log(Error.message);
         }
        return "";
    }
    
}