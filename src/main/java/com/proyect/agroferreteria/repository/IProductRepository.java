package com.proyect.agroferreteria.repository;

import com.proyect.agroferreteria.models.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IProductRepository extends CrudRepository<Product, Long> {
    //Iterable<Product> findProductByTypeProduct(String nameTypeProduct);
    public Product findByName(String name);

    @Query("select p from Product p join fetch p.typeProduct t where upper(t.name) = upper(?1)")
    Iterable<Product> buscarProductoPorTyipoDeProducto(String typeProduct);

    @Query("select p from Product  p where p.name like %?1%")
    Iterable<Product> buscarProductoPorNombre(String name);

    @Query("select p from Product p where p.stock < 10")
    Iterable<Product> obtenerProductosBajosEnStock();

}
