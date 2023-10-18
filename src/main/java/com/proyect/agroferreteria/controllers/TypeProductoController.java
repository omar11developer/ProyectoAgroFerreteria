package com.proyect.agroferreteria.controllers;

import com.proyect.agroferreteria.models.entity.TypeProduct;
import com.proyect.agroferreteria.services.contracts.TypeProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/agroferreteria")
public class TypeProductoController {

    @Autowired
    private TypeProductService typeProductService;



    @GetMapping("/TypeProductos")
    public List<TypeProduct> getProducts(){
        return typeProductService.findAll();
    }

    @GetMapping("/TypeProductos/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        TypeProduct typeProduct = null;
        Map<String, Object> response = new HashMap<>();
        try {
            typeProduct = typeProductService.findById(id);
        }catch (DataAccessException e){
            response.put("Mensaje", "Error al buscar un tipo de producto");
            response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (typeProduct == null){
            response.put("Mensaje: ", "El id no existe");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<TypeProduct>(typeProduct, HttpStatus.OK);
    }

    @PostMapping("/TypeProductos")
    public ResponseEntity<?> saveTypeProduct(@RequestBody TypeProduct typeProduct) {
        TypeProduct typeProductLocal = typeProductService.getByName(typeProduct.getName());
        Map<String, Object> response = new HashMap<>();
        if(typeProductLocal != null){
            response.put("Mensaje: ", "El tipo de producto ya existe");
            return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        }
        try {
            typeProductLocal = typeProductService.save(typeProduct);
        } catch (DataAccessException e){
            response.put("Mensaje: ", "Error al guardar tipo de producto");
            response.put("Error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<TypeProduct>(typeProduct, HttpStatus.CREATED);

    }
    @PutMapping("/TypeProductos/{id}")
    public ResponseEntity<?> editTypeProduct(@RequestBody TypeProduct typeProduct, @PathVariable Long id){
        TypeProduct productActual = typeProductService.findById(id);
        TypeProduct typeProductUpdate =null;
        Map<String, Object> response = new HashMap<>();

        if(productActual == null){
            response.put("Mensaje: ", "El tipo de producto que desea editar no existe");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            productActual.setName(typeProduct.getName());
            typeProductUpdate = typeProductService.save(productActual);
        }catch (DataAccessException e){
            response.put("Mensaje: ", "Error al actualizar tipo de producto");
            response.put("Error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("Mensaje: ", "El cliente ha sido actualizado con exito!");
        response.put("typeProduct: ", typeProductUpdate);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
    @DeleteMapping("/TypeProductos/{id}")
    public ResponseEntity<?> deleteTypeProduct(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        TypeProduct typeProduct = typeProductService.findById(id);
        if(typeProduct == null){
            response.put("Mensaje: ", "El tipo de producto no existe");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        try{
            typeProductService.deleteById(id);
            response.put("Mensaje: ", "cliente eliminado con exito");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (DataAccessException e){
            response.put("Mensaje: ", "Error al eliminar el tipo de producto");
            response.put("Error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
