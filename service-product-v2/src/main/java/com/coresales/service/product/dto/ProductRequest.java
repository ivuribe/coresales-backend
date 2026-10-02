package com.coresales.service.product.dto;

import com.coresales.service.product.model.Brand;
import com.coresales.service.product.model.ProductCategory;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data //agregar getters, setters y constructor sin parametro
public class ProductRequest {
    Long productoId;
    String codigo;
    String nombre;
    String descripcion;
    Integer categoriaProductoId;
    String categoria;
    Integer marcaId;
    String marca;
    BigDecimal precioCompra;
    BigDecimal precioVenta;
    Integer stockMinimo;
    Integer stockActual;
    Boolean activo;
    LocalDateTime fechaRegistro;
}
