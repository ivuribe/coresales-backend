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
        System.out.println("PASO 2: SERVICE (LOGICA DE NEGOCIO)");
        //return new ArrayList<>(productoRepository.findAll()); //Con JPA
        return new ArrayList<>(productoRepository.listarProductos()); //Con repositorio propio para llamar SP's
    }

    @Override
    @Transactional(readOnly = true)
    public Product obtenerPorId(Long id) {
        //return productoRepository.findById(id).orElse(null);
        return productoRepository.obtenerProductoPorId(id);
    }

    @Override
    public Product crear(Product producto){
        //Product guardado = productoRepository.save(producto);
        Product guardado = productoRepository.insertarProducto(producto);
        return guardado;
    }

    @Override
    public Product actualizar(Long id, Product producto){
        Product productoBusqueda = obtenerPorId(id);

        if (productoBusqueda == null) return null;

        producto.setFechaRegistro(productoBusqueda.getFechaRegistro());
        //Product actualizado = productoRepository.save(producto);
        Product actualizado = productoRepository.actualizarProducto(producto);

        return actualizado;
    }

    @Override
    public void eliminar(Long id){
        productoRepository.eliminarProducto(id);
        //productoRepository.deleteById(id); //Con JPA
        //Product producto = obtenerPorId(id);
        //productoRepository.delete(producto);
    }
}
