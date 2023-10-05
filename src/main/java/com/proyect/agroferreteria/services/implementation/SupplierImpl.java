package com.proyect.agroferreteria.services.implementation;

import com.proyect.agroferreteria.models.entity.Supplier;
import com.proyect.agroferreteria.repository.ISupplier;
import com.proyect.agroferreteria.services.contracts.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierImpl implements ISupplierService {
    @Autowired
    private ISupplier supplierRepository;
    @Override
    public List<Supplier> findAll() {
        return (List<Supplier>) supplierRepository.findAll();
    }

    @Override
    public void save(Supplier supplier) {
        if(supplier != null){
            supplierRepository.save(supplier);
        }
    }

    @Override
    public Supplier findById(Long id) {
        return supplierRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        if(id > 0){
            supplierRepository.deleteById(id);
        }
    }
}
