package com.proyect.agroferreteria.controllers;

import com.proyect.agroferreteria.models.entity.Bill;
import com.proyect.agroferreteria.services.contracts.BillDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Deprecated
@RestController
@RequestMapping("/agroferreteria/factura")
@ConditionalOnProperty(prefix = "app", name = "controller.enable-dto", havingValue = "false")
public class BillController extends GenericoController<Bill, BillDAO> {
    @Autowired
    public BillController(BillDAO service) {
        super(service);
    }

}
