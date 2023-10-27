package com.proyect.agroferreteria.repository;

import com.proyect.agroferreteria.models.entity.Inventories;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface InventoriesRepository extends CrudRepository<Inventories, Long> {

    @Query("select  i from Inventories i join fetch i.supplier s join fetch i.product p order by i.id_Inventory asc")
    Iterable<Inventories> obtnerInventarioConProductos();
    @Query("select i from Inventories i join fetch i.supplier s join fetch i.product p where i.id_Inventory  = ?1")
    Optional<Inventories> obtnerInventarioById(Long id);
}
