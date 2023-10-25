package com.proyect.agroferreteria.services.implementation;

import com.proyect.agroferreteria.models.entity.Product;
import com.proyect.agroferreteria.repository.ProductRepository;
import com.proyect.agroferreteria.services.contracts.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductImpl extends GenericoImpl<Product,ProductRepository> implements ProductDAO{

    public ProductImpl(ProductRepository repository) {
        super(repository);
    }


    @Override
    public boolean existeByNameProduct(String name) {
        return repository.existsByNameProduct(name);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Product> getProductByTypeProduct(String typeProduct) {
        return  repository.buscarProductoPorTyipoDeProducto(typeProduct);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Product> buscarProductosPorNombre(String name) {
        return repository.buscarProductoPorNombre(name);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Product> obtenerProductosBajosEnStock() {
        return repository.obtenerProductosBajosEnStock();
    }
}
