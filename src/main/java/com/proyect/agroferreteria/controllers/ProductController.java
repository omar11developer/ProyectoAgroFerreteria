package com.proyect.agroferreteria.controllers;

import com.proyect.agroferreteria.models.entity.Product;
import com.proyect.agroferreteria.models.entity.TypeProduct;
import com.proyect.agroferreteria.services.contracts.ProductService;
import com.proyect.agroferreteria.services.contracts.TypeProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/agroferreteria")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private TypeProductService typeProductService;

    @GetMapping("/productos")
    public ResponseEntity<?> getProducts(){
        List<Product> products = new ArrayList<>();
        Map<String, Object> response = new HashMap<>();

        try {
            products = productService.findAll();
            if (products.isEmpty()){
                response.put("Mensaje","No se econtraron productos");
                return  new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        }catch (DataAccessException e){
            response.put("Mensaje","Error al listar");
            response.put("Error",e.getMessage());
            return  new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/productos/TypeProduct/{typeProduct}")
    public ResponseEntity<?> getProductByTypeProduct(@PathVariable String typeProduct){
        List<Product> products = new ArrayList<>();
        Map<String, Object> response = new HashMap<>();
        try{
            products = productService.getProductByTypeProduct(typeProduct);
            if (products.isEmpty()){
                response.put("Mensaje: ", "Esta categoria no tiene productos");
                return  new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        }catch (DataAccessException e){
            response.put("Mensaje: ", "Error al obtener los productos");
            response.put("Error: " , e.getMessage());
            return  new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    @GetMapping("/productos/{name}")
    public ResponseEntity<?> findByNameProduct(@PathVariable String name){
        List<Product> products = new ArrayList<>();
        Map<String, Object> response = new HashMap<>();
        try {
            products = productService.buscarProductoPorNombre(name);
            if (products.isEmpty()){
                response.put("Mensaje","No se encontraron productos con ese nombre");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        }catch (DataAccessException e){
            response.put("Mensaje: ", "Error al obtener los productos");
            response.put("Error: " , e.getMessage());
            return  new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    @GetMapping("/productos/stocklower")
    public ResponseEntity<?> getProductsLowerStock(){
        Map<String, Object> response = new HashMap<>();
        List<Product> products = new ArrayList<>();
        try {
            products= productService.obtenerProductosBajosEnStock();
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
    @PostMapping("/productos")
    public ResponseEntity<?> saveProduct(@RequestBody Product product){
        Product productLocal = productService.findByName(product.getName());
        TypeProduct typeProduct = typeProductService.getByName(product.getTypeProduct().getName());
        Map<String, Object> response = new HashMap<>();
        if(productLocal != null  ){
            response.put("Mensaje","El producto ya existe");
            return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        }
        try{
            if (typeProduct != null) {
                product.setTypeProduct(typeProduct);
                productService.save(product);
            }else{
                response.put("Mensaje","El tipo de producto no se pudo encontrar");
                return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
            }

        } catch (DataAccessException e){
            response.put("Mensaje: ", "Error al guardar el producto");
            response.put("Error: ", e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(product, HttpStatus.CREATED);

    }
    @PutMapping("/productos/{id}")
    public ResponseEntity<?> editarProducto(@RequestBody Product product, @PathVariable Long id){
        Product productActual = productService.findById(id);
        Product productUpdate = null;
        Map<String, Object> response = new HashMap<>();

        if (productActual == null){
            response.put("Mensaje: ", "El producto que desea editar no existe");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        try {
            productActual.setName(product.getName());
            productActual.setUnitPrice(product.getUnitPrice());
            productActual.setUnitWeight(product.getUnitWeight());
            productActual.setStock(product.getStock());
            productActual.setTypeProduct(product.getTypeProduct());
            TypeProduct typeProductName = typeProductService.getByName(productActual.getTypeProduct().getName());
            if (typeProductName != null){
                productService.save(productActual);
                productUpdate= productActual;
            }else{
                response.put("Mensaje: ", "No se puede editar el producto porque el tipo de producto no es correcto");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        }catch (DataAccessException e){
            response.put("Mensaje: ", "Error al actualizar el producto");
            response.put("Erro: ", e.getMessage());
        }
        response.put("Mensaje: ", "El producto ha sido actualizado con exito");
        response.put("Product: ", productUpdate);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
    @DeleteMapping("/productos/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
        Product productFind = productService.findById(id);
        Map<String, Object> response = new HashMap<>();
        try{
            if(productFind != null){
                productService.delete(id);
            }else{
                response.put("Mensaje: ", "No se pudo encontrar el rpoducto");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        }catch (DataAccessException e){
            response.put("Mensaje: ", "No se puede editar el producto porque el tipo de producto no es correcto");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        response.put("Mensaje: ", "El producto se elimino correctamente");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



}
