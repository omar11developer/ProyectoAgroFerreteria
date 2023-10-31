package com.proyect.agroferreteria.repository;

import com.proyect.agroferreteria.models.entity.Inventories;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

public interface InventoriesRepository extends CrudRepository<Inventories, Long> {



    @Query("select i from Inventories i order by i.createAtOrder desc")
    Iterable<Inventories> orderByDate();

    @Query("select i from Inventories i where i.createAtOrder = ?1")
    Iterable<Inventories> buscarInventarioPorFecha(Date date);

    @Query("select  i from Inventories  i where i.stock<10")
    Iterable<Inventories> buscarInvetarioConStokBajos();


}
