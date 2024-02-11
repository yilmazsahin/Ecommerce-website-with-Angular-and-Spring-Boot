package com.shopVista.ecommerce.controller;

import com.shopVista.ecommerce.dto.PaymentInfo;
import com.shopVista.ecommerce.dto.Purchase;
import com.shopVista.ecommerce.dto.PurchaseResponse;
import com.shopVista.ecommerce.service.CheckOutService;
import com.shopVista.ecommerce.service.CheckOutServiceImpl;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author yilmazsahin
 * @since 1/29/2024
 */
@RestController
@RequestMapping("/api/checkout")
public class CheckOutController {
    private CheckOutService checkOutService;

    @Autowired
    public CheckOutController(CheckOutService checkOutService) {
        this.checkOutService = checkOutService;
    }

    @PostMapping("/purchase")
    public PurchaseResponse placeOrder(@RequestBody Purchase purchase) {
        PurchaseResponse purchaseResponse = checkOutService.placeOrder(purchase);
        return purchaseResponse;
    }

    @PostMapping("/payment-intent")
    public ResponseEntity<String> createPaymentIntent(@RequestBody PaymentInfo paymentInfo) throws StripeException {
        PaymentIntent paymentIntent = checkOutService.createPaymentIntent(paymentInfo);
        String paymentStr = paymentIntent.toJson();
        return new ResponseEntity<>(paymentStr, HttpStatus.OK);
    }

}
