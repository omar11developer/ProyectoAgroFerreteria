package com.proyect.agroferreteria.repository;

import com.proyect.agroferreteria.models.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface IProduct extends CrudRepository<Product, Long> {
}
