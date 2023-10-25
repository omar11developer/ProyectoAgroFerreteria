package com.proyect.agroferreteria.controllers;

import com.proyect.agroferreteria.models.entity.Product;
import com.proyect.agroferreteria.models.entity.TypeProduct;
import com.proyect.agroferreteria.services.contracts.ProductDAO;
import com.proyect.agroferreteria.services.contracts.TypeProductDAO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/agroferreteria/productos")
public class ProductController extends GenericoController<Product, ProductDAO> {



    private final TypeProductDAO typeProductDAO;
    @Autowired
    public ProductController(ProductDAO service, TypeProductDAO typeProductDAO) {
        super(service);
        this.typeProductDAO = typeProductDAO;
        nombreEntidad = "productos";
    }


    @GetMapping("/TypeProduct/{typeProduct}")
    public ResponseEntity<?> getProductByTypeProduct(@PathVariable String typeProduct){
        Iterable<Product> products = new ArrayList<>();
        Map<String, Object> response = new HashMap<>();

        try{
            TypeProduct typeProductSearch = typeProductDAO.getByName(typeProduct);
            if (typeProductSearch != null){
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
    @GetMapping("/stocklower")
    public ResponseEntity<?> getProductsLowerStock(){
        Map<String, Object> response = new HashMap<>();
        List<Product> products = new ArrayList<>();
        try {
            products= (List<Product>) service.obtenerProductosBajosEnStock();
            if(products.isEmpty()){
                response.put("Mensaje","No se encontraron productos con stock menores a 10");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        }catch (DataAccessException e){
            response.put("Mensaje: ", "Error al obtener los productos");
            response.put("Error: " , e.getMessage());
            return  new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
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
        Product productNew = null;
        TypeProduct typeProduct = typeProductDAO.getByName(product.getTypeProduct().getName());
        Map<String, Object> response = new HashMap<>();
        try {
            if(service.existeByNameProduct(product.getName())){
                response.put("Mensaje", "El producto ya existe");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }else if( typeProduct == null){
                response.put("Mensaje", "La categoria del producto no existe");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }else {
                product.setTypeProduct(typeProduct);
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
                productUpdate.setStock(productUpdate.getStock());
                TypeProduct typeProductName = typeProductDAO.getByName(product.getTypeProduct().getName());
                if(typeProductName != null){
                    service.save(productUpdate);
                    return new ResponseEntity<Product>(productUpdate, HttpStatus.OK);
                }else{
                    response.put("Mensaje: ", "No se puede editar el producto porque el tipo de producto no es correcto");
                    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
                }
            }
        }catch (DataAccessException e){
            response.put("Error: " , e.getMessage());
            return  new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
