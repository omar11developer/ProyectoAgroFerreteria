package com.proyect.agroferreteria.repository;

import com.proyect.agroferreteria.models.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface IUser extends CrudRepository<User, Long> {

}
