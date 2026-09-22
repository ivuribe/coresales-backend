package com.coresales.service.product.exception;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ProductExceptionAspect {

    private final ProductExceptionTranslator exceptionTranslator;

    public ProductExceptionAspect(ProductExceptionTranslator exceptionTranslator) {
        this.exceptionTranslator = exceptionTranslator;
    }

    @AfterThrowing(
            pointcut = "execution(* com.coresales.service.product.repository..*(..))",
            throwing = "exception"
    )
    public void manejarExcepcion(Throwable exception) {
        throw exceptionTranslator.translate(
                exception,
                "Error al acceder a la información de productos."
        );
    }
}