import { Injectable } from '@angular/core';
import { Link } from '../interface/link';
import { ErrorBeans , ResponseData } from '../interface/responseData'
import { Observable } from 'rxjs/Observable';

@Injectable()
export class AbstractLinkService {

  private childObjects: Map<string, Link> = new Map<string, Link>();

  constructor() { }

  public addChildObjects(links: Link[]) {
    for (const childData of links) {
      this.childObjects.set(childData.rel + '.' + childData.method, childData);
    }
    sessionStorage.olamLinks = JSON.stringify(Array.from(this.childObjects.entries()));
  }

  public getChildObject(objectName: string): Link {
    try {
      this.childObjects = new Map(JSON.parse(sessionStorage.olamLinks));
      return this.childObjects.get(objectName);
    } catch (error) {
      return null;
    }
  }

  public getChildObjects(): Map<string, Link> {
    try {
      this.childObjects = new Map(JSON.parse(sessionStorage.olamLinks));
      return this.childObjects;
    } catch (error) {
      return null;
    }
  }
  

  public errorHandling(error: any): Observable<any> {
    const errorResponse: ResponseData = JSON.parse(error._body);
    const errorBean: ErrorBeans[] = errorResponse.errorBeans;
    const errorStatusCode: number = error.status;

    if (error.status === 500) {
        return Observable.throw(this.getErrorList(errorBean) != null ? this.getErrorList(errorBean) : errorResponse.errorMessage );

    } else if (error.status === 400) {
      return Observable.throw(this.getErrorList(errorBean) != null ? this.getErrorList(errorBean) : errorResponse.errorMessage );
    } else if (error.status === 409 || error.status === 404) {
      return Observable.throw(this.getErrorList(errorBean) != null ? this.getErrorList(errorBean) : errorResponse.errorMessage );
    } else if (error.status === 406) {
      return Observable.throw(this.getErrorList(errorBean) != null ? this.getErrorList(errorBean) : errorResponse.errorMessage );
    }
  }

  private getErrorList(errorBean: ErrorBeans[]): Error[] {
    const errors: Error[] = [];
     if ( errorBean != null) {
        errorBean.forEach(element => {
        const err: Error = new Error(element.errorMessage);
        errors.push(err);
      });
     }
      return errors;
  }

}