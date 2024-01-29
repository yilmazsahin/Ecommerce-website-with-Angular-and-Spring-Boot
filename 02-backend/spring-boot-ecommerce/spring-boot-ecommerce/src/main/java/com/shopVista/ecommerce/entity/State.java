package com.shopVista.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * @author yilmazsahin
 * @since 1/28/2024
 */
@Entity
@Data
@Table(name = "state")
public class State {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    @Column(name = "name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;
}
