package com.proyect.agroferreteria.controllers.dto;

import com.proyect.agroferreteria.models.dto.ProductoDTO;
import com.proyect.agroferreteria.models.entity.Category;
import com.proyect.agroferreteria.models.entity.Product;
import com.proyect.agroferreteria.models.mapper.mapstruct.ProductoMapper;
import com.proyect.agroferreteria.services.contracts.CategoryDAO;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/productos")
@ConditionalOnProperty(prefix = "app", name = "controller.enable-dto", havingValue = "true")
public class ProductDtoController extends GenericoDtoController<Product, ProductDAO>{
    @Autowired
    private ProductoMapper mapper;
    private final CategoryDAO categoryDAO;
    private final SupplierDAO supplierDAO;
    public ProductDtoController(ProductDAO service, CategoryDAO categoryDAO, SupplierDAO supplierDAO) {
        super(service, "Producto");
        this.categoryDAO = categoryDAO;
        this.supplierDAO = supplierDAO;
    }
    @GetMapping("/")
    public ResponseEntity<?> obtenerProductos(){
        Map<String, Object> response = new HashMap<>();
        List<Product> products = super.obtenerTodos();
        if(products.isEmpty()){
            response.put("succes", Boolean.FALSE);
            response.put("mensaje", String.format("No se encontraron %ss cargados", nombreEntidad));
            return ResponseEntity.badRequest().body(response);
        }
        List<ProductoDTO> productoDTOS = products
                .stream()
                .map(mapper::mapProducto)
                .collect(Collectors.toList());
        response.put("succes", Boolean.TRUE);
        response.put("data", productoDTOS);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/searchByCategory/{categoria}")
    public ResponseEntity<?> buscarProductoPorCategoria(@PathVariable String categoria){
        Map<String, Object> response = new HashMap<>();
        List<Product> products = (List<Product>) service.getProductByTypeProduct(categoria);
        if(products.isEmpty()){
            response.put("success", Boolean.FALSE);
            response.put("messagge", String.format("No se econtro un %s con la categoria %s", nombreEntidad, categoria));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        List<ProductoDTO> productoDTOS = products
                .stream()
                .map(mapper::mapProducto)
                .collect(Collectors.toList());
        response.put("success", Boolean.TRUE);
        response.put("data", productoDTOS);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/searchByProveedor/{proveedor}")
    public ResponseEntity<?> buscarProductosPorProveedor(@PathVariable String proveedor){
        Map<String, Object> response = new HashMap<>();
        List<Product> products = (List<Product>) service.findByProductBySupplier(proveedor);
        if(products.isEmpty()){
            response.put("success", Boolean.FALSE);
            response.put("messagge", String.format("No se encontro un %s con el proveedor %s", nombreEntidad, proveedor));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        List<ProductoDTO> productoDTOS = products.stream().map(mapper::mapProducto).collect(Collectors.toList());
        response.put("success", Boolean.TRUE);
        response.put("data", productoDTOS);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/")
    public ResponseEntity<?> saveProduct(@Valid @RequestBody Product product, BindingResult result){
        Map<String, Object> response = new HashMap<>();
        boolean productLocal = service.existeByNameProduct(product.getName());
        boolean categoryLocal = categoryDAO.existeTypeProductName(product.getCategory().getName());
        boolean supplierLocal = supplierDAO.existsByName(product.getSupplier().getName());
        if(result.hasErrors()){
            response.put("success", Boolean.FALSE);
            response.put("messagge", super.obtenerValidaciones(result));
            return ResponseEntity.badRequest().body(response);
        } else if (productLocal) {
            response.put("success", Boolean.FALSE);
            response.put("messagge", String.format("El nombre %s del producto que deseas agregar ya existe", product.getName()));
            return ResponseEntity.badRequest().body(response);
        } else if (!categoryLocal) {
            response.put("success", Boolean.FALSE);
            response.put("messagge", String.format("El nombre de la categoria %s no existe", product.getCategory().getName()));
            return ResponseEntity.badRequest().body(response);
        } else if (!supplierLocal) {
            response.put("success", Boolean.FALSE);
            response.put("messagge", String.format("El nombre del proveedor %s no existe", product.getSupplier().getName()));
            return ResponseEntity.badRequest().body(response);
        }
        product.setSupplier(supplierDAO.findByName(product.getSupplier().getName()));
        product.setCategory(categoryDAO.getByName(product.getCategory().getName()));
        Product productSave = super.altaEntidad(product);
        ProductoDTO dto = mapper.mapProducto(productSave);
        response.put("success", Boolean.TRUE);
        response.put("data", dto);
        return ResponseEntity.ok(response);
    }

}
