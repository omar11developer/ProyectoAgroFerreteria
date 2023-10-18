package com.proyect.agroferreteria.services.implementation;

import com.proyect.agroferreteria.models.entity.TypeProduct;
import com.proyect.agroferreteria.repository.TypeProductRepository;
import com.proyect.agroferreteria.services.contracts.TypeProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypeProductImpl implements TypeProductService {
    @Autowired
    private TypeProductRepository typeProductRepository;
    @Override
    @Transactional(readOnly = true)
    public List<TypeProduct> findAll() {
        return (List<TypeProduct>) typeProductRepository.findAll();
    }

    @Override
    @Transactional
    public TypeProduct save(TypeProduct typeProduct) {

        return typeProductRepository.save(typeProduct);
    }


    @Override
    @Transactional(readOnly = true)
    public TypeProduct findById(Long id) {
        return typeProductRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {

            typeProductRepository.deleteById(id);

    }

    @Override
    public TypeProduct getByName(String name) {
        return typeProductRepository.findByName(name);
    }
}
