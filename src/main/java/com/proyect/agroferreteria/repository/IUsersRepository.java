package com.proyect.agroferreteria.repository;

import com.proyect.agroferreteria.models.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface IUsersRepository extends JpaRepository<Users, Long> {

    public Users findByUserName(String username);
}
