package com.proyect.agroferreteria.services.contracts;

import com.proyect.agroferreteria.models.entity.Client;

import java.util.List;

public interface ClientService {

    public List<Client> findAll();

    public Client findById(Long id);

    public Client save(Client client);

    public void delete(Long id);

    boolean existsByIdentification(String identification);

    boolean existsByEmail(String email);
}

