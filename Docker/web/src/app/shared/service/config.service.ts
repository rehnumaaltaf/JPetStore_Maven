import { environment } from '../../../environments/environment.prod';
import { Injectable } from '@angular/core';

@Injectable()

export class ConfigService {

  constructor() { }

  public get getAdalConfig(): any {

    return {
      tenant: environment.tenantId, // 'ce8d9ca6-e8d0-4f87-87d0-b979e28d5b3c', // '5e007b6c-258b-4fde-adc1-8bf8a135885d',
      clientId: environment.clientId, // '1cdafe9a-1213-4435-a7d2-607dfdca1cd3', // '4cc0ace3-56f9-47b9-b822-4692185c2077',
      redirectUri: window.location.origin + '/', // 'https://ctrmdev.azurewebsites.net/.auth/login/aad/callback', // 
      // localLoginUrl: window.location.origin + '/login',
      postLogoutRedirectUri: window.location.origin + '/',
      isAngular: true,
      // cacheLocation: '/olam/test'
      // tenant?: string;
      // clientId: string;
      // redirectUri?: string;
      // instance?: string;
      // endpoints?: any;  // If you need to send CORS api requests.
      // popUp?: boolean;
      // localLoginUrl?: string;
      // displayCall?: (urlNavigate: string) => any;
      // postLogoutRedirectUri?: string; // redirect url after succesful logout operation
      // cacheLocation?: string;
      // anonymousEndpoints?: string[];
      // expireOffsetSeconds?: number;
      // correlationId?: string;
      // loginResource?: string;
      // resource?: string;
      // extraQueryParameter?: string;
      // navigateToLoginRequestUrl?: boolean;
      // logOutUri?: string;
      // isAngular?: boolean;
    };
  }
}
