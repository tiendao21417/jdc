package com.jdc.onlineshopping.repository;

import com.jdc.onlineshopping.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author tiendao on 22/07/2021
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
