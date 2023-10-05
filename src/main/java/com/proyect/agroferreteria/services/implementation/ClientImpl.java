package com.proyect.agroferreteria.services.implementation;

import com.proyect.agroferreteria.models.entity.Client;
import com.proyect.agroferreteria.repository.IClient;
import com.proyect.agroferreteria.services.contracts.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientImpl implements IClientService {
    @Autowired
    private IClient clientRespository;

    @Override
    public List<Client> findAll() {
        return (List<Client>) clientRespository.findAll();
    }

    @Override
    public void save(Client client) {
        if (client != null){
            clientRespository.save(client);
        }
    }

    @Override
    public Client findById(Long id) {
        return clientRespository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        if(id > 0){
            clientRespository.deleteById(id);
        }
    }
}
