package com.proyect.agroferreteria.repository;

import com.proyect.agroferreteria.models.entity.Users;
import org.springframework.data.repository.CrudRepository;

public interface IUserRepository extends CrudRepository<Users, Long> {
    public Users findByUsername(String username);
}
