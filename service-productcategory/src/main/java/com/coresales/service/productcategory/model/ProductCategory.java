package com.coresales.service.productcategory.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "CategoriaProducto")
@Data //agregar getters, setters y constructor sin parametro
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CategoriaProductoId")
    Integer categoriaProductoId;

    @Column(name = "Nombre", length = 150, nullable = false)
    String nombre;

    @Column(name="Descripcion",length = 500, nullable = false)
    String descripcion;

    @Column(name="Activo")
    Boolean activo;
}
