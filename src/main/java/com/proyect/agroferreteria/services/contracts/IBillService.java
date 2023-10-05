package com.proyect.agroferreteria.services.contracts;

import com.proyect.agroferreteria.models.entity.Bill;

import java.util.List;

public interface IBillService {
    public List<Bill> findAll();
    public void save(Bill bill);
    public Bill findById(Long id);
    public void delete(Long id);


}
