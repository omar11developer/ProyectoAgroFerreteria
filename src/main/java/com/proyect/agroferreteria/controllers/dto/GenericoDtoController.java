package com.proyect.agroferreteria.controllers.dto;

import com.proyect.agroferreteria.services.contracts.GenericoDAO;
import lombok.AllArgsConstructor;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
public class GenericoDtoController <E, S extends GenericoDAO<E>> {
    protected final S service;
    protected String nombreEntidad;

    public List<E> obtenerTodos(){
        return (List<E>) service.findAll();
    }
    public Optional<E> obtenerPorId(Long id){
        return service.findById(id);
    }
    public E altaEntidad(E entidad){
        return service.save(entidad);
    }
    void  eliminarPorId(Long id){
        service.deleteById(id);
    }

    void delete(E entidad){service.delete(entidad);}

    protected Map<String , Object> obtenerValidaciones(BindingResult result){
        Map<String, Object> validaciones = new HashMap<>();
        result.getFieldErrors().forEach(
                error -> validaciones.put(error.getField(), error.getDefaultMessage())
        );
        return validaciones;
    }

}
