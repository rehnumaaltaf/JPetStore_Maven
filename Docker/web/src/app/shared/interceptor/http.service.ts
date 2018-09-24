import { AdalService } from '../service/adal.service';
import { parse } from 'querystring';
// import { AdalService } from 'ng2-adal/core';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { XHRBackend, RequestOptions, Request, RequestOptionsArgs, Response, Http, Headers } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { environment } from '../../../environments/environment';
import { LoaderService } from '../loading-animation/loader.service';
import { noLoading } from '../interface/router-links';

@Injectable()
export class HttpService extends Http {
    public showLoading: Boolean = false;

    constructor(backend: XHRBackend, defaultOptions: RequestOptions,
        private adalService: AdalService, private router: Router, private loaderService: LoaderService) {
        super(backend, defaultOptions);
    }

    get(url: string, options?: RequestOptionsArgs): Observable<Response> {
        this.turnOnModal(url);
        url = this.updateUrl(url);
        return this.intercept(url, super.get(url, this.getRequestOptionArgs(options)));
    }

    post(url: string, body: string, options?: RequestOptionsArgs): Observable<Response> {
        this.turnOnModal(url);
        url = this.updateUrl(url);
        return this.intercept(url, super.post(url, body, this.getRequestOptionArgs(options)));
    }

    put(url: string, body: string, options?: RequestOptionsArgs): Observable<Response> {
        this.turnOnModal(url);
        url = this.updateUrl(url);
        return this.intercept(url, super.put(url, body, this.getRequestOptionArgs(options)));
    }

    delete(url: string, options?: RequestOptionsArgs): Observable<Response> {
        this.turnOnModal(url);
        url = this.updateUrl(url);
        return this.intercept(url, super.delete(url, this.getRequestOptionArgs(options)));
    }

    private updateUrl(req: string) {
        if (req.startsWith('./assets')) {
            return req;
        } else if (req.startsWith(noLoading)) {
            req = req.replace(noLoading, '');
        } else {
            window.scrollTo(0, 0);
            console.log('Access Token-->' + this.adalService.accessToken);
            if (!this.adalService.isAuthenticated && environment.enableAuth) {
                this.adalService.login();
                // this.router.navigate(['error']);
                // throw new Error();
                // return null;
            }
        }
        return environment.path + req;
    }

    private getRequestOptionArgs(options?: RequestOptionsArgs): RequestOptionsArgs {
        if (options == null) {
            options = new RequestOptions();
        }
        if (options.headers == null) {
            options.headers = new Headers();
        }
        options.headers.append('Content-Type', 'application/json');
        options.headers.append('Authorization', 'Bearer ' + this.adalService.accessToken);

        return options;
    }

    private intercept(url, observable: Observable<Response>): Observable<Response> {
        return observable
            .catch(this.onCatch)
            .do((res: Response) => {
                this.onSuccess(res);
            }, (err: any) => {
                this.onError(err);
            })
            .finally(() => {
                this.turnOffModal(url);
            });
    }

    private onCatch(error: any, caught: Observable<any>): Observable<any> {
        return Observable.throw(error);
    }

    private onSuccess(res: Response): void {
        // console.log('Request successful')
    }

    private onError(res: Response): void {
        // TODO: logging of error to server
        console.log('Error, status code: ' + res.status);
    }

    private turnOnModal(url: string): void {
         if (!url.startsWith(noLoading) && !url.startsWith('./assets')) {
            const counter: number = parseInt(sessionStorage.getItem('loadingImageCounter'), 0);
            sessionStorage.setItem('loadingImageCounter', String(counter != null ? counter + 1 : 1));
            this.loaderService.show();
        }
    }

    private turnOffModal(url: string): void {
        if (!url.startsWith(noLoading) && !url.startsWith('./assets')) {
            const counter: number = parseInt(sessionStorage.getItem('loadingImageCounter'), 0);
            sessionStorage.setItem('loadingImageCounter', String(counter - 1));
            if (counter <= 1) {
                sessionStorage.setItem('loadingImageCounter', String(0));
                this.loaderService.hide();
            }
        }
    }
}
