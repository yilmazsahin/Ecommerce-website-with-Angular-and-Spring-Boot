import { environment } from '../../environments/environment';
// import { environment } from './../../environments/environment';

import { OktaAuth } from '@okta/okta-auth-js';
import {
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
} from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { Observable, from, lastValueFrom } from 'rxjs';
import { OKTA_AUTH } from '@okta/okta-angular';

@Injectable({
  providedIn: 'root',
})
export class AuthInterceptorService implements HttpInterceptor {
  constructor(@Inject(OKTA_AUTH) private oktaAuth: OktaAuth) {}

  intercept(
    request: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    return from(this.handleAccess(request, next));
  }

  private async handleAccess(
    request: HttpRequest<any>,
    next: HttpHandler
  ): Promise<HttpEvent<any>> {
    const theEndPoint = environment.shopVistaApiUrl + '/orders';
    // const theEndPoint = environment.shopVistaApiUrl + '/orders';
    // Only add an access token for secured endpoints
    const securedEndpoints = [theEndPoint];

    if (securedEndpoints.some((url) => request.urlWithParams.includes(url))) {
      // get access token
      const accessToken = this.oktaAuth.getAccessToken();

      // clone the request and add new header with access token
      request = request.clone({
        setHeaders: {
          Authorization: 'Bearer ' + accessToken,
        },
      });
    }

    return await lastValueFrom(next.handle(request));
  }
}
