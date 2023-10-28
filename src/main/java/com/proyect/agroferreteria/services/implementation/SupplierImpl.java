package com.proyect.agroferreteria.services.implementation;

import com.proyect.agroferreteria.models.entity.Supplier;
import com.proyect.agroferreteria.repository.SupplierRepository;
import com.proyect.agroferreteria.services.contracts.SupplierDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SupplierImpl extends GenericoImpl<Supplier, SupplierRepository> implements SupplierDAO {

    public SupplierImpl(SupplierRepository repository) {
        super(repository);
    }


    @Override
    public Iterable<Supplier> searchByNameLikeIgnoreCase(String name) {
        return repository.searchByNameLikeIgnoreCase(name);
    }

    @Override
    public boolean existsByName(String name) {
        return repository.existsByName(name);
    }
}
