package com.proyect.agroferreteria.controllers;

import com.proyect.agroferreteria.models.entity.Product;
import com.proyect.agroferreteria.models.entity.TypeProduct;
import com.proyect.agroferreteria.services.contracts.IProductService;
import com.proyect.agroferreteria.services.contracts.ITypeProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/agroferreteria")
public class TypeProductoController {

    @Autowired
    private ITypeProductService typeProductService;



    @GetMapping("/TypeProductos")
    public List<TypeProduct> getProducts(){
        return typeProductService.findAll();
    }

    @GetMapping("TypeProductos/{id}")
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



}
