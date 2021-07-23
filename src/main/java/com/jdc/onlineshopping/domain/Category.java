package com.jdc.onlineshopping.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author tiendao on 17/07/2021
 */
@Entity
@Table(name = "m_category")
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;
}
