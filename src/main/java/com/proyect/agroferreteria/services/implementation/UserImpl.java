package com.proyect.agroferreteria.services.implementation;

import com.proyect.agroferreteria.models.entity.Users;
import com.proyect.agroferreteria.repository.IUser;
import com.proyect.agroferreteria.services.contracts.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserImpl implements IUserService {
    @Autowired
    private IUser userRepository;


    @Override
    public List<Users> finAll() {
        return (List<Users>) userRepository.findAll();
    }

    @Override
    public void save(Users users) {
        if(users != null){
            userRepository.save(users);
        }
    }

    @Override
    public Users findOneId(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        if(id > 0){
            userRepository.deleteById(id);
        }
    }
}
