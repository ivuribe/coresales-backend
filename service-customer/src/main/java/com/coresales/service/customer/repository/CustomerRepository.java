package com.coresales.service.customer.repository;

import com.coresales.service.customer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//@Repository //Aquí en la interface no es necesario poner @Repository porque ya está extendiendo a JpaRepository
public interface CustomerRepository extends JpaRepository<Customer, Long>, CustomerRepositoryCustom {
    Optional<Customer> findByNumeroDocumento(String numeroDocumento);
    boolean existsByNumeroDocumento(String numeroDocumento);
}