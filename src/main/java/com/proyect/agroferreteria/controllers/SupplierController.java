package com.proyect.agroferreteria.controllers;

import com.proyect.agroferreteria.models.entity.Supplier;
import com.proyect.agroferreteria.services.contracts.SupplierDAO;
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
@RequestMapping("/agroferreteria/proveedores")
public class SupplierController extends GenericoController<Supplier, SupplierDAO>{
    @Autowired
    public SupplierController(SupplierDAO service) {
        super(service);
        nombreEntidad = "Supplier";
    }

    @GetMapping("searchByName/{name}")
    public ResponseEntity<?> searchByName(@PathVariable String name){
        Map<String, Object> response = new HashMap<>();
        try{
            List<Supplier> suppliers = (List<Supplier>) service.searchByNameLikeIgnoreCase(name);
            if(suppliers.isEmpty()){
                response.put("Mensjae: ", "No se enonctro ningun proveedor con el nombre: ".concat(name));
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }else {
                return new ResponseEntity<>(suppliers, HttpStatus.OK);
            }
        }catch (DataAccessException e){
            response.put("Mensaje: ", "Error al obtener los proveedores");
            response.put("Error: " , e.getMessage());
            return  new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping
    public ResponseEntity<?> saveSupplier(@RequestBody Supplier supplier){
        Supplier newSupplier = null;
        Map<String, Object> response = new HashMap<>();
        try{
            if(service.existsByName(supplier.getName())){
                response.put("Mensaje", "El proveedor ya existe");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }else{
                newSupplier = service.save(supplier);
                return new ResponseEntity<>(newSupplier, HttpStatus.CREATED);
            }

        }catch (DataAccessException e){
            response.put("Mensaje: ", "Error al guardar los proveedores");
            response.put("Error: " , e.getMessage());
            return  new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> editSupplier(@PathVariable Long id, @RequestBody Supplier supplier){
        Optional<Supplier> supplierActual = service.findById(id);
        Supplier supplierUpdate = null;
        Map<String, Object> response = new HashMap<>();
        try{
            if (!supplierActual.isPresent()){
                response.put("Mensaje: ", "El provedor que desea editar no existe");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }else{
                supplierUpdate = supplierActual.get();
                supplierUpdate.setName(supplier.getName());
                supplierUpdate.setCity(supplier.getCity());
                supplierUpdate.setPhone(supplier.getPhone());
                supplierUpdate.setAddress(supplier.getAddress());
                service.save(supplierUpdate);
                return new ResponseEntity<>(supplierUpdate, HttpStatus.OK);
            }
        }catch (DataAccessException e){
            response.put("Mensaje: ", "Error al editar los proveedores");
            response.put("Error: " , e.getMessage());
            return  new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
