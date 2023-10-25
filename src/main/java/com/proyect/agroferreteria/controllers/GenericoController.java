package com.proyect.agroferreteria.controllers;

import com.proyect.agroferreteria.exeption.BadRequestException;
import com.proyect.agroferreteria.services.contracts.GenericoDAO;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


public class GenericoController <E, S extends GenericoDAO<E>> {
    protected final S service;
    protected String nombreEntidad;
    public GenericoController(S service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> obtenerTodos(){
        Map<String, Object> response = new HashMap<>();
        List<E> listar = new ArrayList<>();
        try{
            listar= (List<E>) service.findAll();
            if(listar.isEmpty()){
                response.put("Mensaje: ", "No se han encontrado ".concat(nombreEntidad));
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

        } catch (DataAccessException e){
            response.put("Mensaje: ", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(listar, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        Optional<E> obj = null;
        try {
            obj = (Optional<E>) service.findById(id);
            if(!obj.isPresent()){
                response.put("Mensaje:", "El id no existe");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

            }else{
                return new ResponseEntity<E>((E) obj, HttpStatus.OK);
            }
        }catch (DataAccessException e){
            response.put("Mensaje: ", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPorId(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        Optional<E>  obj = null;
        try {
             obj = service.findById(id);
            if(!obj.isPresent()){
                response.put("Mensaje:", "No se encontro el id");
                return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
            }
                service.deleteById(id);
                response.put("Mensaje: ", "Elimnado con exito");
                return new ResponseEntity<>(response, HttpStatus.OK);

        }catch (DataAccessException e){
            response.put("Mensaje: ", e.getMessage());
            return new ResponseEntity<> (response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}
