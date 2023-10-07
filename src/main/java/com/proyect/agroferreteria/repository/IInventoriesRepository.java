package com.proyect.agroferreteria.repository;

import com.proyect.agroferreteria.models.entity.Inventories;
import org.springframework.data.repository.CrudRepository;

public interface IInventoriesRepository extends CrudRepository<Inventories, Long> {
}