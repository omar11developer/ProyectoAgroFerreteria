package com.proyect.agroferreteria.services.implementation;

import com.proyect.agroferreteria.models.entity.Product;
import com.proyect.agroferreteria.repository.IProductRepository;
import com.proyect.agroferreteria.services.contracts.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductImpl implements IProductService {
    @Autowired
    private IProductRepository productRepository;
    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    @Transactional
    public void save(Product product) {
        if(product != null){
            productRepository.save(product);
        }
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

}
