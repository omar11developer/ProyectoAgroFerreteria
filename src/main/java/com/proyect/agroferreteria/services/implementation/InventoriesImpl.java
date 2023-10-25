package com.proyect.agroferreteria.services.implementation;

import com.proyect.agroferreteria.models.entity.Inventories;
import com.proyect.agroferreteria.models.entity.TypeProduct;
import com.proyect.agroferreteria.repository.InventoriesRepository;
import com.proyect.agroferreteria.services.contracts.InventoriesDAO;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@Service
public class InventoriesImpl extends GenericoImpl<Inventories,InventoriesRepository> implements InventoriesDAO {

    public InventoriesImpl(InventoriesRepository repository) {
        super(repository);
    }

    @Override
    public Iterable<Inventories> obtenerTodosLosProductos() {
        return repository.obtenrTodosLosProductos();
    }


}
