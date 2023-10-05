package com.proyect.agroferreteria.services.contracts;

import com.proyect.agroferreteria.models.entity.Product;

import java.util.List;

public interface IProductService {
    public List<Product> findAll();
    public void save(Product product);

    public Product findById(Long id);

    public void delete(Long id);
}
