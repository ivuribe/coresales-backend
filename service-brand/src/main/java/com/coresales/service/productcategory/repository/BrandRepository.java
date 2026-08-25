package com.coresales.service.productcategory.repository;

import com.coresales.service.productcategory.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Integer> {
}
