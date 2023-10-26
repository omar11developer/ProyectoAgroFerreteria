package com.proyect.agroferreteria.repository;

import com.proyect.agroferreteria.models.entity.Inventories;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface InventoriesRepository extends CrudRepository<Inventories, Long> {

    @Query("select i from Inventories i join fetch i.supplier s join fetch i.product p")
    Iterable<Inventories> obtnerInventarioConProductos();

}
