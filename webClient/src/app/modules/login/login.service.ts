
   

    
    import { Injectable } from '@angular/core';
    import { Http, Headers, RequestOptions, Response } from '@angular/http';
    import { UserTeantProfile } from '../../models/index';
    import { AjaxService } from '../../services/index';
    import { Observable } from 'rxjs';
    import { Router, ActivatedRoute } from '@angular/router';
    @Injectable()
    export class LoginService {
        errorMessage: string;
        constructor(private ajaxService:AjaxService) {}
          setUserTenantProfile(returnUrl:string,router: Router){
          return  this.ajaxService.httpGet("api/v1/analytics/user/profile", null).subscribe(
                      userTenantProfile => {
                      localStorage.setItem('currentUserTenantProfile', JSON.stringify(userTenantProfile));
                      router.navigate([returnUrl]);
                  });
                  //error =>  this.errorMessage = <any>error);
                  
            }
    
}
    
    
   