package com.jdc.onlineshopping.repository;

import com.jdc.onlineshopping.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author tiendao on 22/07/2021
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
