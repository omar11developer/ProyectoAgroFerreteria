package com.proyect.agroferreteria.controllers.dto;

import com.proyect.agroferreteria.models.dto.SupplierDTO;
import com.proyect.agroferreteria.models.entity.Supplier;
import com.proyect.agroferreteria.models.mapper.mapstruct.SupplierMapper;
import com.proyect.agroferreteria.services.contracts.SupplierDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/proveedores")
@ConditionalOnProperty(prefix = "app", name = "controller.enable-dto", havingValue = "true")
public class SupplierDtoController extends GenericoDtoController<Supplier, SupplierDAO> {
    @Autowired
    private SupplierMapper mapper;
    public SupplierDtoController(SupplierDAO service) {
        super(service, "Provedor");
    }
    @GetMapping("/")
    public ResponseEntity<?> obtenerProveedores(){
        Map<String, Object> response = new HashMap<>();
        List<Supplier> suppliers = super.obtenerTodos();
        if(suppliers.isEmpty()){
            response.put("succes", Boolean.FALSE);
            response.put("mensaje", String.format("No se encontro %ses cargador", nombreEntidad));
            return ResponseEntity.badRequest().body(response);
        }
        List<SupplierDTO> supplierDTOS = suppliers
                .stream()
                .map(mapper::mapSupplier)
                .collect(Collectors.toList());
        response.put("succes", Boolean.TRUE);
        response.put("data", supplierDTOS);
        return ResponseEntity.ok(response);
    }


}
