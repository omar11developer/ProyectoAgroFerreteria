package com.proyect.agroferreteria.controllers.dto;

import com.proyect.agroferreteria.models.dto.ProductoDTO;
import com.proyect.agroferreteria.models.entity.Category;
import com.proyect.agroferreteria.models.entity.Product;
import com.proyect.agroferreteria.models.entity.Supplier;
import com.proyect.agroferreteria.models.mapper.mapstruct.ProductoMapper;
import com.proyect.agroferreteria.services.contracts.CategoryDAO;
import com.proyect.agroferreteria.services.contracts.ProductDAO;
import com.proyect.agroferreteria.services.contracts.SupplierDAO;
import io.swagger.annotations.Api;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/productos")
@ConditionalOnProperty(prefix = "app", name = "controller.enable-dto", havingValue = "true")
@Api(value = "Acciones relacionadas con Productos", tags = "Acciones sobre Producto")
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
    @GetMapping("/detalleProducto/{id}")
    public ResponseEntity<?> buscarPoroductopordID(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        Optional<Product> product = super.obtenerPorId(id);
        if(product.isEmpty()){
            response.put("success", Boolean.FALSE);
            response.put("mensaje", String.format("No se encontro un %s con el id %d", nombreEntidad, id));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        Product productFind = product.get();
        ProductoDTO dto = mapper.mapProducto(productFind);
        response.put("success", Boolean.TRUE);
        response.put("data", dto);
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
    @GetMapping("/searchLikeName/{nameProduct}")
    public ResponseEntity<?> buscarProductosPorNombre(@PathVariable String nameProduct){
        Map<String, Object> response = new HashMap<>();
        List<Product> products = (List<Product>) service.buscarProductosPorNombre(nameProduct);
        if (products.isEmpty()){
            response.put("success", Boolean.FALSE);
            response.put("Message", String.format("No se encontraron productos con el nombre:%s", nameProduct));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        List<ProductoDTO> productoDTOS = products.stream().map(mapper::mapProducto).collect(Collectors.toList());
        response.put("success", Boolean.TRUE);
        response.put("data", productoDTOS);
        return ResponseEntity.ok(response);

    }


   @PostMapping("/guardarProducto/")
    public ResponseEntity<?> saveProduct(@Valid @RequestBody ProductoDTO product, BindingResult result){
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
        Product productSave = super.altaEntidad(mapper.mapDtoProducto(product));
        ProductoDTO dto = mapper.mapProducto(productSave);
        response.put("success", Boolean.TRUE);
        response.put("data", dto);
        return ResponseEntity.ok(response);
    }


    @PutMapping("/editarProducto/{id}")
    public ResponseEntity<?> editarProducto(@Valid @RequestBody ProductoDTO product, @PathVariable Long id, BindingResult result){
        Map<String, Object> response = new HashMap<>();
        Optional<Product> productoLocal = super.obtenerPorId(id);
        Boolean existSupplier = supplierDAO.existsByName(product.getSupplier().getName());
        Boolean existCategory = categoryDAO.existeTypeProductName(product.getCategory().getName());
        Product productUpdate;
        if(result.hasErrors()){
            response.put("success", Boolean.FALSE);
            response.put("messagge", super.obtenerValidaciones(result));
            return ResponseEntity.badRequest().body(response);
        }
        if (!productoLocal.isEmpty()){
            productUpdate = productoLocal.get();
            productUpdate.setName(product.getName());
            productUpdate.setUnitPrice(product.getUnit_Price());
            productUpdate.setUnitWeight(product.getUnit_Weight());
            if (existCategory){
                Category category = categoryDAO.getByName(product.getCategory().getName());
                productUpdate.setCategory(category);
            }else{
                response.put("success", Boolean.FALSE);
                response.put("messagge", "No existe relacion entre la categoria y el producto");
                return ResponseEntity.badRequest().body(response);
            }
            if (existSupplier){
                Supplier supplier = supplierDAO.findByName(product.getSupplier().getName());
                productUpdate.setSupplier(supplier);
            }else{
                response.put("success", Boolean.FALSE);
                response.put("messagge", "No existe relacion entre la proovedor y el producto");
                return ResponseEntity.badRequest().body(response);
            }
            Product productSave = super.altaEntidad(productUpdate);
            ProductoDTO dto = mapper.mapProducto(productSave);
            response.put("success", Boolean.TRUE);
            response.put("data", dto);
            return ResponseEntity.ok(response);

        }else{
            response.put("success", Boolean.FALSE);
            response.put("messagge", String.format("El %s  que deseas editar no existe con el id %d", nombreEntidad, id));
            return ResponseEntity.badRequest().body(response);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarProductoPorId(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        Optional<Product> product = super.obtenerPorId(id);
        if (product.isEmpty()){
            response.put("success", Boolean.FALSE);
            response.put("mensaje", String.format("No se encontro un %s con el id %d", nombreEntidad, id));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        ProductoDTO productoDTO = mapper.mapProducto(product.get());
        super.eliminarPorId(id);
        response.put("success", Boolean.TRUE);
        response.put("data", productoDTO);
        response.put("messagge", "Producto eliminado con exito");
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> validacionID(MethodArgumentTypeMismatchException ex){
        Map<String, Object> response = new HashMap<>();
        response.put("success", Boolean.FALSE);
        response.put("Message", "El parametro 'id' debe ser un numero");
        return ResponseEntity.badRequest().body(response);
    }

}
