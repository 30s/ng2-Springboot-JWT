import { NgModule,Optional ,SkipSelf} from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent,RegisterComponent,AdminDashboardComponent } from './modules/index';

export const routes: Routes = [
    { path: 'login', component: LoginComponent },
    { path: 'register', component: RegisterComponent },
    { path: 'adminSettings', component: AdminDashboardComponent },
/*  This is a lazy loading it will load when user click on admin link  
    {
        path: 'admin',
        loadChildren: 'app/admin/admin.module#AdminModule',
      }, */
    { path: '', redirectTo: 'home' ,pathMatch: 'full'},
  // { path: '**', component: RouteComponentNotFound }
];
@NgModule({
    imports: [RouterModule.forRoot(routes)],//RouterModule.forRoot(routes, { useHash: true })  
    /* This is a preloading Strategy example,it load all the module on the basis od route order. this strategy can load module
     * implemented by Canload guard
     RouterModule.forRoot(
      appRoutes
      , { preloadingStrategy: PreloadAllModules }
    )
 */
    exports: [RouterModule]
  })
  
 export class AppRoutingModule {
    /* This is Preventing  reimport of the CustomComponentModules */
    constructor (@Optional() @SkipSelf() appRoutingModule: AppRoutingModule) {
        if (appRoutingModule) {
          throw new Error(
            'AppRoutingModule is already loaded. Import it in the AppModule only');
        }
      }
}
