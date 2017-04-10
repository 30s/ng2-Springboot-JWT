import { Injectable } from '@angular/core';
import { Http, Headers,Request, Response,RequestOptions ,RequestMethod} from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { Constants } from '../utils/index';
import 'rxjs/add/operator/map'

@Injectable()
export class AuthenticationService {
    constructor(private http: Http,private constant:Constants) { }

    login(username: string, password: string) {///api/authenticate
     var  headers = new Headers();
     headers.append('Content-Type', 'application/json');
     headers.append('X-Requested-With','XMLHttpRequest');
     headers.append('Access-Control-Allow-Origin','*');
     headers.append('Access-Control-Allow-Methods','*');
     headers.append("Access-Control-Allow-Headers", "Access-Control-Allow-Origin,Origin, X-Requested-With, Content-Type, Accept");
     let surl=this.constant.serverDomainUrl;
   //  let url=this.constant.serverDomainUrl+'/api/auth/login';
    
     console.log("auth url "+surl);
          
     let requestoptions = new RequestOptions({
         method: RequestMethod.Post,
         url: surl,
         headers: headers,
         body: JSON.stringify({ username: username, password: password })
     })

     return this.http.request(new Request(requestoptions))
         .map((response: Response) => {
                let user = response.json();
                if (user && user.token) { 
                    localStorage.setItem('currentUser', JSON.stringify(user));
                }
            });
     
       
         /*   
           return this.http.post(surl+"/api/auth/login", JSON.stringify({ username: username, password: password }),{headers:headers})
              .map((response: Response) => {
                let user = response.json();
                if (user && user.token) { 
                    localStorage.setItem('currentUser', JSON.stringify(user));
                }
            });
            */
      }

    logout() {
        // remove user from local storage to log user out
        localStorage.removeItem('currentUser');
    }
}