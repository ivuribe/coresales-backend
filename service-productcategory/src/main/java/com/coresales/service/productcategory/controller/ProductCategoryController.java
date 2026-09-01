package com.coresales.service.productcategory.controller;

import com.coresales.service.productcategory.model.ProductCategory;
import com.coresales.service.productcategory.service.ProductCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productcategory")
@CrossOrigin("http://localhost:5173")
public class ProductCategoryController {
    private final ProductCategoryService categoriaProductoService;

    //==========================================
    // CONSTRUCTOR
    //==========================================
    public ProductCategoryController(ProductCategoryService categoriaProductoService) {
        this.categoriaProductoService = categoriaProductoService;
    }

    //==========================================
    // GET /api/categoriaproductos
    //==========================================
    @GetMapping()
    public ResponseEntity<List<ProductCategory>> listar() {
        return ResponseEntity.ok(categoriaProductoService.listar());
    }

    //==========================================
    // GET /api/categoriaproductos/{id}
    //==========================================
    @GetMapping("/{id}")
    public ResponseEntity<ProductCategory> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(categoriaProductoService.obtenerPorId(id));
    }

    //==========================================
    // POST /api/categoriaproductos
    //==========================================
    @PostMapping()
    public ResponseEntity<ProductCategory> crear(@RequestBody ProductCategory request) {
        ProductCategory response = categoriaProductoService.crear(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    //==========================================
    // PUT /api/categoriaproductos/{id}
    //==========================================
    @PutMapping("/{id}")
    public ResponseEntity<ProductCategory> actualizar(@PathVariable Integer id, @RequestBody ProductCategory request) {
        ProductCategory response = categoriaProductoService.actualizar(id, request);
        return ResponseEntity.ok(response);
    }

    //==========================================
    // DELETE /api/categoriaproductos/{id}
    //==========================================
    @DeleteMapping("/{id}")
    public ResponseEntity<ProductCategory> eliminar(@PathVariable Integer id) {
        categoriaProductoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}