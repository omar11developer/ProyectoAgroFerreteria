package com.proyect.agroferreteria.services.contracts;

import com.proyect.agroferreteria.models.entity.TypeProduct;

public interface TypeProductDAO extends GenericoDAO<TypeProduct> {


    public TypeProduct getByName(String name);
    boolean existeTypeProductName(String name);

}
