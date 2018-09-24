import { environment } from '../../../environments/environment.prod';
import { window } from 'rxjs/operator/window';
import { AdalService } from '../service/adal.service';
// import { AdalService } from 'ng2-adal/core';
import { ActivatedRouteSnapshot, CanActivate, CanActivateChild, Router, RouterStateSnapshot, NavigationExtras } from '@angular/router';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class AuthGuardService implements CanActivate, CanActivateChild {

  constructor(private adalService: AdalService, private router: Router) { }


  public canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    // ---------------->>>>>> custom adal implementation
    if (environment.enableAuth) {
      if (this.adalService.isAuthenticated) {
        return true;
      } else {
        // this.router.navigate(['/']);
        this.adalService.login();
        return false;
      }
    } else {
      return true;
    }

    // ------------------>>>> ng2-adal implementation
    // if (this.adalService.userInfo.isAuthenticated) {
    //   return true;
    // } else {
    //   // this.router.navigate(['/login']);
    //   this.adalService.login();
    //   return false;
    // }
  }

  public canActivateChild(childRoute: ActivatedRouteSnapshot, state: RouterStateSnapshot)
    : Observable<boolean> | Promise<boolean> | boolean {
    return this.canActivate(childRoute, state);
  }
}


