package com.proyect.agroferreteria.services.contracts;

import com.proyect.agroferreteria.models.entity.Product;

public interface ProductDAO extends GenericoDAO<Product> {

    boolean existeByNameProduct(String name);

    public Iterable<Product> getProductByTypeProduct(String typeProduct);

    public Iterable<Product> buscarProductosPorNombre(String name);



    Product findByName(String name);

}
