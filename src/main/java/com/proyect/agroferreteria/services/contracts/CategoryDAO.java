package com.proyect.agroferreteria.services.contracts;

import com.proyect.agroferreteria.models.entity.Category;

public interface CategoryDAO extends GenericoDAO<Category> {


    public Category getByName(String name);
    boolean existeTypeProductName(String name);

}
