package com.proyect.agroferreteria.repository;

import com.proyect.agroferreteria.models.entity.Product;
import com.proyect.agroferreteria.models.entity.Supplier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SupplierRepository extends CrudRepository<Supplier, Long> {
    /*@Query("select p from Product  p where p.name like %?1%")
    Iterable<Product> buscarProductoPorNombre(String name);*/

    @Query("select s from Supplier s where s.name like %?1%")
    Iterable<Supplier> searchByNameLikeIgnoreCase(String name);

    boolean existsByName(String name);

    Supplier findByName(String name);
}
