package com.coresales.service.product.repository;

import com.coresales.service.product.model.Product;
import java.util.List;

public interface ProductRepositoryCustom {
    List<Product> listarProductos();
    Product obtenerProductoPorId(Long id);
    Product insertarProducto(Product product);
    Product actualizarProducto(Product product);
    void eliminarProducto(Long id);
}
