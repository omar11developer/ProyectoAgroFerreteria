package com.proyect.agroferreteria.repository;

import com.proyect.agroferreteria.models.entity.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface CategoryRepository extends CrudRepository<Category, Long> {
    public Category findByName(String name);

    @Query("Select case when count(t) > 0 then true else false end from Category  t where t.name = :name")
    boolean existsTypeProductName(@Param("name") String name);
}
