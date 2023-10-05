package com.proyect.agroferreteria.services.contracts;

import com.proyect.agroferreteria.models.entity.ItemBill;

import java.util.List;

public interface IItemBillService {
    public List<ItemBill> findAll();
    public void save(ItemBill itemBill);
    public ItemBill findById(Long id);
    public void delete(Long id);
}
