package com.proyect.agroferreteria.services.implementation;

import com.proyect.agroferreteria.models.entity.Supplier;
import com.proyect.agroferreteria.repository.SupplierRepository;
import com.proyect.agroferreteria.services.contracts.SupplierDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SupplierImpl implements SupplierDAO {
    @Autowired
    private SupplierRepository supplierRepository;
    @Override
    @Transactional(readOnly = true)
    public List<Supplier> findAll() {
        return (List<Supplier>) supplierRepository.findAll();
    }

    @Override
    @Transactional
    public void save(Supplier supplier) {
        if(supplier != null){
            supplierRepository.save(supplier);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Supplier findById(Long id) {
        return supplierRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if(id > 0){
            supplierRepository.deleteById(id);
        }
    }
}
