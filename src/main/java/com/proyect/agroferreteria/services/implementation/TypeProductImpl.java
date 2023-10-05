package com.proyect.agroferreteria.services.implementation;

import com.proyect.agroferreteria.models.entity.TypeProduct;
import com.proyect.agroferreteria.repository.ITypeProduct;
import com.proyect.agroferreteria.services.contracts.ITypeProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeProductImpl implements ITypeProductService {
    @Autowired
    private ITypeProduct typeProductRepository;
    @Override
    public List<TypeProduct> findAll() {
        return (List<TypeProduct>) typeProductRepository.findAll();
    }

    @Override
    public void save(TypeProduct typeProduct) {
        if(typeProduct != null){
            typeProductRepository.save(typeProduct);
        }
    }

    @Override
    public TypeProduct findById(Long id) {
        return typeProductRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        if(id > 0){
            typeProductRepository.deleteById(id);
        }
    }
}