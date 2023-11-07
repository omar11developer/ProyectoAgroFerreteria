package com.proyect.agroferreteria.controllers;

import com.proyect.agroferreteria.models.entity.Category;
import com.proyect.agroferreteria.services.contracts.CategoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
@Deprecated
@RestController
@RequestMapping("/agroferreteria/tiposdeproducto")
@ConditionalOnProperty(prefix = "app", name = "controller.enable-dto", havingValue = "false")
public class CategoryController extends GenericoController<Category, CategoryDAO> {

    @Autowired
    public CategoryController(CategoryDAO service) {
        super(service);
        nombreEntidad = "Tipo de producto";
    }
    @PostMapping
    public ResponseEntity<?> saveTypeProduct(@RequestBody Category category) {
        Category categoryLocal = service.getByName(category.getName());
        Map<String, Object> response = new HashMap<>();

        try {
            if(categoryLocal != null){
                response.put("Mensaje: ", "El tipo de producto ya existe");
                return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
            }else{
                categoryLocal = service.save(category);
                return new ResponseEntity<Category>(category, HttpStatus.CREATED);
            }
        } catch (DataAccessException e){
            response.put("Mensaje: ", "Error al guardar tipo de producto");
            response.put("Error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @PutMapping("/{id}")
    public ResponseEntity<?> editTypeProduct(@RequestBody Category category, @PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        Optional<Category> productActual = service.findById(id);
        Category categoryUpdate =null;
        try {

            if(!productActual.isPresent()){
                response.put("Mensaje: ", "El tipo de producto que desea editar no existe");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }else{
                categoryUpdate = productActual.get();
                categoryUpdate.setName(category.getName());
                service.save(categoryUpdate);
                response.put("Mensaje: ", "El cliente ha sido actualizado con exito!");
                response.put("typeProduct: ", categoryUpdate);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }catch (DataAccessException e){
            response.put("Mensaje: ", "Error al actualizar tipo de producto");
            response.put("Error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
