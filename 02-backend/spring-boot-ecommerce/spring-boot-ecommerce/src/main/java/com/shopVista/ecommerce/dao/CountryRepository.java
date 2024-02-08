package com.shopVista.ecommerce.dao;

import com.shopVista.ecommerce.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * @author yilmazsahin
 * @since 1/28/2024
 */

@RepositoryRestResource(collectionResourceRel = "countries", path = "countries")

public interface CountryRepository extends JpaRepository<Country, Integer> {

}
