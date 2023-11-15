package com.proyect.agroferreteria.controllers.dto;

import com.proyect.agroferreteria.models.dto.InventorieDTO;
import com.proyect.agroferreteria.models.dto.ProductoDTO;
import com.proyect.agroferreteria.models.entity.Category;
import com.proyect.agroferreteria.models.entity.Inventories;
import com.proyect.agroferreteria.models.entity.Product;
import com.proyect.agroferreteria.models.entity.Supplier;
import com.proyect.agroferreteria.models.mapper.mapstruct.InventoriesMapper;
import com.proyect.agroferreteria.services.contracts.CategoryDAO;
import com.proyect.agroferreteria.services.contracts.InventoriesDAO;
import com.proyect.agroferreteria.services.contracts.ProductDAO;
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
@RequestMapping("/inventario")
@ConditionalOnProperty(prefix = "app", name = "controller.enable-dto", havingValue = "true")
public class InventarioDtoController extends GenericoDtoController<Inventories, InventoriesDAO>{
    @Autowired
    private InventoriesMapper mapper;
    private final ProductDAO productDAO;
    private final CategoryDAO categoryDAO;
    private final SupplierDAO supplierDAO;
      public InventarioDtoController(InventoriesDAO service, ProductDAO productDAO, CategoryDAO categoryDAO, SupplierDAO supplierDAO) {
        super(service, "Inventario");
          this.productDAO = productDAO;
          this.categoryDAO = categoryDAO;
          this.supplierDAO = supplierDAO;
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
    @PostMapping("/")
    public ResponseEntity<?> guardarInventarioCompleto(@Valid @RequestBody Inventories inventories, BindingResult result){
          Map<String, Object> response = new HashMap<>();
          if(inventories.getProduct().getName() == null || inventories.getProduct().getCategory().getName() == null || inventories.getProduct().getSupplier().getName() == null){
              response.put("success", Boolean.FALSE);
              response.put("message", String.format("El campo nombre no puede quedar vacio"));
              return ResponseEntity.badRequest().body(response);
          }
          boolean productoLocal = productDAO.existeByNameProduct(inventories.getProduct().getName());
          boolean categoryLocal = categoryDAO.existeTypeProductName(inventories.getProduct().getCategory().getName());
          boolean supplierLocal = supplierDAO.existsByName(inventories.getProduct().getSupplier().getName());
          if(result.hasErrors()){
              response.put("success", Boolean.FALSE);
              response.put("messagge", super.obtenerValidaciones(result));
              return ResponseEntity.badRequest().body(response);
          }
          if(productoLocal){
             response.put("success", Boolean.FALSE);
             response.put("message", String.format("No se puede agregar producto porque ya existe un producto con el nombre %s ", inventories.getProduct().getName()));
             return ResponseEntity.badRequest().body(response);
          }
          if (!categoryLocal){
              response.put("success", Boolean.FALSE);
              response.put("message", String.format("No se puede agregar producto porque no existe una categoria con el nombre %s ", inventories.getProduct().getCategory().getName()));
              return ResponseEntity.badRequest().body(response);
          } else if (!supplierLocal) {
              response.put("success", Boolean.FALSE);
              response.put("message", String.format("No se puede agregar producto porque no existe un Proveedor con el nombre %s ", inventories.getProduct().getSupplier().getName()));
              return ResponseEntity.badRequest().body(response);
          }
        Category category = categoryDAO.getByName(inventories.getProduct().getCategory().getName());
        Supplier supplier = supplierDAO.findByName(inventories.getProduct().getSupplier().getName());
        inventories.getProduct().setCategory(category);
        inventories.getProduct().setSupplier(supplier);
        Product productSave = productDAO.save(inventories.getProduct());
        inventories.setProduct(productSave);
        Inventories inventarioSave = super.altaEntidad(inventories);
        InventorieDTO dto = mapper.mapInventario(inventarioSave);
        response.put("success", Boolean.TRUE);
        response.put("data", dto);
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
