import { PaymentInfo } from './../common/payment-info';
import { HttpBackend, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Purchase } from '../common/purchase';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class CheckoutService {
  private paymentIntentUrl =
    environment.shopVistaApiUrl + '/checkout/payment-intent';
  private purchaseUrl = environment.shopVistaApiUrl + '/checkout/purchase';

  constructor(private httpClient: HttpClient) {}
  placeOrder(purchase: Purchase): Observable<any> {
    return this.httpClient.post<Purchase>(this.purchaseUrl, purchase);
  }
  createPaymentIntent(paymentInfo: PaymentInfo): Observable<any> {
    return this.httpClient.post<PaymentInfo>(
      this.paymentIntentUrl,
      paymentInfo
    );
  }
}
