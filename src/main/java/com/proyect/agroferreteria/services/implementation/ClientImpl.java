package com.proyect.agroferreteria.services.implementation;

import com.proyect.agroferreteria.models.entity.Client;
import com.proyect.agroferreteria.repository.IClientRepository;
import com.proyect.agroferreteria.services.contracts.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientImpl implements IClientService {

    private final IClientRepository clienteRepository;

    @Autowired
    public ClientImpl(IClientRepository clientRepository) {
        this.clienteRepository = clientRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Client> findAll() {
        return (List<Client>) clienteRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Client findById(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Client save(Client cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }

    @Override
    public boolean existsByIdentification(String identification) {
        return clienteRepository.existsByIdentification(identification);
    }
    @Override
    public boolean existsByEmail(String email) {
        if (!isValidEmail(email)) {
            return false; // El formato del correo electrónico no es válido
        }
        return clienteRepository.existsByEmail(email);
    }

    private boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(regex);
    }

}
