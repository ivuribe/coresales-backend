package com.coresales.service.product.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Producto")
@Data //agregar getters, setters y constructor sin parametro
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProductoId")
    Long productoId;

    @Column(name = "Codigo", length = 30, nullable = false)
    String codigo;

    @Column(name = "Nombre", length = 150, nullable = false)
    String nombre;

    @Column(name="Descripcion",length = 500, nullable = false)
    String descripcion;

    @Column(name="CategoriaProductoId", nullable = false)
    Integer categoriaProductoId;

    @Column(name="marcaId")
    Integer marcaId;

    @ManyToOne(fetch = FetchType.EAGER) //LAZY = carga ociosa y EAGER = carga anciosa
    @JoinColumn(name = "CategoriaProductoId", nullable = false, insertable = false, updatable = false)
    private ProductCategory categoriaProducto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MarcaId", insertable = false, updatable = false)
    private Brand marca;

    @Column(name="PrecioCompra", precision = 12, scale = 2)
    BigDecimal precioCompra;

    @Column(name="PrecioVenta", precision = 12, scale = 2)
    BigDecimal precioVenta;

    @Column(name="StockMinimo")
    Integer stockMinimo;

    @Column(name="Activo")
    Boolean activo;

    @Column(name="FechaRegistro")
    LocalDateTime fechaRegistro;

    @PrePersist
    public void prePersist() {
        fechaRegistro = LocalDateTime.now();
    }
}
