package com.proyect.agroferreteria.repository;

import com.proyect.agroferreteria.models.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {

    public Users findByUserName(String username);
}
