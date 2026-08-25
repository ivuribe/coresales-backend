package com.coresales.service.inventorymovement.service;

import com.coresales.service.inventorymovement.model.InventoryMovement;

import java.util.List;

public interface InventoryMovementService {
    List<InventoryMovement> listar();
    InventoryMovement obtenerPorId(Long id);
    InventoryMovement crear(InventoryMovement movimientoInventario);
    InventoryMovement actualizar(Long id, InventoryMovement movimientoInventario);
    void eliminar(Long id);
}
