package com.proyect.agroferreteria.services.implementation;

import com.proyect.agroferreteria.models.entity.Product;
import com.proyect.agroferreteria.repository.IProduct;
import com.proyect.agroferreteria.services.contracts.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductImpl implements IProductService {
    @Autowired
    private IProduct productRepository;
    @Override
    public List<Product> findAll() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public void save(Product product) {
        if(product != null){
            productRepository.save(product);
        }
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        if(id > 0){
            productRepository.deleteById(id);
        }
    }
}
