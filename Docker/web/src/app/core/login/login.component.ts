import { AdalService } from '../../shared/service/adal.service';
import { environment } from '../../../environments/environment';
import { Component, EventEmitter, Output, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { LocaleService } from 'angular-l10n';

import { LoginService } from './login.service';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { Login } from './login';
import { AccordionComponent, DraggableItem } from 'ngx-bootstrap';
import { master } from '../../shared/interface/router-links';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit, OnDestroy {
    // @Output() loggedIn = new EventEmitter<Login>();
    login: Login = new Login();
    subscription: Subscription;
    lang: string;
    Data: Login;
    country: string;
    userForm: FormGroup;
    constructor(private router: Router, private loginService: LoginService,
        private adalService: AdalService, private fb: FormBuilder, public locale: LocaleService) {
    }


    ngOnInit() {
        this.router.navigate(['']);
        this.userForm = this.fb.group({
            email: ['', [
                Validators.required,
                Validators.pattern('[^ @]*@[^ @]*')]],
            pwd: ['', [
                Validators.required,
                Validators.minLength(18)]],
        });
    }

    onTestPost() {
        // let flag = '';

        this.loginService.isLoggedIn = true;
        this.locale.setDefaultLocale('en', 'US');
        this.router.navigate([master]);
        /**
        if(this.userForm.value.email === "" || this.userForm.value.pwd === "" ||
                    this.userForm.value.email === undefined || this.userForm.value.pwd==undefined){
            alert("Please Enter Email and Password");
            return false;
        }
        else{
         this.loginService.postJSON(this.userForm.value).subscribe(Data => {
         this.Data = <Login>Data;
             flag = this.Data.isAuthenticated;
            // console.log('flag===>' + flag)
             if (flag == '1') {
                // console.log('success Login');
                 this.router.navigate([master]);
             } else {
                 alert('Invalid Login');
                 return  false;
             }
         },
             error => alert(error),
             () => console.log('Finished')
 );
        }*/
        // this.getLocale();

        // this.loginService.postJSON(this.userForm.value).subscribe(Data => {
        //     this.Data = <Login>Data;
        //     flag = this.Data.isAuthenticated;
        //     console.log('flag===>' + flag)
        //     if (flag === '1') {
        //         console.log('success Login');
        //         this.router.navigate(['/partygrade']);
        //     } else {
        //         console.log('Invalid Login');
        //         return  false;
        //     }
        // },
        //     error => alert(error),
        //     () => console.log('Finished')
        // );
    }



    getLocale() {
        this.subscription = this.loginService.getLocale().subscribe(data => {
            this.lang = data.lang;
            this.country = data.country;
            // this.locale.setDefaultLocale(this.lang, this.country);
            this.locale.setDefaultLocale('en', 'US');
            this.router.navigate([master]);
        }, error => {
            console.log('Error Loading UOM Listing: ' + <any>error)
            // this.loadingData = false;
            // this.notificationService.addNotications(error);
        });
    }

    redirecttoOlam() {
        // window.location.href = 'http://olamgroup.com';
        window.open('http://olamgroup.com');
       // this.router.navigate([master]);
    }

    ngOnDestroy() {
        // Called once, before the instance is destroyed.
        // this.subscription.unsubscribe();
    }
}
