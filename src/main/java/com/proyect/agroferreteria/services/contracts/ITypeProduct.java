package com.proyect.agroferreteria.services.contracts;

import com.proyect.agroferreteria.models.entity.Product;
import com.proyect.agroferreteria.models.entity.TypeProduct;

import java.util.List;

public interface ITypeProduct {
    public List<TypeProduct> findAll();
    public void save(TypeProduct typeProduct);

    public TypeProduct findOne(Long id);

    public void delete(Long id);


}
