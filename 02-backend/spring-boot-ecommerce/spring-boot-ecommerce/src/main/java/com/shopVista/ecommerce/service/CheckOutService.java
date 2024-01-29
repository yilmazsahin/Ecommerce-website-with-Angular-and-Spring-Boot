package com.shopVista.ecommerce.service;

import com.shopVista.ecommerce.dto.Purchase;
import com.shopVista.ecommerce.dto.PurchaseResponse;

/**
 * @author yilmazsahin
 * @since 1/29/2024
 */

public interface CheckOutService {
    PurchaseResponse placeOrder(Purchase purchase);
}
