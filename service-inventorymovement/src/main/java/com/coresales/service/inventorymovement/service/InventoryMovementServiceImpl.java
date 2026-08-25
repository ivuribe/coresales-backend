package com.coresales.service.inventorymovement.service;

import com.coresales.service.inventorymovement.model.InventoryMovement;
import com.coresales.service.inventorymovement.repository.InventoryMovementRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class InventoryMovementServiceImpl implements InventoryMovementService {

    private final InventoryMovementRepository movimientoInventarioRepository;

    //==========================================
    // CONSTRUCTOR
    //==========================================
    public InventoryMovementServiceImpl(InventoryMovementRepository movimientoInventarioRepository) {
        this.movimientoInventarioRepository = movimientoInventarioRepository;
    }

    //==========================================
    // MÉTODOS
    //==========================================
    @Override
    @Transactional(readOnly = true)
    public List<InventoryMovement> listar(){
        return new ArrayList<>(movimientoInventarioRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public InventoryMovement obtenerPorId(Long id) {
        return movimientoInventarioRepository.findById(id).orElse(null);
    }

    @Override
    public InventoryMovement crear(InventoryMovement movimientoInventario){
        InventoryMovement guardado = movimientoInventarioRepository.save(movimientoInventario);
        return guardado;
    }

    @Override
    public InventoryMovement actualizar(Long id, InventoryMovement movimientoInventario){
        InventoryMovement movimientoInventarioBusqueda = obtenerPorId(id);
        if (movimientoInventarioBusqueda == null) return null;
        InventoryMovement actualizado = movimientoInventarioRepository.save(movimientoInventario);
        return actualizado;
    }

    @Override
    public void eliminar(Long id){
        movimientoInventarioRepository.deleteById(id);
        //Product movimientoInventario = obtenerPorId(id);
        //movimientoInventarioRepository.delete(movimientoInventario);
    }
}
