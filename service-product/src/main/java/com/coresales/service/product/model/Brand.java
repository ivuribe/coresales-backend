package com.coresales.service.product.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Marca")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MarcaId")
    private Integer marcaId;

    @Column(name = "Nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "Activo", nullable = false)
    private Boolean activo;
}
