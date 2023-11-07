package com.proyect.agroferreteria.controllers;

import com.proyect.agroferreteria.models.entity.ItemBill;
import com.proyect.agroferreteria.services.contracts.ItemBillDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Deprecated
@ConditionalOnProperty(prefix = "app", name = "controller.enable-dto", havingValue = "false")
@RestController
@RequestMapping("/agroferreteria/itemFactura")
public class ItemBillController extends GenericoController<ItemBill, ItemBillDAO> {
    @Autowired
    public ItemBillController(ItemBillDAO service) {
        super(service);
    }
}
