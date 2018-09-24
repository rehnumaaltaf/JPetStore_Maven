import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Subject } from 'rxjs/Subject';

import { LoaderState } from './loader';

@Injectable()

export class LoaderService {

    loaderSubject = new Subject<LoaderState>();

    loaderState = this.loaderSubject.asObservable();

    constructor() { }

    show() {
        this.loaderSubject.next(<LoaderState>{show: true});
    }

    hide() {
        this.loaderSubject.next(<LoaderState>{show: false});
    }
    getMessage(): Observable<LoaderState> {
        return this.loaderSubject.asObservable();
    }
}
