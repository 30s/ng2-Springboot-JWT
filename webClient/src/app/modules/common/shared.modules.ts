import { NgModule,Optional,SkipSelf }            from '@angular/core';
import { CommonModule }        from '@angular/common';
import { FormsModule }         from '@angular/forms';
import { HighlightDirective,ExponentialPipe,UpgradeComponentsDirective }         from './index';
//import { CustomDatepickerComponent,HighlightDirective,ExponentialPipe,UpgradeComponentsDirective }         from './index';
@NgModule({
    imports:      [ CommonModule ],
    declarations: [ ExponentialPipe, HighlightDirective ,UpgradeComponentsDirective],
    exports:      [ ExponentialPipe, HighlightDirective,
                    CommonModule, FormsModule,UpgradeComponentsDirective ]
})
export class SharedModule {
         
    constructor (@Optional() @SkipSelf() sharedModule: SharedModule) {
        if (sharedModule) {
          throw new Error('SharedModule is already loaded. Import it in the AppModule only');
        }
      }
}