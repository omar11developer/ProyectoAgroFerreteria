package com.proyect.agroferreteria.services.implementation;

import com.proyect.agroferreteria.models.entity.Category;
import com.proyect.agroferreteria.repository.TypeProductRepository;
import com.proyect.agroferreteria.services.contracts.TypeProductDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TypeProductImpl extends GenericoImpl<Category, TypeProductRepository> implements TypeProductDAO {
    @Autowired
    public TypeProductImpl(TypeProductRepository repository) {
        super(repository);
    }

    @Override
    @Transactional(readOnly = true)
    public Category getByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public boolean existeTypeProductName(String name) {
        return repository.existsTypeProductName(name);
    }
}
