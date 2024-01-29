package com.shopVista.ecommerce.dto;

import com.shopVista.ecommerce.entity.Address;
import com.shopVista.ecommerce.entity.Customer;
import com.shopVista.ecommerce.entity.Order;
import com.shopVista.ecommerce.entity.OrderItem;
import lombok.Data;

import java.util.Set;

/**
 * @author yilmazsahin
 * @since 1/29/2024
 */
@Data
public class Purchase {
    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;
}
