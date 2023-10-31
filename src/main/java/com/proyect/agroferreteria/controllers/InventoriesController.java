package com.proyect.agroferreteria.controllers;

import com.proyect.agroferreteria.models.entity.Inventories;
import com.proyect.agroferreteria.models.entity.Product;
import com.proyect.agroferreteria.services.contracts.InventoriesDAO;
import com.proyect.agroferreteria.services.contracts.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/agroferreteria/inventario")
public class InventoriesController extends GenericoController<Inventories, InventoriesDAO> {

    private final ProductDAO productDAO;
    @Autowired
    public InventoriesController(InventoriesDAO service, ProductDAO productDAO) {
        super(service);
        this.productDAO = productDAO;
    }

    @GetMapping("/orderByDate")
    public ResponseEntity<?> orderByDate(){
        Map<String, Object> response = new HashMap<>();
        Iterable<Inventories> inventories = service.orderByDate();
        try{
            if(inventories != null){
                return new ResponseEntity<>(inventories, HttpStatus.OK);
            }else{
                response.put("Mensaje: ", "No hay ningun inventario");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

        }catch (DataAccessException e){
            response.put("Error: " , e.getMessage());
            return  new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/orderByStokLower")
    public ResponseEntity<?> buscarInventarioConStokBajos(){
        Map<String, Object> response = new HashMap<>();
        Iterable<Inventories> inventories = service.buscarInvetarioConStokBajos();
        try{
            if(inventories != null){
                return new ResponseEntity<>(inventories, HttpStatus.OK);
            }else{
                response.put("Mensaje: ", "No hay ningun inventario");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

        }catch (DataAccessException e){
            response.put("Error: " , e.getMessage());
            return  new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/searchByDate/{date}")
    public ResponseEntity<?> buscarInventarioPorFecha(@PathVariable String date) throws ParseException {
        Map<String, Object> response = new HashMap<>();
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
        Date formattedDate = sdf.parse(date);
        try{
            Iterable<Inventories> inventories = service.buscarInventarioPorFecha(formattedDate);
            if(date.toString().isEmpty()){
                response.put("Mensaje: ", "Ingrese una fecha");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }else if(inventories != null){
                return new ResponseEntity<>(inventories, HttpStatus.OK);
            }else{
                response.put("Mensaje: ", "No hay ningun inventario");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

        }catch (DataAccessException e){
            response.put("Error: " , e.getMessage());
            return  new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
   @PostMapping("/")
    public ResponseEntity<?> saveInvetories(@RequestBody Inventories inventories){
       Product product = productDAO.findByName(inventories.getProduct().getName());
       Map<String, Object> response = new HashMap<>();
       try{
           if(product != null){
               inventories.setProduct(product);
               service.save(inventories);
               return new ResponseEntity<>(inventories, HttpStatus.OK);
           }else {
               response.put("Mensaje: ", "No se puede crear porque el producto no existe");
               return  new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
           }
       }catch (DataAccessException e){
           response.put("Error: " , e.getMessage());
           return  new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
       }

    }
    @PutMapping("/{id}")
    public ResponseEntity<?> editarInventario(@RequestBody Inventories inventories, @PathVariable Long id){
        Optional<Inventories> inventarioActual = service.findById(id);
        Inventories inventariaUpdate = null;
        Map<String, Object> response = new HashMap<>();
        try{
            if(!inventarioActual.isPresent()){
                response.put("Mensaje: ", "El Inventario que desea editar no existe");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }else{
                inventariaUpdate = inventarioActual.get();
                inventariaUpdate.setCreateAtOrder(inventories.getCreateAtOrder());
                inventariaUpdate.setSalePrice(inventories.getSalePrice());
                inventariaUpdate.setStock(inventories.getStock());
                Product product = productDAO.findByName(inventories.getProduct().getName());
                if(product != null){
                    inventariaUpdate.setProduct(product);
                    service.save(inventariaUpdate);
                    response.put("Mensaje: ", "Modificado con exito");
                    return new ResponseEntity<>(inventariaUpdate, HttpStatus.OK);
                }else{
                    response.put("Mensaje: ", "No se puede editar el invetario porque el  producto no existe");
                    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
                }
            }
        }catch (DataAccessException e){
            response.put("Error: " , e.getMessage());
            return  new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}
