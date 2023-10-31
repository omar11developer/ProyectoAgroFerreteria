package com.proyect.agroferreteria.controllers;

import com.proyect.agroferreteria.models.entity.Product;
import com.proyect.agroferreteria.models.entity.Supplier;
import com.proyect.agroferreteria.models.entity.Category;
import com.proyect.agroferreteria.services.contracts.ProductDAO;
import com.proyect.agroferreteria.services.contracts.SupplierDAO;
import com.proyect.agroferreteria.services.contracts.TypeProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/agroferreteria/productos")
public class ProductController extends GenericoController<Product, ProductDAO> {


    private final SupplierDAO supplierDAO;
    private final TypeProductDAO typeProductDAO;
    @Autowired
    public ProductController(ProductDAO service, SupplierDAO supplierDAO, TypeProductDAO typeProductDAO) {
        super(service);
        this.supplierDAO = supplierDAO;
        this.typeProductDAO = typeProductDAO;
        nombreEntidad = "productos";
    }


    @GetMapping("/TypeProduct/{typeProduct}")
    public ResponseEntity<?> getProductByTypeProduct(@PathVariable String typeProduct){
        Iterable<Product> products = new ArrayList<>();
        Map<String, Object> response = new HashMap<>();
        try{
            Category categorySearch = typeProductDAO.getByName(typeProduct);
            if (categorySearch != null){
                products = service.getProductByTypeProduct(typeProduct);
                return new ResponseEntity<>(products, HttpStatus.OK);

            }else {
                response.put("Mensaje: ", "Esta categoria no tiene productos");
                return  new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        }catch (DataAccessException e){
            response.put("Mensaje: ", "Error al obtener los productos");
            response.put("Error: " , e.getMessage());
            return  new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/search/{name}")
    public ResponseEntity<?> findByNameProduct(@PathVariable String name){
        List<Product> products = new ArrayList<>();
        Map<String, Object> response = new HashMap<>();
        try {
            products = (List<Product>) service.buscarProductosPorNombre(name);
            if (!products.isEmpty()){
               return new ResponseEntity<>(products, HttpStatus.OK);
            }else{
                response.put("Mensaje","No se encontraron productos con ese nombre");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        }catch (DataAccessException e){
            response.put("Mensaje: ", "Error al obtener los productos");
            response.put("Error: " , e.getMessage());
            return  new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping
    public ResponseEntity<?> saveProduct(@RequestBody Product product){

        Category category = typeProductDAO.getByName(product.getCategory().getName());
        Supplier supplier= supplierDAO.findByName(product.getSupplier().getName());
        Map<String, Object> response = new HashMap<>();
        try {
            if(service.existeByNameProduct(product.getName())){
                response.put("Mensaje", "El producto ya existe");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }else if( category == null){
                response.put("Mensaje", "La categoria del producto no existe");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }else if (supplier == null){
                response.put("Mensaje", "El proovedor del producto no existe");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }else {
                product.setCategory(category);
                product.setSupplier(supplier);
                service.save(product);
                return new ResponseEntity<Product>(product, HttpStatus.CREATED);
            }
        }catch (DataAccessException e){

            response.put("Error: " , e.getMessage());
            return  new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> editarProducto(@RequestBody Product product, @PathVariable Long id){
        Optional<Product> productActual = service.findById(id);
        Product productUpdate = null;
        Map<String, Object> response = new HashMap<>();
        try{
            if(!productActual.isPresent()){
                response.put("Mensaje: ", "El producto que deseas editar no existe");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }else{
                productUpdate = productActual.get();
                productUpdate.setName(product.getName());
                productUpdate.setUnitWeight(productUpdate.getUnitWeight());
                Category categoryName = typeProductDAO.getByName(product.getCategory().getName());
                Supplier supplier = supplierDAO.findByName(product.getSupplier().getName());
                if(categoryName != null && supplier != null){
                    service.save(productUpdate);
                    return new ResponseEntity<Product>(productUpdate, HttpStatus.OK);
                }else{
                    response.put("Mensaje: ", "No se puede editar el producto porque el tipo de producto o el proveedor no es correcto");
                    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
                }
            }
        }catch (DataAccessException e){
            response.put("Error: " , e.getMessage());
            return  new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
