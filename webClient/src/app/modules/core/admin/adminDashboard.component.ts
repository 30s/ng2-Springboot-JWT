import { Component, OnInit }  from '@angular/core';
import { ActivatedRoute }     from '@angular/router';
import { Observable }         from 'rxjs/Observable';
import 'rxjs/add/operator/map';

@Component({
     styleUrls :[],
     providers:[],
     templateUrl:'./adminDashboard.component.html',
  //styles :''
})
export class AdminDashboardComponent implements OnInit {
  sessionId: Observable<string>;
  token: Observable<string>;

  constructor() {}

  ngOnInit() {

  }

}
