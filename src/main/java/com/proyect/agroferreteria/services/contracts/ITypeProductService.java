package com.proyect.agroferreteria.services.contracts;

import com.proyect.agroferreteria.models.entity.Product;
import com.proyect.agroferreteria.models.entity.TypeProduct;

import java.util.List;

public interface ITypeProductService {
    public List<TypeProduct> findAll();
    public TypeProduct save(TypeProduct typeProduct) ;

    public TypeProduct findById(Long id);

    public void deleteById(Long id);

    public TypeProduct getByName(String name);


}
