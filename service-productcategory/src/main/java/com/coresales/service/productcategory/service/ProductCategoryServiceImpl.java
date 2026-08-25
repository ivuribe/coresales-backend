package com.coresales.service.productcategory.service;

import com.coresales.service.productcategory.model.ProductCategory;
import com.coresales.service.productcategory.repository.ProductCategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProductCategoryServiceImpl implements ProductCategoryService {

    private final ProductCategoryRepository categoriaProductoRepository;

    //==========================================
    // CONSTRUCTOR
    //==========================================
    public ProductCategoryServiceImpl(ProductCategoryRepository categoriaProductoRepository) {
        this.categoriaProductoRepository = categoriaProductoRepository;
    }

    //==========================================
    // MÉTODOS
    //==========================================
    @Override
    @Transactional(readOnly = true)
    public List<ProductCategory> listar(){
        return new ArrayList<>(categoriaProductoRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public ProductCategory obtenerPorId(Integer id) {
        return categoriaProductoRepository.findById(id).orElse(null);
    }

    @Override
    public ProductCategory crear(ProductCategory categoriaProducto){
        ProductCategory guardado = categoriaProductoRepository.save(categoriaProducto);
        return guardado;
    }

    @Override
    public ProductCategory actualizar(Integer id, ProductCategory categoriaProducto){
        ProductCategory categoriaProductoBusqueda = obtenerPorId(id);
        if (categoriaProductoBusqueda == null) return null;
        ProductCategory actualizado = categoriaProductoRepository.save(categoriaProducto);
        return actualizado;
    }

    @Override
    public void eliminar(Integer id){
        categoriaProductoRepository.deleteById(id);
        //Product categoriaProducto = obtenerPorId(id);
        //categoriaProductoRepository.delete(categoriaProducto);
    }
}
