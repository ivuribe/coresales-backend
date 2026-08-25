package com.coresales.service.productcategory.service;

import com.coresales.service.productcategory.model.Brand;
import com.coresales.service.productcategory.repository.BrandRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BrandServiceImpl implements BrandService {

    private final BrandRepository marcaRepository;

    //==========================================
    // CONSTRUCTOR
    //==========================================
    public BrandServiceImpl(BrandRepository marcaRepository) {
        this.marcaRepository = marcaRepository;
    }

    //==========================================
    // MÉTODOS
    //==========================================
    @Override
    @Transactional(readOnly = true)
    public List<Brand> listar(){
        return new ArrayList<>(marcaRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Brand obtenerPorId(Integer id) {
        return marcaRepository.findById(id).orElse(null);
    }

    @Override
    public Brand crear(Brand marca){
        Brand guardado = marcaRepository.save(marca);
        return guardado;
    }

    @Override
    public Brand actualizar(Integer id, Brand marca){
        Brand marcaBusqueda = obtenerPorId(id);
        if (marcaBusqueda == null) return null;
        Brand actualizado = marcaRepository.save(marca);
        return actualizado;
    }

    @Override
    public void eliminar(Integer id){
        marcaRepository.deleteById(id);
        //Product marca = obtenerPorId(id);
        //marcaRepository.delete(marca);
    }
}
