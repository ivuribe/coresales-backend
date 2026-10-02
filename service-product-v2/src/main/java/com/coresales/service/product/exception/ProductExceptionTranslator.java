package com.coresales.service.product.exception;

import org.springframework.stereotype.Component;
import java.sql.SQLException;

@Component
public class ProductExceptionTranslator {

    //==========================================
    // CÓDIGOS DE ERROR SQL SERVER
    //==========================================
    private static final int ERROR_CODIGO_DUPLICADO = 50010;
    private static final int ERROR_PRODUCTO_NO_EXISTE = 50011;
    private static final int ERROR_CODIGO_EN_USO = 50012;
    private static final int ERROR_PRODUCTO_NO_EXISTE_ELIMINAR = 50013;

    //==========================================
    // TRADUCIR ERROR
    //==========================================
    public RuntimeException translate(Throwable exception,String mensajeGenerico) {
        Integer codigoError = obtenerCodigoError(exception);

        if (exception  instanceof ProductException)
            return (RuntimeException) exception;

        //======================================
        // 50010
        //======================================
        if (codigoError != null && codigoError == ERROR_CODIGO_DUPLICADO) {
            return new BusinessException("Ya existe un producto con el código indicado.");
        }

        //======================================
        // 50011
        //======================================
        if (codigoError != null && codigoError == ERROR_PRODUCTO_NO_EXISTE) {
            return new ProductNotFoundException("El producto indicado no existe."
            );
        }

        //======================================
        // 50012
        //======================================
        if (codigoError != null && codigoError == ERROR_CODIGO_EN_USO) {
            return new BusinessException("El código ya pertenece a otro producto.");
        }

        //======================================
        // 50013
        //======================================
        if (codigoError != null && codigoError == ERROR_PRODUCTO_NO_EXISTE_ELIMINAR) {
            return new ProductNotFoundException("El producto indicado no existe.");
        }

        //======================================
        // ERROR NO CONTROLADO
        //======================================
        return new DataAccessException(mensajeGenerico,exception);
    }

    //==========================================
    // OBTENER CÓDIGO SQL SERVER
    //==========================================
    private Integer obtenerCodigoError(Throwable exception) {
        Throwable causa = exception;
        while (causa != null) {
            if (causa instanceof SQLException sqlException) {
                return sqlException.getErrorCode();
            }
            causa = causa.getCause();
        }
        return null;
    }
}