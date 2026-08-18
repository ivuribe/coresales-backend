package com.coresales.service.customer.service;

import com.coresales.service.customer.model.Customer;

import com.coresales.service.customer.repository.ICustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerServiceImpl implements ICustomerService{

    private final ICustomerRepository clienteRepository;

    //==========================================
    // CONSTRUCTOR
    //==========================================
    public CustomerServiceImpl(ICustomerRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    //==========================================
    // MÉTODOS
    //==========================================
    @Override
    @Transactional(readOnly = true)
    public List<Customer> listar(){
        return new ArrayList<>(clienteRepository.findAll());
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

        Customer guardado = clienteRepository.save(cliente);

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
        Customer actualizado = clienteRepository.save(cliente);
        return actualizado;
    }

    @Override
    public void eliminar(Long id){
        clienteRepository.deleteById(id);

        //Customer cliente = obtenerPorId(id);
        //clienteRepository.delete(cliente);
    }
}
