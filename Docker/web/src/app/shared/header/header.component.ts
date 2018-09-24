import { Component, Input, OnInit } from '@angular/core';
import { LoginService} from '../../core/login/login.service';

@Component({
    selector: 'app-header',
    templateUrl: './header.component.html',
    styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
    @Input() public title: string;
    @Input() public isUserLoggedIn: boolean;

    constructor(private loginService: LoginService) {

    }

    ngOnInit() {
      // Called after the constructor, initializing input properties, and the first call to ngOnChanges.
      // Add 'implements OnInit' to the class.
    }

    isLoggedIn() {
        return this.loginService.isLoggedIn;
    }
}
