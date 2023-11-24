package com.proyect.agroferreteria.controllers.dto;

import com.proyect.agroferreteria.models.dto.SupplierDTO;
import com.proyect.agroferreteria.models.entity.Supplier;
import com.proyect.agroferreteria.models.mapper.mapstruct.SupplierMapper;
import com.proyect.agroferreteria.services.contracts.SupplierDAO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/proveedores")
@ConditionalOnProperty(prefix = "app", name = "controller.enable-dto", havingValue = "true")
public class SupplierDtoController extends GenericoDtoController<Supplier, SupplierDAO> {
    @Autowired
    private SupplierMapper mapper;
    public SupplierDtoController(SupplierDAO service) {
        super(service, "Proveedor");
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
    @GetMapping("/detalle/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        Map<String, Object> response= new HashMap<>();
        Optional<Supplier> supplier = super.obtenerPorId(id);
        if (supplier.isEmpty()){
            response.put("success", Boolean.FALSE);
            response.put("mensagge", String.format("No se encontro %s con el ID %d", nombreEntidad, id));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        Supplier supplierMapper = supplier.get();
        SupplierDTO dto = mapper.mapSupplier(supplierMapper);
        response.put("Success", Boolean.TRUE);
        response.put("data", dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/buscarlikeNombre/{name}")
    public ResponseEntity<?> buscarPorNombre(@PathVariable String name){
        Map<String, Object> response = new HashMap<>();
        List<Supplier> suppliers = (List<Supplier>) service.searchByNameLikeIgnoreCase(name);
        if(suppliers.isEmpty()){
            response.put("success", Boolean.FALSE);
            response.put("messagge", String.format("No se encontro %ses con el nombre %s", nombreEntidad, name));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        List<SupplierDTO> supplierDTOS = suppliers
                .stream()
                .map(mapper::mapSupplier)
                .collect(Collectors.toList());
        response.put("success", Boolean.TRUE);
        response.put("data", supplierDTOS);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/guardarProveedor/")
    public ResponseEntity<?> guardarProveedor(@Valid @RequestBody SupplierDTO supplier, BindingResult result){
        Map<String, Object> response = new HashMap<>();
        boolean supplierLocal = service.existsByName(supplier.getName());
        if(result.hasErrors()){
            response.put("success", Boolean.FALSE);
            response.put("validations", super.obtenerValidaciones(result));
            return ResponseEntity.badRequest().body(response);
        } else if (supplierLocal) {
            response.put("success", Boolean.FALSE);
            response.put("messagge", String.format("El %s que desea guardar ya existe", nombreEntidad));
            return ResponseEntity.badRequest().body(response);
        }
        Supplier oSupplier = super.altaEntidad(mapper.mapDtoSupplier(supplier));
        SupplierDTO dto = mapper.mapSupplier(oSupplier);
        response.put("success", Boolean.TRUE);
        response.put("data", dto);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/editProveedor/{id}")
    public ResponseEntity<?> editarProveedor(@Valid @RequestBody SupplierDTO supplier, @PathVariable Long id, BindingResult result){
        Map<String, Object> response = new HashMap<>();
        Optional<Supplier> supplierLocal = super.obtenerPorId(id);
        Supplier supplierUpdate;
        if (result.hasErrors()){
            response.put("success", Boolean.FALSE);
            response.put("validation", super.obtenerValidaciones(result));
            return ResponseEntity.badRequest().body(response);
        } else if (supplierLocal.isEmpty()) {
            response.put("success", Boolean.FALSE);
            response.put("messagge", String.format("El %s que desea editar no existe", nombreEntidad));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        supplierUpdate = supplierLocal.get();
        supplierUpdate.setName(supplier.getName());
        supplierUpdate.setCity(supplier.getCity());
        supplierUpdate.setPhone(supplier.getPhone());
        supplierUpdate.setAddress(supplier.getAddress());
        Supplier save = super.altaEntidad(supplierUpdate);
        SupplierDTO dto = mapper.mapSupplier(save);
        response.put("success", Boolean.TRUE);
        response.put("data", dto);
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        Optional<Supplier> supplierLocal = super.obtenerPorId(id);

        if(supplierLocal.isEmpty()){
            response.put("success", Boolean.FALSE);
            response.put("messagge", String.format("El %s que deseas eliminar con el id %d no existe", nombreEntidad, id));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        Supplier supplier = supplierLocal.get();
        super.eliminarPorId(id);
        SupplierDTO dto = mapper.mapSupplier(supplier);
        response.put("success", Boolean.TRUE);
        response.put("messagge", "Eliminado con éxito");
        response.put("data", dto);
        return ResponseEntity.ok(response);
    }

}
