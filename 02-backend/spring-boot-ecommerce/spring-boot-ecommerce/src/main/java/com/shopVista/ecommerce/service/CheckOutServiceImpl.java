package com.shopVista.ecommerce.service;

import com.shopVista.ecommerce.dao.CustomerRepository;
import com.shopVista.ecommerce.dto.PaymentInfo;
import com.shopVista.ecommerce.dto.Purchase;
import com.shopVista.ecommerce.dto.PurchaseResponse;
import com.shopVista.ecommerce.entity.Customer;
import com.shopVista.ecommerce.entity.Order;
import com.shopVista.ecommerce.entity.OrderItem;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author yilmazsahin
 * @since 1/29/2024
 */
@Service
public class CheckOutServiceImpl implements CheckOutService {
    private CustomerRepository customerRepository;

    public CheckOutServiceImpl(CustomerRepository customerRepository,
                               @Value("${stripe.key.secret}") String secretKey) {
        this.customerRepository = customerRepository;
        Stripe.apiKey = secretKey;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {

        Order order = purchase.getOrder();

        String orderTrackingNumber = generateOrderTrackingNumber();
        order.setOrderTrackingNumber(orderTrackingNumber);

        Set<OrderItem> orderItems = purchase.getOrderItems();
        orderItems.forEach(item -> order.add(item));

        order.setBillingAddress(purchase.getBillingAddress());
        order.setShippingAddress(purchase.getShippingAddress());

        Customer customer = purchase.getCustomer();
        String theEmail = customer.getEmail();

        Customer customerFromDB = customerRepository.findByEmail(theEmail);
        if (customerFromDB != null) {
            customer = customerFromDB;
        }
        customer.add(order);

        customerRepository.save(customer);


        return new PurchaseResponse(orderTrackingNumber);
    }

    @Override
    public PaymentIntent createPaymentIntent(PaymentInfo paymentInfo) throws StripeException {
        List<String> paymentMethodTypes = new ArrayList<>();
        paymentMethodTypes.add("card");
        Map<String, Object> params = new HashMap<>();
        params.put("amount", paymentInfo.getAmount());
        params.put("currency", paymentInfo.getCurrency());
        params.put("payment_method_types", paymentMethodTypes);
        return PaymentIntent.create(params);

    }

    private String generateOrderTrackingNumber() {
        return UUID.randomUUID().toString();
    }
}
