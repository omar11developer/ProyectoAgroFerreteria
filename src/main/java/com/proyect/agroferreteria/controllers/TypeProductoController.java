package com.proyect.agroferreteria.controllers;

import com.proyect.agroferreteria.models.entity.TypeProduct;
import com.proyect.agroferreteria.services.contracts.TypeProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/agroferreteria/tiposdeproducto")
public class TypeProductoController extends GenericoController<TypeProduct, TypeProductDAO> {

    @Autowired
    public TypeProductoController(TypeProductDAO service) {
        super(service);
        nombreEntidad = "Tipo de producto";
    }
    @PostMapping
    public ResponseEntity<?> saveTypeProduct(@RequestBody TypeProduct typeProduct) {
        TypeProduct typeProductLocal = service.getByName(typeProduct.getName());
        Map<String, Object> response = new HashMap<>();

        try {
            if(typeProductLocal != null){
                response.put("Mensaje: ", "El tipo de producto ya existe");
                return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
            }else{
                typeProductLocal = service.save(typeProduct);
                return new ResponseEntity<TypeProduct>(typeProduct, HttpStatus.CREATED);
            }
        } catch (DataAccessException e){
            response.put("Mensaje: ", "Error al guardar tipo de producto");
            response.put("Error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @PutMapping("/{id}")
    public ResponseEntity<?> editTypeProduct(@RequestBody TypeProduct typeProduct, @PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        Optional<TypeProduct> productActual = service.findById(id);
        TypeProduct typeProductUpdate =null;
        try {

            if(!productActual.isPresent()){
                response.put("Mensaje: ", "El tipo de producto que desea editar no existe");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }else{
                typeProductUpdate = productActual.get();
                typeProductUpdate.setName(typeProduct.getName());
                service.save(typeProductUpdate);
                response.put("Mensaje: ", "El cliente ha sido actualizado con exito!");
                response.put("typeProduct: ", typeProductUpdate);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }catch (DataAccessException e){
            response.put("Mensaje: ", "Error al actualizar tipo de producto");
            response.put("Error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
