package com.proyect.agroferreteria.services.contracts;

import com.proyect.agroferreteria.models.entity.Users;

import java.util.List;

public interface IUserService {
    public List<Users> findAll();
    public void save(Users users);
    public Users findOneId(Long id);

    public void delete(Long id);
}
