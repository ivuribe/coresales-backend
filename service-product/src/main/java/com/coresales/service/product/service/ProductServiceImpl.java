package com.coresales.service.product.service;

import com.coresales.service.product.model.Product;
import com.coresales.service.product.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productoRepository;

    //==========================================
    // CONSTRUCTOR
    //==========================================
    public ProductServiceImpl(ProductRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    //==========================================
    // MÉTODOS
    //==========================================
    @Override
    @Transactional(readOnly = true)
    public List<Product> listar(){
        return new ArrayList<>(productoRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Product obtenerPorId(Long id) {

        return productoRepository.findById(id).orElse(null);

    }

    @Override
    public Product crear(Product producto){
        Product guardado = productoRepository.save(producto);
        return guardado;
    }

    @Override
    public Product actualizar(Long id, Product producto){
        Product productoBusqueda = obtenerPorId(id);

        if (productoBusqueda == null) return null;

        producto.setFechaRegistro(productoBusqueda.getFechaRegistro());
        Product actualizado = productoRepository.save(producto);

        return actualizado;
    }

    @Override
    public void eliminar(Long id){
        productoRepository.deleteById(id);
        //Product producto = obtenerPorId(id);
        //productoRepository.delete(producto);
    }
}
