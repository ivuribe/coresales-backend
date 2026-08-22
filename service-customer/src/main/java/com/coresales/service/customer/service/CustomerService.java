package com.coresales.service.customer.service;

import com.coresales.service.customer.model.Customer;
import java.util.List;

public interface CustomerService {
    List<Customer> listar();
    Customer obtenerPorId(Long id);
    Customer crear(Customer cliente);
    Customer actualizar(Long id, Customer cliente);
    void eliminar(Long id);
}
