package com.proyect.agroferreteria.repository;

import com.proyect.agroferreteria.models.entity.Supplier;
import org.springframework.data.repository.CrudRepository;

public interface ISupplierRepository extends CrudRepository<Supplier, Long> {
}
