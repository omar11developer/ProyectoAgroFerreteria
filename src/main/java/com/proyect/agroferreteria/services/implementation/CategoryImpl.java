package com.proyect.agroferreteria.services.implementation;

import com.proyect.agroferreteria.models.entity.Category;
import com.proyect.agroferreteria.repository.CategoryRepository;
import com.proyect.agroferreteria.services.contracts.CategoryDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryImpl extends GenericoImpl<Category, CategoryRepository> implements CategoryDAO {
    @Autowired
    public CategoryImpl(CategoryRepository repository) {
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
