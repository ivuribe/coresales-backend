package com.coresales.service.user.auth.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "[User]")
@Data //para generar getters y setters
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "Username", length = 50, nullable = false)
    private String username;

    @Column(name = "Password", length = 200, nullable = false)
    private String password;

    @Column(name = "Role", length = 20, nullable = false)
    private String role;
}
