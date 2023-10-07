package com.proyect.agroferreteria.services.contracts;

import com.proyect.agroferreteria.models.entity.UserRols;
import com.proyect.agroferreteria.models.entity.Users;

import java.util.List;
import java.util.Set;

public interface IUserService {

    public List<Users> AllUsers();

    public Users saveUser(Users users, Set<UserRols> userRols) throws Exception;

    public Users getByUserName(String username);

    public void deleteUser(Long id);
}
