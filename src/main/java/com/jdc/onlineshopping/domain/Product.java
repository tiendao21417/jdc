package com.jdc.onlineshopping.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author tiendao on 17/07/2021
 */
@Entity
@Table(name = "m_product")
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JsonIgnoreProperties(value = "category", allowSetters = true)
    private Category category;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private double price;

    @Column(name = "remain_amount")
    private int remainAmount;

    @ManyToOne
    @JsonIgnoreProperties(value = "brand", allowSetters = true)
    private Brand brand;

    @Column(name = "colour")
    private String colour;

    @Column(name = "url")
    private String url;
}
