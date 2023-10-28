package com.proyect.agroferreteria.services.implementation;

import com.proyect.agroferreteria.models.entity.Inventories;
import com.proyect.agroferreteria.models.entity.TypeProduct;
import com.proyect.agroferreteria.repository.InventoriesRepository;
import com.proyect.agroferreteria.services.contracts.InventoriesDAO;
import org.springframework.dao.DataAccessException;
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
    @Transactional(readOnly = true)
    public Iterable<Inventories> obtnerInventarioConProductos() {
        return repository.obtnerInventarioConProductos();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Inventories> obtnerInventarioById(Long id) {
        return repository.obtnerInventarioById(id);
    }

    @Override
    public void deleteInvetario(Long id) {
        repository.deleteById(id);
    }


}
