import { Injectable } from '@angular/core';
import { CanDeactivate } from '@angular/router';
import { Observable } from 'rxjs/Observable';

export interface CanComponentDeactivate {
  canDeactivate: () => Observable<boolean> | Promise<boolean> | boolean;
}

@Injectable()
export class CanDeactivateGuard implements CanDeactivate<CanComponentDeactivate> {
  canDeactivate(component: CanComponentDeactivate) {
    if (!component.canDeactivate()) {
      // TODO: window.confirm need to be changed to the design provided
      return window.confirm('You are moving away from the page without saving. Your data will be lost, Do you want to proceed ?');
    }
    return true;
  }
}
