package com.proyect.agroferreteria.services.implementation;

import com.proyect.agroferreteria.models.entity.Client;
import com.proyect.agroferreteria.repository.ClientRepository;
import com.proyect.agroferreteria.services.contracts.ClientDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientImpl extends GenericoImpl<Client, ClientRepository> implements ClientDAO {
    @Autowired
    public ClientImpl(ClientRepository repository) {
        super(repository);
    }

    @Override
    public Optional<Client> buscarCliente(String name) {
        return repository.findByName(name);
    }

    @Override
    public Optional<Client> findByIdentification(String identificacion) {
        return repository.findByIdentification(identificacion);
    }

    @Override
    public Optional<Client> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public boolean existsByIdentificationAndNotCurrentId(String identification, Long id) {
        return repository.existsByIdentificationAndNotCurrentId(identification, id);
    }

    @Override
    public boolean existsByEmailAndNotCurrentId(String email, Long clientId) {
        return repository.existsByEmailAndNotCurrentId(email,clientId);
    }


    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }
}
