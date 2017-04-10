import { Component, OnInit ,ViewEncapsulation} from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { AlertService, AuthenticationService } from '../../services/index';
import { LoginService } from './login.service';

@Component({
   // moduleId: module.id,
    templateUrl: './login.component.html',
    styleUrls:['login.css','../../css/test.css'],
    providers:[LoginService],
    encapsulation:ViewEncapsulation.None
})

export class LoginComponent implements OnInit {
    model: any = {};
    loading = false;
    returnUrl: string;

    constructor(
        private route: ActivatedRoute,
        private router: Router,
        private authenticationService: AuthenticationService,
        private alertService: AlertService,private loginService:LoginService) { }

    ngOnInit() {
        this.authenticationService.logout();
        this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
    }

    login() {
        this.loading = true;
        this.authenticationService.login(this.model.username, this.model.password)
            .subscribe(
                data => {
                    this.loginService.setUserTenantProfile(this.returnUrl,this.router);
                },
                error => {
                    this.alertService.error(error);
                    this.loading = false;
                });


    }
}
