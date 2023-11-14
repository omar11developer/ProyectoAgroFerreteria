package com.proyect.agroferreteria.controllers.dto;

import com.proyect.agroferreteria.models.dto.InventorieDTO;
import com.proyect.agroferreteria.models.entity.Inventories;
import com.proyect.agroferreteria.models.mapper.mapstruct.InventoriesMapper;
import com.proyect.agroferreteria.services.contracts.InventoriesDAO;
import com.proyect.agroferreteria.services.contracts.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/inventario")
@ConditionalOnProperty(prefix = "app", name = "controller.enable-dto", havingValue = "true")
public class InventarioDtoController extends GenericoDtoController<Inventories, InventoriesDAO>{
    @Autowired
    private InventoriesMapper mapper;
    private final ProductDAO productDAO;
      public InventarioDtoController(InventoriesDAO service, ProductDAO productDAO) {
        super(service, "Inventario");
          this.productDAO = productDAO;
      }
        @GetMapping("/")
        public ResponseEntity<?> obtenerInvetario(){
        Map<String, Object> response= new HashMap<>();
        List<Inventories> inventories = super.obtenerTodos();
        if(inventories.isEmpty()){
            response.put("succes", Boolean.FALSE);
            response.put("message", String.format("No se encontraron %ss cargados", nombreEntidad));
            return ResponseEntity.badRequest().body(response);
        }
        List<InventorieDTO> inventorieDTOS = inventories
                .stream()
                .map(mapper::mapInventario)
                .collect(Collectors.toList());
        response.put("success", Boolean.TRUE);
        response.put("data", inventorieDTOS);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarInvetario(@PathVariable Long id){
          Map<String, Object> response = new HashMap<>();
        Optional<Inventories> inventoriesLocal = super.obtenerPorId(id);
        if (inventoriesLocal.isEmpty()){
            response.put("success", Boolean.FALSE);
            response.put("message", String.format("No se encontro el %s con el id %d ", nombreEntidad, id));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        super.eliminarPorId(id);
        response.put("success", Boolean.TRUE);
        response.put("message", String.format("El %s #%d ha sido eliminado", nombreEntidad, id));
        return ResponseEntity.ok(response);
    }

}
