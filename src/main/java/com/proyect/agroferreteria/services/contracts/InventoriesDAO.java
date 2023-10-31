package com.proyect.agroferreteria.services.contracts;

import com.proyect.agroferreteria.models.entity.Inventories;

import java.util.Date;
import java.util.Optional;

public interface InventoriesDAO extends GenericoDAO<Inventories> {

    public  Iterable<Inventories> orderByDate();

    public  Iterable<Inventories> buscarInventarioPorFecha(Date date);
    public Iterable<Inventories> buscarInvetarioConStokBajos();

}
