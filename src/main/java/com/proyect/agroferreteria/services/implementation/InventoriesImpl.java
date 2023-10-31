package com.proyect.agroferreteria.services.implementation;

import com.proyect.agroferreteria.models.entity.Inventories;
import com.proyect.agroferreteria.repository.InventoriesRepository;
import com.proyect.agroferreteria.services.contracts.InventoriesDAO;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class InventoriesImpl extends GenericoImpl<Inventories,InventoriesRepository> implements InventoriesDAO {

    public InventoriesImpl(InventoriesRepository repository) {
        super(repository);
    }


    @Override
    public Iterable<Inventories> orderByDate() {
        return repository.orderByDate();
    }

    @Override
    public Iterable<Inventories> buscarInventarioPorFecha(Date date) {
        return repository.buscarInventarioPorFecha(date);
    }

    @Override
    public Iterable<Inventories> buscarInvetarioConStokBajos() {
        return repository.buscarInvetarioConStokBajos();
    }
}
