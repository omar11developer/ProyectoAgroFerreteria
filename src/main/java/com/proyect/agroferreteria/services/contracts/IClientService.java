package com.proyect.agroferreteria.services.contracts;

import com.proyect.agroferreteria.models.entity.Client;

import java.util.List;

public interface IClientService {
    public List<Client> findAll();
    public void save(Client client);
    public Client findById(Long id);
    public void delete(Long id);
}
