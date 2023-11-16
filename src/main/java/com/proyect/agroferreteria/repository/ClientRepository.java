package com.proyect.agroferreteria.repository;

import com.proyect.agroferreteria.models.entity.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ClientRepository extends CrudRepository<Client, Long> {

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Client c WHERE c.identification = :identification and  c.idClient != :clientId")
    boolean existsByIdentificationAndNotCurrentId(@Param("identification") String identification, @Param("clientId") Long clientId);
    @Query("select CASE when count(c) > 0 then true  else  false  end from Client  c where c.email = :email and c.idClient != :clientId")
    boolean existsByEmailAndNotCurrentId(@Param("email") String email, @Param("clientId") Long clientId);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Client c WHERE c.email = :email")
    boolean existsByEmail(@Param("email") String email);

    Optional<Client> findByName(String name);
    Optional<Client> findByIdentification(String identificacion);
    Optional<Client> findByEmail(String email);
}

