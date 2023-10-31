package com.proyect.agroferreteria.controllers;

import com.proyect.agroferreteria.services.contracts.InventoriesDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agroferreteria/inventario")
public class InventoriesController {
    @Autowired
    private InventoriesDAO service;


/*

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
*/

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
