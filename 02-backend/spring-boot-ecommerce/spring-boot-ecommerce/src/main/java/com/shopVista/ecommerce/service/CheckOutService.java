package com.shopVista.ecommerce.service;

import com.shopVista.ecommerce.dto.PaymentInfo;
import com.shopVista.ecommerce.dto.Purchase;
import com.shopVista.ecommerce.dto.PurchaseResponse;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

/**
 * @author yilmazsahin
 * @since 1/29/2024
 */

public interface CheckOutService {
    PurchaseResponse placeOrder(Purchase purchase);
    PaymentIntent createPaymentIntent(PaymentInfo paymentInfo) throws StripeException;
}
