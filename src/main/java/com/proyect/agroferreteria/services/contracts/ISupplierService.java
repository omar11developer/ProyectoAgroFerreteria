package com.proyect.agroferreteria.services.contracts;

import com.proyect.agroferreteria.models.entity.Supplier;

import java.util.List;

public interface ISupplierService {
    public List<Supplier> findAll();
    public void save(Supplier supplier);

    public Supplier findById(Long id);

    public void delete(Long id);
}
