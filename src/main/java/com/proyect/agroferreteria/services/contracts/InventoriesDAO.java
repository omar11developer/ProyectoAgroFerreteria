package com.proyect.agroferreteria.services.contracts;

import com.proyect.agroferreteria.models.entity.Inventories;

public interface InventoriesDAO extends GenericoDAO<Inventories> {

    Iterable<Inventories> obtnerInventarioConProductos();

}
