package com.coresales.service.customer.repository;

import com.coresales.service.customer.model.Customer;

import java.util.List;

public interface ICustomerRepositoryCustom {
    List<Customer> listarClientes();
    Customer buscarCliente(Long id);
    //Customer crearCliente(Customer cliente);
    //Customer actualizarCliente(Long id, Customer cliente);
    //void eliminarCliente(Long id);
}
