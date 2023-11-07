package com.proyect.agroferreteria.services.implementation;

import com.proyect.agroferreteria.models.entity.ItemBill;
import com.proyect.agroferreteria.repository.ItemBillRepository;
import com.proyect.agroferreteria.services.contracts.ItemBillDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemBillImpl extends GenericoImpl<ItemBill, ItemBillRepository> implements ItemBillDAO {


    public ItemBillImpl(ItemBillRepository repository) {
        super(repository);
    }
}
