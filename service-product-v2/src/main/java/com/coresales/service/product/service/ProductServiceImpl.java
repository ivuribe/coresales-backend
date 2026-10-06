package com.coresales.service.product.service;

import com.coresales.service.product.exception.BusinessException;
import com.coresales.service.product.model.Product;
import com.coresales.service.product.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productoRepository;
    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

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
        //System.out.println("PASO 2: SERVICE (LOGICA DE NEGOCIO)");
        //return new ArrayList<>(productoRepository.findAll()); //Con JPA
        //return new ArrayList<>(productoRepository.listarProductos()); //Con repositorio propio para llamar SP's

        log.info("Ejecutando consulta de productos");
        List<Product> productos = new ArrayList<>(productoRepository.listarProductos());
        log.info("Consulta de productos completada. Registros: {}", productos.size());
        return productos;
    }

    @Override
    @Transactional(readOnly = true)
    public Product obtenerPorId(Long id) {
        //return productoRepository.findById(id).orElse(null); //con JPA
        //return productoRepository.obtenerProductoPorId(id); //con SP

        log.info("Buscando producto con id: {}", id);
        Product producto = productoRepository.obtenerProductoPorId(id);
        if (producto == null) {
            log.warn("Producto no encontrado. Id: {}", id);
        } else {
            log.debug("Producto encontrado. Id: {}", id);
        }
        return producto;
    }

    @Override
    public Product crear(Product producto){
        //Product guardado = productoRepository.save(producto); //con JPA
        log.info("Creando producto. Código: {}, Nombre: {}", producto.getCodigo(),producto.getNombre());
        Product guardado = productoRepository.insertarProducto(producto); //con SP
        log.info("Producto creado correctamente. Id: {}", guardado.getProductoId());
        return guardado;
    }

    @Override
    public Product actualizar(Long id, Product producto){
        //Pasos con JPA:
        /*
        Product productoBusqueda = obtenerPorId(id);
        if (productoBusqueda == null) return null;
        producto.setFechaRegistro(productoBusqueda.getFechaRegistro());
        Product actualizado = productoRepository.save(producto); //Llamado del método del JPA
        */

        //Paso con método propio que llama a Stored Procedure
        log.info("Actualizando producto. Id: {}", id);
        Product actualizado = productoRepository.actualizarProducto(producto); //llamado a método propio
        log.info("Producto actualizado correctamente. Id: {}", id);
        return actualizado;
    }

    @Override
    public void eliminar(Long id){
        log.info("Eliminando producto. Id: {}", id);
        productoRepository.eliminarProducto(id);
        log.info("Producto eliminado correctamente. Id: {}", id);
        //productoRepository.deleteById(id); //Con JPA
        //Product producto = obtenerPorId(id);
        //productoRepository.delete(producto);
    }
}
