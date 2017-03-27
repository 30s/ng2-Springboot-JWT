import { Component }        from '@angular/core';
import { Observable }       from 'rxjs/Observable';
import { Subject }          from 'rxjs/Subject';
import { UtilityService }                   from '../../services/index';
@Component({
  moduleId: module.id,
  selector: 'my-wiki-smart',
  templateUrl: 'wiki.component.html',
 // providers: [ WikipediaService ]
})
export class SmartSearchComponent {
  title   = 'Smarter Wikipedia Demo';
  fetches = 'Fetches when typing stops';
  searchText : string;
  items: Observable<string[]>;
  private searchTermStream = new Subject<string>();
  search(term: string) { 
      this.searchTermStream.next(term); 
      }
  
  constructor (private utilityService: UtilityService) {
    this.items = this.searchTermStream
      .debounceTime(300) // this function used to wait user to resume to type for 300miliseconds
      .distinctUntilChanged() // This function fire only when use change Search text , refer link - https://angular.io/docs/ts/latest/guide/server-communication.html
      .switchMap((term: string) => this.utilityService.getSearchResult(term));
  }
}