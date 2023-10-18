package com.proyect.agroferreteria.services.implementation;

import com.proyect.agroferreteria.models.entity.ItemBill;
import com.proyect.agroferreteria.repository.ItemBillRepository;
import com.proyect.agroferreteria.services.contracts.ItemBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemBillImpl implements ItemBillService {
    @Autowired
    private ItemBillRepository itemBillRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ItemBill> findAll() {
        return (List<ItemBill>) itemBillRepository.findAll();
    }

    @Override
    @Transactional
    public void save(ItemBill itemBill) {
        if(itemBill != null){
            itemBillRepository.save(itemBill);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ItemBill findById(Long id) {
        return itemBillRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if(id > 0){
            itemBillRepository.deleteById(id);
        }
    }
}
