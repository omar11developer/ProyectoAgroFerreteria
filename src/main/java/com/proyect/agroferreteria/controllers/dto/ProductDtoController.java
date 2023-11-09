package com.proyect.agroferreteria.controllers.dto;

import com.proyect.agroferreteria.models.dto.ProductoDTO;
import com.proyect.agroferreteria.models.entity.Product;
import com.proyect.agroferreteria.models.mapper.mapstruct.ProductoMapper;
import com.proyect.agroferreteria.services.contracts.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    public ProductDtoController(ProductDAO service) {
        super(service, "Producto");
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


}
