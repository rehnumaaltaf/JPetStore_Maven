import { ErrorHandler, Injectable, Injector } from '@angular/core';
import { Router } from '@angular/router';

@Injectable()
export class GlobalErrorHandler implements ErrorHandler {

   router: Router;

  constructor(private injector: Injector) {
    setTimeout(() => this.router = injector.get(Router));
  }

    handleError(error) {
        console.log('Error');
        this.router.navigate(['error']);
        // IMPORTANT: Rethrow the error otherwise it gets swallowed
        throw error;
    }

}
