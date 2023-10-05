package com.proyect.agroferreteria.services.contracts;

import com.proyect.agroferreteria.models.entity.Inventories;

import java.util.List;

public interface IInventoriesService {
    public List<Inventories> findAll();
    public Inventories findById(Long id);
    public void save(Inventories inventories);
    public void delete(Long id);

}
