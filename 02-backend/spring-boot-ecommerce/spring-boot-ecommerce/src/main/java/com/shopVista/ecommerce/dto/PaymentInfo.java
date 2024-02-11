package com.shopVista.ecommerce.dto;

import lombok.Data;

/**
 * @author yilmazsahin
 * @since 2/11/2024
 */
@Data
public class PaymentInfo {
    private int amount;
    private String currency;
}
