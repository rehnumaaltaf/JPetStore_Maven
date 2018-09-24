import { Injectable } from '@angular/core';

import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';

import { AdalService } from '../service/adal.service';

@Injectable()

export class OAuthCallbackHandler implements CanActivate {

    constructor(private router: Router, private adalService: AdalService) {

    }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {

        this.adalService.handleCallback();

        if (this.adalService.userInfo) {

            const returnUrl = route.queryParams['returnUrl'];
            if (!returnUrl) {
                this.router.navigate(['']);
            } else {
                this.router.navigate([route.queryParams]);
            }
        } else {
            // this.router.navigate(['login']);
            this.adalService.login();
        }

        return false;
    }

}
