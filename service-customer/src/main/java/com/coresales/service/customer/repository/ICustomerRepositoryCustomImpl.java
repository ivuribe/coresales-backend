package com.coresales.service.customer.repository;

import com.coresales.service.customer.model.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;

import java.util.List;

public class ICustomerRepositoryCustomImpl implements ICustomerRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager ;

    @Override
    public List<Customer> listarClientes(){
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("usp_Cliente_Listar",Customer.class);
        query.execute();
        return query.getResultList();
    }

    public Customer buscarCliente(Long id){
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("usp_Cliente_ObtenerPorId",Customer.class);
        query.setParameter("ClienteId", id);
        query.execute();
        List<Customer> listClientes = query.getResultList();

        return listClientes.isEmpty() ? null : listClientes.get(0);
    }
    /*
    public Customer crearCliente(Customer cliente){

    }
    public Customer actualizarCliente(Long id, Customer cliente){

    }
    public void eliminarCliente(Long id){

    }
     */
}
