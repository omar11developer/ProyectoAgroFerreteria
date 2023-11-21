package com.proyect.agroferreteria.services.implementation;

import com.proyect.agroferreteria.models.entity.Inventories;
import com.proyect.agroferreteria.models.entity.ItemBill;
import com.proyect.agroferreteria.repository.InventoriesRepository;
import com.proyect.agroferreteria.services.contracts.InventoriesDAO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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

/*    @Override
    @Transactional
    public void deleteById(Long id) {
        Optional<Inventories> inventarioOptional = repository.findById(id);
        if(inventarioOptional.isPresent()){
            Inventories inventories = inventarioOptional.get();

            inventories.getItemBill().forEach(itemBill -> itemBill.setInventories(null));

            super.deleteById(id);
        }
    }*/

}
