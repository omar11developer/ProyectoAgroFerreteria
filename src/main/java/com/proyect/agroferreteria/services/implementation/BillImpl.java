package com.proyect.agroferreteria.services.implementation;

import com.proyect.agroferreteria.models.entity.Bill;
import com.proyect.agroferreteria.repository.IBill;
import com.proyect.agroferreteria.services.contracts.IBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BillImpl implements IBillService {
    @Autowired
    private IBill billRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Bill> findAll() {
        return (List<Bill>) billRepository.findAll();
    }

    @Override
    @Transactional
    public void save(Bill bill) {
        if(bill != null){
            billRepository.save(bill);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Bill findById(Long id) {
        return billRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if(id > 0){
            billRepository.deleteById(id);
        }
    }
}
