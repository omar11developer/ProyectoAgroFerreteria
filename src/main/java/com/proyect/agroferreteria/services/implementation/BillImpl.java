package com.proyect.agroferreteria.services.implementation;

import com.proyect.agroferreteria.models.entity.Bill;
import com.proyect.agroferreteria.repository.BillRepository;
import com.proyect.agroferreteria.services.contracts.BillDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BillImpl extends GenericoImpl<Bill, BillRepository> implements BillDAO {
    @Autowired
    public BillImpl(BillRepository repository) {
        super(repository);
    }
}
