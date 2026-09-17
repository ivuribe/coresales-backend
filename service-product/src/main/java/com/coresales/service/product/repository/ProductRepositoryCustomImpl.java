package com.coresales.service.product.repository;

import com.coresales.service.product.model.Product;
import com.coresales.service.product.model.ProductCategory;
import jakarta.persistence.*;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ProductRepositoryCustomImpl implements ProductRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Product> listarProductos(){
        System.out.println("PASO 3: REPOSITORY LISTAR PRODUCTOS");
        StoredProcedureQuery query =
                entityManager.createStoredProcedureQuery("usp_Producto_Listar"/*,Product.class*/);
        query.execute();
        //List<Product> lstProduct = query.getResultList();
        //return lstProduct;

        //Haciendo el mapeo campo por campo
        List<Object[]> resultado = query.getResultList();
        List<Product> lstProductos = resultado.stream().map(row -> {
            Product product = new Product();
            product.setProductoId((Long) row[0]);
            product.setCodigo((String) row[1]);
            product.setNombre((String) row[2]);
            product.setDescripcion((String) row[3]);
            product.setCategoriaProductoId((Integer) row[4]);

            //Para mapear el nombre de la categoría creamos el objeto productCategory
            ProductCategory pc = new ProductCategory();
            pc.setNombre((String) row[5]);
            product.setCategoriaProducto(pc);

            product.setMarcaId((Integer) row[6]);
            product.setPrecioCompra((BigDecimal) row[8]);
            product.setPrecioVenta((BigDecimal) row[9]);
            product.setStockMinimo((Integer) row[10]);
            product.setStockActual((Integer) row[11]); //este campo tiene el @Transient
            product.setActivo((Boolean) row[12]);
            product.setFechaRegistro((LocalDateTime) row[13]);

            return product;
        }).toList();

        return lstProductos;
    }

    @Override
    public Product obtenerProductoPorId(Long codigo) {
        StoredProcedureQuery query =
                entityManager.createStoredProcedureQuery("usp_Producto_ObtenerPorId",Product.class);
        query.registerStoredProcedureParameter("ProductoId", Long.class, ParameterMode.IN);
        query.setParameter("ProductoId", codigo);
        query.execute();
        List<Product> listProductos = query.getResultList();
        return listProductos.isEmpty() ? null : listProductos.get(0);
    }

    @Override
    public Product insertarProducto(Product product) {
        StoredProcedureQuery query =
                entityManager.createStoredProcedureQuery("dbo.usp_Producto_Insertar", Product.class);
        //Registramos todos los parámetros que necesita recibir el Stored Procedure
        query.registerStoredProcedureParameter("Codigo", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("Nombre", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("Descripcion", String.class,ParameterMode.IN);
        query.registerStoredProcedureParameter("CategoriaProductoId", Integer.class,ParameterMode.IN);
        query.registerStoredProcedureParameter("MarcaId", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("PrecioCompra", BigDecimal.class,ParameterMode.IN);
        query.registerStoredProcedureParameter("PrecioVenta", BigDecimal.class,ParameterMode.IN);
        query.registerStoredProcedureParameter("StockMinimo", Integer.class,ParameterMode.IN);
        query.registerStoredProcedureParameter("StockInicial", Integer.class,ParameterMode.IN);
        query.registerStoredProcedureParameter("Activo", Boolean.class, ParameterMode.IN);

        //Seteamos los valores para cada parámetro
        query.setParameter("Codigo", product.getCodigo());
        query.setParameter("Nombre", product.getNombre());
        query.setParameter("Descripcion", product.getDescripcion());
        query.setParameter("CategoriaProductoId", product.getCategoriaProductoId());
        query.setParameter("MarcaId", product.getMarcaId());
        query.setParameter("PrecioCompra", product.getPrecioCompra());
        query.setParameter("PrecioVenta", product.getPrecioVenta());
        query.setParameter("StockMinimo", product.getStockMinimo());
        query.setParameter("StockInicial",product.getStockActual() != null? product.getStockActual(): 0);
        query.setParameter("Activo", product.getActivo()!= null ? product.getActivo() : true);

        query.execute();
        List<Product> listProductos = query.getResultList();
        return listProductos.isEmpty() ? null : listProductos.get(0);
    }

    @Override
    public Product actualizarProducto(Product product) {
        StoredProcedureQuery query =
                entityManager.createStoredProcedureQuery("dbo.usp_Producto_Actualizar", Product.class);
        //Registramos todos los parámetros que necesita recibir el Stored Procedure
        query.registerStoredProcedureParameter("ProductoId", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("Codigo", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("Nombre", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("Descripcion", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("CategoriaProductoId", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("MarcaId", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("PrecioCompra", BigDecimal.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("PrecioVenta", BigDecimal.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("StockMinimo", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("Activo", Boolean.class, ParameterMode.IN);

        //Seteamos los valores para cada parámetro
        query.setParameter("ProductoId", product.getProductoId());
        query.setParameter("Codigo", product.getCodigo());
        query.setParameter("Nombre", product.getNombre());
        query.setParameter("Descripcion", product.getDescripcion());
        query.setParameter("CategoriaProductoId", product.getCategoriaProductoId());
        query.setParameter("MarcaId", product.getMarcaId());
        query.setParameter("PrecioCompra", product.getPrecioCompra());
        query.setParameter("PrecioVenta", product.getPrecioVenta());
        query.setParameter("StockMinimo", product.getStockMinimo());
        query.setParameter("Activo", product.getActivo()!= null ? product.getActivo() : true);

        query.execute();
        List<Product> listProductos = query.getResultList();
        return listProductos.isEmpty() ? null : listProductos.get(0);
    }

    @Override
    public void eliminarProducto(Long id) {
        StoredProcedureQuery query =
                entityManager.createStoredProcedureQuery("usp_Producto_Eliminar");
        query.registerStoredProcedureParameter("ProductoId", Long.class, ParameterMode.IN);
        query.setParameter("ProductoId", id);
        query.execute();
    }
}
