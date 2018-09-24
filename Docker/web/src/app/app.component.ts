import { ConfigService } from './shared/service/config.service';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, NavigationEnd, Event } from '@angular/router';
import { Title } from '@angular/platform-browser';
import { TranslationService } from 'angular-l10n';
import { AdalService } from './shared/service/adal.service';
// import { AdalService } from 'ng2-adal/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})
export class AppComponent implements OnInit {
  constructor(titleService: Title, router: Router, activatedRoute: ActivatedRoute, public translation: TranslationService,
    private adalService: AdalService, private secretService: ConfigService) {

    // this.adalService.init(this.secretService.getAdalConfig);
    router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        const title = this.getTitle(router.routerState, router.routerState.root).join('-');
        // console.log('title', title);
        if (title === '') {
          titleService.setTitle('OLAM International');
        } else {
          titleService.setTitle(this.translation.translate(title));
        }
      }
    });
  }

  getTitle(state, parent) {
    const data = [];
    if (parent && parent.snapshot.data && parent.snapshot.data.title) {
      data.push(parent.snapshot.data.title);
    }

    if (state && parent) {
      data.push(... this.getTitle(state, state.firstChild(parent)));
    }
    return data;
  }

    public ngOnInit(): void {
      // this.adalService.handleWindowCallback();
      // this.adalService.getUser();
      // this.adalService.userInfo;
      sessionStorage.setItem('loadingImageCounter', String(0));
      console.log('User Details --->' + this.adalService.userInfo);
    }
}

