package com.proyect.agroferreteria.services.contracts;

import com.proyect.agroferreteria.models.entity.Supplier;
import com.proyect.agroferreteria.models.entity.TypeProduct;

import java.util.List;

public interface ISupplier {
    public List<Supplier> findAll();
    public void save(Supplier supplier);

    public Supplier findOne(Long id);

    public void delete(Long id);
}
