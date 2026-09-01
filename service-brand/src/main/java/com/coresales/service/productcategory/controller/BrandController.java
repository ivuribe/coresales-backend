package com.coresales.service.productcategory.controller;

import com.coresales.service.productcategory.model.Brand;
import com.coresales.service.productcategory.service.BrandService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brand")
@CrossOrigin("http://localhost:5173")
public class BrandController {
    private final BrandService marcaService;

    //==========================================
    // CONSTRUCTOR
    //==========================================
    public BrandController(BrandService marcaService) {
        this.marcaService = marcaService;
    }

    //==========================================
    // GET /api/marcas
    //==========================================
    @GetMapping()
    public ResponseEntity<List<Brand>> listar() {
        return ResponseEntity.ok(marcaService.listar());
    }

    //==========================================
    // GET /api/marcas/{id}
    //==========================================
    @GetMapping("/{id}")
    public ResponseEntity<Brand> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(marcaService.obtenerPorId(id));
    }

    //==========================================
    // POST /api/marcas
    //==========================================
    @PostMapping()
    public ResponseEntity<Brand> crear(@RequestBody Brand request) {
        Brand response = marcaService.crear(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    //==========================================
    // PUT /api/marcas/{id}
    //==========================================
    @PutMapping("/{id}")
    public ResponseEntity<Brand> actualizar(@PathVariable Integer id, @RequestBody Brand request) {
        Brand response = marcaService.actualizar(id, request);
        return ResponseEntity.ok(response);
    }

    //==========================================
    // DELETE /api/marcas/{id}
    //==========================================
    @DeleteMapping("/{id}")
    public ResponseEntity<Brand> eliminar(@PathVariable Integer id) {
        marcaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}