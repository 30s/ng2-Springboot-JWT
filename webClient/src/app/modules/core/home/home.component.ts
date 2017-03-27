import { Component, OnInit } from '@angular/core';

import { User,UserTeantProfile } from '../../../models/index';
import { UserService } from '../../../services/index';

@Component({
   // styleUrls :  ['app/css/innerBase.css'],
    templateUrl: './home.component.html',
})

export class HomeComponent implements OnInit {
    constructor() {
    }
    ngOnInit() {
    }
  
    userLogout(){
    }
}
