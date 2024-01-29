package com.shopVista.ecommerce.dao;

import com.shopVista.ecommerce.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * @author yilmazsahin
 * @since 1/29/2024
 */
//@CrossOrigin("http://localhost:4200")
public interface CustomerRepository extends JpaRepository<Customer,Long> {

}
