package com.proyect.agroferreteria.controllers;

import com.proyect.agroferreteria.models.entity.Inventories;
import com.proyect.agroferreteria.services.contracts.InventoriesDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/agroferreteria/inventario")
public class InventoriesController extends GenericoController<Inventories, InventoriesDAO>{
    @Autowired
    public InventoriesController(InventoriesDAO service) {
        super(service);
        nombreEntidad="Inventario";
    }

    @GetMapping("/all")
    public ResponseEntity<?> obtenerTodoElInventario(){
        Map<String, Object> response = new HashMap<>();
        Iterable<Inventories> inventories = new ArrayList<>();
        try{
            inventories= service.obtenerTodosLosProductos();
            if (inventories != null){

                return new ResponseEntity<>(inventories, HttpStatus.OK);

            }else {
                response.put("Mensaje: ", "No existe ningun producto en el inventario");
                return  new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        }catch (DataAccessException e){
            response.put("Mensaje: ", "Error al obtener los productos");
            response.put("Error: " , e.getMessage());
            return  new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
