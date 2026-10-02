package com.coresales.service.product.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "CategoriaProducto")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CategoriaProductoId")
    private Integer categoriaProductoId;

    @Column(name = "Nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "Descripcion", length = 500)
    private String descripcion;

    @Column(name = "Activo", nullable = false)
    private Boolean activo;
}