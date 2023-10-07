package com.proyect.agroferreteria.services.implementation;

import com.proyect.agroferreteria.models.entity.UserRols;
import com.proyect.agroferreteria.models.entity.Users;
import com.proyect.agroferreteria.repository.IUsersRepository;
import com.proyect.agroferreteria.repository.RolRepository;
import com.proyect.agroferreteria.services.contracts.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class UserImpl implements IUserService {
    @Autowired
    private IUsersRepository userRepository;

    @Autowired
    private RolRepository rolRepository;


    @Override
    public List<Users> AllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Users saveUser(Users user, Set<UserRols> userRols) throws Exception {
        Users userLocal = userRepository.findByUserName(user.getUserName());
        if(userLocal != null){
            throw new Exception("El Usuario ya existe");
        }else{
            for (UserRols userRol:userRols){
                rolRepository.save(userRol.getRol());
            }
            user.getUsersRoles().addAll(userRols);
            userLocal = userRepository.save(user);
        }
        return userLocal;

    }

    @Override
    public Users getByUserName(String username) {
        return userRepository.findByUserName(username);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
