package com.proyect.agroferreteria.services.implementation;

import com.proyect.agroferreteria.models.entity.Product;
import com.proyect.agroferreteria.repository.ProductRepository;
import com.proyect.agroferreteria.services.contracts.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public void save(Product product) {

        productRepository.save(product);
    }



    @Override
    @Transactional(readOnly = true)
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if(id > 0){
            productRepository.deleteById(id);
        }
    }

    @Override
    public Product findByName(String name) {

        return productRepository.findByName(name);
    }

    @Override
    public List<Product> getProductByTypeProduct(String typeProduct) {
        return (List<Product>) productRepository.buscarProductoPorTyipoDeProducto(typeProduct);
    }

    @Override
    public List<Product> buscarProductoPorNombre(String name) {
        return (List<Product>) productRepository.buscarProductoPorNombre(name);
    }

    @Override
    public List<Product> obtenerProductosBajosEnStock() {
        return (List<Product>) productRepository.obtenerProductosBajosEnStock();
    }

}
