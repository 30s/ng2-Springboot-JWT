import { Directive, ElementRef ,EventEmitter,Input,Renderer} from '@angular/core';
//<div my-button></div>
/*
Map<String, String> host
read-only
Specifiy the events, actions, properties and attributes related to the host element.
*/
@Directive({
    selector: '[my-button]',
    host: {
      'role': 'button'
    }
  })
export class MyButton{
    
}

@Directive({ selector: '[after-ngfor-finished]' })
export class UpgradeComponentsDirective{
    constructor(private _element: ElementRef, private renderer: Renderer) {
      //  renderer.setElementStyle(_element, 'color', 'red');
    }
    @Input('after-ngfor-finished')
    set updateAfterngForFinished(upgrade : boolean){
        if(upgrade) {
            console.log("inside the after-ngfor-finished");
            
            //perform logic implement after ngfor finished 
        };
    }
    logSomething(text:string){
        console.log('from custom directive:'+ text);
      }
}
@Directive({
  selector: 'input',
  host: {
    '(change)': 'onChange($event)',
    '(window:resize)': 'onResize($event)',
    '@emitFocus': 'focus()'
  }
})
export class EventDirective {
   
    emitFocus:any;    

    constructor() {
        this.emitFocus = new EventEmitter();
      }

      focus() {
        this.emitFocus.next();
      }
  onChange(event:Event) {
    console.log("*** onChange Event binding ** "+event);
  }
  onResize(event:Event) {
      console.log("*** onResize Event binding ** "+event);
  }
}