package com.proyect.agroferreteria.repository;

import com.proyect.agroferreteria.models.entity.Supplier;
import org.springframework.data.repository.CrudRepository;

public interface ISupplier extends CrudRepository<Supplier, Long> {
}
