package com.proyect.agroferreteria.services.contracts;

import com.proyect.agroferreteria.models.entity.Supplier;

import java.util.List;

public interface SupplierDAO extends GenericoDAO<Supplier> {
    Iterable<Supplier> searchByNameLikeIgnoreCase(String name);
    boolean existsByName(String name);
}
