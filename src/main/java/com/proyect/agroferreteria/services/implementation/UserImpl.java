package com.proyect.agroferreteria.services.implementation;

import com.proyect.agroferreteria.models.entity.User;
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
    public List<User> finAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public void save(User user) {
        if(user != null){
            userRepository.save(user);
        }
    }

    @Override
    public User findOneId(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        if(id > 0){
            userRepository.deleteById(id);
        }
    }
}
