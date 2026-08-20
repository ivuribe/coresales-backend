package com.coresales.service.customer.repository;

import com.coresales.service.customer.model.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;

import java.util.List;

public class CustomerRepositoryCustomImpl implements CustomerRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager ;

    @Override
    public List<Customer> listarClientes(){
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("usp_Cliente_Listar",Customer.class);
        query.execute();
        return query.getResultList();
    }

    public Customer findByCodigo(Long codigo){
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("usp_Cliente_ObtenerPorId",Customer.class);
        query.registerStoredProcedureParameter("ClienteId", Long.class, ParameterMode.IN);
        query.setParameter("ClienteId", codigo);
        query.execute();
        List<Customer> listClientes = query.getResultList();

        return listClientes.isEmpty() ? null : listClientes.get(0);
    }

    public Customer crearCliente(Customer cliente){
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("usp_Cliente_Insertar",Customer.class);
        //Registramos todos los parámetros que necesita recibir el Stored Procedure
        query.registerStoredProcedureParameter("TipoDocumentoId", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("NumeroDocumento", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("Nombres", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("Apellidos", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("RazonSocial", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("Email", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("Telefono", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("Direccion", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("Activo", Boolean.class, ParameterMode.IN);

        //Seteamos los valores para cada parámetro
        query.setParameter("TipoDocumentoId", cliente.getTipoDocumento());
        query.setParameter("NumeroDocumento", cliente.getNumeroDocumento());
        query.setParameter("Nombres", cliente.getNombres());
        query.setParameter("Apellidos", cliente.getApellidos());
        query.setParameter("RazonSocial", cliente.getRazonSocial());
        query.setParameter("Email", cliente.getEmail());
        query.setParameter("Telefono", cliente.getTelefono());
        query.setParameter("Direccion", cliente.getDireccion());
        query.setParameter("Activo", cliente.getActivo());

        query.execute();
        List<Customer> listClientes = query.getResultList();
        return listClientes.isEmpty() ? null : listClientes.get(0);
    }

    public Customer actualizarCliente(Long id, Customer cliente){
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("usp_Cliente_Actualizar",Customer.class);
        //Registramos todos los parámetros que necesita recibir el Stored Procedure
        query.registerStoredProcedureParameter("ClienteId", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("TipoDocumentoId", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("NumeroDocumento", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("Nombres", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("Apellidos", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("RazonSocial", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("Email", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("Telefono", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("Direccion", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("Activo", Boolean.class, ParameterMode.IN);

        //Seteamos los valores para cada parámetro
        query.setParameter("ClienteId", cliente.getId());
        query.setParameter("TipoDocumentoId", cliente.getTipoDocumento());
        query.setParameter("NumeroDocumento", cliente.getNumeroDocumento());
        query.setParameter("Nombres", cliente.getNombres());
        query.setParameter("Apellidos", cliente.getApellidos());
        query.setParameter("RazonSocial", cliente.getRazonSocial());
        query.setParameter("Email", cliente.getEmail());
        query.setParameter("Telefono", cliente.getTelefono());
        query.setParameter("Direccion", cliente.getDireccion());
        query.setParameter("Activo", cliente.getActivo());

        query.execute();
        List<Customer> listClientes = query.getResultList();
        return listClientes.isEmpty() ? null : listClientes.get(0);
    }

    public void eliminarCliente(Long id){
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("usp_Cliente_Eliminar",Customer.class);

        query.registerStoredProcedureParameter("ClienteId", Integer.class, ParameterMode.IN);
        query.setParameter("ClienteId", id);

        query.execute();
        //List<Customer> listClientes = query.getResultList();
        //return listClientes.isEmpty() ? null : listClientes.get(0);
    }

}