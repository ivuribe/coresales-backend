package com.coresales.service.productcategory.repository;

import com.coresales.service.productcategory.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
}
