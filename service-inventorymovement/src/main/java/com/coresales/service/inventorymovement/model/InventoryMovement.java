package com.coresales.service.inventorymovement.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "MovimientoInventario")
@Data //agregar getters, setters y constructor sin parametro
public class InventoryMovement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MovimientoInventarioId")
    Long movimientoInventarioId;

    @Column(name = "ProductoId", nullable = false)
    Long productoId;

    @Column(name = "TipoMovimientoInventarioId", nullable = false)
    Integer tipoMovimientoInventarioId;

    @Column(name = "Cantidad", nullable = false)
    Integer cantidad;

    @Column(name = "StockAnterior", nullable = false)
    Integer stockAnterior;

    @Column(name = "StockPosterior", nullable = false)
    Integer stockPosterior;

    @Column(name = "Referencia", length = 100)
    String referencia;

    @Column(name = "Observacion", length = 250)
    String observacion;

    @Column(name="FechaMovimiento", nullable = false)
    LocalDateTime fechaMovimiento;

    @PrePersist
    public void prePersist() {
        fechaMovimiento = LocalDateTime.now();
    }
}
