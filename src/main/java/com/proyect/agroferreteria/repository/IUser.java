package com.proyect.agroferreteria.repository;

import com.proyect.agroferreteria.models.entity.Users;
import org.springframework.data.repository.CrudRepository;

public interface IUser extends CrudRepository<Users, Long> {

}
