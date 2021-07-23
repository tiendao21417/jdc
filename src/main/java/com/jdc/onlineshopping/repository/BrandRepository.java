package com.jdc.onlineshopping.repository;

import com.jdc.onlineshopping.domain.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author tiendao on 18/07/2021
 */
@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
}
