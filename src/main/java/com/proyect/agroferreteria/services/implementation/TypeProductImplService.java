package com.proyect.agroferreteria.services.implementation;

import com.proyect.agroferreteria.models.entity.TypeProduct;
import com.proyect.agroferreteria.repository.ITypeProduct;
import com.proyect.agroferreteria.services.contracts.ITypeProductService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeProductImplService implements ITypeProductService {
    private ITypeProduct typeProduct;
    @Override
    public List<TypeProduct> findAll() {
        return null;
    }

    @Override
    public void save(TypeProduct typeProduct) {

    }

    @Override
    public TypeProduct findById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
