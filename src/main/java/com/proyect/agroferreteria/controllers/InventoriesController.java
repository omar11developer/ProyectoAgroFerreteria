package com.proyect.agroferreteria.controllers;

import com.proyect.agroferreteria.models.entity.Inventories;
import com.proyect.agroferreteria.models.entity.Product;
import com.proyect.agroferreteria.models.entity.Supplier;
import com.proyect.agroferreteria.services.contracts.InventoriesDAO;
import com.proyect.agroferreteria.services.contracts.ProductDAO;
import com.proyect.agroferreteria.services.contracts.SupplierDAO;
import com.proyect.agroferreteria.services.contracts.TypeProductDAO;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/agroferreteria/inventario")
public class InventoriesController {
    @Autowired
    private InventoriesDAO service;

    @Autowired
    private ProductDAO productDAO;
    @Autowired
    private SupplierDAO supplierDAO;

    @GetMapping("/")
    public ResponseEntity<?> obtnerInventarios(){
        Map<String, Object> response = new HashMap<>();
        try {
            Iterable<Inventories> inventories = new ArrayList<>();
            inventories = service.obtnerInventarioConProductos();
            if (inventories != null){
                return new ResponseEntity<>(inventories, HttpStatus.OK);
            }else {
                response.put("Mensaje: ", "No existe ningun producto en el inventario");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        }catch (DataAccessException e){
            response.put("Mensaje: ", "Error al obtener los productos");
            response.put("Error: " , e.getMessage());
            return  new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerById(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<Inventories> inventorie = service.obtnerInventarioById(id);
            if(!inventorie.isPresent()){
                response.put("Mensaje: ", "El id no existe ");
                return  new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }else {
                return new ResponseEntity<>(inventorie, HttpStatus.OK);
            }
        }catch (DataAccessException e){
            response.put("Mensaje: ", "Error al obtener el inventario");
            response.put("Error: " , e.getMessage());
            return  new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping
    public ResponseEntity<?> saveInvetario(@RequestBody Inventories inventories){

        boolean productActual = productDAO.existeByNameProduct(inventories.getProduct().getName());
        boolean supplierActual = supplierDAO.existsByName(inventories.getSupplier().getName());
        Map<String, Object> response = new HashMap<>();
        try{
            if(!productActual || !supplierActual){
                response.put("Mensaje: ", "El producto o el proveedor no existen");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }else{
                Product producto = productDAO.findByName(inventories.getProduct().getName());
                Supplier supplier = supplierDAO.findByName(inventories.getSupplier().getName());
                inventories.setProduct(producto);
                inventories.setSupplier(supplier);
                service.save(inventories);
                return new ResponseEntity<>(inventories, HttpStatus.CREATED);
            }
        }catch (DataAccessException e){
            response.put("Mensaje: ", "Error al guardar el inventario");
            response.put("Error: " , e.getMessage());
            return  new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
/*
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteInvetorie(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        Optional<Inventories> deleteInvetory = null;
        try {
            deleteInvetory = service.obtnerInventarioById(id);
            if(!deleteInvetory.isPresent()){
                response.put("Mensaje: ", "El invetario que deceas eliminar no existe");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }else{
                service.deleteById(id);
                response.put("Mensaje: ", "Se elimino correctamente");
                response.put("Object: ",deleteInvetory.toString());
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

        }catch (DataAccessException e){
            response.put("Mensaje: ", "Error al eliminar el inventario");
            response.put("Error: " , e.getMessage());
            return  new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
*/



}
