import { XHRBackend, RequestOptions } from '@angular/http';
import { HttpService } from './http.service';
import { LoaderService } from '../loading-animation/loader.service';
import { AdalService } from '../service/adal.service';
// import { AdalService } from 'ng2-adal/core';
import { Router } from '@angular/router';

function httpServiceFactory(backend: XHRBackend, defaultOptions: RequestOptions,
    adalService: AdalService, router: Router,  loaderService: LoaderService ) {
    return new HttpService(backend, defaultOptions, adalService, router, loaderService);
}

export { httpServiceFactory };
