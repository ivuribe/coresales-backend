package com.coresales.service.productcategory.service;

import com.coresales.service.productcategory.model.ProductCategory;

import java.util.List;

public interface ProductCategoryService {
    List<ProductCategory> listar();
    ProductCategory obtenerPorId(Integer id);
    ProductCategory crear(ProductCategory categoriaProducto);
    ProductCategory actualizar(Integer id, ProductCategory categoriaProducto);
    void eliminar(Integer id);
}
