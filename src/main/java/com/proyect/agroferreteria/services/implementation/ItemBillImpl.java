package com.proyect.agroferreteria.services.implementation;

import com.proyect.agroferreteria.models.entity.ItemBill;
import com.proyect.agroferreteria.repository.IItemBill;
import com.proyect.agroferreteria.services.contracts.IItemBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemBillImpl implements IItemBillService {
    @Autowired
    private IItemBill itemBillRepository;

    @Override
    public List<ItemBill> findAll() {
        return (List<ItemBill>) itemBillRepository.findAll();
    }

    @Override
    public void save(ItemBill itemBill) {
        if(itemBill != null){
            itemBillRepository.save(itemBill);
        }
    }

    @Override
    public ItemBill findById(Long id) {
        return itemBillRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        if(id > 0){
            itemBillRepository.deleteById(id);
        }
    }
}
