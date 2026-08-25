package com.coresales.service.inventorymovement.repository;

import com.coresales.service.inventorymovement.model.InventoryMovement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryMovementRepository extends JpaRepository<InventoryMovement, Long> {
}
