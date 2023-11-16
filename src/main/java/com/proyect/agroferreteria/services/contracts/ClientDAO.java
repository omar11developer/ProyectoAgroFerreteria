package com.proyect.agroferreteria.services.contracts;

import com.proyect.agroferreteria.models.entity.Client;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ClientDAO extends GenericoDAO<Client> {

    Optional<Client> buscarCliente(String name);
    Optional<Client> findByIdentification(String identificacion);
    Optional<Client> findByEmail(String email);
    boolean existsByIdentificationAndNotCurrentId(String identification, Long id);
    boolean existsByEmailAndNotCurrentId(@Param("email") String email, @Param("clientId") Long clientId);

    boolean existsByEmail(String email);
}

