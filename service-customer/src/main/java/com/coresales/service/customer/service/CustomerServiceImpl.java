package com.coresales.service.customer.service;

import com.coresales.service.customer.model.Customer;

import com.coresales.service.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository clienteRepository;

    //==========================================
    // CONSTRUCTOR
    //==========================================
    public CustomerServiceImpl(CustomerRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    //==========================================
    // MÉTODOS
    //==========================================
    @Override
    @Transactional(readOnly = true)
    public List<Customer> listar(){
        return new ArrayList<>(clienteRepository.listarClientes());
    }

    @Override
    @Transactional(readOnly = true)
    public Customer obtenerPorId(Long id){
        return clienteRepository.findById(id).orElseThrow(null);
    }

    @Override
    public Customer crear(Customer cliente){
        if (clienteRepository.existsByNumeroDocumento(cliente.getNumeroDocumento())) {
            throw new IllegalArgumentException(
                    "Ya existe un cliente con el número de documento indicado"
            );
        }

        if (cliente.getActivo() == null) {
            cliente.setActivo(true);
        }

        Customer guardado = clienteRepository.crearCliente(cliente);

        return guardado;
    }

    @Override
    public Customer actualizar(Long id, Customer cliente){
        Customer clienteBusqueda = obtenerPorId(id);

        if (!cliente.getNumeroDocumento().equals(clienteBusqueda.getNumeroDocumento
                ())
                &&
                clienteRepository.existsByNumeroDocumento(cliente.getNumeroDocumento())
        ){
            throw new IllegalArgumentException(
                    "El número de documento ya pertenece a otro cliente"
            );
        }
        cliente.setFechaRegistro(clienteBusqueda.getFechaRegistro());
        Customer actualizado = clienteRepository.actualizarCliente(id,cliente);
        return actualizado;
    }

    @Override
    public void eliminar(Long id){
        clienteRepository.eliminarCliente(id);

        //Customer cliente = obtenerPorId(id);
        //clienteRepository.delete(cliente);
    }
}
