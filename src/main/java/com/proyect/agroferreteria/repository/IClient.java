package com.proyect.agroferreteria.repository;

import com.proyect.agroferreteria.models.entity.Client;
import org.springframework.data.repository.CrudRepository;

public interface IClient extends CrudRepository<Client, Long> {
}