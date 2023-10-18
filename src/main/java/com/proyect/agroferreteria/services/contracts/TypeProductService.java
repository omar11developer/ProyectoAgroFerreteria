package com.proyect.agroferreteria.services.contracts;

import com.proyect.agroferreteria.models.entity.TypeProduct;

import java.util.List;

public interface TypeProductService extends GenericoDAO<TypeProduct> {


    public TypeProduct getByName(String name);


}
