package com.proyect.agroferreteria.services.contracts;

import com.proyect.agroferreteria.models.entity.Product;
import com.proyect.agroferreteria.models.entity.TypeProduct;

import java.util.List;

public interface IProductService {
    public List<Product> findAll();
    public void save(Product product);

    public Product findById(Long id);

    public void delete(Long id);
    public Product findByName(String name);

    public List<Product> getProductByTypeProduct(String typeProduct);

    public List<Product> buscarProductoPorNombre(String name);

    public List<Product> obtenerProductosBajosEnStock();

}
