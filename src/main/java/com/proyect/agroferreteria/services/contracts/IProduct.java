package com.proyect.agroferreteria.services.contracts;

import com.proyect.agroferreteria.models.entity.Product;
import com.proyect.agroferreteria.models.entity.TypeProduct;

import java.util.List;

public interface IProduct {
    public List<Product> findAll();
    public void save(Product product);

    public Product findOne(Long id);

    public void delete(Long id);
}
