package com.coresales.service.inventorymovement.controller;

import com.coresales.service.inventorymovement.model.InventoryMovement;
import com.coresales.service.inventorymovement.service.InventoryMovementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventorymovement")
@CrossOrigin("http://localhost:5173")
public class InventoryMovementController {
    private final InventoryMovementService movimientoInventarioService;

    //==========================================
    // CONSTRUCTOR
    //==========================================
    public InventoryMovementController(InventoryMovementService movimientoInventarioService) {
        this.movimientoInventarioService = movimientoInventarioService;
    }

    //==========================================
    // GET /api/movimientosinventario
    //==========================================
    @GetMapping()
    public ResponseEntity<List<InventoryMovement>> listar() {
        return ResponseEntity.ok(movimientoInventarioService.listar());
    }

    //==========================================
    // GET /api/movimientosinventario/{id}
    //==========================================
    @GetMapping("/{id}")
    public ResponseEntity<InventoryMovement> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(movimientoInventarioService.obtenerPorId(id));
    }

    //==========================================
    // POST /api/movimientosinventario
    //==========================================
    @PostMapping()
    public ResponseEntity<InventoryMovement> crear(@RequestBody InventoryMovement request) {
        InventoryMovement response = movimientoInventarioService.crear(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    //==========================================
    // PUT /api/movimientosinventario/{id}
    //==========================================
    @PutMapping("/{id}")
    public ResponseEntity<InventoryMovement> actualizar(@PathVariable Long id, @RequestBody InventoryMovement request) {
        InventoryMovement response = movimientoInventarioService.actualizar(id, request);
        return ResponseEntity.ok(response);
    }

    //==========================================
    // DELETE /api/movimientosinventario/{id}
    //==========================================
    @DeleteMapping("/{id}")
    public ResponseEntity<InventoryMovement> eliminar(@PathVariable Long id) {
        movimientoInventarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}