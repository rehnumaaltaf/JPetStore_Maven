import { Component, OnInit } from '@angular/core';

import { Router } from '@angular/router';

import { AdalService } from '../service/adal.service';

@Component({

    template: '<div>Please wait...</div>'

})

export class OAuthCallbackComponent implements OnInit {

    constructor(private router: Router, private adalService: AdalService) {

    }

    ngOnInit() {

        if (!this.adalService.userInfo) {

            // this.router.navigate(['login']);
            this.adalService.login();

        } else {

            this.router.navigate(['']);

        }

    }

}
