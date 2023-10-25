package com.proyect.agroferreteria.services.contracts;

import com.proyect.agroferreteria.models.entity.UserRols;
import com.proyect.agroferreteria.models.entity.Users;

import java.util.List;

public interface UserService {

    public List<Users> AllUsers();

    public Users saveUser(Users users, List<UserRols> userRols) throws Exception;

    public Users getByUserName(String username);

    public void deleteUser(Long id);
}
