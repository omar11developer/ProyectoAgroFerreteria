package com.proyect.agroferreteria.controllers;

import com.proyect.agroferreteria.models.entity.Product;
import com.proyect.agroferreteria.services.contracts.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductoController {
    @Autowired
    private IProductService productService;
    @GetMapping("/")
    public List<Product> getProducts(){
        return productService.findAll();
    }

}
