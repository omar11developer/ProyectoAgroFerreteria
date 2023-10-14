package com.proyect.agroferreteria.controllers;

import com.proyect.agroferreteria.models.entity.Product;
import com.proyect.agroferreteria.services.contracts.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/agroferreteria")
public class ProductController {
    @Autowired
    private IProductService productService;

    @GetMapping("/productos")
    public ResponseEntity<?> getProducts(){
        List<Product> products = new ArrayList<>();
        Map<String, Object> response = new HashMap<>();

        try {
            products = productService.findAll();
        }catch (DataAccessException e){
            response.put("Mensaje","Error al listar");
            response.put("Error",e.getMessage());
            return  new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (products.isEmpty()){
            response.put("Mensaje","No se econtraron productos");
            return  new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    @GetMapping("/productos/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id){
        Product product = null;
        Map<String, Object> response = new HashMap<>();
        try {
            product=productService.findById(id);
        }catch (DataAccessException e){
            response.put("Mensaje","Error al buscar producto");
            response.put("Error", e.getMessage());
            return  new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (product == null){
            response.put("Mensaje: ", "El id no existe");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }
    @GetMapping("/productos/TypeProduct/{typeProduct}")
    public ResponseEntity<?> getProductByTypeProduct(@PathVariable String typeProduct){
        List<Product> products = new ArrayList<>();
        Map<String, Object> response = new HashMap<>();
        try{
            products = productService.getProductByTypeProduct(typeProduct);
        }catch (DataAccessException e){
            response.put("Mensaje: ", "Error al obtener los productos");
            response.put("Error: " , e.getMessage());
            return  new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (products.isEmpty()){
            response.put("Mensaje: ", "Esta categoria no tiene productos");
            return  new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }


}
