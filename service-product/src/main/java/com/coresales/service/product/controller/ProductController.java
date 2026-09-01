package com.coresales.service.product.controller;

import com.coresales.service.product.model.Product;
import com.coresales.service.product.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin("http://localhost:5173")
public class ProductController {
    private final ProductService productoService;

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
        return ResponseEntity.ok(productoService.listar());
    }

    //==========================================
    // GET /api/productos/{id}
    //==========================================
    @GetMapping("/{id}")
    public ResponseEntity<Product> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.obtenerPorId(id));
    }

    //==========================================
    // POST /api/productos
    //==========================================
    @PostMapping()
    public ResponseEntity<Product> crear(@RequestBody Product request) {
        Product response = productoService.crear(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    //==========================================
    // PUT /api/productos/{id}
    //==========================================
    @PutMapping("/{id}")
    public ResponseEntity<Product> actualizar(@PathVariable Long id,@RequestBody Product request) {
        Product response = productoService.actualizar(id, request);
        return ResponseEntity.ok(response);
    }

    //==========================================
    // DELETE /api/productos/{id}
    //==========================================
    @DeleteMapping("/{id}")
    public ResponseEntity<Product> eliminar(@PathVariable Long id) {
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}