package com.proyect.agroferreteria.services.implementation;

import com.proyect.agroferreteria.models.entity.Inventories;
import com.proyect.agroferreteria.repository.InventoriesRepository;
import com.proyect.agroferreteria.services.contracts.InventoriesDAO;
import org.springframework.stereotype.Service;

@Service
public class InventoriesImpl extends GenericoImpl<Inventories,InventoriesRepository> implements InventoriesDAO {

    public InventoriesImpl(InventoriesRepository repository) {
        super(repository);
    }




}
