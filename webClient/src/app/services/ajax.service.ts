import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions, Response } from '@angular/http';
import { User } from '../models/index';
import { Observable } from 'rxjs/Observable';
import { Constants } from '../utils/index';
import {UtilityService} from  './index';

@Injectable()
export class AjaxService {
    
   constructor(private utilService:UtilityService,private http:Http,private constant:Constants) {  }
    
   public httpGet(url:string,queryParam:any):Observable<any>{
       url=this.constant.serverDomainUrl+url;
     if(queryParam) url=url+queryParam
     return this.http.get(url,this.utilService.jwt("token"))
            .map(this.extractData);
         };
          
          private extractData(res: Response) {
              let body ={};
              if(res!=null) body = res.json();
              console.log(" http get response **** " +body);
              return body || { };
            }
            private handleError (error: Response | any){
               /*
                 if (res.status < 200 || res.status >= 300) {
                    throw new Error('Response status: ' + res.status);
                  }
                  */
              let errMsg: string;
              if (error instanceof Response) {
                const body = error.json() || '';
                const err = body.error || JSON.stringify(body);
                errMsg = `${error.status} - ${error.statusText || ''} ${err}`;
              } else {
                errMsg = error.message ? error.message : error.toString();
              }
              console.error(errMsg);
             // return Observable.throw(errMsg);
              //Observable.throw(errMsg); */
            } 
   }

import {Request, XHRBackend, XHRConnection} from '@angular/http';

@Injectable()
export class ApiXHRBackend extends XHRBackend {
    createConnection(request: Request): XHRConnection {
        if (request.url.startsWith('/')){
            request.url = 'http://localhost:8082' + request.url;     // prefix base url
        }
        return super.createConnection(request);
    }
}
