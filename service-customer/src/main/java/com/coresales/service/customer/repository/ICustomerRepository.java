package com.coresales.service.customer.repository;

import com.coresales.service.customer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//@Repository //Aquí en la interface no es necesario poner @Repository porque ya está extendiendo a JpaRepository
public interface ICustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByNumeroDocumento(String numeroDocumento);
    boolean existsByNumeroDocumento(String numeroDocumento);
}
