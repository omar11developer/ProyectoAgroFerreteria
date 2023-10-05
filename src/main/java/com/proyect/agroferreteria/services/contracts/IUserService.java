package com.proyect.agroferreteria.services.contracts;

import com.proyect.agroferreteria.models.entity.User;

import java.util.List;

public interface IUserService {
    public List<User> finAll();
    public void save(User user);
    public User findOneId(Long id);

    public void delete(Long id);
}
