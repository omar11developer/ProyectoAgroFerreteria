package com.proyect.agroferreteria.repository;

import com.proyect.agroferreteria.models.entity.TypeProduct;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface TypeProductRepository extends CrudRepository<TypeProduct, Long> {
    public TypeProduct findByName(String name);

    @Query("Select case when count(t) > 0 then true else false end from TypeProduct  t where t.name = :name")
    boolean existsTypeProductName(@Param("name") String name);
}
