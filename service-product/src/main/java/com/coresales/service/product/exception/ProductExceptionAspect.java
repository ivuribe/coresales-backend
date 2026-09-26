package com.coresales.service.product.exception;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ProductExceptionAspect {

    private final ProductExceptionTranslator exceptionTranslator;

    public ProductExceptionAspect(ProductExceptionTranslator exceptionTranslator) {
        this.exceptionTranslator = exceptionTranslator;
    }
    /**/
    @AfterThrowing(
            pointcut = "execution(* com.coresales.service.product.repository.ProductRepositoryCustomImpl.*(..))",
            throwing = "exception"
    )
    public void manejarExcepcion(Throwable exception) {
        throw exceptionTranslator.translate(
                exception,
                "Error al acceder a la información de productos."
        );
    }
    /**/
    /*
    @Around("execution(* com.coresales.service.product.repository.ProductRepositoryCustomImpl.*(..))")
    public Object manejarExcepcionConAround(ProceedingJoinPoint joinPoint){
        try{
            return joinPoint.proceed();
        }catch(ProductException exception){
            //excepción ya traducida
            throw exception;
        }catch(Throwable exception){
            throw exceptionTranslator.translate(
                    exception,
                    "Error al acceder a la información de productos."
            );
        }
    }
     */
}