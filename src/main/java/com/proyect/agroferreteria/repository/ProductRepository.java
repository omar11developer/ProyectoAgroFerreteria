package com.proyect.agroferreteria.repository;

import com.proyect.agroferreteria.models.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends CrudRepository<Product, Long> {
    //Iterable<Product> findProductByTypeProduct(String nameTypeProduct);
   // @Query("select p from Product p where p.name = ?1")
    @Query("Select case when count(p) > 0 then true else false end from Product  p where p.name = :name")
    boolean existsByNameProduct(@Param("name") String name);

    @Query("select p from Product p join fetch p.category t where upper(t.name) = upper(?1)")
    Iterable<Product> buscarProductoPorTyipoDeProducto(String category);

    @Query("select p from Product  p where p.name like %?1%")
    Iterable<Product> buscarProductoPorNombre(String name);
    @Query("select p from Product p join fetch p.supplier s where upper(s.name) = upper(?1)")
    Iterable<Product> getProductsBySupplierIgnoreCase(String supplier);

    Product findByName(String name);

}
