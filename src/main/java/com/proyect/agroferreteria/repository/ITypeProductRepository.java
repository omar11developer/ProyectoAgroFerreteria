package com.proyect.agroferreteria.repository;

import com.proyect.agroferreteria.models.entity.TypeProduct;
import org.springframework.data.repository.CrudRepository;



public interface ITypeProductRepository extends CrudRepository<TypeProduct, Long> {
    public TypeProduct findByName(String name);
}
