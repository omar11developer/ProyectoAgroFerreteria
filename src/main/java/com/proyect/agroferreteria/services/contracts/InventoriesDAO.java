package com.proyect.agroferreteria.services.contracts;

import com.proyect.agroferreteria.models.entity.Inventories;

import java.util.Optional;

public interface InventoriesDAO extends GenericoDAO<Inventories> {

    Iterable<Inventories> obtnerInventarioConProductos();
    Optional<Inventories> obtnerInventarioById(Long id);
}
