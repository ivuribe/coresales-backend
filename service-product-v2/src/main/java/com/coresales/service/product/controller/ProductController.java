package com.coresales.service.product.controller;

import com.coresales.service.product.model.Product;
import com.coresales.service.product.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
//@CrossOrigin("http://localhost:5173")
public class ProductController {
    private final ProductService productoService;
    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    //==========================================
    // CONSTRUCTOR
    //==========================================
    public ProductController(ProductService productoService) {
        this.productoService = productoService;
    }

    //==========================================
    // GET /api/productos
    //==========================================
    @GetMapping()
    public ResponseEntity<List<Product>> listar() {
        //System.out.println("PASO 1: CONTROLLER");
        //return ResponseEntity.ok(productoService.listar());
        log.info("GET /api/products - Listando productos");
        List<Product> productos = productoService.listar();
        log.info("GET /api/products - Productos encontrados: {}", productos.size());
        return ResponseEntity.ok(productos);
    }

    //==========================================
    // GET /api/productos/{id}
    //==========================================
    @GetMapping("/{id}")
    public ResponseEntity<Product> obtenerPorId(@PathVariable Long id) {
        log.info("GET /api/products/{} - Consultando producto", id);
        return ResponseEntity.ok(productoService.obtenerPorId(id));
    }

    //==========================================
    // POST /api/productos
    //==========================================
    @PostMapping()
    public ResponseEntity<Product> crear(@RequestBody Product request) {
        log.info("POST /api/products - Creando producto con código: {}", request.getCodigo());
        Product response = productoService.crear(request);
        log.info("POST /api/products - Producto creado. Id: {}", response.getProductoId());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    //==========================================
    // PUT /api/productos/{id}
    //==========================================
    @PutMapping("/{id}")
    public ResponseEntity<Product> actualizar(@PathVariable Long id,@RequestBody Product request) {
        log.info("PUT /api/products/{} - Actualizando producto", id);
        Product response = productoService.actualizar(id, request);
        log.info("PUT /api/products/{} - Producto actualizado", id);
        return ResponseEntity.ok(response);
    }

    //==========================================
    // DELETE /api/productos/{id}
    //==========================================
    @DeleteMapping("/{id}")
    public ResponseEntity<Product> eliminar(@PathVariable Long id) {
        log.info("DELETE /api/products/{} - Eliminando producto", id);
        productoService.eliminar(id);
        log.info("DELETE /api/products/{} - Producto eliminado", id);
        return ResponseEntity.noContent().build();
    }
}