package com.proyect.agroferreteria.services.implementation;

import com.proyect.agroferreteria.models.entity.Inventories;
import com.proyect.agroferreteria.repository.IInventories;
import com.proyect.agroferreteria.services.contracts.IInventoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoriesImpl implements IInventoriesService {
    @Autowired
    private IInventories inventoriesRepository;

    @Override
    public List<Inventories> findAll() {
        return (List<Inventories>) inventoriesRepository.findAll();
    }

    @Override
    public Inventories findById(Long id) {
        return inventoriesRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Inventories inventories) {
        if(inventories != null){
            inventoriesRepository.save(inventories);
        }
    }

    @Override
    public void delete(Long id) {
        if(id > 0){
            inventoriesRepository.deleteById(id);
        }
    }
}
