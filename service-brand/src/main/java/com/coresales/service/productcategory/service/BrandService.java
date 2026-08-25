package com.coresales.service.productcategory.service;

import com.coresales.service.productcategory.model.Brand;

import java.util.List;

public interface BrandService {
    List<Brand> listar();
    Brand obtenerPorId(Integer id);
    Brand crear(Brand marca);
    Brand actualizar(Integer id, Brand marca);
    void eliminar(Integer id);
}
